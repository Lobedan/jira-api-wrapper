package com.github.lobedan.jira.api.dsl.jiraurl;

import com.github.lobedan.jira.api.types.ExpandType;
import com.github.lobedan.jira.api.types.FieldType;
import com.github.lobedan.jira.api.types.SchemeType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static com.github.lobedan.jira.api.dsl.jiraurl.JiraUrlBuilder.jiraUrl;
import static com.github.lobedan.jira.api.dsl.jql.JQLBuilder.jql;
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
                .toString(), is("http://example.com:8080/jira/rest/api/2"));

    assertThat(
        jiraUrl()
            .scheme(SchemeType.HTTPS)
            .host("example.com")
            .build()
                .toString(), is("https://example.com/rest/api/latest"));

    assertThat(
        jiraUrl()
            .scheme(SchemeType.HTTP)
            .host("example.com")
            .startAt(0)
            .maxResults(50)
            .total(100)
            .build()
                .toString(), is("http://example.com/rest/api/latest?startat=0&maxresults=50&total=100"));

    assertThat(
        jiraUrl()
            .scheme(SchemeType.HTTPS)
            .host("example.com")
                .path("/jira")
            .apiVersion(2)
            .fields(FieldType.ASSIGNEE, FieldType.COMMENT)
            .expand(ExpandType.CHANGELOG)
            .build()
                .toString(), is("https://example.com/jira/rest/api/2?fields=assignee%2Ccomment&expand=changelog"));
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
            .jqlQuery(
                jql()
                    .reporter().equal("Markus")
                    .or()
                    .assignee().equal("Sven")
                    .and()
                    .created().changed().after("2012-02-13")
                    .andReturn()
            )
            .build()
            .toString(),
            is("http://example.com:8080/jira/rest/api/latest?jql=reporter%20%3D%20Markus%20OR%20assignee%20%3D%20Sven%20AND%20created%20CHANGED%20after%202012-02-13"));

      assertThat(
              jiraUrl()
                      .scheme(SchemeType.HTTP)
                      .host("example.com")
                      .port(8080)
                      .path("/jira")
                      .apiVersion("latest")
                      .jqlQuery(
                              jql()
                                      .reporter().equal("Markus")
                                      .or()
                                      .assignee().equal("Sven")
                                      .and()
                                      .created().changed().after("2012-02-13")
                                      .and()
                                      .status().changed().from("Open").to("Closed")
                                      .andReturn()
                      )
                      .build()
                      .toString(),
              is("http://example.com:8080/jira/rest/api/latest?jql=reporter%20%3D%20Markus%20OR%20assignee%20%3D%20Sven%20AND%20created%20CHANGED%20after%202012-02-13%20AND%20status%20CHANGED%20FROM%20Open%20TO%20Closed"));
  }
}
