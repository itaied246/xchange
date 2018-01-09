create table requests (
    id serial primary key,
    user_id integer references users(id),
    created_at timestamp default localtimestamp,
    description text,
    platform varchar(50),
    title varchar(50)
);