FROM gradle:5.6.4-jdk11

WORKDIR /patients-inbound
RUN mkdir repo
COPY . ./repo
RUN gradle patients-inbound:clean patients-inbound:bootJar --no-daemon -p ./repo


RUN mv ./repo/patients-inbound/build/libs/* .
RUN rm -rf ./repo

ENTRYPOINT ["java", "-jar", "patients-inbound.jar"]