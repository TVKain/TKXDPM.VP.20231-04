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
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(32),
    `password`VARCHAR(32) NOT NULL,
    status VARCHAR(32) NOT NULL
);

-- Account Data
INSERT INTO Account(email, password, status)
VALUES
("tvkain.it@gmail.com", "1", "ACTIVE"),
("vinhkhanh2611@gmail.com", "1", "ACTIVE"),
("khanhngocdam@gmail.com", "1", "ACTIVE");

-- Account Role (many to many)
CREATE TABLE IF NOT EXISTS AccountRole (
    accountId INTEGER,
    roleName VARCHAR(32),
    PRIMARY KEY (accountId, roleName),
    FOREIGN KEY (roleName) REFERENCES Role(roleName),
    FOREIGN KEY (accountId) REFERENCES Account(id) ON DELETE CASCADE
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
  `id` INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL,
  `category` VARCHAR(45) NOT NULL,
  `price` INTEGER NOT NULL,
  `quantity` INTEGER NOT NULL,
  `weight` FLOAT NOT NULL,
  `title` VARCHAR(45) NOT NULL,
  `value` INTEGER NOT NULL,
  `imageUrl` VARCHAR(45) NOT NULL,
  `rushDelivery` INTEGER NOT NULL
);


-- Order
CREATE TABLE IF NOT EXISTS `Order` (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    status VARCHAR(16)
);


CREATE TABLE IF NOT EXISTS OrderMedia (
    orderId INTEGER,
    mediaId INTEGER,
    quantity INTEGER,
    PRIMARY KEY (orderId, mediaId),
    FOREIGN KEY (orderId) REFERENCES `Order`(id),
    FOREIGN KEY (mediaId) REFERENCES Media(id)
);

-- Delivery Info
CREATE TABLE IF NOT EXISTS DeliveryInfo (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(64),
    phone VARCHAR(20),
    city VARCHAR(64),
    email VARCHAR(64),
    address VARCHAR(64),
    instruction VARCHAR(32)
);

CREATE TABLE IF NOT EXISTS RushDeliveryInfo (
    id INTEGER PRIMARY KEY,
    rushInstruction VARCHAR(64),
    rushTime INTEGER,
    FOREIGN KEY (id) REFERENCES DeliveryInfo(id)
);


-- Invoice
CREATE TABLE IF NOT EXISTS Invoice (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    mediaTotal DOUBLE,
    mediaSubtotal DOUBLE,
    vat DOUBLE,
    shippingFee DOUBLE,
    total DOUBLE,
    orderId INTEGER,
    deliveryInfoId INTEGER,
    FOREIGN KEY (orderId) REFERENCES `Order`(id),
    FOREIGN KEY (deliveryInfoId) REFERENCES DeliveryInfo(id)
);


-- Table: "Book"
CREATE TABLE `Book` (
    `id` INTEGER PRIMARY KEY AUTO_INCREMENT,
    `author` VARCHAR(45) NOT NULL,
    `publisher` VARCHAR(45) NOT NULL,
    `publish_date` DATE NOT NULL,
    `numer_of_page` INTEGER,
    `book_category` VARCHAR(45),
    `cover_type` VARCHAR(45) NOT NULL,
    `language` VARCHAR(45),
    FOREIGN KEY(`id`) REFERENCES `Media`(`id`)
);

-- Table: "CD"
CREATE TABLE `CD` (
    `id` INTEGER PRIMARY KEY AUTO_INCREMENT,
    `artist` VARCHAR(45) NOT NULL,
    `music_type` VARCHAR(45) NOT NULL,
    `record_label` VARCHAR(45) NOT NULL,
    `category_cd` VARCHAR(45) NOT NULL,
    `release_date` DATE,
    FOREIGN KEY(`id`) REFERENCES `Media`(`id`)
);

-- Table: "DVD"
CREATE TABLE `DVD` (
    `id` INTEGER PRIMARY KEY AUTO_INCREMENT,
    `studio` VARCHAR(45) NOT NULL,
    `disc_type` VARCHAR(45) NOT NULL,
    `subtitle` VARCHAR(45) NOT NULL,
    `language` VARCHAR(45) NOT NULL,
    `runtime` VARCHAR(45) NOT NULL,
    `director` VARCHAR(45) NOT NULL,
    `release_date` DATE,
    `dvd_category` VARCHAR(45),
    FOREIGN KEY(`id`) REFERENCES `Media`(`id`)
);
-- Table: "Track"
CREATE TABLE `Track` (
    `id` INTEGER PRIMARY KEY,
    `name` VARCHAR(45) NOT NULL,
    `CD_id` INTEGER NOT NULL,
    FOREIGN KEY (`CD_id`) REFERENCES `CD` (`id`)
);


-- DATA Book
INSERT INTO Media VALUES(38,'story',32000,12,1.2,'book2',29000,'/image/book/book2.jpg',1);
INSERT INTO Book VALUES(38,'author2','publisher2','2023-12-29',300, 'category2', 'covertype2', 'english');
INSERT INTO Media VALUES(39,'adventure',21000,2,0.8,'book9',20000,'/image/book/book9.jpg',0);
INSERT INTO Book VALUES(39,'author9','publisher9','2023-12-29',300, 'category9', 'covertype9', 'english');
-- Data DVD
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