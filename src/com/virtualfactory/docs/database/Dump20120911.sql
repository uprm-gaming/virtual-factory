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
INSERT INTO `t_activity` VALUES (1,0,1,'Purchase Part1','Purchase',1,4,8,'','',10.00,'',7),(1,1,1,'Purchase Part1','Purchase',1,2,0,'','',0.00,'',7),(1,2,1,'Purchase Part1','Purchase',1,3,0,'','',0.00,'',10),(1,3,1,'Purchase Part1','Purchase',1,4,0,'','',0.00,'',10),(2,0,2,'Purchase Part2','Purchase',2,5,8,'','',10.00,'',7),(2,1,1,'Part1 from Purchase to StorageRM','Transport',2,3,0,'','',0.00,'Transport',6),(2,2,2,'Purchase Part2','Purchase',2,4,0,'','',0.00,'',10),(2,3,2,'Purchase Part2','Purchase',2,5,0,'','',0.00,'',10),(3,0,3,'Assemble Part3','Operation',3,6,3,'','',10.00,'Operation',4),(3,1,1,'Part1 from StorageRM to Assemble','Transport',3,4,0,'','',0.00,'Transport',5),(3,2,1,'Part1 from Purchase to StorageRM','Transport',3,5,0,'','',0.00,'Transport',9),(3,3,3,'Purchase Part3','Purchase',3,6,0,'','',0.00,'',10),(4,0,1,'Part1 from Purchase to StorageRM','Transport',4,9,4,'','',10.00,'Transport',6),(4,1,2,'Assemble Part2','Operation',4,5,0,'','',0.00,'Operation',4),(4,2,2,'Part2 from Purchase to StorageRM','Transport',4,6,0,'','',0.00,'Transport',9),(4,3,1,'Part1 from Purchase to StorageRM','Transport',4,7,0,'','',0.00,'Transport',9),(5,0,2,'Part2 from Purchase to StorageRM','Transport',5,10,4,'','',10.00,'Transport',6),(5,1,2,'Part2 from Assemble to StorageFG','Transport',5,6,0,'','',0.00,'Transport',3),(5,2,1,'Part1 from StorageRM to Assemble','Transport',5,7,0,'','',0.00,'Transport',8),(5,3,2,'Part2 from Purchase to StorageRM','Transport',5,8,0,'','',0.00,'Transport',9),(6,0,3,'Part3 from Assembly to StorageFG','Transport',6,7,5,'','',10.00,'Transport',3),(6,1,2,'Part2 from StorageFG to Shipping','Transport',6,7,0,'','',0.00,'Transport',2),(6,2,2,'Part2 from StorageRM to Assemble','Transport',6,7,0,'','',0.00,'Transport',8),(6,3,3,'Part3 from Purchase to StorageRM','Transport',6,12,0,'','',0.00,'Transport',9),(7,0,3,'Part3 from StorageFG to Shipping','Transport',7,8,6,'','',10.00,'Transport',2),(7,1,2,'Ship Part2','Ship',7,0,10,'','',0.00,'',1),(7,2,3,'Assemble Part3','Operation',7,8,0,'','',0.00,'Operation',7),(7,3,1,'Part1 from StorageRM to Assemble','Transport',7,9,0,'','',0.00,'Transport',8),(8,0,3,'Ship Part3','Ship',8,0,10,'','',10.00,'',1),(8,2,3,'Part3 from Assemble to StorageIG','Transport',8,9,0,'','',0.00,'Transport',6),(8,3,2,'Part2 from StorageRM to Assemble','Transport',8,9,0,'','',0.00,'Transport',8),(9,0,1,'Part1 from StorageRM to Assemble','Transport',9,3,0,'','',10.00,'Transport',5),(9,2,3,'Part3 from StorageIG to Assemble2','Transport',9,10,0,'','',0.00,'Transport',5),(9,3,4,'Assemble Part4','Operation',9,10,0,'','',0.00,'Operation',7),(10,0,2,'Part2 from StorageRM to Assemble','Transport',10,3,0,'','',10.00,'Transport',5),(10,2,4,'Assemble Part4','Operation',10,11,0,'','',0.00,'Operation',4),(10,3,4,'Part4 from Assemble to StorageIG','Transport',10,11,0,'','',0.00,'Transport',6),(11,2,4,'Part4 from Assemble2 to StorageFG','Transport',11,12,0,'','',0.00,'Transport',3),(11,3,4,'Part4 from StorageIG to Assemble2','Transport',11,13,0,'','',0.00,'Transport',5),(12,2,4,'Part4 from StorageFG to Shipping','Transport',12,13,0,'','',0.00,'Transport',2),(12,3,3,'Part3 from StorageRM to Assemble2','Transport',12,13,0,'','',0.00,'Transport',5),(13,2,4,'Ship Part4','Ship',13,0,10,'','',0.00,'',1),(13,3,5,'Assemble Part5','Operation',13,14,0,'','',0.00,'Operation',4),(14,3,5,'Part5 from Assemble2 to StorageFG','Transport',14,15,0,'','',0.00,'Transport',3),(15,3,5,'Part5 from StorageFG to Shipping','Transport',15,16,0,'','',0.00,'Transport',2),(16,3,5,'Ship Part5','Ship',16,0,10,'','',0.00,'',1);
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
INSERT INTO `t_catalog` VALUES (1,1,0,0.00,'',8.00,0.00,10,0.15,50,0.12,100,0.10),(1,1,1,0.00,'',10.00,0.00,20,1.50,50,1.20,100,1.00),(1,1,2,0.00,'',10.00,0.00,20,1.40,50,1.20,100,1.00),(1,1,3,0.00,'',10.00,0.00,20,1.40,50,1.20,100,1.00),(1,2,0,0.00,'',8.00,0.00,10,0.30,25,0.25,50,0.20),(1,2,2,0.00,'',10.00,0.00,20,1.80,50,1.20,100,1.00),(1,2,3,0.00,'',10.00,0.00,20,1.80,50,1.20,100,1.00),(1,3,3,0.00,'',10.00,0.00,20,1.60,50,1.30,100,1.00),(2,1,1,0.00,'',10.00,0.00,20,2.50,50,1.00,100,0.80),(2,1,2,0.00,'',10.00,0.00,20,2.20,50,1.00,100,0.90),(2,1,3,0.00,'',10.00,0.00,20,2.20,50,1.00,100,0.90),(2,2,2,0.00,'',10.00,0.00,20,2.10,50,1.50,100,1.20),(2,2,3,0.00,'',10.00,0.00,20,2.10,50,1.50,100,1.20),(2,3,3,0.00,'',10.00,0.00,20,1.70,50,1.20,100,0.90);
/*!40000 ALTER TABLE `t_catalog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_event`
--

DROP TABLE IF EXISTS `t_event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_event` (
  `id_event` int(11) NOT NULL,
  `id_game` int(11) NOT NULL,
  `time_factor_for_speed` double DEFAULT NULL,
  `time_missing` double DEFAULT NULL,
  `id_operator` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_event`,`id_game`),
  KEY `fkn_game_id_123` (`id_game`),
  KEY `event_game` (`id_game`),
  CONSTRAINT `event_game` FOREIGN KEY (`id_game`) REFERENCES `t_game` (`id_game`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_event`
--

LOCK TABLES `t_event` WRITE;
/*!40000 ALTER TABLE `t_event` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_event` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_game`
--

DROP TABLE IF EXISTS `t_game`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_game` (
  `id_game` int(11) NOT NULL,
  `date_time` datetime DEFAULT NULL,
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
INSERT INTO `t_game` VALUES (0,'2012-08-02 16:46:45',1,0.00,0,0,1,1,0,1,'CPU','Normal','Activities: 2 purchases, 6 transports, 1 assembly. \nOrders: total 10, maximum 8 to fail',10000,20.00,'Level 2','Unlocked',2,3,0,'Create and send part 3 when the orders arrive','phase2.png'),(0,'2012-08-02 18:02:30',3,8176.70,0,0,1,1,0,1,'USER','Normal','Activities: 2 purchases, 6 transports, 1 assembly. \nOrders: total 10, maximum 8 to fail',10000,20.00,'Level 2','Unlocked',2,3,0,'Create and send part 3 when the orders arrive','phase2.png'),(0,'2012-08-16 18:04:44',4,8808.15,2,2,1,1,151.5120000000029,0.25,'USER','Normal','Activities: 2 purchases, 6 transports, 1 assembly. \nOrders: total 10, maximum 8 to fail',10000,20.00,'Level 2','Unlocked',2,3,0,'Create and send part 3 when the orders arrive','phase2.png'),(0,'2012-08-15 12:17:25',5,8176.70,0,0,1,1,0,1,'USER','Normal','Activities: 2 purchases, 6 transports, 1 assembly. \nOrders: total 10, maximum 8 to fail',10000,20.00,'Level 2','Unlocked',2,3,0,'Create and send part 3 when the orders arrive','phase2.png'),(0,'2012-08-20 14:50:20',6,0.00,0,0,1,1,0,1,'USER','Normal','Activities: 2 purchases, 6 transports, 1 assembly. \nOrders: total 10, maximum 8 to fail',10000,20.00,'Level 2','Unlocked',2,3,0,'Create and send part 3 when the orders arrive','phase2.png'),(0,'2012-08-21 12:34:49',7,0.00,0,0,1,1,0,1,'USER','Normal','Activities: 2 purchases, 6 transports, 1 assembly. \nOrders: total 10, maximum 8 to fail',10000,20.00,'Level 2','Unlocked',2,3,0,'Create and send part 3 when the orders arrive','phase2.png'),(0,'2012-09-05 17:35:12',8,10000.00,0,0,1,1,0,1,'USER','Normal','Activities: 2 purchases, 6 transports, 1 assembly. \nOrders: total 10, maximum 8 to fail',10000,20.00,'Level 2','Completed',2,3,0,'Create and send part 3 when the orders arrive','phase2.png'),(1,'2012-08-19 23:33:38',1,0.00,0,0,1,1,0,1,'CPU','Easy','Activities: 1 purchase, 4 transports, 1 assembly. \nOrders: total 10, maximum 5 to fail',5000,50.00,'Level 1','Unlocked',1,2,0,'Create and ship part 2 when the orders arrive','phase1.png'),(1,'2012-08-20 14:50:21',6,0.00,0,0,1,1,0,1,'USER','Easy','Activities: 1 purchase, 4 transports, 1 assembly. \nOrders: total 10, maximum 5 to fail',5000,50.00,'Level 1','Unlocked',1,2,0,'Create and ship part 2 when the orders arrive','phase1.png'),(1,'2012-08-21 12:34:49',7,0.00,0,0,1,1,0,1,'USER','Easy','Activities: 1 purchase, 4 transports, 1 assembly. \nOrders: total 10, maximum 5 to fail',5000,50.00,'Level 1','Unlocked',1,2,0,'Create and ship part 2 when the orders arrive','phase1.png'),(1,'2012-09-03 13:46:01',8,1916.25,59,2,3,1,290.2559999999987,0.25,'USER','Easy','Activities: 1 purchase, 4 transports, 1 assembly. \nOrders: total 10, maximum 5 to fail',5000,50.00,'Level 1','Completed',1,2,0,'Create and ship part 2 when the orders arrive','phase1.png'),(2,'2012-08-21 12:30:27',1,0.00,0,0,1,1,0,1,'CPU','Normal','Activities: 2 purchases, 8 transports, 2 assembly. \nOrders: total 10, maximum 5 to fail',5000,50.00,'Level 3','Unlocked',3,4,0,'Create and ship part 4 when the orders arrive','phase3.png'),(2,'2012-08-21 12:34:49',7,0.00,0,0,1,1,0,1,'USER','Normal','Activities: 2 purchases, 8 transports, 2 assembly. \nOrders: total 10, maximum 5 to fail',5000,50.00,'Level 3','Unlocked',3,4,0,'Create and ship part 4 when the orders arrive','phase3.png'),(2,'2012-08-21 14:01:39',8,0.00,0,0,1,1,0,1,'USER','Normal','Activities: 2 purchases, 8 transports, 2 assembly. \nOrders: total 10, maximum 5 to fail',5000,50.00,'Level 3','Unlocked',3,4,0,'Create and ship part 4 when the orders arrive','phase3.png'),(3,'2012-08-21 13:59:03',1,0.00,0,0,1,1,0,1,'CPU','Hard','Activities: 3 purchases, 10 transports, 2 assembly. \nOrders: total 10, maximum 5 to fail',5000,50.00,'Level 4','Unlocked',4,5,0,'Create and ship part 5 when the orders arrive','phase4.png'),(3,'2012-08-21 14:01:39',8,0.00,0,0,1,1,0,1,'USER','Hard','Activities: 3 purchases, 10 transports, 2 assembly. \nOrders: total 10, maximum 5 to fail',5000,50.00,'Level 4','Unlocked',4,5,0,'Create and ship part 5 when the orders arrive','phase4.png');
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
) ENGINE=InnoDB AUTO_INCREMENT=91 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_log`
--

LOCK TABLES `t_log` WRITE;
/*!40000 ALTER TABLE `t_log` DISABLE KEYS */;
INSERT INTO `t_log` VALUES (1,'2012-05-13 01:17:05','Proc: Machine_Select(0) - Error: Unknown column ´virtual_maxtrix_end_j´ in ´field list´'),(2,'2012-05-13 01:20:08','Proc: Machine_Select(0) - Error: Unknown column ´virtual_maxtrix_end_j´ in ´field list´'),(3,'2012-05-13 01:23:34','Proc: Machine_Select(0) - Error: Unknown column ´virtual_maxtrix_end_j´ in ´field list´'),(4,'2012-05-13 01:26:27','Proc: Machine_Select(0) - Error: Unknown column ´virtual_maxtrix_end_j´ in ´field list´'),(5,'2012-05-13 01:31:13','Proc: Machine_Select(0) - Error: Unknown column ´virtual_maxtrix_end_j´ in ´field list´'),(6,'2012-05-18 11:56:57','Proc: Machine_Select(0) - Error: Unknown column ´virtual_maxtrix_end_j´ in ´field list´'),(7,'2012-05-18 12:05:37','Proc: Machine_Select(0) - Error: Unknown column ´virtual_maxtrix_end_j´ in ´field list´'),(8,'2012-05-18 12:07:27','Proc: Machine_Select(0) - Error: Unknown column ´virtual_maxtrix_end_j´ in ´field list´'),(9,'2012-05-18 12:08:18','Proc: Machine_Select(0) - Error: Unknown column ´virtual_maxtrix_end_j´ in ´field list´'),(10,'2012-05-18 12:18:59','Proc: Machine_Select(0) - Error: Unknown column ´virtual_maxtrix_end_j´ in ´field list´'),(11,'2012-05-18 12:19:40','Proc: Machine_Select(0) - Error: Unknown column ´virtual_maxtrix_end_j´ in ´field list´'),(12,'2012-05-18 12:23:18','Proc: Machine_Select(0) - Error: Unknown column ´virtual_maxtrix_end_j´ in ´field list´'),(13,'2012-05-18 12:24:46','Proc: Machine_Select(0) - Error: Unknown column ´virtual_maxtrix_end_j´ in ´field list´'),(14,'2012-05-18 12:27:39','Proc: Machine_Select(0) - Error: Unknown column ´virtual_maxtrix_end_j´ in ´field list´'),(15,'2012-05-18 12:32:27','Proc: Machine_Select(0) - Error: Unknown column ´virtual_maxtrix_end_j´ in ´field list´'),(16,'2012-05-18 12:40:00','Proc: Machine_Select(0) - Error: Unknown column ´virtual_maxtrix_end_j´ in ´field list´'),(17,'2012-05-18 12:42:23','Proc: Machine_Select(0) - Error: Unknown column ´virtual_maxtrix_end_j´ in ´field list´'),(18,'2012-05-18 12:53:36','Proc: Machine_Select(0) - Error: Unknown column ´virtual_maxtrix_end_j´ in ´field list´'),(19,'2012-05-18 13:01:53','Proc: Machine_Select(0) - Error: Unknown column ´virtual_maxtrix_end_j´ in ´field list´'),(20,'2012-05-18 13:03:13','Proc: Machine_Select(0) - Error: Unknown column ´virtual_maxtrix_end_j´ in ´field list´'),(21,'2012-05-18 16:47:20','Proc: Machine_Select(0) - Error: Unknown column ´virtual_maxtrix_end_j´ in ´field list´'),(22,'2012-05-18 17:02:44','Proc: Machine_Select(0) - Error: Unknown column ´virtual_maxtrix_end_j´ in ´field list´'),(23,'2012-05-18 17:07:08','Proc: Machine_Select(0) - Error: Unknown column ´virtual_maxtrix_end_j´ in ´field list´'),(24,'2012-05-18 17:08:48','Proc: Machine_Select(0) - Error: Unknown column ´virtual_maxtrix_end_j´ in ´field list´'),(25,'2012-05-18 17:09:40','Proc: Machine_Select(0) - Error: Unknown column ´virtual_maxtrix_end_j´ in ´field list´'),(26,'2012-05-18 17:12:04','Proc: Machine_Select(0) - Error: Unknown column ´virtual_maxtrix_end_j´ in ´field list´'),(27,'2012-05-18 17:22:25','Proc: Machine_Select(0) - Error: Unknown column ´virtual_maxtrix_end_j´ in ´field list´'),(28,'2012-05-18 17:23:09','Proc: Machine_Select(0) - Error: Unknown column ´virtual_maxtrix_end_j´ in ´field list´'),(29,'2012-05-18 17:56:27','Proc: Machine_Select(0) - Error: Unknown column ´virtual_maxtrix_end_j´ in ´field list´'),(30,'2012-05-18 17:58:59','Proc: Machine_Select(10) - Error: Unknown column ´virtual_maxtrix_end_j´ in ´field list´'),(31,'2012-05-18 18:02:20','Proc: Machine_Select(0) - Error: Unknown column ´virtual_maxtrix_end_j´ in ´field list´'),(32,'2012-05-18 18:10:48','Proc: Machine_Select(0) - Error: Unknown column ´virtual_maxtrix_end_j´ in ´field list´'),(33,'2012-05-18 18:15:10','Proc: Machine_Select(0) - Error: Unknown column ´virtual_maxtrix_end_j´ in ´field list´'),(34,'2012-05-18 18:53:04','Proc: Machine_Select(0) - Error: Unknown column ´virtual_maxtrix_end_j´ in ´field list´'),(35,'2012-05-18 18:53:52','Proc: Machine_Select(0) - Error: Unknown column ´virtual_maxtrix_end_j´ in ´field list´'),(36,'2012-05-18 18:56:37','Proc: Machine_Select(0) - Error: Unknown column ´virtual_maxtrix_end_j´ in ´field list´'),(37,'2012-06-04 04:57:55','Proc: Operation_Insert(3,Assemble Part3,,0.0,0.0,0,2,1,1,2) - Error: Duplicate entry 3 for key PRIMARY'),(38,'2012-06-04 04:57:55','Proc: TransportStore_Insert(4,10,2,2,1) - Error: Duplicate entry 4 for key PRIMARY'),(39,'2012-06-04 04:57:55','Proc: TransportStore_Insert(5,10,3,2,1) - Error: Duplicate entry 5 for key PRIMARY'),(40,'2012-06-04 04:57:55','Proc: TransportStore_Insert(7,30,5,3,4) - Error: Duplicate entry 7 for key PRIMARY'),(41,'2012-06-04 04:57:55','Proc: TransportStore_Insert(6,20,5,1,3) - Error: Duplicate entry 6 for key PRIMARY'),(42,'2012-06-04 04:57:55','Proc: Purchase_Insert(1,2,1,10,100) - Error: Duplicate entry 1 for key PRIMARY'),(43,'2012-06-04 04:57:55','Proc: Purchase_Insert(2,2,1,10,50) - Error: Duplicate entry 2 for key PRIMARY'),(44,'2012-06-04 04:57:55','Proc: Ship_Insert(8,4,50) - Error: Duplicate entry 8 for key PRIMARY'),(45,'2012-06-04 09:09:45','Proc: Game_Insert(1,1500.0,47,2,6,0,473.0929999997742) - Error: Data truncation: Out of range value for column ´decCurrentMoney´ at row 196'),(46,'2012-06-04 09:09:45','Proc: TypeOperation_Insert(1,0,Paint) - Error: Duplicate entry 1-0 for key PRIMARY'),(47,'2012-06-04 09:09:45','Proc: TypeOperation_Insert(2,0,Assemble) - Error: Duplicate entry 2-0 for key PRIMARY'),(48,'2012-06-04 09:09:45','Proc: TypeOperation_Insert(3,0,Cut) - Error: Duplicate entry 3-0 for key PRIMARY'),(49,'2012-06-04 09:09:45','Proc: Operator_Insert(1,0,Pepe,300.0,Busy,User,Walking,0,0,-3,-87,20.0) - Error: Duplicate entry 1-0 for key PRIMARY'),(50,'2012-06-04 09:09:45','Proc: Operator_Insert(2,0,Bob,300.0,Busy,User,Walking,0,0,35,57,22.0) - Error: Duplicate entry 2-0 for key PRIMARY'),(51,'2012-06-04 09:09:45','Proc: Operator_Insert(3,0,Juan,300.0,Idle,User,STATION1,0,0,67,-82,25.0) - Error: Duplicate entry 3-0 for key PRIMARY'),(52,'2012-06-04 09:09:45','Proc: Operator_Insert(4,0,Maria,300.0,Idle,User,STATION1,0,0,43,-62,15.0) - Error: Duplicate entry 4-0 for key PRIMARY'),(53,'2012-06-04 09:09:45','Proc: Part_Insert(1,0,Part One,EACH,100.0,100.0,100,52,0.25,User,Models/Screw/screw.j3o,0.01,Red) - Error: Duplicate entry 1-0 for key PRIMARY'),(54,'2012-06-04 09:09:45','Proc: Part_Insert(2,0,Part Two,EACH,100.0,100.0,50,26,0.4,User,Models/Screw2/screw2.j3o,0.01,Blue) - Error: Duplicate entry 2-0 for key PRIMARY'),(55,'2012-06-04 09:09:45','Proc: Part_Insert(3,0,Part Three,EACH,300.0,300.0,25,724,2.0,User,Models/Chair/chair.j3o,0.05,Green) - Error: Duplicate entry 3-0 for key PRIMARY'),(56,'2012-06-04 09:09:45','Proc: Supplier_Insert(1,0,Supplier Pepito,0,0,0.0) - Error: Duplicate entry 1-0 for key PRIMARY'),(57,'2012-06-04 09:09:45','Proc: Station_Insert(1,0,Station to create part 3,50,-70,40.0,40.0,1.0,25,25,Idle,User,Other) - Error: Duplicate entry 1-0 for key PRIMARY'),(58,'2012-06-04 09:09:45','Proc: Station_Insert(2,0,Receiving Dock,-120,-70,30.0,30.0,1.0,0,50,Idle,User,Other) - Error: Duplicate entry 2-0 for key PRIMARY'),(59,'2012-06-04 09:09:45','Proc: Station_Insert(3,0,Storage,240,10,50.0,50.0,1.0,200,200,Idle,User,Other) - Error: Duplicate entry 3-0 for key PRIMARY'),(60,'2012-06-04 09:09:45','Proc: Station_Insert(4,0,Ship Station,-120,70,30.0,30.0,1.0,200,200,Idle,User,Other) - Error: Duplicate entry 4-0 for key PRIMARY'),(61,'2012-06-04 09:09:45','Proc: Station_Insert(5,0,Staff Zone,50,50,40.0,20.0,1.0,0,0,Idle,User,StaffZone) - Error: Duplicate entry 5-0 for key PRIMARY'),(62,'2012-06-04 09:09:45','Proc: Station_Insert(6,0,Machine Zone,-60,50,40.0,20.0,1.0,0,0,Idle,User,MachineZone) - Error: Duplicate entry 6-0 for key PRIMARY'),(63,'2012-06-07 11:23:47','Proc: Event_Insert(0,28,1.0,192.0,4) - Error: Duplicate entry 0-28 for key PRIMARY'),(64,'2012-06-13 17:43:44','Proc: TypeOperationPerSkill_Select(0,0) - Error: Table ´gaming.t_skill_type_operation´ doesn´t exist'),(65,'2012-06-13 17:43:44','Proc: TypeOperationPerSkill_Select(0,1) - Error: Table ´gaming.t_skill_type_operation´ doesn´t exist'),(66,'2012-06-13 17:43:44','Proc: TypeOperationPerSkill_Select(0,2) - Error: Table ´gaming.t_skill_type_operation´ doesn´t exist'),(67,'2012-06-13 17:43:44','Proc: TypeOperationPerSkill_Select(0,3) - Error: Table ´gaming.t_skill_type_operation´ doesn´t exist'),(68,'2012-06-13 17:43:44','Proc: TypeOperationPerSkill_Select(0,4) - Error: Table ´gaming.t_skill_type_operation´ doesn´t exist'),(69,'2012-06-13 17:43:45','Proc: Operator_Select(0) - Error: Unknown column ´is_executing_activity´ in ´field list´'),(70,'2012-06-13 17:45:19','Proc: TypeOperationPerSkill_Select(0,0) - Error: Table ´gaming.t_skill_type_operation´ doesn´t exist'),(71,'2012-06-13 17:45:19','Proc: TypeOperationPerSkill_Select(0,1) - Error: Table ´gaming.t_skill_type_operation´ doesn´t exist'),(72,'2012-06-13 17:45:19','Proc: TypeOperationPerSkill_Select(0,2) - Error: Table ´gaming.t_skill_type_operation´ doesn´t exist'),(73,'2012-06-13 17:45:19','Proc: TypeOperationPerSkill_Select(0,3) - Error: Table ´gaming.t_skill_type_operation´ doesn´t exist'),(74,'2012-06-13 17:45:19','Proc: TypeOperationPerSkill_Select(0,4) - Error: Table ´gaming.t_skill_type_operation´ doesn´t exist'),(75,'2012-06-13 17:45:19','Proc: Operator_Select(0) - Error: Unknown column ´is_executing_activity´ in ´field list´'),(76,'2012-06-13 17:47:45','Proc: TypeOperationPerSkill_Select(0,0) - Error: Table ´gaming.t_skill_type_operation´ doesn´t exist'),(77,'2012-06-13 17:47:45','Proc: TypeOperationPerSkill_Select(0,1) - Error: Table ´gaming.t_skill_type_operation´ doesn´t exist'),(78,'2012-06-13 17:47:45','Proc: TypeOperationPerSkill_Select(0,2) - Error: Table ´gaming.t_skill_type_operation´ doesn´t exist'),(79,'2012-06-13 17:47:45','Proc: TypeOperationPerSkill_Select(0,3) - Error: Table ´gaming.t_skill_type_operation´ doesn´t exist'),(80,'2012-06-13 17:47:45','Proc: TypeOperationPerSkill_Select(0,4) - Error: Table ´gaming.t_skill_type_operation´ doesn´t exist'),(81,'2012-06-13 17:47:53','Proc: Operator_Select(0) - Error: Unknown column ´is_executing_activity´ in ´field list´'),(82,'2012-06-13 18:14:01','Proc: Operator_Select(0) - Error: Unknown column ´is_executing_activity´ in ´field list´'),(83,'2012-06-23 17:07:48','Proc: Game_SelectByType(CPU) - Error: Unknown column ´CPU´ in ´field list´'),(84,'2012-06-23 17:09:13','Proc: Game_SelectByType(CPU) - Error: Unknown column ´CPU´ in ´field list´'),(85,'2012-06-23 17:09:44','Proc: Game_SelectByType(CPU) - Error: Unknown column ´CPU´ in ´field list´'),(86,'2012-06-23 17:10:34','Proc: Game_SelectByType(CPU) - Error: Unknown column ´CPU´ in ´field list´'),(87,'2012-08-14 14:13:24','Proc: Order_Select(0) - Error: Column Index out of range, 7 > 6. '),(88,'2012-08-14 14:15:58','Proc: Order_Select(0) - Error: Column Index out of range, 7 > 6. '),(89,'2012-08-19 22:18:58','Proc: Game_SelectAGame(0) - Error: Column Index out of range, 22 > 21. '),(90,'2012-08-19 22:20:12','Proc: Game_SelectAGame(0) - Error: Column Index out of range, 22 > 21. ');
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
  `pick_up_time_distn` varchar(10) DEFAULT NULL,
  `pick_up_time_parameter1` decimal(10,2) DEFAULT NULL,
  `pick_up_time_parameter2` decimal(10,2) DEFAULT NULL,
  `machine_time_distn` varchar(10) DEFAULT NULL,
  `machine_time_parameter1` decimal(10,2) DEFAULT NULL,
  `machine_time_parameter2` decimal(10,2) DEFAULT NULL,
  `placement_time_distn` varchar(10) DEFAULT NULL,
  `placement_time_parameter1` decimal(10,2) DEFAULT NULL,
  `placement_time_parameter2` decimal(10,2) DEFAULT NULL,
  `time_between_failures_distn` varchar(10) DEFAULT NULL,
  `time_between_failures_parameter1` decimal(10,2) DEFAULT NULL,
  `time_between_failures_parameter2` decimal(10,2) DEFAULT NULL,
  `repair_time_distn` varchar(10) DEFAULT NULL,
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
INSERT INTO `t_machine` VALUES (1,0,'Machine to assemble',100.00,150.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',12.00,0.00,'',5.00,0.00,300.00,'Idle','User','Models/Machine/machine1.j3o','-1',0,0,0,0,0,0,15,20,5.00,'Operation','Active',''),(1,1,'Machine to transport',100.00,100.00,100.00,'',0.99,0.00,'',0.97,0.00,'',0.98,0.00,'',15.00,0.00,'',8.00,0.00,1000.00,'Idle','User','Models/Kart/kart.j3o','-1',0,0,0,0,0,0,5,5,8.00,'Transport','Active','Models/Kart/kart.j3m'),(1,2,'Machine to transport',100.00,100.00,100.00,'',0.99,0.00,'',0.97,0.00,'',0.98,0.00,'',15.00,0.00,'',8.00,0.00,1000.00,'Idle','User','Models/Kart/kart.j3o','-1',0,0,0,0,0,0,5,5,8.00,'Transport','Active','Models/Kart/kart.j3m'),(1,3,'Machine to transport',100.00,100.00,100.00,'',0.99,0.00,'',0.97,0.00,'',0.98,0.00,'',15.00,0.00,'',8.00,0.00,1000.00,'Idle','User','Models/Kart/kart.j3o','-1',0,0,0,0,0,0,5,5,8.00,'Transport','Active','Models/Kart/kart.j3m'),(2,0,'Machine to transport',100.00,110.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',20.00,0.00,'',5.00,0.00,200.00,'Idle','User','Models/Kart/kart.j3o','-1',0,0,0,0,0,0,5,5,5.00,'Transport','Inactive','Models/Kart/kart.j3m'),(2,1,'Machine to transport',100.00,100.00,100.00,'',0.99,0.00,'',0.97,0.00,'',0.98,0.00,'',15.00,0.00,'',8.00,0.00,1000.00,'Idle','User','Models/Kart/kart.j3o','-1',0,0,0,0,0,0,5,5,8.00,'Transport','Active','Models/Kart/kart.j3m'),(2,2,'Machine to transport',100.00,100.00,100.00,'',0.99,0.00,'',0.97,0.00,'',0.98,0.00,'',15.00,0.00,'',8.00,0.00,1000.00,'Idle','User','Models/Kart/kart.j3o','-1',0,0,0,0,0,0,5,5,8.00,'Transport','Active','Models/Kart/kart.j3m'),(2,3,'Machine to transport',100.00,100.00,100.00,'',0.99,0.00,'',0.97,0.00,'',0.98,0.00,'',15.00,0.00,'',8.00,0.00,1000.00,'Idle','User','Models/Kart/kart.j3o','-1',0,0,0,0,0,0,5,5,8.00,'Transport','Active','Models/Kart/kart.j3m'),(3,0,'Machine to transport',100.00,150.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',18.00,0.00,'',4.00,0.00,250.00,'Idle','User','Models/Kart/kart.j3o','-1',0,0,0,0,0,0,5,5,5.00,'Transport','Active','Models/Kart/kart.j3m'),(3,1,'Machine to transport',100.00,100.00,100.00,'',0.95,0.00,'',0.91,0.00,'',0.85,0.00,'',20.00,0.00,'',12.00,0.00,1000.00,'Idle','User','Models/Kart/kart.j3o','-1',0,0,0,0,0,0,5,5,5.00,'Transport','Active','Models/Kart/kart.j3m'),(3,2,'Machine to transport',100.00,100.00,100.00,'',0.95,0.00,'',0.91,0.00,'',0.85,0.00,'',20.00,0.00,'',12.00,0.00,1000.00,'Idle','User','Models/Kart/kart.j3o','-1',0,0,0,0,0,0,5,5,5.00,'Transport','Active','Models/Kart/kart.j3m'),(3,3,'Machine to transport',100.00,100.00,100.00,'',0.95,0.00,'',0.91,0.00,'',0.85,0.00,'',20.00,0.00,'',12.00,0.00,1000.00,'Idle','User','Models/Kart/kart.j3o','-1',0,0,0,0,0,0,5,5,5.00,'Transport','Active','Models/Kart/kart.j3m'),(4,0,'Machine to transport',100.00,200.00,200.00,NULL,0.95,0.00,NULL,0.97,0.00,NULL,0.95,0.00,NULL,20.00,0.00,NULL,10.00,0.00,999.00,'Idle','User','Models/Kart/kart.j3o','-1',0,0,0,0,0,0,5,5,7.00,'Transport','Active','Models/Kart/kart.j3m'),(4,1,'Machine to transport',100.00,100.00,100.00,'',0.96,0.00,'',0.92,0.00,'',0.89,0.00,'',11.00,0.00,'',16.00,0.00,1000.00,'Idle','User','Models/Kart/kart.j3o','-1',0,0,0,0,0,0,5,5,12.00,'Transport','Active','Models/Kart/kart.j3m'),(4,2,'Machine to transport',100.00,100.00,100.00,'',0.96,0.00,'',0.92,0.00,'',0.89,0.00,'',11.00,0.00,'',16.00,0.00,1000.00,'Idle','User','Models/Kart/kart.j3o','-1',0,0,0,0,0,0,5,5,12.00,'Transport','Active','Models/Kart/kart.j3m'),(4,3,'Machine to transport',100.00,100.00,100.00,'',0.96,0.00,'',0.92,0.00,'',0.89,0.00,'',11.00,0.00,'',16.00,0.00,1000.00,'Idle','User','Models/Kart/kart.j3o','-1',0,0,0,0,0,0,5,5,12.00,'Transport','Active','Models/Kart/kart.j3m'),(5,0,'Machine to transport',100.00,200.00,200.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',10.00,0.00,'',2.00,0.00,500.00,'Idle','User','Models/Kart/kart.j3o','-1',0,0,0,0,0,0,5,5,5.00,'Transport','Active','Models/Kart/kart.j3m'),(5,1,'Machine to transport',100.00,100.00,100.00,'',0.97,0.00,'',0.95,0.00,'',0.93,0.00,'',25.00,0.00,'',19.00,0.00,1000.00,'Idle','User','Models/Kart/kart.j3o','-1',0,0,0,0,0,0,5,5,7.50,'Transport','Active','Models/Kart/kart.j3m'),(5,2,'Machine to transport',100.00,100.00,100.00,'',0.97,0.00,'',0.95,0.00,'',0.93,0.00,'',25.00,0.00,'',19.00,0.00,1000.00,'Idle','User','Models/Kart/kart.j3o','-1',0,0,0,0,0,0,5,5,7.50,'Transport','Active','Models/Kart/kart.j3m'),(5,3,'Machine to transport',100.00,100.00,100.00,'',0.97,0.00,'',0.95,0.00,'',0.93,0.00,'',25.00,0.00,'',19.00,0.00,1000.00,'Idle','User','Models/Kart/kart.j3o','-1',0,0,0,0,0,0,5,5,7.50,'Transport','Active','Models/Kart/kart.j3m'),(6,0,'Machine to transport',100.00,200.00,200.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',16.00,0.00,'',5.00,0.00,500.00,'Idle','User','Models/Kart/kart.j3o','-1',0,0,0,0,0,0,5,5,5.00,'Transport','Active','Models/Kart/kart.j3m'),(6,1,'Machine to transport',100.00,100.00,100.00,'',0.93,0.00,'',0.98,0.00,'',0.95,0.00,'',25.00,0.00,'',18.00,0.00,1000.00,'Idle','User','Models/Kart/kart.j3o','-1',0,0,0,0,0,0,5,5,10.00,'Transport','Active','Models/Kart/kart.j3m'),(6,2,'Machine to transport',100.00,100.00,100.00,'',0.93,0.00,'',0.98,0.00,'',0.95,0.00,'',25.00,0.00,'',18.00,0.00,1000.00,'Idle','User','Models/Kart/kart.j3o','-1',0,0,0,0,0,0,5,5,10.00,'Transport','Active','Models/Kart/kart.j3m'),(6,3,'Machine to transport',100.00,100.00,100.00,'',0.93,0.00,'',0.98,0.00,'',0.95,0.00,'',25.00,0.00,'',18.00,0.00,1000.00,'Idle','User','Models/Kart/kart.j3o','-1',0,0,0,0,0,0,5,5,10.00,'Transport','Active','Models/Kart/kart.j3m'),(7,0,'Machine to transport',100.00,200.00,200.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',16.00,0.00,'',5.00,0.00,500.00,'Idle','User','Models/Kart/kart.j3o','-1',0,0,0,0,0,0,5,5,5.00,'Transport','Active','Models/Kart/kart.j3m'),(7,1,'Machine to transport',100.00,100.00,100.00,'',0.92,0.00,'',0.99,0.00,'',0.98,0.00,'',19.00,0.00,'',12.00,0.00,1000.00,'Idle','User','Models/Kart/kart.j3o','-1',0,0,0,0,0,0,5,5,11.00,'Transport','Active','Models/Kart/kart.j3m'),(7,2,'Machine to transport',100.00,100.00,100.00,'',0.92,0.00,'',0.99,0.00,'',0.98,0.00,'',19.00,0.00,'',12.00,0.00,1000.00,'Idle','User','Models/Kart/kart.j3o','-1',0,0,0,0,0,0,5,5,11.00,'Transport','Active','Models/Kart/kart.j3m'),(7,3,'Machine to transport',100.00,100.00,100.00,'',0.92,0.00,'',0.99,0.00,'',0.98,0.00,'',19.00,0.00,'',12.00,0.00,1000.00,'Idle','User','Models/Kart/kart.j3o','-1',0,0,0,0,0,0,5,5,11.00,'Transport','Active','Models/Kart/kart.j3m'),(8,0,'Machine to assemble',100.00,200.00,200.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',16.00,0.00,'',5.00,0.00,500.00,'Idle','User','Models/Machine/machine1.j3o','-1',0,0,0,0,0,0,15,20,5.00,'Operation','Active',''),(8,1,'Machine to assemble',100.00,100.00,100.00,'',0.99,0.00,'',0.97,0.00,'',0.98,0.00,'',15.00,0.00,'',8.00,0.00,1000.00,'Idle','User','Models/Machine/machine1.j3o','-1',0,0,0,0,0,0,15,20,8.00,'Operation','Active',''),(8,2,'Machine to assemble',100.00,100.00,100.00,'',0.92,0.00,'',0.99,0.00,'',0.98,0.00,'',19.00,0.00,'',12.00,0.00,1000.00,'Idle','User','Models/Machine/machine1.j3o','-1',0,0,0,0,0,0,15,20,11.00,'Operation','Active',''),(8,3,'Machine to assemble',100.00,100.00,100.00,'',0.92,0.00,'',0.99,0.00,'',0.98,0.00,'',19.00,0.00,'',12.00,0.00,1000.00,'Idle','User','Models/Machine/machine1.j3o','-1',0,0,0,0,0,0,15,20,11.00,'Operation','Active',''),(9,0,'Machine to assemble',100.00,200.00,200.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',16.00,0.00,'',5.00,0.00,500.00,'Idle','User','Models/Machine/machine1.j3o','-1',0,0,0,0,0,0,15,20,5.00,'Operation','Active',''),(9,1,'Machine to assemble',100.00,100.00,100.00,'',0.99,0.00,'',0.97,0.00,'',0.98,0.00,'',15.00,0.00,'',8.00,0.00,1000.00,'Idle','User','Models/Machine/machine1.j3o','-1',0,0,0,0,0,0,15,20,8.00,'Operation','Inactive',''),(9,2,'Machine to assemble',100.00,100.00,100.00,'',0.92,0.00,'',0.99,0.00,'',0.98,0.00,'',19.00,0.00,'',12.00,0.00,1000.00,'Idle','User','Models/Machine/machine1.j3o','-1',0,0,0,0,0,0,15,20,11.00,'Operation','Active',''),(9,3,'Machine to assemble',100.00,100.00,100.00,'',0.92,0.00,'',0.99,0.00,'',0.98,0.00,'',19.00,0.00,'',12.00,0.00,1000.00,'Idle','User','Models/Machine/machine1.j3o','-1',0,0,0,0,0,0,15,20,11.00,'Operation','Active',''),(10,1,'Machine to assemble',100.00,100.00,100.00,'',0.99,0.00,'',0.97,0.00,'',0.98,0.00,'',15.00,0.00,'',8.00,0.00,1000.00,'Idle','User','Models/Machine/machine1.j3o','-1',0,0,0,0,0,0,15,20,8.00,'Operation','Active',''),(10,2,'Machine to assemble',100.00,100.00,100.00,'',0.92,0.00,'',0.99,0.00,'',0.98,0.00,'',19.00,0.00,'',12.00,0.00,1000.00,'Idle','User','Models/Machine/machine1.j3o','-1',0,0,0,0,0,0,15,20,11.00,'Operation','Active',''),(10,3,'Machine to assemble',100.00,100.00,100.00,'',0.92,0.00,'',0.99,0.00,'',0.98,0.00,'',19.00,0.00,'',12.00,0.00,1000.00,'Idle','User','Models/Machine/machine1.j3o','-1',0,0,0,0,0,0,15,20,11.00,'Operation','Active',''),(11,1,'Machine to assemble',100.00,100.00,100.00,'',0.99,0.00,'',0.97,0.00,'',0.98,0.00,'',15.00,0.00,'',8.00,0.00,1000.00,'Idle','User','Models/Machine/machine1.j3o','-1',0,0,0,0,0,0,15,20,8.00,'Operation','Inactive',''),(11,2,'Machine to assemble',100.00,100.00,100.00,'',0.92,0.00,'',0.99,0.00,'',0.98,0.00,'',19.00,0.00,'',12.00,0.00,1000.00,'Idle','User','Models/Machine/machine1.j3o','-1',0,0,0,0,0,0,15,20,11.00,'Operation','Active',''),(11,3,'Machine to assemble',100.00,100.00,100.00,'',0.92,0.00,'',0.99,0.00,'',0.98,0.00,'',19.00,0.00,'',12.00,0.00,1000.00,'Idle','User','Models/Machine/machine1.j3o','-1',0,0,0,0,0,0,15,20,11.00,'Operation','Active','');
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
INSERT INTO `t_operator` VALUES (1,0,'Pepe',8.00,'Idle','User','-1',0,0,0,0,20.00,0,-1,0,0,'None',0,'Active'),(1,1,'Pepe',15.00,'Idle','User','-1',0,0,0,0,30.00,0,-1,0,0,'None',0,'Active'),(1,2,'Pepe',15.00,'Idle','User','-1',0,0,0,0,30.00,0,-1,0,0,'None',0,'Active'),(1,3,'Pepe',15.00,'Idle','User','-1',0,0,0,0,30.00,0,-1,0,0,'None',0,'Active'),(2,0,'Bob',8.00,'Idle','User','-1',0,0,0,0,22.00,0,-1,0,0,'None',0,'Active'),(2,1,'Cali',5.00,'Idle','User','-1',0,0,0,0,15.00,0,-1,0,0,'None',0,'Inactive'),(2,2,'Cali',5.00,'Idle','User','-1',0,0,0,0,15.00,0,-1,0,0,'None',0,'Active'),(2,3,'Cali',5.00,'Idle','User','-1',0,0,0,0,15.00,0,-1,0,0,'None',0,'Active'),(3,0,'Juan',8.00,'Idle','User','-1',0,0,0,0,25.00,0,-1,0,0,'None',0,'Active'),(3,1,'Brian',20.00,'Idle','User','-1',0,0,0,0,40.00,0,-1,0,0,'None',0,'Active'),(3,2,'Brian',20.00,'Idle','User','-1',0,0,0,0,40.00,0,-1,0,0,'None',0,'Active'),(3,3,'Brian',20.00,'Idle','User','-1',0,0,0,0,40.00,0,-1,0,0,'None',0,'Active'),(4,0,'Maria',8.00,'Idle','User','-1',0,0,0,0,15.00,0,-1,0,0,'None',0,'Active'),(4,1,'Juan',15.00,'Idle','User','-1',0,0,0,0,35.00,0,-1,0,0,'None',0,'Inactive'),(4,2,'Juan',15.00,'Idle','User','-1',0,0,0,0,35.00,0,-1,0,0,'None',0,'Active'),(4,3,'Juan',15.00,'Idle','User','-1',0,0,0,0,35.00,0,-1,0,0,'None',0,'Active'),(5,0,'Luis',10.00,'Idle','User','-1',0,0,0,0,30.00,0,-1,0,0,'None',0,'Active'),(5,1,'Jose',10.00,'Idle','User','-1',0,0,0,0,20.00,0,-1,0,0,'None',0,'Active'),(5,2,'Jose',10.00,'Idle','User','-1',0,0,0,0,20.00,0,-1,0,0,'None',0,'Active'),(5,3,'Jose',10.00,'Idle','User','-1',0,0,0,0,20.00,0,-1,0,0,'None',0,'Active'),(6,0,'Carlos',12.00,'Idle','User','-1',0,0,0,0,35.00,0,-1,0,0,'None',0,'Active'),(6,1,'Ramon',12.00,'Idle','User','-1',0,0,0,0,21.00,0,-1,0,0,'None',0,'Inactive'),(6,2,'Ramon',12.00,'Idle','User','-1',0,0,0,0,21.00,0,-1,0,0,'None',0,'Active'),(6,3,'Ramon',12.00,'Idle','User','-1',0,0,0,0,21.00,0,-1,0,0,'None',0,'Active'),(7,2,'Axel',12.00,'Idle','User','-1',0,0,0,0,21.00,0,-1,0,0,'None',0,'Active'),(7,3,'Axel',12.00,'Idle','User','-1',0,0,0,0,21.00,0,-1,0,0,'None',0,'Active'),(8,2,'Luis',12.00,'Idle','User','-1',0,0,0,0,21.00,0,-1,0,0,'None',0,'Active'),(8,3,'Luis',12.00,'Idle','User','-1',0,0,0,0,21.00,0,-1,0,0,'None',0,'Active');
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
INSERT INTO `t_part` VALUES (1,0,'Part One','PART',100.00,100.00,100,0,0.25,'User','Models/Screw/screw.j3o',0.01,'Red',0.97),(1,1,'Part One','PART',100.00,100.00,50,0,5.00,'User','',0.00,'Red',0.95),(1,2,'Part One','PART',100.00,100.00,50,0,5.00,'User','',0.00,'Red',0.95),(1,3,'Part One','PART',100.00,100.00,50,0,5.00,'User','',0.00,'Red',0.95),(2,0,'Part Two','PART',100.00,100.00,50,0,0.40,'User','Models/Screw2/screw2.j3o',0.01,'Blue',0.97),(2,1,'Part Two','PART',100.00,100.00,50,0,5.00,'User','',0.00,'Blue',0.99),(2,2,'Part Two','PART',100.00,100.00,50,0,5.00,'User','',0.00,'Blue',0.99),(2,3,'Part Two','PART',100.00,100.00,50,0,5.00,'User','',0.00,'Blue',0.99),(3,0,'Part Three','PART',300.00,300.00,25,0,2.00,'User','Models/Chair/chair.j3o',0.05,'Green',0.97),(3,2,'Part Three','PART',100.00,100.00,50,0,5.00,'User','',0.00,'Green',0.92),(3,3,'Part Three','PART',100.00,100.00,50,0,5.00,'User','',0.00,'Green',0.92),(4,2,'Part Four','PART',100.00,100.00,50,0,5.00,'User','',0.00,'Brown',0.97),(4,3,'Part Four','PART',100.00,100.00,50,0,5.00,'User','',0.00,'Brown',0.97),(5,3,'Part Five','PART',100.00,100.00,50,0,5.00,'User','',0.00,'Magenta',0.95);
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
  PRIMARY KEY (`id_player`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_player`
--

LOCK TABLES `t_player` WRITE;
/*!40000 ALTER TABLE `t_player` DISABLE KEYS */;
INSERT INTO `t_player` VALUES (0,'DefaultPlayer','def','default@gaming.com','123456'),(1,'newUser','','new@email.com','123456'),(2,'user1','','user1@email.com','123456'),(3,'user2','','user2@email.com','123456'),(4,'yserYYY','','yyy@mail.com','123456'),(5,'usezzzz','','userz@mail.com','123456'),(6,'newMAil','','new@mail.com','123456'),(7,'mmmmmmm','','mmm@mmm.com','123456'),(8,'nnnnnn','','nnn@nnn.com','123456');
/*!40000 ALTER TABLE `t_player` ENABLE KEYS */;
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
INSERT INTO `t_ship` VALUES (7,1,7,1),(8,0,4,50),(13,2,9,1),(16,3,9,1);
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
INSERT INTO `t_skill_operator` VALUES (0,1,0,0.90),(0,1,1,0.95),(0,1,2,0.95),(0,1,3,0.95),(0,2,0,0.95),(0,2,1,0.85),(0,2,2,0.85),(0,2,3,0.85),(0,3,0,0.99),(0,3,1,0.88),(0,3,2,0.88),(0,3,3,0.88),(0,4,0,0.98),(0,4,1,0.87),(0,4,2,0.87),(0,4,3,0.87),(0,5,0,0.85),(0,5,1,0.90),(0,5,2,0.90),(0,5,3,0.90),(0,6,0,0.86),(0,6,1,0.88),(0,6,2,0.88),(0,6,3,0.88),(0,7,2,0.95),(0,7,3,0.95),(0,8,2,0.85),(0,8,3,0.85),(1,1,0,0.97),(1,1,1,0.90),(1,1,2,0.90),(1,1,3,0.90),(1,2,0,0.95),(1,2,1,0.91),(1,2,2,0.91),(1,2,3,0.91),(1,3,0,0.93),(1,3,1,0.94),(1,3,2,0.94),(1,3,3,0.94),(1,4,0,0.94),(1,4,1,0.91),(1,4,2,0.91),(1,4,3,0.91),(1,5,0,0.90),(1,5,1,0.95),(1,5,2,0.95),(1,5,3,0.95),(1,6,0,0.88),(1,6,1,0.90),(1,6,2,0.90),(1,6,3,0.90),(1,7,2,0.90),(1,7,3,0.90),(1,8,2,0.91),(1,8,3,0.91),(2,1,0,0.97),(2,1,1,0.99),(2,1,2,0.99),(2,1,3,0.99),(2,2,0,0.90),(2,2,1,0.94),(2,2,2,0.94),(2,2,3,0.94),(2,3,0,0.96),(2,3,1,0.97),(2,3,2,0.97),(2,3,3,0.97),(2,4,0,0.93),(2,4,1,0.94),(2,4,2,0.94),(2,4,3,0.94),(2,5,0,0.96),(2,5,1,0.91),(2,5,2,0.91),(2,5,3,0.91),(2,6,0,0.95),(2,6,1,0.89),(2,6,2,0.89),(2,6,3,0.89),(2,7,2,0.99),(2,7,3,0.99),(2,8,2,0.94),(2,8,3,0.94),(3,1,0,0.92),(3,1,1,0.88),(3,1,2,0.88),(3,1,3,0.88),(3,2,0,0.89),(3,2,1,0.97),(3,2,2,0.97),(3,2,3,0.97),(3,3,0,0.88),(3,3,1,0.91),(3,3,2,0.91),(3,3,3,0.91),(3,4,0,0.90),(3,4,1,0.97),(3,4,2,0.97),(3,4,3,0.97),(3,5,0,0.79),(3,5,1,0.99),(3,5,2,0.99),(3,5,3,0.99),(3,6,0,0.92),(3,6,1,0.95),(3,6,2,0.95),(3,6,3,0.95),(3,7,2,0.88),(3,7,3,0.88),(3,8,2,0.97),(3,8,3,0.97),(4,1,0,0.94),(4,1,1,0.93),(4,1,2,0.93),(4,1,3,0.93),(4,2,0,0.85),(4,2,1,0.99),(4,2,2,0.99),(4,2,3,0.99),(4,3,0,0.87),(4,3,1,0.90),(4,3,2,0.90),(4,3,3,0.90),(4,4,0,0.83),(4,4,1,0.99),(4,4,2,0.99),(4,4,3,0.99),(4,5,0,0.82),(4,5,1,0.97),(4,5,2,0.97),(4,5,3,0.97),(4,6,0,0.96),(4,6,1,0.97),(4,6,2,0.97),(4,6,3,0.97),(4,7,2,0.93),(4,7,3,0.93),(4,8,2,0.99),(4,8,3,0.99),(5,1,0,0.96),(5,1,1,0.96),(5,1,2,0.96),(5,1,3,0.96),(5,2,0,0.93),(5,2,1,0.93),(5,2,2,0.93),(5,2,3,0.93),(5,3,0,0.91),(5,4,0,0.98),(5,5,1,0.94),(5,5,2,0.94),(5,5,3,0.94),(5,6,1,0.97),(5,6,2,0.97),(5,6,3,0.97),(5,7,2,0.94),(5,7,3,0.94),(5,8,2,0.97),(5,8,3,0.97),(6,3,1,0.91),(6,3,2,0.91),(6,3,3,0.91),(6,4,1,0.98),(6,4,2,0.98),(6,4,3,0.98),(6,5,0,0.94),(6,5,1,0.94),(6,5,2,0.94),(6,5,3,0.94),(6,6,0,0.97),(6,6,1,0.97),(6,6,2,0.97),(6,6,3,0.97),(6,7,2,0.94),(6,7,3,0.94),(6,8,2,0.97),(6,8,3,0.97);
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
INSERT INTO `t_station` VALUES (1,0,'Assemble Zone',100,-70,40.00,40.00,1.00,25,25,'Idle','User','AssembleZone',5.00),(1,1,'Staff Zone',180,-80,40.00,20.00,1.00,0,0,'Idle','User','StaffZone',5.00),(1,2,'Staff Zone',180,-80,40.00,20.00,1.00,0,0,'Idle','User','StaffZone',5.00),(1,3,'Staff Zone',180,-80,40.00,20.00,1.00,0,0,'Idle','User','StaffZone',5.00),(2,0,'Receiving Dock',-120,-70,30.00,30.00,1.00,0,50,'Idle','User','PurchaseZone',5.00),(2,1,'Machine Zone',180,50,80.00,20.00,1.00,0,0,'Idle','User','MachineZone',5.00),(2,2,'Machine Zone',180,50,80.00,20.00,1.00,0,0,'Idle','User','MachineZone',5.00),(2,3,'Machine Zone',180,50,80.00,20.00,1.00,0,0,'Idle','User','MachineZone',5.00),(3,0,'StorageFG',100,50,50.00,50.00,1.00,200,200,'Idle','User','StorageFG',5.00),(3,1,'Receiving Dock',-120,-70,30.00,30.00,1.00,0,50,'Idle','User','PurchaseZone',5.00),(3,2,'Receiving Dock',-120,-70,30.00,30.00,1.00,0,50,'Idle','User','PurchaseZone',5.00),(3,3,'Receiving Dock',-120,-70,30.00,30.00,1.00,0,50,'Idle','User','PurchaseZone',5.00),(4,0,'Ship Station',-120,70,30.00,30.00,1.00,200,200,'Idle','User','ShippingZone',5.00),(4,1,'Storage RM',-30,-70,40.00,40.00,1.00,25,25,'Idle','User','StorageRM',5.00),(4,2,'Storage RM',-30,-70,40.00,40.00,1.00,50,50,'Idle','User','StorageRM',5.00),(4,3,'Storage RM',-30,-70,40.00,40.00,1.00,50,50,'Idle','User','StorageRM',5.00),(5,0,'Staff Zone',180,-80,40.00,20.00,1.00,0,0,'Idle','User','StaffZone',5.00),(5,1,'Assemble Zone',100,-70,40.00,40.00,1.00,25,25,'Idle','User','AssembleZone',5.00),(5,2,'Assemble Zone',100,-70,40.00,40.00,1.00,25,25,'Idle','User','AssembleZone',5.00),(5,3,'Assemble Zone',100,-70,40.00,40.00,1.00,25,25,'Idle','User','AssembleZone',5.00),(6,0,'Machine Zone',180,50,60.00,20.00,1.00,0,0,'Idle','User','MachineZone',5.00),(6,1,'Storage FG',100,50,50.00,50.00,1.00,200,200,'Idle','User','StorageFG',5.00),(6,2,'Storage IG',100,50,40.00,40.00,1.00,50,50,'Idle','User','StorageIG',5.00),(6,3,'Storage IG',100,50,40.00,40.00,1.00,50,50,'Idle','User','StorageIG',5.00),(7,0,'StorageRM',-30,-70,40.00,40.00,1.00,25,25,'Idle','User','StorageRM',5.00),(7,1,'Ship Station',-120,70,30.00,30.00,1.00,200,200,'Idle','User','ShippingZone',5.00),(7,2,'Assemble Zone',40,50,40.00,40.00,1.00,25,25,'Idle','User','AssembleZone',5.00),(7,3,'Assemble Zone',40,50,40.00,40.00,1.00,25,25,'Idle','User','AssembleZone',5.00),(8,2,'Storage FG',-60,50,50.00,50.00,1.00,50,50,'Idle','User','StorageFG',5.00),(8,3,'Storage FG',-60,50,50.00,50.00,1.00,50,50,'Idle','User','StorageFG',5.00),(9,2,'Ship Station',-120,70,30.00,30.00,1.00,200,200,'Idle','User','ShippingZone',5.00),(9,3,'Ship Station',-120,70,30.00,30.00,1.00,200,200,'Idle','User','ShippingZone',5.00);
/*!40000 ALTER TABLE `t_station` ENABLE KEYS */;
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
INSERT INTO `t_terrain_reserved` VALUES (0,0,175,-20,40,90),(1,0,-85,45,24,24),(2,0,-85,-15,24,24),(3,0,-85,15,24,24);
/*!40000 ALTER TABLE `t_terrain_reserved` ENABLE KEYS */;
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
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,STRICT_ALL_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,TRADITIONAL,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
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
    vcFlowImage         varchar(100)
)
BEGIN

/*declare newIdGame int;
select ifnull(max(id_game)+1,1) into newIdGame from t_game;
*/
INSERT INTO t_game(id_game, date_time, id_player, current_money,
    current_minute, current_hour, current_day, current_month,
    current_game_time, time_factor, game_type, game_category,
    description, initial_money, percentage_to_win, name, game_status,
    id_phase, next_id_phase, id_terrain, objective, flow_image)
VALUES (intIdGame, now(), intIdPlayer, decCurrentMoney,
    intCurrentMinute, intCurrentHour, intCurrentDay, intCurrentMonth,
    dblCurrentTime, dblTimeFactor, vcGameType, vcGameCategory,
    vcDescription, decInitialMoney, decPercentageToWin, vcName,
    vcGameStatus, intIdPhase, intNextIdPhase, intIdTerrain, vcObjective,
    vcFlowImage);

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
        id_game, date_format(date_time,'%m/%d/%Y %r') as date_time, 
        id_player, current_money, current_minute, current_hour, 
        current_day, current_month, current_game_time, time_factor,
        game_type, game_category, description, initial_money,
        percentage_to_win, name, game_status, id_phase, next_id_phase,
        id_terrain, objective, flow_image
    from 
        t_game
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
    intIdGame     int
)
BEGIN

    select
        id_game, date_format(date_time,'%m/%d/%Y %r') as date_time, 
        id_player, current_money, current_minute, current_hour, 
        current_day, current_month, current_game_time, time_factor,
        game_type, game_category, description, initial_money,
        percentage_to_win, name, game_status, id_phase, next_id_phase,
        id_terrain, objective, flow_image
    from 
        t_game
    where
        id_game = intIdGame
    order by
        date_time desc;
        
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
        id_game, date_format(date_time,'%m/%d/%Y %r') as date_time, 
        id_player, current_money, current_minute, current_hour, 
        current_day, current_month, current_game_time, time_factor,
        game_type, game_category, description, initial_money,
        percentage_to_win, name, game_status, id_phase, next_id_phase,
        id_terrain, objective, flow_image
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
    vcFlowImage     varchar(100)
)
BEGIN

UPDATE t_game
SET date_time = now(),
    current_money = decCurrentMoney,
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
    flow_image = vcFlowImage
WHERE id_game = intIdGame and id_player = intIdPlayer;

IF vcGameStatus = 'Completed' THEN
    UPDATE t_game
    SET game_status = 'Unlocked'
    WHERE 
    id_player = intIdPlayer AND id_phase = intNextIdPhase;
END IF;

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
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,STRICT_ALL_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,TRADITIONAL,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
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
    vcMachineMaterial       varchar(100)
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
        machine_category, state, machine_material)
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
        vcMachineCategory, vcState, vcMachineMaterial);
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
        size_w, size_l, cost_per_hour, machine_category, state, machine_material
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
    vcState         varchar(45)
)
BEGIN
    insert into t_operator
        (id_operator, id_game, name_operator, salary_per_hour, status, owner,
        virtual_id_location, virtual_matrix_i, virtual_matrix_j,
        current_location_x, current_location_z, speed,
        is_moving, id_machine_attached, end_location_x,
        end_location_z, activity_doing, id_activity_assigned, state)
    values
        (intIdOperator, intIdGame, vcNameOperator,
        decSalaryPerHour, vcStatus, vcOwner, vcVirtualIdlocation,
        intVirtualMatrixI, intVirtualMatrixJ,
        intCurrentLocationX, intCurrentLocationZ, decSpeed,
        intIsMoving, intIdMachineAttached, intEndLocationX,
        intEndLocationZ, vcActivityDoing, intIdActivityAssigned, vcState);
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
        activity_doing, id_activity_assigned, state
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
    decFactor           decimal(10,2)
)
BEGIN
    INSERT INTO t_part(id_part, id_game, part_description, unit, volume,
    weight, quantity_palette, current_stock, price_for_sale,
    owner, part_design, part_design_scale, part_design_color, factor) 
    VALUES (intIdPart, intIdGame, vcPartDescription, vcUnit, decVolume,
    decWeight, intQuantityPalette, intCurrentStock, intPriceForSale,
    vcOwner, partDesign, partDesignScale, partDesignColor, decFactor); 
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
        owner, part_design, part_design_scale, part_design_color, factor
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
    vcPassword varchar(20)
)
BEGIN
declare newIdPlayer int;
select ifnull(max(id_player)+1,1) into newIdPlayer from t_player;

INSERT INTO t_player(id_player, name, nick_name, email, password) 
VALUES (newIdPlayer, vcName, vcNickName, vcEmail, vcPassword); 

select newIdPlayer;

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

    select
        id_player, name, nick_name, email, password
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
/*!50003 DROP PROCEDURE IF EXISTS `Player_Update` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,STRICT_ALL_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,TRADITIONAL,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `Player_Update`(
    intIdPlayer     int,
    vcName          varchar(100),
    vcNickName      varchar(50),
    vcEmail         varchar(100),
    vcPassword      varchar(20)
)
BEGIN
    update  
        t_player
    set
        name = vcName,
        nick_name = vcNickName,
        email = vcEmail,
        password = vcPassword
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
    intLoadQuantity  int
)
BEGIN
    insert into t_ship
        (id_ship, id_game, id_station, load_quantity)
    values
        (intIdShip, intIdGame, intIdStation, intLoadQuantity);
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
        a.activity_status, a.cost_per_execution, a.priority_queue
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
        id_skill, efficiency
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
    decCostPerHour     decimal(10,2)
)
BEGIN
    insert into t_station
        (id_station, id_game, station_description, station_location_x, 
        station_location_y, size_w, size_l, price_for_purchase,
        input_palette_capacity, output_palette_capacity, status, owner,
        station_type, cost_per_hour)
    values
        (intIdStation, intIdGame, vcStationDesc, intStationLocationX, 
        intStationLocationY, decSizeW, decSizeL, decPriceForPurchase,
        intInputPaletteCapacity, intOutputPaletteCapacity,
        vcStatus, vcOwner, vcStationType, decCostPerHour);
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
        status, owner, station_type, cost_per_hour
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

-- Dump completed on 2012-09-11 12:53:46
