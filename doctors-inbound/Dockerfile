FROM gradle:5.6.4-jdk11 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle doctors-inbound:clean doctors-inbound:bootJar --no-daemon

FROM openjdk:11-jre-slim

RUN mkdir /app

COPY --from=build /home/gradle/src/doctors-inbound/build/libs/*.jar  /app/doctors-inbound.jar


ENTRYPOINT ["java", "-jar", "/app/doctors-inbound.jar"]