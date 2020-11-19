CREATE DATABASE IF NOT EXISTS chiquify;
USE chiquify;

DROP TABLE IF EXISTS producto;
DROP TABLE IF EXISTS usuario;

CREATE TABLE `usuario` (
  `nombre` varchar(50) NOT NULL,
  `apellido1` varchar(45) NOT NULL,
  `apellido2` varchar(45) DEFAULT NULL,
  `ciudad` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `passwd` varchar(255) NOT NULL,
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Tabla que contiene la información de todos los usuarios registrados en la aplicación';

CREATE TABLE `producto` (
  `idproduct` int NOT NULL AUTO_INCREMENT,
  `titulo` varchar(45) NOT NULL,
  `categoria` varchar(45) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `imagen` LONGTEXT NOT NULL,
  `precio` int NOT NULL,
  `vendedor` varchar(45) NOT NULL,
  `comprador` varchar(45) DEFAULT NULL,
  `estado` varchar(45) NOT NULL DEFAULT 'Disponible',
  PRIMARY KEY (`idproduct`),
  KEY `vendedor_idx` (`vendedor`),
  KEY `comprador_idx` (`comprador`),
  CONSTRAINT `comprador` FOREIGN KEY (`comprador`) REFERENCES `usuario` (`email`) ON UPDATE CASCADE,
  CONSTRAINT `vendedor` FOREIGN KEY (`vendedor`) REFERENCES `usuario` (`email`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Productos aÃ±adidos por los usuarios en la aplicaciÃ³n';