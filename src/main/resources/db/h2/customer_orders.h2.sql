DROP TABLE IF EXISTS customer;
DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS cart;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS orders__books;


create table customer (id int not null primary key auto_increment, name varchar(20) not null, password char(32) not null, email varchar(30), balance double default 100);
insert into customer(name,password,email) values('simon','B30BD351371C686298D32281B337E8E9','simon@163.com');

create table books (id int not null primary key auto_increment, name varchar(100) not null, author varchar(20), price double, description text);
insert into books(name,author,price,description) values('Thinking in Java (4th Edition)','Bruce Eckel',42.69,'Thinking in Java has earned raves from programmers worldwide for its extraordinary clarity, careful organization, and small, direct programming examples. From the fundamentals of Java syntax to its most advanced features, Thinking in Java is designed to teach, one simple step at a time.');
insert into books(name,author,price,description) values('Head First Java, 2nd Edition','Kathy Sierra',28.95,'Learning a complex new language is no easy task especially when it s an object-oriented computer programming language like Java. You might think the problem is your brain. It seems to have a mind of its own, a mind that doesn''t always want to take in the dry, technical stuff you''re forced to study.');
insert into books(name,author,price,description) values('Effective Java (2nd Edition)','Joshua Bloch',35.47,'Are you looking for a deeper understanding of the Java™ programming language so that you can write code that is clearer, more correct, more robust, and more reusable? Look no further! Effective Java™, Second Edition, brings together seventy-eight indispensable programmer’s rules of thumb: working, best-practice solutions for the programming challenges you encounter every day.');

create table cart (customer_id int not null, book_id int not null,primary key(customer_id,book_id),foreign key(customer_id) references customer(id),foreign key(book_id) references books(id));

create table orders (id int not null primary key auto_increment, order_date timestamp default current_timestamp, customer_id int not null,foreign key(customer_id) references customer(id));
insert into orders(customer_id) values(1);

create table orders__books (order_id int not null, book_id int not null,primary key(order_id,book_id),foreign key(order_id) references orders(id),foreign key(book_id) references books(id));
insert into orders__books(order_id,book_id) values(1,1);
insert into orders__books(order_id,book_id) values(1,2);