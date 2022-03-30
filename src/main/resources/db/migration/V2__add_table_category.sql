use shop_shoe;
create table category (
	 id int primary key not null auto_increment,
  	 name varchar(100),
 	 createDate datetime default null,
  	 updateDate datetime default null
);