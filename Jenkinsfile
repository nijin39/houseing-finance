pipeline {
  agent any
  stages {
    stage('Stage 1') {
      steps {
        echo 'FirstStep'
      }
    }
    
  stage('Sonarqube') {
    environment {
        scannerHome = tool 'SonarQubeScanner'
    }    steps {
        withSonarQubeEnv('sonarqube') {
            sh "${scannerHome}/bin/sonar-scanner"
        }        timeout(time: 10, unit: 'MINUTES') {
            waitForQualityGate abortPipeline: true
        }
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
