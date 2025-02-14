/*
pipeline {
	agent any

    environment {
		DOCKER_IMAGE = "orhanturkmenoglu/spring-boot-crud-api"
    }

    stages {
		stage('Checkout') {
			steps {
				echo "Checkout is done"
            }
        }

        stage('Build') {
			steps {
				echo "Build is done"
            }
        }

        stage('Test') {
			steps {
				echo "Test is done"
            }
        }

        stage('Docker Build & Run') {
			steps {
				script {
					echo "Building Docker image"
                    // Docker image'ı build et
                    dockerImage = docker.build("${DOCKER_IMAGE}:${env.BUILD_TAG}")
                }
            }
        }

        stage('Docker Hub Push') {
			steps {
				echo "Pushing Docker image to Docker Hub"
                script {
					// Docker Hub'a push işlemi
                    docker.withRegistry('', 'dockerhub') {
						dockerImage.push()  // Image'ı push et
                        dockerImage.push('latest')  // latest tag ile de push et
                    }
                }
            }
        }
    }
}
*/

pipeline {
	agent any

    environment {
		DOCKER_IMAGE_SPRING_BOOT = "orhanturkmenoglu/spring-boot-crud-api:latest"
        DOCKER_IMAGE_JENKINS = "orhanturkmenoglu/jenkins:latest"
    }

	tools {
		maven 'myMaven'
	}

    stages {
		stage('Checkout') {
			steps {
				echo "Checking out code from GitHub"
                checkout scm
            }
        }

        stage('Build Spring Boot') {
			steps {
				echo "Building Spring Boot Application"
                sh 'mvn clean package -DskipTests'  // Spring Boot uygulamanızı build edin
            }
        }

        stage('Docker Build & Run Jenkins') {
			steps {
				echo "Building Docker image for Jenkins"
                script {
					// Jenkins imajını build ediyoruz
                    dockerImageJenkins = docker.build(DOCKER_IMAGE_JENKINS)
                    dockerImageJenkins.run('-d -p 8080:8080 -p 50000:50000')  // Jenkins konteynerini çalıştırıyoruz
                }
            }
        }

        stage('Docker Build & Run Spring Boot') {
			steps {
				echo "Building Docker image for Spring Boot Application"
                script {
					// Spring Boot imajını build ediyoruz
                    dockerImageSpringBoot = docker.build(DOCKER_IMAGE_SPRING_BOOT)
                    dockerImageSpringBoot.run('-d -p 8081:8080')  // Spring Boot uygulamasını çalıştırıyoruz
                }
            }
        }

        stage('Clean up Jenkins Container') {
			steps {
				echo "Cleaning up Jenkins container"
                sh "docker stop ${dockerImageJenkins.id}"  // Jenkins konteynerini durduruyoruz
                sh "docker rm ${dockerImageJenkins.id}"    // Jenkins konteynerini siliyoruz
            }
        }

        stage('Docker Hub Push') {
			steps {
				echo "Pushing Docker images to Docker Hub"
                script {
					// Spring Boot için Docker Hub'a push işlemi
                    docker.withRegistry('', 'dockerhub') {
						dockerImageSpringBoot.push()
                        dockerImageSpringBoot.push('latest')
                    }
                }
            }
        }
    }
}
