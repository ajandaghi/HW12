/*create database HW8;*/
create TYPE UserType as ENUM ('CUSTOMER','ADMIN');
create table if not exists User_profile(
    id serial PRIMARY KEY,
    fullName varchar (100),
    nationalId char(10),
    password varchar(100),
    userType UserType
);

create table if not exists Customer(
    userId Integer,
    address varchar(256),
    budget bigInt
    );

create table if not exists  CustomerBasket (
       customerId integer,
       productId integer,
        number integer,
      totalPrice decimal );


create table if not exists  Product
(
    id serial primary key,
    adminId integer,
    categoryId integer,
    name varchar(100),
    price decimal,
    number integer
    );


create table if not exists  SaleProduct
(
    customerId integer,
    productId integer,
    number integer,
    totalPrice decimal,
    date TIMESTAMP );

create table if not exists  Category(
  id serial primary key,
  parentId integer,
  name varchar(100) );

