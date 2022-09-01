# Kuro-station-RESTAPI

Restful API of stations system.

Create passengers, buy tickets for travels from station A to station B, create trains to travel, and use JWT tokens to authenticate passengers.

This API is made in Java 17 with Spring Boot, following the concepts of HATEOAS.

See the [DOCS](https://documenter.getpostman.com/view/20195671/UyxnFQmc)

[![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/20195671-3e694c2a-5437-4c14-a72f-922ae3a745fb?action=collection%2Ffork&collection-url=entityId%3D20195671-3e694c2a-5437-4c14-a72f-922ae3a745fb%26entityType%3Dcollection%26workspaceId%3D340d12f8-bfd8-4f84-8bc7-f3b080c24682)

### Docker Image

You can run this project with the [docker image](https://hub.docker.com/r/kurovale/kuro-station) of this project

## Quick Setup
Run on your local machine:

1. ```git clone https://github.com/kuro-vale/kuro-station.git```

2. Set enviroment variables
    - DB_HOST = The url of your postgres database
    - DB_DATABASE = The name of the database
    - DB_USERNAME = The database username
    - DB_PASSWORD = The password of the database user
    - RSA_PUBLIC = Path to rsa public key*
    - RSA_PRIVATE = Path to rsa private key*
3. Meet pom.xml dependencies
4. Run ```./mvnw spring-boot:run```

## Generate RSA pair keys
#### Private key

```openssl genpkey -algorithm RSA -out private.pem -pkeyopt rsa_keygen_bits:2048```

### Public key

```openssl rsa -pubout -in private.pem -out public_key.pem```

## Admin token
Most of the administrative endpoints (like create a station, travel, train) require of a admin permission, you can login as kuro@vale.com, the password is admin123 (if you seed the database with up.sql)
