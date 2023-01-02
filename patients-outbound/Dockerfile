FROM gradle:5.6.4-jdk11

WORKDIR /patients-outbound
RUN mkdir repo
COPY . ./repo

RUN gradle patients-outbound:clean patients-outbound:bootJar --no-daemon -p ./repo

RUN mv ./repo/patients-outbound/build/libs/* .
RUN rm -rf ./repo

ENTRYPOINT ["java", "-jar", "patients-outbound.jar"]