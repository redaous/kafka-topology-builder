package com.purbon.kafka.topology.model;

import com.purbon.kafka.topology.TopicManager;
import java.util.HashMap;
import java.util.Map;

public class TopicImpl implements Topic {

  public static final String UNKNOWN_DATATYPE = "unknown";
  public static final String DEFAULT_TOPIC_NAME = "default";
  private String dataType;
  private String name;
  private HashMap<String, String> config;

  private Project project;

  public TopicImpl(String name) {
    this(name, UNKNOWN_DATATYPE, new HashMap<>());
  }

  public TopicImpl(String name, String dataType) {
    this(name, dataType, new HashMap<>());
  }

  public TopicImpl(String name, String dataType, HashMap<String, String> config) {
    this.name = name;
    this.dataType = dataType;
    this.config = config;
  }

  public TopicImpl() {
    this(DEFAULT_TOPIC_NAME, UNKNOWN_DATATYPE, new HashMap<>());
  }

  public String getName() {
    return name;
  }

  private String toString(Project project) {
    StringBuilder sb = new StringBuilder();
    sb.append(project.buildTopicPrefix())
        .append(".")
        .append(getName());

    if (!getDataType().equals(UNKNOWN_DATATYPE)) {
      sb.append(".")
          .append(getDataType());
    }

    return sb.toString();
  }

  @Override
  public String toString() {
    return toString(project);
  }

  public void setName(String name) {
    this.name = name;
  }

  public HashMap<String, String> getConfig() {
    return config;
  }

  public void setConfig(HashMap<String, String> config) {
    this.config = config;
  }

  public Map<String, String> rawConfig() {
    getConfig().remove(TopicManager.NUM_PARTITIONS);
    getConfig().remove(TopicManager.REPLICATION_FACTOR);
    return getConfig();
  }

  @Override
  public int getNumPartition() {
    return 0;
  }

  @Override
  public short getReplicationFactor() {
    return 0;
  }

  public String getDataType() {
    return dataType;
  }

  @Override
  public void setProject(edu.umd.cs.findbugs.Project project) {

  }

  public void setProject(Project project) {
    this.project = project;
  }
}
