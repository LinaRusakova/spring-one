drop table if exists product_db cascade;
create table product_db
(
    id    bigserial primary key,
    title varchar(255),
    price int
);
insert into product_db (title, price)
values ('Milk', 35),
       ('Chocolate', 44),
       ('Coffee', 99),
       ('Beacon', 124),
       ('Tea', 84);