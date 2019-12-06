package com.purbon.kafka.topology.model;

import edu.umd.cs.findbugs.Project;
import java.util.HashMap;
import java.util.Map;

public interface Topic {

  Map<String, String> rawConfig();
  int getNumPartition();
  short getReplicationFactor();

  String getName();
  String toString();

  void setName(String name);
  HashMap<String, String> getConfig();

  void setConfig(HashMap<String, String> config);

  String getDataType();
  void setProject(Project project);

}
