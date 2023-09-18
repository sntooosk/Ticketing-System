-- --------------------------------------------------------
-- Servidor:                     127.0.0.1
-- Versão do servidor:           5.6.13 - MySQL Community Server (GPL)
-- OS do Servidor:               Win32
-- HeidiSQL Versão:              12.3.0.6589
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Copiando estrutura do banco de dados para bd_metro
CREATE DATABASE IF NOT EXISTS `bd_metro` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `bd_metro`;

-- Copiando estrutura para tabela bd_metro.tb01_bilhete
CREATE TABLE IF NOT EXISTS `tb01_bilhete` (
  `tb01_id` int(6) NOT NULL AUTO_INCREMENT,
  `tb01_numero` varchar(50) NOT NULL,
  `tb01_data` date NOT NULL,
  `tb01_hora` time NOT NULL,
  `tb01_estacao` varchar(50) NOT NULL,
  `tb01_valor` double(10,2) NOT NULL,
  `tb01_status` varchar(50) NOT NULL,
  PRIMARY KEY (`tb01_id`)
) ENGINE=InnoDB AUTO_INCREMENT=170 DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela bd_metro.tb01_bilhete: ~27 rows (aproximadamente)
INSERT INTO `tb01_bilhete` (`tb01_id`, `tb01_numero`, `tb01_data`, `tb01_hora`, `tb01_estacao`, `tb01_valor`, `tb01_status`) VALUES
	(143, '101250', '2023-06-28', '14:27:01', 'TUCURUVI', 4.40, 'Emitido C#'),
	(144, '101251', '2023-06-28', '14:27:18', 'TUCURUVI', 4.40, 'Emitido C#'),
	(145, '101252', '2023-06-28', '14:28:14', 'TUCURUVI', 4.40, 'Emitido C#'),
	(146, '101253', '2023-06-29', '18:06:29', 'TUCURUVI', 4.40, 'Emitido Java'),
	(147, '101254', '2023-06-29', '18:24:43', 'TIÊTE', 4.40, 'Emitido Java'),
	(148, '101255', '2023-06-30', '09:24:38', 'SANTANA', 4.40, 'Emitido Java'),
	(149, '101256', '2023-06-30', '11:21:36', 'TUCURUVI', 4.40, 'Emitido Java'),
	(150, '101257', '2023-06-30', '14:12:18', 'CARANDIRU', 4.40, 'Emitido Java'),
	(151, '101258', '2023-06-30', '14:21:03', 'CARANDIRU', 4.40, 'Emitido Java'),
	(152, '101259', '2023-06-30', '14:22:29', 'CARANDIRU', 4.40, 'Emitido Java'),
	(153, '101260', '2023-07-03', '19:06:03', 'TUCURUVI', 4.40, 'Emitido Java'),
	(154, '101261', '2023-07-12', '15:17:31', 'TUCURUVI', 4.40, 'Emitido Java'),
	(155, '101262', '2023-07-12', '15:17:40', 'TUCURUVI', 4.40, 'Emitido Java'),
	(156, '101263', '2023-07-12', '15:29:20', 'SANTANA', 4.40, 'Emitido Java'),
	(157, '101264', '2023-08-07', '16:21:24', 'CARANDIRU', 4.40, 'Emitido C#'),
	(158, '101265', '2023-08-07', '16:22:59', 'TIÊTE', 4.40, 'Emitido C#'),
	(159, '101266', '2023-08-07', '16:27:54', 'CARANDIRU', 4.40, 'Emitido C#'),
	(160, '101267', '2023-08-07', '16:29:10', 'CARANDIRU', 4.40, 'Emitido C#'),
	(161, '101268', '2023-08-07', '16:29:41', 'SANTANA', 4.40, 'Emitido C#'),
	(162, '101269', '2023-08-07', '16:30:29', 'TIÊTE', 4.40, 'Emitido C#'),
	(163, '101270', '2023-08-07', '16:30:40', 'TIÊTE', 4.40, 'Emitido C#'),
	(164, '101271', '2023-08-07', '16:30:47', 'TIÊTE', 4.40, 'Emitido C#'),
	(165, '101272', '2023-08-22', '13:39:08', 'TUCURUVI', 4.40, 'Emitido Java'),
	(166, '101273', '2023-08-22', '14:03:02', 'TUCURUVI', 4.40, 'Emitido Java'),
	(167, '101273', '2023-08-22', '14:29:31', 'LUZ', 4.40, 'Emitido Java'),
	(168, '101275', '2023-08-22', '14:31:07', 'SANTANA', 4.40, 'Emitido Java'),
	(169, '101276', '2023-08-22', '14:37:30', 'TIÊTE', 4.40, 'Emitido Java');

-- Copiando estrutura para tabela bd_metro.tb02_referencia
CREATE TABLE IF NOT EXISTS `tb02_referencia` (
  `tb02_id` int(6) NOT NULL,
  `tb02_valor` double(10,2) NOT NULL,
  `tb02_bilhete` int(8) NOT NULL,
  PRIMARY KEY (`tb02_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela bd_metro.tb02_referencia: ~1 rows (aproximadamente)
INSERT INTO `tb02_referencia` (`tb02_id`, `tb02_valor`, `tb02_bilhete`) VALUES
	(1, 4.40, 101276);

-- Copiando estrutura para tabela bd_metro.tb03_estacoes
CREATE TABLE IF NOT EXISTS `tb03_estacoes` (
  `tb03_id` int(11) NOT NULL AUTO_INCREMENT,
  `tb03_linha` varchar(50) NOT NULL,
  `tb03_nome` varchar(50) NOT NULL,
  PRIMARY KEY (`tb03_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

-- Copiando dados para a tabela bd_metro.tb03_estacoes: ~8 rows (aproximadamente)
INSERT INTO `tb03_estacoes` (`tb03_id`, `tb03_linha`, `tb03_nome`) VALUES
	(1, 'AZUL', 'TUCURUVI'),
	(2, 'AZUL', 'SANTANA'),
	(3, 'AZUL', 'CARANDIRU'),
	(4, 'AZUL', 'TIÊTE'),
	(5, 'AZUL', 'ARMÊNIA'),
	(6, 'AZUL', 'LUZ'),
	(7, 'AZUL', 'SÃO BENTO'),
	(8, 'AZUL', 'SÉ');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
