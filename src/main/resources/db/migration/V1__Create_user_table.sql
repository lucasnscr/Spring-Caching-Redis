create schema if not exists users;

create table users.user (
    id bigserial primary key,
    name varchar(100) not null,
    email varchar(100) not null,
    age varchar(100) not null
);