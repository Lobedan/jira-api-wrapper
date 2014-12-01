package com.github.lobedan.jira.api.dsl.builder;

import java.util.Date;

import com.github.lobedan.jira.api.domain.Field;
import com.github.lobedan.jira.api.domain.JQL;
import com.github.lobedan.jira.api.domain.Order;
import com.github.lobedan.jira.api.domain.Predicate;
import com.github.lobedan.jira.api.util.StringUtils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author svenklemmer
 * @since jira-api-wrapper 0.1.0
 */
public class JQLPredicateBuilder implements Predicate {
  private static final Logger LOGGER = LogManager.getLogger(JQLPredicateBuilder.class);

  private JQL jql;

  public JQLPredicateBuilder(JQL aJQL) {
    this.jql = aJQL;
  }

  private Predicate addPredicate(String field) {
    jql.add(field);
    return this;
  }

  private Field addPredicateToField(String field) {
    jql.add(field);
    return new JQLFieldBuilder(jql);
  }

  private Order addPredicateToOrder(String field) {
    jql.add(field);
    return new JQLOrderBuilder(jql);
  }

  @Override
  public Predicate after(String date) {
    return addPredicate(" after " + date);
  }

  @Override
  public Predicate after(Date aDate) {
    return after(StringUtils.dateToString(aDate));
  }

  @Override
  public Predicate before(String date) {
    return addPredicate(" before " + date);
  }

  @Override
  public Predicate before(Date date) {
    return after(StringUtils.dateToString(date));
  }

  @Override
  public Predicate by(String username) {
    return addPredicate(" BY " + username);
  }

  @Override
  public Predicate during(String date1, String date2) {
    return addPredicate(" DURING (" + date1 + ", " + date2 + ")");
  }

  @Override
  public Predicate during(Date date1, Date date2) {
    return during(StringUtils.dateToString(date1), StringUtils.dateToString(date2));
  }

  @Override
  public Predicate on(String date) {
    return addPredicate(" ON " + date);
  }

  @Override
  public Predicate on(Date aDate) {
    return on(StringUtils.dateToString(aDate));
  }

  @Override
  public Predicate from(Object value) {
    return addPredicate(" FROM " + StringUtils.stringify(value));
  }

  @Override
  public Predicate to(Object value) {
    return addPredicate(" TO " + StringUtils.stringify(value));
  }

  @Override
  public Field and() {
    return addPredicateToField(" AND ");
  }

  @Override
  public Field or() {
    return addPredicateToField(" OR ");
  }

  @Override
  public Order orderBy(String... fieldNames) {
    return addPredicateToOrder(" order by " + StringUtils.commaSeparatedList(fieldNames));
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
