CREATE SCHEMA IF NOT EXISTS invoicemanagement;


SET NAMES 'UTF8MB4';

USE invoicemanagement;


DROP TABLE IF EXISTS Users;


CREATE TABLE Users (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    phone VARCHAR(12) DEFAULT NULL,
    email VARCHAR(20) NOT NULL

)


DROP TABLE IF EXIST Roles;
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    role_name  NOT NULL,


