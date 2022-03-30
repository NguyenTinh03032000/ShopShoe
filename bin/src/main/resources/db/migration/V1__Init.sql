use shop_shoe;
create table roles (
	id int primary key not null auto_increment,
    name varchar(20) not null,
    createDate datetime default null,
    updateDate datetime default null
);
create table users(
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
create table user_roles (
	user_id bigint not null,
    role_id int not null,
    primary key(user_id,role_id),
    constraint foreign key (user_id) references users (id),
    constraint foreign key (role_id) references roles (id)
);

INSERT INTO roles(name) VALUES('ROLE_CUSTOMER');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');
INSERT INTO roles(name) VALUES('ROLE_SALESMAN');