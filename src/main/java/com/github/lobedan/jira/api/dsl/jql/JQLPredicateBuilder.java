package com.github.lobedan.jira.api.dsl.jql;

import com.github.lobedan.jira.api.domain.dsl.jql.JQL;
import com.github.lobedan.jira.api.domain.dsl.jql.JQLField;
import com.github.lobedan.jira.api.domain.dsl.jql.JQLOrder;
import com.github.lobedan.jira.api.domain.dsl.jql.JQLPredicate;
import com.github.lobedan.jira.api.util.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;

/**
 * @author svenklemmer
 * @since jira-api-wrapper 0.1.0
 */
public class JQLPredicateBuilder implements JQLPredicate {
  private static final Logger LOGGER = LogManager.getLogger(JQLPredicateBuilder.class);

  private JQL jql;

  public JQLPredicateBuilder(JQL aJQL) {
    this.jql = aJQL;
  }

  private JQLPredicate addPredicate(String field) {
    jql.add(field);
    return this;
  }

  private JQLField addPredicateToField(String field) {
    jql.add(field);
    return new JQLFieldBuilder(jql);
  }

  private JQLOrder addPredicateToOrder(String field) {
    jql.add(field);
    return new JQLOrderBuilder(jql);
  }

  @Override
  public JQLPredicate after(String date) {
    return addPredicate(" after " + date);
  }

  @Override
  public JQLPredicate after(Date aDate) {
    return after(StringUtils.dateToString(aDate));
  }

  @Override
  public JQLPredicate before(String date) {
    return addPredicate(" before " + date);
  }

  @Override
  public JQLPredicate before(Date date) {
    return after(StringUtils.dateToString(date));
  }

  @Override
  public JQLPredicate by(String username) {
    return addPredicate(" BY " + username);
  }

  @Override
  public JQLPredicate during(String date1, String date2) {
    return addPredicate(" DURING (" + date1 + ", " + date2 + ")");
  }

  @Override
  public JQLPredicate during(Date date1, Date date2) {
    return during(StringUtils.dateToString(date1), StringUtils.dateToString(date2));
  }

  @Override
  public JQLPredicate on(String date) {
    return addPredicate(" ON " + date);
  }

  @Override
  public JQLPredicate on(Date aDate) {
    return on(StringUtils.dateToString(aDate));
  }

  @Override
  public JQLPredicate from(Object value) {
    return addPredicate(" FROM " + StringUtils.stringify(value));
  }

  @Override
  public JQLPredicate to(Object value) {
    return addPredicate(" TO " + StringUtils.stringify(value));
  }

  @Override
  public JQLField and() {
    return addPredicateToField(" AND ");
  }

  @Override
  public JQLField or() {
    return addPredicateToField(" OR ");
  }

  @Override
  public JQLOrder orderBy(String... fieldNames) {
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
