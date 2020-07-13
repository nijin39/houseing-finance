node {
  stage('SCM') {
    git 'https://github.com/nijin39/houseing-finance.git'
  }
  stage('SonarQube analysis') {
    withSonarQubeEnv() { // Will pick the global server connection you have configured
      sh './gradlew -Dsonar.host.url=http://ec2-3-34-52-68.ap-northeast-2.compute.amazonaws.com:9000 sonarqube'
    }
  }
}
