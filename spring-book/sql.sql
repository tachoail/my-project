CREATE DATABASE bookstore CHARACTER SET utf8mb4;
-- 切换到bookstore数据库（必须执行这一步，否则表会创建到其他数据库）
USE bookstore;

-- 1. 用户表（t_user）
CREATE TABLE t_user (
                        id INT PRIMARY KEY AUTO_INCREMENT,
                        username VARCHAR(50) NOT NULL UNIQUE,  -- 用户名（唯一）
                        password VARCHAR(100) NOT NULL,       -- 密码（MD5加密）
                        real_name VARCHAR(50) NOT NULL,       -- 真实姓名
                        id_card VARCHAR(20) NOT NULL,         -- 身份证号
                        phone VARCHAR(20) NOT NULL,           -- 手机号
                        address VARCHAR(255) NOT NULL,        -- 地址
                        role VARCHAR(10) NOT NULL,            -- 角色（admin：管理员，customer：顾客）
                        create_time DATETIME NOT NULL         -- 创建时间
);

-- 2. 图书表（t_book）
CREATE TABLE t_book (
                        id INT PRIMARY KEY AUTO_INCREMENT,
                        book_name VARCHAR(100) NOT NULL,      -- 书名
                        author VARCHAR(50) NOT NULL,          -- 作者
                        publisher VARCHAR(100) NOT NULL,      -- 出版社
                        intro TEXT NOT NULL,                  -- 图书简介
                        price DECIMAL(10,2) NOT NULL,         -- 价格
                        stock INT NOT NULL,                   -- 库存
                        create_time DATETIME NOT NULL         -- 创建时间
);

-- 3. 订单表（t_order）
CREATE TABLE t_order (
                         id INT PRIMARY KEY AUTO_INCREMENT,
                         order_no VARCHAR(50) NOT NULL UNIQUE, -- 订单编号（唯一）
                         user_id INT NOT NULL,                 -- 关联用户ID
                         total_price DECIMAL(10,2) NOT NULL,   -- 订单总价
                         create_time DATETIME NOT NULL,        -- 下单时间
                         is_modifiable TINYINT NOT NULL DEFAULT 1, -- 是否可修改（1：是，0：否）
                         FOREIGN KEY (user_id) REFERENCES t_user(id) -- 外键关联用户表
);

-- 4. 订单详情表（t_order_item）
CREATE TABLE t_order_item (
                              id INT PRIMARY KEY AUTO_INCREMENT,
                              order_id INT NOT NULL,                -- 关联订单ID
                              book_id INT NOT NULL,                 -- 关联图书ID
                              quantity INT NOT NULL,                -- 购买数量
                              price DECIMAL(10,2) NOT NULL,         -- 下单时的单价（快照）
                              FOREIGN KEY (order_id) REFERENCES t_order(id), -- 外键关联订单表
                              FOREIGN KEY (book_id) REFERENCES t_book(id)    -- 外键关联图书表
);

-- 初始化管理员账号（用户名：admin，密码：admin，MD5加密后为21232f297a57a5a743894a0e4a801fc3）
INSERT INTO t_user (username, password, real_name, id_card, phone, address, role, create_time)
VALUES ('admin', '21232f297a57a5a743894a0e4a801fc3', '系统管理员', '110101199001011234', '13800138000', '北京市海淀区', 'admin', NOW());

-- 初始化测试图书数据（可选，方便测试）
INSERT INTO t_book (book_name, author, publisher, intro, price, stock, create_time)
VALUES
    ('Java编程思想', 'Bruce Eckel', '机械工业出版社', 'Java领域经典著作，深入讲解Java核心概念', 89.00, 100, NOW()),
    ('Spring Boot实战', 'Craig Walls', '人民邮电出版社', 'Spring Boot入门到精通，实战案例丰富', 69.00, 80, NOW()),
    ('MySQL必知必会', 'Ben Forta', '人民邮电出版社', 'MySQL数据库入门经典，适合零基础学习', 45.00, 120, NOW());