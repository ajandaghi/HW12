CREATE TYPE AccountType AS ENUM ('Saving', 'Current', 'LongInvestment', 'ShortInvestment');

create table if not exists account(
    id serial primary key,
    accountNo varchar(24),
    accountId integer,
    branchId integer,
    accountType AccountType ,
    balance bigint,
    cardId integer

);

CREATE TYPE Gender AS ENUM ('Male','Female' );
create table if not exists customers(
  id serial primary key,
  userId varchar(50),
  pass varchar(50),
  nationalId varchar(24),
  fullName varchar(255),
  gender   Gender,
  address varchar(255),
  isEnable boolean



);

CREATE TYPE TransType As ENUM  ( 'Deposit', 'Withdraw', 'CardtoCard');
create table if not exists transactions(
  id serial primary key,

  amount bigint,
  transType TransType,
  accountId integer,
  desAccountId integer,
  dateTime TIMESTAMP,
  operator  varchar(100)
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









