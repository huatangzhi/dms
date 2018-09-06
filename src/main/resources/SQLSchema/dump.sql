-- MySQL dump 10.13  Distrib 5.7.19, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: dms
-- ------------------------------------------------------
-- Server version	5.7.19-log

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
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL DEFAULT '',
  `password` varchar(128) NOT NULL DEFAULT '',
  `salt` varchar(32) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (11,'root','67fc8e46b0881d1951d58fc6e315bffa0e62664b1ccfbc181bfb87cc2cf78ba5','a4276');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `admin_login_ticket`
--

DROP TABLE IF EXISTS `admin_login_ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin_login_ticket` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `ticket` varchar(45) NOT NULL,
  `expired` datetime NOT NULL,
  `status` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `ticket_UNIQUE` (`ticket`)
) ENGINE=InnoDB AUTO_INCREMENT=93 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin_login_ticket`
--

LOCK TABLES `admin_login_ticket` WRITE;
/*!40000 ALTER TABLE `admin_login_ticket` DISABLE KEYS */;
INSERT INTO `admin_login_ticket` VALUES (92,11,'a112eb49475e49059daa61589ae7e21f','2018-09-07 10:36:36',0);
/*!40000 ALTER TABLE `admin_login_ticket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `device`
--

DROP TABLE IF EXISTS `device`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `device` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL,
  `index_code` int(11) NOT NULL,
  `resource_type` varchar(64) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `device_id_uindex` (`id`),
  UNIQUE KEY `device_name_uindex` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=1296 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `device`
--

LOCK TABLES `device` WRITE;
/*!40000 ALTER TABLE `device` DISABLE KEYS */;
INSERT INTO `device` VALUES (1234,'',1234,'CAMERA'),(1235,'root25',1,'CAMERA'),(1287,'111',111,'11111'),(1288,'root',22222,'22222'),(1289,'123',1111,'1111'),(1290,'root22',22222,'22222'),(1291,'root2',1111,'1111'),(1292,'root222',22222,'22222'),(1293,'wqe',12122222,'w'),(1294,'11121',11111111,'1111111111');
/*!40000 ALTER TABLE `device` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `login_ticket`
--

DROP TABLE IF EXISTS `login_ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `login_ticket` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `ticket` varchar(45) NOT NULL,
  `expired` datetime NOT NULL,
  `status` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `ticket_UNIQUE` (`ticket`)
) ENGINE=InnoDB AUTO_INCREMENT=281 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login_ticket`
--

LOCK TABLES `login_ticket` WRITE;
/*!40000 ALTER TABLE `login_ticket` DISABLE KEYS */;
INSERT INTO `login_ticket` VALUES (280,38,'cc9707cc550d455e9548669973311d63','2018-09-07 10:50:50',0);
/*!40000 ALTER TABLE `login_ticket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL DEFAULT '',
  `password` varchar(128) NOT NULL DEFAULT '',
  `salt` varchar(32) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=328 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (38,'hp','7f78c8ac58b00eb1ad2b112b9c05147fec856d8a379415797ccca5ee3eb02d09','54fca'),(39,'hp2','76149c717229e4d2ce703c6da58a89c374715ba2ceeffe2a9804db7a5a4e4bc5','2d5c8'),(40,'hp3','6aa0e1e576570dde2b7898111dfb44cc29415be6be0f76cf640bf7ece51271ed','8b0b1'),(41,'hp4','4984c9250de8011e71f1f0d8c57caf9451605ef393062f04358693a1bdeb6523','58394'),(232,'root5','51e459aacc54addca93659d0342fb2133d9bbb302f83af4d24d09e802e1e85dc','hikvision'),(233,'root6','c0fdaef448ff461818ddbbd8478fb6d3a128c505bade0d5cb9c8d6e7c6bf188e','hikvision'),(234,'root7','5f2488f2af175f74aab90fbf59a9b09b0b084dbd73afb054ebfe496f4209b536','hikvision'),(235,'root8','4fa21cbc42df267bbafa197d06d9823acbf994bd902bc33c55af7ad124d95594','hikvision'),(236,'root9','1f3f2e6287bb1e947a7057b63765aea396528c59e64ef6dc8cbbaa5ac25e557c','hikvision'),(237,'root10','73c522864044df125faff861900719b87e577165665370a93d79665a1f7d144a','hikvision'),(238,'root11','b69313e63f32ef3c69b79aabd5ea4a0c0f4cb2fe7a06cf7d55fb1dbc4007aaa8','hikvision'),(239,'root12','3629f60d3bee44b2a36cc6c87d6ed5975ace70bc33b66cb419718ec96227a763','hikvision'),(240,'root13','903a29efd239704eab00e8dd758ad750eb0ce02f09fccde3feb3e742d62f011e','hikvision'),(241,'root14','bc7d88637f99e95726ce9f54c6096d3e5def8388a751dd3513125fa927bc0a81','hikvision'),(242,'root15','e1611b49691fe32dc2237e15cc88fa7bf02958bbe0ad6327fbe22f0347e6eef2','hikvision'),(243,'root16','355afb28b0f97707541956ceb2945dca4eb1adfb21673bb937638a9bdbed1660','hikvision'),(244,'root17','9a9e40906620c6f747bdb23224fcaa4204ca665731c9095de720394bb9d6d470','hikvision'),(245,'root18','9d02ba643f4c2b4cb27578a7c385c95403850841e7e7a6335438b6d1bc51109c','hikvision'),(246,'root19','26a031ed164645621d9aa1fa7d9162e2639ec88ca13a456c74bfdb52597694aa','hikvision'),(247,'root20','121c38fdb1efea1deaf29b225aa6325c883c68c67624398b34eac0c440f1d3b6','hikvision'),(248,'root21','1b064e23d890a4b03a105738e7f910b5d7029b8d45b7f60e7f1ce8a00dc77406','hikvision'),(249,'root22','207871a078d8d0b2c84808916a8f3ba9d25dc70f90bbd5f73c0d4be14d52ce00','hikvision'),(250,'root23','0f4ada09044f6e76274f1d3c2186fb1ee4128a0bdc082cc62652928b3ce717dc','hikvision'),(251,'root24','333d5af99eafa1ecb5c9c4d1aba894b57321314f2ce5c045214d8c2d0218e1b3','hikvision'),(252,'root25','babec101820b4d2c21238c0ad1788b25b4772135f9cc3488850a3ca423d49e5a','hikvision'),(253,'root26','99531e3af533c57f7f88571cfac34b3f4eeb9e0403ac2caee7f36d8ed43d3bca','hikvision'),(254,'root27','2e42006d5d472941becc6f6a345b0ed1a44a5b100713653ef112c58c67486993','hikvision'),(255,'root28','3cdc91ec436653ea4941fc21a06d5bf90a4d5bf2676c2045a6672a9783830c7c','hikvision'),(256,'root29','4159ba43da14e2b629bac58a3291fbb5e1e04ec93dbd0d14303c0998ebe205df','hikvision'),(257,'root30','f91d8a5d9d642fd02872befd9e0adf320a616f8266903d4b6fe3f944ce546c23','hikvision'),(258,'root31','76479b6c0e9f1295c237bbc23bc1f2d9ddb455e7a02c90544b7a110b93d0c90e','hikvision'),(259,'root32','2e5f0e0f2487fa49826c08d9861ee5f7a0944eb65313e4f33fc63e20a703b243','hikvision'),(260,'root33','c0fcddc455b8d5003e8bfe9bc08b88945bbd0ff2ed59703c81202bde52ec0553','hikvision'),(261,'root34','e3829a048e5ae30dee720898c3e11252356760504d5d84ae54e0370746b0294e','hikvision'),(262,'root35','865cb3ad49c70273b291d173f2487fc0b9f0bb4940112689a65f46bf80fa80ad','hikvision'),(263,'root36','82ec47bce0b725581906618a70848a81ee9372e6ac007fb6083adc4996f17489','hikvision'),(264,'root37','446a3d5ff310e5112484db2ee404dc4a9870c75dc3aeb0cd610f120a64f00925','hikvision'),(265,'root38','7580cc679ad90e3cd22d37e29a1c6bb41ebafe2fef9c5131adaae9527d24ad8e','hikvision'),(266,'root39','e9e84d23a08d4ee25dbadbf91b9ea2d2971bcbf082de62211366170254f78296','hikvision'),(267,'root40','b659259ca47d0d0beeb072a9d2d170ce99852790765f5fd9ca0f9c2c9999bc37','hikvision'),(268,'root41','ce658d3b30036919ba0c660cc88ac6ddba2904c362a856c5f6c744c7d4e0d142','hikvision'),(269,'root42','098cdd27dabce69f607e1793d9ad47c88092964c03d3cb8dc3ce0e81ff36f41a','hikvision'),(270,'root43','93acf2d6b963e259f99e9d3c3d8e74b513b222bc3b092be757a72c9d2a743424','hikvision'),(271,'root44','a3d943c2f5045ae9ea29f82f9e732e4d4c7180997badec80c24ffec8ae0ca584','hikvision'),(272,'root45','6255e0f9630fda64c6e14967e86c2fdb466baac2766c5918db0f7e0174354e03','hikvision'),(273,'root46','3f2544f0a41d23d3c4d2c4f6d8f9ba3460cb649215e0354168ac98ad5bca6a48','hikvision'),(274,'root47','3eb808a0130142a16e46eec416818d4b61645f6b12798cf0bc33e8c8c0f7e2fa','hikvision'),(275,'root48','3607e307ebf1397779c6f7f9117c0652884245a5b463c70d04141e0c044bd473','hikvision'),(276,'root49','6701b3225bff5403efb66cd596e3504812ed1b8e76f64a07954e0a7f4b22b060','hikvision'),(277,'root50','98261dec27864f6351a203c4824979daf8f8e9068e4c6a349b1754b07fd26292','hikvision'),(278,'root51','18d70b23f924143496cf7e8d61ec10f9ec17e0091c45d054128061bdbaadf45d','hikvision'),(279,'root52','ac04395fc8a7bb5dab008e4e381c98ac729d633553ef562e6e50657d33ce1627','hikvision'),(280,'root53','ef448096683771ef54f056d923f5bf7127c7d3f517094040781bd490811f94f8','hikvision'),(281,'root54','8b7e40a3c33034236d9c0e9daf408ef45bc1c246e3c9bcb1240e7fc8c2b040e2','hikvision'),(282,'root55','6256bde4c0e9a723620926b9ae4e4d3ccb7fdc389817e745e2b8bb0ceafd6a57','hikvision'),(283,'root56','c317b253d0b823e4cc5deb1303096e785614170199219439652e1eb2b688f8c6','hikvision'),(284,'root57','129dbfec7609fc24b690462333353863d6f551a798bf113275630240a0df23f8','hikvision'),(285,'root58','a6fcbeb2753292c70f6c6d982640d90001d271122b3a3b8d3dd52227ef202e21','hikvision'),(286,'root59','c3c13e93028fade963c0ce63143a1e17a2245226715dfc2bbba6910ce031b93d','hikvision'),(287,'root60','54f8e9fbf507dce7ddccdc83f15f1a635d7f7b90861f27ef17340231c2cbccaf','hikvision'),(288,'root61','2d7063de095297a2b19ff2098515b5f5fe4c05511cd24bcf9c8a62b51ee629bc','hikvision'),(289,'root62','ed3a2a6fcd901ce2f8289f95ea75d4891babb5ae20aefe75c24400a49d088e47','hikvision'),(290,'root63','b42e960c9a55c6bfad8cb6a1f0ba41dc0482139708effcb2a5c504d4b74ebe57','hikvision'),(291,'root64','e11bfe2bae987d510495f0ce1e01370a85974e565582e45d495347a43c71fbef','hikvision'),(292,'root65','4a29ed384271fe824c2cf48789370e9b14b4ce11ef74e0de3e35af713e8d9262','hikvision'),(293,'root66','2a24235ea88178dccdd34df4fa645636d5a5fafa68ab78e1d29905182a26f9b9','hikvision'),(294,'root67','7a72db74b372c0187f19c383096448feb24adaf2200b5e811ea4733f1e686a63','hikvision'),(295,'root68','409e6b5775e0e28e14dc0866266439344f293868487a34fdd36d9f21c35ab3d8','hikvision'),(296,'root69','c6976823b5c518c00df58b6400134e8ac9c758740dd81feecad12d70ff7552e4','hikvision'),(297,'root70','3dd492824ef751f525cb0544e2bd5df25266ddbe7cfdf20b7cb5d56dcc39f7e3','hikvision'),(298,'root71','c2a26168b24fe1eeab4ccdba8bf72941c1943814be282e7b0ddeabc7ca44571a','hikvision'),(299,'root72','b0084571acdf24048507a6df344e83f37a8ccf9e4639b012ce7c9ae73cfbf2ac','hikvision'),(300,'root73','28224b187a35fbfe9be2cd97a29fa54841a9e3fae11bf5d74cc58ed78f3c624c','hikvision'),(301,'root74','5989d74193482b06085d511c5118c369aee6b7de15bf51c7c79cdb2f4f8bd95a','hikvision'),(302,'root75','481aed109462f20c11bc29ab44d5329b8f3706858584a0ff9adfa5a49352a9cb','hikvision'),(303,'root76','545b1cc6b6932e29ba39f12adccd71cceb364eea20ca2c7827cbfde9eb13a705','hikvision'),(304,'root77','936d90189269fa3e6eb083e044005f279f030a8db22af91398174c53b16e6897','hikvision'),(305,'root78','1bd62db0e07754332a650be712f5d94b8e2a95154ae00d6b7c178409f9972d71','hikvision'),(306,'root79','4ed848313a3ccd44baf1a95d64979a47149ccf89f634b0a43f83a70761969500','hikvision'),(307,'root80','9d44c437b60d20ad0bf9b5c84e4e2107b4e7bbd314d8c18189c20770905f08a9','hikvision'),(308,'root81','16d7b338a0892d8c7d18efcbeaf796663be4053793af8a56402a5689b2c7c9aa','hikvision'),(309,'root82','e7740c5dca13e580313721a8aa54e0ebabac6f87a9d349e1e89415641b7874ff','hikvision'),(310,'root83','a9d6ed148ddcc5327b8102f894df29c5f580123963af5ce05f1ca37f00098b15','hikvision'),(311,'root84','0dbf1bcf46bdbeb49996a4c50ee71c06ece83c58f5992dc9d830a13012cfc3c2','hikvision'),(312,'root85','087990751a63dd180d5169ed8167aa530d396c4a610c0d6e9141c8cec57338e3','hikvision'),(313,'root86','ca6a89824bd83e90f64efab201f4a31c1409611743d6ed030fa38726d6d8286b','hikvision'),(314,'root87','d5ef46c6e96b6e317650e5e03d81b676f56848b4105de0387758c2a466d93f8a','hikvision'),(315,'root88','64b08a293b2de15838e951ef77bdae95a9a3493d75056b0a424aa4899bb44a49','hikvision'),(316,'root89','f9b79f5a61451b0210bab71c00816e9d0b48628f233b202c27052bf8bea79ec5','hikvision'),(317,'root90','a2fdbf62a61944b8baebf7406ada93ad8ef2c510537e892a07fdb6fcf5fa9f97','hikvision'),(318,'root91','9ea47bc6d564a7552686bd25f52a7b68c8d373b581ce67354277c7210526ff35','hikvision'),(319,'root92','5037674050fc125afad8c22c88d6287392edaa03ed7800d662bccccaffcee349','hikvision'),(320,'root93','6b9525fd4fa84cb6f1c1ca57c837c37ddd988112dc9900a72c7af4e2f021de89','hikvision'),(321,'root94','bf99cce64845c636cebeafc80c60ee388a3f393f728a6517ac830c54db24cac4','hikvision'),(322,'root95','ea96fd4921756dd37e60ae2ad4145deb4ef85d69f6f550e71819d16ae7190566','hikvision'),(323,'root96','169bfed70c6ba0006722d016e5a7aeb44c6556b118f0a29f94cd79b348d61369','hikvision'),(324,'root97','a3a0744c493e1d5d67478010184df493bf3ad629411ac24c8386df8ec3af828c','hikvision'),(325,'root98','1ac614b1ed4fd558bb5b0675eed7fb824eee043034bf9d218f8aabb76c0f61d0','hikvision'),(326,'root99','16bf2df776af6048b282e04a7e435425401f8185da4b2304faa5bb17dc442fba','hikvision'),(327,'root','5d906c748e911c9ef7f46216e35f64b9a1115da37a0eb5eb0f045878d0d0f430','e99b5');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_device_record`
--

DROP TABLE IF EXISTS `user_device_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_device_record` (
  `user_id` int(11) NOT NULL,
  `device_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户拥有的设备记录';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_device_record`
--

LOCK TABLES `user_device_record` WRITE;
/*!40000 ALTER TABLE `user_device_record` DISABLE KEYS */;
INSERT INTO `user_device_record` VALUES (38,1287),(38,1288),(38,1289),(38,1290),(38,1291),(38,1292),(38,1293),(38,1294);
/*!40000 ALTER TABLE `user_device_record` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-09-06 12:31:43
