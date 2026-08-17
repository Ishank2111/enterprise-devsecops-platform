pipeline {
    agent any

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build & Test') {
            steps {
                dir('springboot-app') {
                    sh './mvnw clean test'
                }
            }
        }

        stage('Docker Build') {
            steps {
                sh 'docker build -t tyishank/enterprise-app:${BUILD_NUMBER} ./springboot-app'
            }
        }

        stage('Docker Push') {
            steps {
                sh 'docker push tyishank/enterprise-app:${BUILD_NUMBER}'
            }
        }

        stage('Helm Deploy') {
            steps {
                sh '''
                    helm upgrade --install enterprise-app ./enterprise-app \
                    --set image.repository=tyishank/enterprise-app \
                    --set image.tag=${BUILD_NUMBER}
                '''
            }
        }
    }
}