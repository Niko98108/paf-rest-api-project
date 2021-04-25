-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.7.23-log - MySQL Community Server (GPL)
-- Server OS:                    Win32
-- HeidiSQL Version:             11.0.0.5919
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for paf
DROP DATABASE IF EXISTS `paf`;
CREATE DATABASE IF NOT EXISTS `paf` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `paf`;

-- Dumping structure for table paf.fund
DROP TABLE IF EXISTS `fund`;
CREATE TABLE IF NOT EXISTS `fund` (
  `FundID` int(11) NOT NULL,
  `FundType` varchar(50) DEFAULT NULL,
  `Description` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`FundID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table paf.fund: ~1 rows (approximately)
/*!40000 ALTER TABLE `fund` DISABLE KEYS */;
INSERT INTO `fund` (`FundID`, `FundType`, `Description`) VALUES
	(0, 'Full', 'For low income earners');
/*!40000 ALTER TABLE `fund` ENABLE KEYS */;

-- Dumping structure for table paf.fundbodies
DROP TABLE IF EXISTS `fundbodies`;
CREATE TABLE IF NOT EXISTS `fundbodies` (
  `FundBodiesID` int(11) NOT NULL AUTO_INCREMENT,
  `FundBodiesName` varchar(30) DEFAULT NULL,
  `FundBodiesAddress` varchar(30) DEFAULT NULL,
  `FundBodiesPhoneNo` int(10) DEFAULT NULL,
  `FundBodyValue` float DEFAULT NULL,
  PRIMARY KEY (`FundBodiesID`)
) ENGINE=InnoDB AUTO_INCREMENT=118 DEFAULT CHARSET=latin1;

-- Dumping data for table paf.fundbodies: ~6 rows (approximately)
/*!40000 ALTER TABLE `fundbodies` DISABLE KEYS */;
INSERT INTO `fundbodies` (`FundBodiesID`, `FundBodiesName`, `FundBodiesAddress`, `FundBodiesPhoneNo`, `FundBodyValue`) VALUES
	(1, 'chamodi', 'kurunegala', 712345678, 100),
	(112, 'chiranthika', 'colombo', 775643567, 150),
	(113, 'Namal', 'Borella', 912354768, 300),
	(114, 'sakunthala', 'Malabe', 778465789, 4000),
	(116, 'Anu', 'gampaha', 334565789, 50000),
	(117, 'kosali', 'kuliyapitiya', 712346588, 25000);
/*!40000 ALTER TABLE `fundbodies` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
