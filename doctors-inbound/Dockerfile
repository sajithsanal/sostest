FROM gradle:5.6.4-jdk11

WORKDIR /doctors-inbound
COPY . .

RUN gradle doctors-inbound:clean doctors-inbound:bootJar --no-daemon

CMD gradle doctors-inbound:bootRun