# PlayerWallet

## Pre-requisites
Java 8+

## How-to Build

### CREATE DATABASE
```
DROP DATABASE IF EXISTS player_wallet ;
CREATE DATABASE playe_wallet;
```

### Config env vars
```
export DB_URL=jdbc:mysql://localhost:3306/player_wallet?useSSL=false
export DB_USER={YOUR USER HERE}
export DB_PASSWORD={YOUR PASSWORD HERE}
```

### Initialize PlayerWallet

#### Mysql Database
```mvn clean install```

#### Local with H2 Database
```mvn clean install -Dspring.profiles.active=dev```


## How-to Test

#### Mysql Database
```mvn test```

#### Local with H2 Database
```mvn test -Dspring.profiles.active=dev```

## How-to Run

#### Mysql Database
```mvn spring-boot:run```

#### Local with H2 Database
```mvn spring-boot:run -Dspring.profiles.active=dev```


## Endpoint Postman link
https://www.getpostman.com/collections/112c687a51bb15275b7d


