drop table if exists `address`;
CREATE TABLE `address` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `city` varchar(255) NOT NULL,
  `country` varchar(255) NOT NULL,
  `customer_id` bigint(20),
  `state_code` varchar(255) NOT NULL,
  `street_address` varchar(255) DEFAULT NULL,
  `zip_code` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_address_customer_id` (`customer_id`),
  FOREIGN KEY `fk_address_customer_id` (customer_id) REFERENCES customer(id) ON DELETE RESTRICT ON UPDATE RESTRICT
);