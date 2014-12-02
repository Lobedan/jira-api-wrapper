package com.github.lobedan.jira.api.services;

/**
 * makes sure that a service has {@link HttpRestTemplateAware} implementation,
 * which is nothing more than a {@link org.springframework.web.client.RestTemplate} which basic auth
 * methods
 *
 * @author svenklemmer
 * @since jira-api-wrapper 0.1.0
 */
public interface HttpRestTemplateAware {
  /**
   * set the httpRestTemplate to use
   * if you dont use spring boots autoconfigure mechanism
   * then
   * set it as new HttpRestTemplate(new UsernamePasswordCredentials(String, String))
   *
   * @param aHttpRestTemplate
   */
  void setHttpRestTemplate(HttpRestTemplate aHttpRestTemplate);
}
