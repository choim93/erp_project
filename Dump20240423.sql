-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: spring_erp_db
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
-- Table structure for table `account_accounts`
--

DROP TABLE IF EXISTS `account_accounts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account_accounts` (
  `account_id` bigint NOT NULL AUTO_INCREMENT,
  `account_name` varchar(10) DEFAULT NULL,
  `account_type` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`account_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_accounts`
--

LOCK TABLES `account_accounts` WRITE;
/*!40000 ALTER TABLE `account_accounts` DISABLE KEYS */;
INSERT INTO `account_accounts` VALUES (1,'제품매출','수익'),(2,'매출원가','비용'),(3,'복리후생비','비용'),(4,'급여','비용');
/*!40000 ALTER TABLE `account_accounts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `business_partners`
--

DROP TABLE IF EXISTS `business_partners`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `business_partners` (
  `business_partner_id` varchar(12) NOT NULL,
  `business_partner_name` varchar(30) DEFAULT NULL,
  `business_partner_address` varchar(255) DEFAULT NULL,
  `business_partner_contact` varchar(13) DEFAULT NULL,
  PRIMARY KEY (`business_partner_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `business_partners`
--

LOCK TABLES `business_partners` WRITE;
/*!40000 ALTER TABLE `business_partners` DISABLE KEYS */;
INSERT INTO `business_partners` VALUES ('024-24-04356','눈꽃케이크','전라북도 부안군 주산면 덕림리 924-7','070-2239-3939'),('031-26-74356','그린 베이커리','충남 당진시 서해안고속도로 275','070-8279-8554'),('042-53-44459','디저트나라','강원도 홍천 홍천읍 홍천동 309-3','070-7493-1189'),('159-32-67744','(주)재료 나라','세종특별자치시 보람동 324-47','070-8898-9009'),('317-65-00146','(주)온파 ','대구광역시 수성구 사과로 442-2','070-2842-2384'),('332-55-89898','(주)가루 사랑','서울특별시 마포구 한양로 2034','070-1222-3244'),('424-00-30955','(주)충만','대전광역시 남구 하늘로 123-43','070-8848-1234'),('442-12-14237','(주)잠실','인천광역시 미추홀구 아마존로 122-3','070-1120-4833'),('546-43-64356','둔산 빵집','강원도 태백 구문소동 42-499','070-8943-8293');
/*!40000 ALTER TABLE `business_partners` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contracts`
--

DROP TABLE IF EXISTS `contracts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contracts` (
  `contract_id` bigint NOT NULL AUTO_INCREMENT,
  `is_selling` tinyint DEFAULT NULL,
  `contract_business_partner_id` varchar(12) DEFAULT NULL,
  `contract_storage_id` bigint DEFAULT NULL,
  `contract_responsible_employee_id` bigint DEFAULT NULL,
  `contract_item_id` bigint DEFAULT NULL,
  `contract_item_quantity` int DEFAULT NULL,
  `deal_date` date DEFAULT NULL,
  PRIMARY KEY (`contract_id`),
  KEY `contract_responsible_employee_id_idx` (`contract_responsible_employee_id`),
  KEY `contract_item_id_idx` (`contract_item_id`),
  KEY `contract_business_partner_id_idx` (`contract_business_partner_id`),
  KEY `contract_storage_id_idx` (`contract_storage_id`),
  CONSTRAINT `contract_business_partner_id` FOREIGN KEY (`contract_business_partner_id`) REFERENCES `business_partners` (`business_partner_id`),
  CONSTRAINT `contract_item_id` FOREIGN KEY (`contract_item_id`) REFERENCES `items` (`Item_id`),
  CONSTRAINT `contract_responsible_employee_id` FOREIGN KEY (`contract_responsible_employee_id`) REFERENCES `employees` (`employee_id`),
  CONSTRAINT `contract_storage_id` FOREIGN KEY (`contract_storage_id`) REFERENCES `storages` (`storage_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contracts`
--

LOCK TABLES `contracts` WRITE;
/*!40000 ALTER TABLE `contracts` DISABLE KEYS */;
INSERT INTO `contracts` VALUES (1,0,'442-12-14237',5,6,6,50,'2023-08-30'),(2,0,'442-12-14237',5,6,1,50,'2023-12-01'),(3,1,'546-43-64356',5,7,105,100,'2023-12-01'),(4,0,'159-32-67744',5,6,7,50,'2023-12-01'),(5,0,'159-32-67744',5,6,6,50,'2023-12-01'),(6,1,'031-26-74356',5,7,104,100,'2023-12-01');
/*!40000 ALTER TABLE `contracts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `departments`
--

DROP TABLE IF EXISTS `departments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `departments` (
  `department_id` bigint NOT NULL AUTO_INCREMENT,
  `department_name` varchar(30) DEFAULT NULL,
  `department_head_id` bigint DEFAULT NULL,
  PRIMARY KEY (`department_id`),
  KEY `department_head_id_idx` (`department_head_id`),
  CONSTRAINT `department_head_id` FOREIGN KEY (`department_head_id`) REFERENCES `employees` (`employee_id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `departments`
--

LOCK TABLES `departments` WRITE;
/*!40000 ALTER TABLE `departments` DISABLE KEYS */;
INSERT INTO `departments` VALUES (1,'경영팀',1),(2,'회계팀',4),(3,'구매팀',6),(4,'생산팀',5),(5,'판매팀',7),(6,'영업팀',3),(7,'개발팀',2);
/*!40000 ALTER TABLE `departments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employees`
--

DROP TABLE IF EXISTS `employees`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employees` (
  `employee_id` bigint NOT NULL AUTO_INCREMENT,
  `password` varchar(255) DEFAULT NULL,
  `department_id` bigint DEFAULT NULL,
  `employee_name` varchar(30) DEFAULT NULL,
  `employee_email` varchar(255) DEFAULT NULL,
  `employee_contact` varchar(30) DEFAULT NULL,
  `position_id` bigint DEFAULT NULL,
  `employee_salary` int DEFAULT NULL,
  `employee_recruit_date` date DEFAULT NULL,
  `employee_retirement_date` date DEFAULT NULL,
  PRIMARY KEY (`employee_id`),
  KEY `position_id_idx` (`position_id`),
  CONSTRAINT `position_id` FOREIGN KEY (`position_id`) REFERENCES `positions` (`position_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employees`
--

LOCK TABLES `employees` WRITE;
/*!40000 ALTER TABLE `employees` DISABLE KEYS */;
INSERT INTO `employees` VALUES (1,'kFvOC4CpR7zFlDOHa89Pfw',1,'안여진','dksduwls@dksduwls.com','010-1515-4646',12,0,'2020-01-16',NULL),(2,'9aPKGJZ53oA1Paos4edEMQ',7,'성지해','tjdwlgo@tjdwlgo.com','010-1122-1243',10,0,'2020-01-16',NULL),(3,'P2Pnyzr7CL3QR',6,'고혜림','rhgPfla@rhgPfla.com','010-5997-7799',7,0,'2020-09-12',NULL),(4,'zOytyKRjEEZGvkOHJemqYg',2,'정이슬','wjddltmf@wjddltmf.com','010-7979-7811',7,0,'2021-02-09',NULL),(5,'sfsfsfsdfw',4,'서경완','tjruddhks@tjruddhks.com','010-1599-7959',6,0,'2021-05-02',NULL),(6,'',3,'심아롱','tladkfhd@tladkfhd.com','010-7951-8593',5,0,'2021-12-19',NULL),(7,'qweqo123',5,'고남순','\'rhskatns@rhskatns.com','010-9318-9998',4,0,'2021-12-31',NULL),(8,'1koooqo',1,'황보순','ghkdqhskatns@ghkdqhskatns.com','010-9177-5831',3,0,'2022-07-19',NULL),(9,'qwowo1l',4,'남이경','skadlrud@skadlrud.com','010-8213-1329',2,0,'2022-09-12',NULL),(10,'qqpp1p',5,'박은채','qkrdmsco@qkrdmsco.com','010-4799-2313',2,0,'2023-01-02',NULL),(11,'qp1fnn',2,'김성철','kimsungcc@naver.com','010-5393-1927',2,0,'2023-02-17',NULL),(12,'llll1ll',5,'홍성민','hhuhung@naver.com','010-7954-9577',1,0,'2023-08-30',NULL);
/*!40000 ALTER TABLE `employees` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inventory`
--

DROP TABLE IF EXISTS `inventory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inventory` (
  `inventory_id` bigint NOT NULL AUTO_INCREMENT,
  `item_id` bigint NOT NULL,
  `storage_id` bigint NOT NULL,
  `item_quantity` int DEFAULT NULL,
  `move_reason_id` bigint DEFAULT NULL,
  PRIMARY KEY (`inventory_id`),
  KEY `inventory_item_id_idx` (`item_id`),
  KEY `inventory_storage_id_idx` (`storage_id`),
  KEY `move_reason_id_idx` (`move_reason_id`),
  CONSTRAINT `inventory_item_id` FOREIGN KEY (`item_id`) REFERENCES `items` (`Item_id`),
  CONSTRAINT `inventory_storage_id` FOREIGN KEY (`storage_id`) REFERENCES `storages` (`storage_id`),
  CONSTRAINT `move_reason_id` FOREIGN KEY (`move_reason_id`) REFERENCES `inventory_move_reasons` (`reason_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inventory`
--

LOCK TABLES `inventory` WRITE;
/*!40000 ALTER TABLE `inventory` DISABLE KEYS */;
INSERT INTO `inventory` VALUES (1,6,5,50,1),(2,1,5,50,1),(3,105,5,100,5),(4,6,5,-50,6),(5,1,5,-50,6),(6,105,5,-100,2),(7,7,5,50,1),(8,6,5,50,1),(9,104,5,100,5),(10,7,5,-50,6),(11,6,5,-50,6),(12,104,5,-100,2);
/*!40000 ALTER TABLE `inventory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inventory_move_reasons`
--

DROP TABLE IF EXISTS `inventory_move_reasons`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inventory_move_reasons` (
  `reason_id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`reason_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inventory_move_reasons`
--

LOCK TABLES `inventory_move_reasons` WRITE;
/*!40000 ALTER TABLE `inventory_move_reasons` DISABLE KEYS */;
INSERT INTO `inventory_move_reasons` VALUES (1,'구매'),(2,'판매'),(3,'창고이동'),(4,'재고조정'),(5,'생산'),(6,'소모');
/*!40000 ALTER TABLE `inventory_move_reasons` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `items`
--

DROP TABLE IF EXISTS `items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `items` (
  `Item_id` bigint NOT NULL AUTO_INCREMENT,
  `is_raw_material` tinyint(1) NOT NULL,
  `item_price` int DEFAULT NULL,
  `item_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`Item_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5012 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `items`
--

LOCK TABLES `items` WRITE;
/*!40000 ALTER TABLE `items` DISABLE KEYS */;
INSERT INTO `items` VALUES (1,1,3000,'밀가루 1kg'),(2,1,7000,'베이킹 소다 1kg'),(3,1,6000,'소금 1kg'),(4,1,15000,'버터 1kg'),(5,1,5000,'액상 계란 1kg'),(6,1,14000,'설탕 1kg'),(7,1,20000,'전지 분유 1kg'),(8,1,9000,'냉동 딸기 1box'),(9,1,12000,'롤치즈 1box'),(100,0,20000,'식빵 1box'),(102,0,36000,'머핀 1box'),(103,0,40000,'바게트 1box'),(104,0,60000,'크로와상 1box'),(105,0,25000,'브리오슈 1box'),(106,0,36000,'소금빵 1box'),(107,0,10000,'허니 브레드 1개입'),(108,0,20000,'딸기 케이크  1개입');
/*!40000 ALTER TABLE `items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `positions`
--

DROP TABLE IF EXISTS `positions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `positions` (
  `position_id` bigint NOT NULL AUTO_INCREMENT,
  `position_name` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`position_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `positions`
--

LOCK TABLES `positions` WRITE;
/*!40000 ALTER TABLE `positions` DISABLE KEYS */;
INSERT INTO `positions` VALUES (1,'인턴'),(2,'사원'),(3,'주임'),(4,'대리'),(5,'과장'),(6,'차장'),(7,'부장'),(8,'이사'),(9,'상무'),(10,'전무'),(11,'부사장'),(12,'사장'),(13,'부회장'),(14,'회장님');
/*!40000 ALTER TABLE `positions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `production`
--

DROP TABLE IF EXISTS `production`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `production` (
  `production_id` bigint NOT NULL AUTO_INCREMENT,
  `production_date` datetime DEFAULT NULL,
  `item_id` bigint DEFAULT NULL,
  `production_quantity` int DEFAULT NULL,
  `storage_id` bigint DEFAULT NULL,
  PRIMARY KEY (`production_id`),
  KEY `item_id_idx_2` (`item_id`),
  KEY `production_storage_id_idx` (`storage_id`),
  CONSTRAINT `item_id` FOREIGN KEY (`item_id`) REFERENCES `items` (`Item_id`),
  CONSTRAINT `production_storage_id` FOREIGN KEY (`storage_id`) REFERENCES `storages` (`storage_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `production`
--

LOCK TABLES `production` WRITE;
/*!40000 ALTER TABLE `production` DISABLE KEYS */;
INSERT INTO `production` VALUES (1,'2023-12-01 00:00:00',105,100,5),(2,'2023-12-01 00:00:00',104,100,5);
/*!40000 ALTER TABLE `production` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `production_raw_materials`
--

DROP TABLE IF EXISTS `production_raw_materials`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `production_raw_materials` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `production_id` bigint DEFAULT NULL,
  `raw_material_id` bigint DEFAULT NULL,
  `raw_material_quantity` int DEFAULT NULL,
  `storage_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `production_id_idx` (`production_id`),
  KEY `raw_material_id_idx` (`raw_material_id`),
  KEY `storage_id_idx` (`storage_id`),
  CONSTRAINT `production_id` FOREIGN KEY (`production_id`) REFERENCES `production` (`production_id`),
  CONSTRAINT `raw_material_id` FOREIGN KEY (`raw_material_id`) REFERENCES `items` (`Item_id`),
  CONSTRAINT `storage_id` FOREIGN KEY (`storage_id`) REFERENCES `storages` (`storage_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `production_raw_materials`
--

LOCK TABLES `production_raw_materials` WRITE;
/*!40000 ALTER TABLE `production_raw_materials` DISABLE KEYS */;
INSERT INTO `production_raw_materials` VALUES (1,1,6,50,5),(2,1,1,50,5),(3,2,7,50,4),(4,2,6,50,4);
/*!40000 ALTER TABLE `production_raw_materials` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `storages`
--

DROP TABLE IF EXISTS `storages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `storages` (
  `storage_id` bigint NOT NULL AUTO_INCREMENT,
  `storage_name` varchar(30) DEFAULT NULL,
  `storage_address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`storage_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `storages`
--

LOCK TABLES `storages` WRITE;
/*!40000 ALTER TABLE `storages` DISABLE KEYS */;
INSERT INTO `storages` VALUES (1,'세종창고','세종 한누리대로 2130'),(2,'대전창고','대전 서구 둔산로 100'),(3,'서울창고','서울 중구 세종대로 110'),(4,'수원창고','경기 수원시 팔달구 효원로 241'),(5,'파주창고','경기 파주시 시청로 50');
/*!40000 ALTER TABLE `storages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transfer`
--

DROP TABLE IF EXISTS `transfer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transfer` (
  `transfer_id` bigint NOT NULL AUTO_INCREMENT,
  `bank_account_number` varchar(13) DEFAULT NULL,
  `bank_account_name` varchar(20) DEFAULT NULL,
  `volume` int DEFAULT NULL,
  `account_id` bigint DEFAULT NULL,
  `info` varchar(20) DEFAULT NULL,
  `business_partner_id` varchar(12) DEFAULT NULL,
  `transfer_datetime` datetime DEFAULT NULL,
  `balance` int DEFAULT NULL,
  PRIMARY KEY (`transfer_id`),
  KEY `account_business_parter_id_idx` (`business_partner_id`),
  KEY `account_id_idx` (`account_id`),
  CONSTRAINT `account_business_parter_id` FOREIGN KEY (`business_partner_id`) REFERENCES `business_partners` (`business_partner_id`),
  CONSTRAINT `account_id` FOREIGN KEY (`account_id`) REFERENCES `account_accounts` (`account_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transfer`
--

LOCK TABLES `transfer` WRITE;
/*!40000 ALTER TABLE `transfer` DISABLE KEYS */;
/*!40000 ALTER TABLE `transfer` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-04-23 18:33:11
