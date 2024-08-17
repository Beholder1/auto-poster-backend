FROM eclipse-temurin:21-jre-alpine

RUN addgroup -S spring && adduser -S spring -G spring

WORKDIR /opt

COPY target/*.jar /opt/myApp.jar

USER spring:spring

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "myApp.jar"]
