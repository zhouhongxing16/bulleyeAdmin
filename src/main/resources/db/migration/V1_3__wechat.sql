-- 导出  表 bulleye_admin.wx_account 结构
DROP TABLE IF EXISTS `wx_account`;
CREATE TABLE IF NOT EXISTS `wx_account` (
  `id` varchar(40) NOT NULL COMMENT '公众号id',
  `qr_code` varchar(500) DEFAULT NULL COMMENT '公众号二维码',
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `sourceId` varchar(50) DEFAULT NULL COMMENT '微信号',
  `app_id` varchar(50) DEFAULT NULL COMMENT '微信appid',
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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='微信公众号信息';

-- 正在导出表  bulleye_admin.wx_account 的数据：~0 rows (大约)
DELETE FROM `wx_account`;
/*!40000 ALTER TABLE `wx_account` DISABLE KEYS */;
/*!40000 ALTER TABLE `wx_account` ENABLE KEYS */;

-- 导出  表 bulleye_admin.wx_member 结构
DROP TABLE IF EXISTS `wx_member`;
CREATE TABLE IF NOT EXISTS `wx_member` (
  `id` varchar(40) NOT NULL COMMENT '微信用户id',
  `account_id` varchar(50) DEFAULT NULL COMMENT '所属公众号id',
  `subscribe` varchar(50) DEFAULT NULL COMMENT '关注状态 1.关注中 2.未关注',
  `open_id` varchar(50) NOT NULL COMMENT '用户openid',
  `nickname` varchar(50) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '昵称',
  `qr_code` varchar(200) DEFAULT NULL COMMENT '永久二维码code',
  `sex` varchar(50) DEFAULT NULL COMMENT '性别',
  `city` varchar(50) DEFAULT NULL COMMENT '城市',
  `country` varchar(50) DEFAULT NULL COMMENT '国家',
  `province` varchar(50) DEFAULT NULL COMMENT '省份',
  `avatar` varchar(500) DEFAULT NULL COMMENT '头像',
  `group_id` varchar(40) DEFAULT NULL COMMENT '分组',
  `created` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `user_id` varchar(50) DEFAULT NULL COMMENT '关联用户账户id',
  PRIMARY KEY (`id`),
  KEY `openid` (`open_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='微信用户';

-- 正在导出表  bulleye_admin.wx_member 的数据：~0 rows (大约)
DELETE FROM `wx_member`;
/*!40000 ALTER TABLE `wx_member` DISABLE KEYS */;
/*!40000 ALTER TABLE `wx_member` ENABLE KEYS */;

-- 导出  表 bulleye_admin.wx_reply 结构
DROP TABLE IF EXISTS `wx_reply`;
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