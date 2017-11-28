create table if not exists users (
    id serial primary key,
    name varchar(50),
    email varchar(50),
    phone varchar(50)
);

--;;

create table if not exists games (
    id serial primary key,
    title varchar(100)
);

--;;

create table if not exists offers (
    id serial primary key,
    user_id int references users(id),
    game_id int references games(id)
);
