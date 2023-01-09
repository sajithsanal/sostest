FROM gradle:5.6.4-jdk11 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle patients-inbound:clean patients-inbound:bootJar --no-daemon

FROM openjdk:11-jre-slim

RUN mkdir /app

COPY --from=build /home/gradle/src/patients-inbound/build/libs/*.jar  /app/patients-inbound.jar


ENTRYPOINT ["java", "-jar", "/app/patients-inbound.jar"]