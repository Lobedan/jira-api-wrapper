package com.github.lobedan.jira.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * simple information about the resolution of a task and a simple description
 *
 * @author Sven Klemmer
 * @since 0.1.0
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Resolution {

  @JsonProperty(value = "self")
  private String self;

  @JsonProperty(value = "id")
  private long id;

  @JsonProperty(value = "description")
  private String description;

  @JsonProperty(value = "name")
  private String name;

  public Resolution() {
  }

  public long getId() {

    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSelf() {

    return self;
  }

  public void setSelf(String self) {
    this.self = self;
  }

  @Override
  public boolean equals(Object o) {

    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Resolution that = (Resolution) o;

    if (id != that.id) {
      return false;
    }
    if (description != null ? !description.equals(that.description) : that.description != null) {
      return false;
    }
    if (name != null ? !name.equals(that.name) : that.name != null) {
      return false;
    }
    if (self != null ? !self.equals(that.self) : that.self != null) {
      return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int result = self != null ? self.hashCode() : 0;
    result = 31 * result + (int) (id ^ (id >>> 32));
    result = 31 * result + (description != null ? description.hashCode() : 0);
    result = 31 * result + (name != null ? name.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this)
        .append("self", self)
        .append("id", id)
        .append("description", description)
        .append("name", name)
        .toString();
  }
}
