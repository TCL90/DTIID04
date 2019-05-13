start transaction;

create database `Acme-Rookies`;

use `Acme-Rookies`;

create user 'acme-user'@'%' identified by password '*4F10007AADA9EE3DBB2CC36575DFC6F4FDE27577';
create user 'acme-manager'@'%' identified by password '*FDB8CD304EB2317D10C95D797A4BD7492560F55F';

grant select, insert, update, delete on `Acme-Rookies`.* to 'acme-user'@'%';

grant select, insert, update, delete, create, drop, references, index, alter, create temporary tables, lock tables, 
create view, create routine, alter routine, execute, trigger, show view on `Acme-Rookies`.* to 'acme-manager'@'%';


-- MySQL dump 10.13  Distrib 5.5.29, for Win64 (x86)
--
-- Host: localhost    Database: acme-rookies
-- ------------------------------------------------------
-- Server version	5.5.29

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
-- Table structure for table `actor_boxes`
--

DROP TABLE IF EXISTS `actor_boxes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `actor_boxes` (
  `actor` int(11) NOT NULL,
  `boxes` int(11) NOT NULL,
  UNIQUE KEY `UK_6n6psqivvjho155qcf9kjvv1h` (`boxes`),
  CONSTRAINT `FK_6n6psqivvjho155qcf9kjvv1h` FOREIGN KEY (`boxes`) REFERENCES `box` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actor_boxes`
--

LOCK TABLES `actor_boxes` WRITE;
/*!40000 ALTER TABLE `actor_boxes` DISABLE KEYS */;
INSERT INTO `actor_boxes` VALUES (936,902),(848,903),(844,904),(845,905),(935,906),(849,907),(846,908),(847,909),(916,915),(919,918),(929,931),(930,932);
/*!40000 ALTER TABLE `actor_boxes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `actor_social_profiles`
--

DROP TABLE IF EXISTS `actor_social_profiles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `actor_social_profiles` (
  `actor` int(11) NOT NULL,
  `social_profiles` int(11) NOT NULL,
  UNIQUE KEY `UK_4suhrykpl9af1ubs85ycbyt6q` (`social_profiles`),
  CONSTRAINT `FK_4suhrykpl9af1ubs85ycbyt6q` FOREIGN KEY (`social_profiles`) REFERENCES `social_profile` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actor_social_profiles`
--

LOCK TABLES `actor_social_profiles` WRITE;
/*!40000 ALTER TABLE `actor_social_profiles` DISABLE KEYS */;
INSERT INTO `actor_social_profiles` VALUES (935,934);
/*!40000 ALTER TABLE `actor_social_profiles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `administrator`
--

DROP TABLE IF EXISTS `administrator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `administrator` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `computed` bit(1) DEFAULT NULL,
  `cvv` int(11) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `expiration_month` int(11) DEFAULT NULL,
  `expiration_year` int(11) DEFAULT NULL,
  `flag_spam` bit(1) NOT NULL,
  `holder_name` varchar(255) DEFAULT NULL,
  `is_banned` bit(1) NOT NULL,
  `make_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `vat` int(11) DEFAULT NULL,
  `user_account` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_7ohwsa2usmvu0yxb44je2lge` (`user_account`),
  CONSTRAINT `FK_7ohwsa2usmvu0yxb44je2lge` FOREIGN KEY (`user_account`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administrator`
--

LOCK TABLES `administrator` WRITE;
/*!40000 ALTER TABLE `administrator` DISABLE KEYS */;
INSERT INTO `administrator` VALUES (844,0,'Address 1','\0',583,'admin@',5,2025,'\0','Admin Admin','\0','VISA','Administrator','4083602396731593','+34 676676676','http://www.foto.com','Admin',21,836),(845,0,'Address admin2','\0',583,'admin2@',5,2025,'\0','Admin2 Admin2','\0','VISA','admin2','4083602396731593','+34 123676676','http://www.admin2.com','admin2',21,841);
/*!40000 ALTER TABLE `administrator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `application`
--

DROP TABLE IF EXISTS `application`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `application` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `answer_explanation` varchar(255) DEFAULT NULL,
  `link` varchar(255) DEFAULT NULL,
  `moment` datetime DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `hacker` int(11) NOT NULL,
  `position` int(11) NOT NULL,
  `problem` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_3hgwemcpn15ns7bi2upsq613y` (`hacker`),
  KEY `FK_cqpb54jon3yjef814oj6g6o4n` (`position`),
  KEY `FK_7gn6fojv7rim6rxyglc6n9mt2` (`problem`),
  CONSTRAINT `FK_7gn6fojv7rim6rxyglc6n9mt2` FOREIGN KEY (`problem`) REFERENCES `problem` (`id`),
  CONSTRAINT `FK_3hgwemcpn15ns7bi2upsq613y` FOREIGN KEY (`hacker`) REFERENCES `hacker` (`id`),
  CONSTRAINT `FK_cqpb54jon3yjef814oj6g6o4n` FOREIGN KEY (`position`) REFERENCES `position` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application`
--

LOCK TABLES `application` WRITE;
/*!40000 ALTER TABLE `application` DISABLE KEYS */;
INSERT INTO `application` VALUES (937,0,'','','2019-02-02 21:00:00','PENDING',935,864,850),(938,0,'For solve the problem...','http://github.com/unknown/repo','2019-02-02 21:00:00','SUBMITTED',935,865,850),(939,0,'For solve the problem...','http://github.com/unknown/repo','2019-02-02 21:00:00','REJECTED',935,866,850),(940,0,'For solve the problem...','http://github.com/unknown/repo','2019-02-02 21:00:00','ACCEPTED',935,867,850),(941,0,'For solve the problem...','http://github.com/unknown/repo','2019-02-02 21:00:00','REJECTED',935,868,850),(942,0,'For solve the problem...','http://github.com/unknown/repo','2019-02-02 21:00:00','REJECTED',935,869,850),(943,0,'For solve the problem...','http://github.com/unknown/repo','2019-02-02 21:00:00','REJECTED',935,870,850),(944,0,'','','2019-02-02 21:00:00','PENDING',936,871,860),(945,0,'For solve the problem...','http://github.com/unknown/repo','2019-02-02 21:00:00','SUBMITTED',936,872,860),(946,0,'For solve the problem...','http://github.com/unknown/repo','2019-02-02 21:00:00','REJECTED',936,873,860),(947,0,'For solve the problem...','http://github.com/unknown/repo','2019-02-02 21:00:00','ACCEPTED',936,874,860),(948,0,'For solve the problem...','http://github.com/unknown/repo','2019-02-02 21:00:00','REJECTED',936,875,860),(949,0,'For solve the problem...','http://github.com/unknown/repo','2019-02-02 21:00:00','REJECTED',936,876,860),(950,0,'For solve the problem...','http://github.com/unknown/repo','2019-02-02 21:00:00','REJECTED',936,877,860);
/*!40000 ALTER TABLE `application` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `audit`
--

DROP TABLE IF EXISTS `audit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `audit` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `final_mode` bit(1) DEFAULT NULL,
  `moment` datetime DEFAULT NULL,
  `score` double DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  `auditor` int(11) NOT NULL,
  `position` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_3m6p53wfvy7kcl4f4fvphkh9h` (`auditor`),
  KEY `FK_bumsxo4hc038y25pbfsinc38u` (`position`),
  CONSTRAINT `FK_bumsxo4hc038y25pbfsinc38u` FOREIGN KEY (`position`) REFERENCES `position` (`id`),
  CONSTRAINT `FK_3m6p53wfvy7kcl4f4fvphkh9h` FOREIGN KEY (`auditor`) REFERENCES `auditor` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `audit`
--

LOCK TABLES `audit` WRITE;
/*!40000 ALTER TABLE `audit` DISABLE KEYS */;
INSERT INTO `audit` VALUES (910,0,'','2018-10-10 00:00:00',0,'Audit 1 about position 1',846,864),(911,0,'\0','2018-11-10 00:00:00',0,'Audit 2 about position 2',846,865),(912,0,'','2018-11-10 00:00:00',0,'Audit 3 about position 3',847,866),(913,0,'\0','2018-11-10 00:00:00',0,'Audit 4 about position 4',847,867);
/*!40000 ALTER TABLE `audit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auditor`
--

DROP TABLE IF EXISTS `auditor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auditor` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `computed` bit(1) DEFAULT NULL,
  `cvv` int(11) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `expiration_month` int(11) DEFAULT NULL,
  `expiration_year` int(11) DEFAULT NULL,
  `flag_spam` bit(1) NOT NULL,
  `holder_name` varchar(255) DEFAULT NULL,
  `is_banned` bit(1) NOT NULL,
  `make_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `vat` int(11) DEFAULT NULL,
  `user_account` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_1hfceldjralkadedlv9lg1tl8` (`user_account`),
  CONSTRAINT `FK_1hfceldjralkadedlv9lg1tl8` FOREIGN KEY (`user_account`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auditor`
--

LOCK TABLES `auditor` WRITE;
/*!40000 ALTER TABLE `auditor` DISABLE KEYS */;
INSERT INTO `auditor` VALUES (846,0,'Address 4','\0',247,'auditor1@rookies.com',2,2022,'\0','Auditor One','\0','VISA','Auditor 1','4588502912013759','+34 621232222','http://www.foto.com','One',21,842),(847,0,'Address 2','\0',247,'auditor2@rookies.com',2,2022,'\0','Auditor One','\0','VISA','Auditor 2','4588502912013759','+34 622232222','http://www.foto.com','Two',21,843);
/*!40000 ALTER TABLE `auditor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `box`
--

DROP TABLE IF EXISTS `box`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `box` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `box`
--

LOCK TABLES `box` WRITE;
/*!40000 ALTER TABLE `box` DISABLE KEYS */;
INSERT INTO `box` VALUES (902,0,'Pool'),(903,0,'Pool'),(904,0,'Pool'),(905,0,'Pool'),(906,0,'Pool'),(907,0,'Pool'),(908,0,'Pool'),(909,0,'Pool'),(915,0,'Pool'),(918,0,'Pool'),(931,0,'Pool'),(932,0,'Pool');
/*!40000 ALTER TABLE `box` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `box_messages`
--

DROP TABLE IF EXISTS `box_messages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `box_messages` (
  `box` int(11) NOT NULL,
  `messages` int(11) NOT NULL,
  KEY `FK_acfjrqu1jeixjmv14c0386o0s` (`messages`),
  KEY `FK_e6boieojekgfg919on0dci4na` (`box`),
  CONSTRAINT `FK_e6boieojekgfg919on0dci4na` FOREIGN KEY (`box`) REFERENCES `box` (`id`),
  CONSTRAINT `FK_acfjrqu1jeixjmv14c0386o0s` FOREIGN KEY (`messages`) REFERENCES `message` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `box_messages`
--

LOCK TABLES `box_messages` WRITE;
/*!40000 ALTER TABLE `box_messages` DISABLE KEYS */;
INSERT INTO `box_messages` VALUES (903,951),(903,933),(906,951),(931,933);
/*!40000 ALTER TABLE `box_messages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `company`
--

DROP TABLE IF EXISTS `company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `company` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `computed` bit(1) DEFAULT NULL,
  `cvv` int(11) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `expiration_month` int(11) DEFAULT NULL,
  `expiration_year` int(11) DEFAULT NULL,
  `flag_spam` bit(1) NOT NULL,
  `holder_name` varchar(255) DEFAULT NULL,
  `is_banned` bit(1) NOT NULL,
  `make_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `vat` int(11) DEFAULT NULL,
  `user_account` int(11) DEFAULT NULL,
  `company_name` varchar(255) DEFAULT NULL,
  `score` double NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_pno7oguspp7fxv0y2twgplt0s` (`user_account`),
  CONSTRAINT `FK_pno7oguspp7fxv0y2twgplt0s` FOREIGN KEY (`user_account`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company`
--

LOCK TABLES `company` WRITE;
/*!40000 ALTER TABLE `company` DISABLE KEYS */;
INSERT INTO `company` VALUES (848,0,'Address 4','\0',247,'company1@hackerrank.com',2,2022,'\0','Company One','\0','VISA','Company 1','4588502912013759','+34 622222222','http://www.foto.com','One',21,839,'COM',0),(849,0,'Address 5','\0',339,'company2@hackerrank.com',7,2024,'\0','Company Two','\0','VISA','Company','4489952508772500','+34 622222233','http://www.foto.com','Two',21,840,'COMPANY2',0);
/*!40000 ALTER TABLE `company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `credit_card`
--

DROP TABLE IF EXISTS `credit_card`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `credit_card` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `cvv` int(11) DEFAULT NULL,
  `expiration_month` int(11) DEFAULT NULL,
  `expiration_year` int(11) DEFAULT NULL,
  `holder_name` varchar(255) DEFAULT NULL,
  `make_name` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `actor` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_kj6ag2kv2vppcsvh6y24est5s` (`actor`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `credit_card`
--

LOCK TABLES `credit_card` WRITE;
/*!40000 ALTER TABLE `credit_card` DISABLE KEYS */;
/*!40000 ALTER TABLE `credit_card` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `curricula`
--

DROP TABLE IF EXISTS `curricula`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `curricula` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `is_copy` bit(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `application` int(11) DEFAULT NULL,
  `hacker` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_1kcgpl7ralhgysbiurt8qr8x0` (`application`),
  KEY `FK_cmpmwnbib50w71nx0wjlp8x79` (`hacker`),
  CONSTRAINT `FK_cmpmwnbib50w71nx0wjlp8x79` FOREIGN KEY (`hacker`) REFERENCES `hacker` (`id`),
  CONSTRAINT `FK_1kcgpl7ralhgysbiurt8qr8x0` FOREIGN KEY (`application`) REFERENCES `application` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `curricula`
--

LOCK TABLES `curricula` WRITE;
/*!40000 ALTER TABLE `curricula` DISABLE KEYS */;
INSERT INTO `curricula` VALUES (880,1,'\0','Curricula1',937,935),(881,1,'\0','Curricula2',938,935),(882,1,'\0','Curricula3',939,936),(883,1,'\0','Curricula4',940,936);
/*!40000 ALTER TABLE `curricula` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customisation`
--

DROP TABLE IF EXISTS `customisation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customisation` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `banner_url` varchar(255) DEFAULT NULL,
  `finder_duration` int(11) DEFAULT NULL,
  `flat_rate` int(11) DEFAULT NULL,
  `notified` bit(1) NOT NULL,
  `phone_number_code` varchar(255) DEFAULT NULL,
  `rebranded` bit(1) NOT NULL,
  `results_number` int(11) DEFAULT NULL,
  `system_name` varchar(255) DEFAULT NULL,
  `vat` int(11) DEFAULT NULL,
  `welcome_message_eng` varchar(255) DEFAULT NULL,
  `welcome_message_esp` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customisation`
--

LOCK TABLES `customisation` WRITE;
/*!40000 ALTER TABLE `customisation` DISABLE KEYS */;
INSERT INTO `customisation` VALUES (835,0,'https://i.imgur.com/7b8lu4b.png',1,50,'\0','+34','\0',10,'Acme Hacker-Rank',21,'Welcome to Acme hacker Rank! We\'re IT hacker\'s favourite job marketplace!','¡Bienvenidos a Acme Hacker Rank! Somos el mercado de trabajo favorito de los profesionales de las TICs!');
/*!40000 ALTER TABLE `customisation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customisation_spam_words`
--

DROP TABLE IF EXISTS `customisation_spam_words`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customisation_spam_words` (
  `customisation` int(11) NOT NULL,
  `spam_words` varchar(255) DEFAULT NULL,
  KEY `FK_c6m5a0x35im5g3hgaim7j4lpu` (`customisation`),
  CONSTRAINT `FK_c6m5a0x35im5g3hgaim7j4lpu` FOREIGN KEY (`customisation`) REFERENCES `customisation` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customisation_spam_words`
--

LOCK TABLES `customisation_spam_words` WRITE;
/*!40000 ALTER TABLE `customisation_spam_words` DISABLE KEYS */;
INSERT INTO `customisation_spam_words` VALUES (835,'SEX'),(835,'VIAGRA'),(835,'CIALIS'),(835,'YOU\'VE BEEN SELECTED'),(835,'ONE MILLION'),(835,'NIGERIA'),(835,'SEXO'),(835,'VIAGRA'),(835,'CIALIS'),(835,'HAS SIDO SELECIONADO'),(835,'UN MILLÓN');
/*!40000 ALTER TABLE `customisation_spam_words` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `education_data`
--

DROP TABLE IF EXISTS `education_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `education_data` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `degree` varchar(255) DEFAULT NULL,
  `end_moment` date DEFAULT NULL,
  `institution` varchar(255) DEFAULT NULL,
  `mark` double DEFAULT NULL,
  `start_moment` date DEFAULT NULL,
  `curricula` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_b1949439gwkx4pjayup8gn2d6` (`curricula`),
  CONSTRAINT `FK_b1949439gwkx4pjayup8gn2d6` FOREIGN KEY (`curricula`) REFERENCES `curricula` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `education_data`
--

LOCK TABLES `education_data` WRITE;
/*!40000 ALTER TABLE `education_data` DISABLE KEYS */;
INSERT INTO `education_data` VALUES (888,0,'Informatic Systems','2018-02-02','ETSII',8,'2015-02-02',880),(889,0,'Informatic Systems2','2020-02-02','ETSII',8.5,'2018-02-02',880),(890,0,'Informatic Systems','2018-02-02','ETSII',8,'2015-02-02',881),(891,0,'Big Data','2018-02-02','PDO',6,'2015-02-02',882),(892,0,'Java Guide','2018-02-02','MTIS',8,'2015-02-02',883);
/*!40000 ALTER TABLE `education_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `finder`
--

DROP TABLE IF EXISTS `finder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `finder` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `deadline` date DEFAULT NULL,
  `keyword` varchar(255) DEFAULT NULL,
  `maximum_deadline` date DEFAULT NULL,
  `minimum_salary` int(11) DEFAULT NULL,
  `moment` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `finder`
--

LOCK TABLES `finder` WRITE;
/*!40000 ALTER TABLE `finder` DISABLE KEYS */;
INSERT INTO `finder` VALUES (878,0,NULL,'',NULL,NULL,'2019-01-01 00:00:00'),(879,0,NULL,'',NULL,NULL,'2019-01-01 00:00:00');
/*!40000 ALTER TABLE `finder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `finder_positions`
--

DROP TABLE IF EXISTS `finder_positions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `finder_positions` (
  `finder` int(11) NOT NULL,
  `positions` int(11) NOT NULL,
  KEY `FK_3d46gil0nks2dhgg7cyhv2m39` (`positions`),
  KEY `FK_l0b3qg4nly59oeqhe8ig5yssc` (`finder`),
  CONSTRAINT `FK_l0b3qg4nly59oeqhe8ig5yssc` FOREIGN KEY (`finder`) REFERENCES `finder` (`id`),
  CONSTRAINT `FK_3d46gil0nks2dhgg7cyhv2m39` FOREIGN KEY (`positions`) REFERENCES `position` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `finder_positions`
--

LOCK TABLES `finder_positions` WRITE;
/*!40000 ALTER TABLE `finder_positions` DISABLE KEYS */;
/*!40000 ALTER TABLE `finder_positions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hacker`
--

DROP TABLE IF EXISTS `hacker`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hacker` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `computed` bit(1) DEFAULT NULL,
  `cvv` int(11) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `expiration_month` int(11) DEFAULT NULL,
  `expiration_year` int(11) DEFAULT NULL,
  `flag_spam` bit(1) NOT NULL,
  `holder_name` varchar(255) DEFAULT NULL,
  `is_banned` bit(1) NOT NULL,
  `make_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `vat` int(11) DEFAULT NULL,
  `user_account` int(11) DEFAULT NULL,
  `finder` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_eohjws41xqofou03dl7t7aku4` (`finder`),
  KEY `FK_pechhr6iex4b7l2rymmx1xi38` (`user_account`),
  CONSTRAINT `FK_pechhr6iex4b7l2rymmx1xi38` FOREIGN KEY (`user_account`) REFERENCES `user_account` (`id`),
  CONSTRAINT `FK_eohjws41xqofou03dl7t7aku4` FOREIGN KEY (`finder`) REFERENCES `finder` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hacker`
--

LOCK TABLES `hacker` WRITE;
/*!40000 ALTER TABLE `hacker` DISABLE KEYS */;
INSERT INTO `hacker` VALUES (935,0,'Address 2','\0',188,'hacker1@hackerrank.com',4,2022,'\0','Hacker One','\0','VISA','Hacker','4128837079312553','+34 611111111','http://www.foto.com','One',21,837,878),(936,0,'Address 3','\0',206,'hacker2@hackerrank.com',5,2023,'\0','Hacker Two','\0','VISA','Hacker','4338709129661089','+34 621111111','http://www.foto.com','Two',21,838,879);
/*!40000 ALTER TABLE `hacker` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequences`
--

DROP TABLE IF EXISTS `hibernate_sequences`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequences` (
  `sequence_name` varchar(255) DEFAULT NULL,
  `sequence_next_hi_value` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequences`
--

LOCK TABLES `hibernate_sequences` WRITE;
/*!40000 ALTER TABLE `hibernate_sequences` DISABLE KEYS */;
INSERT INTO `hibernate_sequences` VALUES ('domain_entity',1);
/*!40000 ALTER TABLE `hibernate_sequences` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item`
--

DROP TABLE IF EXISTS `item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `link` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `provider` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_isojc9iaj7goju6s26847atbn` (`provider`),
  CONSTRAINT `FK_isojc9iaj7goju6s26847atbn` FOREIGN KEY (`provider`) REFERENCES `provider` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item`
--

LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
INSERT INTO `item` VALUES (920,0,'Description','http://www.link.com','Item name 1',916),(921,0,'Description','http://www.link.com','Item name 2',916),(922,0,'Description','http://www.link.com','Item name 3',916),(923,0,'Description','http://www.link.com','Item name 4',916),(924,0,'Description','http://www.link.com','Item name 5',919),(925,0,'Description','http://www.link.com','Item name 6',919),(926,0,'Description','http://www.link.com','Item name 7',919);
/*!40000 ALTER TABLE `item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item_photos`
--

DROP TABLE IF EXISTS `item_photos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item_photos` (
  `item` int(11) NOT NULL,
  `photos` varchar(255) DEFAULT NULL,
  KEY `FK_gg8p4th6f0ou8x3iw3mgdb31d` (`item`),
  CONSTRAINT `FK_gg8p4th6f0ou8x3iw3mgdb31d` FOREIGN KEY (`item`) REFERENCES `item` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_photos`
--

LOCK TABLES `item_photos` WRITE;
/*!40000 ALTER TABLE `item_photos` DISABLE KEYS */;
/*!40000 ALTER TABLE `item_photos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `message` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `body` longtext,
  `broadcast` bit(1) NOT NULL,
  `flag_spam` bit(1) NOT NULL,
  `moment` datetime DEFAULT NULL,
  `subject` varchar(255) DEFAULT NULL,
  `tag` varchar(255) DEFAULT NULL,
  `recipient` int(11) DEFAULT NULL,
  `sender` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES (933,0,'SOY EL PRINCIPE DE NIGERIA MI FORTUNA DE ONE MILLION DE DOLLARS ES TUYA','\0','','2018-10-10 00:00:00','NIGERIA PRINCE','NIGERIA',848,929),(951,0,'Prueba','\0','\0','2018-10-10 00:00:00','Prueba','Prueba',848,935);
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `miscellaneous_data`
--

DROP TABLE IF EXISTS `miscellaneous_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `miscellaneous_data` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `attachments` varchar(255) DEFAULT NULL,
  `free_text` varchar(255) DEFAULT NULL,
  `curricula` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_h48spqfrohqicpoi2y2kmdvhb` (`curricula`),
  CONSTRAINT `FK_h48spqfrohqicpoi2y2kmdvhb` FOREIGN KEY (`curricula`) REFERENCES `curricula` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `miscellaneous_data`
--

LOCK TABLES `miscellaneous_data` WRITE;
/*!40000 ALTER TABLE `miscellaneous_data` DISABLE KEYS */;
INSERT INTO `miscellaneous_data` VALUES (898,0,'http://Attachment1.com','This is some free text1',880),(899,0,'http://Attachment1.com','This is some free text1',881),(900,0,'http://Attachment1.com','This is some free text1',882),(901,0,'http://Attachment1.com','This is some free text1',883);
/*!40000 ALTER TABLE `miscellaneous_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personal_data`
--

DROP TABLE IF EXISTS `personal_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `personal_data` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `git_profile` varchar(255) DEFAULT NULL,
  `linked_in_profile` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `statement` varchar(255) DEFAULT NULL,
  `curricula` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_9cabl85nl8no5idl3srdofe4c` (`curricula`),
  CONSTRAINT `FK_9cabl85nl8no5idl3srdofe4c` FOREIGN KEY (`curricula`) REFERENCES `curricula` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personal_data`
--

LOCK TABLES `personal_data` WRITE;
/*!40000 ALTER TABLE `personal_data` DISABLE KEYS */;
INSERT INTO `personal_data` VALUES (884,0,'Xavier Jimenez Lopez','https://gitProfile.com/XDS90','https://linkedIn.com/XDS90','666666666','This is a statement',880),(885,0,'Xavier Jimenez Lopez','https://gitProfile.com/XDS90','https://linkedIn.com/XDS90','666666666','This is a statement',881),(886,0,'Augusto Martinez Sancho','https://gitProfile.com/AMS90','https://linkedIn.com/AMS90','666663336','This is a statement',882),(887,0,'Augusto Martinez Sancho','https://gitProfile.com/AMS90','https://linkedIn.com/AMS90','666663336','This is a statement',883);
/*!40000 ALTER TABLE `personal_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `position`
--

DROP TABLE IF EXISTS `position`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `position` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `deadline` date DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `final_mode` bit(1) NOT NULL,
  `is_cancelled` bit(1) NOT NULL,
  `profile` varchar(255) DEFAULT NULL,
  `salary` int(11) DEFAULT NULL,
  `ticker` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `company` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_7qlfn4nye234rrm4w83nms1g8` (`company`),
  CONSTRAINT `FK_7qlfn4nye234rrm4w83nms1g8` FOREIGN KEY (`company`) REFERENCES `company` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `position`
--

LOCK TABLES `position` WRITE;
/*!40000 ALTER TABLE `position` DISABLE KEYS */;
INSERT INTO `position` VALUES (864,0,'2019-06-06','Description','','\0','Experience 1 year',1300,'COMX-1354','Position 1',848),(865,0,'2019-06-06','Description','','\0','Experience 1 year',1300,'COMX-1355','Position 2',848),(866,0,'2019-06-06','Description','','\0','Experience 1 year',1300,'COMX-1854','Position 3',848),(867,0,'2019-06-06','Description','','\0','Experience 1 year',1300,'COMX-1900','Position 4',848),(868,0,'2019-06-06','Description','','\0','Experience 1 year',1300,'COMX-9392','Position 5',848),(869,0,'2019-06-06','Description','','\0','Experience 1 year',1300,'COMX-4555','Position 6',848),(870,0,'2019-06-06','Description','','\0','Experience 1 year',1300,'COMX-7312','Position 7',848),(871,0,'2019-06-06','Description','','\0','Experience 1 year',1300,'COMP-1354','Position 1',849),(872,0,'2019-06-06','Description','','\0','Experience 1 year',1300,'COMP-1355','Position 2',849),(873,0,'2019-06-06','Description','','\0','Experience 1 year',1300,'COMP-1854','Position 3',849),(874,0,'2019-06-06','Description','','\0','Experience 1 year',1300,'COMP-1900','Position 4',849),(875,0,'2019-06-06','Description','','\0','Experience 1 year',1300,'COMP-9392','Position 5',849),(876,0,'2019-06-06','Description','','\0','Experience 1 year',1300,'COMP-4555','Position 6',849),(877,0,'2019-06-06','Description','','\0','Experience 1 year',1300,'COMP-7312','Position 7',849);
/*!40000 ALTER TABLE `position` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `position_data`
--

DROP TABLE IF EXISTS `position_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `position_data` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `end_moment` date DEFAULT NULL,
  `start_moment` date DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `curricula` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_50pf203n1ob4iihr5n18eeoxf` (`curricula`),
  CONSTRAINT `FK_50pf203n1ob4iihr5n18eeoxf` FOREIGN KEY (`curricula`) REFERENCES `curricula` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `position_data`
--

LOCK TABLES `position_data` WRITE;
/*!40000 ALTER TABLE `position_data` DISABLE KEYS */;
INSERT INTO `position_data` VALUES (893,0,'CEO of Google','2018-02-02','2015-02-02','Boss',880),(894,0,'Working on my own business','2021-02-02','2018-02-02','Developer',880),(895,0,'CEO of Amazon','2018-02-02','2015-02-02','Boss',881),(896,0,'CEO of Apple','2018-02-02','2015-02-02','Boss',882),(897,0,'CEO of PillowTronics','2018-02-02','2015-02-02','Boss',883);
/*!40000 ALTER TABLE `position_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `position_problems`
--

DROP TABLE IF EXISTS `position_problems`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `position_problems` (
  `position` int(11) NOT NULL,
  `problems` int(11) NOT NULL,
  KEY `FK_7pe330ganri24wsftsajlm4l9` (`problems`),
  KEY `FK_iji6l3ytrjgytbgo6oi061elj` (`position`),
  CONSTRAINT `FK_iji6l3ytrjgytbgo6oi061elj` FOREIGN KEY (`position`) REFERENCES `position` (`id`),
  CONSTRAINT `FK_7pe330ganri24wsftsajlm4l9` FOREIGN KEY (`problems`) REFERENCES `problem` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `position_problems`
--

LOCK TABLES `position_problems` WRITE;
/*!40000 ALTER TABLE `position_problems` DISABLE KEYS */;
INSERT INTO `position_problems` VALUES (864,850),(864,851),(864,852),(865,850),(865,851),(865,852),(866,850),(866,851),(866,852),(867,850),(867,851),(867,852),(868,850),(868,851),(868,852),(869,850),(869,851),(869,852),(870,850),(870,851),(870,852),(871,860),(871,861),(871,862),(872,860),(872,861),(872,862),(873,860),(873,861),(873,862),(874,860),(874,861),(874,862),(875,860),(875,861),(875,862),(876,860),(876,861),(876,862),(877,860),(877,861),(877,862);
/*!40000 ALTER TABLE `position_problems` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `position_skills`
--

DROP TABLE IF EXISTS `position_skills`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `position_skills` (
  `position` int(11) NOT NULL,
  `skills` varchar(255) DEFAULT NULL,
  KEY `FK_iksb6ri4m9ktp19nm3n0iqq9k` (`position`),
  CONSTRAINT `FK_iksb6ri4m9ktp19nm3n0iqq9k` FOREIGN KEY (`position`) REFERENCES `position` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `position_skills`
--

LOCK TABLES `position_skills` WRITE;
/*!40000 ALTER TABLE `position_skills` DISABLE KEYS */;
INSERT INTO `position_skills` VALUES (864,'Database administration'),(864,'Coding'),(865,'Database administration'),(865,'Coding'),(866,'Database administration'),(866,'Coding'),(867,'Database administration'),(867,'Coding'),(868,'Database administration'),(868,'Coding'),(869,'Database administration'),(869,'Coding'),(870,'Database administration'),(870,'Coding'),(871,'Database administration'),(871,'Coding'),(872,'Database administration'),(872,'Coding'),(873,'Database administration'),(873,'Coding'),(874,'Database administration'),(874,'Coding'),(875,'Database administration'),(875,'Coding'),(876,'Database administration'),(876,'Coding'),(877,'Database administration'),(877,'Coding');
/*!40000 ALTER TABLE `position_skills` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `position_technologies`
--

DROP TABLE IF EXISTS `position_technologies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `position_technologies` (
  `position` int(11) NOT NULL,
  `technologies` varchar(255) DEFAULT NULL,
  KEY `FK_gap9mgajhx1f17j61fkxaagvy` (`position`),
  CONSTRAINT `FK_gap9mgajhx1f17j61fkxaagvy` FOREIGN KEY (`position`) REFERENCES `position` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `position_technologies`
--

LOCK TABLES `position_technologies` WRITE;
/*!40000 ALTER TABLE `position_technologies` DISABLE KEYS */;
INSERT INTO `position_technologies` VALUES (864,'UNIX'),(864,'Ruby'),(865,'UNIX'),(865,'Ruby'),(866,'UNIX'),(866,'Ruby'),(867,'UNIX'),(867,'Ruby'),(868,'UNIX'),(868,'Ruby'),(869,'UNIX'),(869,'Ruby'),(870,'UNIX'),(870,'Ruby'),(871,'UNIX'),(871,'Ruby'),(872,'UNIX'),(872,'Ruby'),(873,'UNIX'),(873,'Ruby'),(874,'UNIX'),(874,'Ruby'),(875,'UNIX'),(875,'Ruby'),(876,'UNIX'),(876,'Ruby'),(877,'UNIX'),(877,'Ruby');
/*!40000 ALTER TABLE `position_technologies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `problem`
--

DROP TABLE IF EXISTS `problem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `problem` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `attachments` varchar(255) DEFAULT NULL,
  `final_mode` bit(1) NOT NULL,
  `hint` varchar(255) DEFAULT NULL,
  `statement` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `company` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_1dla8eoii1nn6emoo4yv86pgb` (`company`),
  CONSTRAINT `FK_1dla8eoii1nn6emoo4yv86pgb` FOREIGN KEY (`company`) REFERENCES `company` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `problem`
--

LOCK TABLES `problem` WRITE;
/*!40000 ALTER TABLE `problem` DISABLE KEYS */;
INSERT INTO `problem` VALUES (850,0,'StackOverFlow','','You have to use ...','Solve this problem:...','Problem 1',848),(851,0,'StackOverFlow','','You have to use ...','Solve this problem:...','Problem 2',848),(852,0,'StackOverFlow','\0','You have to use ...','Solve this problem:...','Problem 3',848),(853,0,'StackOverFlow','','You have to use ...','Solve this problem:...','Problem 4',848),(854,0,'StackOverFlow','','You have to use ...','Solve this problem:...','Problem 5',848),(855,0,'StackOverFlow','','You have to use ...','Solve this problem:...','Problem 6',848),(856,0,'StackOverFlow','','You have to use ...','Solve this problem:...','Problem 7',848),(857,0,'StackOverFlow','','You have to use ...','Solve this problem:...','Problem 1',849),(858,0,'StackOverFlow','','You have to use ...','Solve this problem:...','Problem 2',849),(859,0,'StackOverFlow','','You have to use ...','Solve this problem:...','Problem 3',849),(860,0,'StackOverFlow','','You have to use ...','Solve this problem:...','Problem 4',849),(861,0,'StackOverFlow','','You have to use ...','Solve this problem:...','Problem 5',849),(862,0,'StackOverFlow','','You have to use ...','Solve this problem:...','Problem 6',849),(863,0,'StackOverFlow','','You have to use ...','Solve this problem:...','Problem 7',849);
/*!40000 ALTER TABLE `problem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `provider`
--

DROP TABLE IF EXISTS `provider`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `provider` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `computed` bit(1) DEFAULT NULL,
  `cvv` int(11) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `expiration_month` int(11) DEFAULT NULL,
  `expiration_year` int(11) DEFAULT NULL,
  `flag_spam` bit(1) NOT NULL,
  `holder_name` varchar(255) DEFAULT NULL,
  `is_banned` bit(1) NOT NULL,
  `make_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `vat` int(11) DEFAULT NULL,
  `user_account` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_pj40m1p8m3lcs2fkdl1n3f3lq` (`user_account`),
  CONSTRAINT `FK_pj40m1p8m3lcs2fkdl1n3f3lq` FOREIGN KEY (`user_account`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `provider`
--

LOCK TABLES `provider` WRITE;
/*!40000 ALTER TABLE `provider` DISABLE KEYS */;
INSERT INTO `provider` VALUES (916,0,'Address of the provider','\0',247,'provider1@hackerrank.com',2,2022,'\0','Provider one','\0','VISA','Provider 1','1111222233334444','+34 654654654','http://www.foto.com','One',21,914),(919,0,'Address of the provider','\0',247,'provider2@hackerrank.com',2,2022,'\0','Provider two','\0','VISA','Provider 2','1111222233334444','+34 654654654','http://www.foto.com','One',21,917),(929,0,'Address of the provider','\0',247,'provider@hackerrank.com',2,2022,'\0','Provider one','\0','VISA','Provider 3','1111222233334444','+34 654654654','http://www.foto.com','Three',21,927),(930,0,'Address of the provider','\0',247,'provider@hackerrank.com',2,2022,'\0','Provider one','','VISA','Provider 4','1111222233334444','+34 654654654','http://www.foto.com','One',21,928);
/*!40000 ALTER TABLE `provider` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `social_profile`
--

DROP TABLE IF EXISTS `social_profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `social_profile` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `link` varchar(255) DEFAULT NULL,
  `nick` varchar(255) DEFAULT NULL,
  `social_network` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `social_profile`
--

LOCK TABLES `social_profile` WRITE;
/*!40000 ALTER TABLE `social_profile` DISABLE KEYS */;
INSERT INTO `social_profile` VALUES (934,0,'http://www.twitter.com','UserTest','Twitter');
/*!40000 ALTER TABLE `social_profile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_account`
--

DROP TABLE IF EXISTS `user_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_account` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_castjbvpeeus0r8lbpehiu0e4` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_account`
--

LOCK TABLES `user_account` WRITE;
/*!40000 ALTER TABLE `user_account` DISABLE KEYS */;
INSERT INTO `user_account` VALUES (836,0,'21232f297a57a5a743894a0e4a801fc3','','admin'),(837,0,'2ba2a8ac968a7a2b0a7baa7f2fef18d2','','hacker1'),(838,0,'91af68b69a50a98dbc0800942540342c','','hacker2'),(839,0,'df655f976f7c9d3263815bd981225cd9','','company1'),(840,0,'d196a28097115067fefd73d25b0c0be8','','company2'),(841,0,'c84258e9c39059a89ab77d846ddab909','','admin2'),(842,0,'175d2e7a63f386554a4dd66e865c3e14','','auditor1'),(843,0,'04dd94ba3212ac52ad3a1f4cbfb52d4f','','auditor2'),(914,0,'cdb82d56473901641525fbbd1d5dab56','','provider1'),(917,0,'ebfc815ee2cc6a16225105bb7b3e1e53','','provider2'),(927,0,'4929539f6c0fea8799c223b27ba8e88c','','spammerprovider'),(928,0,'941b2697b7c95ccc7299ac31f7436196','\0','bannedprovider');
/*!40000 ALTER TABLE `user_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_account_authorities`
--

DROP TABLE IF EXISTS `user_account_authorities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_account_authorities` (
  `user_account` int(11) NOT NULL,
  `authority` varchar(255) DEFAULT NULL,
  KEY `FK_pao8cwh93fpccb0bx6ilq6gsl` (`user_account`),
  CONSTRAINT `FK_pao8cwh93fpccb0bx6ilq6gsl` FOREIGN KEY (`user_account`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_account_authorities`
--

LOCK TABLES `user_account_authorities` WRITE;
/*!40000 ALTER TABLE `user_account_authorities` DISABLE KEYS */;
INSERT INTO `user_account_authorities` VALUES (836,'ADMIN'),(837,'HACKER'),(838,'HACKER'),(839,'COMPANY'),(840,'COMPANY'),(841,'ADMIN'),(842,'AUDITOR'),(843,'AUDITOR'),(914,'PROVIDER'),(917,'PROVIDER'),(927,'PROVIDER'),(928,'PROVIDER');
/*!40000 ALTER TABLE `user_account_authorities` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-05-13 12:16:18

commit;