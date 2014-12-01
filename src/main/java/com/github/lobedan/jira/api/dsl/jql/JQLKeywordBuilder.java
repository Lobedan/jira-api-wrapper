package com.github.lobedan.jira.api.dsl.jql;

import com.github.lobedan.jira.api.domain.dsl.jql.JQL;
import com.github.lobedan.jira.api.domain.dsl.jql.JQLField;
import com.github.lobedan.jira.api.domain.dsl.jql.JQLKeyword;
import com.github.lobedan.jira.api.domain.dsl.jql.JQLOrder;
import com.github.lobedan.jira.api.util.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author svenklemmer
 * @since jira-api-wrapper 0.1.0
 */
public class JQLKeywordBuilder implements JQLKeyword {
  private static final Logger LOGGER = LogManager.getLogger(JQLKeywordBuilder.class);

  private JQL jql;

  public JQLKeywordBuilder(JQL aJQL) {
    jql = aJQL;
  }

  private JQLField addKeywordToField(String field) {
    jql.add(field);
    return new JQLFieldBuilder(jql);
  }

  private JQLOrder addKeywordToOrder(String field) {
    jql.add(field);
    return new JQLOrderBuilder(jql);
  }

  @Override
  public JQLField and() {
    return addKeywordToField(" AND ");
  }

  @Override
  public JQLField or() {
    return addKeywordToField(" OR ");
  }

  @Override
  public JQLOrder orderBy(String... fieldNames) {
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
