-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: shoppingdb
-- ------------------------------------------------------
-- Server version	8.0.18

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
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account` (
  `user_mail` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `account_role` varchar(45) NOT NULL,
  `user_name` varchar(45) NOT NULL,
  `user_address` varchar(45) NOT NULL,
  `user_phone` varchar(45) NOT NULL,
  PRIMARY KEY (`user_mail`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES ('kieuquang.8888','Quang889','admin','shine','Bac Giang','0923240916'),('kieuquang.8888@gmail.com','Quang889','admin','shine','Bac Giang','0923240916');
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `user_mail` varchar(45) NOT NULL,
  `order_id` int(11) NOT NULL AUTO_INCREMENT,
  `order_status` varchar(45) NOT NULL,
  `order_date` varchar(45) NOT NULL,
  `order_discount_code` varchar(45) NOT NULL,
  `order_address` varchar(45) NOT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES ('kieuquang.8888@gmail.com',1,'1','2020-06-14','ABCD','Ha Noi');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders_detail`
--

DROP TABLE IF EXISTS `orders_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders_detail` (
  `order_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `amount_product` int(11) NOT NULL,
  `price_product` float NOT NULL,
  PRIMARY KEY (`order_id`),
  KEY `product_id` (`product_id`),
  CONSTRAINT `orders_detail_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders_detail`
--

LOCK TABLES `orders_detail` WRITE;
/*!40000 ALTER TABLE `orders_detail` DISABLE KEYS */;
INSERT INTO `orders_detail` VALUES (1,1,12,1234);
/*!40000 ALTER TABLE `orders_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `product_id` int(11) NOT NULL,
  `product_name` varchar(45) NOT NULL,
  `product_des` varchar(2000) NOT NULL,
  `product_price` double NOT NULL,
  `product_img_source` varchar(455) NOT NULL,
  `product_type` varchar(45) NOT NULL,
  `product_band` varchar(45) NOT NULL,
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,'Iphone 11 Pro','Product Description: Màn hình: 6.1\", Liquid Retina </br>HĐH: iOS 13 CPU: Apple A13 Bionic 8 nhân 24 luồng </br>RAM: 256GB, ROM: 1 MB Camera: 2 MP </br>Selfie: 1.3 MP',1234,'https://cdn.tgdd.vn/Products/Images/42/200533/iphone-11-pro-max-green-400x400.jpg','Luxury','Apple'),(2,'Iphone X','Product Description: Màn hình: 6.1\", Liquid Retina </br>HĐH: iOS 13 CPU: Apple A13 Bionic 8 nhân 24 luồng </br>RAM: 256GB, ROM: 1 MB Camera: 2 MP </br>Selfie: 1.3 MP',123,'https://cdn.tgdd.vn/Products/Images/42/200533/iphone-11-pro-max-green-400x400.jpg','Luxury','Apple'),(3,'Iphone 7S','Product Description: Màn hình: 6.1\", Liquid Retina </br>HĐH: iOS 13 CPU: Apple A13 Bionic 8 nhân 24 luồng </br>RAM: 256GB, ROM: 1 MB Camera: 2 MP </br>Selfie: 1.3 MP',123,'https://cdn.tgdd.vn/Products/Images/42/200533/iphone-11-pro-max-green-400x400.jpg','Luxury','Apple'),(4,'Iphone XS Max','Product Description: Màn hình: 6.1\", Liquid Retina </br>HĐH: iOS 13 CPU: Apple A13 Bionic 8 nhân 24 luồng </br>RAM: 256GB, ROM: 1 MB Camera: 2 MP </br>Selfie: 1.3 MP',200,'https://cdn.tgdd.vn/Products/Images/42/200533/iphone-11-pro-max-green-400x400.jpg','Luxury','Apple'),(5,'Iphone 8 Plus','Product Description: Màn hình: 6.1\", Liquid Retina </br>HĐH: iOS 13 CPU: Apple A13 Bionic 8 nhân 24 luồng </br>RAM: 256GB, ROM: 1 MB Camera: 2 MP </br>Selfie: 1.3 MP',100,'https://cdn.tgdd.vn/Products/Images/42/200533/iphone-11-pro-max-green-400x400.jpg','Luxury','Apple'),(6,'Galaxy S10','Product Description: Màn hình: 6.1\", Liquid Retina </br>HĐH: iOS 13 CPU: Apple A13 Bionic 8 nhân 24 luồng </br>RAM: 256GB, ROM: 1 MB Camera: 2 MP </br>Selfie: 1.3 MP',150,'https://cdn.tgdd.vn/Products/Images/42/200533/iphone-11-pro-max-green-400x400.jpg','Normal','Samsung');
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-07-13  1:35:47
