package com.github.lobedan.jira.api.builder;

import com.github.lobedan.jira.api.domain.JiraDate;

/**
 * provides static methods for {@link com.github.lobedan.jira.api.builder.JQLBuilder} because
 * some methods like
 * @see com.github.lobedan.jira.api.builder.JQLBuilder#brackets(com.github.lobedan.jira.api.builder.JQLBuilder builder)
 * need to have a inline of JQLBuilder
 *
 * @author svenklemmer
 * @since 1.0.0
 */
public class JQLStaticBuilder {

  private static JQLBuilder builder = null;

  private static JQLBuilder create() {
    return new JQLBuilder();
  }

  public static JQLBuilder field(String fieldName) {
    if (builder == null) {
      builder = create();
    }
    return builder.field("person");
  }

  public static String nul() {
    return "null";
  }

  public static String empty() {
    return "empty";
  }

  public static String on(String value) {
    return "on " + value;
  }

  public static String on(JiraDate value) {
    return "on " + value.getDateAsString();
  }

  public static String asc() {
    return "asc";
  }

  public static String desc() {
    return "desc";
  }
}
