pipeline {
	agent any

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
				sh 'mvn test'
				echo "Test is done"
            }
        }

        stage('Docker Build & Run') {
			steps {
				echo "Docker Build and Run is done"
            }
        }
    }
}
