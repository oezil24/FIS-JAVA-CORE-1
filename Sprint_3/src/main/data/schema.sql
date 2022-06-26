
CREATE DATABASE  IF NOT EXISTS `sprint3` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016  */;
USE `sprint3`;
-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: criminal_system
-- ------------------------------------------------------
-- Server version	8.0.28

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `criminalcase`
--

DROP TABLE IF EXISTS `criminalcase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `criminalcase` (
                                `id` int NOT NULL,
                                `version` int NOT NULL,
                                `createdAt` datetime NOT NULL,
                                `modifiedAt` datetime NOT NULL,
                                `number` varchar(100) NOT NULL,
                                `type` enum('UNCATEGORIZED','INFRACTION','MISDEMEANOR','FELONY') DEFAULT NULL,
                                `shortDescription` varchar(500) NOT NULL,
                                `detailedDescription` text NOT NULL,
                                `status` enum('SUBMITTED','UNDER_INVESTIGATION','IN_COURT','CLOSED','DISMISSED','COLD') DEFAULT NULL,
                                `note` varchar(500) NOT NULL,
                                `leadInvestigatorId` int DEFAULT NULL,
                                PRIMARY KEY (`id`),
                                UNIQUE KEY `number` (`number`),
                                KEY `lead_investigator` (`leadInvestigatorId`),
                                CONSTRAINT `lead_investigator` FOREIGN KEY (`leadInvestigatorId`) REFERENCES `detective` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `detective`
--

DROP TABLE IF EXISTS `detective`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detective` (
                             `id` int NOT NULL,
                             `version` int NOT NULL,
                             `createdAt` datetime NOT NULL,
                             `modifiedAt` datetime NOT NULL,
                             `username` varchar(100) NOT NULL,
                             `firstName` varchar(100) NOT NULL,
                             `lastName` varchar(100) NOT NULL,
                             `pw` varchar(100) NOT NULL,
                             `hiringDate` datetime NOT NULL,
                             `badgeNumber` varchar(50) NOT NULL,
                             `rankOfDetective` enum('TRAINEE','JUNIOR','SENIOR','INSPECTOR','CHIEF_INSPECTOR') NOT NULL,
                             `armed` tinyint(1) NOT NULL,
                             `status` enum('ACTIVE','SUSPENDED','VACATION','UNDER_INVESTIGATION','RETIRED') NOT NULL,
                             PRIMARY KEY (`id`),
                             UNIQUE KEY `username` (`username`),
                             UNIQUE KEY `badgeNumber` (`badgeNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `evidence`
--

DROP TABLE IF EXISTS `evidence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `evidence` (
                            `id` int NOT NULL,
                            `version` int NOT NULL,
                            `createdAt` datetime NOT NULL,
                            `modifiedAt` datetime NOT NULL,
                            `number` varchar(100) NOT NULL,
                            `itemName` varchar(100) NOT NULL,
                            `note` varchar(500) NOT NULL,
                            `archived` smallint NOT NULL,
                            `criminalCaseId` int DEFAULT NULL,
                            `storageId` int DEFAULT NULL,
                            PRIMARY KEY (`id`),
                            UNIQUE KEY `number` (`number`),
                            KEY `cc_fk` (`criminalCaseId`),
                            KEY `s_fk` (`storageId`),
                            CONSTRAINT `cc_fk` FOREIGN KEY (`criminalCaseId`) REFERENCES `criminalcase` (`id`),
                            CONSTRAINT `s_fk` FOREIGN KEY (`storageId`) REFERENCES `storage` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `storage`
--

DROP TABLE IF EXISTS `storage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `storage` (
                           `id` int NOT NULL,
                           `version` int NOT NULL,
                           `createdAt` datetime NOT NULL,
                           `modifiedAt` datetime NOT NULL,
                           `name` varchar(100) NOT NULL,
                           `location` varchar(500) NOT NULL,
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `trackentry`
--

DROP TABLE IF EXISTS `trackentry`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trackentry` (
                              `id` int NOT NULL,
                              `version` int NOT NULL,
                              `createdAt` datetime NOT NULL,
                              `modifiedAt` datetime NOT NULL,
                              `trackDate` datetime NOT NULL,
                              `action` enum('SUBMITTED','RETRIEVED','RETURNED') DEFAULT NULL,
                              `reason` varchar(100) NOT NULL,
                              `detectiveId` int DEFAULT NULL,
                              `evidenceId` int DEFAULT NULL,
                              PRIMARY KEY (`id`),
                              KEY `d_fk` (`detectiveId`),
                              KEY `e_fk` (`evidenceId`),
                              CONSTRAINT `d_fk` FOREIGN KEY (`detectiveId`) REFERENCES `detective` (`id`),
                              CONSTRAINT `e_fk` FOREIGN KEY (`evidenceId`) REFERENCES `evidence` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `working_detective_case`
--

DROP TABLE IF EXISTS `working_detective_case`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `working_detective_case` (
                                          `workingId` int NOT NULL,
                                          `detectiveId` int DEFAULT NULL,
                                          `criminalCaseId` int DEFAULT NULL,
                                          PRIMARY KEY (`workingId`),
                                          KEY `detective_fk` (`detectiveId`),
                                          KEY `case_fk` (`criminalCaseId`),
                                          CONSTRAINT `case_fk` FOREIGN KEY (`criminalCaseId`) REFERENCES `criminalcase` (`id`),
                                          CONSTRAINT `detective_fk` FOREIGN KEY (`detectiveId`) REFERENCES `detective` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-06-13 22:04:38