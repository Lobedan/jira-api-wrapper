package com.github.lobedan.jira.api.dsl.jiraurl;

import com.github.lobedan.jira.api.domain.dsl.jiraurl.JiraUrl;
import com.github.lobedan.jira.api.domain.dsl.jql.JQL;
import com.github.lobedan.jira.api.types.ExpandType;
import com.github.lobedan.jira.api.types.FieldType;
import com.github.lobedan.jira.api.types.SchemeType;
import com.github.lobedan.jira.api.util.StringUtils;
import gumi.builders.UrlBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URI;

/**
 * @author svenklemmer
 * @since jira-api-wrapper 0.1.0
 */
public final class JiraUrlBuilder implements JiraUrl {
  private static final Logger LOGGER = LogManager.getLogger(JiraUrlBuilder.class);

  private UrlBuilder urlBuilder;
  private int port;
  private String apiVersion;
  private String path;

  private JiraUrlBuilder() {
    urlBuilder = UrlBuilder.empty();
  }

  public static JiraUrlBuilder jiraUrl() {
    return new JiraUrlBuilder();
  }

  @Override
  public JiraUrlBuilder scheme(SchemeType aType) {
    urlBuilder = urlBuilder.withScheme(aType.getName());
    return this;
  }

  @Override
  public JiraUrlBuilder host(String host) {
    urlBuilder = urlBuilder.withHost(host);
    return this;
  }

  @Override
  public JiraUrlBuilder port(int port) {
    urlBuilder = urlBuilder.withPort(port);
    this.port = port;
    return this;
  }

  @Override
  public JiraUrlBuilder port(String port) {
    urlBuilder = urlBuilder.withPort(Integer.parseInt(port));
    this.port = Integer.parseInt(port);
    return this;
  }

  @Override
  public JiraUrlBuilder path(String aPath) {
    urlBuilder = urlBuilder.withPath((urlBuilder.path != null ? urlBuilder.path : "") + aPath);
    this.path = aPath;
    return this;
  }

  @Override
  public JiraUrlBuilder apiVersion(int version) {
    return apiVersion("" + version);
  }

  @Override
  public JiraUrlBuilder apiVersion(String version) {
    urlBuilder = urlBuilder.withPath(urlBuilder.path.replace("null", "") + "/rest/api/" + version.replaceAll("/", ""));
    this.apiVersion = version;
    return this;
  }

  @Override
  public JiraUrlBuilder jqlQuery(String jql) {
    urlBuilder = urlBuilder.addParameter("jql", jql);
    return this;
  }

  @Override
  public JiraUrlBuilder jqlQuery(JQL aJQL) {
    return jqlQuery(aJQL.build());
  }

  @Override
  public JiraUrlBuilder startAt(int startAt) {
    urlBuilder = urlBuilder.addParameter("startat", StringUtils.stringify(startAt));
    return this;
  }

  @Override
  public JiraUrlBuilder maxResults(int results) {
    urlBuilder = urlBuilder.addParameter("maxresults", StringUtils.stringify(results));
    return this;
  }

  @Override
  public JiraUrlBuilder total(int total) {
    urlBuilder = urlBuilder.addParameter("total", StringUtils.stringify(total));
    return this;
  }

  @Override
  public JiraUrlBuilder fields(FieldType... fields) {
    urlBuilder = urlBuilder.addParameter("fields", StringUtils.commaSeparatedList(fields));
    return this;
  }

  @Override
  public JiraUrlBuilder expand(ExpandType... expand) {
    urlBuilder = urlBuilder.addParameter("expand", StringUtils.commaSeparatedList(expand));
    return this;
  }

  public URI build() {
    if (port == 0) {
//      port(80);
    }
    if (path == null || path.isEmpty()) {
      path("");
    }
    if (apiVersion == null) {
      apiVersion("latest");
    }
    LOGGER.debug("Returning uri " + urlBuilder.toUri().toString());
    return urlBuilder.toUri();
  }
}
