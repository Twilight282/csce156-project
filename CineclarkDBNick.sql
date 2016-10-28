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

DROP TABLE IF EXISTS `Products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Products` (
	`ProductID` int(11) NOT NULL AUTO_INCREMENT,
	`TypeID` int(11) NOT NULL,
	PRIMARY KEY (`ProductID`,`TypeID`),
	FOREIGN KEY (`TypeID`) REFERENCES TypeNames(`TypeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

/*!40000 ALTER TABLE `Products` DISABLE KEYS */;
INSERT INTO `Products` VALUES (0,2),(1,4),(2,1),(3,3),(4,1),(5,1),(6,3),(7,4),(8,2),(9,3),(10,2),(11,4);
/*!40000 ALTER TABLE `Products` ENABLE KEYS */;

DROP TABLE IF EXISTS `TypeNames`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TypeNames` (
	`TypeID` int(11) NOT NULL AUTO_INCREMENT,
	`TypeName` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
	PRIMARY KEY (`TypeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

/*!40000 ALTER TABLE `TypeNames` DISABLE KEYS */;
INSERT INTO `TypeNames` VALUES (1,'Movie Tickets'),(2,'Season Pass'),(3,'Parking Pass'),(4,'Refreshment');
/*!40000 ALTER TABLE `TypeNames` ENABLE KEYS */;

DROP TABLE IF EXISTS `Refreshments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Refreshments` (
	`ProductID` int(11) NOT NULL,
	`RefreshmentName` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
	`Price` decimal(5,2) Not Null Default '0',
	PRIMARY KEY (`ProductID`),
	FOREIGN KEY (`ProductID`) REFERENCES Products(`ProductID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

/*!40000 ALTER TABLE `Refreshments` DISABLE KEYS */;
INSERT INTO `Refreshments` VALUES (1,1.20,'Large Potato'),(7,4.13,'Apple Juice'),(11,6.12,'Pumpkin Pie');
/*!40000 ALTER TABLE `Refreshments` ENABLE KEYS */;

DROP TABLE IF EXISTS `MovieTickets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MovieTickets` (
	`ProductID` int(11) NOT NULL,
	`MovieName` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
	`Price` decimal(5,2) Not Null,
	`Date` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
    `AddressID` int(60) NOT NULL,
    `ScreenNumber` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
	PRIMARY KEY (`ProductID`),
	FOREIGN KEY (`ProductID`) REFERENCES Products(`ProductID`),
    FOREIGN KEY (`AddressID`) REFERENCES Addresses(`AddressID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

/*!40000 ALTER TABLE `MovieTickets` DISABLE KEYS */;
INSERT INTO `MovieTickets` VALUES (2,'Con Air',10.25,'2010-04-13 4:02',3,'3D'),(4,'Generic Movie',11.1,'2012-06-12 1:10',1,'ee'),(5,'That Film Guy',22.8,'2007-01-28',2,'10');
/*!40000 ALTER TABLE `MovieTickets` ENABLE KEYS */;

DROP TABLE IF EXISTS `SeasonPasses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SeasonPasses` (
	`ProductID` int(11) NOT NULL,
	`PassName` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
	`Price` decimal(5,2) Not Null Default '0',
	`StartDate` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
	`EndDate` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
	PRIMARY KEY (`ProductID`),
	FOREIGN KEY (`ProductID`) REFERENCES Products(`ProductID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

/*!40000 ALTER TABLE `SeasonPasses` DISABLE KEYS */;
INSERT INTO `SeasonPasses` VALUES (0,11.2,'zzygzyygy','2014-03-02','2015-04-03'),(8,23.2,'adsfk','2013-02-24','2013-05-24'),(10,42.8,'slfk','2015-06-12','2016-10-25');
/*!40000 ALTER TABLE `SeasonPasses` ENABLE KEYS */;

DROP TABLE IF EXISTS `ParkingPasses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ParkingPasses` (
	`ProductID` int(11) NOT NULL,
	`Price` decimal(5,2) Not Null Default '0',
	PRIMARY KEY (`ProductID`),
	FOREIGN KEY (`ProductID`) REFERENCES Products(`ProductID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

/*!40000 ALTER TABLE `ParkingPasses` DISABLE KEYS */;
INSERT INTO `ParkingPasses` VALUES (3,1.11),(6,2.22),(9,3.43);
/*!40000 ALTER TABLE `ParkingPasses` ENABLE KEYS */;

DROP TABLE IF EXISTS `Addresses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Addresses` (
	`AddressID` int(11) NOT NULL AUTO_INCREMENT,
	`Street` varchar(60) COLLATE utf8_unicode_ci NOT NULL,
	`City` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
	`State` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
	`Zip` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
	`Country` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
	PRIMARY KEY (`AddressID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

/*!40000 ALTER TABLE `Addresses` DISABLE KEYS */;
INSERT INTO `Addresses` VALUES (1, '7320 N. 17th', 'Lincoln', 'NE', '68521','USA'),(2, '3431 Webster St.', 'Omaha', 'NE', '68131','USA'),(3, '872 Lakeshore Dr.', 'Lincoln', 'NE', '68528','USA');
/*!40000 ALTER TABLE `Addresses` ENABLE KEYS */;

DROP TABLE IF EXISTS `InvoiceProducts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `InvoiceProducts` (
	`ProductID` int(11) NOT NULL,
    `InvoiceID` int(11) NOT NULL,
    `Quantity` int(11) NOT NULL DEFAULT '1',
    FOREIGN KEY (`ProductID`) REFERENCES Products(`ProductID`),
    FOREIGN KEY (`InvoiceID`) REFERENCES Invoices(`InvoiceID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

/*!40000 ALTER TABLE `InvoiceProducts` DISABLE KEYS */;
INSERT INTO `InvoiceProducts` VALUES (0,0,1),(4,0,2),(10,0,1),(6,1,3),(5,1,2),(8,2,5),(4,2,1),(6,2,2),(9,3,4),(2,3,2);
/*!40000 ALTER TABLE `InvoiceProducts` ENABLE KEYS */;

DROP TABLE IF EXISTS `Invoices`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Invoices` (
	`InvoiceID` int(11) NOT NULL AUTO_INCREMENT,
	`CustomerID` int(11) NOT NULL,
    `PersonID` int(11) NOT NULL,
	`Date` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
	PRIMARY KEY (`InvoiceID`),
    FOREIGN KEY (`CustomerID`) REFERENCES Customers(`CustomerID`),
    FOREIGN KEY (`PersonID`) REFERENCES Persons(`PersonID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

/*!40000 ALTER TABLE `Invoices` DISABLE KEYS */;
INSERT INTO `Invoices` VALUES (0,1,8765,'10/27/2016'),(1,2,4321,'10/25/2016'),(2,3,7531,'10/10/2016'),(3,4,8642,'11/1/2016');
/*!40000 ALTER TABLE `Invoices` ENABLE KEYS */;

DROP TABLE IF EXISTS `Customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Customers` (
	`CustomerID` int(11) NOT NULL AUTO_INCREMENT,
	`CustomerName` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
	`ContactID` int(11) NOT NULL,
    `CustomerType` int(11) NOT NULL,
	`AddressID` int(11) NOT NULL,
	PRIMARY KEY (`CustomerID`),
    FOREIGN KEY (`ContactID`) REFERENCES Persons(`PersonID`),
    FOREIGN KEY (`AddressID`) REFERENCES Addresses(`AddressID`),
    FOREIGN KEY (`CustomerType`) REFERENCES CustomerTypes(`CustomerType`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

/*!40000 ALTER TABLE `Customers` DISABLE KEYS */;
INSERT INTO `Customers` VALUES (1,'John Doe',4321,1,1),(2,'Mary Sue',8765,2,2),(3,'Bill Shankly',8642,1,3),(4,'Steven Gerrard',7531,1,1);
/*!40000 ALTER TABLE `Customers` ENABLE KEYS */;

DROP TABLE IF EXISTS `CustomerTypes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CustomerTypes` (
	`CustomerType` int(11) NOT NULL AUTO_INCREMENT,
	`TypeName` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
	PRIMARY KEY (`CustomerType`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

/*!40000 ALTER TABLE `CustomerTypes` DISABLE KEYS */;
INSERT INTO `CustomerTypes` VALUES (1,'General'),(2,'Student');
/*!40000 ALTER TABLE `CustomerTypes` ENABLE KEYS */;

DROP TABLE IF EXISTS `Persons`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Persons` (
	`PersonID` int(11) NOT NULL AUTO_INCREMENT,
	`PersonName` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
	`AddressID` int(11) NOT NULL,
	PRIMARY KEY (`PersonID`),
    FOREIGN KEY (`AddressID`) REFERENCES Addresses(`AddressID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

/*!40000 ALTER TABLE `Persons` DISABLE KEYS */;
INSERT INTO `Persons` VALUES (4321,'Lionel Messi', 1),(8765,'Coutinho',2),(8642,'Firmino',3),(7531,'Joel Matip',4);
/*!40000 ALTER TABLE `Persons` ENABLE KEYS */;


DROP TABLE IF EXISTS `Emails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Emails` (
	`PersonID` int(11) NOT NULL,
	`Email` varchar(60) COLLATE utf8_unicode_ci NOT NULL,
    FOREIGN KEY (`PersonID`) REFERENCES Persons(`PersonID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

/*!40000 ALTER TABLE `Emails` DISABLE KEYS */;
INSERT INTO `Emails` VALUES (4321,'blah@blah.com'),(8765,'evenmoreblah@yahoo.com'),(8642,'thisisblah@foogle.com'),(7531,'theblahest@aol.com');
/*!40000 ALTER TABLE `Emails` ENABLE KEYS */;
