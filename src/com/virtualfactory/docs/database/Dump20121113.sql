-- MySQL dump 10.13  Distrib 5.5.15, for Win32 (x86)
--
-- Host: localhost    Database: gaming
-- ------------------------------------------------------
-- Server version	5.5.16

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
-- Current Database: `gaming`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `gaming` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `gaming`;

--
-- Table structure for table `t_activity`
--

DROP TABLE IF EXISTS `t_activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_activity` (
  `id_activity` int(11) NOT NULL,
  `id_game` int(11) NOT NULL,
  `id_part` int(11) DEFAULT NULL,
  `activity_description` varchar(50) DEFAULT NULL,
  `type_activity` varchar(10) DEFAULT NULL,
  `id_order_activity` int(11) DEFAULT NULL,
  `id_next_activity` int(11) DEFAULT NULL,
  `processing_time` int(11) DEFAULT NULL,
  `activity_state` varchar(100) DEFAULT NULL,
  `activity_status` varchar(20) DEFAULT NULL,
  `cost_per_execution` decimal(5,2) DEFAULT NULL,
  `machine_category` varchar(45) DEFAULT NULL,
  `priority_queue` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_activity`,`id_game`),
  KEY `fk_id_game_0011` (`id_game`),
  KEY `fk_activity_part` (`id_part`),
  CONSTRAINT `fk_activity_part` FOREIGN KEY (`id_part`) REFERENCES `t_part` (`id_part`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_id_game_0011` FOREIGN KEY (`id_game`) REFERENCES `t_game` (`id_game`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_activity`
--

LOCK TABLES `t_activity` WRITE;
/*!40000 ALTER TABLE `t_activity` DISABLE KEYS */;
INSERT INTO `t_activity` VALUES (1,0,1,'Purchase Part1','Purchase',1,4,8,'','',10.00,'',7),(1,1,1,'Purchase Part1','Purchase',1,2,0,'','',0.00,'',7),(1,2,1,'Purchase Part1','Purchase',1,3,0,'','',0.00,'',10),(1,3,1,'Purchase Part1','Purchase',1,4,0,'','',0.00,'',10),(2,0,2,'Purchase Part2','Purchase',2,5,8,'','',10.00,'',7),(2,1,1,'Part1 from Purchase to StorageRM','Transport',2,3,0,'','',0.00,'Transport',6),(2,2,2,'Purchase Part2','Purchase',2,4,0,'','',0.00,'',10),(2,3,2,'Purchase Part2','Purchase',2,5,0,'','',0.00,'',10),(3,0,3,'Assemble Part3','Operation',3,6,5,'','',10.00,'Operation',4),(3,1,1,'Part1 from StorageRM to Assemble','Transport',3,4,0,'','',0.00,'Transport',5),(3,2,1,'Part1 from Purchase to StorageRM','Transport',3,5,0,'','',0.00,'Transport',9),(3,3,3,'Purchase Part3','Purchase',3,6,0,'','',0.00,'',10),(4,0,1,'Part1 from Purchase to StorageRM','Transport',4,9,4,'','',10.00,'Transport',6),(4,1,2,'Assemble Part2','Operation',4,5,5,'','',0.00,'Operation',4),(4,2,2,'Part2 from Purchase to StorageRM','Transport',4,6,0,'','',0.00,'Transport',9),(4,3,1,'Part1 from Purchase to StorageRM','Transport',4,7,0,'','',0.00,'Transport',9),(5,0,2,'Part2 from Purchase to StorageRM','Transport',5,10,4,'','',10.00,'Transport',6),(5,1,2,'Part2 from Assemble to StorageFG','Transport',5,6,0,'','',0.00,'Transport',3),(5,2,1,'Part1 from StorageRM to Assemble','Transport',5,7,0,'','',0.00,'Transport',8),(5,3,2,'Part2 from Purchase to StorageRM','Transport',5,8,0,'','',0.00,'Transport',9),(6,0,3,'Part3 from Assembly to StorageFG','Transport',6,7,5,'','',10.00,'Transport',3),(6,1,2,'Part2 from StorageFG to Shipping','Transport',6,7,0,'','',0.00,'Transport',2),(6,2,2,'Part2 from StorageRM to Assemble','Transport',6,7,0,'','',0.00,'Transport',8),(6,3,3,'Part3 from Purchase to StorageRM','Transport',6,12,0,'','',0.00,'Transport',9),(7,0,3,'Part3 from StorageFG to Shipping','Transport',7,8,6,'','',10.00,'Transport',2),(7,1,2,'Ship Part2','Ship',7,0,10,'','',0.00,'',1),(7,2,3,'Assemble Part3','Operation',7,8,5,'','',0.00,'Operation',7),(7,3,1,'Part1 from StorageRM to Assemble','Transport',7,9,0,'','',0.00,'Transport',8),(8,0,3,'Ship Part3','Ship',8,0,10,'','',10.00,'',1),(8,2,3,'Part3 from Assemble to StorageIG','Transport',8,9,0,'','',0.00,'Transport',6),(8,3,2,'Part2 from StorageRM to Assemble','Transport',8,9,0,'','',0.00,'Transport',8),(9,0,1,'Part1 from StorageRM to Assemble','Transport',9,3,0,'','',10.00,'Transport',5),(9,2,3,'Part3 from StorageIG to Assemble2','Transport',9,10,0,'','',0.00,'Transport',5),(9,3,4,'Assemble Part4','Operation',9,10,5,'','',0.00,'Operation',7),(10,0,2,'Part2 from StorageRM to Assemble','Transport',10,3,0,'','',10.00,'Transport',5),(10,2,4,'Assemble Part4','Operation',10,11,5,'','',0.00,'Operation',4),(10,3,4,'Part4 from Assemble to StorageIG','Transport',10,11,0,'','',0.00,'Transport',6),(11,2,4,'Part4 from Assemble2 to StorageFG','Transport',11,12,0,'','',0.00,'Transport',3),(11,3,4,'Part4 from StorageIG to Assemble2','Transport',11,13,0,'','',0.00,'Transport',5),(12,2,4,'Part4 from StorageFG to Shipping','Transport',12,13,0,'','',0.00,'Transport',2),(12,3,3,'Part3 from StorageRM to Assemble2','Transport',12,13,0,'','',0.00,'Transport',5),(13,2,4,'Ship Part4','Ship',13,0,10,'','',0.00,'',1),(13,3,5,'Assemble Part5','Operation',13,14,5,'','',0.00,'Operation',4),(14,3,5,'Part5 from Assemble2 to StorageFG','Transport',14,15,0,'','',0.00,'Transport',3),(15,3,5,'Part5 from StorageFG to Shipping','Transport',15,16,0,'','',0.00,'Transport',2),(16,3,5,'Ship Part5','Ship',16,0,10,'','',0.00,'',1);
/*!40000 ALTER TABLE `t_activity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_assembly_details`
--

DROP TABLE IF EXISTS `t_assembly_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_assembly_details` (
  `id_output_part` int(11) NOT NULL,
  `id_input_part` int(11) NOT NULL,
  `id_game` int(11) NOT NULL,
  `quantity` int(11) DEFAULT NULL,
  `assembly_description` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_output_part`,`id_input_part`,`id_game`),
  KEY `assembly_output` (`id_output_part`),
  KEY `assembly_input` (`id_input_part`),
  KEY `assembly_game` (`id_game`),
  CONSTRAINT `assembly_game` FOREIGN KEY (`id_game`) REFERENCES `t_game` (`id_game`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `assembly_input` FOREIGN KEY (`id_input_part`) REFERENCES `t_part` (`id_part`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `assembly_output` FOREIGN KEY (`id_output_part`) REFERENCES `t_part` (`id_part`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_assembly_details`
--

LOCK TABLES `t_assembly_details` WRITE;
/*!40000 ALTER TABLE `t_assembly_details` DISABLE KEYS */;
INSERT INTO `t_assembly_details` VALUES (2,1,1,1,'Part Two requires 1 unit of Part One'),(3,1,0,2,'Part3 requires 2 units of Part1'),(3,1,2,1,'Part Three requires 1 unit of Part One'),(3,2,0,1,'Part3 requires 1 unit of Part2'),(3,2,2,2,'Part Three requires 2 unit of Part Two'),(4,1,3,1,'Part Four requires 1 unit of Part One'),(4,2,3,2,'Part Four requires 2 unit of Part Two'),(4,3,2,2,'Part Four requires 2 unit of Part Three'),(5,3,3,2,'Part Five requires 2 unit of Part Three'),(5,4,3,1,'Part Five requires 1 unit of Part Four');
/*!40000 ALTER TABLE `t_assembly_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_bucket`
--

DROP TABLE IF EXISTS `t_bucket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_bucket` (
  `id_bucket` int(11) NOT NULL,
  `id_station` int(11) NOT NULL,
  `id_game` int(11) NOT NULL,
  `capacity` int(11) DEFAULT NULL,
  `unit` varchar(10) DEFAULT NULL,
  `size` int(11) DEFAULT NULL,
  `id_part` int(11) NOT NULL,
  `direction` varchar(10) DEFAULT NULL,
  `units_to_arrive` int(11) DEFAULT NULL,
  `units_to_remove` int(11) DEFAULT NULL,
  `state` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_bucket`,`id_station`,`id_game`),
  KEY `fk_id_station_0001` (`id_station`),
  KEY `fk_id_part_0001x` (`id_part`),
  KEY `bucket_game` (`id_game`),
  CONSTRAINT `bucket_game` FOREIGN KEY (`id_game`) REFERENCES `t_game` (`id_game`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_id_part_0001x` FOREIGN KEY (`id_part`) REFERENCES `t_part` (`id_part`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_id_station_0001` FOREIGN KEY (`id_station`) REFERENCES `t_station` (`id_station`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_bucket`
--

LOCK TABLES `t_bucket` WRITE;
/*!40000 ALTER TABLE `t_bucket` DISABLE KEYS */;
INSERT INTO `t_bucket` VALUES (1,1,0,80,'PART',0,1,'Input',0,0,'Active'),(1,2,0,200,'PART',0,1,'Output',0,0,'Active'),(1,3,0,500,'PART',0,1,'Both',0,0,'Active'),(1,3,1,50,'PART',0,1,'Output',0,0,'Active'),(1,3,2,50,'PART',0,1,'Output',0,0,'Active'),(1,3,3,50,'PART',0,1,'Output',0,0,'Active'),(1,4,0,100,'PART',0,1,'Input',0,0,'Active'),(1,4,1,50,'PART',0,1,'Both',0,0,'Active'),(1,4,2,50,'PART',0,1,'Both',0,0,'Active'),(1,4,3,50,'PART',0,1,'Both',0,0,'Active'),(1,5,1,50,'PART',0,1,'Input',0,0,'Active'),(1,5,2,20,'PART',0,1,'Input',0,0,'Active'),(1,5,3,20,'PART',0,1,'Input',0,0,'Active'),(1,6,1,50,'PART',0,2,'Both',0,0,'Active'),(1,6,2,20,'PART',0,3,'Both',0,0,'Active'),(1,6,3,20,'PART',0,4,'Both',0,0,'Active'),(1,7,0,100,'PART',0,1,'Both',0,0,'Active'),(1,7,1,50,'PART',0,2,'Input',0,0,'Active'),(1,7,2,20,'PART',0,3,'Input',0,0,'Active'),(1,7,3,20,'PART',0,3,'Input',0,0,'Active'),(1,8,2,20,'PART',0,4,'Both',0,0,'Active'),(1,8,3,20,'PART',0,5,'Both',0,0,'Active'),(1,9,2,30,'PART',0,4,'Input',0,0,'Active'),(1,9,3,30,'PART',0,5,'Input',0,0,'Active'),(2,1,0,80,'PART',0,2,'Input',0,0,'Active'),(2,2,0,100,'PART',0,2,'Output',0,0,'Active'),(2,3,0,250,'PART',0,2,'Both',0,0,'Active'),(2,3,1,50,'PART',0,1,'Output',0,0,'Active'),(2,3,2,50,'PART',0,1,'Output',0,0,'Active'),(2,3,3,50,'PART',0,1,'Output',0,0,'Active'),(2,4,0,100,'PART',0,2,'Input',0,0,'Active'),(2,4,1,50,'PART',0,1,'Both',0,0,'Inactive'),(2,4,2,50,'PART',0,1,'Both',0,0,'Inactive'),(2,4,3,50,'PART',0,1,'Both',0,0,'Inactive'),(2,5,1,50,'PART',0,1,'Input',0,0,'Active'),(2,5,2,20,'PART',0,1,'Input',0,0,'Active'),(2,5,3,20,'PART',0,1,'Input',0,0,'Active'),(2,6,1,50,'PART',0,2,'Both',0,0,'Inactive'),(2,6,2,20,'PART',0,3,'Both',0,0,'Inactive'),(2,6,3,20,'PART',0,4,'Both',0,0,'Inactive'),(2,7,0,100,'PART',0,2,'Both',0,0,'Active'),(2,7,1,50,'PART',0,2,'Input',0,0,'Active'),(2,7,2,20,'PART',0,3,'Input',0,0,'Active'),(2,7,3,20,'PART',0,3,'Input',0,0,'Active'),(2,8,2,20,'PART',0,4,'Both',0,0,'Inactive'),(2,8,3,20,'PART',0,5,'Both',0,0,'Inactive'),(2,9,2,30,'PART',0,4,'Input',0,0,'Active'),(2,9,3,30,'PART',0,5,'Input',0,0,'Active'),(3,1,0,25,'PART',0,3,'Output',0,0,'Active'),(3,3,0,125,'PART',0,3,'Both',0,0,'Active'),(3,3,2,50,'PART',0,2,'Output',0,0,'Active'),(3,3,3,50,'PART',0,2,'Output',0,0,'Active'),(3,4,0,100,'PART',0,3,'Input',0,0,'Active'),(3,4,2,50,'PART',0,2,'Both',0,0,'Active'),(3,4,3,50,'PART',0,2,'Both',0,0,'Active'),(3,5,1,50,'PART',0,2,'Output',0,0,'Active'),(3,5,2,20,'PART',0,2,'Input',0,0,'Active'),(3,5,3,20,'PART',0,2,'Input',0,0,'Active'),(3,7,2,20,'PART',0,4,'Output',0,0,'Active'),(3,7,3,20,'PART',0,4,'Input',0,0,'Active'),(4,3,2,50,'PART',0,2,'Output',0,0,'Active'),(4,3,3,50,'PART',0,2,'Output',0,0,'Active'),(4,4,2,50,'PART',0,2,'Both',0,0,'Inactive'),(4,4,3,50,'PART',0,2,'Both',0,0,'Inactive'),(4,5,1,50,'PART',0,2,'Output',0,0,'Active'),(4,5,2,20,'PART',0,2,'Input',0,0,'Active'),(4,5,3,20,'PART',0,2,'Input',0,0,'Active'),(4,7,2,20,'PART',0,4,'Output',0,0,'Active'),(4,7,3,20,'PART',0,4,'Input',0,0,'Active'),(5,3,3,50,'PART',0,3,'Output',0,0,'Active'),(5,4,3,50,'PART',0,3,'Both',0,0,'Active'),(5,5,2,20,'PART',0,3,'Output',0,0,'Active'),(5,5,3,20,'PART',0,4,'Output',0,0,'Active'),(5,7,3,20,'PART',0,5,'Output',0,0,'Active'),(6,3,3,50,'PART',0,3,'Output',0,0,'Active'),(6,4,3,50,'PART',0,3,'Both',0,0,'Inactive'),(6,5,2,20,'PART',0,3,'Output',0,0,'Active'),(6,5,3,20,'PART',0,4,'Output',0,0,'Active'),(6,7,3,20,'PART',0,5,'Output',0,0,'Active');
/*!40000 ALTER TABLE `t_bucket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_catalog`
--

DROP TABLE IF EXISTS `t_catalog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_catalog` (
  `id_supplier` int(11) NOT NULL,
  `id_part` int(11) NOT NULL,
  `id_game` int(11) NOT NULL,
  `quality` decimal(10,2) DEFAULT NULL,
  `production_distn` varchar(10) DEFAULT NULL,
  `production_parameter1` decimal(10,2) DEFAULT NULL,
  `production_parameter2` decimal(10,2) DEFAULT NULL,
  `price_function1_limit` int(11) DEFAULT NULL,
  `price_function1_charge` decimal(10,2) DEFAULT NULL,
  `price_function2_limit` int(11) DEFAULT NULL,
  `price_function2_charge` decimal(10,2) DEFAULT NULL,
  `price_function3_limit` int(11) DEFAULT NULL,
  `price_function3_charge` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id_supplier`,`id_part`,`id_game`),
  KEY `fk_{F87663D2-AB54-4FFA-AB68-B965923832D7}` (`id_part`),
  KEY `fk_{C894B249-C7A7-4CC7-9E3E-ADA16FFF782E}` (`id_supplier`),
  KEY `catalog_game` (`id_game`),
  CONSTRAINT `catalog_game` FOREIGN KEY (`id_game`) REFERENCES `t_game` (`id_game`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_{C894B249-C7A7-4CC7-9E3E-ADA16FFF782E}` FOREIGN KEY (`id_supplier`) REFERENCES `t_supplier` (`id_supplier`),
  CONSTRAINT `fk_{F87663D2-AB54-4FFA-AB68-B965923832D7}` FOREIGN KEY (`id_part`) REFERENCES `t_part` (`id_part`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_catalog`
--

LOCK TABLES `t_catalog` WRITE;
/*!40000 ALTER TABLE `t_catalog` DISABLE KEYS */;
INSERT INTO `t_catalog` VALUES (1,1,0,0.00,'',480.00,0.00,5,20.00,15,10.00,100,5.00),(1,1,1,0.00,'',480.00,0.00,5,18.00,15,12.00,100,4.00),(1,1,2,0.00,'',480.00,0.00,5,18.00,15,12.00,100,4.00),(1,1,3,0.00,'',480.00,0.00,5,20.00,15,10.00,100,5.00),(1,2,0,0.00,'',480.00,0.00,5,18.00,15,12.00,100,4.00),(1,2,2,0.00,'',480.00,0.00,5,18.00,15,12.00,100,4.00),(1,2,3,0.00,'',480.00,0.00,5,18.00,15,12.00,100,4.00),(1,3,3,0.00,'',480.00,0.00,5,20.00,15,10.00,100,5.00),(2,1,1,0.00,'',480.00,0.00,5,20.00,15,10.00,100,5.00),(2,1,2,0.00,'',480.00,0.00,5,20.00,15,10.00,100,5.00),(2,1,3,0.00,'',480.00,0.00,5,18.00,15,12.00,100,4.00),(2,2,2,0.00,'',480.00,0.00,5,20.00,15,10.00,100,5.00),(2,2,3,0.00,'',480.00,0.00,5,20.00,15,10.00,100,5.00),(2,3,3,0.00,'',480.00,0.00,5,18.00,15,12.00,100,4.00);
/*!40000 ALTER TABLE `t_catalog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_game`
--

DROP TABLE IF EXISTS `t_game`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_game` (
  `id_game` int(11) NOT NULL,
  `date_time` int(11) DEFAULT NULL,
  `id_player` int(11) NOT NULL,
  `current_money` decimal(10,2) DEFAULT NULL,
  `current_minute` int(11) DEFAULT '0',
  `current_hour` int(11) DEFAULT NULL,
  `current_day` int(11) DEFAULT NULL,
  `current_month` int(11) DEFAULT NULL,
  `current_game_time` double DEFAULT NULL,
  `time_factor` double DEFAULT NULL,
  `game_type` varchar(45) DEFAULT NULL,
  `game_category` varchar(45) DEFAULT NULL,
  `description` varchar(300) DEFAULT NULL,
  `initial_money` double DEFAULT NULL,
  `percentage_to_win` decimal(10,2) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `game_status` varchar(45) DEFAULT NULL,
  `id_phase` int(11) DEFAULT NULL,
  `next_id_phase` int(11) DEFAULT NULL,
  `id_terrain` int(11) DEFAULT NULL,
  `objective` varchar(100) DEFAULT NULL,
  `flow_image` varchar(100) DEFAULT NULL,
  `attempt_numbers` int(11) DEFAULT NULL,
  `overhead` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id_game`,`id_player`),
  KEY `fkn_id_player_0001` (`id_player`),
  KEY `terrain_game` (`id_terrain`),
  CONSTRAINT `fkn_id_player_0001` FOREIGN KEY (`id_player`) REFERENCES `t_player` (`id_player`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `terrain_game` FOREIGN KEY (`id_terrain`) REFERENCES `t_terrain` (`id_terrain`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_game`
--

LOCK TABLES `t_game` WRITE;
/*!40000 ALTER TABLE `t_game` DISABLE KEYS */;
INSERT INTO `t_game` VALUES (0,1352663908,1,0.00,0,0,1,1,0,1,'CPU','Normal','Activities: 2 purchases, 6 transports, 1 assembly. \nOrders: total 10, maximum 8 to fail',5000,40.00,'Level 2','Unlocked',2,3,0,'Create and send part 3 when the orders arrive','Level2.png',0,60.00),(0,1352663908,3,0.00,0,0,1,1,0,1,'USER','Normal','Activities: 2 purchases, 6 transports, 1 assembly. \nOrders: total 10, maximum 8 to fail',5000,40.00,'Level 2','Unlocked',2,3,0,'Create and send part 3 when the orders arrive','Level2.png',0,60.00),(0,1352663908,4,0.00,0,0,1,1,151.5120000000029,1,'USER','Normal','Activities: 2 purchases, 6 transports, 1 assembly. \nOrders: total 10, maximum 8 to fail',5000,40.00,'Level 2','Unlocked',2,3,0,'Create and send part 3 when the orders arrive','Level2.png',0,60.00),(0,1352663908,5,0.00,0,0,1,1,0,1,'USER','Normal','Activities: 2 purchases, 6 transports, 1 assembly. \nOrders: total 10, maximum 8 to fail',5000,40.00,'Level 2','Unlocked',2,3,0,'Create and send part 3 when the orders arrive','Level2.png',0,60.00),(0,1352663908,6,0.00,0,0,1,1,0,1,'USER','Normal','Activities: 2 purchases, 6 transports, 1 assembly. \nOrders: total 10, maximum 8 to fail',5000,40.00,'Level 2','Unlocked',2,3,0,'Create and send part 3 when the orders arrive','Level2.png',0,60.00),(0,1352663908,7,0.00,0,0,1,1,0,1,'USER','Normal','Activities: 2 purchases, 6 transports, 1 assembly. \nOrders: total 10, maximum 8 to fail',5000,40.00,'Level 2','Unlocked',2,3,0,'Create and send part 3 when the orders arrive','Level2.png',0,60.00),(0,1352791112,8,5000.00,0,0,1,1,0,1,'USER','Normal','Activities: 2 purchases, 6 transports, 1 assembly. \nOrders: total 10, maximum 8 to fail',5000,40.00,'Level 2','Unlocked',2,3,0,'Create and send part 3 when the orders arrive','Level2.png',41,60.00),(0,1352663908,9,0.00,0,0,1,1,0,1,'USER','Normal','Activities: 2 purchases, 6 transports, 1 assembly. \nOrders: total 10, maximum 8 to fail',5000,40.00,'Level 2','Unlocked',2,3,0,'Create and send part 3 when the orders arrive','Level2.png',0,60.00),(0,1352663908,10,0.00,0,0,1,1,0,1,'USER','Normal','Activities: 2 purchases, 6 transports, 1 assembly. \nOrders: total 10, maximum 8 to fail',5000,40.00,'Level 2','Unlocked',2,3,0,'Create and send part 3 when the orders arrive','Level2.png',0,60.00),(0,1352663908,11,0.00,0,0,1,1,0,1,'USER','Normal','Activities: 2 purchases, 6 transports, 1 assembly. \nOrders: total 10, maximum 8 to fail',5000,40.00,'Level 2','Unlocked',2,3,0,'Create and send part 3 when the orders arrive','Level2.png',0,60.00),(0,1352663908,12,0.00,0,0,1,1,0,1,'USER','Normal','Activities: 2 purchases, 6 transports, 1 assembly. \nOrders: total 10, maximum 8 to fail',5000,40.00,'Level 2','Unlocked',2,3,0,'Create and send part 3 when the orders arrive','Level2.png',0,60.00),(0,1352663908,13,0.00,0,0,1,1,0,1,'USER','Normal','Activities: 2 purchases, 6 transports, 1 assembly. \nOrders: total 10, maximum 8 to fail',5000,40.00,'Level 2','Unlocked',2,3,0,'Create and send part 3 when the orders arrive','Level2.png',0,60.00),(0,1352663908,14,0.00,0,0,1,1,0,1,'USER','Normal','Activities: 2 purchases, 6 transports, 1 assembly. \nOrders: total 10, maximum 8 to fail',5000,40.00,'Level 2','Unlocked',2,3,0,'Create and send part 3 when the orders arrive','Level2.png',0,60.00),(0,1352663908,15,0.00,0,0,1,1,0,1,'USER','Normal','Activities: 2 purchases, 6 transports, 1 assembly. \nOrders: total 10, maximum 8 to fail',5000,40.00,'Level 2','Unlocked',2,3,0,'Create and send part 3 when the orders arrive','Level2.png',0,60.00),(0,1352663908,16,0.00,0,0,1,1,0,1,'USER','Normal','Activities: 2 purchases, 6 transports, 1 assembly. \nOrders: total 10, maximum 8 to fail',5000,40.00,'Level 2','Unlocked',2,3,0,'Create and send part 3 when the orders arrive','Level2.png',0,60.00),(0,1352663908,17,0.00,0,0,1,1,0,1,'USER','Normal','Activities: 2 purchases, 6 transports, 1 assembly. \nOrders: total 10, maximum 8 to fail',5000,40.00,'Level 2','Unlocked',2,3,0,'Create and send part 3 when the orders arrive','Level2.png',0,60.00),(0,1352663908,18,0.00,0,0,1,1,0,1,'USER','Normal','Activities: 2 purchases, 6 transports, 1 assembly. \nOrders: total 10, maximum 8 to fail',5000,40.00,'Level 2','Unlocked',2,3,0,'Create and send part 3 when the orders arrive','Level2.png',0,60.00),(0,1352663908,19,0.00,0,0,1,1,0,1,'USER','Normal','Activities: 2 purchases, 6 transports, 1 assembly. \nOrders: total 10, maximum 8 to fail',5000,40.00,'Level 2','Unlocked',2,3,0,'Create and send part 3 when the orders arrive','Level2.png',0,60.00),(0,1352663908,20,0.00,0,0,1,1,0,1,'USER','Normal','Activities: 2 purchases, 6 transports, 1 assembly. \nOrders: total 10, maximum 8 to fail',5000,40.00,'Level 2','Unlocked',2,3,0,'Create and send part 3 when the orders arrive','Level2.png',0,60.00),(0,1352663908,21,0.00,0,0,1,1,0,1,'USER','Normal','Activities: 2 purchases, 6 transports, 1 assembly. \nOrders: total 10, maximum 8 to fail',5000,40.00,'Level 2','Unlocked',2,3,0,'Create and send part 3 when the orders arrive','Level2.png',0,60.00),(0,1352663908,27,0.00,0,0,1,1,0,1,'USER','Normal','Activities: 2 purchases, 6 transports, 1 assembly. \nOrders: total 10, maximum 8 to fail',5000,40.00,'Level 2','Unlocked',2,3,0,'Create and send part 3 when the orders arrive','Level2.png',0,60.00),(0,1352671335,28,0.00,0,0,1,1,0,1,'USER','Normal','Activities: 2 purchases, 6 transports, 1 assembly. \nOrders: total 10, maximum 8 to fail',5000,40.00,'Level 2','Unlocked',2,3,0,'Create and send part 3 when the orders arrive','Level2.png',0,60.00),(0,1352671454,29,0.00,0,0,1,1,0,1,'USER','Normal','Activities: 2 purchases, 6 transports, 1 assembly. \nOrders: total 10, maximum 8 to fail',5000,40.00,'Level 2','Unlocked',2,3,0,'Create and send part 3 when the orders arrive','Level2.png',0,60.00),(0,1352672801,30,0.00,0,0,1,1,0,1,'USER','Normal','Activities: 2 purchases, 6 transports, 1 assembly. \nOrders: total 10, maximum 8 to fail',5000,40.00,'Level 2','Unlocked',2,3,0,'Create and send part 3 when the orders arrive','Level2.png',0,60.00),(0,1352792058,31,0.00,0,0,1,1,0,1,'USER','Normal','Activities: 2 purchases, 6 transports, 1 assembly. \nOrders: total 10, maximum 8 to fail',5000,40.00,'Level 2','Unlocked',2,3,0,'Create and send part 3 when the orders arrive','Level2.png',1,60.00),(1,1352791878,1,24521.48,0,0,1,1,0,1,'CPU','Easy','Activities: 1 purchase, 4 transports, 1 assembly. \nOrders: total 10, maximum 5 to fail',5000,40.00,'Level 1','Unlocked',1,2,0,'Create and ship part 2 when the orders arrive','Level1.png',0,60.00),(1,1352663908,6,0.00,0,0,1,1,0,1,'USER','Easy','Activities: 1 purchase, 4 transports, 1 assembly. \nOrders: total 10, maximum 5 to fail',5000,40.00,'Level 1','Unlocked',1,2,0,'Create and ship part 2 when the orders arrive','Level1.png',0,60.00),(1,1352663908,7,0.00,0,0,1,1,0,1,'USER','Easy','Activities: 1 purchase, 4 transports, 1 assembly. \nOrders: total 10, maximum 5 to fail',5000,40.00,'Level 1','Unlocked',1,2,0,'Create and ship part 2 when the orders arrive','Level1.png',0,60.00),(1,1352791112,8,25992.72,22,4,3,1,307.25399999997745,0.25,'USER','Easy','Activities: 1 purchase, 4 transports, 1 assembly. \nOrders: total 10, maximum 5 to fail',5000,40.00,'Level 1','Completed',1,2,0,'Create and ship part 2 when the orders arrive','Level1.png',224,60.00),(1,1352663908,9,0.00,0,0,1,1,0,1,'USER','Easy','Activities: 1 purchase, 4 transports, 1 assembly. \nOrders: total 10, maximum 5 to fail',5000,40.00,'Level 1','Unlocked',1,2,0,'Create and ship part 2 when the orders arrive','Level1.png',0,60.00),(1,1352663908,10,0.00,0,0,1,1,0,1,'USER','Easy','Activities: 1 purchase, 4 transports, 1 assembly. \nOrders: total 10, maximum 5 to fail',5000,40.00,'Level 1','Unlocked',1,2,0,'Create and ship part 2 when the orders arrive','Level1.png',0,60.00),(1,1352663908,11,0.00,0,0,1,1,0,1,'USER','Easy','Activities: 1 purchase, 4 transports, 1 assembly. \nOrders: total 10, maximum 5 to fail',5000,40.00,'Level 1','Unlocked',1,2,0,'Create and ship part 2 when the orders arrive','Level1.png',0,60.00),(1,1352663908,12,0.00,0,0,1,1,0,1,'USER','Easy','Activities: 1 purchase, 4 transports, 1 assembly. \nOrders: total 10, maximum 5 to fail',5000,40.00,'Level 1','Unlocked',1,2,0,'Create and ship part 2 when the orders arrive','Level1.png',0,60.00),(1,1352663908,13,0.00,0,0,1,1,0,1,'USER','Easy','Activities: 1 purchase, 4 transports, 1 assembly. \nOrders: total 10, maximum 5 to fail',5000,40.00,'Level 1','Unlocked',1,2,0,'Create and ship part 2 when the orders arrive','Level1.png',0,60.00),(1,1352663908,14,0.00,0,0,1,1,0,1,'USER','Easy','Activities: 1 purchase, 4 transports, 1 assembly. \nOrders: total 10, maximum 5 to fail',5000,40.00,'Level 1','Unlocked',1,2,0,'Create and ship part 2 when the orders arrive','Level1.png',0,60.00),(1,1352663908,15,0.00,0,0,1,1,0,1,'USER','Easy','Activities: 1 purchase, 4 transports, 1 assembly. \nOrders: total 10, maximum 5 to fail',5000,40.00,'Level 1','Unlocked',1,2,0,'Create and ship part 2 when the orders arrive','Level1.png',0,60.00),(1,1352663908,16,0.00,0,0,1,1,0,1,'USER','Easy','Activities: 1 purchase, 4 transports, 1 assembly. \nOrders: total 10, maximum 5 to fail',5000,40.00,'Level 1','Unlocked',1,2,0,'Create and ship part 2 when the orders arrive','Level1.png',0,60.00),(1,1352663908,17,0.00,0,0,1,1,0,1,'USER','Easy','Activities: 1 purchase, 4 transports, 1 assembly. \nOrders: total 10, maximum 5 to fail',5000,40.00,'Level 1','Unlocked',1,2,0,'Create and ship part 2 when the orders arrive','Level1.png',0,60.00),(1,1352663908,18,0.00,0,0,1,1,0,1,'USER','Easy','Activities: 1 purchase, 4 transports, 1 assembly. \nOrders: total 10, maximum 5 to fail',5000,40.00,'Level 1','Unlocked',1,2,0,'Create and ship part 2 when the orders arrive','Level1.png',0,60.00),(1,1352663908,19,0.00,0,0,1,1,0,1,'USER','Easy','Activities: 1 purchase, 4 transports, 1 assembly. \nOrders: total 10, maximum 5 to fail',5000,40.00,'Level 1','Unlocked',1,2,0,'Create and ship part 2 when the orders arrive','Level1.png',0,60.00),(1,1352663908,20,0.00,0,0,1,1,0,1,'USER','Easy','Activities: 1 purchase, 4 transports, 1 assembly. \nOrders: total 10, maximum 5 to fail',5000,40.00,'Level 1','Unlocked',1,2,0,'Create and ship part 2 when the orders arrive','Level1.png',0,60.00),(1,1352663908,21,0.00,0,0,1,1,0,1,'USER','Easy','Activities: 1 purchase, 4 transports, 1 assembly. \nOrders: total 10, maximum 5 to fail',5000,40.00,'Level 1','Unlocked',1,2,0,'Create and ship part 2 when the orders arrive','Level1.png',0,60.00),(1,1352663908,27,0.00,0,0,1,1,0,1,'USER','Easy','Activities: 1 purchase, 4 transports, 1 assembly. \nOrders: total 10, maximum 5 to fail',5000,40.00,'Level 1','Unlocked',1,2,0,'Create and ship part 2 when the orders arrive','Level1.png',0,60.00),(1,1352671335,28,0.00,0,0,1,1,0,1,'USER','Easy','Activities: 1 purchase, 4 transports, 1 assembly. \nOrders: total 10, maximum 5 to fail',5000,40.00,'Level 1','Unlocked',1,2,0,'Create and ship part 2 when the orders arrive','Level1.png',0,60.00),(1,1352671454,29,0.00,0,0,1,1,0,1,'USER','Easy','Activities: 1 purchase, 4 transports, 1 assembly. \nOrders: total 10, maximum 5 to fail',5000,40.00,'Level 1','Unlocked',1,2,0,'Create and ship part 2 when the orders arrive','Level1.png',0,60.00),(1,1352672801,30,0.00,0,0,1,1,0,1,'USER','Easy','Activities: 1 purchase, 4 transports, 1 assembly. \nOrders: total 10, maximum 5 to fail',5000,40.00,'Level 1','Unlocked',1,2,0,'Create and ship part 2 when the orders arrive','Level1.png',0,60.00),(1,1352791878,31,24521.48,54,4,3,1,79.81600000000236,0.0625,'USER','Easy','Activities: 1 purchase, 4 transports, 1 assembly. \nOrders: total 10, maximum 5 to fail',5000,40.00,'Level 1','Completed',1,2,0,'Create and ship part 2 when the orders arrive','Level1.png',4,60.00),(2,1352663908,1,0.00,0,0,1,1,0,1,'CPU','Normal','Activities: 2 purchases, 8 transports, 2 assembly. \nOrders: total 10, maximum 5 to fail',5000,40.00,'Level 3','Unlocked',3,4,0,'Create and ship part 4 when the orders arrive','Level3.png',0,60.00),(2,1352663908,7,0.00,0,0,1,1,0,1,'USER','Normal','Activities: 2 purchases, 8 transports, 2 assembly. \nOrders: total 10, maximum 5 to fail',5000,40.00,'Level 3','Unlocked',3,4,0,'Create and ship part 4 when the orders arrive','Level3.png',0,60.00),(2,1352789699,8,0.00,0,0,1,1,0,1,'USER','Normal','Activities: 2 purchases, 8 transports, 2 assembly. \nOrders: total 10, maximum 5 to fail',5000,40.00,'Level 3','Unlocked',3,4,0,'Create and ship part 4 when the orders arrive','Level3.png',42,60.00),(2,1352663908,9,0.00,0,0,1,1,0,1,'USER','Normal','Activities: 2 purchases, 8 transports, 2 assembly. \nOrders: total 10, maximum 5 to fail',5000,40.00,'Level 3','Unlocked',3,4,0,'Create and ship part 4 when the orders arrive','Level3.png',0,60.00),(2,1352663908,10,0.00,0,0,1,1,0,1,'USER','Normal','Activities: 2 purchases, 8 transports, 2 assembly. \nOrders: total 10, maximum 5 to fail',5000,40.00,'Level 3','Unlocked',3,4,0,'Create and ship part 4 when the orders arrive','Level3.png',0,60.00),(2,1352663908,11,0.00,0,0,1,1,0,1,'USER','Normal','Activities: 2 purchases, 8 transports, 2 assembly. \nOrders: total 10, maximum 5 to fail',5000,40.00,'Level 3','Unlocked',3,4,0,'Create and ship part 4 when the orders arrive','Level3.png',1,60.00),(2,1352663908,12,0.00,0,0,1,1,0,1,'USER','Normal','Activities: 2 purchases, 8 transports, 2 assembly. \nOrders: total 10, maximum 5 to fail',5000,40.00,'Level 3','Unlocked',3,4,0,'Create and ship part 4 when the orders arrive','Level3.png',0,60.00),(2,1352663908,13,0.00,0,0,1,1,0,1,'USER','Normal','Activities: 2 purchases, 8 transports, 2 assembly. \nOrders: total 10, maximum 5 to fail',5000,40.00,'Level 3','Unlocked',3,4,0,'Create and ship part 4 when the orders arrive','Level3.png',1,60.00),(2,1352663908,14,0.00,0,0,1,1,0,1,'USER','Normal','Activities: 2 purchases, 8 transports, 2 assembly. \nOrders: total 10, maximum 5 to fail',5000,40.00,'Level 3','Unlocked',3,4,0,'Create and ship part 4 when the orders arrive','Level3.png',0,60.00),(2,1352663908,15,0.00,0,0,1,1,0,1,'USER','Normal','Activities: 2 purchases, 8 transports, 2 assembly. \nOrders: total 10, maximum 5 to fail',5000,40.00,'Level 3','Unlocked',3,4,0,'Create and ship part 4 when the orders arrive','Level3.png',0,60.00),(2,1352663908,16,0.00,0,0,1,1,0,1,'USER','Normal','Activities: 2 purchases, 8 transports, 2 assembly. \nOrders: total 10, maximum 5 to fail',5000,40.00,'Level 3','Unlocked',3,4,0,'Create and ship part 4 when the orders arrive','Level3.png',0,60.00),(2,1352663908,17,0.00,0,0,1,1,0,1,'USER','Normal','Activities: 2 purchases, 8 transports, 2 assembly. \nOrders: total 10, maximum 5 to fail',5000,40.00,'Level 3','Unlocked',3,4,0,'Create and ship part 4 when the orders arrive','Level3.png',0,60.00),(2,1352663908,18,0.00,0,0,1,1,0,1,'USER','Normal','Activities: 2 purchases, 8 transports, 2 assembly. \nOrders: total 10, maximum 5 to fail',5000,40.00,'Level 3','Unlocked',3,4,0,'Create and ship part 4 when the orders arrive','Level3.png',0,60.00),(2,1352663908,19,0.00,0,0,1,1,0,1,'USER','Normal','Activities: 2 purchases, 8 transports, 2 assembly. \nOrders: total 10, maximum 5 to fail',5000,40.00,'Level 3','Unlocked',3,4,0,'Create and ship part 4 when the orders arrive','Level3.png',0,60.00),(2,1352663908,20,0.00,0,0,1,1,0,1,'USER','Normal','Activities: 2 purchases, 8 transports, 2 assembly. \nOrders: total 10, maximum 5 to fail',5000,40.00,'Level 3','Unlocked',3,4,0,'Create and ship part 4 when the orders arrive','Level3.png',0,60.00),(2,1352663908,21,0.00,0,0,1,1,0,1,'USER','Normal','Activities: 2 purchases, 8 transports, 2 assembly. \nOrders: total 10, maximum 5 to fail',5000,40.00,'Level 3','Unlocked',3,4,0,'Create and ship part 4 when the orders arrive','Level3.png',0,60.00),(2,1352663908,27,0.00,0,0,1,1,0,1,'USER','Normal','Activities: 2 purchases, 8 transports, 2 assembly. \nOrders: total 10, maximum 5 to fail',5000,40.00,'Level 3','Unlocked',3,4,0,'Create and ship part 4 when the orders arrive','Level3.png',0,60.00),(2,1352671335,28,0.00,0,0,1,1,0,1,'USER','Normal','Activities: 2 purchases, 8 transports, 2 assembly. \nOrders: total 10, maximum 5 to fail',5000,40.00,'Level 3','Unlocked',3,4,0,'Create and ship part 4 when the orders arrive','Level3.png',0,60.00),(2,1352671454,29,0.00,0,0,1,1,0,1,'USER','Normal','Activities: 2 purchases, 8 transports, 2 assembly. \nOrders: total 10, maximum 5 to fail',5000,40.00,'Level 3','Unlocked',3,4,0,'Create and ship part 4 when the orders arrive','Level3.png',0,60.00),(2,1352672801,30,0.00,0,0,1,1,0,1,'USER','Normal','Activities: 2 purchases, 8 transports, 2 assembly. \nOrders: total 10, maximum 5 to fail',5000,40.00,'Level 3','Unlocked',3,4,0,'Create and ship part 4 when the orders arrive','Level3.png',0,60.00),(2,1352793144,31,0.00,0,0,1,1,0,1,'USER','Normal','Activities: 2 purchases, 8 transports, 2 assembly. \nOrders: total 10, maximum 5 to fail',5000,40.00,'Level 3','Unlocked',3,4,0,'Create and ship part 4 when the orders arrive','Level3.png',2,60.00),(3,1352663908,1,0.00,0,0,1,1,0,1,'CPU','Hard','Activities: 3 purchases, 10 transports, 2 assembly. \nOrders: total 10, maximum 5 to fail',5000,40.00,'Level 4','Unlocked',4,5,0,'Create and ship part 5 when the orders arrive','Level4.png',0,60.00),(3,1352789189,8,0.00,0,0,1,1,0,1,'USER','Hard','Activities: 3 purchases, 10 transports, 2 assembly. \nOrders: total 10, maximum 5 to fail',5000,40.00,'Level 4','Unlocked',4,5,0,'Create and ship part 5 when the orders arrive','Level4.png',52,60.00),(3,1352663908,9,0.00,0,0,1,1,0,1,'USER','Hard','Activities: 3 purchases, 10 transports, 2 assembly. \nOrders: total 10, maximum 5 to fail',5000,40.00,'Level 4','Unlocked',4,5,0,'Create and ship part 5 when the orders arrive','Level4.png',0,60.00),(3,1352663908,10,0.00,0,0,1,1,0,1,'USER','Hard','Activities: 3 purchases, 10 transports, 2 assembly. \nOrders: total 10, maximum 5 to fail',5000,40.00,'Level 4','Unlocked',4,5,0,'Create and ship part 5 when the orders arrive','Level4.png',0,60.00),(3,1352663908,11,0.00,0,0,1,1,0,1,'USER','Hard','Activities: 3 purchases, 10 transports, 2 assembly. \nOrders: total 10, maximum 5 to fail',5000,40.00,'Level 4','Unlocked',4,5,0,'Create and ship part 5 when the orders arrive','Level4.png',1,60.00),(3,1352663908,12,0.00,0,0,1,1,0,1,'USER','Hard','Activities: 3 purchases, 10 transports, 2 assembly. \nOrders: total 10, maximum 5 to fail',5000,40.00,'Level 4','Unlocked',4,5,0,'Create and ship part 5 when the orders arrive','Level4.png',0,60.00),(3,1352663908,13,0.00,0,0,1,1,0,1,'USER','Hard','Activities: 3 purchases, 10 transports, 2 assembly. \nOrders: total 10, maximum 5 to fail',5000,40.00,'Level 4','Unlocked',4,5,0,'Create and ship part 5 when the orders arrive','Level4.png',0,60.00),(3,1352663908,14,0.00,0,0,1,1,0,1,'USER','Hard','Activities: 3 purchases, 10 transports, 2 assembly. \nOrders: total 10, maximum 5 to fail',5000,40.00,'Level 4','Unlocked',4,5,0,'Create and ship part 5 when the orders arrive','Level4.png',0,60.00),(3,1352663908,15,0.00,0,0,1,1,0,1,'USER','Hard','Activities: 3 purchases, 10 transports, 2 assembly. \nOrders: total 10, maximum 5 to fail',5000,40.00,'Level 4','Unlocked',4,5,0,'Create and ship part 5 when the orders arrive','Level4.png',0,60.00),(3,1352663908,16,0.00,0,0,1,1,0,1,'USER','Hard','Activities: 3 purchases, 10 transports, 2 assembly. \nOrders: total 10, maximum 5 to fail',5000,40.00,'Level 4','Unlocked',4,5,0,'Create and ship part 5 when the orders arrive','Level4.png',0,60.00),(3,1352663908,17,0.00,0,0,1,1,0,1,'USER','Hard','Activities: 3 purchases, 10 transports, 2 assembly. \nOrders: total 10, maximum 5 to fail',5000,40.00,'Level 4','Unlocked',4,5,0,'Create and ship part 5 when the orders arrive','Level4.png',0,60.00),(3,1352663908,18,0.00,0,0,1,1,0,1,'USER','Hard','Activities: 3 purchases, 10 transports, 2 assembly. \nOrders: total 10, maximum 5 to fail',5000,40.00,'Level 4','Unlocked',4,5,0,'Create and ship part 5 when the orders arrive','Level4.png',0,60.00),(3,1352663908,19,0.00,0,0,1,1,0,1,'USER','Hard','Activities: 3 purchases, 10 transports, 2 assembly. \nOrders: total 10, maximum 5 to fail',5000,40.00,'Level 4','Unlocked',4,5,0,'Create and ship part 5 when the orders arrive','Level4.png',0,60.00),(3,1352663908,20,0.00,0,0,1,1,0,1,'USER','Hard','Activities: 3 purchases, 10 transports, 2 assembly. \nOrders: total 10, maximum 5 to fail',5000,40.00,'Level 4','Unlocked',4,5,0,'Create and ship part 5 when the orders arrive','Level4.png',0,60.00),(3,1352663908,21,0.00,0,0,1,1,0,1,'USER','Hard','Activities: 3 purchases, 10 transports, 2 assembly. \nOrders: total 10, maximum 5 to fail',5000,40.00,'Level 4','Unlocked',4,5,0,'Create and ship part 5 when the orders arrive','Level4.png',0,60.00),(3,1352663908,27,0.00,0,0,1,1,0,1,'USER','Hard','Activities: 3 purchases, 10 transports, 2 assembly. \nOrders: total 10, maximum 5 to fail',5000,40.00,'Level 4','Unlocked',4,5,0,'Create and ship part 5 when the orders arrive','Level4.png',0,60.00),(3,1352671335,28,0.00,0,0,1,1,0,1,'USER','Hard','Activities: 3 purchases, 10 transports, 2 assembly. \nOrders: total 10, maximum 5 to fail',5000,40.00,'Level 4','Unlocked',4,5,0,'Create and ship part 5 when the orders arrive','Level4.png',0,60.00),(3,1352672801,30,0.00,0,0,1,1,0,1,'USER','Hard','Activities: 3 purchases, 10 transports, 2 assembly. \nOrders: total 10, maximum 5 to fail',5000,40.00,'Level 4','Unlocked',4,5,0,'Create and ship part 5 when the orders arrive','Level4.png',0,60.00),(3,1352793876,31,0.00,0,0,1,1,0,1,'USER','Hard','Activities: 3 purchases, 10 transports, 2 assembly. \nOrders: total 10, maximum 5 to fail',5000,40.00,'Level 4','Unlocked',4,5,0,'Create and ship part 5 when the orders arrive','Level4.png',2,60.00);
/*!40000 ALTER TABLE `t_game` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_log`
--

DROP TABLE IF EXISTS `t_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_log` (
  `id_Log` int(11) NOT NULL AUTO_INCREMENT,
  `date_time` datetime DEFAULT NULL,
  `message` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id_Log`)
) ENGINE=InnoDB AUTO_INCREMENT=97 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_log`
--

LOCK TABLES `t_log` WRITE;
/*!40000 ALTER TABLE `t_log` DISABLE KEYS */;
INSERT INTO `t_log` VALUES (1,'2012-05-13 01:17:05','Proc: Machine_Select(0) - Error: Unknown column ´virtual_maxtrix_end_j´ in ´field list´'),(2,'2012-05-13 01:20:08','Proc: Machine_Select(0) - Error: Unknown column ´virtual_maxtrix_end_j´ in ´field list´'),(3,'2012-05-13 01:23:34','Proc: Machine_Select(0) - Error: Unknown column ´virtual_maxtrix_end_j´ in ´field list´'),(4,'2012-05-13 01:26:27','Proc: Machine_Select(0) - Error: Unknown column ´virtual_maxtrix_end_j´ in ´field list´'),(5,'2012-05-13 01:31:13','Proc: Machine_Select(0) - Error: Unknown column ´virtual_maxtrix_end_j´ in ´field list´'),(6,'2012-05-18 11:56:57','Proc: Machine_Select(0) - Error: Unknown column ´virtual_maxtrix_end_j´ in ´field list´'),(7,'2012-05-18 12:05:37','Proc: Machine_Select(0) - Error: Unknown column ´virtual_maxtrix_end_j´ in ´field list´'),(8,'2012-05-18 12:07:27','Proc: Machine_Select(0) - Error: Unknown column ´virtual_maxtrix_end_j´ in ´field list´'),(9,'2012-05-18 12:08:18','Proc: Machine_Select(0) - Error: Unknown column ´virtual_maxtrix_end_j´ in ´field list´'),(10,'2012-05-18 12:18:59','Proc: Machine_Select(0) - Error: Unknown column ´virtual_maxtrix_end_j´ in ´field list´'),(11,'2012-05-18 12:19:40','Proc: Machine_Select(0) - Error: Unknown column ´virtual_maxtrix_end_j´ in ´field list´'),(12,'2012-05-18 12:23:18','Proc: Machine_Select(0) - Error: Unknown column ´virtual_maxtrix_end_j´ in ´field list´'),(13,'2012-05-18 12:24:46','Proc: Machine_Select(0) - Error: Unknown column ´virtual_maxtrix_end_j´ in ´field list´'),(14,'2012-05-18 12:27:39','Proc: Machine_Select(0) - Error: Unknown column ´virtual_maxtrix_end_j´ in ´field list´'),(15,'2012-05-18 12:32:27','Proc: Machine_Select(0) - Error: Unknown column ´virtual_maxtrix_end_j´ in ´field list´'),(16,'2012-05-18 12:40:00','Proc: Machine_Select(0) - Error: Unknown column ´virtual_maxtrix_end_j´ in ´field list´'),(17,'2012-05-18 12:42:23','Proc: Machine_Select(0) - Error: Unknown column ´virtual_maxtrix_end_j´ in ´field list´'),(18,'2012-05-18 12:53:36','Proc: Machine_Select(0) - Error: Unknown column ´virtual_maxtrix_end_j´ in ´field list´'),(19,'2012-05-18 13:01:53','Proc: Machine_Select(0) - Error: Unknown column ´virtual_maxtrix_end_j´ in ´field list´'),(20,'2012-05-18 13:03:13','Proc: Machine_Select(0) - Error: Unknown column ´virtual_maxtrix_end_j´ in ´field list´'),(21,'2012-05-18 16:47:20','Proc: Machine_Select(0) - Error: Unknown column ´virtual_maxtrix_end_j´ in ´field list´'),(22,'2012-05-18 17:02:44','Proc: Machine_Select(0) - Error: Unknown column ´virtual_maxtrix_end_j´ in ´field list´'),(23,'2012-05-18 17:07:08','Proc: Machine_Select(0) - Error: Unknown column ´virtual_maxtrix_end_j´ in ´field list´'),(24,'2012-05-18 17:08:48','Proc: Machine_Select(0) - Error: Unknown column ´virtual_maxtrix_end_j´ in ´field list´'),(25,'2012-05-18 17:09:40','Proc: Machine_Select(0) - Error: Unknown column ´virtual_maxtrix_end_j´ in ´field list´'),(26,'2012-05-18 17:12:04','Proc: Machine_Select(0) - Error: Unknown column ´virtual_maxtrix_end_j´ in ´field list´'),(27,'2012-05-18 17:22:25','Proc: Machine_Select(0) - Error: Unknown column ´virtual_maxtrix_end_j´ in ´field list´'),(28,'2012-05-18 17:23:09','Proc: Machine_Select(0) - Error: Unknown column ´virtual_maxtrix_end_j´ in ´field list´'),(29,'2012-05-18 17:56:27','Proc: Machine_Select(0) - Error: Unknown column ´virtual_maxtrix_end_j´ in ´field list´'),(30,'2012-05-18 17:58:59','Proc: Machine_Select(10) - Error: Unknown column ´virtual_maxtrix_end_j´ in ´field list´'),(31,'2012-05-18 18:02:20','Proc: Machine_Select(0) - Error: Unknown column ´virtual_maxtrix_end_j´ in ´field list´'),(32,'2012-05-18 18:10:48','Proc: Machine_Select(0) - Error: Unknown column ´virtual_maxtrix_end_j´ in ´field list´'),(33,'2012-05-18 18:15:10','Proc: Machine_Select(0) - Error: Unknown column ´virtual_maxtrix_end_j´ in ´field list´'),(34,'2012-05-18 18:53:04','Proc: Machine_Select(0) - Error: Unknown column ´virtual_maxtrix_end_j´ in ´field list´'),(35,'2012-05-18 18:53:52','Proc: Machine_Select(0) - Error: Unknown column ´virtual_maxtrix_end_j´ in ´field list´'),(36,'2012-05-18 18:56:37','Proc: Machine_Select(0) - Error: Unknown column ´virtual_maxtrix_end_j´ in ´field list´'),(37,'2012-06-04 04:57:55','Proc: Operation_Insert(3,Assemble Part3,,0.0,0.0,0,2,1,1,2) - Error: Duplicate entry 3 for key PRIMARY'),(38,'2012-06-04 04:57:55','Proc: TransportStore_Insert(4,10,2,2,1) - Error: Duplicate entry 4 for key PRIMARY'),(39,'2012-06-04 04:57:55','Proc: TransportStore_Insert(5,10,3,2,1) - Error: Duplicate entry 5 for key PRIMARY'),(40,'2012-06-04 04:57:55','Proc: TransportStore_Insert(7,30,5,3,4) - Error: Duplicate entry 7 for key PRIMARY'),(41,'2012-06-04 04:57:55','Proc: TransportStore_Insert(6,20,5,1,3) - Error: Duplicate entry 6 for key PRIMARY'),(42,'2012-06-04 04:57:55','Proc: Purchase_Insert(1,2,1,10,100) - Error: Duplicate entry 1 for key PRIMARY'),(43,'2012-06-04 04:57:55','Proc: Purchase_Insert(2,2,1,10,50) - Error: Duplicate entry 2 for key PRIMARY'),(44,'2012-06-04 04:57:55','Proc: Ship_Insert(8,4,50) - Error: Duplicate entry 8 for key PRIMARY'),(45,'2012-06-04 09:09:45','Proc: Game_Insert(1,1500.0,47,2,6,0,473.0929999997742) - Error: Data truncation: Out of range value for column ´decCurrentMoney´ at row 196'),(46,'2012-06-04 09:09:45','Proc: TypeOperation_Insert(1,0,Paint) - Error: Duplicate entry 1-0 for key PRIMARY'),(47,'2012-06-04 09:09:45','Proc: TypeOperation_Insert(2,0,Assemble) - Error: Duplicate entry 2-0 for key PRIMARY'),(48,'2012-06-04 09:09:45','Proc: TypeOperation_Insert(3,0,Cut) - Error: Duplicate entry 3-0 for key PRIMARY'),(49,'2012-06-04 09:09:45','Proc: Operator_Insert(1,0,Pepe,300.0,Busy,User,Walking,0,0,-3,-87,20.0) - Error: Duplicate entry 1-0 for key PRIMARY'),(50,'2012-06-04 09:09:45','Proc: Operator_Insert(2,0,Bob,300.0,Busy,User,Walking,0,0,35,57,22.0) - Error: Duplicate entry 2-0 for key PRIMARY'),(51,'2012-06-04 09:09:45','Proc: Operator_Insert(3,0,Juan,300.0,Idle,User,STATION1,0,0,67,-82,25.0) - Error: Duplicate entry 3-0 for key PRIMARY'),(52,'2012-06-04 09:09:45','Proc: Operator_Insert(4,0,Maria,300.0,Idle,User,STATION1,0,0,43,-62,15.0) - Error: Duplicate entry 4-0 for key PRIMARY'),(53,'2012-06-04 09:09:45','Proc: Part_Insert(1,0,Part One,EACH,100.0,100.0,100,52,0.25,User,Models/Screw/screw.j3o,0.01,Red) - Error: Duplicate entry 1-0 for key PRIMARY'),(54,'2012-06-04 09:09:45','Proc: Part_Insert(2,0,Part Two,EACH,100.0,100.0,50,26,0.4,User,Models/Screw2/screw2.j3o,0.01,Blue) - Error: Duplicate entry 2-0 for key PRIMARY'),(55,'2012-06-04 09:09:45','Proc: Part_Insert(3,0,Part Three,EACH,300.0,300.0,25,724,2.0,User,Models/Chair/chair.j3o,0.05,Green) - Error: Duplicate entry 3-0 for key PRIMARY'),(56,'2012-06-04 09:09:45','Proc: Supplier_Insert(1,0,Supplier Pepito,0,0,0.0) - Error: Duplicate entry 1-0 for key PRIMARY'),(57,'2012-06-04 09:09:45','Proc: Station_Insert(1,0,Station to create part 3,50,-70,40.0,40.0,1.0,25,25,Idle,User,Other) - Error: Duplicate entry 1-0 for key PRIMARY'),(58,'2012-06-04 09:09:45','Proc: Station_Insert(2,0,Receiving Dock,-120,-70,30.0,30.0,1.0,0,50,Idle,User,Other) - Error: Duplicate entry 2-0 for key PRIMARY'),(59,'2012-06-04 09:09:45','Proc: Station_Insert(3,0,Storage,240,10,50.0,50.0,1.0,200,200,Idle,User,Other) - Error: Duplicate entry 3-0 for key PRIMARY'),(60,'2012-06-04 09:09:45','Proc: Station_Insert(4,0,Ship Station,-120,70,30.0,30.0,1.0,200,200,Idle,User,Other) - Error: Duplicate entry 4-0 for key PRIMARY'),(61,'2012-06-04 09:09:45','Proc: Station_Insert(5,0,Staff Zone,50,50,40.0,20.0,1.0,0,0,Idle,User,StaffZone) - Error: Duplicate entry 5-0 for key PRIMARY'),(62,'2012-06-04 09:09:45','Proc: Station_Insert(6,0,Machine Zone,-60,50,40.0,20.0,1.0,0,0,Idle,User,MachineZone) - Error: Duplicate entry 6-0 for key PRIMARY'),(63,'2012-06-07 11:23:47','Proc: Event_Insert(0,28,1.0,192.0,4) - Error: Duplicate entry 0-28 for key PRIMARY'),(64,'2012-06-13 17:43:44','Proc: TypeOperationPerSkill_Select(0,0) - Error: Table ´gaming.t_skill_type_operation´ doesn´t exist'),(65,'2012-06-13 17:43:44','Proc: TypeOperationPerSkill_Select(0,1) - Error: Table ´gaming.t_skill_type_operation´ doesn´t exist'),(66,'2012-06-13 17:43:44','Proc: TypeOperationPerSkill_Select(0,2) - Error: Table ´gaming.t_skill_type_operation´ doesn´t exist'),(67,'2012-06-13 17:43:44','Proc: TypeOperationPerSkill_Select(0,3) - Error: Table ´gaming.t_skill_type_operation´ doesn´t exist'),(68,'2012-06-13 17:43:44','Proc: TypeOperationPerSkill_Select(0,4) - Error: Table ´gaming.t_skill_type_operation´ doesn´t exist'),(69,'2012-06-13 17:43:45','Proc: Operator_Select(0) - Error: Unknown column ´is_executing_activity´ in ´field list´'),(70,'2012-06-13 17:45:19','Proc: TypeOperationPerSkill_Select(0,0) - Error: Table ´gaming.t_skill_type_operation´ doesn´t exist'),(71,'2012-06-13 17:45:19','Proc: TypeOperationPerSkill_Select(0,1) - Error: Table ´gaming.t_skill_type_operation´ doesn´t exist'),(72,'2012-06-13 17:45:19','Proc: TypeOperationPerSkill_Select(0,2) - Error: Table ´gaming.t_skill_type_operation´ doesn´t exist'),(73,'2012-06-13 17:45:19','Proc: TypeOperationPerSkill_Select(0,3) - Error: Table ´gaming.t_skill_type_operation´ doesn´t exist'),(74,'2012-06-13 17:45:19','Proc: TypeOperationPerSkill_Select(0,4) - Error: Table ´gaming.t_skill_type_operation´ doesn´t exist'),(75,'2012-06-13 17:45:19','Proc: Operator_Select(0) - Error: Unknown column ´is_executing_activity´ in ´field list´'),(76,'2012-06-13 17:47:45','Proc: TypeOperationPerSkill_Select(0,0) - Error: Table ´gaming.t_skill_type_operation´ doesn´t exist'),(77,'2012-06-13 17:47:45','Proc: TypeOperationPerSkill_Select(0,1) - Error: Table ´gaming.t_skill_type_operation´ doesn´t exist'),(78,'2012-06-13 17:47:45','Proc: TypeOperationPerSkill_Select(0,2) - Error: Table ´gaming.t_skill_type_operation´ doesn´t exist'),(79,'2012-06-13 17:47:45','Proc: TypeOperationPerSkill_Select(0,3) - Error: Table ´gaming.t_skill_type_operation´ doesn´t exist'),(80,'2012-06-13 17:47:45','Proc: TypeOperationPerSkill_Select(0,4) - Error: Table ´gaming.t_skill_type_operation´ doesn´t exist'),(81,'2012-06-13 17:47:53','Proc: Operator_Select(0) - Error: Unknown column ´is_executing_activity´ in ´field list´'),(82,'2012-06-13 18:14:01','Proc: Operator_Select(0) - Error: Unknown column ´is_executing_activity´ in ´field list´'),(83,'2012-06-23 17:07:48','Proc: Game_SelectByType(CPU) - Error: Unknown column ´CPU´ in ´field list´'),(84,'2012-06-23 17:09:13','Proc: Game_SelectByType(CPU) - Error: Unknown column ´CPU´ in ´field list´'),(85,'2012-06-23 17:09:44','Proc: Game_SelectByType(CPU) - Error: Unknown column ´CPU´ in ´field list´'),(86,'2012-06-23 17:10:34','Proc: Game_SelectByType(CPU) - Error: Unknown column ´CPU´ in ´field list´'),(87,'2012-08-14 14:13:24','Proc: Order_Select(0) - Error: Column Index out of range, 7 > 6. '),(88,'2012-08-14 14:15:58','Proc: Order_Select(0) - Error: Column Index out of range, 7 > 6. '),(89,'2012-08-19 22:18:58','Proc: Game_SelectAGame(0) - Error: Column Index out of range, 22 > 21. '),(90,'2012-08-19 22:20:12','Proc: Game_SelectAGame(0) - Error: Column Index out of range, 22 > 21. '),(91,'2012-10-19 15:51:51','Proc: Player_LogOut(8) - Error: You cant specify target table t_player_log for update in FROM clause'),(92,'2012-10-19 15:51:51','Proc: Player_LogOut(8) - Error: You cant specify target table t_player_log for update in FROM clause'),(93,'2012-10-19 15:51:51','Proc: Player_LogOut(8) - Error: You cant specify target table t_player_log for update in FROM clause'),(94,'2012-10-19 15:51:51','Proc: Player_LogOut(8) - Error: You cant specify target table t_player_log for update in FROM clause'),(95,'2012-10-19 15:53:55','Proc: Player_LogOut(8) - Error: You cant specify target table t_player_log for update in FROM clause'),(96,'2012-10-19 17:15:01','Proc: PlayerLog_Insert(8Level 2) - Error: You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near Level 2) at line 1');
/*!40000 ALTER TABLE `t_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_machine`
--

DROP TABLE IF EXISTS `t_machine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_machine` (
  `id_machine` int(11) NOT NULL,
  `id_game` int(11) NOT NULL,
  `machine_description` varchar(50) DEFAULT NULL,
  `speed` decimal(10,2) DEFAULT NULL,
  `weight_capacity` decimal(10,2) DEFAULT NULL,
  `volume_capacity` decimal(10,2) DEFAULT NULL,
  `pick_up_time_distn` varchar(20) DEFAULT NULL,
  `pick_up_time_parameter1` decimal(10,2) DEFAULT NULL,
  `pick_up_time_parameter2` decimal(10,2) DEFAULT NULL,
  `machine_time_distn` varchar(20) DEFAULT NULL,
  `machine_time_parameter1` decimal(10,2) DEFAULT NULL,
  `machine_time_parameter2` decimal(10,2) DEFAULT NULL,
  `placement_time_distn` varchar(20) DEFAULT NULL,
  `placement_time_parameter1` decimal(10,2) DEFAULT NULL,
  `placement_time_parameter2` decimal(10,2) DEFAULT NULL,
  `time_between_failures_distn` varchar(20) DEFAULT NULL,
  `time_between_failures_parameter1` decimal(10,2) DEFAULT NULL,
  `time_between_failures_parameter2` decimal(10,2) DEFAULT NULL,
  `repair_time_distn` varchar(20) DEFAULT NULL,
  `repair_time_parameter1` decimal(10,2) DEFAULT NULL,
  `repair_time_parameter2` decimal(10,2) DEFAULT NULL,
  `price_for_purchase` decimal(10,2) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `owner` varchar(10) DEFAULT NULL,
  `machine_design` varchar(100) DEFAULT NULL,
  `virtual_id_location` varchar(45) DEFAULT NULL,
  `virtual_matrix_ini_i` int(11) DEFAULT NULL,
  `virtual_matrix_ini_j` int(11) DEFAULT NULL,
  `virtual_matrix_end_i` int(11) DEFAULT NULL,
  `virtual_matrix_end_j` int(11) DEFAULT NULL,
  `current_location_x` int(11) DEFAULT NULL,
  `current_location_z` int(11) DEFAULT NULL,
  `size_w` int(11) DEFAULT NULL,
  `size_l` int(11) DEFAULT NULL,
  `cost_per_hour` decimal(10,2) DEFAULT NULL,
  `machine_category` varchar(45) DEFAULT NULL,
  `state` varchar(45) DEFAULT NULL,
  `machine_material` varchar(100) DEFAULT NULL,
  `percentage_depreciation` decimal(10,2) DEFAULT NULL,
  `price_preventive_maintenance` decimal(10,2) DEFAULT NULL,
  `factor_param1` decimal(10,2) DEFAULT NULL,
  `factor_param2` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id_machine`,`id_game`),
  KEY `fk_id_game_0007` (`id_game`),
  CONSTRAINT `fk_id_game_0007` FOREIGN KEY (`id_game`) REFERENCES `t_game` (`id_game`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_machine`
--

LOCK TABLES `t_machine` WRITE;
/*!40000 ALTER TABLE `t_machine` DISABLE KEYS */;
INSERT INTO `t_machine` VALUES (1,0,'Assembly Machine',100.00,150.00,50.00,'UNIFORM',0.00,1.00,'UNIFORM',0.00,1.00,'UNIFORM',0.00,1.00,'EXPONENTIAL',1440.00,0.00,'UNIFORM',15.00,40.00,400.00,'Idle','User','Models/Machine/machine1.j3o','-1',0,0,0,0,0,0,15,20,3.00,'Operation','Active','',0.10,25.00,0.80,1.00),(1,1,'Transport Equipment',100.00,100.00,100.00,'UNIFORM',0.00,1.00,'UNIFORM',0.00,1.00,'UNIFORM',0.00,1.00,'EXPONENTIAL',1440.00,0.00,'UNIFORM',15.00,40.00,100.00,'Idle','User','Models/Kart/kart.j3o','-1',0,0,0,0,0,0,5,5,1.00,'Transport','Active','Models/Kart/kart.j3m',0.05,15.00,0.80,1.00),(1,2,'Transport Equipment',100.00,100.00,100.00,'UNIFORM',0.00,1.00,'UNIFORM',0.00,1.00,'UNIFORM',0.00,1.00,'EXPONENTIAL',1440.00,0.00,'UNIFORM',15.00,40.00,100.00,'Idle','User','Models/Kart/kart.j3o','-1',0,0,0,0,0,0,5,5,1.00,'Transport','Active','Models/Kart/kart.j3m',0.05,15.00,0.80,1.00),(1,3,'Transport Equipment',100.00,100.00,100.00,'UNIFORM',0.00,1.00,'UNIFORM',0.00,1.00,'UNIFORM',0.00,1.00,'EXPONENTIAL',1440.00,0.00,'UNIFORM',15.00,40.00,100.00,'Idle','User','Models/Kart/kart.j3o','-1',0,0,0,0,0,0,5,5,1.00,'Transport','Active','Models/Kart/kart.j3m',0.05,15.00,0.80,1.00),(2,0,'Transport Equipment',100.00,110.00,50.00,'UNIFORM',0.00,1.00,'UNIFORM',0.00,1.00,'UNIFORM',0.00,1.00,'EXPONENTIAL',1440.00,0.00,'UNIFORM',15.00,40.00,100.00,'Idle','User','Models/Kart/kart.j3o','-1',0,0,0,0,0,0,5,5,1.00,'Transport','Inactive','Models/Kart/kart.j3m',0.05,15.00,0.80,1.00),(2,1,'Transport Equipment',100.00,100.00,100.00,'UNIFORM',0.00,1.00,'UNIFORM',0.00,1.00,'UNIFORM',0.00,1.00,'EXPONENTIAL',1440.00,0.00,'UNIFORM',15.00,40.00,100.00,'Idle','User','Models/Kart/kart.j3o','-1',0,0,0,0,0,0,5,5,1.00,'Transport','Active','Models/Kart/kart.j3m',0.05,15.00,0.80,1.00),(2,2,'Transport Equipment',100.00,100.00,100.00,'UNIFORM',0.00,1.00,'UNIFORM',0.00,1.00,'UNIFORM',0.00,1.00,'EXPONENTIAL',1440.00,0.00,'UNIFORM',15.00,40.00,100.00,'Idle','User','Models/Kart/kart.j3o','-1',0,0,0,0,0,0,5,5,1.00,'Transport','Active','Models/Kart/kart.j3m',0.05,15.00,0.80,1.00),(2,3,'Transport Equipment',100.00,100.00,100.00,'UNIFORM',0.00,1.00,'UNIFORM',0.00,1.00,'UNIFORM',0.00,1.00,'EXPONENTIAL',1440.00,0.00,'UNIFORM',15.00,40.00,100.00,'Idle','User','Models/Kart/kart.j3o','-1',0,0,0,0,0,0,5,5,1.00,'Transport','Active','Models/Kart/kart.j3m',0.05,15.00,0.80,1.00),(3,0,'Transport Equipment',100.00,150.00,50.00,'UNIFORM',0.00,1.00,'UNIFORM',0.00,1.00,'UNIFORM',0.00,1.00,'EXPONENTIAL',1440.00,0.00,'UNIFORM',15.00,40.00,100.00,'Idle','User','Models/Kart/kart.j3o','-1',0,0,0,0,0,0,5,5,1.00,'Transport','Active','Models/Kart/kart.j3m',0.05,15.00,0.80,1.00),(3,1,'Transport Equipment',100.00,100.00,100.00,'UNIFORM',0.00,1.00,'UNIFORM',0.00,1.00,'UNIFORM',0.00,1.00,'EXPONENTIAL',1440.00,0.00,'UNIFORM',15.00,40.00,100.00,'Idle','User','Models/Kart/kart.j3o','-1',0,0,0,0,0,0,5,5,1.00,'Transport','Active','Models/Kart/kart.j3m',0.05,15.00,0.80,1.00),(3,2,'Transport Equipment',100.00,100.00,100.00,'UNIFORM',0.00,1.00,'UNIFORM',0.00,1.00,'UNIFORM',0.00,1.00,'EXPONENTIAL',1440.00,0.00,'UNIFORM',15.00,40.00,100.00,'Idle','User','Models/Kart/kart.j3o','-1',0,0,0,0,0,0,5,5,1.00,'Transport','Active','Models/Kart/kart.j3m',0.05,15.00,0.80,1.00),(3,3,'Transport Equipment',100.00,100.00,100.00,'UNIFORM',0.00,1.00,'UNIFORM',0.00,1.00,'UNIFORM',0.00,1.00,'EXPONENTIAL',1440.00,0.00,'UNIFORM',15.00,40.00,100.00,'Idle','User','Models/Kart/kart.j3o','-1',0,0,0,0,0,0,5,5,1.00,'Transport','Active','Models/Kart/kart.j3m',0.05,15.00,0.80,1.00),(4,0,'Transport Equipment',100.00,200.00,200.00,'UNIFORM',0.00,1.00,'UNIFORM',0.00,1.00,'UNIFORM',0.00,1.00,'EXPONENTIAL',1440.00,0.00,'UNIFORM',15.00,40.00,100.00,'Idle','User','Models/Kart/kart.j3o','-1',0,0,0,0,0,0,5,5,1.00,'Transport','Active','Models/Kart/kart.j3m',0.05,15.00,0.80,1.00),(4,1,'Transport Equipment',100.00,100.00,100.00,'UNIFORM',0.00,1.00,'UNIFORM',0.00,1.00,'UNIFORM',0.00,1.00,'EXPONENTIAL',1440.00,0.00,'UNIFORM',15.00,40.00,100.00,'Idle','User','Models/Kart/kart.j3o','-1',0,0,0,0,0,0,5,5,1.00,'Transport','Active','Models/Kart/kart.j3m',0.05,15.00,0.80,1.00),(4,2,'Transport Equipment',100.00,100.00,100.00,'UNIFORM',0.00,1.00,'UNIFORM',0.00,1.00,'UNIFORM',0.00,1.00,'EXPONENTIAL',1440.00,0.00,'UNIFORM',15.00,40.00,100.00,'Idle','User','Models/Kart/kart.j3o','-1',0,0,0,0,0,0,5,5,1.00,'Transport','Active','Models/Kart/kart.j3m',0.05,15.00,0.80,1.00),(4,3,'Transport Equipment',100.00,100.00,100.00,'UNIFORM',0.00,1.00,'UNIFORM',0.00,1.00,'UNIFORM',0.00,1.00,'EXPONENTIAL',1440.00,0.00,'UNIFORM',15.00,40.00,100.00,'Idle','User','Models/Kart/kart.j3o','-1',0,0,0,0,0,0,5,5,1.00,'Transport','Active','Models/Kart/kart.j3m',0.05,15.00,0.80,1.00),(5,0,'Transport Equipment',100.00,200.00,200.00,'UNIFORM',0.00,1.00,'UNIFORM',0.00,1.00,'UNIFORM',0.00,1.00,'EXPONENTIAL',1440.00,0.00,'UNIFORM',15.00,40.00,100.00,'Idle','User','Models/Kart/kart.j3o','-1',0,0,0,0,0,0,5,5,1.00,'Transport','Active','Models/Kart/kart.j3m',0.05,15.00,0.80,1.00),(5,1,'Transport Equipment',100.00,100.00,100.00,'UNIFORM',0.00,1.00,'UNIFORM',0.00,1.00,'UNIFORM',0.00,1.00,'EXPONENTIAL',1440.00,0.00,'UNIFORM',15.00,40.00,100.00,'Idle','User','Models/Kart/kart.j3o','-1',0,0,0,0,0,0,5,5,1.00,'Transport','Active','Models/Kart/kart.j3m',0.05,15.00,0.80,1.00),(5,2,'Transport Equipment',100.00,100.00,100.00,'UNIFORM',0.00,1.00,'UNIFORM',0.00,1.00,'UNIFORM',0.00,1.00,'EXPONENTIAL',1440.00,0.00,'UNIFORM',15.00,40.00,100.00,'Idle','User','Models/Kart/kart.j3o','-1',0,0,0,0,0,0,5,5,1.00,'Transport','Active','Models/Kart/kart.j3m',0.05,15.00,0.80,1.00),(5,3,'Transport Equipment',100.00,100.00,100.00,'UNIFORM',0.00,1.00,'UNIFORM',0.00,1.00,'UNIFORM',0.00,1.00,'EXPONENTIAL',1440.00,0.00,'UNIFORM',15.00,40.00,100.00,'Idle','User','Models/Kart/kart.j3o','-1',0,0,0,0,0,0,5,5,1.00,'Transport','Active','Models/Kart/kart.j3m',0.05,15.00,0.80,1.00),(6,0,'Transport Equipment',100.00,200.00,200.00,'UNIFORM',0.00,1.00,'UNIFORM',0.00,1.00,'UNIFORM',0.00,1.00,'EXPONENTIAL',1440.00,0.00,'UNIFORM',15.00,40.00,100.00,'Idle','User','Models/Kart/kart.j3o','-1',0,0,0,0,0,0,5,5,1.00,'Transport','Active','Models/Kart/kart.j3m',0.05,15.00,0.80,1.00),(6,1,'Transport Equipment',100.00,100.00,100.00,'UNIFORM',0.00,1.00,'UNIFORM',0.00,1.00,'UNIFORM',0.00,1.00,'EXPONENTIAL',1440.00,0.00,'UNIFORM',15.00,40.00,100.00,'Idle','User','Models/Kart/kart.j3o','-1',0,0,0,0,0,0,5,5,1.00,'Transport','Active','Models/Kart/kart.j3m',0.05,15.00,0.80,1.00),(6,2,'Transport Equipment',100.00,100.00,100.00,'UNIFORM',0.00,1.00,'UNIFORM',0.00,1.00,'UNIFORM',0.00,1.00,'EXPONENTIAL',1440.00,0.00,'UNIFORM',15.00,40.00,100.00,'Idle','User','Models/Kart/kart.j3o','-1',0,0,0,0,0,0,5,5,1.00,'Transport','Active','Models/Kart/kart.j3m',0.05,15.00,0.80,1.00),(6,3,'Transport Equipment',100.00,100.00,100.00,'UNIFORM',0.00,1.00,'UNIFORM',0.00,1.00,'UNIFORM',0.00,1.00,'EXPONENTIAL',1440.00,0.00,'UNIFORM',15.00,40.00,100.00,'Idle','User','Models/Kart/kart.j3o','-1',0,0,0,0,0,0,5,5,1.00,'Transport','Active','Models/Kart/kart.j3m',0.05,15.00,0.80,1.00),(7,0,'Transport Equipment',100.00,200.00,200.00,'UNIFORM',0.00,1.00,'UNIFORM',0.00,1.00,'UNIFORM',0.00,1.00,'EXPONENTIAL',1440.00,0.00,'UNIFORM',15.00,40.00,100.00,'Idle','User','Models/Kart/kart.j3o','-1',0,0,0,0,0,0,5,5,1.00,'Transport','Active','Models/Kart/kart.j3m',0.05,15.00,0.80,1.00),(7,1,'Transport Equipment',100.00,100.00,100.00,'UNIFORM',0.00,1.00,'UNIFORM',0.00,1.00,'UNIFORM',0.00,1.00,'EXPONENTIAL',1440.00,0.00,'UNIFORM',15.00,40.00,100.00,'Idle','User','Models/Kart/kart.j3o','-1',0,0,0,0,0,0,5,5,1.00,'Transport','Active','Models/Kart/kart.j3m',0.05,15.00,0.80,1.00),(7,2,'Transport Equipment',100.00,100.00,100.00,'UNIFORM',0.00,1.00,'UNIFORM',0.00,1.00,'UNIFORM',0.00,1.00,'EXPONENTIAL',1440.00,0.00,'UNIFORM',15.00,40.00,100.00,'Idle','User','Models/Kart/kart.j3o','-1',0,0,0,0,0,0,5,5,1.00,'Transport','Active','Models/Kart/kart.j3m',0.05,15.00,0.80,1.00),(7,3,'Transport Equipment',100.00,100.00,100.00,'UNIFORM',0.00,1.00,'UNIFORM',0.00,1.00,'UNIFORM',0.00,1.00,'EXPONENTIAL',1440.00,0.00,'UNIFORM',15.00,40.00,100.00,'Idle','User','Models/Kart/kart.j3o','-1',0,0,0,0,0,0,5,5,1.00,'Transport','Active','Models/Kart/kart.j3m',0.05,15.00,0.80,1.00),(8,0,'Assembly Machine',100.00,200.00,200.00,'UNIFORM',0.00,1.00,'UNIFORM',0.00,1.00,'UNIFORM',0.00,1.00,'EXPONENTIAL',1440.00,0.00,'UNIFORM',15.00,40.00,400.00,'Idle','User','Models/Machine/machine1.j3o','-1',0,0,0,0,0,0,15,20,3.00,'Operation','Active','',0.10,25.00,0.80,1.00),(8,1,'Cutting Machine',100.00,100.00,100.00,'UNIFORM',0.00,1.00,'UNIFORM',0.00,1.00,'UNIFORM',0.00,1.00,'EXPONENTIAL',1440.00,0.00,'UNIFORM',15.00,40.00,500.00,'Idle','User','Models/Machine/machine1.j3o','-1',0,0,0,0,0,0,15,20,3.00,'Operation','Active','',0.15,25.00,0.80,1.00),(8,2,'Assembly Machine',100.00,100.00,100.00,'UNIFORM',0.00,1.00,'UNIFORM',0.00,1.00,'UNIFORM',0.00,1.00,'EXPONENTIAL',1440.00,0.00,'UNIFORM',15.00,40.00,400.00,'Idle','User','Models/Machine/machine1.j3o','-1',0,0,0,0,0,0,15,20,3.00,'Operation','Active','',0.10,25.00,0.80,1.00),(8,3,'Assembly Machine',100.00,100.00,100.00,'UNIFORM',0.00,1.00,'UNIFORM',0.00,1.00,'UNIFORM',0.00,1.00,'EXPONENTIAL',1440.00,0.00,'UNIFORM',15.00,40.00,400.00,'Idle','User','Models/Machine/machine1.j3o','-1',0,0,0,0,0,0,15,20,3.00,'Operation','Active','',0.10,25.00,0.80,1.00),(9,0,'Assembly Machine',100.00,200.00,200.00,'UNIFORM',0.00,1.00,'UNIFORM',0.00,1.00,'UNIFORM',0.00,1.00,'EXPONENTIAL',1440.00,0.00,'UNIFORM',15.00,40.00,400.00,'Idle','User','Models/Machine/machine1.j3o','-1',0,0,0,0,0,0,15,20,3.00,'Operation','Active','',0.10,25.00,0.80,1.00),(9,1,'Cutting Machine',100.00,100.00,100.00,'UNIFORM',0.00,1.00,'UNIFORM',0.00,1.00,'UNIFORM',0.00,1.00,'EXPONENTIAL',1440.00,0.00,'UNIFORM',15.00,40.00,500.00,'Idle','User','Models/Machine/machine1.j3o','-1',0,0,0,0,0,0,15,20,3.00,'Operation','Inactive','',0.15,25.00,0.80,1.00),(9,2,'Assembly Machine',100.00,100.00,100.00,'UNIFORM',0.00,1.00,'UNIFORM',0.00,1.00,'UNIFORM',0.00,1.00,'EXPONENTIAL',1440.00,0.00,'UNIFORM',15.00,40.00,400.00,'Idle','User','Models/Machine/machine1.j3o','-1',0,0,0,0,0,0,15,20,3.00,'Operation','Active','',0.10,25.00,0.80,1.00),(9,3,'Assembly Machine',100.00,100.00,100.00,'UNIFORM',0.00,1.00,'UNIFORM',0.00,1.00,'UNIFORM',0.00,1.00,'EXPONENTIAL',1440.00,0.00,'UNIFORM',15.00,40.00,400.00,'Idle','User','Models/Machine/machine1.j3o','-1',0,0,0,0,0,0,15,20,3.00,'Operation','Active','',0.10,25.00,0.80,1.00),(10,1,'Cutting Machine',100.00,100.00,100.00,'UNIFORM',0.00,1.00,'UNIFORM',0.00,1.00,'UNIFORM',0.00,1.00,'EXPONENTIAL',1440.00,0.00,'UNIFORM',15.00,40.00,500.00,'Idle','User','Models/Machine/machine1.j3o','-1',0,0,0,0,0,0,15,20,3.00,'Operation','Active','',0.15,25.00,0.80,1.00),(10,2,'Assembly Machine',100.00,100.00,100.00,'UNIFORM',0.00,1.00,'UNIFORM',0.00,1.00,'UNIFORM',0.00,1.00,'EXPONENTIAL',1440.00,0.00,'UNIFORM',15.00,40.00,400.00,'Idle','User','Models/Machine/machine1.j3o','-1',0,0,0,0,0,0,15,20,3.00,'Operation','Active','',0.10,25.00,0.80,1.00),(10,3,'Assembly Machine',100.00,100.00,100.00,'UNIFORM',0.00,1.00,'UNIFORM',0.00,1.00,'UNIFORM',0.00,1.00,'EXPONENTIAL',1440.00,0.00,'UNIFORM',15.00,40.00,400.00,'Idle','User','Models/Machine/machine1.j3o','-1',0,0,0,0,0,0,15,20,3.00,'Operation','Active','',0.10,25.00,0.80,1.00),(11,1,'Cutting Machine',100.00,100.00,100.00,'UNIFORM',0.00,1.00,'UNIFORM',0.00,1.00,'UNIFORM',0.00,1.00,'EXPONENTIAL',1440.00,0.00,'UNIFORM',15.00,40.00,500.00,'Idle','User','Models/Machine/machine1.j3o','-1',0,0,0,0,0,0,15,20,3.00,'Operation','Inactive','',0.15,25.00,0.80,1.00),(11,2,'Assembly Machine',100.00,100.00,100.00,'UNIFORM',0.00,1.00,'UNIFORM',0.00,1.00,'UNIFORM',0.00,1.00,'EXPONENTIAL',1440.00,0.00,'UNIFORM',15.00,40.00,400.00,'Idle','User','Models/Machine/machine1.j3o','-1',0,0,0,0,0,0,15,20,3.00,'Operation','Active','',0.10,25.00,0.80,1.00),(11,3,'Assembly Machine',100.00,100.00,100.00,'UNIFORM',0.00,1.00,'UNIFORM',0.00,1.00,'UNIFORM',0.00,1.00,'EXPONENTIAL',1440.00,0.00,'UNIFORM',15.00,40.00,400.00,'Idle','User','Models/Machine/machine1.j3o','-1',0,0,0,0,0,0,15,20,3.00,'Operation','Active','',0.10,25.00,0.80,1.00);
/*!40000 ALTER TABLE `t_machine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_operation`
--

DROP TABLE IF EXISTS `t_operation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_operation` (
  `id_operation` int(11) NOT NULL,
  `id_game` int(11) NOT NULL,
  `operation_description` varchar(50) DEFAULT NULL,
  `production_policy` int(11) DEFAULT NULL,
  `id_station` int(11) DEFAULT NULL,
  `quantity_output` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_operation`,`id_game`),
  KEY `operation_station` (`id_station`),
  KEY `operation_activity` (`id_operation`),
  KEY `operation_game` (`id_game`),
  CONSTRAINT `operation_activity` FOREIGN KEY (`id_operation`) REFERENCES `t_activity` (`id_activity`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `operation_game` FOREIGN KEY (`id_game`) REFERENCES `t_game` (`id_game`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `operation_station` FOREIGN KEY (`id_station`) REFERENCES `t_station` (`id_station`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_operation`
--

LOCK TABLES `t_operation` WRITE;
/*!40000 ALTER TABLE `t_operation` DISABLE KEYS */;
INSERT INTO `t_operation` VALUES (3,0,'Assemble Part3',0,1,2),(4,1,'',1,5,5),(7,2,'',1,5,5),(9,3,'',1,5,5),(10,2,'',1,7,10),(13,3,'',1,7,10);
/*!40000 ALTER TABLE `t_operation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_operator`
--

DROP TABLE IF EXISTS `t_operator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_operator` (
  `id_operator` int(11) NOT NULL,
  `id_game` int(11) NOT NULL,
  `name_operator` varchar(50) DEFAULT NULL,
  `salary_per_hour` decimal(10,2) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `owner` varchar(10) DEFAULT NULL,
  `virtual_id_location` varchar(45) DEFAULT NULL,
  `virtual_matrix_i` int(11) DEFAULT NULL,
  `virtual_matrix_j` int(11) DEFAULT NULL,
  `current_location_x` int(11) DEFAULT NULL,
  `current_location_z` int(11) DEFAULT NULL,
  `speed` decimal(10,2) DEFAULT NULL,
  `is_moving` int(11) DEFAULT NULL,
  `id_machine_attached` int(11) DEFAULT NULL,
  `end_location_x` int(11) DEFAULT NULL,
  `end_location_z` int(11) DEFAULT NULL,
  `activity_doing` varchar(45) DEFAULT NULL,
  `id_activity_assigned` int(11) DEFAULT NULL,
  `state` varchar(45) DEFAULT NULL,
  `price_for_hire` decimal(10,2) DEFAULT NULL,
  `price_for_fire` decimal(10,2) DEFAULT NULL,
  `salary_per_hour_carrier` decimal(10,2) DEFAULT NULL,
  `salary_per_hour_assembler` decimal(10,2) DEFAULT NULL,
  `salary_per_hour_versatile` decimal(10,2) DEFAULT NULL,
  `uniformParam1` decimal(10,2) DEFAULT NULL,
  `uniformParam2` decimal(10,2) DEFAULT NULL,
  `normalParamLoad` decimal(10,2) DEFAULT NULL,
  `normalParamUnload` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id_operator`,`id_game`),
  KEY `fk_id_game_0017` (`id_game`),
  CONSTRAINT `fk_id_game_0017` FOREIGN KEY (`id_game`) REFERENCES `t_game` (`id_game`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_operator`
--

LOCK TABLES `t_operator` WRITE;
/*!40000 ALTER TABLE `t_operator` DISABLE KEYS */;
INSERT INTO `t_operator` VALUES (1,0,'Pepe',8.00,'Idle','User','-1',0,0,0,0,20.00,0,-1,0,0,'None',0,'Active',100.00,250.00,7.00,10.00,12.00,0.00,1.00,12.00,20.00),(1,1,'Pepe',15.00,'Idle','User','-1',0,0,0,0,30.00,0,-1,0,0,'None',0,'Active',100.00,250.00,7.00,10.00,12.00,0.00,1.00,12.00,20.00),(1,2,'Pepe',15.00,'Idle','User','-1',0,0,0,0,30.00,0,-1,0,0,'None',0,'Active',100.00,250.00,7.00,10.00,12.00,0.00,1.00,12.00,20.00),(1,3,'Pepe',15.00,'Idle','User','-1',0,0,0,0,30.00,0,-1,0,0,'None',0,'Active',100.00,250.00,7.00,10.00,12.00,0.00,1.00,12.00,20.00),(2,0,'Bob',8.00,'Idle','User','-1',0,0,0,0,22.00,0,-1,0,0,'None',0,'Active',100.00,250.00,7.00,10.00,12.00,0.00,1.00,12.00,20.00),(2,1,'Cali',5.00,'Idle','User','-1',0,0,0,0,15.00,0,-1,0,0,'None',0,'Inactive',100.00,250.00,7.00,10.00,12.00,0.00,1.00,12.00,20.00),(2,2,'Cali',5.00,'Idle','User','-1',0,0,0,0,15.00,0,-1,0,0,'None',0,'Active',100.00,250.00,7.00,10.00,12.00,0.00,1.00,12.00,20.00),(2,3,'Cali',5.00,'Idle','User','-1',0,0,0,0,15.00,0,-1,0,0,'None',0,'Active',100.00,250.00,7.00,10.00,12.00,0.00,1.00,12.00,20.00),(3,0,'Juan',8.00,'Idle','User','-1',0,0,0,0,25.00,0,-1,0,0,'None',0,'Active',100.00,250.00,7.00,10.00,12.00,0.00,1.00,12.00,20.00),(3,1,'Brian',20.00,'Idle','User','-1',0,0,0,0,40.00,0,-1,0,0,'None',0,'Active',100.00,250.00,7.00,10.00,12.00,0.00,1.00,12.00,20.00),(3,2,'Brian',20.00,'Idle','User','-1',0,0,0,0,40.00,0,-1,0,0,'None',0,'Active',100.00,250.00,7.00,10.00,12.00,0.00,1.00,12.00,20.00),(3,3,'Brian',20.00,'Idle','User','-1',0,0,0,0,40.00,0,-1,0,0,'None',0,'Active',100.00,250.00,7.00,10.00,12.00,0.00,1.00,12.00,20.00),(4,0,'Maria',8.00,'Idle','User','-1',0,0,0,0,15.00,0,-1,0,0,'None',0,'Active',100.00,250.00,7.00,10.00,12.00,0.00,1.00,12.00,20.00),(4,1,'Juan',15.00,'Idle','User','-1',0,0,0,0,35.00,0,-1,0,0,'None',0,'Inactive',100.00,250.00,7.00,10.00,12.00,0.00,1.00,12.00,20.00),(4,2,'Juan',15.00,'Idle','User','-1',0,0,0,0,35.00,0,-1,0,0,'None',0,'Active',100.00,250.00,7.00,10.00,12.00,0.00,1.00,12.00,20.00),(4,3,'Juan',15.00,'Idle','User','-1',0,0,0,0,35.00,0,-1,0,0,'None',0,'Active',100.00,250.00,7.00,10.00,12.00,0.00,1.00,12.00,20.00),(5,0,'Luis',10.00,'Idle','User','-1',0,0,0,0,30.00,0,-1,0,0,'None',0,'Active',100.00,250.00,7.00,10.00,12.00,0.00,1.00,12.00,20.00),(5,1,'Jose',10.00,'Idle','User','-1',0,0,0,0,20.00,0,-1,0,0,'None',0,'Active',100.00,250.00,7.00,10.00,12.00,0.00,1.00,12.00,20.00),(5,2,'Jose',10.00,'Idle','User','-1',0,0,0,0,20.00,0,-1,0,0,'None',0,'Active',100.00,250.00,7.00,10.00,12.00,0.00,1.00,12.00,20.00),(5,3,'Jose',10.00,'Idle','User','-1',0,0,0,0,20.00,0,-1,0,0,'None',0,'Active',100.00,250.00,7.00,10.00,12.00,0.00,1.00,12.00,20.00),(6,0,'Carlos',12.00,'Idle','User','-1',0,0,0,0,35.00,0,-1,0,0,'None',0,'Active',100.00,250.00,7.00,10.00,12.00,0.00,1.00,12.00,20.00),(6,1,'Ramon',12.00,'Idle','User','-1',0,0,0,0,21.00,0,-1,0,0,'None',0,'Inactive',100.00,250.00,7.00,10.00,12.00,0.00,1.00,12.00,20.00),(6,2,'Ramon',12.00,'Idle','User','-1',0,0,0,0,21.00,0,-1,0,0,'None',0,'Active',100.00,250.00,7.00,10.00,12.00,0.00,1.00,12.00,20.00),(6,3,'Ramon',12.00,'Idle','User','-1',0,0,0,0,21.00,0,-1,0,0,'None',0,'Active',100.00,250.00,7.00,10.00,12.00,0.00,1.00,12.00,20.00),(7,2,'Axel',12.00,'Idle','User','-1',0,0,0,0,21.00,0,-1,0,0,'None',0,'Active',100.00,250.00,7.00,10.00,12.00,0.00,1.00,12.00,20.00),(7,3,'Axel',12.00,'Idle','User','-1',0,0,0,0,21.00,0,-1,0,0,'None',0,'Active',100.00,250.00,7.00,10.00,12.00,0.00,1.00,12.00,20.00),(8,2,'Luis',12.00,'Idle','User','-1',0,0,0,0,21.00,0,-1,0,0,'None',0,'Active',100.00,250.00,7.00,10.00,12.00,0.00,1.00,12.00,20.00),(8,3,'Luis',12.00,'Idle','User','-1',0,0,0,0,21.00,0,-1,0,0,'None',0,'Active',100.00,250.00,7.00,10.00,12.00,0.00,1.00,12.00,20.00);
/*!40000 ALTER TABLE `t_operator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_order`
--

DROP TABLE IF EXISTS `t_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_order` (
  `id_order` int(11) NOT NULL,
  `id_game` int(11) NOT NULL,
  `id_part` int(11) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `time_units` int(11) DEFAULT NULL,
  `time_to_appear` int(11) DEFAULT NULL,
  `percentage_variability_min` decimal(10,2) DEFAULT NULL,
  `percentage_variability_max` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id_order`,`id_game`),
  KEY `game_orders` (`id_game`),
  KEY `game_part` (`id_part`),
  CONSTRAINT `game_orders` FOREIGN KEY (`id_game`) REFERENCES `t_game` (`id_game`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `game_part` FOREIGN KEY (`id_part`) REFERENCES `t_part` (`id_part`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_order`
--

LOCK TABLES `t_order` WRITE;
/*!40000 ALTER TABLE `t_order` DISABLE KEYS */;
INSERT INTO `t_order` VALUES (0,0,3,5,20,60,80.00,120.00),(0,1,2,20,50,120,80.00,120.00),(0,2,4,20,160,120,80.00,120.00),(0,3,5,10,190,120,80.00,120.00),(1,0,3,20,120,60,80.00,120.00),(1,1,2,40,70,120,80.00,120.00),(1,2,4,30,70,120,80.00,120.00),(1,3,5,20,70,120,80.00,120.00),(2,0,3,10,60,60,80.00,102.00),(2,1,2,30,50,120,80.00,120.00),(2,2,4,25,50,120,80.00,120.00),(2,3,5,15,50,120,80.00,120.00),(3,0,3,5,30,60,80.00,120.00),(3,1,2,40,50,120,80.00,120.00),(3,2,4,35,50,120,80.00,120.00),(3,3,5,25,50,120,80.00,120.00),(4,0,3,12,40,60,80.00,120.00),(4,1,2,20,50,120,80.00,120.00),(4,2,4,25,50,120,80.00,120.00),(4,3,5,15,50,120,80.00,120.00),(5,0,3,24,100,60,80.00,120.00),(5,1,2,50,80,120,80.00,120.00),(5,2,4,40,80,120,80.00,120.00),(5,3,5,20,80,120,80.00,120.00),(6,0,3,8,20,60,80.00,120.00),(6,1,2,45,65,120,80.00,120.00),(6,2,4,50,65,120,80.00,120.00),(6,3,5,30,65,120,80.00,120.00),(7,0,3,60,120,60,80.00,120.00),(7,1,2,10,20,120,80.00,120.00),(7,2,4,20,20,120,80.00,120.00),(7,3,5,15,20,120,80.00,120.00),(8,0,3,20,150,60,80.00,120.00),(8,1,2,30,40,120,80.00,120.00),(8,2,4,30,40,120,80.00,120.00),(8,3,5,12,40,120,80.00,120.00),(9,0,3,15,50,60,80.00,120.00),(9,1,2,20,20,120,80.00,120.00),(9,2,4,20,20,120,80.00,120.00),(9,3,5,8,20,120,80.00,120.00);
/*!40000 ALTER TABLE `t_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_part`
--

DROP TABLE IF EXISTS `t_part`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_part` (
  `id_part` int(11) NOT NULL,
  `id_game` int(11) NOT NULL,
  `part_description` varchar(50) DEFAULT NULL,
  `unit` varchar(10) DEFAULT NULL,
  `volume` decimal(10,2) DEFAULT NULL,
  `weight` decimal(10,2) DEFAULT NULL,
  `quantity_palette` int(11) DEFAULT NULL,
  `current_stock` int(11) DEFAULT NULL,
  `price_for_sale` decimal(10,2) DEFAULT NULL,
  `owner` varchar(10) DEFAULT NULL,
  `part_design` varchar(100) DEFAULT NULL,
  `part_design_scale` decimal(10,2) DEFAULT NULL,
  `part_design_color` varchar(45) DEFAULT NULL,
  `factor` decimal(10,2) DEFAULT NULL,
  `output_quantity` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_part`,`id_game`),
  KEY `fk_id_game_0001` (`id_game`),
  CONSTRAINT `fk_id_game_0001` FOREIGN KEY (`id_game`) REFERENCES `t_game` (`id_game`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_part`
--

LOCK TABLES `t_part` WRITE;
/*!40000 ALTER TABLE `t_part` DISABLE KEYS */;
INSERT INTO `t_part` VALUES (1,0,'Part One','PART',100.00,100.00,100,0,0.00,'User','Models/Screw/screw.j3o',0.01,'Red',0.97,1),(1,1,'Part One','PART',100.00,100.00,50,0,0.00,'User','',0.00,'Red',0.95,1),(1,2,'Part One','PART',100.00,100.00,50,0,0.00,'User','',0.00,'Red',0.95,1),(1,3,'Part One','PART',100.00,100.00,50,0,0.00,'User','',0.00,'Red',0.95,1),(2,0,'Part Two','PART',100.00,100.00,50,0,0.00,'User','Models/Screw2/screw2.j3o',0.01,'Blue',0.97,1),(2,1,'Part Two','PART',100.00,100.00,50,0,150.00,'User','',0.00,'Blue',0.99,4),(2,2,'Part Two','PART',100.00,100.00,50,0,0.00,'User','',0.00,'Blue',0.99,1),(2,3,'Part Two','PART',100.00,100.00,50,0,0.00,'User','',0.00,'Blue',0.99,1),(3,0,'Part Three','PART',300.00,300.00,25,0,150.00,'User','Models/Chair/chair.j3o',0.05,'Green',0.97,1),(3,2,'Part Three','PART',100.00,100.00,50,0,0.00,'User','',0.00,'Green',0.92,1),(3,3,'Part Three','PART',100.00,100.00,50,0,0.00,'User','',0.00,'Green',0.92,1),(4,2,'Part Four','PART',100.00,100.00,50,0,200.00,'User','',0.00,'Brown',0.97,1),(4,3,'Part Four','PART',100.00,100.00,50,0,0.00,'User','',0.00,'Brown',0.97,1),(5,3,'Part Five','PART',100.00,100.00,50,0,200.00,'User','',0.00,'Magenta',0.95,1);
/*!40000 ALTER TABLE `t_part` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_player`
--

DROP TABLE IF EXISTS `t_player`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_player` (
  `id_player` int(11) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `nick_name` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(20) NOT NULL,
  `login_time` datetime DEFAULT NULL,
  `game_time` int(11) DEFAULT NULL,
  `last_login_time` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_player`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_player`
--

LOCK TABLES `t_player` WRITE;
/*!40000 ALTER TABLE `t_player` DISABLE KEYS */;
INSERT INTO `t_player` VALUES (0,'DefaultPlayer','def','default@gaming.com','123456',NULL,NULL,NULL),(1,'newUser','','new@email.com','123456',NULL,NULL,1352595673),(2,'user1','','user1@email.com','123456',NULL,NULL,NULL),(3,'user2','','user2@email.com','123456',NULL,NULL,NULL),(4,'yserYYY','','yyy@mail.com','123456',NULL,NULL,NULL),(5,'usezzzz','','userz@mail.com','123456',NULL,NULL,NULL),(6,'newMAil','','new@mail.com','123456',NULL,NULL,NULL),(7,'mmmmmmm','','mmm@mmm.com','123456',NULL,NULL,NULL),(8,'nnnnnn','','nnn@nnn.com','123456','2012-11-02 20:18:40',58,1352791141),(9,'otroUser','','otro@otro.com','123456',NULL,NULL,NULL),(10,'onemore','','one@one.com','123456','2012-09-13 23:21:29',1,NULL),(11,'David Bengoa','','davidbengoa@hotmail.com','#abcd#','2012-10-10 17:28:07',NULL,NULL),(12,'test testito','','test@testingmail.edu','123456','2012-10-10 13:00:37',NULL,NULL),(13,'otro testito','','test@testito.edu','123456','2012-10-10 13:07:27',NULL,NULL),(14,'test new user','','david@david.edu','dtzxxzf6qj1a','2012-10-10 15:18:19',NULL,NULL),(15,'david bengoa','','david.bengoa@uprm1.edu','g2aizv223wyj','2012-10-10 15:21:24',NULL,NULL),(16,'david bengoa teran','','david.bengoa@uprrp.edu','123456','2012-10-10 15:40:08',NULL,NULL),(17,'david bengoa','','david.bengoa@upr.edu1','eaycq3c7r31t','2012-10-10 15:42:22',0,NULL),(18,'david bengoa teran','','david.bengoa@upr.edu2','8r2wy5opdsdj','2012-11-10 17:08:19',NULL,NULL),(19,'david xxx','','david.bengoa@upr.edu3','vjoau61azcrw','2012-11-10 17:28:19',NULL,NULL),(20,'david yyy','','david.bengoa@upr.edu4','b1bfn1m2lr3i','2012-11-10 17:33:16',0,NULL),(21,'david zzzz','','david.bengoa@upr.edu5','lvj83zx2dugw','2012-11-10 17:46:11',NULL,NULL),(22,'daviddddddddddd','','david.bengoa@upr.edu6','6yylpmq2dtav','2012-11-10 19:20:03',NULL,NULL),(23,'xxx david','','david.bengoa@upr.edu7','jjf8r5f2s1oi','2012-11-10 19:40:11',NULL,NULL),(24,'sssssssssss','','david.bengoa@upr.edu8','ii1915rjkmw7','2012-11-10 19:41:52',NULL,NULL),(25,'wwww wwww','','david.bengoa@upr.edu9','v012051aeztm','2012-11-10 19:44:22',NULL,NULL),(26,'lolololol','','david.bengoa@upr.edu10','a71gd3wdpdxy','2012-11-10 19:47:58',NULL,NULL),(27,'dav dav dav','','david.bengoa@upr.edu11','ldo3nad66pm4','2012-11-10 19:51:45',0,1352595745),(28,'rob rob rob','','david.bengoa@upr.edu12','zfgu4dn45t1w','2012-11-11 18:02:15',NULL,1352671335),(29,'dav rob','','david.bengoa@upr.edu13','hggxkm4fk5ie','2012-11-11 18:04:14',NULL,1352671454),(30,'dav rob ben ter','','david.bengoa@upmr.edu','0vnez7zq7t09','2012-11-11 18:26:41',6,1352672801),(31,'david bnegoa teranci','','david.bengoa@upr.edu','123456','2012-11-13 03:24:13',32,1352794170);
/*!40000 ALTER TABLE `t_player` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_player_log`
--

DROP TABLE IF EXISTS `t_player_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_player_log` (
  `id_player_log` int(11) NOT NULL,
  `id_player` int(11) NOT NULL,
  `login_time` int(11) DEFAULT NULL,
  `logout_time` int(11) DEFAULT NULL,
  `minutes_time` int(11) DEFAULT NULL,
  `game_level` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_player_log`,`id_player`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_player_log`
--

LOCK TABLES `t_player_log` WRITE;
/*!40000 ALTER TABLE `t_player_log` DISABLE KEYS */;
INSERT INTO `t_player_log` VALUES (1,8,NULL,NULL,2897,NULL),(1,31,1352791613,1352791702,1,'Level 1'),(2,8,NULL,NULL,37,NULL),(2,31,1352791702,1352791703,0,'Level 1'),(3,8,NULL,NULL,34,NULL),(3,31,1352791613,1352791702,1,'Level 1'),(4,8,NULL,NULL,23,NULL),(4,31,1352791702,1352791703,0,'Level 1'),(5,8,NULL,NULL,1,NULL),(5,31,1352791793,1352791878,0,'Level 1'),(6,8,NULL,NULL,41,NULL),(6,31,1352791793,1352791878,1,'Level 1'),(7,8,NULL,NULL,40,NULL),(7,31,1352792615,1352792989,6,'Level 4'),(8,8,NULL,NULL,31,NULL),(8,31,1352792615,1352792989,6,'Level 4'),(9,8,NULL,NULL,15,NULL),(9,31,1352793144,1352793243,2,'Level 3'),(10,8,NULL,NULL,8,NULL),(10,31,1352793877,1352793894,0,'Level 4'),(11,8,NULL,NULL,2687,NULL),(12,8,NULL,NULL,2675,NULL),(13,8,NULL,NULL,2666,NULL),(14,8,NULL,NULL,2660,NULL),(15,8,NULL,NULL,2659,NULL),(16,8,NULL,NULL,2656,NULL),(17,8,NULL,NULL,2655,NULL),(18,8,NULL,NULL,2654,NULL),(19,8,NULL,NULL,2653,NULL),(20,8,NULL,NULL,2646,NULL),(21,8,NULL,NULL,2634,NULL),(22,8,NULL,NULL,2625,NULL),(23,8,NULL,NULL,2622,NULL),(24,8,NULL,NULL,2615,NULL),(25,8,NULL,NULL,2602,NULL),(26,8,NULL,NULL,2576,NULL),(27,8,NULL,NULL,2531,NULL),(28,8,NULL,NULL,2529,NULL),(29,8,NULL,NULL,2525,NULL),(30,8,NULL,NULL,2509,NULL),(31,8,NULL,NULL,2491,NULL),(32,8,NULL,NULL,2489,NULL),(33,8,NULL,NULL,1377,NULL),(34,8,NULL,NULL,1350,NULL),(35,8,NULL,NULL,1338,NULL),(36,8,NULL,NULL,1337,NULL),(37,8,NULL,NULL,1252,NULL),(38,8,NULL,NULL,1185,NULL),(39,8,NULL,NULL,1169,NULL),(40,8,NULL,NULL,1157,NULL),(41,8,NULL,NULL,1151,NULL),(42,8,NULL,NULL,1134,NULL),(43,8,NULL,NULL,1084,NULL),(44,8,NULL,NULL,1076,NULL),(45,8,NULL,NULL,1069,NULL),(46,8,NULL,NULL,1049,NULL),(47,8,NULL,NULL,1043,NULL),(48,8,NULL,NULL,1036,NULL),(49,8,NULL,NULL,1002,NULL),(50,8,NULL,NULL,995,NULL),(51,8,NULL,NULL,992,NULL),(52,8,NULL,NULL,989,NULL),(53,8,NULL,NULL,969,NULL),(54,8,NULL,NULL,946,NULL),(55,8,NULL,NULL,944,NULL),(56,8,NULL,NULL,943,NULL),(57,8,NULL,NULL,940,NULL),(58,8,NULL,NULL,933,NULL),(59,8,NULL,NULL,927,NULL),(60,8,NULL,NULL,923,NULL),(61,8,NULL,NULL,919,NULL),(62,8,NULL,NULL,918,NULL),(63,8,NULL,NULL,912,NULL),(64,8,NULL,NULL,280,NULL),(65,8,NULL,NULL,278,NULL),(66,8,NULL,NULL,245,NULL),(67,8,NULL,NULL,239,NULL),(68,8,NULL,NULL,235,NULL),(69,8,NULL,NULL,229,NULL),(70,8,NULL,NULL,226,NULL),(71,8,NULL,NULL,222,NULL),(72,8,NULL,NULL,221,NULL),(73,8,NULL,NULL,214,NULL),(74,8,NULL,NULL,209,NULL),(75,8,NULL,NULL,191,NULL),(76,8,NULL,NULL,184,NULL),(77,8,NULL,NULL,181,NULL),(78,8,NULL,NULL,179,NULL),(79,8,NULL,NULL,178,NULL),(80,8,NULL,NULL,176,NULL),(81,8,NULL,NULL,175,NULL),(82,8,NULL,NULL,171,NULL),(83,8,NULL,NULL,120,NULL),(84,8,NULL,NULL,30,NULL),(85,8,NULL,NULL,12,NULL),(86,8,NULL,NULL,10,NULL),(87,8,NULL,NULL,1,NULL),(88,8,NULL,NULL,0,NULL),(89,8,NULL,NULL,1,NULL),(90,8,NULL,NULL,0,NULL),(91,8,NULL,NULL,0,NULL),(92,8,NULL,NULL,208,NULL),(93,8,NULL,NULL,205,NULL),(94,8,NULL,NULL,186,NULL),(95,8,NULL,NULL,179,NULL),(96,8,NULL,NULL,163,NULL),(97,8,NULL,NULL,121,NULL),(98,8,NULL,NULL,112,NULL),(99,8,NULL,NULL,106,NULL),(100,8,NULL,NULL,105,NULL),(101,8,NULL,NULL,102,NULL),(102,8,NULL,NULL,101,NULL),(103,8,NULL,NULL,95,NULL),(104,8,NULL,NULL,94,NULL),(105,8,NULL,NULL,89,NULL),(106,8,NULL,NULL,87,NULL),(107,8,NULL,NULL,86,NULL),(108,8,NULL,NULL,82,NULL),(109,8,NULL,NULL,81,NULL),(110,8,NULL,NULL,80,NULL),(111,8,NULL,NULL,75,NULL),(112,8,NULL,NULL,73,NULL),(113,8,NULL,NULL,63,NULL),(114,8,NULL,NULL,63,NULL),(115,8,NULL,NULL,60,NULL),(116,8,NULL,NULL,60,NULL),(117,8,NULL,NULL,59,NULL),(118,8,NULL,NULL,57,NULL),(119,8,NULL,NULL,55,NULL),(120,8,NULL,NULL,55,NULL),(121,8,NULL,NULL,18,NULL),(122,8,NULL,NULL,10,NULL),(123,8,NULL,NULL,8,NULL),(124,8,NULL,NULL,8,NULL),(125,8,NULL,NULL,7,NULL),(126,8,NULL,NULL,11,NULL),(127,8,NULL,NULL,10,NULL),(128,8,NULL,NULL,1519,NULL),(129,8,NULL,NULL,1516,NULL),(130,8,NULL,NULL,1515,NULL),(131,8,NULL,NULL,1514,NULL),(132,8,NULL,NULL,1513,NULL),(133,8,NULL,NULL,1508,NULL),(134,8,NULL,NULL,1507,NULL),(135,8,NULL,NULL,1500,NULL),(136,8,NULL,NULL,1499,NULL),(137,8,NULL,NULL,1498,NULL),(138,8,NULL,NULL,1496,NULL),(139,8,NULL,NULL,1495,NULL),(140,8,NULL,NULL,1494,NULL),(141,8,NULL,NULL,1493,NULL),(142,8,NULL,NULL,1476,NULL),(143,8,NULL,NULL,1474,NULL),(144,8,NULL,NULL,1472,NULL),(145,8,NULL,NULL,1471,NULL),(146,8,NULL,NULL,1469,NULL),(147,8,NULL,NULL,1440,NULL),(148,8,NULL,NULL,1430,NULL),(149,8,NULL,NULL,1429,NULL),(150,8,NULL,NULL,1428,NULL),(151,8,NULL,NULL,509,NULL),(152,8,NULL,NULL,495,NULL),(153,8,NULL,NULL,493,NULL),(154,8,NULL,NULL,493,NULL),(155,8,NULL,NULL,486,NULL),(156,8,NULL,NULL,481,NULL),(157,8,NULL,NULL,458,NULL),(158,8,NULL,NULL,411,NULL),(159,8,NULL,NULL,32,NULL),(160,8,NULL,NULL,17,NULL),(161,8,NULL,NULL,43,NULL),(162,8,NULL,NULL,136,NULL),(163,8,NULL,NULL,74,NULL),(164,8,NULL,NULL,69,NULL),(165,8,NULL,NULL,61,NULL),(166,8,NULL,NULL,59,NULL),(167,8,NULL,NULL,59,NULL),(168,8,NULL,NULL,56,NULL),(169,8,NULL,NULL,51,NULL),(170,8,NULL,NULL,50,NULL),(171,8,NULL,NULL,48,NULL),(172,8,NULL,NULL,46,NULL),(173,8,NULL,NULL,42,NULL),(174,8,NULL,NULL,40,NULL),(175,8,NULL,NULL,39,NULL),(176,8,NULL,NULL,38,NULL),(177,8,NULL,NULL,33,NULL),(178,8,NULL,NULL,29,NULL),(179,8,NULL,NULL,26,NULL),(180,8,NULL,NULL,19,NULL),(181,8,NULL,NULL,10,NULL),(182,8,NULL,NULL,5,NULL),(183,8,NULL,NULL,3,NULL),(184,8,NULL,NULL,1,NULL),(185,8,NULL,NULL,0,NULL),(186,8,NULL,NULL,26,NULL),(187,8,NULL,NULL,23,NULL),(188,8,NULL,NULL,2,NULL),(189,8,NULL,NULL,2,NULL),(190,8,NULL,NULL,51,NULL),(191,8,NULL,NULL,41,NULL),(192,8,NULL,NULL,36,NULL),(193,8,NULL,NULL,30,NULL),(194,8,NULL,NULL,28,NULL),(195,8,NULL,NULL,25,NULL),(196,8,NULL,NULL,23,NULL),(197,8,NULL,NULL,22,NULL),(198,8,NULL,NULL,20,NULL),(199,8,NULL,NULL,19,NULL),(200,8,NULL,NULL,1,NULL),(201,8,NULL,NULL,0,NULL),(202,8,NULL,NULL,NULL,NULL),(203,8,NULL,NULL,NULL,NULL),(204,8,NULL,NULL,NULL,NULL),(205,8,NULL,NULL,1,NULL),(206,8,NULL,NULL,10,'Level 2'),(207,8,NULL,NULL,1,'Level 3'),(208,8,NULL,NULL,4,'Level 3'),(209,8,NULL,NULL,0,'Level 1'),(210,8,NULL,NULL,7,'Level 3'),(211,8,NULL,NULL,120,'TESTTT'),(212,8,NULL,NULL,2,'Level 1'),(213,8,NULL,NULL,NULL,'Level 4'),(214,8,NULL,NULL,NULL,'Level 4'),(215,8,1352750155,1352750245,1,'Level 4'),(216,8,1352758191,1352758338,2,'Level 4'),(217,8,1352784051,1352784301,4,'Level 3'),(218,8,1352784545,1352784669,0,'Level 1'),(219,8,1352784758,1352785045,5,'Level 2'),(220,8,1352784758,1352785045,4,'Level 2'),(221,8,1352787876,1352788000,2,'Level 2'),(222,8,1352788885,1352789120,3,'Level 2'),(223,8,1352789190,1352789325,2,'Level 4'),(224,8,1352789699,1352789711,0,'Level 3'),(225,8,1352789711,1352790191,8,'Level 1'),(226,8,1352790799,1352791112,5,'Level 1');
/*!40000 ALTER TABLE `t_player_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_purchase`
--

DROP TABLE IF EXISTS `t_purchase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_purchase` (
  `id_purchase` int(11) NOT NULL,
  `id_game` int(11) NOT NULL,
  `id_station` int(11) DEFAULT NULL,
  `id_supplier` int(11) DEFAULT NULL,
  `order_point` int(11) DEFAULT NULL,
  `order_quantity` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_purchase`,`id_game`),
  KEY `fk_{F4EED5BB-4D6A-4C46-9A2A-A5E8404615A6}` (`id_station`),
  KEY `purchase_activity` (`id_purchase`),
  KEY `puchase_game` (`id_game`),
  CONSTRAINT `fk_{F4EED5BB-4D6A-4C46-9A2A-A5E8404615A6}` FOREIGN KEY (`id_station`) REFERENCES `t_station` (`id_station`),
  CONSTRAINT `puchase_game` FOREIGN KEY (`id_game`) REFERENCES `t_game` (`id_game`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `purchase_activity` FOREIGN KEY (`id_purchase`) REFERENCES `t_activity` (`id_activity`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_purchase`
--

LOCK TABLES `t_purchase` WRITE;
/*!40000 ALTER TABLE `t_purchase` DISABLE KEYS */;
INSERT INTO `t_purchase` VALUES (1,0,2,1,10,100),(1,1,3,1,20,20),(1,2,3,1,20,20),(1,3,3,1,20,20),(2,0,2,1,10,50),(2,2,3,2,20,20),(2,3,3,2,20,20),(3,3,3,2,20,20);
/*!40000 ALTER TABLE `t_purchase` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_ship`
--

DROP TABLE IF EXISTS `t_ship`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_ship` (
  `id_ship` int(11) NOT NULL,
  `id_game` int(11) NOT NULL,
  `id_station` int(11) DEFAULT NULL,
  `load_quantity` int(11) DEFAULT NULL,
  `shipping_time_distn` varchar(10) DEFAULT NULL,
  `shipping_time_parameter1` decimal(5,2) DEFAULT NULL,
  `shipping_time_parameter2` decimal(5,2) DEFAULT NULL,
  PRIMARY KEY (`id_ship`,`id_game`),
  KEY `ship_activity` (`id_ship`),
  KEY `ship_game` (`id_game`),
  KEY `ship_station` (`id_station`),
  CONSTRAINT `ship_activity` FOREIGN KEY (`id_ship`) REFERENCES `t_activity` (`id_activity`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `ship_game` FOREIGN KEY (`id_game`) REFERENCES `t_game` (`id_game`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `ship_station` FOREIGN KEY (`id_station`) REFERENCES `t_station` (`id_station`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_ship`
--

LOCK TABLES `t_ship` WRITE;
/*!40000 ALTER TABLE `t_ship` DISABLE KEYS */;
INSERT INTO `t_ship` VALUES (7,1,7,1,'',8.00,0.00),(8,0,4,1,'',8.00,0.00),(13,2,9,1,'',8.00,0.00),(16,3,9,1,'',8.00,0.00);
/*!40000 ALTER TABLE `t_ship` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_skill`
--

DROP TABLE IF EXISTS `t_skill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_skill` (
  `id_skill` int(11) NOT NULL,
  `id_game` int(11) NOT NULL,
  `skill_description` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_skill`,`id_game`),
  KEY `fk_id_game_0019` (`id_game`),
  CONSTRAINT `fk_id_game_0019` FOREIGN KEY (`id_game`) REFERENCES `t_game` (`id_game`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_skill`
--

LOCK TABLES `t_skill` WRITE;
/*!40000 ALTER TABLE `t_skill` DISABLE KEYS */;
INSERT INTO `t_skill` VALUES (0,0,'Pick up'),(0,1,'Pick up'),(0,2,'Pick up'),(0,3,'Pick up'),(1,0,'Move'),(1,1,'Move'),(1,2,'Move'),(1,3,'Move'),(2,0,'Placement'),(2,1,'Placement'),(2,2,'Placement'),(2,3,'Placement'),(3,0,'Store'),(3,1,'Store'),(3,2,'Store'),(3,3,'Store'),(4,0,'Operate'),(4,1,'Operate'),(4,2,'Operate'),(4,3,'Operate'),(5,0,'Carrier'),(5,1,'Carrier'),(5,2,'Carrier'),(5,3,'Carrier'),(6,0,'Assembler'),(6,1,'Assembler'),(6,2,'Assembler'),(6,3,'Assembler');
/*!40000 ALTER TABLE `t_skill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_skill_activity`
--

DROP TABLE IF EXISTS `t_skill_activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_skill_activity` (
  `id_activity` int(11) NOT NULL,
  `id_skill` int(11) NOT NULL,
  `id_game` int(11) NOT NULL,
  PRIMARY KEY (`id_activity`,`id_skill`,`id_game`),
  KEY `skill_activity_id_activity` (`id_activity`),
  KEY `skill_activity_id_skill` (`id_skill`),
  KEY `skill_activity_id_game` (`id_game`),
  CONSTRAINT `skill_activity_id_activity` FOREIGN KEY (`id_activity`) REFERENCES `t_activity` (`id_activity`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `skill_activity_id_game` FOREIGN KEY (`id_game`) REFERENCES `t_game` (`id_game`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `skill_activity_id_skill` FOREIGN KEY (`id_skill`) REFERENCES `t_skill` (`id_skill`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_skill_activity`
--

LOCK TABLES `t_skill_activity` WRITE;
/*!40000 ALTER TABLE `t_skill_activity` DISABLE KEYS */;
INSERT INTO `t_skill_activity` VALUES (2,0,1),(2,1,1),(2,2,1),(2,3,1),(2,4,1),(2,5,1),(3,0,1),(3,0,2),(3,1,1),(3,1,2),(3,2,1),(3,2,2),(3,3,1),(3,3,2),(3,4,0),(3,4,1),(3,4,2),(3,5,1),(3,5,2),(3,6,0),(4,0,0),(4,0,2),(4,0,3),(4,1,0),(4,1,2),(4,1,3),(4,2,0),(4,2,2),(4,2,3),(4,3,0),(4,3,2),(4,3,3),(4,4,0),(4,4,1),(4,4,2),(4,4,3),(4,5,0),(4,5,2),(4,5,3),(4,6,1),(5,0,0),(5,0,1),(5,0,2),(5,0,3),(5,1,0),(5,1,1),(5,1,2),(5,1,3),(5,2,0),(5,2,1),(5,2,2),(5,2,3),(5,3,0),(5,3,1),(5,3,2),(5,3,3),(5,4,0),(5,4,1),(5,4,2),(5,4,3),(5,5,0),(5,5,1),(5,5,2),(5,5,3),(6,0,0),(6,0,1),(6,0,2),(6,0,3),(6,1,0),(6,1,1),(6,1,2),(6,1,3),(6,2,0),(6,2,1),(6,2,2),(6,2,3),(6,3,0),(6,3,1),(6,3,2),(6,3,3),(6,4,0),(6,4,1),(6,4,2),(6,4,3),(6,5,0),(6,5,1),(6,5,2),(6,5,3),(7,0,0),(7,0,3),(7,1,0),(7,1,3),(7,2,0),(7,2,3),(7,3,0),(7,3,3),(7,4,0),(7,4,2),(7,4,3),(7,5,0),(7,5,3),(7,6,2),(8,0,2),(8,0,3),(8,1,2),(8,1,3),(8,2,2),(8,2,3),(8,3,2),(8,3,3),(8,4,2),(8,4,3),(8,5,2),(8,5,3),(9,0,0),(9,0,2),(9,1,0),(9,1,2),(9,2,0),(9,2,2),(9,3,0),(9,3,2),(9,4,0),(9,4,2),(9,4,3),(9,5,0),(9,5,2),(9,6,3),(10,0,0),(10,0,3),(10,1,0),(10,1,3),(10,2,0),(10,2,3),(10,3,0),(10,3,3),(10,4,0),(10,4,2),(10,4,3),(10,5,0),(10,5,3),(10,6,2),(11,0,2),(11,0,3),(11,1,2),(11,1,3),(11,2,2),(11,2,3),(11,3,2),(11,3,3),(11,4,2),(11,4,3),(11,5,2),(11,5,3),(12,0,2),(12,0,3),(12,1,2),(12,1,3),(12,2,2),(12,2,3),(12,3,2),(12,3,3),(12,4,2),(12,4,3),(12,5,2),(12,5,3),(13,4,3),(13,6,3),(14,0,3),(14,1,3),(14,2,3),(14,3,3),(14,4,3),(14,5,3),(15,0,3),(15,1,3),(15,2,3),(15,3,3),(15,4,3),(15,5,3);
/*!40000 ALTER TABLE `t_skill_activity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_skill_operator`
--

DROP TABLE IF EXISTS `t_skill_operator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_skill_operator` (
  `id_skill` int(11) NOT NULL,
  `id_operator` int(11) NOT NULL,
  `id_game` int(11) NOT NULL,
  `efficiency` decimal(10,2) DEFAULT NULL,
  `dist_param1` decimal(10,2) DEFAULT NULL,
  `dist_param2` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id_skill`,`id_operator`,`id_game`),
  KEY `fk_{3A660564-37B5-4207-80E0-9D06B7947121}` (`id_skill`),
  KEY `fk_{5FA8F82D-AC20-4DB6-B8E1-03BC47CB489F}` (`id_operator`),
  KEY `skillOperatorGame` (`id_game`),
  CONSTRAINT `fk_{3A660564-37B5-4207-80E0-9D06B7947121}` FOREIGN KEY (`id_skill`) REFERENCES `t_skill` (`id_skill`),
  CONSTRAINT `fk_{5FA8F82D-AC20-4DB6-B8E1-03BC47CB489F}` FOREIGN KEY (`id_operator`) REFERENCES `t_operator` (`id_operator`),
  CONSTRAINT `skillOperatorGame` FOREIGN KEY (`id_game`) REFERENCES `t_game` (`id_game`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_skill_operator`
--

LOCK TABLES `t_skill_operator` WRITE;
/*!40000 ALTER TABLE `t_skill_operator` DISABLE KEYS */;
INSERT INTO `t_skill_operator` VALUES (0,1,0,0.90,0.80,1.30),(0,1,1,0.95,0.80,1.30),(0,1,2,0.95,0.80,1.30),(0,1,3,0.95,0.80,1.30),(0,2,0,0.95,0.80,1.30),(0,2,1,0.85,0.80,1.30),(0,2,2,0.85,0.80,1.30),(0,2,3,0.85,0.80,1.30),(0,3,0,0.99,0.80,1.30),(0,3,1,0.88,0.80,1.30),(0,3,2,0.88,0.80,1.30),(0,3,3,0.88,0.80,1.30),(0,4,0,0.98,0.80,1.30),(0,4,1,0.87,0.80,1.30),(0,4,2,0.87,0.80,1.30),(0,4,3,0.87,0.80,1.30),(0,5,0,0.85,0.80,1.30),(0,5,1,0.90,0.80,1.30),(0,5,2,0.90,0.80,1.30),(0,5,3,0.90,0.80,1.30),(0,6,0,0.86,0.80,1.30),(0,6,1,0.88,0.80,1.30),(0,6,2,0.88,0.80,1.30),(0,6,3,0.88,0.80,1.30),(0,7,2,0.95,0.80,1.30),(0,7,3,0.95,0.80,1.30),(0,8,2,0.85,0.80,1.30),(0,8,3,0.85,0.80,1.30),(1,1,0,0.97,0.80,1.30),(1,1,1,0.90,0.80,1.30),(1,1,2,0.90,0.80,1.30),(1,1,3,0.90,0.80,1.30),(1,2,0,0.95,0.80,1.30),(1,2,1,0.91,0.80,1.30),(1,2,2,0.91,0.80,1.30),(1,2,3,0.91,0.80,1.30),(1,3,0,0.93,0.80,1.30),(1,3,1,0.94,0.80,1.30),(1,3,2,0.94,0.80,1.30),(1,3,3,0.94,0.80,1.30),(1,4,0,0.94,0.80,1.30),(1,4,1,0.91,0.80,1.30),(1,4,2,0.91,0.80,1.30),(1,4,3,0.91,0.80,1.30),(1,5,0,0.90,0.80,1.30),(1,5,1,0.95,0.80,1.30),(1,5,2,0.95,0.80,1.30),(1,5,3,0.95,0.80,1.30),(1,6,0,0.88,0.80,1.30),(1,6,1,0.90,0.80,1.30),(1,6,2,0.90,0.80,1.30),(1,6,3,0.90,0.80,1.30),(1,7,2,0.90,0.80,1.30),(1,7,3,0.90,0.80,1.30),(1,8,2,0.91,0.80,1.30),(1,8,3,0.91,0.80,1.30),(2,1,0,0.97,0.80,1.30),(2,1,1,0.99,0.80,1.30),(2,1,2,0.99,0.80,1.30),(2,1,3,0.99,0.80,1.30),(2,2,0,0.90,0.80,1.30),(2,2,1,0.94,0.80,1.30),(2,2,2,0.94,0.80,1.30),(2,2,3,0.94,0.80,1.30),(2,3,0,0.96,0.80,1.30),(2,3,1,0.97,0.80,1.30),(2,3,2,0.97,0.80,1.30),(2,3,3,0.97,0.80,1.30),(2,4,0,0.93,0.80,1.30),(2,4,1,0.94,0.80,1.30),(2,4,2,0.94,0.80,1.30),(2,4,3,0.94,0.80,1.30),(2,5,0,0.96,0.80,1.30),(2,5,1,0.91,0.80,1.30),(2,5,2,0.91,0.80,1.30),(2,5,3,0.91,0.80,1.30),(2,6,0,0.95,0.80,1.30),(2,6,1,0.89,0.80,1.30),(2,6,2,0.89,0.80,1.30),(2,6,3,0.89,0.80,1.30),(2,7,2,0.99,0.80,1.30),(2,7,3,0.99,0.80,1.30),(2,8,2,0.94,0.80,1.30),(2,8,3,0.94,0.80,1.30),(3,1,0,0.92,0.80,1.30),(3,1,1,0.88,0.80,1.30),(3,1,2,0.88,0.80,1.30),(3,1,3,0.88,0.80,1.30),(3,2,0,0.89,0.80,1.30),(3,2,1,0.97,0.80,1.30),(3,2,2,0.97,0.80,1.30),(3,2,3,0.97,0.80,1.30),(3,3,0,0.88,0.80,1.30),(3,3,1,0.91,0.80,1.30),(3,3,2,0.91,0.80,1.30),(3,3,3,0.91,0.80,1.30),(3,4,0,0.90,0.80,1.30),(3,4,1,0.97,0.80,1.30),(3,4,2,0.97,0.80,1.30),(3,4,3,0.97,0.80,1.30),(3,5,0,0.79,0.80,1.30),(3,5,1,0.99,0.80,1.30),(3,5,2,0.99,0.80,1.30),(3,5,3,0.99,0.80,1.30),(3,6,0,0.92,0.80,1.30),(3,6,1,0.95,0.80,1.30),(3,6,2,0.95,0.80,1.30),(3,6,3,0.95,0.80,1.30),(3,7,2,0.88,0.80,1.30),(3,7,3,0.88,0.80,1.30),(3,8,2,0.97,0.80,1.30),(3,8,3,0.97,0.80,1.30),(4,1,0,0.94,0.80,1.30),(4,1,1,0.93,0.80,1.30),(4,1,2,0.93,0.80,1.30),(4,1,3,0.93,0.80,1.30),(4,2,0,0.85,0.80,1.30),(4,2,1,0.99,0.80,1.30),(4,2,2,0.99,0.80,1.30),(4,2,3,0.99,0.80,1.30),(4,3,0,0.87,0.80,1.30),(4,3,1,0.90,0.80,1.30),(4,3,2,0.90,0.80,1.30),(4,3,3,0.90,0.80,1.30),(4,4,0,0.83,0.80,1.30),(4,4,1,0.99,0.80,1.30),(4,4,2,0.99,0.80,1.30),(4,4,3,0.99,0.80,1.30),(4,5,0,0.82,0.80,1.30),(4,5,1,0.97,0.80,1.30),(4,5,2,0.97,0.80,1.30),(4,5,3,0.97,0.80,1.30),(4,6,0,0.96,0.80,1.30),(4,6,1,0.97,0.80,1.30),(4,6,2,0.97,0.80,1.30),(4,6,3,0.97,0.80,1.30),(4,7,2,0.93,0.80,1.30),(4,7,3,0.93,0.80,1.30),(4,8,2,0.99,0.80,1.30),(4,8,3,0.99,0.80,1.30),(5,1,0,0.96,0.80,1.30),(5,1,1,0.96,0.80,1.30),(5,1,2,0.96,0.80,1.30),(5,1,3,0.96,0.80,1.30),(5,2,0,0.93,0.80,1.30),(5,2,1,0.93,0.80,1.30),(5,2,2,0.93,0.80,1.30),(5,2,3,0.93,0.80,1.30),(5,3,0,0.91,0.80,1.30),(5,4,0,0.98,0.80,1.30),(5,5,1,0.94,0.80,1.30),(5,5,2,0.94,0.80,1.30),(5,5,3,0.94,0.80,1.30),(5,6,1,0.97,0.80,1.30),(5,6,2,0.97,0.80,1.30),(5,6,3,0.97,0.80,1.30),(5,7,2,0.94,0.80,1.30),(5,7,3,0.94,0.80,1.30),(5,8,2,0.97,0.80,1.30),(5,8,3,0.97,0.80,1.30),(6,3,1,0.91,0.80,1.30),(6,3,2,0.91,0.80,1.30),(6,3,3,0.91,0.80,1.30),(6,4,1,0.98,0.80,1.30),(6,4,2,0.98,0.80,1.30),(6,4,3,0.98,0.80,1.30),(6,5,0,0.94,0.80,1.30),(6,5,1,0.94,0.80,1.30),(6,5,2,0.94,0.80,1.30),(6,5,3,0.94,0.80,1.30),(6,6,0,0.97,0.80,1.30),(6,6,1,0.97,0.80,1.30),(6,6,2,0.97,0.80,1.30),(6,6,3,0.97,0.80,1.30),(6,7,2,0.94,0.80,1.30),(6,7,3,0.94,0.80,1.30),(6,8,2,0.97,0.80,1.30),(6,8,3,0.97,0.80,1.30);
/*!40000 ALTER TABLE `t_skill_operator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_station`
--

DROP TABLE IF EXISTS `t_station`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_station` (
  `id_station` int(11) NOT NULL,
  `id_game` int(11) NOT NULL,
  `station_description` varchar(50) DEFAULT NULL,
  `station_location_x` int(11) DEFAULT NULL,
  `station_location_y` int(11) DEFAULT NULL,
  `size_w` decimal(10,2) DEFAULT NULL,
  `size_l` decimal(10,2) DEFAULT NULL,
  `price_for_purchase` decimal(10,2) DEFAULT NULL,
  `input_palette_capacity` int(11) DEFAULT NULL,
  `output_palette_capacity` int(11) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `owner` varchar(10) DEFAULT NULL,
  `station_type` varchar(45) DEFAULT NULL,
  `cost_per_hour` decimal(10,2) DEFAULT NULL,
  `station_design` varchar(100) DEFAULT NULL,
  `percentage_selected_slots` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id_station`,`id_game`),
  KEY `fk_id_game_0014` (`id_game`),
  CONSTRAINT `fk_id_game_0014` FOREIGN KEY (`id_game`) REFERENCES `t_game` (`id_game`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_station`
--

LOCK TABLES `t_station` WRITE;
/*!40000 ALTER TABLE `t_station` DISABLE KEYS */;
INSERT INTO `t_station` VALUES (1,0,'Assembly Process 1',10,-200,40.00,40.00,1.00,25,25,'Idle','User','AssembleZone',0.50,'Models/Stations/assemblyProcess1.png',0.50),(1,1,'Staff Zone',0,-330,40.00,20.00,1.00,0,0,'Idle','User','StaffZone',0.50,'',0.50),(1,2,'Staff Zone',0,-330,40.00,20.00,1.00,0,0,'Idle','User','StaffZone',0.50,'',0.50),(1,3,'Staff Zone',0,-330,40.00,20.00,1.00,0,0,'Idle','User','StaffZone',0.50,'',0.50),(2,0,'Receiving Dock',50,0,30.00,30.00,1.00,0,50,'Idle','User','PurchaseZone',0.50,'',0.50),(2,1,'Machine Zone',80,-330,80.00,20.00,1.00,0,0,'Idle','User','MachineZone',0.50,'',0.50),(2,2,'Machine Zone',80,-330,80.00,20.00,1.00,0,0,'Idle','User','MachineZone',0.50,'',0.50),(2,3,'Machine Zone',80,-330,80.00,20.00,1.00,0,0,'Idle','User','MachineZone',0.50,'',0.50),(3,0,'Finished Goods Storage',80,-150,50.00,50.00,1.00,200,200,'Idle','User','StorageFG',0.50,'',0.50),(3,1,'Receiving Dock',50,0,30.00,30.00,1.00,0,50,'Idle','User','PurchaseZone',0.50,'',0.50),(3,2,'Receiving Dock',50,0,30.00,30.00,1.00,0,50,'Idle','User','PurchaseZone',0.50,'',0.50),(3,3,'Receiving Dock',50,0,30.00,30.00,1.00,0,50,'Idle','User','PurchaseZone',0.50,'',0.50),(4,0,'Ship Station',100,0,30.00,30.00,1.00,200,200,'Idle','User','ShippingZone',0.50,'',0.50),(4,1,'Raw Material Storage',0,-100,40.00,40.00,1.00,25,25,'Idle','User','StorageRM',0.50,'',0.50),(4,2,'Raw Material Storage',0,-100,40.00,40.00,1.00,50,50,'Idle','User','StorageRM',0.50,'',0.50),(4,3,'Raw Material Storage',0,-100,40.00,40.00,1.00,50,50,'Idle','User','StorageRM',0.50,'',0.50),(5,0,'Staff Zone',0,-330,40.00,20.00,1.00,0,0,'Idle','User','StaffZone',0.50,'',0.50),(5,1,'Cutting Process 1',10,-200,40.00,40.00,1.00,25,25,'Idle','User','AssembleZone',0.50,'Models/Stations/cuttingProcess1.png',0.50),(5,2,'Assembly Process 1',10,-200,40.00,40.00,1.00,25,25,'Idle','User','AssembleZone',0.50,'Models/Stations/assemblyProcess1.png',0.50),(5,3,'Assembly Process 1',10,-200,40.00,40.00,1.00,25,25,'Idle','User','AssembleZone',0.50,'Models/Stations/assemblyProcess1.png',0.50),(6,0,'Machine Zone',80,-330,60.00,20.00,1.00,0,0,'Idle','User','MachineZone',0.50,'',0.50),(6,1,'Finished Goods Storage',80,-150,50.00,50.00,1.00,200,200,'Idle','User','StorageFG',0.50,'',0.50),(6,2,'Work In Process Storage',50,-280,40.00,40.00,1.00,50,50,'Idle','User','StorageIG',0.50,'',0.50),(6,3,'Work In Process Storage',50,-280,40.00,40.00,1.00,50,50,'Idle','User','StorageIG',0.50,'',0.50),(7,0,'Raw Material Storage',0,-100,40.00,40.00,1.00,25,25,'Idle','User','StorageRM',0.50,'',0.50),(7,1,'Ship Station',100,0,30.00,30.00,1.00,200,200,'Idle','User','ShippingZone',0.50,'',0.50),(7,2,'Assembly Process 2',80,-200,40.00,40.00,1.00,25,25,'Idle','User','AssembleZone',0.50,'Models/Stations/assemblyProcess2.png',0.50),(7,3,'Assembly Process 2',80,-200,40.00,40.00,1.00,25,25,'Idle','User','AssembleZone',0.50,'Models/Stations/assemblyProcess2.png',0.50),(8,1,'TEST',30,-60,10.00,10.00,1.00,0,0,'Idle','Game','StorageRM',0.50,'',0.50),(8,2,'Finished Goods Storage',80,-100,50.00,50.00,1.00,50,50,'Idle','User','StorageFG',0.50,'',0.50),(8,3,'Finished Goods Storage',80,-100,50.00,50.00,1.00,50,50,'Idle','User','StorageFG',0.50,'',0.50),(9,2,'Ship Station',100,0,30.00,30.00,1.00,200,200,'Idle','User','ShippingZone',0.50,'',0.50),(9,3,'Ship Station',100,0,30.00,30.00,1.00,200,200,'Idle','User','ShippingZone',0.50,'',0.50);
/*!40000 ALTER TABLE `t_station` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_stored_procedures_script`
--

DROP TABLE IF EXISTS `t_stored_procedures_script`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_stored_procedures_script` (
  `id_sp_script` int(11) NOT NULL,
  `sp_name` varchar(100) DEFAULT NULL,
  `variables_number` int(11) DEFAULT NULL,
  `definition` varchar(1000) DEFAULT NULL,
  `return_value` int(11) DEFAULT NULL,
  `table_name` varchar(100) DEFAULT NULL,
  `sp_type` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_sp_script`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_stored_procedures_script`
--

LOCK TABLES `t_stored_procedures_script` WRITE;
/*!40000 ALTER TABLE `t_stored_procedures_script` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_stored_procedures_script` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_supplier`
--

DROP TABLE IF EXISTS `t_supplier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_supplier` (
  `id_supplier` int(11) NOT NULL,
  `id_game` int(11) NOT NULL,
  `supplier_description` varchar(50) DEFAULT NULL,
  `supplier_location_x` int(11) DEFAULT NULL,
  `supplier_location_y` int(11) DEFAULT NULL,
  `service_level` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id_supplier`,`id_game`),
  KEY `fk_id_game_0008` (`id_game`),
  CONSTRAINT `fk_id_game_0008` FOREIGN KEY (`id_game`) REFERENCES `t_game` (`id_game`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_supplier`
--

LOCK TABLES `t_supplier` WRITE;
/*!40000 ALTER TABLE `t_supplier` DISABLE KEYS */;
INSERT INTO `t_supplier` VALUES (1,0,'Supplier Pepito',0,0,0.00),(1,1,'Supplier Coqui',0,0,0.00),(1,2,'Supplier Coqui',0,0,0.00),(1,3,'Supplier Coqui',0,0,0.00),(2,1,'Supplier Pepito',0,0,0.00),(2,2,'Supplier Pepito',0,0,0.00),(2,3,'Supplier Pepito',0,0,0.00);
/*!40000 ALTER TABLE `t_supplier` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_terrain`
--

DROP TABLE IF EXISTS `t_terrain`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_terrain` (
  `id_terrain` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `file_name` varchar(100) DEFAULT NULL,
  `local_scale` decimal(10,2) DEFAULT NULL,
  `model` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id_terrain`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_terrain`
--

LOCK TABLES `t_terrain` WRITE;
/*!40000 ALTER TABLE `t_terrain` DISABLE KEYS */;
INSERT INTO `t_terrain` VALUES (0,'Town (principal)','town.zip',2.00,'main.scene');
/*!40000 ALTER TABLE `t_terrain` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_terrain_reserved`
--

DROP TABLE IF EXISTS `t_terrain_reserved`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_terrain_reserved` (
  `id_terrain_reserved` int(11) NOT NULL,
  `id_terrain` int(11) NOT NULL,
  `location_x` int(11) DEFAULT NULL,
  `location_z` int(11) DEFAULT NULL,
  `width` int(11) DEFAULT NULL,
  `length` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_terrain_reserved`,`id_terrain`),
  KEY `terrain_terrain_reserved` (`id_terrain`),
  CONSTRAINT `terrain_terrain_reserved` FOREIGN KEY (`id_terrain`) REFERENCES `t_terrain` (`id_terrain`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_terrain_reserved`
--

LOCK TABLES `t_terrain_reserved` WRITE;
/*!40000 ALTER TABLE `t_terrain_reserved` DISABLE KEYS */;
INSERT INTO `t_terrain_reserved` VALUES (0,0,120,-160,4,370),(1,0,-25,-160,4,370),(2,0,48,-345,144,4),(3,0,48,24,144,4),(4,0,5,-18,60,84);
/*!40000 ALTER TABLE `t_terrain_reserved` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_to_update`
--

DROP TABLE IF EXISTS `t_to_update`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_to_update` (
  `id_to_update` int(11) NOT NULL,
  `id_game` int(11) DEFAULT NULL,
  `game_table_key` int(11) DEFAULT NULL,
  `game_table` varchar(45) DEFAULT NULL,
  `action_to_do` varchar(45) DEFAULT NULL,
  `creation_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id_to_update`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_to_update`
--

LOCK TABLES `t_to_update` WRITE;
/*!40000 ALTER TABLE `t_to_update` DISABLE KEYS */;
INSERT INTO `t_to_update` VALUES (1,1,0,'SKILL_ACTIVITY','INSERT','2012-10-31 16:47:14'),(2,1,0,'SKILL_OPERATOR','INSERT','2012-10-31 16:47:16'),(3,0,0,'GAME','INSERT',NULL),(4,1,0,'GAME','INSERT',NULL),(5,2,0,'GAME','INSERT',NULL),(6,3,0,'GAME','INSERT',NULL),(7,0,0,'ACTIVITY','INSERT',NULL),(8,1,0,'ACTIVITY','INSERT',NULL),(9,2,0,'ACTIVITY','INSERT',NULL),(10,3,0,'ACTIVITY','INSERT',NULL),(11,0,0,'ASSEMBLY_DETAILS','INSERT',NULL),(12,1,0,'ASSEMBLY_DETAILS','INSERT',NULL),(13,2,0,'ASSEMBLY_DETAILS','INSERT',NULL),(14,3,0,'ASSEMBLY_DETAILS','INSERT',NULL),(15,0,0,'BUCKET','INSERT',NULL),(16,1,0,'BUCKET','INSERT',NULL),(17,2,0,'BUCKET','INSERT',NULL),(18,3,0,'BUCKET','INSERT',NULL),(19,0,0,'CATALOG','INSERT',NULL),(20,1,0,'CATALOG','INSERT',NULL),(21,2,0,'CATALOG','INSERT',NULL),(22,3,0,'CATALOG','INSERT',NULL),(23,0,0,'MACHINE','INSERT',NULL),(24,1,0,'MACHINE','INSERT',NULL),(25,2,0,'MACHINE','INSERT',NULL),(26,3,0,'MACHINE','INSERT',NULL),(27,0,0,'OPERATION','INSERT',NULL),(28,1,0,'OPERATION','INSERT',NULL),(29,2,0,'OPERATION','INSERT',NULL),(30,3,0,'OPERATION','INSERT',NULL),(31,0,0,'OPERATOR','INSERT',NULL),(32,1,0,'OPERATOR','INSERT',NULL),(33,2,0,'OPERATOR','INSERT',NULL),(34,3,0,'OPERATOR','INSERT',NULL),(35,0,0,'ORDER','INSERT',NULL),(36,1,0,'ORDER','INSERT',NULL),(37,2,0,'ORDER','INSERT',NULL),(38,3,0,'ORDER','INSERT',NULL),(39,0,0,'PART','INSERT',NULL),(40,1,0,'PART','INSERT',NULL),(41,2,0,'PART','INSERT',NULL),(42,3,0,'PART','INSERT',NULL),(43,0,0,'PURCHASE','INSERT',NULL),(44,1,0,'PURCHASE','INSERT',NULL),(45,2,0,'PURCHASE','INSERT',NULL),(46,3,0,'PURCHASE','INSERT',NULL),(47,0,0,'SHIP','INSERT',NULL),(48,1,0,'SHIP','INSERT',NULL),(49,2,0,'SHIP','INSERT',NULL),(50,3,0,'SHIP','INSERT',NULL),(51,0,0,'SKILL','INSERT',NULL),(52,1,0,'SKILL','INSERT',NULL),(53,2,0,'SKILL','INSERT',NULL),(54,3,0,'SKILL','INSERT',NULL),(55,0,0,'SKILL_ACTIVITY','INSERT',NULL),(56,1,0,'SKILL_ACTIVITY','INSERT',NULL),(57,2,0,'SKILL_ACTIVITY','INSERT',NULL),(58,3,0,'SKILL_ACTIVITY','INSERT',NULL),(59,0,0,'SKILL_OPERATOR','INSERT',NULL),(60,1,0,'SKILL_OPERATOR','INSERT',NULL),(61,2,0,'SKILL_OPERATOR','INSERT',NULL),(62,3,0,'SKILL_OPERATOR','INSERT',NULL),(63,0,0,'STATION','INSERT',NULL),(64,1,0,'STATION','INSERT',NULL),(65,2,0,'STATION','INSERT',NULL),(66,3,0,'STATION','INSERT',NULL),(67,0,0,'SUPPLIER','INSERT',NULL),(68,1,0,'SUPPLIER','INSERT',NULL),(69,2,0,'SUPPLIER','INSERT',NULL),(70,3,0,'SUPPLIER','INSERT',NULL),(71,0,0,'TERRAIN','INSERT',NULL),(72,1,0,'TERRAIN','INSERT',NULL),(73,2,0,'TERRAIN','INSERT',NULL),(74,3,0,'TERRAIN','INSERT',NULL),(75,0,0,'TERRAIN_RESERVED','INSERT',NULL),(76,1,0,'TERRAIN_RESERVED','INSERT',NULL),(77,2,0,'TERRAIN_RESERVED','INSERT',NULL),(78,3,0,'TERRAIN_RESERVED','INSERT',NULL),(79,0,0,'TRANSPORT_STORE','INSERT',NULL),(80,1,0,'TRANSPORT_STORE','INSERT',NULL),(81,2,0,'TRANSPORT_STORE','INSERT',NULL),(82,3,0,'TRANSPORT_STORE','INSERT',NULL);
/*!40000 ALTER TABLE `t_to_update` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_transport_store`
--

DROP TABLE IF EXISTS `t_transport_store`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_transport_store` (
  `id_transport` int(11) NOT NULL,
  `id_game` int(11) NOT NULL,
  `unit_load` int(11) DEFAULT NULL,
  `id_station_ini` int(11) DEFAULT NULL,
  `id_station_end` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_transport`,`id_game`),
  KEY `transport_station_ini` (`id_station_ini`),
  KEY `transport_station_end` (`id_station_end`),
  KEY `transport_activity` (`id_transport`),
  KEY `transport_game` (`id_game`),
  CONSTRAINT `transport_activity` FOREIGN KEY (`id_transport`) REFERENCES `t_activity` (`id_activity`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `transport_game` FOREIGN KEY (`id_game`) REFERENCES `t_game` (`id_game`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `transport_station_end` FOREIGN KEY (`id_station_end`) REFERENCES `t_station` (`id_station`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `transport_station_ini` FOREIGN KEY (`id_station_ini`) REFERENCES `t_station` (`id_station`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_transport_store`
--

LOCK TABLES `t_transport_store` WRITE;
/*!40000 ALTER TABLE `t_transport_store` DISABLE KEYS */;
INSERT INTO `t_transport_store` VALUES (2,1,10,3,4),(3,1,10,4,5),(3,2,10,3,4),(4,0,10,2,7),(4,2,10,3,4),(4,3,10,3,4),(5,0,10,2,7),(5,1,5,5,6),(5,2,10,4,5),(5,3,10,3,4),(6,0,20,1,3),(6,1,12,6,7),(6,2,10,4,5),(6,3,10,3,4),(7,0,30,3,4),(7,3,7,4,5),(8,2,10,5,6),(8,3,7,4,5),(9,0,10,7,1),(9,2,10,6,7),(10,0,10,7,1),(10,3,10,5,6),(11,2,7,7,8),(11,3,5,6,7),(12,2,7,8,9),(12,3,10,4,7),(14,3,7,7,8),(15,3,7,8,9);
/*!40000 ALTER TABLE `t_transport_store` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'gaming'
--
/*!50003 DROP FUNCTION IF EXISTS `Game_GetBestScore` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 FUNCTION `Game_GetBestScore`(
    intIdGame     int
) RETURNS decimal(10,2)
BEGIN
    declare returnValue decimal(10,2); 
    select max(current_money) into returnValue
    from t_game where id_game = intIdGame;
    return ifnull(returnValue,0);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `Activity_Insert` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `Activity_Insert`(
    intIdGame           int,
    intIdActivity       int,
    intIdPart           int,
    vcActivityDesc      varchar(50),
    vcTypeActivity      varchar(10),
    intIdOrderActivity   int,
    intIdNextActivity   int,
    intProcessingTime   int,
    vcActivityState     varchar(100),
    vcActivityStatus    varchar(20),
    decCostPerExecution decimal(10,2),
    vcMachineCategory   varchar(45),
    intPriorityQueue    int
)
BEGIN
    insert into t_activity
        (id_activity, id_game, id_part, activity_description, 
        type_activity, id_order_activity, id_next_activity, 
        processing_time, activity_state, activity_status,
        cost_per_execution, machine_category, priority_queue)
    values
        (intIdActivity, intIdGame, intIdPart, vcActivityDesc, 
        vcTypeActivity, intIdOrderActivity, intIdNextActivity, 
        intProcessingTime, vcActivityState, vcActivityStatus,
        decCostPerExecution, vcMachineCategory, intPriorityQueue);

END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `Activity_Select` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `Activity_Select`(
    intIdGame           int
)
BEGIN
    select
        id_activity, id_part, activity_description, type_activity,
        id_order_activity, id_next_activity, processing_time, 
        activity_state, activity_status, cost_per_execution,
        machine_category, priority_queue
    from
        t_activity
    where
        id_game = intIdGame;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `AssemblyDetails_Insert` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `AssemblyDetails_Insert`(
    intIdGame       int,
    intIdOutputPart int,
    intIdInputPart  int,
    intQuantity     int,
    vcAssemblyDesc  varchar(50)
)
BEGIN

    insert into t_assembly_details(id_game, id_output_part,
    id_input_part, quantity, assembly_description)
    values(intIdGame, intIdOutputPart, intIdInputPart,
    intQuantity, vcAssemblyDesc);    

END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `AssemblyDetails_Select` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `AssemblyDetails_Select`(
    intIdGame       int,
    intIdOutputPart int
)
BEGIN

    select
        id_output_part, id_input_part, 
        quantity, assembly_description
    from
        t_assembly_details
    where
        id_game = intIdGame and
        id_output_part = intIdOutputPart;       

END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `AssemblyDetails_SelectByGame` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `AssemblyDetails_SelectByGame`(
    intIdGame       int
)
BEGIN

    select
        id_output_part, id_input_part, 
        quantity, assembly_description
    from
        t_assembly_details
    where
        id_game = intIdGame;

END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `Bucket_Insert` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `Bucket_Insert`(
    intIdBucket     int,
    intIdStation    int,
    intIdGame       int,
    intCapacity     int,
    vcUnit          varchar(10),
    intSize         int,
    intIdPart       int,
    vcDirection     varchar(10),
    intUnitsToArrive    int,
    intUnitsToRemove    int,
    vcState         varchar(45)
)
BEGIN

    insert into t_bucket(
    id_bucket, id_station, id_game, capacity, unit, 
    size, id_part, direction, units_to_arrive, units_to_remove,
    state)
    values(
    intIdBucket, intIdStation, intIdGame, intCapacity, vcUnit, 
    intSize, intIdPart, vcDirection, intUnitsToArrive, intUnitsToRemove,
    vcState);

END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `Bucket_Select` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `Bucket_Select`(
    intIdGame       int,
    intIdStation    int
)
BEGIN

    select
        id_bucket, capacity, unit, size, id_part, 
        direction, units_to_arrive, units_to_remove, state
    from
        t_bucket
    where
        id_game = intIdGame and
        id_station = intIdStation;

END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `Bucket_SelectByGame` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `Bucket_SelectByGame`(
    intIdGame       int
)
BEGIN

    select
        id_bucket, id_station, capacity, unit, size, id_part, 
        direction, units_to_arrive, units_to_remove, state
    from
        t_bucket
    where
        id_game = intIdGame;

END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `Catalog_Insert` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `Catalog_Insert`(
    intIdGame               int,
    intIdSupplier           int,
    intIdPart               int,
    decQuality              decimal(10,2),
    vcProdDistn             varchar(10),
    decProdParam1           decimal(10,2),
    decProdParam2           decimal(10,2),
    intPriceFunc1_Limit     int,
    decPriceFunc1_Charge    decimal(10,2),
    intPriceFunc2_Limit     int,
    decPriceFunc2_Charge    decimal(10,2),
    intPriceFunc3_Limit     int,
    decPriceFunc3_Charge    decimal(10,2)
)
BEGIN
    insert into t_catalog
        (id_game, id_supplier, id_part, quality, production_distn,
        production_parameter1, production_parameter2, price_function1_limit,
        price_function1_charge, price_function2_limit, price_function2_charge,
        price_function3_limit, price_function3_charge)
    values
        (intIdGame, intIdSupplier, intIdPart, decQuality, vcProdDistn,
        decProdParam1, decProdParam2, intPriceFunc1_Limit, decPriceFunc1_Charge,
        intPriceFunc2_Limit, decPriceFunc2_Charge, intPriceFunc3_Limit, 
        decPriceFunc3_Charge);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `Catalog_Select` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `Catalog_Select`(
    intIdGame       int,
    intIdSupplier   int
)
BEGIN
    select
        id_supplier, id_part, quality, production_distn,
        production_parameter1, production_parameter2, price_function1_limit,
        price_function1_charge, price_function2_limit, price_function2_charge,
        price_function3_limit, price_function3_charge
    from
        t_catalog
    where
        id_game = intIdGame and
        id_supplier = intIdSupplier;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `Catalog_SelectByGame` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `Catalog_SelectByGame`(
    intIdGame       int
)
BEGIN
    select
        id_supplier, id_part, quality, production_distn,
        production_parameter1, production_parameter2, price_function1_limit,
        price_function1_charge, price_function2_limit, price_function2_charge,
        price_function3_limit, price_function3_charge
    from
        t_catalog
    where
        id_game = intIdGame;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `Event_Insert` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `Event_Insert`(
    intIdEvent      int,
    intIdGame       int,
    dblTimeFactorForSpeed  double,
    dblTimeMissing  double,
    intIdOperator   int
)
BEGIN
    insert into t_event
        (id_event, id_game, time_factor_for_speed, time_missing, 
        id_operator)
    values
        (intIdEvent, intIdGame, dblTimeFactorForSpeed, dblTimeMissing, 
        intIdOperator);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `Event_Select` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `Event_Select`(
    intIdGame       int
)
BEGIN
    select
        id_event, id_game, time_factor_for_speed, 
        time_missing, id_operator
    from
        t_event
    where
        id_game = intIdGame;
        
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `Game_AddNewAttempt` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `Game_AddNewAttempt`(
    intIdGame     int,
    intIdPlayer   int,
    intDateTime   int
)
BEGIN
    update
        t_game
    set
        attempt_numbers = attempt_numbers + 1,
        date_time = intDateTime
    where
        id_game = intIdGame and id_player = intIdPlayer;
        
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `Game_Insert` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `Game_Insert`(
    intIdGame       int,
    intDateTime     int,
    intIdPlayer     int,
    decCurrentMoney  decimal(10,2),
    intCurrentMinute    int,
    intCurrentHour      int,
    intCurrentDay       int,
    intCurrentMonth     int,
    dblCurrentTime      double,
    dblTimeFactor       double,
    vcGameType      varchar(45),
    vcGameCategory  varchar(45),
    vcDescription   varchar(300),
    decInitialMoney     double,
    decPercentageToWin  decimal(10,2),
    vcName              varchar(100),
    vcGameStatus        varchar(45),
    intIdPhase          int,
    intNextIdPhase      int,
    intIdTerrain        int,
    vcObjective         varchar(100),
    vcFlowImage         varchar(100),
    intAttemptNumbers   int,
    decOverhead         decimal(10,2)
)
BEGIN

/*declare newIdGame int;
select ifnull(max(id_game)+1,1) into newIdGame from t_game;
*/
INSERT INTO t_game(id_game, date_time, id_player, current_money,
    current_minute, current_hour, current_day, current_month,
    current_game_time, time_factor, game_type, game_category,
    description, initial_money, percentage_to_win, name, game_status,
    id_phase, next_id_phase, id_terrain, objective, flow_image,
    attempt_numbers, overhead)
VALUES (intIdGame, intDateTime, intIdPlayer, decCurrentMoney,
    intCurrentMinute, intCurrentHour, intCurrentDay, intCurrentMonth,
    dblCurrentTime, dblTimeFactor, vcGameType, vcGameCategory,
    vcDescription, decInitialMoney, decPercentageToWin, vcName,
    vcGameStatus, intIdPhase, intNextIdPhase, intIdTerrain, vcObjective,
    vcFlowImage, intAttemptNumbers, decOverhead);

select max(id_game) from t_game;

END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `Game_Select` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `Game_Select`(
    intIdPlayer     int
)
BEGIN

    select
        id_game, date_time, -- date_format(date_time,'%m/%d/%Y %r') as date_time, 
        id_player, current_money, current_minute, current_hour, 
        current_day, current_month, current_game_time, time_factor,
        game_type, game_category, description, initial_money,
        percentage_to_win, name, game_status, id_phase, next_id_phase,
        id_terrain, objective, flow_image, attempt_numbers,
        current_money, (select current_money from t_game 
        where id_game = a.id_game and game_type = 'CPU') as gameBestScore,
        -- game_getBestScore(id_game), 
        overhead
    from 
        t_game as a
    where
        id_player = intIdPlayer
    order by
        id_phase;
        
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `Game_SelectAGame` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `Game_SelectAGame`(
    intIdGame     int,
    intIdPlayer   int
)
BEGIN
    update
        t_game
    set
        attempt_numbers = attempt_numbers + 1
    where
        id_game = intIdGame and id_player = intIdPlayer;

    select
        id_game, date_time, -- date_format(date_time,'%m/%d/%Y %r') as date_time, 
        id_player, current_money, current_minute, current_hour, 
        current_day, current_month, current_game_time, time_factor,
        game_type, game_category, description, initial_money,
        percentage_to_win, name, game_status, id_phase, next_id_phase,
        id_terrain, objective, flow_image, attempt_numbers, overhead
    from 
        t_game
    where
        id_game = intIdGame and id_player = intIdPlayer
    order by
        date_time desc;
        
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `Game_SelectByCPUGame` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `Game_SelectByCPUGame`(
    intIdGame     int
)
BEGIN

    select
        id_game, date_time, id_player, current_money, current_minute, current_hour, 
        current_day, current_month, current_game_time, time_factor,
        game_type, game_category, description, initial_money,
        percentage_to_win, name, game_status, id_phase, next_id_phase,
        id_terrain, objective, flow_image, attempt_numbers, overhead
    from 
        t_game
    where
        id_game = intIdGame and game_type = 'CPU';
        
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `Game_SelectByType` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `Game_SelectByType`(
    vcGameType  varchar(45)
)
BEGIN

    select
        id_game, date_time, -- date_format(date_time,'%m/%d/%Y %r') as date_time, 
        id_player, current_money, current_minute, current_hour, 
        current_day, current_month, current_game_time, time_factor,
        game_type, game_category, description, initial_money,
        percentage_to_win, name, game_status, id_phase, next_id_phase,
        id_terrain, objective, flow_image, attempt_numbers, overhead
    from 
        t_game
    where
        game_type = vcGameType
    order by
        game_category, id_game;
        
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `Game_Update` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `Game_Update`(
    intIdGame       int,
    intDateTime     int,
    intIdPlayer     int,
    decCurrentMoney  decimal(10,2),
    intCurrentMinute    int,
    intCurrentHour      int,
    intCurrentDay       int,
    intCurrentMonth     int,
    dblCurrentTime      double,
    dblTimeFactor       double,
    vcGameType      varchar(45),
    vcGameCategory  varchar(45),
    vcDescription   varchar(300),
    decInitialMoney     double,
    decPercentageToWin  decimal(5,2),
    vcName              varchar(100),
    vcGameStatus        varchar(45),
    intIdPhase      int,
    intNextIdPhase  int,
    intIdTerrain   int,
    vcObjective     varchar(100),
    vcFlowImage     varchar(100),
    intAttemptNumbers int,
    decOverhead     decimal(10,2)
)
BEGIN

UPDATE t_game
SET date_time = intDateTime,
    current_money = if (current_money < decCurrentMoney, decCurrentMoney, current_money) ,
    current_minute = intCurrentMinute, 
    current_hour = intCurrentHour, 
    current_day = intCurrentDay,
    current_month = intCurrentMonth,
    current_game_time = dblCurrentTime, 
    time_factor = dblTimeFactor,
    game_type = vcGameType,
    game_category = vcGameCategory,
    description = vcDescription, 
    initial_money = decInitialMoney,
    percentage_to_win = decPercentageToWin,
    name = vcName, 
    game_status = vcGameStatus,
    id_phase = intIdPhase, 
    next_id_phase = intNextIdPhase,
    id_terrain = intIdTerrain,
    objective = vcObjective,
    flow_image = vcFlowImage,
    attempt_numbers = intAttemptNumbers,
    overhead = decOverhead
WHERE id_game = intIdGame and id_player = intIdPlayer;
/*
IF vcGameStatus = 'Completed' THEN
    UPDATE t_game
    SET game_status = 'Unlocked'
    WHERE 
    id_player = intIdPlayer AND id_phase = intNextIdPhase;
END IF;
*/
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `Game_UpdateFailedGame` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `Game_UpdateFailedGame`(
    intIdGame       int,
    intIdPlayer     int,
    intDateTime     int
)
BEGIN

UPDATE t_game
SET date_time = intDateTime
WHERE id_game = intIdGame and id_player = intIdPlayer;

END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `Game_UpdateWonGame` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `Game_UpdateWonGame`(
    intIdPlayer     int,
    intNextIdPhase  int,
    intDateTime     int
)
BEGIN

    UPDATE 
        t_game
    SET 
        game_status = 'Unlocked',
        date_time = intDateTime
    WHERE 
        id_player = intIdPlayer AND 
        id_phase = intNextIdPhase;

END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `Log_Insert` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `Log_Insert`(
    vcMessage varchar(200)
)
BEGIN
    insert into t_log(date_time, message)
    values (now(), vcMessage);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `Machine_Insert` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `Machine_Insert`(
    intIdMachine            int,
    intIdGame               int,
    vcMachineDesc           varchar(50),
    decSpeed                decimal(10,2),
    decWeightCapacity       decimal(10,2),
    decVolumeCapacity       decimal(10,2),
    vcPickUpTimeDistn       varchar(10),
    decPickUpTimeParam1     decimal(10,2),
    decPickUpTimeParam2     decimal(10,2),
    vcMachineTimeDistn      varchar(10),
    decMachineTimeParam1    decimal(10,2),
    decMachineTimeParam2    decimal(10,2),
    vcPlacementTimeDistn    varchar(10),
    decPlacementTimeParam1  decimal(10,2),
    decPlacementTimeParam2  decimal(10,2),
    vcTimeBetFailsDistn     varchar(10),
    decTimeBetFailsParam1   decimal(10,2),
    decTimeBetFailsParam2   decimal(10,2),
    vcRepairTimeDistn       varchar(10),
    decRepairTimeParam1     decimal(10,2),
    decRepairTimeParam2     decimal(10,2),
    decPriceForPurchase     decimal(10,2),
    vcStatus                varchar(10),
    vcOwner                 varchar(10),
    vcMachineDesign         varchar(100),
    vcVirtualIdLocation     varchar(45),
    intVirtualMatrixIniI    int,
    intVirtualMatrixIniJ    int,
    intVirtualMatrixEndI    int,
    intVirtualMatrixEndJ    int,
    intCurrentLocationX     int,
    intCurrentLocationZ     int,
    intSizeW                int,
    intSizeL                int,
    decCostPerHour          decimal(10,2),
    vcMachineCategory       varchar(45),
    vcState                 varchar(45),
    vcMachineMaterial       varchar(100),
    decPercentageDepreciation         decimal(10,2)
)
BEGIN
    insert into t_machine
        (id_machine, id_game, machine_description, speed, weight_capacity, volume_capacity,
        pick_up_time_distn, pick_up_time_parameter1, pick_up_time_parameter2,
        machine_time_distn, machine_time_parameter1, machine_time_parameter2,
        placement_time_distn, placement_time_parameter1, placement_time_parameter2,
        time_between_failures_distn, time_between_failures_parameter1,
        time_between_failures_parameter2, repair_time_distn,
        repair_time_parameter1, repair_time_parameter2, price_for_purchase, status, owner,
        machine_design, virtual_id_location, virtual_matrix_ini_i,
        virtual_matrix_ini_j, virtual_matrix_end_i, virtual_matrix_end_j,
        current_location_x, current_location_z, size_w, size_l, cost_per_hour, 
        machine_category, state, machine_material, percentage_depreciation)
    values
        (intIdMachine, intIdGame, vcMachineDesc, decSpeed, decWeightCapacity, decVolumeCapacity,
        vcPickUpTimeDistn, decPickUpTimeParam1, decPickUpTimeParam2, vcMachineTimeDistn,
        decMachineTimeParam1, decMachineTimeParam2, vcPlacementTimeDistn,
        decPlacementTimeParam1, decPlacementTimeParam2, vcTimeBetFailsDistn,
        decTimeBetFailsParam1, decTimeBetFailsParam2, vcRepairTimeDistn,
        decRepairTimeParam1, decRepairTimeParam2, decPriceForPurchase, vcStatus, vcOwner,
        vcMachineDesign, vcVirtualIdLocation, intVirtualMatrixIniI,
        intVirtualMatrixIniJ, intVirtualMatrixEndI, intVirtualMatrixEndJ,
        intCurrentLocationX, intCurrentLocationZ, intSizeW, intSizeL, decCostPerHour, 
        vcMachineCategory, vcState, vcMachineMaterial, decPercentageDepreciation);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `Machine_Select` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `Machine_Select`(
    intIdGame               int
)
BEGIN
    select
        id_machine, machine_description, speed, weight_capacity,
        volume_capacity, pick_up_time_distn, pick_up_time_parameter1,
        pick_up_time_parameter2, machine_time_distn, machine_time_parameter1,
        machine_time_parameter2, placement_time_distn, placement_time_parameter1,
        placement_time_parameter2, time_between_failures_distn, 
        time_between_failures_parameter1, time_between_failures_parameter2, 
        repair_time_distn, repair_time_parameter1, repair_time_parameter2,
        price_for_purchase, status, owner, machine_design, virtual_id_location,
        virtual_matrix_ini_i, virtual_matrix_ini_j, virtual_matrix_end_i,
        virtual_matrix_end_j, current_location_x, current_location_z,
        size_w, size_l, cost_per_hour, machine_category, state, machine_material,
        percentage_depreciation, price_preventive_maintenance,
        factor_param1, factor_param2
    from
        t_machine
    where
        id_game = intIdGame;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `Operation_Insert` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `Operation_Insert`(
    intIdOperation      int,
    intIdGame           int,
    vcOperationDesc     varchar(50),
    intProductionPolicy int,
    intIdStation        int,
    intQuantityOutput   int
)
BEGIN
    insert into t_operation
        (id_operation, id_game, operation_description, production_policy,
        id_station, quantity_output)
    values
        (intIdOperation, intIdGame, vcOperationDesc, intProductionPolicy,
        intIdStation, intQuantityOutput);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `Operation_Select` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `Operation_Select`(
    intIdGame       int
)
BEGIN
    select
        o.id_operation, o.operation_description,
        o.production_policy,
        o.id_station, o.quantity_output, a.id_part, a.activity_description,
        a.type_activity, a.id_order_activity, a.id_next_activity, 
        a.processing_time, a.activity_state, a.activity_status,
        a.cost_per_execution, a.machine_category, a.priority_queue
    from
        t_operation o, t_activity a
    where
        o.id_operation = a.id_activity and
        o.id_game = a.id_game and
        a.id_game = intIdGame;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `Operator_Insert` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `Operator_Insert`(
    intIdOperator   int,
    intIdGame       int,
    vcNameOperator  varchar(50),
    decSalaryPerHour    decimal(10,2),
    vcStatus        varchar(10),
    vcOwner         varchar(10),
    vcVirtualIdLocation varchar(45),
    intVirtualMatrixI   int,
    intVirtualMatrixJ   int,
    intCurrentLocationX int,
    intCurrentLocationZ int,
    decSpeed        decimal(10,2),
    intIsMoving     int,
    intIdMachineAttached    int,
    intEndLocationX int,
    intEndLocationZ int,
    vcActivityDoing varchar(45),
    intIdActivityAssigned   int,
    vcState         varchar(45),
    decPriceForHire decimal(10,2),
    decPriceForFire decimal(10,2),
    decSalaryPerHourCarrier     decimal(10,2),
    decSalaryPerHourAssembler   decimal(10,2),
    decSalaryPerHourVersatile   decimal(10,2)
)
BEGIN
    insert into t_operator
        (id_operator, id_game, name_operator, salary_per_hour, status, owner,
        virtual_id_location, virtual_matrix_i, virtual_matrix_j,
        current_location_x, current_location_z, speed,
        is_moving, id_machine_attached, end_location_x,
        end_location_z, activity_doing, id_activity_assigned, state,
        price_for_hire, price_for_fire, salary_per_hour_carrier,
        salary_per_hour_assembler, salary_per_hour_versatile)
    values
        (intIdOperator, intIdGame, vcNameOperator,
        decSalaryPerHour, vcStatus, vcOwner, vcVirtualIdlocation,
        intVirtualMatrixI, intVirtualMatrixJ,
        intCurrentLocationX, intCurrentLocationZ, decSpeed,
        intIsMoving, intIdMachineAttached, intEndLocationX,
        intEndLocationZ, vcActivityDoing, intIdActivityAssigned, vcState,
        decPriceForHire, decPriceForFire, decSalaryPerHourCarrier,
        decSalaryPerHourAssembler, decSalaryPerHourVersatile);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `Operator_Select` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `Operator_Select`(
    intIdGame       int
)
BEGIN
    select
        id_operator, name_operator, salary_per_hour, status, owner,
        virtual_id_location, virtual_matrix_i, virtual_matrix_j,
        current_location_x, current_location_z, speed, is_moving,
        id_machine_attached, end_location_x, end_location_z,
        activity_doing, id_activity_assigned, state,
        price_for_hire, price_for_fire, salary_per_hour_carrier,
        salary_per_hour_assembler, salary_per_hour_versatile,
        uniformParam1, uniformParam2, normalParamLoad, normalParamUnload
    from
        t_operator
    where
        id_game = intIdGame;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `Order_Insert` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `Order_Insert`(
    intIdOrder      int,
    intIdGame       int,
    intIdPart       int,
    intQuantity     int,
    intTimeUnits    int,
    intTimeToAppear int,
    decPercVarMin   decimal(10,2),
    decPercVarMax   decimal(10,2)
)
BEGIN
    insert into t_order(id_order, id_game, id_part, quantity,
    time_units, time_to_appear, percentage_variability_min,
    percentage_variability_max)
    values (intIdOrder, intIdGame, intIdPart, intQuantity,
    intTimeUnits, intTimeToAppear, decPercVarMin, decPercVarMax);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `Order_Select` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `Order_Select`(
    intIdGame     int
)
BEGIN

    select 
        id_order, id_part, quantity, time_units,
        time_to_appear, percentage_variability_min,
        percentage_variability_max
    from
        t_order
    where
        id_game = intIdGame;

END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `Part_Insert` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `Part_Insert`(
    intIdPart           int,
    intIdGame           int,
    vcPartDescription   varchar(50),
    vcUnit              varchar(10),
    decVolume           decimal(10,2),
    decWeight           decimal(10,2),
    intQuantityPalette  int,
    intCurrentStock     int,
    intPriceForSale     int,
    vcOwner             varchar(10),
    partDesign          varchar(100),
    partDesignScale     decimal(10,2),
    partDesignColor     varchar(45),
    decFactor           decimal(10,2),
    intOutputQuantity   int
)
BEGIN
    INSERT INTO t_part(id_part, id_game, part_description, unit, volume,
    weight, quantity_palette, current_stock, price_for_sale,
    owner, part_design, part_design_scale, part_design_color, factor,
    output_quantity) 
    VALUES (intIdPart, intIdGame, vcPartDescription, vcUnit, decVolume,
    decWeight, intQuantityPalette, intCurrentStock, intPriceForSale,
    vcOwner, partDesign, partDesignScale, partDesignColor, decFactor,
    intOutputQuantity); 
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `Part_Select` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `Part_Select`(
    intIdGame     int
)
BEGIN

    select
        id_part, part_description, unit, volume, weight, 
        quantity_palette, current_stock, price_for_sale,
        owner, part_design, part_design_scale, part_design_color, 
        factor, output_quantity
    from 
        t_part
    where
        id_game = intIdGame;

END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `PlayerLog_Insert` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `PlayerLog_Insert`(
    intIdPlayer int,
    vcLoginTime int,
    vcLogoutTime    int,
    intMinutesTime  int,
    vcGameLevel varchar(45)
)
BEGIN
declare newIdPlayerLog int;
select ifnull(max(id_player_log)+1,1) into newIdPlayerLog from t_player_log
where id_player = intIdPlayer;

INSERT INTO t_player_log(id_player_log, id_player, login_time, logout_time, minutes_time, game_level) 
VALUES (newIdPlayerLog, intIdPlayer, vcLoginTime, vcLogoutTime, intMinutesTime, vcGameLevel); 

select newIdPlayerLog;

END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `PlayerLog_Update` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `PlayerLog_Update`(
    intIdPlayer int,
    intIdPlayerLog int,
    intLogOutTime   int
)
BEGIN
/*
declare oldIdPlayerLog int;

select ifnull(max(id_player_log),1) into oldIdPlayerLog from t_player_log
where id_player = intIdPlayer;
*/
update 
    t_player_log
set
    logout_time = intLogOutTime,
    minutes_time = cast((intLogOutTime - login_time)/60 as SIGNED)
where 
    id_player = intIdPlayer and 
    id_player_log = intIdPlayerLog;
    
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `Player_Insert` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `Player_Insert`(
    vcName varchar(100), 
    vcNickName varchar(50), 
    vcEmail varchar(100),
    vcPassword varchar(20),
    intGameTime int,
    intLastLoginTime int
)
BEGIN
declare newIdPlayer int;
select ifnull(max(id_player)+1,1) into newIdPlayer from t_player;

INSERT INTO t_player(id_player, name, nick_name, email, password, login_time, game_time, last_login_time) 
VALUES (newIdPlayer, vcName, vcNickName, vcEmail, vcPassword, now(), intGameTime, intLastLoginTime); 

select newIdPlayer;

END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `Player_LogOut` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `Player_LogOut`(
    intIdPlayer int,
    intLastLoginTime int
)
BEGIN
    update 
        t_player
    set
        game_time = game_time + cast((intLastLoginTime - last_login_time)/60 as SIGNED)
    where 
        id_player = intIdPlayer;
        
    update 
        t_player
    set
        last_login_time = intLastLoginTime
    where 
        id_player = intIdPlayer;
/*
    set @currentTime = null;
    
    select @currentTime := game_time
    from t_player
    where id_player = intIdPlayer;
    
    if isnull(@currentTime) then
        update t_player
        set game_time = timestampdiff(MINUTE,login_time,now())
        where id_player = intIdPlayer;
    else
        update t_player
        set game_time = game_time + timestampdiff(MINUTE,login_time,now())
        where id_player = intIdPlayer;
    End if;
*/
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `Player_Select` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `Player_Select`(
    vcEmail     varchar(100),
    vcPassword  varchar(20)
)
BEGIN

    update
        t_player
    set
        login_time = now()
    where 
        upper(email) = upper(vcEmail) 
        and password = vcPassword;
    
    select
        id_player, name, nick_name, email, 
        password, game_time, last_login_time
    from 
        t_player
    where 
        upper(email) = upper(vcEmail) 
        and password = vcPassword;
        
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `Player_SelectByEmail` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `Player_SelectByEmail`(
    vcEmail     varchar(100)
)
BEGIN

    select
        id_player, name, nick_name, email, 
        password, game_time, last_login_time
    from 
        t_player
    where 
        upper(email) = upper(vcEmail);

END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `Player_Update` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `Player_Update`(
    intIdPlayer     int,
    vcName          varchar(100),
    vcNickName      varchar(50),
    vcEmail         varchar(100),
    vcPassword      varchar(20),
    intGameTime     int,
    intLastLoginTime int
)
BEGIN
    update  
        t_player
    set
        name = vcName,
        nick_name = vcNickName,
        email = vcEmail,
        password = vcPassword,
        game_time = intGameTime,
        last_login_time = intLastLoginTime
    where
        id_player = intIdPlayer;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `Player_ValidateEmail` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `Player_ValidateEmail`(
    vcEmail     varchar(100)
)
BEGIN

    select
        count(*)
    from 
        t_player
    where 
        upper(email) = upper(vcEmail);

END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `Purchase_Insert` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `Purchase_Insert`(
    intIdPurchase   int,
    intIdGame       int,
    intIdStation    int,
    intIdSupplier   int,
    intOrderPoint   int,
    intOrderQuantity    int
)
BEGIN
    insert into t_purchase
        (id_purchase, id_game, id_station, id_supplier, order_point, order_quantity)
    values
        (intIdPurchase, intIdGame, intIdStation, intIdSupplier, intOrderPoint, intOrderQuantity);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `Purchase_Select` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `Purchase_Select`(
    intIdGame       int
)
BEGIN
    select
        p.id_purchase, p.id_station, a.id_part, a.activity_description,
        a.type_activity, a.id_order_activity, a.id_next_activity, 
        a.processing_time, p.id_supplier, p.order_point, p.order_quantity,
        a.activity_state, a.activity_status, a.cost_per_execution, a.priority_queue
    from
        t_purchase p, t_activity a
    where
        p.id_purchase = a.id_activity and
        p.id_game = a.id_game and
        a.id_game = intIdGame;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `Purchase_SelectByGame` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `Purchase_SelectByGame`(
    intIdGame       int
)
BEGIN
    select
        p.id_purchase, p.id_station, p.id_supplier, 
        p.order_point, p.order_quantity
    from
        t_purchase p
    where
        p.id_game = intIdGame;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `Ship_Insert` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `Ship_Insert`(
    intIdShip       int,
    intIdGame       int,
    intIdStation    int,
    intLoadQuantity  int,
    vcShippingTimeDistn     varchar(10),
    decShippingTimeParam1   decimal(5,2),
    decShippingTimeParam2   decimal(5,2)
)
BEGIN
    insert into t_ship
        (id_ship, id_game, id_station, load_quantity, shipping_time_distn,
        shipping_time_parameter1, shipping_time_parameter2)
    values
        (intIdShip, intIdGame, intIdStation, intLoadQuantity, 
        vcShippingTimeDistn, decShippingTimeParam1, decShippingTimeParam2);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `Ship_Select` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `Ship_Select`(
    intIdGame       int
)
BEGIN
    select
        s.id_ship, s.load_quantity, a.id_part, a.activity_description,
        a.type_activity, a.id_order_activity, a.id_next_activity, 
        a.processing_time, s.id_station, a.activity_state, 
        a.activity_status, a.cost_per_execution, a.priority_queue,
        s.shipping_time_distn, s.shipping_time_parameter1, 
        s.shipping_time_parameter2
    from
        t_ship s, t_activity a
    where
        s.id_ship = a.id_activity and
        s.id_game = a.id_game and
        a.id_game = intIdGame;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `Ship_SelectByGame` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `Ship_SelectByGame`(
    intIdGame       int
)
BEGIN
    select
        s.id_ship, s.id_station, s.load_quantity, 
        s.shipping_time_distn, s.shipping_time_parameter1, 
        s.shipping_time_parameter2
    from
        t_ship s
    where
        s.id_game = intIdGame;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `SkillPerOperator_Insert` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `SkillPerOperator_Insert`(
    intIdGame       int,
    intIdSkill      int,
    intIdOperator   int,
    decEfficiency   decimal(10,2)
)
BEGIN
    insert into t_skill_operator
        (id_game, id_skill, id_operator, efficiency)
    values
        (intIdGame, intIdSkill, intIdOperator, decEfficiency);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `SkillPerOperator_Select` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `SkillPerOperator_Select`(
    intIdGame       int,
    intIdOperator   int
)
BEGIN
    select
        id_skill, efficiency, dist_param1, dist_param2
    from
        t_skill_operator
    where
        id_game = intIdGame and
        id_operator = intIdOperator;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `SkillsActivity_SelectByGame` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `SkillsActivity_SelectByGame`(
    intIdGame           int
)
BEGIN
    Select 
        id_activity, id_skill
    from
        t_skill_activity
    where
        intIdGame = id_game;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `SkillsOperator_SelectByGame` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `SkillsOperator_SelectByGame`(
    intIdGame           int
)
BEGIN
    Select 
        id_skill, id_operator, efficiency, dist_param1, dist_param2
    from
        t_skill_operator
    where
        intIdGame = id_game;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `SkillsPerActivity_Insert` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `SkillsPerActivity_Insert`(
    intIdGame           int,
    intIdSkill          int,
    intIdActivity       int
)
BEGIN
    insert into t_skill_activity
        (id_activity, id_skill, id_game)
    values
        (intIdActivity, intIdSkill, intIdGame);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `SkillsPerActivity_Select` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `SkillsPerActivity_Select`(
    intIdGame           int,
    intIdActivity       int
)
BEGIN
    Select 
        id_skill
    from
        t_skill_activity
    where
        intIdGame = id_game and
        intIdActivity = id_activity;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `Skill_Insert` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `Skill_Insert`(
    intIdSkill  int,
    intIdGame   int,
    vcSkillDesc varchar(50)
)
BEGIN
    insert into t_skill
        (id_skill, id_game, skill_description)
    values
        (intIdSkill, intIdGame, vcSkillDesc);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `Skill_Select` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `Skill_Select`(
    intIdGame   int
)
BEGIN
    select
        id_skill, skill_description
    from
        t_skill
    where
        id_game = intIdGame;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `SP_Script_Insert` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `SP_Script_Insert`(
    vcName              varchar(100),
    intVariablesNumber  int,
    vcDefinition        varchar(45),
    intReturnValue      int,
    vcTableName         varchar(100),
    vcSPType            varchar(45)
)
BEGIN
declare newIdSPScript int;
select ifnull(max(id_sp_script)+1,1) into newIdSPScript 
from t_stored_procedures_script;

INSERT INTO t_stored_procedures_script
    (id_sp_script, sp_name, variables_number, definition, 
    return_value, table_name, sp_type) 
VALUES (newIdSPScript, vcName, intVariablesNumber, vcDefinition, 
    intReturnValue, vcTableName, vcSPType); 

END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `SP_Script_Select` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `SP_Script_Select`(
    intIdSPScript   int
)
BEGIN
    select
        id_sp_script, sp_name, variables_number, definition, 
        return_value, table_name, sp_type
    from
        t_stored_procedures_script
    where
        id_sp_script = intIdSPScript;
        
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `Station_Insert` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `Station_Insert`(
    intIdStation            int,
    intIdGame               int,
    vcStationDesc           varchar(50),
    intStationLocationX     int,
    intStationLocationY     int,
    decSizeW                decimal(10,2),
    decSizeL                decimal(10,2),
    decPriceForPurchase     decimal(10,2),
    intInputPaletteCapacity int,
    intOutputPaletteCapacity    int,
    vcStatus                varchar(10),
    vcOwner                 varchar(10),
    vcStationType           varchar(45),
    decCostPerHour     decimal(10,2),
    vcStationDesign         varchar(100),
    decPercentageSelectedSlots  decimal(10,2)
)
BEGIN
    insert into t_station
        (id_station, id_game, station_description, station_location_x, 
        station_location_y, size_w, size_l, price_for_purchase,
        input_palette_capacity, output_palette_capacity, status, owner,
        station_type, cost_per_hour, station_design,
        percentage_selected_slots)
    values
        (intIdStation, intIdGame, vcStationDesc, intStationLocationX, 
        intStationLocationY, decSizeW, decSizeL, decPriceForPurchase,
        intInputPaletteCapacity, intOutputPaletteCapacity,
        vcStatus, vcOwner, vcStationType, decCostPerHour, vcStationDesign,
        decPercentageSelectedSlots);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `Station_Select` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `Station_Select`(
    intIdGame               int
)
BEGIN
    select
        id_station, station_description, station_location_x,
        station_location_y, size_w, size_l, price_for_purchase,
        input_palette_capacity, output_palette_capacity, 
        status, owner, station_type, cost_per_hour, station_design,
        percentage_selected_slots
    from
        t_station
    where
        id_game = intIdGame;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `Supplier_Insert` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `Supplier_Insert`(
    intIdSupplier           int,
    intIdGame               int,
    vcSupplierDescription   varchar(50),
    intSupplierLocationX    int,
    intSupplierLocationY    int,
    decServiceLevel         decimal(10,2)
)
BEGIN
    insert into t_supplier
        (id_supplier, id_game, supplier_description, 
        supplier_location_x, supplier_location_y, service_level)
    values
        (intIdSupplier, intIdGame, vcSupplierDescription, 
        intSupplierLocationX, intSupplierLocationY, decServiceLevel);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `Supplier_Select` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `Supplier_Select`(
    intIdGame               int
)
BEGIN
    select
        id_supplier, supplier_description, supplier_location_x,
        supplier_location_y, service_level
    from
        t_supplier
    where
        id_game = intIdGame;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `Terrain_Reserved_Select` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `Terrain_Reserved_Select`(
    intIdTerrain       int
)
BEGIN
    select
        id_terrain_reserved, location_x, location_z, width, length
    from
        t_terrain_reserved
    where
        id_terrain = intIdTerrain;
        
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `Terrain_Select` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `Terrain_Select`(
    intIdTerrain       int
)
BEGIN
    select
        id_terrain, name, file_name, local_scale, model
    from
        t_terrain
    where
        id_terrain = intIdTerrain;
        
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `ToUpdate_Insert` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `ToUpdate_Insert`(
    intIdGame   int,
    intGameTableKey int,
    vcGameTable varchar(45),
    vcAction    varchar(45)
)
BEGIN
declare newIdToUpdate int;
select ifnull(max(id_to_update)+1,1) into newIdToUpdate 
from t_to_update;

INSERT INTO 
    t_to_update(id_to_update, id_game, game_table_key, 
    game_table, action_to_do, creation_date)
VALUES (newIdToUpdate, intIdGame, intGameTableKey, 
    vcGameTable, vcAction, now()); 

END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `ToUpdate_Select` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `ToUpdate_Select`(
--    intIdToUpdate   int
)
BEGIN
    select
        id_to_update, id_game, game_table_key, game_table, 
        action_to_do, creation_date
    from
        t_to_update
    order by 1;
--    where
--        id_to_update = intIdToUpdate
        
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `TransportStore_Insert` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `TransportStore_Insert`(
    intIdTransport  int,
    intIdGame       int,
    intUnitLoad     int,
    intIdStationIni int,
    intIdStationEnd int
)
BEGIN
    insert into t_transport_store
        (id_transport, id_game, unit_load, 
        id_station_ini, id_station_end)
    values
        (intIdTransport, intIdGame, intUnitLoad, 
        intIdStationIni, intIdStationEnd);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `TransportStore_Select` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `TransportStore_Select`(
    intIdGame       int
)
BEGIN
    select
        s.id_transport, s.unit_load, s.id_station_ini,
        s.id_station_end, a.id_part, a.activity_description,
        a.type_activity, a.id_order_activity, a.id_next_activity, 
        a.processing_time, a.activity_state, a.activity_status,
        a.cost_per_execution, a.machine_category, a.priority_queue
    from
        t_transport_store s, t_activity a
    where
        s.id_transport = a.id_activity and
        s.id_game = a.id_game and
        a.id_game = intIdGame;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2012-11-13  4:19:14
