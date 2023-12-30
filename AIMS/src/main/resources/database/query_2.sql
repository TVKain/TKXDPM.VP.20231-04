CREATE TABLE IF NOT EXISTS Role (
    roleName VARCHAR(32) PRIMARY KEY
);

INSERT INTO Role(roleName)
VALUES
("admin"),
("manager");

CREATE TABLE IF NOT EXISTS Account (
    email VARCHAR(32) PRIMARY KEY,
    password VARCHAR(32) NOT NULL
);

INSERT INTO Account(email, password)
VALUES
("tvkain.it@gmail.com", "1"),
("khanhngocdam@gmail.com", "1");

CREATE TABLE IF NOT EXISTS AccountRole (
    email VARCHAR(32),
    roleName INTEGER,
    PRIMARY KEY (email, roleName),
    FOREIGN KEY (roleName) REFERENCES Role(roleName),
    FOREIGN KEY (email) REFERENCES Account(email)
);

INSERT INTO AccountRole(email, roleName)
VALUES
("tvkain.it@gmail.com", "admin"),
("tvkain.it@gmail.com", "manager"),
("khanhngocdam@gmail.com", "admin"),
("khanhngocdam@gmail.com", "manager");


CREATE TABLE IF NOT EXISTS Admin (
    username VARCHAR(32) PRIMARY KEY,
    password VARCHAR(32) NOT NULL
);


CREATE TABLE IF NOT EXISTS "Media"(
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





INSERT INTO Media VALUES(38,'story',32000,12,1.2,'book2',29000,'/image/book/book2.jpg',1);
INSERT INTO Book VALUES(38,'Dam Ngoc Khanh','HUST','2023-09-30',69,'Rom-com','Physical','Arabic');

INSERT INTO Media VALUES(39,'adventure',21000,2,0.8,'book9',20000,'/image/book/book9.jpg',0);
INSERT INTO Book VALUES(39,'Tran Vinh Khanh','HUST','2023-01-01',42,'Action','Digital','Italian');

INSERT INTO Media VALUES(40,'cartoon',82000,1,0.4,'dvd12',78000,'/image/dvd/dvd12.jpg',0);
INSERT INTO DVD VALUES(40,'Doan Thi Diem Secondary School','Blu-ray','Vietnamese','Chinese','1 Hour','Nguyen Thi Thu Trang','2023-02-01','Digital');

INSERT INTO Media VALUES(41,'science fiction',75000,3,0.6,'dvd10',74000,'/image/dvd/dvd10.jpg',1);
INSERT INTO DVD VALUES(41,'Doan Thi Diem Primary School','VCD','Vietnamese','Cambodian','2 Hour','Bui Thi Mai Anh','2023-02-02','Physical');

INSERT INTO Media VALUES(42,'bussiness',26000,4,0.5,'book11',19000,'/image/book/book11.jpg',1);
INSERT INTO Book VALUES(42,'Dam Ngoc Khanh','HUST','2023-09-30',69,'Rom-com','Physical','Arabic');

INSERT INTO Media VALUES(43,'action',61000,18,0.7,'dvd11',52000,'/image/dvd/dvd11.jpg',0);
INSERT INTO DVD VALUES(43,'Doan Thi Diem Secondary School','Blu-ray','Vietnamese','Chinese','1 Hour','Nguyen Thi Thu Trang','2023-02-01','Digital');

INSERT INTO Media VALUES(44,'pop',24000,6,1.0,'cd7',20000,'/image/cd/cd7.jpg',0);
INSERT INTO CD VALUES(44,'Le Ngoc Dang Khoa','Rock n Roll','Hanoi University of Science and Technology','Digital','2023-01-02');
INSERT INTO Track VALUES(1,'Yeah',44);
INSERT INTO Track VALUES(2,'Oh yeah',44);
INSERT INTO Track VALUES(3,'Say oh yeah',44);

INSERT INTO Media VALUES(45,'pop',66000,8,1.0,'cd3',60000,'/image/cd/cd3.jpg',1);
INSERT INTO CD VALUES(45,'Tran Vinh Khanh','Pop','High school for gifted students','Physical','2023-01-03');
INSERT INTO Track VALUES(4,'lalala',45);
INSERT INTO Track VALUES(5,'play',45);
INSERT INTO Track VALUES(6,'cool',45);


INSERT INTO Media VALUES(46,'rock',40000,4,0.5,'cd4',35000,'/image/cd/cd4.jpg',1);
INSERT INTO CD VALUES(46,'Tran Vinh Khanh','Pop','High school for gifted students','Physical','2023-01-03');
INSERT INTO Track VALUES(7,'lalala',46);
INSERT INTO Track VALUES(8,'play',46);
INSERT INTO Track VALUES(9,'cool',46);