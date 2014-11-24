package com.github.lobedan.jira.api.builder;

import com.github.lobedan.jira.api.util.StringUtils;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * @author svenklemmer
 * @since jira-api-wrapper 0.1.0
 */
public class JQLOpsBuilder {
  private static final Logger LOGGER = LogManager.getLogger(JQLOpsBuilder.class);

  private JQLFieldBuilder parent;

  public JQLOpsBuilder(JQLFieldBuilder aParent) {
    parent = aParent;
  }

  public JQLFieldBuilder is(Object value) {
    parent.sb().append(" = " + StringUtils.stringify(value));
    return parent;
  }
}
