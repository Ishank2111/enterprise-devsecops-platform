pipeline {
    agent any 

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






// pipeline {
//     agent any

//     stages {

//         stage('Checkout') {
//             steps {
//                 checkout scm
//             }
//         }

//         stage('Build & Test') {
//             steps {
//                 dir('springboot-app') {
//                     sh './mvnw clean test'
//                 }
//             }
//         }

//         stage('Docker Build') {
//             steps {
//                 sh 'docker build -t tyishank/enterprise-app:${BUILD_NUMBER} ./springboot-app'
//             }
//         }

//         stage('Docker Push') {
//             steps {
//                 sh 'docker push tyishank/enterprise-app:${BUILD_NUMBER}'
//             }
//         }

//         stage('Helm Deploy') {
//             steps {
//                 sh '''
//                     helm upgrade --install enterprise-app ./enterprise-app \
//                     --set image.repository=tyishank/enterprise-app \
//                     --set image.tag=${BUILD_NUMBER}
//                 '''
//             }
//         }
//     }
// }


// pipeline {
//     agent any 
//         environment {
//             APP_NAME = "enterprise-app"
//             VERSION = "${BUILD_NUMBER}"
//         }
//         stages {
//             stage("Application Info") {
//                 steps {
//                     echo "Application: ${APP_NAME}"
//                     echo "VERSION: ${VERSION}"
                
//                 }
//             }
//         }
// }

// pipeline {
//     agent any

//     stages {
//         stage("Build") {
//             steps {
//                 echo "Building application"
//             }
//         }
//         stage("Test") {
//             steps {
//                 echo "Running tests"
//             }
//         }
//     }

//     post {
//         success {
//             echo "Pipeline successful"
//         }

//         failure {
//             echo "Pipeline failed"
//         }

//         always {
//             echo "Pipeline completed"
//              cleanWs()


//         }
//     }
// }


// pipeline {
//     agent any 

//     environment {
//         DEPLOY_ENV = "production"
//     }
//     stages {
//         stage("Build") {
//             steps{
//                echo "Building"
//             }
//         }
//          stage("Test") {
//             steps{
//                echo "Testing" 
//             }
//         }
//          stage("Deploy") {
//             when {
//                 branch "main"
//             }
//             steps{
//                 echo "Deploying to production"
//             }
//         }
//     }
// }


// pipeline {
//     agent any
//     stages {
//         stage("Build") {
//             steps {
//                 echo "Build completed"
//             }
//             input {
//                 message "Do you want to deploy?"
//                 ok "Yes"
//             }
//         }
//     }
// }


// pipeline {
//     agent any 

//     stages {
//         stage("Build"){
//             steps {
//                 echo "Build completed"
//             }
//         }
//         stage("Approval") {
//             steps {
//                 input message: "Do you want to deploy?", ok: "Yes"
//             }
//         }
//         stage("Deploye") {
//             steps {
//                 echo "Deploying application"
//             }
//         }
//     }
// }

// pipeline {
//     agent any
// environment {
//     IMAGE_NAME = "my-app"
//     IMAGE_TAG = "${BUILD_NUMBER}"
// }
//     stages {
//         stage("Docker Login") {
//             wi
//             steps {
//                     sh "docker build -t ${IMAGE_NAME}:${IMAGE_TAG} ."
//             }
//         }
//         stage("Docker Push") {
//             steps {
//                     sh "docker push ${IMAGE_NAME}:${IMAGE_TAG}"
//             }
//         }
//     }
// }

// pipeline {
//     agent any

//     environment {
//         IMAGE_NAME = "my-app"
//         IMAGE_TAG = "${BUILD_NUMBER}"
//     }

//     stages {

//         stage("Docker Login") {
//             steps {
//                 withCredentials([
//                     usernamePassword(
//                         credentialsId: 'dockerhub-creds',
//                         usernameVariable: 'DOCKER_USER',
//                         passwordVariable: 'DOCKER_PASS'
//                     )
//                 ]) {
//                     sh '''
//                         echo "$DOCKER_PASS" | docker login \
//                             -u "$DOCKER_USER" \
//                             --password-stdin
//                     '''
//                 }
//             }
//         }

//         stage("Docker Build") {
//             steps {
//                 sh "docker build -t ${IMAGE_NAME}:${IMAGE_TAG} ."
//             }
//         }

//         stage("Docker Push") {
//             steps {
//                 sh "docker push ${IMAGE_NAME}:${IMAGE_TAG}"
//             }
//         }
//     }
// }

// pipeline {
//     agent any

//     environment {
//         IMAGE_NAME = "my-app"
//         IMAGE_TAG = "${BUILD_NUMBER}"
//     }

//     stages {

//         stage("Checkout") {
//             steps {
//                 checkout scm
//             }
//         }

//         stage("Build") {
//             steps {
//                 dir('springboot-app') {
//                     sh "./mvnw clean package -DskipTests"
//                 }
//             }
//         }

//         stage("Test") {
//             steps {
//                 dir('springboot-app') {
//                     sh "./mvnw test"
//                 }
//             }
//         }

//         stage("Docker Build") {
//             steps {
//                 dir('springboot-app') {
//                     sh "docker build -t ${IMAGE_NAME}:${IMAGE_TAG} ."
//                 }
//             }
//         }

//         stage("Docker Login") {
//             steps {
//                 withCredentials([
//                     usernamePassword(
//                         credentialsId: 'dockerhub-creds',
//                         usernameVariable: 'DOCKER_USER',
//                         passwordVariable: 'DOCKER_PASS'
//                     )
//                 ]) {
//                     sh '''
//                         echo "$DOCKER_PASS" | docker login \
//                             -u "$DOCKER_USER" \
//                             --password-stdin
//                     '''
//                 }
//             }
//         }

//         stage("Docker Push") {
//             steps {
//                 sh "docker push ${IMAGE_NAME}:${IMAGE_TAG}"
//             }
//         }
//         Stage("Helm Deploy") {
//             steps {
//                 sh ''' 
//                 helm upgrade --install enterprise-app ./enterprise-app \
//                 --set image.repository=my-app \
//                 --set image.tag=${BUILD_NUMBER}
//                 '''
//             }
//         }

//         stage("monitoring") {
//             steps {
//                 echo "Monitoring application"
//             }
//         }
//     }
// }


// pipeline {
//     agent any

//     environment {
//         IMAGE_NAME = "my-app"
//         IMAGE_TAG = "${BUILD_NUMBER}"
//     }

//     stages {

//         stage("Build") {
//             steps {
//                 echo "Building application"
//             }
//         }

//         stage("Tests") {
//             parallel {

//                 stage("Unit Test") {
//                     steps {
//                         echo "Running unit tests"
//                     }
//                 }

//                 stage("Integration Test") {
//                     steps {
//                         echo "Running integration tests"
//                     }
//                 }

//                 stage("Security Scan") {
//                     steps {
//                         echo "Running security scan"
//                     }
//                 }
//             }
//         }

//         stage("Docker Build") {
//             steps {
//                 dir('springboot-app') {
//                     sh "docker build -t ${IMAGE_NAME}:${IMAGE_TAG} ."
//                 }
//             }
//         }

//         stage("Deploy") {
//             steps {
//                 echo "Deploying application"
//             }
//         }
//     }
// }


// pipeline {
//     agent any 

//     stages {
//         stage("Integration Test") {
//             steps {
//                 dir('springboot-app') {
//                     timeout(time: 10, unit: 'MINUTES') {
//                         sh "./mvnw test"
//                     }
//                 }
//             }
//         }
//     }
// }

// pipeline {
//     agent any

//     stages {
//         stage("Security Scan") {
//             steps {
//                 catchError(
//                     buildResult: 'SUCCESS',
//                     stageResult: 'FAILURE'
//                 ) {
//                     sh './security-scan.sh'
//                 }
//             }
//         }
//     }
// }