/*
 Navicat Premium Data Transfer

 Source Server         : hahaha
 Source Server Type    : MySQL
 Source Server Version : 50629
 Source Host           : localhost:3306
 Source Schema         : db_search

 Target Server Type    : MySQL
 Target Server Version : 50629
 File Encoding         : 65001

 Date: 20/10/2019 11:02:56
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_bookinfo
-- ----------------------------
DROP TABLE IF EXISTS `tb_bookinfo`;
CREATE TABLE `tb_bookinfo`  (
  `BookID` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `BookName` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `booktype` int(4) NOT NULL,
  `series` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TPI` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Writer` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Introduce` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `zishu` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `edition` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Book_page` int(4) NOT NULL,
  `Price` float NOT NULL,
  `pDate` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`BookID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_bookinfo
-- ----------------------------
INSERT INTO `tb_bookinfo` VALUES ('1001', 'C语言程序设计', 1, '计算机系列丛书', '清华大学出版社', '谭浩强', 'C程序设计语言经典教材，发行量达', '100000', '第四版', 260, 38, '2014-8-30');
INSERT INTO `tb_bookinfo` VALUES ('1002', 'JAVA程序设计', 1, '计算机系列丛书', '清华大学出版社', '不知道', 'JAVA程序设计语言经典教材，发', '200000', '第三版', 320, 59, '2014-6-20');
INSERT INTO `tb_bookinfo` VALUES ('1003', '圣墟', 2, '网络流行小说', '顶点小说网', '辰东', '在破败中崛起，在寂灭中复苏。\r\n', '连载中', '连载中', 120, 60, '2017-3-20');
INSERT INTO `tb_bookinfo` VALUES ('1004', '健康与生活', 3, '养生保健系列', '不知道的出版社', '作者是谁', '就是很牛逼很养生很健康的一本书', '20000', '第一版', 150, 20, '2017-6-23 ');

-- ----------------------------
-- Table structure for tb_news
-- ----------------------------
DROP TABLE IF EXISTS `tb_news`;
CREATE TABLE `tb_news`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bigSort` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `title` varchar(4000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `content` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `createTime` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_news
-- ----------------------------
INSERT INTO `tb_news` VALUES (1, '绉戝鏁欒偛', '鎴戞牎瀛︾敓鍦ㄧ鍏眾鍏ㄥ浗澶у鐢熷競鍦鸿皟鏌ュ垎鏋愬ぇ璧涚寤哄垎璧涗腑鍠滆幏浣崇哗', '鏃ュ墠锛岀敱鏁欒偛閮ㄩ珮绛夊鏍＄粺璁″鏁欏鎸囧濮斿憳', '2016-5-26 12:29:23');
INSERT INTO `tb_news` VALUES (10, '环球新闻', '可燃冰试采现场指挥长叶建良：可燃冰试采环境污染可控', '央广网广州6月21日消息（记者彭小毛）21日，南海神狐海域的', '2017-06-23 09:11:29');
INSERT INTO `tb_news` VALUES (11, '军事头条', '统一的号角已吹响 北京动手的机会终于来了', '自2016年5月20日蔡英文上台以来，两岸“一个中国”的政治', '2017-06-23 09:13:52');
INSERT INTO `tb_news` VALUES (12, '军事头条', '我军为何近3月未服役一舰 6艘新舰已竣工专等一节点', '首当其冲，就是052D型的东海舰队首舰154厦门舰了。', '2017-06-26 09:13:52');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci NULL DEFAULT NULL COMMENT '电话',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci NULL DEFAULT NULL COMMENT '密码',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_vietnamese_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '18750032799', '18750032799');

SET FOREIGN_KEY_CHECKS = 1;
