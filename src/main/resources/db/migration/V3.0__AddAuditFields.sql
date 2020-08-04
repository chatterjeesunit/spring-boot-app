-- delete existing data to add not null columns
delete from address;
delete from customer;

ALTER TABLE `customer`
ADD COLUMN `created_by` VARCHAR(50) NOT NULL,
ADD COLUMN `updated_by` VARCHAR(50) NULL,
ADD COLUMN `created_on` DATETIME NOT NULL,
ADD COLUMN `updated_on` DATETIME NULL;
