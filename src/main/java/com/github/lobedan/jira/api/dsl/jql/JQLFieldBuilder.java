package com.github.lobedan.jira.api.dsl.jql;

import com.github.lobedan.jira.api.domain.dsl.jql.JQL;
import com.github.lobedan.jira.api.domain.dsl.jql.JQLField;
import com.github.lobedan.jira.api.domain.dsl.jql.JQLOperator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author svenklemmer
 * @since jira-api-wrapper 0.1.0
 */
public class JQLFieldBuilder implements JQLField {
  private static final Logger LOGGER = LogManager.getLogger(JQLFieldBuilder.class);

  private JQL jql;

  public JQLFieldBuilder(JQL aJql) {
    this.jql = aJql;
  }

  private JQLOperator addField(String field) {
    jql.add(field);
    return new JQLOperatorBuilder(jql);
  }

  @Override
  public JQLOperator affectedVersion() {
    return addField("affectedVersion");
  }

  @Override
  public JQLOperator assignee() {
    return addField("assignee");
  }

  @Override
  public JQLOperator attachments() {
    return addField("attachments");
  }

  @Override
  public JQLOperator category() {
    return addField("category");
  }

  @Override
  public JQLOperator comment() {
    return addField("comment");
  }

  @Override
  public JQLOperator component() {
    return addField("component");
  }

  @Override
  public JQLOperator created() {
    return addField("created");
  }

  @Override
  public JQLOperator createdDate() {
    return created();
  }

  @Override
  public JQLOperator creator() {
    return addField("creator");
  }

  @Override
  public JQLOperator customField(String fieldName) {
    return addField(fieldName);
  }

  @Override
  public JQLOperator description() {
    return addField("description");
  }

  @Override
  public JQLOperator due() {
    return addField("due");
  }

  @Override
  public JQLOperator dueDate() {
    return addField("dueDate");
  }

  @Override
  public JQLOperator environment() {
    return addField("environment");
  }

  @Override
  public JQLOperator epicLink() {
    return addField("\"epic link\"");
  }

  @Override
  public JQLOperator filter() {
    return addField("filter");
  }

  @Override
  public JQLOperator fixVersion() {
    return addField("fixVersion");
  }

  @Override
  public JQLOperator id() {
    return addField("id");
  }

  @Override
  public JQLOperator issue() {
    return addField("issue");
  }

  @Override
  public JQLOperator issueKey() {
    return addField("issueKey");
  }

  @Override
  public JQLOperator issueType() {
    return addField("issueType");
  }

  @Override
  public JQLOperator key() {
    return addField("key");
  }

  @Override
  public JQLOperator lastViewed() {
    return addField("lastViewed");
  }

  @Override
  public JQLOperator level() {
    return addField("level");
  }

  @Override
  public JQLOperator originalEstimate() {
    return addField("originalEstimate");
  }

  @Override
  public JQLOperator parent() {
    return addField("parent");
  }

  @Override
  public JQLOperator priority() {
    return addField("priority");
  }

  @Override
  public JQLOperator project() {
    return addField("project");
  }

  @Override
  public JQLOperator remainingEstimate() {
    return addField("remainingEstimate");
  }

  @Override
  public JQLOperator reporter() {
    return addField("reporter");
  }

  @Override
  public JQLOperator request() {
    return addField("request");
  }

  @Override
  public JQLOperator resolution() {
    return addField("resolution");
  }

  @Override
  public JQLOperator resolutionDate() {
    return addField("resolutionDate");
  }

  @Override
  public JQLOperator resolved() {
    return addField("resolved");
  }

  @Override
  public JQLOperator savedFilter() {
    return addField("savedFilter");
  }

  @Override
  public JQLOperator searchRequest() {
    return addField("searchRequest");
  }

  @Override
  public JQLOperator sprint() {
    return addField("sprint");
  }

  @Override
  public JQLOperator status() {
    return addField("status");
  }

  @Override
  public JQLOperator summary() {
    return addField("summary");
  }

  @Override
  public JQLOperator text() {
    return addField("text");
  }

  @Override
  public JQLOperator timeEstimate() {
    return addField("timeEstimate");
  }

  @Override
  public JQLOperator timeOriginalEstimate() {
    return addField("timeOriginalEstimate");
  }

  @Override
  public JQLOperator type() {
    return addField("type");
  }

  @Override
  public JQLOperator timeSpent() {
    return addField("timeSpent");
  }

  @Override
  public JQLOperator updated() {
    return addField("updated");
  }

  @Override
  public JQLOperator updatedDate() {
    return addField("updatedDate");
  }

  @Override
  public JQLOperator voter() {
    return addField("voter");
  }

  @Override
  public JQLOperator votes() {
    return addField("votes");
  }

  @Override
  public JQLOperator watcher() {
    return addField("watcher");
  }

  @Override
  public JQLOperator watchers() {
    return addField("watchers");
  }

  @Override
  public JQLOperator workRatio() {
    return addField("workRatio");
  }
}
