FROM gradle:5.6.4-jdk11 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle doctors-outbound:clean doctors-outbound:bootJar --no-daemon

FROM openjdk:11-jre-slim

RUN mkdir /app

COPY --from=build /home/gradle/src/doctors-outbound/build/libs/*.jar  /app/doctors-outbound.jar


ENTRYPOINT ["java", "-jar", "/app/doctors-outbound.jar"]