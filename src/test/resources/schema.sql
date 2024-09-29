/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  Brooklyn
 * Created: 28/08/2024
 */
DROP TABLE Customer;
DROP TABLE Product;

create table Customer (
    customerId INTEGER AUTO_INCREMENT (1000),
    username varchar(50) not null UNIQUE,
    firstName varchar(20) not null,
    surname varchar(20) not null,
    password varchar(20) not null,
    emailAddress varchar(50) not null,
    shippingAddress varchar(50) not null,
    constraint Customer_PK primary key (customerId)
);

create table Product (
    productId varchar(50) not null UNIQUE,
    name varchar(100) not null,
    description varchar(255),
    category varchar(50) not null,
    listPrice numeric(10, 2) not null check (listPrice >= 0),
    quantityInStock numeric(10, 2) not null check (quantityInStock >= 0),
    constraint Product_PK primary key (productId)
)