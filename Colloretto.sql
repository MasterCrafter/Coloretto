create database colloretto;

create table player(
playerNumber int primary key not null auto_increment,
username char(30) not null,
highscore int null);

select * from player