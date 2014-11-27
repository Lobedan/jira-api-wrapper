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
  void setHttpRestTemplate(HttpRestTemplate aHttpRestTemplate);
}
