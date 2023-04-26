create database quarkus-social;
create table users(
	id bigserial not null primary key,
	nome varchar(100) not null,
	idade integer not null
);