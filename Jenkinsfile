pipeline {
	agent any

	environment {
		DOCKER_IMAGE = "orhanturkmenoglu/spring-boot-crud-api:latest"
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
				echo "Docker Build and Run is done"
				sh "docker build -t ${DOCKER_IMAGE} ."
				sh "docker run -d -p 8082:8080 ${DOCKER_IMAGE}"
            }
        }

        stage ('Docker Hub Push') {
			steps {
				echo "Pushing Docker image to Docker Hub"
                withCredentials([usernamePassword(credentialsId: 'dockerhub', usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
					sh 'docker login -u $DOCKER_USERNAME -p $DOCKER_PASSWORD'
                    sh "docker tag ${DOCKER_IMAGE} ${DOCKER_IMAGE}"
                    sh "docker push ${DOCKER_IMAGE}"
			}
		}
    }
}
}