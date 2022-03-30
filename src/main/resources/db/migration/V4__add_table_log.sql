use shop_shoe;
create table log (
	id int primary key not null auto_increment,
    name_action varchar(100),
    name_method varchar(20),
    content varchar(500),
    id_user bigint,
    id_product int,
	actionDate datetime,
    constraint foreign key (id_user) references users (id),
    constraint foreign key (id_product) references product (id)

);