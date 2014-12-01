package com.github.lobedan.jira.api.domain.jira;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Information about a task being watched by other users
 *
 * @author Sven Klemmer
 * @since 0.1.0
 */
@JsonIgnoreProperties(ignoreUnknown = true)

public class Watches {

  @JsonProperty(value = "self")
  private String self;

  @JsonProperty(value = "watchCount")
  private int watchCount;

  @JsonProperty(value = "isWatching")
  private boolean isWatching;

  public Watches() {
  }

  public String getSelf() {

    return self;
  }

  public void setSelf(String self) {
    this.self = self;
  }

  public int getWatchCount() {
    return watchCount;
  }

  public void setWatchCount(int watchCount) {
    this.watchCount = watchCount;
  }

  public boolean isWatching() {
    return isWatching;
  }

  public void setWatching(boolean isWatching) {
    this.isWatching = isWatching;
  }

  @Override
  public boolean equals(Object o) {

    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Watches watches = (Watches) o;

    if (isWatching != watches.isWatching) {
      return false;
    }
    if (watchCount != watches.watchCount) {
      return false;
    }
    if (self != null ? !self.equals(watches.self) : watches.self != null) {
      return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int result = self != null ? self.hashCode() : 0;
    result = 31 * result + watchCount;
    result = 31 * result + (isWatching ? 1 : 0);
    return result;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this)
        .append("self", self)
        .append("watchCount", watchCount)
        .append("isWatching", isWatching)
        .toString();
  }
}
