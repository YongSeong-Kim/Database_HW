-- MariaDB dump 10.18  Distrib 10.5.8-MariaDB, for osx10.15 (x86_64)
--
-- Host: localhost    Database: musicCompany
-- ------------------------------------------------------
-- Server version	10.5.8-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `musicCompany`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `musicCompany` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `musicCompany`;

--
-- Table structure for table `chart`
--

DROP TABLE IF EXISTS `chart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chart` (
  `date` date DEFAULT NULL,
  `time` varchar(6) DEFAULT NULL,
  `chartnumber` int(11) NOT NULL,
  `name` varchar(20) NOT NULL,
  `genre` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`chartnumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chart`
--

LOCK TABLES `chart` WRITE;
/*!40000 ALTER TABLE `chart` DISABLE KEYS */;
INSERT INTO `chart` VALUES ('2020-12-25','12:00',1,'인기차트','Popluar'),('2010-01-01','13:00',2,'시대별차트','Popular'),('2015-03-01','12:00',3,'pop chart','pop');
/*!40000 ALTER TABLE `chart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `commentBox`
--

DROP TABLE IF EXISTS `commentBox`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `commentBox` (
  `comment` varchar(100) NOT NULL,
  `musicno` int(11) NOT NULL,
  PRIMARY KEY (`comment`,`musicno`),
  KEY `musicno` (`musicno`),
  CONSTRAINT `commentbox_ibfk_1` FOREIGN KEY (`musicno`) REFERENCES `music` (`musicnumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `commentBox`
--

LOCK TABLES `commentBox` WRITE;
/*!40000 ALTER TABLE `commentBox` DISABLE KEYS */;
INSERT INTO `commentBox` VALUES ('I love 방탄',1),('군인이라면 이정도는 알아야지',2),('나때는 말이야....',3);
/*!40000 ALTER TABLE `commentBox` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `manage_chart`
--

DROP TABLE IF EXISTS `manage_chart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `manage_chart` (
  `chartno` int(11) NOT NULL,
  `idno` char(9) NOT NULL,
  PRIMARY KEY (`chartno`,`idno`),
  KEY `idno` (`idno`),
  CONSTRAINT `manage_chart_ibfk_1` FOREIGN KEY (`chartno`) REFERENCES `chart` (`chartnumber`),
  CONSTRAINT `manage_chart_ibfk_2` FOREIGN KEY (`idno`) REFERENCES `musicmanager` (`idnumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `manage_chart`
--

LOCK TABLES `manage_chart` WRITE;
/*!40000 ALTER TABLE `manage_chart` DISABLE KEYS */;
INSERT INTO `manage_chart` VALUES (1,'123123123'),(3,'123456719');
/*!40000 ALTER TABLE `manage_chart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `manage_music`
--

DROP TABLE IF EXISTS `manage_music`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `manage_music` (
  `idno` char(9) NOT NULL,
  `musicno` int(11) NOT NULL,
  PRIMARY KEY (`idno`,`musicno`),
  KEY `musicno` (`musicno`),
  CONSTRAINT `manage_music_ibfk_1` FOREIGN KEY (`idno`) REFERENCES `musicmanager` (`idnumber`),
  CONSTRAINT `manage_music_ibfk_2` FOREIGN KEY (`musicno`) REFERENCES `music` (`musicnumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `manage_music`
--

LOCK TABLES `manage_music` WRITE;
/*!40000 ALTER TABLE `manage_music` DISABLE KEYS */;
INSERT INTO `manage_music` VALUES ('123123123',3);
/*!40000 ALTER TABLE `manage_music` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `manage_user`
--

DROP TABLE IF EXISTS `manage_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `manage_user` (
  `residentno` char(13) NOT NULL,
  `idno` char(9) NOT NULL,
  PRIMARY KEY (`residentno`,`idno`),
  KEY `idno` (`idno`),
  CONSTRAINT `manage_user_ibfk_1` FOREIGN KEY (`residentno`) REFERENCES `streamuser` (`residentnumber`),
  CONSTRAINT `manage_user_ibfk_2` FOREIGN KEY (`idno`) REFERENCES `musicmanager` (`idnumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `manage_user`
--

LOCK TABLES `manage_user` WRITE;
/*!40000 ALTER TABLE `manage_user` DISABLE KEYS */;
INSERT INTO `manage_user` VALUES ('1234561234561','123123123'),('1234561234561','123456789');
/*!40000 ALTER TABLE `manage_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `music`
--

DROP TABLE IF EXISTS `music`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `music` (
  `albumname` varchar(20) NOT NULL,
  `musicname` varchar(20) NOT NULL,
  `lyricwriter` varchar(10) DEFAULT NULL,
  `composer` varchar(10) DEFAULT NULL,
  `lyrics` varchar(100) DEFAULT NULL,
  `videoTime` varchar(10) DEFAULT NULL,
  `genre` varchar(15) DEFAULT NULL,
  `musicnumber` int(11) NOT NULL,
  `playcount` int(11) DEFAULT NULL,
  `releasedate` date NOT NULL,
  PRIMARY KEY (`musicnumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `music`
--

LOCK TABLES `music` WRITE;
/*!40000 ALTER TABLE `music` DISABLE KEYS */;
INSERT INTO `music` VALUES ('Love YourSelf','DNA','Pdogg','Hitman','첫눈에 널 알아보게 됐어 서롤 불러왔던 것처럼...','3:40','Dance',1,130,'2018-08-24'),('군가','전성을간다','김용성','김용성','높은산 깊은골 적막한...',NULL,'군가',2,200,'1900-01-01'),('군가','진짜사나이','정희석','정희석','멋있는 사나이 많고 많지만...',NULL,'군가',3,250,'1900-01-01');
/*!40000 ALTER TABLE `music` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `musicManager`
--

DROP TABLE IF EXISTS `musicManager`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `musicManager` (
  `name` varchar(10) NOT NULL,
  `phonenumber` char(11) DEFAULT NULL,
  `address` varchar(40) DEFAULT NULL,
  `idnumber` char(9) NOT NULL,
  `residentnumber` char(13) NOT NULL,
  PRIMARY KEY (`idnumber`),
  UNIQUE KEY `residentnumber` (`residentnumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `musicManager`
--

LOCK TABLES `musicManager` WRITE;
/*!40000 ALTER TABLE `musicManager` DISABLE KEYS */;
INSERT INTO `musicManager` VALUES ('Jeong','01043211234','Busan','123123111','6809191453312'),('Lee','01022109999','Bupyeong','123123123','1112223334441'),('Park','01020102444','Gangnam','123456719','1234567890123'),('Kim','01011111111','seoul','123456789','9711111111111');
/*!40000 ALTER TABLE `musicManager` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `playlist`
--

DROP TABLE IF EXISTS `playlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `playlist` (
  `name` varchar(20) NOT NULL,
  `playlistnumber` int(11) NOT NULL,
  `presidentno` char(13) NOT NULL,
  PRIMARY KEY (`playlistnumber`),
  KEY `presidentno` (`presidentno`),
  CONSTRAINT `playlist_ibfk_1` FOREIGN KEY (`presidentno`) REFERENCES `streamuser` (`residentnumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `playlist`
--

LOCK TABLES `playlist` WRITE;
/*!40000 ALTER TABLE `playlist` DISABLE KEYS */;
INSERT INTO `playlist` VALUES ('내맘대로',1,'1234561204561');
/*!40000 ALTER TABLE `playlist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `register`
--

DROP TABLE IF EXISTS `register`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `register` (
  `chartno` int(11) NOT NULL,
  `musicno` int(11) NOT NULL,
  PRIMARY KEY (`chartno`,`musicno`),
  KEY `musicno` (`musicno`),
  CONSTRAINT `register_ibfk_1` FOREIGN KEY (`chartno`) REFERENCES `chart` (`chartnumber`),
  CONSTRAINT `register_ibfk_2` FOREIGN KEY (`musicno`) REFERENCES `music` (`musicnumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `register`
--

LOCK TABLES `register` WRITE;
/*!40000 ALTER TABLE `register` DISABLE KEYS */;
INSERT INTO `register` VALUES (1,1),(1,2),(2,2),(2,3);
/*!40000 ALTER TABLE `register` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `save_playlist`
--

DROP TABLE IF EXISTS `save_playlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `save_playlist` (
  `playlistno` int(11) NOT NULL,
  `musicno` int(11) NOT NULL,
  PRIMARY KEY (`playlistno`,`musicno`),
  KEY `musicno` (`musicno`),
  CONSTRAINT `save_playlist_ibfk_1` FOREIGN KEY (`playlistno`) REFERENCES `playlist` (`playlistnumber`),
  CONSTRAINT `save_playlist_ibfk_2` FOREIGN KEY (`musicno`) REFERENCES `music` (`musicnumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `save_playlist`
--

LOCK TABLES `save_playlist` WRITE;
/*!40000 ALTER TABLE `save_playlist` DISABLE KEYS */;
/*!40000 ALTER TABLE `save_playlist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `streamUser`
--

DROP TABLE IF EXISTS `streamUser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `streamUser` (
  `name` varchar(10) NOT NULL,
  `phonenumber` char(11) DEFAULT NULL,
  `address` varchar(40) DEFAULT NULL,
  `id` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `residentnumber` char(13) NOT NULL,
  `currentmusicno` int(11) DEFAULT NULL,
  PRIMARY KEY (`residentnumber`),
  KEY `currentmusicno` (`currentmusicno`),
  CONSTRAINT `streamuser_ibfk_1` FOREIGN KEY (`currentmusicno`) REFERENCES `music` (`musicnumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `streamUser`
--

LOCK TABLES `streamUser` WRITE;
/*!40000 ALTER TABLE `streamUser` DISABLE KEYS */;
INSERT INTO `streamUser` VALUES ('Park','01020102333',NULL,'Update','qwer','1234561204561',NULL),('Kim','01013571357','bupyeong','me','asdf','1234561234561',NULL);
/*!40000 ALTER TABLE `streamUser` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-12-07  1:04:06
