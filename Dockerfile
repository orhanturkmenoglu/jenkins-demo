# Spring Boot için temel imaj
FROM openjdk:17-jdk-slim

# Spring Boot uygulamanızı kopyalayın
COPY target/spring-boot-crud-api.jar /app/spring-boot-crud-api.jar

# Uygulamanın çalışacağı portu açıyoruz
EXPOSE 8083

# Spring Boot uygulamanızı çalıştırıyoruz
ENTRYPOINT ["java", "-jar", "/app/spring-boot-crud-api.jar"]
