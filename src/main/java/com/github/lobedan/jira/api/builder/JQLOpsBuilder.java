package com.github.lobedan.jira.api.builder;

import com.github.lobedan.jira.api.domain.builder.JQLMetaHolder;
import com.github.lobedan.jira.api.types.ValueType;
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

  public JQLBuilder equal(Object value) { return add("=", value); }
  public JQLBuilder notEqual(Object value) { return add("!=", value); }

  public JQLBuilder lessThan(Object value) { return add("<", value); }
  public JQLBuilder lessThanEquals(Object value) { return add("<=", value); }

  public JQLBuilder greaterThan(Object value) { return add(">", value); }
  public JQLBuilder greaterThanEquals(Object value) { return add(">=", value); }

  public JQLBuilder is(ValueType value) {
    return add("is", value);
  }
  public JQLBuilder isNot(ValueType value) {
    return add("is", value);
  }

  public JQLBuilder was(Object value) {
    return add("was", value);
  }

  public JQLBuilder after(Object value) {
    return add("after", value);
  }
  public JQLBuilder before(Object value) {
    return add("before", value);
  }

  public JQLBuilder in(Object... values) {
    return addMultiple("in", values);
  }
  public JQLBuilder notIn(Object... values) {
    return addMultiple("not in", values);
  }

  public JQLBuilder contains(String value) {
    return add("~", value);
  }
  public JQLBuilder doesNotContain(String value) {
    return add("!~", value);
  }

  private JQLBuilder add(String key, Object value) {
    JQLMetaHolder.getInstance().jql().add(" " + key + " " + StringUtils.stringify(value));
    return new JQLBuilder(urlBuilder);
  }

  private JQLBuilder addMultiple(String key, Object... values) {
    JQLMetaHolder.getInstance().jql().add(" (" + StringUtils.commaSeparatedList(values) + ")");
    return new JQLBuilder(urlBuilder);
  }

  public static void empty() {
    JQLMetaHolder.getInstance().jql().add("empty");
  }
  public static void nul() {
    JQLMetaHolder.getInstance().jql().add("null");
  }
}
