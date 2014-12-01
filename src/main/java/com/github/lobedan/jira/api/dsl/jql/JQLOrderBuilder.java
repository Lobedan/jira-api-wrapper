package com.github.lobedan.jira.api.dsl.jql;

import com.github.lobedan.jira.api.domain.dsl.jql.JQL;
import com.github.lobedan.jira.api.domain.dsl.jql.JQLKeyword;
import com.github.lobedan.jira.api.domain.dsl.jql.JQLOperator;
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

  private JQLKeyword addOrderType(String field) {
    jql.add(field);
    return new JQLKeywordBuilder(jql);
  }

  @Override
  public JQLKeyword asc() {
    return addOrderType(" " + StringUtils.stringify(OrderType.ASC));
  }

  @Override
  public JQLKeyword desc() {
    return addOrderType(" " + StringUtils.stringify(OrderType.DESC));
  }
}
