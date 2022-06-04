drop table if exists users;

create table users
(
    user_id   SERIAL,
    user_name TEXT NOT NULL ,
    user_surname TEXT NOT NULL,
    user_age INT NOT NULL
);
