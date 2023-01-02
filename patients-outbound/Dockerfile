FROM gradle:5.6.4-jdk11

WORKDIR /patients-inbound
COPY . .

RUN gradle patients-inbound:clean patients-inbound:bootJar --no-daemon

CMD gradle patients-inbound:bootRun