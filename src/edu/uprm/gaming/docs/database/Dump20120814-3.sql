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
INSERT INTO `t_activity` VALUES (1,0,1,'Purchase Part1','Purchase',1,4,8,'','',10.00,'',1),(1,16,1,'Purchase Part1','Purchase',1,4,8,'','',10.00,'',1),(1,17,1,'Purchase Part1','Purchase',1,4,8,'','',10.00,'',1),(1,18,1,'Purchase Part1','Purchase',1,4,8,'','',10.00,'',1),(1,19,1,'Purchase Part1','Purchase',1,4,8,'','',10.00,'',1),(1,20,1,'Purchase Part1','Purchase',1,4,8,'','',10.00,'',1),(1,21,1,'Purchase Part1','Purchase',1,4,8,'','',10.00,'',1),(1,22,1,'Purchase Part1','Purchase',1,4,8,'','',10.00,'',1),(1,23,1,'Purchase Part1','Purchase',1,4,8,'','',10.00,'',1),(1,24,1,'Purchase Part1','Purchase',1,4,8,'','',10.00,'',1),(1,25,1,'Purchase Part1','Purchase',1,4,8,'','',10.00,'',1),(1,26,1,'Purchase Part1','Purchase',1,4,8,'','',10.00,'',1),(1,27,1,'Purchase Part1','Purchase',1,4,8,'','',10.00,'',1),(1,28,1,'Purchase Part1','Purchase',1,4,8,'','',10.00,'',1),(1,29,1,'Purchase Part1','Purchase',1,4,8,'','',10.00,'',1),(1,30,1,'Purchase Part1','Purchase',1,4,8,'','',10.00,'',1),(1,31,1,'Purchase Part1','Purchase',1,4,8,'','',10.00,'',1),(1,32,1,'Purchase Part1','Purchase',1,4,8,'','',10.00,'',1),(1,33,1,'Purchase Part1','Purchase',1,4,8,'','',10.00,'',1),(1,34,1,'Purchase Part1','Purchase',1,4,8,'','',10.00,'',1),(1,35,1,'Purchase Part1','Purchase',1,4,8,'','',10.00,'',1),(1,36,1,'Purchase Part1','Purchase',1,4,8,'Validate','FREE',10.00,'',1),(1,37,1,'Purchase Part1','Purchase',1,4,8,'Validate','FREE',10.00,'',1),(1,38,1,'Purchase Part1','Purchase',1,4,8,'Validate','FREE',10.00,'',1),(1,39,1,'Purchase Part1','Purchase',1,4,8,'Validate','FREE',10.00,'',1),(1,40,1,'Purchase Part1','Purchase',1,4,8,'Validate','FREE',10.00,'',1),(1,41,1,'Purchase Part1','Purchase',1,4,8,'Validate','FREE',10.00,'',1),(2,0,2,'Purchase Part2','Purchase',2,5,8,'','',10.00,'',1),(2,16,2,'Purchase Part2','Purchase',2,5,8,'','',10.00,'',1),(2,17,2,'Purchase Part2','Purchase',2,5,8,'','',10.00,'',1),(2,18,2,'Purchase Part2','Purchase',2,5,8,'','',10.00,'',1),(2,19,2,'Purchase Part2','Purchase',2,5,8,'','',10.00,'',1),(2,20,2,'Purchase Part2','Purchase',2,5,8,'','',10.00,'',1),(2,21,2,'Purchase Part2','Purchase',2,5,8,'','',10.00,'',1),(2,22,2,'Purchase Part2','Purchase',2,5,8,'','',10.00,'',1),(2,23,2,'Purchase Part2','Purchase',2,5,8,'','',10.00,'',1),(2,24,2,'Purchase Part2','Purchase',2,5,8,'','',10.00,'',1),(2,25,2,'Purchase Part2','Purchase',2,5,8,'','',10.00,'',1),(2,26,2,'Purchase Part2','Purchase',2,5,8,'','',10.00,'',1),(2,27,2,'Purchase Part2','Purchase',2,5,8,'','',10.00,'',1),(2,28,2,'Purchase Part2','Purchase',2,5,8,'','',10.00,'',1),(2,29,2,'Purchase Part2','Purchase',2,5,8,'','',10.00,'',1),(2,30,2,'Purchase Part2','Purchase',2,5,8,'','',10.00,'',1),(2,31,2,'Purchase Part2','Purchase',2,5,8,'','',10.00,'',1),(2,32,2,'Purchase Part2','Purchase',2,5,8,'','',10.00,'',1),(2,33,2,'Purchase Part2','Purchase',2,5,8,'','',10.00,'',1),(2,34,2,'Purchase Part2','Purchase',2,5,8,'','',10.00,'',1),(2,35,2,'Purchase Part2','Purchase',2,5,8,'','',10.00,'',1),(2,36,2,'Purchase Part2','Purchase',2,5,8,'Validate','FREE',10.00,'',1),(2,37,2,'Purchase Part2','Purchase',2,5,8,'Validate','FREE',10.00,'',1),(2,38,2,'Purchase Part2','Purchase',2,5,8,'Validate','FREE',10.00,'',1),(2,39,2,'Purchase Part2','Purchase',2,5,8,'Validate','FREE',10.00,'',1),(2,40,2,'Purchase Part2','Purchase',2,5,8,'Validate','FREE',10.00,'',1),(2,41,2,'Purchase Part2','Purchase',2,5,8,'Validate','FREE',10.00,'',1),(3,0,3,'Assemble Part3','Operation',3,6,3,'','',10.00,'Operation',1),(3,6,3,'Assemble Part3','Operation',3,6,3,'','',10.00,'',1),(3,7,3,'Assemble Part3','Operation',3,6,3,'','',10.00,'',1),(3,8,3,'Assemble Part3','Operation',3,6,3,'','',10.00,'',1),(3,9,3,'Assemble Part3','Operation',3,6,3,'','',10.00,'',1),(3,10,3,'Assemble Part3','Operation',3,6,3,'','',10.00,'',1),(3,11,3,'Assemble Part3','Operation',3,6,3,'','',10.00,'',1),(3,12,3,'Assemble Part3','Operation',3,6,3,'','',10.00,'',1),(3,13,3,'Assemble Part3','Operation',3,6,3,'','',10.00,'',1),(3,14,3,'Assemble Part3','Operation',3,6,3,'','',10.00,'',1),(3,15,3,'Assemble Part3','Operation',3,6,3,'','',10.00,'',1),(3,16,3,'Assemble Part3','Operation',3,6,3,'','',10.00,'',1),(3,17,3,'Assemble Part3','Operation',3,6,3,'','',10.00,'',1),(3,18,3,'Assemble Part3','Operation',3,6,3,'','',10.00,'',1),(3,19,3,'Assemble Part3','Operation',3,6,3,'','',10.00,'',1),(3,20,3,'Assemble Part3','Operation',3,6,3,'','',10.00,'',1),(3,21,3,'Assemble Part3','Operation',3,6,3,'','',10.00,'',1),(3,22,3,'Assemble Part3','Operation',3,6,3,'','',10.00,'',1),(3,23,3,'Assemble Part3','Operation',3,6,3,'','',10.00,'',1),(3,24,3,'Assemble Part3','Operation',3,6,3,'','',10.00,'',1),(3,25,3,'Assemble Part3','Operation',3,6,3,'','',10.00,'',1),(3,26,3,'Assemble Part3','Operation',3,6,3,'','',10.00,'',1),(3,27,3,'Assemble Part3','Operation',3,6,3,'','',10.00,'',1),(3,28,3,'Assemble Part3','Operation',3,6,3,'','',10.00,'',1),(3,29,3,'Assemble Part3','Operation',3,6,3,'','',10.00,'',1),(3,30,3,'Assemble Part3','Operation',3,6,3,'','',10.00,'',1),(3,31,3,'Assemble Part3','Operation',3,6,3,'','',10.00,'',1),(3,32,3,'Assemble Part3','Operation',3,6,3,'','',10.00,'',1),(3,33,3,'Assemble Part3','Operation',3,6,3,'','',10.00,'',1),(3,34,3,'Assemble Part3','Operation',3,6,3,'','',10.00,'',1),(3,35,3,'Assemble Part3','Operation',3,6,3,'','',10.00,'',1),(3,36,3,'Assemble Part3','Operation',3,6,3,'Validate','FREE',10.00,'',1),(3,37,3,'Assemble Part3','Operation',3,6,3,'Validate','FREE',10.00,'',1),(3,38,3,'Assemble Part3','Operation',3,6,3,'Validate','FREE',10.00,'',1),(3,39,3,'Assemble Part3','Operation',3,6,3,'Validate','FREE',10.00,'',1),(3,40,3,'Assemble Part3','Operation',3,6,3,'Validate','FREE',10.00,'',1),(3,41,3,'Assemble Part3','Operation',3,6,3,'Validate','FREE',10.00,'',1),(4,0,1,'Store Part 1 from Receiving Dock to Station 7','Store',4,3,4,'','',10.00,'Transport',1),(4,16,1,'Transport Part1 from Receiving Dock to Station1','Transport',4,3,4,'','',10.00,'',1),(4,17,1,'Transport Part1 from Receiving Dock to Station1','Transport',4,3,4,'','',10.00,'',1),(4,18,1,'Transport Part1 from Receiving Dock to Station1','Transport',4,3,4,'','',10.00,'',1),(4,19,1,'Transport Part1 from Receiving Dock to Station1','Transport',4,3,4,'','',10.00,'',1),(4,20,1,'Transport Part1 from Receiving Dock to Station1','Transport',4,3,4,'','',10.00,'',1),(4,21,1,'Transport Part1 from Receiving Dock to Station1','Transport',4,3,4,'','',10.00,'',1),(4,22,1,'Transport Part1 from Receiving Dock to Station1','Transport',4,3,4,'','',10.00,'',1),(4,23,1,'Transport Part1 from Receiving Dock to Station1','Transport',4,3,4,'','',10.00,'',1),(4,24,1,'Transport Part1 from Receiving Dock to Station1','Transport',4,3,4,'','',10.00,'',1),(4,25,1,'Transport Part1 from Receiving Dock to Station1','Transport',4,3,4,'','',10.00,'',1),(4,26,1,'Transport Part1 from Receiving Dock to Station1','Transport',4,3,4,'','',10.00,'',1),(4,27,1,'Transport Part1 from Receiving Dock to Station1','Transport',4,3,4,'','',10.00,'',1),(4,28,1,'Transport Part1 from Receiving Dock to Station1','Transport',4,3,4,'','',10.00,'',1),(4,29,1,'Transport Part1 from Receiving Dock to Station1','Transport',4,3,4,'','',10.00,'',1),(4,30,1,'Transport Part1 from Receiving Dock to Station1','Transport',4,3,4,'','',10.00,'',1),(4,31,1,'Transport Part1 from Receiving Dock to Station1','Transport',4,3,4,'','',10.00,'',1),(4,32,1,'Transport Part1 from Receiving Dock to Station1','Transport',4,3,4,'','',10.00,'',1),(4,33,1,'Transport Part1 from Receiving Dock to Station1','Transport',4,3,4,'','',10.00,'',1),(4,34,1,'Transport Part1 from Receiving Dock to Station1','Transport',4,3,4,'','',10.00,'',1),(4,35,1,'Transport Part1 from Receiving Dock to Station1','Transport',4,3,4,'','',10.00,'',1),(4,36,1,'Transport Part1 from Receiving Dock to Station1','Transport',4,3,4,'OperatorAndMachineWalkToFROMstation','BUSY',10.00,'',1),(4,37,1,'Transport Part1 from Receiving Dock to Station1','Transport',4,3,4,'OperatorAndMachineWalkToFROMstation','BUSY',10.00,'',1),(4,38,1,'Transport Part1 from Receiving Dock to Station1','Transport',4,3,4,'PickUpItems','BUSY',10.00,'',1),(4,39,1,'Transport Part1 from Receiving Dock to Station1','Transport',4,3,4,'PickUpItems','BUSY',10.00,'',1),(4,40,1,'Transport Part1 from Receiving Dock to Station1','Transport',4,3,4,'OperatorWalksToMachine','BUSY',10.00,'',1),(4,41,1,'Transport Part1 from Receiving Dock to Station1','Transport',4,3,4,'OperatorAndMachineWalkToTOstation','BUSY',10.00,'',1),(5,0,2,'Store Part 2 from Receiving Dock to Station 7','Store',5,3,4,'','',10.00,'Transport',1),(5,16,2,'Transport Part2 from Receiving Dock to Station1','Transport',5,3,4,'','',10.00,'',1),(5,17,2,'Transport Part2 from Receiving Dock to Station1','Transport',5,3,4,'','',10.00,'',1),(5,18,2,'Transport Part2 from Receiving Dock to Station1','Transport',5,3,4,'','',10.00,'',1),(5,19,2,'Transport Part2 from Receiving Dock to Station1','Transport',5,3,4,'','',10.00,'',1),(5,20,2,'Transport Part2 from Receiving Dock to Station1','Transport',5,3,4,'','',10.00,'',1),(5,21,2,'Transport Part2 from Receiving Dock to Station1','Transport',5,3,4,'','',10.00,'',1),(5,22,2,'Transport Part2 from Receiving Dock to Station1','Transport',5,3,4,'','',10.00,'',1),(5,23,2,'Transport Part2 from Receiving Dock to Station1','Transport',5,3,4,'','',10.00,'',1),(5,24,2,'Transport Part2 from Receiving Dock to Station1','Transport',5,3,4,'','',10.00,'',1),(5,25,2,'Transport Part2 from Receiving Dock to Station1','Transport',5,3,4,'','',10.00,'',1),(5,26,2,'Transport Part2 from Receiving Dock to Station1','Transport',5,3,4,'','',10.00,'',1),(5,27,2,'Transport Part2 from Receiving Dock to Station1','Transport',5,3,4,'','',10.00,'',1),(5,28,2,'Transport Part2 from Receiving Dock to Station1','Transport',5,3,4,'','',10.00,'',1),(5,29,2,'Transport Part2 from Receiving Dock to Station1','Transport',5,3,4,'','',10.00,'',1),(5,30,2,'Transport Part2 from Receiving Dock to Station1','Transport',5,3,4,'','',10.00,'',1),(5,31,2,'Transport Part2 from Receiving Dock to Station1','Transport',5,3,4,'','',10.00,'',1),(5,32,2,'Transport Part2 from Receiving Dock to Station1','Transport',5,3,4,'','',10.00,'',1),(5,33,2,'Transport Part2 from Receiving Dock to Station1','Transport',5,3,4,'','',10.00,'',1),(5,34,2,'Transport Part2 from Receiving Dock to Station1','Transport',5,3,4,'','',10.00,'',1),(5,35,2,'Transport Part2 from Receiving Dock to Station1','Transport',5,3,4,'','',10.00,'',1),(5,36,2,'Transport Part2 from Receiving Dock to Station1','Transport',5,3,4,'OperatorWalksToMachine','BUSY',10.00,'',1),(5,37,2,'Transport Part2 from Receiving Dock to Station1','Transport',5,3,4,'PickUpItems','BUSY',10.00,'',1),(5,38,2,'Transport Part2 from Receiving Dock to Station1','Transport',5,3,4,'PickUpItems','BUSY',10.00,'',1),(5,39,2,'Transport Part2 from Receiving Dock to Station1','Transport',5,3,4,'PickUpItems','BUSY',10.00,'',1),(5,40,2,'Transport Part2 from Receiving Dock to Station1','Transport',5,3,4,'OperatorWalksToMachine','BUSY',10.00,'',1),(5,41,2,'Transport Part2 from Receiving Dock to Station1','Transport',5,3,4,'OperatorAndMachineWalkToTOstation','BUSY',10.00,'',1),(6,0,3,'Move Part3 in Station1 to Storage','Store',6,7,5,'','',10.00,'Transport',1),(6,16,3,'Move Part3 in Station1 to Storage','Store',6,7,5,'','',10.00,'',1),(6,17,3,'Move Part3 in Station1 to Storage','Store',6,7,5,'','',10.00,'',1),(6,18,3,'Move Part3 in Station1 to Storage','Store',6,7,5,'','',10.00,'',1),(6,19,3,'Move Part3 in Station1 to Storage','Store',6,7,5,'','',10.00,'',1),(6,20,3,'Move Part3 in Station1 to Storage','Store',6,7,5,'','',10.00,'',1),(6,21,3,'Move Part3 in Station1 to Storage','Store',6,7,5,'','',10.00,'',1),(6,22,3,'Move Part3 in Station1 to Storage','Store',6,7,5,'','',10.00,'',1),(6,23,3,'Move Part3 in Station1 to Storage','Store',6,7,5,'','',10.00,'',1),(6,24,3,'Move Part3 in Station1 to Storage','Store',6,7,5,'','',10.00,'',1),(6,25,3,'Move Part3 in Station1 to Storage','Store',6,7,5,'','',10.00,'',1),(6,26,3,'Move Part3 in Station1 to Storage','Store',6,7,5,'','',10.00,'',1),(6,27,3,'Move Part3 in Station1 to Storage','Store',6,7,5,'','',10.00,'',1),(6,28,3,'Move Part3 in Station1 to Storage','Store',6,7,5,'','',10.00,'',1),(6,29,3,'Move Part3 in Station1 to Storage','Store',6,7,5,'','',10.00,'',1),(6,30,3,'Move Part3 in Station1 to Storage','Store',6,7,5,'','',10.00,'',1),(6,31,3,'Move Part3 in Station1 to Storage','Store',6,7,5,'','',10.00,'',1),(6,32,3,'Move Part3 in Station1 to Storage','Store',6,7,5,'','',10.00,'',1),(6,33,3,'Move Part3 in Station1 to Storage','Store',6,7,5,'','',10.00,'',1),(6,34,3,'Move Part3 in Station1 to Storage','Store',6,7,5,'','',10.00,'',1),(6,35,3,'Move Part3 in Station1 to Storage','Store',6,7,5,'','',10.00,'',1),(6,36,3,'Move Part3 in Station1 to Storage','Store',6,7,5,'Validate','FREE',10.00,'',1),(6,37,3,'Move Part3 in Station1 to Storage','Store',6,7,5,'Validate','FREE',10.00,'',1),(6,38,3,'Move Part3 in Station1 to Storage','Store',6,7,5,'Validate','FREE',10.00,'',1),(6,39,3,'Move Part3 in Station1 to Storage','Store',6,7,5,'Validate','FREE',10.00,'',1),(6,40,3,'Move Part3 in Station1 to Storage','Store',6,7,5,'Validate','FREE',10.00,'',1),(6,41,3,'Move Part3 in Station1 to Storage','Store',6,7,5,'Validate','FREE',10.00,'',1),(7,0,3,'Transport Part3 from Station1 to Ship Station','Store',7,8,6,'','',10.00,'Transport',1),(7,16,3,'Transport Part3 from Station1 to Ship Station','Store',7,8,6,'','',10.00,'',1),(7,17,3,'Transport Part3 from Station1 to Ship Station','Store',7,8,6,'','',10.00,'',1),(7,18,3,'Transport Part3 from Station1 to Ship Station','Store',7,8,6,'','',10.00,'',1),(7,19,3,'Transport Part3 from Station1 to Ship Station','Store',7,8,6,'','',10.00,'',1),(7,20,3,'Transport Part3 from Station1 to Ship Station','Store',7,8,6,'','',10.00,'',1),(7,21,3,'Transport Part3 from Station1 to Ship Station','Store',7,8,6,'','',10.00,'',1),(7,22,3,'Transport Part3 from Station1 to Ship Station','Store',7,8,6,'','',10.00,'',1),(7,23,3,'Transport Part3 from Station1 to Ship Station','Store',7,8,6,'','',10.00,'',1),(7,24,3,'Transport Part3 from Station1 to Ship Station','Store',7,8,6,'','',10.00,'',1),(7,25,3,'Transport Part3 from Station1 to Ship Station','Store',7,8,6,'','',10.00,'',1),(7,26,3,'Transport Part3 from Station1 to Ship Station','Store',7,8,6,'','',10.00,'',1),(7,27,3,'Transport Part3 from Station1 to Ship Station','Store',7,8,6,'','',10.00,'',1),(7,28,3,'Transport Part3 from Station1 to Ship Station','Store',7,8,6,'','',10.00,'',1),(7,29,3,'Transport Part3 from Station1 to Ship Station','Store',7,8,6,'','',10.00,'',1),(7,30,3,'Transport Part3 from Station1 to Ship Station','Store',7,8,6,'','',10.00,'',1),(7,31,3,'Transport Part3 from Station1 to Ship Station','Store',7,8,6,'','',10.00,'',1),(7,32,3,'Transport Part3 from Station1 to Ship Station','Store',7,8,6,'','',10.00,'',1),(7,33,3,'Transport Part3 from Station1 to Ship Station','Store',7,8,6,'','',10.00,'',1),(7,34,3,'Transport Part3 from Station1 to Ship Station','Store',7,8,6,'','',10.00,'',1),(7,35,3,'Transport Part3 from Station1 to Ship Station','Store',7,8,6,'','',10.00,'',1),(7,36,3,'Transport Part3 from Station1 to Ship Station','Store',7,8,6,'Validate','FREE',10.00,'',1),(7,37,3,'Transport Part3 from Station1 to Ship Station','Store',7,8,6,'Validate','FREE',10.00,'',1),(7,38,3,'Transport Part3 from Station1 to Ship Station','Store',7,8,6,'Validate','FREE',10.00,'',1),(7,39,3,'Transport Part3 from Station1 to Ship Station','Store',7,8,6,'Validate','FREE',10.00,'',1),(7,40,3,'Transport Part3 from Station1 to Ship Station','Store',7,8,6,'Validate','FREE',10.00,'',1),(7,41,3,'Transport Part3 from Station1 to Ship Station','Store',7,8,6,'Validate','FREE',10.00,'',1),(8,0,3,'Ship Part3','Ship',8,0,10,'','',10.00,'',1),(8,16,3,'Ship Part3','Ship',8,0,10,'','',10.00,'',1),(8,17,3,'Ship Part3','Ship',8,0,10,'','',10.00,'',1),(8,18,3,'Ship Part3','Ship',8,0,10,'','',10.00,'',1),(8,19,3,'Ship Part3','Ship',8,0,10,'','',10.00,'',1),(8,20,3,'Ship Part3','Ship',8,0,10,'','',10.00,'',1),(8,21,3,'Ship Part3','Ship',8,0,10,'','',10.00,'',1),(8,22,3,'Ship Part3','Ship',8,0,10,'','',10.00,'',1),(8,23,3,'Ship Part3','Ship',8,0,10,'','',10.00,'',1),(8,24,3,'Ship Part3','Ship',8,0,10,'','',10.00,'',1),(8,25,3,'Ship Part3','Ship',8,0,10,'','',10.00,'',1),(8,26,3,'Ship Part3','Ship',8,0,10,'','',10.00,'',1),(8,27,3,'Ship Part3','Ship',8,0,10,'','',10.00,'',1),(8,28,3,'Ship Part3','Ship',8,0,10,'','',10.00,'',1),(8,29,3,'Ship Part3','Ship',8,0,10,'','',10.00,'',1),(8,30,3,'Ship Part3','Ship',8,0,10,'','',10.00,'',1),(8,31,3,'Ship Part3','Ship',8,0,10,'','',10.00,'',1),(8,32,3,'Ship Part3','Ship',8,0,10,'','',10.00,'',1),(8,33,3,'Ship Part3','Ship',8,0,10,'','',10.00,'',1),(8,34,3,'Ship Part3','Ship',8,0,10,'','',10.00,'',1),(8,35,3,'Ship Part3','Ship',8,0,10,'','',10.00,'',1),(8,36,3,'Ship Part3','Ship',8,0,10,'Validate','FREE',10.00,'',1),(8,37,3,'Ship Part3','Ship',8,0,10,'Validate','FREE',10.00,'',1),(8,38,3,'Ship Part3','Ship',8,0,10,'Validate','FREE',10.00,'',1),(8,39,3,'Ship Part3','Ship',8,0,10,'Validate','FREE',10.00,'',1),(8,40,3,'Ship Part3','Ship',8,0,10,'Validate','FREE',10.00,'',1),(8,41,3,'Ship Part3','Ship',8,0,10,'Validate','FREE',10.00,'',1),(9,0,1,'Move Par 1 from Station 7 to Station 1','Store',0,0,0,'','',10.00,'Transport',1),(10,0,2,'Move Part 2 from Station 7 to Station 1','Store',0,0,0,'','',10.00,'Transport',1);
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
INSERT INTO `t_assembly_details` VALUES (3,1,0,2,'Part3 requires 2 units of Part1'),(3,1,1,2,'Part3 requires 2 units of Part1'),(3,1,2,2,'Part3 requires 2 units of Part1'),(3,1,3,2,'Part3 requires 2 units of Part1'),(3,1,4,2,'Part3 requires 2 units of Part1'),(3,1,5,2,'Part3 requires 2 units of Part1'),(3,1,6,2,'Part3 requires 2 units of Part1'),(3,1,7,2,'Part3 requires 2 units of Part1'),(3,1,8,2,'Part3 requires 2 units of Part1'),(3,1,9,2,'Part3 requires 2 units of Part1'),(3,1,10,2,'Part3 requires 2 units of Part1'),(3,1,11,2,'Part3 requires 2 units of Part1'),(3,1,12,2,'Part3 requires 2 units of Part1'),(3,1,13,2,'Part3 requires 2 units of Part1'),(3,1,14,2,'Part3 requires 2 units of Part1'),(3,1,15,2,'Part3 requires 2 units of Part1'),(3,1,16,2,'Part3 requires 2 units of Part1'),(3,1,17,2,'Part3 requires 2 units of Part1'),(3,1,18,2,'Part3 requires 2 units of Part1'),(3,1,19,2,'Part3 requires 2 units of Part1'),(3,1,20,2,'Part3 requires 2 units of Part1'),(3,1,21,2,'Part3 requires 2 units of Part1'),(3,1,22,2,'Part3 requires 2 units of Part1'),(3,1,23,2,'Part3 requires 2 units of Part1'),(3,1,24,2,'Part3 requires 2 units of Part1'),(3,1,25,2,'Part3 requires 2 units of Part1'),(3,1,26,2,'Part3 requires 2 units of Part1'),(3,1,27,2,'Part3 requires 2 units of Part1'),(3,1,28,2,'Part3 requires 2 units of Part1'),(3,1,29,2,'Part3 requires 2 units of Part1'),(3,1,30,2,'Part3 requires 2 units of Part1'),(3,1,31,2,'Part3 requires 2 units of Part1'),(3,1,32,2,'Part3 requires 2 units of Part1'),(3,1,33,2,'Part3 requires 2 units of Part1'),(3,1,34,2,'Part3 requires 2 units of Part1'),(3,1,35,2,'Part3 requires 2 units of Part1'),(3,1,36,2,'Part3 requires 2 units of Part1'),(3,1,37,2,'Part3 requires 2 units of Part1'),(3,1,38,2,'Part3 requires 2 units of Part1'),(3,1,39,2,'Part3 requires 2 units of Part1'),(3,1,40,2,'Part3 requires 2 units of Part1'),(3,1,41,2,'Part3 requires 2 units of Part1'),(3,2,0,1,'Part3 requires 1 unit of Part2'),(3,2,1,1,'Part3 requires 1 unit of Part2'),(3,2,2,1,'Part3 requires 1 unit of Part2'),(3,2,3,1,'Part3 requires 1 unit of Part2'),(3,2,4,1,'Part3 requires 1 unit of Part2'),(3,2,5,1,'Part3 requires 1 unit of Part2'),(3,2,6,1,'Part3 requires 1 unit of Part2'),(3,2,7,1,'Part3 requires 1 unit of Part2'),(3,2,8,1,'Part3 requires 1 unit of Part2'),(3,2,9,1,'Part3 requires 1 unit of Part2'),(3,2,10,1,'Part3 requires 1 unit of Part2'),(3,2,11,1,'Part3 requires 1 unit of Part2'),(3,2,12,1,'Part3 requires 1 unit of Part2'),(3,2,13,1,'Part3 requires 1 unit of Part2'),(3,2,14,1,'Part3 requires 1 unit of Part2'),(3,2,15,1,'Part3 requires 1 unit of Part2'),(3,2,16,1,'Part3 requires 1 unit of Part2'),(3,2,17,1,'Part3 requires 1 unit of Part2'),(3,2,18,1,'Part3 requires 1 unit of Part2'),(3,2,19,1,'Part3 requires 1 unit of Part2'),(3,2,20,1,'Part3 requires 1 unit of Part2'),(3,2,21,1,'Part3 requires 1 unit of Part2'),(3,2,22,1,'Part3 requires 1 unit of Part2'),(3,2,23,1,'Part3 requires 1 unit of Part2'),(3,2,24,1,'Part3 requires 1 unit of Part2'),(3,2,25,1,'Part3 requires 1 unit of Part2'),(3,2,26,1,'Part3 requires 1 unit of Part2'),(3,2,27,1,'Part3 requires 1 unit of Part2'),(3,2,28,1,'Part3 requires 1 unit of Part2'),(3,2,29,1,'Part3 requires 1 unit of Part2'),(3,2,30,1,'Part3 requires 1 unit of Part2'),(3,2,31,1,'Part3 requires 1 unit of Part2'),(3,2,32,1,'Part3 requires 1 unit of Part2'),(3,2,33,1,'Part3 requires 1 unit of Part2'),(3,2,34,1,'Part3 requires 1 unit of Part2'),(3,2,35,1,'Part3 requires 1 unit of Part2'),(3,2,36,1,'Part3 requires 1 unit of Part2'),(3,2,37,1,'Part3 requires 1 unit of Part2'),(3,2,38,1,'Part3 requires 1 unit of Part2'),(3,2,39,1,'Part3 requires 1 unit of Part2'),(3,2,40,1,'Part3 requires 1 unit of Part2'),(3,2,41,1,'Part3 requires 1 unit of Part2');
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
INSERT INTO `t_bucket` VALUES (1,1,0,80,'EACH',0,1,'Input',0,0,'Activate'),(1,1,6,80,'EACH',0,1,'Input',10,0,NULL),(1,1,7,80,'EACH',0,1,'Input',0,0,NULL),(1,1,8,80,'EACH',0,1,'Input',0,0,NULL),(1,1,9,80,'EACH',0,1,'Input',0,0,NULL),(1,1,10,80,'EACH',0,1,'Input',0,0,NULL),(1,1,11,80,'EACH',0,1,'Input',0,0,NULL),(1,1,12,80,'EACH',0,1,'Input',0,0,NULL),(1,1,13,80,'EACH',0,1,'Input',0,0,NULL),(1,1,14,80,'EACH',0,1,'Input',0,0,NULL),(1,1,15,80,'EACH',0,1,'Input',0,0,NULL),(1,1,16,80,'EACH',0,1,'Input',0,0,NULL),(1,1,17,80,'EACH',0,1,'Input',0,0,NULL),(1,1,18,80,'EACH',4,1,'Input',0,0,NULL),(1,1,19,80,'EACH',0,1,'Input',0,0,NULL),(1,1,20,80,'EACH',0,1,'Input',0,0,NULL),(1,1,21,80,'EACH',0,1,'Input',0,0,NULL),(1,1,22,80,'EACH',0,1,'Input',0,0,NULL),(1,1,23,80,'EACH',0,1,'Input',0,0,NULL),(1,1,24,80,'EACH',0,1,'Input',0,0,NULL),(1,1,25,80,'EACH',0,1,'Input',0,0,NULL),(1,1,26,80,'EACH',0,1,'Input',0,0,NULL),(1,1,27,80,'EACH',0,1,'Input',0,0,NULL),(1,1,28,80,'EACH',6,1,'Input',0,0,NULL),(1,1,29,80,'EACH',0,1,'Input',10,0,NULL),(1,1,30,80,'EACH',0,1,'Input',0,0,NULL),(1,1,31,80,'EACH',0,1,'Input',0,0,NULL),(1,1,32,80,'EACH',4,1,'Input',10,0,NULL),(1,1,33,80,'EACH',0,1,'Input',10,0,NULL),(1,1,34,80,'EACH',0,1,'Input',10,0,NULL),(1,1,35,80,'EACH',0,1,'Input',10,0,NULL),(1,1,36,80,'EACH',2,1,'Input',0,0,NULL),(1,1,37,80,'EACH',0,1,'Input',0,0,NULL),(1,1,38,80,'EACH',0,1,'Input',0,0,NULL),(1,1,39,80,'EACH',0,1,'Input',0,0,NULL),(1,1,40,80,'EACH',0,1,'Input',0,0,NULL),(1,1,41,80,'EACH',0,1,'Input',10,0,NULL),(1,2,0,200,'EACH',0,1,'Output',0,0,'Activate'),(1,2,6,200,'EACH',90,1,'Output',0,0,NULL),(1,2,7,200,'EACH',100,1,'Output',0,0,NULL),(1,2,8,200,'EACH',100,1,'Output',0,0,NULL),(1,2,9,200,'EACH',100,1,'Output',0,0,NULL),(1,2,10,200,'EACH',100,1,'Output',0,0,NULL),(1,2,11,200,'EACH',100,1,'Output',0,0,NULL),(1,2,12,200,'EACH',100,1,'Output',0,0,NULL),(1,2,13,200,'EACH',100,1,'Output',0,0,NULL),(1,2,14,200,'EACH',100,1,'Output',0,0,NULL),(1,2,15,200,'EACH',100,1,'Output',0,0,NULL),(1,2,16,200,'EACH',100,1,'Output',0,0,NULL),(1,2,17,200,'EACH',100,1,'Output',0,0,NULL),(1,2,18,200,'EACH',60,1,'Output',0,0,NULL),(1,2,19,200,'EACH',100,1,'Output',0,0,NULL),(1,2,20,200,'EACH',0,1,'Output',100,0,NULL),(1,2,21,200,'EACH',0,1,'Output',100,0,NULL),(1,2,22,200,'EACH',0,1,'Output',100,0,NULL),(1,2,23,200,'EACH',0,1,'Output',100,0,NULL),(1,2,24,200,'EACH',0,1,'Output',100,0,NULL),(1,2,25,200,'EACH',100,1,'Output',0,0,NULL),(1,2,26,200,'EACH',100,1,'Output',0,0,NULL),(1,2,27,200,'EACH',100,1,'Output',0,0,NULL),(1,2,28,200,'EACH',10,1,'Output',0,0,NULL),(1,2,29,200,'EACH',90,1,'Output',0,0,NULL),(1,2,30,200,'EACH',100,1,'Output',0,0,NULL),(1,2,31,200,'EACH',100,1,'Output',0,0,NULL),(1,2,32,200,'EACH',70,1,'Output',0,0,NULL),(1,2,33,200,'EACH',90,1,'Output',0,0,NULL),(1,2,34,200,'EACH',90,1,'Output',0,0,NULL),(1,2,35,200,'EACH',90,1,'Output',0,0,NULL),(1,2,36,200,'EACH',90,1,'Output',0,0,NULL),(1,2,37,200,'EACH',100,1,'Output',0,0,NULL),(1,2,38,200,'EACH',100,1,'Output',0,0,NULL),(1,2,39,200,'EACH',100,1,'Output',0,0,NULL),(1,2,40,200,'EACH',100,1,'Output',0,0,NULL),(1,2,41,200,'EACH',90,1,'Output',0,0,NULL),(1,3,0,500,'EACH',0,1,'Both',0,0,'Activate'),(1,3,6,500,'EACH',0,1,'Both',0,0,NULL),(1,3,7,500,'EACH',0,1,'Both',0,0,NULL),(1,3,8,500,'EACH',0,1,'Both',0,0,NULL),(1,3,9,500,'EACH',0,1,'Both',0,0,NULL),(1,3,10,500,'EACH',0,1,'Both',0,0,NULL),(1,3,11,500,'EACH',0,1,'Both',0,0,NULL),(1,3,12,500,'EACH',0,1,'Both',0,0,NULL),(1,3,13,500,'EACH',0,1,'Both',0,0,NULL),(1,3,14,500,'EACH',0,1,'Both',0,0,NULL),(1,3,15,500,'EACH',0,1,'Both',0,0,NULL),(1,3,16,500,'EACH',0,1,'Both',0,0,NULL),(1,3,17,500,'EACH',0,1,'Both',0,0,NULL),(1,3,18,500,'EACH',0,1,'Both',0,0,NULL),(1,3,19,500,'EACH',0,1,'Both',0,0,NULL),(1,3,20,500,'EACH',0,1,'Both',0,0,NULL),(1,3,21,500,'EACH',0,1,'Both',0,0,NULL),(1,3,22,500,'EACH',0,1,'Both',0,0,NULL),(1,3,23,500,'EACH',0,1,'Both',0,0,NULL),(1,3,24,500,'EACH',0,1,'Both',0,0,NULL),(1,3,25,500,'EACH',0,1,'Both',0,0,NULL),(1,3,26,500,'EACH',0,1,'Both',0,0,NULL),(1,3,27,500,'EACH',0,1,'Both',0,0,NULL),(1,3,28,500,'EACH',0,1,'Both',0,0,NULL),(1,3,29,500,'EACH',0,1,'Both',0,0,NULL),(1,3,30,500,'EACH',0,1,'Both',0,0,NULL),(1,3,31,500,'EACH',0,1,'Both',0,0,NULL),(1,3,32,500,'EACH',0,1,'Both',0,0,NULL),(1,3,33,500,'EACH',0,1,'Both',0,0,NULL),(1,3,34,500,'EACH',0,1,'Both',0,0,NULL),(1,3,35,500,'EACH',0,1,'Both',0,0,NULL),(1,3,36,500,'EACH',0,1,'Both',0,0,NULL),(1,3,37,500,'EACH',0,1,'Both',0,0,NULL),(1,3,38,500,'EACH',0,1,'Both',0,0,NULL),(1,3,39,500,'EACH',0,1,'Both',0,0,NULL),(1,3,40,500,'EACH',0,1,'Both',0,0,NULL),(1,3,41,500,'EACH',0,1,'Both',0,0,NULL),(1,4,0,100,'EACH',0,1,'Input',0,0,'Activate'),(1,4,6,100,'EACH',0,1,'Input',0,0,NULL),(1,4,7,100,'EACH',0,1,'Input',0,0,NULL),(1,4,8,100,'EACH',0,1,'Input',0,0,NULL),(1,4,9,100,'EACH',0,1,'Input',0,0,NULL),(1,4,10,100,'EACH',0,1,'Input',0,0,NULL),(1,4,11,100,'EACH',0,1,'Input',0,0,NULL),(1,4,12,100,'EACH',0,1,'Input',0,0,NULL),(1,4,13,100,'EACH',0,1,'Input',0,0,NULL),(1,4,14,100,'EACH',0,1,'Input',0,0,NULL),(1,4,15,100,'EACH',0,1,'Input',0,0,NULL),(1,4,16,100,'EACH',0,1,'Input',0,0,NULL),(1,4,17,100,'EACH',0,1,'Input',0,0,NULL),(1,4,18,100,'EACH',0,1,'Input',0,0,NULL),(1,4,19,100,'EACH',0,1,'Input',0,0,NULL),(1,4,20,100,'EACH',0,1,'Input',0,0,NULL),(1,4,21,100,'EACH',0,1,'Input',0,0,NULL),(1,4,22,100,'EACH',0,1,'Input',0,0,NULL),(1,4,23,100,'EACH',0,1,'Input',0,0,NULL),(1,4,24,100,'EACH',0,1,'Input',0,0,NULL),(1,4,25,100,'EACH',0,1,'Input',0,0,NULL),(1,4,26,100,'EACH',0,1,'Input',0,0,NULL),(1,4,27,100,'EACH',0,1,'Input',0,0,NULL),(1,4,28,100,'EACH',0,1,'Input',0,0,NULL),(1,4,29,100,'EACH',0,1,'Input',0,0,NULL),(1,4,30,100,'EACH',0,1,'Input',0,0,NULL),(1,4,31,100,'EACH',0,1,'Input',0,0,NULL),(1,4,32,100,'EACH',0,1,'Input',0,0,NULL),(1,4,33,100,'EACH',0,1,'Input',0,0,NULL),(1,4,34,100,'EACH',0,1,'Input',0,0,NULL),(1,4,35,100,'EACH',0,1,'Input',0,0,NULL),(1,4,36,100,'EACH',0,1,'Input',0,0,NULL),(1,4,37,100,'EACH',0,1,'Input',0,0,NULL),(1,4,38,100,'EACH',0,1,'Input',0,0,NULL),(1,4,39,100,'EACH',0,1,'Input',0,0,NULL),(1,4,40,100,'EACH',0,1,'Input',0,0,NULL),(1,4,41,100,'EACH',0,1,'Input',0,0,NULL),(1,7,0,100,'EACH',0,1,'Both',0,0,'Activate'),(2,1,0,80,'EACH',0,2,'Input',0,0,'Activate'),(2,1,6,80,'EACH',10,2,'Input',0,0,NULL),(2,1,7,80,'EACH',0,2,'Input',0,0,NULL),(2,1,8,80,'EACH',0,2,'Input',0,0,NULL),(2,1,9,80,'EACH',0,2,'Input',10,0,NULL),(2,1,10,80,'EACH',0,2,'Input',0,0,NULL),(2,1,11,80,'EACH',0,2,'Input',10,0,NULL),(2,1,12,80,'EACH',0,2,'Input',10,0,NULL),(2,1,13,80,'EACH',0,2,'Input',10,0,NULL),(2,1,14,80,'EACH',0,2,'Input',0,0,NULL),(2,1,15,80,'EACH',0,2,'Input',0,0,NULL),(2,1,16,80,'EACH',0,2,'Input',10,0,NULL),(2,1,17,80,'EACH',0,2,'Input',0,0,NULL),(2,1,18,80,'EACH',32,2,'Input',0,0,NULL),(2,1,19,80,'EACH',0,2,'Input',0,0,NULL),(2,1,20,80,'EACH',0,2,'Input',0,0,NULL),(2,1,21,80,'EACH',0,2,'Input',0,0,NULL),(2,1,22,80,'EACH',0,2,'Input',0,0,NULL),(2,1,23,80,'EACH',0,2,'Input',0,0,NULL),(2,1,24,80,'EACH',0,2,'Input',0,0,NULL),(2,1,25,80,'EACH',0,2,'Input',0,0,NULL),(2,1,26,80,'EACH',0,2,'Input',0,0,NULL),(2,1,27,80,'EACH',0,2,'Input',0,0,NULL),(2,1,28,80,'EACH',8,2,'Input',0,0,NULL),(2,1,29,80,'EACH',10,2,'Input',0,0,NULL),(2,1,30,80,'EACH',0,2,'Input',0,0,NULL),(2,1,31,80,'EACH',0,2,'Input',10,0,NULL),(2,1,32,80,'EACH',12,2,'Input',10,0,NULL),(2,1,33,80,'EACH',10,2,'Input',0,0,NULL),(2,1,34,80,'EACH',10,2,'Input',10,0,NULL),(2,1,35,80,'EACH',10,2,'Input',0,0,NULL),(2,1,36,80,'EACH',16,2,'Input',0,0,NULL),(2,1,37,80,'EACH',0,2,'Input',0,0,NULL),(2,1,38,80,'EACH',0,2,'Input',0,0,NULL),(2,1,39,80,'EACH',0,2,'Input',0,0,NULL),(2,1,40,80,'EACH',0,2,'Input',0,0,NULL),(2,1,41,80,'EACH',0,2,'Input',10,0,NULL),(2,2,0,100,'EACH',0,2,'Output',0,0,'Activate'),(2,2,6,100,'EACH',40,2,'Output',0,0,NULL),(2,2,7,100,'EACH',50,2,'Output',0,0,NULL),(2,2,8,100,'EACH',50,2,'Output',0,0,NULL),(2,2,9,100,'EACH',40,2,'Output',0,0,NULL),(2,2,10,100,'EACH',50,2,'Output',0,0,NULL),(2,2,11,100,'EACH',40,2,'Output',0,0,NULL),(2,2,12,100,'EACH',40,2,'Output',0,0,NULL),(2,2,13,100,'EACH',40,2,'Output',0,0,NULL),(2,2,14,100,'EACH',50,2,'Output',0,0,NULL),(2,2,15,100,'EACH',50,2,'Output',0,0,NULL),(2,2,16,100,'EACH',40,2,'Output',0,0,NULL),(2,2,17,100,'EACH',50,2,'Output',0,0,NULL),(2,2,18,100,'EACH',0,2,'Output',0,0,NULL),(2,2,19,100,'EACH',50,2,'Output',0,0,NULL),(2,2,20,100,'EACH',0,2,'Output',50,0,NULL),(2,2,21,100,'EACH',0,2,'Output',50,0,NULL),(2,2,22,100,'EACH',0,2,'Output',50,0,NULL),(2,2,23,100,'EACH',0,2,'Output',50,0,NULL),(2,2,24,100,'EACH',0,2,'Output',50,0,NULL),(2,2,25,100,'EACH',50,2,'Output',0,0,NULL),(2,2,26,100,'EACH',50,2,'Output',0,0,NULL),(2,2,27,100,'EACH',50,2,'Output',0,0,NULL),(2,2,28,100,'EACH',50,2,'Output',0,0,NULL),(2,2,29,100,'EACH',40,2,'Output',0,0,NULL),(2,2,30,100,'EACH',50,2,'Output',0,0,NULL),(2,2,31,100,'EACH',40,2,'Output',0,0,NULL),(2,2,32,100,'EACH',20,2,'Output',0,0,NULL),(2,2,33,100,'EACH',40,2,'Output',0,0,NULL),(2,2,34,100,'EACH',30,2,'Output',0,0,NULL),(2,2,35,100,'EACH',40,2,'Output',0,0,NULL),(2,2,36,100,'EACH',30,2,'Output',0,0,NULL),(2,2,37,100,'EACH',50,2,'Output',0,0,NULL),(2,2,38,100,'EACH',50,2,'Output',0,0,NULL),(2,2,39,100,'EACH',50,2,'Output',0,0,NULL),(2,2,40,100,'EACH',50,2,'Output',0,0,NULL),(2,2,41,100,'EACH',40,2,'Output',0,0,NULL),(2,3,0,250,'EACH',0,2,'Both',0,0,'Activate'),(2,3,6,250,'EACH',0,2,'Both',0,0,NULL),(2,3,7,250,'EACH',0,2,'Both',0,0,NULL),(2,3,8,250,'EACH',0,2,'Both',0,0,NULL),(2,3,9,250,'EACH',0,2,'Both',0,0,NULL),(2,3,10,250,'EACH',0,2,'Both',0,0,NULL),(2,3,11,250,'EACH',0,2,'Both',0,0,NULL),(2,3,12,250,'EACH',0,2,'Both',0,0,NULL),(2,3,13,250,'EACH',0,2,'Both',0,0,NULL),(2,3,14,250,'EACH',0,2,'Both',0,0,NULL),(2,3,15,250,'EACH',0,2,'Both',0,0,NULL),(2,3,16,250,'EACH',0,2,'Both',0,0,NULL),(2,3,17,250,'EACH',0,2,'Both',0,0,NULL),(2,3,18,250,'EACH',0,2,'Both',0,0,NULL),(2,3,19,250,'EACH',0,2,'Both',0,0,NULL),(2,3,20,250,'EACH',0,2,'Both',0,0,NULL),(2,3,21,250,'EACH',0,2,'Both',0,0,NULL),(2,3,22,250,'EACH',0,2,'Both',0,0,NULL),(2,3,23,250,'EACH',0,2,'Both',0,0,NULL),(2,3,24,250,'EACH',0,2,'Both',0,0,NULL),(2,3,25,250,'EACH',0,2,'Both',0,0,NULL),(2,3,26,250,'EACH',0,2,'Both',0,0,NULL),(2,3,27,250,'EACH',0,2,'Both',0,0,NULL),(2,3,28,250,'EACH',0,2,'Both',0,0,NULL),(2,3,29,250,'EACH',0,2,'Both',0,0,NULL),(2,3,30,250,'EACH',0,2,'Both',0,0,NULL),(2,3,31,250,'EACH',0,2,'Both',0,0,NULL),(2,3,32,250,'EACH',0,2,'Both',0,0,NULL),(2,3,33,250,'EACH',0,2,'Both',0,0,NULL),(2,3,34,250,'EACH',0,2,'Both',0,0,NULL),(2,3,35,250,'EACH',0,2,'Both',0,0,NULL),(2,3,36,250,'EACH',0,2,'Both',0,0,NULL),(2,3,37,250,'EACH',0,2,'Both',0,0,NULL),(2,3,38,250,'EACH',0,2,'Both',0,0,NULL),(2,3,39,250,'EACH',0,2,'Both',0,0,NULL),(2,3,40,250,'EACH',0,2,'Both',0,0,NULL),(2,3,41,250,'EACH',0,2,'Both',0,0,NULL),(2,4,0,100,'EACH',0,2,'Input',0,0,'Activate'),(2,4,6,100,'EACH',0,2,'Input',0,0,NULL),(2,4,7,100,'EACH',0,2,'Input',0,0,NULL),(2,4,8,100,'EACH',0,2,'Input',0,0,NULL),(2,4,9,100,'EACH',0,2,'Input',0,0,NULL),(2,4,10,100,'EACH',0,2,'Input',0,0,NULL),(2,4,11,100,'EACH',0,2,'Input',0,0,NULL),(2,4,12,100,'EACH',0,2,'Input',0,0,NULL),(2,4,13,100,'EACH',0,2,'Input',0,0,NULL),(2,4,14,100,'EACH',0,2,'Input',0,0,NULL),(2,4,15,100,'EACH',0,2,'Input',0,0,NULL),(2,4,16,100,'EACH',0,2,'Input',0,0,NULL),(2,4,17,100,'EACH',0,2,'Input',0,0,NULL),(2,4,18,100,'EACH',0,2,'Input',0,0,NULL),(2,4,19,100,'EACH',0,2,'Input',0,0,NULL),(2,4,20,100,'EACH',0,2,'Input',0,0,NULL),(2,4,21,100,'EACH',0,2,'Input',0,0,NULL),(2,4,22,100,'EACH',0,2,'Input',0,0,NULL),(2,4,23,100,'EACH',0,2,'Input',0,0,NULL),(2,4,24,100,'EACH',0,2,'Input',0,0,NULL),(2,4,25,100,'EACH',0,2,'Input',0,0,NULL),(2,4,26,100,'EACH',0,2,'Input',0,0,NULL),(2,4,27,100,'EACH',0,2,'Input',0,0,NULL),(2,4,28,100,'EACH',0,2,'Input',0,0,NULL),(2,4,29,100,'EACH',0,2,'Input',0,0,NULL),(2,4,30,100,'EACH',0,2,'Input',0,0,NULL),(2,4,31,100,'EACH',0,2,'Input',0,0,NULL),(2,4,32,100,'EACH',0,2,'Input',0,0,NULL),(2,4,33,100,'EACH',0,2,'Input',0,0,NULL),(2,4,34,100,'EACH',0,2,'Input',0,0,NULL),(2,4,35,100,'EACH',0,2,'Input',0,0,NULL),(2,4,36,100,'EACH',0,2,'Input',0,0,NULL),(2,4,37,100,'EACH',0,2,'Input',0,0,NULL),(2,4,38,100,'EACH',0,2,'Input',0,0,NULL),(2,4,39,100,'EACH',0,2,'Input',0,0,NULL),(2,4,40,100,'EACH',0,2,'Input',0,0,NULL),(2,4,41,100,'EACH',0,2,'Input',0,0,NULL),(2,7,0,100,'EACH',0,2,'Both',0,0,'Activate'),(3,1,0,25,'EACH',0,3,'Output',0,0,'Activate'),(3,1,6,25,'EACH',0,3,'Output',0,0,NULL),(3,1,7,25,'EACH',0,3,'Output',0,0,NULL),(3,1,8,25,'EACH',0,3,'Output',0,0,NULL),(3,1,9,25,'EACH',0,3,'Output',0,0,NULL),(3,1,10,25,'EACH',0,3,'Output',0,0,NULL),(3,1,11,25,'EACH',0,3,'Output',0,0,NULL),(3,1,12,25,'EACH',0,3,'Output',0,0,NULL),(3,1,13,25,'EACH',0,3,'Output',0,0,NULL),(3,1,14,25,'EACH',0,3,'Output',0,0,NULL),(3,1,15,25,'EACH',0,3,'Output',0,0,NULL),(3,1,16,25,'EACH',0,3,'Output',0,0,NULL),(3,1,17,25,'EACH',0,3,'Output',0,0,NULL),(3,1,18,25,'EACH',8,3,'Output',0,0,NULL),(3,1,19,25,'EACH',0,3,'Output',0,0,NULL),(3,1,20,25,'EACH',0,3,'Output',0,0,NULL),(3,1,21,25,'EACH',0,3,'Output',0,0,NULL),(3,1,22,25,'EACH',0,3,'Output',0,0,NULL),(3,1,23,25,'EACH',0,3,'Output',0,0,NULL),(3,1,24,25,'EACH',0,3,'Output',0,0,NULL),(3,1,25,25,'EACH',0,3,'Output',0,0,NULL),(3,1,26,25,'EACH',0,3,'Output',0,0,NULL),(3,1,27,25,'EACH',0,3,'Output',0,0,NULL),(3,1,28,25,'EACH',20,3,'Output',2,0,NULL),(3,1,29,25,'EACH',0,3,'Output',0,0,NULL),(3,1,30,25,'EACH',0,3,'Output',0,0,NULL),(3,1,31,25,'EACH',0,3,'Output',0,0,NULL),(3,1,32,25,'EACH',8,3,'Output',0,0,NULL),(3,1,33,25,'EACH',0,3,'Output',0,0,NULL),(3,1,34,25,'EACH',0,3,'Output',0,0,NULL),(3,1,35,25,'EACH',0,3,'Output',0,0,NULL),(3,1,36,25,'EACH',4,3,'Output',0,0,NULL),(3,1,37,25,'EACH',0,3,'Output',0,0,NULL),(3,1,38,25,'EACH',0,3,'Output',0,0,NULL),(3,1,39,25,'EACH',0,3,'Output',0,0,NULL),(3,1,40,25,'EACH',0,3,'Output',0,0,NULL),(3,1,41,25,'EACH',0,3,'Output',0,0,NULL),(3,3,0,125,'EACH',0,3,'Both',0,0,'Activate'),(3,3,6,125,'EACH',0,3,'Both',0,0,NULL),(3,3,7,125,'EACH',0,3,'Both',0,0,NULL),(3,3,8,125,'EACH',0,3,'Both',0,0,NULL),(3,3,9,125,'EACH',0,3,'Both',0,0,NULL),(3,3,10,125,'EACH',0,3,'Both',0,0,NULL),(3,3,11,125,'EACH',0,3,'Both',0,0,NULL),(3,3,12,125,'EACH',0,3,'Both',0,0,NULL),(3,3,13,125,'EACH',0,3,'Both',0,0,NULL),(3,3,14,125,'EACH',0,3,'Both',0,0,NULL),(3,3,15,125,'EACH',0,3,'Both',0,0,NULL),(3,3,16,125,'EACH',0,3,'Both',0,0,NULL),(3,3,17,125,'EACH',0,3,'Both',0,0,NULL),(3,3,18,125,'EACH',0,3,'Both',0,0,NULL),(3,3,19,125,'EACH',0,3,'Both',0,0,NULL),(3,3,20,125,'EACH',0,3,'Both',0,0,NULL),(3,3,21,125,'EACH',0,3,'Both',0,0,NULL),(3,3,22,125,'EACH',0,3,'Both',0,0,NULL),(3,3,23,125,'EACH',0,3,'Both',0,0,NULL),(3,3,24,125,'EACH',0,3,'Both',0,0,NULL),(3,3,25,125,'EACH',0,3,'Both',0,0,NULL),(3,3,26,125,'EACH',0,3,'Both',0,0,NULL),(3,3,27,125,'EACH',0,3,'Both',0,0,NULL),(3,3,28,125,'EACH',20,3,'Both',0,0,NULL),(3,3,29,125,'EACH',0,3,'Both',0,0,NULL),(3,3,30,125,'EACH',0,3,'Both',0,0,NULL),(3,3,31,125,'EACH',0,3,'Both',0,0,NULL),(3,3,32,125,'EACH',0,3,'Both',0,0,NULL),(3,3,33,125,'EACH',0,3,'Both',0,0,NULL),(3,3,34,125,'EACH',0,3,'Both',0,0,NULL),(3,3,35,125,'EACH',0,3,'Both',0,0,NULL),(3,3,36,125,'EACH',0,3,'Both',0,0,NULL),(3,3,37,125,'EACH',0,3,'Both',0,0,NULL),(3,3,38,125,'EACH',0,3,'Both',0,0,NULL),(3,3,39,125,'EACH',0,3,'Both',0,0,NULL),(3,3,40,125,'EACH',0,3,'Both',0,0,NULL),(3,3,41,125,'EACH',0,3,'Both',0,0,NULL),(3,4,0,100,'EACH',0,3,'Input',0,0,'Activate'),(3,4,6,100,'EACH',0,3,'Input',0,0,NULL),(3,4,7,100,'EACH',0,3,'Input',0,0,NULL),(3,4,8,100,'EACH',0,3,'Input',0,0,NULL),(3,4,9,100,'EACH',0,3,'Input',0,0,NULL),(3,4,10,100,'EACH',0,3,'Input',0,0,NULL),(3,4,11,100,'EACH',0,3,'Input',0,0,NULL),(3,4,12,100,'EACH',0,3,'Input',0,0,NULL),(3,4,13,100,'EACH',0,3,'Input',0,0,NULL),(3,4,14,100,'EACH',0,3,'Input',0,0,NULL),(3,4,15,100,'EACH',0,3,'Input',0,0,NULL),(3,4,16,100,'EACH',0,3,'Input',0,0,NULL),(3,4,17,100,'EACH',0,3,'Input',0,0,NULL),(3,4,18,100,'EACH',30,3,'Input',30,0,NULL),(3,4,19,100,'EACH',0,3,'Input',0,0,NULL),(3,4,20,100,'EACH',0,3,'Input',0,0,NULL),(3,4,21,100,'EACH',0,3,'Input',0,0,NULL),(3,4,22,100,'EACH',0,3,'Input',0,0,NULL),(3,4,23,100,'EACH',0,3,'Input',0,0,NULL),(3,4,24,100,'EACH',0,3,'Input',0,0,NULL),(3,4,25,100,'EACH',0,3,'Input',0,0,NULL),(3,4,26,100,'EACH',0,3,'Input',0,0,NULL),(3,4,27,100,'EACH',0,3,'Input',0,0,NULL),(3,4,28,100,'EACH',0,3,'Input',0,0,NULL),(3,4,29,100,'EACH',0,3,'Input',0,0,NULL),(3,4,30,100,'EACH',0,3,'Input',0,0,NULL),(3,4,31,100,'EACH',0,3,'Input',0,0,NULL),(3,4,32,100,'EACH',0,3,'Input',0,0,NULL),(3,4,33,100,'EACH',0,3,'Input',0,0,NULL),(3,4,34,100,'EACH',0,3,'Input',0,0,NULL),(3,4,35,100,'EACH',0,3,'Input',0,0,NULL),(3,4,36,100,'EACH',0,3,'Input',0,0,NULL),(3,4,37,100,'EACH',0,3,'Input',0,0,NULL),(3,4,38,100,'EACH',0,3,'Input',0,0,NULL),(3,4,39,100,'EACH',0,3,'Input',0,0,NULL),(3,4,40,100,'EACH',0,3,'Input',0,0,NULL),(3,4,41,100,'EACH',0,3,'Input',0,0,NULL);
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
  `quality` decimal(5,2) DEFAULT NULL,
  `production_distn` varchar(10) DEFAULT NULL,
  `production_parameter1` decimal(5,2) DEFAULT NULL,
  `production_parameter2` decimal(5,2) DEFAULT NULL,
  `price_function1_limit` int(11) DEFAULT NULL,
  `price_function1_charge` decimal(5,2) DEFAULT NULL,
  `price_function2_limit` int(11) DEFAULT NULL,
  `price_function2_charge` decimal(5,2) DEFAULT NULL,
  `price_function3_limit` int(11) DEFAULT NULL,
  `price_function3_charge` decimal(5,2) DEFAULT NULL,
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
INSERT INTO `t_catalog` VALUES (1,1,0,0.00,'',8.00,0.00,10,0.15,50,0.12,100,0.10),(1,1,6,0.00,'',8.00,0.00,10,0.15,50,0.12,100,0.10),(1,1,7,0.00,'',8.00,0.00,10,0.15,50,0.12,100,0.10),(1,1,8,0.00,'',8.00,0.00,10,0.15,50,0.12,100,0.10),(1,1,9,0.00,'',8.00,0.00,10,0.15,50,0.12,100,0.10),(1,1,10,0.00,'',8.00,0.00,10,0.15,50,0.12,100,0.10),(1,1,11,0.00,'',8.00,0.00,10,0.15,50,0.12,100,0.10),(1,1,12,0.00,'',8.00,0.00,10,0.15,50,0.12,100,0.10),(1,1,13,0.00,'',8.00,0.00,10,0.15,50,0.12,100,0.10),(1,1,14,0.00,'',8.00,0.00,10,0.15,50,0.12,100,0.10),(1,1,15,0.00,'',8.00,0.00,10,0.15,50,0.12,100,0.10),(1,1,16,0.00,'',8.00,0.00,10,0.15,50,0.12,100,0.10),(1,1,17,0.00,'',8.00,0.00,10,0.15,50,0.12,100,0.10),(1,1,18,0.00,'',8.00,0.00,10,0.15,50,0.12,100,0.10),(1,1,19,0.00,'',8.00,0.00,10,0.15,50,0.12,100,0.10),(1,1,20,0.00,'',8.00,0.00,10,0.15,50,0.12,100,0.10),(1,1,21,0.00,'',8.00,0.00,10,0.15,50,0.12,100,0.10),(1,1,22,0.00,'',8.00,0.00,10,0.15,50,0.12,100,0.10),(1,1,23,0.00,'',8.00,0.00,10,0.15,50,0.12,100,0.10),(1,1,24,0.00,'',8.00,0.00,10,0.15,50,0.12,100,0.10),(1,1,25,0.00,'',8.00,0.00,10,0.15,50,0.12,100,0.10),(1,1,26,0.00,'',8.00,0.00,10,0.15,50,0.12,100,0.10),(1,1,27,0.00,'',8.00,0.00,10,0.15,50,0.12,100,0.10),(1,1,28,0.00,'',8.00,0.00,10,0.15,50,0.12,100,0.10),(1,1,29,0.00,'',8.00,0.00,10,0.15,50,0.12,100,0.10),(1,1,30,0.00,'',8.00,0.00,10,0.15,50,0.12,100,0.10),(1,1,31,0.00,'',8.00,0.00,10,0.15,50,0.12,100,0.10),(1,1,32,0.00,'',8.00,0.00,10,0.15,50,0.12,100,0.10),(1,1,33,0.00,'',8.00,0.00,10,0.15,50,0.12,100,0.10),(1,1,34,0.00,'',8.00,0.00,10,0.15,50,0.12,100,0.10),(1,1,35,0.00,'',8.00,0.00,10,0.15,50,0.12,100,0.10),(1,1,36,0.00,'',8.00,0.00,10,0.15,50,0.12,100,0.10),(1,1,37,0.00,'',8.00,0.00,10,0.15,50,0.12,100,0.10),(1,1,38,0.00,'',8.00,0.00,10,0.15,50,0.12,100,0.10),(1,1,39,0.00,'',8.00,0.00,10,0.15,50,0.12,100,0.10),(1,1,40,0.00,'',8.00,0.00,10,0.15,50,0.12,100,0.10),(1,1,41,0.00,'',8.00,0.00,10,0.15,50,0.12,100,0.10),(1,2,0,0.00,'',8.00,0.00,10,0.30,25,0.25,50,0.20),(1,2,6,0.00,'',8.00,0.00,10,0.30,25,0.25,50,0.20),(1,2,7,0.00,'',8.00,0.00,10,0.30,25,0.25,50,0.20),(1,2,8,0.00,'',8.00,0.00,10,0.30,25,0.25,50,0.20),(1,2,9,0.00,'',8.00,0.00,10,0.30,25,0.25,50,0.20),(1,2,10,0.00,'',8.00,0.00,10,0.30,25,0.25,50,0.20),(1,2,11,0.00,'',8.00,0.00,10,0.30,25,0.25,50,0.20),(1,2,12,0.00,'',8.00,0.00,10,0.30,25,0.25,50,0.20),(1,2,13,0.00,'',8.00,0.00,10,0.30,25,0.25,50,0.20),(1,2,14,0.00,'',8.00,0.00,10,0.30,25,0.25,50,0.20),(1,2,15,0.00,'',8.00,0.00,10,0.30,25,0.25,50,0.20),(1,2,16,0.00,'',8.00,0.00,10,0.30,25,0.25,50,0.20),(1,2,17,0.00,'',8.00,0.00,10,0.30,25,0.25,50,0.20),(1,2,18,0.00,'',8.00,0.00,10,0.30,25,0.25,50,0.20),(1,2,19,0.00,'',8.00,0.00,10,0.30,25,0.25,50,0.20),(1,2,20,0.00,'',8.00,0.00,10,0.30,25,0.25,50,0.20),(1,2,21,0.00,'',8.00,0.00,10,0.30,25,0.25,50,0.20),(1,2,22,0.00,'',8.00,0.00,10,0.30,25,0.25,50,0.20),(1,2,23,0.00,'',8.00,0.00,10,0.30,25,0.25,50,0.20),(1,2,24,0.00,'',8.00,0.00,10,0.30,25,0.25,50,0.20),(1,2,25,0.00,'',8.00,0.00,10,0.30,25,0.25,50,0.20),(1,2,26,0.00,'',8.00,0.00,10,0.30,25,0.25,50,0.20),(1,2,27,0.00,'',8.00,0.00,10,0.30,25,0.25,50,0.20),(1,2,28,0.00,'',8.00,0.00,10,0.30,25,0.25,50,0.20),(1,2,29,0.00,'',8.00,0.00,10,0.30,25,0.25,50,0.20),(1,2,30,0.00,'',8.00,0.00,10,0.30,25,0.25,50,0.20),(1,2,31,0.00,'',8.00,0.00,10,0.30,25,0.25,50,0.20),(1,2,32,0.00,'',8.00,0.00,10,0.30,25,0.25,50,0.20),(1,2,33,0.00,'',8.00,0.00,10,0.30,25,0.25,50,0.20),(1,2,34,0.00,'',8.00,0.00,10,0.30,25,0.25,50,0.20),(1,2,35,0.00,'',8.00,0.00,10,0.30,25,0.25,50,0.20),(1,2,36,0.00,'',8.00,0.00,10,0.30,25,0.25,50,0.20),(1,2,37,0.00,'',8.00,0.00,10,0.30,25,0.25,50,0.20),(1,2,38,0.00,'',8.00,0.00,10,0.30,25,0.25,50,0.20),(1,2,39,0.00,'',8.00,0.00,10,0.30,25,0.25,50,0.20),(1,2,40,0.00,'',8.00,0.00,10,0.30,25,0.25,50,0.20),(1,2,41,0.00,'',8.00,0.00,10,0.30,25,0.25,50,0.20);
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
INSERT INTO `t_event` VALUES (0,19,0,13,1),(0,20,0,7,1),(0,21,0,8,1),(0,22,0,3,1),(0,23,0,8,1),(0,24,0,3,1),(0,25,0,13,1),(0,26,0,8.00699999999998,1),(0,27,0,6.635000000000001,1),(0,28,0,190,4),(0,29,0,16.046999999999997,1),(0,30,0,7.296999999999996,1),(1,20,1,9,-1),(1,22,1,9,-1),(1,23,1,9,-1),(1,24,1,9,-1),(2,20,1,9,-1),(2,21,1,9,-1),(2,22,1,9,-1),(2,23,1,9,-1),(2,24,1,9,-1),(4,29,2,19.980333396911618,4),(4,32,2,39.05999994277954,3),(4,33,4,10.96666669845581,4),(4,34,4,11.71666669845581,4),(4,35,1,38.86666679382324,4),(4,38,1,41.07333872235818,4),(4,39,1,41.07333872235818,4),(5,31,2,15.160000085830688,3),(5,32,2,38.07499980926514,1),(5,34,4,11.080000042915344,3),(5,37,1,38.961486816071734,3),(5,38,1,38.961486816071734,3),(5,39,1,38.961486816071734,3);
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
  `percentage_to_win` decimal(5,2) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `game_status` varchar(45) DEFAULT NULL,
  `id_phase` int(11) DEFAULT NULL,
  `next_id_phase` int(11) DEFAULT NULL,
  `id_terrain` int(11) DEFAULT NULL,
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
INSERT INTO `t_game` VALUES (0,'2012-08-02 16:46:45',1,8176.70,0,0,0,0,0,1,'CPU','Easy','Testing the description of the first EASY game',10000,20.00,'Phase 1: 2purchases,1assembly,1ship','Unlocked',1,2,0),(0,'2012-08-02 18:02:30',3,8176.70,0,0,0,0,0,1,'USER','Easy','Testing the description of the first EASY game',10000,20.00,'Phase 1: 2purchases,1assembly,1ship','Unlocked',1,2,0),(0,'2012-08-14 14:51:51',4,8904.63,48,2,1,0,84.01700000000176,0.125,'USER','Easy','Testing the description of the first EASY game',10000,20.00,'Phase 1: 2purchases,1assembly,1ship','Completed',1,2,0),(1,'2012-06-04 04:00:38',1,480.00,41,0,1,0,30.455000000000293,1,'CPU','Normal','Testing the description of the first NORMAL game',1000,60.00,'Phase 2 : .........','Locked',2,3,0),(1,'2012-08-02 18:02:30',3,480.00,41,0,1,0,30.455000000000293,1,'USER','Normal','Testing the description of the first NORMAL game',1000,60.00,'Phase 2 : .........','Locked',2,3,0),(1,'2012-08-14 14:13:18',4,480.00,41,0,1,0,30.455000000000293,1,'USER','Normal','Testing the description of the first NORMAL game',1000,60.00,'Phase 2 : .........','Unlocked',2,3,0),(2,'2012-06-04 04:04:14',1,480.00,18,0,1,0,6.699999999999946,1,'CPU','Hard','Testing the description of the first HARD game',1000,60.00,'Phase 3: .............','Locked',3,0,0),(2,'2012-08-02 18:02:30',3,480.00,18,0,1,0,6.699999999999946,1,'USER','Hard','Testing the description of the first HARD game',1000,60.00,'Phase 3: .............','Locked',3,0,0),(2,'2012-08-14 14:13:18',4,480.00,18,0,1,0,6.699999999999946,1,'USER','Hard','Testing the description of the first HARD game',1000,60.00,'Phase 3: .............','Locked',3,0,0),(3,'2012-06-04 04:07:13',1,480.00,21,0,1,0,6.4489999999999625,1,'USER','Easy','testing the description of the first easy game',1000,60.00,'','Unlocked',0,0,0),(4,'2012-06-04 04:10:36',1,480.00,34,0,1,0,5.207999999999979,1,'USER','Easy','testing the description of the first easy game',1000,60.00,'','Unlocked',0,0,0),(5,'2012-06-04 04:13:49',1,480.00,57,0,1,0,8.517999999999954,1,'USER','Easy','testing the description of the first easy game',1000,60.00,'','Unlocked',0,0,0),(6,'2012-06-04 04:25:51',1,480.00,26,0,1,0,4.547999999999985,1,'USER','Easy','testing the description of the first easy game',1000,60.00,'','Unlocked',0,0,0),(7,'2012-06-04 04:32:44',1,480.00,18,0,1,0,4.810999999999979,1,'USER','Easy','testing the description of the first easy game',1000,60.00,'','Unlocked',0,0,0),(8,'2012-06-04 04:35:54',1,480.00,16,0,1,0,4.267999999999982,1,'USER','Easy','testing the description of the first easy game',1000,60.00,'','Unlocked',0,0,0),(9,'2012-06-04 04:37:40',1,480.00,16,0,1,0,5.0239999999999725,1,'USER','Easy','testing the description of the first easy game',1000,60.00,'','Unlocked',0,0,0),(10,'2012-06-04 04:44:11',1,480.00,14,0,1,0,6.403999999999944,1,'USER','Easy','testing the description of the first easy game',1000,60.00,'','Unlocked',0,0,0),(11,'2012-06-04 04:45:28',1,480.00,13,0,1,0,4.1859999999999795,1,'USER','Easy','testing the description of the first easy game',1000,60.00,'','Unlocked',0,0,0),(12,'2012-06-04 04:46:46',1,480.00,26,0,1,0,6.756999999999963,1,'USER','Easy','testing the description of the first easy game',1000,60.00,'','Unlocked',0,0,0),(13,'2012-06-04 04:48:08',1,480.00,13,0,1,0,4.389999999999985,1,'USER','Easy','testing the description of the first easy game',1000,60.00,'','Unlocked',0,0,0),(14,'2012-06-04 04:50:16',1,480.00,19,0,1,0,5.204999999999967,1,'USER','Easy','testing the description of the first easy game',1000,60.00,'','Unlocked',0,0,0),(15,'2012-06-04 04:51:25',1,480.00,17,0,1,0,4.535999999999978,1,'USER','Easy','testing the description of the first easy game',1000,60.00,'','Unlocked',0,0,0),(16,'2012-06-04 04:57:54',1,480.00,24,0,1,0,6.3899999999999535,1,'USER','Easy','testing the description of the first easy game',1000,60.00,'','Unlocked',0,0,0),(17,'2012-06-04 05:25:09',1,480.00,20,0,1,0,5.3509999999999645,1,'USER','Easy','testing the description of the first easy game',1000,60.00,'','Unlocked',0,0,0),(18,'2012-06-04 11:14:46',1,460.00,36,5,1,0,23.620000000000157,1,'USER','Easy','testing the description of the first easy game',1000,60.00,'','Unlocked',0,0,0),(19,'2012-06-05 19:43:53',1,480.00,12,0,1,0,12.183000000000186,1,'USER','Easy','testing the description of the first easy game',1000,60.00,'','Unlocked',0,0,0),(20,'2012-06-05 19:45:22',1,480.00,6,0,1,0,6.185999999999927,1,'USER','Easy','testing the description of the first easy game',1000,60.00,'','Unlocked',0,0,0),(21,'2012-06-06 16:18:08',1,480.00,7,0,1,0,7.951999999999895,1,'USER','Easy','testing the description of the first easy game',1000,60.00,'','Unlocked',0,0,0),(22,'2012-06-06 17:08:32',1,480.00,2,0,1,0,2.610999999999995,1,'USER','Easy','testing the description of the first easy game',1000,60.00,'','Unlocked',0,0,0),(23,'2012-06-06 17:37:16',1,480.00,7,0,1,0,7.785999999999918,1,'USER','Easy','testing the description of the first easy game',1000,60.00,'','Unlocked',0,0,0),(24,'2012-06-06 17:40:48',1,480.00,2,0,1,0,2.8919999999999915,1,'USER','Easy','testing the description of the first easy game',1000,60.00,'','Unlocked',0,0,0),(25,'2012-06-07 01:06:23',1,480.00,12,0,1,0,12.669999999999998,1,'USER','Easy','testing the description of the first easy game',1000,60.00,'','Unlocked',0,0,0),(26,'2012-06-07 01:08:56',1,480.00,11,0,1,0,7.6409999999999165,1,'USER','Easy','testing the description of the first easy game',1000,60.00,'','Unlocked',0,0,0),(27,'2012-06-07 01:10:24',1,480.00,12,0,1,0,6.138999999999944,1,'USER','Easy','testing the description of the first easy game',1000,60.00,'','Unlocked',0,0,0),(28,'2012-06-07 11:23:46',1,470.00,9,3,1,0,189.64499999997457,1,'USER','Easy','testing the description of the first easy game',1000,60.00,'','Unlocked',0,0,0),(29,'2012-06-07 11:31:10',1,480.00,30,0,1,0,15.822000000000068,1,'USER','Easy','testing the description of the first easy game',1000,60.00,'','Unlocked',0,0,0),(30,'2012-06-07 11:38:09',1,480.00,12,0,1,0,7.272999999999935,1,'USER','Easy','testing the description of the first easy game',1000,60.00,'','Unlocked',0,0,0),(31,'2012-06-07 15:39:23',1,480.00,23,0,1,0,13.006000000000034,1,'USER','Easy','testing the description of the first easy game',1000,60.00,'','Unlocked',0,0,0),(32,'2012-06-08 05:35:41',1,480.00,10,1,1,0,36.38300000000058,1,'USER','Easy','testing the description of the first easy game',1000,60.00,'','Unlocked',0,0,0),(33,'2012-06-09 14:30:21',1,480.00,26,0,1,0,8.68799999999993,1,'USER','Easy','testing the description of the first easy game',1000,60.00,'','Unlocked',0,0,0),(34,'2012-06-09 16:17:48',1,480.00,34,0,1,0,9.781999999999925,0.25,'USER','Easy','testing the description of the first easy game',1000,60.00,'','Unlocked',0,0,0),(35,'2012-06-09 17:34:06',1,480.00,30,0,1,0,30.559000000000268,1,'USER','Easy','testing the description of the first easy game',1000,60.00,'','Unlocked',0,0,0),(36,'2012-06-13 14:47:43',1,480.00,46,0,1,0,12.28000000000017,0.25,'USER','Easy','testing the description of the first easy game',1000,60.00,'','Unlocked',0,0,0),(37,'2012-06-18 21:12:26',1,480.00,29,0,1,0,29.768999999999668,1,'USER','Easy','testing the description of the first easy game',1000,60.00,'','Unlocked',0,0,0),(38,'2012-06-18 21:12:31',1,480.00,32,0,1,0,32.94099999999976,1,'USER','Easy','testing the description of the first easy game',1000,60.00,'','Unlocked',0,0,0),(39,'2012-06-18 21:12:33',1,480.00,32,0,1,0,32.94099999999976,1,'USER','Easy','testing the description of the first easy game',1000,60.00,'','Unlocked',0,0,0),(40,'2012-06-20 11:44:33',1,480.00,13,0,1,0,13.15600000000004,1,'USER','Easy','testing the description of the first easy game',1000,60.00,'','Unlocked',0,0,0),(41,'2012-06-21 18:23:00',1,480.00,48,0,1,0,48.613000000000845,1,'USER','Easy','testing the description of the first easy game',1000,60.00,'','Unlocked',0,0,0),(42,'2012-08-02 17:14:45',2,8176.70,0,0,0,0,0,1,'USER','Easy','Testing the description of the first EASY game',10000,20.00,'Phase 1: 2purchases,1assembly,1ship','Unlocked',1,2,0),(43,'2012-08-02 17:14:45',2,480.00,18,0,1,0,6.699999999999946,1,'USER','Hard','Testing the description of the first HARD game',1000,60.00,'Phase 3: .............','Locked',3,0,0),(44,'2012-08-02 17:14:45',2,480.00,41,0,1,0,30.455000000000293,1,'USER','Normal','Testing the description of the first NORMAL game',1000,60.00,'Phase 2 : .........','Locked',2,3,0);
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
) ENGINE=InnoDB AUTO_INCREMENT=89 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_log`
--

LOCK TABLES `t_log` WRITE;
/*!40000 ALTER TABLE `t_log` DISABLE KEYS */;
INSERT INTO `t_log` VALUES (1,'2012-05-13 01:17:05','Proc: Machine_Select(0) - Error: Unknown column ´virtual_maxtrix_end_j´ in ´field list´'),(2,'2012-05-13 01:20:08','Proc: Machine_Select(0) - Error: Unknown column ´virtual_maxtrix_end_j´ in ´field list´'),(3,'2012-05-13 01:23:34','Proc: Machine_Select(0) - Error: Unknown column ´virtual_maxtrix_end_j´ in ´field list´'),(4,'2012-05-13 01:26:27','Proc: Machine_Select(0) - Error: Unknown column ´virtual_maxtrix_end_j´ in ´field list´'),(5,'2012-05-13 01:31:13','Proc: Machine_Select(0) - Error: Unknown column ´virtual_maxtrix_end_j´ in ´field list´'),(6,'2012-05-18 11:56:57','Proc: Machine_Select(0) - Error: Unknown column ´virtual_maxtrix_end_j´ in ´field list´'),(7,'2012-05-18 12:05:37','Proc: Machine_Select(0) - Error: Unknown column ´virtual_maxtrix_end_j´ in ´field list´'),(8,'2012-05-18 12:07:27','Proc: Machine_Select(0) - Error: Unknown column ´virtual_maxtrix_end_j´ in ´field list´'),(9,'2012-05-18 12:08:18','Proc: Machine_Select(0) - Error: Unknown column ´virtual_maxtrix_end_j´ in ´field list´'),(10,'2012-05-18 12:18:59','Proc: Machine_Select(0) - Error: Unknown column ´virtual_maxtrix_end_j´ in ´field list´'),(11,'2012-05-18 12:19:40','Proc: Machine_Select(0) - Error: Unknown column ´virtual_maxtrix_end_j´ in ´field list´'),(12,'2012-05-18 12:23:18','Proc: Machine_Select(0) - Error: Unknown column ´virtual_maxtrix_end_j´ in ´field list´'),(13,'2012-05-18 12:24:46','Proc: Machine_Select(0) - Error: Unknown column ´virtual_maxtrix_end_j´ in ´field list´'),(14,'2012-05-18 12:27:39','Proc: Machine_Select(0) - Error: Unknown column ´virtual_maxtrix_end_j´ in ´field list´'),(15,'2012-05-18 12:32:27','Proc: Machine_Select(0) - Error: Unknown column ´virtual_maxtrix_end_j´ in ´field list´'),(16,'2012-05-18 12:40:00','Proc: Machine_Select(0) - Error: Unknown column ´virtual_maxtrix_end_j´ in ´field list´'),(17,'2012-05-18 12:42:23','Proc: Machine_Select(0) - Error: Unknown column ´virtual_maxtrix_end_j´ in ´field list´'),(18,'2012-05-18 12:53:36','Proc: Machine_Select(0) - Error: Unknown column ´virtual_maxtrix_end_j´ in ´field list´'),(19,'2012-05-18 13:01:53','Proc: Machine_Select(0) - Error: Unknown column ´virtual_maxtrix_end_j´ in ´field list´'),(20,'2012-05-18 13:03:13','Proc: Machine_Select(0) - Error: Unknown column ´virtual_maxtrix_end_j´ in ´field list´'),(21,'2012-05-18 16:47:20','Proc: Machine_Select(0) - Error: Unknown column ´virtual_maxtrix_end_j´ in ´field list´'),(22,'2012-05-18 17:02:44','Proc: Machine_Select(0) - Error: Unknown column ´virtual_maxtrix_end_j´ in ´field list´'),(23,'2012-05-18 17:07:08','Proc: Machine_Select(0) - Error: Unknown column ´virtual_maxtrix_end_j´ in ´field list´'),(24,'2012-05-18 17:08:48','Proc: Machine_Select(0) - Error: Unknown column ´virtual_maxtrix_end_j´ in ´field list´'),(25,'2012-05-18 17:09:40','Proc: Machine_Select(0) - Error: Unknown column ´virtual_maxtrix_end_j´ in ´field list´'),(26,'2012-05-18 17:12:04','Proc: Machine_Select(0) - Error: Unknown column ´virtual_maxtrix_end_j´ in ´field list´'),(27,'2012-05-18 17:22:25','Proc: Machine_Select(0) - Error: Unknown column ´virtual_maxtrix_end_j´ in ´field list´'),(28,'2012-05-18 17:23:09','Proc: Machine_Select(0) - Error: Unknown column ´virtual_maxtrix_end_j´ in ´field list´'),(29,'2012-05-18 17:56:27','Proc: Machine_Select(0) - Error: Unknown column ´virtual_maxtrix_end_j´ in ´field list´'),(30,'2012-05-18 17:58:59','Proc: Machine_Select(10) - Error: Unknown column ´virtual_maxtrix_end_j´ in ´field list´'),(31,'2012-05-18 18:02:20','Proc: Machine_Select(0) - Error: Unknown column ´virtual_maxtrix_end_j´ in ´field list´'),(32,'2012-05-18 18:10:48','Proc: Machine_Select(0) - Error: Unknown column ´virtual_maxtrix_end_j´ in ´field list´'),(33,'2012-05-18 18:15:10','Proc: Machine_Select(0) - Error: Unknown column ´virtual_maxtrix_end_j´ in ´field list´'),(34,'2012-05-18 18:53:04','Proc: Machine_Select(0) - Error: Unknown column ´virtual_maxtrix_end_j´ in ´field list´'),(35,'2012-05-18 18:53:52','Proc: Machine_Select(0) - Error: Unknown column ´virtual_maxtrix_end_j´ in ´field list´'),(36,'2012-05-18 18:56:37','Proc: Machine_Select(0) - Error: Unknown column ´virtual_maxtrix_end_j´ in ´field list´'),(37,'2012-06-04 04:57:55','Proc: Operation_Insert(3,Assemble Part3,,0.0,0.0,0,2,1,1,2) - Error: Duplicate entry 3 for key PRIMARY'),(38,'2012-06-04 04:57:55','Proc: TransportStore_Insert(4,10,2,2,1) - Error: Duplicate entry 4 for key PRIMARY'),(39,'2012-06-04 04:57:55','Proc: TransportStore_Insert(5,10,3,2,1) - Error: Duplicate entry 5 for key PRIMARY'),(40,'2012-06-04 04:57:55','Proc: TransportStore_Insert(7,30,5,3,4) - Error: Duplicate entry 7 for key PRIMARY'),(41,'2012-06-04 04:57:55','Proc: TransportStore_Insert(6,20,5,1,3) - Error: Duplicate entry 6 for key PRIMARY'),(42,'2012-06-04 04:57:55','Proc: Purchase_Insert(1,2,1,10,100) - Error: Duplicate entry 1 for key PRIMARY'),(43,'2012-06-04 04:57:55','Proc: Purchase_Insert(2,2,1,10,50) - Error: Duplicate entry 2 for key PRIMARY'),(44,'2012-06-04 04:57:55','Proc: Ship_Insert(8,4,50) - Error: Duplicate entry 8 for key PRIMARY'),(45,'2012-06-04 09:09:45','Proc: Game_Insert(1,1500.0,47,2,6,0,473.0929999997742) - Error: Data truncation: Out of range value for column ´decCurrentMoney´ at row 196'),(46,'2012-06-04 09:09:45','Proc: TypeOperation_Insert(1,0,Paint) - Error: Duplicate entry 1-0 for key PRIMARY'),(47,'2012-06-04 09:09:45','Proc: TypeOperation_Insert(2,0,Assemble) - Error: Duplicate entry 2-0 for key PRIMARY'),(48,'2012-06-04 09:09:45','Proc: TypeOperation_Insert(3,0,Cut) - Error: Duplicate entry 3-0 for key PRIMARY'),(49,'2012-06-04 09:09:45','Proc: Operator_Insert(1,0,Pepe,300.0,Busy,User,Walking,0,0,-3,-87,20.0) - Error: Duplicate entry 1-0 for key PRIMARY'),(50,'2012-06-04 09:09:45','Proc: Operator_Insert(2,0,Bob,300.0,Busy,User,Walking,0,0,35,57,22.0) - Error: Duplicate entry 2-0 for key PRIMARY'),(51,'2012-06-04 09:09:45','Proc: Operator_Insert(3,0,Juan,300.0,Idle,User,STATION1,0,0,67,-82,25.0) - Error: Duplicate entry 3-0 for key PRIMARY'),(52,'2012-06-04 09:09:45','Proc: Operator_Insert(4,0,Maria,300.0,Idle,User,STATION1,0,0,43,-62,15.0) - Error: Duplicate entry 4-0 for key PRIMARY'),(53,'2012-06-04 09:09:45','Proc: Part_Insert(1,0,Part One,EACH,100.0,100.0,100,52,0.25,User,Models/Screw/screw.j3o,0.01,Red) - Error: Duplicate entry 1-0 for key PRIMARY'),(54,'2012-06-04 09:09:45','Proc: Part_Insert(2,0,Part Two,EACH,100.0,100.0,50,26,0.4,User,Models/Screw2/screw2.j3o,0.01,Blue) - Error: Duplicate entry 2-0 for key PRIMARY'),(55,'2012-06-04 09:09:45','Proc: Part_Insert(3,0,Part Three,EACH,300.0,300.0,25,724,2.0,User,Models/Chair/chair.j3o,0.05,Green) - Error: Duplicate entry 3-0 for key PRIMARY'),(56,'2012-06-04 09:09:45','Proc: Supplier_Insert(1,0,Supplier Pepito,0,0,0.0) - Error: Duplicate entry 1-0 for key PRIMARY'),(57,'2012-06-04 09:09:45','Proc: Station_Insert(1,0,Station to create part 3,50,-70,40.0,40.0,1.0,25,25,Idle,User,Other) - Error: Duplicate entry 1-0 for key PRIMARY'),(58,'2012-06-04 09:09:45','Proc: Station_Insert(2,0,Receiving Dock,-120,-70,30.0,30.0,1.0,0,50,Idle,User,Other) - Error: Duplicate entry 2-0 for key PRIMARY'),(59,'2012-06-04 09:09:45','Proc: Station_Insert(3,0,Storage,240,10,50.0,50.0,1.0,200,200,Idle,User,Other) - Error: Duplicate entry 3-0 for key PRIMARY'),(60,'2012-06-04 09:09:45','Proc: Station_Insert(4,0,Ship Station,-120,70,30.0,30.0,1.0,200,200,Idle,User,Other) - Error: Duplicate entry 4-0 for key PRIMARY'),(61,'2012-06-04 09:09:45','Proc: Station_Insert(5,0,Staff Zone,50,50,40.0,20.0,1.0,0,0,Idle,User,StaffZone) - Error: Duplicate entry 5-0 for key PRIMARY'),(62,'2012-06-04 09:09:45','Proc: Station_Insert(6,0,Machine Zone,-60,50,40.0,20.0,1.0,0,0,Idle,User,MachineZone) - Error: Duplicate entry 6-0 for key PRIMARY'),(63,'2012-06-07 11:23:47','Proc: Event_Insert(0,28,1.0,192.0,4) - Error: Duplicate entry 0-28 for key PRIMARY'),(64,'2012-06-13 17:43:44','Proc: TypeOperationPerSkill_Select(0,0) - Error: Table ´gaming.t_skill_type_operation´ doesn´t exist'),(65,'2012-06-13 17:43:44','Proc: TypeOperationPerSkill_Select(0,1) - Error: Table ´gaming.t_skill_type_operation´ doesn´t exist'),(66,'2012-06-13 17:43:44','Proc: TypeOperationPerSkill_Select(0,2) - Error: Table ´gaming.t_skill_type_operation´ doesn´t exist'),(67,'2012-06-13 17:43:44','Proc: TypeOperationPerSkill_Select(0,3) - Error: Table ´gaming.t_skill_type_operation´ doesn´t exist'),(68,'2012-06-13 17:43:44','Proc: TypeOperationPerSkill_Select(0,4) - Error: Table ´gaming.t_skill_type_operation´ doesn´t exist'),(69,'2012-06-13 17:43:45','Proc: Operator_Select(0) - Error: Unknown column ´is_executing_activity´ in ´field list´'),(70,'2012-06-13 17:45:19','Proc: TypeOperationPerSkill_Select(0,0) - Error: Table ´gaming.t_skill_type_operation´ doesn´t exist'),(71,'2012-06-13 17:45:19','Proc: TypeOperationPerSkill_Select(0,1) - Error: Table ´gaming.t_skill_type_operation´ doesn´t exist'),(72,'2012-06-13 17:45:19','Proc: TypeOperationPerSkill_Select(0,2) - Error: Table ´gaming.t_skill_type_operation´ doesn´t exist'),(73,'2012-06-13 17:45:19','Proc: TypeOperationPerSkill_Select(0,3) - Error: Table ´gaming.t_skill_type_operation´ doesn´t exist'),(74,'2012-06-13 17:45:19','Proc: TypeOperationPerSkill_Select(0,4) - Error: Table ´gaming.t_skill_type_operation´ doesn´t exist'),(75,'2012-06-13 17:45:19','Proc: Operator_Select(0) - Error: Unknown column ´is_executing_activity´ in ´field list´'),(76,'2012-06-13 17:47:45','Proc: TypeOperationPerSkill_Select(0,0) - Error: Table ´gaming.t_skill_type_operation´ doesn´t exist'),(77,'2012-06-13 17:47:45','Proc: TypeOperationPerSkill_Select(0,1) - Error: Table ´gaming.t_skill_type_operation´ doesn´t exist'),(78,'2012-06-13 17:47:45','Proc: TypeOperationPerSkill_Select(0,2) - Error: Table ´gaming.t_skill_type_operation´ doesn´t exist'),(79,'2012-06-13 17:47:45','Proc: TypeOperationPerSkill_Select(0,3) - Error: Table ´gaming.t_skill_type_operation´ doesn´t exist'),(80,'2012-06-13 17:47:45','Proc: TypeOperationPerSkill_Select(0,4) - Error: Table ´gaming.t_skill_type_operation´ doesn´t exist'),(81,'2012-06-13 17:47:53','Proc: Operator_Select(0) - Error: Unknown column ´is_executing_activity´ in ´field list´'),(82,'2012-06-13 18:14:01','Proc: Operator_Select(0) - Error: Unknown column ´is_executing_activity´ in ´field list´'),(83,'2012-06-23 17:07:48','Proc: Game_SelectByType(CPU) - Error: Unknown column ´CPU´ in ´field list´'),(84,'2012-06-23 17:09:13','Proc: Game_SelectByType(CPU) - Error: Unknown column ´CPU´ in ´field list´'),(85,'2012-06-23 17:09:44','Proc: Game_SelectByType(CPU) - Error: Unknown column ´CPU´ in ´field list´'),(86,'2012-06-23 17:10:34','Proc: Game_SelectByType(CPU) - Error: Unknown column ´CPU´ in ´field list´'),(87,'2012-08-14 14:13:24','Proc: Order_Select(0) - Error: Column Index out of range, 7 > 6. '),(88,'2012-08-14 14:15:58','Proc: Order_Select(0) - Error: Column Index out of range, 7 > 6. ');
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
  `speed` decimal(5,2) DEFAULT NULL,
  `weight_capacity` decimal(5,2) DEFAULT NULL,
  `volume_capacity` decimal(5,2) DEFAULT NULL,
  `pick_up_time_distn` varchar(10) DEFAULT NULL,
  `pick_up_time_parameter1` decimal(5,2) DEFAULT NULL,
  `pick_up_time_parameter2` decimal(5,2) DEFAULT NULL,
  `machine_time_distn` varchar(10) DEFAULT NULL,
  `machine_time_parameter1` decimal(5,2) DEFAULT NULL,
  `machine_time_parameter2` decimal(5,2) DEFAULT NULL,
  `placement_time_distn` varchar(10) DEFAULT NULL,
  `placement_time_parameter1` decimal(5,2) DEFAULT NULL,
  `placement_time_parameter2` decimal(5,2) DEFAULT NULL,
  `time_between_failures_distn` varchar(10) DEFAULT NULL,
  `time_between_failures_parameter1` decimal(5,2) DEFAULT NULL,
  `time_between_failures_parameter2` decimal(5,2) DEFAULT NULL,
  `repair_time_distn` varchar(10) DEFAULT NULL,
  `repair_time_parameter1` decimal(5,2) DEFAULT NULL,
  `repair_time_parameter2` decimal(5,2) DEFAULT NULL,
  `price_for_purchase` decimal(5,2) DEFAULT NULL,
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
  `cost_per_hour` decimal(5,2) DEFAULT NULL,
  `machine_category` varchar(45) DEFAULT NULL,
  `state` varchar(45) DEFAULT NULL,
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
INSERT INTO `t_machine` VALUES (1,0,'Machine to assemble',100.00,150.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',12.00,0.00,'',5.00,0.00,300.00,'Idle','User','Models/Machine/machine1.j3o','-1',0,0,0,0,0,0,10,20,5.00,'Operation','Activate'),(1,6,'Machine assembles part 3',100.00,150.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',12.00,0.00,'',5.00,0.00,300.00,'Idle','User','Models/Machine/machine1.j3o','STATION1',1,6,0,0,37,-62,10,20,5.00,NULL,NULL),(1,7,'Machine assembles part 3',100.00,150.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',12.00,0.00,'',5.00,0.00,300.00,'Idle','User','Models/Machine/machine1.j3o','STATION1',1,6,0,0,37,-62,10,20,5.00,NULL,NULL),(1,8,'Machine assembles part 3',100.00,150.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',12.00,0.00,'',5.00,0.00,300.00,'Idle','User','Models/Machine/machine1.j3o','STATION1',1,6,0,0,37,-62,10,20,5.00,NULL,NULL),(1,9,'Machine assembles part 3',100.00,150.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',12.00,0.00,'',5.00,0.00,300.00,'Idle','User','Models/Machine/machine1.j3o','STATION1',1,6,0,0,37,-62,10,20,5.00,NULL,NULL),(1,10,'Machine assembles part 3',100.00,150.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',12.00,0.00,'',5.00,0.00,300.00,'Idle','User','Models/Machine/machine1.j3o','STATION1',1,6,0,0,37,-62,10,20,5.00,NULL,NULL),(1,11,'Machine assembles part 3',100.00,150.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',12.00,0.00,'',5.00,0.00,300.00,'Idle','User','Models/Machine/machine1.j3o','STATION1',1,6,0,0,37,-62,10,20,5.00,NULL,NULL),(1,12,'Machine assembles part 3',100.00,150.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',12.00,0.00,'',5.00,0.00,300.00,'Idle','User','Models/Machine/machine1.j3o','STATION1',1,6,0,0,37,-62,10,20,5.00,NULL,NULL),(1,13,'Machine assembles part 3',100.00,150.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',12.00,0.00,'',5.00,0.00,300.00,'Idle','User','Models/Machine/machine1.j3o','STATION1',1,6,0,0,37,-62,10,20,5.00,NULL,NULL),(1,14,'Machine assembles part 3',100.00,150.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',12.00,0.00,'',5.00,0.00,300.00,'Idle','User','Models/Machine/machine1.j3o','STATION1',1,6,0,0,37,-62,10,20,5.00,NULL,NULL),(1,15,'Machine assembles part 3',100.00,150.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',12.00,0.00,'',5.00,0.00,300.00,'Idle','User','Models/Machine/machine1.j3o','STATION1',1,6,0,0,37,-62,10,20,5.00,NULL,NULL),(1,16,'Machine assembles part 3',100.00,150.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',12.00,0.00,'',5.00,0.00,300.00,'Idle','User','Models/Machine/machine1.j3o','STATION1',1,6,0,0,37,-62,10,20,5.00,NULL,NULL),(1,17,'Machine assembles part 3',100.00,150.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',12.00,0.00,'',5.00,0.00,300.00,'Idle','User','Models/Machine/machine1.j3o','STATION1',1,6,0,0,37,-62,10,20,5.00,NULL,NULL),(1,18,'Machine assembles part 3',100.00,150.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',12.00,0.00,'',5.00,0.00,300.00,'Idle','User','Models/Machine/machine1.j3o','STATION1',1,6,0,0,37,-62,10,20,5.00,NULL,NULL),(1,19,'Machine assembles part 3',100.00,150.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',12.00,0.00,'',5.00,0.00,300.00,'Idle','User','Models/Machine/machine1.j3o','STATION1',1,6,0,0,37,-62,10,20,5.00,NULL,NULL),(1,20,'Machine assembles part 3',100.00,150.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',12.00,0.00,'',5.00,0.00,300.00,'Idle','User','Models/Machine/machine1.j3o','STATION1',1,6,0,0,37,-62,10,20,5.00,NULL,NULL),(1,21,'Machine assembles part 3',100.00,150.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',12.00,0.00,'',5.00,0.00,300.00,'Idle','User','Models/Machine/machine1.j3o','STATION1',1,6,0,0,37,-62,10,20,5.00,NULL,NULL),(1,22,'Machine assembles part 3',100.00,150.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',12.00,0.00,'',5.00,0.00,300.00,'Idle','User','Models/Machine/machine1.j3o','STATION1',1,6,0,0,37,-62,10,20,5.00,NULL,NULL),(1,23,'Machine assembles part 3',100.00,150.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',12.00,0.00,'',5.00,0.00,300.00,'Idle','User','Models/Machine/machine1.j3o','STATION1',1,6,0,0,37,-62,10,20,5.00,NULL,NULL),(1,24,'Machine assembles part 3',100.00,150.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',12.00,0.00,'',5.00,0.00,300.00,'Idle','User','Models/Machine/machine1.j3o','STATION1',1,6,0,0,37,-62,10,20,5.00,NULL,NULL),(1,25,'Machine assembles part 3',100.00,150.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',12.00,0.00,'',5.00,0.00,300.00,'Idle','User','Models/Machine/machine1.j3o','STATION1',1,6,0,0,37,-62,10,20,5.00,NULL,NULL),(1,26,'Machine assembles part 3',100.00,150.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',12.00,0.00,'',5.00,0.00,300.00,'Idle','User','Models/Machine/machine1.j3o','STATION1',1,6,0,0,37,-62,10,20,5.00,NULL,NULL),(1,27,'Machine assembles part 3',100.00,150.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',12.00,0.00,'',5.00,0.00,300.00,'Idle','User','Models/Machine/machine1.j3o','STATION1',1,6,0,0,37,-62,10,20,5.00,NULL,NULL),(1,28,'Machine assembles part 3',100.00,150.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',12.00,0.00,'',5.00,0.00,300.00,'Busy','User','Models/Machine/machine1.j3o','STATION1',1,6,0,0,37,-62,10,20,5.00,NULL,NULL),(1,29,'Machine assembles part 3',100.00,150.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',12.00,0.00,'',5.00,0.00,300.00,'Idle','User','Models/Machine/machine1.j3o','STATION1',1,6,0,0,37,-62,10,20,5.00,NULL,NULL),(1,30,'Machine assembles part 3',100.00,150.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',12.00,0.00,'',5.00,0.00,300.00,'Idle','User','Models/Machine/machine1.j3o','STATION1',1,6,0,0,37,-62,10,20,5.00,NULL,NULL),(1,31,'Machine assembles part 3',100.00,150.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',12.00,0.00,'',5.00,0.00,300.00,'Idle','User','Models/Machine/machine1.j3o','STATION1',1,6,0,0,37,-62,10,20,5.00,NULL,NULL),(1,32,'Machine assembles part 3',100.00,150.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',12.00,0.00,'',5.00,0.00,300.00,'Broken','User','Models/Machine/machine1.j3o','STATION1',1,6,0,0,37,-62,10,20,5.00,NULL,NULL),(1,33,'Machine assembles part 3',100.00,150.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',12.00,0.00,'',5.00,0.00,300.00,'Idle','User','Models/Machine/machine1.j3o','STATION1',1,6,0,0,37,-62,10,20,5.00,NULL,NULL),(1,34,'Machine assembles part 3',100.00,150.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',12.00,0.00,'',5.00,0.00,300.00,'Idle','User','Models/Machine/machine1.j3o','STATION1',1,6,0,0,37,-62,10,20,5.00,NULL,NULL),(1,35,'Machine assembles part 3',100.00,150.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',12.00,0.00,'',5.00,0.00,300.00,'Idle','User','Models/Machine/machine1.j3o','STATION1',1,6,0,0,37,-62,10,20,5.00,NULL,NULL),(1,36,'Machine assembles part 3',100.00,150.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',12.00,0.00,'',5.00,0.00,300.00,'Idle','User','Models/Machine/machine1.j3o','STATION1',1,6,0,0,37,-62,10,20,5.00,NULL,NULL),(1,37,'Machine assembles part 3',100.00,150.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',12.00,0.00,'',5.00,0.00,300.00,'Idle','User','Models/Machine/machine1.j3o','STATION1',1,6,0,0,37,-62,10,20,5.00,NULL,NULL),(1,38,'Machine assembles part 3',100.00,150.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',12.00,0.00,'',5.00,0.00,300.00,'Idle','User','Models/Machine/machine1.j3o','STATION1',1,6,0,0,37,-62,10,20,5.00,NULL,NULL),(1,39,'Machine assembles part 3',100.00,150.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',12.00,0.00,'',5.00,0.00,300.00,'Idle','User','Models/Machine/machine1.j3o','STATION1',1,6,0,0,37,-62,10,20,5.00,NULL,NULL),(1,40,'Machine assembles part 3',100.00,150.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',12.00,0.00,'',5.00,0.00,300.00,'Idle','User','Models/Machine/machine1.j3o','STATION1',1,6,0,0,37,-62,10,20,5.00,NULL,NULL),(1,41,'Machine assembles part 3',100.00,150.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',12.00,0.00,'',5.00,0.00,300.00,'Idle','User','Models/Machine/machine1.j3o','STATION1',1,6,0,0,37,-62,10,20,5.00,NULL,NULL),(2,0,'Machine to transport',100.00,110.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',20.00,0.00,'',5.00,0.00,200.00,'Idle','User','Models/Kart/kart.j3o','-1',0,0,0,0,0,0,10,5,5.00,'Transport','Deactivate'),(2,6,'Machine to transport',100.00,110.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',20.00,0.00,'',5.00,0.00,200.00,'Busy','User','Models/Kart/kart.j3o','Walking',0,0,0,0,-107,-82,10,5,5.00,NULL,NULL),(2,7,'Machine to transport',100.00,110.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',20.00,0.00,'',5.00,0.00,200.00,'Busy','User','Models/Kart/kart.j3o','Walking',0,0,0,0,-62,44,10,5,5.00,NULL,NULL),(2,8,'Machine to transport',100.00,110.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',20.00,0.00,'',5.00,0.00,200.00,'Busy','User','Models/Kart/kart.j3o','STATION6',3,0,0,0,-62,44,10,5,5.00,NULL,NULL),(2,9,'Machine to transport',100.00,110.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',20.00,0.00,'',5.00,0.00,200.00,'Busy','User','Models/Kart/kart.j3o','Walking',0,0,0,0,-62,44,10,5,5.00,NULL,NULL),(2,10,'Machine to transport',100.00,110.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',20.00,0.00,'',5.00,0.00,200.00,'Busy','User','Models/Kart/kart.j3o','Walking',0,0,0,0,-62,44,10,5,5.00,NULL,NULL),(2,11,'Machine to transport',100.00,110.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',20.00,0.00,'',5.00,0.00,200.00,'Busy','User','Models/Kart/kart.j3o','Walking',0,0,0,0,-62,44,10,5,5.00,NULL,NULL),(2,12,'Machine to transport',100.00,110.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',20.00,0.00,'',5.00,0.00,200.00,'Busy','User','Models/Kart/kart.j3o','Walking',0,0,0,0,-62,44,10,5,5.00,NULL,NULL),(2,13,'Machine to transport',100.00,110.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',20.00,0.00,'',5.00,0.00,200.00,'Busy','User','Models/Kart/kart.j3o','Walking',0,0,0,0,-62,44,10,5,5.00,NULL,NULL),(2,14,'Machine to transport',100.00,110.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',20.00,0.00,'',5.00,0.00,200.00,'Busy','User','Models/Kart/kart.j3o','Walking',0,0,0,0,-62,44,10,5,5.00,NULL,NULL),(2,15,'Machine to transport',100.00,110.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',20.00,0.00,'',5.00,0.00,200.00,'Busy','User','Models/Kart/kart.j3o','STATION6',3,0,0,0,-62,44,10,5,5.00,NULL,NULL),(2,16,'Machine to transport',100.00,110.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',20.00,0.00,'',5.00,0.00,200.00,'Busy','User','Models/Kart/kart.j3o','Walking',0,0,0,0,-62,44,10,5,5.00,NULL,NULL),(2,17,'Machine to transport',100.00,110.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',20.00,0.00,'',5.00,0.00,200.00,'Busy','User','Models/Kart/kart.j3o','Walking',0,0,0,0,-62,44,10,5,5.00,NULL,NULL),(2,18,'Machine to transport',100.00,110.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',20.00,0.00,'',5.00,0.00,200.00,'Busy','User','Models/Kart/kart.j3o','Walking',0,0,0,0,67,-87,10,5,5.00,NULL,NULL),(2,19,'Machine to transport',100.00,110.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',20.00,0.00,'',5.00,0.00,200.00,'Busy','User','Models/Kart/kart.j3o','STATION6',3,0,0,0,-62,44,10,5,5.00,NULL,NULL),(2,20,'Machine to transport',100.00,110.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',20.00,0.00,'',5.00,0.00,200.00,'Idle','User','Models/Kart/kart.j3o','STATION6',3,0,0,0,-62,44,10,5,5.00,NULL,NULL),(2,21,'Machine to transport',100.00,110.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',20.00,0.00,'',5.00,0.00,200.00,'Idle','User','Models/Kart/kart.j3o','STATION6',3,0,0,0,-62,44,10,5,5.00,NULL,NULL),(2,22,'Machine to transport',100.00,110.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',20.00,0.00,'',5.00,0.00,200.00,'Idle','User','Models/Kart/kart.j3o','STATION6',3,0,0,0,-62,44,10,5,5.00,NULL,NULL),(2,23,'Machine to transport',100.00,110.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',20.00,0.00,'',5.00,0.00,200.00,'Idle','User','Models/Kart/kart.j3o','STATION6',3,0,0,0,-62,44,10,5,5.00,NULL,NULL),(2,24,'Machine to transport',100.00,110.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',20.00,0.00,'',5.00,0.00,200.00,'Idle','User','Models/Kart/kart.j3o','STATION6',3,0,0,0,-62,44,10,5,5.00,NULL,NULL),(2,25,'Machine to transport',100.00,110.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',20.00,0.00,'',5.00,0.00,200.00,'Busy','User','Models/Kart/kart.j3o','STATION6',3,0,0,0,-62,44,10,5,5.00,NULL,NULL),(2,26,'Machine to transport',100.00,110.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',20.00,0.00,'',5.00,0.00,200.00,'Busy','User','Models/Kart/kart.j3o','STATION6',3,0,0,0,-62,44,10,5,5.00,NULL,NULL),(2,27,'Machine to transport',100.00,110.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',20.00,0.00,'',5.00,0.00,200.00,'Busy','User','Models/Kart/kart.j3o','STATION6',3,0,0,0,-62,44,10,5,5.00,NULL,NULL),(2,28,'Machine to transport',100.00,110.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',20.00,0.00,'',5.00,0.00,200.00,'Broken','User','Models/Kart/kart.j3o','STATION1',0,0,0,0,67,-87,10,5,5.00,NULL,NULL),(2,29,'Machine to transport',100.00,110.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',20.00,0.00,'',5.00,0.00,200.00,'Busy','User','Models/Kart/kart.j3o','Walking',0,0,0,0,-107,-82,10,5,5.00,NULL,NULL),(2,30,'Machine to transport',100.00,110.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',20.00,0.00,'',5.00,0.00,200.00,'Busy','User','Models/Kart/kart.j3o','STATION6',3,0,0,0,-62,44,10,5,5.00,NULL,NULL),(2,31,'Machine to transport',100.00,110.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',20.00,0.00,'',5.00,0.00,200.00,'Busy','User','Models/Kart/kart.j3o','Walking',0,0,0,0,-62,44,10,5,5.00,NULL,NULL),(2,32,'Machine to transport',100.00,110.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',20.00,0.00,'',5.00,0.00,200.00,'Busy','User','Models/Kart/kart.j3o','Walking',0,0,0,0,-107,-82,10,5,5.00,NULL,NULL),(2,33,'Machine to transport',100.00,110.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',20.00,0.00,'',5.00,0.00,200.00,'Busy','User','Models/Kart/kart.j3o','Walking',0,0,0,0,-107,-82,10,5,5.00,NULL,NULL),(2,34,'Machine to transport',100.00,110.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',20.00,0.00,'',5.00,0.00,200.00,'Busy','User','Models/Kart/kart.j3o','Walking',0,0,0,0,-107,-82,10,5,5.00,NULL,NULL),(2,35,'Machine to transport',100.00,110.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',20.00,0.00,'',5.00,0.00,200.00,'Busy','User','Models/Kart/kart.j3o','Walking',0,0,0,0,-107,-82,10,5,5.00,NULL,NULL),(2,36,'Machine to transport',100.00,110.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',20.00,0.00,'',5.00,0.00,200.00,'Busy','User','Models/Kart/kart.j3o','Walking',0,0,0,0,67,-87,10,5,5.00,NULL,NULL),(2,37,'Machine to transport',100.00,110.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',20.00,0.00,'',5.00,0.00,200.00,'Busy','User','Models/Kart/kart.j3o','Walking',0,0,0,0,-62,44,10,5,5.00,NULL,NULL),(2,38,'Machine to transport',100.00,110.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',20.00,0.00,'',5.00,0.00,200.00,'Busy','User','Models/Kart/kart.j3o','STATION2',0,0,0,0,-107,-82,10,5,5.00,NULL,NULL),(2,39,'Machine to transport',100.00,110.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',20.00,0.00,'',5.00,0.00,200.00,'Busy','User','Models/Kart/kart.j3o','STATION2',0,0,0,0,-107,-82,10,5,5.00,NULL,NULL),(2,40,'Machine to transport',100.00,110.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',20.00,0.00,'',5.00,0.00,200.00,'Busy','User','Models/Kart/kart.j3o','STATION6',3,0,0,0,-62,44,10,5,5.00,NULL,NULL),(2,41,'Machine to transport',100.00,110.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',20.00,0.00,'',5.00,0.00,200.00,'Busy','User','Models/Kart/kart.j3o','Walking',0,0,0,0,-107,-82,10,5,5.00,NULL,NULL),(3,0,'Machine to transport',100.00,150.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',18.00,0.00,'',4.00,0.00,250.00,'Idle','User','Models/Kart/kart.j3o','-1',0,0,0,0,0,0,10,5,5.00,'Transport','Activate'),(3,6,'Machine to transport',100.00,150.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',18.00,0.00,'',4.00,0.00,250.00,'Busy','User','Models/Kart/kart.j3o','Walking',0,0,0,0,67,-82,10,5,5.00,NULL,NULL),(3,7,'Machine to transport',100.00,150.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',18.00,0.00,'',4.00,0.00,250.00,'Busy','User','Models/Kart/kart.j3o','Walking',0,0,0,0,-62,49,10,5,5.00,NULL,NULL),(3,8,'Machine to transport',100.00,150.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',18.00,0.00,'',4.00,0.00,250.00,'Busy','User','Models/Kart/kart.j3o','Walking',0,0,0,0,-62,49,10,5,5.00,NULL,NULL),(3,9,'Machine to transport',100.00,150.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',18.00,0.00,'',4.00,0.00,250.00,'Busy','User','Models/Kart/kart.j3o','Walking',0,0,0,0,-107,-77,10,5,5.00,NULL,NULL),(3,10,'Machine to transport',100.00,150.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',18.00,0.00,'',4.00,0.00,250.00,'Busy','User','Models/Kart/kart.j3o','Walking',0,0,0,0,-62,49,10,5,5.00,NULL,NULL),(3,11,'Machine to transport',100.00,150.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',18.00,0.00,'',4.00,0.00,250.00,'Busy','User','Models/Kart/kart.j3o','Walking',0,0,0,0,-107,-77,10,5,5.00,NULL,NULL),(3,12,'Machine to transport',100.00,150.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',18.00,0.00,'',4.00,0.00,250.00,'Busy','User','Models/Kart/kart.j3o','Walking',0,0,0,0,-107,-77,10,5,5.00,NULL,NULL),(3,13,'Machine to transport',100.00,150.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',18.00,0.00,'',4.00,0.00,250.00,'Busy','User','Models/Kart/kart.j3o','Walking',0,0,0,0,-107,-77,10,5,5.00,NULL,NULL),(3,14,'Machine to transport',100.00,150.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',18.00,0.00,'',4.00,0.00,250.00,'Busy','User','Models/Kart/kart.j3o','Walking',0,0,0,0,-62,49,10,5,5.00,NULL,NULL),(3,15,'Machine to transport',100.00,150.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',18.00,0.00,'',4.00,0.00,250.00,'Busy','User','Models/Kart/kart.j3o','Walking',0,0,0,0,-62,49,10,5,5.00,NULL,NULL),(3,16,'Machine to transport',100.00,150.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',18.00,0.00,'',4.00,0.00,250.00,'Busy','User','Models/Kart/kart.j3o','Walking',0,0,0,0,-107,-77,10,5,5.00,NULL,NULL),(3,17,'Machine to transport',100.00,150.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',18.00,0.00,'',4.00,0.00,250.00,'Busy','User','Models/Kart/kart.j3o','Walking',0,0,0,0,-107,-77,10,5,5.00,NULL,NULL),(3,18,'Machine to transport',100.00,150.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',18.00,0.00,'',4.00,0.00,250.00,'Idle','User','Models/Kart/kart.j3o','STATION1',0,0,0,0,67,-82,10,5,5.00,NULL,NULL),(3,19,'Machine to transport',100.00,150.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',18.00,0.00,'',4.00,0.00,250.00,'Busy','User','Models/Kart/kart.j3o','STATION6',3,1,0,0,-62,49,10,5,5.00,NULL,NULL),(3,20,'Machine to transport',100.00,150.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',18.00,0.00,'',4.00,0.00,250.00,'Idle','User','Models/Kart/kart.j3o','STATION6',3,1,0,0,-62,49,10,5,5.00,NULL,NULL),(3,21,'Machine to transport',100.00,150.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',18.00,0.00,'',4.00,0.00,250.00,'Idle','User','Models/Kart/kart.j3o','STATION6',3,1,0,0,-62,49,10,5,5.00,NULL,NULL),(3,22,'Machine to transport',100.00,150.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',18.00,0.00,'',4.00,0.00,250.00,'Idle','User','Models/Kart/kart.j3o','STATION6',3,1,0,0,-62,49,10,5,5.00,NULL,NULL),(3,23,'Machine to transport',100.00,150.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',18.00,0.00,'',4.00,0.00,250.00,'Idle','User','Models/Kart/kart.j3o','STATION6',3,1,0,0,-62,49,10,5,5.00,NULL,NULL),(3,24,'Machine to transport',100.00,150.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',18.00,0.00,'',4.00,0.00,250.00,'Idle','User','Models/Kart/kart.j3o','STATION6',3,1,0,0,-62,49,10,5,5.00,NULL,NULL),(3,25,'Machine to transport',100.00,150.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',18.00,0.00,'',4.00,0.00,250.00,'Busy','User','Models/Kart/kart.j3o','STATION6',3,1,0,0,-62,49,10,5,5.00,NULL,NULL),(3,26,'Machine to transport',100.00,150.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',18.00,0.00,'',4.00,0.00,250.00,'Busy','User','Models/Kart/kart.j3o','STATION6',3,1,0,0,-62,49,10,5,5.00,NULL,NULL),(3,27,'Machine to transport',100.00,150.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',18.00,0.00,'',4.00,0.00,250.00,'Busy','User','Models/Kart/kart.j3o','STATION6',3,1,0,0,-62,49,10,5,5.00,NULL,NULL),(3,28,'Machine to transport',100.00,150.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',18.00,0.00,'',4.00,0.00,250.00,'Busy','User','Models/Kart/kart.j3o','Walking',0,0,0,0,67,-82,10,5,5.00,NULL,NULL),(3,29,'Machine to transport',100.00,150.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',18.00,0.00,'',4.00,0.00,250.00,'Busy','User','Models/Kart/kart.j3o','Walking',0,0,0,0,67,-82,10,5,5.00,NULL,NULL),(3,30,'Machine to transport',100.00,150.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',18.00,0.00,'',4.00,0.00,250.00,'Busy','User','Models/Kart/kart.j3o','STATION6',3,1,0,0,-62,49,10,5,5.00,NULL,NULL),(3,31,'Machine to transport',100.00,150.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',18.00,0.00,'',4.00,0.00,250.00,'Busy','User','Models/Kart/kart.j3o','Walking',0,0,0,0,-107,-77,10,5,5.00,NULL,NULL),(3,32,'Machine to transport',100.00,150.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',18.00,0.00,'',4.00,0.00,250.00,'Busy','User','Models/Kart/kart.j3o','Walking',0,0,0,0,-107,-77,10,5,5.00,NULL,NULL),(3,33,'Machine to transport',100.00,150.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',18.00,0.00,'',4.00,0.00,250.00,'Busy','User','Models/Kart/kart.j3o','Walking',0,0,0,0,67,-82,10,5,5.00,NULL,NULL),(3,34,'Machine to transport',100.00,150.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',18.00,0.00,'',4.00,0.00,250.00,'Busy','User','Models/Kart/kart.j3o','Walking',0,0,0,0,-107,-77,10,5,5.00,NULL,NULL),(3,35,'Machine to transport',100.00,150.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',18.00,0.00,'',4.00,0.00,250.00,'Busy','User','Models/Kart/kart.j3o','Walking',0,0,0,0,67,-82,10,5,5.00,NULL,NULL),(3,36,'Machine to transport',100.00,150.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',18.00,0.00,'',4.00,0.00,250.00,'Busy','User','Models/Kart/kart.j3o','STATION1',0,0,0,0,67,-82,10,5,5.00,NULL,NULL),(3,37,'Machine to transport',100.00,150.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',18.00,0.00,'',4.00,0.00,250.00,'Busy','User','Models/Kart/kart.j3o','STATION2',0,0,0,0,-107,-77,10,5,5.00,NULL,NULL),(3,38,'Machine to transport',100.00,150.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',18.00,0.00,'',4.00,0.00,250.00,'Busy','User','Models/Kart/kart.j3o','STATION2',0,0,0,0,-107,-77,10,5,5.00,NULL,NULL),(3,39,'Machine to transport',100.00,150.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',18.00,0.00,'',4.00,0.00,250.00,'Busy','User','Models/Kart/kart.j3o','STATION2',0,0,0,0,-107,-77,10,5,5.00,NULL,NULL),(3,40,'Machine to transport',100.00,150.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',18.00,0.00,'',4.00,0.00,250.00,'Busy','User','Models/Kart/kart.j3o','STATION6',3,1,0,0,-62,49,10,5,5.00,NULL,NULL),(3,41,'Machine to transport',100.00,150.00,50.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',18.00,0.00,'',4.00,0.00,250.00,'Busy','User','Models/Kart/kart.j3o','Walking',0,0,0,0,-107,-77,10,5,5.00,NULL,NULL),(4,0,'Some other machine',100.00,300.00,75.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',14.00,0.00,'',3.00,0.00,500.00,'Idle','Game','Models/Kart/kart.j3o','-1',0,0,0,0,0,0,10,5,5.00,'','Activate'),(4,6,'Some other machine',100.00,300.00,75.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',14.00,0.00,'',3.00,0.00,500.00,'Idle','Game','Models/Kart/kart.j3o','-1',0,0,0,0,0,0,10,5,5.00,NULL,NULL),(4,7,'Some other machine',100.00,300.00,75.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',14.00,0.00,'',3.00,0.00,500.00,'Idle','Game','Models/Kart/kart.j3o','-1',0,0,0,0,0,0,10,5,5.00,NULL,NULL),(4,8,'Some other machine',100.00,300.00,75.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',14.00,0.00,'',3.00,0.00,500.00,'Idle','Game','Models/Kart/kart.j3o','-1',0,0,0,0,0,0,10,5,5.00,NULL,NULL),(4,9,'Some other machine',100.00,300.00,75.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',14.00,0.00,'',3.00,0.00,500.00,'Idle','Game','Models/Kart/kart.j3o','-1',0,0,0,0,0,0,10,5,5.00,NULL,NULL),(4,10,'Some other machine',100.00,300.00,75.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',14.00,0.00,'',3.00,0.00,500.00,'Idle','Game','Models/Kart/kart.j3o','-1',0,0,0,0,0,0,10,5,5.00,NULL,NULL),(4,11,'Some other machine',100.00,300.00,75.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',14.00,0.00,'',3.00,0.00,500.00,'Idle','Game','Models/Kart/kart.j3o','-1',0,0,0,0,0,0,10,5,5.00,NULL,NULL),(4,12,'Some other machine',100.00,300.00,75.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',14.00,0.00,'',3.00,0.00,500.00,'Idle','Game','Models/Kart/kart.j3o','-1',0,0,0,0,0,0,10,5,5.00,NULL,NULL),(4,13,'Some other machine',100.00,300.00,75.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',14.00,0.00,'',3.00,0.00,500.00,'Idle','Game','Models/Kart/kart.j3o','-1',0,0,0,0,0,0,10,5,5.00,NULL,NULL),(4,14,'Some other machine',100.00,300.00,75.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',14.00,0.00,'',3.00,0.00,500.00,'Idle','Game','Models/Kart/kart.j3o','-1',0,0,0,0,0,0,10,5,5.00,NULL,NULL),(4,15,'Some other machine',100.00,300.00,75.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',14.00,0.00,'',3.00,0.00,500.00,'Idle','Game','Models/Kart/kart.j3o','-1',0,0,0,0,0,0,10,5,5.00,NULL,NULL),(4,16,'Some other machine',100.00,300.00,75.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',14.00,0.00,'',3.00,0.00,500.00,'Idle','Game','Models/Kart/kart.j3o','-1',0,0,0,0,0,0,10,5,5.00,NULL,NULL),(4,17,'Some other machine',100.00,300.00,75.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',14.00,0.00,'',3.00,0.00,500.00,'Idle','Game','Models/Kart/kart.j3o','-1',0,0,0,0,0,0,10,5,5.00,NULL,NULL),(4,18,'Some other machine',100.00,300.00,75.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',14.00,0.00,'',3.00,0.00,500.00,'Idle','Game','Models/Kart/kart.j3o','-1',0,0,0,0,0,0,10,5,5.00,NULL,NULL),(4,19,'Some other machine',100.00,300.00,75.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',14.00,0.00,'',3.00,0.00,500.00,'Idle','Game','Models/Kart/kart.j3o','-1',0,0,0,0,0,0,10,5,5.00,NULL,NULL),(4,20,'Some other machine',100.00,300.00,75.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',14.00,0.00,'',3.00,0.00,500.00,'Idle','Game','Models/Kart/kart.j3o','-1',0,0,0,0,0,0,10,5,5.00,NULL,NULL),(4,21,'Some other machine',100.00,300.00,75.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',14.00,0.00,'',3.00,0.00,500.00,'Idle','Game','Models/Kart/kart.j3o','-1',0,0,0,0,0,0,10,5,5.00,NULL,NULL),(4,22,'Some other machine',100.00,300.00,75.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',14.00,0.00,'',3.00,0.00,500.00,'Idle','Game','Models/Kart/kart.j3o','-1',0,0,0,0,0,0,10,5,5.00,NULL,NULL),(4,23,'Some other machine',100.00,300.00,75.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',14.00,0.00,'',3.00,0.00,500.00,'Idle','Game','Models/Kart/kart.j3o','-1',0,0,0,0,0,0,10,5,5.00,NULL,NULL),(4,24,'Some other machine',100.00,300.00,75.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',14.00,0.00,'',3.00,0.00,500.00,'Idle','Game','Models/Kart/kart.j3o','-1',0,0,0,0,0,0,10,5,5.00,NULL,NULL),(4,25,'Some other machine',100.00,300.00,75.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',14.00,0.00,'',3.00,0.00,500.00,'Idle','Game','Models/Kart/kart.j3o','-1',0,0,0,0,0,0,10,5,5.00,NULL,NULL),(4,26,'Some other machine',100.00,300.00,75.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',14.00,0.00,'',3.00,0.00,500.00,'Idle','Game','Models/Kart/kart.j3o','-1',0,0,0,0,0,0,10,5,5.00,NULL,NULL),(4,27,'Some other machine',100.00,300.00,75.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',14.00,0.00,'',3.00,0.00,500.00,'Idle','Game','Models/Kart/kart.j3o','-1',0,0,0,0,0,0,10,5,5.00,NULL,NULL),(4,28,'Some other machine',100.00,300.00,75.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',14.00,0.00,'',3.00,0.00,500.00,'Idle','Game','Models/Kart/kart.j3o','-1',0,0,0,0,0,0,10,5,5.00,NULL,NULL),(4,29,'Some other machine',100.00,300.00,75.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',14.00,0.00,'',3.00,0.00,500.00,'Idle','Game','Models/Kart/kart.j3o','-1',0,0,0,0,0,0,10,5,5.00,NULL,NULL),(4,30,'Some other machine',100.00,300.00,75.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',14.00,0.00,'',3.00,0.00,500.00,'Idle','Game','Models/Kart/kart.j3o','-1',0,0,0,0,0,0,10,5,5.00,NULL,NULL),(4,31,'Some other machine',100.00,300.00,75.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',14.00,0.00,'',3.00,0.00,500.00,'Idle','Game','Models/Kart/kart.j3o','-1',0,0,0,0,0,0,10,5,5.00,NULL,NULL),(4,32,'Some other machine',100.00,300.00,75.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',14.00,0.00,'',3.00,0.00,500.00,'Idle','Game','Models/Kart/kart.j3o','-1',0,0,0,0,0,0,10,5,5.00,NULL,NULL),(4,33,'Some other machine',100.00,300.00,75.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',14.00,0.00,'',3.00,0.00,500.00,'Idle','Game','Models/Kart/kart.j3o','-1',0,0,0,0,0,0,10,5,5.00,NULL,NULL),(4,34,'Some other machine',100.00,300.00,75.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',14.00,0.00,'',3.00,0.00,500.00,'Idle','Game','Models/Kart/kart.j3o','-1',0,0,0,0,0,0,10,5,5.00,NULL,NULL),(4,35,'Some other machine',100.00,300.00,75.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',14.00,0.00,'',3.00,0.00,500.00,'Idle','Game','Models/Kart/kart.j3o','-1',0,0,0,0,0,0,10,5,5.00,NULL,NULL),(4,36,'Some other machine',100.00,300.00,75.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',14.00,0.00,'',3.00,0.00,500.00,'Idle','Game','Models/Kart/kart.j3o','-1',0,0,0,0,0,0,10,5,5.00,NULL,NULL),(4,37,'Some other machine',100.00,300.00,75.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',14.00,0.00,'',3.00,0.00,500.00,'Idle','Game','Models/Kart/kart.j3o','-1',0,0,0,0,0,0,10,5,5.00,NULL,NULL),(4,38,'Some other machine',100.00,300.00,75.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',14.00,0.00,'',3.00,0.00,500.00,'Idle','Game','Models/Kart/kart.j3o','-1',0,0,0,0,0,0,10,5,5.00,NULL,NULL),(4,39,'Some other machine',100.00,300.00,75.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',14.00,0.00,'',3.00,0.00,500.00,'Idle','Game','Models/Kart/kart.j3o','-1',0,0,0,0,0,0,10,5,5.00,NULL,NULL),(4,40,'Some other machine',100.00,300.00,75.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',14.00,0.00,'',3.00,0.00,500.00,'Idle','Game','Models/Kart/kart.j3o','-1',0,0,0,0,0,0,10,5,5.00,NULL,NULL),(4,41,'Some other machine',100.00,300.00,75.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',14.00,0.00,'',3.00,0.00,500.00,'Idle','Game','Models/Kart/kart.j3o','-1',0,0,0,0,0,0,10,5,5.00,NULL,NULL),(5,0,'Machine to transport',100.00,200.00,200.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',10.00,0.00,'',2.00,0.00,500.00,'Idle','User','Models/Kart/kart.j3o','-1',0,0,0,0,0,0,10,5,5.00,'Transport','Activate'),(5,6,'Machine to transport',100.00,200.00,200.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',10.00,0.00,'',2.00,0.00,500.00,'Idle','User','Models/Kart/kart.j3o','STATION6',3,2,0,0,-62,54,10,5,5.00,NULL,NULL),(5,7,'Machine to transport',100.00,200.00,200.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',10.00,0.00,'',2.00,0.00,500.00,'Idle','User','Models/Kart/kart.j3o','STATION6',3,2,0,0,-62,54,10,5,5.00,NULL,NULL),(5,8,'Machine to transport',100.00,200.00,200.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',10.00,0.00,'',2.00,0.00,500.00,'Idle','User','Models/Kart/kart.j3o','STATION6',3,2,0,0,-62,54,10,5,5.00,NULL,NULL),(5,9,'Machine to transport',100.00,200.00,200.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',10.00,0.00,'',2.00,0.00,500.00,'Idle','User','Models/Kart/kart.j3o','STATION6',3,2,0,0,-62,54,10,5,5.00,NULL,NULL),(5,10,'Machine to transport',100.00,200.00,200.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',10.00,0.00,'',2.00,0.00,500.00,'Idle','User','Models/Kart/kart.j3o','STATION6',3,2,0,0,-62,54,10,5,5.00,NULL,NULL),(5,11,'Machine to transport',100.00,200.00,200.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',10.00,0.00,'',2.00,0.00,500.00,'Idle','User','Models/Kart/kart.j3o','STATION6',3,2,0,0,-62,54,10,5,5.00,NULL,NULL),(5,12,'Machine to transport',100.00,200.00,200.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',10.00,0.00,'',2.00,0.00,500.00,'Idle','User','Models/Kart/kart.j3o','STATION6',3,2,0,0,-62,54,10,5,5.00,NULL,NULL),(5,13,'Machine to transport',100.00,200.00,200.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',10.00,0.00,'',2.00,0.00,500.00,'Idle','User','Models/Kart/kart.j3o','STATION6',3,2,0,0,-62,54,10,5,5.00,NULL,NULL),(5,14,'Machine to transport',100.00,200.00,200.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',10.00,0.00,'',2.00,0.00,500.00,'Idle','User','Models/Kart/kart.j3o','STATION6',3,2,0,0,-62,54,10,5,5.00,NULL,NULL),(5,15,'Machine to transport',100.00,200.00,200.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',10.00,0.00,'',2.00,0.00,500.00,'Idle','User','Models/Kart/kart.j3o','STATION6',3,2,0,0,-62,54,10,5,5.00,NULL,NULL),(5,16,'Machine to transport',100.00,200.00,200.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',10.00,0.00,'',2.00,0.00,500.00,'Idle','User','Models/Kart/kart.j3o','STATION6',3,2,0,0,-62,54,10,5,5.00,NULL,NULL),(5,17,'Machine to transport',100.00,200.00,200.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',10.00,0.00,'',2.00,0.00,500.00,'Idle','User','Models/Kart/kart.j3o','STATION6',3,2,0,0,-62,54,10,5,5.00,NULL,NULL),(5,18,'Machine to transport',100.00,200.00,200.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',10.00,0.00,'',2.00,0.00,500.00,'Busy','User','Models/Kart/kart.j3o','Walking',0,0,0,0,262,-7,10,5,5.00,NULL,NULL),(5,19,'Machine to transport',100.00,200.00,200.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',10.00,0.00,'',2.00,0.00,500.00,'Idle','User','Models/Kart/kart.j3o','STATION6',3,2,0,0,-62,54,10,5,5.00,NULL,NULL),(5,20,'Machine to transport',100.00,200.00,200.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',10.00,0.00,'',2.00,0.00,500.00,'Idle','User','Models/Kart/kart.j3o','STATION6',3,2,0,0,-62,54,10,5,5.00,NULL,NULL),(5,21,'Machine to transport',100.00,200.00,200.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',10.00,0.00,'',2.00,0.00,500.00,'Idle','User','Models/Kart/kart.j3o','STATION6',3,2,0,0,-62,54,10,5,5.00,NULL,NULL),(5,22,'Machine to transport',100.00,200.00,200.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',10.00,0.00,'',2.00,0.00,500.00,'Idle','User','Models/Kart/kart.j3o','STATION6',3,2,0,0,-62,54,10,5,5.00,NULL,NULL),(5,23,'Machine to transport',100.00,200.00,200.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',10.00,0.00,'',2.00,0.00,500.00,'Idle','User','Models/Kart/kart.j3o','STATION6',3,2,0,0,-62,54,10,5,5.00,NULL,NULL),(5,24,'Machine to transport',100.00,200.00,200.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',10.00,0.00,'',2.00,0.00,500.00,'Idle','User','Models/Kart/kart.j3o','STATION6',3,2,0,0,-62,54,10,5,5.00,NULL,NULL),(5,25,'Machine to transport',100.00,200.00,200.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',10.00,0.00,'',2.00,0.00,500.00,'Idle','User','Models/Kart/kart.j3o','STATION6',3,2,0,0,-62,54,10,5,5.00,NULL,NULL),(5,26,'Machine to transport',100.00,200.00,200.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',10.00,0.00,'',2.00,0.00,500.00,'Idle','User','Models/Kart/kart.j3o','STATION6',3,2,0,0,-62,54,10,5,5.00,NULL,NULL),(5,27,'Machine to transport',100.00,200.00,200.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',10.00,0.00,'',2.00,0.00,500.00,'Idle','User','Models/Kart/kart.j3o','STATION6',3,2,0,0,-62,54,10,5,5.00,NULL,NULL),(5,28,'Machine to transport',100.00,200.00,200.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',10.00,0.00,'',2.00,0.00,500.00,'Busy','User','Models/Kart/kart.j3o','STATION1',0,0,0,0,262,-12,10,5,5.00,NULL,NULL),(5,29,'Machine to transport',100.00,200.00,200.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',10.00,0.00,'',2.00,0.00,500.00,'Idle','User','Models/Kart/kart.j3o','STATION6',3,2,0,0,-62,54,10,5,5.00,NULL,NULL),(5,30,'Machine to transport',100.00,200.00,200.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',10.00,0.00,'',2.00,0.00,500.00,'Idle','User','Models/Kart/kart.j3o','STATION6',3,2,0,0,-62,54,10,5,5.00,NULL,NULL),(5,31,'Machine to transport',100.00,200.00,200.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',10.00,0.00,'',2.00,0.00,500.00,'Idle','User','Models/Kart/kart.j3o','STATION6',3,2,0,0,-62,54,10,5,5.00,NULL,NULL),(5,32,'Machine to transport',100.00,200.00,200.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',10.00,0.00,'',2.00,0.00,500.00,'Idle','User','Models/Kart/kart.j3o','STATION6',3,2,0,0,-62,54,10,5,5.00,NULL,NULL),(5,33,'Machine to transport',100.00,200.00,200.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',10.00,0.00,'',2.00,0.00,500.00,'Idle','User','Models/Kart/kart.j3o','STATION6',3,2,0,0,-62,54,10,5,5.00,NULL,NULL),(5,34,'Machine to transport',100.00,200.00,200.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',10.00,0.00,'',2.00,0.00,500.00,'Idle','User','Models/Kart/kart.j3o','STATION6',3,2,0,0,-62,54,10,5,5.00,NULL,NULL),(5,35,'Machine to transport',100.00,200.00,200.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',10.00,0.00,'',2.00,0.00,500.00,'Idle','User','Models/Kart/kart.j3o','STATION6',3,2,0,0,-62,54,10,5,5.00,NULL,NULL),(5,36,'Machine to transport',100.00,200.00,200.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',10.00,0.00,'',2.00,0.00,500.00,'Idle','User','Models/Kart/kart.j3o','STATION6',3,2,0,0,-62,54,10,5,5.00,NULL,NULL),(5,37,'Machine to transport',100.00,200.00,200.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',10.00,0.00,'',2.00,0.00,500.00,'Idle','User','Models/Kart/kart.j3o','STATION6',3,2,0,0,-62,54,10,5,5.00,NULL,NULL),(5,38,'Machine to transport',100.00,200.00,200.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',10.00,0.00,'',2.00,0.00,500.00,'Idle','User','Models/Kart/kart.j3o','STATION6',3,2,0,0,-62,54,10,5,5.00,NULL,NULL),(5,39,'Machine to transport',100.00,200.00,200.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',10.00,0.00,'',2.00,0.00,500.00,'Idle','User','Models/Kart/kart.j3o','STATION6',3,2,0,0,-62,54,10,5,5.00,NULL,NULL),(5,40,'Machine to transport',100.00,200.00,200.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',10.00,0.00,'',2.00,0.00,500.00,'Idle','User','Models/Kart/kart.j3o','STATION6',3,2,0,0,-62,54,10,5,5.00,NULL,NULL),(5,41,'Machine to transport',100.00,200.00,200.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',10.00,0.00,'',2.00,0.00,500.00,'Idle','User','Models/Kart/kart.j3o','STATION6',3,2,0,0,-62,54,10,5,5.00,NULL,NULL),(6,0,'Machine to transport',100.00,200.00,200.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',16.00,0.00,'',5.00,0.00,500.00,'Idle','User','Models/Kart/kart.j3o','-1',0,0,0,0,0,0,10,5,5.00,'Transport','Activate'),(7,0,'Machine to transport',100.00,200.00,200.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',16.00,0.00,'',5.00,0.00,500.00,'Idle','User','Models/Kart/kart.j3o','-1',0,0,0,0,0,0,10,5,5.00,'Transport','Activate'),(8,0,'Machine to assemble',100.00,200.00,200.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',16.00,0.00,'',5.00,0.00,500.00,'Idle','User','Models/Machine/machine1.j3o','-1',0,0,0,0,0,0,10,20,5.00,'Operation','Activate'),(9,0,'Machine to assemble',100.00,200.00,200.00,'',0.95,0.00,'',0.97,0.00,'',0.95,0.00,'',16.00,0.00,'',5.00,0.00,500.00,'Idle','User','Models/Machine/machine1.j3o','-1',0,0,0,0,0,0,10,20,5.00,'Operation','Activate');
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
INSERT INTO `t_operation` VALUES (3,0,'Assemble Part3',0,1,2),(3,17,'Assemble Part3',0,1,2),(3,18,'Assemble Part3',0,1,2),(3,19,'Assemble Part3',0,1,2),(3,20,'Assemble Part3',0,1,2),(3,21,'Assemble Part3',0,1,2),(3,22,'Assemble Part3',0,1,2),(3,23,'Assemble Part3',0,1,2),(3,24,'Assemble Part3',0,1,2),(3,25,'Assemble Part3',0,1,2),(3,26,'Assemble Part3',0,1,2),(3,27,'Assemble Part3',0,1,2),(3,28,'Assemble Part3',0,1,2),(3,29,'Assemble Part3',0,1,2),(3,30,'Assemble Part3',0,1,2),(3,31,'Assemble Part3',0,1,2),(3,32,'Assemble Part3',0,1,2),(3,33,'Assemble Part3',0,1,2),(3,34,'Assemble Part3',0,1,2),(3,35,'Assemble Part3',0,1,2),(3,36,'Assemble Part3',0,1,2),(3,37,'Assemble Part3',0,1,2),(3,38,'Assemble Part3',0,1,2),(3,39,'Assemble Part3',0,1,2),(3,40,'Assemble Part3',0,1,2),(3,41,'Assemble Part3',0,1,2);
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
  `salary_per_hour` decimal(5,2) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `owner` varchar(10) DEFAULT NULL,
  `virtual_id_location` varchar(45) DEFAULT NULL,
  `virtual_matrix_i` int(11) DEFAULT NULL,
  `virtual_matrix_j` int(11) DEFAULT NULL,
  `current_location_x` int(11) DEFAULT NULL,
  `current_location_z` int(11) DEFAULT NULL,
  `speed` decimal(5,2) DEFAULT NULL,
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
INSERT INTO `t_operator` VALUES (1,0,'Pepe',8.00,'Idle','User','-1',0,0,0,0,20.00,0,-1,0,0,'None',0,'Activate'),(1,1,'Pepe',8.00,'Idle','User','STATION5',0,0,34,44,20.00,0,-1,0,0,'None',0,NULL),(1,2,'Pepe',8.00,'Idle','User','STATION5',0,0,34,44,20.00,0,-1,0,0,'None',0,NULL),(1,3,'Pepe',8.00,'Idle','User','STATION5',0,0,34,44,20.00,0,-1,0,0,'None',0,NULL),(1,4,'Pepe',8.00,'Idle','User','STATION5',0,0,34,44,20.00,0,-1,0,0,'None',0,NULL),(1,5,'Pepe',8.00,'Busy','User','Walking',0,0,-35,-77,20.00,0,-1,0,0,'None',0,NULL),(1,6,'Pepe',8.00,'Idle','User','STATION5',0,0,34,44,20.00,0,-1,0,0,'None',0,NULL),(1,7,'Pepe',8.00,'Idle','User','STATION5',0,0,34,44,20.00,0,-1,0,0,'None',0,NULL),(1,8,'Pepe',8.00,'Idle','User','STATION5',0,0,34,44,20.00,0,-1,0,0,'None',0,NULL),(1,9,'Pepe',8.00,'Idle','User','STATION5',0,0,34,44,20.00,0,-1,0,0,'None',0,NULL),(1,10,'Pepe',8.00,'Idle','User','STATION5',0,0,34,44,20.00,0,-1,0,0,'None',0,NULL),(1,11,'Pepe',8.00,'Idle','User','STATION5',0,0,34,44,20.00,0,-1,0,0,'None',0,NULL),(1,12,'Pepe',8.00,'Idle','User','STATION5',0,0,34,44,20.00,0,-1,0,0,'None',0,NULL),(1,13,'Pepe',8.00,'Idle','User','STATION5',0,0,34,44,20.00,0,-1,0,0,'None',0,NULL),(1,14,'Pepe',8.00,'Idle','User','STATION5',0,0,34,44,20.00,0,-1,0,0,'None',0,NULL),(1,15,'Pepe',8.00,'Idle','User','STATION5',0,0,34,44,20.00,0,-1,0,0,'None',0,NULL),(1,16,'Pepe',8.00,'Idle','User','STATION5',0,0,34,44,20.00,0,-1,0,0,'None',0,NULL),(1,17,'Pepe',8.00,'Idle','User','STATION5',0,0,34,44,20.00,0,-1,0,0,'None',0,NULL),(1,18,'Pepe',8.00,'Busy','User','Walking',0,0,28,-92,20.00,0,-1,0,0,'None',0,NULL),(1,19,'Pepe',8.00,'Idle','User','STATION5',0,0,34,44,20.00,0,-1,0,0,'None',0,NULL),(1,20,'Pepe',8.00,'Idle','User','STATION5',0,0,34,44,20.00,0,-1,0,0,'None',0,NULL),(1,21,'Pepe',8.00,'Idle','User','STATION5',0,0,34,44,20.00,0,-1,0,0,'None',0,NULL),(1,22,'Pepe',8.00,'Idle','User','STATION5',0,0,34,44,20.00,0,-1,0,0,'None',0,NULL),(1,23,'Pepe',8.00,'Idle','User','STATION5',0,0,34,44,20.00,0,-1,0,0,'None',0,NULL),(1,24,'Pepe',8.00,'Idle','User','STATION5',0,0,34,44,20.00,0,-1,0,0,'None',0,NULL),(1,25,'Pepe',8.00,'Idle','User','STATION5',0,0,34,44,20.00,0,-1,0,0,'None',0,NULL),(1,26,'Pepe',8.00,'Idle','User','STATION5',0,0,34,44,20.00,0,-1,0,0,'None',0,NULL),(1,27,'Pepe',8.00,'Idle','User','STATION5',0,0,34,44,20.00,0,-1,0,0,'None',0,NULL),(1,28,'Pepe',8.00,'Busy','User','Walking',0,0,54,-82,20.00,1,3,-107,-82,'None',0,NULL),(1,29,'Pepe',8.00,'Idle','User','STATION5',0,0,34,44,20.00,0,-1,0,0,'None',0,NULL),(1,30,'Pepe',8.00,'Idle','User','STATION5',0,0,34,44,20.00,0,-1,0,0,'None',0,NULL),(1,31,'Pepe',8.00,'Idle','User','STATION5',0,0,34,44,20.00,0,-1,0,0,'None',0,NULL),(1,32,'Pepe',8.00,'Busy','User','Walking',0,0,10,-82,20.00,1,3,67,-82,'None',5,NULL),(1,33,'Pepe',8.00,'Idle','User','STATION5',0,0,34,44,20.00,0,-1,0,0,'None',0,NULL),(1,34,'Pepe',8.00,'Idle','User','STATION5',0,0,34,44,20.00,0,-1,0,0,'None',0,NULL),(1,35,'Pepe',8.00,'Idle','User','STATION5',0,0,34,44,20.00,0,-1,0,0,'None',0,NULL),(1,36,'Pepe',8.00,'Busy','User','Walking',0,0,67,-33,20.00,1,-1,67,-84,'None',5,NULL),(1,37,'Pepe',8.00,'Idle','User','STATION5',0,0,34,44,20.00,0,-1,0,0,'None',0,NULL),(1,38,'Pepe',8.00,'Idle','User','STATION5',0,0,34,44,20.00,0,-1,0,0,'None',0,NULL),(1,39,'Pepe',8.00,'Idle','User','STATION5',0,0,34,44,20.00,0,-1,0,0,'None',0,NULL),(1,40,'Pepe',8.00,'Idle','User','STATION5',0,0,34,44,20.00,0,-1,0,0,'None',0,NULL),(1,41,'Pepe',8.00,'Idle','User','STATION5',0,0,34,44,20.00,0,-1,0,0,'None',0,NULL),(2,0,'Bob',8.00,'Idle','User','-1',0,0,0,0,22.00,0,-1,0,0,'None',0,'Activate'),(2,1,'Bob',8.00,'Idle','User','STATION5',0,1,34,49,22.00,0,-1,0,0,'None',0,NULL),(2,2,'Bob',8.00,'Idle','User','STATION5',0,1,34,49,22.00,0,-1,0,0,'None',0,NULL),(2,3,'Bob',8.00,'Idle','User','STATION5',0,1,34,49,22.00,0,-1,0,0,'None',0,NULL),(2,4,'Bob',8.00,'Idle','User','STATION5',0,1,34,49,22.00,0,-1,0,0,'None',0,NULL),(2,5,'Bob',8.00,'Idle','User','STATION5',0,1,34,49,22.00,0,-1,0,0,'None',0,NULL),(2,6,'Bob',8.00,'Idle','User','STATION5',0,1,34,49,22.00,0,-1,0,0,'None',0,NULL),(2,7,'Bob',8.00,'Idle','User','STATION5',0,1,34,49,22.00,0,-1,0,0,'None',0,NULL),(2,8,'Bob',8.00,'Idle','User','STATION5',0,1,34,49,22.00,0,-1,0,0,'None',0,NULL),(2,9,'Bob',8.00,'Idle','User','STATION5',0,1,34,49,22.00,0,-1,0,0,'None',0,NULL),(2,10,'Bob',8.00,'Idle','User','STATION5',0,1,34,49,22.00,0,-1,0,0,'None',0,NULL),(2,11,'Bob',8.00,'Idle','User','STATION5',0,1,34,49,22.00,0,-1,0,0,'None',0,NULL),(2,12,'Bob',8.00,'Idle','User','STATION5',0,1,34,49,22.00,0,-1,0,0,'None',0,NULL),(2,13,'Bob',8.00,'Idle','User','STATION5',0,1,34,49,22.00,0,-1,0,0,'None',0,NULL),(2,14,'Bob',8.00,'Idle','User','STATION5',0,1,34,49,22.00,0,-1,0,0,'None',0,NULL),(2,15,'Bob',8.00,'Idle','User','STATION5',0,1,34,49,22.00,0,-1,0,0,'None',0,NULL),(2,16,'Bob',8.00,'Idle','User','STATION5',0,1,34,49,22.00,0,-1,0,0,'None',0,NULL),(2,17,'Bob',8.00,'Idle','User','STATION5',0,1,34,49,22.00,0,-1,0,0,'None',0,NULL),(2,18,'Bob',8.00,'Busy','User','Walking',0,0,184,57,22.00,0,-1,0,0,'None',0,NULL),(2,19,'Bob',8.00,'Idle','User','STATION5',0,1,34,49,22.00,0,-1,0,0,'None',0,NULL),(2,20,'Bob',8.00,'Idle','User','STATION5',0,1,34,49,22.00,0,-1,0,0,'None',0,NULL),(2,21,'Bob',8.00,'Idle','User','STATION5',0,1,34,49,22.00,0,-1,0,0,'None',0,NULL),(2,22,'Bob',8.00,'Idle','User','STATION5',0,1,34,49,22.00,0,-1,0,0,'None',0,NULL),(2,23,'Bob',8.00,'Idle','User','STATION5',0,1,34,49,22.00,0,-1,0,0,'None',0,NULL),(2,24,'Bob',8.00,'Idle','User','STATION5',0,1,34,49,22.00,0,-1,0,0,'None',0,NULL),(2,25,'Bob',8.00,'Idle','User','STATION5',0,1,34,49,22.00,0,-1,0,0,'None',0,NULL),(2,26,'Bob',8.00,'Idle','User','STATION5',0,1,34,49,22.00,0,-1,0,0,'None',0,NULL),(2,27,'Bob',8.00,'Idle','User','STATION5',0,1,34,49,22.00,0,-1,0,0,'None',0,NULL),(2,28,'Bob',8.00,'Busy','User','STATION1',0,0,148,-77,22.00,1,5,67,-77,'None',0,NULL),(2,29,'Bob',8.00,'Idle','User','STATION5',0,1,34,49,22.00,0,-1,0,0,'None',0,NULL),(2,30,'Bob',8.00,'Idle','User','STATION5',0,1,34,49,22.00,0,-1,0,0,'None',0,NULL),(2,31,'Bob',8.00,'Idle','User','STATION5',0,1,34,49,22.00,0,-1,0,0,'None',0,NULL),(2,32,'Bob',8.00,'Idle','User','STATION5',0,1,34,49,22.00,0,-1,0,0,'None',0,NULL),(2,33,'Bob',8.00,'Idle','User','STATION5',0,1,34,49,22.00,0,-1,0,0,'None',0,NULL),(2,34,'Bob',8.00,'Idle','User','STATION5',0,1,34,49,22.00,0,-1,0,0,'None',0,NULL),(2,35,'Bob',8.00,'Idle','User','STATION5',0,1,34,49,22.00,0,-1,0,0,'None',0,NULL),(2,36,'Bob',8.00,'Idle','User','STATION5',0,1,34,49,22.00,0,-1,0,0,'None',0,NULL),(2,37,'Bob',8.00,'Idle','User','STATION5',0,1,34,49,22.00,0,-1,0,0,'None',0,NULL),(2,38,'Bob',8.00,'Idle','User','STATION5',0,1,34,49,22.00,0,-1,0,0,'None',0,NULL),(2,39,'Bob',8.00,'Idle','User','STATION5',0,1,34,49,22.00,0,-1,0,0,'None',0,NULL),(2,40,'Bob',8.00,'Idle','User','STATION5',0,1,34,49,22.00,0,-1,0,0,'None',0,NULL),(2,41,'Bob',8.00,'Idle','User','STATION5',0,1,34,49,22.00,0,-1,0,0,'None',0,NULL),(3,0,'Juan',8.00,'Idle','User','-1',0,0,0,0,25.00,0,-1,0,0,'None',0,'Activate'),(3,1,'Juan',8.00,'Busy','User','Walking',0,0,-87,-82,25.00,0,-1,0,0,'None',0,NULL),(3,2,'Juan',8.00,'Busy','User','Walking',0,0,-66,-82,25.00,0,-1,0,0,'None',0,NULL),(3,3,'Juan',8.00,'Idle','User','STATION1',0,0,67,-82,25.00,0,-1,0,0,'None',0,NULL),(3,4,'Juan',8.00,'Busy','User','Walking',0,0,49,-82,25.00,0,-1,0,0,'None',0,NULL),(3,5,'Juan',8.00,'Idle','User','STATION1',0,0,67,-87,25.00,0,-1,0,0,'None',0,NULL),(3,6,'Juan',8.00,'Busy','User','Walking',0,0,-16,-82,25.00,0,-1,0,0,'None',0,NULL),(3,7,'Juan',8.00,'Busy','User','Walking',0,0,-107,-17,25.00,0,-1,0,0,'None',0,NULL),(3,8,'Juan',8.00,'Busy','User','Walking',0,0,-95,27,25.00,0,-1,0,0,'None',0,NULL),(3,9,'Juan',8.00,'Busy','User','Walking',0,0,-64,-82,25.00,0,-1,0,0,'None',0,NULL),(3,10,'Juan',8.00,'Busy','User','Walking',0,0,-107,-16,25.00,0,-1,0,0,'None',0,NULL),(3,11,'Juan',8.00,'Busy','User','Walking',0,0,-65,-82,25.00,0,-1,0,0,'None',0,NULL),(3,12,'Juan',8.00,'Busy','User','Walking',0,0,41,-86,25.00,0,-1,0,0,'None',0,NULL),(3,13,'Juan',8.00,'Busy','User','Walking',0,0,-76,-82,25.00,0,-1,0,0,'None',0,NULL),(3,14,'Juan',8.00,'Busy','User','Walking',0,0,-107,-29,25.00,0,-1,0,0,'None',0,NULL),(3,15,'Juan',8.00,'Busy','User','Walking',0,0,-107,17,25.00,0,-1,0,0,'None',0,NULL),(3,16,'Juan',8.00,'Busy','User','Walking',0,0,-41,-82,25.00,0,-1,0,0,'None',0,NULL),(3,17,'Juan',8.00,'Busy','User','Walking',0,0,-107,-77,25.00,0,-1,0,0,'None',0,NULL),(3,18,'Juan',8.00,'Idle','User','STATION1',0,0,67,-82,25.00,0,-1,0,0,'None',0,NULL),(3,19,'Juan',8.00,'Busy','User','Walking',0,0,-18,47,25.00,0,-1,0,0,'None',0,NULL),(3,20,'Juan',8.00,'Idle','User','STATION5',0,2,34,54,25.00,0,-1,0,0,'None',0,NULL),(3,21,'Juan',8.00,'Idle','User','STATION5',0,2,34,54,25.00,0,-1,0,0,'None',0,NULL),(3,22,'Juan',8.00,'Idle','User','STATION5',0,2,34,54,25.00,0,-1,0,0,'None',0,NULL),(3,23,'Juan',8.00,'Idle','User','STATION5',0,2,34,54,25.00,0,-1,0,0,'None',0,NULL),(3,24,'Juan',8.00,'Idle','User','STATION5',0,2,34,54,25.00,0,-1,0,0,'None',0,NULL),(3,25,'Juan',8.00,'Busy','User','Walking',0,0,-31,47,25.00,1,-1,-62,47,'None',0,NULL),(3,26,'Juan',8.00,'Busy','User','Walking',0,0,-21,47,25.00,1,-1,-62,47,'None',0,NULL),(3,27,'Juan',8.00,'Busy','User','Walking',0,0,-14,47,25.00,1,-1,-62,47,'None',0,NULL),(3,28,'Juan',8.00,'Idle','User','STATION1',0,0,67,-87,25.00,0,-1,67,-87,'None',0,NULL),(3,29,'Juan',8.00,'Busy','User','Walking',0,0,15,-82,25.00,1,3,-107,-82,'None',0,NULL),(3,30,'Juan',8.00,'Busy','User','Walking',0,0,-13,47,25.00,1,-1,-62,47,'None',0,NULL),(3,31,'Juan',8.00,'Busy','User','Walking',0,0,-32,-82,25.00,1,3,67,-82,'None',0,NULL),(3,32,'Juan',8.00,'Busy','User','Walking',0,0,-64,-87,25.00,1,2,67,-87,'None',4,NULL),(3,33,'Juan',8.00,'Busy','User','Walking',0,0,31,-92,25.00,1,3,-107,-82,'None',5,NULL),(3,34,'Juan',8.00,'Busy','User','Walking',0,0,-54,-82,25.00,1,3,67,-82,'None',5,NULL),(3,35,'Juan',8.00,'Busy','User','Walking',0,0,15,-82,25.00,1,3,-107,-82,'None',5,NULL),(3,36,'Juan',8.00,'Busy','User','Walking',0,0,-68,-82,25.00,1,2,-107,-82,'None',4,NULL),(3,37,'Juan',8.00,'Busy','User','STATION2',0,0,-78,28,25.00,0,-1,-107,-77,'Transport',5,NULL),(3,38,'Juan',8.00,'Busy','User','STATION2',0,0,-107,-12,25.00,0,-1,-107,-77,'Transport',5,NULL),(3,39,'Juan',8.00,'Busy','User','STATION2',0,0,-107,-12,25.00,0,-1,-107,-77,'Transport',5,NULL),(3,40,'Juan',8.00,'Busy','User','Walking',0,0,-43,47,25.00,1,-1,-62,47,'Transport',5,NULL),(3,41,'Juan',8.00,'Busy','User','Walking',0,0,-2,-82,25.00,1,3,67,-82,'Transport',5,NULL),(4,0,'Maria',8.00,'Idle','User','-1',0,0,0,0,15.00,0,-1,0,0,'None',0,'Activate'),(4,1,'Maria',8.00,'Busy','User','Walking',0,0,43,-62,15.00,0,-1,0,0,'None',0,NULL),(4,2,'Maria',8.00,'Busy','User','Walking',0,0,-107,-2,15.00,0,-1,0,0,'None',0,NULL),(4,3,'Maria',8.00,'Busy','User','Walking',0,0,-102,-87,15.00,0,-1,0,0,'None',0,NULL),(4,4,'Maria',8.00,'Busy','User','STATION1',0,0,43,-62,15.00,0,-1,0,0,'None',0,NULL),(4,5,'Maria',8.00,'Idle','User','STATION1',0,0,43,-62,15.00,0,-1,0,0,'None',0,NULL),(4,6,'Maria',8.00,'Busy','User','Walking',0,0,-37,-87,15.00,0,-1,0,0,'None',0,NULL),(4,7,'Maria',8.00,'Busy','User','Walking',0,0,-67,37,15.00,0,-1,0,0,'None',0,NULL),(4,8,'Maria',8.00,'Busy','User','Walking',0,0,-48,42,15.00,0,-1,0,0,'None',0,NULL),(4,9,'Maria',8.00,'Busy','User','Walking',0,0,-107,-3,15.00,0,-1,0,0,'None',0,NULL),(4,10,'Maria',8.00,'Busy','User','Walking',0,0,-67,37,15.00,0,-1,0,0,'None',0,NULL),(4,11,'Maria',8.00,'Busy','User','Walking',0,0,-107,-19,15.00,0,-1,0,0,'None',0,NULL),(4,12,'Maria',8.00,'Busy','User','Walking',0,0,-107,-76,15.00,0,-1,0,0,'None',0,NULL),(4,13,'Maria',8.00,'Busy','User','Walking',0,0,-107,5,15.00,0,-1,0,0,'None',0,NULL),(4,14,'Maria',8.00,'Busy','User','Walking',0,0,-87,27,15.00,0,-1,0,0,'None',0,NULL),(4,15,'Maria',8.00,'Busy','User','Walking',0,0,-58,42,15.00,0,-1,0,0,'None',0,NULL),(4,16,'Maria',8.00,'Busy','User','Walking',0,0,-107,-49,15.00,0,-1,0,0,'None',0,NULL),(4,17,'Maria',8.00,'Busy','User','Walking',0,0,-98,26,15.00,0,-1,0,0,'None',0,NULL),(4,18,'Maria',8.00,'Idle','User','STATION1',0,0,43,-62,15.00,0,-1,0,0,'None',0,NULL),(4,19,'Maria',8.00,'Busy','User','Walking',0,0,7,42,15.00,0,-1,0,0,'None',0,NULL),(4,20,'Maria',8.00,'Idle','User','STATION5',0,3,34,59,15.00,0,-1,0,0,'None',0,NULL),(4,21,'Maria',8.00,'Idle','User','STATION5',0,3,34,59,15.00,0,-1,0,0,'None',0,NULL),(4,22,'Maria',8.00,'Idle','User','STATION5',0,3,34,59,15.00,0,-1,0,0,'None',0,NULL),(4,23,'Maria',8.00,'Idle','User','STATION5',0,3,34,59,15.00,0,-1,0,0,'None',0,NULL),(4,24,'Maria',8.00,'Idle','User','STATION5',0,3,34,59,15.00,0,-1,0,0,'None',0,NULL),(4,25,'Maria',8.00,'Busy','User','Walking',0,0,-1,42,15.00,1,-1,-62,42,'None',0,NULL),(4,26,'Maria',8.00,'Busy','User','Walking',0,0,5,42,15.00,1,-1,-62,42,'None',0,NULL),(4,27,'Maria',8.00,'Busy','User','Walking',0,0,9,42,15.00,1,-1,-62,42,'None',0,NULL),(4,28,'Maria',8.00,'Busy','User','STATION1',0,0,43,-62,15.00,0,-1,43,-62,'None',0,NULL),(4,29,'Maria',8.00,'Busy','User','Walking',0,0,-55,-87,15.00,1,2,67,-87,'None',0,NULL),(4,30,'Maria',8.00,'Busy','User','Walking',0,0,10,42,15.00,1,-1,-62,42,'None',0,NULL),(4,31,'Maria',8.00,'Busy','User','Walking',0,0,-107,-23,15.00,1,2,-107,-82,'None',0,NULL),(4,32,'Maria',8.00,'Idle','User','STATION1',0,0,43,-62,15.00,0,-1,43,-62,'None',0,NULL),(4,33,'Maria',8.00,'Busy','User','Walking',0,0,-67,-87,15.00,1,2,67,-87,'None',4,NULL),(4,34,'Maria',8.00,'Busy','User','Walking',0,0,-46,-87,15.00,1,2,67,-87,'None',4,NULL),(4,35,'Maria',8.00,'Busy','User','Walking',0,0,-55,-87,15.00,1,2,67,-87,'None',4,NULL),(4,36,'Maria',8.00,'Idle','User','STATION1',0,0,43,-62,15.00,0,-1,43,-62,'None',0,NULL),(4,37,'Maria',8.00,'Busy','User','Walking',0,0,-107,-78,15.00,1,2,-107,-82,'Transport',4,NULL),(4,38,'Maria',8.00,'Busy','User','STATION2',0,0,-107,-82,15.00,0,-1,-107,-82,'Transport',4,NULL),(4,39,'Maria',8.00,'Busy','User','STATION2',0,0,-107,-82,15.00,0,-1,-107,-82,'Transport',4,NULL),(4,40,'Maria',8.00,'Busy','User','Walking',0,0,-9,42,15.00,1,-1,-62,42,'Transport',4,NULL),(4,41,'Maria',8.00,'Busy','User','Walking',0,0,-46,-87,15.00,1,2,67,-87,'Transport',4,NULL),(5,0,'Luis',10.00,'Idle','User','-1',0,0,0,0,30.00,0,-1,0,0,'None',0,'Activate'),(6,0,'Carlos',12.00,'Idle','User','-1',0,0,0,0,35.00,0,-1,0,0,'None',0,'Activate');
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
  `percentage_variability_min` decimal(6,2) DEFAULT NULL,
  `percentage_variability_max` decimal(6,2) DEFAULT NULL,
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
INSERT INTO `t_order` VALUES (0,0,3,5,20,60,80.00,120.00),(1,0,3,20,120,60,80.00,120.00),(2,0,3,10,60,60,80.00,102.00),(3,0,3,5,30,60,80.00,120.00),(4,0,3,12,40,60,80.00,120.00),(5,0,3,24,100,60,80.00,120.00),(6,0,3,8,20,60,80.00,120.00),(7,0,3,60,120,60,80.00,120.00),(8,0,3,20,150,60,80.00,120.00),(9,0,3,15,50,60,80.00,120.00);
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
  `volume` decimal(5,2) DEFAULT NULL,
  `weight` decimal(5,2) DEFAULT NULL,
  `quantity_palette` int(11) DEFAULT NULL,
  `current_stock` int(11) DEFAULT NULL,
  `price_for_sale` decimal(5,2) DEFAULT NULL,
  `owner` varchar(10) DEFAULT NULL,
  `part_design` varchar(100) DEFAULT NULL,
  `part_design_scale` decimal(5,2) DEFAULT NULL,
  `part_design_color` varchar(45) DEFAULT NULL,
  `factor` decimal(5,2) DEFAULT NULL,
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
INSERT INTO `t_part` VALUES (1,0,'Part One','EACH',100.00,100.00,100,0,0.25,'User','Models/Screw/screw.j3o',0.01,'Red',0.97),(1,1,'Part One','EACH',100.00,100.00,100,100,0.00,'User','Models/Screw/screw.j3o',0.01,'Red',0.97),(1,2,'Part One','EACH',100.00,100.00,100,100,0.00,'User','Models/Screw/screw.j3o',0.01,'Red',0.97),(1,3,'Part One','EACH',100.00,100.00,100,100,0.00,'User','Models/Screw/screw.j3o',0.01,'Red',0.97),(1,4,'Part One','EACH',100.00,100.00,100,96,0.00,'User','Models/Screw/screw.j3o',0.01,'Red',0.97),(1,5,'Part One','EACH',100.00,100.00,100,92,0.00,'User','Models/Screw/screw.j3o',0.01,'Red',0.97),(1,6,'Part One','EACH',100.00,100.00,100,100,0.00,'User','Models/Screw/screw.j3o',0.01,'Red',0.97),(1,7,'Part One','EACH',100.00,100.00,100,100,0.00,'User','Models/Screw/screw.j3o',0.01,'Red',0.97),(1,8,'Part One','EACH',100.00,100.00,100,100,0.00,'User','Models/Screw/screw.j3o',0.01,'Red',0.97),(1,9,'Part One','EACH',100.00,100.00,100,100,0.00,'User','Models/Screw/screw.j3o',0.01,'Red',0.97),(1,10,'Part One','EACH',100.00,100.00,100,100,0.00,'User','Models/Screw/screw.j3o',0.01,'Red',0.97),(1,11,'Part One','EACH',100.00,100.00,100,100,0.00,'User','Models/Screw/screw.j3o',0.01,'Red',0.97),(1,12,'Part One','EACH',100.00,100.00,100,100,0.00,'User','Models/Screw/screw.j3o',0.01,'Red',0.97),(1,13,'Part One','EACH',100.00,100.00,100,100,0.00,'User','Models/Screw/screw.j3o',0.01,'Red',0.97),(1,14,'Part One','EACH',100.00,100.00,100,100,0.00,'User','Models/Screw/screw.j3o',0.01,'Red',0.97),(1,15,'Part One','EACH',100.00,100.00,100,100,0.00,'User','Models/Screw/screw.j3o',0.01,'Red',0.97),(1,16,'Part One','EACH',100.00,100.00,100,100,0.00,'User','Models/Screw/screw.j3o',0.01,'Red',0.97),(1,17,'Part One','EACH',100.00,100.00,100,100,0.00,'User','Models/Screw/screw.j3o',0.01,'Red',0.97),(1,18,'Part One','EACH',100.00,100.00,100,64,0.00,'User','Models/Screw/screw.j3o',0.01,'Red',0.97),(1,19,'Part One','EACH',100.00,100.00,100,100,0.00,'User','Models/Screw/screw.j3o',0.01,'Red',0.97),(1,20,'Part One','EACH',100.00,100.00,100,0,0.00,'User','Models/Screw/screw.j3o',0.01,'Red',0.97),(1,21,'Part One','EACH',100.00,100.00,100,0,0.00,'User','Models/Screw/screw.j3o',0.01,'Red',0.97),(1,22,'Part One','EACH',100.00,100.00,100,0,0.00,'User','Models/Screw/screw.j3o',0.01,'Red',0.97),(1,23,'Part One','EACH',100.00,100.00,100,0,0.00,'User','Models/Screw/screw.j3o',0.01,'Red',0.97),(1,24,'Part One','EACH',100.00,100.00,100,0,0.00,'User','Models/Screw/screw.j3o',0.01,'Red',0.97),(1,25,'Part One','EACH',100.00,100.00,100,100,0.00,'User','Models/Screw/screw.j3o',0.01,'Red',0.97),(1,26,'Part One','EACH',100.00,100.00,100,100,0.00,'User','Models/Screw/screw.j3o',0.01,'Red',0.97),(1,27,'Part One','EACH',100.00,100.00,100,100,0.00,'User','Models/Screw/screw.j3o',0.01,'Red',0.97),(1,28,'Part One','EACH',100.00,100.00,100,16,0.00,'User','Models/Screw/screw.j3o',0.01,'Red',0.97),(1,29,'Part One','EACH',100.00,100.00,100,100,0.00,'User','Models/Screw/screw.j3o',0.01,'Red',0.97),(1,30,'Part One','EACH',100.00,100.00,100,100,0.00,'User','Models/Screw/screw.j3o',0.01,'Red',0.97),(1,31,'Part One','EACH',100.00,100.00,100,100,0.00,'User','Models/Screw/screw.j3o',0.01,'Red',0.97),(1,32,'Part One','EACH',100.00,100.00,100,84,0.00,'User','Models/Screw/screw.j3o',0.01,'Red',0.97),(1,33,'Part One','EACH',100.00,100.00,100,100,0.00,'User','Models/Screw/screw.j3o',0.01,'Red',0.97),(1,34,'Part One','EACH',100.00,100.00,100,100,0.00,'User','Models/Screw/screw.j3o',0.01,'Red',0.97),(1,35,'Part One','EACH',100.00,100.00,100,100,0.00,'User','Models/Screw/screw.j3o',0.01,'Red',0.97),(1,36,'Part One','EACH',100.00,100.00,100,92,0.00,'User','Models/Screw/screw.j3o',0.01,'Red',0.97),(1,37,'Part One','EACH',100.00,100.00,100,100,0.00,'User','Models/Screw/screw.j3o',0.01,'Red',0.97),(1,38,'Part One','EACH',100.00,100.00,100,100,0.00,'User','Models/Screw/screw.j3o',0.01,'Red',0.97),(1,39,'Part One','EACH',100.00,100.00,100,100,0.00,'User','Models/Screw/screw.j3o',0.01,'Red',0.97),(1,40,'Part One','EACH',100.00,100.00,100,100,0.00,'User','Models/Screw/screw.j3o',0.01,'Red',0.97),(1,41,'Part One','EACH',100.00,100.00,100,100,0.00,'User','Models/Screw/screw.j3o',0.01,'Red',0.97),(2,0,'Part Two','EACH',100.00,100.00,50,0,0.40,'User','Models/Screw2/screw2.j3o',0.01,'Blue',0.97),(2,1,'Part Two','EACH',100.00,100.00,50,50,0.00,'User','Models/Screw2/screw2.j3o',0.01,'Blue',0.97),(2,2,'Part Two','EACH',100.00,100.00,50,50,0.00,'User','Models/Screw2/screw2.j3o',0.01,'Blue',0.97),(2,3,'Part Two','EACH',100.00,100.00,50,50,0.00,'User','Models/Screw2/screw2.j3o',0.01,'Blue',0.97),(2,4,'Part Two','EACH',100.00,100.00,50,48,0.00,'User','Models/Screw2/screw2.j3o',0.01,'Blue',0.97),(2,5,'Part Two','EACH',100.00,100.00,50,46,0.00,'User','Models/Screw2/screw2.j3o',0.01,'Blue',0.97),(2,6,'Part Two','EACH',100.00,100.00,50,50,0.00,'User','Models/Screw2/screw2.j3o',0.01,'Blue',0.97),(2,7,'Part Two','EACH',100.00,100.00,50,50,0.00,'User','Models/Screw2/screw2.j3o',0.01,'Blue',0.97),(2,8,'Part Two','EACH',100.00,100.00,50,50,0.00,'User','Models/Screw2/screw2.j3o',0.01,'Blue',0.97),(2,9,'Part Two','EACH',100.00,100.00,50,50,0.00,'User','Models/Screw2/screw2.j3o',0.01,'Blue',0.97),(2,10,'Part Two','EACH',100.00,100.00,50,50,0.00,'User','Models/Screw2/screw2.j3o',0.01,'Blue',0.97),(2,11,'Part Two','EACH',100.00,100.00,50,50,0.00,'User','Models/Screw2/screw2.j3o',0.01,'Blue',0.97),(2,12,'Part Two','EACH',100.00,100.00,50,50,0.00,'User','Models/Screw2/screw2.j3o',0.01,'Blue',0.97),(2,13,'Part Two','EACH',100.00,100.00,50,50,0.00,'User','Models/Screw2/screw2.j3o',0.01,'Blue',0.97),(2,14,'Part Two','EACH',100.00,100.00,50,50,0.00,'User','Models/Screw2/screw2.j3o',0.01,'Blue',0.97),(2,15,'Part Two','EACH',100.00,100.00,50,50,0.00,'User','Models/Screw2/screw2.j3o',0.01,'Blue',0.97),(2,16,'Part Two','EACH',100.00,100.00,50,50,0.00,'User','Models/Screw2/screw2.j3o',0.01,'Blue',0.97),(2,17,'Part Two','EACH',100.00,100.00,50,50,0.00,'User','Models/Screw2/screw2.j3o',0.01,'Blue',0.97),(2,18,'Part Two','EACH',100.00,100.00,50,32,0.00,'User','Models/Screw2/screw2.j3o',0.01,'Blue',0.97),(2,19,'Part Two','EACH',100.00,100.00,50,50,0.00,'User','Models/Screw2/screw2.j3o',0.01,'Blue',0.97),(2,20,'Part Two','EACH',100.00,100.00,50,0,0.00,'User','Models/Screw2/screw2.j3o',0.01,'Blue',0.97),(2,21,'Part Two','EACH',100.00,100.00,50,0,0.00,'User','Models/Screw2/screw2.j3o',0.01,'Blue',0.97),(2,22,'Part Two','EACH',100.00,100.00,50,0,0.00,'User','Models/Screw2/screw2.j3o',0.01,'Blue',0.97),(2,23,'Part Two','EACH',100.00,100.00,50,0,0.00,'User','Models/Screw2/screw2.j3o',0.01,'Blue',0.97),(2,24,'Part Two','EACH',100.00,100.00,50,0,0.00,'User','Models/Screw2/screw2.j3o',0.01,'Blue',0.97),(2,25,'Part Two','EACH',100.00,100.00,50,50,0.00,'User','Models/Screw2/screw2.j3o',0.01,'Blue',0.97),(2,26,'Part Two','EACH',100.00,100.00,50,50,0.00,'User','Models/Screw2/screw2.j3o',0.01,'Blue',0.97),(2,27,'Part Two','EACH',100.00,100.00,50,50,0.00,'User','Models/Screw2/screw2.j3o',0.01,'Blue',0.97),(2,28,'Part Two','EACH',100.00,100.00,50,58,0.00,'User','Models/Screw2/screw2.j3o',0.01,'Blue',0.97),(2,29,'Part Two','EACH',100.00,100.00,50,50,0.00,'User','Models/Screw2/screw2.j3o',0.01,'Blue',0.97),(2,30,'Part Two','EACH',100.00,100.00,50,50,0.00,'User','Models/Screw2/screw2.j3o',0.01,'Blue',0.97),(2,31,'Part Two','EACH',100.00,100.00,50,50,0.00,'User','Models/Screw2/screw2.j3o',0.01,'Blue',0.97),(2,32,'Part Two','EACH',100.00,100.00,50,42,0.00,'User','Models/Screw2/screw2.j3o',0.01,'Blue',0.97),(2,33,'Part Two','EACH',100.00,100.00,50,50,0.00,'User','Models/Screw2/screw2.j3o',0.01,'Blue',0.97),(2,34,'Part Two','EACH',100.00,100.00,50,50,0.00,'User','Models/Screw2/screw2.j3o',0.01,'Blue',0.97),(2,35,'Part Two','EACH',100.00,100.00,50,50,0.00,'User','Models/Screw2/screw2.j3o',0.01,'Blue',0.97),(2,36,'Part Two','EACH',100.00,100.00,50,46,0.00,'User','Models/Screw2/screw2.j3o',0.01,'Blue',0.97),(2,37,'Part Two','EACH',100.00,100.00,50,50,0.00,'User','Models/Screw2/screw2.j3o',0.01,'Blue',0.97),(2,38,'Part Two','EACH',100.00,100.00,50,50,0.00,'User','Models/Screw2/screw2.j3o',0.01,'Blue',0.97),(2,39,'Part Two','EACH',100.00,100.00,50,50,0.00,'User','Models/Screw2/screw2.j3o',0.01,'Blue',0.97),(2,40,'Part Two','EACH',100.00,100.00,50,50,0.00,'User','Models/Screw2/screw2.j3o',0.01,'Blue',0.97),(2,41,'Part Two','EACH',100.00,100.00,50,50,0.00,'User','Models/Screw2/screw2.j3o',0.01,'Blue',0.97),(3,0,'Part Three','EACH',300.00,300.00,25,0,2.00,'User','Models/Chair/chair.j3o',0.05,'Green',0.97),(3,1,'Part Three','EACH',300.00,300.00,25,0,2.00,'User','Models/Chair/chair.j3o',0.05,'Green',0.97),(3,2,'Part Three','EACH',300.00,300.00,25,0,2.00,'User','Models/Chair/chair.j3o',0.05,'Green',0.97),(3,3,'Part Three','EACH',300.00,300.00,25,0,2.00,'User','Models/Chair/chair.j3o',0.05,'Green',0.97),(3,4,'Part Three','EACH',300.00,300.00,25,0,2.00,'User','Models/Chair/chair.j3o',0.05,'Green',0.97),(3,5,'Part Three','EACH',300.00,300.00,25,4,2.00,'User','Models/Chair/chair.j3o',0.05,'Green',0.97),(3,6,'Part Three','EACH',300.00,300.00,25,0,2.00,'User','Models/Chair/chair.j3o',0.05,'Green',0.97),(3,7,'Part Three','EACH',300.00,300.00,25,0,2.00,'User','Models/Chair/chair.j3o',0.05,'Green',0.97),(3,8,'Part Three','EACH',300.00,300.00,25,0,2.00,'User','Models/Chair/chair.j3o',0.05,'Green',0.97),(3,9,'Part Three','EACH',300.00,300.00,25,0,2.00,'User','Models/Chair/chair.j3o',0.05,'Green',0.97),(3,10,'Part Three','EACH',300.00,300.00,25,0,2.00,'User','Models/Chair/chair.j3o',0.05,'Green',0.97),(3,11,'Part Three','EACH',300.00,300.00,25,0,2.00,'User','Models/Chair/chair.j3o',0.05,'Green',0.97),(3,12,'Part Three','EACH',300.00,300.00,25,0,2.00,'User','Models/Chair/chair.j3o',0.05,'Green',0.97),(3,13,'Part Three','EACH',300.00,300.00,25,0,2.00,'User','Models/Chair/chair.j3o',0.05,'Green',0.97),(3,14,'Part Three','EACH',300.00,300.00,25,0,2.00,'User','Models/Chair/chair.j3o',0.05,'Green',0.97),(3,15,'Part Three','EACH',300.00,300.00,25,0,2.00,'User','Models/Chair/chair.j3o',0.05,'Green',0.97),(3,16,'Part Three','EACH',300.00,300.00,25,0,2.00,'User','Models/Chair/chair.j3o',0.05,'Green',0.97),(3,17,'Part Three','EACH',300.00,300.00,25,0,2.00,'User','Models/Chair/chair.j3o',0.05,'Green',0.97),(3,18,'Part Three','EACH',300.00,300.00,25,68,2.00,'User','Models/Chair/chair.j3o',0.05,'Green',0.97),(3,19,'Part Three','EACH',300.00,300.00,25,0,2.00,'User','Models/Chair/chair.j3o',0.05,'Green',0.97),(3,20,'Part Three','EACH',300.00,300.00,25,0,2.00,'User','Models/Chair/chair.j3o',0.05,'Green',0.97),(3,21,'Part Three','EACH',300.00,300.00,25,0,2.00,'User','Models/Chair/chair.j3o',0.05,'Green',0.97),(3,22,'Part Three','EACH',300.00,300.00,25,0,2.00,'User','Models/Chair/chair.j3o',0.05,'Green',0.97),(3,23,'Part Three','EACH',300.00,300.00,25,0,2.00,'User','Models/Chair/chair.j3o',0.05,'Green',0.97),(3,24,'Part Three','EACH',300.00,300.00,25,0,2.00,'User','Models/Chair/chair.j3o',0.05,'Green',0.97),(3,25,'Part Three','EACH',300.00,300.00,25,0,2.00,'User','Models/Chair/chair.j3o',0.05,'Green',0.97),(3,26,'Part Three','EACH',300.00,300.00,25,0,2.00,'User','Models/Chair/chair.j3o',0.05,'Green',0.97),(3,27,'Part Three','EACH',300.00,300.00,25,0,2.00,'User','Models/Chair/chair.j3o',0.05,'Green',0.97),(3,28,'Part Three','EACH',300.00,300.00,25,40,2.00,'User','Models/Chair/chair.j3o',0.05,'Green',0.97),(3,29,'Part Three','EACH',300.00,300.00,25,0,2.00,'User','Models/Chair/chair.j3o',0.05,'Green',0.97),(3,30,'Part Three','EACH',300.00,300.00,25,0,2.00,'User','Models/Chair/chair.j3o',0.05,'Green',0.97),(3,31,'Part Three','EACH',300.00,300.00,25,0,2.00,'User','Models/Chair/chair.j3o',0.05,'Green',0.97),(3,32,'Part Three','EACH',300.00,300.00,25,8,2.00,'User','Models/Chair/chair.j3o',0.05,'Green',0.97),(3,33,'Part Three','EACH',300.00,300.00,25,0,2.00,'User','Models/Chair/chair.j3o',0.05,'Green',0.97),(3,34,'Part Three','EACH',300.00,300.00,25,0,2.00,'User','Models/Chair/chair.j3o',0.05,'Green',0.97),(3,35,'Part Three','EACH',300.00,300.00,25,0,2.00,'User','Models/Chair/chair.j3o',0.05,'Green',0.97),(3,36,'Part Three','EACH',300.00,300.00,25,4,2.00,'User','Models/Chair/chair.j3o',0.05,'Green',0.97),(3,37,'Part Three','EACH',300.00,300.00,25,0,2.00,'User','Models/Chair/chair.j3o',0.05,'Green',0.97),(3,38,'Part Three','EACH',300.00,300.00,25,0,2.00,'User','Models/Chair/chair.j3o',0.05,'Green',0.97),(3,39,'Part Three','EACH',300.00,300.00,25,0,2.00,'User','Models/Chair/chair.j3o',0.05,'Green',0.97),(3,40,'Part Three','EACH',300.00,300.00,25,0,2.00,'User','Models/Chair/chair.j3o',0.05,'Green',0.97),(3,41,'Part Three','EACH',300.00,300.00,25,0,2.00,'User','Models/Chair/chair.j3o',0.05,'Green',0.97);
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
INSERT INTO `t_player` VALUES (0,'DefaultPlayer','def','default@gaming.com','123456'),(1,'newUser','','new@email.com','123456'),(2,'user1','','user1@email.com','123456'),(3,'user2','','user2@email.com','123456'),(4,'yserYYY','','yyy@mail.com','123456');
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
INSERT INTO `t_purchase` VALUES (1,0,2,1,10,100),(1,17,2,1,10,100),(1,18,2,1,10,100),(1,19,2,1,10,100),(1,20,2,1,10,100),(1,21,2,1,10,100),(1,22,2,1,10,100),(1,23,2,1,10,100),(1,24,2,1,10,100),(1,25,2,1,10,100),(1,26,2,1,10,100),(1,27,2,1,10,100),(1,28,2,1,10,100),(1,29,2,1,10,100),(1,30,2,1,10,100),(1,31,2,1,10,100),(1,32,2,1,10,100),(1,33,2,1,10,100),(1,34,2,1,10,100),(1,35,2,1,10,100),(1,36,2,1,10,100),(1,37,2,1,10,100),(1,38,2,1,10,100),(1,39,2,1,10,100),(1,40,2,1,10,100),(1,41,2,1,10,100),(2,0,2,1,10,50),(2,17,2,1,10,50),(2,18,2,1,10,50),(2,19,2,1,10,50),(2,20,2,1,10,50),(2,21,2,1,10,50),(2,22,2,1,10,50),(2,23,2,1,10,50),(2,24,2,1,10,50),(2,25,2,1,10,50),(2,26,2,1,10,50),(2,27,2,1,10,50),(2,28,2,1,10,50),(2,29,2,1,10,50),(2,30,2,1,10,50),(2,31,2,1,10,50),(2,32,2,1,10,50),(2,33,2,1,10,50),(2,34,2,1,10,50),(2,35,2,1,10,50),(2,36,2,1,10,50),(2,37,2,1,10,50),(2,38,2,1,10,50),(2,39,2,1,10,50),(2,40,2,1,10,50),(2,41,2,1,10,50);
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
INSERT INTO `t_ship` VALUES (8,0,4,50),(8,17,4,50),(8,18,4,50),(8,19,4,50),(8,20,4,50),(8,21,4,50),(8,22,4,50),(8,23,4,50),(8,24,4,50),(8,25,4,50),(8,26,4,50),(8,27,4,50),(8,28,4,50),(8,29,4,50),(8,30,4,50),(8,31,4,50),(8,32,4,50),(8,33,4,50),(8,34,4,50),(8,35,4,50),(8,36,4,50),(8,37,4,50),(8,38,4,50),(8,39,4,50),(8,40,4,50),(8,41,4,50);
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
INSERT INTO `t_skill` VALUES (0,0,'Pick up'),(0,37,'Pick up'),(0,38,'Pick up'),(0,39,'Pick up'),(0,40,'Pick up'),(0,41,'Pick up'),(1,0,'Move'),(1,37,'Move'),(1,38,'Move'),(1,39,'Move'),(1,40,'Move'),(1,41,'Move'),(2,0,'Placement'),(2,37,'Placement'),(2,38,'Placement'),(2,39,'Placement'),(2,40,'Placement'),(2,41,'Placement'),(3,0,'Store'),(3,37,'Store'),(3,38,'Store'),(3,39,'Store'),(3,40,'Store'),(3,41,'Store'),(4,0,'Operate'),(4,37,'Operate'),(4,38,'Operate'),(4,39,'Operate'),(4,40,'Operate'),(4,41,'Operate');
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
INSERT INTO `t_skill_activity` VALUES (3,4,0),(4,0,0),(4,0,37),(4,0,38),(4,0,39),(4,0,40),(4,0,41),(4,1,0),(4,2,0),(4,3,0),(4,4,0),(5,0,0),(5,0,37),(5,0,38),(5,0,39),(5,0,40),(5,0,41),(5,1,0),(5,2,0),(5,3,0),(5,4,0),(6,0,0),(6,0,37),(6,0,38),(6,0,39),(6,0,40),(6,0,41),(6,1,0),(6,2,0),(6,3,0),(6,4,0),(7,0,0),(7,1,0),(7,2,0),(7,3,0),(7,4,0),(9,0,0),(9,1,0),(9,2,0),(9,3,0),(9,4,0),(10,0,0),(10,1,0),(10,2,0),(10,3,0),(10,4,0);
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
  `efficiency` decimal(5,2) DEFAULT NULL,
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
INSERT INTO `t_skill_operator` VALUES (0,1,0,0.90),(0,1,37,0.90),(0,1,38,0.90),(0,1,39,0.90),(0,1,40,0.90),(0,1,41,0.90),(0,2,0,0.95),(0,2,37,0.95),(0,2,38,0.95),(0,2,39,0.95),(0,2,40,0.95),(0,2,41,0.95),(0,3,0,0.99),(0,3,37,0.99),(0,3,38,0.99),(0,3,39,0.99),(0,3,40,0.99),(0,3,41,0.99),(0,4,0,0.98),(0,4,37,0.98),(0,4,38,0.98),(0,4,39,0.98),(0,4,40,0.98),(0,4,41,0.98),(0,5,0,0.85),(0,6,0,0.86),(1,1,0,0.97),(1,2,0,0.95),(1,3,0,0.93),(1,4,0,0.94),(1,5,0,0.90),(1,6,0,0.88),(2,1,0,0.97),(2,2,0,0.90),(2,3,0,0.96),(2,4,0,0.93),(2,5,0,0.96),(2,6,0,0.95),(3,1,0,0.92),(3,2,0,0.89),(3,3,0,0.88),(3,4,0,0.90),(3,5,0,0.79),(3,6,0,0.92),(4,1,0,0.94),(4,2,0,0.85),(4,3,0,0.87),(4,4,0,0.83),(4,5,0,0.82),(4,6,0,0.96);
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
  `size_w` decimal(5,2) DEFAULT NULL,
  `size_l` decimal(5,2) DEFAULT NULL,
  `price_for_purchase` decimal(5,2) DEFAULT NULL,
  `input_palette_capacity` int(11) DEFAULT NULL,
  `output_palette_capacity` int(11) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `owner` varchar(10) DEFAULT NULL,
  `station_type` varchar(45) DEFAULT NULL,
  `cost_per_hour` decimal(5,2) DEFAULT NULL,
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
INSERT INTO `t_station` VALUES (1,0,'Station to create part 3',100,-40,40.00,40.00,1.00,25,25,'Idle','User','Work',5.00),(1,6,'Station to create part 3',50,-70,40.00,40.00,1.00,25,25,'Idle','User','Work',5.00),(1,7,'Station to create part 3',50,-70,40.00,40.00,1.00,25,25,'Idle','User','Work',5.00),(1,8,'Station to create part 3',50,-70,40.00,40.00,1.00,25,25,'Idle','User','Work',5.00),(1,9,'Station to create part 3',50,-70,40.00,40.00,1.00,25,25,'Idle','User','Work',5.00),(1,10,'Station to create part 3',50,-70,40.00,40.00,1.00,25,25,'Idle','User','Work',5.00),(1,11,'Station to create part 3',50,-70,40.00,40.00,1.00,25,25,'Idle','User','Work',5.00),(1,12,'Station to create part 3',50,-70,40.00,40.00,1.00,25,25,'Idle','User','Work',5.00),(1,13,'Station to create part 3',50,-70,40.00,40.00,1.00,25,25,'Idle','User','Work',5.00),(1,14,'Station to create part 3',50,-70,40.00,40.00,1.00,25,25,'Idle','User','Work',5.00),(1,15,'Station to create part 3',50,-70,40.00,40.00,1.00,25,25,'Idle','User','Work',5.00),(1,16,'Station to create part 3',50,-70,40.00,40.00,1.00,25,25,'Idle','User','Work',5.00),(1,17,'Station to create part 3',50,-70,40.00,40.00,1.00,25,25,'Idle','User','Work',5.00),(1,18,'Station to create part 3',50,-70,40.00,40.00,1.00,25,25,'Idle','User','Work',5.00),(1,19,'Station to create part 3',50,-70,40.00,40.00,1.00,25,25,'Idle','User','Work',5.00),(1,20,'Station to create part 3',50,-70,40.00,40.00,1.00,25,25,'Idle','User','Work',5.00),(1,21,'Station to create part 3',50,-70,40.00,40.00,1.00,25,25,'Idle','User','Work',5.00),(1,22,'Station to create part 3',50,-70,40.00,40.00,1.00,25,25,'Idle','User','Work',5.00),(1,23,'Station to create part 3',50,-70,40.00,40.00,1.00,25,25,'Idle','User','Work',5.00),(1,24,'Station to create part 3',50,-70,40.00,40.00,1.00,25,25,'Idle','User','Work',5.00),(1,25,'Station to create part 3',50,-70,40.00,40.00,1.00,25,25,'Idle','User','Work',5.00),(1,26,'Station to create part 3',50,-70,40.00,40.00,1.00,25,25,'Idle','User','Work',5.00),(1,27,'Station to create part 3',50,-70,40.00,40.00,1.00,25,25,'Idle','User','Work',5.00),(1,28,'Station to create part 3',50,-70,40.00,40.00,1.00,25,25,'Idle','User','Work',5.00),(1,29,'Station to create part 3',50,-70,40.00,40.00,1.00,25,25,'Idle','User','Work',5.00),(1,30,'Station to create part 3',50,-70,40.00,40.00,1.00,25,25,'Idle','User','Work',5.00),(1,31,'Station to create part 3',50,-70,40.00,40.00,1.00,25,25,'Idle','User','Work',5.00),(1,32,'Station to create part 3',50,-70,40.00,40.00,1.00,25,25,'Idle','User','Work',5.00),(1,33,'Station to create part 3',50,-70,40.00,40.00,1.00,25,25,'Idle','User','Work',5.00),(1,34,'Station to create part 3',50,-70,40.00,40.00,1.00,25,25,'Idle','User','Work',5.00),(1,35,'Station to create part 3',50,-70,40.00,40.00,1.00,25,25,'Idle','User','Work',5.00),(1,36,'Station to create part 3',50,-70,40.00,40.00,1.00,25,25,'Idle','User','Work',5.00),(1,37,'Station to create part 3',50,-70,40.00,40.00,1.00,25,25,'Idle','User','Work',5.00),(1,38,'Station to create part 3',50,-70,40.00,40.00,1.00,25,25,'Idle','User','Work',5.00),(1,39,'Station to create part 3',50,-70,40.00,40.00,1.00,25,25,'Idle','User','Work',5.00),(1,40,'Station to create part 3',50,-70,40.00,40.00,1.00,25,25,'Idle','User','Work',5.00),(1,41,'Station to create part 3',50,-70,40.00,40.00,1.00,25,25,'Idle','User','Work',5.00),(2,0,'Receiving Dock',-120,-70,30.00,30.00,1.00,0,50,'Idle','User','Work',5.00),(2,6,'Receiving Dock',-120,-70,30.00,30.00,1.00,0,50,'Idle','User','Work',5.00),(2,7,'Receiving Dock',-120,-70,30.00,30.00,1.00,0,50,'Idle','User','Work',5.00),(2,8,'Receiving Dock',-120,-70,30.00,30.00,1.00,0,50,'Idle','User','Work',5.00),(2,9,'Receiving Dock',-120,-70,30.00,30.00,1.00,0,50,'Idle','User','Work',5.00),(2,10,'Receiving Dock',-120,-70,30.00,30.00,1.00,0,50,'Idle','User','Work',5.00),(2,11,'Receiving Dock',-120,-70,30.00,30.00,1.00,0,50,'Idle','User','Work',5.00),(2,12,'Receiving Dock',-120,-70,30.00,30.00,1.00,0,50,'Idle','User','Work',5.00),(2,13,'Receiving Dock',-120,-70,30.00,30.00,1.00,0,50,'Idle','User','Work',5.00),(2,14,'Receiving Dock',-120,-70,30.00,30.00,1.00,0,50,'Idle','User','Work',5.00),(2,15,'Receiving Dock',-120,-70,30.00,30.00,1.00,0,50,'Idle','User','Work',5.00),(2,16,'Receiving Dock',-120,-70,30.00,30.00,1.00,0,50,'Idle','User','Work',5.00),(2,17,'Receiving Dock',-120,-70,30.00,30.00,1.00,0,50,'Idle','User','Work',5.00),(2,18,'Receiving Dock',-120,-70,30.00,30.00,1.00,0,50,'Idle','User','Work',5.00),(2,19,'Receiving Dock',-120,-70,30.00,30.00,1.00,0,50,'Idle','User','Work',5.00),(2,20,'Receiving Dock',-120,-70,30.00,30.00,1.00,0,50,'Idle','User','Work',5.00),(2,21,'Receiving Dock',-120,-70,30.00,30.00,1.00,0,50,'Idle','User','Work',5.00),(2,22,'Receiving Dock',-120,-70,30.00,30.00,1.00,0,50,'Idle','User','Work',5.00),(2,23,'Receiving Dock',-120,-70,30.00,30.00,1.00,0,50,'Idle','User','Work',5.00),(2,24,'Receiving Dock',-120,-70,30.00,30.00,1.00,0,50,'Idle','User','Work',5.00),(2,25,'Receiving Dock',-120,-70,30.00,30.00,1.00,0,50,'Idle','User','Work',5.00),(2,26,'Receiving Dock',-120,-70,30.00,30.00,1.00,0,50,'Idle','User','Work',5.00),(2,27,'Receiving Dock',-120,-70,30.00,30.00,1.00,0,50,'Idle','User','Work',5.00),(2,28,'Receiving Dock',-120,-70,30.00,30.00,1.00,0,50,'Idle','User','Work',5.00),(2,29,'Receiving Dock',-120,-70,30.00,30.00,1.00,0,50,'Idle','User','Work',5.00),(2,30,'Receiving Dock',-120,-70,30.00,30.00,1.00,0,50,'Idle','User','Work',5.00),(2,31,'Receiving Dock',-120,-70,30.00,30.00,1.00,0,50,'Idle','User','Work',5.00),(2,32,'Receiving Dock',-120,-70,30.00,30.00,1.00,0,50,'Idle','User','Work',5.00),(2,33,'Receiving Dock',-120,-70,30.00,30.00,1.00,0,50,'Idle','User','Work',5.00),(2,34,'Receiving Dock',-120,-70,30.00,30.00,1.00,0,50,'Idle','User','Work',5.00),(2,35,'Receiving Dock',-120,-70,30.00,30.00,1.00,0,50,'Idle','User','Work',5.00),(2,36,'Receiving Dock',-120,-70,30.00,30.00,1.00,0,50,'Idle','User','Work',5.00),(2,37,'Receiving Dock',-120,-70,30.00,30.00,1.00,0,50,'Idle','User','Work',5.00),(2,38,'Receiving Dock',-120,-70,30.00,30.00,1.00,0,50,'Idle','User','Work',5.00),(2,39,'Receiving Dock',-120,-70,30.00,30.00,1.00,0,50,'Idle','User','Work',5.00),(2,40,'Receiving Dock',-120,-70,30.00,30.00,1.00,0,50,'Idle','User','Work',5.00),(2,41,'Receiving Dock',-120,-70,30.00,30.00,1.00,0,50,'Idle','User','Work',5.00),(3,0,'StorageFG',240,10,50.00,50.00,1.00,200,200,'Idle','User','StorageFG',5.00),(3,6,'Storage',240,10,50.00,50.00,1.00,200,200,'Idle','User','Storage',5.00),(3,7,'Storage',240,10,50.00,50.00,1.00,200,200,'Idle','User','Storage',5.00),(3,8,'Storage',240,10,50.00,50.00,1.00,200,200,'Idle','User','Storage',5.00),(3,9,'Storage',240,10,50.00,50.00,1.00,200,200,'Idle','User','Storage',5.00),(3,10,'Storage',240,10,50.00,50.00,1.00,200,200,'Idle','User','Storage',5.00),(3,11,'Storage',240,10,50.00,50.00,1.00,200,200,'Idle','User','Storage',5.00),(3,12,'Storage',240,10,50.00,50.00,1.00,200,200,'Idle','User','Storage',5.00),(3,13,'Storage',240,10,50.00,50.00,1.00,200,200,'Idle','User','Storage',5.00),(3,14,'Storage',240,10,50.00,50.00,1.00,200,200,'Idle','User','Storage',5.00),(3,15,'Storage',240,10,50.00,50.00,1.00,200,200,'Idle','User','Storage',5.00),(3,16,'Storage',240,10,50.00,50.00,1.00,200,200,'Idle','User','Storage',5.00),(3,17,'Storage',240,10,50.00,50.00,1.00,200,200,'Idle','User','Storage',5.00),(3,18,'Storage',240,10,50.00,50.00,1.00,200,200,'Idle','User','Storage',5.00),(3,19,'Storage',240,10,50.00,50.00,1.00,200,200,'Idle','User','Storage',5.00),(3,20,'Storage',240,10,50.00,50.00,1.00,200,200,'Idle','User','Storage',5.00),(3,21,'Storage',240,10,50.00,50.00,1.00,200,200,'Idle','User','Storage',5.00),(3,22,'Storage',240,10,50.00,50.00,1.00,200,200,'Idle','User','Storage',5.00),(3,23,'Storage',240,10,50.00,50.00,1.00,200,200,'Idle','User','Storage',5.00),(3,24,'Storage',240,10,50.00,50.00,1.00,200,200,'Idle','User','Storage',5.00),(3,25,'Storage',240,10,50.00,50.00,1.00,200,200,'Idle','User','Storage',5.00),(3,26,'Storage',240,10,50.00,50.00,1.00,200,200,'Idle','User','Storage',5.00),(3,27,'Storage',240,10,50.00,50.00,1.00,200,200,'Idle','User','Storage',5.00),(3,28,'Storage',240,10,50.00,50.00,1.00,200,200,'Idle','User','Storage',5.00),(3,29,'Storage',240,10,50.00,50.00,1.00,200,200,'Idle','User','Storage',5.00),(3,30,'Storage',240,10,50.00,50.00,1.00,200,200,'Idle','User','Storage',5.00),(3,31,'Storage',240,10,50.00,50.00,1.00,200,200,'Idle','User','Storage',5.00),(3,32,'Storage',240,10,50.00,50.00,1.00,200,200,'Idle','User','Storage',5.00),(3,33,'Storage',240,10,50.00,50.00,1.00,200,200,'Idle','User','Storage',5.00),(3,34,'Storage',240,10,50.00,50.00,1.00,200,200,'Idle','User','Storage',5.00),(3,35,'Storage',240,10,50.00,50.00,1.00,200,200,'Idle','User','Storage',5.00),(3,36,'Storage',240,10,50.00,50.00,1.00,200,200,'Idle','User','Storage',5.00),(3,37,'Storage',240,10,50.00,50.00,1.00,200,200,'Idle','User','Storage',5.00),(3,38,'Storage',240,10,50.00,50.00,1.00,200,200,'Idle','User','Storage',5.00),(3,39,'Storage',240,10,50.00,50.00,1.00,200,200,'Idle','User','Storage',5.00),(3,40,'Storage',240,10,50.00,50.00,1.00,200,200,'Idle','User','Storage',5.00),(3,41,'Storage',240,10,50.00,50.00,1.00,200,200,'Idle','User','Storage',5.00),(4,0,'Ship Station',-120,70,30.00,30.00,1.00,200,200,'Idle','User','Work',5.00),(4,6,'Ship Station',-120,70,30.00,30.00,1.00,200,200,'Idle','User','Work',5.00),(4,7,'Ship Station',-120,70,30.00,30.00,1.00,200,200,'Idle','User','Work',5.00),(4,8,'Ship Station',-120,70,30.00,30.00,1.00,200,200,'Idle','User','Work',5.00),(4,9,'Ship Station',-120,70,30.00,30.00,1.00,200,200,'Idle','User','Work',5.00),(4,10,'Ship Station',-120,70,30.00,30.00,1.00,200,200,'Idle','User','Work',5.00),(4,11,'Ship Station',-120,70,30.00,30.00,1.00,200,200,'Idle','User','Work',5.00),(4,12,'Ship Station',-120,70,30.00,30.00,1.00,200,200,'Idle','User','Work',5.00),(4,13,'Ship Station',-120,70,30.00,30.00,1.00,200,200,'Idle','User','Work',5.00),(4,14,'Ship Station',-120,70,30.00,30.00,1.00,200,200,'Idle','User','Work',5.00),(4,15,'Ship Station',-120,70,30.00,30.00,1.00,200,200,'Idle','User','Work',5.00),(4,16,'Ship Station',-120,70,30.00,30.00,1.00,200,200,'Idle','User','Work',5.00),(4,17,'Ship Station',-120,70,30.00,30.00,1.00,200,200,'Idle','User','Work',5.00),(4,18,'Ship Station',-120,70,30.00,30.00,1.00,200,200,'Idle','User','Work',5.00),(4,19,'Ship Station',-120,70,30.00,30.00,1.00,200,200,'Idle','User','Work',5.00),(4,20,'Ship Station',-120,70,30.00,30.00,1.00,200,200,'Idle','User','Work',5.00),(4,21,'Ship Station',-120,70,30.00,30.00,1.00,200,200,'Idle','User','Work',5.00),(4,22,'Ship Station',-120,70,30.00,30.00,1.00,200,200,'Idle','User','Work',5.00),(4,23,'Ship Station',-120,70,30.00,30.00,1.00,200,200,'Idle','User','Work',5.00),(4,24,'Ship Station',-120,70,30.00,30.00,1.00,200,200,'Idle','User','Work',5.00),(4,25,'Ship Station',-120,70,30.00,30.00,1.00,200,200,'Idle','User','Work',5.00),(4,26,'Ship Station',-120,70,30.00,30.00,1.00,200,200,'Idle','User','Work',5.00),(4,27,'Ship Station',-120,70,30.00,30.00,1.00,200,200,'Idle','User','Work',5.00),(4,28,'Ship Station',-120,70,30.00,30.00,1.00,200,200,'Idle','User','Work',5.00),(4,29,'Ship Station',-120,70,30.00,30.00,1.00,200,200,'Idle','User','Work',5.00),(4,30,'Ship Station',-120,70,30.00,30.00,1.00,200,200,'Idle','User','Work',5.00),(4,31,'Ship Station',-120,70,30.00,30.00,1.00,200,200,'Idle','User','Work',5.00),(4,32,'Ship Station',-120,70,30.00,30.00,1.00,200,200,'Idle','User','Work',5.00),(4,33,'Ship Station',-120,70,30.00,30.00,1.00,200,200,'Idle','User','Work',5.00),(4,34,'Ship Station',-120,70,30.00,30.00,1.00,200,200,'Idle','User','Work',5.00),(4,35,'Ship Station',-120,70,30.00,30.00,1.00,200,200,'Idle','User','Work',5.00),(4,36,'Ship Station',-120,70,30.00,30.00,1.00,200,200,'Idle','User','Work',5.00),(4,37,'Ship Station',-120,70,30.00,30.00,1.00,200,200,'Idle','User','Work',5.00),(4,38,'Ship Station',-120,70,30.00,30.00,1.00,200,200,'Idle','User','Work',5.00),(4,39,'Ship Station',-120,70,30.00,30.00,1.00,200,200,'Idle','User','Work',5.00),(4,40,'Ship Station',-120,70,30.00,30.00,1.00,200,200,'Idle','User','Work',5.00),(4,41,'Ship Station',-120,70,30.00,30.00,1.00,200,200,'Idle','User','Work',5.00),(5,0,'Staff Zone',50,50,40.00,20.00,1.00,0,0,'Idle','User','StaffZone',5.00),(5,6,'Staff Zone',50,50,40.00,20.00,1.00,0,0,'Idle','User','StaffZone',5.00),(5,7,'Staff Zone',50,50,40.00,20.00,1.00,0,0,'Idle','User','StaffZone',5.00),(5,8,'Staff Zone',50,50,40.00,20.00,1.00,0,0,'Idle','User','StaffZone',5.00),(5,9,'Staff Zone',50,50,40.00,20.00,1.00,0,0,'Idle','User','StaffZone',5.00),(5,10,'Staff Zone',50,50,40.00,20.00,1.00,0,0,'Idle','User','StaffZone',5.00),(5,11,'Staff Zone',50,50,40.00,20.00,1.00,0,0,'Idle','User','StaffZone',5.00),(5,12,'Staff Zone',50,50,40.00,20.00,1.00,0,0,'Idle','User','StaffZone',5.00),(5,13,'Staff Zone',50,50,40.00,20.00,1.00,0,0,'Idle','User','StaffZone',5.00),(5,14,'Staff Zone',50,50,40.00,20.00,1.00,0,0,'Idle','User','StaffZone',5.00),(5,15,'Staff Zone',50,50,40.00,20.00,1.00,0,0,'Idle','User','StaffZone',5.00),(5,16,'Staff Zone',50,50,40.00,20.00,1.00,0,0,'Idle','User','StaffZone',5.00),(5,17,'Staff Zone',50,50,40.00,20.00,1.00,0,0,'Idle','User','StaffZone',5.00),(5,18,'Staff Zone',50,50,40.00,20.00,1.00,0,0,'Idle','User','StaffZone',5.00),(5,19,'Staff Zone',50,50,40.00,20.00,1.00,0,0,'Idle','User','StaffZone',5.00),(5,20,'Staff Zone',50,50,40.00,20.00,1.00,0,0,'Idle','User','StaffZone',5.00),(5,21,'Staff Zone',50,50,40.00,20.00,1.00,0,0,'Idle','User','StaffZone',5.00),(5,22,'Staff Zone',50,50,40.00,20.00,1.00,0,0,'Idle','User','StaffZone',5.00),(5,23,'Staff Zone',50,50,40.00,20.00,1.00,0,0,'Idle','User','StaffZone',5.00),(5,24,'Staff Zone',50,50,40.00,20.00,1.00,0,0,'Idle','User','StaffZone',5.00),(5,25,'Staff Zone',50,50,40.00,20.00,1.00,0,0,'Idle','User','StaffZone',5.00),(5,26,'Staff Zone',50,50,40.00,20.00,1.00,0,0,'Idle','User','StaffZone',5.00),(5,27,'Staff Zone',50,50,40.00,20.00,1.00,0,0,'Idle','User','StaffZone',5.00),(5,28,'Staff Zone',50,50,40.00,20.00,1.00,0,0,'Idle','User','StaffZone',5.00),(5,29,'Staff Zone',50,50,40.00,20.00,1.00,0,0,'Idle','User','StaffZone',5.00),(5,30,'Staff Zone',50,50,40.00,20.00,1.00,0,0,'Idle','User','StaffZone',5.00),(5,31,'Staff Zone',50,50,40.00,20.00,1.00,0,0,'Idle','User','StaffZone',5.00),(5,32,'Staff Zone',50,50,40.00,20.00,1.00,0,0,'Idle','User','StaffZone',5.00),(5,33,'Staff Zone',50,50,40.00,20.00,1.00,0,0,'Idle','User','StaffZone',5.00),(5,34,'Staff Zone',50,50,40.00,20.00,1.00,0,0,'Idle','User','StaffZone',5.00),(5,35,'Staff Zone',50,50,40.00,20.00,1.00,0,0,'Idle','User','StaffZone',5.00),(5,36,'Staff Zone',50,50,40.00,20.00,1.00,0,0,'Idle','User','StaffZone',5.00),(5,37,'Staff Zone',50,50,40.00,20.00,1.00,0,0,'Idle','User','StaffZone',5.00),(5,38,'Staff Zone',50,50,40.00,20.00,1.00,0,0,'Idle','User','StaffZone',5.00),(5,39,'Staff Zone',50,50,40.00,20.00,1.00,0,0,'Idle','User','StaffZone',5.00),(5,40,'Staff Zone',50,50,40.00,20.00,1.00,0,0,'Idle','User','StaffZone',5.00),(5,41,'Staff Zone',50,50,40.00,20.00,1.00,0,0,'Idle','User','StaffZone',5.00),(6,0,'Machine Zone',-60,50,60.00,20.00,1.00,0,0,'Idle','User','MachineZone',5.00),(6,6,'Machine Zone',-60,50,60.00,20.00,1.00,0,0,'Idle','User','MachineZone',5.00),(6,7,'Machine Zone',-60,50,60.00,20.00,1.00,0,0,'Idle','User','MachineZone',5.00),(6,8,'Machine Zone',-60,50,60.00,20.00,1.00,0,0,'Idle','User','MachineZone',5.00),(6,9,'Machine Zone',-60,50,60.00,20.00,1.00,0,0,'Idle','User','MachineZone',5.00),(6,10,'Machine Zone',-60,50,60.00,20.00,1.00,0,0,'Idle','User','MachineZone',5.00),(6,11,'Machine Zone',-60,50,60.00,20.00,1.00,0,0,'Idle','User','MachineZone',5.00),(6,12,'Machine Zone',-60,50,60.00,20.00,1.00,0,0,'Idle','User','MachineZone',5.00),(6,13,'Machine Zone',-60,50,60.00,20.00,1.00,0,0,'Idle','User','MachineZone',5.00),(6,14,'Machine Zone',-60,50,60.00,20.00,1.00,0,0,'Idle','User','MachineZone',5.00),(6,15,'Machine Zone',-60,50,60.00,20.00,1.00,0,0,'Idle','User','MachineZone',5.00),(6,16,'Machine Zone',-60,50,60.00,20.00,1.00,0,0,'Idle','User','MachineZone',5.00),(6,17,'Machine Zone',-60,50,60.00,20.00,1.00,0,0,'Idle','User','MachineZone',5.00),(6,18,'Machine Zone',-60,50,60.00,20.00,1.00,0,0,'Idle','User','MachineZone',5.00),(6,19,'Machine Zone',-60,50,60.00,20.00,1.00,0,0,'Idle','User','MachineZone',5.00),(6,20,'Machine Zone',-60,50,60.00,20.00,1.00,0,0,'Idle','User','MachineZone',5.00),(6,21,'Machine Zone',-60,50,60.00,20.00,1.00,0,0,'Idle','User','MachineZone',5.00),(6,22,'Machine Zone',-60,50,60.00,20.00,1.00,0,0,'Idle','User','MachineZone',5.00),(6,23,'Machine Zone',-60,50,60.00,20.00,1.00,0,0,'Idle','User','MachineZone',5.00),(6,24,'Machine Zone',-60,50,60.00,20.00,1.00,0,0,'Idle','User','MachineZone',5.00),(6,25,'Machine Zone',-60,50,60.00,20.00,1.00,0,0,'Idle','User','MachineZone',5.00),(6,26,'Machine Zone',-60,50,60.00,20.00,1.00,0,0,'Idle','User','MachineZone',5.00),(6,27,'Machine Zone',-60,50,60.00,20.00,1.00,0,0,'Idle','User','MachineZone',5.00),(6,28,'Machine Zone',-60,50,60.00,20.00,1.00,0,0,'Idle','User','MachineZone',5.00),(6,29,'Machine Zone',-60,50,60.00,20.00,1.00,0,0,'Idle','User','MachineZone',5.00),(6,30,'Machine Zone',-60,50,60.00,20.00,1.00,0,0,'Idle','User','MachineZone',5.00),(6,31,'Machine Zone',-60,50,60.00,20.00,1.00,0,0,'Idle','User','MachineZone',5.00),(6,32,'Machine Zone',-60,50,60.00,20.00,1.00,0,0,'Idle','User','MachineZone',5.00),(6,33,'Machine Zone',-60,50,60.00,20.00,1.00,0,0,'Idle','User','MachineZone',5.00),(6,34,'Machine Zone',-60,50,60.00,20.00,1.00,0,0,'Idle','User','MachineZone',5.00),(6,35,'Machine Zone',-60,50,60.00,20.00,1.00,0,0,'Idle','User','MachineZone',5.00),(6,36,'Machine Zone',-60,50,60.00,20.00,1.00,0,0,'Idle','User','MachineZone',5.00),(6,37,'Machine Zone',-60,50,60.00,20.00,1.00,0,0,'Idle','User','MachineZone',5.00),(6,38,'Machine Zone',-60,50,60.00,20.00,1.00,0,0,'Idle','User','MachineZone',5.00),(6,39,'Machine Zone',-60,50,60.00,20.00,1.00,0,0,'Idle','User','MachineZone',5.00),(6,40,'Machine Zone',-60,50,60.00,20.00,1.00,0,0,'Idle','User','MachineZone',5.00),(6,41,'Machine Zone',-60,50,60.00,20.00,1.00,0,0,'Idle','User','MachineZone',5.00),(7,0,'StorageRM',-30,-70,40.00,40.00,1.00,25,25,'Idle','User','StorageRM',5.00);
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
  `service_level` decimal(5,2) DEFAULT NULL,
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
INSERT INTO `t_supplier` VALUES (1,0,'Supplier Pepito',0,0,0.00),(1,1,'Supplier Pepito',0,0,0.00),(1,2,'Supplier Pepito',0,0,0.00),(1,3,'Supplier Pepito',0,0,0.00),(1,4,'Supplier Pepito',0,0,0.00),(1,5,'Supplier Pepito',0,0,0.00),(1,6,'Supplier Pepito',0,0,0.00),(1,7,'Supplier Pepito',0,0,0.00),(1,8,'Supplier Pepito',0,0,0.00),(1,9,'Supplier Pepito',0,0,0.00),(1,10,'Supplier Pepito',0,0,0.00),(1,11,'Supplier Pepito',0,0,0.00),(1,12,'Supplier Pepito',0,0,0.00),(1,13,'Supplier Pepito',0,0,0.00),(1,14,'Supplier Pepito',0,0,0.00),(1,15,'Supplier Pepito',0,0,0.00),(1,16,'Supplier Pepito',0,0,0.00),(1,17,'Supplier Pepito',0,0,0.00),(1,18,'Supplier Pepito',0,0,0.00),(1,19,'Supplier Pepito',0,0,0.00),(1,20,'Supplier Pepito',0,0,0.00),(1,21,'Supplier Pepito',0,0,0.00),(1,22,'Supplier Pepito',0,0,0.00),(1,23,'Supplier Pepito',0,0,0.00),(1,24,'Supplier Pepito',0,0,0.00),(1,25,'Supplier Pepito',0,0,0.00),(1,26,'Supplier Pepito',0,0,0.00),(1,27,'Supplier Pepito',0,0,0.00),(1,28,'Supplier Pepito',0,0,0.00),(1,29,'Supplier Pepito',0,0,0.00),(1,30,'Supplier Pepito',0,0,0.00),(1,31,'Supplier Pepito',0,0,0.00),(1,32,'Supplier Pepito',0,0,0.00),(1,33,'Supplier Pepito',0,0,0.00),(1,34,'Supplier Pepito',0,0,0.00),(1,35,'Supplier Pepito',0,0,0.00),(1,36,'Supplier Pepito',0,0,0.00),(1,37,'Supplier Pepito',0,0,0.00),(1,38,'Supplier Pepito',0,0,0.00),(1,39,'Supplier Pepito',0,0,0.00),(1,40,'Supplier Pepito',0,0,0.00),(1,41,'Supplier Pepito',0,0,0.00);
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
  `local_scale` decimal(5,2) DEFAULT NULL,
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
INSERT INTO `t_transport_store` VALUES (4,0,10,2,7),(4,17,10,2,7),(4,18,10,2,7),(4,19,10,2,7),(4,20,10,2,7),(4,21,10,2,7),(4,22,10,2,7),(4,23,10,2,7),(4,24,10,2,7),(4,25,10,2,7),(4,26,10,2,7),(4,27,10,2,7),(4,28,10,2,7),(4,29,10,2,7),(4,30,10,2,7),(4,31,10,2,7),(4,32,10,2,7),(4,33,10,2,7),(4,34,10,2,7),(4,35,10,2,7),(4,36,10,2,7),(4,37,10,2,7),(4,38,10,2,7),(4,39,10,2,7),(4,40,10,2,7),(4,41,10,2,7),(5,0,10,2,7),(5,17,10,2,7),(5,18,10,2,7),(5,19,10,2,7),(5,20,10,2,7),(5,21,10,2,7),(5,22,10,2,7),(5,23,10,2,7),(5,24,10,2,7),(5,25,10,2,7),(5,26,10,2,7),(5,27,10,2,7),(5,28,10,2,7),(5,29,10,2,7),(5,30,10,2,7),(5,31,10,2,7),(5,32,10,2,7),(5,33,10,2,7),(5,34,10,2,7),(5,35,10,2,7),(5,36,10,2,7),(5,37,10,2,7),(5,38,10,2,7),(5,39,10,2,7),(5,40,10,2,7),(5,41,10,2,7),(6,0,20,1,3),(6,17,20,1,3),(6,18,20,1,3),(6,19,20,1,3),(6,20,20,1,3),(6,21,20,1,3),(6,22,20,1,3),(6,23,20,1,3),(6,24,20,1,3),(6,25,20,1,3),(6,26,20,1,3),(6,27,20,1,3),(6,28,20,1,3),(6,29,20,1,3),(6,30,20,1,3),(6,31,20,1,3),(6,32,20,1,3),(6,33,20,1,3),(6,34,20,1,3),(6,35,20,1,3),(6,36,20,1,3),(6,37,20,1,3),(6,38,20,1,3),(6,39,20,1,3),(6,40,20,1,3),(6,41,20,1,3),(7,0,30,3,4),(7,17,30,3,4),(7,18,30,3,4),(7,19,30,3,4),(7,20,30,3,4),(7,21,30,3,4),(7,22,30,3,4),(7,23,30,3,4),(7,24,30,3,4),(7,25,30,3,4),(7,26,30,3,4),(7,27,30,3,4),(7,28,30,3,4),(7,29,30,3,4),(7,30,30,3,4),(7,31,30,3,4),(7,32,30,3,4),(7,33,30,3,4),(7,34,30,3,4),(7,35,30,3,4),(7,36,30,3,4),(7,37,30,3,4),(7,38,30,3,4),(7,39,30,3,4),(7,40,30,3,4),(7,41,30,3,4),(9,0,10,7,1),(10,0,10,7,1);
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
    decCostPerExecution decimal(5,2),
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
    decQuality              decimal(5,2),
    vcProdDistn             varchar(10),
    decProdParam1           decimal(5,2),
    decProdParam2           decimal(5,2),
    intPriceFunc1_Limit     int,
    decPriceFunc1_Charge    decimal(5,2),
    intPriceFunc2_Limit     int,
    decPriceFunc2_Charge    decimal(5,2),
    intPriceFunc3_Limit     int,
    decPriceFunc3_Charge    decimal(5,2)
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
    decPercentageToWin  decimal(5,2),
    vcName              varchar(100),
    vcGameStatus        varchar(45),
    intIdPhase          int,
    intNextIdPhase      int,
    intIdTerrain        int
)
BEGIN

/*declare newIdGame int;
select ifnull(max(id_game)+1,1) into newIdGame from t_game;
*/
INSERT INTO t_game(id_game, date_time, id_player, current_money,
    current_minute, current_hour, current_day, current_month,
    current_game_time, time_factor, game_type, game_category,
    description, initial_money, percentage_to_win, name, game_status,
    id_phase, next_id_phase, id_terrain)
VALUES (intIdGame, now(), intIdPlayer, decCurrentMoney,
    intCurrentMinute, intCurrentHour, intCurrentDay, intCurrentMonth,
    dblCurrentTime, dblTimeFactor, vcGameType, vcGameCategory,
    vcDescription, decInitialMoney, decPercentageToWin, vcName,
    vcGameStatus, intIdPhase, intNextIdPhase, intIdTerrain);

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
        id_terrain
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
        id_terrain
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
        id_terrain
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
    intIdTerrain   int
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
    id_terrain = intIdTerrain
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
    decSpeed                decimal(5,2),
    decWeightCapacity       decimal(5,2),
    decVolumeCapacity       decimal(5,2),
    vcPickUpTimeDistn       varchar(10),
    decPickUpTimeParam1     decimal(5,2),
    decPickUpTimeParam2     decimal(5,2),
    vcMachineTimeDistn      varchar(10),
    decMachineTimeParam1    decimal(5,2),
    decMachineTimeParam2    decimal(5,2),
    vcPlacementTimeDistn    varchar(10),
    decPlacementTimeParam1  decimal(5,2),
    decPlacementTimeParam2  decimal(5,2),
    vcTimeBetFailsDistn     varchar(10),
    decTimeBetFailsParam1   decimal(5,2),
    decTimeBetFailsParam2   decimal(5,2),
    vcRepairTimeDistn       varchar(10),
    decRepairTimeParam1     decimal(5,2),
    decRepairTimeParam2     decimal(5,2),
    decPriceForPurchase     decimal(5,2),
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
    decCostPerHour          decimal(5,2),
    vcMachineCategory       varchar(45),
    vcState                 varchar(45)
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
        machine_category, state)
    values
        (intIdMachine, intIdGame, vcMachineDesc, decSpeed, decWeightCapacity, decVolumeCapacity,
        vcPickUpTimeDistn, decPickUpTimeParam1, decPickUpTimeParam2, vcMachineTimeDistn,
        decMachineTimeParam1, decMachineTimeParam2, vcPlacementTimeDistn,
        decPlacementTimeParam1, decPlacementTimeParam2, vcTimeBetFailsDistn,
        decTimeBetFailsParam1, decTimeBetFailsParam2, vcRepairTimeDistn,
        decRepairTimeParam1, decRepairTimeParam2, decPriceForPurchase, vcStatus, vcOwner,
        vcMachineDesign, vcVirtualIdLocation, intVirtualMatrixIniI,
        intVirtualMatrixIniJ, intVirtualMatrixEndI, intVirtualMatrixEndJ,
        intCurrentLocationX, intCurrentLocationZ, intSizeW, intSizeL, descCostPerHour, 
        vcMachineCategory, vcState);
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
        size_w, size_l, cost_per_hour, machine_category, state
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
    decSalaryPerHour    decimal(5,2),
    vcStatus        varchar(10),
    vcOwner         varchar(10),
    vcVirtualIdLocation varchar(45),
    intVirtualMatrixI   int,
    intVirtualMatrixJ   int,
    intCurrentLocationX int,
    intCurrentLocationZ int,
    decSpeed        decimal(5,2),
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
    decVolume           decimal(5,2),
    decWeight           decimal(5,2),
    intQuantityPalette  int,
    intCurrentStock     int,
    intPriceForSale     int,
    vcOwner             varchar(10),
    partDesign          varchar(100),
    partDesignScale     decimal(5,2),
    partDesignColor     varchar(45),
    decFactor           decimal(5,2)
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
/*!50003 DROP PROCEDURE IF EXISTS `Path_Insert` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `Path_Insert`(
    intIdGame       int,
    intIdOperator   int,
    intIdNumber     int,
    intLocationX    int,
    intLocationZ    int
)
BEGIN
    insert into t_path
        (id_game, id_operator, id_number, location_x, location_z)
    values
        (intIdGame, intIdOperator, intIdNumber, intLocationX, intLocationZ);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `Path_Select` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `Path_Select`(
    intIdGame       int,
    intIdOperator   int
)
BEGIN
    select
        id_number, location_x, location_z
    from
        t_path
    where
        id_game = intIdGame and
        id_operator = intIdOperator
    order by id_number asc;
        
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
    decEfficiency   decimal(5,2)
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
    decSizeW                decimal(5,2),
    decSizeL                decimal(5,2),
    decPriceForPurchase     decimal(5,2),
    intInputPaletteCapacity int,
    intOutputPaletteCapacity    int,
    vcStatus                varchar(10),
    vcOwner                 varchar(10),
    vcStationType           varchar(45),
    decCostPerHour     decimal(5,2)
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
    decServiceLevel         decimal(5,2)
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
        a.id_game = intIdGame;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `TypeOperation_Insert` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `TypeOperation_Insert`(
    intIdTypeOperation  int,
    intIdGame       int,
    vcOperationDesc varchar(50)
)
BEGIN
    insert into t_type_operation
        (id_type_operation, id_game, operation_description)
    values
        (intIdTypeOperation, intIdGame, vcOperationDesc);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `TypeOperation_Select` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `TypeOperation_Select`(
    intIdGame       int
)
BEGIN
    select
        id_type_operation, operation_description
    from
        t_type_operation
    where
        id_game = intIdGame;
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

-- Dump completed on 2012-08-14 18:15:49
