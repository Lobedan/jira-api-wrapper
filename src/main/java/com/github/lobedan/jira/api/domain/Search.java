package com.github.lobedan.jira.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * Object representing the root from json string received at /api/latest/search
 *
 * the most values are included in {@link com.github.lobedan.jira.api.domain.Issue object}
 *
 * @author Sven Klemmer
 * @since 0.1.0
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Search {

    /**
     * list of comma seperated string which tells, which particular sections of fields are
     * available in this searchquery
     */
    @JsonProperty(value = "expand")
    private String expand;

    @JsonProperty(value = "startAt")
    private int startAt;

    @JsonProperty(value = "maxResults")
    private int maxResults;

    @JsonProperty(value = "total")
    private int total;

    @JsonProperty(value = "issues")
    private List<Issue> issues;

    public Search() {
    }

    public String getExpand() {
        return expand;
    }

    public void setExpand(String expand) {
        this.expand = expand;
    }

    public int getStartAt() {
        return startAt;
    }

    public void setStartAt(int startAt) {
        this.startAt = startAt;
    }

    public int getMaxResults() {
        return maxResults;
    }

    public void setMaxResults(int maxResults) {
        this.maxResults = maxResults;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Issue> getIssues() {
        return issues;
    }

    public void setIssues(List<Issue> issues) {
        this.issues = issues;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Search search = (Search) o;

        if (maxResults != search.maxResults) return false;
        if (startAt != search.startAt) return false;
        if (total != search.total) return false;
        if (expand != null ? !expand.equals(search.expand) : search.expand != null) return false;
        if (issues != null ? !issues.equals(search.issues) : search.issues != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = expand != null ? expand.hashCode() : 0;
        result = 31 * result + startAt;
        result = 31 * result + maxResults;
        result = 31 * result + total;
        result = 31 * result + (issues != null ? issues.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("expand", expand)
                .append("startAt", startAt)
                .append("maxResults", maxResults)
                .append("total", total)
                .append("issues", issues)
                .toString();
    }
}
