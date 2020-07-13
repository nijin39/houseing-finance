pipeline {
  agent any
  stages {
    stage('Stage 1') {
      steps {
        echo 'FirstStep'
      }
    }
    
  stage('SonarQube analysis') {
    withSonarQubeEnv() { // Will pick the global server connection you have configured
      sh './gradlew sonarqube'
    }
  }

    stage('Stage2') {
      steps {
        echo 'Second Step'
      }
    }

    stage('Stage 3') {
      steps {
        echo 'Thrid Step'
      }
    }

  }
}
