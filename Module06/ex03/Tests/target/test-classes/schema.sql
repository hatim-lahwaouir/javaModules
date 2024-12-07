DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS user;

CREATE TABLE product(
  id  INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
  name VARCHAR(255),
  price int
);



CREATE TABLE user (
  id  INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
  login VARCHAR(255),
  password VARCHAR(255),
  authenticated TINYINT DEFAULT 0
);

