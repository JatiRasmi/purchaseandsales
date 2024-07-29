-- MySQL dump 10.13  Distrib 8.0.28, for Linux (x86_64)
--
-- Host: localhost    Database: purchasesales
-- ------------------------------------------------------
-- Server version	8.0.28-0ubuntu0.20.04.3

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `address` varchar(50) NOT NULL,
  `contact` varchar(10) NOT NULL,
  `email` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_aw6s3lg0as8w492n40r8tgh2m` (`contact`),
  UNIQUE KEY `UK_dwk6cx0afu8bs9o4t536v1j5v` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'Bhaktapur','9841526379','rasmi12@gmail.com','rasmi jati');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `my_user`
--

DROP TABLE IF EXISTS `my_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `my_user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(50) NOT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_oojv0tfv8mcxgq60bbibie6cm` (`email`),
  UNIQUE KEY `UK_9dn7n3we4p3df9l5vvdggacv7` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `my_user`
--

LOCK TABLES `my_user` WRITE;
/*!40000 ALTER TABLE `my_user` DISABLE KEYS */;
INSERT INTO `my_user` VALUES (1,'admin123@gmail.com','admin','$2a$10$pqQXaRJXHP3NtyACRBGhR.p/QR7OwWE1EM0bLV/ED8S7ylXw6Bw1C');
/*!40000 ALTER TABLE `my_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(50) DEFAULT NULL,
  `name` varchar(50) NOT NULL,
  `unit_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_jmivyxk9rmgysrmsqw15lqr5b` (`name`),
  KEY `FK_g030a1iq0t53dprsxmeg12thm` (`unit_id`),
  CONSTRAINT `FK_g030a1iq0t53dprsxmeg12thm` FOREIGN KEY (`unit_id`) REFERENCES `unit` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'fruits','Mango',1),(2,'fruits','Banana',1),(3,'fruits','Pineapple',2),(6,'fruits','Apple',1),(7,'fruits','Pear',2);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchase_order`
--

DROP TABLE IF EXISTS `purchase_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `purchase_order` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `discountAmount` decimal(19,2) NOT NULL,
  `expected_delivery_date` date NOT NULL,
  `subTotal` decimal(19,2) NOT NULL,
  `total_amount` decimal(19,2) NOT NULL,
  `vatAmount` decimal(19,2) NOT NULL,
  `supplier_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_dgil1aiqhau54y5giflc3806n` (`supplier_id`),
  CONSTRAINT `FK_dgil1aiqhau54y5giflc3806n` FOREIGN KEY (`supplier_id`) REFERENCES `supplier` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchase_order`
--

LOCK TABLES `purchase_order` WRITE;
/*!40000 ALTER TABLE `purchase_order` DISABLE KEYS */;
INSERT INTO `purchase_order` VALUES (1,'2022-08-05',40.00,'2022-08-08',1600.00,1680.00,120.00,1),(2,'2022-08-08',600.00,'2022-08-15',6000.00,5400.00,0.00,1),(3,'2022-08-10',1170.00,'2022-08-17',8100.00,7744.50,814.50,1),(4,'2022-08-15',0.00,'2022-08-19',100.00,113.00,13.00,1);
/*!40000 ALTER TABLE `purchase_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchase_order_detail`
--

DROP TABLE IF EXISTS `purchase_order_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `purchase_order_detail` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `discount` decimal(19,2) NOT NULL,
  `discount_amount` decimal(19,2) NOT NULL,
  `quantity` decimal(19,2) NOT NULL,
  `rate` decimal(19,2) NOT NULL,
  `sub_total` decimal(19,2) NOT NULL,
  `sub_total_after_discount` decimal(19,2) NOT NULL,
  `total_amount` decimal(19,2) NOT NULL,
  `vat` decimal(19,2) NOT NULL,
  `vat_amount` decimal(19,2) NOT NULL,
  `product_id` bigint NOT NULL,
  `purchase_order_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_fnub9fkpxoi15a5wm4g9s5div` (`product_id`),
  KEY `FK_ddgwvg20ddyobyml5vnqx37lr` (`purchase_order_id`),
  CONSTRAINT `FK_ddgwvg20ddyobyml5vnqx37lr` FOREIGN KEY (`purchase_order_id`) REFERENCES `purchase_order` (`id`),
  CONSTRAINT `FK_fnub9fkpxoi15a5wm4g9s5div` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchase_order_detail`
--

LOCK TABLES `purchase_order_detail` WRITE;
/*!40000 ALTER TABLE `purchase_order_detail` DISABLE KEYS */;
INSERT INTO `purchase_order_detail` VALUES (1,10.00,40.00,20.00,20.00,400.00,360.00,360.00,0.00,0.00,1,1),(2,0.00,0.00,60.00,20.00,1200.00,1200.00,1320.00,10.00,120.00,2,1),(3,10.00,600.00,300.00,20.00,6000.00,5400.00,5400.00,0.00,0.00,1,2),(4,10.00,450.00,50.00,90.00,4500.00,4050.00,4576.50,13.00,526.50,7,3),(5,20.00,720.00,60.00,60.00,3600.00,2880.00,3168.00,10.00,288.00,6,3),(6,0.00,0.00,10.00,10.00,100.00,100.00,113.00,13.00,13.00,1,4);
/*!40000 ALTER TABLE `purchase_order_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sales_order`
--

DROP TABLE IF EXISTS `sales_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sales_order` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `discountAmount` decimal(19,2) NOT NULL,
  `subTotal` decimal(19,2) NOT NULL,
  `total_amount` decimal(19,2) NOT NULL,
  `vatAmount` decimal(19,2) NOT NULL,
  `customer_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_tgmt6ny9xrqqht5f3b2c9w8vl` (`customer_id`),
  CONSTRAINT `FK_tgmt6ny9xrqqht5f3b2c9w8vl` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sales_order`
--

LOCK TABLES `sales_order` WRITE;
/*!40000 ALTER TABLE `sales_order` DISABLE KEYS */;
INSERT INTO `sales_order` VALUES (11,'2022-08-15',50.00,500.00,508.50,58.50,1);
/*!40000 ALTER TABLE `sales_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sales_order_detail`
--

DROP TABLE IF EXISTS `sales_order_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sales_order_detail` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `discount` decimal(19,2) NOT NULL,
  `discount_amount` decimal(19,2) NOT NULL,
  `quantity` decimal(19,2) NOT NULL,
  `rate` decimal(19,2) NOT NULL,
  `sub_total` decimal(19,2) NOT NULL,
  `sub_total_after_discount` decimal(19,2) NOT NULL,
  `total_amount` decimal(19,2) NOT NULL,
  `vat` decimal(19,2) NOT NULL,
  `vat_amount` decimal(19,2) NOT NULL,
  `product_id` bigint NOT NULL,
  `sales_order_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_p1e8ja6ky14u3e9en6xluww20` (`product_id`),
  KEY `FK_jtillqfxnp2p8onvtegx4fcln` (`sales_order_id`),
  CONSTRAINT `FK_jtillqfxnp2p8onvtegx4fcln` FOREIGN KEY (`sales_order_id`) REFERENCES `sales_order` (`id`),
  CONSTRAINT `FK_p1e8ja6ky14u3e9en6xluww20` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sales_order_detail`
--

LOCK TABLES `sales_order_detail` WRITE;
/*!40000 ALTER TABLE `sales_order_detail` DISABLE KEYS */;
INSERT INTO `sales_order_detail` VALUES (17,10.00,50.00,10.00,50.00,500.00,450.00,508.50,13.00,58.50,1,11);
/*!40000 ALTER TABLE `sales_order_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supplier`
--

DROP TABLE IF EXISTS `supplier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `supplier` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `address` varchar(50) NOT NULL,
  `contact` varchar(10) NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  `email` varchar(25) NOT NULL,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_nvnw6xt6bycy4kxr3ieuvpso` (`contact`),
  UNIQUE KEY `UK_g7qiwwu4vpciysmeeyme9gg1d` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplier`
--

LOCK TABLES `supplier` WRITE;
/*!40000 ALTER TABLE `supplier` DISABLE KEYS */;
INSERT INTO `supplier` VALUES (1,'Kathmandu','9841414152','fruits supplier','abc@gmail.com','abc supplier'),(2,'Bhaktapur','9841414141','fruits supplier','xyz@gmail.com','xyz supplier');
/*!40000 ALTER TABLE `supplier` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `unit`
--

DROP TABLE IF EXISTS `unit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `unit` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_aa58c9de9eu0v585le47w25my` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `unit`
--

LOCK TABLES `unit` WRITE;
/*!40000 ALTER TABLE `unit` DISABLE KEYS */;
INSERT INTO `unit` VALUES (2,'gm'),(1,'kg');
/*!40000 ALTER TABLE `unit` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-08-18 13:31:45
