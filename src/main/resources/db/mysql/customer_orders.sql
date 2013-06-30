 drop table orders__books;
 drop table orders;
 drop table customer;
 drop table books;
 
 create table customer (id int not null primary key auto_increment, name varchar(20) not null, address varchar(30) not null);
 insert into customer(name,address) values('tom','xuhui');
 insert into customer(name,address) values('Jerry','pudong');
 insert into customer(name,address) values('Bart','Springfield'),('Homer','Springfiled');
 
 create table books (id int not null primary key auto_increment, name varchar(20) not null, author varchar(20) not null);
 insert into books(name,author) values('Thinking in Java','Bruce');
 insert into books(name,author) values('Design patterns','Gang4');
 
 create table orders (id int not null primary key, order_date timestamp default current_timestamp, customer_id int not null,foreign key(customer_id) references customer(id));
 insert into orders(id,customer_id) values(1001,1);
 insert into orders(id,customer_id) values(1002,2);
 insert into orders(id,customer_id) values(1003,2);
 
 create table orders__books (order_id int not null, book_id int not null,primary key(order_id,book_id),foreign key(order_id) references orders(id),foreign key(book_id) references books(id));
 insert into orders__books(order_id,book_id) values(1001,1);
 insert into orders__books(order_id,book_id) values(1001,2);
 insert into orders__books(order_id,book_id) values(1002,2);
 insert into orders__books(order_id,book_id) values(1003,1);