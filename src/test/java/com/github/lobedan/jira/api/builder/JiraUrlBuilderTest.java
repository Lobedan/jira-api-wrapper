package com.github.lobedan.jira.api.builder;

import com.github.lobedan.jira.api.types.ExpandType;
import com.github.lobedan.jira.api.types.FieldType;
import com.github.lobedan.jira.api.types.SchemeType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static com.github.lobedan.jira.api.builder.JiraUrlBuilder.jiraUrl;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author svenklemmer
 * @since jira-api-wrapper 0.1.0
 */
@RunWith(JUnit4.class)
public class JiraUrlBuilderTest {
  private static final Logger LOGGER = LogManager.getLogger(JiraUrlBuilderTest.class);

  @Test
  public void testCanConstructJiraUrlDSL() throws Exception {
    assertThat(
        jiraUrl()
            .scheme(SchemeType.HTTP)
            .host("example.com")
            .port(8080)
            .path("/jira")
            .apiVersion(2)
            .build()
            .toString(), is("http://example.com:8080/jira/rest/api/2/"));

    assertThat(
        jiraUrl()
            .scheme(SchemeType.HTTPS)
            .host("example.com")
            .path("")
            .apiVersion("latest")
            .build()
            .toString(), is("https://example.com/rest/api/latest/"));

    assertThat(
        jiraUrl()
            .scheme(SchemeType.HTTP)
            .host("example.com")
            .path("")
            .apiVersion("latest")
            .startAt(0)
            .maxResults(50)
            .total(100)
            .build()
            .toString(), is("http://example.com/rest/api/latest/?startat=0&maxresults=50&total=100"));

    assertThat(
        jiraUrl()
            .scheme(SchemeType.HTTPS)
            .host("example.com")
            .path("jira")
            .apiVersion("latest")
            .fields(FieldType.ASSIGNEE, FieldType.COMMENT)
            .expand(ExpandType.CHANGELOG)
            .build()
            .toString(), is("https://example.com/jira/rest/api/latest/?fields=assignee%2Ccomment&expand=changelog"));
  }

  @Test
  public void testCanConstructJiraUrlWithJQLDSL() throws Exception {
    assertThat(
        jiraUrl()
            .scheme(SchemeType.HTTP)
            .host("example.com")
            .port(8080)
            .path("/jira")
            .apiVersion("latest")
            .jql()
            .reporter().equal("Markus")
            .or()
            .assignee().equal("Sven")
            .and()
            .created().changed().after("2012-02-13")
            .end()
            .build()
            .toString(),
        is("http://example.com:8080/jira/rest/api/latest/?jql=reporter%20%3D%20Markus%20OR%20assignee%20%3D%20"
           + "Sven%20AND%20created%20CHANGED%20%20AFTER%202012-02-13"));
  }
}
