-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: hotalsystem
-- ------------------------------------------------------
-- Server version	8.0.36

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
-- Table structure for table `bill`
--

DROP TABLE IF EXISTS `bill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `amount` double NOT NULL,
  `date` datetime(6) DEFAULT NULL,
  `due_date` datetime(6) DEFAULT NULL,
  `status` enum('NOT_PAID','PAID') DEFAULT NULL,
  `reservationid` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2c2qcfn7wft9xy1n0vf3su7w0` (`reservationid`),
  CONSTRAINT `FK2c2qcfn7wft9xy1n0vf3su7w0` FOREIGN KEY (`reservationid`) REFERENCES `reservation` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill`
--

LOCK TABLES `bill` WRITE;
/*!40000 ALTER TABLE `bill` DISABLE KEYS */;
/*!40000 ALTER TABLE `bill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `house_keeping_task`
--

DROP TABLE IF EXISTS `house_keeping_task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `house_keeping_task` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `completed_date` datetime(6) DEFAULT NULL,
  `status` enum('COMPLETED','PENDING') DEFAULT NULL,
  `task_date` datetime(6) DEFAULT NULL,
  `task_description` varchar(255) DEFAULT NULL,
  `employeeid` bigint DEFAULT NULL,
  `roomid` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKtmf3f93j38lucs96n5uc8068u` (`employeeid`),
  KEY `FKb4csmsj5d9fdfgbqf2plt7mdg` (`roomid`),
  CONSTRAINT `FKb4csmsj5d9fdfgbqf2plt7mdg` FOREIGN KEY (`roomid`) REFERENCES `room` (`id`),
  CONSTRAINT `FKtmf3f93j38lucs96n5uc8068u` FOREIGN KEY (`employeeid`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `house_keeping_task`
--

LOCK TABLES `house_keeping_task` WRITE;
/*!40000 ALTER TABLE `house_keeping_task` DISABLE KEYS */;
INSERT INTO `house_keeping_task` VALUES (1,'2024-06-12 00:00:00.000000','PENDING','2024-06-11 00:00:00.000000','Clean the room',6,3),(2,'2024-06-19 00:00:00.000000','COMPLETED','2024-06-19 00:00:00.000000','Clean ',11,8);
/*!40000 ALTER TABLE `house_keeping_task` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservation`
--

DROP TABLE IF EXISTS `reservation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reservation` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `check_in_date` datetime(6) DEFAULT NULL,
  `check_out_date` datetime(6) DEFAULT NULL,
  `number_of_days` int NOT NULL,
  `price` double NOT NULL,
  `customerid` bigint DEFAULT NULL,
  `roomid` bigint DEFAULT NULL,
  `status` enum('APPROVED','CANCELLED','PENDING','REJECTED','RESERVED') DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKkblc8itkk6dfe07tcn5lbb97b` (`customerid`),
  KEY `FKe6tnoh1ucce3otqrry6gu47xe` (`roomid`),
  CONSTRAINT `FKe6tnoh1ucce3otqrry6gu47xe` FOREIGN KEY (`roomid`) REFERENCES `room` (`id`),
  CONSTRAINT `FKkblc8itkk6dfe07tcn5lbb97b` FOREIGN KEY (`customerid`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservation`
--

LOCK TABLES `reservation` WRITE;
/*!40000 ALTER TABLE `reservation` DISABLE KEYS */;
INSERT INTO `reservation` VALUES (13,'2023-03-01 00:00:00.000000','2023-03-05 00:00:00.000000',4,500,7,7,'APPROVED'),(14,'2023-04-01 00:00:00.000000','2023-04-05 00:00:00.000000',4,500,7,7,'REJECTED'),(15,'2023-04-01 00:00:00.000000','2023-04-05 00:00:00.000000',4,500,7,7,'PENDING');
/*!40000 ALTER TABLE `reservation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room`
--

DROP TABLE IF EXISTS `room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `capacity` int NOT NULL,
  `price` double NOT NULL,
  `room_no` varchar(255) DEFAULT NULL,
  `size` double NOT NULL,
  `status` enum('AVAILABLE','RESERVED') DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room`
--

LOCK TABLES `room` WRITE;
/*!40000 ALTER TABLE `room` DISABLE KEYS */;
INSERT INTO `room` VALUES (3,2,150,'6',3,'AVAILABLE','hh'),(4,2,150,'102',35,'AVAILABLE','Deluxe'),(5,2,150,'103',35,'RESERVED','Deluxe'),(6,2,150,'104',35,'AVAILABLE','Deluxe'),(7,2,150,'105',35,'AVAILABLE','Deluxe'),(8,2,10,'1201',3,'AVAILABLE','Deluxe');
/*!40000 ALTER TABLE `room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `token`
--

DROP TABLE IF EXISTS `token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `token` (
  `id` int NOT NULL,
  `expired` bit(1) NOT NULL,
  `revoked` bit(1) NOT NULL,
  `token` varchar(255) DEFAULT NULL,
  `token_type` enum('BEARER') DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKpddrhgwxnms2aceeku9s2ewy5` (`token`),
  KEY `FKe32ek7ixanakfqsdaokm4q9y2` (`user_id`),
  CONSTRAINT `FKe32ek7ixanakfqsdaokm4q9y2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `token`
--

LOCK TABLES `token` WRITE;
/*!40000 ALTER TABLE `token` DISABLE KEYS */;
INSERT INTO `token` VALUES (1,_binary '',_binary '','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJraGFybWVoMUBnbWFpbC5jb20iLCJleHAiOjE3MTg3NTUxNzl9.xQC_9BpLsOc3HT-gZlPKyuuya6dyG3LzNztOs3KntC8','BEARER',1),(2,_binary '',_binary '','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJraGFybWVoMUBnbWFpbC5jb20iLCJleHAiOjE3MTg3NTU0MDh9.I2y1K367OXcuR2txVCNktZg-tTfeA4x_ysn3b8uhKZY','BEARER',1),(52,_binary '',_binary '','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJraGFybWVoMUBnbWFpbC5jb20iLCJleHAiOjE3MTg3NTU5NjZ9.GQ_0fOlAtUiWHNXC9M_NBwvEtBLVGGbqWBAvIbA_pHc','BEARER',1),(102,_binary '',_binary '','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJBaG1hZEBnbWFpbC5jb20iLCJleHAiOjE3MTg3NTYxMjR9.tWdVxWk37sko4RCDFMbeeo-eJ5PiXjOq-h1hqDJ94tY','BEARER',5),(152,_binary '',_binary '','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJBaG1hZEBnbWFpbC5jb20iLCJleHAiOjE3MTg3NTY4NjF9.ButlLncJHI8ZVCVR3XhLcQO6ZyUltGqCvQEKpOsWwDI','BEARER',5),(153,_binary '',_binary '','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJBaG1hZEBnbWFpbC5jb20iLCJleHAiOjE3MTg3NTY5MjN9.wB1dotgsLSa9WF8Z5tqxSDxgqlHAwwr1m-zzU5tFQZc','BEARER',5),(154,_binary '',_binary '','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJraGFybWVoMUBnbWFpbC5jb20iLCJleHAiOjE3MTg3NTcwMzB9.4lWeo-BZa-YC5V2EAVRnN8QbRcjTfbuN5N1dNaAS3zs','BEARER',1),(155,_binary '',_binary '','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJBaG1hZEBnbWFpbC5jb20iLCJleHAiOjE3MTg3NTcwOTB9.MhyqT0WV03nVFfJfeXWqC5wBUxwwh3yGrkBKjcwoY70','BEARER',5),(156,_binary '',_binary '','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJraGFybWVoMUBnbWFpbC5jb20iLCJleHAiOjE3MTg3NTcxNDh9.tYg8YMIob_II3x66WpdPp93-lFo77w40eBb39fQTDao','BEARER',1),(202,_binary '',_binary '','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJraGFybWVoMUBnbWFpbC5jb20iLCJleHAiOjE3MTg3NTg4Njh9.eHTxtoEY5zNqvromDzJeRXI9Q_35dtVt6tlZj05E23Q','BEARER',1),(203,_binary '',_binary '','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJTYWVkQGdtYWlsLmNvbSIsImV4cCI6MTcxODc1ODk1Nn0.N8JT48COxfRlxKt2o6f70L_HOpRRIWItZbnCwfL1YTs','BEARER',9),(204,_binary '',_binary '','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJTYWVkQGdtYWlsLmNvbSIsImV4cCI6MTcxODc1ODk3N30.eg5Ce3_3kwctCrpf5EwaUjupx0epDJdZKY493J0msXY','BEARER',9),(252,_binary '',_binary '','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJTYWVkQGdtYWlsLmNvbSIsImV4cCI6MTcxODgwOTg1OX0.SU2nnXGFPByxj0spTxjbB9u65MsQm4GlqKy0K7AuK8U','BEARER',9),(253,_binary '',_binary '','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJTYWVkQGdtYWlsLmNvbSIsImV4cCI6MTcxODgwOTg4NX0.XsrTUxjQPg7Iqcl4ZS7efBikVYs--v5aTEomAiSYo-Q','BEARER',9),(302,_binary '',_binary '','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJTYWVkQGdtYWlsLmNvbSIsImV4cCI6MTcxODgxMDI1Nn0.oBeY893sAvHc9uj7k5TinkrvmLLpvbEFwMuc00zmJXY','BEARER',9),(352,_binary '\0',_binary '\0','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJTYWVkQGdtYWlsLmNvbSIsImV4cCI6MTcxODgxMDM1MH0.oZFbThRLpHc-JHnGMuUtCZxXRudiXz7qkzX1wnwlMJM','BEARER',9),(353,_binary '',_binary '','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJraGFybWVoMUBnbWFpbC5jb20iLCJleHAiOjE3MTg4MTA0NDd9.mPdd--kUaPJxWHrubuDArgxsJCvogrZpbi_GHquKEHg','BEARER',1),(354,_binary '',_binary '','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJBaG1hZEBnbWFpbC5jb20iLCJleHAiOjE3MTg4MTA1Mjl9.xjfC3VRH3Eyc5UyKDt70QYZ052a_KoFgbygVNkTI9ZA','BEARER',5),(402,_binary '',_binary '','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJraGFybWVoMUBnbWFpbC5jb20iLCJleHAiOjE3MTg4MTE3MDN9.mVhh8PjrM4RS_6icA9RwkPMFPX0elUyZNpOiEhaUuy0','BEARER',1),(403,_binary '',_binary '','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJraGFybWVoMUBnbWFpbC5jb20iLCJleHAiOjE3MTg4MTE3ODV9.aCEj9-QgoAXZMPfahu6byvBIUNNlwSEuZgbZ5oxC4dU','BEARER',1),(404,_binary '',_binary '','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJpc3NhYWFAZ21haWwuY29tIiwiZXhwIjoxNzE4ODExODIzfQ.g-yiUyte_ZocoURQQc364_Y8DRNvu04emtw2AmXvotA','BEARER',8),(405,_binary '',_binary '','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJpc3NhYWFAZ21haWwuY29tIiwiZXhwIjoxNzE4ODExODg5fQ.Bc71HIWFRoTDzWoNYmIeT6Wjbwp5my5kseHTUJAlhs4','BEARER',8),(452,_binary '',_binary '','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJpc3NhYWFAZ21haWwuY29tIiwiZXhwIjoxNzE4ODExOTgxfQ.O4Qeqa-l-Kd4RTHfUN0KdcjWmqwodpD7veVw2R_lgeg','BEARER',8),(502,_binary '',_binary '','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJpc3NhYWFAZ21haWwuY29tIiwiZXhwIjoxNzE4ODEyMTYwfQ.MEC7DeCwL0yvnVbQulv4Pqe0qOXel_LZVZI82mF-JvM','BEARER',8),(552,_binary '',_binary '','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJpc3NhYWFAZ21haWwuY29tIiwiZXhwIjoxNzE4ODEyMjU0fQ.u5fbrkUL3u1Mcwv7KfXkCKImYG2k8EcpdJwOH9DDYt0','BEARER',8),(553,_binary '',_binary '','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJraGFybWVoMUBnbWFpbC5jb20iLCJleHAiOjE3MTg4MTIyODl9.I3JaeSPv3wdW8WjcKyPZM_0XQVBOMwCMs1-IT7qqheE','BEARER',1),(602,_binary '',_binary '','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJpc3NhYWFAZ21haWwuY29tIiwiZXhwIjoxNzE4ODEyMzcxfQ.H-o-CXVlMPp-0jVEdepccZbHeSdiSrbst9O23RNejD0','BEARER',8),(652,_binary '',_binary '','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJpc3NhYWFAZ21haWwuY29tIiwiZXhwIjoxNzE4ODEyNzI3fQ.OuJ7BGJf-d-5NGe2QxbD8g8RtDA2UEolYhBFfCz2uB0','BEARER',8),(702,_binary '',_binary '','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJpc3NhYWFAZ21haWwuY29tIiwiZXhwIjoxNzE4ODEyODQ1fQ.3cJao_u2j8nmtuHDLY9rjoxLzBhH2JcghUl2rNOuwuc','BEARER',8),(703,_binary '',_binary '','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJraGFybWVoMUBnbWFpbC5jb20iLCJleHAiOjE3MTg4MTI4ODl9.LGI67zqdwM4qbdrjWcLqeWj-Zi7HVlIT-CN_62aK7CA','BEARER',1),(704,_binary '',_binary '','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJraGFybWVoMUBnbWFpbC5jb20iLCJleHAiOjE3MTg4MTMwMTR9.eoXg6eYrHPhAu1p3zkaMyRqBNBnfaOOb6kMiaBHkNj0','BEARER',1),(752,_binary '',_binary '','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJraGFybWVoMUBnbWFpbC5jb20iLCJleHAiOjE3MTg4MTMwNzZ9.GSA9lNdMPKIvniGz2QntfQe8wnHp9SuAV8ByydvW27I','BEARER',1),(753,_binary '',_binary '','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJraGFybWVoMUBnbWFpbC5jb20iLCJleHAiOjE3MTg4MTMzMTN9.WX5iWNhnqs1Vy_maOoZ1NAqfmm3EHL9bwaZ_MWP1Z78','BEARER',1),(754,_binary '',_binary '','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJraGFybWVoMUBnbWFpbC5jb20iLCJleHAiOjE3MTg4MTM1OTZ9.xLHlcR4aV2eQ8iaVoYQPBKouy0Vjoo4ZzkF5kL08h04','BEARER',1),(755,_binary '\0',_binary '\0','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJBaG1hZEBnbWFpbC5jb20iLCJleHAiOjE3MTg4MTM3NDB9.pFOhsQt7f9fofw8MjEOAuHkSHsmYiKn4f_JyxjWX8q8','BEARER',5),(756,_binary '',_binary '','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJraGFybWVoMUBnbWFpbC5jb20iLCJleHAiOjE3MTg4MTM4MzV9.8SKf6228NupfJtlbY00rbYs2NmiTQFvCu1ianRk7toM','BEARER',1),(757,_binary '\0',_binary '\0','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJpc3NhYWFAZ21haWwuY29tIiwiZXhwIjoxNzE4ODEzODUyfQ.3RohegMczXrSIhHIJxMgZhtLybO7k7GNTqh7bXc5DYo','BEARER',8),(758,_binary '',_binary '','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJraGFybWVoMUBnbWFpbC5jb20iLCJleHAiOjE3MTg4MTM4Njh9.tC4EX3q2qTUgFC4BftqkDxCg-6B6q7Y7GBhRcUUeJI8','BEARER',1),(802,_binary '',_binary '','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJraGFybWVoMUBnbWFpbC5jb20iLCJleHAiOjE3MTg4MTM5MDZ9.sDlYrTMX14LmFgAjlPFbf8C04xLkXhxSVWSQmnKMAvQ','BEARER',1),(852,_binary '',_binary '','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJraGFybWVoMUBnbWFpbC5jb20iLCJleHAiOjE3MTg4MTQwMTF9.xYI4hBtfV1yi6h4O-FW2jqihRQ5TmArYmYwYDJnCCbE','BEARER',1),(902,_binary '',_binary '','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJraGFybWVoMUBnbWFpbC5jb20iLCJleHAiOjE3MTg4MTQ1NDl9.zze0DUola4Mu64WQ6g3gJeft92sHQ_UwIDwdfiFiycM','BEARER',1),(952,_binary '',_binary '','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJraGFybWVoMUBnbWFpbC5jb20iLCJleHAiOjE3MTg4MTQ2MzB9.3Dy8LoWu-oTnrgeDrRy77GcRgWyidDLbCV2XbSYsVUs','BEARER',1),(953,_binary '',_binary '','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJraGFybWVoMUBnbWFpbC5jb20iLCJleHAiOjE3MTg4MTQ2NjJ9.S9-uqjMDi_YDix_PoCKMIx62lbxaE0IPRahp10m2Bmc','BEARER',1),(954,_binary '',_binary '','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJraGFybWVoMUBnbWFpbC5jb20iLCJleHAiOjE3MTg4MTQ3NjV9.xvKN6ci2GJ3mSUDMLFGIXIHiaLEG56qgZ8ZALtg7lYw','BEARER',1),(955,_binary '',_binary '','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJraGFybWVoMUBnbWFpbC5jb20iLCJleHAiOjE3MTg4MTQ4MTZ9.s6wjj4zdI2WrjJqNsfzrnEWo_8JUVz_JfWsill1Olnw','BEARER',1),(1002,_binary '',_binary '','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJraGFybWVoMUBnbWFpbC5jb20iLCJleHAiOjE3MTg4MTUxMTN9.7YXyWH6ZJuzdzXpcIDQUeqD-PlOhILy3ThvFBCHX168','BEARER',1),(1052,_binary '',_binary '','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJraGFybWVoMUBnbWFpbC5jb20iLCJleHAiOjE3MTg4MTk3MDV9.KcBlRj9EVuFhKU-IRPO4KH3MgTH4_A5ncnJ7rJlIfm0','BEARER',1),(1102,_binary '',_binary '','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJraGFybWVoMUBnbWFpbC5jb20iLCJleHAiOjE3MTg4MjEzNzZ9.JxCJjcC42UFSXvsZGOiXxAMXzY6qAEbFjOVPXnSUiWs','BEARER',1),(1152,_binary '',_binary '','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJraGFybWVoMUBnbWFpbC5jb20iLCJleHAiOjE3MTg4MzAwNzF9.vivlH1Nt1S3DMd8nJ4lb6a78o6Dq_2wt5luMD4PxiDI','BEARER',1),(1202,_binary '',_binary '','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJraGFybWVoMUBnbWFpbC5jb20iLCJleHAiOjE3MTg4MzAyMTd9.SerIKbbM6PCT6bf7iWwYYUrHaA-gEVO5BrUkqeN-nwQ','BEARER',1),(1252,_binary '',_binary '','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJraGFybWVoMUBnbWFpbC5jb20iLCJleHAiOjE3MTg4MzE4NTJ9.zun6hJTsFafsseZfWHCTDUyb7nBSu8iSkFhTiVEsOXw','BEARER',1),(1302,_binary '\0',_binary '\0','eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJraGFybWVoMUBnbWFpbC5jb20iLCJleHAiOjE3MTg4MzE5NjZ9.2Il45Txb8FaZXIS7-gKd2pGHqB5WrBMwmv9_iezhht0','BEARER',1);
/*!40000 ALTER TABLE `token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `token_seq`
--

DROP TABLE IF EXISTS `token_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `token_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `token_seq`
--

LOCK TABLES `token_seq` WRITE;
/*!40000 ALTER TABLE `token_seq` DISABLE KEYS */;
INSERT INTO `token_seq` VALUES (1401);
/*!40000 ALTER TABLE `token_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `pass` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `role` enum('ADMIN','CUSTOMER','EMPLOYEE') DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKob8kqyqqgmefl0aco34akdtpe` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'kharmeh1@gmail.com','Kharmeh','123','1234567890','ADMIN'),(2,'Abood@gmail.com','Abood','123','1234567890','ADMIN'),(3,'SOSO@gmail.com','SOSO','123','1234567890','ADMIN'),(5,'Ahmad@gmail.com','Ahmad','123','1234567890','CUSTOMER'),(6,'Ibrhim@gmail.com','Ibrhim','123','1234567890','EMPLOYEE'),(7,'issa@gmail.com','issa','123','1234567890','CUSTOMER'),(8,'issaaa@gmail.com','issaaaa','1234asda','1234567890','CUSTOMER'),(9,'Saed@gmail.com','Saed','newPassword123','1234567890','CUSTOMER'),(10,'ss@gmail.com','Saed','1123','1234567890','CUSTOMER'),(11,'aa@example.com','aa','1234','1234567890','EMPLOYEE');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-19 23:12:53
