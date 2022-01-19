CREATE TYPE AccountType AS ENUM ('Saving', 'Current', 'LongInvestment', 'ShortInvestment');

create table if not exists account(
    id serial primary key,
    accountNo varchar(24),
    customerId integer,
    branchId integer,
    accountType AccountType ,
    balance bigint,
    cardId integer,
    isEnable boolean

);

CREATE TYPE Gender AS ENUM ('Male','Female' );
create table if not exists customers(
  id serial primary key,
  userId varchar(50),
  pass varchar(50),
  nationalId varchar(24),
  fullName varchar(255),
  gender   Gender,
  address varchar(255)


);

CREATE TYPE TransType As ENUM  ( 'Deposit', 'Withdraw', 'CardReceive', 'CardSend');
create table if not exists transactions(
  id serial primary key,
  transId Integer,
  amount bigint,
  transType TransType,
  customerId integer,
  desCustomerId integer,
  dateTime TIMESTAMP,
  operatorId  Integer
  );

create table if not exists cards (
  id serial primary key,
  accountId integer,
  cardNo char(19),
  cvv2   char(4),
  expDate  date,
  pass2  varchar(100),
  isEnable boolean
);

create table if not exists branch (
   id serial primary key,
   branchNo varchar(5),
    branchName varchar(100),
    address varchar(255),
    bossStaffId  varchar(50)
    );

CREATE TYPE StaffType As ENUM  ( 'Boss', 'Employee');
create table if not exists staffs
(
    id    serial primary key,
    fullName Varchar(256),
    userId varchar(50),
    pass varchar(50),
    staffType StaffType,
    branchNo  varchar(100)
);









