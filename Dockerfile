FROM openjdk:17-jdk-alpine
EXPOSE 9090
ADD target/project2hotel-management-system.jar project2hotel-management-system.jar
ENTRYPOINT ["java", "-jar", "project2hotel-management-system.jar"]
