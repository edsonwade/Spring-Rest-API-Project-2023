create table tb_books
(
    book_id     INT(10) AUTO_INCREMENT PRIMARY KEY,
    author      varchar(255)   NOT NULL,
    launch_date datetime(6),
    price       decimal(65, 2) NOT NULL,
    title       varchar(200)   NOT NULL
);
