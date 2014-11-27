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
 * Test for JQLBuilder, JQLFieldBuilder and JQLOpsBuilder
 *
 * @author svenklemmer
 * @since jira-api-wrapper 0.1.0
 */
@RunWith(JUnit4.class)
public class JQLBuilderTest {
  private static final Logger LOGGER = LogManager.getLogger(JQLBuilder.class);

  @Test
  public void testCanBuildJQLQuery() throws Exception {
    //general test
    assertThat(JiraUrl()
                   .scheme(SchemeType.HTTP)
                   .host("example.com")
                   .path("")
                   .apiVersion("latest")
                   .jql()
                   .project().in("JRA, CONF")
                   .and()
                   .status().is("open")
                   .or()
                   .status().is("closed")
                   .and()
                   .assignee().isNot("jsmith")
                   .end()
                   .build()
                   .toString(),
               is("http://example.com/rest/api/latest/"
                  + "?jql=project+in+%28JRA%2C+CONF%29+AND+status+%3D+open+OR+status+%3D+closed+AND+assignee+%21%3D+jsmith"));

    //test fields reporter, assignee, created, updated, project, status
    //include test operations is, isNot, after, before, in, was, by
    assertThat(JiraUrl()
                   .scheme(SchemeType.HTTP)
                   .host("example.com")
                   .path("")
                   .apiVersion("latest")
                   .jql()
                   .reporter().is("person")
                   .and()
                   .assignee().isNot("hans")
                   .and()
                   .created().after("2012-02-02")
                   .or()
                   .updated().before("2012-02-15")
                   .and()
                   .project().in("DIH", "JRA")
                   .and()
                   .status().was("Resolved").by("jsmith").before("2011-02-02")
                   .end()
                   .build()
                   .toString(),
               is("http://example.com/rest/api/latest/"
                  + "?jql=reporter is person and assigne is not hans and created after 2012-02-02 OR updated before 2012-02-15"
                  + "AND project in (DIH,JRA) and status was Resolved BY jsmith before 2011-02-02"));
  }
}
