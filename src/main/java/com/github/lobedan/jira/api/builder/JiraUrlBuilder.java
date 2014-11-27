package com.github.lobedan.jira.api.builder;

import java.net.URI;

import com.github.lobedan.jira.api.domain.builder.JQLMetaHolder;
import com.github.lobedan.jira.api.types.SchemeType;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import gumi.builders.UrlBuilder;

/**
 * @author svenklemmer
 * @since jira-api-wrapper 0.1.0
 */
public class JiraUrlBuilder {
  private static final Logger LOGGER = LogManager.getLogger(JiraUrlBuilder.class);

  private UrlBuilder urlBuilder;
  private boolean wasBuild = false;
  private URI latest;

  public static JiraUrlBuilder JiraUrl() {
    return new JiraUrlBuilder();
  }

  private JiraUrlBuilder() {
    urlBuilder = UrlBuilder.empty();
  }

  public JiraUrlBuilder scheme(SchemeType aType) {
    urlBuilder = urlBuilder.withScheme(aType.getName());
    return this;
  }

  public JiraUrlBuilder host(String host) {
    urlBuilder = urlBuilder.withHost(host);
    return this;
  }

  public JiraUrlBuilder port(int port) {
    urlBuilder = urlBuilder.withPort(port);
    return this;
  }

  public JiraUrlBuilder port(String port) {
    urlBuilder = urlBuilder.withPort(Integer.parseInt(port));
    return this;
  }

  public JiraUrlBuilder path(String path) {
    urlBuilder = urlBuilder.withPath(path);
    return this;
  }

  public JiraUrlBuilder apiVersion(int version) {
   return apiVersion(""+version);
  }

  public JiraUrlBuilder apiVersion(String version) {
    urlBuilder = urlBuilder.withPath(urlBuilder.path + "/rest/api/" + version + "/");
    return this;
  }

  public JQLFieldBuilder jql() {
    return new JQLFieldBuilder(this);
  }

  public void addJQL(String jqlString) {
    urlBuilder = urlBuilder.addParameter("jql", jqlString);
  }

  public URI build() {
    if (!wasBuild) {
      latest = urlBuilder.toUri();
      wasBuild = true;
    } else {
      clearAll();
    }
    return latest;
  }

  private void clearAll() {
    urlBuilder = UrlBuilder.empty();
    JQLMetaHolder.getInstance().clear();
    wasBuild = false;
  }
}
