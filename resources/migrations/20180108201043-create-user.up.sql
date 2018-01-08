create table users (
    id serial primary key,
    name varchar(50),
    email varchar(50),
    phone varchar(50),
    created_at timestamp default localtimestamp
);