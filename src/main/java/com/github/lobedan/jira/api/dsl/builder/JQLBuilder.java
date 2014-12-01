package com.github.lobedan.jira.api.dsl.builder;

import com.github.lobedan.jira.api.domain.JQL;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author svenklemmer
 * @since jira-api-wrapper 0.1.0
 */
public class JQLBuilder {
  private static final Logger LOGGER = LogManager.getLogger(JQLBuilder.class);

  private static JQL jql;

  private JQLBuilder() {

  }

  public static JQLFieldBuilder jql() {
    jql = new JQL();
    return new JQLFieldBuilder(jql);
  }
}
