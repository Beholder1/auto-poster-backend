FROM maven:3-eclipse-temurin-25 AS build

RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app

COPY pom.xml /usr/src/app

RUN mvn dependency:go-offline

COPY src /usr/src/app/src

RUN mvn -T 1C package

FROM eclipse-temurin:25-jre-alpine
RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app
COPY --from=build /usr/src/app/target/dependency ./lib
COPY --from=build /usr/src/app/target/autoposter-backend.jar .
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "autoposter-backend.jar"]
