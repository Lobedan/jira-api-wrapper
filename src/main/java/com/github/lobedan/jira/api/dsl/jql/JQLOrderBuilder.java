package com.github.lobedan.jira.api.dsl.jql;

import com.github.lobedan.jira.api.domain.dsl.jql.JQL;
import com.github.lobedan.jira.api.domain.dsl.jql.JQLOrder;
import com.github.lobedan.jira.api.types.OrderType;
import com.github.lobedan.jira.api.util.StringUtils;

/**
 * @author svenklemmer
 * @since jira-api-wrapper 0.1.0
 */
public class JQLOrderBuilder implements JQLOrder {
  private JQL jql;

  public JQLOrderBuilder(JQL aJQL) {
    jql = aJQL;
  }

  @Override
  public void asc() {
    jql.add(" " + StringUtils.stringify(OrderType.ASC));
  }

  @Override
  public void desc() {
    jql.add(" " + StringUtils.stringify(OrderType.DESC));
  }
}
