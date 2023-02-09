########
# Dockerfile to build auth-service container image
#
########
FROM adoptopenjdk/openjdk15:jre-15.0.2_7-alpine

LABEL maintainer="Petrulin Alexander"

COPY target/auth-service-*.jar app.jar

EXPOSE 9090

ENTRYPOINT ["java","-jar","/app.jar"]
