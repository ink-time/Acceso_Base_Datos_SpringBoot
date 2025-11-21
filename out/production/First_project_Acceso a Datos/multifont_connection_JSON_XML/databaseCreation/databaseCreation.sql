CREATE DATABASE pascual_mercadona_db;

CREATE TABLE user (
    name VARCHAR (100) NOT NULL,
    surname VARCHAR (100) NOT NULL,
    dni VARCHAR (9) NOT NULL,
    company VARCHAR (100) NOT NULL,
    CONSTRAINT pk_user PRIMARY KEY (dni)
)Engine = InnoDB default charset = utf8mb4 collate = utf8mb4_spanish2_ci;

CREATE TABLE emails (
    dni VARCHAR (9) NOT NULL,
    email VARCHAR (100) NOT NULL,
    CONSTRAINT fk_user_1 FOREIGN KEY (dni) REFERENCES user (dni),
    CONSTRAINT pk_emails PRIMARY KEY (dni, email)
)Engine = InnoDB default charset = utf8mb4 collate = utf8mb4_spanish2_ci;

CREATE TABLE phoneNums (
    dni VARCHAR (9) NOT NULL,
    number BIGINT (17) NOT NULL,
    CONSTRAINT fk_user_2 FOREIGN KEY (dni) REFERENCES user (dni),
    CONSTRAINT pk_phoneNums PRIMARY KEY (dni, number)
)Engine = InnoDB default charset = utf8mb4 collate = utf8mb4_spanish2_ci;