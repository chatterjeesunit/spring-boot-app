CREATE TABLE `revision_info` (
	`revision_id` INTEGER PRIMARY KEY AUTO_INCREMENT,
    `rev_timestamp` BIGINT(20) NOT NULL,
    `user` VARCHAR(50) NOT NULL
);

CREATE TABLE `customer_audit` (
  `revision_id` INTEGER NOT NULL,
  `id` BIGINT(20) NOT NULL,
  `revision_type` TINYINT NOT NULL,
  `email_address` VARCHAR(255),
  `first_name` VARCHAR(255),
  `last_name` VARCHAR(255),
  `created_by` VARCHAR(50),
  `updated_by` VARCHAR(50),
  `created_on` DATETIME,
  `updated_on` DATETIME,
  PRIMARY KEY (`revision_id`, `id`),
  CONSTRAINT `idfk_customer_revinfo_rev_id`
	FOREIGN KEY (`revision_id`) REFERENCES `revision_info` (`revision_id`)
);

CREATE TABLE `address_audit` (
  `revision_id` INTEGER NOT NULL,
  `id` BIGINT(20) NOT NULL,
  `revision_type` TINYINT NOT NULL,
  `city` VARCHAR(255),
  `country` VARCHAR(255),
  `customer_id` BIGINT(20),
  `state_code` VARCHAR(255),
  `street_address` VARCHAR(255),
  `zip_code` VARCHAR(255),
  PRIMARY KEY (`revision_id`, `id`),
  CONSTRAINT `idfk_address_revinfo_rev_id`
	FOREIGN KEY (`revision_id`) REFERENCES `revision_info` (`revision_id`)
);