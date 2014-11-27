package com.github.lobedan.jira.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * litte information about the project this task is assigned to
 *
 * @author Sven Klemmer
 * @since 0.1.0
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Project {

  @JsonProperty(value = "self")
  private String self;

  @JsonProperty(value = "id")
  private long id;

  @JsonProperty(value = "key")
  private String key;

  @JsonProperty(value = "name")
  private String name;

  public Project() {
  }

  public String getSelf() {

    return self;
  }

  public void setSelf(String self) {
    this.self = self;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public boolean equals(Object o) {

    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Project project = (Project) o;

    if (id != project.id) {
      return false;
    }
    if (key != null ? !key.equals(project.key) : project.key != null) {
      return false;
    }
    if (name != null ? !name.equals(project.name) : project.name != null) {
      return false;
    }
    if (self != null ? !self.equals(project.self) : project.self != null) {
      return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int result = self != null ? self.hashCode() : 0;
    result = 31 * result + (int) (id ^ (id >>> 32));
    result = 31 * result + (key != null ? key.hashCode() : 0);
    result = 31 * result + (name != null ? name.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this)
        .append("self", self)
        .append("id", id)
        .append("key", key)
        .append("name", name)
        .toString();
  }
}
