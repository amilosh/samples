#
# Build stage
#
FROM openjdk:17
COPY ./target/*.jar person-service.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/person-service.jar"]