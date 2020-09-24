insert into customer(id, email_address, first_name, last_name, created_by, created_on)
values
(1, 'blackwidow@superheroes.com', 'Natasha', 'Romanoff' , 'Sunit Chatterjee', now()),
(2, 'superman@superheroes.com', 'Clark', 'Kent' , 'Sunit Chatterjee', now() ),
(3, 'spiderman@superheroes.com', 'Peter', 'Parker' , 'Sunit Chatterjee', now()),
(4, 'ironman@superheroes.com', 'Tony', 'Stark' , 'Sunit Chatterjee', now()),
(5, 'captainamerica@superheroes.com', 'Steve' , 'Rogers' , 'Sunit Chatterjee', now()),
(6, 'hulk@superheroes.com', 'Bruce', 'Banner' , 'Sunit Chatterjee' , now()),
(7, 'wonderwoman@superheroes.com', 'Diana', 'Prince' , 'Sunit Chatterjee', now()),
(8, 'captainmarvel@superheroes.com', 'Carol' , 'Danvers' , 'Sunit Chatterjee', now() ),
(9, 'batman@superheroes.com', 'Bruce', 'Wayne' , 'Sunit Chatterjee', now());



insert into address (customer_id, city, country, state_code, street_address, zip_code)
values
(1, 'New York', 'United States of America', 'NY', '135 W. 50th Street', '10076'),
(9, 'Gotham', 'United States of America', 'NY', '1007 Mountain Drive', '10004'),
(3, 'Queens', 'United States of America', 'NY', '20 Ingram Street, Forest Hills', '10054'),
(4, 'Malibu', 'United States of America', 'CA', '10880 Malibu Point', '90265'),
(5, 'Brooklyn', 'United States of America', 'NY', '569 Leaman Place, Brooklyn Heights ', '11201'),
(6, 'Dayton', 'United States of America', 'OH', '-', '45377'),
(7, 'Paradise Island', 'Unknown', 'UK', '-', '-'),
(8, 'New York', 'United States of America', 'NY', '135 W. 50th Street', '10076'),
(2, 'Metropolis', 'United States of America', 'NY', '1938 Sullivan Place', '10005');

commit;