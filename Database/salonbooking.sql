CREATE DATABASE  IF NOT EXISTS `onlinesalonbooking` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `onlinesalonbooking`;
-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: onlinesalonbooking
-- ------------------------------------------------------
-- Server version	8.0.34

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
-- Table structure for table `appointment`
--

DROP TABLE IF EXISTS `appointment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `appointment` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `empid` bigint NOT NULL,
  `employee_name` varchar(200) DEFAULT NULL,
  `from_booking_date` datetime(6) DEFAULT NULL,
  `from_time` varchar(255) DEFAULT NULL,
  `productid` bigint NOT NULL,
  `serviceid` bigint NOT NULL,
  `status` varchar(200) DEFAULT NULL,
  `to_booking_date` datetime(6) DEFAULT NULL,
  `to_time` varchar(255) DEFAULT NULL,
  `user_name` varchar(200) DEFAULT NULL,
  `userid` bigint NOT NULL,
  `price` bigint NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appointment`
--

LOCK TABLES `appointment` WRITE;
/*!40000 ALTER TABLE `appointment` DISABLE KEYS */;
INSERT INTO `appointment` VALUES (1,8,'emp','2023-09-21 00:00:00.000000','22:35',1,13,'Available','2023-09-21 00:00:00.000000','22:37','tapetop',10,0),(10,9,'wypuboemp','2023-09-25 00:00:00.000000','13:48',3,14,'confirm','2023-09-25 00:00:00.000000','17:49','sefyferoda',7,5269),(11,8,'emp','2023-09-25 00:00:00.000000','14:47',4,16,'Available','2023-09-25 00:00:00.000000','15:48','gimaba',11,999);
/*!40000 ALTER TABLE `appointment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `productname` varchar(200) DEFAULT NULL,
  `servicecharge` bigint DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `totalcharge` bigint DEFAULT NULL,
  `user_name` varchar(200) DEFAULT NULL,
  `userid` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `address` varchar(3000) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phoneno` varchar(255) DEFAULT NULL,
  `roleid` bigint NOT NULL,
  `role_name` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `productname` varchar(200) DEFAULT NULL,
  `servicecharge` bigint DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `totalcharge` bigint DEFAULT NULL,
  `user_name` varchar(200) DEFAULT NULL,
  `userid` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` VALUES (1,'fgfd',567,'booked',567,'nabebim',6),(2,'sdfsdf',500,'booked',1067,'nabebim',6);
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cardnumber` varchar(255) DEFAULT NULL,
  `cvv` varchar(255) DEFAULT NULL,
  `expairydate` varchar(255) DEFAULT NULL,
  `orderid` varchar(255) DEFAULT NULL,
  `payment` bigint NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  `userid` bigint NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
INSERT INTO `payment` VALUES (1,'4363636','45345','08/08/2025',NULL,1067,'Paid',6,'nabebim'),(2,'455646456','656','05055767',NULL,567,'Paid',10,'tapetop'),(3,'5475756754','6656','0505223',NULL,567,'Paid',10,'tapetop'),(4,'5754747567','456','565464',NULL,567,'Paid',10,'tapetop'),(5,'','','',NULL,5269,'Paid',7,'sefyferoda'),(6,'757575675','123','03/2029',NULL,999,'Paid',11,'gimaba');
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `image` longblob,
  `price` bigint DEFAULT NULL,
  `product_name` varchar(255) DEFAULT NULL,
  `service_name` varchar(255) DEFAULT NULL,
  `serviceid` bigint NOT NULL,
  `service_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK61c4l240ktirpds56w9o4aofx` (`service_id`),
  CONSTRAINT `FK61c4l240ktirpds56w9o4aofx` FOREIGN KEY (`service_id`) REFERENCES `services` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `services`
--

DROP TABLE IF EXISTS `services`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `services` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `address` varchar(3000) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `image` longblob,
  `service_name` varchar(255) DEFAULT NULL,
  `shop_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `services`
--

LOCK TABLES `services` WRITE;
/*!40000 ALTER TABLE `services` DISABLE KEYS */;
/*!40000 ALTER TABLE `services` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `address` varchar(3000) DEFAULT NULL,
  `dob` datetime(6) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phoneno` varchar(255) DEFAULT NULL,
  `role_id` bigint DEFAULT NULL,
  `role_name` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'mumbai','2014-02-05 00:00:00.000000','Admin@123Gmail.com','Male','Admin@1234','9739124890',1,'Admin','admin'),(5,'osara','2023-08-24 00:00:00.000000','dashrath@123gmail.com','Male','User@123','8989098799',2,'User','dashrat Ahir'),(6,'Aut cillum fugiat pr','2023-08-23 00:00:00.000000','fihumyvor@mailinator.com','Male','Pa$$w0rd!','+1 (184) 373-1087',2,'User','nabebim'),(7,'Eum porro labore qui','2000-02-08 00:00:00.000000','bidoda@mailinator.com','Male','Demo@123','8585858585',2,'User','sefyferoda'),(8,'fgfdg','2000-09-18 00:00:00.000000','Emp123@gmial.com','Male','Demo@123','8585858585',3,'Employee','emp'),(9,'Dolore cillum iure v','2023-09-14 00:00:00.000000','jyjif@mailinator.com','Male','Pa$$w0rd!','+1 (545) 581-5027',3,'Employee','wypuboemp'),(10,'Reiciendis voluptate','2023-09-06 00:00:00.000000','hyhycytu@mailinator.com','Male','Demo@123','+1 (454) 739-6126',2,'User','tapetopppp'),(11,'Dolores architecto d','2023-09-07 00:00:00.000000','vysovexan@mailinator.com','Male','Pa$$w0rd!','+1 (478) 372-3137',2,'User','gimaba');
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

-- Dump completed on 2023-09-25 17:30:35
