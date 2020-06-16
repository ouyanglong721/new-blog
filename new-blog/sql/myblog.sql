/*
 Navicat Premium Data Transfer

 Source Server         : n2
 Source Server Type    : MySQL
 Source Server Version : 100412
 Source Host           : 192.168.3.66:3306
 Source Schema         : myblog

 Target Server Type    : MySQL
 Target Server Version : 50599
 File Encoding         : 65001

 Date: 16/06/2020 00:03:37
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id主键',
  `author_id` bigint(20) NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `modify_time` datetime NULL DEFAULT NULL,
  `title` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `comment_count` bigint(20) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `article_author_id`(`author_id`) USING BTREE,
  CONSTRAINT `article_ibfk_1` FOREIGN KEY (`author_id`) REFERENCES `user` (`id`) ON DELETE SET NULL ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 43 CHARACTER SET = utf8 COLLATE = utf8_general_ci;

-- ----------------------------
-- Records of article
-- ----------------------------
BEGIN;
INSERT INTO `article` VALUES (1, 1, '2020-06-09 01:30:36', '2020-06-11 07:36:33', '哈哈哈', '测试标签的更新', 8), (2, 1, '2020-06-01 01:30:40', NULL, 'tttt', '数据库属性和实体对象的驼峰命名映射，只需要开启这个配置即可。\r\n\r\n使用方法：\r\nmybatis和spring结合使用时（java web工程），配置：', NULL), (3, 1, '2020-06-06 01:43:09', NULL, 'asdwt', '里我使用了一个CategoryController类\r\n@Controller表示这是一个Controller\r\n@Autowired自动绑定对应的CategoryService\r\n@GetMapping表示对url的绑定, 而且限定必须是get操作, 同样还有post, delete, put也是类似的意思;\r\n@RequestParam(value = \"start\", defaultValue = \"0\")这个是在listCategory中的方法对参数的注解, 表示接受的值是start, 如果没有传来start那么默认的value是0, 那么对于size也是类似的意思;\r\n@PathVariable(\"id\")在DELETE操作中传来了一', NULL), (4, 1, '2020-06-01 01:43:13', NULL, 'awds', 'd自动绑定对应的CategoryService\r\n@GetMapping表示对url的绑定, 而且限定必须是get操作, 同样还有post, delete, put也是类似的意思;\r\n@RequestParam(value = \"start\", defaultValue = \"0\")这个是在listCategory中的方法对参数的注解, 表示接受的值是start, 如果没有传来start那么默认的value是0, 那么对于size也是类似的意思;\r\n@PathVariable(\"id\")在DELETE操作中传来了一', 1), (5, 1, NULL, NULL, 'agwd', 'stParam(value = \"start\", defaultValue = \"0\")这个是在listCategory中的方法对参数的注解, 表示接受的值是start, 如果没有传来start那么默认的value是0, 那么对于size也是类似的意思;\r\n@PathVariable(\"id\")在DELETE操作中传来了一', NULL), (6, 1, NULL, '2020-06-10 13:39:51', 'string', 'string', NULL), (7, 1, '2020-05-27 01:43:16', NULL, '最新', 'art那么art那么stParam(value = \"start\", defaultValue = \"0\")这个是在listCategory中的方法对参数的注解, 表示接受的值是start, 如果没有传来start那么默认的value是0, 那么对于size也是类似的意思;\r\n外我发(\"id\")在DELETE操作中传来了一', NULL), (8, 1, NULL, NULL, '撒扥', '安外 \"id\")在DELETE操作中传来了一', NULL), (9, 1, NULL, NULL, '安外', 'm(value = \"start\", defaultValue = \"0\")这个是在listCategory中的方法对参数的注解, 表示接受的值是start, 如果没有传来start那么默认的value是0, 那么对于size也是类似的意思;\r\n (\"id\")在DELETE操作中传来了一', NULL), (10, 1, NULL, NULL, '奥森而', '俺得 riable(\"id\")在DELETE操作中传来了一', NULL), (11, 1, NULL, NULL, '赛森', '得俺得奥赛安抚sad奥赛俺得的ad俺得 (\"id\")在DELETE操作中传来了一', NULL), (12, 1, '2020-06-05 01:43:45', NULL, '6哼', '阿达外么art那么stParam(value = \"start\", defaultValue = \"0\")这个是在listCategory中的方法对参数的注解, 表示接受的值是start, 如果没有传来start那么默认的value是0, 那么对于size也是类似的意思;\r\n奥赛人hVariable(\"id\")在DELETE操作中传来了一', NULL), (13, 1, NULL, NULL, '文章的', '文章的', NULL), (14, 1, NULL, NULL, '我的的', '安外俺得么art那么stParam(value = \"start\", defaultValue = \"0\")这个是在listCategory中的方法对参数的注解, 表示接受的值是start, 如果没有传来start那么默认的value是0, 那么对于size也是类似的意思;\r\n@PathVariable(\"id\")在DELETE操作中传来了一', NULL), (15, 1, NULL, NULL, '文章的', '文章的', NULL), (19, 1, NULL, '2020-06-09 01:43:39', '文章标题', '撒挡杀死多阿三戳死欧艾斯宠爱戳撒戳', NULL), (20, 10, NULL, NULL, '测试文章', '测试文章的内容', NULL), (21, 10, NULL, NULL, '测试文章', '测试文章的内容', NULL), (22, 10, '2020-06-12 01:43:30', '2020-06-04 01:43:34', '测试文章', '测试文章的内容', NULL), (23, 10, NULL, NULL, '更新后的标题', '再改一次后的内容', NULL), (39, 1, NULL, NULL, '文章测试中', '文章测试', NULL), (40, 1, NULL, '2020-06-18 01:43:42', '文章测试中', '文章测试', NULL), (41, 1, '2020-06-10 13:27:58', '2020-06-10 13:27:58', 'string', 'string', 0);
COMMIT;

-- ----------------------------
-- Table structure for article_category
-- ----------------------------
DROP TABLE IF EXISTS `article_category`;
CREATE TABLE `article_category`  (
  `article_id` bigint(20) NULL DEFAULT NULL,
  `category_id` bigint(20) NULL DEFAULT NULL,
  `id` bigint(20) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `article_id`(`article_id`) USING BTREE,
  INDEX `category_id`(`category_id`) USING BTREE,
  CONSTRAINT `article_category_ibfk_1` FOREIGN KEY (`article_id`) REFERENCES `article` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `article_category_ibfk_2` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE SET NULL ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci;

-- ----------------------------
-- Records of article_category
-- ----------------------------
BEGIN;
INSERT INTO `article_category` VALUES (1, 2, 00000000000000000001), (1, 5, 00000000000000000002), (1, 6, 00000000000000000003);
COMMIT;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '种类名称',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分类的描述信息',
  `parent_id` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `parent_id`(`parent_id`) USING BTREE,
  CONSTRAINT `category_ibfk_1` FOREIGN KEY (`parent_id`) REFERENCES `category` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci;

-- ----------------------------
-- Records of category
-- ----------------------------
BEGIN;
INSERT INTO `category` VALUES (1, 'C', 'C语言', 5), (2, 'C++', NULL, 5), (3, 'JAVA', NULL, 5), (4, 'Python', NULL, 5), (5, '编程语言', NULL, NULL), (6, 'winapi', NULL, 1), (7, 'Qt', 'Qt', 2), (9, '数学', '数学', NULL);
COMMIT;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `article_id` bigint(20) NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `parent_id` bigint(20) NULL DEFAULT NULL,
  `to_id` bigint(20) NULL DEFAULT NULL COMMENT '二级评论,回复哪条评论',
  `email` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `nickname` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `website` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `comment_article_id`(`article_id`) USING BTREE,
  INDEX `comment_parent_id`(`parent_id`) USING BTREE,
  INDEX `comment_ibfk_5`(`to_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8 COLLATE = utf8_general_ci;

-- ----------------------------
-- Records of comment
-- ----------------------------
BEGIN;
INSERT INTO `comment` VALUES (13, '评论测试', 1, '2020-06-15 13:41:42', NULL, 0, '123123@qq.com', '哈哈哈', 'string'), (14, '评论测试', 1, '2020-06-15 13:45:01', NULL, 0, '123123@qq.com', '哈哈哈', 'string'), (15, '评论测试', 1, '2020-06-15 13:45:02', NULL, 0, '123123@qq.com', '哈哈哈', 'string'), (16, '评论测试', 1, '2020-06-15 13:45:06', NULL, 0, '123123@qq.com', '哈哈哈', 'string'), (17, '评论测试', 1, '2020-06-15 13:45:06', NULL, 0, '123123@qq.com', '哈哈哈', 'string'), (18, '评论奥赛ad安慰', 1, '2020-06-15 13:45:18', NULL, 0, '123123@qq.com', '奥赛德哈', 'string'), (19, '评论奥赛ad安慰', 1, '2020-06-15 13:45:18', NULL, 0, '123123@qq.com', '奥赛德哈', 'string'), (20, '评论奥赛ad安慰', 1, '2020-06-15 13:45:18', NULL, 0, '123123@qq.com', '奥赛德哈', 'string'), (21, '评论奥赛ad安慰', 1, '2020-06-15 13:45:19', NULL, 0, '123123@qq.com', '奥赛德哈', 'string'), (22, '评论奥赛ad安慰', 1, '2020-06-15 13:45:19', 14, 0, '123123@qq.com', '奥赛德哈', 'string'), (23, '评论奥赛ad安慰', 1, '2020-06-15 13:45:30', 14, 0, '123123@qq.com', '奥赛德哈', 'string'), (24, '评论奥赛ad安慰', 1, '2020-06-15 13:45:30', 14, 0, '123123@qq.com', '奥赛德哈', 'string'), (25, '评论奥赛ad安慰', 1, '2020-06-15 13:45:38', 14, 0, '123123@qq.com', '奥赛德哈', 'string'), (26, '评论奥赛ad安慰', 1, '2020-06-15 13:51:27', NULL, 0, '123123@qq.com', '奥赛德哈', 'string'), (27, '评论奥赛ad安慰', 1, '2020-06-15 13:51:33', NULL, 0, '123123@qq.com', '奥赛德哈', 'string');
COMMIT;

-- ----------------------------
-- Table structure for tag
-- ----------------------------
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci;

-- ----------------------------
-- Records of tag
-- ----------------------------
BEGIN;
INSERT INTO `tag` VALUES (1, '语文'), (2, '数学'), (3, '英语'), (4, '物理'), (5, '化学'), (6, '生物'), (7, '体育'), (8, '历史');
COMMIT;

-- ----------------------------
-- Table structure for tagmap
-- ----------------------------
DROP TABLE IF EXISTS `tagmap`;
CREATE TABLE `tagmap`  (
  `tag_id` bigint(20) NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `tag_id`(`tag_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8 COLLATE = utf8_general_ci;

-- ----------------------------
-- Records of tagmap
-- ----------------------------
BEGIN;
INSERT INTO `tagmap` VALUES (1, 1), (1, 2), (1, 3), (2, 4), (2, 18), (3, 5), (5, 6), (5, 7), (5, 8), (6, 19);
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名 最长16位',
  `password` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '加密后的密码',
  `create_time` datetime NULL DEFAULT NULL,
  `nickname` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `avatar_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 39 CHARACTER SET = utf8 COLLATE = utf8_general_ci;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES (1, 'admin', 'admin1', '2020-04-04 16:15:25.000000', '哈哈哈哈噶', 'admin@oylong.com', 'http://localhost:8081/img/1.bmp'), (8, '123哈哈哈', '123123', '2020-04-05 17:02:59.000000', '高级人类', 'admin@oylong.com', 'http://localhost:8080/img/a8cdd450-7520-49aa-9286-4418a0ec8f39.png'), (9, '好人好事', '123', '2020-04-05 17:03:17.000000', '高级人类', 'admin@oylong.com', 'http://localhost:8080/img/16cbd739-5dd9-4c1a-b389-d62be0555b28.bmp'), (10, 'agood', '123', '2020-04-05 17:03:22.000000', '高级人类', 'admin@oylong.com', 'https://www.oylong.com'), (11, '11', '123123', '2020-04-06 08:35:44.000000', 'string', 'asdawd@qq.com', 'string'), (14, '123123', '123123', '2020-06-04 18:01:12.000000', 'gsa', 'asgasd2@qq.com', 'asgwad'), (15, 'string123', '123123', '2020-06-04 10:05:03.000000', 'haha', '4214@oylong.com', 'string'), (16, '23', '123', '2020-06-04 10:05:10.000000', 'string', '4214@oylong.com', 'http://localhost:8080/img/0ab05075-2b4d-48b8-86ef-0e6cdfc79cca.png'), (18, '123213', '123', '2020-06-04 10:05:15.000000', 'string', '4214@oylong.com', 'string'), (20, 'admin1', '123', '2020-06-04 10:05:21.000000', 'string', '4214@oylong.com', 'string'), (27, 'admin11', 'admin', '2020-06-05 13:02:49.000000', 'ouyang', '4214@oylong.com', 'http://localhost:8080/img/9a9b92f3-328d-4f49-a3fa-47f9d7835c1e.bmp'), (28, 'admin124241', 'admin', '2020-06-05 13:04:53.000000', 'asdasd', '4214@oylong.com', 'http://localhost:8080/img/020b57b8-deb7-45ea-a421-41286ca26063.jpg'), (29, '21123', 'admin', '2020-06-05 13:11:58.000000', '124124', '4214@oylong.com', 'http://localhost:8080/img/461cab1b-0796-4e06-a238-1d47b4be152e.png'), (30, '124124', 'admin', '2020-06-05 13:12:33.000000', '531e123', '4214@oylong.com', 'http://localhost:8080/img/5d417d3b-26d2-4aaf-a44d-9b3cd817e3f4.png'), (31, 'string', 'string', '2020-06-10 11:13:12.000000', 'string', 'string', 'string'), (32, '111', '123456', '2020-06-10 11:13:12.000000', NULL, 'ouyanglong721@gmail.com', 'string'), (33, '12211', 'string', '2020-06-10 11:13:12.000000', NULL, 'string', 'st2ring'), (34, 'adadad', '123123123', NULL, '123123', 'ouyanglong721@gmail.com', NULL), (35, 'asjdh', 'asdasd', NULL, 'asasd', 'ouyanglong721@gmail.com', NULL), (36, 'saiohdiosd', '123123123', '2020-06-12 17:33:07.000000', '阿斯利大赛', 'admin@qq.com', NULL), (38, 'oylong', '123456', '2020-06-12 17:36:12.000000', '欧阳龙', 'ouyanglong721@gmail.com', 'http://localhost:8081/img/d61d908d-6c2c-46f2-9112-ffa7573d3616.png');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
