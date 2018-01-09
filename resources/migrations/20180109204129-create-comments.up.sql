create table comments (
    id serial primary key,
    user_id integer references users(id),
    request_id integer references requests(id),
    offer_id integer references offers(id),
    created_at timestamp default localtimestamp,
    body text
);