package com.github.lobedan.jira.api.dsl.jql;

import com.github.lobedan.jira.api.domain.dsl.jql.JQL;

/**
 * @author svenklemmer
 * @since jira-api-wrapper 0.1.0
 */
public class JQLBuilder {
  private static JQL jql;

  public static JQLFieldBuilder jql() {
    jql = new JQL();
    return new JQLFieldBuilder(jql);
  }
}
