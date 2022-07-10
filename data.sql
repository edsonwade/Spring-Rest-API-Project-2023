create table if not exists tb_persons
(
    id         int auto_increment
    primary key,
    address    varchar(255) null,
    email      varchar(40)  not null,
    first_name varchar(255) not null,
    gender     varchar(255) null,
    last_name  varchar(200) not null,
    constraint UK_ksrgjyqcn6cmmrsug93j7fdva
        unique (email)
)engine=Innodb default charset=utf8;

insert into tb_persons ( address, email, first_name, gender, last_name)
values ('Maia Address','manuel@outlook.pt','manuel','MALE','lucas');

VALUES ('Benfica Lisbon', 'augusto@gmail.com', 'augusto', 'MALE', 'texeira');
INSERT INTO tb_persons (address, email, first_name, gender, last_name)
VALUES ('Street Amesterdam 123', 'tugcesenturk@gmail.com', 'tugce', 'FEMALE', 'senturky');
create table tb_persons
(
    id         int auto_increment
        primary key,
    address    varchar(255) null,
    email      varchar(40)  not null,
    first_name varchar(255) not null,
    gender     varchar(255) null,
    last_name  varchar(200) not null,
    constraint UK_ksrgjyqcn6cmmrsug93j7fdva
        unique (email)
);

INSERT INTO tb_persons ( address, email, first_name, gender, last_name) VALUES ( 'Rua Luz Soriano Porto', 'vanilson@gmail.com', 'vanilson', 'MALE', 'wayne');
INSERT INTO tb_persons ( address, email, first_name, gender, last_name) VALUES ( 'Street Amesterdam', 'tugce@gmail.com', 'tugce', 'FEMALE', 'senturk');
INSERT INTO tb_persons ( address, email, first_name, gender, last_name) VALUES ( 'mexico ', 'rick@gmail.com', 'Rick', 'OTHER', 'Renner');
INSERT INTO tb_persons ( address, email, first_name, gender, last_name) VALUES ( null, 'rui@gmail.com', 'Rui', 'MALE', 'Renato');
INSERT INTO tb_persons ( address, email, first_name, gender, last_name) VALUES ( 'Maia Address', 'manuel@outlook.pt', 'manuel', 'MALE', 'lucas');