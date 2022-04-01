use Shop_Shoe;
drop table if exists order_details;
drop table if exists orders;
drop table if exists user_token;
drop table if exists cart_index;
drop table if exists cart;
drop table if exists log;
drop table if exists product;
drop table if exists Discount;
drop table if exists category;

create table if not exists roles (
	id int primary key not null auto_increment,
    name varchar(20) not null,
    createDate datetime default null,
    updateDate datetime default null
);
create table if not exists users(
	id bigint primary key not null auto_increment,
    name varchar(250),
    address varchar(1000),
    phone_number varchar(50),
    username varchar(100) not null,
    email varchar(100) not null,
    password varchar(100) not null,
	scores long,
    createDate datetime default null,
    updateDate datetime default null
);
create table if not exists user_roles (
	user_id bigint not null,
    role_id int not null,
    primary key(user_id,role_id),
    constraint foreign key (user_id) references users (id),
    constraint foreign key (role_id) references roles (id)
);
create table if not exists category (
	 id bigint primary key not null auto_increment,
  	 name varchar(100),
 	 createDate datetime default null,
  	 updateDate datetime default null
);
create table if not exists product (
	id bigint primary key not null auto_increment,
    name varchar(200),
    price double,
    description varchar(500),
    brand varchar(100),
    id_category bigint,
    constraint foreign key (id_category) references category (id),
    createDate datetime default null,
    updateDate datetime default null
);
create table if not exists Discount (
	id bigint primary key not null auto_increment,
	name_discount varchar(100),
    content varchar(5000),
	date_discount datetime,
	percent_discount int,
    id_product bigint,
    constraint foreign key (id_product) references product (id),
	createDate datetime default null,
	updateDate datetime default null
);
create table if not exists log (
	id bigint primary key not null auto_increment,
    name_action varchar(100),
    name_method varchar(20),
    content varchar(500),
    id_user bigint,
    id_product bigint,
	actionDate datetime,
    constraint foreign key (id_user) references users (id),
    constraint foreign key (id_product) references product (id)
);
create table if not exists cart (
	id bigint primary key not null auto_increment ,
	total_money double,
	id_user bigint not null,
	constraint foreign key (id_user) references users (id)
);
create table if not exists cart_index (
	id bigint primary key not null auto_increment,
	amount int,
	id_cart bigint,
	id_product bigint,
	constraint foreign key (id_cart) references cart (id),
	constraint foreign key (id_product) references product (id)
);
create table if not exists user_token(
	id bigint primary key not null auto_increment,
	id_user bigint not null,
	token varchar(500),
	time_expired datetime,
	constraint foreign key (id_user) references users (id),
	createDate datetime default null,
	updateDate datetime default null
);
create table if not exists orders (
	id bigint primary key not null auto_increment,
	delivery_address varchar(1000),
	note varchar(1000),
	recipient_name varchar(100),
	delivery_phone_number varchar(50),
	order_date datetime,
	delivery_date datetime,
	total_value double,
	payment_method varchar(100),
	order_status varchar(100),
	id_orderer bigint,
	constraint foreign key (id_orderer) references users (id)
);
create table if not exists order_details (
	id bigint primary key not null auto_increment,
	unit_price double,
	number_of_orders int,
	id_order bigint,
	id_product bigint,
	constraint foreign key (id_order) references orders (id),
	constraint foreign key (id_product) references product (id),
	createDate datetime default null,
	updateDate datetime default null
);