# Name:     xbin-store-cloud-eureka
# Time:     2017-05-07

#FROM java:8-jre-alpine
FROM java:alpine

MAINTAINER LCC <1038127753@qq.com>

RUN mkdir /app

WORKDIR /app

COPY app-service-eureka.jar /app

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app/app-service-eureka.jar"]

EXPOSE 30000