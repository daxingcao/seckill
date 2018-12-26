-- ----------------------------
-- oauth应用登录信息表
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client`;
CREATE TABLE `oauth_client`(
	`id` BIGINT(20) NOT NULL auto_increment comment '主键id',
	`client_id` VARCHAR(255) DEFAULT null comment '应用ID',
	`client_secret` varchar(255) default null comment '应用安全key',
	`client_name` varchar(255) default null comment '应用名称',
	`code` varchar(255) default null comment '应用code',
	`expiry_date` timestamp default null comment '过期时间',
	`create_date` timestamp default current_timestamp comment '创建时间',
	`update_date` timestamp default null comment '更新时间',
	`is_delete` int(2) default 0 comment '是否删除;0.未删除,1.已删除',
	primary key (`id`),
	unique key `client_id_index` (`client_id`)
)engine=innodb DEFAULT charset=utf8 comment='应用登录信息表';
alter table `oauth_client` auto_increment = 100;

-- -----------------------------
-- oauth验证token信息表
-- -----------------------------
DROP TABLE IF EXISTS `oauth_token`;
CREATE TABLE `oauth_token`(
	`id` BIGINT(20) NOT NULL auto_increment comment '主键id',
	`oauth_client_id` bigint(20) DEFAULT null comment '对应应用登录信息表主键ID',
	`access_token` varchar(255) default null comment '应用安全key',
	`expiry_date` timestamp default null comment '过期时间',
	`create_date` timestamp default current_timestamp comment '创建时间',
	`update_date` timestamp default null comment '更新时间',
	`is_delete` int(2) default 0 comment '是否删除;0.未删除,1.已删除',
	primary key (`id`),
	unique key `oauth_client_id_index` (`oauth_client_id`)
)engine=innodb DEFAULT charset=utf8 comment='token信息表';