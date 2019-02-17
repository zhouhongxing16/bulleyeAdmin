
-- 导出 bulleye_admin 的数据库结构
CREATE DATABASE IF NOT EXISTS `bulleye_admin` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `bulleye_admin`;

-- 导出  表 bulleye_admin.wx_account 结构
CREATE TABLE IF NOT EXISTS `wx_account` (
  `id` varchar(40) NOT NULL COMMENT '公众号id',
  `qr_code` varchar(500) DEFAULT NULL COMMENT '公众号二维码',
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `source_id` varchar(50) DEFAULT NULL COMMENT '微信号',
  `app_id` varchar(50) DEFAULT NULL COMMENT '微信appid',
  `aes_key` varchar(50) DEFAULT NULL,
  `app_secret` varchar(50) DEFAULT NULL COMMENT '微信appsecret',
  `token` varchar(50) DEFAULT NULL COMMENT '微信token',
  `domain` varchar(500) DEFAULT NULL COMMENT '域名',
  `access_token` varchar(50) DEFAULT NULL COMMENT 'access_token',
  `token_time` datetime DEFAULT NULL COMMENT '上次获取access_token的时间',
  `menu_state` varchar(50) DEFAULT NULL COMMENT 'menu_state',
  `user_id` varchar(50) DEFAULT NULL COMMENT '添加人ID',
  `created` bigint(20) DEFAULT NULL COMMENT '添加时间',
  `updated` bigint(20) DEFAULT NULL COMMENT '修改时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `partner` varchar(255) DEFAULT NULL COMMENT '微信商户id',
  `partner_key` varchar(255) DEFAULT NULL COMMENT '微信商户支付秘钥',
  `certificate_path` varchar(255) DEFAULT NULL COMMENT '证书文件',
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='微信公众号信息';

-- 正在导出表  bulleye_admin.wx_account 的数据：~0 rows (大约)
DELETE FROM `wx_account`;
/*!40000 ALTER TABLE `wx_account` DISABLE KEYS */;
INSERT INTO `wx_account` (`id`, `qr_code`, `name`, `source_id`, `app_id`, `aes_key`, `app_secret`, `token`, `domain`, `access_token`, `token_time`, `menu_state`, `user_id`, `created`, `updated`, `remark`, `partner`, `partner_key`, `certificate_path`, `status`) VALUES
	('dfdfgsdfgsdfgsdfgsdfgsdfgsdfgsdfg', '3', '2', '2', 'wx428db2722153d12d', '2', 'e1e8aed568ff374cb368428529c78d13', 'chris520', '2', '2', '2019-02-15 23:34:49', '2', '2', 2, 2, '2', '2', '2', '2', 2);
/*!40000 ALTER TABLE `wx_account` ENABLE KEYS */;

-- 导出  表 bulleye_admin.wx_member 结构
CREATE TABLE IF NOT EXISTS `wx_member` (
  `id` varchar(40) NOT NULL COMMENT '微信用户id',
  `account_id` varchar(50) DEFAULT NULL COMMENT '所属公众号id',
  `subscribe` varchar(50) DEFAULT NULL COMMENT '关注状态 1.关注中 2.未关注',
  `open_id` varchar(50) NOT NULL COMMENT '用户openid',
  `nickname` varchar(50) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '昵称',
  `sex` varchar(50) DEFAULT NULL COMMENT '性别',
  `sex_desc` varchar(50) DEFAULT NULL,
  `language` varchar(200) DEFAULT NULL,
  `city` varchar(50) DEFAULT NULL COMMENT '城市',
  `qr_code` varchar(200) DEFAULT NULL COMMENT '永久二维码code',
  `province` varchar(50) DEFAULT NULL COMMENT '省份',
  `country` varchar(50) DEFAULT NULL COMMENT '国家',
  `head_img_url` varchar(500) DEFAULT NULL COMMENT '头像',
  `subscribe_time` bigint(20) DEFAULT NULL,
  `union_id` varchar(50) DEFAULT NULL,
  `remark` varchar(50) DEFAULT NULL,
  `group_id` varchar(200) DEFAULT NULL COMMENT '分组',
  `privileges` varchar(200) DEFAULT NULL,
  `tag_ids` varchar(50) DEFAULT NULL,
  `subscribe_scene` varchar(200) DEFAULT NULL,
  `qr_scene` varchar(200) DEFAULT NULL,
  `qr_scene_str` varchar(200) DEFAULT NULL,
  `created` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `user_id` varchar(50) DEFAULT NULL COMMENT '关联用户账户id',
  `status` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `openid` (`open_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='微信用户';

-- 正在导出表  bulleye_admin.wx_member 的数据：~0 rows (大约)
DELETE FROM `wx_member`;
/*!40000 ALTER TABLE `wx_member` DISABLE KEYS */;
/*!40000 ALTER TABLE `wx_member` ENABLE KEYS */;

-- 导出  表 bulleye_admin.wx_reply 结构
CREATE TABLE IF NOT EXISTS `wx_reply` (
  `id` varchar(50) NOT NULL COMMENT 'ID',
  `account_id` varchar(50) DEFAULT NULL COMMENT '所属公众号ID',
  `keyword` varchar(100) DEFAULT NULL COMMENT '关键字',
  `title` varchar(100) DEFAULT NULL COMMENT '标题',
  `pic` varchar(150) DEFAULT NULL COMMENT '图片',
  `url` varchar(100) DEFAULT NULL COMMENT '链接',
  `content` varchar(250) DEFAULT NULL COMMENT '内容',
  `type` varchar(50) DEFAULT NULL COMMENT '类型 1.图文 2.纯文字',
  `num` bigint(20) DEFAULT NULL COMMENT '回复次数',
  `status` int(11) DEFAULT NULL,
  `created` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='微信自动回复';

-- 正在导出表  bulleye_admin.wx_reply 的数据：~0 rows (大约)
DELETE FROM `wx_reply`;