FROM openjdk:17
COPY ./target/*.jar consumer-service.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","/consumer-service.jar"]