/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : century_20150912

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2015-09-12 17:22:26
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_sys_bas`
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_bas`;
CREATE TABLE `t_sys_bas` (
  `sys_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `upd_date` varchar(10) DEFAULT NULL,
  `upd_time` varchar(8) DEFAULT NULL,
  `upd_user` varchar(20) DEFAULT NULL,
  `attr1` varchar(20) DEFAULT NULL,
  `attr2` varchar(20) DEFAULT NULL,
  `attr3` varchar(20) DEFAULT NULL,
  `attr4` varchar(20) DEFAULT NULL,
  `attr5` varchar(20) DEFAULT NULL,
  `attr6` varchar(20) DEFAULT NULL,
  `bas_id` varchar(50) DEFAULT NULL,
  `cla_id` varchar(50) DEFAULT NULL,
  `delflag` bit(1) NOT NULL,
  `demo` varchar(100) DEFAULT NULL,
  `modflag` bit(1) NOT NULL,
  `name` varchar(30) DEFAULT NULL,
  `create_date` varchar(255) DEFAULT NULL,
  `create_time` varchar(255) DEFAULT NULL,
  `order_by` varchar(255) DEFAULT NULL,
  `create_user` varchar(20) DEFAULT NULL,
  `msg_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`sys_id`)
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_bas
-- ----------------------------
INSERT INTO `t_sys_bas` VALUES ('9', '2015-04-23', '16:13:56', 'admin', '', '', '', '', '', '', '00', 'AccountState', '', '', '', '账户状态', null, null, '1', null, null);
INSERT INTO `t_sys_bas` VALUES ('10', '', '', '', '123456', '', '', '', '', '', '00', 'InitPassword', '', '', '', '初始密码', null, null, null, null, null);
INSERT INTO `t_sys_bas` VALUES ('11', '2015-05-14', '21:52:49', 'admin', '', '', '', '', '', '', '01', 'AccountState', '', '', '', '未激活', null, null, '2', null, null);
INSERT INTO `t_sys_bas` VALUES ('12', '2015-05-14', '21:58:50', 'admin', '', '', '', '', '', '', '02', 'AccountState', '', '', '', '正常', null, null, '3', null, null);
INSERT INTO `t_sys_bas` VALUES ('13', '2015-05-14', '21:58:33', 'admin', '', '', '', '', '', '', '03', 'AccountState', '', '', '', '停用', null, null, '4', null, null);
INSERT INTO `t_sys_bas` VALUES ('16', '2015-06-09', '21:01:56', 'admin', '', '', '', '', '', '', '00', 'Position', '', '', '', '职位', null, null, '1', null, null);
INSERT INTO `t_sys_bas` VALUES ('17', '2015-06-09', '21:01:48', 'admin', '', '', '', '', '', '', '01', 'Position', '', '', '', '管理员', null, null, '2', null, null);
INSERT INTO `t_sys_bas` VALUES ('26', '', '', '', '', '', '', '', '', '', '00', 'Gender', '', '', '', '性别', null, null, null, null, null);
INSERT INTO `t_sys_bas` VALUES ('27', '2015-06-09', '20:42:58', 'admin', '', '', '', '', '', '', 'O', 'Gender', '', '', '', '未知', null, null, '1', null, null);
INSERT INTO `t_sys_bas` VALUES ('28', '2015-06-09', '20:43:17', 'admin', '', '', '', '', '', '', 'M', 'Gender', '', '', '', '男', null, null, '1', null, null);
INSERT INTO `t_sys_bas` VALUES ('29', '2015-06-09', '20:43:37', 'admin', '', '', '', '', '', '', 'F', 'Gender', '', '', '', '女', null, null, '1', null, null);
INSERT INTO `t_sys_bas` VALUES ('31', '2015-06-09', '21:02:07', 'admin', '', '', '', '', '', '', '00', 'MenuState', '', '', '', '菜单状态', null, null, '1', null, null);
INSERT INTO `t_sys_bas` VALUES ('33', '2015-06-09', '21:02:03', 'admin', '', '', '', '', '', '', '01', 'MenuState', '', '', '', '正常', null, null, '2', null, null);
INSERT INTO `t_sys_bas` VALUES ('34', '2015-05-14', '22:25:39', 'admin', '', '', '', '', '', '', '02', 'MenuState', '', '', '', '停用', null, null, '3', null, null);
INSERT INTO `t_sys_bas` VALUES ('40', '', '', '', '', '', '', '', '', '', '00', 'FUN', '', '', '', '功能按钮', null, null, null, null, null);
INSERT INTO `t_sys_bas` VALUES ('41', null, null, null, '1', '', '', '', '', '', 'create', 'FUN', '', '', '', '新增', null, null, null, null, null);
INSERT INTO `t_sys_bas` VALUES ('42', null, null, null, '2', '', '', '', '', '', 'edit', 'FUN', '', '', '', '修改', null, null, null, null, null);
INSERT INTO `t_sys_bas` VALUES ('45', '2015-04-27', '14:48:29', 'admin', '3', '', '', '', '', '', 'delete', 'FUN', '', '', '', '删除', null, null, '1', null, null);
INSERT INTO `t_sys_bas` VALUES ('46', null, null, null, '4', '', '', '', '', '', 'excel', 'FUN', '', '', '', '汇出XLS', null, null, null, null, null);
INSERT INTO `t_sys_bas` VALUES ('47', null, null, null, '5', '', '', '', '', '', 'show', 'FUN', '', '显示指的是父亲菜单，只需要显示菜单而没有实际内容', '', '显示', null, null, null, null, null);
INSERT INTO `t_sys_bas` VALUES ('56', null, null, null, '6', '', '', '', '', '', 'query', 'FUN', '', '', '', '查询', null, null, null, null, null);
INSERT INTO `t_sys_bas` VALUES ('57', null, null, null, '7', '', '', '', '', '', 'detail', 'FUN', '', '', '', '详情', null, null, null, null, null);
INSERT INTO `t_sys_bas` VALUES ('58', null, null, null, '', '', '', '', '', '', '00', 'MenuIcon', '', '', '', '菜单图标', null, null, null, null, null);
INSERT INTO `t_sys_bas` VALUES ('59', '2015-05-14', '21:55:09', 'admin', '', '', '', '', '', '', 'fa-desktop', 'MenuIcon', '', '', '', '桌面', null, null, '1', null, null);
INSERT INTO `t_sys_bas` VALUES ('60', '2015-05-14', '21:54:59', 'admin', '', '', '', '', '', '', 'fa-list', 'MenuIcon', '', '', '', '列表', null, null, '1', null, null);
INSERT INTO `t_sys_bas` VALUES ('61', null, null, null, '', '', '', '', '', '', 'fa-calendar', 'MenuIcon', '', '', '', '日历', null, null, null, null, null);
INSERT INTO `t_sys_bas` VALUES ('62', null, null, null, '', '', '', '', '', '', 'fa-comment', 'MenuIcon', '', '', '', '评论', null, null, null, null, null);
INSERT INTO `t_sys_bas` VALUES ('63', null, null, null, '', '', '', '', '', '', 'fa-envelope', 'MenuIcon', '', '', '', '邮件', null, null, null, null, null);
INSERT INTO `t_sys_bas` VALUES ('64', null, null, null, '', '', '', '', '', '', 'fa-bell', 'MenuIcon', '', '', '', '铃铛', null, null, null, null, null);
INSERT INTO `t_sys_bas` VALUES ('65', null, null, null, '', '', '', '', '', '', 'fa-credit-card', 'MenuIcon', '', '', '', '信用卡', null, null, null, null, null);
INSERT INTO `t_sys_bas` VALUES ('66', null, null, null, '', '', '', '', '', '', 'fa-folder', 'MenuIcon', '', '', '', '文件夹', null, null, null, null, null);
INSERT INTO `t_sys_bas` VALUES ('67', '2015-04-23', '16:11:07', 'admin', '', '', '', '', '', '', 'fa-book', 'MenuIcon', '', '', '', '书本', null, null, '1', null, null);
INSERT INTO `t_sys_bas` VALUES ('68', null, null, null, '', '', '', '', '', '', '00', 'DeptState', '', '', '', '部门状态', '2015-04-27', '09:25:46', '1', 'admin', null);
INSERT INTO `t_sys_bas` VALUES ('69', null, null, null, '', '', '', '', '', '', '01', 'DeptState', '', '', '', '未激活', '2015-04-27', '09:26:06', '1', 'admin', null);
INSERT INTO `t_sys_bas` VALUES ('70', null, null, null, '', '', '', '', '', '', '02', 'DeptState', '', '', '', '正常', '2015-04-27', '09:26:24', '2', 'admin', null);
INSERT INTO `t_sys_bas` VALUES ('71', null, null, null, '', '', '', '', '', '', '03', 'DeptState', '', '', '', '停用', '2015-04-27', '09:26:37', '3', 'admin', null);
INSERT INTO `t_sys_bas` VALUES ('72', '2015-06-09', '21:01:52', 'admin', '', '', '', '', '', '', '02', 'Position', '', '', '', '普通会员', '2015-06-09', '21:01:21', '3', 'admin', null);
INSERT INTO `t_sys_bas` VALUES ('73', null, null, null, '', '', '', '', '', '', '00', 'Category', '', '', '', '栏目', '2015-06-17', '10:07:26', '1', 'test', null);
INSERT INTO `t_sys_bas` VALUES ('74', '2015-06-29', '15:01:21', 'admin', null, null, null, null, null, null, '2', 'Category', '', null, '', '融资', '2015-06-17', '11:07:27', null, 'test', null);
INSERT INTO `t_sys_bas` VALUES ('75', '2015-06-22', '19:48:27', 'admin', null, null, null, null, null, null, '02', 'Category', '', null, '', '投资', '2015-06-22', '19:02:04', null, 'admin', null);
INSERT INTO `t_sys_bas` VALUES ('76', null, null, null, null, null, null, null, null, null, '1', 'Category', '', null, '', 'n', '2015-06-29', '15:01:36', null, 'admin', null);
INSERT INTO `t_sys_bas` VALUES ('77', '2015-07-08', '10:25:35', 'admin', '', '', '', '', '', '', '03', 'Position', '', '', '', '商家', '2015-07-08', '10:25:23', '3', 'admin', null);
INSERT INTO `t_sys_bas` VALUES ('78', null, null, null, '', '', '', '', '', '', '00', 'ConsumeType', '', '', '', '消费方式', '2015-07-09', '16:16:27', '1', 'admin', null);
INSERT INTO `t_sys_bas` VALUES ('79', null, null, null, '', '', '', '', '', '', '1', 'ConsumeType', '', '', '', '线下消费', '2015-07-09', '16:16:46', '1', 'admin', null);
INSERT INTO `t_sys_bas` VALUES ('80', null, null, null, '', '', '', '', '', '', '2', 'ConsumeType', '', '', '', '充值', '2015-07-09', '16:17:00', '1', 'admin', null);

-- ----------------------------
-- Table structure for `t_sys_dept`
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_dept`;
CREATE TABLE `t_sys_dept` (
  `sys_id` varchar(255) NOT NULL,
  `upd_date` varchar(10) DEFAULT NULL,
  `upd_time` varchar(8) DEFAULT NULL,
  `upd_user` varchar(20) DEFAULT NULL,
  `dept_enname` varchar(30) DEFAULT NULL,
  `dept_id` varchar(10) DEFAULT NULL,
  `dept_level` int(11) DEFAULT NULL,
  `dept_name` varchar(30) DEFAULT NULL,
  `dept_relation` varchar(100) DEFAULT NULL,
  `order_by` int(11) DEFAULT NULL,
  `state` varchar(10) DEFAULT NULL,
  `under_dept` varchar(100) DEFAULT NULL,
  `user_count` int(11) DEFAULT NULL,
  `boss_id` varchar(255) DEFAULT NULL,
  `create_date` varchar(255) DEFAULT NULL,
  `create_time` varchar(255) DEFAULT NULL,
  `msg_id` varchar(255) DEFAULT NULL,
  `create_user` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`sys_id`),
  KEY `FK_strosqe6ccmefcnttbpddl2xg` (`boss_id`) USING BTREE,
  CONSTRAINT `t_sys_dept_ibfk_1` FOREIGN KEY (`boss_id`) REFERENCES `t_sys_user` (`sys_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_dept
-- ----------------------------
INSERT INTO `t_sys_dept` VALUES ('20150130100000000040', '2015-07-08', '10:44:44', 'admin', 'century', '01', '1', '世纪通', '/', '1', '02', '', null, null, null, null, 'century', null);
INSERT INTO `t_sys_dept` VALUES ('20150708100000000004', null, null, null, 'merchant', '02', '2', '商家', '/01/', '1', '02', null, null, null, '2015-07-08', '10:56:38', 'merchant', 'admin');

-- ----------------------------
-- Table structure for `t_sys_login`
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_login`;
CREATE TABLE `t_sys_login` (
  `sys_id` varchar(255) NOT NULL,
  `create_date` varchar(255) DEFAULT NULL,
  `create_time` varchar(255) DEFAULT NULL,
  `upd_date` varchar(255) DEFAULT NULL,
  `upd_time` varchar(255) DEFAULT NULL,
  `upd_user` varchar(255) DEFAULT NULL,
  `dept_sys_id` varchar(255) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `mail_valid_code` varchar(255) DEFAULT NULL,
  `mail_valid_time` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `role_id` varchar(255) DEFAULT NULL,
  `salt_key` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `create_user` varchar(20) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`sys_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_login
-- ----------------------------
INSERT INTO `t_sys_login` VALUES ('admin', '2014-10-10', '20:10:10', '2014-10-10', '20:10:10', 'admin', '20150130100000000040', '173186915@qq.com', '', '', '18650036719', 'ff0b4396a245d24124790c1119c102e625474f3a0de9e92468f7b0a3785c2f9a', '01', '11566abd4679662f0039fef1a7c140b6', '01', null, null);
INSERT INTO `t_sys_login` VALUES ('test', '2015-04-18', '17:22:35', '2015-04-18', '17:22:35', 'admin', '20150130100000000040', 'peng4400@foxmail.com', '', '', '18650037619', 'd6b6a76c3846d1bb458634df98556c66f7ce560b26032ffb160d743f0d7fd31f', '01', '31469eafd528c3d0115c6c1348ec6cc0', '01', null, null);
INSERT INTO `t_sys_login` VALUES ('weihuangpeng', null, null, '2015-05-31', '22:49:46', 'admin', '20150130100000000040', 'peng4400@gmail.com', '', '', '15260363780', '116d60eeff3032b170e98144473711a37b8c10e6dcaeb7b1c3f0710bdd298bca', '01', 'c65019373c17a8735ded8572716daa98', '01', 'admin', null);

-- ----------------------------
-- Table structure for `t_sys_login_log`
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_login_log`;
CREATE TABLE `t_sys_login_log` (
  `sys_id` varchar(255) NOT NULL,
  `create_date` varchar(255) DEFAULT NULL,
  `create_time` varchar(255) DEFAULT NULL,
  `upd_date` varchar(255) DEFAULT NULL,
  `upd_time` varchar(255) DEFAULT NULL,
  `upd_user` varchar(255) DEFAULT NULL,
  `demo` varchar(255) DEFAULT NULL,
  `dept_sys_id` varchar(255) DEFAULT NULL,
  `dept_name` varchar(255) DEFAULT NULL,
  `host_name` varchar(255) DEFAULT NULL,
  `ip` varchar(255) DEFAULT NULL,
  `login_date` varchar(255) DEFAULT NULL,
  `login_time` varchar(255) DEFAULT NULL,
  `logout_date` varchar(255) DEFAULT NULL,
  `logout_time` varchar(255) DEFAULT NULL,
  `role_id` varchar(255) DEFAULT NULL,
  `role_name` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `create_user` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`sys_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_login_log
-- ----------------------------

-- ----------------------------
-- Table structure for `t_sys_menu`
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_menu`;
CREATE TABLE `t_sys_menu` (
  `sys_id` varchar(255) NOT NULL,
  `upd_date` varchar(10) DEFAULT NULL,
  `upd_time` varchar(8) DEFAULT NULL,
  `upd_user` varchar(20) DEFAULT NULL,
  `funs` longtext,
  `menu_enname` varchar(30) DEFAULT NULL,
  `menu_id` varchar(10) DEFAULT NULL,
  `menu_level` int(11) DEFAULT NULL,
  `menu_name` varchar(30) DEFAULT NULL,
  `menu_relation` varchar(100) DEFAULT NULL,
  `order_by` int(11) DEFAULT NULL,
  `state` varchar(10) DEFAULT NULL,
  `under_menu` varchar(100) DEFAULT NULL,
  `url` varchar(100) DEFAULT NULL,
  `permission_pre` varchar(255) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `create_date` varchar(255) DEFAULT NULL,
  `create_time` varchar(255) DEFAULT NULL,
  `msg_id` varchar(255) DEFAULT NULL,
  `create_user` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`sys_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_menu
-- ----------------------------
INSERT INTO `t_sys_menu` VALUES ('20150420100000000001', '2015-04-27', '14:41:54', 'admin', 'show', 'Sys', 'SYSM00', '1', '系统维护', '/', '1', '01', null, '#', 'modules:sys', 'fa-desktop', '2015-04-19', '20:41:19', 'sys', 'admin');
INSERT INTO `t_sys_menu` VALUES ('20150420100000000002', '2015-04-27', '14:42:04', 'admin', 'excel,delete,query,edit,create', 'SysTable', 'SYSM01', '2', '表的信息维护', '/SYSM00/', '1', '01', null, '/modules/sys/table', 'modules:sys:table', 'fa-calendar', '2015-04-19', '20:46:24', 'table', 'admin');
INSERT INTO `t_sys_menu` VALUES ('20150420100000000003', '2015-04-27', '14:43:59', 'admin', 'excel,delete,query,edit,create', 'Schedule', 'SYSM02', '2', '任务调度管理', '/SYSM00/', '2', '01', null, '/modules/sys/schedule', 'modules:sys:schedule', 'fa-calendar', '2015-04-19', '20:41:19', 'schedule', 'admin');
INSERT INTO `t_sys_menu` VALUES ('20150420100000000004', '2015-04-27', '14:44:06', 'admin', 'excel,delete,query,edit,create', 'SysMsg', 'SYSM03', '2', '语系资料查询', '/SYSM00/', '3', '01', null, '/modules/sys/msg', 'modules:sys:msg', 'fa-calendar', '2015-04-19', '20:40:41', 'msg', 'admin');
INSERT INTO `t_sys_menu` VALUES ('20150420100000000005', '2015-05-14', '22:13:43', 'admin', 'excel,delete,query,edit,create', 'SysMenu', 'SYSM04', '2', '菜单信息维护', '/SYSM00/', '4', '01', null, '/modules/sys/menu', 'modules:sys:menu', 'fa-calendar', '2015-04-19', '20:46:16', 'menu', 'admin');
INSERT INTO `t_sys_menu` VALUES ('20150420100000000006', '2015-05-14', '22:13:54', 'admin', 'excel,delete,query,edit,create', 'SysUser', 'SYSM05', '2', '员工资料维护', '/SYSM00/', '5', '01', null, '/modules/sys/user', 'modules:sys:user', 'fa-calendar', '2015-04-19', '20:40:04', 'user', 'admin');
INSERT INTO `t_sys_menu` VALUES ('20150420100000000007', '2015-05-14', '22:14:06', 'admin', 'query,edit', 'SysRolePermission', 'SYSM06', '2', '角色权限维护', '/SYSM00/', '6', '01', null, '/modules/sys/rolePermission', 'modules:sys:rolePermission', 'fa-calendar', '2015-04-19', '20:40:18', 'rolePermission', 'admin');
INSERT INTO `t_sys_menu` VALUES ('20150420100000000008', '2015-05-14', '22:14:34', 'admin', 'query,edit', 'SysUserPermission', 'SYSM07', '2', '用户权限维护', '/SYSM00/', '7', '01', null, '/modules/sys/userPermission', 'modules:sys:userPermission', 'fa-calendar', '2015-04-19', '20:40:51', 'userPermission', 'admin');
INSERT INTO `t_sys_menu` VALUES ('20150420100000000009', '2015-05-14', '22:14:27', 'admin', 'excel,delete,query,edit,create', 'SysBas', 'SYSM08', '2', '系统参数维护', '/SYSM00/', '8', '01', null, '/modules/sys/bas', 'modules:sys:bas', 'fa-calendar', '2015-04-19', '20:40:25', 'bas', 'admin');
INSERT INTO `t_sys_menu` VALUES ('20150420100000000010', '2015-05-14', '22:14:41', 'admin', 'excel,delete,query,edit,create', 'SysDept', 'SYSM07', '2', '部门信息维护', '/SYSM00/', '9', '01', null, '/modules/sys/dept', 'modules:sys:dept', 'fa-calendar', '2015-04-19', '20:40:58', 'dept', 'admin');
INSERT INTO `t_sys_menu` VALUES ('20150420100000000011', '2015-05-14', '22:14:16', 'admin', 'query', 'SysLoginLog', 'SYSM10', '2', '登陆日志查询', '/SYSM00/', '10', '01', null, '/modules/sys/loginLog', 'modules:sys:loginLog', 'fa-calendar', '2015-04-19', '20:40:30', 'loginLog', 'admin');
INSERT INTO `t_sys_menu` VALUES ('20150511100000000001', '2015-05-14', '22:36:59', 'admin', 'excel,query', 'SysLog', 'SYSM11', '2', '系统日志统计', '/SYSM00/', '11', '01', null, '/modules/sys/log', 'modules:sys:log', 'fa-calendar', '2015-05-11', '10:44:20', 'SysLog', 'admin');
INSERT INTO `t_sys_menu` VALUES ('20150604100000000003', '2015-06-04', '04:58:36', 'test', 'show', 'CMS', 'CMS00', '1', '内容管理', '/', '1', '01', null, '#', 'modules:cms', 'fa-calendar', '2015-06-04', '04:53:36', 'cms', 'test');
INSERT INTO `t_sys_menu` VALUES ('20150604100000000004', '2015-06-04', '04:59:47', 'test', 'excel,delete,query,edit,create,detail', 'Article', 'CMS01', '2', '文章管理', '/CMS00/', '1', '01', null, '/modules/cms/article', 'modules:cms:article', 'fa-calendar', '2015-06-04', '04:55:03', 'article', 'test');
INSERT INTO `t_sys_menu` VALUES ('20150612100000000005', null, null, null, 'show', 'CEN00', 'CEN00', '1', '推送管理', '/', '0', '01', null, '#', 'modules:century', 'fa-desktop', '2015-06-12', '09:47:52', 'CEN00', 'test');
INSERT INTO `t_sys_menu` VALUES ('20150612100000000006', '2015-06-12', '10:04:18', 'test', 'excel,delete,query,edit,create,detail', 'CeNews', 'CEN01', '2', '新闻管理', '/CEN00/', '1', '01', null, '/modules/century/news', 'modules:century:news', 'fa-calendar', '2015-06-12', '09:53:10', 'news', 'test');
INSERT INTO `t_sys_menu` VALUES ('20150612100000000007', null, null, null, 'excel,delete,query,edit,create,detail', 'broadcast', 'CEN02', '2', '广播管理', '/CEN00/', '2', '01', null, '/modules/century/broadcast', 'modules:century:broadcast', 'fa-calendar', '2015-06-12', '10:40:40', 'broadcast', 'test');
INSERT INTO `t_sys_menu` VALUES ('20150613100000000008', '2015-06-13', '13:19:45', 'test', 'excel,delete,query,show,edit,create,detail', 'activity', 'CEN03', '2', '活动信息管理', '/CEN00/', '3', '01', null, '/modules/century/activity', 'modules:century:activity', 'fa-calendar', '2015-06-13', '12:56:26', 'activity', 'test');
INSERT INTO `t_sys_menu` VALUES ('20150613100000000009', '2015-06-13', '13:19:56', 'test', 'excel,delete,query,edit,create,detail', 'enroll', 'CEN04', '2', '活动报名管理', '/CEN00/', '4', '01', null, '/modules/century/enroll', 'modules:century:enroll', 'fa-calendar', '2015-06-13', '12:57:40', 'enroll', 'test');
INSERT INTO `t_sys_menu` VALUES ('20150613100000000010', null, null, null, 'excel,delete,query,edit,create,detail', 'aboutus', 'CMS02', '2', '关于我们', '/CMS00/', '2', '01', null, '/modules/century/aboutus', 'modules:century:aboutus', 'fa-calendar', '2015-06-13', '17:52:12', 'aboutus', 'test');
INSERT INTO `t_sys_menu` VALUES ('20150617100000000011', '2015-06-17', '10:29:24', 'test', 'show', 'NEM00', 'NEM00', '1', '参数设置', '/', '0', '01', null, '#', 'modules:ne', 'fa-desktop', '2015-06-17', '10:27:02', 'NEM00', 'test');
INSERT INTO `t_sys_menu` VALUES ('20150617100000000012', '2015-06-17', '11:00:16', 'test', 'delete,edit,create', 'category', 'NEM01', '2', '栏目设置', '/NEM00/', '1', '01', null, '/modules/ne/category', 'modules:ne:category', 'fa-calendar', '2015-06-17', '10:28:25', 'category', 'test');
INSERT INTO `t_sys_menu` VALUES ('20150708100000000013', null, null, null, 'show', 'custom00', 'custom00', '1', '客服', '/', '5', '01', null, '#', 'modules:century', 'fa-bell', '2015-07-08', '16:18:42', 'customService', 'admin');
INSERT INTO `t_sys_menu` VALUES ('20150708100000000014', '2015-07-08', '16:27:21', 'admin', 'query', 'customMsg', 'custom01', '2', '客服消息', '/custom00/', '1', '01', null, '/modules/century/social/', 'modules:century:social', 'fa-bell', '2015-07-08', '16:22:21', 'customMsg', 'admin');
INSERT INTO `t_sys_menu` VALUES ('20150709100000000015', '2015-07-09', '16:51:42', 'admin', 'show', 'Consume00', 'Consume', '1', '商家', '/', '1', '01', null, '#', 'modules:century', 'fa-envelope', '2015-07-09', '16:51:34', 'Consume', 'admin');
INSERT INTO `t_sys_menu` VALUES ('20150709100000000016', '2015-07-09', '16:59:16', 'admin', 'query,edit,create', 'ConsumeMag', 'Consume01', '2', '商户消费', '/Consume/', '1', '02', null, '/modules/century/order', 'modules:century:order', 'fa-calendar', '2015-07-09', '16:53:21', 'ConsumeMag', 'admin');
INSERT INTO `t_sys_menu` VALUES ('20150912100000000017', '2015-09-12', '10:34:23', 'admin', 'query,show,edit,create', 'order', 'Consume02', '2', '商家订单', '/Consume/', '1', '01', null, '/modules/century/order', 'modules:century:order', 'fa-desktop', '2015-09-12', '10:33:22', 'order', 'admin');

-- ----------------------------
-- Table structure for `t_sys_msg`
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_msg`;
CREATE TABLE `t_sys_msg` (
  `sys_id` varchar(255) NOT NULL,
  `upd_date` varchar(10) DEFAULT NULL,
  `upd_time` varchar(8) DEFAULT NULL,
  `upd_user` varchar(20) DEFAULT NULL,
  `attr1` varchar(30) DEFAULT NULL,
  `attr2` varchar(30) DEFAULT NULL,
  `chs_name` varchar(100) DEFAULT NULL,
  `cht_name` varchar(100) DEFAULT NULL,
  `demo` varchar(100) DEFAULT NULL,
  `en_name` varchar(100) DEFAULT NULL,
  `msg_id` varchar(100) DEFAULT NULL,
  `msg_type` varchar(10) DEFAULT NULL,
  `state` varchar(10) DEFAULT NULL,
  `create_date` varchar(255) DEFAULT NULL,
  `create_time` varchar(255) DEFAULT NULL,
  `create_user` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`sys_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_msg
-- ----------------------------
INSERT INTO `t_sys_msg` VALUES ('20150202100000000006', '2015-03-22', '10:11:16', 'admin', null, null, '消息代号', '消息代號', '是', 'MsgId', 'msgId', '栏位', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150322100000000014', '2015-03-22', '10:11:16', 'admin', null, null, '简体名称', '簡體名稱', '', 'ChsName', 'chsName', '栏位', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150322100000000015', '2015-03-22', '10:11:16', 'admin', null, null, '繁体名称', '繁體名稱', '', 'ChtName', 'chtName', '栏位', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150322100000000016', '2015-03-22', '10:11:16', 'admin', null, null, '英文名称', '英文名稱', '', 'EnName', 'enName', '栏位', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150322100000000017', '2015-03-24', '15:22:15', 'admin', 'd', 'd', '备注', '備註', 's', 'Demo', 'demo', '栏位', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150322100000000018', '2015-03-22', '10:49:21', 'admin', null, null, '状态', '狀態', '', 'State', 'state', '栏位', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150322100000000019', '2015-03-22', '10:49:21', 'admin', null, null, '消息类别', '類別', 's', 'MsgType', 'msgType', '栏位', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150322100000000020', '2015-03-26', '10:05:49', 'admin', null, null, '查询', '查詢', 's', 'Query', 'query', '系统按钮', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150322100000000021', '2015-03-26', '10:05:49', 'admin', null, null, '修改', '修改', '', 'Edit', 'edit', '系统按钮', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150322100000000022', '2015-03-27', '10:10:10', 'admin', '', '', '创建', '創建', '', 'Create', 'create', '系统按鈕', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150322100000000023', '2015-03-25', '14:31:37', 'admin', null, null, '删除', '刪除', '', 'Delete', 'delete', '按鈕', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150322100000000024', '2015-03-26', '10:05:49', 'admin', null, null, '导出', '導出', '', 'Export', 'export', '系统按钮', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150322100000000025', '2015-03-25', '14:31:37', 'admin', null, null, '新增', '新增', 'd', 'Add', 'add', '按鈕', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150325100000000026', '2015-03-25', '15:14:11', 'admin', '是', '是', '重置', '重置', 's', 'Rest', 'reset', '按鈕', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150325100000000027', '2015-03-25', '15:12:26', 'admin', null, null, '保存', '保存', 'd', 'Save', 'save', '按鈕', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150325100000000028', '2015-03-26', '10:05:49', 'admin', null, null, '对话框', '對話框', '', 'Dialog', 'dialog', '系统消息', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150325100000000029', '2015-03-25', '15:12:26', 'admin', null, null, '确定', '確定', '', 'Confirm', 'confirm', '消息', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150325100000000030', '2015-03-25', '15:12:26', 'admin', null, null, '取消', '取消', '', 'Cancel', 'cancel', '消息', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150325100000000031', '2015-03-25', '15:12:26', 'admin', null, null, '删除成功', '刪除成功', '', 'DeleteSuccess', 'deleteSuccess', '消息', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150325100000000032', '2015-03-25', '15:12:26', 'admin', null, null, '删除失败', '刪除失敗', '', 'DeleteFail', 'deleteFail', '消息', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150325100000000033', '2015-03-25', '15:12:26', 'admin', null, null, '保存成功', '保存成功', '', 'SaveSuccess', 'saveSuccess', '消息', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150325100000000034', '2015-03-25', '15:12:26', 'admin', null, null, '保存失败', '保存失敗', '', 'saveFail', 'saveFail', '消息', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150325100000000035', '2015-03-26', '10:05:49', 'admin', null, null, '编辑成功', '編輯成功', '', 'EditSuccess', 'editSuccess', '系统消息', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150325100000000036', '2015-03-26', '10:05:49', 'admin', null, null, '编辑失败', '編輯失敗', '', 'EditFail', 'editFail', '系统消息', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150325100000000037', '2015-03-25', '15:12:26', 'admin', null, null, '确认是否删除', '確認是否刪除', '', 'ConfirmDelete', 'confirmDelete', '消息', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150325100000000038', '2015-03-25', '15:12:26', 'admin', null, null, '请选择重试', '請選擇後重試', '', 'SelectRetry', 'selectedReTry', '消息', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150326100000000039', '2015-03-26', '10:05:49', 'admin', null, null, '复制', '複製', '', 'Copy', 'copy', '系统按钮', '', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150326100000000041', '2015-03-26', '15:54:21', 'admin', '1', '1', '员工编号', '員工編號', '1', 'userId', 'userId', '欄位', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150326100000000042', '2015-03-26', '15:53:12', 'admin', '1', '1', '中文名字', '中文名字', '1', 'name', 'name', '欄位', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150326100000000043', '2015-03-26', '16:04:30', 'admin', '1', '1', '英文名字', '英文名字', '1', 'enName', 'enName', '欄位', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150326100000000044', '2015-03-26', '16:20:03', 'admin', '1', '1', '用工类型', '用工類型', '1', 'userType', 'userType', '栏位', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150326100000000045', '2015-03-26', '16:35:16', 'admin', '1', '1', '联系方式', '聯繫方式', '1', 'personEmail', 'personEmail', '栏位', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150326100000000046', '2015-03-26', '16:21:54', 'admin', '1', '1', '班级', '班級', '1', 'stuClass', 'stuClass', '欄位', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150326100000000047', '2015-03-26', '16:30:27', 'admin', '1', '1', '加入日期', '加入日期', '1', 'inDate', 'inDate', '欄位', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150326100000000048', '2015-03-26', '16:46:11', 'admin', '1', '1', '部门', '部門', '1', 'deptId', 'deptId', '欄位', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150326100000000049', '2015-03-26', '16:47:38', 'admin', '1', '1', '职务名称', '職務名稱', '1', 'titleId', 'titleId', '欄位', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150326100000000050', '2015-03-26', '17:43:01', 'admin', '1', '1', '身份证', '身份證', '1', 'idNo', 'idNo', '欄位', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150326100000000051', '2015-03-26', '17:49:35', 'admin', '1', '1', '性別', '性別', '1', 'gender', 'gender', '欄位', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150326100000000052', '2015-03-26', '21:36:18', 'admin', '1', '1', '出生日期', '出生日期', '1', 'birthday', 'birthday', '欄位', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150326100000000053', '2015-03-26', '21:42:14', 'admin', '1', '1', '离职日期', '離職日期', '1', 'leaveDate', 'leaveDate', '欄位', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150326100000000054', '2015-03-26', '22:02:15', 'admin', '1', '1', '手机号码', '手機號碼', '1', 'mobile', 'mobile', '欄位', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150326100000000055', '2015-03-26', '22:03:54', 'admin', '1', '1', 'QQ', 'QQ', '1', 'qq', 'qq', '欄位', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150326100000000056', '2015-03-26', '22:05:58', 'admin', '1', '1', '籍贯', '籍貫', '1', 'nativeAddr', 'nativeAddr', '栏位', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150326100000000057', '2015-03-26', '22:06:59', 'admin', '1', '1', '家庭住址', '家庭住址', '11', 'homeAddr', 'homeAddr', '栏位', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150326100000000058', '2015-03-26', '22:07:49', 'admin', '1', '1', '赞助地址', '贊助地址', '11', 'tempAddr', 'tempAddr', '栏位', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150326100000000059', '2015-03-26', '22:08:32', 'admin', '1', '1', '邮箱', '郵箱', '1', 'email', 'email', '栏位', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150326100000000060', '2015-03-26', '22:09:06', 'admin', '1', '1', '公司邮箱', '公司郵箱', '1', 'email', 'email', '栏位', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150326100000000061', '2015-03-26', '22:10:06', 'admin', '1', '1', '电话', '電話', '1', 'tel', 'tel', '欄位', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150326100000000062', '2015-03-26', '22:10:52', 'admin', '1', '1', '紧急电话', '緊急電話', '1', 'backUpTel', 'backUpTel', '欄位', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150326100000000063', '2015-03-26', '22:14:18', 'admin', '1', '1', '学校', '學校', '1', 'stuSchool', 'stuSchool', '欄位', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150326100000000064', '2015-03-26', '22:16:15', 'admin', '1', '1', '学号', '學號', '1', 'stuId', 'stuId', '欄位', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150326100000000065', '2015-03-26', '22:17:08', 'admin', '1', '1', '毕业日期', '畢業日期', '1', 'graduationDate', 'graduationDate', '欄位', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150326100000000066', '2015-03-26', '22:18:16', 'admin', '1', '1', '技术特长', '技術特長', '1', 'skills', 'skills', '欄位', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150327100000000069', '2015-03-27', '10:22:07', 'admin', 'd', '', '关闭', '關閉', '', 'Close', 'close', '按鈕', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150327100000000070', '2015-03-27', '10:24:04', 'admin', '', '', '更新成功', '更新成功', '', 'UpdateSuccess', 'updateSuccess', '消息', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150327100000000071', '2015-03-27', '10:25:29', 'admin', '', '', '更新失败', '更新失敗', '', 'UpdateFail', 'updateFail', '消息', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150327100000000073', '2015-03-27', '10:40:11', 'admin', '', '', '更新对话框', '更新對話框', '', 'UpdateDialog', 'updateDialog', '消息', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150327100000000075', '2015-03-27', '10:41:29', 'admin', '', '', '消息对话框', '消息對話框', '', 'MessageDialog', 'messageDialog', '消息', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150327100000000079', '2015-03-27', '10:48:06', 'admin', '', '', '查询对话框', '查詢對話框', '', 'QueryDialog', 'queryDialog', '消息', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150327100000000082', '2015-03-27', '10:52:43', 'admin', '', '', '类别', '類別', '', 'Category', 'category', '栏位', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150327100000000091', '2015-03-27', '11:06:14', 'admin', '', '', '点击查看详情', '點擊查看詳情', '', 'ClickSampleDetails', 'clickSampleDetails', '欄位', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150327100000000092', '2015-03-27', '11:08:01', 'admin', '', '', '确认对话框', '確認對話框', '', 'ConfirmDialog', 'confirmDialog', '消息', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150327100000000093', '2015-03-27', '11:17:41', 'admin', '', '', '警告提示框', '警告提示框', '', 'WarningBox', 'warningBox', '消息', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150327100000000094', '2015-03-27', '11:20:21', 'admin', '', '', '创建对话框', '創建對話框', '', 'CreateDialog', 'createDialog', '消息', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150327100000000095', '2015-03-27', '11:21:47', 'admin', '', '', '更新', '更新', '', 'Update', 'update', '按鈕', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150327100000000097', '2015-03-27', '11:29:18', 'admin', '', '', '保存对话框', '保存對話框', '', 'SaveDialog', 'saveDialog', '消息', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150327100000000105', '2015-03-27', '14:44:46', 'admin', '', '', '负责人', '負責人', '', 'Head', 'head', '栏位', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150327100000000110', '2015-03-27', '17:06:03', 'admin', '', '', '选择导出数据类型', '選擇導出數據類型', '', 'SelectExportDataTypes', 'selectExportDataTypes', '消息', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150327100000000111', '2015-03-27', '16:46:12', 'admin', '', '', '请选择', '請選擇', '', 'PleaseSelect', 'pleaseSelect', '按鈕', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150327100000000112', '2015-03-27', '17:23:03', 'admin', '', '', '您当前尚未选中一行，请选择后再试', '您當前尚未選中一行，請選擇后再試', '', 'You currently have not yet selected a line, please try again after selection', 'youCurrentlyHaveNotYetSelectedALinePleaseTryAgainAfterSelection', '消息', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150327100000000113', '2015-03-27', '17:23:16', 'admin', '', '', '您当前选中多行，请重新选择', '您當前選中多行，請重新選擇', '', 'You currently selected multiple lines, please choose again', 'youCurrentlySelectedMultipleLinesPleaseChooseAgain', '消息', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150327100000000115', '2015-03-27', '17:41:16', 'admin', '', '', '数据有误', '數據有誤', '', 'The data is wrong', 'theDataIsWrong', '消息', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150330100000000132', '2015-03-30', '09:17:35', 'admin', '', '', '复制对话框', '复制对话框', '', 'Copydialog', 'copyDialog', '栏位', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150330100000000133', '2015-03-30', '09:23:51', 'admin', '', '', '是否确认删除', '是否确认删除', '', 'AreYouSureToDelete', 'areYouSureToDelete', '消息', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150330100000000146', '2015-03-30', '10:54:43', 'admin', '', '', '联系人', '联系人', '', 'Contacts', 'contacts', '栏目', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150330100000000147', '2015-03-30', '10:56:26', 'admin', '', '', '请输入联系人', '请输入联系人', '', 'PleaseContacts', 'pleaseContacts', '栏目', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150330100000000148', '2015-03-30', '10:58:00', 'admin', '', '', '联系电话', '联系电话', '', 'Phone', 'phone', '栏目', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150330100000000149', '2015-03-30', '11:01:27', 'admin', '', '', '清输入联系电话', '請輸入聯繫電話', '', 'PleasePhone', 'pleasePhone', '栏目', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150330100000000150', '2015-03-30', '11:02:55', 'admin', '', '', '收货地址', '收貨地址', '', 'ShippingAddress', 'shippingAddress', '栏目', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150330100000000151', '2015-03-30', '11:04:02', 'admin', '', '', '请输入收货地址', '請輸入收貨地址', '', 'PleaseAddress', 'pleaseAddress', '栏目', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150330100000000154', '2015-03-30', '15:18:13', 'admin', '', '', '请输入', '請輸入', '', 'please', 'please', '栏目', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150330100000000155', '2015-03-30', '14:36:23', 'admin', '', '', '至', '至', '', 'To', 'to', '栏目', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150330100000000158', '2015-03-30', '15:09:41', 'admin', '', '', '清除', '清除', '', 'Clear', 'clear', '按钮', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150330100000000160', '2015-03-30', '15:17:35', 'admin', '', '', '请输入', '請輸入', '', 'Please Enter The', 'PleaseEnterThe', '消息', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150330100000000161', '2015-03-30', '15:35:33', 'admin', '', '', '无', '無', '', 'Not', 'not', '提示', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150331100000000165', '2015-03-31', '10:37:08', 'admin', '', '', '用户id', '用戶id', '', 'UserId', 'userId', '栏位', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150331100000000169', '2015-03-31', '10:57:58', 'admin', '', '', '留言信息', '留言信息', '', 'VoiceMessage', 'voiceMessage', '栏位', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150331100000000171', '2015-03-31', '14:17:35', 'admin', '', '', '已完成', '已完成', '', 'HasBeenCompleted', 'hasBeenCompleted', '消息', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150331100000000172', '2015-03-31', '16:55:35', 'admin', '', '', '不可修改', '不可修改', '', 'Do not modify the', 'doNotModifyThe', '消息', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150401100000000173', '2015-04-01', '10:21:54', 'admin', '', '', '请选择用户id', '請選擇用戶id', '', 'PleaseSelectUserId', 'pleaseSelectUserId', '消息', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150403100000000177', '2015-04-03', '15:47:01', 'admin', '', '', '请输入你的信息', '情輸入你的信息', '', 'pleaseEnterYourInformation', 'pleaseEnterYourInformation', '栏目', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150403100000000178', '2015-04-03', '15:48:09', 'admin', '', '', '留言', '留言', '', 'Voice', 'voice', '欄目', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150403100000000179', '2015-04-03', '15:51:26', 'admin', '', '', '输入手机号码/邮箱', '輸入手機號碼/郵箱', '', 'PleasePhoneNumberMailbox', 'pleasePhoneNumberMailbox', '栏目', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150403100000000180', '2015-04-03', '15:53:06', 'admin', '', '', '请输入密码', '請輸入密碼', '', 'PleaseEnterAPassword', 'pleaseEnterAPassword', '栏目', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150403100000000183', '2015-04-03', '15:59:06', 'admin', '', '', '请输入验证码', '請輸入驗證碼', '', 'PleaseEnterTheVerificationCode', 'pleaseEnterTheVerificationCode', '栏目', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150403100000000184', '2015-04-03', '16:00:47', 'admin', '', '', '下次自动登录', '下次自動登錄', '', 'RememberLogin', 'rememberLogin', '栏目', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150403100000000185', '2015-04-03', '16:02:16', 'admin', '', '', '登录', '登錄', '', 'LogIn', 'logIn', '栏目', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150403100000000186', '2015-04-03', '16:03:47', 'admin', '', '', '注册账号', '註冊賬號', '', 'RegisterAnAccount', 'registerAnAccount', '栏目', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150403100000000187', '2015-04-03', '16:04:58', 'admin', '', '', '忘记密码', '忘記密碼', '', 'ForgotPassword', 'forgotPassword', '栏目', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150403100000000188', '2015-04-03', '16:07:36', 'admin', '', '', '找回密码', '找回密碼', '', 'RorgotPassword', 'retrievePassword', '栏目', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150403100000000189', '2015-04-03', '16:09:23', 'admin', '', '', '通过登陆邮箱找回密码', '通過登陸郵箱找回密碼', '', 'ForgotYourPasswordByLoggingMailbox', 'forgotYourPasswordByLoggingMailbox', '栏目', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150403100000000190', '2015-04-03', '16:12:20', 'admin', '', '', '请输入登录邮箱', '請輸入登錄郵箱', '', 'PleaseEnterYourLoginEmail', 'pleaseEnterYourLoginEmail', '栏目', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150403100000000191', '2015-04-03', '16:13:30', 'admin', '', '', '发送验证邮箱', '發送驗證郵箱', '', 'SendVerificationEmail', 'sendVerificationEmail', '栏目', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150403100000000193', '2015-04-03', '16:14:55', 'admin', '', '', '返回登陆', '返回登陸', '', 'BackToLogin', 'backToLogin', '栏目', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150403100000000194', '2015-04-03', '16:17:17', 'admin', '', '', '请填写注册信息', '請填寫註冊信息系', '', 'PleaseFillOutTheRegistrationInformation', 'peaseFillOutTheRegistrationInformation', '栏目', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150403100000000195', '2015-04-03', '16:18:15', 'admin', '', '', '请输入名称', '請輸入名稱', '', 'PleaseEnterAName', 'pleaseEnterAName', '栏目', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150403100000000196', '2015-04-03', '16:20:57', 'admin', '', '', '请输入英文名称', '請輸入英文名稱', '', 'PleaseEnterTheEnglishName', 'pleaseEnterTheEnglishName', '栏目', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150403100000000197', '2015-04-03', '16:23:02', 'admin', '', '', '请输入联系邮箱', '請輸入聯繫郵箱', '', 'PleaseEnterAContactEmail', 'pleaseEnterAContactEmail', '栏目', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150403100000000198', '2015-04-03', '16:24:34', 'admin', '', '', '请输入联系电话', '請輸入聯繫電話', '', 'PleaseEnterTheContactTelephoneNumber', 'pleaseEnterTheContactTelephoneNumber', '栏目', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150403100000000199', '2015-04-03', '16:26:52', 'admin', '', '', '请输入公司名称', '請輸入公司名稱', '', 'PleaseEnterTheCompanyName', 'pleaseEnterTheCompanyName', '栏目', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150403100000000200', '2015-04-03', '16:27:40', 'admin', '', '', '确定注册', '確定註冊', '', 'OKSignUp', 'oKSignUp', '栏目', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150403100000000201', '2015-04-03', '16:30:01', 'admin', '', '', '创新研发中心', '創新研發中心', '', 'InnovationCenter', 'innovationCenter', '栏目', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150403100000000202', '2015-04-03', '16:31:58', 'admin', '', '', '主题切换:', '主題切換:', '', 'SwitchingTopics', 'switchingTopics', '栏目', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150403100000000203', '2015-04-03', '16:32:50', 'admin', '', '', '黑色', '黑色', '', 'Black', 'black', '栏目', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150403100000000204', '2015-04-03', '16:33:37', 'admin', '', '', '蓝色', '藍色', '', 'Blue', 'blue', '栏目', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150403100000000205', '2015-04-03', '16:34:36', 'admin', '', '', '白色', '白色', '', 'White', 'white', '栏目', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150403100000000206', '2015-04-03', '16:36:02', 'admin', '', '', '*必填', '*必填', '', 'Required', 'required', '栏目', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150403100000000207', '2015-04-03', '16:43:57', 'admin', '', '', '请输入正确的英文名！', '請輸入正確的英文名！', '', 'PleaseeEnterTheCorrectEnglishName', 'pleaseeEnterTheCorrectEnglishName', '栏目', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150403100000000208', '2015-04-03', '16:45:01', 'admin', '', '', '请输入正确的邮箱!', '請輸入正確的郵箱！', '', 'PleaseEnterACorrectEmail', 'pleaseEnterACorrectEmail', '栏目', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150403100000000209', '2015-04-03', '16:46:14', 'admin', '', '', '请输入正确的电话号码!', '請輸入正確的電話號碼！', '', 'PleaseEnterTheCorrectTelephoneNumber', 'pleaseEnterTheCorrectTelephoneNumber', '栏目', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150403100000000210', '2015-04-03', '16:47:37', 'admin', '', '', '注册成功', '註冊成功', '', 'SuccessfulRegistration', 'successfulRegistration', '栏目', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150403100000000211', '2015-04-03', '16:48:14', 'admin', '', '', '注册对话框', '註冊對話框', '', 'RegistrationDialog', 'registrationDialog', '栏目', '1', null, null, null);
INSERT INTO `t_sys_msg` VALUES ('20150403100000000212', '2015-04-03', '16:48:56', 'admin', '', '', '注册失败', '註冊失敗', '', 'RegistrationFailed', 'registrationFailed', '栏目', '1', null, null, null);

-- ----------------------------
-- Table structure for `t_sys_role_permission`
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_role_permission`;
CREATE TABLE `t_sys_role_permission` (
  `sys_id` varchar(255) NOT NULL,
  `create_date` varchar(255) DEFAULT NULL,
  `create_time` varchar(255) DEFAULT NULL,
  `create_user` varchar(20) DEFAULT NULL,
  `upd_date` varchar(255) DEFAULT NULL,
  `upd_time` varchar(255) DEFAULT NULL,
  `upd_user` varchar(255) DEFAULT NULL,
  `dept_sys_id` varchar(255) DEFAULT NULL,
  `func` longtext,
  `menu_sys_id` varchar(255) DEFAULT NULL,
  `permission` longtext,
  `permission_pre` varchar(255) DEFAULT NULL,
  `role_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`sys_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_role_permission
-- ----------------------------
INSERT INTO `t_sys_role_permission` VALUES ('20150912100000001668', '2015-09-12', '10:33:45', 'admin', null, null, null, '20150130100000000040', 'show', '20150612100000000005', 'modules:century:show', 'modules:century', '01');
INSERT INTO `t_sys_role_permission` VALUES ('20150912100000001669', '2015-09-12', '10:33:45', 'admin', null, null, null, '20150130100000000040', 'excel', '20150612100000000006', 'modules:century:news:excel', 'modules:century:news', '01');
INSERT INTO `t_sys_role_permission` VALUES ('20150912100000001670', '2015-09-12', '10:33:45', 'admin', null, null, null, '20150130100000000040', 'delete', '20150612100000000006', 'modules:century:news:delete', 'modules:century:news', '01');
INSERT INTO `t_sys_role_permission` VALUES ('20150912100000001671', '2015-09-12', '10:33:45', 'admin', null, null, null, '20150130100000000040', 'query', '20150612100000000006', 'modules:century:news:query', 'modules:century:news', '01');
INSERT INTO `t_sys_role_permission` VALUES ('20150912100000001672', '2015-09-12', '10:33:45', 'admin', null, null, null, '20150130100000000040', 'edit', '20150612100000000006', 'modules:century:news:edit', 'modules:century:news', '01');
INSERT INTO `t_sys_role_permission` VALUES ('20150912100000001673', '2015-09-12', '10:33:45', 'admin', null, null, null, '20150130100000000040', 'create', '20150612100000000006', 'modules:century:news:create', 'modules:century:news', '01');
INSERT INTO `t_sys_role_permission` VALUES ('20150912100000001674', '2015-09-12', '10:33:45', 'admin', null, null, null, '20150130100000000040', 'detail', '20150612100000000006', 'modules:century:news:detail', 'modules:century:news', '01');
INSERT INTO `t_sys_role_permission` VALUES ('20150912100000001675', '2015-09-12', '10:33:45', 'admin', null, null, null, '20150130100000000040', 'excel', '20150612100000000007', 'modules:century:broadcast:excel', 'modules:century:broadcast', '01');
INSERT INTO `t_sys_role_permission` VALUES ('20150912100000001676', '2015-09-12', '10:33:45', 'admin', null, null, null, '20150130100000000040', 'delete', '20150612100000000007', 'modules:century:broadcast:delete', 'modules:century:broadcast', '01');
INSERT INTO `t_sys_role_permission` VALUES ('20150912100000001677', '2015-09-12', '10:33:45', 'admin', null, null, null, '20150130100000000040', 'query', '20150612100000000007', 'modules:century:broadcast:query', 'modules:century:broadcast', '01');
INSERT INTO `t_sys_role_permission` VALUES ('20150912100000001678', '2015-09-12', '10:33:45', 'admin', null, null, null, '20150130100000000040', 'edit', '20150612100000000007', 'modules:century:broadcast:edit', 'modules:century:broadcast', '01');
INSERT INTO `t_sys_role_permission` VALUES ('20150912100000001679', '2015-09-12', '10:33:45', 'admin', null, null, null, '20150130100000000040', 'create', '20150612100000000007', 'modules:century:broadcast:create', 'modules:century:broadcast', '01');
INSERT INTO `t_sys_role_permission` VALUES ('20150912100000001680', '2015-09-12', '10:33:45', 'admin', null, null, null, '20150130100000000040', 'detail', '20150612100000000007', 'modules:century:broadcast:detail', 'modules:century:broadcast', '01');
INSERT INTO `t_sys_role_permission` VALUES ('20150912100000001681', '2015-09-12', '10:33:45', 'admin', null, null, null, '20150130100000000040', 'excel', '20150613100000000008', 'modules:century:activity:excel', 'modules:century:activity', '01');
INSERT INTO `t_sys_role_permission` VALUES ('20150912100000001682', '2015-09-12', '10:33:45', 'admin', null, null, null, '20150130100000000040', 'delete', '20150613100000000008', 'modules:century:activity:delete', 'modules:century:activity', '01');
INSERT INTO `t_sys_role_permission` VALUES ('20150912100000001683', '2015-09-12', '10:33:45', 'admin', null, null, null, '20150130100000000040', 'query', '20150613100000000008', 'modules:century:activity:query', 'modules:century:activity', '01');
INSERT INTO `t_sys_role_permission` VALUES ('20150912100000001684', '2015-09-12', '10:33:45', 'admin', null, null, null, '20150130100000000040', 'show', '20150613100000000008', 'modules:century:activity:show', 'modules:century:activity', '01');
INSERT INTO `t_sys_role_permission` VALUES ('20150912100000001685', '2015-09-12', '10:33:45', 'admin', null, null, null, '20150130100000000040', 'edit', '20150613100000000008', 'modules:century:activity:edit', 'modules:century:activity', '01');
INSERT INTO `t_sys_role_permission` VALUES ('20150912100000001686', '2015-09-12', '10:33:45', 'admin', null, null, null, '20150130100000000040', 'create', '20150613100000000008', 'modules:century:activity:create', 'modules:century:activity', '01');
INSERT INTO `t_sys_role_permission` VALUES ('20150912100000001687', '2015-09-12', '10:33:45', 'admin', null, null, null, '20150130100000000040', 'detail', '20150613100000000008', 'modules:century:activity:detail', 'modules:century:activity', '01');
INSERT INTO `t_sys_role_permission` VALUES ('20150912100000001688', '2015-09-12', '10:33:45', 'admin', null, null, null, '20150130100000000040', 'excel', '20150613100000000009', 'modules:century:enroll:excel', 'modules:century:enroll', '01');
INSERT INTO `t_sys_role_permission` VALUES ('20150912100000001689', '2015-09-12', '10:33:45', 'admin', null, null, null, '20150130100000000040', 'delete', '20150613100000000009', 'modules:century:enroll:delete', 'modules:century:enroll', '01');
INSERT INTO `t_sys_role_permission` VALUES ('20150912100000001690', '2015-09-12', '10:33:45', 'admin', null, null, null, '20150130100000000040', 'query', '20150613100000000009', 'modules:century:enroll:query', 'modules:century:enroll', '01');
INSERT INTO `t_sys_role_permission` VALUES ('20150912100000001691', '2015-09-12', '10:33:45', 'admin', null, null, null, '20150130100000000040', 'edit', '20150613100000000009', 'modules:century:enroll:edit', 'modules:century:enroll', '01');
INSERT INTO `t_sys_role_permission` VALUES ('20150912100000001692', '2015-09-12', '10:33:45', 'admin', null, null, null, '20150130100000000040', 'create', '20150613100000000009', 'modules:century:enroll:create', 'modules:century:enroll', '01');
INSERT INTO `t_sys_role_permission` VALUES ('20150912100000001693', '2015-09-12', '10:33:45', 'admin', null, null, null, '20150130100000000040', 'detail', '20150613100000000009', 'modules:century:enroll:detail', 'modules:century:enroll', '01');
INSERT INTO `t_sys_role_permission` VALUES ('20150912100000001694', '2015-09-12', '10:33:45', 'admin', null, null, null, '20150130100000000040', 'show', '20150617100000000011', 'modules:ne:show', 'modules:ne', '01');
INSERT INTO `t_sys_role_permission` VALUES ('20150912100000001695', '2015-09-12', '10:33:45', 'admin', null, null, null, '20150130100000000040', 'delete', '20150617100000000012', 'modules:ne:category:delete', 'modules:ne:category', '01');
INSERT INTO `t_sys_role_permission` VALUES ('20150912100000001696', '2015-09-12', '10:33:45', 'admin', null, null, null, '20150130100000000040', 'edit', '20150617100000000012', 'modules:ne:category:edit', 'modules:ne:category', '01');
INSERT INTO `t_sys_role_permission` VALUES ('20150912100000001697', '2015-09-12', '10:33:45', 'admin', null, null, null, '20150130100000000040', 'create', '20150617100000000012', 'modules:ne:category:create', 'modules:ne:category', '01');
INSERT INTO `t_sys_role_permission` VALUES ('20150912100000001698', '2015-09-12', '10:33:45', 'admin', null, null, null, '20150130100000000040', 'show', '20150420100000000001', 'modules:sys:show', 'modules:sys', '01');
INSERT INTO `t_sys_role_permission` VALUES ('20150912100000001699', '2015-09-12', '10:33:45', 'admin', null, null, null, '20150130100000000040', 'excel', '20150420100000000002', 'modules:sys:table:excel', 'modules:sys:table', '01');
INSERT INTO `t_sys_role_permission` VALUES ('20150912100000001700', '2015-09-12', '10:33:45', 'admin', null, null, null, '20150130100000000040', 'delete', '20150420100000000002', 'modules:sys:table:delete', 'modules:sys:table', '01');
INSERT INTO `t_sys_role_permission` VALUES ('20150912100000001701', '2015-09-12', '10:33:45', 'admin', null, null, null, '20150130100000000040', 'query', '20150420100000000002', 'modules:sys:table:query', 'modules:sys:table', '01');
INSERT INTO `t_sys_role_permission` VALUES ('20150912100000001702', '2015-09-12', '10:33:45', 'admin', null, null, null, '20150130100000000040', 'edit', '20150420100000000002', 'modules:sys:table:edit', 'modules:sys:table', '01');
INSERT INTO `t_sys_role_permission` VALUES ('20150912100000001703', '2015-09-12', '10:33:45', 'admin', null, null, null, '20150130100000000040', 'create', '20150420100000000002', 'modules:sys:table:create', 'modules:sys:table', '01');
INSERT INTO `t_sys_role_permission` VALUES ('20150912100000001704', '2015-09-12', '10:33:45', 'admin', null, null, null, '20150130100000000040', 'excel', '20150420100000000003', 'modules:sys:schedule:excel', 'modules:sys:schedule', '01');
INSERT INTO `t_sys_role_permission` VALUES ('20150912100000001705', '2015-09-12', '10:33:45', 'admin', null, null, null, '20150130100000000040', 'delete', '20150420100000000003', 'modules:sys:schedule:delete', 'modules:sys:schedule', '01');
INSERT INTO `t_sys_role_permission` VALUES ('20150912100000001706', '2015-09-12', '10:33:46', 'admin', null, null, null, '20150130100000000040', 'query', '20150420100000000003', 'modules:sys:schedule:query', 'modules:sys:schedule', '01');
INSERT INTO `t_sys_role_permission` VALUES ('20150912100000001707', '2015-09-12', '10:33:46', 'admin', null, null, null, '20150130100000000040', 'edit', '20150420100000000003', 'modules:sys:schedule:edit', 'modules:sys:schedule', '01');
INSERT INTO `t_sys_role_permission` VALUES ('20150912100000001708', '2015-09-12', '10:33:46', 'admin', null, null, null, '20150130100000000040', 'create', '20150420100000000003', 'modules:sys:schedule:create', 'modules:sys:schedule', '01');
INSERT INTO `t_sys_role_permission` VALUES ('20150912100000001709', '2015-09-12', '10:33:46', 'admin', null, null, null, '20150130100000000040', 'excel', '20150420100000000004', 'modules:sys:msg:excel', 'modules:sys:msg', '01');
INSERT INTO `t_sys_role_permission` VALUES ('20150912100000001710', '2015-09-12', '10:33:46', 'admin', null, null, null, '20150130100000000040', 'delete', '20150420100000000004', 'modules:sys:msg:delete', 'modules:sys:msg', '01');
INSERT INTO `t_sys_role_permission` VALUES ('20150912100000001711', '2015-09-12', '10:33:46', 'admin', null, null, null, '20150130100000000040', 'query', '20150420100000000004', 'modules:sys:msg:query', 'modules:sys:msg', '01');
INSERT INTO `t_sys_role_permission` VALUES ('20150912100000001712', '2015-09-12', '10:33:46', 'admin', null, null, null, '20150130100000000040', 'edit', '20150420100000000004', 'modules:sys:msg:edit', 'modules:sys:msg', '01');
INSERT INTO `t_sys_role_permission` VALUES ('20150912100000001713', '2015-09-12', '10:33:46', 'admin', null, null, null, '20150130100000000040', 'create', '20150420100000000004', 'modules:sys:msg:create', 'modules:sys:msg', '01');
INSERT INTO `t_sys_role_permission` VALUES ('20150912100000001714', '2015-09-12', '10:33:46', 'admin', null, null, null, '20150130100000000040', 'excel', '20150420100000000005', 'modules:sys:menu:excel', 'modules:sys:menu', '01');
INSERT INTO `t_sys_role_permission` VALUES ('20150912100000001715', '2015-09-12', '10:33:46', 'admin', null, null, null, '20150130100000000040', 'delete', '20150420100000000005', 'modules:sys:menu:delete', 'modules:sys:menu', '01');
INSERT INTO `t_sys_role_permission` VALUES ('20150912100000001716', '2015-09-12', '10:33:46', 'admin', null, null, null, '20150130100000000040', 'query', '20150420100000000005', 'modules:sys:menu:query', 'modules:sys:menu', '01');
INSERT INTO `t_sys_role_permission` VALUES ('20150912100000001717', '2015-09-12', '10:33:46', 'admin', null, null, null, '20150130100000000040', 'edit', '20150420100000000005', 'modules:sys:menu:edit', 'modules:sys:menu', '01');
INSERT INTO `t_sys_role_permission` VALUES ('20150912100000001718', '2015-09-12', '10:33:46', 'admin', null, null, null, '20150130100000000040', 'create', '20150420100000000005', 'modules:sys:menu:create', 'modules:sys:menu', '01');
INSERT INTO `t_sys_role_permission` VALUES ('20150912100000001719', '2015-09-12', '10:33:46', 'admin', null, null, null, '20150130100000000040', 'excel', '20150420100000000006', 'modules:sys:user:excel', 'modules:sys:user', '01');
INSERT INTO `t_sys_role_permission` VALUES ('20150912100000001720', '2015-09-12', '10:33:46', 'admin', null, null, null, '20150130100000000040', 'delete', '20150420100000000006', 'modules:sys:user:delete', 'modules:sys:user', '01');
INSERT INTO `t_sys_role_permission` VALUES ('20150912100000001721', '2015-09-12', '10:33:46', 'admin', null, null, null, '20150130100000000040', 'query', '20150420100000000006', 'modules:sys:user:query', 'modules:sys:user', '01');
INSERT INTO `t_sys_role_permission` VALUES ('20150912100000001722', '2015-09-12', '10:33:46', 'admin', null, null, null, '20150130100000000040', 'edit', '20150420100000000006', 'modules:sys:user:edit', 'modules:sys:user', '01');
INSERT INTO `t_sys_role_permission` VALUES ('20150912100000001723', '2015-09-12', '10:33:46', 'admin', null, null, null, '20150130100000000040', 'create', '20150420100000000006', 'modules:sys:user:create', 'modules:sys:user', '01');
INSERT INTO `t_sys_role_permission` VALUES ('20150912100000001724', '2015-09-12', '10:33:46', 'admin', null, null, null, '20150130100000000040', 'query', '20150420100000000007', 'modules:sys:rolePermission:query', 'modules:sys:rolePermission', '01');
INSERT INTO `t_sys_role_permission` VALUES ('20150912100000001725', '2015-09-12', '10:33:46', 'admin', null, null, null, '20150130100000000040', 'edit', '20150420100000000007', 'modules:sys:rolePermission:edit', 'modules:sys:rolePermission', '01');
INSERT INTO `t_sys_role_permission` VALUES ('20150912100000001726', '2015-09-12', '10:33:46', 'admin', null, null, null, '20150130100000000040', 'query', '20150420100000000008', 'modules:sys:userPermission:query', 'modules:sys:userPermission', '01');
INSERT INTO `t_sys_role_permission` VALUES ('20150912100000001727', '2015-09-12', '10:33:46', 'admin', null, null, null, '20150130100000000040', 'edit', '20150420100000000008', 'modules:sys:userPermission:edit', 'modules:sys:userPermission', '01');
INSERT INTO `t_sys_role_permission` VALUES ('20150912100000001728', '2015-09-12', '10:33:46', 'admin', null, null, null, '20150130100000000040', 'excel', '20150420100000000009', 'modules:sys:bas:excel', 'modules:sys:bas', '01');
INSERT INTO `t_sys_role_permission` VALUES ('20150912100000001729', '2015-09-12', '10:33:46', 'admin', null, null, null, '20150130100000000040', 'delete', '20150420100000000009', 'modules:sys:bas:delete', 'modules:sys:bas', '01');
INSERT INTO `t_sys_role_permission` VALUES ('20150912100000001730', '2015-09-12', '10:33:46', 'admin', null, null, null, '20150130100000000040', 'query', '20150420100000000009', 'modules:sys:bas:query', 'modules:sys:bas', '01');
INSERT INTO `t_sys_role_permission` VALUES ('20150912100000001731', '2015-09-12', '10:33:46', 'admin', null, null, null, '20150130100000000040', 'edit', '20150420100000000009', 'modules:sys:bas:edit', 'modules:sys:bas', '01');
INSERT INTO `t_sys_role_permission` VALUES ('20150912100000001732', '2015-09-12', '10:33:46', 'admin', null, null, null, '20150130100000000040', 'create', '20150420100000000009', 'modules:sys:bas:create', 'modules:sys:bas', '01');
INSERT INTO `t_sys_role_permission` VALUES ('20150912100000001733', '2015-09-12', '10:33:46', 'admin', null, null, null, '20150130100000000040', 'excel', '20150420100000000010', 'modules:sys:dept:excel', 'modules:sys:dept', '01');
INSERT INTO `t_sys_role_permission` VALUES ('20150912100000001734', '2015-09-12', '10:33:46', 'admin', null, null, null, '20150130100000000040', 'delete', '20150420100000000010', 'modules:sys:dept:delete', 'modules:sys:dept', '01');
INSERT INTO `t_sys_role_permission` VALUES ('20150912100000001735', '2015-09-12', '10:33:46', 'admin', null, null, null, '20150130100000000040', 'query', '20150420100000000010', 'modules:sys:dept:query', 'modules:sys:dept', '01');
INSERT INTO `t_sys_role_permission` VALUES ('20150912100000001736', '2015-09-12', '10:33:46', 'admin', null, null, null, '20150130100000000040', 'edit', '20150420100000000010', 'modules:sys:dept:edit', 'modules:sys:dept', '01');
INSERT INTO `t_sys_role_permission` VALUES ('20150912100000001737', '2015-09-12', '10:33:46', 'admin', null, null, null, '20150130100000000040', 'create', '20150420100000000010', 'modules:sys:dept:create', 'modules:sys:dept', '01');
INSERT INTO `t_sys_role_permission` VALUES ('20150912100000001738', '2015-09-12', '10:33:46', 'admin', null, null, null, '20150130100000000040', 'query', '20150420100000000011', 'modules:sys:loginLog:query', 'modules:sys:loginLog', '01');
INSERT INTO `t_sys_role_permission` VALUES ('20150912100000001739', '2015-09-12', '10:33:46', 'admin', null, null, null, '20150130100000000040', 'excel', '20150511100000000001', 'modules:sys:log:excel', 'modules:sys:log', '01');
INSERT INTO `t_sys_role_permission` VALUES ('20150912100000001740', '2015-09-12', '10:33:46', 'admin', null, null, null, '20150130100000000040', 'query', '20150511100000000001', 'modules:sys:log:query', 'modules:sys:log', '01');
INSERT INTO `t_sys_role_permission` VALUES ('20150912100000001741', '2015-09-12', '10:33:46', 'admin', null, null, null, '20150130100000000040', 'show', '20150604100000000003', 'modules:cms:show', 'modules:cms', '01');
INSERT INTO `t_sys_role_permission` VALUES ('20150912100000001742', '2015-09-12', '10:33:46', 'admin', null, null, null, '20150130100000000040', 'excel', '20150604100000000004', 'modules:cms:article:excel', 'modules:cms:article', '01');
INSERT INTO `t_sys_role_permission` VALUES ('20150912100000001743', '2015-09-12', '10:33:46', 'admin', null, null, null, '20150130100000000040', 'delete', '20150604100000000004', 'modules:cms:article:delete', 'modules:cms:article', '01');
INSERT INTO `t_sys_role_permission` VALUES ('20150912100000001744', '2015-09-12', '10:33:46', 'admin', null, null, null, '20150130100000000040', 'query', '20150604100000000004', 'modules:cms:article:query', 'modules:cms:article', '01');
INSERT INTO `t_sys_role_permission` VALUES ('20150912100000001745', '2015-09-12', '10:33:46', 'admin', null, null, null, '20150130100000000040', 'edit', '20150604100000000004', 'modules:cms:article:edit', 'modules:cms:article', '01');
INSERT INTO `t_sys_role_permission` VALUES ('20150912100000001746', '2015-09-12', '10:33:46', 'admin', null, null, null, '20150130100000000040', 'create', '20150604100000000004', 'modules:cms:article:create', 'modules:cms:article', '01');
INSERT INTO `t_sys_role_permission` VALUES ('20150912100000001747', '2015-09-12', '10:33:47', 'admin', null, null, null, '20150130100000000040', 'detail', '20150604100000000004', 'modules:cms:article:detail', 'modules:cms:article', '01');
INSERT INTO `t_sys_role_permission` VALUES ('20150912100000001748', '2015-09-12', '10:33:47', 'admin', null, null, null, '20150130100000000040', 'excel', '20150613100000000010', 'modules:century:aboutus:excel', 'modules:century:aboutus', '01');
INSERT INTO `t_sys_role_permission` VALUES ('20150912100000001749', '2015-09-12', '10:33:47', 'admin', null, null, null, '20150130100000000040', 'delete', '20150613100000000010', 'modules:century:aboutus:delete', 'modules:century:aboutus', '01');
INSERT INTO `t_sys_role_permission` VALUES ('20150912100000001750', '2015-09-12', '10:33:47', 'admin', null, null, null, '20150130100000000040', 'query', '20150613100000000010', 'modules:century:aboutus:query', 'modules:century:aboutus', '01');
INSERT INTO `t_sys_role_permission` VALUES ('20150912100000001751', '2015-09-12', '10:33:47', 'admin', null, null, null, '20150130100000000040', 'edit', '20150613100000000010', 'modules:century:aboutus:edit', 'modules:century:aboutus', '01');
INSERT INTO `t_sys_role_permission` VALUES ('20150912100000001752', '2015-09-12', '10:33:47', 'admin', null, null, null, '20150130100000000040', 'create', '20150613100000000010', 'modules:century:aboutus:create', 'modules:century:aboutus', '01');
INSERT INTO `t_sys_role_permission` VALUES ('20150912100000001753', '2015-09-12', '10:33:47', 'admin', null, null, null, '20150130100000000040', 'detail', '20150613100000000010', 'modules:century:aboutus:detail', 'modules:century:aboutus', '01');
INSERT INTO `t_sys_role_permission` VALUES ('20150912100000001754', '2015-09-12', '10:33:47', 'admin', null, null, null, '20150130100000000040', 'show', '20150709100000000015', 'modules:century:show', 'modules:century', '01');
INSERT INTO `t_sys_role_permission` VALUES ('20150912100000001755', '2015-09-12', '10:33:47', 'admin', null, null, null, '20150130100000000040', 'query', '20150709100000000016', 'modules:century:order:query', 'modules:century:order', '01');
INSERT INTO `t_sys_role_permission` VALUES ('20150912100000001756', '2015-09-12', '10:33:47', 'admin', null, null, null, '20150130100000000040', 'edit', '20150709100000000016', 'modules:century:order:edit', 'modules:century:order', '01');
INSERT INTO `t_sys_role_permission` VALUES ('20150912100000001757', '2015-09-12', '10:33:47', 'admin', null, null, null, '20150130100000000040', 'create', '20150709100000000016', 'modules:century:order:create', 'modules:century:order', '01');
INSERT INTO `t_sys_role_permission` VALUES ('20150912100000001758', '2015-09-12', '10:33:47', 'admin', null, null, null, '20150130100000000040', 'query', '20150912100000000017', 'modules:century:order:query', 'modules:century:order', '01');
INSERT INTO `t_sys_role_permission` VALUES ('20150912100000001759', '2015-09-12', '10:33:47', 'admin', null, null, null, '20150130100000000040', 'show', '20150912100000000017', 'modules:century:order:show', 'modules:century:order', '01');
INSERT INTO `t_sys_role_permission` VALUES ('20150912100000001760', '2015-09-12', '10:33:47', 'admin', null, null, null, '20150130100000000040', 'edit', '20150912100000000017', 'modules:century:order:edit', 'modules:century:order', '01');
INSERT INTO `t_sys_role_permission` VALUES ('20150912100000001761', '2015-09-12', '10:33:47', 'admin', null, null, null, '20150130100000000040', 'create', '20150912100000000017', 'modules:century:order:create', 'modules:century:order', '01');
INSERT INTO `t_sys_role_permission` VALUES ('20150912100000001762', '2015-09-12', '10:33:47', 'admin', null, null, null, '20150130100000000040', 'show', '20150708100000000013', 'modules:century:show', 'modules:century', '01');
INSERT INTO `t_sys_role_permission` VALUES ('20150912100000001763', '2015-09-12', '10:33:47', 'admin', null, null, null, '20150130100000000040', 'query', '20150708100000000014', 'modules:century:social:query', 'modules:century:social', '01');

-- ----------------------------
-- Table structure for `t_sys_schedule_job`
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_schedule_job`;
CREATE TABLE `t_sys_schedule_job` (
  `sys_id` varchar(255) NOT NULL,
  `create_date` varchar(255) DEFAULT NULL,
  `create_time` varchar(255) DEFAULT NULL,
  `upd_date` varchar(255) DEFAULT NULL,
  `upd_time` varchar(255) DEFAULT NULL,
  `upd_user` varchar(255) DEFAULT NULL,
  `bean_class` varchar(255) DEFAULT NULL,
  `cron_expression` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `is_concurrent` varchar(255) DEFAULT NULL,
  `job_group` varchar(255) DEFAULT NULL,
  `job_name` varchar(255) DEFAULT NULL,
  `job_status` varchar(255) DEFAULT NULL,
  `method_name` varchar(255) DEFAULT NULL,
  `spring_id` varchar(255) DEFAULT NULL,
  `create_user` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`sys_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_schedule_job
-- ----------------------------
INSERT INTO `t_sys_schedule_job` VALUES ('20150420100000000005', '2015-04-21', '10:00:05', '2015-05-14', '20:22:16', 'admin', '', '0 0/2 * * * ?', null, '0', 'Sys', 'ScheduleJobService', '0', 'test', 'sysScheduleJobService', null);

-- ----------------------------
-- Table structure for `t_sys_table`
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_table`;
CREATE TABLE `t_sys_table` (
  `sys_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` varchar(255) DEFAULT NULL,
  `create_time` varchar(255) DEFAULT NULL,
  `upd_date` varchar(255) DEFAULT NULL,
  `upd_time` varchar(255) DEFAULT NULL,
  `upd_user` varchar(255) DEFAULT NULL,
  `max_serial_num` varchar(255) DEFAULT NULL,
  `max_sys_id` varchar(20) DEFAULT NULL,
  `max_val` bigint(20) DEFAULT NULL,
  `min_val` bigint(20) DEFAULT NULL,
  `name` varchar(30) DEFAULT NULL,
  `pre_num` int(11) DEFAULT NULL,
  `serial_num` int(11) DEFAULT NULL,
  `table_name` varchar(40) DEFAULT NULL,
  `create_user` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`sys_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_table
-- ----------------------------
INSERT INTO `t_sys_table` VALUES ('1', null, null, '2015-04-23', '15:15:19', 'admin', '100000056114', '20150912100000056114', '999999999999', '100000000000', '系统日志表', '8', '12', 't_sys_log', null);
INSERT INTO `t_sys_table` VALUES ('2', '2015-04-19', '21:48:17', '2015-04-19', '21:48:17', 'admin', '100000000005', '20150420100000000001', '999999999999', '100000000000', '任务调度表', '8', '12', 't_sys_schedule_job', null);
INSERT INTO `t_sys_table` VALUES ('3', '2015-04-23', '00:03:48', '2015-04-23', '00:04:32', 'admin', '100000001763', '20150912100000001763', '999999999999', '100000000000', '角色权限表', '8', '12', 't_sys_role_permission', 'admin');
INSERT INTO `t_sys_table` VALUES ('4', '2015-04-23', '11:03:27', null, null, null, '100000000004', '20150708100000000004', '999999999999', '100000000000', '部门信息表', '8', '12', 't_sys_dept', 'admin');
INSERT INTO `t_sys_table` VALUES ('5', '2015-04-23', '15:13:01', null, null, null, '100000000017', '20150912100000000017', '999999999999', '100000000000', '菜单信息表', '8', '12', 't_sys_menu', 'admin');
INSERT INTO `t_sys_table` VALUES ('6', '2015-04-23', '15:14:19', null, null, null, '100000000275', '20150708100000000275', '99999999999', '100000000000', '图片信息表', '8', '12', 't_sys_picture', 'admin');
INSERT INTO `t_sys_table` VALUES ('7', '2015-04-23', '15:17:02', null, null, null, '100000000001', '20150423100000000001', '999999999999', '100000000000', '语系信息表', '8', '12', 't_sys_msg', 'admin');
INSERT INTO `t_sys_table` VALUES ('8', '2015-04-23', '15:18:24', null, null, null, '100000000397', '20150912100000000397', '999999999999', '100000000000', '登陆日志表', '8', '12', 't_sys_login_log', 'admin');
INSERT INTO `t_sys_table` VALUES ('9', '2015-04-23', '15:20:01', null, null, null, '100000000018', '20150427100000000018', '999999999999', '100000000000', '用户权限表', '8', '12', 't_sys_user_permission', 'admin');
INSERT INTO `t_sys_table` VALUES ('10', '2015-05-31', '23:57:56', '2015-06-14', '15:05:29', 'test', '100000000016', '20150703100000000016', '999999999999', '100000000001', '活动信息表', '8', '12', 't_sjt_activity', 'admin');
INSERT INTO `t_sys_table` VALUES ('11', '2015-05-31', '23:57:56', null, null, null, '100000000743', '20150708100000000743', '999999999999', '100000000001', '文件信息表', '8', '12', 't_sys_file', 'admin');
INSERT INTO `t_sys_table` VALUES ('12', '2015-06-04', '04:50:22', null, null, null, '100000000000', '10000000100000000000', '999999999999', '100000000001', '文章表', '8', '12', 't_cms_article', 'test');
INSERT INTO `t_sys_table` VALUES ('13', '2015-06-09', '20:34:09', null, null, null, '100000000289', '20150708100000000289', '999999999999', '100000000001', '用户信息表', '8', '12', 't_sys_user', 'admin');
INSERT INTO `t_sys_table` VALUES ('14', '2015-06-10', '18:48:27', null, null, null, '100000000014', '20150703100000000014', '999999999999', '100000000001', '活动报名表', '8', '12', 't_sjt_enroll', 'test');
INSERT INTO `t_sys_table` VALUES ('15', '2015-06-11', '16:19:01', null, null, null, '100000000112', '20150703100000000112', '999999999999', '100000000001', '好友关系表', '8', '12', 't_sjt_friend', 'admin');
INSERT INTO `t_sys_table` VALUES ('16', '2015-06-11', '16:19:28', null, null, null, '100000001310', '20150708100000001310', '999999999999', '100000000001', '消息记录表', '8', '12', 't_sjt_msg', 'admin');
INSERT INTO `t_sys_table` VALUES ('17', '2015-06-12', '10:37:48', null, null, null, '100000000038', '20150708100000000038', '999999999999', '100000000001', '新闻广播表', '8', '12', 't_sjt_newscast', 'test');
INSERT INTO `t_sys_table` VALUES ('18', '2015-07-01', '10:24:19', '2015-07-01', '10:24:51', 'admin', '100000000032', '20150708100000000032', '999999999999', '100000000001', '话题表', '8', '12', 't_sjt_topic', 'admin');
INSERT INTO `t_sys_table` VALUES ('19', '2015-07-01', '10:24:39', null, null, null, '100000000102', '20150708100000000102', '999999999999', '100000000001', '话题扩展表', '8', '12', 't_sjt_topic_ext', 'admin');
INSERT INTO `t_sys_table` VALUES ('20', '2015-07-09', '10:41:46', null, null, null, '100000000003', '20150709100000000003', '999999999999', '100000000001', '消费记录表', '8', '12', 't_sjt_consume', 'admin');

-- ----------------------------
-- Table structure for `t_sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_user`;
CREATE TABLE `t_sys_user` (
  `sys_id` varchar(255) NOT NULL,
  `create_date` varchar(255) DEFAULT NULL,
  `create_time` varchar(255) DEFAULT NULL,
  `upd_date` varchar(255) DEFAULT NULL,
  `upd_time` varchar(255) DEFAULT NULL,
  `upd_user` varchar(255) DEFAULT NULL,
  `birthday` varchar(10) DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `name` varchar(30) DEFAULT NULL,
  `native_addr` varchar(255) DEFAULT NULL,
  `create_user` varchar(20) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `super_admin` bit(1) DEFAULT NULL,
  `nick_name` varchar(50) DEFAULT NULL,
  `client_id` varchar(255) DEFAULT NULL,
  `lat_str` varchar(255) DEFAULT NULL,
  `lng_str` varchar(255) DEFAULT NULL,
  `avaliable_money` decimal(19,2) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `cultural` varchar(255) DEFAULT NULL,
  `have_child` varchar(255) DEFAULT NULL,
  `id_card` varchar(255) DEFAULT NULL,
  `marital` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`sys_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_user
-- ----------------------------
INSERT INTO `t_sys_user` VALUES ('admin', '2014-10-10', '10:10:25', '2015-06-22', '18:36:13', 'admin', '2015-10-10', 'O', '', 'admin', '福建厦门', null, '02', '', 'test', 'hahahahha', null, null, null, null, null, null, null, null);
INSERT INTO `t_sys_user` VALUES ('test', '2015-04-21', '09:41:44', null, null, null, '20150402', 'F', null, '测试', '福建厦门', null, '03', '', null, null, null, null, null, null, null, null, null, null);
INSERT INTO `t_sys_user` VALUES ('weihuangpeng', '2015-04-21', '09:41:44', null, null, null, '20150421', 'M', null, '18650036719', '福建泉州', null, null, '', null, null, null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for `t_sys_user_permission`
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_user_permission`;
CREATE TABLE `t_sys_user_permission` (
  `sys_id` varchar(255) NOT NULL,
  `create_date` varchar(255) DEFAULT NULL,
  `create_time` varchar(255) DEFAULT NULL,
  `upd_date` varchar(255) DEFAULT NULL,
  `upd_time` varchar(255) DEFAULT NULL,
  `upd_user` varchar(255) DEFAULT NULL,
  `dept_sys_id` varchar(255) NOT NULL,
  `role_id` varchar(255) NOT NULL,
  `user_sys_id` varchar(255) NOT NULL,
  `state` varchar(255) DEFAULT NULL,
  `create_user` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`sys_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_user_permission
-- ----------------------------
