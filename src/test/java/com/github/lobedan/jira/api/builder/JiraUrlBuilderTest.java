package com.github.lobedan.jira.api.builder;

import com.github.lobedan.jira.api.types.SchemeType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static com.github.lobedan.jira.api.builder.JiraUrlBuilder.JiraUrl;
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
  public void testCanBuildJiraUrlDSL() throws Exception {
    assertThat(JiraUrl()
                   .scheme(SchemeType.HTTP)
                   .host("example.com")
                   .port(8080)
                   .path("/jira")
                   .api("latest")
                   .jql()
                   .start()
                         .reporter()
                             .is("markus")
                             .or()
                             .assignee()
                             .is("salvatore")
                     .and()
                     .created()
                      .was("2012-01-01")
                   .end()
                   .startAt(0)
                   .maxResults(10)
                   .total(50)
        .build(),
               is("http://example.com:8080/jira/rest/api/latest/?jql=(reporter = markus OR assignee = savaltore) AND created was 2012-01-01&startAt=0&maxResults=10&total=50"));
  }

}
