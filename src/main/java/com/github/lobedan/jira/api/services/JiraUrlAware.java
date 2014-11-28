package com.github.lobedan.jira.api.services;

import com.github.lobedan.jira.api.builder.JiraUrlBuilder;

/**
 * makes sure that a {@link com.github.lobedan.jira.api.services.JiraService}
 * has a given jiraurl build with {@link com.github.lobedan.jira.api.builder.JiraUrlBuilder}
 * <p/>
 * when this wrapper is used with spring boot its autoconfiguration mechanism will provide a
 * basic url for jira instance build out of {@link com.github.lobedan.jira.api.autoconfigure.JiraProperties}
 * specified in application.properties
 * <p/>
 * the baseurl should automatically be {@link org.springframework.beans.factory.annotation.Autowired} by spring
 *
 * @author svenklemmer
 * @see com.github.lobedan.jira.api.services.JiraService
 * @see com.github.lobedan.jira.api.autoconfigure.JiraAutoConfiguration
 * @since jira-api-wrapper 0.1.0
 */
public interface JiraUrlAware {
  void setBaseUrlBuilder(JiraUrlBuilder aUrlBuilder);
  JiraUrlBuilder getBaseUrlBuilder();
}
