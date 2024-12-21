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
        stage('Check Docker Status') {
            steps {
                script {
                    // Check Docker status and make sure it's running
                    def dockerStatus = sh(script: 'docker info > /dev/null 2>&1; echo $? ', returnStdout: true).trim()
                    if (dockerStatus != '0') {
                        error 'Docker is not running, skipping deployment.'
                    } else {
                        echo 'Docker is running.'
                    }
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
