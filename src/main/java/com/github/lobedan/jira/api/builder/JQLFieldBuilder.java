package com.github.lobedan.jira.api.builder;

import com.github.lobedan.jira.api.domain.builder.JQLMetaHolder;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * @author svenklemmer
 * @since jira-api-wrapper 0.1.0
 */
public class JQLFieldBuilder {
  private static final Logger LOGGER = LogManager.getLogger(JQLFieldBuilder.class);

  private JiraUrlBuilder urlBuilder;

  public JQLFieldBuilder(JiraUrlBuilder jiraUrl) {
    urlBuilder = jiraUrl;
  }

  public JQLOpsBuilder reporter() {
    return add("reporter");
  }

  public JQLOpsBuilder assignee() {
    return add("assignee");
  }

  public JQLOpsBuilder created() {
    return add("created");
  }

  public JQLOpsBuilder project() {
    return add("project");
  }

  public JQLOpsBuilder status() {
    return add("status");
  }

  public JQLOpsBuilder updated() {
    return add("updated");
  }



  private JQLOpsBuilder add(String toAdd) {
    JQLMetaHolder.getInstance().jql().add(toAdd);
    return new JQLOpsBuilder(urlBuilder);
  }
}
