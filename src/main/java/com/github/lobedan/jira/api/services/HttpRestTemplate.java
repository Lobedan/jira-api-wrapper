package com.github.lobedan.jira.api.services;

import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.client.RestTemplate;

/**
 * implementation of {@link org.springframework.web.client.RestTemplate} which special basic auth
 * methods for jira
 * credentials are also provided by {@link com.github.lobedan.jira.api.autoconfigure.JiraAutoConfiguration} when
 * using spring boot
 *
 * @author svenklemmer
 * @since jira-api-wrapper 0.1.0
 */
public class HttpRestTemplate extends RestTemplate {
  private static final Logger LOGGER = LogManager.getLogger(HttpRestTemplate.class);

  private UsernamePasswordCredentials credentials;

  public HttpRestTemplate() { super(); }
  public HttpRestTemplate(UsernamePasswordCredentials aCredentials) {
    setCredentials(aCredentials);
  }

  public Credentials getCredentials() {
    return credentials;
  }

  public void setCredentials(UsernamePasswordCredentials aCredentials) {
    credentials = aCredentials;
  }
}
