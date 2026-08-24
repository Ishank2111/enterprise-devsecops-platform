pipeline {
    agent any 

    triggers {
       cron('H/5 * * * *')
    }
    environment {
        APP_NAME = "tyishank/enterprise-app"
        VERSION = "${BUILD_NUMBER}"
    }
    stages {
        stage("checkout") {
            steps {
                checkout scm
            }
        }

        stage("Build") {
            steps {
                dir('springboot-app') {
                    sh "./mvnw clean package"
                }
            }
        }

        stage("Test") {
            steps {
                dir('springboot-app') {
                     sh './mvnw test'
                }
            }
        }

        stage("Docker Build") {
            steps {
                dir('springboot-app') {
                     sh "docker build -t ${APP_NAME}:${VERSION} ."
                }
            }
        }

        stage("Trivy Scan") {
             steps {
                 sh "trivy image --exit-code 1 --severity HIGH,CRITICAL ${APP_NAME}:${VERSION}"
             }
        }
  

        stage("Docker Login") {
            steps {
                withCredentials([
                    usernamePassword(
                        credentialsId: "dockerhub-creds",
                        usernameVariable: "DOCKER_USER",
                        passwordVariable: "DOCKER_PASS"
                    )
                ]) {
                    sh '''
                        echo "$DOCKER_PASS" | docker login \
                            -u "$DOCKER_USER" \
                            --password-stdin
                    '''
                }
            }
        }

        stage("Docker Push") {
            steps {
                  sh "docker push ${APP_NAME}:${VERSION}"
            }
        }
        stage("Helm Deploy") {
    steps {
        sh '''
            helm upgrade --install enterprise-app ./enterprise-app \
            --set image.repository=${APP_NAME} \
            --set image.tag=${VERSION}
        '''
    } 
        }
    }
}


