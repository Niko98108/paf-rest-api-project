-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.7.33-log - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL Version:             11.0.0.5919
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for paf_proj
DROP DATABASE IF EXISTS `paf_proj`;
CREATE DATABASE IF NOT EXISTS `paf_proj` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `paf_proj`;

-- Dumping structure for table paf_proj.products
DROP TABLE IF EXISTS `products`;
CREATE TABLE IF NOT EXISTS `products` (
  `productID` int(11) NOT NULL AUTO_INCREMENT,
  `productCode` varchar(50) DEFAULT NULL,
  `productName` varchar(250) DEFAULT NULL,
  `productPrice` double DEFAULT NULL,
  `productDes` varchar(250) DEFAULT NULL,
  KEY `productID` (`productID`)
) ENGINE=InnoDB AUTO_INCREMENT=246 DEFAULT CHARSET=latin1;

-- Dumping data for table paf_proj.products: ~4 rows (approximately)
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` (`productID`, `productCode`, `productName`, `productPrice`, `productDes`) VALUES
	(245, '48', 'test2', 8000, 'testing1'),
	(2, '202', 'watch', 25000, 'waterproof'),
	(3, '303', 'key board', 15000, 'macanical'),
	(4, '303', 'iphone', 157000, 'waterproof');
/*!40000 ALTER TABLE `products` ENABLE KEYS */;

-- Dumping structure for table paf_proj.researcher
DROP TABLE IF EXISTS `researcher`;
CREATE TABLE IF NOT EXISTS `researcher` (
  `R__id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(50) DEFAULT NULL,
  `Email` varchar(50) DEFAULT NULL,
  `Address` varchar(50) DEFAULT NULL,
  `Phone_number` varchar(50) DEFAULT NULL,
  KEY `R_id` (`R__id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

-- Dumping data for table paf_proj.researcher: ~5 rows (approximately)
/*!40000 ALTER TABLE `researcher` DISABLE KEYS */;
INSERT INTO `researcher` (`R__id`, `Name`, `Email`, `Address`, `Phone_number`) VALUES
	(2, 'ruvini', 'ruvini@gmail.com', 'gampaha', '0768829458'),
	(2, 'anuradi', 'anuradi@gmail.com', 'colombo', '0768859123'),
	(3, 'anushi', 'dilhari@gmail.com', 'gampaha', '0789956241'),
	(4, 'rusiru', 'rajapaksha@gmail.com', 'veyangoda', '0789956248'),
	(5, 'prakash', 'prakash@gmail.com', 'malabe', '0789984628');
/*!40000 ALTER TABLE `researcher` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
