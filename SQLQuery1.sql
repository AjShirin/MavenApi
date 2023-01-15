create database MavenApi;
USE MavenApi;

create table MavenApi(
ID INT primary key IDENTITY(1,1), 
web_pages varchar (50),
state_province  varchar (50),
alpha_two_code  varchar (50),
name  varchar (50),
country  varchar (50),
domains  varchar (50));

SELECT * From MavenApi;
