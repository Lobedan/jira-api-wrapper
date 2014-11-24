package com.github.lobedan.jira.api.builder;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * @author svenklemmer
 * @since jira-api-wrapper 0.1.0
 */
public class JQLFieldBuilder {
  private static final Logger LOGGER = LogManager.getLogger(JQLFieldBuilder.class);

  private JQLBuilder parent;

  public JQLFieldBuilder(JQLBuilder aParent) {
    parent = aParent;
  }

  public JQLOpsBuilder reporter() {
    parent.sb().append("reporter");
    return ops();
  }

  public JQLOpsBuilder assignee() {
    parent.sb().append("assignee");
    return ops();
  }

  public JQLFieldBuilder or() {
    parent.sb().append(" OR ");
    return this;
  }

  public StringBuilder sb() {
    return parent.sb();
  }

  private JQLOpsBuilder ops() {
    return new JQLOpsBuilder(this);
  }

}
