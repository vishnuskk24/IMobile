FROM openjdk:17-jdk-alpine
EXPOSE 8443
EXPOSE 8080
ADD target/imobile.jar imobile.jar
ENTRYPOINT [ "java", "-jar","/imobile.jar" ]