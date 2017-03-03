CREATE TABLE `user`(
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL DEFAULT '',
  `password` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY(`user_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

DROP TABLE IF EXISTS `dish`;
CREATE TABLE `dish` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) NOT NULL COMMENT '菜品名称',
  `price` decimal(7,2) unsigned NOT NULL DEFAULT '0.00' COMMENT '单价',
  `belong` bigint(20) unsigned NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `logo` bigint(20) NOT NULL COMMENT '封面图片',
  `type` bigint(20) unsigned NOT NULL COMMENT '菜品类型',
  UNIQUE KEY `id` (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单表';