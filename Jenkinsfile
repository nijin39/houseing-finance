node {
  stage('SCM') {
    git 'https://github.com/nijin39/houseing-finance.git'
  }
  stage('SonarQube analysis') {
    withSonarQubeEnv() { // Will pick the global server connection you have configured
      sh './gradlew sonarqube'
    }
  }
}
