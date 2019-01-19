-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.5.21 - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  9.1.0.4894
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 导出 bulleye_admin 的数据库结构
CREATE DATABASE IF NOT EXISTS `bulleye_admin` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `bulleye_admin`;

CREATE TABLE `wx_account` (
	`id` VARCHAR(40) NOT NULL COMMENT '公众号id',
	`qr_code` VARCHAR(500) NULL DEFAULT NULL COMMENT '公众号二维码',
	`name` VARCHAR(50) NULL DEFAULT NULL COMMENT '名称',
	`sourceId` VARCHAR(50) NULL DEFAULT NULL COMMENT '微信号',
	`app_id` VARCHAR(50) NULL DEFAULT NULL COMMENT '微信appid',
	`app_secret` VARCHAR(50) NULL DEFAULT NULL COMMENT '微信appsecret',
	`token` VARCHAR(50) NULL DEFAULT NULL COMMENT '微信token',
	`domain` VARCHAR(500) NULL DEFAULT NULL COMMENT '域名',
	`access_token` VARCHAR(50) NULL DEFAULT NULL COMMENT 'access_token',
	`token_time` DATETIME NULL DEFAULT NULL COMMENT '上次获取access_token的时间',
	`menu_state` VARCHAR(50) NULL DEFAULT NULL COMMENT 'menu_state',
	`user_id` VARCHAR(50) NULL DEFAULT NULL COMMENT '添加人ID',
	`created` DATETIME NULL DEFAULT NULL COMMENT '添加时间',
	`updated` TIMESTAMP NULL DEFAULT NULL COMMENT '修改时间',
	`remark` VARCHAR(500) NULL DEFAULT NULL COMMENT '备注',
	`partner` VARCHAR(255) NULL DEFAULT NULL COMMENT '微信商户id',
	`partner_key` VARCHAR(255) NULL DEFAULT NULL COMMENT '微信商户支付秘钥',
	`certificate_path` VARCHAR(255) NULL DEFAULT NULL COMMENT '证书文件',
	PRIMARY KEY (`id`)
)
COMMENT='微信公众号信息'
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;


CREATE TABLE `wx_member` (
	`id` VARCHAR(40) NOT NULL COMMENT '微信用户id',
	`account_id` VARCHAR(50) NULL DEFAULT NULL COMMENT '所属公众号id',
	`subscribe` VARCHAR(50) NULL DEFAULT NULL COMMENT '关注状态 1.关注中 2.未关注',
	`open_id` VARCHAR(50) NOT NULL COMMENT '用户openid',
	`nickname` VARCHAR(50) NULL DEFAULT NULL COMMENT '昵称' COLLATE 'utf8mb4_general_ci',
	`qr_code` VARCHAR(200) NULL DEFAULT NULL COMMENT '永久二维码code',
	`sex` VARCHAR(50) NULL DEFAULT NULL COMMENT '性别',
	`city` VARCHAR(50) NULL DEFAULT NULL COMMENT '城市',
	`country` VARCHAR(50) NULL DEFAULT NULL COMMENT '国家',
	`province` VARCHAR(50) NULL DEFAULT NULL COMMENT '省份',
	`avatar` VARCHAR(500) NULL DEFAULT NULL COMMENT '头像',
	`group_id` VARCHAR(40) NULL DEFAULT NULL COMMENT '分组',
	`created` DATETIME NULL DEFAULT NULL COMMENT '创建时间',
	`user_id` VARCHAR(50) NULL DEFAULT NULL COMMENT '关联用户账户id',
	PRIMARY KEY (`id`),
	INDEX `openid` (`open_id`)
)
COMMENT='微信用户'
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;

CREATE TABLE `wx_reply` (
	`id` VARCHAR(50) NOT NULL COMMENT 'ID',
	`account_id` VARCHAR(50) NULL DEFAULT NULL COMMENT '所属公众号ID',
	`keyword` VARCHAR(100) NULL DEFAULT NULL COMMENT '关键字',
	`title` VARCHAR(100) NULL DEFAULT NULL COMMENT '标题',
	`pic` VARCHAR(150) NULL DEFAULT NULL COMMENT '图片',
	`url` VARCHAR(100) NULL DEFAULT NULL COMMENT '链接',
	`content` VARCHAR(250) NULL DEFAULT NULL COMMENT '内容',
	`type` VARCHAR(50) NULL DEFAULT NULL COMMENT '类型 1.图文 2.纯文字',
	`num` BIGINT(20) NULL DEFAULT NULL COMMENT '回复次数',
	`status` INT(11) NULL DEFAULT NULL,
	PRIMARY KEY (`id`)
)
COMMENT='微信自动回复'
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;


