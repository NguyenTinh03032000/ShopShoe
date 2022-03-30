use shop_shoe;
create table Discount (
	id int primary key not null auto_increment,
    	name_discount varchar(100),
    	date_discount datetime,
    	percent_discount int,
    	createDate datetime default null,
    	updateDate datetime default null
);
create table product (
	id int primary key not null auto_increment,
    name varchar(200),
    price double,
    description varchar(500),
    brand varchar(100),
    id_category int,
    id_discount int,
    constraint foreign key (id_category) references category (id),
    constraint foreign key (id_discount) references Discount (id),
    createDate datetime default null,
    updateDate datetime default null
);