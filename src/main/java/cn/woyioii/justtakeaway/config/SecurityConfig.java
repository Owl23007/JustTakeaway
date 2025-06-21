package cn.woyioii.justtakeaway.config;

import cn.woyioii.justtakeaway.entity.Employee;
import cn.woyioii.justtakeaway.service.EmployeeService;

import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Spring Security 安全配置类
 * 包含数据库认证、BCrypt 加密、JWT Token 支持、会话管理等。
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true) // 启用 Spring Security 方法级安全
@RequiredArgsConstructor
public class SecurityConfig {

    private final EmployeeService employeeService;

    /**
     * 密码加密器，使用 BCryptPasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 自定义 UserDetailsService 实现，从数据库中加载用户信息
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            Employee employee = employeeService.getByUsername(username);
            if (employee == null) {
                throw new UsernameNotFoundException("用户不存在");
            }
            return new org.springframework.security.core.userdetails.User(
                    employee.getUsername(),
                    employee.getPassword(),
                    true, true, true, true,
                    AuthorityUtils.createAuthorityList("ROLE_USER")
            );
        };
    }

    /**
     * 构建 AuthenticationManager
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    /**
     * 安全过滤链配置
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http  .headers(headers -> headers
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin)) //  允许同源的 iframe 嵌套
            .csrf(AbstractHttpConfigurer::disable) //  禁用 CSRF 保护
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) //  禁用会话管理
                    .authorizeHttpRequests(auth -> auth //  配置 URL 授权
                .requestMatchers("/backend/**", "/front/**", "/employee/login", "/employee/logout").permitAll()
                .anyRequest().authenticated() //  其他请求都需要认证
            );

        return http.build();
    }
}

