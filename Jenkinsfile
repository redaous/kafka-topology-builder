pipeline {
    agent any

   tools {
        maven 'localMaven'
    }
   parameters {
     string(name: 'Kafka_Brokers',defaultValue: '192.168.1.135', description: 'Kafka Cluster to Apply the Topology on')
   }

   triggers {
      pollSCM ('* * * * *')
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
