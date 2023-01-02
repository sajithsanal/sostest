FROM gradle:5.6.4-jdk11

WORKDIR /doctors-outbound

COPY . .
RUN gradle doctors-outbound:clean doctors-outbound:bootJar --no-daemon

CMD gradle doctors-outbound:bootRun