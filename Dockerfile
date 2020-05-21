FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD target/desafio-0.0.1-SNAPSHOT.jar fcoSalesApp.jar
ENTRYPOINT ["java", "-jar", "/fcoSalesApp.jar"]