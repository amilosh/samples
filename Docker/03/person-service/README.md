# Person Service
Docker compose: Sprig Boot, MySQL.

### Tools
- Java 17
- Maven 3.9.6
- Spring Boot 3.2.2
- MySQL 8.3

### Features
- Spring Data JPA

### Run
```
mvn clean package

docker build -t person-service-image .

docker-compose up -d  
```

### End-points
```
GET http://localhost:8080/person

POST http://localhost:8080/person
{
    "name": "John"
}
```