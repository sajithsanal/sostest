FROM gradle:5.6.4-jdk11

WORKDIR /doctors-outbound

COPY ./common-lib .
COPY ./common-module .
COPY ./doctors-common .
COPY ./doctors-outbound .
COPY ./settings.gradle .

RUN gradle clean bootJar --no-daemon

CMD gradle bootRun