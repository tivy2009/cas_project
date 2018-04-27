/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50165
Source Host           : localhost:3306
Source Database       : pcloud

Target Server Type    : MYSQL
Target Server Version : 50165
File Encoding         : 65001

Date: 2018-02-28 14:09:25
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `machine`
-- ----------------------------
DROP TABLE IF EXISTS `machine`;
CREATE TABLE `machine` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `hostname` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=82 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of machine
-- ----------------------------
INSERT INTO `machine` VALUES ('16', 'hostname-Sun Feb 11 09:45:42 GMT+08:00 2018');
INSERT INTO `machine` VALUES ('17', 'hostname-Sun Feb 11 11:54:12 GMT+08:00 2018');
INSERT INTO `machine` VALUES ('18', 'hostname-Sun Feb 11 11:54:12 GMT+08:00 2018');
INSERT INTO `machine` VALUES ('19', 'hostname-Sun Feb 11 11:54:12 GMT+08:00 2018');
INSERT INTO `machine` VALUES ('20', 'hostname-Sun Feb 11 11:54:12 GMT+08:00 2018');
INSERT INTO `machine` VALUES ('21', 'hostname-Sun Feb 11 11:54:12 GMT+08:00 2018');
INSERT INTO `machine` VALUES ('22', 'hostname-Sun Feb 11 11:54:12 GMT+08:00 2018');
INSERT INTO `machine` VALUES ('23', 'hostname-Sun Feb 11 11:54:12 GMT+08:00 2018');
INSERT INTO `machine` VALUES ('24', 'hostname-Sun Feb 11 11:54:12 GMT+08:00 2018');
INSERT INTO `machine` VALUES ('25', 'hostname-Sun Feb 11 11:54:12 GMT+08:00 2018');
INSERT INTO `machine` VALUES ('26', 'hostname-Sun Feb 11 11:54:12 GMT+08:00 2018');
INSERT INTO `machine` VALUES ('28', 'Tue Feb 13 15:35:51 GMT+08:00 2018');
INSERT INTO `machine` VALUES ('29', 'Tue Feb 13 15:36:25 GMT+08:00 2018');
INSERT INTO `machine` VALUES ('31', 'Tue Feb 13 15:37:58 GMT+08:00 2018');
INSERT INTO `machine` VALUES ('32', 'Tue Feb 13 15:42:31 GMT+08:00 2018');
INSERT INTO `machine` VALUES ('35', 'Tue Feb 13 15:47:12 GMT+08:00 2018');
INSERT INTO `machine` VALUES ('40', 'Tue Feb 13 16:14:24 GMT+08:00 2018');
INSERT INTO `machine` VALUES ('41', '分布式事务');
INSERT INTO `machine` VALUES ('42', 'Tue Feb 13 16:15:39 GMT+08:00 2018');
INSERT INTO `machine` VALUES ('43', '分布式事务');
INSERT INTO `machine` VALUES ('48', '分布式事务');
INSERT INTO `machine` VALUES ('49', '分布式事务');
INSERT INTO `machine` VALUES ('53', '分布式事务');
INSERT INTO `machine` VALUES ('54', '分布式事务');
INSERT INTO `machine` VALUES ('56', '分布式事务');
INSERT INTO `machine` VALUES ('58', '分布式事务');
INSERT INTO `machine` VALUES ('60', '分布式事务');
INSERT INTO `machine` VALUES ('62', '分布式事务');
INSERT INTO `machine` VALUES ('67', 'Tue Feb 13 17:03:41 GMT+08:00 2018');
INSERT INTO `machine` VALUES ('68', 'Tue Feb 13 17:07:10 GMT+08:00 2018');
INSERT INTO `machine` VALUES ('69', '分布式事务');
INSERT INTO `machine` VALUES ('74', '分布式事务');
INSERT INTO `machine` VALUES ('75', '分布式事务');
INSERT INTO `machine` VALUES ('77', '分布式事务');
INSERT INTO `machine` VALUES ('78', '分布式事务');
INSERT INTO `machine` VALUES ('80', 'Tue Feb 13 17:43:13 GMT+08:00 2018');
INSERT INTO `machine` VALUES ('81', '分布式事务');
