package com.github.lobedan.jira.api.dsl.jiraurl;

import java.net.URI;

import com.github.lobedan.jira.api.domain.dsl.jiraurl.JiraUrl;
import com.github.lobedan.jira.api.domain.dsl.jql.JQL;
import com.github.lobedan.jira.api.types.ExpandType;
import com.github.lobedan.jira.api.types.FieldType;
import com.github.lobedan.jira.api.types.SchemeType;
import com.github.lobedan.jira.api.util.StringUtils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import gumi.builders.UrlBuilder;

/**
 * @author svenklemmer
 * @since jira-api-wrapper 0.1.0
 */
public final class JiraUrlBuilder implements JiraUrl {
    private static final Logger LOGGER = LogManager.getLogger(JiraUrlBuilder.class);

    private UrlBuilder urlBuilder;
    private boolean wasBuild = false;
    private URI latest;

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
        return this;
    }

    @Override
    public JiraUrlBuilder port(String port) {
        urlBuilder = urlBuilder.withPort(Integer.parseInt(port));
        return this;
    }

    @Override
    public JiraUrlBuilder path(String path) {
        urlBuilder = urlBuilder.withPath((path.isEmpty() || path.contains("/")) ? path : "/" + path);
        return this;
    }

    @Override
    public JiraUrlBuilder apiVersion(int version) {
        return apiVersion("" + version);
    }

    @Override
    public JiraUrlBuilder apiVersion(String version) {
        urlBuilder = urlBuilder.withPath(urlBuilder.path + "/rest/api/" + version + "/");
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
        return urlBuilder.toUri();
    }
}
