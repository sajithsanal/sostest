FROM gradle:5.6.4-jdk11

WORKDIR /doctors-outbound

RUN mkdir repo
COPY . ./repo

RUN gradle doctors-outbound:clean doctors-outbound:bootJar --no-daemon -p ./repo

RUN mv ./repo/doctors-outbound/build/libs/* .
RUN rm -rf ./repo

ENTRYPOINT ["java", "-jar", "doctors-outbound.jar"]