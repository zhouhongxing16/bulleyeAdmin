-- --------------------------------------------------------
-- 主机:                           localhost
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

-- 导出  表 bulleye_admin.b_account 结构
DROP TABLE IF EXISTS `b_account`;
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

-- 正在导出表  bulleye_admin.b_account 的数据：~2 rows (大约)
DELETE FROM `b_account`;
/*!40000 ALTER TABLE `b_account` DISABLE KEYS */;
INSERT INTO `b_account` (`id`, `username`, `password`, `email`, `mobile_login_flag`, `account_locked`, `account_expired`, `staff_id`, `organization_id`, `wx_openid`, `alipay_openid`, `status`, `remark`, `modified`, `expired_date`, `created`) VALUES
	('4081fe65-125a-11e9-97db-382c4a232da1', 'zhx', '{bcrypt}$2a$10$cBxwbxYB8iUZ2.W2BspGbOPxAlsZT1ppDtD.nFJsJMCQMAW8YCUWy', '961860916@qq.com', NULL, 0, 0, '1', 'suiji', NULL, NULL, 1, NULL, NULL, '2019-04-13 15:16:20', '2019-04-16 19:25:32'),
	('fe885bfa-6bf9-11e9-8e25-049226bfdadf', '18080332897', NULL, '961860916@qq.com', b'1', 0, 0, '1', NULL, NULL, NULL, 1, '111', NULL, NULL, '2019-05-01 18:15:15');
/*!40000 ALTER TABLE `b_account` ENABLE KEYS */;

-- 导出  表 bulleye_admin.b_account_role 结构
DROP TABLE IF EXISTS `b_account_role`;
CREATE TABLE IF NOT EXISTS `b_account_role` (
  `id` varchar(40) NOT NULL COMMENT '唯一标识',
  `role_id` varchar(40) DEFAULT NULL COMMENT '角色外键',
  `account_id` varchar(40) DEFAULT NULL COMMENT '用户外键',
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色中间表';

-- 正在导出表  bulleye_admin.b_account_role 的数据：~4 rows (大约)
DELETE FROM `b_account_role`;
/*!40000 ALTER TABLE `b_account_role` DISABLE KEYS */;
INSERT INTO `b_account_role` (`id`, `role_id`, `account_id`, `created`) VALUES
	('1', '1', '00278d19-9794-11e7-975b-000c29687dd9', '2018-07-31 11:49:48'),
	('e77c7026-6cef-11e9-8e25-049226bfdadf', '5ebcefdf-6c2a-11e9-8e25-049226bfdadf', 'fe885bfa-6bf9-11e9-8e25-049226bfdadf', '2019-05-02 23:35:33'),
	('e77d46df-6cef-11e9-8e25-049226bfdadf', '12312312312312123', 'fe885bfa-6bf9-11e9-8e25-049226bfdadf', '2019-05-02 23:35:33'),
	('e9d83753-6cef-11e9-8e25-049226bfdadf', '2', '4081fe65-125a-11e9-97db-382c4a232da1', '2019-05-02 23:35:37');
/*!40000 ALTER TABLE `b_account_role` ENABLE KEYS */;

-- 导出  表 bulleye_admin.b_department 结构
DROP TABLE IF EXISTS `b_department`;
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

-- 正在导出表  bulleye_admin.b_department 的数据：~0 rows (大约)
DELETE FROM `b_department`;
/*!40000 ALTER TABLE `b_department` DISABLE KEYS */;
INSERT INTO `b_department` (`id`, `organization_id`, `code`, `parent_id`, `name`, `type_id`, `remark`, `user_id`, `status`, `created`) VALUES
	('test', 'suiji', '管理部门', NULL, '管理部门', 'guanli', '管理', NULL, 0, '2018-07-31 11:43:02');
/*!40000 ALTER TABLE `b_department` ENABLE KEYS */;

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
  PRIMARY KEY (`id`)
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

-- 导出  表 bulleye_admin.b_function 结构
DROP TABLE IF EXISTS `b_function`;
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

-- 正在导出表  bulleye_admin.b_function 的数据：~2 rows (大约)
DELETE FROM `b_function`;
/*!40000 ALTER TABLE `b_function` DISABLE KEYS */;
INSERT INTO `b_function` (`id`, `code`, `name`, `icon`, `url`, `describe`, `status`, `created`) VALUES
	('1', 'add', '增加', NULL, NULL, NULL, 0, '2018-07-31 11:48:47'),
	('2', 'list', NULL, NULL, NULL, NULL, NULL, '2019-01-08 14:26:19');
/*!40000 ALTER TABLE `b_function` ENABLE KEYS */;

-- 导出  表 bulleye_admin.b_login_record 结构
DROP TABLE IF EXISTS `b_login_record`;
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

-- 正在导出表  bulleye_admin.b_login_record 的数据：~0 rows (大约)
DELETE FROM `b_login_record`;
/*!40000 ALTER TABLE `b_login_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `b_login_record` ENABLE KEYS */;

-- 导出  表 bulleye_admin.b_logs 结构
DROP TABLE IF EXISTS `b_logs`;
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

-- 正在导出表  bulleye_admin.b_logs 的数据：~0 rows (大约)
DELETE FROM `b_logs`;
/*!40000 ALTER TABLE `b_logs` DISABLE KEYS */;
/*!40000 ALTER TABLE `b_logs` ENABLE KEYS */;

-- 导出  表 bulleye_admin.b_menu 结构
DROP TABLE IF EXISTS `b_menu`;
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

-- 正在导出表  bulleye_admin.b_menu 的数据：~18 rows (大约)
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
	('5', '2', 'wechat', '111', '员工管理', '/staff/list', 0, 1, '2019-04-28 22:38:42'),
	('52091a21-460a-11e9-bd13-382c5e4d3b2a', '2', 'wechat', '1', '菜单权限管理', '/menuauth/list', 1, 111, '2019-04-28 22:38:42'),
	('6', '1', 'wechat', '111', '微信菜单管理', '/wxmenu/list', 1, 1, '2019-04-28 22:38:42'),
	('7', '1', 'wechat', '111', '微信公众号管理', '/wxaccount/list', 1, 1, '2019-04-28 22:38:42'),
	('8', '1', NULL, '111', '微信会员管理', '/wxmember/list', 1, 1, '2019-04-28 22:38:42'),
	('9', '1', NULL, '111', '微信自动回复管理', '/wxreply/list', 0, 1, '2019-04-28 22:38:42');
/*!40000 ALTER TABLE `b_menu` ENABLE KEYS */;

-- 导出  表 bulleye_admin.b_menu_auth 结构
DROP TABLE IF EXISTS `b_menu_auth`;
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

-- 正在导出表  bulleye_admin.b_menu_auth 的数据：~2 rows (大约)
DELETE FROM `b_menu_auth`;
/*!40000 ALTER TABLE `b_menu_auth` DISABLE KEYS */;
INSERT INTO `b_menu_auth` (`id`, `menu_id`, `code`, `name`, `path`, `status`, `created`) VALUES
	('5376de89-4b9d-11e9-8600-382c5e4d3b2a', '1', 'query', '查看', '/query', '0', '2019-04-28 22:38:53'),
	('83e6e514-4c4b-11e9-8600-382c5e4d3b2a', '4', 'list', '查看', 'list', '0', '2019-04-28 22:38:53');
/*!40000 ALTER TABLE `b_menu_auth` ENABLE KEYS */;

-- 导出  表 bulleye_admin.b_menu_function 结构
DROP TABLE IF EXISTS `b_menu_function`;
CREATE TABLE IF NOT EXISTS `b_menu_function` (
  `id` varchar(40) NOT NULL COMMENT '唯一标识',
  `menu_id` varchar(40) DEFAULT NULL COMMENT '菜单外键',
  `function_id` varchar(40) DEFAULT NULL COMMENT '功能外键',
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单功能中间表';

-- 正在导出表  bulleye_admin.b_menu_function 的数据：~2 rows (大约)
DELETE FROM `b_menu_function`;
/*!40000 ALTER TABLE `b_menu_function` DISABLE KEYS */;
INSERT INTO `b_menu_function` (`id`, `menu_id`, `function_id`, `created`) VALUES
	('dfdf', '3', '2', '2019-01-08 14:26:05'),
	('dsdfsfdsdfsdfs', '2', '1', '2019-01-07 17:03:17');
/*!40000 ALTER TABLE `b_menu_function` ENABLE KEYS */;

-- 导出  表 bulleye_admin.b_organization 结构
DROP TABLE IF EXISTS `b_organization`;
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

-- 正在导出表  bulleye_admin.b_organization 的数据：~0 rows (大约)
DELETE FROM `b_organization`;
/*!40000 ALTER TABLE `b_organization` DISABLE KEYS */;
INSERT INTO `b_organization` (`id`, `pid`, `code`, `name`, `area_id`, `brief`, `contact_name`, `contact_phone`, `domain`, `status`, `end_date`, `created`) VALUES
	('suiji', NULL, 'admin', '管理', NULL, '管理组', '管理员工', '1328179872', NULL, 0, '2018-07-31 11:41:55', '2018-07-31 11:41:56');
/*!40000 ALTER TABLE `b_organization` ENABLE KEYS */;

-- 导出  表 bulleye_admin.b_organization_menu 结构
DROP TABLE IF EXISTS `b_organization_menu`;
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

-- 正在导出表  bulleye_admin.b_organization_menu 的数据：~16 rows (大约)
DELETE FROM `b_organization_menu`;
/*!40000 ALTER TABLE `b_organization_menu` DISABLE KEYS */;
INSERT INTO `b_organization_menu` (`id`, `organization_id`, `menu_id`, `parent_id`, `display_name`, `status`, `is_leaf`, `created`) VALUES
	('2b001133-6d8a-11e9-8e25-049226bfdadf', 'suiji', '2', NULL, '系统管理', 1, b'0', '2019-05-03 17:59:46'),
	('2b023e22-6d8a-11e9-8e25-049226bfdadf', 'suiji', '11', NULL, '机构部门管理', 1, b'1', '2019-05-03 17:59:46'),
	('2b035148-6d8a-11e9-8e25-049226bfdadf', 'suiji', '2333', NULL, '登录日志管理', 1, b'1', '2019-05-03 17:59:46'),
	('2b046260-6d8a-11e9-8e25-049226bfdadf', 'suiji', '4', NULL, '账号管理', 1, b'1', '2019-05-03 17:59:46'),
	('2b0594ae-6d8a-11e9-8e25-049226bfdadf', 'suiji', '10', NULL, '组织机构管理', 1, b'1', '2019-05-03 17:59:46'),
	('2b0684d8-6d8a-11e9-8e25-049226bfdadf', 'suiji', '3', NULL, '角色管理', 1, b'1', '2019-05-03 17:59:46'),
	('2b076383-6d8a-11e9-8e25-049226bfdadf', 'suiji', '17', NULL, '菜单管理', 1, b'1', '2019-05-03 17:59:46'),
	('2b08293f-6d8a-11e9-8e25-049226bfdadf', 'suiji', '343223', NULL, '操作日志管理', 1, b'1', '2019-05-03 17:59:46'),
	('2b08fb1e-6d8a-11e9-8e25-049226bfdadf', 'suiji', '52091a21-460a-11e9-bd13-382c5e4d3b2a', NULL, '菜单权限管理', 1, b'1', '2019-05-03 17:59:46'),
	('2b09e65a-6d8a-11e9-8e25-049226bfdadf', 'suiji', '12', NULL, '一级菜单', 1, b'0', '2019-05-03 17:59:46'),
	('2b0ace59-6d8a-11e9-8e25-049226bfdadf', 'suiji', '13', NULL, '二级菜单', 1, b'0', '2019-05-03 17:59:46'),
	('2b0bd0f3-6d8a-11e9-8e25-049226bfdadf', 'suiji', '14', NULL, '三级菜单', 1, b'1', '2019-05-03 17:59:46'),
	('2b0c9d59-6d8a-11e9-8e25-049226bfdadf', 'suiji', '1', NULL, '微信管理', 1, b'0', '2019-05-03 17:59:46'),
	('2b0d5beb-6d8a-11e9-8e25-049226bfdadf', 'suiji', '6', NULL, '微信菜单管理', 1, b'1', '2019-05-03 17:59:46'),
	('2b0e35b3-6d8a-11e9-8e25-049226bfdadf', 'suiji', '8', NULL, '微信会员管理', 1, b'1', '2019-05-03 17:59:46'),
	('2b0f0f4f-6d8a-11e9-8e25-049226bfdadf', 'suiji', '7', NULL, '微信公众号管理', 1, b'1', '2019-05-03 17:59:46');
/*!40000 ALTER TABLE `b_organization_menu` ENABLE KEYS */;

-- 导出  表 bulleye_admin.b_organization_role 结构
DROP TABLE IF EXISTS `b_organization_role`;
CREATE TABLE IF NOT EXISTS `b_organization_role` (
  `id` varchar(50) NOT NULL,
  `organization_id` varchar(50) NOT NULL,
  `role_id` varchar(50) NOT NULL,
  `status` int(11) NOT NULL,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='组织角色授权';

-- 正在导出表  bulleye_admin.b_organization_role 的数据：~0 rows (大约)
DELETE FROM `b_organization_role`;
/*!40000 ALTER TABLE `b_organization_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `b_organization_role` ENABLE KEYS */;

-- 导出  表 bulleye_admin.b_role 结构
DROP TABLE IF EXISTS `b_role`;
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

-- 正在导出表  bulleye_admin.b_role 的数据：~4 rows (大约)
DELETE FROM `b_role`;
/*!40000 ALTER TABLE `b_role` DISABLE KEYS */;
INSERT INTO `b_role` (`id`, `organization_id`, `name`, `code`, `data_auth_flag`, `remark`, `status`, `created`) VALUES
	('1', 'admin', 'admin', 'admin', 'personal', '1212122222222', 0, '2018-07-31 11:44:18'),
	('12312312312312123', 'suiji', '4325345', 'test', NULL, '测试角色', 0, '2019-05-02 00:01:33'),
	('2', 'suiji', 'super', 'super', 'personal', '超级', 0, '2019-05-02 20:22:31'),
	('5ebcefdf-6c2a-11e9-8e25-049226bfdadf', 'suiji', '测试', 'test', NULL, '测试角色', 0, '2019-05-02 00:01:33');
/*!40000 ALTER TABLE `b_role` ENABLE KEYS */;

-- 导出  表 bulleye_admin.b_role_function 结构
DROP TABLE IF EXISTS `b_role_function`;
CREATE TABLE IF NOT EXISTS `b_role_function` (
  `id` varchar(40) NOT NULL COMMENT '唯一标识',
  `role_id` varchar(40) DEFAULT NULL COMMENT '角色标识',
  `menu_id` varchar(40) DEFAULT NULL COMMENT '菜单标识',
  `function_id` varchar(40) DEFAULT NULL COMMENT '功能标识',
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色功能关系表';

-- 正在导出表  bulleye_admin.b_role_function 的数据：~2 rows (大约)
DELETE FROM `b_role_function`;
/*!40000 ALTER TABLE `b_role_function` DISABLE KEYS */;
INSERT INTO `b_role_function` (`id`, `role_id`, `menu_id`, `function_id`, `created`) VALUES
	('dfgdf', '1', '1', '1', '2019-01-07 17:05:59'),
	('dfvd', '1', '3', '2', '2019-01-08 14:24:26');
/*!40000 ALTER TABLE `b_role_function` ENABLE KEYS */;

-- 导出  表 bulleye_admin.b_role_menu 结构
DROP TABLE IF EXISTS `b_role_menu`;
CREATE TABLE IF NOT EXISTS `b_role_menu` (
  `id` varchar(40) NOT NULL COMMENT '唯一标识',
  `role_id` varchar(40) DEFAULT NULL COMMENT '角色外键',
  `menu_id` varchar(40) NOT NULL COMMENT '菜单外键',
  `status` int(11) DEFAULT '1',
  `is_leaf` bit(1) DEFAULT b'1',
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色菜单表';

-- 正在导出表  bulleye_admin.b_role_menu 的数据：~33 rows (大约)
DELETE FROM `b_role_menu`;
/*!40000 ALTER TABLE `b_role_menu` DISABLE KEYS */;
INSERT INTO `b_role_menu` (`id`, `role_id`, `menu_id`, `status`, `is_leaf`, `created`) VALUES
	('2ea343da-6d4d-11e9-8e25-049226bfdadf', '5ebcefdf-6c2a-11e9-8e25-049226bfdadf', '2', 1, b'0', '2019-05-03 10:43:13'),
	('2ea44e42-6d4d-11e9-8e25-049226bfdadf', '5ebcefdf-6c2a-11e9-8e25-049226bfdadf', '52091a21-460a-11e9-bd13-382c5e4d3b2a', 1, b'1', '2019-05-03 10:43:13'),
	('2ea52a76-6d4d-11e9-8e25-049226bfdadf', '5ebcefdf-6c2a-11e9-8e25-049226bfdadf', '343223', 1, b'1', '2019-05-03 10:43:13'),
	('2ea651e2-6d4d-11e9-8e25-049226bfdadf', '5ebcefdf-6c2a-11e9-8e25-049226bfdadf', '3', 1, b'1', '2019-05-03 10:43:13'),
	('2ea7825c-6d4d-11e9-8e25-049226bfdadf', '5ebcefdf-6c2a-11e9-8e25-049226bfdadf', '2333', 1, b'1', '2019-05-03 10:43:13'),
	('2ea87ee4-6d4d-11e9-8e25-049226bfdadf', '5ebcefdf-6c2a-11e9-8e25-049226bfdadf', '4', 1, b'1', '2019-05-03 10:43:13'),
	('33272454-6d8a-11e9-8e25-049226bfdadf', '2', '2', 1, b'0', '2019-05-03 18:00:00'),
	('33290afb-6d8a-11e9-8e25-049226bfdadf', '2', '17', 1, b'1', '2019-05-03 18:00:00'),
	('332a303e-6d8a-11e9-8e25-049226bfdadf', '2', '4', 1, b'1', '2019-05-03 18:00:00'),
	('332b1383-6d8a-11e9-8e25-049226bfdadf', '2', '52091a21-460a-11e9-bd13-382c5e4d3b2a', 1, b'1', '2019-05-03 18:00:00'),
	('332bf46f-6d8a-11e9-8e25-049226bfdadf', '2', '3', 1, b'1', '2019-05-03 18:00:00'),
	('332cbadf-6d8a-11e9-8e25-049226bfdadf', '2', '2333', 1, b'1', '2019-05-03 18:00:00'),
	('332d7bc1-6d8a-11e9-8e25-049226bfdadf', '2', '343223', 1, b'1', '2019-05-03 18:00:00'),
	('332e3d64-6d8a-11e9-8e25-049226bfdadf', '2', '10', 1, b'1', '2019-05-03 18:00:00'),
	('332ee83c-6d8a-11e9-8e25-049226bfdadf', '2', '11', 1, b'1', '2019-05-03 18:00:00'),
	('332f8633-6d8a-11e9-8e25-049226bfdadf', '2', '1', 1, b'0', '2019-05-03 18:00:00'),
	('33302ad1-6d8a-11e9-8e25-049226bfdadf', '2', '8', 1, b'1', '2019-05-03 18:00:00'),
	('3330dee5-6d8a-11e9-8e25-049226bfdadf', '2', '6', 1, b'1', '2019-05-03 18:00:00'),
	('33319acd-6d8a-11e9-8e25-049226bfdadf', '2', '7', 1, b'1', '2019-05-03 18:00:00'),
	('33324769-6d8a-11e9-8e25-049226bfdadf', '2', '12', 1, b'0', '2019-05-03 18:00:00'),
	('3332f0ea-6d8a-11e9-8e25-049226bfdadf', '2', '13', 1, b'0', '2019-05-03 18:00:00'),
	('3333aa58-6d8a-11e9-8e25-049226bfdadf', '2', '14', 1, b'1', '2019-05-03 18:00:00'),
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

-- 导出  表 bulleye_admin.b_staff 结构
DROP TABLE IF EXISTS `b_staff`;
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
  `join_date` varchar(20) DEFAULT NULL COMMENT '入职日期',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='人员信息表';

-- 正在导出表  bulleye_admin.b_staff 的数据：~12 rows (大约)
DELETE FROM `b_staff`;
/*!40000 ALTER TABLE `b_staff` DISABLE KEYS */;
INSERT INTO `b_staff` (`id`, `serial_no`, `name`, `gender_id`, `mobile`, `email`, `avatar`, `organization_id`, `department_id`, `birthday`, `academic_id`, `degree_id`, `position_id`, `title_id`, `type_id`, `identify_type_id`, `identify_no`, `birth_province_id`, `birth_city_id`, `policy`, `nation_id`, `join_date`, `remark`, `status`, `created`) VALUES
	('1', '111111111', '周红星12', '1', '13258179872', '121212@qq.com', 'http://thirdwx.qlogo.cn/mmopen/7DkVkX1qfEGSjTDwwYXoShDTqo60F3HiclqLUbZEfOh2eLJFXvKUkxS0j7hvsRDpshJkjQYDzmfNDLzvQUegXKRl0s8RyjwI5/132', 'suiji', 'test', '19950602', '1', '1', '1', '1', '1', '111', '1', '1', '1', '1', '11', '', '11111', 1, '2018-07-31 14:15:10'),
	('2323', '111111111', '周红星12', '1', '13258179872', '121212@qq.com', 'http://thirdwx.qlogo.cn/mmopen/7DkVkX1qfEGSjTDwwYXoShDTqo60F3HiclqLUbZEfOh2eLJFXvKUkxS0j7hvsRDpshJkjQYDzmfNDLzvQUegXKRl0s8RyjwI5/132', 'suiji', 'test', '19950602', '1', '1', '1', '1', '1', '', '', '', '', '', '', '', '11111', 0, '2018-07-31 14:15:10'),
	('2333', '111111111', '周红星12', '1', '13258179872', '121212@qq.com', 'http://thirdwx.qlogo.cn/mmopen/7DkVkX1qfEGSjTDwwYXoShDTqo60F3HiclqLUbZEfOh2eLJFXvKUkxS0j7hvsRDpshJkjQYDzmfNDLzvQUegXKRl0s8RyjwI5/132', 'suiji', 'test', '19950602', '1', '1', '1', '1', '1', '', '', '', '', '', '', '', '11111', 0, '2018-07-31 14:15:10'),
	('3333', '511', '周红星', '1', '13258179872', '121212@qq.com', 'http://thirdwx.qlogo.cn/mmopen/7DkVkX1qfEGSjTDwwYXoShDTqo60F3HiclqLUbZEfOh2eLJFXvKUkxS0j7hvsRDpshJkjQYDzmfNDLzvQUegXKRl0s8RyjwI5/132', 'suiji', 'test', '19950602', '1', '1', '1', '1', '1', '', '', '', '', '', '', '', '', 0, '2018-07-31 14:15:10'),
	('4', '511', '周红星', '1', '13258179872', '121212@qq.com', 'http://thirdwx.qlogo.cn/mmopen/7DkVkX1qfEGSjTDwwYXoShDTqo60F3HiclqLUbZEfOh2eLJFXvKUkxS0j7hvsRDpshJkjQYDzmfNDLzvQUegXKRl0s8RyjwI5/132', 'suiji', 'test', '19950602', '1', '1', '1', '1', '1', '', '', '', '', '', '', '', '', 2, '2018-07-31 14:15:10'),
	('4444', '111111111', '周红星12', '1', '13258179872', '121212@qq.com', 'http://thirdwx.qlogo.cn/mmopen/7DkVkX1qfEGSjTDwwYXoShDTqo60F3HiclqLUbZEfOh2eLJFXvKUkxS0j7hvsRDpshJkjQYDzmfNDLzvQUegXKRl0s8RyjwI5/132', 'suiji', 'test', '19950602', '1', '1', '1', '1', '1', '', '', '', '', '', '', '', '11111', 0, '2018-07-31 14:15:10'),
	('44444444', '511', '周红星', '1', '13258179872', '121212@qq.com', 'http://thirdwx.qlogo.cn/mmopen/7DkVkX1qfEGSjTDwwYXoShDTqo60F3HiclqLUbZEfOh2eLJFXvKUkxS0j7hvsRDpshJkjQYDzmfNDLzvQUegXKRl0s8RyjwI5/132', 'suiji', 'test', '19950602', '1', '1', '1', '1', '1', '', '', '', '', '', '', '', '', NULL, '2018-07-31 14:15:10'),
	('5', '511', '周红星', '1', '13258179872', '121212@qq.com', 'http://thirdwx.qlogo.cn/mmopen/7DkVkX1qfEGSjTDwwYXoShDTqo60F3HiclqLUbZEfOh2eLJFXvKUkxS0j7hvsRDpshJkjQYDzmfNDLzvQUegXKRl0s8RyjwI5/132', 'suiji', 'test', '19950602', '1', '1', '1', '1', '1', '', '', '', '', '', '', '', '', 0, '2018-07-31 14:15:10'),
	('6', '511', '周红星', '1', '13258179872', '121212@qq.com', 'http://thirdwx.qlogo.cn/mmopen/7DkVkX1qfEGSjTDwwYXoShDTqo60F3HiclqLUbZEfOh2eLJFXvKUkxS0j7hvsRDpshJkjQYDzmfNDLzvQUegXKRl0s8RyjwI5/132', 'suiji', 'test', '19950602', '1', '1', '1', '1', '1', '', '', '', '', '', '', '', '', NULL, '2018-07-31 14:15:10'),
	('7', '511', '周红星', '1', '13258179872', '121212@qq.com', 'http://thirdwx.qlogo.cn/mmopen/7DkVkX1qfEGSjTDwwYXoShDTqo60F3HiclqLUbZEfOh2eLJFXvKUkxS0j7hvsRDpshJkjQYDzmfNDLzvQUegXKRl0s8RyjwI5/132', 'suiji', 'test', '19950602', '1', '1', '1', '1', '1', '', '', '', '', '', '', '', '23232333333', 0, '2018-07-31 14:15:10'),
	('ae371c56-13f0-11e9-97db-382c4a232da1', '12121212', '123zhx1111', NULL, '1325817972', 'zhx', 'http://thirdwx.qlogo.cn/mmopen/7DkVkX1qfEGSjTDwwYXoShDTqo60F3HiclqLUbZEfOh2eLJFXvKUkxS0j7hvsRDpshJkjQYDzmfNDLzvQUegXKRl0s8RyjwI5/132', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1212', 0, '2019-01-09 17:26:31'),
	('qwqwq', '11111111111111', '周红星12', '1', '13258179872', '121212@qq.com', 'http://thirdwx.qlogo.cn/mmopen/7DkVkX1qfEGSjTDwwYXoShDTqo60F3HiclqLUbZEfOh2eLJFXvKUkxS0j7hvsRDpshJkjQYDzmfNDLzvQUegXKRl0s8RyjwI5/132', 'suiji', 'test', '19950602', '1', '1', '1', '1', '1', '', '', '', '', '', '', '', '11111', 0, '2018-07-31 14:15:10');
/*!40000 ALTER TABLE `b_staff` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
