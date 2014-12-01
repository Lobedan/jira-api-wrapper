package com.github.lobedan.jira.api.dsl.builder;

import com.github.lobedan.jira.api.domain.Field;
import com.github.lobedan.jira.api.domain.JQL;
import com.github.lobedan.jira.api.domain.Operator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author svenklemmer
 * @since jira-api-wrapper 0.1.0
 */
public class JQLFieldBuilder implements Field {
  private static final Logger LOGGER = LogManager.getLogger(JQLFieldBuilder.class);

  private JQL jql;

  public JQLFieldBuilder(JQL aJql) {
    this.jql = aJql;
  }

  private Operator addField(String field) {
    jql.add(field);
    return new JQLOperatorBuilder(jql);
  }

  @Override
  public Operator affectedVersion() {
    return addField("affectedVersion");
  }

  @Override
  public Operator assignee() {
    return addField("assignee");
  }

  @Override
  public Operator attachments() {
    return addField("attachments");
  }

  @Override
  public Operator category() {
    return addField("category");
  }

  @Override
  public Operator comment() {
    return addField("comment");
  }

  @Override
  public Operator component() {
    return addField("component");
  }

  @Override
  public Operator created() {
    return addField("created");
  }

  @Override
  public Operator createdDate() {
    return created();
  }

  @Override
  public Operator creator() {
    return addField("creator");
  }

  @Override
  public Operator customField(String fieldName) {
    return addField(fieldName);
  }

  @Override
  public Operator description() {
    return addField("description");
  }

  @Override
  public Operator due() {
    return addField("due");
  }

  @Override
  public Operator dueDate() {
    return addField("dueDate");
  }

  @Override
  public Operator environment() {
    return addField("environment");
  }

  @Override
  public Operator epicLink() {
    return addField("\"epic link\"");
  }

  @Override
  public Operator filter() {
    return addField("filter");
  }

  @Override
  public Operator fixVersion() {
    return addField("fixVersion");
  }

  @Override
  public Operator id() {
    return addField("id");
  }

  @Override
  public Operator issue() {
    return addField("issue");
  }

  @Override
  public Operator issueKey() {
    return addField("issueKey");
  }

  @Override
  public Operator key() {
    return addField("key");
  }

  @Override
  public Operator lastViewed() {
    return addField("lastViewed");
  }

  @Override
  public Operator level() {
    return addField("level");
  }

  @Override
  public Operator originalEstimate() {
    return addField("originalEstimate");
  }

  @Override
  public Operator parent() {
    return addField("parent");
  }

  @Override
  public Operator priority() {
    return addField("priority");
  }

  @Override
  public Operator project() {
    return addField("project");
  }

  @Override
  public Operator remainingEstimate() {
    return addField("remainingEstimate");
  }

  @Override
  public Operator reporter() {
    return addField("reporter");
  }

  @Override
  public Operator request() {
    return addField("request");
  }

  @Override
  public Operator resolution() {
    return addField("resolution");
  }

  @Override
  public Operator resolutionDate() {
    return addField("resolutionDate");
  }

  @Override
  public Operator resolved() {
    return addField("resolved");
  }

  @Override
  public Operator savedFilter() {
    return addField("savedFilter");
  }

  @Override
  public Operator searchRequest() {
    return addField("searchRequest");
  }

  @Override
  public Operator sprint() {
    return addField("sprint");
  }

  @Override
  public Operator status() {
    return addField("status");
  }

  @Override
  public Operator summary() {
    return addField("summary");
  }

  @Override
  public Operator text() {
    return addField("text");
  }

  @Override
  public Operator timeEstimate() {
    return addField("timeEstimate");
  }

  @Override
  public Operator timeOriginalEstimate() {
    return addField("timeOriginalEstimate");
  }

  @Override
  public Operator type() {
    return addField("type");
  }

  @Override
  public Operator timeSpent() {
    return addField("timeSpent");
  }

  @Override
  public Operator updated() {
    return addField("updated");
  }

  @Override
  public Operator voter() {
    return addField("voter");
  }

  @Override
  public Operator votes() {
    return addField("votes");
  }

  @Override
  public Operator watcher() {
    return addField("watcher");
  }

  @Override
  public Operator watchers() {
    return addField("watchers");
  }

  @Override
  public Operator workRatio() {
    return addField("workRatio");
  }
}
