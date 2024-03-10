FROM openjdk:17
COPY ./target/*.jar producer-service.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/producer-service.jar"]