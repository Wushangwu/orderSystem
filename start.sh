







CREATE TABLE `order_info_t`(
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`distance` INT(11) NULL DEFAULT NULL,
	`status` VARCHAR(60) NOT NULL,
	PRIMARY KEY (`id`)
)
  COMMENT='order basic information'
  ENGINE = INNODB
  DEFAULT CHARSET = utf8;
