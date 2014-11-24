package com.github.lobedan.jira.api.builder;

import java.net.URI;

import com.github.lobedan.jira.api.types.ExpandTypes;
import com.github.lobedan.jira.api.types.ProtocolType;
import com.github.lobedan.jira.api.util.StringUtils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import gumi.builders.UrlBuilder;

/**
 * builds a url to access your jira instance
 *
 * @author svenklemmer
 * @since 0.1.0
 */
public class CustomJiraUrlBuilder implements Builder<URI> {
  private static final Logger LOGGER = LogManager.getLogger(CustomJiraUrlBuilder.class);

  private UrlBuilder builder;

  public CustomJiraUrlBuilder() {
    builder = UrlBuilder.empty();
  }

  public CustomJiraUrlBuilder protocol(String scheme) {
    builder = builder.withScheme(scheme);
    return this;
  }

  public CustomJiraUrlBuilder protocol(ProtocolType scheme) {
    builder = builder.withScheme(scheme.toString());
    return this;
  }

  public CustomJiraUrlBuilder host(String host) {
    builder = builder.withHost(host);
    return this;
  }

  public CustomJiraUrlBuilder port(String port) {
    builder =  builder.withPort(Integer.parseInt(port));
    return this;
  }

  public CustomJiraUrlBuilder port(int port) {
    builder =  builder.withPort(port);
    return this;
  }

  public CustomJiraUrlBuilder apiPath(String path) {
    builder = builder.withPath(path);
    return this;
  }

  public CustomJiraUrlBuilder jql(JQLBuilder jql) {
    builder = builder.addParameter("jql", jql.buildAndClear());
    return this;
  }

  public CustomJiraUrlBuilder expand(String... values) {
    builder = builder.addParameter("expand", StringUtils.commaSeparatedList(values));
    return this;
  }

  public CustomJiraUrlBuilder expand(ExpandTypes... values) {
    builder = builder.addParameter("expand", StringUtils.commaSeparatedList(values));
    return this;
  }

  public CustomJiraUrlBuilder startAt(int startAt) {
    builder = builder.addParameter("startAt", StringUtils.stringify(startAt));
    return this;
  }

  public CustomJiraUrlBuilder maxResults(int maxResults) {
    builder = builder.addParameter("maxResults", StringUtils.stringify(maxResults));
    return this;
  }

  public CustomJiraUrlBuilder total(int total) {
    builder = builder.addParameter("total", StringUtils.stringify(total));
    return this;
  }

  @Override
  public URI build() {
    return builder.toUri();
  }

  @Override
  public URI buildAndClear() {
    URI uri = build();
    clear();
    return uri;
  }

  @Override
  public void clear() {
    builder = UrlBuilder.empty();
  }
}
