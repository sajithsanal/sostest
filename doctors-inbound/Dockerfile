FROM gradle:5.6.4-jdk11

WORKDIR /doctors-inbound
COPY ./common-lib .
COPY ./common-module .
COPY ./doctors-common .
COPY ./doctors-inbound .
COPY ./settings.gradle .

RUN gradle clean bootJar --no-daemon

CMD gradle bootRun