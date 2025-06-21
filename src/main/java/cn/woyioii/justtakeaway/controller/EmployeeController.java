package cn.woyioii.justtakeaway.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import cn.woyioii.justtakeaway.common.BaseContext;
import cn.woyioii.justtakeaway.common.R;
import cn.woyioii.justtakeaway.entity.Employee;
import cn.woyioii.justtakeaway.service.EmployeeService;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public EmployeeController(EmployeeService employeeService, PasswordEncoder passwordEncoder) {
        this.employeeService = employeeService;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * 员工登录
     * 
     * @param employee 包含用户名和密码的员工实体对象，用于验证用户身份。
     * @return 返回 R<Employee> 类型结果，包含登录成功后的员工信息或错误提示。
     */

    @PostMapping("/login")
    public R<Employee> login(HttpServletRequest request, @RequestBody Employee employee) {
        // 根据用户名查询数据库
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getUsername, employee.getUsername());
        Employee emp = employeeService.getOne(queryWrapper);

        // 如果没有查询到则返回登录失败结果
        if (emp == null) {
            return R.error("登录失败");
        }

        // 检查是否是旧的 MD5 密码
        String oldMd5Password = DigestUtils.md5DigestAsHex(employee.getPassword().getBytes());
        boolean passwordMatch = false;
        // 判断数据库中存储的密码是否为旧的MD5加密（32位长度且全为16进制字符）
        if (emp.getPassword() != null && emp.getPassword().matches("^[a-fA-F0-9]{32}$")) {
            // 用MD5加密前端传来的明文密码，与数据库中的MD5密码比对
            if (emp.getPassword().equals(oldMd5Password)) {
                // 如果匹配，说明是旧密码，升级为BCrypt
                emp.setPassword(passwordEncoder.encode(employee.getPassword()));
                log.info("员工 {} 的密码已升级为 BCrypt", emp.getUsername());
                employeeService.updateById(emp);
                passwordMatch = true;
            }
        } else {
            // 验证 BCrypt 密码
            passwordMatch = passwordEncoder.matches(employee.getPassword(), emp.getPassword());
        }

        if (!passwordMatch) {
            return R.error("登录失败");
        }

        // 检查账号状态
        if (emp.getStatus() == 0) {
            return R.error("账号已禁用");
        }

        // 创建认证令牌并设置到上下文
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                emp.getUsername(),
                null,
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 存储员工 ID 到 session 中
        request.getSession().setAttribute("employee", emp.getId());
        // 打印 authentication 信息
        log.info("登录时 authentication: {}", authentication);

        log.info("员工 {} 登录成功", emp.getUsername());

        return R.success(emp);
    }

    /**
     * 员工退出
     * 
     * @return 返回 R<String> 类型结果，包含退出成功提示信息。
     */
    @PostMapping("/logout")
    public R<String> logout() {
        SecurityContextHolder.clearContext();
        log.info("退出成功");
        return R.success("退出成功");
    }

    /**
     * 新增员工
     *
     * @param request  HttpServletRequest 对象，用于获取当前登录用户的信息。
     * @param employee 包含员工信息的实体对象，需设置密码、创建时间、更新人等。
     * @return 返回 R<String> 类型结果，包含新增成功提示信息。
     */
    @PostMapping
    public R<String> save(HttpServletRequest request, @RequestBody Employee employee) {
        log.info("新增员工，员工信息：{}", employee);

        // 参数验证
        if (employee.getUsername() == null || employee.getUsername().trim().isEmpty()) {
            return R.error("用户名不能为空");
        }
        if (employee.getName() == null || employee.getName().trim().isEmpty()) {
            return R.error("员工姓名不能为空");
        }

        // 检查用户名是否已存在
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getUsername, employee.getUsername());
        Employee existingEmployee = employeeService.getOne(queryWrapper);
        if (existingEmployee != null) {
            return R.error("用户名已存在");
        }

        Employee currentEmployee = getCurrentEmployee(request);
        if (currentEmployee != null) {
            // 如果有当前登录用户，设置到BaseContext中
            BaseContext.setCurrentId(currentEmployee.getId());
        }

        // 设置默认密码
        employee.setPassword(passwordEncoder.encode("123456"));
        // 设置启用状态
        employee.setStatus(1);

        // 处理必填字段的默认值
        if (employee.getIdNumber() == null || employee.getIdNumber().trim().isEmpty()) {
            employee.setIdNumber("000000000000000000"); // 默认身份证号
        }
        if (employee.getPhone() == null || employee.getPhone().trim().isEmpty()) {
            employee.setPhone("13800000000"); // 默认手机号
        }
        if (employee.getSex() == null || employee.getSex().trim().isEmpty()) {
            employee.setSex("男"); // 默认性别
        }

        // 性别值标准化处理：将数字转换为中文
        if ("1".equals(employee.getSex())) {
            employee.setSex("男");
        } else if ("0".equals(employee.getSex())) {
            employee.setSex("女");
        }

        try {
            employeeService.save(employee);
            log.info("员工 {} 新增成功，ID: {}", employee.getName(), employee.getId());
        } catch (Exception e) {
            log.error("新增员工失败：{}", e.getMessage(), e);
            return R.error("新增员工失败：" + e.getMessage());
        }

        return R.success("新增员工成功");
    }

    /**
     * 员工信息分页查询
     * 
     * @param page     当前页码
     * @param pageSize 每页显示的记录数
     * @param name     员工姓名，用于模糊查询
     * @return 返回 R<Page> 类型结果，包含分页查询结果
     */
    @GetMapping("/page")
    public R<Page<Employee>> page(int page, int pageSize, String name) {
        log.info("page = {},pageSize = {},name = {}", page, pageSize, name);

        // 构造分页构造器
        Page<Employee> pageInfo = new Page<>(page, pageSize);

        // 构造条件构造器
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        // 添加过滤条件
        queryWrapper.like(StringUtils.isNotEmpty(name), Employee::getName, name);
        // 添加排序条件
        queryWrapper.orderByDesc(Employee::getUpdateTime);

        // 执行查询
        employeeService.page(pageInfo, queryWrapper);

        return R.success(pageInfo);
    }

    /**
     * 修改员工信息
     *
     * @param request  HttpServletRequest 对象，用于获取当前登录用户的信息。
     * @param employee 包含员工信息的实体对象，需设置更新时间、更新人等。
     * @return 返回 R<String> 类型结果，包含修改成功提示信息。
     */
    @PutMapping
    public R<String> update(HttpServletRequest request, @RequestBody Employee employee) {
        log.info(employee.toString());

        Employee currentEmployee = getCurrentEmployee(request);
        if (currentEmployee == null) {
            return R.error("未登录或登录已过期或当前登录用户信息不存在");
        }

        // 设置更新信息
        employee.setUpdateTime(LocalDateTime.now());
        employee.setUpdateUser(currentEmployee.getId());

        employeeService.updateById(employee);

        return R.success("员工信息修改成功");
    }

    /**
     * 根据id查询员工信息
     * 
     * @param id 员工ID，用于查询对应的员工信息。
     * @return 返回 R<Employee> 类型结果，包含查询到的员工信息。
     */
    @GetMapping("/{id}")
    public R<Employee> getById(@PathVariable Long id) {
        log.info("根据id查询员工信息...");
        Employee employee = employeeService.getById(id);
        if (employee != null) {
            return R.success(employee);
        }
        return R.error("没有查询到对应员工信息");
    }

    /**
     * 辅助方法 —— 获取当前登录员工信息，若未登录或不存在则返回null
     *
     * @param request HTTP请求对象
     * @return 返回当前登录员工信息，若未登录或不存在则返回null
     */
    private Employee getCurrentEmployee(HttpServletRequest request) {
        Object empIdObj = request.getSession().getAttribute("employee");
        if (empIdObj == null) {
            log.warn("未登录或Session已过期，无法获取当前员工信息");
            return null;
        }
        Long empId;
        if (empIdObj instanceof Long) {
            empId = (Long) empIdObj;
        } else {
            try {
                empId = Long.valueOf(empIdObj.toString());
            } catch (NumberFormatException e) {
                log.error("Session中的employee属性无法转换为Long: {}", empIdObj);
                return null;
            }
        }
        Employee employee = employeeService.getById(empId);
        if (employee == null) {
            log.warn("Session中员工ID未查到员工信息: {}", empId);
        }
        return employee;
    }
}
