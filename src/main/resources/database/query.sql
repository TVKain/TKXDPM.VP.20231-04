CREATE TABLE IF NOT EXISTS Admin (
    username VARCHAR(32) PRIMARY KEY,
    password VARCHAR(32) NOT NULL
);

INSERT INTO Admin (username, password)
VALUES
('khanhtv', '1'),
('khanhnd', '1'),
('khoalnd', '1');