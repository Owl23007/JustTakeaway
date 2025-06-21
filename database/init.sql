-- JustTakeaway 数据库初始化脚本
-- 创建数据库
CREATE DATABASE IF NOT EXISTS `takeaway` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE `takeaway`;

-- 员工信息表
CREATE TABLE `employee` (
  `id` bigint NOT NULL COMMENT '主键',
  `name` varchar(32) NOT NULL COMMENT '姓名',
  `username` varchar(32) NOT NULL COMMENT '用户名',
  `password` varchar(64) NOT NULL COMMENT '密码',
  `phone` varchar(11) NOT NULL COMMENT '手机号',
  `sex` varchar(2) NOT NULL COMMENT '性别',
  `id_number` varchar(18) NOT NULL COMMENT '身份证号',
  `status` int NOT NULL DEFAULT '1' COMMENT '状态 0:禁用，1:正常',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `create_user` bigint NOT NULL COMMENT '创建人',
  `update_user` bigint NOT NULL COMMENT '修改人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='员工信息';

-- 分类表
CREATE TABLE `category` (
  `id` bigint NOT NULL COMMENT '主键',
  `type` int DEFAULT NULL COMMENT '类型   1 菜品分类 2 套餐分类',
  `name` varchar(64) NOT NULL COMMENT '分类名称',
  `sort` int NOT NULL DEFAULT '0' COMMENT '顺序',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `create_user` bigint NOT NULL COMMENT '创建人',
  `update_user` bigint NOT NULL COMMENT '修改人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_category_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='菜品及套餐分类';

-- 菜品表
CREATE TABLE `dish` (
  `id` bigint NOT NULL COMMENT '主键',
  `name` varchar(64) NOT NULL COMMENT '菜品名称',
  `category_id` bigint NOT NULL COMMENT '菜品分类id',
  `price` decimal(10,2) DEFAULT NULL COMMENT '菜品价格',
  `code` varchar(64) NOT NULL COMMENT '商品码',
  `image` varchar(200) NOT NULL COMMENT '图片',
  `description` varchar(400) DEFAULT NULL COMMENT '描述信息',
  `status` int NOT NULL DEFAULT '1' COMMENT '0 停售 1 起售',
  `sort` int NOT NULL DEFAULT '0' COMMENT '顺序',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `create_user` bigint NOT NULL COMMENT '创建人',
  `update_user` bigint NOT NULL COMMENT '修改人',
  `is_deleted` int NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_dish_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='菜品管理';

-- 菜品口味关系表
CREATE TABLE `dish_flavor` (
  `id` bigint NOT NULL COMMENT '主键',
  `dish_id` bigint NOT NULL COMMENT '菜品',
  `name` varchar(64) NOT NULL COMMENT '口味名称',
  `value` varchar(500) DEFAULT NULL COMMENT '口味数据list',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `create_user` bigint NOT NULL COMMENT '创建人',
  `update_user` bigint NOT NULL COMMENT '修改人',
  `is_deleted` int NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='菜品口味关系表';

-- 套餐表
CREATE TABLE `setmeal` (
  `id` bigint NOT NULL COMMENT '主键',
  `category_id` bigint NOT NULL COMMENT '菜品分类id',
  `name` varchar(64) NOT NULL COMMENT '套餐名称',
  `price` decimal(10,2) NOT NULL COMMENT '套餐价格',
  `status` int DEFAULT NULL COMMENT '状态 0:停用 1:启用',
  `code` varchar(32) DEFAULT NULL COMMENT '编码',
  `description` varchar(512) DEFAULT NULL COMMENT '描述信息',
  `image` varchar(255) DEFAULT NULL COMMENT '图片',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `create_user` bigint NOT NULL COMMENT '创建人',
  `update_user` bigint NOT NULL COMMENT '修改人',
  `is_deleted` int NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_setmeal_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='套餐';

-- 套餐菜品关系表
CREATE TABLE `setmeal_dish` (
  `id` bigint NOT NULL COMMENT '主键',
  `setmeal_id` varchar(32) NOT NULL COMMENT '套餐id ',
  `dish_id` varchar(32) NOT NULL COMMENT '菜品id',
  `name` varchar(32) DEFAULT NULL COMMENT '菜品名称 （冗余字段）',
  `price` decimal(10,2) DEFAULT NULL COMMENT '菜品原价（冗余字段）',
  `copies` int NOT NULL COMMENT '份数',
  `sort` int NOT NULL DEFAULT '0' COMMENT '排序',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `create_user` bigint NOT NULL COMMENT '创建人',
  `update_user` bigint NOT NULL COMMENT '修改人',
  `is_deleted` int NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='套餐菜品关系';

-- 用户信息表
CREATE TABLE `user` (
  `id` bigint NOT NULL COMMENT '主键',
  `name` varchar(50) DEFAULT NULL COMMENT '姓名',
  `phone` varchar(100) NOT NULL COMMENT '手机号',
  `sex` varchar(2) DEFAULT NULL COMMENT '性别',
  `id_number` varchar(18) DEFAULT NULL COMMENT '身份证号',
  `avatar` varchar(500) DEFAULT NULL COMMENT '头像',
  `status` int DEFAULT '0' COMMENT '状态 0:禁用，1:正常',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户信息';

-- 地址管理表
CREATE TABLE `address_book` (
  `id` bigint NOT NULL COMMENT '主键',
  `user_id` bigint NOT NULL COMMENT '用户id',
  `consignee` varchar(50) NOT NULL COMMENT '收货人',
  `sex` tinyint NOT NULL COMMENT '性别 0 女 1 男',
  `phone` varchar(11) NOT NULL COMMENT '手机号',
  `province_code` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '省级区划编号',
  `province_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '省级名称',
  `city_code` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '市级区划编号',
  `city_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '市级名称',
  `district_code` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '区级区划编号',
  `district_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '区级名称',
  `detail` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '详细地址',
  `label` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '标签',
  `is_default` tinyint(1) NOT NULL DEFAULT '0' COMMENT '默认 0 否 1是',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `create_user` bigint NOT NULL COMMENT '创建人',
  `update_user` bigint NOT NULL COMMENT '修改人',
  `is_deleted` int NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='地址管理';

-- 购物车表
CREATE TABLE `shopping_cart` (
  `id` bigint NOT NULL COMMENT '主键',
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `image` varchar(100) DEFAULT NULL COMMENT '图片',
  `user_id` bigint NOT NULL COMMENT '主键',
  `dish_id` bigint DEFAULT NULL COMMENT '菜品id',
  `setmeal_id` bigint DEFAULT NULL COMMENT '套餐id',
  `dish_flavor` varchar(50) DEFAULT NULL COMMENT '口味',
  `number` int NOT NULL DEFAULT '1' COMMENT '数量',
  `amount` decimal(10,2) NOT NULL COMMENT '金额',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='购物车';

-- 订单表
CREATE TABLE `orders` (
  `id` bigint NOT NULL COMMENT '主键',
  `number` varchar(50) DEFAULT NULL COMMENT '订单号',
  `status` int NOT NULL DEFAULT '1' COMMENT '订单状态 1待付款，2待派送，3已派送，4已完成，5已取消',
  `user_id` bigint NOT NULL COMMENT '下单用户',
  `address_book_id` bigint NOT NULL COMMENT '地址id',
  `order_time` datetime NOT NULL COMMENT '下单时间',
  `checkout_time` datetime DEFAULT NULL COMMENT '结账时间',
  `pay_method` int NOT NULL DEFAULT '1' COMMENT '支付方式 1微信,2支付宝',
  `amount` decimal(10,2) NOT NULL COMMENT '实收金额',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `phone` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `consignee` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='订单表';

-- 订单明细表
CREATE TABLE `order_detail` (
  `id` bigint NOT NULL COMMENT '主键',
  `name` varchar(50) DEFAULT NULL COMMENT '名字',
  `image` varchar(100) DEFAULT NULL COMMENT '图片',
  `order_id` bigint NOT NULL COMMENT '订单id',
  `dish_id` bigint DEFAULT NULL COMMENT '菜品id',
  `setmeal_id` bigint DEFAULT NULL COMMENT '套餐id',
  `dish_flavor` varchar(50) DEFAULT NULL COMMENT '口味',
  `number` int NOT NULL DEFAULT '1' COMMENT '数量',
  `amount` decimal(10,2) NOT NULL COMMENT '金额',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='订单明细表';

-- 插入初始管理员账户 (密码: admin123)
INSERT INTO `employee` (id, name, username, password, phone, sex, id_number, status, create_time, update_time, create_user, update_user) 
VALUES (1, '管理员', 'admin', '$2a$10$oLsHiKbAW6Bd4SjQJ9kv3.xhWpcUJUb7d5bfz1N4rI8jBKC5K4n8G', '13888888888', '1', '110101199001011234', 1, NOW(), NOW(), 1, 1);

-- 插入基础分类数据
INSERT INTO `category` (id, type, name, sort, create_time, update_time, create_user, update_user) VALUES
(1001, 1, '川菜', 1, NOW(), NOW(), 1, 1),
(1002, 1, '粤菜', 2, NOW(), NOW(), 1, 1),
(1003, 1, '湘菜', 3, NOW(), NOW(), 1, 1),
(1004, 2, '商务套餐', 1, NOW(), NOW(), 1, 1),
(1005, 2, '儿童套餐', 2, NOW(), NOW(), 1, 1);
