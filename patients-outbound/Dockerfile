FROM gradle:5.6.4-jdk11

WORKDIR /patients-outbound
COPY . .

RUN gradle patients-outbound:clean patients-outbound:bootJar --no-daemon

CMD gradle patients-outbound:bootRun