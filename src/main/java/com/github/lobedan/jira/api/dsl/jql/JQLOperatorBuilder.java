package com.github.lobedan.jira.api.dsl.jql;

import com.github.lobedan.jira.api.domain.dsl.jql.*;
import com.github.lobedan.jira.api.util.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author svenklemmer
 * @since jira-api-wrapper 0.1.0
 */
public class JQLOperatorBuilder implements JQLOperator {
  private static final Logger LOGGER = LogManager.getLogger(JQLOperatorBuilder.class);

  private JQL jql;

  public JQLOperatorBuilder(JQL aJQL) {
    jql = aJQL;
  }

  private JQLKeyword addOperatorToKeyword(String field) {
    jql.add(field);
    return new JQLKeywordBuilder(jql);
  }

  private JQLValue addOperatorToValue(String field) {
    jql.add(field);
    return new JQLValueBuilder(jql);
  }

  private JQLPredicate addOperatorToPredicate(String field) {
    jql.add(field);
    return new JQLPredicateBuilder(jql);
  }

  private JQLOperator addOperator(String field) {
    jql.add(field);
    return this;
  }

  @Override
  public JQLKeyword equal(Object value) {
    return addOperatorToKeyword(" = " + StringUtils.stringify(value));
  }

  @Override
  public JQLKeyword notEquals(Object value) {
    return addOperatorToKeyword(" != " + StringUtils.stringify(value));
  }

  @Override
  public JQLKeyword greaterThan(Object value) {
    return addOperatorToKeyword(" > " + StringUtils.stringify(value));
  }

  @Override
  public JQLKeyword greaterThanEquals(Object value) {
    return addOperatorToKeyword(" >= " + StringUtils.stringify(value));
  }

  @Override
  public JQLKeyword lessThan(Object value) {
    return addOperatorToKeyword(" < " + StringUtils.stringify(value));
  }

  @Override
  public JQLKeyword lessThanEquals(Object value) {
    return addOperatorToKeyword(" <= " + StringUtils.stringify(value));
  }

  @Override
  public JQLKeyword in(Object... values) {
    return addOperatorToKeyword(" in (" + StringUtils.commaSeparatedList(values) + ")");
  }

  @Override
  public JQLKeyword notIn(Object... values) {
    return addOperatorToKeyword(" not in (" + StringUtils.commaSeparatedList(values) + ")");
  }

  @Override
  public JQLKeyword contains(String value) {
    return addOperatorToKeyword(" ~ " + value);
  }

  @Override
  public JQLKeyword doesNotContain(String value) {
    return addOperatorToKeyword(" !~ " + value);
  }

  @Override
  public JQLValue is() {
    return addOperatorToValue(" is");
  }

  @Override
  public JQLValue isNot() {
    return addOperatorToValue(" is not");
  }

  @Override
  public JQLPredicate was(Object value) {
    return addOperatorToPredicate(" was " + StringUtils.stringify(value));
  }

  @Override
  public JQLPredicate wasIn(Object... values) {
    return addOperatorToPredicate(" was in (" + StringUtils.commaSeparatedList(values) + ")");
  }

  @Override
  public JQLPredicate wasNotIn(Object... values) {
    return addOperatorToPredicate(" was not in (" + StringUtils.commaSeparatedList(values) + ")");
  }

  @Override
  public JQLPredicate wasNot(Object value) {
    return addOperatorToPredicate(" was not " + StringUtils.stringify(value));
  }

  @Override
  public JQLPredicate changed() {
    return addOperatorToPredicate(" CHANGED");
  }

  @Override
  public JQLOperator not() {
    return addOperator(" not");
  }
}
