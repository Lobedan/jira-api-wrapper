package com.github.lobedan.jira.api.dsl.builder;

import com.github.lobedan.jira.api.domain.JQL;
import com.github.lobedan.jira.api.domain.Order;

/**
 * @author svenklemmer
 * @since jira-api-wrapper 0.1.0
 */
public class JQLOrderBuilder implements Order {
  private JQL jql;

  public JQLOrderBuilder(JQL aJQL) {
    jql = aJQL;
  }

  @Override
  public void asc() {
    jql.add(" asc");
  }

  @Override
  public void desc() {
    jql.add(" desc");
  }
}
