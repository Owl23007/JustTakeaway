# JustTakeaway 外卖订餐系统

## 项目简介

JustTakeaway 是一个基于 Spring Boot 3.5 和 Java 21 开发的现代化外卖订餐系统，提供完整的餐厅管理和用户订餐功能。

## 技术栈

### 后端技术

- **Spring Boot 3.5.0** - 核心框架
- **Java 21** - 编程语言
- **Spring Security** - 安全认证框架
- **JWT** - 令牌认证
- **MyBatis-Plus 3.5.8** - ORM 框架
- **MySQL** - 数据库
- **Druid** - 数据库连接池

### 前端技术

- **Vue.js** - 前端框架
- **Element UI** - UI 组件库
- **Axios** - HTTP 客户端

## 系统功能

### 管理端功能

- 🔐 员工登录认证
- 👥 员工管理
- 📂 分类管理（菜品分类、套餐分类）
- 🍽️ 菜品管理（新增、修改、删除、启售/停售）
- 🍱 套餐管理（新增、修改、删除、启售/停售）
- 📋 订单管理
- 📊 数据统计

### 用户端功能

- 📱 用户登录/注册
- 🏠 地址管理
- 🛒 购物车功能
- 📝 在线下单
- 💰 订单支付
- 📋 订单查询

## 系统架构

```
JustTakeaway/
├── src/main/java/cn/woyioii/justtakeaway/
│   ├── common/           # 通用工具类
│   ├── config/           # 配置类
│   ├── controller/       # 控制器层
│   ├── dto/             # 数据传输对象
│   ├── entity/          # 实体类
│   ├── filter/          # 过滤器
│   ├── mapper/          # 数据访问层
│   ├── service/         # 业务逻辑层
│   └── util/            # 工具类
├── src/main/resources/
│   ├── backend/         # 管理端静态资源
│   ├── front/           # 用户端静态资源
│   └── application.yml  # 应用配置
└── src/test/            # 测试文件
```

## 快速开始

### 环境要求

- JDK 21+
- MySQL 8.0+
- Maven 3.6+

### 安装步骤

1. **克隆项目**

   ```bash
   git clone https://github.com/Owl23007/JustTakeaway.git
   cd JustTakeaway
   ```

2. **配置文件设置**

   - 复制 `src/main/resources/application.yml.template` 为 `application.yml`
   - 创建数据库 `takeaway`，可以使用 `database/init.sql` 脚本初始化
   - 修改 `application.yml` 中的配置信息：

   ```yaml
   spring:
     datasource:
       url: jdbc:mysql://localhost:3306/takeaway
       username: your_username
       password: your_password
   jwt:
     secret: your_jwt_secret_key # 请生成一个安全的JWT密钥
   ```

3. **编译项目**

   ```bash
   ./mvnw clean compile
   ```

4. **启动应用**

   ```bash
   ./mvnw spring-boot:run
   ```

5. **访问系统**
   - 管理端：http://localhost:8080/backend/index.html
   - 用户端：http://localhost:8080/front/index.html

## 配置说明

### ⚠️ 安全注意事项

1. **首次部署**：

   - 复制 `application.yml.template` 为 `application.yml`
   - 修改其中的数据库密码和 JWT 密钥
   - 确保 `application.yml` 不会被提交到版本控制

2. **JWT 密钥生成**：

   ```bash
   # 可以使用以下方式生成安全的JWT密钥
   openssl rand -base64 64
   ```

3. **环境变量配置** (推荐生产环境使用)：
   ```bash
   export DB_PASSWORD=your_database_password
   export JWT_SECRET=your_jwt_secret_key
   export UPLOAD_PATH=/path/to/upload/directory
   ```

### 主要配置项

```yaml
server:
  port: 8080 # 服务端口

spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/takeaway
    username: root
    password: your_password

jwt:
  secret: your_jwt_secret # JWT密钥
  expiration: 86400000 # 令牌有效期(24小时)

justtakeaway:
  path: D:\img\ # 文件上传路径
```

## API 接口

### 员工管理

- `POST /employee/login` - 员工登录
- `POST /employee/logout` - 员工登出
- `GET /employee/page` - 分页查询员工
- `POST /employee` - 新增员工
- `PUT /employee` - 修改员工信息

### 分类管理

- `GET /category/page` - 分页查询分类
- `POST /category` - 新增分类
- `PUT /category` - 修改分类
- `DELETE /category` - 删除分类

### 菜品管理

- `GET /dish/page` - 分页查询菜品
- `POST /dish` - 新增菜品
- `PUT /dish` - 修改菜品
- `DELETE /dish` - 删除菜品

### 用户管理

- `POST /user/sendMsg` - 发送验证码
- `POST /user/login` - 用户登录

## 项目特点

- ✨ **现代化技术栈** - 采用最新的 Spring Boot 3.5 和 Java 21
- 🔒 **安全认证** - 集成 Spring Security 和 JWT 认证
- 🎯 **代码规范** - 遵循 RESTful API 设计规范
- 📱 **响应式设计** - 支持多端访问
- 🔧 **易于扩展** - 清晰的分层架构，便于功能扩展

## 贡献指南

1. Fork 项目
2. 创建特性分支 (`git checkout -b feature/AmazingFeature`)
3. 提交更改 (`git commit -m 'Add some AmazingFeature'`)
4. 推送到分支 (`git push origin feature/AmazingFeature`)
5. 开启 Pull Request

## 许可证

本项目采用 MIT 许可证 - 查看 [LICENSE](LICENSE) 文件了解详情

## 联系方式

- 项目地址：https://github.com/Owl23007/JustTakeaway
- 开发者：Owl23007
- 邮箱：mailofowlwork@gmail.com

---

⭐ 如果这个项目对您有帮助，请给个星标支持！
