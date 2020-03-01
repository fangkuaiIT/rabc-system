/*
Navicat MySQL Data Transfer

Source Server         : 腾讯云
Source Server Version : 80019
Source Host           : 129.211.76.96:3306
Source Database       : rabc-system

Target Server Type    : MYSQL
Target Server Version : 80019
File Encoding         : 65001

Date: 2020-03-01 21:55:44
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for imms_department
-- ----------------------------
DROP TABLE IF EXISTS `imms_department`;
CREATE TABLE `imms_department` (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `status` int NOT NULL,
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `version` bigint DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `note` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `pid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of imms_department
-- ----------------------------
INSERT INTO `imms_department` VALUES ('171031202691371008', 'admin', '2019-11-23 13:56:32', '0', 'admin', '2019-11-23 13:56:32', '0', '设计部', '设计专用的部门', '74043587333484544');
INSERT INTO `imms_department` VALUES ('71822581871505408', 'admin', '2019-02-22 11:36:53', '0', 'admin', '2019-02-22 11:36:53', '0', '设备部', '维修组', null);
INSERT INTO `imms_department` VALUES ('71822747999498240', 'admin', '2019-02-22 11:37:33', '0', 'admin', '2019-02-22 11:37:33', '0', '生产部', '报修组', null);
INSERT INTO `imms_department` VALUES ('74043587333484544', 'admin', '2019-02-28 22:42:22', '0', 'admin', '2019-06-13 14:17:20', '3', '生技部', '生技部', null);

-- ----------------------------
-- Table structure for imms_employee
-- ----------------------------
DROP TABLE IF EXISTS `imms_employee`;
CREATE TABLE `imms_employee` (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `status` int NOT NULL,
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `version` bigint DEFAULT NULL,
  `department_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `note` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of imms_employee
-- ----------------------------
INSERT INTO `imms_employee` VALUES ('170686513210564608', 'admin', '2019-11-22 15:06:51', '0', 'admin', '2019-11-22 15:06:51', '0', '71822581871505408', null, '支与', null, '1356488889888');
INSERT INTO `imms_employee` VALUES ('170686911619112960', 'admin', '2019-11-22 15:08:26', '0', 'admin', '2019-11-22 15:08:26', '0', '71822581871505408', null, 'linzi', null, '121212121');
INSERT INTO `imms_employee` VALUES ('170692539909513216', 'admin', '2019-11-22 15:30:48', '0', 'admin', '2019-11-22 15:30:48', '0', '74043587333484544', null, '林总', null, '12121212121');
INSERT INTO `imms_employee` VALUES ('71823399697870848', 'admin', '2019-02-22 11:40:08', '0', 'admin', '2019-03-05 11:58:21', '6', '71822581871505408', null, '李明', null, '15015112012');
INSERT INTO `imms_employee` VALUES ('71823553087762432', 'admin', '2019-02-22 11:40:45', '0', 'admin', '2019-03-05 11:57:23', '3', '71822581871505408', null, '刘建勇', null, '13824307574');
INSERT INTO `imms_employee` VALUES ('71824019569864704', 'admin', '2019-02-22 11:42:36', '0', 'yhx2', '2019-02-26 17:17:25', '2', '71822581871505408', null, '姚洲', null, null);
INSERT INTO `imms_employee` VALUES ('71824079875567616', 'admin', '2019-02-22 11:42:50', '0', 'admin', '2019-03-05 11:56:09', '3', '71822747999498240', null, '王旭', null, '18566152140');
INSERT INTO `imms_employee` VALUES ('71824137517887488', 'admin', '2019-02-22 11:43:04', '0', 'admin', '2019-03-05 11:55:43', '7', '71822747999498240', null, '林佰旭', null, '15916822770');
INSERT INTO `imms_employee` VALUES ('71824185832075264', 'admin', '2019-02-22 11:43:16', '0', 'admin', '2019-03-05 11:56:32', '5', '71822747999498240', null, '刘威', null, '18381161353');
INSERT INTO `imms_employee` VALUES ('71824223958298624', 'admin', '2019-02-22 11:43:25', '0', 'admin', '2019-03-05 11:56:51', '5', '71822747999498240', null, '张耸', null, '18218961986');
INSERT INTO `imms_employee` VALUES ('71865361318637568', 'admin', '2019-02-22 14:26:53', '0', 'admin', '2019-02-24 16:23:50', '7', '71822581871505408', null, '杨华轩', null, '15016850422');
INSERT INTO `imms_employee` VALUES ('72777920953724928', 'admin', '2019-02-25 02:53:04', '0', 'admin', '2019-02-25 02:53:04', '0', '71822581871505408', null, '杨紫轩', null, '15016850423');
INSERT INTO `imms_employee` VALUES ('74045676440809472', 'admin', '2019-02-28 22:50:40', '0', 'lifusheng', '2019-04-04 11:57:13', '4', '74043587333484544', null, '丘亿鹏', null, '');
INSERT INTO `imms_employee` VALUES ('74045762591813632', 'admin', '2019-02-28 22:51:01', '0', 'lifusheng', '2019-04-04 11:57:55', '3', '74043587333484544', null, '戈玉竹', null, '');
INSERT INTO `imms_employee` VALUES ('75690600743596032', 'admin', '2019-03-05 11:47:01', '1', 'admin', '2019-11-22 14:37:26', '3', '74043587333484544', null, '李复生', null, '');
INSERT INTO `imms_employee` VALUES ('76007847511617536', 'admin', '2019-03-06 08:47:38', '0', 'admin', '2019-11-22 14:37:06', '2', '71822747999498240', '123@qq.com', '杨英活', null, '188888888888');
INSERT INTO `imms_employee` VALUES ('76008009692770304', 'admin', '2019-03-06 08:48:17', '0', 'lifusheng', '2019-04-04 11:57:44', '1', '74043587333484544', null, '丘福', null, '');
INSERT INTO `imms_employee` VALUES ('76008375498993664', 'admin', '2019-03-06 08:49:44', '0', 'admin', '2019-03-06 09:08:16', '1', '71822747999498240', null, '生产人员01', null, '18056041000');
INSERT INTO `imms_employee` VALUES ('76008433221005312', 'admin', '2019-03-06 08:49:58', '0', 'admin', '2019-03-06 09:08:12', '1', '71822747999498240', null, '生产人员02', null, '18056041001');
INSERT INTO `imms_employee` VALUES ('76008492717207552', 'admin', '2019-03-06 08:50:12', '0', 'admin', '2019-03-06 09:08:07', '1', '71822747999498240', null, '生产人员03', null, '18056041003');
INSERT INTO `imms_employee` VALUES ('76008556227358720', 'admin', '2019-03-06 08:50:27', '0', 'admin', '2019-03-06 09:08:01', '1', '71822747999498240', null, '生产人员04', null, '18056041004');
INSERT INTO `imms_employee` VALUES ('76008608660353024', 'admin', '2019-03-06 08:50:40', '0', 'admin', '2019-03-06 09:07:53', '2', '71822747999498240', null, '生产人员05', null, '18056041005');
INSERT INTO `imms_employee` VALUES ('76018176400453632', 'admin', '2019-03-06 09:28:41', '0', 'admin', '2019-03-06 09:28:41', '0', '71822581871505408', null, '设备人员01', null, '18056042000');
INSERT INTO `imms_employee` VALUES ('76018245191233536', 'admin', '2019-03-06 09:28:57', '0', 'admin', '2019-03-06 09:29:05', '1', '71822581871505408', null, '设备人员02', null, '18056042002');
INSERT INTO `imms_employee` VALUES ('76018350464069632', 'admin', '2019-03-06 09:29:23', '0', 'admin', '2019-03-06 09:29:23', '0', '71822581871505408', null, '设备人员03', null, '18056042003');
INSERT INTO `imms_employee` VALUES ('76018411986120704', 'admin', '2019-03-06 09:29:37', '0', 'admin', '2019-03-06 09:29:37', '0', '71822581871505408', null, '设备人员04', null, '18056042004');
INSERT INTO `imms_employee` VALUES ('76018493292703744', 'admin', '2019-03-06 09:29:57', '0', 'admin', '2019-03-06 09:29:57', '0', '71822581871505408', null, '设备人员05', null, '18056042005');
INSERT INTO `imms_employee` VALUES ('76018821815758848', 'admin', '2019-03-06 09:31:15', '0', 'lifusheng', '2019-04-04 11:57:36', '1', '74043587333484544', null, '生计人员01', null, '');
INSERT INTO `imms_employee` VALUES ('76018925532508160', 'admin', '2019-03-06 09:31:40', '0', 'lifusheng', '2019-04-04 11:57:32', '1', '74043587333484544', null, '生计人员02', null, '');
INSERT INTO `imms_employee` VALUES ('76019160774242304', 'admin', '2019-03-06 09:32:36', '0', 'lifusheng', '2019-04-04 11:57:29', '1', '74043587333484544', null, '生计人员03', null, '');
INSERT INTO `imms_employee` VALUES ('76019229934120960', 'admin', '2019-03-06 09:32:52', '0', 'lifusheng', '2019-04-04 11:57:25', '1', '74043587333484544', null, '生计人员04', null, '');
INSERT INTO `imms_employee` VALUES ('76019279988944896', 'admin', '2019-03-06 09:33:04', '0', 'lifusheng', '2019-04-04 11:57:21', '1', '74043587333484544', null, '生计人员05', null, '');
INSERT INTO `imms_employee` VALUES ('86567751930576896', 'lifusheng', '2019-04-04 12:08:56', '0', 'lifusheng', '2019-04-04 12:08:56', '0', '74043587333484544', null, '肖仲春', null, '18888888888');
INSERT INTO `imms_employee` VALUES ('86567836802318336', 'lifusheng', '2019-04-04 12:09:16', '0', 'admin', '2019-06-26 15:00:31', '2', '74043587333484544', null, '王杰', null, '18888888888');
INSERT INTO `imms_employee` VALUES ('86567914656989184', 'lifusheng', '2019-04-04 12:09:35', '0', 'lifusheng', '2019-04-04 12:09:37', '1', '74043587333484544', null, '吴祖双', null, '18888888888');
INSERT INTO `imms_employee` VALUES ('86568003131637760', 'lifusheng', '2019-04-04 12:09:56', '0', 'lifusheng', '2019-04-04 12:09:57', '1', '74043587333484544', null, '岩永东', null, '18888888888');
INSERT INTO `imms_employee` VALUES ('86568070894813184', 'lifusheng', '2019-04-04 12:10:12', '0', 'lifusheng', '2019-04-04 12:10:12', '0', '74043587333484544', null, '曾浇飞', null, '18888888888');
INSERT INTO `imms_employee` VALUES ('86568128566493184', 'lifusheng', '2019-04-04 12:10:26', '0', 'lifusheng', '2019-04-04 12:10:26', '0', '74043587333484544', null, '辜卓鹏', null, '18888888888');
INSERT INTO `imms_employee` VALUES ('86568180735246336', 'lifusheng', '2019-04-04 12:10:38', '0', 'lifusheng', '2019-04-04 12:10:38', '0', '74043587333484544', null, '杨家荣', null, '18888888888');
INSERT INTO `imms_employee` VALUES ('86568277531394048', 'lifusheng', '2019-04-04 12:11:01', '0', 'lifusheng', '2019-04-04 12:11:01', '0', '74043587333484544', null, '李建东', null, '18888888888');
INSERT INTO `imms_employee` VALUES ('86568342278864896', 'lifusheng', '2019-04-04 12:11:17', '0', 'lifusheng', '2019-04-04 12:11:17', '0', '74043587333484544', null, '丘亿鹏', null, '18888888888');
INSERT INTO `imms_employee` VALUES ('86568410436304896', 'lifusheng', '2019-04-04 12:11:33', '0', 'lifusheng', '2019-04-04 12:11:33', '0', '74043587333484544', null, '宁华', null, '18888888888');
INSERT INTO `imms_employee` VALUES ('86568453100765184', 'lifusheng', '2019-04-04 12:11:43', '0', 'lifusheng', '2019-04-04 12:11:43', '0', '74043587333484544', null, '宁超', null, '18888888888');
INSERT INTO `imms_employee` VALUES ('86568529193828352', 'lifusheng', '2019-04-04 12:12:01', '0', 'lifusheng', '2019-04-04 12:12:01', '0', '74043587333484544', null, '黎建阅', null, '18888888888');
INSERT INTO `imms_employee` VALUES ('86568589679886336', 'lifusheng', '2019-04-04 12:12:16', '1', 'admin', '2019-11-22 14:23:12', '3', '74043587333484544', null, '尹业文', null, '18888888888');
INSERT INTO `imms_employee` VALUES ('87262629090000896', 'lifusheng', '2019-04-06 10:10:07', '0', 'admin', '2019-10-11 15:52:29', '2', '74043587333484544', null, '刘少辉', null, '18888888888');

-- ----------------------------
-- Table structure for imms_resource
-- ----------------------------
DROP TABLE IF EXISTS `imms_resource`;
CREATE TABLE `imms_resource` (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `status` int NOT NULL,
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `version` bigint DEFAULT NULL,
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `pid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `resource_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `component` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `enabled` tinyint(1) DEFAULT NULL,
  `icon_cls` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `keep_alive` bit(1) DEFAULT NULL,
  `require_auth` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of imms_resource
-- ----------------------------
INSERT INTO `imms_resource` VALUES ('73668539561922561', 'admin', '2020-02-25 13:00:28', '1', 'admin', '2020-02-25 13:00:28', '0', null, '/', null, null, '所有', '1', null, '1', null, '\0', '\0');
INSERT INTO `imms_resource` VALUES ('73678539561922560', 'admin', '2019-02-27 22:31:48', '0', 'admin', '2019-02-27 22:47:27', '2', '/home', '/', '73678539561922560', null, '部门管理', '1', 'Home', '1', null, '', '');
INSERT INTO `imms_resource` VALUES ('73683780244107264', 'admin', '2019-02-27 22:52:38', '0', 'admin', '2019-02-27 22:52:38', '0', '/home', '/', '73683780244107264', null, '基础资料', '1', 'Home', '1', null, '', '');
INSERT INTO `imms_resource` VALUES ('73684274618331136', 'admin', '2019-02-27 22:54:35', '0', 'admin', '2019-02-27 22:54:50', '1', '/employee', '/employee/**', '73683780244107264', null, '员工资料', '2', 'Employee', '1', null, '', '');
INSERT INTO `imms_resource` VALUES ('73684498409615360', 'admin', '2019-02-27 22:55:29', '0', 'admin', '2019-02-27 22:56:21', '1', '/department', '/department/**', '73678539561922560', null, '部门资料', '2', 'Department', '1', null, '', '');
INSERT INTO `imms_resource` VALUES ('73689704216162304', 'admin', '2019-02-27 23:16:10', '0', 'admin', '2019-02-27 23:16:10', '0', '/factory', '/resource/sys/menu', '73687457646276608', null, '查看', '2', null, null, null, null, null);

-- ----------------------------
-- Table structure for imms_role
-- ----------------------------
DROP TABLE IF EXISTS `imms_role`;
CREATE TABLE `imms_role` (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `status` int NOT NULL,
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `version` bigint DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `role_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `name_zh` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of imms_role
-- ----------------------------
INSERT INTO `imms_role` VALUES ('71824369345458176', 'admin', '2019-02-22 11:43:59', '0', 'admin', '2019-03-12 22:40:21', '3', null, 'ROLE_ADMIN', '管理员');
INSERT INTO `imms_role` VALUES ('71824615131672576', 'admin', '2019-02-22 11:44:58', '0', 'admin', '2019-11-20 09:06:19', '6', null, '????', null);
INSERT INTO `imms_role` VALUES ('71824700921966592', 'admin', '2019-02-22 11:45:19', '0', 'admin', '2019-03-12 22:42:44', '2', null, 'ROLE_TECH', '技术员');
INSERT INTO `imms_role` VALUES ('74050486070177792', 'admin', '2019-02-28 23:09:47', '0', 'admin', '2019-08-23 09:46:32', '7', null, 'ROLE_TEST', '测试员');
INSERT INTO `imms_role` VALUES ('95624844423434240', 'admin', '2019-04-29 11:58:35', '9999', 'admin', '2019-04-29 13:57:43', '2', null, 'ROLE_TEST', '测试员');

-- ----------------------------
-- Table structure for imms_t_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `imms_t_role_resource`;
CREATE TABLE `imms_t_role_resource` (
  `role_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `resource_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`role_id`,`resource_id`) USING BTREE,
  KEY `FKa3029hx8186ifsf13dvfm1g4s` (`resource_id`) USING BTREE,
  CONSTRAINT `FK5ldu49jwsxptyeuf0dlqs8c9c` FOREIGN KEY (`role_id`) REFERENCES `imms_role` (`id`),
  CONSTRAINT `FKa3029hx8186ifsf13dvfm1g4s` FOREIGN KEY (`resource_id`) REFERENCES `imms_resource` (`id`),
  CONSTRAINT `imms_t_role_resource_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `imms_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `imms_t_role_resource_ibfk_2` FOREIGN KEY (`resource_id`) REFERENCES `imms_resource` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of imms_t_role_resource
-- ----------------------------
INSERT INTO `imms_t_role_resource` VALUES ('71824369345458176', '73678539561922560');
INSERT INTO `imms_t_role_resource` VALUES ('71824700921966592', '73678539561922560');
INSERT INTO `imms_t_role_resource` VALUES ('71824369345458176', '73683780244107264');
INSERT INTO `imms_t_role_resource` VALUES ('71824369345458176', '73684274618331136');
INSERT INTO `imms_t_role_resource` VALUES ('71824700921966592', '73684498409615360');
INSERT INTO `imms_t_role_resource` VALUES ('71824369345458176', '73689704216162304');
INSERT INTO `imms_t_role_resource` VALUES ('71824700921966592', '73689704216162304');

-- ----------------------------
-- Table structure for imms_t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `imms_t_user_role`;
CREATE TABLE `imms_t_user_role` (
  `user_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `role_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`) USING BTREE,
  KEY `FKgkg1sgsgwly137a52u2nqxxt7` (`role_id`) USING BTREE,
  CONSTRAINT `FKgkg1sgsgwly137a52u2nqxxt7` FOREIGN KEY (`role_id`) REFERENCES `imms_role` (`id`),
  CONSTRAINT `FKhjlj33p9gvxr92vmdtof6d6xk` FOREIGN KEY (`user_id`) REFERENCES `imms_user` (`id`),
  CONSTRAINT `imms_t_user_role_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `imms_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `imms_t_user_role_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `imms_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of imms_t_user_role
-- ----------------------------
INSERT INTO `imms_t_user_role` VALUES ('71774496830300160', '71824369345458176');
INSERT INTO `imms_t_user_role` VALUES ('71825484707360768', '71824700921966592');

-- ----------------------------
-- Table structure for imms_user
-- ----------------------------
DROP TABLE IF EXISTS `imms_user`;
CREATE TABLE `imms_user` (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `status` int NOT NULL,
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `version` bigint DEFAULT NULL,
  `employee_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `last_password_reset` datetime DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of imms_user
-- ----------------------------
INSERT INTO `imms_user` VALUES ('169865050567462912', 'admin', '2019-11-20 08:42:39', '0', 'admin', '2019-11-20 08:42:39', '0', null, null, '$2a$10$KtKBKNWXwokUL2y9H6uV4O7JXYgbNsQzS3dgWhK/rzkQohbMLGo6K', 'zhangyugeng');
INSERT INTO `imms_user` VALUES ('71774496830300160', 'anonymousUser', '2019-02-22 08:25:49', '0', 'admin', '2019-02-22 11:51:14', '2', null, null, '$2a$10$yzb.0FlvXLvGqG6lRpxhnOSjRxv4ooMdHAmO9nNnZkO1pPhMEwo2.', 'admin');
INSERT INTO `imms_user` VALUES ('71825484707360768', 'admin', '2019-02-22 11:48:25', '0', 'admin', '2019-02-22 11:48:25', '0', '71823399697870848', null, '$2a$10$yzb.0FlvXLvGqG6lRpxhnOSjRxv4ooMdHAmO9nNnZkO1pPhMEwo2.', 'liming');
INSERT INTO `imms_user` VALUES ('71825576310960128', 'admin', '2019-02-22 11:48:47', '0', 'admin', '2019-02-22 11:50:26', '1', '71823553087762432', null, '$2a$10$gZc8G9uk5MeN56c8LNcTcu4FksKdehFkpbQJ9KVyP.iALxPldG8CW', 'liujianyong');
INSERT INTO `imms_user` VALUES ('71825679255957504', 'admin', '2019-02-22 11:49:12', '0', 'admin', '2019-02-22 11:50:35', '1', '71824019569864704', null, '$2a$10$pH0G90zkvg6b1.Vro/pAIufgzI1qe3nVxqc4XzQtB14dpTC/dq3qu', 'yaozhou');
INSERT INTO `imms_user` VALUES ('71825759941783552', 'admin', '2019-02-22 11:49:31', '0', 'admin', '2019-02-22 11:50:47', '1', '71824079875567616', null, '$2a$10$KHXGBo6jG780zx4L4z5wJOb/aNn5ws0oeMxRLQVteGzSwzmlMKx6m', 'wangxu');
INSERT INTO `imms_user` VALUES ('71825880192479232', 'admin', '2019-02-22 11:50:00', '0', 'admin', '2019-02-22 11:50:57', '1', '71824185832075264', null, '$2a$10$H4Fp.8wM92kHX/1mS0lW0.MOEDRnIRV4v75ZZltJyay7xzR.gX7Wm', 'liuwei');
INSERT INTO `imms_user` VALUES ('71825939525103616', 'admin', '2019-02-22 11:50:14', '0', 'admin', '2019-02-22 11:51:02', '1', '71824223958298624', null, '$2a$10$xgSefV9h.o2G04PGmaOlV.lWFxVbEsS7lmEgxtg.1bo2yTexwE.cu', 'zhangsong');
INSERT INTO `imms_user` VALUES ('71865040395661312', 'admin', '2019-02-22 14:25:36', '0', 'yhx', '2019-02-22 14:52:38', '3', '71865361318637568', null, '$2a$10$cKO9usNrmo0MRxiQI7vdFeoKA60WhbysTJ7axx2UHrgbzjGQ6PPzm', 'yhx');
INSERT INTO `imms_user` VALUES ('72776981421240320', 'admin', '2019-02-25 02:49:20', '0', 'admin', '2019-02-25 02:54:54', '1', '72777920953724928', null, '$2a$10$pMTYJVpGzjxhBU4XgIwxAOi1TDezAmxfMmEpO6mmRRvNbFd7K3gMG', 'yhx2');
INSERT INTO `imms_user` VALUES ('74046023100035072', 'admin', '2019-02-28 22:52:03', '1', 'lifusheng', '2019-04-04 12:14:28', '2', '74045676440809472', null, '$2a$10$zYGPSmTdt7D0rhNIPCkVVOK4RJd1P0MJn0i0f6d3.qCYURUEXj9Qe', 'zengzipiao');
INSERT INTO `imms_user` VALUES ('74046122907693056', 'admin', '2019-02-28 22:52:27', '0', 'admin', '2019-03-06 08:53:11', '1', '74045762591813632', null, '$2a$10$PyydIf91w9tu8QYVd5jdGOMGHTNWUi7OeyIzx/S5lxz9P4A2GMonC', 'geyuzhu');
INSERT INTO `imms_user` VALUES ('74050355727986688', 'admin', '2019-02-28 23:09:16', '0', 'admin', '2019-02-28 23:09:16', '0', '71824137517887488', null, '$2a$10$PMyFGok5aXmneMf7iJowm.DnGKqyarjuGjBfX3Ddb30CV96jzZsgG', 'linbaixu');
INSERT INTO `imms_user` VALUES ('75690897096339456', 'admin', '2019-03-05 11:48:12', '0', 'admin', '2019-03-06 08:53:01', '1', '75690600743596032', null, '$2a$10$PtkbsjmF7TYO5SHsfS4MvuEQL3qXC407AHIEDOtUNsQ2kd66bTsXW', 'lifusheng');
INSERT INTO `imms_user` VALUES ('76009517129822208', 'admin', '2019-03-06 08:54:16', '0', 'admin', '2019-03-06 08:54:16', '0', '76007847511617536', null, '$2a$10$qrQ/cV61hVhXLTVveBUhhOXByedR4hWyYykY2.ijQMBWbyCvdUaTm', 'yangyinghuo');
INSERT INTO `imms_user` VALUES ('76009617738592256', 'admin', '2019-03-06 08:54:40', '0', 'admin', '2019-03-06 08:54:48', '1', '76008009692770304', null, '$2a$10$SUjMECAMzmwClwSC.i/DCuUVePKPpKCyD38bsnl3nmrZ5JDrxyqI6', 'qiufu');
INSERT INTO `imms_user` VALUES ('76013991705604096', 'admin', '2019-03-06 09:12:03', '0', 'admin', '2019-03-06 09:12:03', '0', '76008375498993664', null, '$2a$10$8e1jrR65o/a4j2n.J3kcQ.PYuxtmM9xU7JTx3m7ctAsyV/t126yZy', 'sc0001');
INSERT INTO `imms_user` VALUES ('76014053286375424', 'admin', '2019-03-06 09:12:18', '0', 'admin', '2019-03-06 09:13:26', '1', '76008433221005312', null, '$2a$10$MtEW1yuxlglIfE1HLH9e8.8V85ZtM6gpAMrjR4D0oR5chfzfQYO5e', 'sc0002');
INSERT INTO `imms_user` VALUES ('76014106856026112', 'admin', '2019-03-06 09:12:31', '0', 'admin', '2019-03-06 09:13:21', '1', '76008492717207552', null, '$2a$10$88L/3/m.pDekfY/C6VWdQ.LkUlwZO/8A0i4oNcrzzB.Ftu1XHK0Te', 'sc0003');
INSERT INTO `imms_user` VALUES ('76014155166019584', 'admin', '2019-03-06 09:12:42', '0', 'admin', '2019-03-06 09:13:15', '1', '76008556227358720', null, '$2a$10$CjVw34BvzRb3/8QVmawlpeCUk8s9IEoxOXxLArwm2.0rf5tXdE.X2', 'sc0004');
INSERT INTO `imms_user` VALUES ('76014227647787008', 'admin', '2019-03-06 09:13:00', '0', 'admin', '2019-03-06 09:13:10', '1', '76008608660353024', null, '$2a$10$MQGSRP95BWpdfUqnBglPfOsK/zNr.WaYjBVdd9zbgNvumUlA2edIy', 'sc0005');
INSERT INTO `imms_user` VALUES ('76019446641225728', 'admin', '2019-03-06 09:33:44', '0', 'admin', '2019-03-06 09:33:44', '0', '76018176400453632', null, '$2a$10$AtyIR0OT2TjqrltZzQY5U.Mh2baRMV2Lk74h7jmMlVVRWM15iGWpG', 'sb0001');
INSERT INTO `imms_user` VALUES ('76019499556564992', 'admin', '2019-03-06 09:33:56', '0', 'admin', '2019-03-06 09:34:52', '1', '76018245191233536', null, '$2a$10$f0..vR.s3ISpW73YQqY.Eut3X5z4ooVaBASu36VKqXdnu5ebzbNzW', 'sb0002');
INSERT INTO `imms_user` VALUES ('76019546524381184', 'admin', '2019-03-06 09:34:08', '0', 'admin', '2019-03-06 09:34:57', '1', '76018350464069632', null, '$2a$10$Bws0lZQxVmKpqLGKNTmagODlbRRLS.MvxveLCEQf5eOcZcevbvQmm', 'sb0003');
INSERT INTO `imms_user` VALUES ('76019595987808256', 'admin', '2019-03-06 09:34:19', '0', 'admin', '2019-03-06 09:35:03', '1', '76018411986120704', null, '$2a$10$VyEx.WsZ00poaO2aH8Edduhd7jerKibxK51GnXdCoVX/9Q.GvCI7W', 'sb0004');
INSERT INTO `imms_user` VALUES ('76019654011809792', 'admin', '2019-03-06 09:34:33', '0', 'admin', '2019-03-06 09:35:09', '1', '76018493292703744', null, '$2a$10$AYNbmLlj1Lbv5DWw/UTGb.LISd8dOi4mAWXrWLiWrSoGTJjnrLFDe', 'sb0005');
INSERT INTO `imms_user` VALUES ('76019919695802368', 'admin', '2019-03-06 09:35:37', '0', 'admin', '2019-03-06 09:37:58', '1', '76018821815758848', null, '$2a$10$nmjiWOE9ZfggIL8297/aJec5Ik39m4i.bon.LyZ1d5eI/jQeXqMB.', 'sj0001');
INSERT INTO `imms_user` VALUES ('76020121609596928', 'admin', '2019-03-06 09:36:25', '0', 'admin', '2019-03-06 09:36:25', '0', '76018925532508160', null, '$2a$10$j3381IZ.Okftg75Ar3RaB.1NUjudLXqZcNSNOaYS6O1Zu/52sveFK', 'sj0002');
INSERT INTO `imms_user` VALUES ('76020373209116672', 'admin', '2019-03-06 09:37:25', '0', 'admin', '2019-03-06 09:38:06', '1', '76019160774242304', null, '$2a$10$cNpflV1UCX5xJjkat1sn5.r6xDtTqnrswMT94SWTQpt.lAmJER9vG', 'sj0003');
INSERT INTO `imms_user` VALUES ('76020427324026880', 'admin', '2019-03-06 09:37:38', '0', 'admin', '2019-03-06 09:38:13', '1', '76019229934120960', null, '$2a$10$A3FMO3HRS26EBu135gzx6u6BjfWewGH08SMEwtLlRJR1PNtDY09sW', 'sj0004');
INSERT INTO `imms_user` VALUES ('76020481657040896', 'admin', '2019-03-06 09:37:51', '0', 'admin', '2019-03-06 09:38:18', '1', '76019279988944896', null, '$2a$10$P1K/neGxglTQT3xU6PW6aOLKMxqwGAxNtpE34lsSFY.1htnLnZdS2', 'sj0005');
INSERT INTO `imms_user` VALUES ('86566171466498048', 'lifusheng', '2019-04-04 12:02:39', '0', 'lifusheng', '2019-04-04 12:13:51', '1', '86568342278864896', null, '$2a$10$4t/FEx/WNA.X75RbbRLvhuNlkULmWHJEOwnGTYIp6SC8txF7jmsqe', 'qiuyipeng');
INSERT INTO `imms_user` VALUES ('86566395731738624', 'lifusheng', '2019-04-04 12:03:32', '0', 'lifusheng', '2019-04-04 12:13:59', '1', '86567751930576896', null, '$2a$10$0Vp5qQxXy8Pj/PANiVHH7OU3i5DSgAwK9Jr0z4G1mlefjfgOEh6Ze', 'xiaozhongchun');
INSERT INTO `imms_user` VALUES ('86566483480772608', 'lifusheng', '2019-04-04 12:03:53', '0', 'lifusheng', '2019-04-04 12:13:46', '1', '86567836802318336', null, '$2a$10$z0NBj49nPSj5sfbniw86neJ0R./UvpSVMHKi0KfKPwl7hbUpY4xdW', 'wangjie');
INSERT INTO `imms_user` VALUES ('86566627794190336', 'lifusheng', '2019-04-04 12:04:28', '0', 'lifusheng', '2019-04-04 12:13:36', '1', '86567914656989184', null, '$2a$10$g//h0tsLCWXXiOzx/xigJ.tjIRzKqX/eDJKW7CZALGJyW0rgvy9LK', 'wuzushuang');
INSERT INTO `imms_user` VALUES ('86566711416029184', 'lifusheng', '2019-04-04 12:04:48', '0', 'lifusheng', '2019-04-04 12:13:27', '1', '86568277531394048', null, '$2a$10$6r00CZctgUh7SFNXTu5ykOwyLp4uUrUaXrfzwmugMdl.mwjCP0hwK', 'lijiandong');
INSERT INTO `imms_user` VALUES ('86566791569178624', 'lifusheng', '2019-04-04 12:05:07', '0', 'lifusheng', '2019-04-04 12:13:16', '1', '86568003131637760', null, '$2a$10$J8Zi5En20bwexunqoCGNUOiYhS7vTw8s4SdBDeFEzEnorNYLQi.Y2', 'yanyongdong');
INSERT INTO `imms_user` VALUES ('86566884628201472', 'lifusheng', '2019-04-04 12:05:29', '0', 'lifusheng', '2019-04-04 12:13:09', '1', '86568180735246336', null, '$2a$10$7uFN5Gy5PPbRSJEy4/q.YualKlnoALGdSlRLAY8lhGDW2NzCRUos.', 'yangjiarong');
INSERT INTO `imms_user` VALUES ('86566962289934336', 'lifusheng', '2019-04-04 12:05:48', '0', 'lifusheng', '2019-04-04 12:13:01', '1', '86568070894813184', null, '$2a$10$tCz5W2xBH2qLVAci8Wr9B.kK/orR0t6/BinFPmyiKHCDaa9MoxTzq', 'zengjiaofei');
INSERT INTO `imms_user` VALUES ('86567029793062912', 'lifusheng', '2019-04-04 12:06:04', '0', 'lifusheng', '2019-04-04 12:12:55', '1', '86568128566493184', null, '$2a$10$LMnF.bMWHtnc1SiC/NUPQuLuYPyH2V5N.fPKiCk/VIGNuwRSy5b3y', 'guzhuopeng');
INSERT INTO `imms_user` VALUES ('86567140610768896', 'lifusheng', '2019-04-04 12:06:30', '0', 'lifusheng', '2019-04-04 12:12:48', '1', '86568410436304896', null, '$2a$10$LcmxmWE8q//rutKmCqNxZeltHaN6ZOhDz1TSlsmeUYzj8aSTW9oLS', 'ninghua');
INSERT INTO `imms_user` VALUES ('86567385453264896', 'lifusheng', '2019-04-04 12:07:28', '0', 'lifusheng', '2019-04-04 12:12:43', '1', '86568453100765184', null, '$2a$10$xzPs19sH18typlHxxKr6/uMU4qnLzbZ7teCYg81EPpP0TNaFWqXPu', 'ningchao');
INSERT INTO `imms_user` VALUES ('86567477467906048', 'lifusheng', '2019-04-04 12:07:50', '0', 'lifusheng', '2019-04-04 12:12:37', '1', '86568529193828352', null, '$2a$10$sjXDOSIbBuYiYCwi3ZDHHuTn8lGjIp.Opuau437CNja46e6cgBRGa', 'lijianyue');
INSERT INTO `imms_user` VALUES ('86567544706793472', 'lifusheng', '2019-04-04 12:08:06', '0', 'lifusheng', '2019-04-04 12:12:31', '1', '86568589679886336', null, '$2a$10$9H/F3n/8NfNGQP.CRCQ/xOp5eV.hGIn6vlerEjthVvjkVlN/axsIC', 'yinyewen');
INSERT INTO `imms_user` VALUES ('87262486156509184', 'lifusheng', '2019-04-06 10:09:33', '0', 'lifusheng', '2019-04-06 10:10:33', '1', '87262629090000896', null, '$2a$10$FuWfMBHRMD/4V5lQG4ZrGOffTTBCjDJHzkxTA5hP6j0dVzYYcKzWC', 'liushaohui');
