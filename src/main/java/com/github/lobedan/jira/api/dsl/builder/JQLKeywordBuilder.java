package com.github.lobedan.jira.api.dsl.builder;

import com.github.lobedan.jira.api.domain.Field;
import com.github.lobedan.jira.api.domain.JQL;
import com.github.lobedan.jira.api.domain.Keyword;
import com.github.lobedan.jira.api.domain.Order;
import com.github.lobedan.jira.api.util.StringUtils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author svenklemmer
 * @since jira-api-wrapper 0.1.0
 */
public class JQLKeywordBuilder implements Keyword {
  private static final Logger LOGGER = LogManager.getLogger(JQLKeywordBuilder.class);

  private JQL jql;

  public JQLKeywordBuilder(JQL aJQL) {
    jql = aJQL;
  }

  private Field addKeywordToField(String field) {
    jql.add(field);
    return new JQLFieldBuilder(jql);
  }

  private Order addKeywordToOrder(String field) {
    jql.add(field);
    return new JQLOrderBuilder(jql);
  }

  @Override
  public Field and() {
    return addKeywordToField(" AND ");
  }

  @Override
  public Field or() {
    return addKeywordToField(" OR ");
  }

  @Override
  public Order orderBy(String... fieldNames) {
    return addKeywordToOrder(" order by " + StringUtils.commaSeparatedList(fieldNames));
  }

  @Override
  public String build() {
    return jql.build();
  }

  @Override
  public JQL andReturn() {
    return jql;
  }
}
