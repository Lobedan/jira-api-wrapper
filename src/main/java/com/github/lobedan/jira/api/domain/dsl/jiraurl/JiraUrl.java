package com.github.lobedan.jira.api.domain.dsl.jiraurl;

import com.github.lobedan.jira.api.domain.dsl.jql.JQL;
import com.github.lobedan.jira.api.dsl.jiraurl.JiraUrlBuilder;
import com.github.lobedan.jira.api.types.ExpandType;
import com.github.lobedan.jira.api.types.FieldType;
import com.github.lobedan.jira.api.types.SchemeType;

/**
 * Created by zambezi on 01.12.14.
 */
public interface JiraUrl {

    /**
     * does your jira instance run on http, https or something else
     *
     * @see com.github.lobedan.jira.api.types.SchemeType
     */
    JiraUrlBuilder scheme(SchemeType type);

    /**
     * where is your jira instance hostet
     */
    JiraUrlBuilder host(String host);

    /**
     * on which port is your jira instance running
     * for port 80 you can leave it out
     *
     * @param port
     * @return
     */
    JiraUrlBuilder port(int port);

    /**
     * @param port
     * @return
     * @see #port(int)
     */
    JiraUrlBuilder port(String port);

    /**
     * path to your jira instance
     */
    JiraUrlBuilder path(String path);

    /**
     * which api version whould you like to use
     * advanced setting for latest apiVersion just tipe in "latest"
     *
     * @param version
     * @return
     */
    JiraUrlBuilder apiVersion(String version);

    /**
     * @param version
     * @return
     * @see #apiVersion(String)
     */
    JiraUrlBuilder apiVersion(int version);

    /**
     * jql query to perfom a better search
     * you can use JQL-DSL to construct a query for more information
     *
     * @param jql
     * @return
     * @see com.github.lobedan.jira.api.dsl.jql.JQLBuilder
     */
    JiraUrlBuilder jqlQuery(JQL jql);

    /**
     * +
     * to return jql as string use
     *
     * @param jql
     * @return
     * @see com.github.lobedan.jira.api.dsl.jql.JQLKeywordBuilder#build()  or
     * @see com.github.lobedan.jira.api.dsl.jql.JQLPredicateBuilder#build()
     * @see #jqlQuery(String)
     */
    JiraUrlBuilder jqlQuery(String jql);

    JiraUrlBuilder startAt(int startAt);

    JiraUrlBuilder maxResults(int maxResults);

    JiraUrlBuilder total(int total);

    JiraUrlBuilder fields(FieldType... fields);

    JiraUrlBuilder expand(ExpandType... expandTypes);


}
