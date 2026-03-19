pipeline {
    agent any
    tools{
    maven 'Maven3'
    jdk 'JDK21'
    }
    environment {
        PATH = "C:\\Program Files\\Docker\\Docker\\resources\\bin;${env.PATH}"
        DOCKERHUB_CREDENTIALS_ID = 'Docker_Hub'
        DOCKERHUB_REPO = 'ryougilee/sep2_week1_inclass'
        DOCKER_IMAGE_TAG = 'latest'
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/RyougiLee/SEP2_Week1_inClass.git'
            }
        }
        stage('Run Tests') {
            steps {
                // Run the tests first to generate data for Jacoco and JUnit
                bat 'mvn clean test' // For Windows agents
                // sh 'mvn clean test' // Uncomment if on a Linux agent
            }
        }
        stage('Code Coverage') {
            steps {
                // Generate Jacoco report after the tests have run
                bat 'mvn jacoco:report'
            }
        }
        stage('Publish Test Results') {
            steps {
                // Publish JUnit test results
                junit '**/target/surefire-reports/*.xml'
            }
        }
        stage('Publish Coverage Report') {
            steps {
                // Publish Jacoco coverage report
                jacoco()
            }
        }
        stage('Build Docker Image') {
            steps {
                bat "docker build -t ${env.DOCKERHUB_REPO}:${env.DOCKER_IMAGE_TAG} ."
            }
        }
        stage('Push Docker Image to Docker Hub') {
            steps {
                withCredentials([usernamePassword(credentialsId: env.DOCKERHUB_CREDENTIALS_ID, usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                    bat "docker login -u %DOCKER_USER% -p %DOCKER_PASS%"
                    bat "docker push ${env.DOCKERHUB_REPO}:${env.DOCKER_IMAGE_TAG}"
                }
            }
        }
    }
}





