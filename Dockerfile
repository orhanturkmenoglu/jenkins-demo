# Jenkins ve Spring Boot için temel imaj
FROM jenkins/jenkins:2.492.1-jdk17

# Docker'ı kurmak için gerekli bağımlılıkları yüklüyoruz
USER root
RUN apt-get update && apt-get install -y lsb-release
RUN curl -fsSLo /usr/share/keyrings/docker-archive-keyring.asc https://download.docker.com/linux/debian/gpg
RUN echo "deb [arch=$(dpkg --print-architecture) signed-by=/usr/share/keyrings/docker-archive-keyring.asc] https://download.docker.com/linux/debian $(lsb_release -cs) stable" > /etc/apt/sources.list.d/docker.list
RUN apt-get update && apt-get install -y docker-ce-cli
USER jenkins

# Jenkins plugin'lerini kuruyoruz
RUN jenkins-plugin-cli --plugins "blueocean docker-workflow"

# Spring Boot uygulamanızı kopyalayın ve çalıştırın
COPY target/spring-boot-crud-api.jar /app/spring-boot-crud-api.jar
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "/app/spring-boot-crud-api.jar"]

# Jenkins'i başlatıyoruz
EXPOSE 8083 50000
ENTRYPOINT ["/bin/tini", "--", "/usr/local/bin/jenkins.sh"]
