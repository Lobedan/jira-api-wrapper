package com.github.lobedan.jira.api.dsl.builder;

import com.github.lobedan.jira.api.domain.JQL;
import com.github.lobedan.jira.api.domain.Predicate;
import com.github.lobedan.jira.api.domain.Value;

/**
 * @author svenklemmer
 * @since jira-api-wrapper 0.1.0
 */
public class JQLValueBuilder implements Value {
  private JQL jql;

  public JQLValueBuilder(JQL aJQL) {
    jql = aJQL;
  }

  private Predicate addValueToPredicate(String field) {
    jql.add(field);
    return new JQLPredicateBuilder(jql);
  }

  @Override
  public Predicate empty() {
    return addValueToPredicate(" empty");
  }

  @Override
  public Predicate nul() {
    return addValueToPredicate(" null");
  }
}
