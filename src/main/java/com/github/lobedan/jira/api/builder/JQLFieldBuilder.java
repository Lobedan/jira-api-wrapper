package com.github.lobedan.jira.api.builder;

import com.github.lobedan.jira.api.domain.builder.JQLMetaHolder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author svenklemmer
 * @since jira-api-wrapper 0.1.0
 */
public class JQLFieldBuilder {
    private static final Logger LOGGER = LogManager.getLogger(JQLFieldBuilder.class);

    private JiraUrlBuilder urlBuilder;

    public JQLFieldBuilder(JiraUrlBuilder jiraUrl) {
        urlBuilder = jiraUrl;
    }

    public JQLOpsBuilder affectedVersion() {
        return add("affectedVersion");
    }

    public JQLOpsBuilder assignee() {
        return add("assignee");
    }

    public JQLOpsBuilder attachments() {
        return add("attachments");
    }

    public JQLOpsBuilder category() {
        return add("category");
    }

    public JQLOpsBuilder comment() {
        return add("comment");
    }

    public JQLOpsBuilder component() {
        return add("component");
    }

    public JQLOpsBuilder created() {
        return add("created");
    }

    public JQLOpsBuilder creator() {
        return add("creator");
    }

    public JQLOpsBuilder customField(String fieldName) {
        return add(fieldName);
    }

    public JQLOpsBuilder description() {
        return add("description");
    }

    public JQLOpsBuilder dueDate() {
        return add("due");
    }

    public JQLOpsBuilder environment() {
        return add("environment");
    }

    public JQLOpsBuilder epicLink() {
        return add("\"epic link\"");
    }

    public JQLOpsBuilder filter() {
        return add("filter");
    }

    public JQLOpsBuilder fixVersion() {
        return add("fixVersion");
    }

    public JQLOpsBuilder issueKey() {
        return add("issueKey");
    }

    public JQLOpsBuilder key() {
        return issueKey();
    }

    public JQLOpsBuilder lastViewed() {
        return add("lastViewed");
    }

    public JQLOpsBuilder level() {
        return add("level");
    }

    public JQLOpsBuilder originalEstimate() {
        return add("originalEstimate");
    }

    public JQLOpsBuilder parent() {
        return add("parent");
    }

    public JQLOpsBuilder priority() {
        return add("priority");
    }

    public JQLOpsBuilder project() {
        return add("project");
    }

    public JQLOpsBuilder remainingEstimate() {
        return add("remainingEstimate");
    }

    public JQLOpsBuilder reporter() {
        return add("reporter");
    }

    public JQLOpsBuilder resolution() {
        return add("resolution");
    }

    public JQLOpsBuilder resolved() {
        return add("resolved");
    }

    public JQLOpsBuilder resolutionDate() {
        return add("resolutionDate");
    }

    public JQLOpsBuilder sprint() {
        return add("sprint");
    }

    public JQLOpsBuilder status() {
        return add("status");
    }

    public JQLOpsBuilder summary() {
        return add("summary");
    }

    public JQLOpsBuilder text() {
        return add("text");
    }

    public JQLOpsBuilder type() {
        return add("type");
    }

    public JQLOpsBuilder issueType() {
        return add("issueType");
    }

    public JQLOpsBuilder timeSpent() {
        return add("timeSpent");
    }

    public JQLOpsBuilder updated() {
        return add("updated");
    }

    public JQLOpsBuilder voter() {
        return add("voter");
    }

    public JQLOpsBuilder votes() {
        return add("votes");
    }

    public JQLOpsBuilder watcher() {
        return add("watcher");
    }

    public JQLOpsBuilder watchers() {
        return add("watchers");
    }

    public JQLOpsBuilder workRatio() {
        return add("workRatio");
    }

    private JQLOpsBuilder add(String toAdd) {
        JQLMetaHolder.getInstance().jql().add(toAdd);
        return new JQLOpsBuilder(urlBuilder);
    }
}
