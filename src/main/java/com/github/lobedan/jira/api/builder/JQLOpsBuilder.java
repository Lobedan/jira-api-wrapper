package com.github.lobedan.jira.api.builder;

import com.github.lobedan.jira.api.domain.builder.JQLMetaHolder;
import com.github.lobedan.jira.api.util.StringUtils;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * @author svenklemmer
 * @since jira-api-wrapper 0.1.0
 */
public class JQLOpsBuilder {
  private static final Logger LOGGER = LogManager.getLogger(JQLOpsBuilder.class);

  private JiraUrlBuilder urlBuilder;

  public JQLOpsBuilder(JiraUrlBuilder aJiraUrlBuilder) {
    urlBuilder = aJiraUrlBuilder;
  }

  public JQLBuilder is(Object value) {
    return add("=", value);
  }
  public JQLBuilder isNot(Object value) {
    return add("!=", value);
  }

  public JQLBuilder was(Object value) {
    return add("was", value);
  }

  public JQLBuilder after(Object value) {
    return add("after", value);
  }

  public JQLBuilder before(Object value) {
    return add("after", value);
  }

  public JQLBuilder in(Object... values) {
    JQLMetaHolder.getInstance().jql().add(" in (" + StringUtils.commaSeparatedList(values) + ")");
    return new JQLBuilder(urlBuilder);
  }

  public JQLBuilder notIn(Object... values) {
    JQLMetaHolder.getInstance().jql().add(" not in (" + StringUtils.commaSeparatedList(values) + ")");
    return new JQLBuilder(urlBuilder);
  }

  private JQLBuilder add(String key, Object value) {
    JQLMetaHolder.getInstance().jql().add(" " + key + " " + StringUtils.stringify(value));
    return new JQLBuilder(urlBuilder);
  }
}
