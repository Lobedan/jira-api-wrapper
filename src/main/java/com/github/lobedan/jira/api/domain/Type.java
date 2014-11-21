package com.github.lobedan.jira.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * it gives the issuetype of a task e.g is it a epic task or just a simple todo
 *
 * @author Sven Klemmer
 * @since 0.1.0
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Type {

    @JsonProperty(value = "self")
    private String self;

    @JsonProperty(value = "id")
    private long id;

    @JsonProperty(value = "description")
    private String description;

    @JsonProperty(value = "iconUrl")
    private String iconUrl;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "subtask")
    private boolean subtask;

    public Type() {
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

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSubtask() {
        return subtask;
    }

    public void setSubtask(boolean subtask) {
        this.subtask = subtask;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Type type = (Type) o;

        if (id != type.id) return false;
        if (subtask != type.subtask) return false;
        if (description != null ? !description.equals(type.description) : type.description != null) return false;
        if (iconUrl != null ? !iconUrl.equals(type.iconUrl) : type.iconUrl != null) return false;
        if (name != null ? !name.equals(type.name) : type.name != null) return false;
        if (self != null ? !self.equals(type.self) : type.self != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = self != null ? self.hashCode() : 0;
        result = 31 * result + (int) (id ^ (id >>> 32));
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (iconUrl != null ? iconUrl.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (subtask ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("self", self)
                .append("id", id)
                .append("description", description)
                .append("iconUrl", iconUrl)
                .append("name", name)
                .append("subtask", subtask)
                .toString();
    }
}
