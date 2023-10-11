-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        8.0.29 - MySQL Community Server - GPL
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- 导出 test 的数据库结构
DROP DATABASE IF EXISTS `ordersystem`;
CREATE DATABASE IF NOT EXISTS `ordersystem` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `ordersystem`;

-- 导出  表 test.t_user 结构
DROP TABLE IF EXISTS `order_info_t`;
CREATE TABLE IF NOT EXISTS `order_info_t` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `distance` INT(11) NULL DEFAULT NULL,
    `status` VARCHAR(60) NOT NULL,
    PRIMARY KEY (`id`)
)   COMMENT='order basic information'
    ENGINE = INNODB
    DEFAULT CHARSET = utf8;

