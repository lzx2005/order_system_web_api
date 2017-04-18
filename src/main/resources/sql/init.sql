/*
 Navicat Premium Backup

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : localhost
 Source Database       : order_system

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : utf-8

 Date: 04/18/2017 14:11:22 PM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `dish`
-- ----------------------------
DROP TABLE IF EXISTS `dish`;
CREATE TABLE `dish` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) NOT NULL COMMENT '菜品名称',
  `price` decimal(7,2) unsigned NOT NULL DEFAULT '0.00' COMMENT '单价',
  `belong` int(11) unsigned NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `logo` bigint(20) NOT NULL COMMENT '封面图片',
  `type` bigint(20) unsigned NOT NULL COMMENT '菜品类型',
  `image` bigint(20) DEFAULT NULL,
  `dish_type_type_id` int(11) DEFAULT NULL,
  `type_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `dish`
-- ----------------------------
BEGIN;
INSERT INTO `dish` VALUES ('1', '红烧里脊', '15.70', '1', '2017-03-06 15:09:26', '2', '3', null, null, null), ('2', '红烧里脊', '15.70', '1', '2017-03-06 15:11:38', '2', '3', null, null, null), ('3', '青椒炒蛋', '12.90', '1', '2017-03-06 15:12:41', '2', '3', null, null, null), ('4', '青椒炒蛋', '12.90', '1', '2017-03-06 15:13:45', '2', '3', null, null, null), ('5', '左宗棠鸡', '28.90', '2', '2017-04-07 16:05:55', '1', '2', null, null, null), ('6', '左宗棠鸡', '28.90', '2', '2017-04-07 16:06:58', '1', '2', null, null, null), ('7', '左宗棠鸡', '28.90', '2', '2017-04-07 16:08:47', '1', '5', null, null, null), ('8', '左宗棠鸡', '28.90', '2', '2017-04-07 16:08:50', '1', '6', null, null, null), ('9', '左宗棠鸡', '28.90', '2', '2017-04-07 16:08:50', '1', '11', null, null, null), ('10', '左宗棠鸡', '28.90', '2', '2017-04-07 16:08:51', '1', '7', null, null, null), ('11', '左宗棠鸡', '28.90', '2', '2017-04-07 16:08:51', '1', '2', null, null, null), ('12', '红烧排骨', '11.00', '2', '2017-04-07 16:09:15', '1', '2', null, null, null), ('13', '红烧排骨', '11.00', '2', '2017-04-10 10:39:48', '1', '2', null, null, null), ('14', '红烧排骨', '11.00', '2', '2017-04-18 11:34:38', '1', '2', null, null, null), ('15', '红烧排骨', '11.00', '2', '2017-04-18 11:34:40', '1', '2', null, null, null), ('16', '红烧排骨', '11.00', '2', '2017-04-18 11:34:40', '1', '2', null, null, null), ('17', '红烧排骨', '11.00', '2', '2017-04-18 11:34:41', '1', '2', null, null, null), ('18', '红烧排骨', '11.00', '2', '2017-04-18 11:34:41', '1', '2', null, null, null), ('19', '红烧排骨', '11.00', '2', '2017-04-18 11:34:41', '1', '2', null, null, null), ('20', '红烧排骨', '11.00', '2', '2017-04-18 11:34:41', '1', '2', null, null, null), ('21', '红烧排骨', '11.00', '2', '2017-04-18 11:34:41', '1', '2', null, null, null), ('22', '红烧排骨', '11.00', '2', '2017-04-18 11:34:42', '1', '2', null, null, null), ('23', '红烧排骨', '11.00', '2', '2017-04-18 11:34:42', '1', '2', null, null, null), ('24', '红烧排骨', '11.00', '2', '2017-04-18 11:34:42', '1', '2', null, null, null), ('25', '红烧排骨', '11.00', '2', '2017-04-18 11:34:42', '1', '2', null, null, null), ('26', '红烧排骨', '11.00', '2', '2017-04-18 11:34:42', '1', '2', null, null, null), ('27', '红烧排骨', '11.00', '2', '2017-04-18 11:34:42', '1', '2', null, null, null), ('28', '红烧排骨', '11.00', '2', '2017-04-18 11:34:42', '1', '2', null, null, null), ('29', '红烧排骨', '11.00', '2', '2017-04-18 11:34:43', '1', '2', null, null, null), ('30', '红烧排骨', '11.00', '2', '2017-04-18 11:34:43', '1', '2', null, null, null), ('31', '红烧排骨', '11.00', '2', '2017-04-18 11:34:43', '1', '2', null, null, null), ('32', '红烧排骨', '11.00', '2', '2017-04-18 11:34:43', '1', '2', null, null, null);
COMMIT;

-- ----------------------------
--  Table structure for `dish_type`
-- ----------------------------
DROP TABLE IF EXISTS `dish_type`;
CREATE TABLE `dish_type` (
  `type_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `type_name` varchar(200) NOT NULL DEFAULT '',
  `belong` int(11) unsigned NOT NULL DEFAULT '0',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `dish_type`
-- ----------------------------
BEGIN;
INSERT INTO `dish_type` VALUES ('6', '2', '2', '2017-04-12 17:18:09'), ('7', '3', '2', '2017-04-12 17:18:11'), ('8', '4', '2', '2017-04-12 17:18:14'), ('9', '5', '2', '2017-04-12 17:18:16'), ('10', '6', '2', '2017-04-12 17:18:19'), ('11', '爱我的哇多', '2', '2017-04-12 17:18:24'), ('13', '阿达瓦无大', '2', '2017-04-12 17:18:33'), ('14', 'ssss', '2', '2017-04-12 17:18:39'), ('15', 'asdawdaw', '2', '2017-04-12 17:20:37'), ('16', 'xawxaw', '2', '2017-04-12 17:20:40'), ('17', 'asdasd', '2', '2017-04-12 17:25:13'), ('18', 'xawaxwax', '2', '2017-04-12 17:25:20'), ('19', 'dawdawd', '2', '2017-04-12 17:25:23'), ('20', 'asdad', '2', '2017-04-17 15:23:25');
COMMIT;

-- ----------------------------
--  Table structure for `dish_type_dishs`
-- ----------------------------
DROP TABLE IF EXISTS `dish_type_dishs`;
CREATE TABLE `dish_type_dishs` (
  `dish_type_type_id` int(11) NOT NULL,
  `dishs_id` bigint(20) NOT NULL,
  UNIQUE KEY `UK_i8v23xidipusr6bru0enjcbyh` (`dishs_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `order`
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `order_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `order_belong` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '属于谁',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `order_status` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '0:无效，1:新创建，2:正在点菜，3:已经提交下厨，4:已全部上菜，5:已买单',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `order_dish`
-- ----------------------------
DROP TABLE IF EXISTS `order_dish`;
CREATE TABLE `order_dish` (
  `order_dish_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `order_id` bigint(20) unsigned NOT NULL COMMENT '订单ID',
  `dish_id` bigint(20) unsigned NOT NULL COMMENT '菜品ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `status` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '0:无效，1:有效',
  PRIMARY KEY (`order_dish_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL DEFAULT '',
  `password` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
--  Records of `user`
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES ('2', 'lzx2005', '7add6f24e1d99acc4bc3b6435e8b6207'), ('3', 'lzx2005', 'test');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
