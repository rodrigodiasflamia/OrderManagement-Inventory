create table if not exists inventory (
    id serial primary key,
    sku varchar(255) unique,
    quantity int not null check (quantity >= 0)
);