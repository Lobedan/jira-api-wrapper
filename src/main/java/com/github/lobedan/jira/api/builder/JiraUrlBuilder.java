package com.github.lobedan.jira.api.builder;

import java.net.URI;

import com.github.lobedan.jira.api.domain.JQL;
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
public final class JiraUrlBuilder {
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
        urlBuilder = urlBuilder.withPath((path.isEmpty() || path.contains("/")) ? path : "/" + path);
        return this;
    }

    public JiraUrlBuilder apiVersion(int version) {
        return apiVersion("" + version);
    }

    public JiraUrlBuilder apiVersion(String version) {
        urlBuilder = urlBuilder.withPath(urlBuilder.path + "/rest/api/" + version + "/");
        return this;
    }

    public JiraUrlBuilder jqlQuery(String jql) {
        urlBuilder = urlBuilder.addParameter("jql", jql);
        return this;
    }

    public JiraUrlBuilder jqlQuery(JQL aJQL) {
        return jqlQuery(aJQL.build());
    }

    public JiraUrlBuilder startAt(int startAt) {
        urlBuilder = urlBuilder.addParameter("startat", StringUtils.stringify(startAt));
        return this;
    }

    public JiraUrlBuilder maxResults(int results) {
        urlBuilder = urlBuilder.addParameter("maxresults", StringUtils.stringify(results));
        return this;
    }

    public JiraUrlBuilder total(int total) {
        urlBuilder = urlBuilder.addParameter("total", StringUtils.stringify(total));
        return this;
    }

    public JiraUrlBuilder fields(FieldType... fields) {
        urlBuilder = urlBuilder.addParameter("fields", StringUtils.commaSeparatedList(fields));
        return this;
    }

    public JiraUrlBuilder expand(ExpandType... expand) {
        urlBuilder = urlBuilder.addParameter("expand", StringUtils.commaSeparatedList(expand));
        return this;
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
        wasBuild = false;
    }
}
