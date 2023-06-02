drop database  if exists spring_HW_4 ;
CREATE DATABASE spring_HW_4;
use spring_HW_4;
create table user
(
    id       int auto_increment primary key,
    username varchar(30) not null,
    age      int check ( age > 0 ),
    email    varchar(100) unique
);
insert into user(id, username, age, email) VALUE
    (1, 'username_1', 22, 'email_username_1.@com'),
    (2, 'username_2', 22, 'email_username_2.@com'),
    (3, 'username_3', 22, 'email_username_3.@com'),
    (4, 'username_4', 22, 'email_username_4.@com'),
    (5, 'username_5', 22, 'email_username_5.@com');
create table item
(
    id        int auto_increment primary key,
    item_name varchar(50) not null,
    price     float check ( price > 0 )
);
insert into item(id, item_name, price) VALUE
    (1, 'item_name_1', 3000),
    (2, 'item_name_2', 320),
    (3, 'item_name_3', 2200),
    (4, 'item_name_4', 1100),
    (5, 'item_name_5', 9800);
create table orders

(
    id      int auto_increment primary key,
    user_id int references user (id),
    item_id int references item (id)
);
insert into orders(id, user_id, item_id) VALUE
    (1, 1, 1),
    (2, 2, 2),
    (3, 3, 2),
    (4, 2, 5),
    (5, 3, 3),
    (6, 5, 3);
