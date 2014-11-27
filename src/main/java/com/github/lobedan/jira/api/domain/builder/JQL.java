package com.github.lobedan.jira.api.domain.builder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author svenklemmer
 * @since jira-api-wrapper 0.1.0
 */
public class JQL {
  private static final Logger LOGGER = LogManager.getLogger(JQL.class);

  private StringBuilder sb;

  public JQL() {
    clear();
  }

  public StringBuilder sb() {
    return sb;
  }

  public void add(String string) {
    LOGGER.info("added string \"" + string + "\" to stringbuilder");
    sb.append(string);
  }

  public void clear() {
    sb = new StringBuilder();
  }
}