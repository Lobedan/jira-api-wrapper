package com.github.lobedan.jira.api.builder;

import java.net.URI;

import com.github.lobedan.jira.api.types.ProtocolType;
import com.github.lobedan.jira.api.util.StringUtils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import gumi.builders.UrlBuilder;

/**
 * @author svenklemmer
 * @since 0.1.0
 */
public class JiraUrlBuilder implements Builder<URI> {
  private static final Logger LOGGER = LogManager.getLogger(JiraUrlBuilder.class);

  private UrlBuilder builder;

  public JiraUrlBuilder() {
    builder = UrlBuilder.empty();
  }

  public JiraUrlBuilder protocol(String scheme) {
    builder = builder.withScheme(scheme);
    return this;
  }

  public JiraUrlBuilder protocol(ProtocolType scheme) {
    builder = builder.withScheme(scheme.toString());
    return this;
  }

  public JiraUrlBuilder host(String host) {
    builder = builder.withHost(host);
    return this;
  }

  public JiraUrlBuilder port(String port) {
    builder =  builder.withPort(Integer.parseInt(port));
    return this;
  }

  public JiraUrlBuilder port(int port) {
    builder =  builder.withPort(port);
    return this;
  }

  public JiraUrlBuilder apiPath(String path) {
    builder = builder.withPath(path);
    return this;
  }

  public JiraUrlBuilder jqlQuery(JQLBuilder jql) {
    builder.addParameter("jql", jql.build());
    return this;
  }

  public JiraUrlBuilder expand(String... values) {
    builder = builder.addParameter("expand", StringUtils.commaSeparatedList(values));
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
