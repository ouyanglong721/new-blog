/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50720
 Source Host           : localhost:3306
 Source Schema         : new_blog

 Target Server Type    : MySQL
 Target Server Version : 50720
 File Encoding         : 65001

 Date: 09/01/2021 21:26:40
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id主键',
  `author_id` bigint(20) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `modify_time` datetime(0) NULL DEFAULT NULL,
  `title` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `comment_count` bigint(20) NULL DEFAULT 0,
  `state` tinyint(4) NULL DEFAULT 0 COMMENT '状态值 true表示公开 false表示私有',
  `is_draft` tinyint(4) NULL DEFAULT NULL COMMENT '是否是草稿',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `article_author_id`(`author_id`) USING BTREE,
  CONSTRAINT `article_ibfk_1` FOREIGN KEY (`author_id`) REFERENCES `user` (`id`) ON DELETE SET NULL ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 52 CHARACTER SET = utf8 COLLATE = utf8_general_ci;

-- ----------------------------
-- Records of article
-- ----------------------------
BEGIN;
INSERT INTO `article` VALUES (1, NULL, '2020-06-18 17:14:50', '2020-06-18 17:14:59', '哈哈哈', '# 123 haha哈哈\n## 测试中.......', 3, 0, 0), (2, NULL, '2020-06-01 01:30:40', '2020-06-18 16:16:39', 'tttt', '数据库属性和实体对象的驼峰命名映射，只需要开启这个配置即可。\r\n\r\n使用方法：\r\nmybatis和spring结合使用时（java web工程），配置：', NULL, 1, 0), (3, NULL, '2020-06-06 01:43:09', NULL, 'asdwt', '里我使用了一个CategoryController类\r\n@Controller表示这是一个Controller\r\n@Autowired自动绑定对应的CategoryService\r\n@GetMapping表示对url的绑定, 而且限定必须是get操作, 同样还有post, delete, put也是类似的意思;\r\n@RequestParam(value = \"start\", defaultValue = \"0\")这个是在listCategory中的方法对参数的注解, 表示接受的值是start, 如果没有传来start那么默认的value是0, 那么对于size也是类似的意思;\r\n@PathVariable(\"id\")在DELETE操作中传来了一', NULL, 1, 0), (4, NULL, '2020-06-01 01:43:13', '2020-06-18 16:03:49', 'awds', 'd自动绑定对应的CategoryService\r\n@GetMapping表示对url的绑定, 而且限定必须是get操作, 同样还有post, delete, put也是类似的意思;\r\n@RequestParam(value = \"start\", defaultValue = \"0\")这个是在listCategory中的方法对参数的注解, 表示接受的值是start, 如果没有传来start那么默认的value是0, 那么对于size也是类似的意思;\r\n@PathVariable(\"id\")在DELETE操作中传来了一', 0, 0, 0), (5, NULL, NULL, '2020-06-18 16:10:11', '哈哈哈哈', 'stParam(value = \"start\", defaultValue = \"0\")这个是在listCategory中的方法对参数的注解, 表示接受的值是start, 如果没有传来start那么默认的value是0, 那么对于size也是类似的意思;\r\n@PathVariable(\"id\")在DELETE操作中传来了一', NULL, 0, 0), (6, 39, NULL, '2020-06-10 13:39:51', 'string', 'string', NULL, NULL, 0), (7, 39, '2020-05-27 01:43:16', NULL, '最新', 'art那么art那么stParam(value = \"start\", defaultValue = \"0\")这个是在listCategory中的方法对参数的注解, 表示接受的值是start, 如果没有传来start那么默认的value是0, 那么对于size也是类似的意思;\r\n外我发(\"id\")在DELETE操作中传来了一', NULL, 1, 0), (8, NULL, NULL, NULL, '撒扥', '安外 \"id\")在DELETE操作中传来了一', NULL, 0, 0), (9, NULL, NULL, NULL, '安外', 'm(value = \"start\", defaultValue = \"0\")这个是在listCategory中的方法对参数的注解, 表示接受的值是start, 如果没有传来start那么默认的value是0, 那么对于size也是类似的意思;\r\n (\"id\")在DELETE操作中传来了一', NULL, 0, 0), (10, NULL, NULL, NULL, '奥森而', '俺得 riable(\"id\")在DELETE操作中传来了一', NULL, 0, 0), (11, NULL, NULL, NULL, '赛森', '得俺得奥赛安抚sad奥赛俺得的ad俺得 (\"id\")在DELETE操作中传来了一', NULL, 1, 0), (12, NULL, '2020-06-05 01:43:45', NULL, '6哼', '阿达外么art那么stParam(value = \"start\", defaultValue = \"0\")这个是在listCategory中的方法对参数的注解, 表示接受的值是start, 如果没有传来start那么默认的value是0, 那么对于size也是类似的意思;\r\n奥赛人hVariable(\"id\")在DELETE操作中传来了一', NULL, 1, 0), (13, NULL, NULL, NULL, '文章的', '文章的', NULL, NULL, 0), (14, NULL, NULL, NULL, '我的的', '安外俺得么art那么stParam(value = \"start\", defaultValue = \"0\")这个是在listCategory中的方法对参数的注解, 表示接受的值是start, 如果没有传来start那么默认的value是0, 那么对于size也是类似的意思;\r\n@PathVariable(\"id\")在DELETE操作中传来了一', NULL, NULL, 0), (15, NULL, NULL, NULL, '文章的', '文章的', NULL, NULL, 1), (19, NULL, NULL, '2020-06-09 01:43:39', '文章标题', '撒挡杀死多阿三戳死欧艾斯宠爱戳撒戳', NULL, NULL, 1), (20, NULL, NULL, NULL, '测试文章', '测试文章的内容', NULL, NULL, 1), (21, NULL, NULL, NULL, '测试文章', '测试文章的内容', NULL, NULL, 1), (22, NULL, '2020-06-12 01:43:30', '2020-06-04 01:43:34', '测试文章', '测试文章的内容', NULL, NULL, 1), (23, NULL, NULL, NULL, '更新后的标题', '再改一次后的内容', NULL, NULL, 1), (39, NULL, NULL, NULL, '文章测试中', '文章测试', NULL, NULL, 1), (40, NULL, NULL, '2020-06-18 01:43:42', '文章测试中', '文章测试', NULL, NULL, 0), (41, NULL, '2020-06-10 13:27:58', '2020-06-10 13:27:58', 'string', 'string', 0, NULL, 0), (43, 39, '2020-06-17 14:18:36', '2020-06-17 14:18:36', '', '', 0, NULL, 0), (44, 39, '2020-06-17 14:24:23', '2020-06-17 14:24:23', '', '', 0, NULL, 0), (45, 39, '2020-06-17 14:24:32', '2020-06-17 14:24:32', '', '', 0, NULL, 1), (46, 39, '2020-06-17 14:25:24', '2020-06-17 14:25:24', '', '', 0, NULL, 0), (47, 39, '2020-06-17 14:39:20', '2020-06-17 14:39:20', '第一篇博客', '# 这是第一片博客\n测试内容\n### 起飞\n起飞起飞~~~', 0, NULL, 0), (48, 39, '2020-06-17 14:40:36', '2020-06-17 14:40:37', '第一篇博客', '# 这是第一片博客\n测试内容\n### 起飞\n起飞起飞~~~', 0, NULL, 0), (49, 39, '2020-06-17 14:46:35', '2020-06-17 14:46:35', '第一篇博客', '# 这是第一片博客\n测试内容\n### 起飞\n起飞起飞~~~', 0, NULL, 0), (50, 39, '2020-06-17 14:52:10', '2020-06-17 14:52:11', '第一篇博客', '# 这是第一片博客\n测试内容\n### 起飞\n起飞起飞~~~', 0, 0, 0), (51, NULL, '2021-01-01 14:32:00', '2021-01-01 14:32:00', '202111', '2125wdzsdadawffw', 0, 1, 0);
COMMIT;

-- ----------------------------
-- Table structure for article_category
-- ----------------------------
DROP TABLE IF EXISTS `article_category`;
CREATE TABLE `article_category`  (
  `article_id` bigint(20) NULL DEFAULT NULL,
  `category_id` bigint(20) NULL DEFAULT NULL,
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `article_id`(`article_id`) USING BTREE,
  INDEX `category_id`(`category_id`) USING BTREE,
  CONSTRAINT `article_category_ibfk_1` FOREIGN KEY (`article_id`) REFERENCES `article` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `article_category_ibfk_2` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE SET NULL ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_general_ci;

-- ----------------------------
-- Records of article_category
-- ----------------------------
BEGIN;
INSERT INTO `article_category` VALUES (NULL, 3, 4), (NULL, 4, 5), (NULL, 9, 6), (39, 3, 7), (39, 4, 8), (39, 9, 9), (1, 3, 11), (1, 9, 12), (1, 4, 13), (1, 1, 14), (1, 6, 15), (4, 9, 16), (51, 9, 17);
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
  CONSTRAINT `category_ibfk_1` FOREIGN KEY (`parent_id`) REFERENCES `category` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 36 CHARACTER SET = utf8 COLLATE = utf8_general_ci;

-- ----------------------------
-- Records of category
-- ----------------------------
BEGIN;
INSERT INTO `category` VALUES (1, 'C', 'C语言', 5), (2, 'C++', NULL, 5), (3, 'JAVA', NULL, 5), (4, 'Python', NULL, 5), (5, '编程语言', '123', NULL), (6, 'winapi', NULL, 1), (7, 'Qt', 'Qt', 2), (9, '数学', '修改一波', NULL), (30, '新分类测试', '新分类测试', NULL), (31, '子分类测试', '子分类测试', 30), (32, 'QWidget', 'QWidget测试', 7), (34, '大学物理', '大学物理', NULL), (35, '角动量', '角动量', 34);
COMMIT;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `article_id` bigint(20) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `parent_id` bigint(20) NULL DEFAULT NULL,
  `to_id` bigint(20) NULL DEFAULT NULL COMMENT '二级评论,回复哪条评论',
  `email` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `nickname` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `website` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `comment_article_id`(`article_id`) USING BTREE,
  INDEX `comment_parent_id`(`parent_id`) USING BTREE,
  INDEX `comment_ibfk_5`(`to_id`) USING BTREE,
  CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`article_id`) REFERENCES `article` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_general_ci;

-- ----------------------------
-- Records of comment
-- ----------------------------
BEGIN;
INSERT INTO `comment` VALUES (14, '评论测试', 1, '2020-06-15 13:45:01', NULL, 0, '123123@qq.com', '哈哈哈', 'string'), (15, '评论测试', 1, '2020-06-15 13:45:02', NULL, 0, '123123@qq.com', '哈哈哈', 'string'), (17, '评论测试', 1, '2020-06-15 13:45:06', NULL, 0, '123123@qq.com', '哈哈哈', 'string');
COMMIT;

-- ----------------------------
-- Table structure for friend
-- ----------------------------
DROP TABLE IF EXISTS `friend`;
CREATE TABLE `friend`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `website` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '友链地址',
  `icon_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci;

-- ----------------------------
-- Records of friend
-- ----------------------------
BEGIN;
INSERT INTO `friend` VALUES (2, '新的友链', 'https://www.baidu.com', 'https://cube.elemecdn.com/e/fd/0fc7d20532fdaf769a25683617711png.png'), (3, '新的友链', 'https://oylong.com', 'https://www.oylong.com/usr/uploads/icon.png');
COMMIT;

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `start_time` datetime(0) NULL DEFAULT NULL,
  `end_time` datetime(0) NULL DEFAULT NULL,
  `title` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `content` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci;

-- ----------------------------
-- Records of notice
-- ----------------------------
BEGIN;
INSERT INTO `notice` VALUES (1, '2020-06-11 00:00:00', '2020-06-25 00:00:00', '新的通知', '这是一个新的通知哈哈哈哈哈'), (4, '2020-06-10 00:00:00', '2020-07-07 00:00:00', '新的公告', '这是一个新的公告!!!!'), (5, NULL, NULL, '再来一个公告', '新的公告再来一个');
COMMIT;

-- ----------------------------
-- Table structure for setting
-- ----------------------------
DROP TABLE IF EXISTS `setting`;
CREATE TABLE `setting`  (
  `id` bigint(20) NOT NULL,
  `name` varchar(33) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `description` varchar(129) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `url` varchar(129) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `provider_num` varchar(65) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `comment_enabled` tinyint(4) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci;

-- ----------------------------
-- Records of setting
-- ----------------------------
BEGIN;
INSERT INTO `setting` VALUES (0, 'newBlog', '新博客', 'https://www.oylong.com', '湘 12345678', 1);
COMMIT;

-- ----------------------------
-- Table structure for tag
-- ----------------------------
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
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
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci;

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
  `create_time` datetime(0) NULL DEFAULT NULL,
  `nickname` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `avatar_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 41 CHARACTER SET = utf8 COLLATE = utf8_general_ci;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES (39, 'admin', '$2a$10$pl0DSvIwBSYk46QCek6YdODh2cqwI4ZwLNE6yoDX9quofCjy5GdSW', NULL, 'oyl', 'ouyanglong721@gmail.com', 'http://127.0.0.1:8081/img/e808be33-0c11-4964-aa9d-9bfb375cf843.png');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
