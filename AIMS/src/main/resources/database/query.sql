CREATE TABLE IF NOT EXISTS Role (
    id INTEGER PRIMARY KEY,
    roleName VARCHAR(32) NOT NULL
);

INSERT INTO Role(id, roleName)
VALUES
(1, "admin"),
(2, "manager");

CREATE TABLE IF NOT EXISTS Account (
    username VARCHAR(32) PRIMARY KEY,
    password VARCHAR(32) NOT NULL,
    roleId INTEGER NOT NULL,
    FOREIGN KEY (roleId) REFERENCES Role(id)
);

INSERT INTO Account(username, password, roleId)
VALUES
("khanhtv", "1", 1),
("khanhnd", "1", 2),
("khoalnd", "1", 1);


CREATE TABLE IF NOT EXISTS Admin (
    username VARCHAR(32) PRIMARY KEY,
    password VARCHAR(32) NOT NULL
);


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
  "rushDelivery" INTEGER NOT NULL
);

-- Table: "Book"
CREATE TABLE "Book" (
    "id" INTEGER PRIMARY KEY AUTOINCREMENT,
    "author" VARCHAR(45) NOT NULL,
    "publisher" VARCHAR(45) NOT NULL,
    "publisher_date" DATETIME NOT NULL,
    "numer_of_page" INTEGER,
    "book_category" VARCHAR(45),
    "cover_type" VARCHAR(45) NOT NULL,
    "language" VARCHAR(45),
    FOREIGN KEY("id")
    REFERENCES "Media"("id")
);

-- Table: "CD"
CREATE TABLE "CD" (
    "id" INTEGER PRIMARY KEY AUTOINCREMENT,
    "artist" VARCHAR(45) NOT NULL,
    "music_type" VARCHAR(45) NOT NULL,
    "record_label" VARCHAR(45) NOT NULL,
    "category_cd" VARCHAR(45) NOT NULL,
    "release_date" DATETIME,
    FOREIGN KEY("id")
    REFERENCES "Media"("id")
);

-- Table: "DVD"
CREATE TABLE "DVD" (
    "id" INTEGER PRIMARY KEY AUTOINCREMENT,
    "studio" VARCHAR(45) NOT NULL,
    "disc_type" VARCHAR(45) NOT NULL,
    "subtitle" VARCHAR(45) NOT NULL,
    "language" VARCHAR(45) NOT NULL,
    "runtime" VARCHAR(45) NOT NULL,
    "director" VARCHAR(45) NOT NULL,
    "release_date" DATETIME,
    "dvd_category" VARCHAR(45),
    FOREIGN KEY("id")
    REFERENCES "Media"("id")
);
-- Table: "Track"
CREATE TABLE "Track" (
    "id" INTEGER PRIMARY KEY,
    "name" VARCHAR(45) NOT NULL,
    "CD_id" INTEGER NOT NULL,
    FOREIGN KEY ("CD_id") REFERENCES "CD" ("id")
);



INSERT INTO Media VALUES(38,'book','story',32,12,1.2,'book2',29,'/image/book/book2.jpg',1);
INSERT INTO Media VALUES(39,'book','adventure',21,2,0.8,'book9',20,'/image/book/book9.jpg',0);
INSERT INTO Media VALUES(40,'book','adventure',73,11,0.5,'book10',69,'/image/book/book10.jpg',1);
INSERT INTO Media VALUES(41,'book','story',66,2,1.1,'book6',62,'/image/book/book6.jpg',1);
INSERT INTO Media VALUES(42,'cd','pop',24,6,1.0,'cd7',20,'/image/cd/cd7.jpg',0);
INSERT INTO Media VALUES(43,'book','story',50,7,0.5,'book12',44,'/image/book/book12.jpg',0);
INSERT INTO Media VALUES(44,'book','story',57,10,0.6,'book4',53,'/image/book/book4.jpg',1);
INSERT INTO Media VALUES(45,'cd','pop',66,8,1.0,'cd3',60,'/image/cd/cd3.jpg',1);
INSERT INTO Media VALUES(46,'book','bussiness',79,17,0.5,'book1',72,'/image/book/book1.jpg',1);
INSERT INTO Media VALUES(47,'dvd','cartoon',82,1,0.4,'dvd12',78,'/image/dvd/dvd12.jpg',0);
INSERT INTO Media VALUES(48,'book','science',25,10,0.6,'book3',22,'/image/book/book3.jpg',0);
INSERT INTO Media VALUES(49,'dvd','science fiction',75,3,0.6,'dvd10',74,'/image/dvd/dvd10.jpg',1);
INSERT INTO Media VALUES(50,'book','bussiness',26,4,0.5,'book11',19,'/image/book/book11.jpg',1);
INSERT INTO Media VALUES(51,'dvd','action',61,18,0.7,'dvd11',52,'/image/dvd/dvd11.jpg',0);
INSERT INTO Media VALUES(52,'cd','rock',40,4,0.5,'cd4',35,'/image/cd/cd4.jpg',1);
INSERT INTO Media VALUES(53,'dvd','action',70,16,0.6,'dvd9',60,'/image/dvd/dvd9.jpg',0);
INSERT INTO Media VALUES(54,'dvd','romance',47,19,0.7,'dvd2',39,'/image/dvd/dvd2.jpg',0);
INSERT INTO Media VALUES(55,'cd','pop',74,6,0.4,'cd2',71,'/image/cd/cd2.jpg',0);
INSERT INTO Media VALUES(56,'cd','rock',70,20,0.6,'cd1',60,'/image/cd/cd1.jpg',1);
INSERT INTO Media VALUES(57,'book','adventure',38,2,0.4,'book8',36,'/image/book/book8.jpg',1);
INSERT INTO Media VALUES(58,'dvd','cartoon',55,13,0.3,'dvd3',51,'/image/dvd/dvd3.jpg',1);
INSERT INTO Media VALUES(59,'dvd','action',28,1,0.3,'dvd6',26,'/image/dvd/dvd6.jpg',1);
INSERT INTO Media VALUES(60,'dvd','romance',38,17,0.3,'dvd4',33,'/image/dvd/dvd4.jpg',0);
INSERT INTO Media VALUES(61,'cd','pop',42,15,0.6,'cd12',38,'/image/cd/cd12.jpg',0);
INSERT INTO Media VALUES(62,'book','bussiness',34,15,0.6,'book5',29,'/image/book/book5.jpg',1);
INSERT INTO Media VALUES(63,'cd','ballad',99,4,0.6,'cd5',92,'/image/cd/cd5.jpg',0);
INSERT INTO Media VALUES(64,'cd','pop',38,10,0.6,'cd8',32,'/image/cd/cd8.jpg',0);
INSERT INTO Media VALUES(65,'cd','classic',37,10,0.6,'cd6',31,'/image/cd/cd6.jpg',1);
INSERT INTO Media VALUES(66,'book','bussiness',93,15,0.6,'book7',88,'/image/book/book7.jpg',1);
INSERT INTO Media VALUES(67,'cd','classic',25,5,0.6,'cd9',23,'/image/cd/cd9.jpg',1);
INSERT INTO Media VALUES(68,'dvd','romance',71,4,0.4,'dvd5',64,'/image/dvd/dvd5.jpg',0);
INSERT INTO Media VALUES(69,'cd','pop',97,17,0.4,'cd10',89,'/image/cd/cd10.jpg',0);
INSERT INTO Media VALUES(70,'dvd','romance',47,19,0.4,'dvd8',37,'/image/dvd/dvd8.jpg',1);
INSERT INTO Media VALUES(71,'dvd','science fiction',95,11,0.4,'dvd1',92,'/image/dvd/dvd1.jpg',0);
INSERT INTO Media VALUES(72,'dvd','action',23,9,0.7,'dvd7',16,'/image/dvd/dvd7.jpg',1);
INSERT INTO Media VALUES(73,'cd','classic',28,3,0.6,'cd11',20,'/image/cd/cd11.jpg',1);