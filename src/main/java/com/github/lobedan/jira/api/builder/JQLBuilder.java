package com.github.lobedan.jira.api.builder;

import java.util.List;

import com.github.lobedan.jira.api.ParameterCountException;
import com.github.lobedan.jira.api.domain.JiraDate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * can build jql query for use with jira rest api
 * its capable to use standalone or in combinaten with {@link JiraUrlBuilder}
 * <p/>
 * NOTE: this builder only builds a jql query but not verifies that it can be used at all jira rest routes
 * so be careful when using it
 * normally jira will return a json which will tell wether it was correct or not
 * <p/>
 * a jql query contains of 3 elements
 * 1. a field name which is any field available
 * 2. a operation such like !=, IS, IS NOT, >, >=, <, <=
 * 3. a value that the field should have and you are searching for
 * <p/>
 * so its really easy to make queries
 * just call a new instance of JQLBuilder and use the following methods
 * <p/>
 * the field method is simply the name of the field
 *
 * @author svenklemmer
 * @see #field(String name)
 * <p/>
 * operation and values are combined in one method so you are sure where to use more than one parameter
 * each possible operation is available as a method, the combination of operation and value is more readable and
 * makes it more intuitive to build a jql query
 * @see #equal(Object value)
 * @see #notEqual(Object value)
 * @see #greaterThan(Object value)
 * @see #greaterThanEquals(Object value)
 * @see #lessThan(Object value)
 * @see #lessThanEquals(Object value)
 * @see #in(List values)
 * @see #in(Object... values)
 * @see #notIn(List values)
 * @see #notIn(Object... values)
 * @see #contains(List values)
 * @see #contains(Object... values)
 * @see #doesNotContain(List values)
 * @see #doesNotContain(Object...values)
 * @see #is(Object value)
 * @see #isNot(Object value)
 * @see #was(Object value)
 * @see #wasNot(Object value)
 * @see #wasIn(List values)
 * @see #wasIn(Object... values)
 * @see #wasNotIn(List values)
 * @see #wasNotIn(Object... values)
 * @see #changed()
 * <p/>
 * there are also some special values available
 * @see com.github.lobedan.jira.api.builder.JQLStaticBuilder#empty()
 * @see com.github.lobedan.jira.api.builder.JQLStaticBuilder#nul() - one l because in java you cant use null as a method name
 * <p/>
 * usage: field("person").isNot(empty())
 * would ouput: person is not empty
 * <p/>
 * <p/>
 * to make a chain of assignments like person == "ralf" AND date="today()"
 * use the given and() and or() methods, they will make the chain
 * @see #or()
 * @see #and()
 * <p/>
 * <p/>
 * to enable brackets use sep() or brackets() method
 * @see #sep(com.github.lobedan.jira.api.builder.JQLBuilder builder)
 * @see #brackets(com.github.lobedan.jira.api.builder.JQLBuilder builder)
 * <p/>
 * usage brackets(
 * field("person").is("ralf").or().field("person").is("markus")
 * )
 * .and(
 * field("created").was("2010-01-01")
 * )
 * <p/>
 * would output: (person is ralf OR person is markus) AND created WAS 2010-01-01
 * <p/>
 * <p/>
 * special methods are also which works as values and operations in one method
 * @see #after(com.github.lobedan.jira.api.domain.JiraDate date)
 * @see #before(com.github.lobedan.jira.api.domain.JiraDate date)
 * @see #by(String username)
 * @see #during(String value1, String value2)
 * @see #during(com.github.lobedan.jira.api.domain.JiraDate date1, com.github.lobedan.jira.api.domain.JiraDate date2)
 * @see com.github.lobedan.jira.api.builder.JQLStaticBuilder#on(com.github.lobedan.jira.api.domain.JiraDate date)
 * @see com.github.lobedan.jira.api.builder.JQLStaticBuilder#on(String value)
 * @see #from(Object value)
 * @see #to(Object value)
 * @see #on(com.github.lobedan.jira.api.domain.JiraDate date)
 * <p/>
 * <p/>
 * orderBy is a endpoint in the query it will just sort the results after the
 * {@link com.github.lobedan.jira.api.enumeration.OrderType} you have set
 * <p/>
 * but to make it more readable like operations you can use the desc() for highest to lowest or asc() for
 * lowest to highest sorting
 * @see #orderBy()
 * @see #asc() : OrderType.ASC
 * @see #desc(): OrderType.DESC
 * <p/>
 * for more information about how to combine the {@link JiraUrlBuilder} with JQLBuilder
 * refer to the {@link JiraUrlBuilder class}
 * @see JiraUrlBuilder
 * <p/>
 * following methods will return the last query as string or url object
 * @see #build() : {@link java.lang.String}
 * @see #buildAndClear() : {@link java.lang.String} - will return that string and clean builder at the same time so you can use one instance again
 * <p/>
 * complete list of methods:
 * <p/>
 * <p/>
 * TODO: maybe provide different field methods but that will not support customfields but can be more readable
 * @since 0.1.0
 */
public class JQLBuilder {
  private static final Logger LOGGER = LogManager.getLogger(JQLBuilder.class);

  private StringBuilder sb;

  public JQLBuilder() {
    sb = new StringBuilder();
  }

  public JQLBuilder field(String fieldName) {
    add(fieldName);
    return this;
  }

  public JQLBuilder value(Object... values) {
    for (Object o : values) {
      value(o);
    }
    return this;
  }

  public JQLBuilder sep(JQLBuilder aBuilder) {
    return brackets(aBuilder.build());
  }

  public JQLBuilder brackets(JQLBuilder aBuilder) {
    return brackets(aBuilder.build());
  }

  public JQLBuilder sep(String values) {
    return brackets(values);
  }

  public JQLBuilder brackets(String values) {
    add("(" + values + ")");
    return this;
  }

  public JQLBuilder and() {
    add(" AND ");
    return this;
  }

  public JQLBuilder or() {
    add(" OR ");
    return this;
  }

  public JQLBuilder equal(Object value) {
    add(" = " + stringify(value));
    return this;
  }

  public JQLBuilder notEqual(Object value) {
    add(" != " + stringify(value));
    return this;
  }

  public JQLBuilder greaterThan(Object value) {
    add(" > " + stringify(value));
    return this;
  }

  public JQLBuilder greaterThanEquals(Object value) {
    add(" >= " + stringify(value));
    return this;
  }

  public JQLBuilder lessThan(Object value) {
    add(" < " + stringify(value));
    return this;
  }

  public JQLBuilder lessThanEquals(Object value) {
    add(" <= " + stringify(value));
    return this;
  }

  public JQLBuilder in(List<Object> values) {
    return in(values.toArray(new Object[values.size()]));
  }

  public JQLBuilder in(Object... values) {
    verifyParams("IN", values);
    add(" in ");
    return brackets(commaSeparatedList(values));
  }

  public JQLBuilder notIn(List<Object> values) {
    return notIn(values.toArray(new Object[values.size()]));
  }

  public JQLBuilder notIn(Object... values) {
    verifyParams("NOT IN", values);
    add(" not in ");
    return brackets(commaSeparatedList(values));
  }

  public JQLBuilder contains(List<Object> values) {
    return contains(values.toArray(new Object[values.size()]));
  }

  public JQLBuilder contains(Object... values) {
    verifyParams("CONTAINS", values);
    add(" ~ ");
    return quote(wsSeparatedList(values));
  }

  public JQLBuilder doesNotContain(List<Object> values) {
    return doesNotContain(values.toArray(new Object[values.size()]));
  }

  public JQLBuilder doesNotContain(Object... values) {
    verifyParams("NOT CONTAINS", values);
    add(" !~ ");
    return quote(wsSeparatedList(values));
  }

  public JQLBuilder is(Object value) {
    add(" is " + stringify(value));
    return this;
  }

  public JQLBuilder isNot(Object value) {
    add(" is not " + stringify(value));
    return this;
  }

  public JQLBuilder was(Object value) {
    add(" was " + stringify(value));
    return this;
  }

  public JQLBuilder wasNot(Object value) {
    add(" WAS NOT " + stringify(value));
    return this;
  }

  public JQLBuilder wasIn(List<Object> values) {
    return wasIn(values.toArray(new Object[values.size()]));
  }

  public JQLBuilder wasIn(Object... values) {
    verifyParams("WAS IN", values);
    add(" WAS IN ");
    return brackets(commaSeparatedList(values));
  }

  public JQLBuilder wasNotIn(List<Object> values) {
    return wasNotIn(values.toArray(new Object[values.size()]));
  }

  public JQLBuilder wasNotIn(Object... values) {
    verifyParams("WAS NOT IN", values);
    add(" WAS NOT IN ");
    return brackets(commaSeparatedList(values));
  }

  public JQLBuilder changed() {
    add(" CHANGED");
    return this;
  }

  public JQLBuilder after(JiraDate date) {
    return after(date.getDateAsString());
  }

  public JQLBuilder after(String value) {
    add(" AFTER " + value);
    return this;
  }

  public JQLBuilder before(JiraDate date) {
    return before(date.getDateAsString());
  }

  public JQLBuilder before(String value) {
    add(" BEFORE " + value);
    return this;
  }

  public JQLBuilder by(String value) {
    add(" BY " + value);
    return this;
  }

  public JQLBuilder from(Object value) {
    add(" FROM " + stringify(value));
    return this;
  }

  public JQLBuilder to(Object value) {
    add(" TO " + stringify(value));
    return this;
  }

  public JQLBuilder during(JiraDate date1, JiraDate date2) {
    return during(date1.getDateAsString(), date2.getDateAsString());
  }

  public JQLBuilder during(String value1, String value2) {
    add(" DURING (" + value1 + ", " + value2 + ")");
    return this;
  }

  public JQLBuilder orderBy(List<Object> values, String orderType) {
    return orderBy(orderType, values.toArray(new Object[values.size()]));
  }

  public JQLBuilder orderBy(String orderType, Object... values) {
    verifyParams("ORDER BY", values);
    add(" order by " + commaSeparatedList(values) + " " + orderType);
    return this;
  }

  public JQLBuilder orderBy(String fieldName, String orderType) {
    add(" order by " + fieldName + " " + orderType);
    return this;
  }

  private void add(String value) {
    sb.append(value);
  }

  private JQLBuilder quote(String value) {
    add("\"" + value + "\"");
    return this;
  }

  private String stringify(Object value) {
    if (value instanceof Boolean) {
      boolean b = (Boolean) value;
      return (b) ? "true" : "false";
    } else {
      if (value instanceof Integer) {
        return "" + (Integer) value;
      } else if (value instanceof Double) {
        return "" + (Double) value;
      } else if (value instanceof Long) {
        return "" + (Long) value;
      } else if (value instanceof Float) {
        return "" + (Float) value;
      } else {
        return (String) value;
      }
    }
  }

  private String commaSeparatedList(Object... values) {
    String toAdd = "";
    for (int i = 0; i < values.length; i++) {
      if (i == values.length - 1) {
        toAdd += stringify(values[i]);
      } else {
        toAdd += stringify(values[i]) + ",";
      }
    }
    return toAdd;
  }

  private String wsSeparatedList(Object... values) {
    String toAdd = "";
    for (int i = 0; i < values.length; i++) {
      if (i == values.length - 1) {
        toAdd += stringify(values[i]);
      } else {
        toAdd += stringify(values[i]) + " ";
      }
    }
    return toAdd;
  }

  private void verifyParams(String keyword, Object... params) {
    if (params.length < 2) {
      throw new ParameterCountException("Keyword " + keyword + " needs at least 2 arguments");
    }
  }

  private void verifyParams(String keyword, List<Object> params) {
    if (params.size() < 2) {
      throw new ParameterCountException("Keyword " + keyword + " needs at least 2 arguments");
    }
  }

  public String build() {
    return sb.toString();
  }

  public String buildAndClear() {
    String tmp = sb.toString();
    sb = null;
    sb = new StringBuilder();
    return tmp;
  }
}
