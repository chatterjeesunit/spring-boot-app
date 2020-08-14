delete from `address`;
delete from `customer`;

ALTER TABLE `customer`
ADD UNIQUE INDEX `idx_customer_email` (`email_address` ASC) VISIBLE;
