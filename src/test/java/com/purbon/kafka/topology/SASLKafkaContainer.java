package com.purbon.kafka.topology;

import com.purbon.kafka.topology.integration.AclsManagerIT;
import org.testcontainers.containers.BindMode;
import org.testcontainers.containers.KafkaContainer;

public class SASLKafkaContainer extends KafkaContainer {

  public SASLKafkaContainer(String confluentVersion) {
    super(confluentVersion);

    withEnv("KAFKA_LISTENERS", "PLAINTEXT://0.0.0.0:9093,BROKER://0.0.0.0:9092,SASL_PLAINTEXT://0.0.0.0:9094");
    withEnv("KAFKA_LISTENER_SECURITY_PROTOCOL_MAP", "BROKER:PLAINTEXT,PLAINTEXT:PLAINTEXT, SASL_PLAINTEXT:SASL_PLAINTEXT");
    withEnv("KAFKA_SASL_ENABLED_MECHANISMS", "PLAIN");
    withEnv("KAFKA_AUTHORIZER_CLASS_NAME", "kafka.security.auth.SimpleAclAuthorizer");
    withEnv("KAFKA_MECHANISMS_INTER_BROKER_PROTOCOL", "SASL_PLAINTEXT");
    withEnv("KAFKA_SASL_MECHANISM_INTER_BROKER_PROTOCOL", "PLAIN");
    withEnv("KAFKA_ALLOW_EVERYONE_IF_NO_ACL_FOUND", "FALSE");
    withEnv("KAFKA_SUPER_USER", "User:kafka");
    withEnv("KAFKA_OPTS", "-Djava.security.auth.login.config=/etc/kafka-conf/kafka_server_jaas.conf");
    withEnv("KAFKA_LOG4J_LOGGERS", "kafka.authorizer.logger=DEBUG");

    String fsPath = AclsManagerIT.class.getResource("/docker").getPath();
    String containerPath = "/etc/kafka-conf";
    addFileSystemBind(fsPath, containerPath, BindMode.READ_ONLY);

    withExposedPorts(new Integer[]{9093,9094});
  }

  @Override
  public String getBootstrapServers() {
    int port = this.getMappedPort(9094);
    return String.format("SASL_PLAINTEXT://%s:%s", this.getContainerIpAddress(), port);
  }
}
