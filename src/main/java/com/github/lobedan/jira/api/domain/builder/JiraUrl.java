package com.github.lobedan.jira.api.domain.builder;

import java.net.URI;

/**
 * @author svenklemmer
 * @since jira-api-wrapper 0.1.0
 */
public class JiraUrl {
  private String scheme;
  private String host;
  private int port;
  private String path;
  private String api;

  public URI toUri() {
    return null;
  }
}
