create table if not exists tb_persons
(
    person_id  int auto_increment primary key,
    first_name varchar(255) not null,
    last_name  varchar(200) not null,
    email      varchar(40)  not null,
    address    varchar(255) null,
    gender     varchar(255) null,
    constraint UK_ksrgjyqcn6cmmrsug93j7fdva
        unique (email)
);

