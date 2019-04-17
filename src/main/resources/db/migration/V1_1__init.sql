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

-- Dumping structure for table bulleye_admin.b_accounts
DROP TABLE IF EXISTS `b_accounts`;
CREATE TABLE IF NOT EXISTS `b_accounts` (
  `id` varchar(40) NOT NULL COMMENT '唯一标识',
  `username` varchar(50) DEFAULT NULL COMMENT '账户',
  `password` varchar(128) DEFAULT NULL COMMENT '密码',
  `account_locked` tinyint(1) DEFAULT NULL COMMENT '是否锁定',
  `credentials_expired` tinyint(1) DEFAULT NULL COMMENT '是否凭证过期',
  `account_expired` tinyint(1) DEFAULT NULL COMMENT '是否过期',
  `staff_id` varchar(40) DEFAULT NULL COMMENT '人员标识',
  `organization_id` varchar(40) DEFAULT NULL COMMENT '组织标识',
  `wx_openid` varchar(64) DEFAULT NULL COMMENT '微信OpenId',
  `alipay_openid` varchar(64) DEFAULT NULL COMMENT '支付宝OpenId',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `mobile_login_flag` bit(1) DEFAULT NULL COMMENT '是否开通手机号登录',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `expired_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '账号过期时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- Dumping data for table bulleye_admin.b_accounts: ~2 rows (approximately)
DELETE FROM `b_accounts`;
/*!40000 ALTER TABLE `b_accounts` DISABLE KEYS */;
INSERT INTO `b_accounts` (`id`, `username`, `password`, `account_locked`, `credentials_expired`, `account_expired`, `staff_id`, `organization_id`, `wx_openid`, `alipay_openid`, `email`, `status`, `mobile_login_flag`, `remark`, `created`, `modified`, `expired_date`) VALUES
	('00278d19-9794-11e7-975b-000c29687dd9', 'admin', '{bcrypt}$2a$10$cBxwbxYB8iUZ2.W2BspGbOPxAlsZT1ppDtD.nFJsJMCQMAW8YCUWy', 1, NULL, 1, '1', 'suiji', NULL, NULL, '961860916@qq.com', 1, NULL, NULL, '2019-04-16 19:25:32', '2018-06-13 11:07:19', NULL),
	('4081fe65-125a-11e9-97db-382c4a232da1', 'zhx', '{bcrypt}$2a$10$cBxwbxYB8iUZ2.W2BspGbOPxAlsZT1ppDtD.nFJsJMCQMAW8YCUWy', 1, NULL, 1, '1', 'suiji', NULL, NULL, '961860916@qq.com', 1, NULL, NULL, '2019-04-16 19:25:32', NULL, NULL);
/*!40000 ALTER TABLE `b_accounts` ENABLE KEYS */;

-- Dumping structure for table bulleye_admin.b_account_role
DROP TABLE IF EXISTS `b_account_role`;
CREATE TABLE IF NOT EXISTS `b_account_role` (
  `id` varchar(40) NOT NULL COMMENT '唯一标识',
  `role_id` varchar(40) DEFAULT NULL COMMENT '角色外键',
  `account_id` varchar(40) DEFAULT NULL COMMENT '用户外键',
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色中间表';

-- Dumping data for table bulleye_admin.b_account_role: ~2 rows (approximately)
DELETE FROM `b_account_role`;
/*!40000 ALTER TABLE `b_account_role` DISABLE KEYS */;
INSERT INTO `b_account_role` (`id`, `role_id`, `account_id`, `created`) VALUES
	('1', '1', '00278d19-9794-11e7-975b-000c29687dd9', '2018-07-31 11:49:48'),
	('2', '2', '4081fe65-125a-11e9-97db-382c4a232da1', '2019-01-08 14:24:11');
/*!40000 ALTER TABLE `b_account_role` ENABLE KEYS */;

-- Dumping structure for table bulleye_admin.b_departments
DROP TABLE IF EXISTS `b_departments`;
CREATE TABLE IF NOT EXISTS `b_departments` (
  `id` varchar(40) NOT NULL COMMENT '唯一标识',
  `organization_id` varchar(40) DEFAULT NULL COMMENT '组织标识',
  `code` varchar(30) DEFAULT NULL COMMENT '部门代码',
  `name` varchar(100) DEFAULT NULL COMMENT '部门名称',
  `type_id` varchar(40) DEFAULT NULL COMMENT '部门类型',
  `remark` varchar(255) DEFAULT NULL COMMENT '描述',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `created` bigint(20) NOT NULL COMMENT '创建日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部门表';

-- Dumping data for table bulleye_admin.b_departments: ~0 rows (approximately)
DELETE FROM `b_departments`;
/*!40000 ALTER TABLE `b_departments` DISABLE KEYS */;
INSERT INTO `b_departments` (`id`, `organization_id`, `code`, `name`, `type_id`, `remark`, `status`, `created`) VALUES
	('test', 'suiji', '管理部门', '管理部门', 'guanli', '管理', 0, 20180731114302);
/*!40000 ALTER TABLE `b_departments` ENABLE KEYS */;

-- Dumping structure for table bulleye_admin.b_dictionary_data
DROP TABLE IF EXISTS `b_dictionary_data`;
CREATE TABLE IF NOT EXISTS `b_dictionary_data` (
  `id` varchar(40) NOT NULL COMMENT '唯一标识',
  `type_id` varchar(40) DEFAULT NULL COMMENT '类型标识',
  `code` varchar(20) DEFAULT NULL COMMENT '字典编码',
  `name` varchar(100) DEFAULT NULL COMMENT '字典名称',
  `remark` varchar(200) DEFAULT NULL COMMENT '字典备注',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `created` bigint(20) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='字典数据表';

-- Dumping data for table bulleye_admin.b_dictionary_data: ~0 rows (approximately)
DELETE FROM `b_dictionary_data`;
/*!40000 ALTER TABLE `b_dictionary_data` DISABLE KEYS */;
/*!40000 ALTER TABLE `b_dictionary_data` ENABLE KEYS */;

-- Dumping structure for table bulleye_admin.b_dictionary_type
DROP TABLE IF EXISTS `b_dictionary_type`;
CREATE TABLE IF NOT EXISTS `b_dictionary_type` (
  `id` varchar(40) NOT NULL COMMENT '唯一标识',
  `code` varchar(30) DEFAULT NULL COMMENT '类型代码',
  `name` varchar(50) DEFAULT NULL COMMENT '类型名称',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `created` bigint(20) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部门类型、性别、专业、学历、职位、职称';

-- Dumping data for table bulleye_admin.b_dictionary_type: ~0 rows (approximately)
DELETE FROM `b_dictionary_type`;
/*!40000 ALTER TABLE `b_dictionary_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `b_dictionary_type` ENABLE KEYS */;

-- Dumping structure for table bulleye_admin.b_functions
DROP TABLE IF EXISTS `b_functions`;
CREATE TABLE IF NOT EXISTS `b_functions` (
  `id` varchar(40) NOT NULL COMMENT '唯一标识',
  `code` varchar(40) DEFAULT NULL COMMENT '功能code',
  `name` varchar(50) DEFAULT NULL COMMENT '功能名称',
  `icon` varchar(50) DEFAULT NULL COMMENT '图标',
  `url` varchar(255) DEFAULT NULL COMMENT '功能url',
  `describe` varchar(200) DEFAULT NULL COMMENT '功能描述',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `created` bigint(20) NOT NULL COMMENT '创建日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限表';

-- Dumping data for table bulleye_admin.b_functions: ~2 rows (approximately)
DELETE FROM `b_functions`;
/*!40000 ALTER TABLE `b_functions` DISABLE KEYS */;
INSERT INTO `b_functions` (`id`, `code`, `name`, `icon`, `url`, `describe`, `status`, `created`) VALUES
	('1', 'add', '增加', NULL, NULL, NULL, 0, 20180731114847),
	('2', 'list', NULL, NULL, NULL, NULL, NULL, 20190108142619);
/*!40000 ALTER TABLE `b_functions` ENABLE KEYS */;

-- Dumping structure for table bulleye_admin.b_login_record
DROP TABLE IF EXISTS `b_login_record`;
CREATE TABLE IF NOT EXISTS `b_login_record` (
  `id` varchar(50) NOT NULL COMMENT '访问ID',
  `username` varchar(50) DEFAULT '' COMMENT '登录账号',
  `ip` varchar(50) DEFAULT '' COMMENT '登录IP地址',
  `login_location` varchar(255) DEFAULT '' COMMENT '登录地点',
  `browser` varchar(50) DEFAULT '' COMMENT '浏览器类型',
  `os` varchar(50) DEFAULT '' COMMENT '操作系统',
  `status` char(1) DEFAULT '0' COMMENT '登录状态（0成功 1失败）',
  `message` varchar(255) DEFAULT '' COMMENT '提示消息',
  `created` datetime DEFAULT NULL COMMENT '访问时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统访问记录';

-- Dumping data for table bulleye_admin.b_login_record: ~0 rows (approximately)
DELETE FROM `b_login_record`;
/*!40000 ALTER TABLE `b_login_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `b_login_record` ENABLE KEYS */;

-- Dumping structure for table bulleye_admin.b_logs
DROP TABLE IF EXISTS `b_logs`;
CREATE TABLE IF NOT EXISTS `b_logs` (
  `id` varchar(40) NOT NULL,
  `organization_id` varchar(40) DEFAULT NULL,
  `option_name` varchar(50) DEFAULT NULL,
  `option_type` varchar(20) DEFAULT NULL COMMENT '新增、删除、修改、查询、登录',
  `params` text,
  `user_id` varchar(40) DEFAULT NULL,
  `ip` varchar(50) DEFAULT NULL,
  `execution_time` int(11) DEFAULT NULL COMMENT '执行时长',
  `method` varchar(200) DEFAULT NULL,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='操作日志';

-- Dumping data for table bulleye_admin.b_logs: ~5 rows (approximately)
DELETE FROM `b_logs`;
/*!40000 ALTER TABLE `b_logs` DISABLE KEYS */;
INSERT INTO `b_logs` (`id`, `organization_id`, `option_name`, `option_type`, `params`, `user_id`, `ip`, `execution_time`, `method`, `created`, `status`) VALUES
	('13b1335b-6053-11e9-8e25-049226bfdadf', 'suiji', '员工管理', '', '', '4081fe65-125a-11e9-97db-382c4a232da1', '127.0.0.1', 9, 'com.chris.bulleyeadmin.system.controller.StaffController.getStaffInfo()', '2019-04-16 22:22:30', 1),
	('13b133a3-6053-11e9-8e25-049226bfdadf', 'suiji', '菜单管理', '获取所有菜单', '', '4081fe65-125a-11e9-97db-382c4a232da1', '127.0.0.1', 8, 'com.chris.bulleyeadmin.system.controller.MenuController.getAllMenus()', '2019-04-16 22:22:30', 1),
	('13d5ab47-6053-11e9-8e25-049226bfdadf', 'suiji', '公众号管理', '', '\n{\n	"id":null,\n	"qrCode":null,\n	"name":null,\n	"sourceId":null,\n	"appId":null,\n	"appSecret":null,\n	"token":null,\n	"aesKey":null,\n	"domain":null,\n	"accessToken":null,\n	"tokenTime":null,\n	"menuState":null,\n	"userId":null,\n	"created":null,\n	"updated":null,\n	"remark":null,\n	"status":null,\n	"partner":null,\n	"partnerKey":null,\n	"certificatePath":null\n}', '4081fe65-125a-11e9-97db-382c4a232da1', '127.0.0.1', 4, 'com.chris.bulleyeadmin.wechat.controller.WxAccountController.select()', '2019-04-16 22:22:31', 1),
	('13e002a5-6053-11e9-8e25-049226bfdadf', 'suiji', '公众号菜单管理', '查询分页数据', '\n"dfdfgsdfgsdfgsdfgsdfgsdfgsdfgsdfg"', '4081fe65-125a-11e9-97db-382c4a232da1', '127.0.0.1', 4, 'com.chris.bulleyeadmin.wechat.controller.WxMenuController.listPage()', '2019-04-16 22:22:31', 1),
	('e1c29581-60d4-11e9-91e3-382c5e4d3b2a', NULL, '登陆', '', '', NULL, '127.0.0.1', 6, 'com.chris.bulleyeadmin.system.controller.LoginController.loginPage()', '2019-04-17 13:51:33', 1);
/*!40000 ALTER TABLE `b_logs` ENABLE KEYS */;

-- Dumping structure for table bulleye_admin.b_menus
DROP TABLE IF EXISTS `b_menus`;
CREATE TABLE IF NOT EXISTS `b_menus` (
  `id` varchar(40) NOT NULL COMMENT '唯一标识',
  `parent_id` varchar(40) DEFAULT NULL COMMENT '父菜单id',
  `icon` varchar(50) DEFAULT NULL COMMENT '图标',
  `code` varchar(40) DEFAULT NULL COMMENT '代码',
  `title` varchar(50) DEFAULT NULL COMMENT '名称',
  `path` varchar(255) DEFAULT NULL COMMENT '路径',
  `status` int(11) DEFAULT NULL COMMENT '是否显示（0：不显示，1：显示）',
  `sort` int(11) DEFAULT NULL COMMENT '显示顺序',
  `created` bigint(20) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单表';

-- Dumping data for table bulleye_admin.b_menus: ~16 rows (approximately)
DELETE FROM `b_menus`;
/*!40000 ALTER TABLE `b_menus` DISABLE KEYS */;
INSERT INTO `b_menus` (`id`, `parent_id`, `icon`, `code`, `title`, `path`, `status`, `sort`, `created`) VALUES
	('1', NULL, 'wechat', 'admin', '微信管理', '/menu', 1, 2, 20180731114527),
	('10', '2', NULL, '111', '组织机构管理', '/organization/list', 1, 1, 20180731171128),
	('11', '2', NULL, '111', '机构部门管理', '/department/list', 1, 1, 20180731171128),
	('12', NULL, NULL, NULL, '一级菜单', NULL, 1, 1, 20180865115616),
	('13', '12', NULL, NULL, '二级菜单', NULL, 1, 1, 20180865115616),
	('14', '13', 'wechat', '665869', '三级菜单', '111', 1, 1, 20180865115616),
	('17', '2', NULL, NULL, '菜单管理', '/menu/list', 1, 1, 20180865115616),
	('2', NULL, NULL, 'admin1', '系统管理', '/account/list', 1, 1, 20180731114527),
	('3', '2', NULL, 'staff', '角色管理', '/role/list', 1, 1, 20180731114527),
	('4', '3', NULL, 'admin2', '账号管理', '/account/list', 1, 1, 20180731114527),
	('5', '3', NULL, '111', '员工管理', '/staff/list', 1, 1, 20180731171128),
	('52091a21-460a-11e9-bd13-382c5e4d3b2a', '2', 'wechat', '1', '菜单权限管理', '/menuauth/list', 1, 111, 1552534575142),
	('6', '1', 'wechat', '111', '微信菜单管理', '/wxmenu/list', 1, 1, 20180731171128),
	('7', '1', 'wechat', '111', '微信公众号管理', '/wxaccount/list', 1, 1, 20180731171128),
	('8', '1', NULL, '111', '微信会员管理', '/wxmember/list', 1, 1, 20180731171128),
	('9', '1', NULL, '111', '微信自动回复管理', '/wxreply/list', 0, 1, 20180731171128);
/*!40000 ALTER TABLE `b_menus` ENABLE KEYS */;

-- Dumping structure for table bulleye_admin.b_menu_auth
DROP TABLE IF EXISTS `b_menu_auth`;
CREATE TABLE IF NOT EXISTS `b_menu_auth` (
  `id` varchar(50) NOT NULL,
  `menu_id` varchar(50) DEFAULT NULL,
  `code` varchar(50) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `path` varchar(50) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `created` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单权限表';

-- Dumping data for table bulleye_admin.b_menu_auth: ~2 rows (approximately)
DELETE FROM `b_menu_auth`;
/*!40000 ALTER TABLE `b_menu_auth` DISABLE KEYS */;
INSERT INTO `b_menu_auth` (`id`, `menu_id`, `code`, `name`, `path`, `status`, `created`) VALUES
	('5376de89-4b9d-11e9-8600-382c5e4d3b2a', '1', 'query', '查看', '/query', '0', NULL),
	('83e6e514-4c4b-11e9-8600-382c5e4d3b2a', '4', 'list', '查看', 'list', '0', NULL);
/*!40000 ALTER TABLE `b_menu_auth` ENABLE KEYS */;

-- Dumping structure for table bulleye_admin.b_menu_function
DROP TABLE IF EXISTS `b_menu_function`;
CREATE TABLE IF NOT EXISTS `b_menu_function` (
  `id` varchar(40) NOT NULL COMMENT '唯一标识',
  `menu_id` varchar(40) DEFAULT NULL COMMENT '菜单外键',
  `function_id` varchar(40) DEFAULT NULL COMMENT '功能外键',
  `created` bigint(20) NOT NULL COMMENT '创建日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单功能中间表';

-- Dumping data for table bulleye_admin.b_menu_function: ~2 rows (approximately)
DELETE FROM `b_menu_function`;
/*!40000 ALTER TABLE `b_menu_function` DISABLE KEYS */;
INSERT INTO `b_menu_function` (`id`, `menu_id`, `function_id`, `created`) VALUES
	('dfdf', '3', '2', 20190108142605),
	('dsdfsfdsdfsdfs', '2', '1', 20190107170317);
/*!40000 ALTER TABLE `b_menu_function` ENABLE KEYS */;

-- Dumping structure for table bulleye_admin.b_organizations
DROP TABLE IF EXISTS `b_organizations`;
CREATE TABLE IF NOT EXISTS `b_organizations` (
  `id` varchar(40) NOT NULL COMMENT '唯一标识',
  `pid` varchar(40) DEFAULT NULL,
  `code` varchar(30) DEFAULT NULL COMMENT '组织编码',
  `name` varchar(100) DEFAULT NULL COMMENT '组织名称',
  `area_id` varchar(40) DEFAULT NULL COMMENT '所在地区',
  `brief` varchar(255) DEFAULT NULL COMMENT '组织简介',
  `contact_name` varchar(20) DEFAULT NULL COMMENT '联系人',
  `contact_phone` varchar(20) DEFAULT NULL COMMENT '联系电话',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `end_date` bigint(20) DEFAULT NULL COMMENT '试用结束日期',
  `domain` varchar(100) DEFAULT NULL COMMENT '域名',
  `created` bigint(20) NOT NULL COMMENT '创建日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='组织信息表';

-- Dumping data for table bulleye_admin.b_organizations: ~0 rows (approximately)
DELETE FROM `b_organizations`;
/*!40000 ALTER TABLE `b_organizations` DISABLE KEYS */;
INSERT INTO `b_organizations` (`id`, `pid`, `code`, `name`, `area_id`, `brief`, `contact_name`, `contact_phone`, `status`, `end_date`, `domain`, `created`) VALUES
	('suiji', NULL, 'admin', '管理', NULL, '管理组', '管理员工', '1328179872', 0, 20180731114155, NULL, 20180731114156);
/*!40000 ALTER TABLE `b_organizations` ENABLE KEYS */;

-- Dumping structure for table bulleye_admin.b_organization_menu
DROP TABLE IF EXISTS `b_organization_menu`;
CREATE TABLE IF NOT EXISTS `b_organization_menu` (
  `id` varchar(40) NOT NULL,
  `organization_id` varchar(40) DEFAULT NULL,
  `menu_id` varchar(40) DEFAULT NULL,
  `p_id` varchar(40) DEFAULT NULL,
  `display_name` varchar(40) DEFAULT NULL,
  `created` bigint(20) NOT NULL,
  `status` int(11) DEFAULT '0',
  `is_leaf` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='组织自己的菜单层级';

-- Dumping data for table bulleye_admin.b_organization_menu: ~5 rows (approximately)
DELETE FROM `b_organization_menu`;
/*!40000 ALTER TABLE `b_organization_menu` DISABLE KEYS */;
INSERT INTO `b_organization_menu` (`id`, `organization_id`, `menu_id`, `p_id`, `display_name`, `created`, `status`, `is_leaf`) VALUES
	('c7c4cffd-603d-11e9-8e25-049226bfdadf', 'suiji', '5', NULL, '员工管理', 1555415403665, 1, b'1'),
	('c7c6b725-603d-11e9-8e25-049226bfdadf', 'suiji', '8', NULL, '微信会员管理', 1555415403677, 1, b'1'),
	('c7c7fbc3-603d-11e9-8e25-049226bfdadf', 'suiji', '2', NULL, '系统管理', 1555415403685, 1, b'0'),
	('c7c949a4-603d-11e9-8e25-049226bfdadf', 'suiji', '3', NULL, '角色管理', 1555415403694, 1, b'0'),
	('c7ca782e-603d-11e9-8e25-049226bfdadf', 'suiji', '1', NULL, '微信管理', 1555415403702, 1, b'0');
/*!40000 ALTER TABLE `b_organization_menu` ENABLE KEYS */;

-- Dumping structure for table bulleye_admin.b_organization_role
DROP TABLE IF EXISTS `b_organization_role`;
CREATE TABLE IF NOT EXISTS `b_organization_role` (
  `id` varchar(50) NOT NULL,
  `organization_id` varchar(50) NOT NULL,
  `role_id` varchar(50) NOT NULL,
  `status` int(11) DEFAULT NULL,
  `created` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='组织角色授权';

-- Dumping data for table bulleye_admin.b_organization_role: ~0 rows (approximately)
DELETE FROM `b_organization_role`;
/*!40000 ALTER TABLE `b_organization_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `b_organization_role` ENABLE KEYS */;

-- Dumping structure for table bulleye_admin.b_roles
DROP TABLE IF EXISTS `b_roles`;
CREATE TABLE IF NOT EXISTS `b_roles` (
  `id` varchar(40) NOT NULL COMMENT '唯一标识',
  `organization_id` varchar(40) DEFAULT NULL COMMENT '组织标识',
  `code` varchar(50) DEFAULT NULL COMMENT '角色代码',
  `name` varchar(50) DEFAULT NULL COMMENT '角色名称',
  `data_auth_flag` varchar(30) DEFAULT 'personal' COMMENT '个人（pesonal），部门（department）,组织（organization）,系统（system）',
  `describle` varchar(100) DEFAULT NULL COMMENT '角色描述',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `created` bigint(20) NOT NULL COMMENT '创建日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

-- Dumping data for table bulleye_admin.b_roles: ~2 rows (approximately)
DELETE FROM `b_roles`;
/*!40000 ALTER TABLE `b_roles` DISABLE KEYS */;
INSERT INTO `b_roles` (`id`, `organization_id`, `code`, `name`, `data_auth_flag`, `describle`, `status`, `created`) VALUES
	('1', 'admin', 'admin', 'admin', 'personal', '1', 0, 20180731114418),
	('2', 'super', 'super', 'super', 'personal', '超级', 0, 20180731115226);
/*!40000 ALTER TABLE `b_roles` ENABLE KEYS */;

-- Dumping structure for table bulleye_admin.b_role_function
DROP TABLE IF EXISTS `b_role_function`;
CREATE TABLE IF NOT EXISTS `b_role_function` (
  `id` varchar(40) NOT NULL,
  `role_id` varchar(40) DEFAULT NULL,
  `menu_id` varchar(40) DEFAULT NULL,
  `function_id` varchar(40) DEFAULT NULL,
  `created` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色功能关系表';

-- Dumping data for table bulleye_admin.b_role_function: ~2 rows (approximately)
DELETE FROM `b_role_function`;
/*!40000 ALTER TABLE `b_role_function` DISABLE KEYS */;
INSERT INTO `b_role_function` (`id`, `role_id`, `menu_id`, `function_id`, `created`) VALUES
	('dfgdf', '1', '1', '1', 20190107170559),
	('dfvd', '1', '3', '2', 20190108142426);
/*!40000 ALTER TABLE `b_role_function` ENABLE KEYS */;

-- Dumping structure for table bulleye_admin.b_role_menu
DROP TABLE IF EXISTS `b_role_menu`;
CREATE TABLE IF NOT EXISTS `b_role_menu` (
  `id` varchar(40) NOT NULL COMMENT '唯一标识',
  `role_id` varchar(40) DEFAULT NULL COMMENT '角色外键',
  `menu_id` varchar(40) NOT NULL COMMENT '菜单外键',
  `created` bigint(20) NOT NULL COMMENT '创建日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色菜单表';

-- Dumping data for table bulleye_admin.b_role_menu: ~5 rows (approximately)
DELETE FROM `b_role_menu`;
/*!40000 ALTER TABLE `b_role_menu` DISABLE KEYS */;
INSERT INTO `b_role_menu` (`id`, `role_id`, `menu_id`, `created`) VALUES
	('1', '1', '1', 20180731114624),
	('2', '1', '2', 20180731114624),
	('3', '1', '3', 20180731114624),
	('4', '1', '4', 20180731114624),
	('5', '1', '5', 20180731114624);
/*!40000 ALTER TABLE `b_role_menu` ENABLE KEYS */;

-- Dumping structure for table bulleye_admin.b_staff
DROP TABLE IF EXISTS `b_staff`;
CREATE TABLE IF NOT EXISTS `b_staff` (
  `id` varchar(40) NOT NULL,
  `serial_no` varchar(20) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `birthday` varchar(20) DEFAULT NULL,
  `gender_id` varchar(40) DEFAULT NULL,
  `academic_id` varchar(40) DEFAULT NULL,
  `degree_id` varchar(40) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像',
  `organization_id` varchar(40) DEFAULT NULL COMMENT '所属组织',
  `department_id` varchar(40) DEFAULT NULL,
  `position_id` varchar(40) DEFAULT NULL,
  `title_id` varchar(40) DEFAULT NULL,
  `type_id` varchar(40) DEFAULT NULL,
  `mobile` varchar(20) DEFAULT NULL,
  `identify_type_id` varchar(40) DEFAULT NULL,
  `identify_no` varchar(20) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `birth_province_id` varchar(40) DEFAULT NULL,
  `birth_city_id` varchar(40) DEFAULT NULL,
  `policy` varchar(40) DEFAULT NULL COMMENT '党员、共青团员、群众',
  `weight` decimal(4,1) DEFAULT NULL COMMENT '体重',
  `height` decimal(10,0) DEFAULT NULL COMMENT '身高',
  `health_status` varchar(50) DEFAULT NULL COMMENT '健康状态',
  `marry_status_id` varchar(40) DEFAULT NULL COMMENT '已婚、未婚、离异',
  `nation_id` varchar(40) DEFAULT NULL,
  `vmnet_no` varchar(20) DEFAULT NULL COMMENT 'V网账号',
  `join_date` varchar(20) DEFAULT NULL,
  `created` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='人员信息表';

-- Dumping data for table bulleye_admin.b_staff: ~12 rows (approximately)
DELETE FROM `b_staff`;
/*!40000 ALTER TABLE `b_staff` DISABLE KEYS */;
INSERT INTO `b_staff` (`id`, `serial_no`, `name`, `birthday`, `gender_id`, `academic_id`, `degree_id`, `avatar`, `organization_id`, `department_id`, `position_id`, `title_id`, `type_id`, `mobile`, `identify_type_id`, `identify_no`, `email`, `remark`, `status`, `birth_province_id`, `birth_city_id`, `policy`, `weight`, `height`, `health_status`, `marry_status_id`, `nation_id`, `vmnet_no`, `join_date`, `created`) VALUES
	('1', '111111111', '周红星12', '19950602', '1', '1', '1', 'http://thirdwx.qlogo.cn/mmopen/7DkVkX1qfEGSjTDwwYXoShDTqo60F3HiclqLUbZEfOh2eLJFXvKUkxS0j7hvsRDpshJkjQYDzmfNDLzvQUegXKRl0s8RyjwI5/132', 'suiji', 'test', '1', '1', '1', '13258179872', '', '', '121212@qq.com', '11111', 0, '', '', '', NULL, NULL, '', '', '', '', '', 20180731141510),
	('2323', '111111111', '周红星12', '19950602', '1', '1', '1', 'http://thirdwx.qlogo.cn/mmopen/7DkVkX1qfEGSjTDwwYXoShDTqo60F3HiclqLUbZEfOh2eLJFXvKUkxS0j7hvsRDpshJkjQYDzmfNDLzvQUegXKRl0s8RyjwI5/132', 'suiji', 'test', '1', '1', '1', '13258179872', '', '', '121212@qq.com', '11111', 0, '', '', '', NULL, NULL, '', '', '', '', '', 20180731141510),
	('2333', '111111111', '周红星12', '19950602', '1', '1', '1', 'http://thirdwx.qlogo.cn/mmopen/7DkVkX1qfEGSjTDwwYXoShDTqo60F3HiclqLUbZEfOh2eLJFXvKUkxS0j7hvsRDpshJkjQYDzmfNDLzvQUegXKRl0s8RyjwI5/132', 'suiji', 'test', '1', '1', '1', '13258179872', '', '', '121212@qq.com', '11111', 0, '', '', '', NULL, NULL, '', '', '', '', '', 20180731141510),
	('3333', '511', '周红星', '19950602', '1', '1', '1', 'http://thirdwx.qlogo.cn/mmopen/7DkVkX1qfEGSjTDwwYXoShDTqo60F3HiclqLUbZEfOh2eLJFXvKUkxS0j7hvsRDpshJkjQYDzmfNDLzvQUegXKRl0s8RyjwI5/132', 'suiji', 'test', '1', '1', '1', '13258179872', '', '', '121212@qq.com', '', 0, '', '', '', NULL, NULL, '', '', '', '', '', 20180731141510),
	('4', '511', '周红星', '19950602', '1', '1', '1', 'http://thirdwx.qlogo.cn/mmopen/7DkVkX1qfEGSjTDwwYXoShDTqo60F3HiclqLUbZEfOh2eLJFXvKUkxS0j7hvsRDpshJkjQYDzmfNDLzvQUegXKRl0s8RyjwI5/132', 'suiji', 'test', '1', '1', '1', '13258179872', '', '', '121212@qq.com', '', 2, '', '', '', NULL, NULL, '', '', '', '', '', 20180731141510),
	('4444', '111111111', '周红星12', '19950602', '1', '1', '1', 'http://thirdwx.qlogo.cn/mmopen/7DkVkX1qfEGSjTDwwYXoShDTqo60F3HiclqLUbZEfOh2eLJFXvKUkxS0j7hvsRDpshJkjQYDzmfNDLzvQUegXKRl0s8RyjwI5/132', 'suiji', 'test', '1', '1', '1', '13258179872', '', '', '121212@qq.com', '11111', 0, '', '', '', NULL, NULL, '', '', '', '', '', 20180731141510),
	('44444444', '511', '周红星', '19950602', '1', '1', '1', 'http://thirdwx.qlogo.cn/mmopen/7DkVkX1qfEGSjTDwwYXoShDTqo60F3HiclqLUbZEfOh2eLJFXvKUkxS0j7hvsRDpshJkjQYDzmfNDLzvQUegXKRl0s8RyjwI5/132', 'suiji', 'test', '1', '1', '1', '13258179872', '', '', '121212@qq.com', '', NULL, '', '', '', NULL, NULL, '', '', '', '', '', 20180731141510),
	('5', '511', '周红星', '19950602', '1', '1', '1', 'http://thirdwx.qlogo.cn/mmopen/7DkVkX1qfEGSjTDwwYXoShDTqo60F3HiclqLUbZEfOh2eLJFXvKUkxS0j7hvsRDpshJkjQYDzmfNDLzvQUegXKRl0s8RyjwI5/132', 'suiji', 'test', '1', '1', '1', '13258179872', '', '', '121212@qq.com', '', 0, '', '', '', NULL, NULL, '', '', '', '', '', 20180731141510),
	('6', '511', '周红星', '19950602', '1', '1', '1', 'http://thirdwx.qlogo.cn/mmopen/7DkVkX1qfEGSjTDwwYXoShDTqo60F3HiclqLUbZEfOh2eLJFXvKUkxS0j7hvsRDpshJkjQYDzmfNDLzvQUegXKRl0s8RyjwI5/132', 'suiji', 'test', '1', '1', '1', '13258179872', '', '', '121212@qq.com', '', NULL, '', '', '', NULL, NULL, '', '', '', '', '', 20180731141510),
	('7', '511', '周红星', '19950602', '1', '1', '1', 'http://thirdwx.qlogo.cn/mmopen/7DkVkX1qfEGSjTDwwYXoShDTqo60F3HiclqLUbZEfOh2eLJFXvKUkxS0j7hvsRDpshJkjQYDzmfNDLzvQUegXKRl0s8RyjwI5/132', 'suiji', 'test', '1', '1', '1', '13258179872', '', '', '121212@qq.com', '23232333333', 0, '', '', '', NULL, NULL, '', '', '', '', '', 20180731141510),
	('ae371c56-13f0-11e9-97db-382c4a232da1', '12121212', '123zhx1111', NULL, NULL, NULL, NULL, 'http://thirdwx.qlogo.cn/mmopen/7DkVkX1qfEGSjTDwwYXoShDTqo60F3HiclqLUbZEfOh2eLJFXvKUkxS0j7hvsRDpshJkjQYDzmfNDLzvQUegXKRl0s8RyjwI5/132', NULL, NULL, NULL, NULL, NULL, '1325817972', NULL, NULL, 'zhx', '1212', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 20190109172631),
	('qwqwq', '11111111111111', '周红星12', '19950602', '1', '1', '1', 'http://thirdwx.qlogo.cn/mmopen/7DkVkX1qfEGSjTDwwYXoShDTqo60F3HiclqLUbZEfOh2eLJFXvKUkxS0j7hvsRDpshJkjQYDzmfNDLzvQUegXKRl0s8RyjwI5/132', 'suiji', 'test', '1', '1', '1', '13258179872', '', '', '121212@qq.com', '11111', 0, '', '', '', NULL, NULL, '', '', '', '', '', 20180731141510);
/*!40000 ALTER TABLE `b_staff` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
