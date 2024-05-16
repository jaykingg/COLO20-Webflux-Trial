def PROJECT_NAME = 'jenkins-test-0.0.1-SNAPSHOT'
pipeline {
    agent {
        docker {
            image 'gradle:6.8.3-jdk11'
            args '-v $HOME/.gradle:/home/gradle/.gradle'
        }
    }

    environment {
        EC2_IP = 'http://ec2-3-39-195-186.ap-northeast-2.compute.amazonaws.com:8080/'
        JAR_FILE = 'build/libs/webflux-starter-0.0.1-SNAPSHOT.jar'
        DEPLOY_DIR = '/home/ec2-user/'
    }

    stages {
//         stage('Checkout') {
//             steps {
//                 // Git 리포지토리에서 소스 코드를 체크아웃
//                 git credentialsId: 'your-credentials-id', url: 'https://github.com/your-repo.git'
//             }
//         }

        stage('Build') {
            steps {
                // Gradle을 사용하여 빌드
                sh './gradlew clean build'
            }
        }

        stage('Test') {
            steps {
                // Gradle을 사용하여 테스트 실행
                sh './gradlew test'
            }
        }

//         stage('Deploy') {
//             steps {
//                 // EC2 인스턴스로 JAR 파일을 복사하고 애플리케이션을 재시작
//                 sh """
//                     scp -i ${SSH_KEY_PATH} ${JAR_FILE} ec2-user@${EC2_IP}:${DEPLOY_DIR}
//                     ssh -i ${SSH_KEY_PATH} ec2-user@${EC2_IP} << EOF
//                     pkill -f your-webflux-app.jar
//                     nohup java -jar ${DEPLOY_DIR}/your-webflux-app.jar > /dev/null 2>&1 &
//                     EOF
//                 """
//             }
//         }
    }

    post {
        success {
            echo 'Deployment succeeded!'
        }
        failure {
            echo 'Deployment failed!'
        }
    }
}