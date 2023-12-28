CREATE TABLE IF NOT EXISTS Admin (
    username VARCHAR(32) PRIMARY KEY,
    password VARCHAR(32) NOT NULL
);

INSERT INTO Admin (username, password)
VALUES
('khanhtv', '1'),
('khanhnd', '1'),
('khoalnd', '1');

CREATE TABLE IF NOT EXISTS "Media"(
  "id" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
  "type" VARCHAR(45) NOT NULL,
  "category" VARCHAR(45) NOT NULL,
  "price" INTEGER NOT NULL,
  "quantity" INTEGER NOT NULL,
  "weight" FLOAT NOT NULL,
  "title" VARCHAR(45) NOT NULL,
  "value" INTEGER NOT NULL,
  "imageUrl" VARCHAR(45) NOT NULL,
  --"rushDelivery" INTEGER NOT NULL
);

INSERT INTO Media VALUES(38,'book','story',32,12,1.2,'book2',29,'/image/book/book2.jpg');
INSERT INTO Media VALUES(39,'book','adventure',21,2,0.8,'book9',20,'/image/book/book9.jpg');
INSERT INTO Media VALUES(40,'book','adventure',73,11,0.5,'book10',69,'/image/book/book10.jpg');
INSERT INTO Media VALUES(41,'book','story',66,2,1.1,'book6',62,'/image/book/book6.jpg');
INSERT INTO Media VALUES(42,'cd','pop',24,6,1.0,'cd7',20,'/image/cd/cd7.jpg');
INSERT INTO Media VALUES(43,'book','story',50,7,0.5,'book12',44,'/image/book/book12.jpg');
INSERT INTO Media VALUES(44,'book','story',57,10,0.6,'book4',53,'/image/book/book4.jpg');
INSERT INTO Media VALUES(45,'cd','pop',66,8,1.0,'cd3',60,'/image/cd/cd3.jpg');
INSERT INTO Media VALUES(46,'book','bussiness',79,17,0.5,'book1',72,'/image/book/book1.jpg');
INSERT INTO Media VALUES(47,'dvd','cartoon',82,1,0.4,'dvd12',78,'/image/dvd/dvd12.jpg');
INSERT INTO Media VALUES(48,'book','science',25,10,0.6,'book3',22,'/image/book/book3.jpg');
INSERT INTO Media VALUES(49,'dvd','science fiction',75,3,0.6,'dvd10',74,'/image/dvd/dvd10.jpg');
INSERT INTO Media VALUES(50,'book','bussiness',26,4,0.5,'book11',19,'/image/book/book11.jpg');
INSERT INTO Media VALUES(51,'dvd','action',61,18,0.7,'dvd11',52,'/image/dvd/dvd11.jpg');
INSERT INTO Media VALUES(52,'cd','rock',40,4,0.5,'cd4',35,'/image/cd/cd4.jpg');
INSERT INTO Media VALUES(53,'dvd','action',70,16,0.6,'dvd9',60,'/image/dvd/dvd9.jpg');
INSERT INTO Media VALUES(54,'dvd','romance',47,19,0.7,'dvd2',39,'/image/dvd/dvd2.jpg');
INSERT INTO Media VALUES(55,'cd','pop',74,6,0.4,'cd2',71,'/image/cd/cd2.jpg');
INSERT INTO Media VALUES(56,'cd','rock',70,20,0.6,'cd1',60,'/image/cd/cd1.jpg');
INSERT INTO Media VALUES(57,'book','adventure',38,2,0.4,'book8',36,'/image/book/book8.jpg');
INSERT INTO Media VALUES(58,'dvd','cartoon',55,13,0.3,'dvd3',51,'/image/dvd/dvd3.jpg');
INSERT INTO Media VALUES(59,'dvd','action',28,1,0.3,'dvd6',26,'/image/dvd/dvd6.jpg');
INSERT INTO Media VALUES(60,'dvd','romance',38,17,0.3,'dvd4',33,'/image/dvd/dvd4.jpg');
INSERT INTO Media VALUES(61,'cd','pop',42,15,0.6,'cd12',38,'/image/cd/cd12.jpg');
INSERT INTO Media VALUES(62,'book','bussiness',34,15,0.6,'book5',29,'/image/book/book5.jpg');
INSERT INTO Media VALUES(63,'cd','ballad',99,4,0.6,'cd5',92,'/image/cd/cd5.jpg');
INSERT INTO Media VALUES(64,'cd','pop',38,10,0.6,'cd8',32,'/image/cd/cd8.jpg');
INSERT INTO Media VALUES(65,'cd','classic',37,10,0.6,'cd6',31,'/image/cd/cd6.jpg');
INSERT INTO Media VALUES(66,'book','bussiness',93,15,0.6,'book7',88,'/image/book/book7.jpg');
INSERT INTO Media VALUES(67,'cd','classic',25,5,0.6,'cd9',23,'/image/cd/cd9.jpg');
INSERT INTO Media VALUES(68,'dvd','romance',71,4,0.4,'dvd5',64,'/image/dvd/dvd5.jpg');
INSERT INTO Media VALUES(69,'cd','pop',97,17,0.4,'cd10',89,'/image/cd/cd10.jpg');
INSERT INTO Media VALUES(70,'dvd','romance',47,19,0.4,'dvd8',37,'/image/dvd/dvd8.jpg');
INSERT INTO Media VALUES(71,'dvd','science fiction',95,11,0.4,'dvd1',92,'/image/dvd/dvd1.jpg');
INSERT INTO Media VALUES(72,'dvd','action',23,9,0.7,'dvd7',16,'/image/dvd/dvd7.jpg');
INSERT INTO Media VALUES(73,'cd','classic',28,3,0.6,'cd11',20,'/image/cd/cd11.jpg');