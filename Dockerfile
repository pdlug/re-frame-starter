FROM java:8-alpine

ADD target/starter.jar /

ENTRYPOINT ["java", "-jar", "/starter.jar"]
