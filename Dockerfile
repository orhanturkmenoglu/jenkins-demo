# Spring Boot için temel imaj
FROM openjdk:17-jdk-slim

# Spring Boot uygulamanızın JAR dosyasını kopyalayın
COPY target/springsection1-0.0.1-SNAPSHOT.jar /app/springsection1-0.0.1-SNAPSHOT.jar

# Uygulamanın çalışacağı portu açıyoruz
EXPOSE 8083

# Spring Boot uygulamanızı çalıştırıyoruz
ENTRYPOINT ["java", "-jar", "/app/springsection1-0.0.1-SNAPSHOT.jar"]
