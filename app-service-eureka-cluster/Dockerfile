# Name:    集群


FROM java:8

RUN mkdir /app

WORKDIR /app

COPY app-service-eureka-cluster.jar /app

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "app-service-eureka-cluster.jar"]

EXPOSE 8501