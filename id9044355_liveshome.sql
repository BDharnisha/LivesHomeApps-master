-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Jun 05, 2019 at 05:40 AM
-- Server version: 10.3.14-MariaDB
-- PHP Version: 7.3.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `id9044355_liveshome`
--

-- --------------------------------------------------------

--
-- Table structure for table `CART`
--

CREATE TABLE `CART` (
  `DONATION_ITEM_ID` varchar(20) CHARACTER SET latin1 NOT NULL,
  `USER_ID` varchar(40) CHARACTER SET latin1 NOT NULL,
  `QUANTITY` varchar(20) CHARACTER SET latin1 NOT NULL,
  `ITEM_PRICE` varchar(10) CHARACTER SET latin1 NOT NULL,
  `ITEM_NAME` varchar(40) CHARACTER SET latin1 NOT NULL,
  `ITEM_CONDITION` varchar(30) CHARACTER SET latin1 NOT NULL,
  `STATUS` varchar(40) CHARACTER SET latin1 NOT NULL,
  `DONATION_ID` varchar(20) CHARACTER SET latin1 NOT NULL,
  `ORDERID` varchar(20) CHARACTER SET latin1 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=COMPACT;

--
-- Dumping data for table `CART`
--

INSERT INTO `CART` (`DONATION_ITEM_ID`, `USER_ID`, `QUANTITY`, `ITEM_PRICE`, `ITEM_NAME`, `ITEM_CONDITION`, `STATUS`, `DONATION_ID`, `ORDERID`) VALUES
('1', '0124457316', '1', '5.00', 'Harry Porter Novel', 'Used', 'not paid', '1', '01062019-efVIfjI'),
('1', '0124457316', '1', '5.00', 'Harry Porter Novel', 'Used', 'not paid', '1', '01062019-tXkYlJL'),
('2', '0124457316', '2', '1.00', 'Biology Books', 'Used', 'not paid', '2', '01062019-wqq7Qen'),
('3', '0124457316', '2', '5.00', 'Formal Shirts', 'New', 'not paid', '3', '01062019-Z1v4c4T'),
('5', '0124457316', '1', '12.00', 'Kids Gown', 'New', 'not paid', '4', '01062019-o98kwtD'),
('2', '0124457316', '1', '1.00', 'Biology Books', 'Used', 'not paid', '2', '01062019-o98kwtD'),
('3', '0124457316', '1', '5.00', 'Formal Shirts', 'New', 'not paid', '3', '01062019-o98kwtD'),
('1', '0124457316', '1', '5.00', 'Harry Porter Novel', 'Used', 'not paid', '1', '01062019-o98kwtD'),
('1', '0124457316', '1', '5.00', 'Harry Porter Novel', 'Used', 'not paid', '1', '01062019-o98kwtD'),
('3', '0124457316', '1', '5.00', 'Formal Shirts', 'New', 'not paid', '3', '01062019-o98kwtD'),
('1', '0124457316', '1', '5.00', 'Harry Porter Novel', 'Used', 'not paid', '1', '01062019-o98kwtD'),
('10', '0124457316', '1', '50.00', 'Samsung Fridge', 'Used', 'not paid', '9', '01062019-o98kwtD'),
('1', '0124457316', '1', '5.00', 'Harry Porter Novel', 'Used', 'not paid', '1', '01062019-o98kwtD'),
('1', '0124457316', '1', '5.00', 'Harry Porter Novel', 'Used', 'not paid', '1', '01062019-o98kwtD'),
('1', '0124457316', '1', '5.00', 'Harry Porter Novel', 'Used', 'not paid', '1', '01062019-o98kwtD'),
('1', '1', '1', '5.00', 'Harry Porter Novel', 'Used', 'not paid', '1', '04062019-rvGmhC0'),
('2', '0124573456', '1', '1.00', 'Biology Books', 'Used', 'not paid', '2', '04062019-rNBfERP'),
('3', '0124457316', '1', '5.00', 'Formal Shirts', 'New', 'not paid', '3', '01062019-o98kwtD'),
('3', '0124457316', '1', '5.00', 'Formal Shirts', 'New', 'not paid', '3', '01062019-o98kwtD'),
('1', '0124457316', '1', '5.00', 'Harry Porter Novel', 'Used', 'not paid', '1', '01062019-o98kwtD'),
('3', '0124457316', '1', '5.00', 'Formal Shirts', 'New', 'not paid', '3', '01062019-o98kwtD'),
('1', '01245458748', '1', '5.00', 'Harry Porter Novel', 'Used', 'not paid', '1', '04062019-rn666e9'),
('1', '0124457316', '1', '5.00', 'Harry Porter Novel', 'Used', 'not paid', '1', '01062019-o98kwtD');

-- --------------------------------------------------------

--
-- Table structure for table `DONATION`
--

CREATE TABLE `DONATION` (
  `DONATION_ID` int(10) NOT NULL,
  `DONATION_NAME` varchar(100) NOT NULL,
  `DONOR_NAME` varchar(50) NOT NULL,
  `DONOR_PHONE_NO` varchar(20) NOT NULL,
  `DONOR_LOCATION` varchar(50) NOT NULL,
  `ITEM_CATEGORY` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `DONATION`
--

INSERT INTO `DONATION` (`DONATION_ID`, `DONATION_NAME`, `DONOR_NAME`, `DONOR_PHONE_NO`, `DONOR_LOCATION`, `ITEM_CATEGORY`) VALUES
(1, 'Novels', 'Rajput Parina', '0144656566', 'Changlun', 'Books'),
(2, 'Educational Books', 'Dharisha Karan', '0192323244', 'Sintok', 'Books'),
(3, 'Mens Wear', 'Kamilah Mukarn', '0115376854', 'Changlun', 'Clothes'),
(4, 'Kids Wear', 'Siti Aminah', '0149898982', 'Changlun', 'Clothes'),
(5, 'Traditional Wear', 'Haritha Shankar', '0123434353', 'Sintok', 'Clothes'),
(6, 'Sport Shoes', 'Arun Tarun', '0184542223', 'Changlun', 'Shoes'),
(7, 'Colour Pencils', 'Siti Savinah', '0165732269', 'Sintok', 'Stationery'),
(8, 'Cabinet', 'Siti Kasinah', '0117776678', 'Changlun', 'Furnitures'),
(9, 'Refrigerator', 'Varun Tej', '0146097632', 'Changlun', 'Electrical Appliances');

-- --------------------------------------------------------

--
-- Table structure for table `DONATION_ITEMS`
--

CREATE TABLE `DONATION_ITEMS` (
  `DONATION_ITEM_ID` int(5) NOT NULL,
  `ITEM_NAME` varchar(50) CHARACTER SET latin1 NOT NULL,
  `ITEM_CONDITION` varchar(30) CHARACTER SET latin1 NOT NULL,
  `ITEM_PRICE` varchar(5) CHARACTER SET latin1 NOT NULL,
  `QUANTITY` varchar(50) CHARACTER SET latin1 NOT NULL,
  `DONATION_ID` varchar(10) CHARACTER SET latin1 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `DONATION_ITEMS`
--

INSERT INTO `DONATION_ITEMS` (`DONATION_ITEM_ID`, `ITEM_NAME`, `ITEM_CONDITION`, `ITEM_PRICE`, `QUANTITY`, `DONATION_ID`) VALUES
(1, 'Harry Porter Novel', 'Used', '5.00', '31', '1'),
(2, 'Biology Books', 'Used', '1.00', '29', '2'),
(3, 'Formal Shirts', 'New', '5.00', '61', '3'),
(4, 'Short Pants', 'Used', '4.00', '30', '3'),
(5, 'Kids Gown', 'New', '12.00', '23', '4'),
(6, 'Punjabi Suit', 'Used', '10.00', '43', '5'),
(7, 'Reebok Shoes', 'Used', '15.00', '10', '6'),
(8, 'Faber-Castell ', 'New', '1.50', '29', '7'),
(9, 'Storage Cabinet', 'Used', '20.00', '10', '8'),
(10, 'Samsung Fridge', 'Used', '50.00', '4', '9');

-- --------------------------------------------------------

--
-- Table structure for table `ORDERED`
--

CREATE TABLE `ORDERED` (
  `ORDERID` varchar(20) CHARACTER SET latin1 NOT NULL,
  `USERID` varchar(10) CHARACTER SET latin1 NOT NULL,
  `TOTAL` varchar(10) CHARACTER SET latin1 NOT NULL,
  `DATE` timestamp(6) NOT NULL DEFAULT current_timestamp(6)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `USER`
--

CREATE TABLE `USER` (
  `EMAIL` varchar(100) CHARACTER SET latin1 NOT NULL,
  `PASSWORD` varchar(60) CHARACTER SET latin1 NOT NULL,
  `PHONE` varchar(15) CHARACTER SET latin1 NOT NULL,
  `NAME` varchar(100) CHARACTER SET latin1 NOT NULL,
  `HOMENAME` varchar(100) CHARACTER SET latin1 NOT NULL,
  `LOCATION` varchar(30) CHARACTER SET latin1 NOT NULL,
  `LATITUDE` varchar(30) CHARACTER SET latin1 NOT NULL,
  `LONGITUDE` varchar(30) CHARACTER SET latin1 NOT NULL,
  `POSITION` varchar(200) CHARACTER SET latin1 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `USER`
--

INSERT INTO `USER` (`EMAIL`, `PASSWORD`, `PHONE`, `NAME`, `HOMENAME`, `LOCATION`, `LATITUDE`, `LONGITUDE`, `POSITION`) VALUES
('abdu@gmail.com', 'abdu', '0125645878', 'Abdullah bin Kamal', 'Agathians Home', 'Changlun', '6.447083008988462', '100.43602097779512', 'Home Assistant Manager'),
('amin21@gmail.com', 'amin123', '0124573456', 'Amin Abdullah', 'Aman Home', 'Changlun', '6.443224050268126', '100.42504772543909', 'Home Manager'),
('arun@yahoo.com', 'hayati', '0125685887', 'Arun Vijay', 'Aman Home', 'Changlun', '6.443224050268126', '100.42504772543909', 'Home Secretary'),
('cheenu@yahoo.com', 'cheee01', '0163434121', 'Cheenu Raj', 'Aman Home', 'Changlun', '6.443224050268126', '100.42504772543909', 'Home Assistant Manager'),
('elwinisha@gmail.com', 'saro', '0125555425', 'Saroka', 'Agathians Home', 'Changlun', '6.433105', '100.436619', 'Home Secretary'),
('nisha01raj@ymail.com', 'abc', '0123246576', 'Sakinah', 'Aman Home', 'Sintok', '6.443224050268126', '100.42504772543909', 'Home Manager'),
('raav@gmail.com', 'magio', '0146665666', 'Ravrin Prajin', 'Aman Home', 'Sintok', '6.443224050268126', '100.42504772543909', 'Home Manager'),
('seeta@gmail.com', 'seeta', '01245458748', 'Seeta Devi', 'Tia Childrens Home', 'Changlun', '6.519607493461354', '100.42110990732908', 'Home Assistant Manager'),
('shatu@gmail.com', 'rani', '0124854544', 'Sharania Theeban', 'Tia Childrens Home', 'Changlun', '6.477651', '100.524096', 'Home Assistant Manager'),
('sheela05@gmail.com', 'she353', '0124457316', 'Sheela Nair', 'Agathians Home', 'Changlun', '6.433105', '100.436619', 'Home Manager');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `DONATION`
--
ALTER TABLE `DONATION`
  ADD PRIMARY KEY (`DONATION_ID`);

--
-- Indexes for table `DONATION_ITEMS`
--
ALTER TABLE `DONATION_ITEMS`
  ADD PRIMARY KEY (`DONATION_ITEM_ID`);

--
-- Indexes for table `USER`
--
ALTER TABLE `USER`
  ADD PRIMARY KEY (`EMAIL`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `DONATION`
--
ALTER TABLE `DONATION`
  MODIFY `DONATION_ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `DONATION_ITEMS`
--
ALTER TABLE `DONATION_ITEMS`
  MODIFY `DONATION_ITEM_ID` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
