package com.github.lobedan.jira.api.builder;

import com.github.lobedan.jira.api.domain.builder.JQLMetaHolder;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * @author svenklemmer
 * @since jira-api-wrapper 0.1.0
 */
public class JQLBuilder {
  private static final Logger LOGGER = LogManager.getLogger(JQLBuilder.class);

  private JiraUrlBuilder parent;

  public JQLBuilder(JiraUrlBuilder p) {
    parent = p;
  }

  public JQLFieldBuilder or() {
    JQLMetaHolder.getInstance().jql().add(" OR ");
    return new JQLFieldBuilder(parent);
  }

  public JQLFieldBuilder and() {
    JQLMetaHolder.getInstance().jql().add(" AND ");
    return new JQLFieldBuilder(parent);
  }

  public JQLOpsBuilder by(String username) {
    JQLMetaHolder.getInstance().jql().add(" BY " + username);
    return new JQLOpsBuilder(parent);
  }

  public JiraUrlBuilder end() {
    parent.addJQL(JQLMetaHolder.getInstance().jql().sb().toString());
    return parent;
  }
}
