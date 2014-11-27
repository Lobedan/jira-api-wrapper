package com.github.lobedan.jira.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Information about the author of a task
 * <p/>
 * avatarUrls are missing because they are not a really usefull informationen
 * they are maybe used only in uis where its needed to show them (e.g. virtual boards)
 *
 * @author Sven Klemmer
 * @since 0.1.0
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Reporter {

  @JsonProperty(value = "self")
  private String self;

  @JsonProperty(value = "displayName")
  private String name;

  @JsonProperty(value = "name")
  private String userName;

  @JsonProperty(value = "emailAddress")
  private String emailAdress;

  @JsonProperty(value = "active")
  private boolean active;

  public Reporter() {
  }

  public String getSelf() {

    return self;
  }

  public void setSelf(String self) {
    this.self = self;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getEmailAdress() {
    return emailAdress;
  }

  public void setEmailAdress(String emailAdress) {
    this.emailAdress = emailAdress;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Reporter reporter = (Reporter) o;

    if (active != reporter.active) {
      return false;
    }
    if (emailAdress != null ? !emailAdress.equals(reporter.emailAdress) : reporter.emailAdress != null) {
      return false;
    }
    if (name != null ? !name.equals(reporter.name) : reporter.name != null) {
      return false;
    }
    if (self != null ? !self.equals(reporter.self) : reporter.self != null) {
      return false;
    }
    if (userName != null ? !userName.equals(reporter.userName) : reporter.userName != null) {
      return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int result = self != null ? self.hashCode() : 0;
    result = 31 * result + (name != null ? name.hashCode() : 0);
    result = 31 * result + (userName != null ? userName.hashCode() : 0);
    result = 31 * result + (emailAdress != null ? emailAdress.hashCode() : 0);
    result = 31 * result + (active ? 1 : 0);
    return result;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this)
        .append("self", self)
        .append("name", name)
        .append("userName", userName)
        .append("emailAdress", emailAdress)
        .append("active", active)
        .toString();
  }
}
