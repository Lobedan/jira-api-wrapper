package com.github.lobedan.jira.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Simple the status of a task e.g is it closed, resolved etc
 *
 * @author Sven Klemmer
 * @since 0.1.0
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Status {

    @JsonProperty(value = "self")
    private String self;

    @JsonProperty(value = "id")
    private long id;

    @JsonProperty(value = "description")
    private String description;

    @JsonProperty(value = "name")
    private String name;

    public Status() {
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

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Status status = (Status) o;

        if (id != status.id) return false;
        if (description != null ? !description.equals(status.description) : status.description != null) return false;
        if (name != null ? !name.equals(status.name) : status.name != null) return false;
        if (self != null ? !self.equals(status.self) : status.self != null) return false;

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
