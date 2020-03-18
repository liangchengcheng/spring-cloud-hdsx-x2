FROM java:8  
VOLUME /tmp  
ADD center.jar center.jar  
RUN sh -c 'touch /center.jar'  
ENV JAVA_OPTS=""  
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/center.jar"]  