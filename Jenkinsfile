pipeline {
  agent any
  stages {
    stage('Stage 1') {
      steps {
        echo 'FirstStep'
      }
    }
    
  stage('SonarQube analysis') {
    def scannerHome = tool 'SonarScanner 4.0';
    withSonarQubeEnv('My SonarQube Server') { // If you have configured more than one global server connection, you can specify its name
      sh "${scannerHome}/bin/sonar-scanner"
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
