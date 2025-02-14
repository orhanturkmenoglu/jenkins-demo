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
				sh "docker run -d -p 8080:8080 ${DOCKER_IMAGE}"
            }
        }

        stage ('Docker Hub Push') {
			steps {
				echo "Pushing Docker image to Docker Hub"
                withCredentials([string(credentialsId: 'dockerhub', variable: 'DOCKER_HUB_PASS')]) {
					sh 'echo $DOCKER_HUB_PASS | docker login -u orhanturkmenoglu --password-stdin'
                    sh "docker tag myapp:latest orhanturkmenoglu/spring-boot-crud-api:latest"
					sh "docker push ${DOCKER_IMAGE}"
			}
		}
    }
}
}