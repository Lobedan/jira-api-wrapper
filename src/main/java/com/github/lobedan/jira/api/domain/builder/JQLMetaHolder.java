package com.github.lobedan.jira.api.domain.builder;

/**
 * @author svenklemmer
 * @since jira-api-wrapper 0.1.0
 */
public class JQLMetaHolder {
  private static JQLMetaHolder ourInstance;
  private JQL jql;

  private JQLMetaHolder() {
    clear();
  }

  public static JQLMetaHolder getInstance() {
    if (ourInstance == null) {
      ourInstance = new JQLMetaHolder();
    }
    return ourInstance;
  }

  public JQL jql() {
    return jql;
  }

  public void clear() {
    jql = new JQL();
  }
}
