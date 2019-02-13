-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.7.9-log - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  9.5.0.5196
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- 导出 bulleye_admin 的数据库结构
CREATE DATABASE IF NOT EXISTS `bulleye_admin` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `bulleye_admin`;

-- 导出  表 bulleye_admin.b_accounts 结构
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
  `created` bigint(20) NOT NULL COMMENT '创建时间',
  `modified` bigint(20) DEFAULT NULL COMMENT '修改时间',
  `expired_date` bigint(20) DEFAULT NULL COMMENT '账号过期时间',
  PRIMARY KEY (`id`),
  KEY `FK_b_accounts_b_staff` (`staff_id`),
  KEY `FK_b_accounts_b_organizations` (`organization_id`),
  CONSTRAINT `FK_b_accounts_b_organizations` FOREIGN KEY (`organization_id`) REFERENCES `b_organizations` (`id`),
  CONSTRAINT `FK_b_accounts_b_staff` FOREIGN KEY (`staff_id`) REFERENCES `b_staff` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- 正在导出表  bulleye_admin.b_accounts 的数据：~2 rows (大约)
DELETE FROM `b_accounts`;
/*!40000 ALTER TABLE `b_accounts` DISABLE KEYS */;
INSERT INTO `b_accounts` (`id`, `username`, `password`, `account_locked`, `credentials_expired`, `account_expired`, `staff_id`, `organization_id`, `wx_openid`, `alipay_openid`, `email`, `status`, `mobile_login_flag`, `remark`, `created`, `modified`, `expired_date`) VALUES
	('00278d19-9794-11e7-975b-000c29687dd9', 'admin', '{bcrypt}$2a$10$cBxwbxYB8iUZ2.W2BspGbOPxAlsZT1ppDtD.nFJsJMCQMAW8YCUWy', NULL, NULL, NULL, '1', 'suiji', NULL, NULL, NULL, NULL, NULL, NULL, 20180613110719, 20180613110719, NULL),
	('4081fe65-125a-11e9-97db-382c4a232da1', 'zhx', '{bcrypt}$2a$10$cBxwbxYB8iUZ2.W2BspGbOPxAlsZT1ppDtD.nFJsJMCQMAW8YCUWy', NULL, NULL, NULL, '1', NULL, NULL, NULL, '961860916@qq.com', NULL, NULL, NULL, 20190107165716, NULL, NULL);
/*!40000 ALTER TABLE `b_accounts` ENABLE KEYS */;

-- 导出  表 bulleye_admin.b_account_role 结构
DROP TABLE IF EXISTS `b_account_role`;
CREATE TABLE IF NOT EXISTS `b_account_role` (
  `id` varchar(40) NOT NULL COMMENT '唯一标识',
  `role_id` varchar(40) DEFAULT NULL COMMENT '角色外键',
  `account_id` varchar(40) DEFAULT NULL COMMENT '用户外键',
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  PRIMARY KEY (`id`),
  KEY `FK_b_account_role_b_roles` (`role_id`),
  KEY `FK_b_account_role_b_accounts` (`account_id`),
  CONSTRAINT `FK_b_account_role_b_accounts` FOREIGN KEY (`account_id`) REFERENCES `b_accounts` (`id`),
  CONSTRAINT `FK_b_account_role_b_roles` FOREIGN KEY (`role_id`) REFERENCES `b_roles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色中间表';

-- 正在导出表  bulleye_admin.b_account_role 的数据：~0 rows (大约)
DELETE FROM `b_account_role`;
/*!40000 ALTER TABLE `b_account_role` DISABLE KEYS */;
INSERT INTO `b_account_role` (`id`, `role_id`, `account_id`, `created`) VALUES
	('1', '1', '00278d19-9794-11e7-975b-000c29687dd9', '2018-07-31 11:49:48'),
	('2', '2', '4081fe65-125a-11e9-97db-382c4a232da1', '2019-01-08 14:24:11');
/*!40000 ALTER TABLE `b_account_role` ENABLE KEYS */;

-- 导出  表 bulleye_admin.b_departments 结构
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
  PRIMARY KEY (`id`),
  KEY `FK_b_departments_b_organizations` (`organization_id`),
  CONSTRAINT `FK_b_departments_b_organizations` FOREIGN KEY (`organization_id`) REFERENCES `b_organizations` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部门表';

-- 正在导出表  bulleye_admin.b_departments 的数据：~0 rows (大约)
DELETE FROM `b_departments`;
/*!40000 ALTER TABLE `b_departments` DISABLE KEYS */;
INSERT INTO `b_departments` (`id`, `organization_id`, `code`, `name`, `type_id`, `remark`, `status`, `created`) VALUES
	('test', 'suiji', '管理部门', '管理部门', 'guanli', '管理', 0, 20180731114302);
/*!40000 ALTER TABLE `b_departments` ENABLE KEYS */;

-- 导出  表 bulleye_admin.b_dictionary_data 结构
DROP TABLE IF EXISTS `b_dictionary_data`;
CREATE TABLE IF NOT EXISTS `b_dictionary_data` (
  `id` varchar(40) NOT NULL COMMENT '唯一标识',
  `type_id` varchar(40) DEFAULT NULL COMMENT '类型标识',
  `code` varchar(20) DEFAULT NULL COMMENT '字典编码',
  `name` varchar(100) DEFAULT NULL COMMENT '字典名称',
  `remark` varchar(200) DEFAULT NULL COMMENT '字典备注',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `created` bigint(20) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `FK_b_dictionary_data_b_dictionary_type` (`type_id`),
  CONSTRAINT `FK_b_dictionary_data_b_dictionary_type` FOREIGN KEY (`type_id`) REFERENCES `b_dictionary_type` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='字典数据表';

-- 正在导出表  bulleye_admin.b_dictionary_data 的数据：~0 rows (大约)
DELETE FROM `b_dictionary_data`;
/*!40000 ALTER TABLE `b_dictionary_data` DISABLE KEYS */;
/*!40000 ALTER TABLE `b_dictionary_data` ENABLE KEYS */;

-- 导出  表 bulleye_admin.b_dictionary_type 结构
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

-- 正在导出表  bulleye_admin.b_dictionary_type 的数据：~0 rows (大约)
DELETE FROM `b_dictionary_type`;
/*!40000 ALTER TABLE `b_dictionary_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `b_dictionary_type` ENABLE KEYS */;

-- 导出  表 bulleye_admin.b_functions 结构
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

-- 正在导出表  bulleye_admin.b_functions 的数据：~2 rows (大约)
DELETE FROM `b_functions`;
/*!40000 ALTER TABLE `b_functions` DISABLE KEYS */;
INSERT INTO `b_functions` (`id`, `code`, `name`, `icon`, `url`, `describe`, `status`, `created`) VALUES
	('1', 'add', '增加', NULL, NULL, NULL, 0, 20180731114847),
	('2', 'list', NULL, NULL, NULL, NULL, NULL, 20190108142619);
/*!40000 ALTER TABLE `b_functions` ENABLE KEYS */;

-- 导出  表 bulleye_admin.b_logs 结构
DROP TABLE IF EXISTS `b_logs`;
CREATE TABLE IF NOT EXISTS `b_logs` (
  `id` varchar(40) NOT NULL,
  `org_id` varchar(40) DEFAULT NULL,
  `optname` varchar(50) DEFAULT NULL,
  `opttype` varchar(20) DEFAULT NULL COMMENT '新增、删除、修改、查询、登录',
  `content` text,
  `user_id` varchar(40) DEFAULT NULL,
  `staff_id` varchar(40) DEFAULT NULL,
  `created` bigint(20) NOT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='操作日志';

-- 正在导出表  bulleye_admin.b_logs 的数据：~28 rows (大约)
DELETE FROM `b_logs`;
/*!40000 ALTER TABLE `b_logs` DISABLE KEYS */;
INSERT INTO `b_logs` (`id`, `org_id`, `optname`, `opttype`, `content`, `user_id`, `staff_id`, `created`, `status`) VALUES
	('0d1d139c-22e4-11e9-b39e-382c4a232da1', 'suiji', '登陆', '进入首页', '{\n	\n}', '00278d19-9794-11e7-975b-000c29687dd9', '1', 20190128180400, NULL),
	('0f01d3a4-13d4-11e9-97db-382c4a232da1', 'suiji', '登陆', '进入首页', '{\n	\n}', '00278d19-9794-11e7-975b-000c29687dd9', '1', 20190109140138, NULL),
	('174a520b-1310-11e9-97db-382c4a232da1', 'suiji', '登陆', '进入首页', '{\n	\n}', '00278d19-9794-11e7-975b-000c29687dd9', '1', 20190108143852, NULL),
	('17d2c586-14a5-11e9-97db-382c4a232da1', 'suiji', '登陆', '进入首页', '{\n	\n}', '00278d19-9794-11e7-975b-000c29687dd9', '1', 20190110145756, NULL),
	('3296b97a-130e-11e9-97db-382c4a232da1', 'suiji', '登陆', '进入首页', '{\n	\n}', '00278d19-9794-11e7-975b-000c29687dd9', '1', 20190108142519, NULL),
	('405c7e84-125a-11e9-97db-382c4a232da1', 'suiji', '登陆', '进入首页', '{\n	\n}', '00278d19-9794-11e7-975b-000c29687dd9', '1', 20190107165716, NULL),
	('576b3b56-22e3-11e9-b39e-382c4a232da1', 'suiji', '登陆', '进入首页', '{\n	\n}', '00278d19-9794-11e7-975b-000c29687dd9', '1', 20190128175855, NULL),
	('5ee9c9fa-14a4-11e9-97db-382c4a232da1', 'suiji', '登陆', '进入首页', '{\n	\n}', '00278d19-9794-11e7-975b-000c29687dd9', '1', 20190110145246, NULL),
	('609c7c29-13f4-11e9-97db-382c4a232da1', 'suiji', '登陆', '进入首页', '{\n	\n}', '00278d19-9794-11e7-975b-000c29687dd9', '1', 20190109175259, NULL),
	('60d86f39-1312-11e9-97db-382c4a232da1', 'suiji', '登陆', '进入首页', '{\n	\n}', '00278d19-9794-11e7-975b-000c29687dd9', '1', 20190108145515, NULL),
	('6d992cab-13f5-11e9-97db-382c4a232da1', 'suiji', '登陆', '进入首页', '{\n	\n}', '00278d19-9794-11e7-975b-000c29687dd9', '1', 20190109180030, NULL),
	('6f5e2d28-14a5-11e9-97db-382c4a232da1', 'suiji', '登陆', '进入首页', '{\n	\n}', '00278d19-9794-11e7-975b-000c29687dd9', '1', 20190110150023, NULL),
	('7d2bc706-fdf4-11e8-a6cd-382c4a232da1', 'suiji', '登陆', '进入首页', '{\n	\n}', '00278d19-9794-11e7-975b-000c29687dd9', '1', 20181212175830, NULL),
	('87675cfa-14a5-11e9-97db-382c4a232da1', 'suiji', '登陆', '进入首页', '{\n	\n}', '00278d19-9794-11e7-975b-000c29687dd9', '1', 20190110150104, NULL),
	('9492e777-22e3-11e9-b39e-382c4a232da1', 'suiji', '登陆', '进入首页', '{\n	\n}', '00278d19-9794-11e7-975b-000c29687dd9', '1', 20190128180038, NULL),
	('96f27f48-130e-11e9-97db-382c4a232da1', 'suiji', '登陆', '进入首页', '{\n	\n}', '00278d19-9794-11e7-975b-000c29687dd9', '1', 20190108142807, NULL),
	('9b347ca7-fdf4-11e8-a6cd-382c4a232da1', 'suiji', '登陆', '进入首页', '{\n	\n}', '00278d19-9794-11e7-975b-000c29687dd9', '1', 20181212175920, NULL),
	('9c214d7d-2084-11e9-b39e-382c4a232da1', 'suiji', '登陆', '进入首页', '{\n	\n}', '00278d19-9794-11e7-975b-000c29687dd9', '1', 20190125173548, NULL),
	('a2c1849c-14a4-11e9-97db-382c4a232da1', 'suiji', '登陆', '进入首页', '{\n	\n}', '00278d19-9794-11e7-975b-000c29687dd9', '1', 20190110145440, NULL),
	('ac362d4b-22e3-11e9-b39e-382c4a232da1', 'suiji', '登陆', '进入首页', '{\n	\n}', '00278d19-9794-11e7-975b-000c29687dd9', '1', 20190128180117, NULL),
	('ba9ec9dc-2460-11e9-b39e-382c4a232da1', 'suiji', '登陆', '进入首页', '{\n	\n}', '00278d19-9794-11e7-975b-000c29687dd9', '1', 20190130152857, NULL),
	('bf8f9d9e-130f-11e9-97db-382c4a232da1', 'suiji', '登陆', '进入首页', '{\n	\n}', '00278d19-9794-11e7-975b-000c29687dd9', '1', 20190108143625, NULL),
	('c7df18eb-14a4-11e9-97db-382c4a232da1', 'suiji', '登陆', '进入首页', '{\n	\n}', '00278d19-9794-11e7-975b-000c29687dd9', '1', 20190110145542, NULL),
	('c979ce24-13c2-11e9-97db-382c4a232da1', 'suiji', '登陆', '进入首页', '{\n	\n}', '00278d19-9794-11e7-975b-000c29687dd9', '1', 20190109115800, NULL),
	('cec46bf2-14a4-11e9-97db-382c4a232da1', 'suiji', '登陆', '进入首页', '{\n	\n}', '00278d19-9794-11e7-975b-000c29687dd9', '1', 20190110145554, NULL),
	('d14872a2-1316-11e9-97db-382c4a232da1', 'suiji', '登陆', '进入首页', '{\n	\n}', '00278d19-9794-11e7-975b-000c29687dd9', '1', 20190108152701, NULL),
	('e260b869-fdf3-11e8-a6cd-382c4a232da1', 'suiji', '登陆', '进入首页', '{\n	\n}', '00278d19-9794-11e7-975b-000c29687dd9', '1', 20181212175410, NULL),
	('f54d3a1f-14a4-11e9-97db-382c4a232da1', 'suiji', '登陆', '进入首页', '{\n	\n}', '00278d19-9794-11e7-975b-000c29687dd9', '1', 20190110145658, NULL);
/*!40000 ALTER TABLE `b_logs` ENABLE KEYS */;

-- 导出  表 bulleye_admin.b_menus 结构
DROP TABLE IF EXISTS `b_menus`;
CREATE TABLE IF NOT EXISTS `b_menus` (
  `id` varchar(40) NOT NULL COMMENT '唯一标识',
  `p_id` varchar(40) DEFAULT NULL COMMENT '父菜单id',
  `icon` varchar(50) DEFAULT NULL COMMENT '图标',
  `code` varchar(40) DEFAULT NULL COMMENT '代码',
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `path` varchar(255) DEFAULT NULL COMMENT '路径',
  `status` int(11) DEFAULT NULL COMMENT '是否显示（0：不显示，1：显示）',
  `sort` int(11) DEFAULT NULL COMMENT '显示顺序',
  `created` bigint(20) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单表';

-- 正在导出表  bulleye_admin.b_menus 的数据：~5 rows (大约)
DELETE FROM `b_menus`;
/*!40000 ALTER TABLE `b_menus` DISABLE KEYS */;
INSERT INTO `b_menus` (`id`, `p_id`, `icon`, `code`, `name`, `path`, `status`, `sort`, `created`) VALUES
	('1', NULL, NULL, 'admin', '菜单', '/menu', 0, 1, 20180731114527),
	('2', NULL, NULL, 'admin1', '菜单1', '/menu/list', 0, 1, 20180731114527),
	('3', '2', NULL, 'staff', '菜单2', '/staff/list', 0, 1, 20180731114527),
	('4', '2', NULL, 'admin2', '菜单2111111111111111111', '/staff/add', 0, 1, 20180731114527),
	('5', '4', NULL, '111', '123', '/staff/list', 0, 1, 20180731171128);
/*!40000 ALTER TABLE `b_menus` ENABLE KEYS */;

-- 导出  表 bulleye_admin.b_menu_function 结构
DROP TABLE IF EXISTS `b_menu_function`;
CREATE TABLE IF NOT EXISTS `b_menu_function` (
  `id` varchar(40) NOT NULL COMMENT '唯一标识',
  `menu_id` varchar(40) DEFAULT NULL COMMENT '菜单外键',
  `function_id` varchar(40) DEFAULT NULL COMMENT '功能外键',
  `created` bigint(20) NOT NULL COMMENT '创建日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单功能中间表';

-- 正在导出表  bulleye_admin.b_menu_function 的数据：~2 rows (大约)
DELETE FROM `b_menu_function`;
/*!40000 ALTER TABLE `b_menu_function` DISABLE KEYS */;
INSERT INTO `b_menu_function` (`id`, `menu_id`, `function_id`, `created`) VALUES
	('dfdf', '3', '2', 20190108142605),
	('dsdfsfdsdfsdfs', '2', '1', 20190107170317);
/*!40000 ALTER TABLE `b_menu_function` ENABLE KEYS */;

-- 导出  表 bulleye_admin.b_organizations 结构
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

-- 正在导出表  bulleye_admin.b_organizations 的数据：~0 rows (大约)
DELETE FROM `b_organizations`;
/*!40000 ALTER TABLE `b_organizations` DISABLE KEYS */;
INSERT INTO `b_organizations` (`id`, `pid`, `code`, `name`, `area_id`, `brief`, `contact_name`, `contact_phone`, `status`, `end_date`, `domain`, `created`) VALUES
	('suiji', NULL, 'admin', '管理', NULL, '管理组', '管理员工', '1328179872', 0, 20180731114155, NULL, 20180731114156);
/*!40000 ALTER TABLE `b_organizations` ENABLE KEYS */;

-- 导出  表 bulleye_admin.b_organization_menu 结构
DROP TABLE IF EXISTS `b_organization_menu`;
CREATE TABLE IF NOT EXISTS `b_organization_menu` (
  `id` varchar(40) NOT NULL,
  `organization_id` varchar(40) DEFAULT NULL,
  `menu_id` varchar(40) DEFAULT NULL,
  `p_id` varchar(40) DEFAULT NULL,
  `display_name` varchar(40) DEFAULT NULL,
  `created` bigint(20) NOT NULL,
  `status` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `FK_Reference_156` (`menu_id`),
  CONSTRAINT `FK_Reference_156` FOREIGN KEY (`menu_id`) REFERENCES `b_menus` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='组织自己的菜单层级';

-- 正在导出表  bulleye_admin.b_organization_menu 的数据：~0 rows (大约)
DELETE FROM `b_organization_menu`;
/*!40000 ALTER TABLE `b_organization_menu` DISABLE KEYS */;
/*!40000 ALTER TABLE `b_organization_menu` ENABLE KEYS */;

-- 导出  表 bulleye_admin.b_organization_role 结构
DROP TABLE IF EXISTS `b_organization_role`;
CREATE TABLE IF NOT EXISTS `b_organization_role` (
  `id` varchar(50) NOT NULL,
  `organization_id` varchar(50) NOT NULL,
  `role_id` varchar(50) NOT NULL,
  `status` int(11) DEFAULT NULL,
  `created` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_b_org_role_b_organizations` (`organization_id`),
  KEY `FK_b_org_role_b_roles` (`role_id`),
  CONSTRAINT `FK_b_org_role_b_organizations` FOREIGN KEY (`organization_id`) REFERENCES `b_organizations` (`id`),
  CONSTRAINT `FK_b_org_role_b_roles` FOREIGN KEY (`role_id`) REFERENCES `b_roles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='组织角色授权';

-- 正在导出表  bulleye_admin.b_organization_role 的数据：~0 rows (大约)
DELETE FROM `b_organization_role`;
/*!40000 ALTER TABLE `b_organization_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `b_organization_role` ENABLE KEYS */;

-- 导出  表 bulleye_admin.b_roles 结构
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

-- 正在导出表  bulleye_admin.b_roles 的数据：~2 rows (大约)
DELETE FROM `b_roles`;
/*!40000 ALTER TABLE `b_roles` DISABLE KEYS */;
INSERT INTO `b_roles` (`id`, `organization_id`, `code`, `name`, `data_auth_flag`, `describle`, `status`, `created`) VALUES
	('1', 'admin', 'admin', 'admin', 'personal', '1', 0, 20180731114418),
	('2', 'super', 'super', 'super', 'personal', '超级', 0, 20180731115226);
/*!40000 ALTER TABLE `b_roles` ENABLE KEYS */;

-- 导出  表 bulleye_admin.b_role_function 结构
DROP TABLE IF EXISTS `b_role_function`;
CREATE TABLE IF NOT EXISTS `b_role_function` (
  `id` varchar(40) NOT NULL,
  `role_id` varchar(40) DEFAULT NULL,
  `menu_id` varchar(40) DEFAULT NULL,
  `function_id` varchar(40) DEFAULT NULL,
  `created` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `Index_rolefunction_role` (`role_id`),
  KEY `FK_b_role_function_b_menus` (`menu_id`),
  KEY `FK_b_role_function_b_functions` (`function_id`),
  CONSTRAINT `FK_b_role_function_b_functions` FOREIGN KEY (`function_id`) REFERENCES `b_functions` (`id`),
  CONSTRAINT `FK_b_role_function_b_menus` FOREIGN KEY (`menu_id`) REFERENCES `b_menus` (`id`),
  CONSTRAINT `FK_b_role_function_b_roles` FOREIGN KEY (`role_id`) REFERENCES `b_roles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色功能关系表';

-- 正在导出表  bulleye_admin.b_role_function 的数据：~2 rows (大约)
DELETE FROM `b_role_function`;
/*!40000 ALTER TABLE `b_role_function` DISABLE KEYS */;
INSERT INTO `b_role_function` (`id`, `role_id`, `menu_id`, `function_id`, `created`) VALUES
	('dfgdf', '1', '1', '1', 20190107170559),
	('dfvd', '1', '3', '2', 20190108142426);
/*!40000 ALTER TABLE `b_role_function` ENABLE KEYS */;

-- 导出  表 bulleye_admin.b_role_menu 结构
DROP TABLE IF EXISTS `b_role_menu`;
CREATE TABLE IF NOT EXISTS `b_role_menu` (
  `id` varchar(40) NOT NULL COMMENT '唯一标识',
  `role_id` varchar(40) DEFAULT NULL COMMENT '角色外键',
  `menu_id` varchar(40) NOT NULL COMMENT '菜单外键',
  `created` bigint(20) NOT NULL COMMENT '创建日期',
  PRIMARY KEY (`id`),
  KEY `FK_b_role_menu_b_menus` (`menu_id`),
  KEY `FK_b_role_menu_b_roles` (`role_id`),
  CONSTRAINT `FK_b_role_menu_b_menus` FOREIGN KEY (`menu_id`) REFERENCES `b_menus` (`id`),
  CONSTRAINT `FK_b_role_menu_b_roles` FOREIGN KEY (`role_id`) REFERENCES `b_roles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色菜单表';

-- 正在导出表  bulleye_admin.b_role_menu 的数据：~5 rows (大约)
DELETE FROM `b_role_menu`;
/*!40000 ALTER TABLE `b_role_menu` DISABLE KEYS */;
INSERT INTO `b_role_menu` (`id`, `role_id`, `menu_id`, `created`) VALUES
	('1', '1', '1', 20180731114624),
	('2', '1', '2', 20180731114624),
	('3', '1', '3', 20180731114624),
	('4', '1', '4', 20180731114624),
	('5', '1', '5', 20180731114624);
/*!40000 ALTER TABLE `b_role_menu` ENABLE KEYS */;

-- 导出  表 bulleye_admin.b_staff 结构
DROP TABLE IF EXISTS `b_staff`;
CREATE TABLE IF NOT EXISTS `b_staff` (
  `id` varchar(40) NOT NULL,
  `serial_no` varchar(20) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `birthday` varchar(20) DEFAULT NULL,
  `major_id` varchar(40) DEFAULT NULL,
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
  PRIMARY KEY (`id`),
  KEY `staff_index_type` (`type_id`),
  KEY `Index_staff_hospital_section` (`organization_id`,`department_id`),
  KEY `Index_staff_hospital` (`organization_id`),
  KEY `serial_no` (`serial_no`),
  KEY `name` (`name`),
  KEY `identify_no` (`identify_no`),
  KEY `FK_b_staff_b_departments` (`department_id`),
  CONSTRAINT `FK_b_staff_b_departments` FOREIGN KEY (`department_id`) REFERENCES `b_departments` (`id`),
  CONSTRAINT `FK_b_staff_b_organizations` FOREIGN KEY (`organization_id`) REFERENCES `b_organizations` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='人员信息表';

-- 正在导出表  bulleye_admin.b_staff 的数据：~5 rows (大约)
DELETE FROM `b_staff`;
/*!40000 ALTER TABLE `b_staff` DISABLE KEYS */;
INSERT INTO `b_staff` (`id`, `serial_no`, `name`, `birthday`, `major_id`, `gender_id`, `academic_id`, `degree_id`, `avatar`, `organization_id`, `department_id`, `position_id`, `title_id`, `type_id`, `mobile`, `identify_type_id`, `identify_no`, `email`, `remark`, `status`, `birth_province_id`, `birth_city_id`, `policy`, `weight`, `height`, `health_status`, `marry_status_id`, `nation_id`, `vmnet_no`, `join_date`, `created`) VALUES
	('1', '511', '周红星', '19950602', '1', '1', '1', '1', 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1549969134321&di=113371aa86883c695f6d88bc4613c936&imgtype=0&src=http%3A%2F%2Fwww.5snow.com%2Ffiles%2Fa%2Fa0319f12a7c895a6b942227a7e6c9c1f.jpg', 'suiji', 'test', '1', '1', '1', '13258179872', '', '', '121212@qq.com', '', NULL, '', '', '', NULL, NULL, '', '', '', '', '', 20180731141510),
	('185615b6-13d4-11e9-97db-382c4a232da1', NULL, '123', NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1325817972', NULL, NULL, '1212@qq.com', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 20190109140154),
	('20557f0d-13e5-11e9-97db-382c4a232da1', NULL, '333333', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '13258179872', NULL, NULL, '231@qq.com', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 20190109160349),
	('218e2f1a-13e5-11e9-97db-382c4a232da1', NULL, '444444', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1325817972', NULL, NULL, '444444@qq.com', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 20190109160351),
	('ae371c56-13f0-11e9-97db-382c4a232da1', NULL, '123', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1325817972', NULL, NULL, 'zhx', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 20190109172631);
/*!40000 ALTER TABLE `b_staff` ENABLE KEYS */;