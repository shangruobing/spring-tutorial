CREATE DATABASE IF NOT EXISTS SpringTutorial;

USE SpringTutorial;

SET foreign_key_checks = 0;

-- 公司表
CREATE TABLE IF NOT EXISTS company
(
    id             INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    name           VARCHAR(255),
    type           VARCHAR(255),
    deleted        INT      DEFAULT 0,
    create_time    DATETIME DEFAULT NOW(),
    update_time    DATETIME DEFAULT NOW() ON UPDATE NOW(),
    create_user_id INT UNSIGNED,
    update_user_id INT UNSIGNED,
    FOREIGN KEY (create_user_id) REFERENCES user (id),
    FOREIGN KEY (update_user_id) REFERENCES user (id)
);

-- 用户表 一对多关系
CREATE TABLE IF NOT EXISTS user
(
    id              INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    state           TINYINT(1)         DEFAULT TRUE COMMENT '是否启用',
    union_id        VARCHAR(255) COMMENT '微信统一用户ID',
    open_id         VARCHAR(255) COMMENT '微信公众号用户ID',
    role            VARCHAR(255),
    name            VARCHAR(255),
    username        VARCHAR(255),
    gender          ENUM ('男' , '女') DEFAULT '女',
    phone           VARCHAR(255),
    password        VARCHAR(255)       DEFAULT '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92',
    email           VARCHAR(255),
    join_time       DATETIME,
    last_login_time DATETIME,
    company_id      INT UNSIGNED,
    deleted         INT                DEFAULT 0,
    create_time     DATETIME           DEFAULT NOW(),
    update_time     DATETIME           DEFAULT NOW() ON UPDATE NOW(),
    create_user_id  INT UNSIGNED,
    update_user_id  INT UNSIGNED,
    FOREIGN KEY (create_user_id) REFERENCES user (id),
    FOREIGN KEY (update_user_id) REFERENCES user (id),
    FOREIGN KEY (company_id) REFERENCES company (id)
);

-- 初始化公司
BEGIN;
INSERT INTO company (id, name, deleted, create_time, update_time, create_user_id, update_user_id)
VALUES (1, 'Infoweaver', 0, '2023-11-15 07:00:00', '2023-11-15 07:00:00', null, null);
COMMIT;

-- 初始化管理员
BEGIN;
INSERT INTO user (id, state, company_id, union_id, open_id, role, name, username, gender, phone, password, email,
                  join_time, last_login_time, deleted, create_time, update_time, create_user_id, update_user_id)
VALUES (1, 1, 1, '', '', '管理员', 'RuobingShang', '', '男', '123456789',
        '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 'shangruobing29@gmail.com', '2023-11-15 07:00:00',
        '2023-11-15 07:00:00', 0, '2023-11-15 07:00:00', '2023-11-15 07:00:00', null, null);
COMMIT;
