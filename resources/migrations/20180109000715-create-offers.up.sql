create table offers (
    id serial primary key,
    user_id integer references users(id),
    created_at timestamp default localtimestamp,
    price int,
    description text,
    platform varchar(50),
    title varchar(50)
);