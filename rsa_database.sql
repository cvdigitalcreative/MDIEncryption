-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 23 Apr 2018 pada 05.09
-- Versi Server: 10.1.28-MariaDB
-- PHP Version: 7.1.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `rsa_database`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `data_rsa`
--

CREATE TABLE `data_rsa` (
  `id_key` int(11) NOT NULL,
  `nama_file` text NOT NULL,
  `private_key` double NOT NULL,
  `nilai_n` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `data_rsa`
--

INSERT INTO `data_rsa` (`id_key`, `nama_file`, `private_key`, `nilai_n`) VALUES
(1, '1.bmp', 2147, 3337),
(2, '2.bmp', 2147, 3337),
(3, '3.bmp', 43, 77),
(4, 'contoh.bmp', 43, 77),
(5, 'data1.bmp', 411, 667),
(6, 'data2.bmp', 103, 143),
(7, 'data3.bmp', 5, 35),
(8, 'data4.bmp', 77, 221);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `data_rsa`
--
ALTER TABLE `data_rsa`
  ADD PRIMARY KEY (`id_key`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
