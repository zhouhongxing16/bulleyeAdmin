-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- Server version:               5.7.9-log - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL 版本:                  10.1.0.5464
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for bulleye_admin
CREATE DATABASE IF NOT EXISTS `bulleye_admin` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `bulleye_admin`;

-- Dumping structure for table bulleye_admin.b_account
CREATE TABLE IF NOT EXISTS `b_account` (
  `id` varchar(40) NOT NULL COMMENT '唯一标识',
  `username` varchar(50) DEFAULT NULL COMMENT '账户',
  `password` varchar(128) DEFAULT NULL COMMENT '密码',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `mobile_login_flag` bit(1) DEFAULT NULL COMMENT '是否开通手机号登录',
  `account_locked` tinyint(1) DEFAULT NULL COMMENT '是否锁定',
  `account_expired` tinyint(1) DEFAULT NULL COMMENT '是否过期',
  `staff_id` varchar(40) DEFAULT NULL COMMENT '人员标识',
  `organization_id` varchar(40) DEFAULT NULL COMMENT '组织标识',
  `wx_openid` varchar(64) DEFAULT NULL COMMENT '微信OpenId',
  `alipay_openid` varchar(64) DEFAULT NULL COMMENT '支付宝OpenId',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `modified` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `expired_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '账号过期时间',
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- Dumping data for table bulleye_admin.b_account: ~2 rows (approximately)
DELETE FROM `b_account`;
/*!40000 ALTER TABLE `b_account` DISABLE KEYS */;
INSERT INTO `b_account` (`id`, `username`, `password`, `email`, `mobile_login_flag`, `account_locked`, `account_expired`, `staff_id`, `organization_id`, `wx_openid`, `alipay_openid`, `status`, `remark`, `modified`, `expired_date`, `created`) VALUES
	('4081fe65-125a-11e9-97db-382c4a232da1', 'zhx', '{bcrypt}$2a$10$cBxwbxYB8iUZ2.W2BspGbOPxAlsZT1ppDtD.nFJsJMCQMAW8YCUWy', '961860916@qq.com', NULL, 0, 0, '1', 'suiji', NULL, NULL, 1, NULL, NULL, '2019-04-13 15:16:20', '2019-04-16 19:25:32'),
	('fe885bfa-6bf9-11e9-8e25-049226bfdadf', '18080332897', NULL, '961860916@qq.com', b'1', 0, 0, '1', NULL, NULL, NULL, 1, '111', NULL, NULL, '2019-05-01 18:15:15');
/*!40000 ALTER TABLE `b_account` ENABLE KEYS */;

-- Dumping structure for table bulleye_admin.b_account_role
CREATE TABLE IF NOT EXISTS `b_account_role` (
  `id` varchar(40) NOT NULL COMMENT '唯一标识',
  `role_id` varchar(40) DEFAULT NULL COMMENT '角色外键',
  `account_id` varchar(40) DEFAULT NULL COMMENT '用户外键',
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色中间表';

-- Dumping data for table bulleye_admin.b_account_role: ~4 rows (approximately)
DELETE FROM `b_account_role`;
/*!40000 ALTER TABLE `b_account_role` DISABLE KEYS */;
INSERT INTO `b_account_role` (`id`, `role_id`, `account_id`, `created`) VALUES
	('1', '1', '00278d19-9794-11e7-975b-000c29687dd9', '2018-07-31 11:49:48'),
	('e77c7026-6cef-11e9-8e25-049226bfdadf', '5ebcefdf-6c2a-11e9-8e25-049226bfdadf', 'fe885bfa-6bf9-11e9-8e25-049226bfdadf', '2019-05-02 23:35:33'),
	('e77d46df-6cef-11e9-8e25-049226bfdadf', '12312312312312123', 'fe885bfa-6bf9-11e9-8e25-049226bfdadf', '2019-05-02 23:35:33'),
	('e9d83753-6cef-11e9-8e25-049226bfdadf', '2', '4081fe65-125a-11e9-97db-382c4a232da1', '2019-05-02 23:35:37');
/*!40000 ALTER TABLE `b_account_role` ENABLE KEYS */;

-- Dumping structure for table bulleye_admin.b_attach_file
CREATE TABLE IF NOT EXISTS `b_attach_file` (
  `id` varchar(40) NOT NULL COMMENT '唯一标识',
  `organization_id` varchar(50) DEFAULT NULL COMMENT '组织标识',
  `biz_id` varchar(40) DEFAULT NULL COMMENT '业务标识',
  `name` varchar(50) DEFAULT NULL COMMENT '附件名称',
  `path` varchar(200) DEFAULT NULL COMMENT '附件路径',
  `download_count` int(11) DEFAULT '0' COMMENT '下载次数',
  `status` int(11) DEFAULT '0' COMMENT '状态',
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='通用附件信息';

-- Dumping data for table bulleye_admin.b_attach_file: ~15 rows (approximately)
DELETE FROM `b_attach_file`;
/*!40000 ALTER TABLE `b_attach_file` DISABLE KEYS */;
INSERT INTO `b_attach_file` (`id`, `organization_id`, `biz_id`, `name`, `path`, `download_count`, `status`, `created`) VALUES
	('024522f3-880e-11e9-b58b-382c5e4d3b2a', NULL, NULL, 'avatar.jpg', '/c77e18b0-1ffa-11e7-b036-00163e0603fa/201906061148367394avatar.jpg', 0, 1, '2019-06-06 11:48:36'),
	('16f9cc7c-880f-11e9-b58b-382c5e4d3b2a', NULL, NULL, 'avatar.jpg', '/c77e18b0-1ffa-11e7-b036-00163e0603fa/201906061156207454avatar.jpg', 0, 1, '2019-06-06 11:56:20'),
	('1821a84f-882d-11e9-b58b-382c5e4d3b2a', NULL, NULL, 'avatar.jpg', '/c77e18b0-1ffa-11e7-b036-00163e0603fa/201906061531074035avatar.jpg', 0, 1, '2019-06-06 15:31:07'),
	('2bcb923f-880e-11e9-b58b-382c5e4d3b2a', NULL, NULL, 'avatar.jpg', '/c77e18b0-1ffa-11e7-b036-00163e0603fa/201906061149469688avatar.jpg', 0, 1, '2019-06-06 11:49:46'),
	('44447f7d-880f-11e9-b58b-382c5e4d3b2a', NULL, NULL, 'avatar.jpg', '/c77e18b0-1ffa-11e7-b036-00163e0603fa/201906061157369092avatar.jpg', 0, 1, '2019-06-06 11:57:36'),
	('46028259-8833-11e9-b58b-382c5e4d3b2a', NULL, NULL, 'avatar.jpg', '/c77e18b0-1ffa-11e7-b036-00163e0603fa/201906061615213751avatar.jpg', 0, 1, '2019-06-06 16:15:21'),
	('4f51af93-880d-11e9-b58b-382c5e4d3b2a', NULL, NULL, 'avatar.jpg', '/c77e18b0-1ffa-11e7-b036-00163e0603fa/201906061143363583avatar.jpg', 0, 1, '2019-06-06 11:43:36'),
	('5452ed05-882d-11e9-b58b-382c5e4d3b2a', NULL, NULL, 'avatar.jpg', '/c77e18b0-1ffa-11e7-b036-00163e0603fa/201906061532488987avatar.jpg', 0, 1, '2019-06-06 15:32:48'),
	('757f9e1e-880f-11e9-b58b-382c5e4d3b2a', NULL, NULL, 'avatar.jpg', '/c77e18b0-1ffa-11e7-b036-00163e0603fa/201906061158598412avatar.jpg', 0, 1, '2019-06-06 11:58:59'),
	('92c3918e-8773-11e9-b58b-382c5e4d3b2a', NULL, NULL, 'avatar.jpg', '/c77e18b0-1ffa-11e7-b036-00163e0603fa/201906051723083738avatar.jpg', 0, 1, '2019-06-05 17:23:08'),
	('b4d0f78d-882c-11e9-b58b-382c5e4d3b2a', NULL, NULL, 'avatar.jpg', '/c77e18b0-1ffa-11e7-b036-00163e0603fa/201906061528211652avatar.jpg', 0, 1, '2019-06-06 15:28:21'),
	('bb2aac9d-880d-11e9-b58b-382c5e4d3b2a', NULL, NULL, 'avatar.jpg', '/c77e18b0-1ffa-11e7-b036-00163e0603fa/201906061146374111avatar.jpg', 0, 1, '2019-06-06 11:46:37'),
	('db90cc12-880e-11e9-b58b-382c5e4d3b2a', NULL, NULL, 'avatar.jpg', '/c77e18b0-1ffa-11e7-b036-00163e0603fa/201906061154412189avatar.jpg', 0, 1, '2019-06-06 11:54:41'),
	('ddaf9f1c-8834-11e9-b58b-382c5e4d3b2a', NULL, NULL, 'avatar.jpg', '/c77e18b0-1ffa-11e7-b036-00163e0603fa/201906061626452646avatar.jpg', 0, 1, '2019-06-06 16:26:45'),
	('fab59962-882c-11e9-b58b-382c5e4d3b2a', NULL, NULL, 'avatar.jpg', '/c77e18b0-1ffa-11e7-b036-00163e0603fa/201906061530181654avatar.jpg', 0, 1, '2019-06-06 15:30:18');
/*!40000 ALTER TABLE `b_attach_file` ENABLE KEYS */;

-- Dumping structure for table bulleye_admin.b_department
CREATE TABLE IF NOT EXISTS `b_department` (
  `id` varchar(40) NOT NULL COMMENT '唯一标识',
  `organization_id` varchar(40) DEFAULT NULL COMMENT '组织标识',
  `code` varchar(30) DEFAULT NULL COMMENT '代码',
  `parent_id` varchar(40) DEFAULT NULL COMMENT '父级ID',
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `type_id` varchar(40) DEFAULT NULL COMMENT '类型',
  `remark` varchar(255) DEFAULT NULL COMMENT '描述',
  `user_id` varchar(40) DEFAULT NULL COMMENT '创建人',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部门表';

-- Dumping data for table bulleye_admin.b_department: ~0 rows (approximately)
DELETE FROM `b_department`;
/*!40000 ALTER TABLE `b_department` DISABLE KEYS */;
INSERT INTO `b_department` (`id`, `organization_id`, `code`, `parent_id`, `name`, `type_id`, `remark`, `user_id`, `status`, `created`) VALUES
	('test', 'suiji', '管理部门', NULL, '管理部门', 'guanli', '管理', NULL, 0, '2018-07-31 11:43:02');
/*!40000 ALTER TABLE `b_department` ENABLE KEYS */;

-- Dumping structure for table bulleye_admin.b_dictionary_data
CREATE TABLE IF NOT EXISTS `b_dictionary_data` (
  `id` varchar(40) NOT NULL COMMENT '唯一标识',
  `type_id` varchar(40) DEFAULT NULL COMMENT '类型标识',
  `code` varchar(20) DEFAULT NULL COMMENT '字典编码',
  `name` varchar(100) DEFAULT NULL COMMENT '字典名称',
  `remark` varchar(200) DEFAULT NULL COMMENT '字典备注',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='字典数据表';

-- Dumping data for table bulleye_admin.b_dictionary_data: ~0 rows (approximately)
DELETE FROM `b_dictionary_data`;
/*!40000 ALTER TABLE `b_dictionary_data` DISABLE KEYS */;
/*!40000 ALTER TABLE `b_dictionary_data` ENABLE KEYS */;

-- Dumping structure for table bulleye_admin.b_dictionary_type
CREATE TABLE IF NOT EXISTS `b_dictionary_type` (
  `id` varchar(40) NOT NULL COMMENT '唯一标识',
  `code` varchar(30) DEFAULT NULL COMMENT '类型代码',
  `name` varchar(50) DEFAULT NULL COMMENT '类型名称',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部门类型、性别、专业、学历、职位、职称';

-- Dumping data for table bulleye_admin.b_dictionary_type: ~0 rows (approximately)
DELETE FROM `b_dictionary_type`;
/*!40000 ALTER TABLE `b_dictionary_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `b_dictionary_type` ENABLE KEYS */;

-- Dumping structure for table bulleye_admin.b_function
CREATE TABLE IF NOT EXISTS `b_function` (
  `id` varchar(40) NOT NULL COMMENT '唯一标识',
  `code` varchar(40) DEFAULT NULL COMMENT '功能code',
  `name` varchar(50) DEFAULT NULL COMMENT '功能名称',
  `icon` varchar(50) DEFAULT NULL COMMENT '图标',
  `url` varchar(255) DEFAULT NULL COMMENT '功能url',
  `describe` varchar(200) DEFAULT NULL COMMENT '功能描述',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限表';

-- Dumping data for table bulleye_admin.b_function: ~0 rows (approximately)
DELETE FROM `b_function`;
/*!40000 ALTER TABLE `b_function` DISABLE KEYS */;
/*!40000 ALTER TABLE `b_function` ENABLE KEYS */;

-- Dumping structure for table bulleye_admin.b_login_record
CREATE TABLE IF NOT EXISTS `b_login_record` (
  `id` varchar(50) NOT NULL COMMENT '访问ID',
  `username` varchar(50) DEFAULT '' COMMENT '登录账号',
  `ip` varchar(50) DEFAULT '' COMMENT '登录IP地址',
  `login_location` varchar(255) DEFAULT '' COMMENT '登录地点',
  `browser` varchar(50) DEFAULT '' COMMENT '浏览器类型',
  `os` varchar(50) DEFAULT '' COMMENT '操作系统',
  `status` char(1) DEFAULT '0' COMMENT '登录状态（1成功 0失败）',
  `message` varchar(255) DEFAULT '' COMMENT '提示消息',
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '访问时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统访问记录';

-- Dumping data for table bulleye_admin.b_login_record: ~0 rows (approximately)
DELETE FROM `b_login_record`;
/*!40000 ALTER TABLE `b_login_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `b_login_record` ENABLE KEYS */;

-- Dumping structure for table bulleye_admin.b_logs
CREATE TABLE IF NOT EXISTS `b_logs` (
  `id` varchar(40) NOT NULL COMMENT 'ID',
  `organization_id` varchar(40) DEFAULT NULL COMMENT '所属组织',
  `option_name` varchar(50) DEFAULT NULL COMMENT '操作类',
  `option_type` varchar(20) DEFAULT NULL COMMENT '操作方法名',
  `method` varchar(200) DEFAULT NULL COMMENT '操作方法',
  `params` text COMMENT '参数',
  `user_id` varchar(40) DEFAULT NULL COMMENT '操作人',
  `ip` varchar(50) DEFAULT NULL COMMENT 'IP',
  `execution_time` int(11) DEFAULT NULL COMMENT '执行时长',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='操作日志';

-- Dumping data for table bulleye_admin.b_logs: ~0 rows (approximately)
DELETE FROM `b_logs`;
/*!40000 ALTER TABLE `b_logs` DISABLE KEYS */;
/*!40000 ALTER TABLE `b_logs` ENABLE KEYS */;

-- Dumping structure for table bulleye_admin.b_menu
CREATE TABLE IF NOT EXISTS `b_menu` (
  `id` varchar(40) NOT NULL COMMENT '唯一标识',
  `parent_id` varchar(40) DEFAULT NULL COMMENT '父菜单id',
  `icon` varchar(50) DEFAULT NULL COMMENT '图标',
  `code` varchar(40) DEFAULT NULL COMMENT '代码',
  `title` varchar(50) DEFAULT NULL COMMENT '名称',
  `path` varchar(255) DEFAULT NULL COMMENT '路径',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `sort` int(11) DEFAULT NULL COMMENT '显示顺序',
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单表';

-- Dumping data for table bulleye_admin.b_menu: ~18 rows (approximately)
DELETE FROM `b_menu`;
/*!40000 ALTER TABLE `b_menu` DISABLE KEYS */;
INSERT INTO `b_menu` (`id`, `parent_id`, `icon`, `code`, `title`, `path`, `status`, `sort`, `created`) VALUES
	('1', NULL, 'wechat', 'admin', '微信管理', '/menu', 1, 2, '2019-04-28 22:38:42'),
	('10', '2', NULL, '111', '组织机构管理', '/organization/list', 1, 1, '2019-04-28 22:38:42'),
	('11', '2', NULL, '111', '机构部门管理', '/department/list', 1, 1, '2019-04-28 22:38:42'),
	('12', NULL, NULL, NULL, '一级菜单', NULL, 1, 1, '2019-04-28 22:38:42'),
	('13', '12', NULL, NULL, '二级菜单', NULL, 1, 1, '2019-04-28 22:38:42'),
	('14', '13', 'wechat', '665869', '三级菜单', '111', 1, 1, '2019-04-28 22:38:42'),
	('17', '2', NULL, NULL, '菜单管理', '/menu/list', 1, 1, '2019-04-28 22:38:42'),
	('2', NULL, NULL, 'admin1', '系统管理', '/account/list', 1, 1, '2019-04-28 22:38:42'),
	('2333', '2', NULL, NULL, '登录日志管理', '/loginrecord/list', 1, 1, '2019-04-28 22:38:42'),
	('3', '2', NULL, 'staff', '角色管理', '/role/list', 1, 1, '2019-04-28 22:38:42'),
	('343223', '2', NULL, NULL, '操作日志管理', '/log/list', 1, 1, '2019-04-28 22:38:42'),
	('4', '2', 'wechat', 'admin2', '账号管理', '/account/list', 1, 1, '2019-04-28 22:38:42'),
	('5', '2', 'wechat', '111', '员工管理', '/staff/list', 1, 1, '2019-04-28 22:38:42'),
	('52091a21-460a-11e9-bd13-382c5e4d3b2a', '2', 'wechat', '1', '菜单权限管理', '/menuauth/list', 1, 111, '2019-04-28 22:38:42'),
	('6', '1', 'wechat', '111', '微信菜单管理', '/wxmenu/list', 1, 1, '2019-04-28 22:38:42'),
	('7', '1', 'wechat', '111', '微信公众号管理', '/wxaccount/list', 1, 1, '2019-04-28 22:38:42'),
	('8', '1', NULL, '111', '微信会员管理', '/wxmember/list', 1, 1, '2019-04-28 22:38:42'),
	('9', '1', NULL, '111', '微信自动回复管理', '/wxreply/list', 0, 1, '2019-04-28 22:38:42');
/*!40000 ALTER TABLE `b_menu` ENABLE KEYS */;

-- Dumping structure for table bulleye_admin.b_menu_auth
CREATE TABLE IF NOT EXISTS `b_menu_auth` (
  `id` varchar(50) NOT NULL,
  `menu_id` varchar(50) DEFAULT NULL,
  `code` varchar(50) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `path` varchar(50) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单权限表';

-- Dumping data for table bulleye_admin.b_menu_auth: ~2 rows (approximately)
DELETE FROM `b_menu_auth`;
/*!40000 ALTER TABLE `b_menu_auth` DISABLE KEYS */;
INSERT INTO `b_menu_auth` (`id`, `menu_id`, `code`, `name`, `path`, `status`, `created`) VALUES
	('5376de89-4b9d-11e9-8600-382c5e4d3b2a', '1', 'query', '查看', '/query', '0', '2019-04-28 22:38:53'),
	('83e6e514-4c4b-11e9-8600-382c5e4d3b2a', '4', 'list', '查看', '/list', '0', '2019-04-28 22:38:53');
/*!40000 ALTER TABLE `b_menu_auth` ENABLE KEYS */;

-- Dumping structure for table bulleye_admin.b_organization
CREATE TABLE IF NOT EXISTS `b_organization` (
  `id` varchar(40) NOT NULL COMMENT '唯一标识',
  `pid` varchar(40) DEFAULT NULL,
  `code` varchar(30) DEFAULT NULL COMMENT '组织编码',
  `name` varchar(100) DEFAULT NULL COMMENT '组织名称',
  `area_id` varchar(40) DEFAULT NULL COMMENT '所在地区',
  `brief` varchar(255) DEFAULT NULL COMMENT '组织简介',
  `contact_name` varchar(20) DEFAULT NULL COMMENT '联系人',
  `contact_phone` varchar(20) DEFAULT NULL COMMENT '联系电话',
  `domain` varchar(100) DEFAULT NULL COMMENT '域名',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `end_date` timestamp NULL DEFAULT NULL COMMENT '试用结束日期',
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='组织信息表';

-- Dumping data for table bulleye_admin.b_organization: ~0 rows (approximately)
DELETE FROM `b_organization`;
/*!40000 ALTER TABLE `b_organization` DISABLE KEYS */;
INSERT INTO `b_organization` (`id`, `pid`, `code`, `name`, `area_id`, `brief`, `contact_name`, `contact_phone`, `domain`, `status`, `end_date`, `created`) VALUES
	('suiji', NULL, 'admin', '管理', NULL, '管理组', '管理员工', '1328179872', NULL, 0, '2018-07-31 11:41:55', '2018-07-31 11:41:56');
/*!40000 ALTER TABLE `b_organization` ENABLE KEYS */;

-- Dumping structure for table bulleye_admin.b_organization_menu
CREATE TABLE IF NOT EXISTS `b_organization_menu` (
  `id` varchar(40) NOT NULL,
  `organization_id` varchar(40) DEFAULT NULL,
  `menu_id` varchar(40) DEFAULT NULL,
  `parent_id` varchar(40) DEFAULT NULL,
  `display_name` varchar(40) DEFAULT NULL,
  `status` int(11) DEFAULT '0',
  `is_leaf` bit(1) DEFAULT NULL,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='组织自己的菜单层级';

-- Dumping data for table bulleye_admin.b_organization_menu: ~17 rows (approximately)
DELETE FROM `b_organization_menu`;
/*!40000 ALTER TABLE `b_organization_menu` DISABLE KEYS */;
INSERT INTO `b_organization_menu` (`id`, `organization_id`, `menu_id`, `parent_id`, `display_name`, `status`, `is_leaf`, `created`) VALUES
	('5f998598-876e-11e9-b58b-382c5e4d3b2a', 'suiji', '2', NULL, '系统管理', 1, b'0', '2019-06-05 16:45:55'),
	('5f9b31ff-876e-11e9-b58b-382c5e4d3b2a', 'suiji', '4', NULL, '账号管理', 1, b'1', '2019-06-05 16:45:55'),
	('5f9bd660-876e-11e9-b58b-382c5e4d3b2a', 'suiji', '3', NULL, '角色管理', 1, b'1', '2019-06-05 16:45:55'),
	('5f9c94f5-876e-11e9-b58b-382c5e4d3b2a', 'suiji', '343223', NULL, '操作日志管理', 1, b'1', '2019-06-05 16:45:55'),
	('5f9d458d-876e-11e9-b58b-382c5e4d3b2a', 'suiji', '2333', NULL, '登录日志管理', 1, b'1', '2019-06-05 16:45:55'),
	('5fa01f2d-876e-11e9-b58b-382c5e4d3b2a', 'suiji', '10', NULL, '组织机构管理', 1, b'1', '2019-06-05 16:45:55'),
	('5fa0c8f4-876e-11e9-b58b-382c5e4d3b2a', 'suiji', '5', NULL, '员工管理', 1, b'1', '2019-06-05 16:45:55'),
	('5fa17251-876e-11e9-b58b-382c5e4d3b2a', 'suiji', '17', NULL, '菜单管理', 1, b'1', '2019-06-05 16:45:55'),
	('5fa21446-876e-11e9-b58b-382c5e4d3b2a', 'suiji', '11', NULL, '机构部门管理', 1, b'1', '2019-06-05 16:45:55'),
	('5fa2e790-876e-11e9-b58b-382c5e4d3b2a', 'suiji', '52091a21-460a-11e9-bd13-382c5e4d3b2a', NULL, '菜单权限管理', 1, b'1', '2019-06-05 16:45:55'),
	('5fa3a5b0-876e-11e9-b58b-382c5e4d3b2a', 'suiji', '12', NULL, '一级菜单', 1, b'0', '2019-06-05 16:45:55'),
	('5fa45351-876e-11e9-b58b-382c5e4d3b2a', 'suiji', '13', NULL, '二级菜单', 1, b'0', '2019-06-05 16:45:55'),
	('5fa4fb8c-876e-11e9-b58b-382c5e4d3b2a', 'suiji', '14', NULL, '三级菜单', 1, b'1', '2019-06-05 16:45:55'),
	('5fa5b1a7-876e-11e9-b58b-382c5e4d3b2a', 'suiji', '1', NULL, '微信管理', 1, b'0', '2019-06-05 16:45:55'),
	('5fa665cc-876e-11e9-b58b-382c5e4d3b2a', 'suiji', '7', NULL, '微信公众号管理', 1, b'1', '2019-06-05 16:45:55'),
	('5fa72494-876e-11e9-b58b-382c5e4d3b2a', 'suiji', '8', NULL, '微信会员管理', 1, b'1', '2019-06-05 16:45:55'),
	('5fa80603-876e-11e9-b58b-382c5e4d3b2a', 'suiji', '6', NULL, '微信菜单管理', 1, b'1', '2019-06-05 16:45:55');
/*!40000 ALTER TABLE `b_organization_menu` ENABLE KEYS */;

-- Dumping structure for table bulleye_admin.b_organization_role
CREATE TABLE IF NOT EXISTS `b_organization_role` (
  `id` varchar(50) NOT NULL,
  `organization_id` varchar(50) NOT NULL,
  `role_id` varchar(50) NOT NULL,
  `status` int(11) NOT NULL,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='组织角色授权';

-- Dumping data for table bulleye_admin.b_organization_role: ~0 rows (approximately)
DELETE FROM `b_organization_role`;
/*!40000 ALTER TABLE `b_organization_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `b_organization_role` ENABLE KEYS */;

-- Dumping structure for table bulleye_admin.b_role
CREATE TABLE IF NOT EXISTS `b_role` (
  `id` varchar(40) NOT NULL COMMENT '唯一标识',
  `organization_id` varchar(40) DEFAULT NULL COMMENT '组织标识',
  `name` varchar(50) DEFAULT NULL COMMENT '角色名称',
  `code` varchar(50) DEFAULT NULL COMMENT '角色代码',
  `data_auth_flag` varchar(30) DEFAULT 'personal' COMMENT '个人（pesonal），部门（department）,组织（organization）,系统（system）',
  `remark` varchar(100) DEFAULT NULL COMMENT '角色描述',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

-- Dumping data for table bulleye_admin.b_role: ~4 rows (approximately)
DELETE FROM `b_role`;
/*!40000 ALTER TABLE `b_role` DISABLE KEYS */;
INSERT INTO `b_role` (`id`, `organization_id`, `name`, `code`, `data_auth_flag`, `remark`, `status`, `created`) VALUES
	('1', 'admin', 'admin', 'admin', 'personal', '1212122222222', 0, '2018-07-31 11:44:18'),
	('12312312312312123', 'suiji', '4325345', 'test', NULL, '测试角色', 0, '2019-05-02 00:01:33'),
	('2', 'suiji', 'super', 'super', 'personal', '超级', 0, '2019-05-02 20:22:31'),
	('5ebcefdf-6c2a-11e9-8e25-049226bfdadf', 'suiji', '测试', 'test', NULL, '测试角色', 0, '2019-05-02 00:01:33');
/*!40000 ALTER TABLE `b_role` ENABLE KEYS */;

-- Dumping structure for table bulleye_admin.b_role_menu
CREATE TABLE IF NOT EXISTS `b_role_menu` (
  `id` varchar(40) NOT NULL COMMENT '唯一标识',
  `role_id` varchar(40) DEFAULT NULL COMMENT '角色外键',
  `menu_id` varchar(40) NOT NULL COMMENT '菜单外键',
  `status` int(11) DEFAULT '1',
  `is_leaf` bit(1) DEFAULT b'1',
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色菜单表';

-- Dumping data for table bulleye_admin.b_role_menu: ~34 rows (approximately)
DELETE FROM `b_role_menu`;
/*!40000 ALTER TABLE `b_role_menu` DISABLE KEYS */;
INSERT INTO `b_role_menu` (`id`, `role_id`, `menu_id`, `status`, `is_leaf`, `created`) VALUES
	('2ea343da-6d4d-11e9-8e25-049226bfdadf', '5ebcefdf-6c2a-11e9-8e25-049226bfdadf', '2', 1, b'0', '2019-05-03 10:43:13'),
	('2ea44e42-6d4d-11e9-8e25-049226bfdadf', '5ebcefdf-6c2a-11e9-8e25-049226bfdadf', '52091a21-460a-11e9-bd13-382c5e4d3b2a', 1, b'1', '2019-05-03 10:43:13'),
	('2ea52a76-6d4d-11e9-8e25-049226bfdadf', '5ebcefdf-6c2a-11e9-8e25-049226bfdadf', '343223', 1, b'1', '2019-05-03 10:43:13'),
	('2ea651e2-6d4d-11e9-8e25-049226bfdadf', '5ebcefdf-6c2a-11e9-8e25-049226bfdadf', '3', 1, b'1', '2019-05-03 10:43:13'),
	('2ea7825c-6d4d-11e9-8e25-049226bfdadf', '5ebcefdf-6c2a-11e9-8e25-049226bfdadf', '2333', 1, b'1', '2019-05-03 10:43:13'),
	('2ea87ee4-6d4d-11e9-8e25-049226bfdadf', '5ebcefdf-6c2a-11e9-8e25-049226bfdadf', '4', 1, b'1', '2019-05-03 10:43:13'),
	('63d255d2-876e-11e9-b58b-382c5e4d3b2a', '2', '2', 1, b'0', '2019-06-05 16:46:02'),
	('63d34d01-876e-11e9-b58b-382c5e4d3b2a', '2', '5', 1, b'1', '2019-06-05 16:46:02'),
	('63d3de53-876e-11e9-b58b-382c5e4d3b2a', '2', '343223', 1, b'1', '2019-06-05 16:46:02'),
	('63d4759a-876e-11e9-b58b-382c5e4d3b2a', '2', '11', 1, b'1', '2019-06-05 16:46:02'),
	('63d512ad-876e-11e9-b58b-382c5e4d3b2a', '2', '10', 1, b'1', '2019-06-05 16:46:02'),
	('63d5b548-876e-11e9-b58b-382c5e4d3b2a', '2', '3', 1, b'1', '2019-06-05 16:46:02'),
	('63d652b3-876e-11e9-b58b-382c5e4d3b2a', '2', '17', 1, b'1', '2019-06-05 16:46:02'),
	('63d6e922-876e-11e9-b58b-382c5e4d3b2a', '2', '2333', 1, b'1', '2019-06-05 16:46:02'),
	('63d779a0-876e-11e9-b58b-382c5e4d3b2a', '2', '4', 1, b'1', '2019-06-05 16:46:02'),
	('63d821a0-876e-11e9-b58b-382c5e4d3b2a', '2', '52091a21-460a-11e9-bd13-382c5e4d3b2a', 1, b'1', '2019-06-05 16:46:02'),
	('63d8abc6-876e-11e9-b58b-382c5e4d3b2a', '2', '1', 1, b'0', '2019-06-05 16:46:02'),
	('63d95724-876e-11e9-b58b-382c5e4d3b2a', '2', '7', 1, b'1', '2019-06-05 16:46:02'),
	('63d9ec78-876e-11e9-b58b-382c5e4d3b2a', '2', '6', 1, b'1', '2019-06-05 16:46:02'),
	('63daaf90-876e-11e9-b58b-382c5e4d3b2a', '2', '8', 1, b'1', '2019-06-05 16:46:02'),
	('63db95c7-876e-11e9-b58b-382c5e4d3b2a', '2', '12', 1, b'0', '2019-06-05 16:46:02'),
	('63dc25b9-876e-11e9-b58b-382c5e4d3b2a', '2', '13', 1, b'0', '2019-06-05 16:46:02'),
	('63dd06f9-876e-11e9-b58b-382c5e4d3b2a', '2', '14', 1, b'1', '2019-06-05 16:46:02'),
	('64e36753-6d7e-11e9-8e25-049226bfdadf', '12312312312312123', '3', 1, b'1', '2019-05-03 16:35:29'),
	('64e44ae8-6d7e-11e9-8e25-049226bfdadf', '12312312312312123', '3', 1, b'1', '2019-05-03 17:58:31'),
	('64e540b1-6d7e-11e9-8e25-049226bfdadf', '12312312312312123', '3', 1, b'0', '2019-05-03 17:58:30'),
	('68863f3b-6d7e-11e9-8e25-049226bfdadf', '1', '4', 1, b'1', '2019-05-03 16:35:35'),
	('688722b6-6d7e-11e9-8e25-049226bfdadf', '1', '3', 1, b'1', '2019-05-03 16:35:35'),
	('68881792-6d7e-11e9-8e25-049226bfdadf', '1', '52091a21-460a-11e9-bd13-382c5e4d3b2a', 1, b'1', '2019-05-03 16:35:35'),
	('6888cc6e-6d7e-11e9-8e25-049226bfdadf', '1', '343223', 1, b'1', '2019-05-03 16:35:35'),
	('68899510-6d7e-11e9-8e25-049226bfdadf', '1', '12', 1, b'0', '2019-05-03 16:35:35'),
	('688a5527-6d7e-11e9-8e25-049226bfdadf', '1', '13', 1, b'0', '2019-05-03 16:35:35'),
	('688b1e81-6d7e-11e9-8e25-049226bfdadf', '1', '3', 1, b'1', '2019-05-03 17:58:17'),
	('688bffdc-6d7e-11e9-8e25-049226bfdadf', '1', '2', 1, b'0', '2019-05-03 16:35:35');
/*!40000 ALTER TABLE `b_role_menu` ENABLE KEYS */;

-- Dumping structure for table bulleye_admin.b_role_menu_auth
CREATE TABLE IF NOT EXISTS `b_role_menu_auth` (
  `id` varchar(40) NOT NULL COMMENT '唯一标识',
  `role_id` varchar(40) DEFAULT NULL COMMENT '角色ID',
  `menu_auth_id` varchar(40) DEFAULT NULL COMMENT '授权路径ID',
  `user_id` varchar(40) DEFAULT NULL COMMENT '操作人',
  `status` int(2) DEFAULT NULL COMMENT '状态',
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色菜单路径授权';

-- Dumping data for table bulleye_admin.b_role_menu_auth: ~2 rows (approximately)
DELETE FROM `b_role_menu_auth`;
/*!40000 ALTER TABLE `b_role_menu_auth` DISABLE KEYS */;
INSERT INTO `b_role_menu_auth` (`id`, `role_id`, `menu_auth_id`, `user_id`, `status`, `created`) VALUES
	('dfdf', '1', '5376de89-4b9d-11e9-8600-382c5e4d3b2a', NULL, NULL, '2019-01-08 14:26:05'),
	('dsdfsfdsdfsdfs', '2', '83e6e514-4c4b-11e9-8600-382c5e4d3b2a', NULL, NULL, '2019-01-07 17:03:17');
/*!40000 ALTER TABLE `b_role_menu_auth` ENABLE KEYS */;

-- Dumping structure for table bulleye_admin.b_staff
CREATE TABLE IF NOT EXISTS `b_staff` (
  `id` varchar(40) NOT NULL COMMENT 'ID',
  `serial_no` varchar(20) DEFAULT NULL COMMENT '工号',
  `name` varchar(20) DEFAULT NULL COMMENT '姓名',
  `gender_id` varchar(40) DEFAULT NULL COMMENT '性别',
  `mobile` varchar(20) DEFAULT NULL COMMENT '电话号码',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像',
  `organization_id` varchar(40) DEFAULT NULL COMMENT '所属组织',
  `department_id` varchar(40) DEFAULT NULL COMMENT '所属部门',
  `birthday` varchar(20) DEFAULT NULL COMMENT '生日',
  `academic_id` varchar(40) DEFAULT NULL COMMENT '学位',
  `degree_id` varchar(40) DEFAULT NULL COMMENT '学历',
  `position_id` varchar(40) DEFAULT NULL COMMENT '职位',
  `title_id` varchar(40) DEFAULT NULL COMMENT '职称',
  `type_id` varchar(40) DEFAULT NULL COMMENT '人员类型',
  `identify_type_id` varchar(40) DEFAULT NULL COMMENT '证件类型',
  `identify_no` varchar(20) DEFAULT NULL COMMENT '证件号码',
  `birth_province_id` varchar(40) DEFAULT NULL COMMENT '所属省份',
  `birth_city_id` varchar(40) DEFAULT NULL COMMENT '所属城市',
  `policy` varchar(40) DEFAULT NULL COMMENT '政治面貌',
  `nation_id` varchar(40) DEFAULT NULL COMMENT '民族',
  `join_date` varchar(40) DEFAULT NULL COMMENT '入职日期',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='人员信息表';

-- Dumping data for table bulleye_admin.b_staff: ~1 rows (approximately)
DELETE FROM `b_staff`;
/*!40000 ALTER TABLE `b_staff` DISABLE KEYS */;
INSERT INTO `b_staff` (`id`, `serial_no`, `name`, `gender_id`, `mobile`, `email`, `avatar`, `organization_id`, `department_id`, `birthday`, `academic_id`, `degree_id`, `position_id`, `title_id`, `type_id`, `identify_type_id`, `identify_no`, `birth_province_id`, `birth_city_id`, `policy`, `nation_id`, `join_date`, `remark`, `status`, `created`) VALUES
	('1', '111111111', '周红星12', '1', '13258179872', '121212@qq.com', 'http://localhost:8001/file/view/fab59962-882c-11e9-b58b-382c5e4d3b2a', 'suiji', 'test', '1995-06-10', '1', '1', '1', '1', '1', '111', '1', '', '1', '1', '11', '2018-07-20', '11111', 0, '2018-07-31 14:15:10');
/*!40000 ALTER TABLE `b_staff` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
