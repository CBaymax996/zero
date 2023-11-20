create table if not exists `table_user`
(
    `id`   bigint auto_increment primary key,
    `name` varchar(100) not null,
    `role` varchar(100) not null
);