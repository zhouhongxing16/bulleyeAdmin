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
DROP DATABASE IF EXISTS `bulleye_admin`;
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
  `org_id` varchar(40) DEFAULT NULL COMMENT '组织标识',
  `wx_openid` varchar(64) DEFAULT NULL COMMENT '微信OpenId',
  `alipay_openid` varchar(64) DEFAULT NULL COMMENT '支付宝OpenId',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `mobile_login_flag` bit(1) DEFAULT NULL COMMENT '是否开通手机号登录',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `expired_date` datetime DEFAULT NULL COMMENT '账号过期时间',
  PRIMARY KEY (`id`),
  KEY `FK_b_accounts_b_staff` (`staff_id`),
  KEY `FK_b_accounts_b_organizations` (`org_id`),
  CONSTRAINT `FK_b_accounts_b_organizations` FOREIGN KEY (`org_id`) REFERENCES `b_organizations` (`id`),
  CONSTRAINT `FK_b_accounts_b_staff` FOREIGN KEY (`staff_id`) REFERENCES `b_staff` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- 正在导出表  bulleye_admin.b_accounts 的数据：~1 rows (大约)
DELETE FROM `b_accounts`;
/*!40000 ALTER TABLE `b_accounts` DISABLE KEYS */;
INSERT INTO `b_accounts` (`id`, `username`, `password`, `account_locked`, `credentials_expired`, `account_expired`, `staff_id`, `org_id`, `wx_openid`, `alipay_openid`, `email`, `status`, `mobile_login_flag`, `remark`, `created`, `modified`, `expired_date`) VALUES
	('00278d19-9794-11e7-975b-000c29687dd9', 'admin', '{bcrypt}$2a$10$cBxwbxYB8iUZ2.W2BspGbOPxAlsZT1ppDtD.nFJsJMCQMAW8YCUWy', NULL, NULL, NULL, '1', 'suiji', NULL, NULL, NULL, NULL, NULL, NULL, '2018-06-13 11:07:19', '2018-06-13 11:07:19', NULL);
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
	('1', '1', '00278d19-9794-11e7-975b-000c29687dd9', '2018-07-31 11:49:48');
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
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  PRIMARY KEY (`id`),
  KEY `FK_b_departments_b_organizations` (`organization_id`),
  CONSTRAINT `FK_b_departments_b_organizations` FOREIGN KEY (`organization_id`) REFERENCES `b_organizations` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部门表';

-- 正在导出表  bulleye_admin.b_departments 的数据：~1 rows (大约)
DELETE FROM `b_departments`;
/*!40000 ALTER TABLE `b_departments` DISABLE KEYS */;
INSERT INTO `b_departments` (`id`, `organization_id`, `code`, `name`, `type_id`, `remark`, `status`, `created`) VALUES
	('test', 'suiji', '管理部门', '管理部门', 'guanli', '管理', 0, '2018-07-31 11:43:02');
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
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
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
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
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
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限表';

-- 正在导出表  bulleye_admin.b_functions 的数据：~0 rows (大约)
DELETE FROM `b_functions`;
/*!40000 ALTER TABLE `b_functions` DISABLE KEYS */;
INSERT INTO `b_functions` (`id`, `code`, `name`, `icon`, `url`, `describe`, `status`, `created`) VALUES
	('1', 'add', '增加', NULL, NULL, NULL, 0, '2018-07-31 11:48:47');
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
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='操作日志';

-- 正在导出表  bulleye_admin.b_logs 的数据：~2 rows (大约)
DELETE FROM `b_logs`;
/*!40000 ALTER TABLE `b_logs` DISABLE KEYS */;
INSERT INTO `b_logs` (`id`, `org_id`, `optname`, `opttype`, `content`, `user_id`, `staff_id`, `created`, `status`) VALUES
	('7d2bc706-fdf4-11e8-a6cd-382c4a232da1', 'suiji', '登陆', '进入首页', '{\n	\n}', '00278d19-9794-11e7-975b-000c29687dd9', '1', '2018-12-12 17:58:30', NULL),
	('9b347ca7-fdf4-11e8-a6cd-382c4a232da1', 'suiji', '登陆', '进入首页', '{\n	\n}', '00278d19-9794-11e7-975b-000c29687dd9', '1', '2018-12-12 17:59:20', NULL),
	('e260b869-fdf3-11e8-a6cd-382c4a232da1', 'suiji', '登陆', '进入首页', '{\n	\n}', '00278d19-9794-11e7-975b-000c29687dd9', '1', '2018-12-12 17:54:10', NULL);
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
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单表';

-- 正在导出表  bulleye_admin.b_menus 的数据：~5 rows (大约)
DELETE FROM `b_menus`;
/*!40000 ALTER TABLE `b_menus` DISABLE KEYS */;
INSERT INTO `b_menus` (`id`, `p_id`, `icon`, `code`, `name`, `path`, `status`, `sort`, `created`) VALUES
	('1', NULL, NULL, 'admin', '菜单', '/menu/list', 0, 1, '2018-07-31 11:45:27'),
	('2', '1', NULL, 'admin1', '菜单1', '/menu/list', 0, 1, '2018-07-31 11:45:27'),
	('3', '2', NULL, 'admin2', '菜单2', '/menu/list', 0, 1, '2018-07-31 11:45:27'),
	('4', '3', NULL, 'admin2', '菜单2', '/menu/list', 0, 1, '2018-07-31 11:45:27'),
	('5', '4', NULL, '111', '123', '/staff/list', 0, 1, '2018-07-31 17:11:28');
/*!40000 ALTER TABLE `b_menus` ENABLE KEYS */;

-- 导出  表 bulleye_admin.b_menu_function 结构
DROP TABLE IF EXISTS `b_menu_function`;
CREATE TABLE IF NOT EXISTS `b_menu_function` (
  `id` varchar(40) NOT NULL COMMENT '唯一标识',
  `menu_id` varchar(40) DEFAULT NULL COMMENT '菜单外键',
  `func_id` varchar(40) DEFAULT NULL COMMENT '功能外键',
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单功能中间表';

-- 正在导出表  bulleye_admin.b_menu_function 的数据：~0 rows (大约)
DELETE FROM `b_menu_function`;
/*!40000 ALTER TABLE `b_menu_function` DISABLE KEYS */;
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
  `end_date` timestamp NULL DEFAULT NULL COMMENT '试用结束日期',
  `domain` varchar(100) DEFAULT NULL COMMENT '域名',
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='组织信息表';

-- 正在导出表  bulleye_admin.b_organizations 的数据：~1 rows (大约)
DELETE FROM `b_organizations`;
/*!40000 ALTER TABLE `b_organizations` DISABLE KEYS */;
INSERT INTO `b_organizations` (`id`, `pid`, `code`, `name`, `area_id`, `brief`, `contact_name`, `contact_phone`, `status`, `end_date`, `domain`, `created`) VALUES
	('suiji', NULL, 'admin', '管理', NULL, '管理组', '管理员工', '1328179872', 0, '2018-07-31 11:41:55', NULL, '2018-07-31 11:41:56');
/*!40000 ALTER TABLE `b_organizations` ENABLE KEYS */;

-- 导出  表 bulleye_admin.b_org_menu 结构
DROP TABLE IF EXISTS `b_org_menu`;
CREATE TABLE IF NOT EXISTS `b_org_menu` (
  `id` varchar(40) NOT NULL,
  `organization_id` varchar(40) DEFAULT NULL,
  `menu_id` varchar(40) DEFAULT NULL,
  `p_id` varchar(40) DEFAULT NULL,
  `display_name` varchar(40) DEFAULT NULL,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `status` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `FK_Reference_156` (`menu_id`),
  CONSTRAINT `FK_Reference_156` FOREIGN KEY (`menu_id`) REFERENCES `b_menus` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='组织自己的菜单层级';

-- 正在导出表  bulleye_admin.b_org_menu 的数据：~0 rows (大约)
DELETE FROM `b_org_menu`;
/*!40000 ALTER TABLE `b_org_menu` DISABLE KEYS */;
/*!40000 ALTER TABLE `b_org_menu` ENABLE KEYS */;

-- 导出  表 bulleye_admin.b_org_role 结构
DROP TABLE IF EXISTS `b_org_role`;
CREATE TABLE IF NOT EXISTS `b_org_role` (
  `id` varchar(50) NOT NULL,
  `organization_id` varchar(50) NOT NULL,
  `role_id` varchar(50) NOT NULL,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `FK_b_org_role_b_organizations` (`organization_id`),
  KEY `FK_b_org_role_b_roles` (`role_id`),
  CONSTRAINT `FK_b_org_role_b_organizations` FOREIGN KEY (`organization_id`) REFERENCES `b_organizations` (`id`),
  CONSTRAINT `FK_b_org_role_b_roles` FOREIGN KEY (`role_id`) REFERENCES `b_roles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='组织角色授权';

-- 正在导出表  bulleye_admin.b_org_role 的数据：~0 rows (大约)
DELETE FROM `b_org_role`;
/*!40000 ALTER TABLE `b_org_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `b_org_role` ENABLE KEYS */;

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
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

-- 正在导出表  bulleye_admin.b_roles 的数据：~2 rows (大约)
DELETE FROM `b_roles`;
/*!40000 ALTER TABLE `b_roles` DISABLE KEYS */;
INSERT INTO `b_roles` (`id`, `organization_id`, `code`, `name`, `data_auth_flag`, `describle`, `status`, `created`) VALUES
	('1', 'admin', 'admin', 'admin', 'personal', '1', 0, '2018-07-31 11:44:18'),
	('2', 'super', 'super', 'super', 'personal', '超级', 0, '2018-07-31 11:52:26');
/*!40000 ALTER TABLE `b_roles` ENABLE KEYS */;

-- 导出  表 bulleye_admin.b_role_menu 结构
DROP TABLE IF EXISTS `b_role_menu`;
CREATE TABLE IF NOT EXISTS `b_role_menu` (
  `id` varchar(40) NOT NULL COMMENT '唯一标识',
  `role_id` varchar(40) DEFAULT NULL COMMENT '角色外键',
  `menu_id` varchar(40) NOT NULL COMMENT '菜单外键',
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
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
	('1', '1', '1', '2018-07-31 11:46:24'),
	('2', '1', '2', '2018-07-31 11:46:24'),
	('3', '1', '3', '2018-07-31 11:46:24'),
	('4', '1', '4', '2018-07-31 11:46:24'),
	('5', '1', '5', '2018-07-31 11:46:24');
/*!40000 ALTER TABLE `b_role_menu` ENABLE KEYS */;

-- 导出  表 bulleye_admin.b_staff 结构
DROP TABLE IF EXISTS `b_staff`;
CREATE TABLE IF NOT EXISTS `b_staff` (
  `id` varchar(40) NOT NULL,
  `serial_no` varchar(20) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `gender_id` varchar(40) DEFAULT NULL,
  `birthday` varchar(20) DEFAULT NULL,
  `major_id` varchar(40) DEFAULT NULL,
  `academic_id` varchar(40) DEFAULT NULL,
  `degree_id` varchar(40) DEFAULT NULL,
  `avator` varchar(255) DEFAULT NULL COMMENT '头像',
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
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `birth_province_id` varchar(40) DEFAULT NULL,
  `birth_city_id` varchar(40) DEFAULT NULL,
  `policy` varchar(40) DEFAULT NULL COMMENT '党员、共青团员、群众',
  `weight` decimal(4,1) DEFAULT NULL COMMENT '体重',
  `height` int(4) DEFAULT NULL COMMENT '身高',
  `health_status` varchar(50) DEFAULT NULL COMMENT '健康状态',
  `marry_status_id` varchar(40) DEFAULT NULL COMMENT '已婚、未婚、离异',
  `nation_id` varchar(40) DEFAULT NULL,
  `vmnet_no` varchar(20) DEFAULT NULL COMMENT 'V网账号',
  `join_date` varchar(20) DEFAULT NULL,
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

-- 正在导出表  bulleye_admin.b_staff 的数据：~0 rows (大约)
DELETE FROM `b_staff`;
/*!40000 ALTER TABLE `b_staff` DISABLE KEYS */;
INSERT INTO `b_staff` (`id`, `serial_no`, `name`, `gender_id`, `birthday`, `major_id`, `academic_id`, `degree_id`, `avator`, `organization_id`, `department_id`, `position_id`, `title_id`, `type_id`, `mobile`, `identify_type_id`, `identify_no`, `email`, `remark`, `status`, `created`, `birth_province_id`, `birth_city_id`, `policy`, `weight`, `height`, `health_status`, `marry_status_id`, `nation_id`, `vmnet_no`, `join_date`) VALUES
	('1', '511', '周红星', '1', '19950602', NULL, NULL, NULL, NULL, 'suiji', 'test', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2018-07-31 14:15:10', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
/*!40000 ALTER TABLE `b_staff` ENABLE KEYS */;

-- 导出  表 bulleye_admin.flyway_schema_history 结构
DROP TABLE IF EXISTS `flyway_schema_history`;
CREATE TABLE IF NOT EXISTS `flyway_schema_history` (
  `installed_rank` int(11) NOT NULL,
  `version` varchar(50) DEFAULT NULL,
  `description` varchar(200) NOT NULL,
  `type` varchar(20) NOT NULL,
  `script` varchar(1000) NOT NULL,
  `checksum` int(11) DEFAULT NULL,
  `installed_by` varchar(100) NOT NULL,
  `installed_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `execution_time` int(11) NOT NULL,
  `success` tinyint(1) NOT NULL,
  PRIMARY KEY (`installed_rank`),
  KEY `flyway_schema_history_s_idx` (`success`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  bulleye_admin.flyway_schema_history 的数据：~1 rows (大约)
DELETE FROM `flyway_schema_history`;
/*!40000 ALTER TABLE `flyway_schema_history` DISABLE KEYS */;
INSERT INTO `flyway_schema_history` (`installed_rank`, `version`, `description`, `type`, `script`, `checksum`, `installed_by`, `installed_on`, `execution_time`, `success`) VALUES
	(1, '1', '<< Flyway Baseline >>', 'BASELINE', '<< Flyway Baseline >>', NULL, 'root', '2018-12-12 17:36:04', 0, 1);
/*!40000 ALTER TABLE `flyway_schema_history` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
