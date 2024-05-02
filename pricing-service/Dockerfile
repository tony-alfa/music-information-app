FROM openjdk:21
VOLUME /tmp
EXPOSE 8081
ADD ./target/pricing-service-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
