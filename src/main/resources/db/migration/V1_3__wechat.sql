
CREATE DATABASE IF NOT EXISTS `bulleye_admin` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `bulleye_admin`;


-- 导出  表 bulleye_admin.wx_account 结构
CREATE TABLE IF NOT EXISTS `wx_account` (
  `account_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '公众号id',
  `account_pic` varchar(500) DEFAULT NULL COMMENT '公众号二维码',
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `number` varchar(50) DEFAULT NULL COMMENT '微信号',
  `appid` varchar(50) DEFAULT NULL COMMENT '微信appid',
  `appsecret` varchar(50) DEFAULT NULL COMMENT '微信appsecret',
  `token` varchar(50) DEFAULT NULL COMMENT '微信token',
  `domain` varchar(500) DEFAULT NULL COMMENT '域名',
  `access_token` varchar(50) DEFAULT NULL COMMENT 'access_token',
  `token_time` datetime DEFAULT NULL COMMENT '上次获取access_token的时间',
  `menu_state` varchar(50) DEFAULT NULL COMMENT 'menu_state',
  `adduserid` bigint(20) DEFAULT NULL COMMENT '添加人ID',
  `addusername` varchar(50) DEFAULT NULL COMMENT '添加人NAME',
  `addtime` varchar(50) DEFAULT NULL COMMENT '添加时间',
  `updateid` bigint(20) DEFAULT NULL COMMENT '修改人ID',
  `updatename` varchar(50) DEFAULT NULL COMMENT '修改人NAME',
  `updatetime` varchar(50) DEFAULT NULL COMMENT '修改时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `main_account` varchar(50) DEFAULT NULL COMMENT '主公众号',
  `wx_partner` varchar(255) DEFAULT NULL COMMENT '微信商户id',
  `wx_partnerkey` varchar(255) DEFAULT NULL COMMENT '微信商户支付秘钥',
  `wx_file` varchar(255) DEFAULT NULL COMMENT '证书文件',
  PRIMARY KEY (`account_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='微信公众号信息';

-- 导出  表 bulleye_admin.wx_member 结构
CREATE TABLE IF NOT EXISTS `wx_member` (
  `w_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '微信用户id',
  `account_id` varchar(50) DEFAULT NULL COMMENT '所属公众号id',
  `account_name` varchar(50) DEFAULT NULL COMMENT '所属公众号名称',
  `subscribe` varchar(50) DEFAULT NULL COMMENT '关注状态 1.关注中 2.未关注',
  `openid` varchar(50) NOT NULL COMMENT '用户openid',
  `nickname` varchar(50) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '昵称',
  `sex` varchar(50) DEFAULT NULL COMMENT '性别',
  `city` varchar(50) DEFAULT NULL COMMENT '城市',
  `country` varchar(50) DEFAULT NULL COMMENT '国家',
  `province` varchar(50) DEFAULT NULL COMMENT '省份',
  `headimgurl` varchar(500) DEFAULT NULL COMMENT '头像',
  `createTime` varchar(50) DEFAULT NULL COMMENT '创建时间',
  `user_id` varchar(50) DEFAULT NULL COMMENT '关联用户账户id',
  `code` varchar(100) DEFAULT NULL COMMENT '永久二维码凑得',
  PRIMARY KEY (`w_id`),
  KEY `openid` (`openid`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='微信用户';


-- 导出  表 bulleye_admin.wx_reply 结构
CREATE TABLE IF NOT EXISTS `wx_reply` (
  `reply_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `account_id` bigint(20) DEFAULT NULL COMMENT '所属公众号ID',
  `reply_keyword` varchar(100) DEFAULT NULL COMMENT '关键字',
  `reply_title` varchar(100) DEFAULT NULL COMMENT '标题',
  `reply_pic` varchar(150) DEFAULT NULL COMMENT '图片',
  `reply_url` varchar(100) DEFAULT NULL COMMENT '链接',
  `reply_content` varchar(250) DEFAULT NULL COMMENT '内容',
  `reply_type` varchar(50) DEFAULT NULL COMMENT '类型 1.图文 2.纯文字',
  `reply_num` bigint(20) DEFAULT NULL COMMENT '回复次数',
  PRIMARY KEY (`reply_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='微信自动回复';

