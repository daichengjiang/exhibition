/*
Navicat MySQL Data Transfer

Source Server         : Test
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2017-04-13 11:09:48
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_classify
-- ----------------------------
DROP TABLE IF EXISTS `sys_classify`;
CREATE TABLE `sys_classify` (
  `classifyId` int(18) NOT NULL,
  `userId` int(18) NOT NULL,
  `classifyName` varchar(255) CHARACTER SET utf8 NOT NULL,
  `parentName` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `status` varchar(10) CHARACTER SET utf8 NOT NULL,
  `createTime` datetime NOT NULL,
  PRIMARY KEY (`classifyId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of sys_classify
-- ----------------------------

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `mnuId` int(11) NOT NULL AUTO_INCREMENT,
  `mnuPid` int(11) NOT NULL,
  `munName` varchar(255) NOT NULL,
  `mnuRemark` varchar(255) NOT NULL,
  `munIco` varchar(255) NOT NULL,
  `munUrl` varchar(255) NOT NULL,
  `munModule` int(11) NOT NULL,
  `munType` int(11) NOT NULL,
  PRIMARY KEY (`mnuId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '1', 'qwe', 'sjflwlelke', 'skjfklewlk', 'slflwekj', '1', '1');

-- ----------------------------
-- Table structure for sys_operatelog
-- ----------------------------
DROP TABLE IF EXISTS `sys_operatelog`;
CREATE TABLE `sys_operatelog` (
  `id` int(24) NOT NULL AUTO_INCREMENT COMMENT '操作日志ID',
  `operateType` varchar(64) CHARACTER SET utf8 NOT NULL COMMENT '操作日志类型',
  `operateContent` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '操作日志内容',
  `operateTime` datetime NOT NULL COMMENT '操作时间',
  `operateIP` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '操作IP',
  `operator` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '操作者',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of sys_operatelog
-- ----------------------------
INSERT INTO `sys_operatelog` VALUES ('26', '重置密码', '重置管理后台登录密码为【加密前：123456】-【加密后：e10adc3949ba59abbe56e057f20f883e】', '2017-04-13 09:39:19', '0:0:0:0:0:0:0:1', 'admin');

-- ----------------------------
-- Table structure for sys_picture
-- ----------------------------
DROP TABLE IF EXISTS `sys_picture`;
CREATE TABLE `sys_picture` (
  `picId` int(18) NOT NULL AUTO_INCREMENT,
  `classifyId` int(18) DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `type` int(1) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`picId`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of sys_picture
-- ----------------------------
INSERT INTO `sys_picture` VALUES ('1', '0', '', 'Chrysanthemum.jpg', '0', '2016-03-14 16:46:31');
INSERT INTO `sys_picture` VALUES ('3', '0', '', 'Desert.jpg', '0', '2016-03-14 16:46:56');
INSERT INTO `sys_picture` VALUES ('4', '0', '', 'Hydrangeas.jpg', '0', '2016-03-14 16:46:56');
INSERT INTO `sys_picture` VALUES ('5', '0', '', 'Jellyfish.jpg', '0', '2016-03-14 16:46:56');
INSERT INTO `sys_picture` VALUES ('6', '0', '', 'Koala.jpg', '0', '2016-03-14 16:46:57');
INSERT INTO `sys_picture` VALUES ('7', '0', '', 'Lighthouse.jpg', '0', '2016-03-14 16:46:57');
INSERT INTO `sys_picture` VALUES ('8', '0', '', 'Penguins.jpg', '0', '2016-03-14 16:46:57');
INSERT INTO `sys_picture` VALUES ('9', '0', '', 'Tulips.jpg', '0', '2016-03-14 16:46:57');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(255) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '密码',
  `phone` varchar(11) CHARACTER SET utf8 DEFAULT NULL COMMENT '手机号码',
  `email` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '电子邮箱',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `status` varchar(1) CHARACTER SET utf8 NOT NULL COMMENT '状态',
  `nickname` varchar(30) CHARACTER SET utf8 DEFAULT NULL COMMENT '昵称',
  `icon` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '头像',
  `lastIp` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '最后一次登录IP',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'daicj', '111111', '13501234567', '13501234567@163.com', '2016-02-22 17:09:52', '1', '一直很安静', '', '');
INSERT INTO `sys_user` VALUES ('2', 'admin', 'e10adc3949ba59abbe56e057f20f883e', '13501234560', '13501234560@163.com', '2016-02-22 17:11:05', '1', ' 十口心思，思君思国思社稷', '', '0:0:0:0:0:0:0:1');
