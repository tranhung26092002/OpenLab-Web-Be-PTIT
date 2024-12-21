pipeline {
    agent any
    environment {
        // Credentials lưu trên Jenkins
        DB_USERNAME = credentials('POSTGRES_USER') 
        DB_PASSWORD = credentials('POSTGRES_PASSWORD')
    }
    stages {
        stage('Clean Old Containers') {
            steps {
                script {
                    // Stop and remove old containers (if using docker-compose)
                    sh '''
                    if [ "$(docker ps -q -f name=mqtt-service)" ]; then
                        echo "Stopping and removing old containers"
                        docker-compose down
                    else
                        echo "No old containers to stop."
                    fi
                    '''
                }
            }
        }
        stage('Build Application') {
            steps {
                script {
                    // Build jar file
                    echo 'Building application...'
                    sh './mvnw clean package -DskipTests'
                }
            }
        }
        stage('Build and Deploy with Docker') {
            steps {
                script {
                    // Check if Docker is running
                    sh 'docker info > /dev/null 2>&1'
                    if (currentBuild.result == 'SUCCESS') {
                        echo 'Docker is running, proceeding with deployment.'
                        // Using Docker Compose to build and deploy
                        sh '''
                        docker-compose down
                        docker-compose up -d --build
                        '''
                    } else {
                        error 'Docker is not running, skipping deployment.'
                    }
                }
            }
        }
    }
}
