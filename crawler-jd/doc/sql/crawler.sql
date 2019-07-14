/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80012
 Source Host           : localhost:3306
 Source Schema         : crawler

 Target Server Type    : MySQL
 Target Server Version : 80012
 File Encoding         : 65001

 Date: 14/07/2019 23:41:49
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for jd_item
-- ----------------------------
DROP TABLE IF EXISTS `jd_item`;
CREATE TABLE `jd_item`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `spu` bigint(255) NULL DEFAULT NULL COMMENT '商品集合id',
  `sku` bigint(255) NULL DEFAULT NULL COMMENT '商品最小品类单元id',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商品标题',
  `price` double(10, 0) NULL DEFAULT NULL COMMENT '商品价格',
  `pic` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商品图片',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商品详情地址',
  `created` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updated` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '京东商品表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
