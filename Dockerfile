FROM maven:3.9.6-eclipse-temurin-21 as builder

RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app

COPY pom.xml /usr/src/app

RUN mvn dependency:go-offline

COPY src /usr/src/app/src

RUN mvn -T 1C package

FROM openjdk:21-jdk
RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app
COPY --from=builder /usr/src/app/target/dependency ./lib
COPY --from=builder /usr/src/app/target/autoposter.jar .
EXPOSE 8080

ENTRYPOINT exec java $JAVA_OPTS -jar autoposter.jar
