CREATE DATABASE  IF NOT EXISTS `sprint4` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `sprint4`;
-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: sprint4
-- ------------------------------------------------------
-- Server version	8.0.29

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
-- Table structure for table `criminal_case`
--

DROP TABLE IF EXISTS `criminal_case`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `criminal_case` (
                                 `id` bigint NOT NULL AUTO_INCREMENT,
                                 `create_at` datetime(6) DEFAULT NULL,
                                 `modified_at` datetime(6) DEFAULT NULL,
                                 `version` int DEFAULT NULL,
                                 `detailed_description` varchar(255) DEFAULT NULL,
                                 `notes` varchar(255) DEFAULT NULL,
                                 `number` varchar(255) DEFAULT NULL,
                                 `short_description` varchar(255) DEFAULT NULL,
                                 `status` varchar(255) DEFAULT NULL,
                                 `case_type` varchar(255) DEFAULT NULL,
                                 `lead_investigator` bigint NOT NULL,
                                 PRIMARY KEY (`id`),
                                 KEY `FKkjewmop105nykj0vieyqxjdue` (`lead_investigator`),
                                 CONSTRAINT `FKkjewmop105nykj0vieyqxjdue` FOREIGN KEY (`lead_investigator`) REFERENCES `detective` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `detective`
--

DROP TABLE IF EXISTS `detective`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detective` (
                             `id` bigint NOT NULL AUTO_INCREMENT,
                             `create_at` datetime(6) DEFAULT NULL,
                             `modified_at` datetime(6) DEFAULT NULL,
                             `version` int DEFAULT NULL,
                             `armed` bit(1) DEFAULT NULL,
                             `badge_number` varchar(255) NOT NULL,
                             `rank` varchar(255) DEFAULT NULL,
                             `status` varchar(255) DEFAULT NULL,
                             `person_id` bigint DEFAULT NULL,
                             PRIMARY KEY (`id`),
                             UNIQUE KEY `UK_4y29i3x2ffrsqka03tevdsice` (`badge_number`),
                             KEY `FKc938d75vilp7eohdshlknj66l` (`person_id`),
                             CONSTRAINT `FKc938d75vilp7eohdshlknj66l` FOREIGN KEY (`person_id`) REFERENCES `person` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `evidence`
--

DROP TABLE IF EXISTS `evidence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `evidence` (
                            `id` bigint NOT NULL AUTO_INCREMENT,
                            `create_at` datetime(6) DEFAULT NULL,
                            `modified_at` datetime(6) DEFAULT NULL,
                            `version` int DEFAULT NULL,
                            `archived` bit(1) DEFAULT NULL,
                            `item_name` varchar(255) DEFAULT NULL,
                            `notes` varchar(255) DEFAULT NULL,
                            `number` varchar(255) DEFAULT NULL,
                            `case_fk` bigint NOT NULL,
                            `storage_fk` bigint NOT NULL,
                            PRIMARY KEY (`id`),
                            KEY `FKd0lrrh25rt0hcq4wmjxdrpalb` (`case_fk`),
                            KEY `FKewfyrlt0wk4n7yruuk8eixq9p` (`storage_fk`),
                            CONSTRAINT `FKd0lrrh25rt0hcq4wmjxdrpalb` FOREIGN KEY (`case_fk`) REFERENCES `criminal_case` (`id`),
                            CONSTRAINT `FKewfyrlt0wk4n7yruuk8eixq9p` FOREIGN KEY (`storage_fk`) REFERENCES `storage` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
    `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `person`
--

DROP TABLE IF EXISTS `person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `person` (
                          `id` bigint NOT NULL AUTO_INCREMENT,
                          `create_at` datetime(6) DEFAULT NULL,
                          `modified_at` datetime(6) DEFAULT NULL,
                          `version` int DEFAULT NULL,
                          `first_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                          `hiring_date` datetime(6) NOT NULL,
                          `last_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                          `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                          `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
                         `role_id` bigint NOT NULL,
                         `role_name` varchar(255) DEFAULT NULL,
                         PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `storage`
--

DROP TABLE IF EXISTS `storage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `storage` (
                           `id` bigint NOT NULL AUTO_INCREMENT,
                           `create_at` datetime(6) DEFAULT NULL,
                           `modified_at` datetime(6) DEFAULT NULL,
                           `version` int DEFAULT NULL,
                           `location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                           `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `track_entry`
--

DROP TABLE IF EXISTS `track_entry`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `track_entry` (
                               `id` bigint NOT NULL AUTO_INCREMENT,
                               `create_at` datetime(6) DEFAULT NULL,
                               `modified_at` datetime(6) DEFAULT NULL,
                               `version` int DEFAULT NULL,
                               `action` varchar(255) DEFAULT NULL,
                               `date` datetime(6) DEFAULT NULL,
                               `reason` varchar(255) DEFAULT NULL,
                               `detective_fk` bigint NOT NULL,
                               `evidence_fk` bigint NOT NULL,
                               PRIMARY KEY (`id`),
                               KEY `FKdh1gext60nljqhw71x2yof7mt` (`detective_fk`),
                               KEY `FKhf235yp7v4aq5vdxmibub9j66` (`evidence_fk`),
                               CONSTRAINT `FKdh1gext60nljqhw71x2yof7mt` FOREIGN KEY (`detective_fk`) REFERENCES `detective` (`id`),
                               CONSTRAINT `FKhf235yp7v4aq5vdxmibub9j66` FOREIGN KEY (`evidence_fk`) REFERENCES `evidence` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role` (
                             `user_role_id` bigint NOT NULL,
                             `person_id` bigint DEFAULT NULL,
                             `role_role_id` bigint DEFAULT NULL,
                             PRIMARY KEY (`user_role_id`),
                             KEY `FKnt5cmhwl8pig4r7m02suqutn0` (`person_id`),
                             KEY `FKao2i3n3h8p40jvwbq4am0j9mg` (`role_role_id`),
                             CONSTRAINT `FKao2i3n3h8p40jvwbq4am0j9mg` FOREIGN KEY (`role_role_id`) REFERENCES `roles` (`role_id`),
                             CONSTRAINT `FKnt5cmhwl8pig4r7m02suqutn0` FOREIGN KEY (`person_id`) REFERENCES `person` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `working_detective_case`
--

DROP TABLE IF EXISTS `working_detective_case`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `working_detective_case` (
                                          `detective_id` bigint NOT NULL,
                                          `case_id` bigint NOT NULL,
                                          PRIMARY KEY (`case_id`,`detective_id`),
                                          KEY `FKogok3ud3eo9p1taswnd5e14j2` (`detective_id`),
                                          CONSTRAINT `FKogok3ud3eo9p1taswnd5e14j2` FOREIGN KEY (`detective_id`) REFERENCES `detective` (`id`),
                                          CONSTRAINT `FKtjlq667q57n2irxj55tva1bbl` FOREIGN KEY (`case_id`) REFERENCES `criminal_case` (`id`)
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

-- Dump completed on 2022-06-27 12:06:21
