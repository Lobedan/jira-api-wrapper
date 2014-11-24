package com.github.lobedan.jira.api.builder;

import java.net.URI;

import com.github.lobedan.jira.api.domain.builder.JiraUrl;
import com.github.lobedan.jira.api.types.SchemeType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author svenklemmer
 * @since jira-api-wrapper 0.1.0
 */
public class JiraUrlBuilder implements Builder<URI>{
  private static final Logger LOGGER = LogManager.getLogger(JiraUrlBuilder.class);

  private JiraUrl url;

  private JiraUrlBuilder() {
    url = new JiraUrl();
  }

  public static JiraUrlBuilder JiraUrl() {
    return new JiraUrlBuilder();
  }

  public JiraUrlBuilder scheme(SchemeType type) {
    url.setScheme(type.getName());
    return this;
  }

  public JiraUrlBuilder host(String host) {
    url.setHost(host);
    return this;
  }

  public JiraUrlBuilder port(int port) {
    url.setPort(port);
    return this;
  }

  public JiraUrlBuilder path(String path) {
    url.setPath(path);
    return this;
  }

  public JiraUrlBuilder api(int version) {
    url.setApi(version);
    return this;
  }

  public JiraUrlBuilder api(String version) {
    url.setApi(version);
    return this;
  }

  public JQLBuilder jql() {
    return new JQLBuilder(this);
  }




  @Override
  public URI build() {
    return url.toUri();
  }

  @Override
  public URI buildAndClear() {
   JiraUrl tmp = url;
    clear();
    return tmp.toUri();
  }

  @Override
  public void clear() {
    url = new JiraUrl();
  }
}
