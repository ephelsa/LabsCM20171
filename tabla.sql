SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone="+00:00";

CREATE TABLE IF NOT EXISTS Usuario(
id INT(12) NOT NULL AUTO_INCREMENT,
username VARCHAR(20) NOT NULL UNIQUE,
name VARCHAR(50) DEFAULT NULL,
pass VARCHAR(20) NOT NULL,
email VARCHAR(50) NOT NULL ,
age VARCHAR(3) DEFAULT NULL,
photo VARCHAR(30) NOT NULL,
PRIMARY KEY(id)
)ENGINE=InnoBD DEFAULT CHARSET=latin1 AUTO_INCREMENT=11;

CREATE TABLE IF NOT EXISTS Evento(
id INT(12) NOT NULL AUTO_INCREMENT,
name VARCHAR(60) NOT NULL,
response VARCHAR(20) NOT NULL,
score VARCHAR(5) DEFAULT NULL,
fecha DATE NOT NULL,
location VARCHAR(50) NOT NULL,
info VARCHAR(255) NOT NULL,
photo VARCHAR (20) NOT NULL,
PRIMARY KEY(id)
)ENGINE=InnoBD DEFAULT CHARSET=latin1 AUTO_INCREMENT=6;

INSERT INTO Usuario(id,username,name,pass,email,age,photo) VALUES 
(1,"ADMIN","administrador","admin","ADMIN@ADMIN.COM","","ADMINimg.png"),
(5,"JULIAN","Julian correa","123","JULIANM.CORREA@UDEA.EDU.CO","20","JULIANimg.png"),
(4,"Q","TEST","1","TEST@TEST.TEST","99","Qimg.png"),
(10,"LEO","Leito","123","ALGUN@CORREO.COM","","LEOimg.png");

INSERT INTO Evento(id,name,response,score,fecha,location,info,photo) VALUES
(1,"viva la vida","ADMIN","5","2017-05-25","UDEA","El evento mas grandioso del mundo......................hola mundo","img.png"),
(2,"Esto se descontrola","ADMIN","3","2017-04-25","UNAL","Otro evento mas ......................hola mundo","img.png"),
(3,"Feo el que lo lea","JULIAN","5","2017-04-20","REMINGTON","Reunion de feos y engreidos......................hola mundo","img.png"),
(4,"Yo no se mañana","LEO","5","2017-05-25","ITM","SI estaremos juntos si se acaba el mundo mundo","img.png"),
(5,"viva la vida2","ADMIN","5","2017-05-25","UDEA","El evento mas grandioso del mundo por segunda vez......hola mundo","img.png");

