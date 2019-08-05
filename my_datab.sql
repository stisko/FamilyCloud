CREATE DATABASE  IF NOT EXISTS `famdb` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `famdb`;
-- MySQL dump 10.13  Distrib 5.6.23, for Win64 (x86_64)
--
-- Host: localhost    Database: famdb
-- ------------------------------------------------------
-- Server version	5.6.24-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `directs_users`
--

DROP TABLE IF EXISTS `directs_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `directs_users` (
  `director_username` varchar(45) NOT NULL,
  `familymember_username` varchar(45) NOT NULL,
  PRIMARY KEY (`director_username`,`familymember_username`),
  KEY `familyMember_username_FK_idx` (`familymember_username`),
  CONSTRAINT `director_username_FK` FOREIGN KEY (`director_username`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `familyMember_usernamel_FK` FOREIGN KEY (`familymember_username`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='autoi pou dioikoun xristes';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `famcalevents`
--

DROP TABLE IF EXISTS `famcalevents`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `famcalevents` (
  `idfamCalEvents` int(11) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(45) NOT NULL,
  `title` varchar(45) NOT NULL,
  `start` datetime NOT NULL,
  `end` datetime DEFAULT NULL,
  `category` varchar(45) NOT NULL,
  `location` varchar(45) DEFAULT NULL,
  `visibility` varchar(45) NOT NULL,
  `participating_members` varchar(200) DEFAULT NULL,
  `description` varchar(160) DEFAULT NULL,
  `repeat_frequency` int(11) DEFAULT NULL,
  `repeat_time` varchar(45) DEFAULT NULL,
  `starts_at` datetime DEFAULT NULL,
  `expiration` datetime DEFAULT NULL,
  `director_username` varchar(45) NOT NULL,
  `notification_time` int(11) DEFAULT NULL,
  `notification_date` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idfamCalEvents`,`created_by`,`title`,`director_username`),
  KEY `createdFK_idx` (`created_by`),
  KEY `directorFK_idx` (`director_username`),
  CONSTRAINT `createdFK` FOREIGN KEY (`created_by`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `directorFK` FOREIGN KEY (`director_username`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `famdb`.`famcalevents_AFTER_INSERT` AFTER INSERT ON `famcalevents` FOR EACH ROW
BEGIN
DECLARE n INT DEFAULT 0;
DECLARE i INT DEFAULT 0;
	
    
    
  CREATE TEMPORARY TABLE temp1 (SELECT
  
  SUBSTRING_INDEX(SUBSTRING_INDEX(NEW.participating_members, ',', numbers.n), ',', -1) name
  
FROM
  numbers INNER JOIN famcalevents
  ON CHAR_LENGTH(NEW.participating_members)
     -CHAR_LENGTH(REPLACE(NEW.participating_members, ',', ''))>=numbers.n-1
	WHERE famcalevents.idfamCalEvents=NEW.idfamCalEvents
ORDER BY
  NEW.idfamCalEvents, n);
  
  SELECT COUNT(*) FROM temp1 INTO n;
  SET i=0;
  
  WHILE i<n DO 
	
    INSERT INTO notifications(usernameA,usernameB,notification_type,message,date_created,referred_id, isreadA, isreadB) 
    VALUES(NEW.created_by, (SELECT name  FROM temp1 LIMIT i,1), 'famcalevents', NEW.title, NOW(), NEW.idfamCalEvents,'N','N');
  
	
  SET i = i + 1;
  END WHILE;
	

END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `famdb`.`famcalevents_AFTER_UPDATE` AFTER UPDATE ON `famcalevents` FOR EACH ROW
BEGIN
  
	DECLARE n INT DEFAULT 0;
DECLARE i INT DEFAULT 0;
	
    
    DROP TEMPORARY TABLE if exists temp1;
  CREATE TEMPORARY TABLE temp1 (SELECT
  
  SUBSTRING_INDEX(SUBSTRING_INDEX(NEW.participating_members, ',', numbers.n), ',', -1) name
  
FROM
  numbers INNER JOIN famcalevents
  ON CHAR_LENGTH(NEW.participating_members)
     -CHAR_LENGTH(REPLACE(NEW.participating_members, ',', ''))>=numbers.n-1
	WHERE famcalevents.idfamCalEvents=NEW.idfamCalEvents
ORDER BY
  NEW.idfamCalEvents, n);
  
  SELECT COUNT(*) FROM temp1 INTO n;
  SET i=0;
  
  WHILE i<n DO 
	
    INSERT INTO notifications(usernameA,usernameB,notification_type,message,date_created,referred_id, isreadA, isreadB) 
    VALUES(NEW.created_by, (SELECT name  FROM temp1 LIMIT i,1), 'famcalevents_update',  NEW.title, NOW(), NEW.idfamCalEvents,'N','N');
  
  SET i = i + 1;
  END WHILE;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `famdb`.`famcalevents_BEFORE_DELETE` BEFORE DELETE ON `famcalevents` FOR EACH ROW
BEGIN

	DECLARE n INT DEFAULT 0;
DECLARE i INT DEFAULT 0;
	
    
DROP temporary TABLE if exists temp1;
  CREATE TEMPORARY TABLE temp1 (SELECT
  
  SUBSTRING_INDEX(SUBSTRING_INDEX(OLD.participating_members, ',', numbers.n), ',', -1) name
  
FROM
  numbers INNER JOIN famcalevents
  ON CHAR_LENGTH(OLD.participating_members)
     -CHAR_LENGTH(REPLACE(OLD.participating_members, ',', ''))>=numbers.n-1
	WHERE famcalevents.idfamCalEvents=OLD.idfamCalEvents
ORDER BY
  OLD.idfamCalEvents, n);
  
  SELECT COUNT(*) FROM temp1 INTO n;
  SET i=0;
  
  WHILE i<n DO 
	
    INSERT INTO notifications(usernameA,usernameB,notification_type,message,date_created,referred_id, isreadA, isreadB) 
    VALUES(OLD.created_by, (SELECT name  FROM temp1 LIMIT i,1), 'famcalevents_delete', OLD.title, NOW(), OLD.idfamCalEvents,'N','N');
  
  SET i = i + 1;
  END WHILE;

END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `mealsplan`
--

DROP TABLE IF EXISTS `mealsplan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mealsplan` (
  `idmealsplan` int(11) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(45) NOT NULL,
  `director_username` varchar(45) NOT NULL,
  `title` varchar(45) NOT NULL,
  `location` varchar(45) DEFAULT NULL,
  `start_date` date NOT NULL,
  `meal_type` varchar(45) NOT NULL,
  `notification_time` int(11) DEFAULT NULL,
  `notification_date` varchar(45) DEFAULT NULL,
  `description` varchar(160) DEFAULT NULL,
  `repeat_frequency` varchar(45) DEFAULT NULL,
  `repeat_time` varchar(45) DEFAULT NULL,
  `starts_at` date DEFAULT NULL,
  `expiration` date DEFAULT NULL,
  PRIMARY KEY (`idmealsplan`,`created_by`,`director_username`),
  KEY `createdbyFK_idx` (`created_by`),
  KEY `directorusnFK_idx` (`director_username`),
  CONSTRAINT `createdbyFK` FOREIGN KEY (`created_by`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `directorusnFK` FOREIGN KEY (`director_username`) REFERENCES `user` (`username`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `message` (
  `idMessages` int(11) NOT NULL AUTO_INCREMENT,
  `sender` varchar(45) NOT NULL,
  `receiver` varchar(45) NOT NULL,
  `message` varchar(45) NOT NULL,
  `file` longblob,
  `date` datetime DEFAULT NULL,
  PRIMARY KEY (`idMessages`,`sender`,`receiver`),
  KEY `sender_idx` (`sender`,`receiver`),
  KEY `receiver_idx` (`receiver`),
  CONSTRAINT `receiver` FOREIGN KEY (`receiver`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `sender` FOREIGN KEY (`sender`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=182 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `famdb`.`message_AFTER_INSERT` AFTER INSERT ON `message` FOR EACH ROW
BEGIN

INSERT INTO notifications(usernameA,usernameB,notification_type,message,date_created,referred_id, isreadA, isreadB) 
    VALUES(NEW.sender, NEW.receiver, 'messages', NEW.message, NOW(), NEW.idMessages,'Y','N');

END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `notifications`
--

DROP TABLE IF EXISTS `notifications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notifications` (
  `idnotifications` int(11) NOT NULL AUTO_INCREMENT,
  `usernameA` varchar(45) NOT NULL,
  `usernameB` varchar(45) DEFAULT NULL,
  `notification_type` varchar(45) DEFAULT NULL,
  `message` varchar(160) DEFAULT NULL,
  `date_created` datetime NOT NULL,
  `referred_id` int(11) DEFAULT NULL,
  `isreadA` varchar(45) NOT NULL,
  `isreadB` varchar(45) NOT NULL,
  PRIMARY KEY (`idnotifications`),
  KEY `usera_idx` (`usernameA`),
  KEY `useb_idx` (`usernameB`),
  CONSTRAINT `usera` FOREIGN KEY (`usernameA`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=882 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `numbers`
--

DROP TABLE IF EXISTS `numbers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `numbers` (
  `n` int(11) NOT NULL,
  PRIMARY KEY (`n`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `perscalevents`
--

DROP TABLE IF EXISTS `perscalevents`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `perscalevents` (
  `idperCalEvents` int(11) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(45) NOT NULL,
  `title` varchar(45) NOT NULL,
  `start` datetime NOT NULL,
  `end` datetime DEFAULT NULL,
  `category` varchar(45) NOT NULL,
  `location` varchar(45) DEFAULT NULL,
  `description` varchar(160) DEFAULT NULL,
  `repeat_frequency` int(11) DEFAULT NULL,
  `repeat_time` varchar(45) DEFAULT NULL,
  `starts_at` datetime DEFAULT NULL,
  `expiration` datetime DEFAULT NULL,
  `director_username` varchar(45) NOT NULL,
  `notification_time` int(11) DEFAULT NULL,
  `notification_date` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idperCalEvents`,`created_by`,`director_username`),
  KEY `created_b_idx` (`created_by`),
  CONSTRAINT `created` FOREIGN KEY (`created_by`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `shopping_list`
--

DROP TABLE IF EXISTS `shopping_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shopping_list` (
  `shopitemid` int(11) NOT NULL AUTO_INCREMENT,
  `director_username` varchar(45) NOT NULL,
  `title` varchar(45) NOT NULL,
  `quantity` int(11) NOT NULL,
  `price` varchar(45) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `assigned_to` varchar(200) DEFAULT NULL,
  `created_by` varchar(45) NOT NULL,
  PRIMARY KEY (`shopitemid`,`created_by`),
  KEY `director_user_idx` (`director_username`),
  KEY `created_idx` (`created_by`),
  CONSTRAINT `director_user` FOREIGN KEY (`director_username`) REFERENCES `user` (`username`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=84 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `famdb`.`shopping_list_AFTER_INSERT` AFTER INSERT ON `shopping_list` FOR EACH ROW
BEGIN
DECLARE n INT DEFAULT 0;
DECLARE i INT DEFAULT 0;
	
    
    
  CREATE TEMPORARY TABLE temp1 (SELECT
  
  SUBSTRING_INDEX(SUBSTRING_INDEX(NEW.assigned_to, ',', numbers.n), ',', -1) name
  
FROM
  numbers INNER JOIN shopping_list
  ON CHAR_LENGTH(NEW.assigned_to)
     -CHAR_LENGTH(REPLACE(NEW.assigned_to, ',', ''))>=numbers.n-1
	WHERE shopping_list.shopitemid=NEW.shopitemid
ORDER BY
  NEW.shopitemid, n);
  
  SELECT COUNT(*) FROM temp1 INTO n;
  SET i=0;
  
  WHILE i<n DO 
	
    INSERT INTO notifications(usernameA,usernameB,notification_type,message,date_created,referred_id, isreadA, isreadB) 
    VALUES(NEW.created_by, (SELECT name  FROM temp1 LIMIT i,1), 'shopping_list', NEW.title, NOW(), NEW.shopitemid,'N','N');
  
  SET i = i + 1;
  END WHILE;
	

END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `famdb`.`shopping_list_AFTER_UPDATE` AFTER UPDATE ON `shopping_list` FOR EACH ROW
BEGIN
DECLARE n INT DEFAULT 0;
DECLARE i INT DEFAULT 0;
	
    
    
  CREATE TEMPORARY TABLE temp1 (SELECT
  
  SUBSTRING_INDEX(SUBSTRING_INDEX(NEW.assigned_to, ',', numbers.n), ',', -1) name
  
FROM
  numbers INNER JOIN shopping_list
  ON CHAR_LENGTH(NEW.assigned_to)
     -CHAR_LENGTH(REPLACE(NEW.assigned_to, ',', ''))>=numbers.n-1
	WHERE shopping_list.shopitemid=NEW.shopitemid
ORDER BY
  NEW.shopitemid, n);
  
  SELECT COUNT(*) FROM temp1 INTO n;
  SET i=0;
  
  WHILE i<n DO 
	
    INSERT INTO notifications(usernameA,usernameB,notification_type,message,date_created,referred_id, isreadA, isreadB) 
    VALUES(NEW.created_by, (SELECT name  FROM temp1 LIMIT i,1), 'shopping_list_update', NEW.title, NOW(), NEW.shopitemid,'N','N');
  
  SET i = i + 1;
  END WHILE;

END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `famdb`.`shopping_list_BEFORE_DELETE` BEFORE DELETE ON `shopping_list` FOR EACH ROW
BEGIN
DECLARE n INT DEFAULT 0;
DECLARE i INT DEFAULT 0;
	
    
    drop temporary table if exists temp1;
  CREATE TEMPORARY TABLE temp1 (SELECT
  
  SUBSTRING_INDEX(SUBSTRING_INDEX(OLD.assigned_to, ',', numbers.n), ',', -1) name
  
FROM
  numbers INNER JOIN shopping_list
  ON CHAR_LENGTH(OLD.assigned_to)
     -CHAR_LENGTH(REPLACE(OLD.assigned_to, ',', ''))>=numbers.n-1
	WHERE shopping_list.shopitemid=OLD.shopitemid
ORDER BY
  OLD.shopitemid, n);
  
  SELECT COUNT(*) FROM temp1 INTO n;
  SET i=0;
  
  WHILE i<n DO 
	
    INSERT INTO notifications(usernameA,usernameB,notification_type,message,date_created,referred_id, isreadA, isreadB) 
    VALUES(OLD.created_by, (SELECT name  FROM temp1 LIMIT i,1), 'shopping_list_delete', OLD.title, NOW(), OLD.shopitemid,'N','N');
  
  SET i = i + 1;
  END WHILE;

END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `todo_list`
--

DROP TABLE IF EXISTS `todo_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `todo_list` (
  `itemid` int(11) NOT NULL AUTO_INCREMENT,
  `director_username` varchar(45) NOT NULL,
  `title` varchar(45) NOT NULL,
  `assigned_to` varchar(100) DEFAULT NULL,
  `created_by` varchar(45) NOT NULL,
  `due_date` date DEFAULT NULL,
  `completed_date` date DEFAULT NULL,
  `status` varchar(45) NOT NULL,
  PRIMARY KEY (`itemid`,`director_username`),
  KEY `director_username_FK_idx` (`director_username`),
  KEY `dir_usn_FK_idx` (`director_username`),
  KEY `created_idx` (`created_by`),
  CONSTRAINT `direct` FOREIGN KEY (`director_username`) REFERENCES `user` (`username`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `famdb`.`todo_list_AFTER_INSERT` AFTER INSERT ON `todo_list` FOR EACH ROW
BEGIN
DECLARE n INT DEFAULT 0;
DECLARE i INT DEFAULT 0;
	
    
	drop temporary table if exists temp1;
  CREATE TEMPORARY TABLE temp1 (SELECT
  
  SUBSTRING_INDEX(SUBSTRING_INDEX(NEW.assigned_to, ',', numbers.n), ',', -1) name
  
FROM
  numbers INNER JOIN todo_list
  ON CHAR_LENGTH(NEW.assigned_to)
     -CHAR_LENGTH(REPLACE(NEW.assigned_to, ',', ''))>=numbers.n-1
	WHERE todo_list.itemid=NEW.itemid
ORDER BY
  NEW.itemid, n);
  
  SELECT COUNT(*) FROM temp1 INTO n;
  SET i=0;
  
  WHILE i<n DO 
	
    INSERT INTO notifications(usernameA,usernameB,notification_type,message,date_created,referred_id, isreadA, isreadB) 
    VALUES(NEW.created_by, (SELECT name  FROM temp1 LIMIT i,1), 'todo_list', NEW.title, NOW(), NEW.itemid,'N','N');
  
  SET i = i + 1;
  END WHILE;
	
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `famdb`.`todo_list_AFTER_UPDATE` AFTER UPDATE ON `todo_list` FOR EACH ROW
BEGIN
DECLARE n INT DEFAULT 0;
DECLARE i INT DEFAULT 0;
	
    
    DROP TEMPORARY TABLE IF EXISTS temp1;
  CREATE TEMPORARY TABLE temp1 (SELECT
  
  SUBSTRING_INDEX(SUBSTRING_INDEX(NEW.assigned_to, ',', numbers.n), ',', -1) name
  
FROM
  numbers INNER JOIN todo_list
  ON CHAR_LENGTH(NEW.assigned_to)
     -CHAR_LENGTH(REPLACE(NEW.assigned_to, ',', ''))>=numbers.n-1
	WHERE todo_list.itemid=NEW.itemid
ORDER BY
  NEW.itemid, n);
  
  SELECT COUNT(*) FROM temp1 INTO n;
  SET i=0;
  
  WHILE i<n DO 
	
    INSERT INTO notifications(usernameA,usernameB,notification_type,message,date_created,referred_id, isreadA, isreadB) 
    VALUES(NEW.created_by, (SELECT name  FROM temp1 LIMIT i,1), 'todo_list_update', NEW.title, NOW(), NEW.itemid,'N','N');
  
  SET i = i + 1;
  END WHILE;
	
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `famdb`.`todo_list_BEFORE_DELETE` BEFORE DELETE ON `todo_list` FOR EACH ROW
BEGIN
DECLARE n INT DEFAULT 0;
DECLARE i INT DEFAULT 0;
	
    
    DROP TEMPORARY TABLE IF EXISTS temp1;
  CREATE TEMPORARY TABLE temp1 (SELECT
  
  SUBSTRING_INDEX(SUBSTRING_INDEX(OLD.assigned_to, ',', numbers.n), ',', -1) name
  
FROM
  numbers INNER JOIN todo_list
  ON CHAR_LENGTH(OLD.assigned_to)
     -CHAR_LENGTH(REPLACE(OLD.assigned_to, ',', ''))>=numbers.n-1
	WHERE todo_list.itemid=OLD.itemid
ORDER BY
  OLD.itemid, n);
  
  SELECT COUNT(*) FROM temp1 INTO n;
  SET i=0;
  
  WHILE i<n DO 
	
    INSERT INTO notifications(usernameA,usernameB,notification_type,message,date_created,referred_id, isreadA, isreadB) 
    VALUES(OLD.created_by, (SELECT name  FROM temp1 LIMIT i,1), 'todo_list_delete', OLD.title, NOW(), OLD.itemid,'N','N');
  
  SET i = i + 1;
  END WHILE;
	
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `firstname` varchar(45) NOT NULL,
  `lastname` varchar(45) NOT NULL,
  `birthdate` date NOT NULL,
  `email` varchar(45) NOT NULL,
  `town` varchar(45) DEFAULT NULL,
  `director` char(1) NOT NULL DEFAULT 'N',
  `picture` longblob,
  PRIMARY KEY (`username`,`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `wallpost`
--

DROP TABLE IF EXISTS `wallpost`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wallpost` (
  `idWallPost` int(11) NOT NULL AUTO_INCREMENT,
  `director_username` varchar(45) NOT NULL,
  `created_by` varchar(45) NOT NULL,
  `message` varchar(250) DEFAULT NULL,
  `creation_time` datetime NOT NULL,
  `file` longblob,
  PRIMARY KEY (`idWallPost`,`director_username`,`created_by`),
  KEY `createdFK_idx` (`created_by`),
  KEY `dirusernameFK_idx` (`director_username`),
  CONSTRAINT `created_b` FOREIGN KEY (`created_by`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `director_us` FOREIGN KEY (`director_username`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `famdb`.`wallpost_AFTER_INSERT` AFTER INSERT ON `wallpost` FOR EACH ROW
BEGIN

DECLARE n INT DEFAULT 0;
DECLARE i INT DEFAULT 0;

	 DROP TEMPORARY TABLE IF EXISTS temp1;
	CREATE TEMPORARY TABLE temp1 (SELECT familymember_username from directs_users where (director_username= NEW.director_username and familymember_username!=NEW.created_by));
    
    SELECT COUNT(*) FROM temp1 INTO n;
  SET i=0;
  
  WHILE i<n DO 
	
    INSERT INTO notifications(usernameA,usernameB,notification_type,message,date_created,referred_id, isreadA, isreadB) 
    VALUES(NEW.created_by, (SELECT familymember_username  FROM temp1 LIMIT i,1), 'wall_post', 'post', NOW(), NEW.idWallPost,'N','N');
  
  SET i = i + 1;
  END WHILE;
    
  
  

END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Dumping events for database 'famdb'
--

--
-- Dumping routines for database 'famdb'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-07-14  5:38:24
