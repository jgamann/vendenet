# SQL Manager 2005 for MySQL 3.6.5.1
# ---------------------------------------
# Host     : localhost
# Port     : 3306
# Database : vendenet


SET FOREIGN_KEY_CHECKS=0;

DROP DATABASE IF EXISTS `vendenet`;

CREATE DATABASE `vendenet`
    CHARACTER SET 'utf8'
    COLLATE 'utf8_general_ci';

USE `vendenet`;

#
# Structure for the `categoriaadjunto` table : 
#

DROP TABLE IF EXISTS `categoriaadjunto`;

CREATE TABLE `categoriaadjunto` (
  `CATEGORIAADJUNTO_ID` int(11) NOT NULL,
  `CATEGORIAADJUNTO_NAME` varchar(255) default NULL,
  PRIMARY KEY  (`CATEGORIAADJUNTO_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Data for the `categoriaadjunto` table  (LIMIT 0,500)
#

INSERT INTO `categoriaadjunto` (`CATEGORIAADJUNTO_ID`, `CATEGORIAADJUNTO_NAME`) VALUES 
  (1,'FOTOGRAFIA');

COMMIT;

#
# Structure for the `provincia` table : 
#

DROP TABLE IF EXISTS `provincia`;

CREATE TABLE `provincia` (
  `PROVINCIA_ID` int(11) NOT NULL,
  `PROVINCIA_NAME` varchar(255) default NULL,
  PRIMARY KEY  (`PROVINCIA_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Data for the `provincia` table  (LIMIT 0,500)
#

INSERT INTO `provincia` (`PROVINCIA_ID`, `PROVINCIA_NAME`) VALUES 
  (1,'Araba'),
  (2,'Albacete'),
  (3,'Alicante'),
  (4,'Almería'),
  (5,'Avila'),
  (6,'Badajoz'),
  (7,'Baleares'),
  (8,'Barcelona'),
  (9,'Burgos'),
  (10,'Cáceres'),
  (11,'Cádiz'),
  (12,'Castellón'),
  (13,'Ciudad Real'),
  (14,'Córdoba'),
  (15,'A Coruña'),
  (16,'Cuenca'),
  (17,'Girona'),
  (18,'Granada'),
  (19,'Guadalajara'),
  (20,'Gipuzkoa'),
  (21,'Huelva'),
  (22,'Huesca'),
  (23,'Jaen'),
  (24,'León'),
  (25,'Lérida'),
  (26,'La Rioja'),
  (27,'Lugo'),
  (28,'Madrid'),
  (29,'Málaga'),
  (30,'Murcia'),
  (31,'Navarra'),
  (32,'Ourense'),
  (33,'Asturias'),
  (34,'Palencia'),
  (35,'Las Palmas'),
  (36,'Pontevedra'),
  (37,'Salamanca'),
  (38,'S.C.Tenerife'),
  (39,'Cantabria'),
  (40,'Segovia'),
  (41,'Sevilla'),
  (42,'Soria'),
  (43,'Tarragona'),
  (44,'Teruel'),
  (45,'Toledo'),
  (46,'Valencia'),
  (47,'Valladolid'),
  (48,'Bizkaia'),
  (49,'Zamora'),
  (50,'Zaragoza'),
  (51,'Ceuta'),
  (52,'Melilla');

COMMIT;

#
# Structure for the `tipovendedor` table : 
#

DROP TABLE IF EXISTS `tipovendedor`;

CREATE TABLE `tipovendedor` (
  `TIPOVENDEDOR_ID` int(11) NOT NULL,
  `TIPOVENDEDOR_NAME` varchar(255) default NULL,
  PRIMARY KEY  (`TIPOVENDEDOR_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Data for the `tipovendedor` table  (LIMIT 0,500)
#

INSERT INTO `tipovendedor` (`TIPOVENDEDOR_ID`, `TIPOVENDEDOR_NAME`) VALUES 
  (1,'Particular'),
  (2,'Profesional');

COMMIT;

#
# Structure for the `categoriaanuncio` table : 
#

DROP TABLE IF EXISTS `categoriaanuncio`;

CREATE TABLE `categoriaanuncio` (
  `CATEGORIA_ID` int(11) NOT NULL,
  `CATEGORIA_NAME` varchar(255) default NULL,
  `CATEGORIAPADRE_ID` int(11) default NULL,
  `ORDEN` int(11) default NULL,
  PRIMARY KEY  (`CATEGORIA_ID`),
  KEY `IdCategoriaPadre` (`CATEGORIAPADRE_ID`),
  CONSTRAINT `categoriaanuncio_fk` FOREIGN KEY (`CATEGORIAPADRE_ID`) REFERENCES `categoriaanuncio` (`CATEGORIA_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Data for the `categoriaanuncio` table  (LIMIT 0,500)
#

INSERT INTO `categoriaanuncio` (`CATEGORIA_ID`, `CATEGORIA_NAME`, `CATEGORIAPADRE_ID`, `ORDEN`) VALUES 
  (0,'CATEGORIANULA',0,0),
  (1,'VEHICULOS',0,1),
  (2,'INMOBILIARIA',0,10),
  (3,'ELECTRÓNICA',0,19),
  (4,'OCIO Y DEPORTE',0,23),
  (5,'CASA Y JARDÍN',0,31),
  (6,'NEGOCIOS Y EMPLEO',0,37),
  (7,'Otros',0,42),
  (11,'Coches',1,2),
  (12,'Accesorios de coches',1,3),
  (13,'Motos',1,4),
  (14,'Quads y buggies',1,5),
  (15,'Accesorios de motos',1,6),
  (16,'Remolques y Caravanas',1,7),
  (17,'Barcos y náutica',1,8),
  (18,'Vehículos industriales y agrícolas',1,9),
  (21,'Pisos',2,11),
  (22,'Casas y chalés',2,12),
  (23,'Terrenos y fincas rústicas',2,13),
  (24,'Garajes y trasteros',2,14),
  (25,'Locales, oficinas y naves',2,15),
  (26,'Alquiler de vacaciones y apartamentos',2,16),
  (27,'Pisos Compartidos y habitaciones',2,17),
  (28,'Casas Rurales',2,18),
  (31,'Informática y juegos',3,20),
  (32,'Audio, video y fotografía',3,21),
  (33,'Teléfonos y otros electrónica',3,22),
  (41,'Deportes',4,24),
  (42,'Animales y accesorios',4,25),
  (43,'Música, películas y libros',4,26),
  (44,'Tiempo libre y aficiones',4,27),
  (45,'Coleccionismo',4,28),
  (46,'Vacaciones y viajes',4,29),
  (47,'Amor y amistad',4,30),
  (51,'Para la casa',5,32),
  (52,'Para jardín y agricultura',5,33),
  (53,'Para los niños',5,34),
  (54,'Moda, joyas y complementos',5,35),
  (55,'Belleza, salud y estética',5,36),
  (61,'Negocios y traspasos',6,38),
  (62,'Trabajo y empleo',6,39),
  (63,'Cursos y formación',6,40),
  (64,'Servicios Profesionales',6,41);

COMMIT;

#
# Structure for the `tipoanuncio` table : 
#

DROP TABLE IF EXISTS `tipoanuncio`;

CREATE TABLE `tipoanuncio` (
  `TIPOANUNCIO_ID` int(11) NOT NULL,
  `TIPOANUNCIO_NAME` varchar(255) default NULL,
  PRIMARY KEY  (`TIPOANUNCIO_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Data for the `tipoanuncio` table  (LIMIT 0,500)
#

INSERT INTO `tipoanuncio` (`TIPOANUNCIO_ID`, `TIPOANUNCIO_NAME`) VALUES 
  (1,'Se vende'),
  (2,'Se compra'),
  (3,'Se alquila'),
  (4,'Se busca alquiler');

COMMIT;

#
# Structure for the `cliente` table : 
#

DROP TABLE IF EXISTS `cliente`;

CREATE TABLE `cliente` (
  `CLIENTE_ID` int(11) NOT NULL auto_increment,
  `CLIENTE_NAME` varchar(255) default NULL,
  `EMAIL` varchar(255) NOT NULL,
  `PASS` varchar(255) NOT NULL,
  `TELEFONO` varchar(255) default NULL,
  PRIMARY KEY  (`CLIENTE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Data for the `cliente` table  (LIMIT 0,500)
#

INSERT INTO `cliente` (`CLIENTE_ID`, `CLIENTE_NAME`, `EMAIL`, `PASS`, `TELEFONO`) VALUES 
  (34,'Jesus','txeus@hotmail.com','111','666555465'),
  (35,'Jesus','jgamann@gmail.com','333','666555465'),
  (38,'Jesus','jgamann@gmail.com','222','666555465'),
  (39,'Jesus','jgamann@gmail.com','1','666555465');

COMMIT;

#
# Structure for the `anuncio` table : 
#

DROP TABLE IF EXISTS `anuncio`;

CREATE TABLE `anuncio` (
  `ANUNCIO_ID` int(11) NOT NULL auto_increment,
  `ANUNCIO_NAME` varchar(255) default NULL,
  `CLIENTE_ID` int(11) default NULL,
  `FECHA_ALTA` datetime default NULL,
  `TIPOVENDEDOR_ID` int(11) default NULL,
  `CUERPO` varchar(4000) default NULL,
  `PRECIO` double(15,2) default NULL,
  `CATEGORIA_ID` int(11) default NULL,
  `TIPOANUNCIO_ID` int(11) default NULL,
  `DESTACADO` tinyint(4) default NULL,
  `FECHA_CADUCIDAD` datetime default NULL,
  `FECHA_BORRADO` datetime default NULL,
  `PROVINCIA_ID` int(11) default NULL,
  `PUBLICADO` tinyint(4) default '0',
  `REVISADO` tinyint(4) default '0',
  PRIMARY KEY  (`ANUNCIO_ID`),
  KEY `IdTipoVendedor` (`TIPOVENDEDOR_ID`),
  KEY `IdCategoria` (`CATEGORIA_ID`),
  KEY `IdTipoAnuncio` (`TIPOANUNCIO_ID`),
  KEY `IdCategoria_2` (`CATEGORIA_ID`),
  KEY `IdTipoAnuncio_2` (`TIPOANUNCIO_ID`),
  KEY `IdTipoVendedor_2` (`TIPOVENDEDOR_ID`),
  KEY `PROVINCIA_ID` (`PROVINCIA_ID`),
  KEY `CLIENTE_ID` (`CLIENTE_ID`),
  CONSTRAINT `anuncio_fk` FOREIGN KEY (`PROVINCIA_ID`) REFERENCES `provincia` (`PROVINCIA_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `anuncio_fk1` FOREIGN KEY (`TIPOVENDEDOR_ID`) REFERENCES `tipovendedor` (`TIPOVENDEDOR_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `anuncio_fk2` FOREIGN KEY (`CATEGORIA_ID`) REFERENCES `categoriaanuncio` (`CATEGORIA_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `anuncio_fk3` FOREIGN KEY (`TIPOANUNCIO_ID`) REFERENCES `tipoanuncio` (`TIPOANUNCIO_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `anuncio_fk4` FOREIGN KEY (`CLIENTE_ID`) REFERENCES `cliente` (`CLIENTE_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Data for the `anuncio` table  (LIMIT 0,500)
#

INSERT INTO `anuncio` (`ANUNCIO_ID`, `ANUNCIO_NAME`, `CLIENTE_ID`, `FECHA_ALTA`, `TIPOVENDEDOR_ID`, `CUERPO`, `PRECIO`, `CATEGORIA_ID`, `TIPOANUNCIO_ID`, `DESTACADO`, `FECHA_CADUCIDAD`, `FECHA_BORRADO`, `PROVINCIA_ID`, `PUBLICADO`, `REVISADO`) VALUES 
  (43,'Vendo humo',34,'2011-02-14 00:08:43',2,'Vendo golf gti 1. 8i, en buen estado y practicamente todo al dia. . Color negro. \r\nTOYOTA Auris 1.6 VVTi Dual Sol MM, AZUL CLARO, año 2007, 40000 km, Tela Luna y Sol Gris Oscuro,Navegador + Bluetooth,Azul Claro Mica (metalizado),, 14400 eur., CONCESIONARIO OFICIAL TOYOTA PARA TODA LA PROVINCIA. VEHICULOS REVISADOS Y GARANTIZADOS.. \r\nAUDI A3 Sportback 2.0 TDI S tronic Ambition, BLANCO SLINE, año 2006, 114000 km, Apoyabrazos delntero con compartimiento,Inserciones en aluminio Plata Medial,Paquete deportivo S line,Posavaso en el salpicadero,Tapicería cuero \"Vienna\" con logo S line,Sistema de navegación,Open sky System,Vol. dep mult cuero perforado tipt/DSG,Barras laterales en el techo pulidas,Paquete exterior S line,, 19900 eur., AUDI A3 SPORTBACK 2.0 TDI 140 CV S LINE CAMBIO DSG LEVAS AL VOLANTE BLANCO TELA CUERO NEGRO ASIENTOS DEPORTIVOS , XENON, TECHO PANORAMA, GPS , INSERCIONES EN ALUMINIO , LLANTA 17 12 MESES DE GARANTIA\r\n',55,12,4,0,'2012-02-22 21:11:54',NULL,1,1,1),
  (44,'Titulo del anuncio',35,'2011-02-14 21:42:04',1,'Texto del anuncio',6500,11,1,0,'2012-02-19 19:51:06',NULL,48,1,1),
  (47,'Vendo humo',38,'2011-02-19 17:40:39',2,'dlkgdf gdfg hijo de sdfsksk nsdfnsldknf lskdfns df\r\nsfjlsak nfsalnf lsjkg slkj dslf jgdlfkg jdfjg',13500,11,4,0,'2012-02-19 21:15:04',NULL,2,1,1),
  (48,'&&&&&%%%%%%$$$$$$$$$$$<<<<<>>>>>>>>¡¡¡¡¡¡¡dflkgjdfño`pi',39,'2011-02-22 21:13:41',2,'ljknlknlkn4k65346h 67jlkh·$%&&%$?¿¿\'+{}{}ç´ç´ç´ç+',654,12,3,0,'2012-02-22 21:14:01',NULL,1,1,1);

COMMIT;

#
# Structure for the `adjunto` table : 
#

DROP TABLE IF EXISTS `adjunto`;

CREATE TABLE `adjunto` (
  `ADJUNTO_ID` int(11) NOT NULL auto_increment,
  `ADJUNTO_NAME` varchar(255) NOT NULL,
  `ADJUNTO_PATH` varchar(255) NOT NULL,
  `CATEGORIAADJUNTO_ID` int(11) default NULL,
  `FECHA` datetime default NULL,
  `ANUNCIO_ID` int(11) default NULL,
  `PRINCIPAL` tinyint(1) default NULL,
  PRIMARY KEY  (`ADJUNTO_ID`),
  KEY `adjuntos_fk` (`CATEGORIAADJUNTO_ID`),
  KEY `ANUNCIO_ID` (`ANUNCIO_ID`),
  CONSTRAINT `adjuntos_fk` FOREIGN KEY (`CATEGORIAADJUNTO_ID`) REFERENCES `categoriaadjunto` (`CATEGORIAADJUNTO_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `adjunto_fk` FOREIGN KEY (`ANUNCIO_ID`) REFERENCES `anuncio` (`ANUNCIO_ID`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Data for the `adjunto` table  (LIMIT 0,500)
#

INSERT INTO `adjunto` (`ADJUNTO_ID`, `ADJUNTO_NAME`, `ADJUNTO_PATH`, `CATEGORIAADJUNTO_ID`, `FECHA`, `ANUNCIO_ID`, `PRINCIPAL`) VALUES 
  (3,'3','2011_7/3.jpg',1,'2011-02-14 00:08:33',43,0),
  (5,'5','2011_7/5.jpg',1,'2011-02-14 00:09:05',43,0),
  (7,'7','2011_7/7.jpg',1,'2011-02-14 21:41:34',44,0),
  (8,'8','2011_7/8.jpg',1,'2011-02-14 21:41:39',44,0),
  (9,'9','2011_7/9.jpg',1,'2011-02-14 21:41:42',44,0),
  (12,'12','2011_7/12.jpg',1,'2011-02-19 21:14:13',47,0),
  (13,'13','2011_7/13.jpg',1,'2011-02-19 21:14:18',47,0),
  (14,'14','2011_7/14.jpg',1,'2011-02-19 21:14:21',47,0),
  (15,'15','2011_7/15.jpg',1,'2011-02-19 21:14:25',47,0),
  (27,'','',1,NULL,NULL,0);

COMMIT;

#
# Structure for the `criterioorden` table : 
#

DROP TABLE IF EXISTS `criterioorden`;

CREATE TABLE `criterioorden` (
  `CRITERIOORDEN_ID` int(11) NOT NULL,
  `CRITERIOORDEN_NAME` varchar(20) default NULL,
  `CLAVE_ORDEN` varchar(20) default NULL,
  PRIMARY KEY  (`CRITERIOORDEN_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Data for the `criterioorden` table  (LIMIT 0,500)
#

INSERT INTO `criterioorden` (`CRITERIOORDEN_ID`, `CRITERIOORDEN_NAME`, `CLAVE_ORDEN`) VALUES 
  (1,'Fecha','fechaAlta'),
  (2,'Precio','precio'),
  (3,'T&iacute;tulo','name');

COMMIT;

#
# Structure for the `enviopendiente` table : 
#

DROP TABLE IF EXISTS `enviopendiente`;

CREATE TABLE `enviopendiente` (
  `ENVIOPENDIENTE_ID` int(11) NOT NULL auto_increment,
  `ENVIOPENDIENTE_NAME` varchar(20) default NULL,
  `EMAIL_FROM` varchar(255) default NULL,
  `EMAIL_TO` varchar(255) default NULL,
  `EMAIL_SUBJECT` varchar(255) default NULL,
  `EMAIL_BODY` varchar(2000) default NULL,
  `FECHA_ALTA` datetime default NULL,
  `ANUNCIO_ID` int(11) default NULL,
  `ENVIADO` tinyint(2) default NULL,
  PRIMARY KEY  (`ENVIOPENDIENTE_ID`),
  KEY `ANUNCIO_ID` (`ANUNCIO_ID`),
  CONSTRAINT `enviopendiente_fk` FOREIGN KEY (`ANUNCIO_ID`) REFERENCES `anuncio` (`ANUNCIO_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Structure for the `filtro` table : 
#

DROP TABLE IF EXISTS `filtro`;

CREATE TABLE `filtro` (
  `FILTRO_ID` int(11) NOT NULL,
  `FILTRO_NAME` varchar(255) default NULL,
  PRIMARY KEY  (`FILTRO_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Data for the `filtro` table  (LIMIT 0,500)
#

INSERT INTO `filtro` (`FILTRO_ID`, `FILTRO_NAME`) VALUES 
  (1,'Provincia'),
  (2,'Categoria');

COMMIT;

#
# Structure for the `ipvisitante` table : 
#

DROP TABLE IF EXISTS `ipvisitante`;

CREATE TABLE `ipvisitante` (
  `IPVISITANTE_ID` int(11) NOT NULL auto_increment,
  `IPVISITANTE_NAME` varchar(20) default NULL,
  `ANUNCIO_ID` int(11) default NULL,
  PRIMARY KEY  (`IPVISITANTE_ID`),
  KEY `ANUNCIO_ID` (`ANUNCIO_ID`),
  CONSTRAINT `ipvistante_fk` FOREIGN KEY (`ANUNCIO_ID`) REFERENCES `anuncio` (`ANUNCIO_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Data for the `ipvisitante` table  (LIMIT 0,500)
#

INSERT INTO `ipvisitante` (`IPVISITANTE_ID`, `IPVISITANTE_NAME`, `ANUNCIO_ID`) VALUES 
  (4,'0:0:0:0:0:0:0:1',44),
  (5,'0:0:0:0:0:0:0:1',47),
  (7,'0:0:0:0:0:0:0:1',43),
  (8,'0:0:0:0:0:0:0:1',48),
  (9,'192.168.1.34',47),
  (10,'192.168.1.34',48),
  (11,'192.168.1.34',44);

COMMIT;

#
# Structure for the `listanegra` table : 
#

DROP TABLE IF EXISTS `listanegra`;

CREATE TABLE `listanegra` (
  `LISTANEGRA_ID` int(11) NOT NULL auto_increment,
  `LISTANEGRA_NAME` varchar(255) default NULL,
  `FECHA` datetime default NULL,
  PRIMARY KEY  (`LISTANEGRA_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Structure for the `marcascoche` table : 
#

DROP TABLE IF EXISTS `marcascoche`;

CREATE TABLE `marcascoche` (
  `IdMarca` int(11) NOT NULL,
  `NombreMarca` varchar(255) default NULL,
  PRIMARY KEY  (`IdMarca`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Data for the `marcascoche` table  (LIMIT 0,500)
#

INSERT INTO `marcascoche` (`IdMarca`, `NombreMarca`) VALUES 
  (1,'AIXAM'),
  (2,'ALFA ROMEO'),
  (3,'ARO'),
  (4,'ASIA MOTORS'),
  (5,'ASTON MARTIN'),
  (6,'AUDI'),
  (7,'AUSTIN'),
  (8,'AUVERLAND'),
  (9,'BELLIER'),
  (10,'BENTLEY'),
  (11,'BERTONE'),
  (12,'BMW'),
  (13,'CADILLAC'),
  (14,'CHATENET'),
  (15,'CHEVROLET'),
  (16,'CHRYSLER'),
  (17,'CITROEN'),
  (18,'DACIA'),
  (19,'DAEWOO'),
  (20,'DAIHATSU'),
  (21,'DAIMLER'),
  (22,'DODGE'),
  (23,'FERRARI'),
  (24,'FIAT'),
  (25,'FORD'),
  (26,'GALLOPER'),
  (27,'GRECAV'),
  (28,'HONDA'),
  (29,'HUMMER'),
  (30,'HYUNDAI'),
  (31,'INFINITI'),
  (32,'INNOCENTI'),
  (33,'ISUZU'),
  (34,'JAGUAR'),
  (35,'JDM'),
  (36,'JEEP'),
  (37,'KIA'),
  (38,'LADA'),
  (39,'LAMBORGHINI'),
  (40,'LANCIA'),
  (41,'LAND-ROVER'),
  (42,'LEXUS'),
  (43,'LIGIER'),
  (44,'LOTUS'),
  (45,'MAHINDRA'),
  (46,'MASERATI'),
  (47,'MAYBACH'),
  (48,'MAZDA'),
  (49,'MERCEDES-BENZ'),
  (50,'MG'),
  (51,'MICROCAR'),
  (52,'MINI'),
  (53,'MITSUBISHI'),
  (54,'MORGAN'),
  (55,'NISSAN'),
  (56,'OPEL'),
  (57,'PEUGEOT'),
  (58,'PIAGGIO'),
  (59,'PONTIAC'),
  (60,'PORSCHE'),
  (61,'RENAULT'),
  (62,'ROLLS-ROYCE'),
  (63,'ROVER'),
  (64,'SAAB'),
  (65,'SEAT'),
  (66,'SKODA'),
  (67,'SMART'),
  (68,'SSANGYONG'),
  (69,'SUBARU'),
  (70,'SUZUKI'),
  (71,'TALBOT'),
  (72,'TASSO'),
  (73,'TATA'),
  (74,'TOYOTA'),
  (75,'VOLKSWAGEN'),
  (76,'VOLVO'),
  (77,'WARTBURG');

COMMIT;

#
# Structure for the `marcasmoto` table : 
#

DROP TABLE IF EXISTS `marcasmoto`;

CREATE TABLE `marcasmoto` (
  `IdMarca` int(11) NOT NULL,
  `NombreMarca` varchar(255) default NULL,
  PRIMARY KEY  (`IdMarca`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Data for the `marcasmoto` table  (LIMIT 0,500)
#

INSERT INTO `marcasmoto` (`IdMarca`, `NombreMarca`) VALUES 
  (1,'Achice'),
  (2,'Adiva'),
  (3,'Adly Moto'),
  (4,'AEC'),
  (5,'Aeon'),
  (6,'Aero'),
  (7,'AIE'),
  (8,'Aiyumo'),
  (9,'AJP'),
  (10,'Alfer'),
  (11,'Alpina Renania'),
  (12,'A&M'),
  (13,'AMS'),
  (14,'Apex'),
  (15,'Aprilia'),
  (16,'Arctic Cat'),
  (17,'Argo'),
  (18,'Armstrong'),
  (19,'ASA'),
  (20,'Asiawing'),
  (21,'Asya'),
  (22,'Atala'),
  (23,'ATK'),
  (24,'AXR'),
  (25,'Azel'),
  (26,'Baimo'),
  (27,'Bajaj'),
  (28,'Banhe'),
  (29,'Baotian'),
  (30,'Barigo'),
  (31,'Barossa'),
  (32,'Bashan'),
  (33,'Benelli'),
  (34,'Bereco'),
  (35,'Beta'),
  (36,'Beyond'),
  (37,'Big Dog'),
  (38,'Bimota'),
  (39,'BMW'),
  (40,'Bombardier'),
  (41,'Boom'),
  (42,'Branson'),
  (43,'BSA'),
  (44,'Buell'),
  (45,'Bultaco'),
  (46,'Bunker'),
  (47,'Cagiva'),
  (48,'Campell'),
  (49,'CAN AM'),
  (50,'Cannondale'),
  (51,'Carnielli'),
  (52,'CCM Armstrong'),
  (53,'CF Moto'),
  (54,'Changjiang'),
  (55,'Chituma'),
  (56,'Chongqing'),
  (57,'Chuanl'),
  (58,'Chun Feng'),
  (59,'Clipic'),
  (60,'Cofersa'),
  (61,'Colomet'),
  (62,'Condor'),
  (63,'Cooltra'),
  (64,'Cosmopolitan'),
  (65,'CPI Motor'),
  (66,'Daelim'),
  (67,'Dafier'),
  (68,'Daihatsu'),
  (69,'DAK'),
  (70,'Dayang'),
  (71,'Dayun'),
  (72,'Dazon'),
  (73,'Derbi'),
  (74,'Devils Trike'),
  (75,'Dflong'),
  (76,'Di Blasi'),
  (77,'DIC'),
  (78,'Dinli'),
  (79,'Direct Bikes'),
  (80,'DJL'),
  (81,'DKW'),
  (82,'DMA'),
  (83,'Dnepr'),
  (84,'Dong Fang'),
  (85,'Dorton'),
  (86,'Ducati'),
  (87,'Ducson'),
  (88,'Durandal'),
  (89,'DVS'),
  (90,'Easy Trike'),
  (91,'Elig'),
  (92,'E Max'),
  (93,'EMF'),
  (94,'Enfield'),
  (95,'Erato'),
  (96,'E Ton'),
  (97,'Evt'),
  (98,'Explorer'),
  (99,'Faam'),
  (100,'Factory'),
  (101,'Famel Zundapp'),
  (102,'Fantic'),
  (103,'Fantic-Garelli'),
  (104,'Fantozzi'),
  (105,'Faztek'),
  (106,'Feiying'),
  (107,'Fite-Lem'),
  (108,'Fmc Motor'),
  (109,'FN'),
  (110,'Fosti'),
  (111,'FRC'),
  (112,'Freewiel'),
  (113,'Fuxin'),
  (114,'FYM'),
  (115,'Garelli'),
  (116,'Gas Gas'),
  (117,'Geely'),
  (118,'Generic'),
  (119,'Giantco'),
  (120,'Gilera'),
  (121,'Gimson'),
  (122,'Goes'),
  (123,'Greencar'),
  (124,'GS Moon'),
  (125,'Guoben'),
  (126,'Haizhimeng'),
  (127,'Hammel'),
  (128,'Hanglong'),
  (129,'Haostone'),
  (130,'Haotian'),
  (131,'Harley Davidson'),
  (132,'Herchee'),
  (133,'Hercules'),
  (134,'Hi Bird'),
  (135,'HM'),
  (136,'Honda'),
  (137,'Hongyi'),
  (138,'Honlei'),
  (139,'HRD'),
  (140,'Hsun'),
  (141,'Huasha'),
  (142,'Huatian'),
  (143,'Huawin'),
  (144,'Huoniao'),
  (145,'Huracan'),
  (146,'Husaberg'),
  (147,'Husqmoto'),
  (148,'Husqvarna'),
  (149,'Hyosung'),
  (150,'Ibeston'),
  (151,'IGM'),
  (152,'IGN'),
  (153,'Imoto'),
  (154,'Indian'),
  (155,'Iresa'),
  (156,'Ironhorse'),
  (157,'Iso'),
  (158,'Isorivolta'),
  (159,'Italjet'),
  (160,'Jawa Penta'),
  (161,'Jaxin'),
  (162,'Jiaji'),
  (163,'Jiajue'),
  (164,'Jialing'),
  (165,'Jianshe'),
  (166,'Jiaxin'),
  (167,'Jincheng-Sumco'),
  (168,'Jinlun'),
  (169,'Jinma'),
  (170,'Jj Cobas'),
  (171,'Jmstar'),
  (172,'Jonway'),
  (173,'Joyner'),
  (174,'Junak'),
  (175,'Junesun'),
  (176,'Junma'),
  (177,'Kaisar'),
  (178,'Kandi'),
  (179,'Kangchao'),
  (180,'Kangxin'),
  (181,'Kasea'),
  (182,'Kataya'),
  (183,'Kawasaki'),
  (184,'Kazuma'),
  (185,'Keen'),
  (186,'Keeway'),
  (187,'Kenbo'),
  (188,'Kenrod'),
  (189,'Kinlon'),
  (190,'Kinroad'),
  (191,'KTM'),
  (192,'KVN'),
  (193,'Kymco'),
  (194,'Lambretta'),
  (195,'Lamuratti'),
  (196,'Lanvertti'),
  (197,'Laverda'),
  (198,'LDL'),
  (199,'Leonart'),
  (200,'Lifan'),
  (201,'Ligier'),
  (202,'Lingben'),
  (203,'Lingying'),
  (204,'Lingyu'),
  (205,'Lingyun'),
  (206,'Linhai'),
  (207,'LML'),
  (208,'Loncin CSR'),
  (209,'Longbo'),
  (210,'Longchang'),
  (211,'Longjia'),
  (212,'Lube'),
  (213,'Macal'),
  (214,'Macbor'),
  (215,'Macquad'),
  (216,'Maico'),
  (217,'Malaguti'),
  (218,'Masai'),
  (219,'Massey Ferguson'),
  (220,'Mayse'),
  (221,'MBK'),
  (222,'MDL'),
  (223,'Mecatecno'),
  (224,'Megelli'),
  (225,'Meiduo'),
  (226,'Meitian'),
  (227,'Meko'),
  (228,'Merlin'),
  (229,'Metrakit'),
  (230,'MIG'),
  (231,'Minelli'),
  (232,'Miro Vehicles'),
  (233,'Mobilette'),
  (234,'Mondial'),
  (235,'Monkey Bikes'),
  (236,'Montesa'),
  (237,'Morbidelli'),
  (238,'Motalli'),
  (239,'Motivas'),
  (240,'Moto'),
  (241,'Moto Gac'),
  (242,'Moto Guzzi'),
  (243,'Moto Morini'),
  (244,'Moto Zeta'),
  (245,'Motoaupa'),
  (246,'Motobecane'),
  (247,'Motobi'),
  (248,'Motom'),
  (249,'Motonix'),
  (250,'Motorhispania'),
  (251,'Motormania'),
  (252,'MTR'),
  (253,'MUZ'),
  (254,'MV Agusta'),
  (255,'MVM'),
  (256,'MX Onda'),
  (257,'Mymsa'),
  (258,'MZ'),
  (259,'Narcla'),
  (260,'National Motor'),
  (261,'New Era'),
  (262,'New Force'),
  (263,'Nippi'),
  (264,'Norton'),
  (265,'Odes'),
  (266,'Omer'),
  (267,'Orion'),
  (268,'Ossa'),
  (269,'Peda'),
  (270,'Pegasus Moto'),
  (271,'Peidu'),
  (272,'Pelpi'),
  (273,'Petronas'),
  (274,'Peugeot'),
  (275,'Pexma'),
  (276,'PGO'),
  (277,'Phalton'),
  (278,'Piaggio'),
  (279,'Piaggio Vespa'),
  (280,'Pioneer'),
  (281,'Polaris'),
  (282,'Polini'),
  (283,'Pony'),
  (284,'Puch'),
  (285,'Pugar'),
  (286,'Qingqi'),
  (287,'Quickfoot'),
  (288,'Rabassa'),
  (289,'Raider'),
  (290,'Regal Raptor'),
  (291,'Reinmech'),
  (292,'Renault'),
  (293,'Revatto'),
  (294,'Rewaco'),
  (295,'Rhon'),
  (296,'Rieju'),
  (297,'Riya'),
  (298,'RMH'),
  (299,'ROA'),
  (300,'Roxon'),
  (301,'Royal Classic'),
  (302,'Sachs'),
  (303,'Sadrian'),
  (304,'Saga'),
  (305,'Samada'),
  (306,'Sanben'),
  (307,'Sanglas'),
  (308,'Sanili'),
  (309,'Sanya'),
  (310,'Scorpa'),
  (311,'Scuci'),
  (312,'Seakia'),
  (313,'Senke'),
  (314,'Shangben'),
  (315,'Shangeen'),
  (316,'She Lung'),
  (317,'Shenk'),
  (318,'Shenke'),
  (319,'Shenmao'),
  (320,'Shepherd'),
  (321,'Sherco'),
  (322,'Shineray'),
  (323,'Siamoto'),
  (324,'Side Bike'),
  (325,'Simson'),
  (326,'Sinski'),
  (327,'SIS Morini'),
  (328,'SIS Sachs'),
  (329,'SK'),
  (330,'Skygo'),
  (331,'Skyteam'),
  (332,'SM Taiwan'),
  (333,'SMC'),
  (334,'Snow'),
  (335,'Soca'),
  (336,'Solex'),
  (337,'Songking'),
  (338,'Sonik'),
  (339,'Sukida'),
  (340,'Sumco'),
  (341,'Sumo Motor'),
  (342,'Superbyke'),
  (343,'Suzhou'),
  (344,'Suzuki'),
  (345,'Swei Swen'),
  (346,'SWM'),
  (347,'SYM'),
  (348,'Tasio'),
  (349,'TBQ'),
  (350,'Terrot'),
  (351,'Teycars'),
  (352,'TGB'),
  (353,'Thumpstar'),
  (354,'Tisong'),
  (355,'Titan'),
  (356,'TM'),
  (357,'TMS'),
  (358,'Tomos'),
  (359,'Torrot'),
  (360,'Trigger'),
  (361,'Trimak'),
  (362,'Triumph'),
  (363,'TWA'),
  (364,'Uips'),
  (365,'UM'),
  (366,'Unic'),
  (367,'Unilli'),
  (368,'Unimoto'),
  (369,'Unison'),
  (370,'Universal Motor'),
  (371,'UNO'),
  (372,'Ural'),
  (373,'Vectrix'),
  (374,'Velocette'),
  (375,'Vento'),
  (376,'Vertemati'),
  (377,'Vespacar'),
  (378,'Vikers'),
  (379,'Vincent'),
  (380,'Vonroad'),
  (381,'VOR'),
  (382,'Voxan'),
  (383,'VST'),
  (384,'Wachauer'),
  (385,'Wangye'),
  (386,'Wildlander'),
  (387,'Windland'),
  (388,'WK'),
  (389,'Wolfboy'),
  (390,'Wonjan'),
  (391,'Wuyang'),
  (392,'Xgjao'),
  (393,'Xingyue'),
  (394,'Xinling'),
  (395,'Xinlun'),
  (396,'Yamaha'),
  (397,'Yamazu'),
  (398,'Yaqi'),
  (399,'Yiben'),
  (400,'Yide Field'),
  (401,'Yinxiang'),
  (402,'Yiying'),
  (403,'Yongkang Mtl'),
  (404,'Yuki'),
  (405,'Zealsun'),
  (406,'Zentaurus'),
  (407,'Zhe Jiang'),
  (408,'Zhenhua'),
  (409,'Zhongneng'),
  (410,'Zhongyu'),
  (411,'Zipstar'),
  (412,'Znen'),
  (413,'Zongshen'),
  (414,'Csr');

COMMIT;

#
# Structure for the `modeloscoche` table : 
#

DROP TABLE IF EXISTS `modeloscoche`;

CREATE TABLE `modeloscoche` (
  `IdModelo` int(11) NOT NULL,
  `IdMarca` int(11) default NULL,
  `NombreModelo` varchar(255) default NULL,
  PRIMARY KEY  (`IdModelo`),
  KEY `IdMarca` (`IdMarca`),
  CONSTRAINT `modeloscoche_fk` FOREIGN KEY (`IdMarca`) REFERENCES `marcascoche` (`IdMarca`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Data for the `modeloscoche` table  (LIMIT 0,500)
#

INSERT INTO `modeloscoche` (`IdModelo`, `IdMarca`, `NombreModelo`) VALUES 
  (1,1,'400'),
  (2,1,'500-4'),
  (3,1,'721'),
  (4,1,'741'),
  (5,1,'Mega'),
  (6,1,'Pick Up'),
  (7,1,'Scouty'),
  (9,2,'156'),
  (10,2,'Alfa 145'),
  (11,2,'Alfa 146'),
  (12,2,'Alfa 147'),
  (13,2,'Alfa 155'),
  (14,2,'Alfa 156'),
  (15,2,'Alfa 159'),
  (16,2,'Alfa 164'),
  (17,2,'Alfa 166'),
  (18,2,'Alfa 33'),
  (19,2,'Alfa 75'),
  (20,2,'Alfa 90'),
  (21,2,'Alfetta'),
  (22,2,'Brera'),
  (23,2,'Giulietta'),
  (24,2,'GT'),
  (25,2,'GTV'),
  (26,2,'Spider'),
  (27,2,'Sprint'),
  (29,3,'Aro'),
  (30,3,'Dacia'),
  (32,4,'DB'),
  (33,4,'Rocsta'),
  (35,5,'DB 7'),
  (36,5,'DB 9'),
  (37,5,'DB8'),
  (38,5,'Vanquish'),
  (39,5,'Vantage'),
  (40,5,'Virage'),
  (41,5,'Volante'),
  (43,6,'100'),
  (44,6,'200'),
  (45,6,'80'),
  (46,6,'90'),
  (47,6,'A2'),
  (48,6,'A3'),
  (49,6,'A4'),
  (50,6,'A6'),
  (51,6,'A8'),
  (52,6,'Allroad'),
  (53,6,'Cabrio'),
  (54,6,'Coupe'),
  (55,6,'Coupe TT'),
  (56,6,'Q7'),
  (57,6,'Quattro'),
  (58,6,'Roadster TT'),
  (59,6,'RS6'),
  (60,6,'S4'),
  (61,6,'V8'),
  (63,7,'Maestro'),
  (64,7,'Metro'),
  (65,7,'Mini'),
  (66,7,'Montego'),
  (68,8,'Cateva'),
  (69,8,'Continental'),
  (71,10,'Arnage'),
  (72,10,'Azure'),
  (73,10,'Brooklands'),
  (74,10,'Continental'),
  (75,10,'Corniche'),
  (76,10,'Eight'),
  (77,10,'Mulliner'),
  (78,10,'Mulsanne'),
  (79,10,'Turbo R'),
  (81,11,'Freeclimber'),
  (83,12,'Serie 1'),
  (84,12,'Serie 3'),
  (85,12,'Serie 5'),
  (86,12,'Serie 6'),
  (87,12,'Serie 7'),
  (88,12,'Serie 8'),
  (89,12,'X3'),
  (90,12,'X5'),
  (91,12,'Z1'),
  (92,12,'Z3'),
  (93,12,'Z8'),
  (94,12,'Z4'),
  (96,13,'BLS'),
  (97,13,'CTS'),
  (98,13,'Eldorado'),
  (99,13,'Escalade'),
  (100,13,'Seville'),
  (101,13,'SRX'),
  (102,13,'STS'),
  (103,13,'XLR'),
  (105,14,'400'),
  (106,14,'Barooder'),
  (107,14,'Media'),
  (108,14,'S-2'),
  (109,14,'Speedino'),
  (110,14,'Stella'),
  (112,15,'Alero'),
  (113,15,'Astro'),
  (114,15,'Aveo'),
  (115,15,'Blazer'),
  (116,15,'Camaro'),
  (117,15,'Cavalier'),
  (118,15,'Corvette'),
  (119,15,'Evanda'),
  (120,15,'Kalos_4p'),
  (121,15,'Kalos_5p'),
  (122,15,'Lacetti'),
  (123,15,'Matiz'),
  (124,15,'Nubira'),
  (125,15,'Suburban'),
  (126,15,'Tacuma'),
  (127,15,'Tahoe'),
  (128,15,'Trailblazer'),
  (129,15,'Trans Sport'),
  (131,16,'300'),
  (132,16,'Crossfire'),
  (133,16,'Grand Voyager'),
  (134,16,'L 200'),
  (135,16,'Le Baron'),
  (136,16,'Neon'),
  (137,16,'New Yorker'),
  (138,16,'PT Cruiser'),
  (139,16,'Saratoga'),
  (140,16,'Sebring'),
  (141,16,'Stratus'),
  (142,16,'Viper'),
  (143,16,'Vision'),
  (144,16,'Voyager'),
  (146,17,'2 CV'),
  (147,17,'AX'),
  (148,17,'BX'),
  (149,17,'C1'),
  (150,17,'C2'),
  (151,17,'C3'),
  (152,17,'C4'),
  (153,17,'C5'),
  (154,17,'C6'),
  (155,17,'C8'),
  (156,17,'CX'),
  (157,17,'Evasion'),
  (158,17,'GSA'),
  (159,17,'LNA'),
  (160,17,'Mehari'),
  (161,17,'Mehari'),
  (162,17,'Saxo'),
  (163,17,'Visa'),
  (164,17,'Xantia'),
  (165,17,'XM'),
  (166,17,'Xsara'),
  (167,17,'ZX'),
  (169,18,'Logan'),
  (171,19,'Aranos'),
  (172,19,'Compact'),
  (173,19,'Evanda'),
  (174,19,'Kalos'),
  (175,19,'Lacetti'),
  (176,19,'Lanos'),
  (177,19,'Leganza'),
  (178,19,'Matiz'),
  (179,19,'Nexia'),
  (180,19,'Nubira'),
  (181,19,'Tacuma'),
  (183,20,'Applause'),
  (184,20,'Charade'),
  (185,20,'Feroza'),
  (186,20,'Rocky'),
  (187,20,'Sirion'),
  (188,20,'Terios'),
  (190,21,'Double Six'),
  (191,21,'Six'),
  (192,21,'XJ V8'),
  (194,22,'Dakota'),
  (195,22,'Durango'),
  (196,22,'Magnun'),
  (197,22,'Ram'),
  (198,22,'Stealth'),
  (199,22,'Viper'),
  (201,23,'328'),
  (202,23,'348'),
  (203,23,'355'),
  (204,23,'360'),
  (205,23,'412'),
  (206,23,'430'),
  (207,23,'456'),
  (208,23,'512'),
  (209,23,'550'),
  (210,23,'599'),
  (211,23,'612'),
  (212,23,'Challenge'),
  (213,23,'Enzo'),
  (214,23,'F40'),
  (215,23,'F430'),
  (216,23,'F50'),
  (217,23,'F512'),
  (218,23,'Mondial'),
  (219,23,'Superamerica'),
  (220,23,'Testarossa'),
  (222,24,'Argenta'),
  (223,24,'Barchetta'),
  (224,24,'Brava'),
  (225,24,'Bravo'),
  (226,24,'Cinquecento'),
  (227,24,'Coupe'),
  (228,24,'Croma'),
  (229,24,'Grande Punto'),
  (230,24,'Idea'),
  (231,24,'Marea'),
  (232,24,'Multipla'),
  (233,24,'Palio Weekend'),
  (234,24,'Panda'),
  (235,24,'Punto'),
  (236,24,'Regata'),
  (237,24,'Ritmo'),
  (238,24,'Seicento'),
  (239,24,'Stilo'),
  (240,24,'Tempra'),
  (241,24,'Tipo'),
  (242,24,'Ulysse'),
  (243,24,'Uno'),
  (244,24,'X19'),
  (246,25,'Capri'),
  (247,25,'Cobra'),
  (248,25,'Cougar'),
  (249,25,'Escort'),
  (250,25,'Excursion'),
  (251,25,'Explorer'),
  (252,25,'F-150'),
  (253,25,'F-350'),
  (254,25,'Fiesta'),
  (255,25,'Focus'),
  (256,25,'Fusion'),
  (257,25,'Galaxy'),
  (258,25,'Granada'),
  (259,25,'KA'),
  (260,25,'Maverick'),
  (261,25,'Mondeo'),
  (262,25,'Mustang'),
  (263,25,'Orion'),
  (264,25,'Probe'),
  (265,25,'Puma'),
  (266,25,'Ranger'),
  (267,25,'Scorpio'),
  (268,25,'Sierra'),
  (269,25,'Thunderbird'),
  (271,26,'Exceed'),
  (272,26,'Santamo'),
  (274,27,'Bingo'),
  (275,27,'Eke'),
  (276,27,'SW'),
  (278,28,'Accord'),
  (279,28,'Civic'),
  (280,28,'Concerto'),
  (281,28,'CR-V'),
  (282,28,'CRX'),
  (283,28,'FR-V'),
  (284,28,'HR-V'),
  (285,28,'Integra'),
  (286,28,'Jazz'),
  (287,28,'Legend'),
  (288,28,'Logo'),
  (289,28,'NSX'),
  (290,28,'Prelude'),
  (291,28,'S2000'),
  (292,28,'Stream'),
  (294,29,'H1'),
  (295,29,'H2'),
  (296,29,'H3'),
  (298,30,'Accent'),
  (299,30,'Atos'),
  (300,30,'Coupe'),
  (301,30,'Elantra'),
  (302,30,'Galloper'),
  (303,30,'Getz'),
  (304,30,'H-1'),
  (305,30,'Lantra'),
  (306,30,'Matrix'),
  (307,30,'Pony'),
  (308,30,'Santa Fe'),
  (309,30,'S-Coupe'),
  (310,30,'Sonata'),
  (311,30,'Terracan'),
  (312,30,'Trajet'),
  (313,30,'Tucson'),
  (314,30,'XG'),
  (316,31,'FX'),
  (317,31,'G'),
  (318,31,'M'),
  (319,31,'Q'),
  (320,31,'QX'),
  (322,32,'Minitre'),
  (324,33,'Pick up'),
  (325,33,'Rodeo'),
  (326,33,'Trooper'),
  (328,34,'Daimler'),
  (329,34,'Sovereing'),
  (330,34,'S-Type'),
  (331,34,'XJ'),
  (332,34,'XJ6'),
  (333,34,'XK8'),
  (334,34,'XKR'),
  (335,34,'X-Type'),
  (337,35,'Albicia'),
  (338,35,'Titane'),
  (340,36,'Bravo'),
  (341,36,'Cherokee'),
  (342,36,'CJ7'),
  (343,36,'Commander'),
  (344,36,'Grand Cherokee'),
  (345,36,'Mahindra'),
  (346,36,'Willy'),
  (347,36,'Wrangler'),
  (349,37,'Carens'),
  (350,37,'Carnival'),
  (351,37,'Cerato'),
  (352,37,'Clarus'),
  (353,37,'Joice'),
  (354,37,'Magentis'),
  (355,37,'Opirus'),
  (356,37,'Picanto'),
  (357,37,'Pride'),
  (358,37,'Rio'),
  (359,37,'Sephia'),
  (360,37,'Shuma'),
  (361,37,'Sorento'),
  (362,37,'Sportage'),
  (364,38,'Niva'),
  (365,38,'Stawra'),
  (366,38,'Vaz'),
  (368,39,'Countach'),
  (369,39,'Diablo'),
  (370,39,'Gallardo'),
  (371,39,'Murcielago'),
  (372,39,'Urraco'),
  (374,40,'A 112'),
  (375,40,'Beta'),
  (376,40,'Dedra'),
  (377,40,'Delta'),
  (378,40,'Fulvia'),
  (379,40,'Kappa'),
  (380,40,'Lybra'),
  (381,40,'Musa'),
  (382,40,'Phedra'),
  (383,40,'Prisma'),
  (384,40,'Thema'),
  (385,40,'Thesis'),
  (386,40,'Trevi'),
  (387,40,'Y 10'),
  (388,40,'Ypsilon'),
  (389,40,'Zeta'),
  (391,41,'Defender'),
  (392,41,'Discovery'),
  (393,41,'Freelander'),
  (394,41,'Range Rover'),
  (395,41,'Santana'),
  (397,42,'GS'),
  (398,42,'IS'),
  (399,42,'LS 400'),
  (400,42,'LS 430'),
  (401,42,'RX'),
  (402,42,'RX-300'),
  (403,42,'SC'),
  (405,43,'505'),
  (406,43,'Ambra'),
  (407,43,'Be-Two'),
  (408,43,'Beup'),
  (409,43,'City'),
  (410,43,'Fiora'),
  (411,43,'JS 27'),
  (412,43,'Nova'),
  (413,43,'Two'),
  (414,43,'X-Pro'),
  (415,43,'X-Too'),
  (416,43,'Zenith'),
  (417,43,'Zenith'),
  (419,44,'Elise'),
  (420,44,'Esprit'),
  (422,46,'222'),
  (423,46,'224'),
  (424,46,'228'),
  (425,46,'3200'),
  (426,46,'430'),
  (427,46,'Biturbo'),
  (428,46,'Coupe'),
  (429,46,'Ghibli'),
  (430,46,'Gran Sport'),
  (431,46,'Quattroporte'),
  (432,46,'Shamal'),
  (433,46,'Spyder'),
  (435,47,'Maybach'),
  (437,48,'121'),
  (438,48,'323'),
  (439,48,'626'),
  (440,48,'929'),
  (441,48,'Demio'),
  (442,48,'Mazda 2'),
  (443,48,'Mazda 3'),
  (444,48,'Mazda 6'),
  (445,48,'MPV'),
  (446,48,'MX-3'),
  (447,48,'MX-5'),
  (448,48,'MX-6'),
  (449,48,'Pick-Up'),
  (450,48,'Premacy'),
  (451,48,'RX 7'),
  (452,48,'RX-8'),
  (453,48,'Tribute'),
  (454,48,'Xedos 6'),
  (455,48,'Xedos 9'),
  (457,49,'CE'),
  (458,49,'CL'),
  (459,49,'Clase A'),
  (460,49,'Clase B'),
  (461,49,'Clase C'),
  (462,49,'Clase E'),
  (463,49,'Clase M'),
  (464,49,'Clase R'),
  (465,49,'Clase S'),
  (466,49,'Clase V'),
  (467,49,'CLK'),
  (468,49,'CLS'),
  (469,49,'G'),
  (470,49,'GE'),
  (471,49,'ML'),
  (472,49,'SE'),
  (473,49,'SEC'),
  (474,49,'SEL'),
  (475,49,'SL'),
  (476,49,'SLK'),
  (477,49,'SLR'),
  (478,49,'Vaneo'),
  (479,49,'Viano'),
  (481,50,'SV-R'),
  (482,50,'TF'),
  (483,50,'ZR'),
  (484,50,'ZS'),
  (485,50,'ZT'),
  (487,51,'Lira'),
  (488,51,'MC1'),
  (489,51,'MC2'),
  (490,51,'Tasso'),
  (491,51,'Virgo'),
  (493,52,'Cooper'),
  (494,52,'Mini'),
  (495,52,'Moke'),
  (496,52,'One'),
  (498,53,'3000 GT'),
  (499,53,'Carisma'),
  (500,53,'Colt'),
  (501,53,'Eclipse'),
  (502,53,'Galant'),
  (503,53,'Grandis'),
  (504,53,'GS'),
  (505,53,'L200'),
  (506,53,'Lancer Evolution'),
  (507,53,'Montero'),
  (508,53,'Outlander'),
  (509,53,'Pajero'),
  (510,53,'Space'),
  (512,54,'Plus 4'),
  (513,54,'Plus 8'),
  (515,55,'100 NX'),
  (516,55,'2.0 TD'),
  (517,55,'200 SX'),
  (518,55,'300 ZX'),
  (519,55,'350 Z'),
  (520,55,'Almera'),
  (521,55,'Bluebird'),
  (522,55,'Infinity'),
  (523,55,'Maxima'),
  (524,55,'Micra'),
  (525,55,'Murano'),
  (526,55,'Navara'),
  (527,55,'Pathfinder'),
  (528,55,'Patrol'),
  (529,55,'Pick-up'),
  (530,55,'Prairie'),
  (531,55,'Primera'),
  (532,55,'Serena'),
  (533,55,'Sunny'),
  (534,55,'SX'),
  (535,55,'Terrano'),
  (536,55,'X-Trail'),
  (538,56,'Agila'),
  (539,56,'Ascona'),
  (540,56,'Astra'),
  (541,56,'Calibra'),
  (542,56,'Corsa'),
  (543,56,'Frontera'),
  (544,56,'Kadett'),
  (545,56,'Manta B'),
  (546,56,'Meriva'),
  (547,56,'Monterey'),
  (548,56,'Monza'),
  (549,56,'Omega'),
  (550,56,'Rekord E'),
  (551,56,'Senator B'),
  (552,56,'Signum'),
  (553,56,'Sintra');

COMMIT;

#
# Data for the `modeloscoche` table  (LIMIT 500,500)
#

INSERT INTO `modeloscoche` (`IdModelo`, `IdMarca`, `NombreModelo`) VALUES 
  (554,56,'Speedster'),
  (555,56,'Tigra'),
  (556,56,'Vectra'),
  (557,56,'Zafira'),
  (559,57,'1007'),
  (560,57,'106'),
  (561,57,'107'),
  (562,57,'205'),
  (563,57,'206'),
  (564,57,'207'),
  (565,57,'306'),
  (566,57,'307'),
  (567,57,'309'),
  (568,57,'405'),
  (569,57,'406'),
  (570,57,'407'),
  (571,57,'505'),
  (572,57,'604'),
  (573,57,'605'),
  (574,57,'607'),
  (575,57,'806'),
  (576,57,'807'),
  (578,58,'Ape'),
  (580,59,'Fiero'),
  (581,59,'Firebird'),
  (582,59,'Sunbird'),
  (583,59,'Trans'),
  (585,60,'356'),
  (586,60,'911'),
  (587,60,'924'),
  (588,60,'928'),
  (589,60,'944'),
  (590,60,'968'),
  (591,60,'993'),
  (592,60,'996'),
  (593,60,'997'),
  (594,60,'Boxster'),
  (595,60,'Cayenne'),
  (596,60,'Cayman'),
  (598,61,'Alpine'),
  (599,61,'Avantime'),
  (600,61,'Clio'),
  (601,61,'Espace'),
  (602,61,'Fuego'),
  (603,61,'Gran Espace'),
  (604,61,'Grand Scenic'),
  (605,61,'Laguna'),
  (606,61,'Megane'),
  (607,61,'Modus'),
  (608,61,'R11'),
  (609,61,'R18'),
  (610,61,'R19'),
  (611,61,'R21'),
  (612,61,'R25'),
  (613,61,'R4'),
  (614,61,'R5'),
  (615,61,'R6'),
  (616,61,'R9'),
  (617,61,'Safrane'),
  (618,61,'Scenic'),
  (619,61,'Spider'),
  (620,61,'Twingo'),
  (621,61,'Vel Satis'),
  (623,62,'Corniche'),
  (624,62,'Park Ward'),
  (625,62,'Phantom'),
  (626,62,'Silver'),
  (627,62,'Touring Limousine'),
  (629,63,'100'),
  (630,63,'200'),
  (631,63,'214'),
  (632,63,'220'),
  (633,63,'25'),
  (634,63,'400'),
  (635,63,'416'),
  (636,63,'45'),
  (637,63,'600'),
  (638,63,'75'),
  (639,63,'800'),
  (640,63,'Mini'),
  (641,63,'SD'),
  (642,63,'Streetwise'),
  (644,64,'900'),
  (645,64,'9000'),
  (646,64,'38785'),
  (647,64,'38846'),
  (649,65,'124'),
  (650,65,'600'),
  (651,65,'Alhambra'),
  (652,65,'Altea'),
  (653,65,'Arosa'),
  (654,65,'Cordoba'),
  (655,65,'Ibiza'),
  (656,65,'Inca'),
  (657,65,'Leon'),
  (658,65,'Malaga'),
  (659,65,'Marbella'),
  (660,65,'Ronda'),
  (661,65,'Toledo'),
  (663,66,'103 Coupe'),
  (664,66,'Fabia'),
  (665,66,'Favorit'),
  (666,66,'Felicia'),
  (667,66,'Forman'),
  (668,66,'Octavia'),
  (669,66,'S 120'),
  (670,66,'Superb'),
  (672,67,'crossblade'),
  (673,67,'Forfour'),
  (674,67,'Fortwo'),
  (675,67,'Roadster'),
  (676,67,'Smart'),
  (678,68,'Korando'),
  (679,68,'Kyron'),
  (680,68,'Musso'),
  (681,68,'Rexton'),
  (682,68,'Rodius'),
  (683,68,'Sports Pick Up'),
  (685,69,'1800'),
  (686,69,'Forester'),
  (687,69,'GX'),
  (688,69,'Impreza'),
  (689,69,'Justy'),
  (690,69,'Legacy'),
  (691,69,'Outback'),
  (692,69,'SVX'),
  (693,69,'XT'),
  (695,70,'Alto'),
  (696,70,'Baleno'),
  (697,70,'Grand Vitara'),
  (698,70,'Ignis'),
  (699,70,'Jimny'),
  (700,70,'JLX'),
  (701,70,'Liana'),
  (702,70,'Maruti'),
  (703,70,'Samurai'),
  (704,70,'Santana'),
  (705,70,'Swift'),
  (706,70,'Vitara'),
  (707,70,'Wagon R+'),
  (709,71,'Horizon'),
  (710,71,'Samba'),
  (711,71,'Solara'),
  (712,71,'Tagora'),
  (714,72,'Domino'),
  (716,73,'Grand Safari'),
  (717,73,'Indica'),
  (718,73,'Indigo'),
  (719,73,'Safari'),
  (720,73,'Solara'),
  (721,73,'Sumo'),
  (722,73,'Telcoline'),
  (723,73,'Telcosport'),
  (725,74,'4 Runner'),
  (726,74,'Avensis'),
  (727,74,'Aygo'),
  (728,74,'Camry'),
  (729,74,'Carina E'),
  (730,74,'Celica'),
  (731,74,'Corolla'),
  (732,74,'FJ'),
  (733,74,'FZJ'),
  (734,74,'Hilux'),
  (735,74,'HZJ '),
  (736,74,'Land Cruiser'),
  (737,74,'MR 2'),
  (738,74,'Paseo'),
  (739,74,'Picnic'),
  (740,74,'Previa'),
  (741,74,'Prius'),
  (742,74,'RAV-4'),
  (743,74,'Supra'),
  (744,74,'Yaris'),
  (746,75,'Beetle'),
  (747,75,'Bora'),
  (748,75,'Caravelle'),
  (749,75,'Corrado'),
  (750,75,'Eos'),
  (751,75,'Fox'),
  (752,75,'Golf'),
  (753,75,'Jetta'),
  (754,75,'Lupo'),
  (755,75,'Passat'),
  (756,75,'Phaeton'),
  (757,75,'Polo'),
  (758,75,'Santana'),
  (759,75,'Scirocco'),
  (760,75,'Sharan'),
  (761,75,'Syncro'),
  (762,75,'Touareg'),
  (763,75,'Touran'),
  (764,75,'Vento'),
  (766,76,'240'),
  (767,76,'340'),
  (768,76,'360'),
  (769,76,'440'),
  (770,76,'460'),
  (771,76,'480'),
  (772,76,'740'),
  (773,76,'760'),
  (774,76,'780'),
  (775,76,'850'),
  (776,76,'940'),
  (777,76,'960'),
  (778,76,'C70'),
  (779,76,'Polar'),
  (780,76,'S40'),
  (781,76,'S60'),
  (782,76,'S70'),
  (783,76,'S80'),
  (784,76,'S90'),
  (785,76,'V40'),
  (786,76,'V50'),
  (787,76,'V70'),
  (788,76,'V90'),
  (789,76,'XC 70'),
  (790,76,'XC 90'),
  (791,76,'XV'),
  (793,77,'353');

COMMIT;

#
# Structure for the `palabrota` table : 
#

DROP TABLE IF EXISTS `palabrota`;

CREATE TABLE `palabrota` (
  `PALABROTA_ID` int(11) NOT NULL auto_increment,
  `PALABROTA_NAME` varchar(255) default NULL,
  PRIMARY KEY  (`PALABROTA_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for the `palabrota` table  (LIMIT 0,500)
#

INSERT INTO `palabrota` (`PALABROTA_ID`, `PALABROTA_NAME`) VALUES 
  (1,'puta'),
  (2,'hijo de puta');

COMMIT;

#
# Structure for the `usuario` table : 
#

DROP TABLE IF EXISTS `usuario`;

CREATE TABLE `usuario` (
  `USUARIO_ID` int(11) NOT NULL auto_increment,
  `USUARIO_NAME` varchar(20) NOT NULL,
  `USUARIO_PASS` varchar(20) NOT NULL,
  PRIMARY KEY  (`USUARIO_ID`),
  UNIQUE KEY `USUARIO_NAME` (`USUARIO_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for the `usuario` table  (LIMIT 0,500)
#

INSERT INTO `usuario` (`USUARIO_ID`, `USUARIO_NAME`, `USUARIO_PASS`) VALUES 
  (1,'admin','bOrtxUs!'),
  (2,'a','a');

COMMIT;

#
# Structure for the `visitaweb` table : 
#

DROP TABLE IF EXISTS `visitaweb`;

CREATE TABLE `visitaweb` (
  `VISITAWEB_ID` int(11) NOT NULL auto_increment,
  `VISITAWEB_NAME` varchar(20) default NULL,
  `FECHA_VISITA` datetime default NULL,
  PRIMARY KEY  (`VISITAWEB_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for the `visitaweb` table  (LIMIT 0,500)
#

INSERT INTO `visitaweb` (`VISITAWEB_ID`, `VISITAWEB_NAME`, `FECHA_VISITA`) VALUES 
  (1,'0:0:0:0:0:0:0:1','2011-02-27 17:20:12'),
  (2,'192.168.1.34','2011-02-27 17:15:48');

COMMIT;

