# Sample Spring Boot project 

## Releases
 * with few basic CRUD REST APIs - [Git Tag V1.1](https://github.com/chatterjeesunit/spring-boot-app/tree/v1.1)
 * with Flyway integration for Database migration - [Git Tag V2.0](https://github.com/chatterjeesunit/spring-boot-app/tree/v2.0)
 * with Basic Authentication/Authorization - [Git Tag V3.0](https://github.com/chatterjeesunit/spring-boot-app/tree/v3.0)
 * with Hibernate Envers/JPA Auditing - [Git Tag V4.0](https://github.com/chatterjeesunit/spring-boot-app/tree/v4.0)
 * with Authentication using JWT - [Git Tag V5.0](https://github.com/chatterjeesunit/spring-boot-app/tree/v5.0)
 * with Caffeine Caching - [Git Tag V6.0](https://github.com/chatterjeesunit/spring-boot-app/tree/v6.0)
 * with Docker Compose - [Git Tag V7.0](https://github.com/chatterjeesunit/spring-boot-app/tree/v7.0)
 * with Distributed Cache using Hazelcast - [Git Tag V8.0](https://github.com/chatterjeesunit/spring-boot-app/tree/v8.0)
 
For more detailed step by step guide on how to build this app, please refer to the blogs on - http://sunitc.dev/tag/spring-boot/


## How to run it
 * Pre-requisite (for first time run only) <br>
  Create a folder for mysql volume mount <br>
  `mkdir -p ~/data/mysql8`

 * Build<br>
   `./gradlew clean build`

 * Run via Docker Compose <br>
   `docker-compose up -d --build --scale application=2`

 
## Creating Dummy Data
 * Run SQL Script - `scripts/Create_Dummy_Data.sql`
 
 
## Default Users for Login
Username|Password|ROLE
--------|--------|-----
admin01@tw.com|admin01@123#|ADMIN
admin02@tw.com|admin02@123#|ADMIN
user01@tw.com|welcome@123#|USER

## Sample APIS

##### 1. API to get JWT Auth Token
```
curl --location --request POST 'localhost/api/v1/auth/token' \
--header 'Content-Type: application/json' \
--data-raw '{
    "userName": "admin01@tw.com",
    "password": "admin01@123#"
}'
```

##### 2. API to get all customers (paginated)
```
curl --location --request GET 'dev-sandbox/api/v1/customer/?pageNum=0&pageSize=10' \
--header 'Authorization: Bearer <<REPLACE JWT TOKEN HERE>>'
```
In above code, replace the text `<<REPLACE JWT TOKEN HERE>>` with JWT token from Auth API


## Troubleshooting
 * If after running Docker Compose, you are getting `404` on the APIs, ONLY restart the load-balancer.
 Sometimes this happens when loadbalancer starts before application server starts
 
 
 
## To-Do

 * Add a wait-for script in Docker compose for following container dependencies
   * Loadbalancer - Depends on - Application
   * Application  - Depends on - Database 