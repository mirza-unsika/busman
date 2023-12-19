-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 19 Des 2023 pada 08.53
-- Versi server: 10.4.28-MariaDB
-- Versi PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `busman`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `bus`
--

CREATE TABLE `bus` (
  `bus_id` int(11) NOT NULL,
  `jenis_bus_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `bus`
--

INSERT INTO `bus` (`bus_id`, `jenis_bus_id`) VALUES
(1, 2);

-- --------------------------------------------------------

--
-- Struktur dari tabel `data_penumpang`
--

CREATE TABLE `data_penumpang` (
  `booking_id` int(11) NOT NULL,
  `nama` varchar(255) DEFAULT NULL,
  `nik` varchar(16) DEFAULT NULL,
  `pilih_bus` varchar(255) DEFAULT NULL,
  `jurusan` varchar(255) DEFAULT NULL,
  `hari` varchar(255) DEFAULT NULL,
  `keberangkatan` varchar(255) DEFAULT NULL,
  `kursi` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `data_penumpang`
--

INSERT INTO `data_penumpang` (`booking_id`, `nama`, `nik`, `pilih_bus`, `jurusan`, `hari`, `keberangkatan`, `kursi`) VALUES
(11232, 'Muhammad Mirza', '3603170910040002', 'Transjakarta', 'Bekasi-Tangerang', 'Rabu', '23.30', 'M12'),
(11233, 'mq', '1111111111111111', 'Metromini', 'Bekasi-Karawang', 'Senin', '08.00', 'M01');

-- --------------------------------------------------------

--
-- Struktur dari tabel `jadwal`
--

CREATE TABLE `jadwal` (
  `jadwal_id` int(11) NOT NULL,
  `bus_id` int(11) DEFAULT NULL,
  `jurusan` varchar(255) NOT NULL,
  `hari` varchar(20) NOT NULL,
  `jam_keberangkatan` time NOT NULL,
  `harga` decimal(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `jadwal`
--

INSERT INTO `jadwal` (`jadwal_id`, `bus_id`, `jurusan`, `hari`, `jam_keberangkatan`, `harga`) VALUES
(1, 1, 'Bekasi-Karawang', 'Senin-Jumat', '08:00:00', 10000.00),
(2, 1, 'Bekasi-Karawang', 'Senin-Jumat', '11:30:00', 10000.00),
(3, 1, 'Bekasi-Karawang', 'Senin-Jumat', '15:00:00', 10000.00),
(4, 1, 'Bekasi-Karawang', 'Senin-Jumat', '18:30:00', 10000.00);

-- --------------------------------------------------------

--
-- Struktur dari tabel `jenis_bus`
--

CREATE TABLE `jenis_bus` (
  `jenis_bus_id` int(11) NOT NULL,
  `nama_jenis_bus` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `jenis_bus`
--

INSERT INTO `jenis_bus` (`jenis_bus_id`, `nama_jenis_bus`) VALUES
(1, 'Transjakarta'),
(2, 'Metromini'),
(3, 'Kopaja'),
(4, 'Sinar Mas'),
(5, 'Pahala Kencana');

-- --------------------------------------------------------

--
-- Struktur dari tabel `kursi`
--

CREATE TABLE `kursi` (
  `kursi_id` int(11) NOT NULL,
  `jadwal_id` int(11) DEFAULT NULL,
  `nomor_kursi` varchar(10) NOT NULL,
  `status` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `kursi`
--

INSERT INTO `kursi` (`kursi_id`, `jadwal_id`, `nomor_kursi`, `status`) VALUES
(1, 1, 'M01', 'Tersedia'),
(2, 1, 'M02', 'Tersedia'),
(3, 1, 'M03', 'Tersedia'),
(4, 1, 'M04', 'Tersedia'),
(5, 1, 'M05', 'Tersedia'),
(6, 1, 'M06', 'Tersedia'),
(7, 1, 'M07', 'Tersedia'),
(8, 1, 'M08', 'Tersedia'),
(9, 1, 'M09', 'Tersedia'),
(10, 1, 'M10', 'Tersedia'),
(11, 2, 'A01', 'Tersedia'),
(12, 2, 'A02', 'Tersedia'),
(13, 2, 'A03', 'Tersedia'),
(14, 2, 'A04', 'Tersedia'),
(15, 2, 'A05', 'Tersedia'),
(16, 2, 'A06', 'Tersedia'),
(17, 2, 'A07', 'Tersedia'),
(18, 2, 'A08', 'Tersedia'),
(19, 2, 'A09', 'Tersedia'),
(20, 2, 'A10', 'Tersedia'),
(21, 3, 'B01', 'Tersedia'),
(22, 3, 'B02', 'Tersedia'),
(23, 3, 'B03', 'Tersedia'),
(24, 3, 'B04', 'Tersedia'),
(25, 3, 'B05', 'Tersedia'),
(26, 3, 'B06', 'Tersedia'),
(27, 3, 'B07', 'Tersedia'),
(28, 3, 'B08', 'Tersedia'),
(29, 3, 'B09', 'Tersedia'),
(30, 3, 'B10', 'Tersedia'),
(31, 4, 'C01', 'Tersedia'),
(32, 4, 'C02', 'Tersedia'),
(33, 4, 'C03', 'Tersedia'),
(34, 4, 'C04', 'Tersedia'),
(35, 4, 'C05', 'Tersedia'),
(36, 4, 'C06', 'Tersedia'),
(37, 4, 'C07', 'Tersedia'),
(38, 4, 'C08', 'Tersedia'),
(39, 4, 'C09', 'Tersedia'),
(40, 4, 'C10', 'Tersedia');

-- --------------------------------------------------------

--
-- Struktur dari tabel `kursi_bus`
--

CREATE TABLE `kursi_bus` (
  `nama_jenis_bus` varchar(50) DEFAULT NULL,
  `kursi` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `kursi_bus`
--

INSERT INTO `kursi_bus` (`nama_jenis_bus`, `kursi`) VALUES
('Metromini', 'M11'),
('Metromini', 'M12'),
('Metromini', 'M13'),
('Metromini', 'M14'),
('Metromini', 'M15');

-- --------------------------------------------------------

--
-- Struktur dari tabel `login`
--

CREATE TABLE `login` (
  `username` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `login`
--

INSERT INTO `login` (`username`, `password`) VALUES
('Mirzakur', '190704');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `bus`
--
ALTER TABLE `bus`
  ADD PRIMARY KEY (`bus_id`),
  ADD KEY `jenis_bus_id` (`jenis_bus_id`);

--
-- Indeks untuk tabel `data_penumpang`
--
ALTER TABLE `data_penumpang`
  ADD PRIMARY KEY (`booking_id`);

--
-- Indeks untuk tabel `jadwal`
--
ALTER TABLE `jadwal`
  ADD PRIMARY KEY (`jadwal_id`),
  ADD KEY `bus_id` (`bus_id`);

--
-- Indeks untuk tabel `jenis_bus`
--
ALTER TABLE `jenis_bus`
  ADD PRIMARY KEY (`jenis_bus_id`);

--
-- Indeks untuk tabel `kursi`
--
ALTER TABLE `kursi`
  ADD PRIMARY KEY (`kursi_id`),
  ADD KEY `jadwal_id` (`jadwal_id`);

--
-- Indeks untuk tabel `login`
--
ALTER TABLE `login`
  ADD PRIMARY KEY (`username`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `bus`
--
ALTER TABLE `bus`
  MODIFY `bus_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT untuk tabel `data_penumpang`
--
ALTER TABLE `data_penumpang`
  MODIFY `booking_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11236;

--
-- AUTO_INCREMENT untuk tabel `jadwal`
--
ALTER TABLE `jadwal`
  MODIFY `jadwal_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT untuk tabel `jenis_bus`
--
ALTER TABLE `jenis_bus`
  MODIFY `jenis_bus_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT untuk tabel `kursi`
--
ALTER TABLE `kursi`
  MODIFY `kursi_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=41;

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `bus`
--
ALTER TABLE `bus`
  ADD CONSTRAINT `bus_ibfk_1` FOREIGN KEY (`jenis_bus_id`) REFERENCES `jenis_bus` (`jenis_bus_id`);

--
-- Ketidakleluasaan untuk tabel `jadwal`
--
ALTER TABLE `jadwal`
  ADD CONSTRAINT `jadwal_ibfk_1` FOREIGN KEY (`bus_id`) REFERENCES `bus` (`bus_id`);

--
-- Ketidakleluasaan untuk tabel `kursi`
--
ALTER TABLE `kursi`
  ADD CONSTRAINT `kursi_ibfk_1` FOREIGN KEY (`jadwal_id`) REFERENCES `jadwal` (`jadwal_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
