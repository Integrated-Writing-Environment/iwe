CREATE DATABASE
IF
	NOT EXISTS iwe;
USE iwe;
CREATE TABLE
IF
	NOT EXISTS `user` (
		`id` INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
		`username` VARCHAR ( 32 ) NOT NULL COMMENT '用户账号名',
		`nickname` VARCHAR ( 32 ) NOT NULL COMMENT '用户昵称',
		`pwd` VARCHAR ( 32 ) NOT NULL COMMENT '加密密码',
		`description` VARCHAR ( 255 ) DEFAULT NULL COMMENT '用户描述',
		`phone` VARCHAR ( 32 ) DEFAULT NULL COMMENT '用户手机号',
		`email` VARCHAR ( 64 ) DEFAULT NULL COMMENT '用户邮箱',
		`is_deleted` TINYINT UNSIGNED NOT NULL DEFAULT ( 0 ) COMMENT '删除标记',
		`create_time` DATETIME NOT NULL COMMENT '创建时间',
		`update_time` DATETIME NOT NULL COMMENT '更新时间',
		PRIMARY KEY `pk_id` ( `id` ),
		UNIQUE KEY `uk_username` ( `username` ),
		UNIQUE KEY `uk_nickname` ( `nickname` )
	) ENGINE = InnoDB COMMENT = '用户_数据表';
CREATE TABLE
IF
	NOT EXISTS `article` (
		`id` INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
		`title` VARCHAR ( 64 ) NOT NULL COMMENT '标题',
		`content` TEXT COMMENT '内容',
		`tools` JSON,
		`is_deleted` TINYINT UNSIGNED NOT NULL DEFAULT ( 0 ) COMMENT '删除标记',
		`create_time` DATETIME NOT NULL COMMENT '创建时间',
		`update_time` DATETIME NOT NULL COMMENT '更新时间',
		PRIMARY KEY `pk_id` ( `id` ),
		INDEX `idx_title` ( `title` )
	) ENGINE = InnoDB COMMENT = '文章_数据表';
CREATE TABLE
IF
	NOT EXISTS `tool` (
		`id` INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
		`nickname` VARCHAR ( 64 ) NOT NULL COMMENT '工具昵称',
		`description` VARCHAR ( 255 ) NOT NULL COMMENT '工具介绍',
		`fc` TEXT ( 2048 ) COMMENT '工具作用的函数',
		`is_deleted` TINYINT UNSIGNED NOT NULL DEFAULT ( 0 ) COMMENT '删除标记',
		`create_time` DATETIME NOT NULL COMMENT '创建时间',
		`update_time` DATETIME NOT NULL COMMENT '更新时间',
		PRIMARY KEY `pk_id` ( `id` ),
		INDEX `idx_nickname` ( `nickname` )
	) ENGINE = InnoDB COMMENT = '工具_数据表';
CREATE TABLE
IF
	NOT EXISTS `permission` (
		`id` INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
		`permission` JSON COMMENT '用户包含的所有权限',
		`is_deleted` TINYINT UNSIGNED NOT NULL DEFAULT ( 0 ) COMMENT '删除标记',
		`create_time` DATETIME NOT NULL COMMENT '创建时间',
		`update_time` DATETIME NOT NULL COMMENT '更新时间',
		PRIMARY KEY `pk_id` ( `id` )
	) ENGINE = InnoDB COMMENT = '权限_数据表';
CREATE TABLE
IF
	NOT EXISTS `user_article` (
		`id` INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
		`user_id` INTEGER UNSIGNED NOT NULL,
		`article_id` INTEGER UNSIGNED NOT NULL,
		`is_deleted` TINYINT UNSIGNED NOT NULL DEFAULT ( 0 ) COMMENT '删除标记',
		`create_time` DATETIME NOT NULL COMMENT '创建时间',
		`update_time` DATETIME NOT NULL COMMENT '更新时间',
		PRIMARY KEY `pk_id` ( `id` ),
		INDEX `idx_user_id` ( `user_id` ),
		INDEX `idx_article_id` ( `article_id` )
	) ENGINE = InnoDB COMMENT = '用户_文章_关系表';
CREATE TABLE
IF
	NOT EXISTS `user_permission` (
		`id` INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
		`user_id` INTEGER UNSIGNED NOT NULL,
		`permission_id` INTEGER UNSIGNED NOT NULL,
		`is_deleted` TINYINT UNSIGNED NOT NULL DEFAULT ( 0 ) COMMENT '删除标记',
		`create_time` DATETIME NOT NULL COMMENT '创建时间',
		`update_time` DATETIME NOT NULL COMMENT '更新时间',
		PRIMARY KEY `pk_id` ( `id` ),
		INDEX `idx_user_id` ( `user_id` ),
	INDEX `idx_permission_id` ( `permission_id` )
	) ENGINE = InnoDB COMMENT = '用户_权限_关系表';