FROM alpine:3.6
FROM anapsix/alpine-java:8_server-jre

EXPOSE 80

COPY build/libs/spring-boot-mongodb-example.jar /spring-boot-mongodb-example/spring-boot-mongodb-example.jar
COPY build/resources/main/log4j2-filebeat.yml /spring-boot-mongodb-example/log4j2-filebeat.yml

RUN mkdir /var/log/spring-boot-mongodb-example

CMD ["java", "-Dlog4j.configurationFile=/spring-boot-mongodb-example/log4j2-filebeat.yml", "-jar", "/spring-boot-mongodb-example/spring-boot-mongodb-example.jar"]