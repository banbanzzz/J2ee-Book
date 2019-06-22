/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80012
 Source Host           : localhost:3306
 Source Schema         : book

 Target Server Type    : MySQL
 Target Server Version : 80012
 File Encoding         : 65001

 Date: 12/06/2019 16:23:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admininfo
-- ----------------------------
DROP TABLE IF EXISTS `admininfo`;
CREATE TABLE `admininfo`  (
  `admin_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '管理员ID',
  `admin_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '管理员名称',
  `admin_pwd` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '管理员密码',
  PRIMARY KEY (`admin_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admininfo
-- ----------------------------
INSERT INTO `admininfo` VALUES (1, 'admin', '123');

-- ----------------------------
-- Table structure for booksinfo
-- ----------------------------
DROP TABLE IF EXISTS `booksinfo`;
CREATE TABLE `booksinfo`  (
  `book_id` bigint(50) NOT NULL AUTO_INCREMENT COMMENT '书籍编码',
  `book_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '书籍名字',
  `book_author` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '书籍作者',
  `book_price` float(10, 2) NOT NULL COMMENT '书籍价格',
  `book_press` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '出版社',
  `book_num` int(10) NOT NULL DEFAULT 5 COMMENT '书籍库存',
  `book_status` int(10) NOT NULL DEFAULT 1 COMMENT '书籍状态1可借  0不可借   ',
  PRIMARY KEY (`book_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of booksinfo
-- ----------------------------
INSERT INTO `booksinfo` VALUES (1, '智能硬件开发入门', '刘修文', 49.50, '中国电力出版社', 0, 1);
INSERT INTO `booksinfo` VALUES (2, '软件架构', '贾山', 49.50, '清华大学出版社', 8, 0);
INSERT INTO `booksinfo` VALUES (3, 'java web高级编程', '王肖峰', 99.80, '清华大学出版社', 1, 1);
INSERT INTO `booksinfo` VALUES (4, '党旗下的青春', '龙文清', 20.00, '广东高等教育出版社', 9, 1);
INSERT INTO `booksinfo` VALUES (5, '离散数学基础', '谢胜利', 34.60, '清华大学出版社', 4, 0);
INSERT INTO `booksinfo` VALUES (6, '计算机网络基础', '谢希仁', 45.00, '电子工业出版社', 7, 0);
INSERT INTO `booksinfo` VALUES (7, '智能硬件', '刘修文', 30.50, '中国电力出版社', 3, 1);
INSERT INTO `booksinfo` VALUES (8, 'Web前端开发', '耿方方', 39.00, '中国水利水电出版社', 3, 1);
INSERT INTO `booksinfo` VALUES (9, 'C语言程序设计', '谭浩强', 33.00, '清华大学出版社', 0, 1);

-- ----------------------------
-- Table structure for borrowinfo
-- ----------------------------
DROP TABLE IF EXISTS `borrowinfo`;
CREATE TABLE `borrowinfo`  (
  `borrow_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '借书编号',
  `borrow_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT ' 所借用户 通过user_name 找到用户',
  `borrow_date` datetime(0) NOT NULL COMMENT '借书时间',
  `book_id` bigint(20) NOT NULL COMMENT '书籍id 通过id找到数据信息',
  `operator` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '操作者',
  PRIMARY KEY (`borrow_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of borrowinfo
-- ----------------------------
INSERT INTO `borrowinfo` VALUES (2, 'user1', '2019-06-05 01:00:00', 2, 'user1');
INSERT INTO `borrowinfo` VALUES (11, 'user1', '2019-06-09 15:49:55', 9, 'user1');
INSERT INTO `borrowinfo` VALUES (12, 'user1', '2019-06-09 15:50:20', 9, 'user1');
INSERT INTO `borrowinfo` VALUES (25, 'user', '2019-06-11 11:43:14', 1, 'user');
INSERT INTO `borrowinfo` VALUES (26, 'user2', '2019-06-11 17:10:47', 3, 'admin');
INSERT INTO `borrowinfo` VALUES (27, 'user2', '2019-06-11 17:13:13', 4, 'admin');

-- ----------------------------
-- Table structure for returninfo
-- ----------------------------
DROP TABLE IF EXISTS `returninfo`;
CREATE TABLE `returninfo`  (
  `return_id` bigint(10) NOT NULL AUTO_INCREMENT COMMENT '还书编号',
  `return_user` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '还书用户  通过user_name找到用户',
  `borrow_date` datetime(0) NOT NULL COMMENT '借书时间',
  `return_date` datetime(0) NOT NULL COMMENT '还书时间',
  `book_id` bigint(10) NOT NULL COMMENT '书籍id 通过id找到数据信息',
  `operator` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '\r\n操作者',
  PRIMARY KEY (`return_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of returninfo
-- ----------------------------
INSERT INTO `returninfo` VALUES (5, 'user', '2019-06-11 03:55:19', '2019-06-11 04:00:16', 1, 'user');
INSERT INTO `returninfo` VALUES (6, 'user', '2019-06-11 03:24:00', '2019-06-11 04:00:17', 3, 'user');
INSERT INTO `returninfo` VALUES (7, 'user1', '2019-06-09 16:01:37', '2019-06-11 16:24:44', 8, 'admin');
INSERT INTO `returninfo` VALUES (8, 'user1', '2019-06-09 16:01:09', '2019-06-11 16:24:46', 8, 'admin');
INSERT INTO `returninfo` VALUES (9, 'user1', '2019-06-09 16:01:24', '2019-06-11 17:02:22', 8, 'admin');
INSERT INTO `returninfo` VALUES (10, 'user', '2019-06-11 03:23:10', '2019-06-11 17:02:24', 4, 'admin');

-- ----------------------------
-- Table structure for userinfo
-- ----------------------------
DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo`  (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `user_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名称',
  `user_pwd` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户密码',
  `user_status` int(10) NOT NULL DEFAULT 1 COMMENT '用户状态 1正常 0注销',
  `user_num` int(10) NOT NULL DEFAULT 10 COMMENT '用户借书量',
  `user_tel` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户手机号',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of userinfo
-- ----------------------------
INSERT INTO `userinfo` VALUES (1, 'user', '123', 1, 11, '15216785634');
INSERT INTO `userinfo` VALUES (2, 'user2', '123456', 1, 8, '13445566633');
INSERT INTO `userinfo` VALUES (3, 'test', '123', 1, 11, '13373777277');
INSERT INTO `userinfo` VALUES (4, 'user1', '123', 1, 9, '13523451232');
INSERT INTO `userinfo` VALUES (5, 'user4', '1234', 0, 9, '13523451232');
INSERT INTO `userinfo` VALUES (6, 'user5', '123', 0, 5, '13512312312');

SET FOREIGN_KEY_CHECKS = 1;
