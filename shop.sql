CREATE DATABASE  IF NOT EXISTS `fashionstore` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `fashionstore`;
-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: fashionstore
-- ------------------------------------------------------
-- Server version	8.1.0

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
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart` (
  `id` int NOT NULL AUTO_INCREMENT,
  `count` int NOT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  `product_details_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKl70asp4l4w0jmbm1tqyofho4o` (`user_id`),
  KEY `FK_product_dt_ID` (`product_details_id`),
  CONSTRAINT `FK_product_dt_ID` FOREIGN KEY (`product_details_id`) REFERENCES `product_details` (`id`),
  CONSTRAINT `FKl70asp4l4w0jmbm1tqyofho4o` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
INSERT INTO `cart` VALUES (27,1,'quanghuy',1);
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `category_name` varchar(1111) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Áo'),(2,'Quần'),(3,'Giày'),(4,'Mũ'),(5,'Kính');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `conversations`
--

DROP TABLE IF EXISTS `conversations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `conversations` (
  `id` int NOT NULL AUTO_INCREMENT,
  `message` varchar(11111) NOT NULL,
  `is_user` int DEFAULT NULL,
  `timestamp` datetime DEFAULT CURRENT_TIMESTAMP,
  `user_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `conversations_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `conversations`
--

LOCK TABLES `conversations` WRITE;
/*!40000 ALTER TABLE `conversations` DISABLE KEYS */;
INSERT INTO `conversations` VALUES (64,'xin chào',1,'2023-10-20 17:52:10','quanghuy'),(65,'Bạn đi chơi ở vùng nào?',0,'2023-10-20 17:52:37','quanghuy'),(69,'\"hello\"',1,'2023-10-20 18:01:18','quanghuy'),(70,'Bạn đi chơi ở vùng nào?',0,'2023-10-20 18:01:19','quanghuy'),(71,'\"xin chào\"',1,'2023-10-20 18:01:24','quanghuy'),(72,'Bạn đi chơi ở vùng nào?',0,'2023-10-20 18:01:24','quanghuy'),(73,'\"ơ;\"',1,'2023-10-20 18:02:00','quanghuy'),(74,'Bạn đi chơi ở vùng nào?',0,'2023-10-20 18:02:01','quanghuy'),(75,'\";ơ;\"',1,'2023-10-20 18:02:02','quanghuy'),(76,'Bạn đi chơi ở vùng nào?',0,'2023-10-20 18:02:03','quanghuy'),(77,'\"biển\"',1,'2023-10-20 18:02:25','quanghuy'),(78,'list3',0,'2023-10-20 18:02:25','quanghuy');
/*!40000 ALTER TABLE `conversations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order` (
  `id` int NOT NULL AUTO_INCREMENT,
  `address` varchar(1111) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `booking_date` date DEFAULT NULL,
  `country` varchar(1111) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `email` varchar(1111) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `fullname` varchar(1111) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `note` varchar(1111) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `payment_method` varchar(1111) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `phone` varchar(1111) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `status` varchar(1111) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `total` int DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKcpl0mjoeqhxvgeeeq5piwpd3i` (`user_id`),
  CONSTRAINT `FKcpl0mjoeqhxvgeeeq5piwpd3i` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` VALUES (16,'','2023-10-02','','voquanghuy08102000@gmail.com','Võ Quang Huy','','Payment on delivery','','Pending',1729000,'quanghuy');
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_item`
--

DROP TABLE IF EXISTS `order_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_item` (
  `id` int NOT NULL AUTO_INCREMENT,
  `count` int DEFAULT NULL,
  `order_id` int DEFAULT NULL,
  `product_details_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKs234mi6jususbx4b37k44cipy` (`order_id`),
  KEY `FK_product_dt1_ID` (`product_details_id`),
  CONSTRAINT `FK_product_dt1_ID` FOREIGN KEY (`product_details_id`) REFERENCES `product_details` (`id`),
  CONSTRAINT `FKs234mi6jususbx4b37k44cipy` FOREIGN KEY (`order_id`) REFERENCES `order` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_item`
--

LOCK TABLES `order_item` WRITE;
/*!40000 ALTER TABLE `order_item` DISABLE KEYS */;
INSERT INTO `order_item` VALUES (43,1,16,43),(44,2,16,59),(45,1,16,1);
/*!40000 ALTER TABLE `order_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` date DEFAULT NULL,
  `description` varchar(11111) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `is_active` int DEFAULT NULL,
  `is_selling` int DEFAULT NULL,
  `price` int DEFAULT NULL,
  `product_name` varchar(1111) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `sold` int DEFAULT NULL,
  `category_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1mtsbur82frn64de7balymq9s` (`category_id`),
  CONSTRAINT `FK1mtsbur82frn64de7balymq9s` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'2023-01-21','Lightweight hoodie from UO in a shrunken fit thatproduct_imageproduct_image\'s perfect for layering. Garment-washed for a lived-in look with a zip front and kanga pockets. Only at Urban Outfitters.',1,1,500000,'BDG Bonfire Zip-Up Hoodie Sweatshirt',25,20,1),(2,'2023-01-21','Big corduroy work shirt from Urban Outfitters. Cut in a slightly oversized silhouette, this cotton cord shirt offers a full-length button-front closure, pointed collar and single patch pocket at the chest. Complete with long sleeves with adjustable button cuffs, yoked back and a split, rounded hemline.',1,1,300000,'UO Big Corduroy Work Shirt',54,10,1),(3,'2023-01-11','Wide stripe pattern t-shirt by Urban Outfitters. Premium short sleeve tee in a boxy silhouette with short sleeves and a ribbed knit crew neck.',1,1,360000,'UO Striped Boxy Tee',98,20,1),(4,'2023-03-30','Plaid pattern long sleeve overshirt by Wax London. Button-down closure front with a left chest pocket & standard collar. Dedicated to crafting premium tailored designs with eco-friendly methods, Wax London creates their pieces in their UK homeland with sustainably produced fabrics and natural dye techniques. With a firm belief in practicing what they preach, the brand strives to reduce their environmental impact with each and every stitch.',1,1,520000,'Wax London Whiting Overshirt',150,90,1),(5,'2023-02-09','Polo collar pullover sweatshirt by Levi’s. Striped look with text at the left chest. Fitted with a partial button closure at the front. Since 1873, Levi’s® has stood for classic American style and effortless cool. From their classic 501 jeans, modern 511 slim fit jeans, Trucker denim jackets and everything in between, they get it right every time.',1,1,120000,'Levi’s Archive Collared Sweatshirt',100,80,1),(6,'2023-01-03','Short sleeve shirt by The North Face topped with patch and embroidered detailing. Button-down style in a slightly boxy fit. Topped with chest pockets and fitted with a notched collar. The North Face mission is to build the best gear, support the preservation of wild places and inspire a global movement of outdoor exploration.',1,1,340000,'The North Face Valley Utility Shirt',100,130,1),(7,'2023-04-21','Textured cotton shirt jacket by Standard Cloth. Fitted with a button closure at the front and notched tab collar. Topped with flap pockets at the chest. Urban Outfitters exclusive. Standard Cloth is committed to using quality materials, offering great fits and leaving no detail unconsidered. These are your new favorites.',1,1,250000,'Standard Cloth Textured Shirt Jacket',100,20,1),(8,'2023-01-21','Quilted shirt jacket by Standard Cloth. Button-down style with flap pockets at the chest and a tab collar. Urban Outfitters exclusive.',1,1,220000,'Standard Cloth Lightweight Quilted Shirt Jacket',100,70,1),(9,'2023-05-02','Utility vest by American sportswear label Polo Ralph Lauren. Durable cotton canvas with allover flap & zip closure pockets. V-Neckline silhouette with a zip closure front. Topped with patch accents and chainstitch embroidered detailing.',1,1,190000,'Polo Ralph Lauren Marlin Lined Vest',100,20,1),(10,'2023-04-10','Slim-fit jeans by BDG with ripped accents at the knees. Mid-rise waist and straight leg silhouette. Longline inseam for stacked styling to compliment your favorite sneakers. Fitted with a zip fly & button closure. Urban Outfitters exclusive.',1,1,549000,'BDG Sneaker Stack Jean – Destructed Vintage Wash',100,90,2),(11,'2023-03-09','Slim fit jeans by BDG in a throwback style. Cut with a mid rise and subtle tapered leg in rigid BDG denim. Features a classic 5-pocket styling with a button & zip closure. Only at Urban Outfitters.',1,1,520000,'BDG \'90s Vintage Slim Fit Jean',100,10,2),(12,'2023-02-10','Skinny fit jeans by BDG. Rigid denim with a low-rise waist and skinny fit. Classic 5-pocket style with a zip fly & button closure. Urban Outfitters exclusive.',1,1,230000,'BDG Low Rise Skinny Jean',100,75,2),(13,'2023-05-09','Skinny fit jeans by Rolla’s in rigid denim. Features a mid-rise waist and fitted with pockets. Includes a zip fly and button closure.',1,1,900000,'Rolla’s Stinger Skinny Fit Jean',100,90,2),(14,'2023-01-07','Stripe pattern mesh basketball shorts by Standard Cloth. Fitted with pockets at the sides and back. Includes an elastic waistband with an adjustable tie at the waist. Urban Outfitters exclusive.',1,1,180000,'Standard Cloth Striped Mesh Basketball Short',100,20,2),(15,'2023-04-06','Nylon shorts by Standard Cloth. Basic silhouette with 4\" inseams Fitted with pockets. Includes an elastic waistband & adjustable tie closure. Urban Outfitters exclusive.',1,1,100000,'Standard Cloth Oliver 2.0 3\" Nylon Short',100,200,2),(16,'2023-04-17','Essential swim shorts by Vans with an elastic waistband and adjustable drawstring closure. Fitted with pockets at the sides and a logo label tab accent.',1,1,370000,'Vans Primary Solid Elastic Swim Short',100,200,2),(17,'2023-04-11','Balloon silhouette pants by Champion with a nylon fabrication. Fitted with a stretch elastic waistband and gathered ankle cuffs. Fitted with pockets at the sides and back. Urban Outfitters exclusive.',1,1,910000,'Champion UO Exclusive Nylon Balloon Pant',100,10,2),(18,'2023-01-18','Swim shorts by Dark seas topped with flap pocket detailing at the side. Cut with a mid-rise waist and 5\" inseam. Finished with a zip fly and button closure.',1,1,200000,'Dark Seas Hitch Stretch Swim Short',100,20,2),(19,'2023-04-04','Overalls by Levi’s® with contrast stitching. Classic bib front style with utility pockets. Fitted with adjustable shoulder straps & buckle closures.',1,1,750000,'Levi’s® Contrast Stitch Skate Overall',100,80,2),(20,'2023-04-11','The classic Chuck 70 style by Converse in a seasonal colorway. High top silhouette with a lace up front. Fitted with a rubber toe cap and finished with textured traction at the sole.',1,1,720000,'Converse Chuck 70 High Top Sneaker',100,50,3),(21,'2023-03-10','Updated 2002R sneakers by New Balance with a distressed look. Suede and mesh overlay uppers with a lace up front. Cushioned AZORB tech with textured tread at the rubber outsole.',1,1,520000,'New Balance 2002R Protection Pack Sneaker',100,10,3),(22,'2023-02-10','Retro vibe Chuck Taylor high tops from Converse. The Chuck 70 Core 162050C sneaker has a durable canvas upper offers contrast stitch detailing and a lace front closure, complete with a rubber toe cap and grippy rubber outsole. A legit heritage classic in the world of sneakers, Converse is the name behind our beloved Chucks, and is respected worldwide as a producer of true, original, athletic footwear.',1,1,520000,'Converse Chuck 70 Core High Top Sneaker',100,10,3),(23,'2023-04-29','Mule style clog by Dr. Martens. Features a strap at the back with an adjustable buckle. Outstitch detailing and finished with a lugged sole.',1,1,370000,'Dr. Martens Jorge II Clog',100,20,3),(24,'2023-04-30','Classic high top sneakers by Converse in the essential Chuck 70 silhouette. Multi-color upper with a lace closure. Includes a rubber toe cap and textured rubber outsole.',1,1,120000,'Converse Chuck 70 Craft Mix High Top Sneaker',100,20,3),(25,'2023-02-12','The ultimate recovery sandal by HOKA ONE ONE®. Features a dual-layer construction with optimized groove placement and fitted with a cushioned midsole for maximum support.',1,1,510000,'HOKA ONE ONE® Ora Recovery 3 Slide Sandal',100,100,3),(26,'2023-03-29','Classic low top sneakers by Converse and Beyond Retro. Patchwork look upper with a lace up closure. Fitted with a rubber toe cap and textured tread at the rubber sole.',1,1,820000,'Converse X Beyond Retro Chuck 70 Low Top Sneaker',100,70,3),(27,'2023-02-24','Essential flip flops by Rainbow crafted from leather with a 1\" strap. Single layer arch style with a non-slip outsole.',1,1,670000,'Rainbow Single Layer Flip Flop',100,50,3),(28,'2023-05-23','Designed with a lightweight but durable sole for indoors and out, this classic UGG slipper is a versatile must-have. Soft suede upper in a slip-on silhouette with plush natural wool insole and sheepskin lining. Updated with a bold logo at the top. Set on a Treadlite by UGG™ outsole for added traction and finished with an embroidered Tasman braid.',1,1,710000,'UGG Tasman Logo Slipper Clog',100,90,3),(29,'2023-03-09','Corduroy cap by ’47 with an adjustable closure and curved brim. Topped Los Angeles Dodgers logo embroidery. Urban Outfitters exclusive.',1,1,249000,'’47 UO Exclusive MLB Los Angeles Dodgers Cord Cleanup Baseball Hat',100,100,4),(30,'2023-04-10','Short roll beanie from Urban Outfitters, crafted in a circular knit from an acrylic fabrication. Finished with an adjustable roll top for a customizable wear.',1,1,250000,'UO Short Roll Knit Beanie',200,310,4),(31,'2023-05-04','Foldover cuff beanie by OBEY in a ribbed fabrication and topped with logo text at the front.',1,1,180000,'OBEY Future Beanie',100,10,4),(32,'2023-04-14','Retro look hat by Mitchell & Ness with Los Angeles Lakers embroidery at the front. Fitted with an adjustable snapback closure and a curved brim.',1,1,990000,'Mitchell & Ness Deadstock Championship Los Angeles Lakers Hat',100,90,4),(33,'2023-01-16','Logo bucket hat by Champion with embroidered text at the front. Cotton canvas with a garment wash effect. Flat top style with an all-around brim.',1,1,100000,'Champion Garment Washed Relaxed Bucket Hat',100,20,4),(34,'2023-03-23','Pinstripe pattern MLB team hat by New Era. Cotton canvas paneled hat with an adjustable snapback closure.',1,1,190000,'New Era San Francisco Giants Pinstripe Baseball Hat',100,70,4),(35,'2023-03-25','Tie-dye hat by Market with embroidered text at the front. Adjustable fit with a strap closure at the back. Fitted with a flat brim.',1,1,200000,'Market Creative Chemistry Hat',100,90,4),(36,'2023-01-19','Embroidered New York Eagles hat by American Needle. Paneled construction with an adjustable snap closure at the back and a curved brim.',1,1,560000,'American Needle New York Eagles Hat',100,78,4),(37,'2023-01-15','Paneled hat by ’47 with a University Of North Carolina logo motif embroidered at the front. Fitted with an adjustable strap closure at the back and a curved brim.',1,1,120000,'’47 University Of North Carolina Baseball Hat',100,120,4),(38,'2023-05-04','Vintage sunglasses in their original form. Features a round retro silhouette and fitted with tinted lenses. The Urban Renewal team sourced the best of this vintage style from around the world. We’ll pick one just for you from our limited supply of one-of-a-kind designs. Each piece is unique and the one you receive will vary from what you see here.',1,1,999000,'Urban Renewal Vintage Orbiter Sunglasses',100,30,5),(39,'2023-05-30','Round silhouette blue light glasses. Features a classic look wire frame with a padded nose bridge. Finished with non-magnifying clear lenses.',1,1,899000,'Wes Round Blue Light Glasses',148,50,5),(40,'2023-05-29','Vintage sunglasses in an aviator silhouette with a plastic frame and tinted lenses. The Urban Renewal team sourced the best of this vintage style from around the world. We’ll pick one just for you from our limited supply of one-of-a-kind designs. Each piece is unique and the one you receive will vary from what you see here.',1,1,690000,'Urban Renewal Vintage Triton Sunglasses',80,70,5),(41,'2023-05-27','Vintage sunglasses in their original form. Features chunky plastic side arms and a metal frame with rounded look. Fitted with tinted lenses. The Urban Renewal team sourced the best of this vintage style from around the world. We’ll pick one just for you from our limited supply of one-of-a-kind designs. Each piece is unique and the one you receive will vary from what you see here.',1,1,230000,'Urban Renewal Vintage Hell Yeah Sunglasses',100,10,5),(42,'2023-04-06','Micro shield silhouette sunglasses with a retro look and tinted UV-blocking lens.',1,1,760000,'Trey Micro Shield Sunglasses',100,20,5),(43,'2023-03-30','Retro aviator silhouette sunglasses with an updated angular look. Includes a brow bar accent and fitted with tinted UV-blocking lenses.',1,1,520000,'Burke Metal Navigator Sunglasses',100,90,5),(44,'2023-04-15','From the pros at Crap Eyewear, these Sweet Leaf sunglasses are handcrafted with optical-grade biodegradable bioacetate in a small, round silhouette. Equipped with CR-39 lenses + 100% UVA/UVB protection. Finished with durable 5-barrel hinges and custom wire temple cores for added strength and superior fit.',1,1,350000,'Crap Eyewear The Sweet Leaf Rectangle Oval Sunglasses',100,10,5),(45,'2023-05-16','Sunglasses by EPOKHE and Dylan Rieder. Premium acetate sunglasses fitted with tinted UV-blocking lenses.',1,1,120000,'EPOKHE Dylan Sunglasses',100,50,5),(46,'2023-03-11','Aviator style sunglasses with a retro look. Plastic frame style and fitted with tinted lenses.',1,1,240000,'Eldridge Aviator Sunglasses',100,500,5),(62,'2023-10-01','',1,1,456,'123',114,0,1);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_details`
--

DROP TABLE IF EXISTS `product_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_details` (
  `id` int NOT NULL AUTO_INCREMENT,
  `product_id` int DEFAULT NULL,
  `size` int DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `color` varchar(11) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `product_id` (`product_id`),
  CONSTRAINT `product_details_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_details`
--

LOCK TABLES `product_details` WRITE;
/*!40000 ALTER TABLE `product_details` DISABLE KEYS */;
INSERT INTO `product_details` VALUES (1,39,36,32,'#f7941d'),(2,39,37,46,'#f7941d'),(3,1,36,9,'#7eabe9'),(4,2,49,44,'#111111'),(5,3,38,59,'#322323'),(6,4,1,48,'#'),(7,5,1,50,'#'),(8,6,1,50,'#'),(9,7,1,50,'#'),(10,8,1,50,'#'),(11,9,1,50,'#'),(12,10,1,50,'#'),(13,11,1,50,'#'),(14,12,1,50,'#'),(15,13,1,49,'#'),(16,14,1,50,'#'),(17,15,1,50,'#'),(18,16,1,50,'#'),(19,17,1,50,'#'),(20,18,1,50,'#'),(21,19,1,50,'#'),(22,20,1,50,'#'),(23,21,1,50,'#'),(24,22,1,50,'#'),(25,23,1,50,'#'),(26,24,1,50,'#'),(27,25,1,50,'#'),(28,26,1,50,'#'),(29,27,1,50,'#'),(30,28,1,50,'#'),(31,29,1,50,'#'),(32,30,1,50,'#'),(33,31,1,50,'#'),(34,32,1,50,'#'),(35,33,1,50,'#'),(36,34,1,50,'#'),(37,35,1,50,'#'),(38,36,1,50,'#'),(39,37,1,50,'#'),(40,38,1,50,'#'),(42,40,1,48,'#'),(43,41,1,45,'#'),(44,42,1,50,'#'),(45,43,1,50,'#'),(46,44,1,50,'#'),(47,45,1,50,'#'),(48,46,1,50,'#'),(50,39,36,48,'#7eabe9'),(51,1,37,16,'#808080'),(52,39,37,20,'#7eabe9'),(59,2,48,6,'#322323'),(60,3,36,39,'#7eabe3'),(61,62,38,55,'# F4A460'),(62,62,36,59,'#7eabe3');
/*!40000 ALTER TABLE `product_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_image`
--

DROP TABLE IF EXISTS `product_image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_image` (
  `id` int NOT NULL AUTO_INCREMENT,
  `url_image` varchar(1111) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `product_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6oo0cvcdtb6qmwsga468uuukk` (`product_id`),
  CONSTRAINT `FK6oo0cvcdtb6qmwsga468uuukk` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=186 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_image`
--

LOCK TABLES `product_image` WRITE;
/*!40000 ALTER TABLE `product_image` DISABLE KEYS */;
INSERT INTO `product_image` VALUES (1,'https://res.cloudinary.com/drlroexjl/image/upload/v1680703085/fashionstore/69614485_004_b_zughuc.webp',1),(2,'https://res.cloudinary.com/drlroexjl/image/upload/v1680703093/fashionstore/69614485_004_d_ukknfz.webp',1),(3,'https://res.cloudinary.com/drlroexjl/image/upload/v1680703104/fashionstore/69614485_004_f_h5y7g0.webp',1),(4,'https://res.cloudinary.com/drlroexjl/image/upload/v1680703127/fashionstore/69614485_004_e_t8yw8b.webp',1),(5,'https://res.cloudinary.com/drlroexjl/image/upload/v1680703710/fashionstore/55150924_022_b_hrhh3f.webp',2),(6,'https://res.cloudinary.com/drlroexjl/image/upload/v1680703715/fashionstore/55150924_022_d_hnpgns.webp',2),(7,'https://res.cloudinary.com/drlroexjl/image/upload/v1680703723/fashionstore/55150924_022_e_mesg3x.webp',2),(8,'https://res.cloudinary.com/drlroexjl/image/upload/v1680703729/fashionstore/55150924_022_f_jsarwh.webp',2),(9,'https://res.cloudinary.com/drlroexjl/image/upload/v1680704262/fashionstore/82058512_029_b_wkz8t6.webp',3),(10,'https://res.cloudinary.com/drlroexjl/image/upload/v1680704266/fashionstore/82058512_029_d_jgju9x.webp',3),(11,'https://res.cloudinary.com/drlroexjl/image/upload/v1680704271/fashionstore/82058512_029_e_ppcya8.webp',3),(12,'https://res.cloudinary.com/drlroexjl/image/upload/v1680704280/fashionstore/82058512_029_f_g333ep.webp',3),(13,'https://res.cloudinary.com/drlroexjl/image/upload/v1680704388/fashionstore/82627878_015_e_jztu3u.webp',4),(14,'https://res.cloudinary.com/drlroexjl/image/upload/v1680704394/fashionstore/82627878_015_b_mju6tw.webp',4),(15,'https://res.cloudinary.com/drlroexjl/image/upload/v1680704400/fashionstore/82627878_015_d_kpjrzz.webp',4),(16,'https://res.cloudinary.com/drlroexjl/image/upload/v1680704403/fashionstore/82627878_015_f_ql0qiv.webp',4),(17,'https://res.cloudinary.com/drlroexjl/image/upload/v1680704510/fashionstore/79610747_010_b_esc3jq.webp',5),(18,'https://res.cloudinary.com/drlroexjl/image/upload/v1680704516/fashionstore/79610747_010_d_ohmgbh.webp',5),(19,'https://res.cloudinary.com/drlroexjl/image/upload/v1680704522/fashionstore/79610747_010_e_tv0vvf.webp',5),(20,'https://res.cloudinary.com/drlroexjl/image/upload/v1680704526/fashionstore/79610747_010_f_nwkwer.webp',5),(21,'https://res.cloudinary.com/drlroexjl/image/upload/v1680704641/fashionstore/79453056_010_b_cycctq.webp',6),(22,'https://res.cloudinary.com/drlroexjl/image/upload/v1680704647/fashionstore/79453056_010_d_l8dicf.webp',6),(23,'https://res.cloudinary.com/drlroexjl/image/upload/v1680704652/fashionstore/79453056_010_e_xegezi.webp',6),(24,'https://res.cloudinary.com/drlroexjl/image/upload/v1680704656/fashionstore/79453056_010_f_zzsaxx.webp',6),(25,'https://res.cloudinary.com/drlroexjl/image/upload/v1680704802/fashionstore/79474201_001_b_f13p3l.webp',7),(26,'https://res.cloudinary.com/drlroexjl/image/upload/v1680704808/fashionstore/79474201_001_d_sezl3e.webp',7),(27,'https://res.cloudinary.com/drlroexjl/image/upload/v1680704814/fashionstore/79474201_001_e_sotiwx.webp',7),(28,'https://res.cloudinary.com/drlroexjl/image/upload/v1680704817/fashionstore/79474201_001_f_hchxks.webp',7),(29,'https://res.cloudinary.com/drlroexjl/image/upload/v1680704920/79474672_004_b_ywcas1.webp',8),(30,'https://res.cloudinary.com/drlroexjl/image/upload/v1680704926/79474672_004_d_easojx.webp',8),(31,'https://res.cloudinary.com/drlroexjl/image/upload/v1680704939/79474672_004_e_yurwxm.webp',8),(32,'https://res.cloudinary.com/drlroexjl/image/upload/v1680704942/79474672_004_f_iqy2xo.webp',8),(33,'https://res.cloudinary.com/drlroexjl/image/upload/v1680705026/83140749_031_b_mxvrov.webp',9),(34,'https://res.cloudinary.com/drlroexjl/image/upload/v1680705034/83140749_031_d_rjphfh.webp',9),(35,'https://res.cloudinary.com/drlroexjl/image/upload/v1680705042/83140749_031_e_g845ud.webp',9),(36,'https://res.cloudinary.com/drlroexjl/image/upload/v1680705138/83140749_031_g_cxujlf.webp',9),(37,'https://res.cloudinary.com/drlroexjl/image/upload/v1680705226/81041956_011_d_jl3ip6.webp',10),(38,'https://res.cloudinary.com/drlroexjl/image/upload/v1680705234/81041956_011_b_pvyryr.webp',10),(39,'https://res.cloudinary.com/drlroexjl/image/upload/v1680705240/81041956_011_e_d93qy6.webp',10),(40,'https://res.cloudinary.com/drlroexjl/image/upload/v1680705247/81041956_011_f_vgihia.webp',10),(41,'https://res.cloudinary.com/drlroexjl/image/upload/v1680705416/68413962_012_b_ue4qnn.webp',11),(42,'https://res.cloudinary.com/drlroexjl/image/upload/v1680705421/68413962_012_d_rgb4qb.webp',11),(43,'https://res.cloudinary.com/drlroexjl/image/upload/v1680705427/68413962_012_e_u0jhkk.webp',11),(44,'https://res.cloudinary.com/drlroexjl/image/upload/v1680705434/68413962_012_f_y4ziya.webp',11),(45,'https://res.cloudinary.com/drlroexjl/image/upload/v1680705510/80993843_106_b_cmnnw6.webp',12),(46,'https://res.cloudinary.com/drlroexjl/image/upload/v1680705521/80993843_106_d_elcq9r.webp',12),(47,'https://res.cloudinary.com/drlroexjl/image/upload/v1680705525/80993843_106_e_grmgyq.webp',12),(48,'https://res.cloudinary.com/drlroexjl/image/upload/v1680705530/80993843_106_f_edahmo.webp',12),(49,'https://res.cloudinary.com/drlroexjl/image/upload/v1680705615/79091062_107_b_jfjkmf.webp',13),(50,'https://res.cloudinary.com/drlroexjl/image/upload/v1680705621/79091062_107_d_fnol7q.webp',13),(51,'https://res.cloudinary.com/drlroexjl/image/upload/v1680705627/79091062_107_e_uw4yt8.webp',13),(52,'https://res.cloudinary.com/drlroexjl/image/upload/v1680705632/79091062_107_f_primbh.webp',13),(53,'https://res.cloudinary.com/drlroexjl/image/upload/v1680705818/80865280_001_b_kfan9h.webp',14),(54,'https://res.cloudinary.com/drlroexjl/image/upload/v1680705823/80865280_001_d_p83k8h.webp',14),(55,'https://res.cloudinary.com/drlroexjl/image/upload/v1680705829/80865280_001_e_jqyrc2.webp',14),(56,'https://res.cloudinary.com/drlroexjl/image/upload/v1680705835/80865280_001_f_zgynak.webp',14),(57,'https://res.cloudinary.com/drlroexjl/image/upload/v1680705958/79978565_001_b_q0jo3j.webp',15),(58,'https://res.cloudinary.com/drlroexjl/image/upload/v1680705964/79978565_001_d_tzrcon.webp',15),(59,'https://res.cloudinary.com/drlroexjl/image/upload/v1680705971/79978565_001_e_zuf9ht.webp',15),(60,'https://res.cloudinary.com/drlroexjl/image/upload/v1680705978/79978565_001_g_d1hz0w.webp',15),(61,'https://res.cloudinary.com/drlroexjl/image/upload/v1680706090/79727277_054_b_upqujt.webp',16),(62,'https://res.cloudinary.com/drlroexjl/image/upload/v1680706096/79727277_054_d_amkjdy.webp',16),(63,'https://res.cloudinary.com/drlroexjl/image/upload/v1680706101/79727277_054_e_czd1ge.webp',16),(64,'https://res.cloudinary.com/drlroexjl/image/upload/v1680706105/79727277_054_f_rakfhd.webp',16),(65,'https://res.cloudinary.com/drlroexjl/image/upload/v1680706203/81419822_001_b_hvgj54.webp',17),(66,'https://res.cloudinary.com/drlroexjl/image/upload/v1680706209/81419822_001_d_sdpv19.webp',17),(67,'https://res.cloudinary.com/drlroexjl/image/upload/v1680706214/81419822_001_e_tzlnuo.webp',17),(68,'https://res.cloudinary.com/drlroexjl/image/upload/v1680706218/81419822_001_f_bcd9zn.webp',17),(69,'https://res.cloudinary.com/drlroexjl/image/upload/v1680706321/81844144_040_b_xsleug.webp',18),(70,'https://res.cloudinary.com/drlroexjl/image/upload/v1680706326/81844144_040_d_ldar6h.webp',18),(71,'https://res.cloudinary.com/drlroexjl/image/upload/v1680706331/81844144_040_e_ntz0h7.webp',18),(72,'https://res.cloudinary.com/drlroexjl/image/upload/v1680706335/81844144_040_f_dtc2hv.webp',18),(73,'https://res.cloudinary.com/drlroexjl/image/upload/v1680706565/76130350_083_b_gqomij.webp',19),(74,'https://res.cloudinary.com/drlroexjl/image/upload/v1680706570/76130350_083_d_wowz5h.webp',19),(75,'https://res.cloudinary.com/drlroexjl/image/upload/v1680706575/76130350_083_e_lb3edt.webp',19),(76,'https://res.cloudinary.com/drlroexjl/image/upload/v1680706581/76130350_083_f_qhdckk.webp',19),(77,'https://res.cloudinary.com/drlroexjl/image/upload/v1680706874/fashionstore/66047960_432_b_g3rxzr.webp',20),(78,'https://res.cloudinary.com/drlroexjl/image/upload/v1680706884/fashionstore/66047960_432_d_mkqasb.webp',20),(79,'https://res.cloudinary.com/drlroexjl/image/upload/v1680706889/fashionstore/66047960_432_e_pzhseu.webp',20),(80,'https://res.cloudinary.com/drlroexjl/image/upload/v1680706899/fashionstore/66047960_432_f_ydq27g.webp',20),(81,'https://res.cloudinary.com/drlroexjl/image/upload/v1680707303/fashionstore/78786068_001_b_sliqhq.webp',21),(82,'https://res.cloudinary.com/drlroexjl/image/upload/v1680707310/fashionstore/78786068_001_d_wnv3lw.webp',21),(83,'https://res.cloudinary.com/drlroexjl/image/upload/v1680707320/fashionstore/78786068_001_e_sayrtp.webp',21),(84,'https://res.cloudinary.com/drlroexjl/image/upload/v1680707324/fashionstore/78786068_001_f_hkp4o4.webp',21),(85,'https://res.cloudinary.com/drlroexjl/image/upload/v1680707382/fashionstore/53378626_001_b_x9wdwg.webp',22),(86,'https://res.cloudinary.com/drlroexjl/image/upload/v1680707389/fashionstore/53378626_001_d_tbrufa.webp',22),(87,'https://res.cloudinary.com/drlroexjl/image/upload/v1680707395/fashionstore/53378626_001_e_r0zdzt.webp',22),(88,'https://res.cloudinary.com/drlroexjl/image/upload/v1680707401/fashionstore/53378626_001_g_l8rr9l.webp',22),(89,'https://res.cloudinary.com/drlroexjl/image/upload/v1680707460/fashionstore/78890258_013_b_dgt665.webp',23),(90,'https://res.cloudinary.com/drlroexjl/image/upload/v1680707464/fashionstore/78890258_013_d_ib7lri.webp',23),(91,'https://res.cloudinary.com/drlroexjl/image/upload/v1680707469/fashionstore/78890258_013_e_szsryo.webp',23),(92,'https://res.cloudinary.com/drlroexjl/image/upload/v1680707473/fashionstore/78890258_013_f_ixtwjw.webp',23),(93,'https://res.cloudinary.com/drlroexjl/image/upload/v1680707528/fashionstore/79287058_039_b_d1c61y.webp',24),(94,'https://res.cloudinary.com/drlroexjl/image/upload/v1680707537/fashionstore/79287058_039_d_k9ydkn.webp',24),(95,'https://res.cloudinary.com/drlroexjl/image/upload/v1680707541/fashionstore/79287058_039_e_ggcmf6.webp',24),(96,'https://res.cloudinary.com/drlroexjl/image/upload/v1680707546/fashionstore/79287058_039_f_oobnzi.webp',24),(97,'https://res.cloudinary.com/drlroexjl/image/upload/v1680707604/fashionstore/78590411_004_b_f9ciow.webp',25),(98,'https://res.cloudinary.com/drlroexjl/image/upload/v1680707609/fashionstore/78590411_004_d_f2ujnh.webp',25),(99,'https://res.cloudinary.com/drlroexjl/image/upload/v1680707615/fashionstore/78590411_004_e_spuzl3.webp',25),(100,'https://res.cloudinary.com/drlroexjl/image/upload/v1680707620/fashionstore/78590411_004_f_hxjfyn.webp',25),(101,'https://res.cloudinary.com/drlroexjl/image/upload/v1680707713/fashionstore/79139705_029_b_zbieok.webp',26),(102,'https://res.cloudinary.com/drlroexjl/image/upload/v1680707719/fashionstore/79139705_029_d_pzxftg.webp',26),(103,'https://res.cloudinary.com/drlroexjl/image/upload/v1680707724/fashionstore/79139705_029_e_yvhjq3.webp',26),(104,'https://res.cloudinary.com/drlroexjl/image/upload/v1680707727/fashionstore/79139705_029_f_vmtphw.webp',26),(105,'https://res.cloudinary.com/drlroexjl/image/upload/v1680707823/fashionstore/79489613_037_b_iq0gr0.webp',27),(106,'https://res.cloudinary.com/drlroexjl/image/upload/v1680707829/fashionstore/79489613_037_d_cldhsd.webp',27),(107,'https://res.cloudinary.com/drlroexjl/image/upload/v1680707834/fashionstore/79489613_037_e_cl1jno.webp',27),(108,'https://res.cloudinary.com/drlroexjl/image/upload/v1680707839/fashionstore/79489613_037_f_ifty61.webp',27),(109,'https://res.cloudinary.com/drlroexjl/image/upload/v1680708007/fashionstore/68333103_021_e_pmogdy.webp',28),(110,'https://res.cloudinary.com/drlroexjl/image/upload/v1680708011/fashionstore/68333103_021_b_warqfb.webp',28),(111,'https://res.cloudinary.com/drlroexjl/image/upload/v1680708019/fashionstore/68333103_021_d_orpkml.webp',28),(112,'https://res.cloudinary.com/drlroexjl/image/upload/v1680708026/fashionstore/68333103_021_f_r9vn9f.webp',28),(113,'https://res.cloudinary.com/drlroexjl/image/upload/v1680708149/fashionstore/64185531_037_b_hehczh.webp',29),(114,'https://res.cloudinary.com/drlroexjl/image/upload/v1680708160/fashionstore/64185531_037_d_vagkyo.webp',29),(115,'https://res.cloudinary.com/drlroexjl/image/upload/v1680708165/fashionstore/64185531_037_e_ot4ksk.webp',29),(116,'https://res.cloudinary.com/drlroexjl/image/upload/v1680708171/fashionstore/64185531_037_f_lnaqqk.webp',29),(117,'https://res.cloudinary.com/drlroexjl/image/upload/v1680708377/fashionstore/58422767_074_b_imq0ku.webp',30),(118,'https://res.cloudinary.com/drlroexjl/image/upload/v1680708384/fashionstore/58422767_074_d_ie7hik.webp',30),(119,'https://res.cloudinary.com/drlroexjl/image/upload/v1680708393/fashionstore/58422767_074_e_gil6g6.webp',30),(120,'https://res.cloudinary.com/drlroexjl/image/upload/v1680708500/fashionstore/78617487_001_b_hezfvd.webp',31),(121,'https://res.cloudinary.com/drlroexjl/image/upload/v1680708505/fashionstore/78617487_001_d_ajy3kw.webp',31),(122,'https://res.cloudinary.com/drlroexjl/image/upload/v1680708510/fashionstore/78617487_001_e_xaf7ui.webp',31),(123,'https://res.cloudinary.com/drlroexjl/image/upload/v1680708603/fashionstore/80203110_050_b_geyiou.webp',32),(124,'https://res.cloudinary.com/drlroexjl/image/upload/v1680708610/fashionstore/80203110_050_b_lvvmrc.webp',32),(125,'https://res.cloudinary.com/drlroexjl/image/upload/v1680708616/fashionstore/80203110_050_e_fyvng4.webp',32),(126,'https://res.cloudinary.com/drlroexjl/image/upload/v1680708622/fashionstore/80203110_050_f_lzedqm.webp',32),(127,'https://res.cloudinary.com/drlroexjl/image/upload/v1680708708/fashionstore/78626413_016_b_fmcrrm.webp',33),(128,'https://res.cloudinary.com/drlroexjl/image/upload/v1680708714/fashionstore/78626413_016_d_mkyjxw.webp',33),(129,'https://res.cloudinary.com/drlroexjl/image/upload/v1680708724/fashionstore/78626413_016_e_ozgw0x.webp',33),(130,'https://res.cloudinary.com/drlroexjl/image/upload/v1680708731/fashionstore/78626413_016_f_qbv8fu.webp',33),(131,'https://res.cloudinary.com/drlroexjl/image/upload/v1680708880/fashionstore/80152333_080_b_pflgch.webp',34),(132,'https://res.cloudinary.com/drlroexjl/image/upload/v1680708890/fashionstore/80152333_080_d_uetkxn.webp',34),(133,'https://res.cloudinary.com/drlroexjl/image/upload/v1680708895/fashionstore/80152333_080_e_xzhr1r.webp',34),(134,'https://res.cloudinary.com/drlroexjl/image/upload/v1680708901/fashionstore/80152333_080_f_lr063s.webp',34),(135,'https://res.cloudinary.com/drlroexjl/image/upload/v1680708969/fashionstore/80147598_095_b_kkhcax.webp',35),(136,'https://res.cloudinary.com/drlroexjl/image/upload/v1680708973/fashionstore/80147598_095_d_tm7o71.webp',35),(137,'https://res.cloudinary.com/drlroexjl/image/upload/v1680708980/fashionstore/80147598_095_e_gsy5d5.webp',35),(138,'https://res.cloudinary.com/drlroexjl/image/upload/v1680708982/fashionstore/80147598_095_f_mzhf9p.webp',35),(139,'https://res.cloudinary.com/drlroexjl/image/upload/v1680709071/fashionstore/80823636_041_b_ba7rde.webp',36),(140,'https://res.cloudinary.com/drlroexjl/image/upload/v1680709076/fashionstore/80823636_041_d_n4psfu.webp',36),(141,'https://res.cloudinary.com/drlroexjl/image/upload/v1680709081/fashionstore/80823636_041_e_mwi6ms.webp',36),(142,'https://res.cloudinary.com/drlroexjl/image/upload/v1680709086/fashionstore/80823636_041_f_p6b1b1.webp',36),(143,'https://res.cloudinary.com/drlroexjl/image/upload/v1680709168/fashionstore/79491718_045_b_fhckrw.webp',37),(144,'https://res.cloudinary.com/drlroexjl/image/upload/v1680709173/fashionstore/79491718_045_d_z3kxl3.webp',37),(145,'https://res.cloudinary.com/drlroexjl/image/upload/v1680709200/fashionstore/79491718_045_e_gigbi3.webp',37),(146,'https://res.cloudinary.com/drlroexjl/image/upload/v1680709183/fashionstore/79491718_045_f_ta7qre.webp',37),(147,'https://res.cloudinary.com/drlroexjl/image/upload/v1680709342/fashionstore/82745787_070_b_vjtdxl.webp',38),(148,'https://res.cloudinary.com/drlroexjl/image/upload/v1680709347/fashionstore/82745787_070_d_odtshr.webp',38),(149,'https://res.cloudinary.com/drlroexjl/image/upload/v1680709355/fashionstore/82745787_070_e_mfcern.webp',38),(150,'https://res.cloudinary.com/drlroexjl/image/upload/v1680709419/fashionstore/81017352_007_b_ymnqnw.webp',39),(151,'https://res.cloudinary.com/drlroexjl/image/upload/v1680709425/fashionstore/81017352_007_d_mxj6jm.webp',39),(152,'https://res.cloudinary.com/drlroexjl/image/upload/v1680709430/fashionstore/81017352_007_e_vysu4t.webp',39),(153,'https://res.cloudinary.com/drlroexjl/image/upload/v1680709435/fashionstore/81017352_007_f_nndhdo.webp',39),(154,'https://res.cloudinary.com/drlroexjl/image/upload/v1680709484/fashionstore/83031443_001_b_putrwt.webp',40),(155,'https://res.cloudinary.com/drlroexjl/image/upload/v1680709489/fashionstore/83031443_001_d_fmuoo3.webp',40),(156,'https://res.cloudinary.com/drlroexjl/image/upload/v1680709494/fashionstore/83031443_001_e_fj1pdc.webp',40),(157,'https://res.cloudinary.com/drlroexjl/image/upload/v1680709500/fashionstore/83031443_001_e_lnz9kk.webp',40),(158,'https://res.cloudinary.com/drlroexjl/image/upload/v1680709509/fashionstore/83031443_001_f_oiqkjd.jpg',40),(159,'https://res.cloudinary.com/drlroexjl/image/upload/v1680709607/fashionstore/83031484_027_b_at1eyd.webp',41),(160,'https://res.cloudinary.com/drlroexjl/image/upload/v1680709612/fashionstore/83031484_027_d_kasr8v.webp',41),(161,'https://res.cloudinary.com/drlroexjl/image/upload/v1680709623/fashionstore/83031484_027_e_tvr9fi.webp',41),(162,'https://res.cloudinary.com/drlroexjl/image/upload/v1680709629/fashionstore/83031484_027_f_jhyktd.webp',41),(163,'https://res.cloudinary.com/drlroexjl/image/upload/v1680709695/fashionstore/82875949_270_b_gonebt.webp',42),(164,'https://res.cloudinary.com/drlroexjl/image/upload/v1680709699/fashionstore/82875949_270_d_zbrm0x.webp',42),(165,'https://res.cloudinary.com/drlroexjl/image/upload/v1680709705/fashionstore/82875949_270_e_hfhmzr.webp',42),(166,'https://res.cloudinary.com/drlroexjl/image/upload/v1680709787/fashionstore/82876129_001_b_p91nw4.webp',43),(167,'https://res.cloudinary.com/drlroexjl/image/upload/v1680709793/fashionstore/82876129_001_d_rdddqt.webp',43),(168,'https://res.cloudinary.com/drlroexjl/image/upload/v1680709797/fashionstore/82876129_001_e_j2vkic.webp',43),(169,'https://res.cloudinary.com/drlroexjl/image/upload/v1680709807/fashionstore/82876129_001_f_g6z3cc.webp',43),(170,'https://res.cloudinary.com/drlroexjl/image/upload/v1680709891/fashionstore/82568999_012_b_n8fg8z.webp',44),(171,'https://res.cloudinary.com/drlroexjl/image/upload/v1680709900/fashionstore/82568999_012_d_iqqob5.webp',44),(172,'https://res.cloudinary.com/drlroexjl/image/upload/v1680709909/fashionstore/82568999_012_e_zb0c5y.webp',44),(173,'https://res.cloudinary.com/drlroexjl/image/upload/v1680709913/fashionstore/82568999_012_f_ui5bzn.webp',44),(174,'https://res.cloudinary.com/drlroexjl/image/upload/v1680709971/fashionstore/82930504_060_b_mfox1w.webp',45),(175,'https://res.cloudinary.com/drlroexjl/image/upload/v1680709985/fashionstore/82930504_060_d_gb4kd3.webp',45),(176,'https://res.cloudinary.com/drlroexjl/image/upload/v1680709991/fashionstore/82930504_060_e_z8t1rl.webp',45),(177,'https://res.cloudinary.com/drlroexjl/image/upload/v1680710043/fashionstore/81186041_086_b_t9wkhe.webp',46),(178,'https://res.cloudinary.com/drlroexjl/image/upload/v1680710048/fashionstore/81186041_086_d_tsxufl.webp',46),(179,'https://res.cloudinary.com/drlroexjl/image/upload/v1680710052/fashionstore/81186041_086_e_mubla0.webp',46),(180,'https://res.cloudinary.com/drlroexjl/image/upload/v1680710058/fashionstore/81186041_086_f_imdkd1.webp',46),(185,'http://res.cloudinary.com/drlroexjl/image/upload/v1696145107/eijkdu8lvgmqyauzxny5.jpg',62);
/*!40000 ALTER TABLE `product_image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` varchar(255) NOT NULL,
  `avatar` varchar(1111) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `email` varchar(1111) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `login_type` varchar(1111) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `password` varchar(1111) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `phone_number` varchar(1111) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `role` varchar(1111) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `user_name` varchar(1111) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('admin','https://haycafe.vn/wp-content/uploads/2022/02/Avatar-trang-den.png','khanhvanchi69@gmail.com','default','MQ==','0932456789','admin','Văn Chí Khanh'),('khanhvc','https://haycafe.vn/wp-content/uploads/2022/02/Avatar-trang-den.png','khanhvanchi69@gmail.com','default','MQ==','0931876293','user','Văn Chí Khanh'),('quanghuy','https://haycafe.vn/wp-content/uploads/2022/02/Avatar-trang-den.png','voquanghuy08102000@gmail.com','default','MTIzNDU2',NULL,'user','Võ Quang Huy');
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

-- Dump completed on 2023-10-21 15:13:27
