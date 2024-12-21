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
        stage('Fix Permissions') {
            steps {
                script {
                    // Cấp quyền thực thi cho mvnw
                    sh 'chmod +x ./mvnw'
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
        stage('Verify Jar Existence') {
            steps {
                script {
                    // Kiểm tra sự tồn tại của mqtt-service.jar
                    sh '''
                    if [ ! -f target/mqtt-service.jar ]; then
                        echo "mqtt-service.jar not found! Exiting."
                        exit 1
                    fi
                    echo "mqtt-service.jar found!"
                    '''
                }
            }
        }
        stage('Build and Deploy with Docker') {
            steps {
                script {
                    // Using Docker Compose to build and deploy
                    echo 'Deploying application with Docker Compose...'
                    sh '''
                    docker-compose down
                    docker-compose up -d --build
                    '''
                }
            }
        }
    }
}
