-- MySQL dump 10.13  Distrib 8.0.42, for Win64 (x86_64)
--
-- Host: localhost    Database: my_db
-- ------------------------------------------------------
-- Server version	8.0.42

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
-- Table structure for table `art_t`
--

DROP TABLE IF EXISTS `art_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `art_t` (
  `art_idx` bigint NOT NULL AUTO_INCREMENT,
  `subject` varchar(100) NOT NULL,
  `auther` varchar(50) NOT NULL,
  `type` varchar(30) DEFAULT NULL,
  `price` decimal(9,2) DEFAULT NULL,
  `make_year` varchar(20) DEFAULT NULL,
  `reg_date` date DEFAULT NULL,
  PRIMARY KEY (`art_idx`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `art_t`
--

LOCK TABLES `art_t` WRITE;
/*!40000 ALTER TABLE `art_t` DISABLE KEYS */;
/*!40000 ALTER TABLE `art_t` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bbs`
--

DROP TABLE IF EXISTS `bbs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bbs` (
  `b_idx` bigint NOT NULL AUTO_INCREMENT,
  `hit` bigint DEFAULT '0',
  `state` bigint DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `write_date` varchar(255) DEFAULT NULL,
  `writer` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`b_idx`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bbs`
--

LOCK TABLES `bbs` WRITE;
/*!40000 ALTER TABLE `bbs` DISABLE KEYS */;
INSERT INTO `bbs` VALUES (1,0,0,'테스트입니다.','제목1','25. 9. 22. 오후 3:31','마루치'),(2,0,0,'테스트입니다.','제목2','25. 9. 22. 오후 3:31','아라치'),(3,0,0,'테스트입니다.','제목3','25. 9. 22. 오후 3:31','손오공'),(4,0,0,'테스트입니다.','제목4','25. 9. 22. 오후 3:31','도라에몽');
/*!40000 ALTER TABLE `bbs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bbs_t`
--

DROP TABLE IF EXISTS `bbs_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bbs_t` (
  `b_idx` bigint NOT NULL AUTO_INCREMENT,
  `subject` varchar(50) DEFAULT NULL,
  `writer` varchar(20) DEFAULT NULL,
  `content` text,
  `file_name` varchar(50) DEFAULT NULL,
  `ori_name` varchar(50) DEFAULT NULL,
  `pwd` varchar(20) DEFAULT NULL,
  `write_date` date DEFAULT NULL,
  `ip` varchar(30) DEFAULT NULL,
  `hit` int DEFAULT NULL,
  `bname` varchar(20) DEFAULT NULL,
  `status` int DEFAULT NULL,
  PRIMARY KEY (`b_idx`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bbs_t`
--

LOCK TABLES `bbs_t` WRITE;
/*!40000 ALTER TABLE `bbs_t` DISABLE KEYS */;
INSERT INTO `bbs_t` VALUES (1,'테스트','마루치',NULL,NULL,NULL,'1111','2025-07-22','192.168.0.58',10,'BBS',0),(2,'연습1','을불','메롱<hr>',NULL,NULL,NULL,'2025-07-23','0:0:0:0:0:0:0:1',3,'BBS',0),(3,'연습2','이순신','<p>거북선</p>','스크린샷 2025-06-12 120251.png','스크린샷 2025-06-12 120251.png',NULL,'2025-07-23','0:0:0:0:0:0:0:1',0,'BBS',0),(4,'1','1','<p>1</p>','스크린샷 2025-06-12 1202511.png','스크린샷 2025-06-12 120251.png',NULL,'2025-07-23','0:0:0:0:0:0:0:1',6,'BBS',0),(5,'2','2','<p>2</p>',NULL,NULL,NULL,'2025-07-23','0:0:0:0:0:0:0:1',0,'BBS',0),(6,'3','3','<p>3</p>',NULL,NULL,NULL,'2025-07-23','0:0:0:0:0:0:0:1',1,'BBS',0),(7,'4','4','<p>4</p>',NULL,NULL,NULL,'2025-07-23','0:0:0:0:0:0:0:1',1,'BBS',0),(8,'5','5','<p>5</p>',NULL,NULL,NULL,'2025-07-23','0:0:0:0:0:0:0:1',9,'BBS',0),(9,'635','635','<p>635</p>',NULL,NULL,NULL,'2025-07-23','0:0:0:0:0:0:0:1',55,'BBS',0),(10,'7','7','<p>7</p>','스크린샷 2025-06-13 091910.png','스크린샷 2025-06-13 091910.png',NULL,'2025-07-23','0:0:0:0:0:0:0:1',1,'BBS',0),(11,'8','8','<p>8</p>',NULL,NULL,NULL,'2025-07-23','0:0:0:0:0:0:0:1',1,'BBS',0),(12,'9','9','<p>1111</p>',NULL,NULL,NULL,'2025-07-23','0:0:0:0:0:0:0:1',4,'BBS',0),(13,'제목','11','<p><img src=\"/resources/editor_img//19.gif\" style=\"width: 120px;\"><br></p>',NULL,NULL,NULL,'2025-07-23','0:0:0:0:0:0:0:1',6,'BBS',0),(14,'제목','111','<p>목목</p>',NULL,NULL,NULL,'2025-07-23','0:0:0:0:0:0:0:1',161,'BBS',0),(18,'111','111','<p>111</p>','',NULL,NULL,'2025-08-29','0:0:0:0:0:0:0:1',2,'BBS',0),(19,'새로운제목','이도','<p><img src=\"/resources/editor_img///1.gif\" style=\"width: 120px;\"><br></p>','',NULL,NULL,'2025-09-01','0:0:0:0:0:0:0:1',1,'BBS',0),(20,'111','이도','<p><img src=\"/resources/editor_img///11.gif\" style=\"width: 120px;\"><br></p>','',NULL,NULL,'2025-09-01','0:0:0:0:0:0:0:1',5,'BBS',0),(21,'글쓴이','글쓴이','<p>글쓴이 글의 내용이 바뀜</p>','',NULL,NULL,'2025-09-02','0:0:0:0:0:0:0:1',3,'BBS',0),(22,'클릭','괴도우진','<p><img src=\"/resources/editor_img/2.gif\" style=\"width: 120px;\"><br></p>','2.gif','2.gif',NULL,'2025-09-08','0:0:0:0:0:0:0:1',2,'BBS',0),(23,'111','132142161612','<p><img src=\"/resources/editor_img/13.gif\" style=\"width: 120px;\"><br></p>','21.gif',NULL,NULL,'2025-09-08','0:0:0:0:0:0:0:1',1,'BBS',0),(24,'게시판','백만번째 이름','<p><img src=\"/resources/editor_img/15.gif\" style=\"width: 120px;\"><br></p>','11.gif','1.gif',NULL,'2025-09-08','0:0:0:0:0:0:0:1',6,'BBS',0),(25,'수정이','백만번째 이름','<p><img src=\"/resources/editor_img/131.gif\" style=\"width: 120px;\"><br></p>','17.gif',NULL,NULL,'2025-09-09','0:0:0:0:0:0:0:1',2,'BBS',0),(26,'다시','백만번째 이름','<p><img src=\"/resources/editor_img/16.gif\" style=\"width: 120px;\"><br></p>','12.gif','1.gif',NULL,'2025-09-09','0:0:0:0:0:0:0:1',8,'BBS',0),(27,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,0),(28,'1','1','1',NULL,NULL,NULL,NULL,NULL,0,'BBS',0),(29,'2','2','2',NULL,NULL,NULL,NULL,NULL,0,'BBS',0),(30,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,0),(31,'파람즈 뺌','파람즈 ㅃㅁ','파람즈 뺌',NULL,NULL,NULL,NULL,NULL,0,'BBS',0);
/*!40000 ALTER TABLE `bbs_t` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book_t`
--

DROP TABLE IF EXISTS `book_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book_t` (
  `b_idx` bigint NOT NULL AUTO_INCREMENT,
  `subject` varchar(200) DEFAULT NULL,
  `author` varchar(60) DEFAULT NULL,
  `reg_date` date DEFAULT NULL,
  `price` decimal(9,1) DEFAULT NULL,
  PRIMARY KEY (`b_idx`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book_t`
--

LOCK TABLES `book_t` WRITE;
/*!40000 ALTER TABLE `book_t` DISABLE KEYS */;
/*!40000 ALTER TABLE `book_t` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category1_t`
--

DROP TABLE IF EXISTS `category1_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category1_t` (
  `idx` int NOT NULL AUTO_INCREMENT,
  `c_name` varchar(255) DEFAULT NULL,
  `desc` varchar(45) DEFAULT NULL,
  `status` int DEFAULT NULL,
  PRIMARY KEY (`idx`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category1_t`
--

LOCK TABLES `category1_t` WRITE;
/*!40000 ALTER TABLE `category1_t` DISABLE KEYS */;
INSERT INTO `category1_t` VALUES (1,'그림','유화, 1980년도 제작',0),(2,'사진','풍경사진, 어느 봄날의 풍경',0),(3,'조각상','석고, 생각하는 남자',0);
/*!40000 ALTER TABLE `category1_t` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category1_t_seq`
--

DROP TABLE IF EXISTS `category1_t_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category1_t_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category1_t_seq`
--

LOCK TABLES `category1_t_seq` WRITE;
/*!40000 ALTER TABLE `category1_t_seq` DISABLE KEYS */;
INSERT INTO `category1_t_seq` VALUES (1);
/*!40000 ALTER TABLE `category1_t_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment_t`
--

DROP TABLE IF EXISTS `comment_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment_t` (
  `c_idx` bigint NOT NULL AUTO_INCREMENT,
  `writer` varchar(20) DEFAULT NULL,
  `content` text,
  `pwd` varchar(20) DEFAULT NULL,
  `write_date` date DEFAULT NULL,
  `ip` varchar(30) DEFAULT NULL,
  `b_idx` bigint DEFAULT NULL,
  PRIMARY KEY (`c_idx`),
  KEY `comm_t_fk` (`b_idx`),
  CONSTRAINT `comm_t_fk` FOREIGN KEY (`b_idx`) REFERENCES `bbs_t` (`b_idx`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment_t`
--

LOCK TABLES `comment_t` WRITE;
/*!40000 ALTER TABLE `comment_t` DISABLE KEYS */;
INSERT INTO `comment_t` VALUES (1,'와칸다','포애버','123','2025-07-23','0:0:0:0:0:0:0:1',14),(2,'와칸다','포애버','123','2025-07-23','0:0:0:0:0:0:0:1',14),(3,'와칸다','포애버','123','2025-07-23','0:0:0:0:0:0:0:1',14),(4,'와칸다','포애버','123','2025-07-23','0:0:0:0:0:0:0:1',14);
/*!40000 ALTER TABLE `comment_t` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dept`
--

DROP TABLE IF EXISTS `dept`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dept` (
  `deptno` decimal(2,0) NOT NULL,
  `dname` varchar(14) DEFAULT NULL,
  `loc_code` int DEFAULT NULL,
  PRIMARY KEY (`deptno`),
  KEY `dept_fk_idx` (`loc_code`),
  CONSTRAINT `dept_fk` FOREIGN KEY (`loc_code`) REFERENCES `locations` (`loc_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dept`
--

LOCK TABLES `dept` WRITE;
/*!40000 ALTER TABLE `dept` DISABLE KEYS */;
INSERT INTO `dept` VALUES (10,'ACCOUNTING',1),(20,'RESEARCH',2),(30,'SALES',3),(40,'OPERATIONS',1);
/*!40000 ALTER TABLE `dept` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `emp`
--

DROP TABLE IF EXISTS `emp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `emp` (
  `empno` decimal(4,0) NOT NULL,
  `ename` varchar(10) DEFAULT NULL,
  `job` varchar(9) DEFAULT NULL,
  `mgr` decimal(4,0) DEFAULT NULL,
  `hiredate` date DEFAULT NULL,
  `sal` decimal(7,2) DEFAULT NULL,
  `comm` decimal(7,2) DEFAULT NULL,
  `deptno` decimal(2,0) DEFAULT NULL,
  PRIMARY KEY (`empno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `emp`
--

LOCK TABLES `emp` WRITE;
/*!40000 ALTER TABLE `emp` DISABLE KEYS */;
INSERT INTO `emp` VALUES (1000,'이도','DEVELOP',NULL,'2024-04-16',810.00,NULL,20),(1001,'마동석','배우',NULL,'2025-07-18',NULL,NULL,NULL),(1002,'두식이','치킨',NULL,'2025-07-18',NULL,NULL,NULL),(1101,'창조리','DEVELOP',NULL,'2025-06-13',NULL,NULL,NULL),(1102,'창조리','DEVELOP',NULL,'2025-06-13',NULL,NULL,NULL),(1200,'을불','DEVLOP',NULL,'2024-04-18',900.00,NULL,10),(1210,'이순신','DEVLOP',NULL,'2024-04-18',1000.00,0.59,10),(1211,'김유신','DEVLOP',NULL,'2024-04-18',900.00,NULL,10),(1300,'마동석','DEVLOP',NULL,'2024-04-18',800.00,NULL,10),(2000,'마루치','DEVLOP',1000,'2024-04-19',900.00,NULL,10),(2020,'박하사탕','개발',NULL,'2025-07-10',NULL,NULL,NULL),(3333,'손오공','DEVELOP',NULL,'2025-03-08',NULL,NULL,NULL),(7369,'SMITH','CLERK',7902,'1980-12-17',800.00,NULL,20),(7499,'ALLEN','SALESMAN',7698,'1981-02-20',1600.00,300.00,30),(7521,'WARD','SALESMAN',7698,'1981-02-22',1250.00,500.00,30),(7566,'JONES','MANAGER',7839,'1981-04-02',2975.00,NULL,20),(7654,'MARTIN','SALESMAN',7698,'1981-09-28',1250.00,1400.00,30),(7698,'BLAKE','MANAGER',7839,'1981-05-01',2850.00,NULL,30),(7782,'CLARK','MANAGER',7839,'1981-06-09',2450.00,NULL,10),(7788,'SCOTT','ANALYST',7566,'1982-12-09',3000.00,NULL,20),(7839,'KING','PRESIDENT',NULL,'1981-11-17',5000.00,NULL,10),(7844,'TURNER','SALESMAN',7698,'1981-09-08',1500.00,0.00,30),(7876,'ADAMS','CLERK',7788,'1983-01-12',1100.00,NULL,20),(7900,'JAMES','CLERK',7698,'1981-12-03',950.00,NULL,30),(7902,'FORD','ANALYST',7566,'1981-12-03',3000.00,NULL,20),(7934,'MILLER','CLERK',7782,'1982-01-23',1300.00,NULL,10);
/*!40000 ALTER TABLE `emp` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `locations`
--

DROP TABLE IF EXISTS `locations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `locations` (
  `loc_code` int NOT NULL AUTO_INCREMENT,
  `city` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`loc_code`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `locations`
--

LOCK TABLES `locations` WRITE;
/*!40000 ALTER TABLE `locations` DISABLE KEYS */;
INSERT INTO `locations` VALUES (1,'NEW YORK'),(2,'DALLAS'),(3,'CHICAGO'),(4,'BOSTON');
/*!40000 ALTER TABLE `locations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member` (
  `b_idx` bigint NOT NULL AUTO_INCREMENT,
  `refresh_token` varchar(1024) DEFAULT NULL,
  `access_token` varchar(255) DEFAULT NULL,
  `mid` varchar(255) DEFAULT NULL,
  `mname` varchar(255) DEFAULT NULL,
  `mpwd` varchar(255) DEFAULT NULL,
  `write_date` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`b_idx`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` VALUES (1,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJTSVNUIiwiZXhwIjoxNzY3MTY0MjEyLCJ3cml0ZV9kYXRlIjoiMjUuIDkuIDIyLiDsmKTtm4QgMzozMSIsIm1pZCI6ImRvamluIiwibW5hbWUiOiLrj4Tsp4QiLCJpZHgiOjF9.9QpKmEcfa6_-JW557P-YMUepQ-nVXnm0srDKz8648J52VuEoWfEbqEML4PmuKMrGG-ve4ujFmVzZXZoUzsdVug','eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJTSVNUIiwiZXhwIjoxNzU4NTI0NTEyLCJ3cml0ZV9kYXRlIjoiMjUuIDkuIDIyLiDsmKTtm4QgMzozMSIsIm1pZCI6ImRvamluIiwibW5hbWUiOiLrj4Tsp4QiLCJpZHgiOjF9.CzgIYRE4-AyJ2I5RZMUovgkdRGKP6emkgZKjjcY65PfCrgM474DNkmZbfg9hHy-55ewZm1a3fCKotCM0PBP-WQ','dojin','도진','$2a$10$.atiSlfsco6OkpKXcKOOHO0fMVlyhAqEynmsAPUX.QdaA/48z2fey','25. 9. 22. 오후 3:31'),(2,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJTSVNUIiwiZXhwIjoxNzY3MTYyNjYzLCJtcHdkIjoiJDJhJDEwJGtUN2dFdi5EYW9zL0R5YTJXOFVRTC5MUnczMEdyQUFSUEtFOWZtTmNjSVVYN0tOckJMS2FtIiwibWlkIjoiYWRtaW4iLCJtbmFtZSI6Iuq0gOumrOyekCJ9.nfG9vDbDxSur5vdfrYmorVx443SuYakD9vB3A84-oSzCfWiSlHEBavRIAwwkwuuHymNKrxy4v8PYx1cF3T10iQ',NULL,'admin','관리자','$2a$10$kT7gEv.Daos/Dya2W8UQL.LRw30GrAARPKE9fmNccIUX7KNrBLKam','25. 9. 22. 오후 3:31');
/*!40000 ALTER TABLE `member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member_t`
--

DROP TABLE IF EXISTS `member_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member_t` (
  `m_idx` bigint NOT NULL AUTO_INCREMENT,
  `m_name` varchar(50) NOT NULL,
  `m_id` varchar(30) NOT NULL,
  `m_pw` varchar(200) NOT NULL,
  `m_phone` varchar(20) DEFAULT NULL,
  `m_addr` varchar(50) DEFAULT NULL,
  `reg_date` date DEFAULT NULL,
  `status` int DEFAULT '0',
  `etc1` varchar(10) DEFAULT NULL,
  `etc2` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`m_idx`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member_t`
--

LOCK TABLES `member_t` WRITE;
/*!40000 ALTER TABLE `member_t` DISABLE KEYS */;
INSERT INTO `member_t` VALUES (1,'마동석','mmm','1111','010-1234-5678','서울시','2025-03-10',0,NULL,NULL),(2,'문우진','moon','1111','010-4443-4887',NULL,NULL,0,NULL,NULL),(8,'사자','사자','$2a$10$.Ivj52T9EiPS3DY6B4GwxOMOmnzdbO2AUoeYfIUVobiay88p/N64G',NULL,NULL,NULL,0,NULL,NULL);
/*!40000 ALTER TABLE `member_t` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `memo_t`
--

DROP TABLE IF EXISTS `memo_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `memo_t` (
  `idx` bigint NOT NULL AUTO_INCREMENT,
  `writer` varchar(30) DEFAULT NULL,
  `content` varchar(4000) DEFAULT NULL,
  `reg_date` date DEFAULT NULL,
  `ip` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`idx`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `memo_t`
--

LOCK TABLES `memo_t` WRITE;
/*!40000 ALTER TABLE `memo_t` DISABLE KEYS */;
INSERT INTO `memo_t` VALUES (1,'나는','나비','2025-07-14','0:0:0:0:0:0:0:1'),(2,'아싸','호랑나비','2025-07-14','0:0:0:0:0:0:0:1'),(3,'졸리다','매우 졸림','2025-07-14','0:0:0:0:0:0:0:1'),(4,'ㅁㅇㅈ','123','2025-07-14','0:0:0:0:0:0:0:1'),(5,'ㅋㅍㅌ','ㅎㅎㅎ','2025-07-14','0:0:0:0:0:0:0:1'),(6,'이도','을불','2025-07-14','0:0:0:0:0:0:0:1'),(7,'문우진','내용','2025-07-14','0:0:0:0:0:0:0:1'),(8,'ì´ë','123','2025-08-28','0:0:0:0:0:0:0:1'),(9,'이도','123','2025-08-28','0:0:0:0:0:0:0:1'),(10,'이름','345678','2025-08-28','0:0:0:0:0:0:0:1'),(11,'제목','<p>123</p>','2025-08-29','0:0:0:0:0:0:0:1'),(12,NULL,'<p><img src=\"/resources/editor_img//1.gif\" style=\"width: 120px;\"><br></p>','2025-08-29','0:0:0:0:0:0:0:1');
/*!40000 ALTER TABLE `memo_t` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_t`
--

DROP TABLE IF EXISTS `product_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_t` (
  `p_num` bigint NOT NULL,
  `category1` int NOT NULL,
  `category2` int NOT NULL,
  `category3` int NOT NULL,
  `p_company` varchar(255) DEFAULT NULL,
  `p_name` varchar(255) DEFAULT NULL,
  `reg_date` date DEFAULT NULL,
  PRIMARY KEY (`p_num`),
  KEY `FK6qguu3nwa03a6h9enqyx90pjk` (`category1`),
  CONSTRAINT `FK6qguu3nwa03a6h9enqyx90pjk` FOREIGN KEY (`category1`) REFERENCES `category1_t` (`idx`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_t`
--

LOCK TABLES `product_t` WRITE;
/*!40000 ALTER TABLE `product_t` DISABLE KEYS */;
INSERT INTO `product_t` VALUES (1,1,0,0,'Art Company','빈센트 아몬드나무',NULL),(2,1,0,0,'Art company','빈센트 해바라기',NULL),(52,2,0,0,'Art company','빈센트 해바라기',NULL),(152,3,0,0,'sist','비상',NULL),(202,3,0,0,'sist','비상',NULL);
/*!40000 ALTER TABLE `product_t` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_t_seq`
--

DROP TABLE IF EXISTS `product_t_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_t_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_t_seq`
--

LOCK TABLES `product_t_seq` WRITE;
/*!40000 ALTER TABLE `product_t_seq` DISABLE KEYS */;
INSERT INTO `product_t_seq` VALUES (301);
/*!40000 ALTER TABLE `product_t_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sales_t`
--

DROP TABLE IF EXISTS `sales_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sales_t` (
  `s_idx` bigint NOT NULL AUTO_INCREMENT,
  `art_idx` bigint NOT NULL,
  `m_idx` bigint NOT NULL,
  `start_date` date DEFAULT NULL,
  `sale_date` date DEFAULT NULL,
  `status` int DEFAULT NULL,
  PRIMARY KEY (`s_idx`),
  KEY `sales_t_fk1` (`art_idx`),
  KEY `sales_t_fk2` (`m_idx`),
  CONSTRAINT `sales_t_fk1` FOREIGN KEY (`art_idx`) REFERENCES `art_t` (`art_idx`),
  CONSTRAINT `sales_t_fk2` FOREIGN KEY (`m_idx`) REFERENCES `member_t` (`m_idx`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sales_t`
--

LOCK TABLES `sales_t` WRITE;
/*!40000 ALTER TABLE `sales_t` DISABLE KEYS */;
/*!40000 ALTER TABLE `sales_t` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shop_t`
--

DROP TABLE IF EXISTS `shop_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shop_t` (
  `num` bigint NOT NULL AUTO_INCREMENT,
  `category` varchar(10) NOT NULL,
  `p_num` varchar(10) NOT NULL,
  `p_name` varchar(50) NOT NULL,
  `p_company` varchar(50) NOT NULL,
  `p_price` bigint NOT NULL,
  `p_saleprice` bigint NOT NULL,
  `p_image_s` varchar(50) DEFAULT NULL,
  `p_image_l` varchar(50) DEFAULT NULL,
  `p_content` varchar(4000) NOT NULL,
  `p_date` date NOT NULL,
  PRIMARY KEY (`num`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shop_t`
--

LOCK TABLES `shop_t` WRITE;
/*!40000 ALTER TABLE `shop_t` DISABLE KEYS */;
INSERT INTO `shop_t` VALUES (1,'sp003','RC-113','로체스 인라인','로체스',3200,1150,'pds1.jpg','pds1_z.jpg','바이오맥스 통풍 나일론-HGPU SHELL * 특수 충격 흡수 밑창 * 신발끈 메모리 버클 * 힐 락에 의한 신속한 신발끈 시스템 * 느린 메모리 포말에 의한 편안한 통풍성의 숨쉬는 라이너 * 쿨 통풍 시스템 * 통풍성의 인체공학적 신발밑창 * 손쉬운 엔트리 시스템(신기 편한 입구) * 몰디드 알루미늄 프레임 * 80mm 82a hyper dubbs 휠 * 강철 스페이서 * ABEC-5 베어링','2025-07-16'),(2,'ele002','vC-13','사니PDP-TV','사니',9200,4750,'pds4.jpg','pds4_z.jpg','질러~ 질러! \n무조건 질러봐~ 후회 하지 않아~~','2025-07-16'),(3,'ele002','vC-111','LG 디오스 오브제컬렉션','LG전자',2000000,1820000,'dios_s.PNG','dios_l.PNG','자주 먹는 음료, 우리아이 간식, 엄마와 아빠의 건강주스와 맥주 등을 보관하여 냉장고를 편리하게 사용할 수 있는 마법의 공간입니다.','2025-07-16');
/*!40000 ALTER TABLE `shop_t` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-09-30 10:47:47
