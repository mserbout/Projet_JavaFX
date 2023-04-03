-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 03, 2023 at 05:28 PM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `projet-11`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `id` int(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id`, `username`, `password`) VALUES
(1, 'admin', 'admin'),
(2, 'mohamed', 'mohamed123');

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `id` int(255) NOT NULL,
  `customer_id` int(255) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `gender` varchar(255) NOT NULL,
  `phoneNumber` varchar(255) NOT NULL,
  `productID` int(255) NOT NULL,
  `productName` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  `quantity` int(255) NOT NULL,
  `total` double NOT NULL,
  `date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`id`, `customer_id`, `first_name`, `last_name`, `gender`, `phoneNumber`, `productID`, `productName`, `type`, `quantity`, `total`, `date`) VALUES
(1, 0, 'Mohamed', 'Serbout', 'Male', '0667773772', 2, 'Hp EliteBook 850 G3', 'Available', 2, 1640, '2023-03-08'),
(2, 1, 'Manal', 'Manal', 'Female', '066999693', 2, 'Earphone Lenovo LP40 pro', 'Available', 3, 30, '2023-03-01'),
(3, 2, 'Issam', 'Serbout', 'Male', '0669995111', 2, 'Hp EliteBook 850 G3', 'Available', 4, 1640, '2023-02-28'),
(4, 4, 'Mohamed', 'Serbout', 'Male', '0667773772', 2, 'Xiaomi Redmi Note 12 pro', 'Available', 2, 780, '2023-03-02'),
(5, 5, 'Zakaria', 'Serbout', 'Male', '063381815', 3, 'Hp EliteBook 850 G3', 'Available', 1, 410, '2023-03-08'),
(6, 6, 'Zakaria', 'Serbout', 'Male', '06666999988', 5, 'USB Wireless Mouse 2000DPI USB 2.0', 'Available', 3, 24, '2023-03-28'),
(7, 7, 'Salma', 'Tita', 'Female', '0696635233', 2, 'Earphone Lenovo LP40 pro', 'Available', 2, 20, '2023-03-29'),
(8, 8, 'Marouan', 'Daghmoumy', 'Male', '066999852', 2, 'Xiaomi Redmi Note 12 pro', 'Available', 3, 2340, '2023-03-29'),
(9, 9, 'Tasnim', 'Laarabi', 'Female', '06996952', 5, 'Baseus USB C to USB Type C Cable for MacBook Pro', 'Available', 4, 15.96, '2023-03-29'),
(10, 10, 'Mohamed', 'Serbout', 'Male', '0667773772', 2, 'Hp EliteBook 850 G3', 'Available', 2, 820, '2023-03-29'),
(11, 11, 'Wissal', 'Wassini', 'Female', '06696969966', 7, 'Baseus H5 Handheld Wireless Vacuum Cleaner', 'Available', 2, 279.8, '2023-03-30'),
(12, 12, 'Hakim', 'Ziyech', 'Male', '069969598', 7, 'Baseus H5 Handheld Wireless Vacuum Cleaner', 'Available', 10, 1399, '2023-03-30'),
(13, 13, 'Wiam', 'Kacimi', 'Male', '066999366', 6, 'Baseus H5 Handheld Wireless Vacuum Cleaner', 'Available', 3, 419.70000000000005, '2023-03-31'),
(14, 14, 'Mohamed', 'Serbout', 'Male', '0667773772', 3, 'Xiaomi Redmi Note 12 pro', 'Available', 3, 1170, '2023-04-01');

-- --------------------------------------------------------

--
-- Table structure for table `customer_receipt`
--

CREATE TABLE `customer_receipt` (
  `id` int(255) NOT NULL,
  `customer_id` int(255) NOT NULL,
  `total` double NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `customer_receipt`
--

INSERT INTO `customer_receipt` (`id`, `customer_id`, `total`, `date`) VALUES
(1, 7, 20, '2023-03-29'),
(2, 8, 2340, '2023-03-29'),
(3, 9, 15.96, '2023-03-29'),
(4, 10, 820, '2023-03-29'),
(5, 11, 279.8, '2023-03-30'),
(6, 12, 1399, '2023-03-30'),
(7, 13, 419.70000000000005, '2023-03-31'),
(8, 14, 1170, '2023-04-01');

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `id` int(255) NOT NULL,
  `product_id` int(255) NOT NULL,
  `name_product` varchar(255) NOT NULL,
  `status` varchar(255) NOT NULL,
  `price` double NOT NULL,
  `date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`id`, `product_id`, `name_product`, `status`, `price`, `date`) VALUES
(1, 1, 'Earphone Lenovo LP40 pro', 'Available', 10, '2023-03-27'),
(2, 2, 'Hp EliteBook 850 G3', 'Available', 410, '2023-03-31'),
(3, 3, 'Xiaomi Redmi Note 12 pro', 'Available', 390, '2023-04-03'),
(6, 5, 'USB Wireless Mouse 2000DPI USB 2.0', 'Available', 8, '2023-05-18'),
(7, 6, 'Baseus USB C to USB Type C Cable for MacBook Pro', 'Available', 3.99, '2023-03-29'),
(8, 7, 'Baseus H5 Handheld Wireless Vacuum Cleaner', 'Available', 139.9, '2023-03-30'),
(10, 9, 'MacBook Pro', 'Available', 10020, '2023-04-01');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `customer_receipt`
--
ALTER TABLE `customer_receipt`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `customer_receipt`
--
ALTER TABLE `customer_receipt`
  MODIFY `id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
