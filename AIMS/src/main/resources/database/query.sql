-- Role
CREATE TABLE IF NOT EXISTS Role (
    roleName VARCHAR(32) PRIMARY KEY
);

-- Data Role
INSERT INTO Role(roleName)
VALUES
("admin"),
("manager");

-- Account
CREATE TABLE IF NOT EXISTS Account (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    email VARCHAR(32),
    password VARCHAR(32) NOT NULL,
    status VARCHAR(32) NOT NULL
);

-- Account Data
INSERT INTO Account(email, password, status)
VALUES
("tvkain.it@gmail.com", "12345678", "ACTIVE"),
("vinhkhanh2611@gmail.com", "12345678", "ACTIVE"),
("khanhngocdam@gmail.com", "12345678", "ACTIVE");

-- Account Role (many to many)
CREATE TABLE IF NOT EXISTS AccountRole (
    accountId INTEGER,
    roleName VARCHAR(32),
    PRIMARY KEY (accountId, roleName),
    FOREIGN KEY (roleName) REFERENCES Role(roleName),
    FOREIGN KEY (accountId) REFERENCES Account(id)
);

-- Account Role data
INSERT INTO AccountRole(accountId, roleName)
VALUES
(1, "admin"),
(1, "manager"),
(2, "manager"),
(3, "admin"),
(3, "manager");


CREATE TABLE IF NOT EXISTS Media(
  "id" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
  "category" VARCHAR(45) NOT NULL,
  "price" INTEGER NOT NULL,
  "quantity" INTEGER NOT NULL,
  "weight" FLOAT NOT NULL,
  "title" VARCHAR(45) NOT NULL,
  "value" INTEGER NOT NULL,
  "imageUrl" VARCHAR(45) NOT NULL,
  "rushDelivery" INTEGER NOT NULL
);


-- Order
CREATE TABLE IF NOT EXISTS "Order" (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    status VARCHAR(16)
);


CREATE TABLE IF NOT EXISTS OrderMedia (
    orderId INTEGER,
    mediaId INTEGER,
    quantity INTEGER,
    PRIMARY KEY (orderId, mediaId),
    FOREIGN KEY (orderId) REFERENCES "Order"(id),
    FOREIGN KEY (mediaId) REFERENCES Media(id)
);

-- Delivery Info
CREATE TABLE IF NOT EXISTS DeliveryInfo (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    "name" VARCHAR(64),
    phone VARCHAR(20),
    city VARCHAR(64),
    email VARCHAR(64),
    address VARCHAR(64),
    instruction VARCHAR(32),
    FOREIGN KEY (id) REFERENCES Invoice(id)
);

CREATE TABLE IF NOT EXISTS RushDeliveryInfo (
    id INTEGER PRIMARY KEY,
    rushInstruction VARCHAR(64),
    rushTime INTEGER,
    FOREIGN KEY (id) REFERENCES DeliveryInfo(id)
);


-- Invoice
CREATE TABLE IF NOT EXISTS Invoice (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    mediaTotal DOUBLE,
    mediaSubtotal DOUBLE,
    vat DOUBLE,
    shippingFee DOUBLE,
    total DOUBLE,
    orderId INTEGER,
    deliveryInfoId INTEGER,
    FOREIGN KEY (orderId) REFERENCES "Order"(id),
    FOREIGN KEY (deliveryInfoId) REFERENCES DeliveryInfo(id)
);


-- Table: "Book"
CREATE TABLE "Book" (
    "id" INTEGER PRIMARY KEY AUTOINCREMENT,
    "author" VARCHAR(45) NOT NULL,
    "publisher" VARCHAR(45) NOT NULL,
    "publish_date" DATE NOT NULL,
    "numer_of_page" INTEGER,
    "book_category" VARCHAR(45),
    "cover_type" VARCHAR(45) NOT NULL,
    "language" VARCHAR(45),
    FOREIGN KEY("id") REFERENCES "Media"("id")
);

-- Table: "CD"
CREATE TABLE "CD" (
    "id" INTEGER PRIMARY KEY AUTOINCREMENT,
    "artist" VARCHAR(45) NOT NULL,
    "music_type" VARCHAR(45) NOT NULL,
    "record_label" VARCHAR(45) NOT NULL,
    "category_cd" VARCHAR(45) NOT NULL,
    "release_date" DATE,
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
    "release_date" DATE,
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


--DATA Book
INSERT INTO Media VALUES(38,'story',32000,12,1.2,'book2',29000,'/image/book/book2.jpg',1);
INSERT INTO Book VALUES(38,'author2','publisher2','2023-12-29',300, 'category2', 'covertype2', 'english');
INSERT INTO Media VALUES(39,'adventure',21000,2,0.8,'book9',20000,'/image/book/book9.jpg',0);
INSERT INTO Book VALUES(39,'author9','publisher9','2023-12-29',300, 'category9', 'covertype9', 'english');
--Data DVD
INSERT INTO Media VALUES(58,'cartoon',55000,13,0.3,'dvd3',51000,'/image/dvd/dvd3.jpg',1);
INSERT INTO DVD VALUES(58,'studio3','type3','subtitle3', 'language3', '1:30', 'director3', '2023-12-29', 'dvd-category3');
INSERT INTO Media VALUES(59,'action',28000,1,0.3,'dvd6',26000,'/image/dvd/dvd6.jpg',1);
INSERT INTO DVD VALUES(59,'studio6','type6','subtitle6', 'language6', '1:30', 'director6', '2023-12-29', 'dvd-category6');
-- Data CD
INSERT INTO Media VALUES(55,'pop',74000,6,0.4,'cd2',71000,'/image/cd/cd2.jpg',0);
INSERT INTO CD VALUES(55,'artist2','music_type2','record_label2','category_cd2', '2023-12-29');
INSERT INTO Track VALUES(1,'track1',55);
INSERT INTO Track VALUES(2,'track2',55);
INSERT INTO Media VALUES(56,'rock',70000,20,0.6,'cd1',60000,'/image/cd/cd1.jpg',1);
INSERT INTO CD VALUES(56,'artist1','music_type1','record_label1','category_cd1', '2023-12-29');
INSERT INTO Track VALUES(3,'track3',56);
INSERT INTO Track VALUES(4,'track4',56);

INSERT INTO Media VALUES(40,'book',73000,11,0.5,'book10',69000,'/image/book/book10.jpg',1);
INSERT INTO Book VALUES(40,'author10','publisher10','2023-12-29',300, 'category10', 'covertype2', 'english');

INSERT INTO Media VALUES(41,'book',660000,2,1.1,'book6',620000,'/image/book/book6.jpg',1);
INSERT INTO Book VALUES(41,'author6','publisher6','2023-12-29',300, 'category6', 'covertype2', 'english');
--INSERT INTO Media VALUES(42,'cd','pop',24,6,1.0,'cd7',20,'/image/cd/cd7.jpg',0);
INSERT INTO Media VALUES(43,'book',50000,7,0.5,'book12',44000,'/image/book/book12.jpg',0);
INSERT INTO Book VALUES(43,'author12','publisher12','2023-12-29',300, 'category12', 'covertype2', 'english');
INSERT INTO Media VALUES(44,'book',57000,10,0.6,'book4',53000,'/image/book/book4.jpg',1);
INSERT INTO Book VALUES(44,'author4','publisher4','2023-12-29',300, 'category4', 'covertype2', 'english');
--INSERT INTO Media VALUES(45,'cd','pop',66,8,1.0,'cd3',60,'/image/cd/cd3.jpg',1);
--INSERT INTO Media VALUES(46,'book','bussiness',79,17,0.5,'book1',72,'/image/book/book1.jpg',1);
INSERT INTO Media VALUES(47,'dvd',82000,1,0.4,'dvd12',78000,'/image/dvd/dvd12.jpg',0);
INSERT INTO DVD VALUES(47,'studio12','type3','subtitle12', 'language3', '1:30', 'director12', '2023-12-29', 'dvd-category12');
--INSERT INTO Media VALUES(48,'book','science',25,10,0.6,'book3',22,'/image/book/book3.jpg',0);
INSERT INTO Media VALUES(49,'dvd',75000,3,0.6,'dvd10',74000,'/image/dvd/dvd10.jpg',1);
INSERT INTO DVD VALUES(49,'studio10','type3','subtitle10', 'language3', '1:30', 'director10', '2023-12-29', 'dvd-category10');
--INSERT INTO Media VALUES(50,'book','bussiness',26,4,0.5,'book11',19,'/image/book/book11.jpg',1);
INSERT INTO Media VALUES(51,'dvd',610000,18,0.7,'dvd11',52000,'/image/dvd/dvd11.jpg',0);
INSERT INTO DVD VALUES(51,'studio11','type3','subtitle11', 'language3', '1:30', 'director11', '2023-12-29', 'dvd-category11');
--INSERT INTO Media VALUES(52,'cd','rock',40,4,0.5,'cd4',35,'/image/cd/cd4.jpg',1);
--INSERT INTO Media VALUES(53,'dvd','action',70,16,0.6,'dvd9',60,'/image/dvd/dvd9.jpg',0);
--INSERT INTO Media VALUES(54,'dvd','romance',47,19,0.7,'dvd2',39,'/image/dvd/dvd2.jpg',0);
--INSERT INTO Media VALUES(55,'cd','pop',74,6,0.4,'cd2',71,'/image/cd/cd2.jpg',0);
--INSERT INTO Media VALUES(56,'cd','rock',70,20,0.6,'cd1',60,'/image/cd/cd1.jpg',1);
--INSERT INTO Media VALUES(57,'book','adventure',38,2,0.4,'book8',36,'/image/book/book8.jpg',1);
--INSERT INTO Media VALUES(58,'dvd','cartoon',55,13,0.3,'dvd3',51,'/image/dvd/dvd3.jpg',1);
--INSERT INTO Media VALUES(59,'dvd','action',28,1,0.3,'dvd6',26,'/image/dvd/dvd6.jpg',1);
--INSERT INTO Media VALUES(60,'dvd','romance',38,17,0.3,'dvd4',33,'/image/dvd/dvd4.jpg',0);
--INSERT INTO Media VALUES(61,'cd','pop',42,15,0.6,'cd12',38,'/image/cd/cd12.jpg',0);
--INSERT INTO Media VALUES(62,'book','bussiness',34,15,0.6,'book5',29,'/image/book/book5.jpg',1);
--INSERT INTO Media VALUES(63,'cd','ballad',99,4,0.6,'cd5',92,'/image/cd/cd5.jpg',0);
--INSERT INTO Media VALUES(64,'cd','pop',38,10,0.6,'cd8',32,'/image/cd/cd8.jpg',0);
--INSERT INTO Media VALUES(65,'cd','classic',37,10,0.6,'cd6',31,'/image/cd/cd6.jpg',1);
--INSERT INTO Media VALUES(66,'book','bussiness',93,15,0.6,'book7',88,'/image/book/book7.jpg',1);
--INSERT INTO Media VALUES(67,'cd','classic',25,5,0.6,'cd9',23,'/image/cd/cd9.jpg',1);
--INSERT INTO Media VALUES(68,'dvd','romance',71,4,0.4,'dvd5',64,'/image/dvd/dvd5.jpg',0);
--INSERT INTO Media VALUES(69,'cd','pop',97,17,0.4,'cd10',89,'/image/cd/cd10.jpg',0);
--INSERT INTO Media VALUES(70,'dvd','romance',47,19,0.4,'dvd8',37,'/image/dvd/dvd8.jpg',1);
--INSERT INTO Media VALUES(71,'dvd','science fiction',95,11,0.4,'dvd1',92,'/image/dvd/dvd1.jpg',0);
--INSERT INTO Media VALUES(72,'dvd','action',23,9,0.7,'dvd7',16,'/image/dvd/dvd7.jpg',1);
--INSERT INTO Media VALUES(73,'cd','classic',28,3,0.6,'cd11',20,'/image/cd/cd11.jpg',1);