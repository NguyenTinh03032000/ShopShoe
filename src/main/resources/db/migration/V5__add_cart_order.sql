use shop_shoe;
create table cart (
	id int primary key not null auto_increment ,
	total_money double,
	id_user bigint not null,
	constraint foreign key (id_user) references users (id)
);
create table cart_index (
	id int primary key not null auto_increment,
	amount int,
	id_cart int,
	id_product int,
	constraint foreign key (id_cart) references cart (id),
	constraint foreign key (id_product) references product (id)
);
create table user_token(
	id int primary key not null auto_increment,
	id_user bigint not null,
	token varchar(500),
	time_expired datetime,
	constraint foreign key (id_user) references users (id),
	createDate datetime default null,
	updateDate datetime default null
);
create table orders (
	id int primary key not null auto_increment,
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
create table order_details (
	id int primary key not null auto_increment,
	unit_price double,
	number_of_orders int,
	id_order int,
	id_product int,
	constraint foreign key (id_order) references orders (id),
	constraint foreign key (id_product) references product (id),
	createDate datetime default null,
	updateDate datetime default null
);
-- 