ALTER TABLE `customer_audit`
ADD COLUMN `created_by_mod` TINYINT(1) NULL,
ADD COLUMN `created_on_mod` TINYINT(1) NULL ,
ADD COLUMN `email_address_mod` boolean NULL,
ADD COLUMN `first_name_mod` boolean NULL,
ADD COLUMN `last_name_mod` boolean NULL,
ADD COLUMN `updated_by_mod` boolean NULL,
ADD COLUMN `updated_on_mod` boolean NULL;