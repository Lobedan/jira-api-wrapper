package com.github.lobedan.jira.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Datatype which holds general information like ticket id or key
 * more information are stored in {@link Fields} when it comes to title
 * description etc.
 *
 * @author Sven Klemmer
 * @since 0.1.0
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Issue {

    @JsonProperty(value = "expand")
    private String expand;

    @JsonProperty(value = "id")
    private long id;

    @JsonProperty(value = "self")
    private String apiUrl;

    @JsonProperty(value = "key")
    private String key;

    @JsonProperty(value = "fields")
    private Fields fields;

    public Issue() {
    }

    public String getExpand() {

        return expand;
    }

    public void setExpand(String expand) {
        this.expand = expand;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Fields getFields() {
        return fields;
    }

    public void setFields(Fields fields) {
        this.fields = fields;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Issue issue = (Issue) o;

        if (id != issue.id) return false;
        if (apiUrl != null ? !apiUrl.equals(issue.apiUrl) : issue.apiUrl != null) return false;
        if (expand != null ? !expand.equals(issue.expand) : issue.expand != null) return false;
        if (fields != null ? !fields.equals(issue.fields) : issue.fields != null) return false;
        if (key != null ? !key.equals(issue.key) : issue.key != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = expand != null ? expand.hashCode() : 0;
        result = 31 * result + (int) (id ^ (id >>> 32));
        result = 31 * result + (apiUrl != null ? apiUrl.hashCode() : 0);
        result = 31 * result + (key != null ? key.hashCode() : 0);
        result = 31 * result + (fields != null ? fields.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("expand", expand)
                .append("id", id)
                .append("apiUrl", apiUrl)
                .append("key", key)
                .append("fields", fields)
                .toString();
    }
}
