FROM gradle:5.6.4-jdk11

WORKDIR /doctors-inbound
COPY . .
RUN gradle clean bootJar --no-daemon

CMD gradle bootRun