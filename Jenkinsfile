pipeline {
    agent any

   tools {
        maven 'localMaven'
        jdk 'localJDK'
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
       stage ('Apply Acls on kafka brokers'){
         steps {
         sh ' java -jar kafka-topology-builder-jar-with-dependencies.jar --clientConfig myparams/topology-builder-sasl-plain.properties  --topology myparams/descriptor.yaml  --brokers 192.168.1.135 --allowDelete '
       }
    }
    }
}
