package com.github.lobedan.jira.api.services;

import com.github.lobedan.jira.api.builder.JiraUrlBuilder;

/**
 * makes sure that a {@link com.github.lobedan.jira.api.services.JiraService}
 * has a given jiraurl build with {@link com.github.lobedan.jira.api.builder.CustomJiraUrlBuilder}
 *
 * when this wrapper is used with spring boot its autoconfiguration mechanism will provide a
 * basic url for jira instance build out of {@link com.github.lobedan.jira.api.autoconfigure.JiraProperties}
 * specified in application.properties
 *
 * when use with {@link com.github.lobedan.jira.api.services.SimpleJiraService} the baseurl is automatically
 * {@link org.springframework.beans.factory.annotation.Autowired}
 *
 * @see com.github.lobedan.jira.api.services.JiraService
 * @see com.github.lobedan.jira.api.autoconfigure.JiraAutoConfiguration
 *
 * @author svenklemmer
 * @since jira-api-wrapper 0.1.0
 */
public interface JiraUrlAware {
  void setBaseUrl(JiraUrlBuilder aUrlBuilder);
}
