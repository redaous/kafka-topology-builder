pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
              sh '/opt/apache-maven-3.6.3/bin/mvn clean package'  
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
