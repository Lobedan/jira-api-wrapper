package com.github.lobedan.jira.api.dsl.builder;

import com.github.lobedan.jira.api.domain.JQL;
import com.github.lobedan.jira.api.domain.Keyword;
import com.github.lobedan.jira.api.domain.Operator;
import com.github.lobedan.jira.api.domain.Predicate;
import com.github.lobedan.jira.api.domain.Value;
import com.github.lobedan.jira.api.util.StringUtils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author svenklemmer
 * @since jira-api-wrapper 0.1.0
 */
public class JQLOperatorBuilder implements Operator {
  private static final Logger LOGGER = LogManager.getLogger(JQLOperatorBuilder.class);

  private JQL jql;

  public JQLOperatorBuilder(JQL aJQL) {
    jql = aJQL;
  }

  private Keyword addOperatorToKeyword(String field) {
    jql.add(field);
    return new JQLKeywordBuilder(jql);
  }

  private Value addOperatorToValue(String field) {
    jql.add(field);
    return new JQLValueBuilder(jql);
  }

  private Predicate addOperatorToPredicate(String field) {
    jql.add(field);
    return new JQLPredicateBuilder(jql);
  }

  private Operator addOperator(String field) {
    jql.add(field);
    return this;
  }

  @Override
  public Keyword equal(Object value) {
    return addOperatorToKeyword(" = " + StringUtils.stringify(value));
  }

  @Override
  public Keyword notEquals(Object value) {
    return addOperatorToKeyword(" != " + StringUtils.stringify(value));
  }

  @Override
  public Keyword greaterThan(Object value) {
    return addOperatorToKeyword(" > " + StringUtils.stringify(value));
  }

  @Override
  public Keyword greaterThanEquals(Object value) {
    return addOperatorToKeyword(" >= " + StringUtils.stringify(value));
  }

  @Override
  public Keyword lessThan(Object value) {
    return addOperatorToKeyword(" < " + StringUtils.stringify(value));
  }

  @Override
  public Keyword lessThanEquals(Object value) {
    return addOperatorToKeyword(" <= " + StringUtils.stringify(value));
  }

  @Override
  public Keyword in(Object... values) {
    return addOperatorToKeyword(" in (" + StringUtils.commaSeparatedList(values) + ")");
  }

  @Override
  public Keyword notIn(Object... values) {
    return addOperatorToKeyword(" not in (" + StringUtils.commaSeparatedList(values) + ")");
  }

  @Override
  public Keyword contains(String value) {
    return addOperatorToKeyword(" ~ " + value);
  }

  @Override
  public Keyword doesNotContain(String value) {
    return addOperatorToKeyword(" !~ " + value);
  }

  @Override
  public Value is() {
    return addOperatorToValue(" is");
  }

  @Override
  public Value isNot() {
    return addOperatorToValue(" is not");
  }

  @Override
  public Predicate was(Object value) {
    return addOperatorToPredicate(" was " + StringUtils.stringify(value));
  }

  @Override
  public Predicate wasIn(Object... values) {
    return addOperatorToPredicate(" was in (" + StringUtils.commaSeparatedList(values) + ")");
  }

  @Override
  public Predicate wasNotIn(Object... values) {
    return addOperatorToPredicate(" was not in (" + StringUtils.commaSeparatedList(values) + ")");
  }

  @Override
  public Predicate wasNot(Object value) {
    return addOperatorToPredicate(" was not " + StringUtils.stringify(value));
  }

  @Override
  public Predicate changed() {
    return addOperatorToPredicate(" CHANGED");
  }

  @Override
  public Operator not() {
    return addOperator(" not");
  }
}
