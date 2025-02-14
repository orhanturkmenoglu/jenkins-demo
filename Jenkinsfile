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
				script {
					echo "Building Docker image"
					dockerImage = docker.build(DOCKER_IMAGE:${env.BUILD_TAG})
            }
        }

        stage ('Docker Hub Push') {
			steps {
				echo "Pushing Docker image to Docker Hub"
				docker.withRegistry('', 'dockerhub') {
					dockerImage.push();
					dockerImage.push('latest');
				}
			}
		}
    }
}
}