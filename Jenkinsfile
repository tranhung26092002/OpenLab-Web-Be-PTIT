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
                    // Kiểm tra và dừng các container cũ nếu có
                    sh '''
                    # Dừng và xóa container mqtt-service nếu tồn tại
                    if [ "$(docker ps -q -f name=mqtt-service)" ]; then
                        echo "Stopping and removing old mqtt-service container..."
                        docker stop mqtt-service
                        docker rm mqtt-service
                    else
                        echo "No old mqtt-service container to stop."
                    fi

                    # Dừng và xóa container postgres nếu tồn tại
                    if [ "$(docker ps -q -f name=postgres)" ]; then
                        echo "Stopping and removing old postgres container..."
                        docker stop postgres
                        docker rm postgres
                    else
                        echo "No old postgres container to stop."
                    fi

                    # Dừng và xóa container mqtt-broker nếu tồn tại
                    if [ "$(docker ps -q -f name=mqtt-broker)" ]; then
                        echo "Stopping and removing old mqtt-broker container..."
                        docker stop mqtt-broker
                        docker rm mqtt-broker
                    else
                        echo "No old mqtt-broker container to stop."
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
                    if [ ! -f target/*.jar ]; then
                        echo "No .jar file found in target directory! Exiting."
                        exit 1
                    fi
                    echo ".jar file found!"
                    '''
                }
            }
        }
        stage('Build and Deploy with Docker') {
            steps {
                script {
                    // Xác nhận tên image và container trong Docker Compose
                    echo 'Deploying application with Docker Compose...'
                    sh '''
                    docker-compose down
                    docker container prune -f   # Xóa các container đã dừng
                    docker-compose up -d --build
                    '''
                }
            }
        }
    }
}
