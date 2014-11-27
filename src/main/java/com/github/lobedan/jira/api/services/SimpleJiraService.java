package com.github.lobedan.jira.api.services;

import java.net.URI;

import com.github.lobedan.jira.api.builder.JiraUrlBuilder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * implements methods from {@link com.github.lobedan.jira.api.services.JiraService} its the simplest implementation
 * to work with jira rest api
 * its also available in spring context due to its {@link org.springframework.stereotype.Service} annotation
 *
 * for more
 * @see com.github.lobedan.jira.api.services.JiraService
 *
 * @author svenklemmer
 * @since jira-api-wrapper 0.1.0
 */
@Service
public class SimpleJiraService implements JiraService, HttpRestTemplateAware {
  private static final Logger LOGGER = LogManager.getLogger(SimpleJiraService.class);

  private JiraUrlBuilder baseUrlBuilder;
  private URI baseUrl;
  private HttpRestTemplate httpRestTemplate;

  @Override
  public void searchJira() {

  }

  @Override
  public void createProject() {

  }

  @Override
  public void changeProject() {

  }

  @Override
  public void deleteProject() {

  }

  @Autowired
  @Qualifier(value = "jiraBaseUrl")
  @Override
  public void setBaseUrl(JiraUrlBuilder aUrlBuilder) {
    this.baseUrlBuilder = aUrlBuilder;
    this.baseUrl = aUrlBuilder.build();
  }

  @Autowired
  @Qualifier(value = "defaultHttpRestTemplate")
  @Override
  public void setHttpRestTemplate(HttpRestTemplate aHttpRestTemplate) {
    this.httpRestTemplate = aHttpRestTemplate;
  }
}
