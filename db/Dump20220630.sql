-- MySQL dump 10.13  Distrib 8.0.25, for Linux (x86_64)
--
-- Host: localhost    Database: movie_review
-- ------------------------------------------------------
-- Server version	8.0.25-0ubuntu0.20.10.1

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
-- Table structure for table `actors`
--

DROP TABLE IF EXISTS `actors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `actors` (
  `id` int NOT NULL AUTO_INCREMENT,
  `full_name` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `year_birthday` int DEFAULT NULL,
  `country_code` varchar(255) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKh89hh681u22ddl4i1m1e4bbcg` (`country_code`),
  CONSTRAINT `FKh89hh681u22ddl4i1m1e4bbcg` FOREIGN KEY (`country_code`) REFERENCES `countries` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actors`
--

LOCK TABLES `actors` WRITE;
/*!40000 ALTER TABLE `actors` DISABLE KEYS */;
INSERT INTO `actors` VALUES (1,'John Deep','male',1985,'USA',NULL,NULL),(2,'Nguyen Quang Dung','male',1980,'VN',NULL,NULL),(3,'Hana','male',1970,'ESP',NULL,NULL),(4,'Jimmy Leoson','female',1960,'USA',NULL,NULL),(5,'Micheal','male',1990,'CAN',NULL,NULL),(6,'Joan Felix','male',1997,'ESP',NULL,NULL),(7,'asdf','male',1951,'AGO',NULL,NULL),(8,'asdfasdf','female',123123,'AGO',NULL,NULL),(9,'asdfasdf','male',213213,'ESP',NULL,NULL);
/*!40000 ALTER TABLE `actors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categories` (
  `id` int NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (1,'mavels','mavels USA',NULL,NULL),(5,'Phim kinh di, doa ma gioi treasdf','Kinh di',NULL,NULL),(6,'Phim hai, gay tieng coi','Phim hai',NULL,NULL),(7,'Phim tai lieuas','Phim tai lieu',NULL,NULL),(8,'Phim chieu rap','Phim chieu rap',NULL,NULL),(9,'Game show','game show',NULL,NULL),(10,'asdf','asdf',NULL,NULL),(11,'asdfasdf','asdfasdf',NULL,NULL),(12,'asdf','asdf',NULL,NULL);
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comments`
--

DROP TABLE IF EXISTS `comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comments` (
  `id` int NOT NULL AUTO_INCREMENT,
  `comment` varchar(255) NOT NULL,
  `movie_id` int NOT NULL,
  `user_id` int NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKr1xv5xvew7k2aed5qu5lci3kt` (`movie_id`),
  KEY `FK8omq0tc18jd43bu5tjh6jvraq` (`user_id`),
  CONSTRAINT `FK8omq0tc18jd43bu5tjh6jvraq` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKr1xv5xvew7k2aed5qu5lci3kt` FOREIGN KEY (`movie_id`) REFERENCES `movies` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comments`
--

LOCK TABLES `comments` WRITE;
/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
INSERT INTO `comments` VALUES (1,'Hello',1,1,'2021-08-02 22:48:52.000000','2021-08-02 22:48:52.000000'),(2,'asdf',2,1,'2021-08-02 22:48:52.000000','2021-08-02 22:48:52.000000'),(75,'123',7,7,'2022-06-30 11:32:18.531000',NULL),(76,'asdf',1,1,'2022-06-30 12:08:57.216000',NULL),(77,'a',1,1,'2022-06-30 12:09:19.819000',NULL),(78,'asdf',4,1,'2022-06-30 12:16:45.653000',NULL),(79,'aasdf',7,1,'2022-06-30 12:16:52.564000',NULL),(80,'asdf',7,1,'2022-06-30 12:16:58.627000',NULL);
/*!40000 ALTER TABLE `comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `countries`
--

DROP TABLE IF EXISTS `countries`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `countries` (
  `code` varchar(255) NOT NULL,
  `continent` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `countries`
--

LOCK TABLES `countries` WRITE;
/*!40000 ALTER TABLE `countries` DISABLE KEYS */;
INSERT INTO `countries` VALUES ('AFG','Asia','Afghanistan',NULL,NULL),('AGO','Africa','Angola',NULL,NULL),('AUS','Asia','Australia',NULL,NULL),('CAN','South Armerican','Canada',NULL,NULL),('ESP','Europe','Tay Ban Nha',NULL,NULL),('KWD','Asia','Chau A',NULL,NULL),('USA','South Armerican','Bac My',NULL,NULL),('VN','Asia','Viet Nam',NULL,NULL);
/*!40000 ALTER TABLE `countries` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie_actor`
--

DROP TABLE IF EXISTS `movie_actor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movie_actor` (
  `movie_id` int NOT NULL,
  `actor_id` int NOT NULL,
  PRIMARY KEY (`movie_id`,`actor_id`),
  KEY `FKr5wsak3io275nfc8jc5mtup2` (`actor_id`),
  CONSTRAINT `FK90bbe6vpr6eoahw20ta95nkta` FOREIGN KEY (`movie_id`) REFERENCES `movies` (`id`),
  CONSTRAINT `FKr5wsak3io275nfc8jc5mtup2` FOREIGN KEY (`actor_id`) REFERENCES `actors` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie_actor`
--

LOCK TABLES `movie_actor` WRITE;
/*!40000 ALTER TABLE `movie_actor` DISABLE KEYS */;
INSERT INTO `movie_actor` VALUES (1,1);
/*!40000 ALTER TABLE `movie_actor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movies`
--

DROP TABLE IF EXISTS `movies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movies` (
  `id` int NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `director` varchar(255) DEFAULT NULL,
  `rating` double DEFAULT NULL,
  `release_year` int DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `trailer` varchar(255) DEFAULT NULL,
  `year` int DEFAULT NULL,
  `category_id` int NOT NULL,
  `country_code` varchar(255) NOT NULL,
  `thumbnail` varchar(255) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKn00vsw85tow1xyebya4hih3ke` (`category_id`),
  KEY `FK139x2n456fk3plm5kq7iynibp` (`country_code`),
  CONSTRAINT `FK139x2n456fk3plm5kq7iynibp` FOREIGN KEY (`country_code`) REFERENCES `countries` (`code`),
  CONSTRAINT `FKn00vsw85tow1xyebya4hih3ke` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movies`
--

LOCK TABLES `movies` WRITE;
/*!40000 ALTER TABLE `movies` DISABLE KEYS */;
INSERT INTO `movies` VALUES (1,'Doctor strage','HN',3.75,2022,'Doctor strage','https://youtu.be/vtVyh4bZo2k',2022,1,'USA','https://m.media-amazon.com/images/M/MV5BNWM0ZGJlMzMtZmYwMi00NzI3LTgzMzMtNjMzNjliNDRmZmFlXkEyXkFqcGdeQXVyMTM1MTE1NDMx._V1_FMjpg_UX1000_.jpg',NULL,NULL,'film'),(2,'VN Travel','HN',4.125,2021,'Hello strage','https://youtu.be/gySop12clP4',2022,1,'USA','https://cdnb.artstation.com/p/assets/images/images/021/449/133/large/narinder-singh-docter-strange.jpg?1571734835',NULL,NULL,'film'),(3,'Xin Chao','HN',5,2021,'Chao xin','https://youtu.be/yq-JF_f2G9A',2022,1,'USA','https://i.ex-cdn.com/giadinhmoi.vn/files/content/2022/02/01/xin-chao-tay-sung-than-05-2304.jpg',NULL,NULL,'film'),(4,'Em va Trinh','HN',4.199999809265137,2022,'Em va Trinh','https://youtu.be/yq-JF_f2G9A',2022,8,'VN','https://bazaarvietnam.vn/wp-content/uploads/2022/05/Harpers-Bazaar-Em-va-Trinh-poster_03.jpg',NULL,NULL,'film'),(5,'Bo Gia','HN',4.142857074737549,2021,'Bo Gia','https://youtu.be/yq-JF_f2G9A',2021,8,'VN','https://i.imgur.com/eeLEz4Y.jpg',NULL,NULL,'film'),(6,'Raw','HN',4,2017,'Raw','https://youtu.be/yq-JF_f2G9A',2017,8,'USA','https://img.idesign.vn/2018/06/27/raw_percival-associates.jpg',NULL,NULL,'film'),(7,'spider-man amazing','HN',5,2016,'spider man','https://youtu.be/yq-JF_f2G9A',2017,8,'USA','https://sm.ign.com/t/ign_za/gallery/s/spider-man/spider-man-far-from-home-official-movie-posters_ex7e.1080.jpg',NULL,NULL,'film'),(8,'co ba sai gon','HN',4.181818008422852,2018,'Co ba sai gon','https://youtu.be/yq-JF_f2G9A',2018,8,'VN','https://image.vietnamnews.vn/uploadvnnews/Article/2020/5/21/87811_poster.jpg',NULL,NULL,'film'),(9,'Titanic phim tam ly tinh cam','HN',4.222222328186035,2014,'Titanic','https://youtu.be/yq-JF_f2G9A',2017,8,'USA','https://images.complex.com/complex/image/upload/c_fill,dpr_auto,f_auto,fl_lossy,g_face,q_auto,w_1280/wjnhpz3osrai5aningjl.jpg',NULL,NULL,'film'),(10,'phim co bac','HN',4,2014,'Casino jack','https://youtu.be/yq-JF_f2G9A',2016,8,'USA','https://bizweb.dktcdn.net/100/332/012/files/poster-phim-mang-tinh-cu-the-hoa-nhung-dieu-truu-tuong.jpg?v=1580462041276',NULL,NULL,'film'),(11,'City tv','HN',5,2017,'Beat Shazam','https://youtu.be/yq-JF_f2G9A',2016,9,'USA','https://www.citytv.com/wp-content/uploads/2022/06/Beat_Shazam_S03_Show_Art_430x645.jpg',NULL,NULL,'tv');
/*!40000 ALTER TABLE `movies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movies_actors`
--

DROP TABLE IF EXISTS `movies_actors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movies_actors` (
  `movies_id` int NOT NULL,
  `actors_id` int NOT NULL,
  PRIMARY KEY (`movies_id`,`actors_id`),
  KEY `FKi9nbemods4rqk1geesbsgjpl1` (`actors_id`),
  CONSTRAINT `FKh3li5kax0d7ooqs3195t82uqw` FOREIGN KEY (`movies_id`) REFERENCES `movies` (`id`),
  CONSTRAINT `FKi9nbemods4rqk1geesbsgjpl1` FOREIGN KEY (`actors_id`) REFERENCES `actors` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movies_actors`
--

LOCK TABLES `movies_actors` WRITE;
/*!40000 ALTER TABLE `movies_actors` DISABLE KEYS */;
/*!40000 ALTER TABLE `movies_actors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reviews`
--

DROP TABLE IF EXISTS `reviews`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reviews` (
  `id` int NOT NULL AUTO_INCREMENT,
  `rating` double NOT NULL,
  `movie_id` int NOT NULL,
  `user_id` int NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK87tlqya0rq8ijfjscldpvvdyq` (`movie_id`),
  KEY `FKcgy7qjc1r99dp117y9en6lxye` (`user_id`),
  CONSTRAINT `FK87tlqya0rq8ijfjscldpvvdyq` FOREIGN KEY (`movie_id`) REFERENCES `movies` (`id`),
  CONSTRAINT `FKcgy7qjc1r99dp117y9en6lxye` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reviews`
--

LOCK TABLES `reviews` WRITE;
/*!40000 ALTER TABLE `reviews` DISABLE KEYS */;
INSERT INTO `reviews` VALUES (1,4,1,1,'2021-08-02 22:48:52.000000','2021-08-02 22:48:52.000000'),(2,5,1,1,'2021-08-02 22:48:52.000000','2021-08-02 22:48:52.000000'),(15,5,3,1,NULL,NULL),(16,4,1,1,NULL,NULL),(17,2,3,1,NULL,NULL),(18,4,3,1,NULL,NULL),(19,2,3,1,NULL,NULL),(20,5,2,7,NULL,NULL),(21,5,3,7,NULL,NULL),(22,5,8,7,NULL,NULL),(23,4,4,7,NULL,NULL),(24,2,4,7,NULL,NULL),(25,4,5,7,NULL,NULL),(26,4,5,7,NULL,NULL),(27,4,2,7,NULL,NULL),(28,5,9,7,NULL,NULL),(29,4,8,7,NULL,NULL),(30,4,8,7,NULL,NULL),(31,4,1,1,NULL,NULL);
/*!40000 ALTER TABLE `reviews` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `id` int NOT NULL AUTO_INCREMENT,
  `display_name` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'ADMIN','ADMIN',NULL,NULL),(2,'CRITIC','CRITIC',NULL,NULL),(3,'MEMBER','MEMBER',NULL,NULL);
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role` (
  `user_id` int NOT NULL,
  `role_id` int NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKt7e7djp752sqn6w22i6ocqy6q` (`role_id`),
  CONSTRAINT `FKj345gk1bovqvfame88rcx7yyx` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKt7e7djp752sqn6w22i6ocqy6q` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1,1),(2,1),(5,2),(6,3),(7,3);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'admin@gmail.com','aaa','$2a$10$0C7JYEozdPeWZ.DXXfYSruUiqRsncrYPJ9nAlkq34i2n76rEE5IWS','23',_binary '','admin',NULL,NULL),(2,'admin@11gmail.com','bbb','$2a$10$VQA7B7rlR0b/gmMWbJZAP.LcGVk2DRWIUdiBAxeu8wG356SUQEYDm','123',_binary '','',NULL,NULL),(5,'asdf@123','asdf','$2a$10$51bMUCppI0OIcuq//pPtveSx/wEj57c5YaFA15NvS1Est9ttkFu3S','asdf',_binary '','asdf',NULL,NULL),(6,NULL,'123','$2a$10$ne81BHGWg/01GnO.kMh/f.ux6TjNViMWECr.12HoPSPA4UTvBKiPK','123',_binary '','123',NULL,NULL),(7,NULL,'124','$2a$10$aZRNGbix1y1Wcp8Tw47yUesdgUer1lIuNc75gYp6v4.eQKl85HySG','124',_binary '','124',NULL,NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-06-30 13:32:57
