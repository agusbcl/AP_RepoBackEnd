FROM amazoncorretto:19-alpine-jdk
MAINTAINER agusbcl
COPY target/ab-0.0.1-SNAPSHOT.jar portfolioab-app.jar
ENTRYPOINT ["java","-jar","/portfolioab-app.jar"]
