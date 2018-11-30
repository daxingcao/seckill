-- 商品表
drop table if exists `shop`;
create table shop(
	`id` BIGINT not null auto_increment comment '商品主键id',
	`shop_code` varchar(50) not null comment '商品编号',
	`shop_name` varchar(200) not null comment '商品名称',
	`shop_price` decimal(19,2) default 0.00 comment '商品价格',
	`shop_introduce` varchar(1000) default null comment '商品相关介绍',
	`is_delete` tinyint default 0 comment '是否删除:0.未删除;1.已删除',
	`is_seckill` tinyint default 0 comment '是否参与秒杀:0.不参与;1.参与',
	`start_time` datetime default null comment '秒杀开始时间',
	`end_time` datetime default null comment '秒杀结束时间',
	`create_time` datetime default now() comment '创建时间',
	`shop_number` bigint default 0 comment '商品库存',
	`remark` varchar(200) default null comment '备注',
	primary key (id),
	unique key `index_shop_code` (shop_code) using BTREE
)ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=UTF8 COMMENT='商品表';
alter table `shop` auto_increment=10000;

insert into `shop`(shop_code,shop_name,is_seckill,start_time,end_time,shop_price,shop_number)values
('x00000001','液晶电视',1,'2018-11-11 15:30:00','2018-11-11 16:30:00',3999.00,100),
('x00000002','笔记本电脑',1,'2018-11-11 15:30:00','2018-11-11 16:30:00',5599.00,10),
('x00000003','九阳豆浆机',1,'2018-11-11 15:30:00','2018-11-11 16:30:00',298.00,200);

-- 用户登录表
drop table if exists `login_user`;
CREATE TABLE `login_user` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '用户登录表ID',
  `USERNAME` varchar(30) NOT NULL COMMENT '用户名',
  `PASSWORD` varchar(50) NOT NULL COMMENT '登录密码',
  `LAST_DATE` date DEFAULT NULL COMMENT '上次登录时间',
  `CREATE_DATE` date DEFAULT NULL COMMENT '创建时间',
  `UPDATE_DATE` date DEFAULT NULL COMMENT '更新时间',
  `LOGIN_STATUS` int(4) DEFAULT '0' COMMENT '登录状态, 0.未登录, 1.已登录',
  `USER_ID` int(4) DEFAULT NULL COMMENT '对应数据信息表id',
  `HEAD_FILE_ID` int(11) DEFAULT NULL COMMENT '头像文件id,对应sysfile表id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='用户登录表';

-- 用户订单表
drop table if exists `shop_order`;
create table `shop_order`(
	`order_code` varchar(50) not null comment '订单编号',
	`user_id` bigint not null comment '用户id',
	`shop_code` varchar(50) not null comment '商品编号',
	`create_time` datetime default now() comment '创建时间',
	`is_delete` tinyint default 0 comment '是否删除:0.未删除;1.已删除',
	primary key(user_id,shop_code),
	unique key `index_order_code` (order_code),
	key `index_user_id` (user_id)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8 COMMENT='用户订单表';

