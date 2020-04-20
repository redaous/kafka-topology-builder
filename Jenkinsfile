pipeline {
    agent any

   tools {
        maven 'localMaven'
    }

    stages {
        stage('Build') {
            steps {
              sh 'mvn clean package'  
            }
            post {
                success {
                   echo "Archiving the Topology Jar file ..."
                   archiveArtifacts artifacts: '**/target/*.jar'
                }
            }
        }
          
    }
}
