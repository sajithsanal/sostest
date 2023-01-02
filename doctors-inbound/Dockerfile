FROM gradle:5.6.4-jdk11

WORKDIR /doctors-inbound
RUN mkdir repo
COPY . ./repo

RUN gradle doctors-inbound:clean doctors-inbound:bootJar --no-daemon -p ./repo

RUN mv ./repo/doctors-inbound/build/libs/* .
RUN rm -rf ./repo

ENTRYPOINT ["java", "-jar", "doctors-inbound.jar"]