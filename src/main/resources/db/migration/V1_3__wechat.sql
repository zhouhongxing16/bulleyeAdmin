CREATE DATABASE IF NOT EXISTS `bulleye_admin` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `bulleye_admin`;

-- 导出  表 bulleye_admin.wx_account 结构
DROP TABLE IF EXISTS `wx_account`;
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
	('dfdfgsdfgsdfgsdfgsdfgsdfgsdfgsdfg', '3', 'zhouhongxing16测试号', 'gh_89ac68370508', 'wx428db2722153d12d', '2', 'e1e8aed568ff374cb368428529c78d13', 'chris520', '2', '2', '2019-02-15 23:34:49', '2', '2', 2, 2, '2', '2', '2', '2', 2),
    ('1', '0', 'lhs', 'gh_1065c22dc61c', 'wx0a8f9a49c445fd2b', '2', '5aaea0afe5b65d60892a475bf43d668b', 'myToken', '167702s65a.iok.la', NULL, NULL, 'lhs', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);

/*!40000 ALTER TABLE `wx_account` ENABLE KEYS */;

-- 导出  表 bulleye_admin.wx_graphic 结构
DROP TABLE IF EXISTS `wx_graphic`;
CREATE TABLE IF NOT EXISTS `wx_graphic` (
  `id` varchar(50) NOT NULL,
  `title` varchar(100) DEFAULT NULL,
  `brief` varchar(200) DEFAULT NULL,
  `content` longtext,
  `url` varchar(200) DEFAULT NULL,
  `imgurl` varchar(200) DEFAULT NULL,
  `read_num` int(11) DEFAULT '0',
  `created` bigint(20) NOT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='图文消息';

-- 正在导出表  bulleye_admin.wx_graphic 的数据：~0 rows (大约)
DELETE FROM `wx_graphic`;
/*!40000 ALTER TABLE `wx_graphic` DISABLE KEYS */;
/*!40000 ALTER TABLE `wx_graphic` ENABLE KEYS */;

-- 导出  表 bulleye_admin.wx_member 结构
DROP TABLE IF EXISTS `wx_member`;
CREATE TABLE IF NOT EXISTS `wx_member` (
  `id` varchar(40) NOT NULL COMMENT '微信用户id',
  `account_id` varchar(50) DEFAULT NULL COMMENT '所属公众号id',
  `subscribe` bit(1) DEFAULT NULL COMMENT '关注状态 1.关注中 2.未关注',
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
  `user_id` varchar(50) DEFAULT NULL COMMENT '关联用户账户id',
  `unsubscribe_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `openid` (`open_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='微信用户';

-- 正在导出表  bulleye_admin.wx_member 的数据：~0 rows (大约)
DELETE FROM `wx_member`;
/*!40000 ALTER TABLE `wx_member` DISABLE KEYS */;
INSERT INTO `wx_member` (`id`, `account_id`, `subscribe`, `open_id`, `nickname`, `sex`, `sex_desc`, `language`, `city`, `qr_code`, `province`, `country`, `head_img_url`, `subscribe_time`, `union_id`, `remark`, `group_id`, `privileges`, `tag_ids`, `subscribe_scene`, `qr_scene`, `qr_scene_str`, `user_id`, `unsubscribe_time`) VALUES
	('a2624bad-3caf-11e9-a6e6-382c4a232da1', 'gh_89ac68370508', b'1', 'oXjM7wnpKt0pugyjZDw7ZeuvwWBo', 'Chris', '1', '男', 'zh_CN', '宜宾', NULL, '四川', '中国', 'http://thirdwx.qlogo.cn/mmopen/7DkVkX1qfEGSjTDwwYXoShDTqo60F3HiclqLUbZEfOh2eLJFXvKUkxS0j7hvsRDpshJkjQYDzmfNDLzvQUegXKRl0s8RyjwI5/132', 1551506024, NULL, '', '0', NULL, NULL, 'ADD_SCENE_QR_CODE', '0', '', NULL, 1551506018583),
	('c722a105-3e4c-11e9-a6e6-382c4a232da1', 'gh_89ac68370508', b'1', 'oXjM7wjcJSX7ztH8idZOw1bPfRqs', '空澄', '1', '男', 'zh_CN', '', NULL, '', '', 'http://thirdwx.qlogo.cn/mmopen/L4vS9NXCVM6Zlz9acR9MjQVW29hDTsF3RGqX4bm6QDxLwJQzcO22SbNJwyicLZAGwTpVPZeoZv4qbQp73QWk9Tfe6SRsF1oFX/132', 1551683443, NULL, '', '0', NULL, NULL, 'ADD_SCENE_PROFILE_CARD', '0', '', NULL, NULL);
/*!40000 ALTER TABLE `wx_member` ENABLE KEYS */;

-- 导出  表 bulleye_admin.wx_menu 结构
DROP TABLE IF EXISTS `wx_menu`;
CREATE TABLE IF NOT EXISTS `wx_menu` (
  `id` varchar(50) NOT NULL,
  `account_id` varchar(50) DEFAULT NULL COMMENT '公众号id',
  `type` varchar(20) DEFAULT NULL COMMENT '菜单的响应动作类型，view表示网页类型，click表示点击类型，miniprogram表示小程序类型',
  `name` varchar(100) DEFAULT NULL COMMENT '菜单标题',
  `key_value` varchar(100) DEFAULT NULL COMMENT '菜单KEY值，用于消息接口推送',
  `url` varchar(1000) DEFAULT NULL COMMENT '网页 链接',
  `media_id` varchar(100) DEFAULT NULL COMMENT '永久素材media_id',
  `app_id` varchar(50) DEFAULT NULL COMMENT '小程序的appid',
  `page_path` varchar(500) DEFAULT NULL COMMENT '小程序的页面路径',
  `parent_id` varchar(50) DEFAULT NULL COMMENT '父级id',
  `author` varchar(50) DEFAULT NULL COMMENT '是否获取用户信息 1.是 2.否',
  `sort` int(10) DEFAULT NULL COMMENT '排序',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='微信菜单表';

-- 正在导出表  bulleye_admin.wx_menu 的数据：~2 rows (大约)
DELETE FROM `wx_menu`;
/*!40000 ALTER TABLE `wx_menu` DISABLE KEYS */;
INSERT INTO `wx_menu` (`id`, `account_id`, `type`, `name`, `key_value`, `url`, `media_id`, `app_id`, `page_path`, `parent_id`, `author`, `sort`, `remark`) VALUES
	('111', 'dfdfgsdfgsdfgsdfgsdfgsdfgsdfgsdfg', '1', '1', '1', '1', '1', '1', '1', '1·1·1', '1', 1, NULL),
	('1·1·1', 'dfdfgsdfgsdfgsdfgsdfgsdfgsdfgsdfg', '1', '1', '1', '1', '1', '1', '1', '0', '1', 1, NULL),
    ('1', '1', 'click', '一级菜单', '#', NULL, '1', '1', '1', '0', '1', 1, ''),
    ('2', '1', 'view', '二级菜单', NULL, 'http://167702s65a.iok.la', '3', '3', '3', '1', '1', 3, '3');

/*!40000 ALTER TABLE `wx_menu` ENABLE KEYS */;

-- 导出  表 bulleye_admin.wx_reply 结构
DROP TABLE IF EXISTS `wx_reply`;
CREATE TABLE IF NOT EXISTS `wx_reply` (
  `id` varchar(50) NOT NULL COMMENT 'ID',
  `account_id` varchar(50) DEFAULT NULL COMMENT '所属公众号ID',
  `key_word` varchar(100) DEFAULT NULL COMMENT '关键字',
  `key_value` longtext COMMENT '值',
  `key_type` varchar(50) DEFAULT NULL COMMENT '类型 1.图文 2.纯文字',
  `graphic_id` varchar(50) DEFAULT NULL COMMENT '图文标识',
  `num` int(10) DEFAULT NULL COMMENT '回复次数',
  `status` int(11) DEFAULT NULL,
  `created` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='微信自动回复';

-- 正在导出表  bulleye_admin.wx_reply 的数据：~0 rows (大约)
DELETE FROM `wx_reply`;

DROP TABLE IF EXISTS `wx_material`;
CREATE TABLE `wx_material` (
	`id` VARCHAR(50) NOT NULL,
	`media_id` VARCHAR(100) NOT NULL COMMENT '新增的永久素材的media_id',
	`account_id` VARCHAR(50) NOT NULL COMMENT '所属公众号',
	`name` VARCHAR(50) NOT NULL COMMENT '素材名称',
	`parent_id` VARCHAR(50) NOT NULL COMMENT '父级id（图文时，可以由多个图文生成一个素材）',
	`type` VARCHAR(50) NOT NULL COMMENT '图片（image）、语音（voice）、视频（video）、缩略图（thumb）、图文（news）',
	`title` VARCHAR(50) NOT NULL COMMENT '标题',
	`thumb_media_id` VARCHAR(50) NOT NULL COMMENT '图文消息的封面图片素材id（必须是永久mediaID）',
	`author` VARCHAR(50) NOT NULL COMMENT '作者',
	`digest` VARCHAR(50) NOT NULL COMMENT '图文消息的摘要，仅有单图文消息才有摘要，多图文此处为空。如果本字段为没有填写，则默认抓取正文前64个字。',
	`show_cover_pic` BIT(1) NOT NULL COMMENT '是否显示封面，0为false，即不显示，1为true，即显示',
	`content` VARCHAR(18000) NOT NULL COMMENT '图文消息的具体内容，支持HTML标签，必须少于2万字符，小于1M，且此处会去除JS,涉及图片url必须来源 "上传图文消息内的图片获取URL"接口获取。外部图片url将被过滤。',
	`content_source_url` VARCHAR(200) NOT NULL COMMENT '图文消息的原文地址，即点击“阅读原文”后的URL',
	`need_open_comment` BIT(1) NOT NULL COMMENT 'Uint32 是否打开评论，0不打开，1打开',
	`only_fans_can_comment` BIT(1) NOT NULL COMMENT 'Uint32 是否粉丝才可评论，0所有人可评论，1粉丝才可评论',
	`introduction` VARCHAR(100) NOT NULL COMMENT '视频素材的描述',
	`down_url` VARCHAR(100) NOT NULL COMMENT '素材的url',
	`created` BIGINT(20) NOT NULL COMMENT '添加时间',
	PRIMARY KEY (`id`)
)
COMMENT='素材管理'
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;

