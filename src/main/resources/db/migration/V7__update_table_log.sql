use shop_shoe;
ALTER TABLE shop_shoe.log  CHANGE COLUMN action_date action_date DATETIME NULL DEFAULT NULL ;
ALTER TABLE  shop_shoe.log DROP COLUMN  actionDate; 
