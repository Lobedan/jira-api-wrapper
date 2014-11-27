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
                   .status().equal("open")
                   .or()
                   .status().equal("closed")
                   .and()
                   .assignee().notEqual("jsmith")
                   .end()
                   .build()
                   .toString(),
               is("http://example.com/rest/api/latest/?jql=project%20%28JRA%2C%20CONF%29%20AND%20status%20%3D%20open%20OR" +
                       "%20status%20%3D%20closed%20AND%20assignee%20%21%3D%20jsmith")
    );

    //test fields reporter, assignee, created, updated, project, status
    //include test operations is, isNot, after, before, in, was, by
    assertThat(JiraUrl()
                   .scheme(SchemeType.HTTP)
                   .host("example.com")
                   .path("")
                   .apiVersion("latest")
                   .jql()
                        .reporter().equal("person")
                    .and()
                        .assignee().notEqual("hans")
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
                  + "?jql=reporter%20%3D%20person%20AND%20assignee%20%21%3D%20hans%20AND%20created%20after%202012-02-02%20OR" +
                       "%20updated%20before%202012-02-15%20AND%20project%20%28DIH%2CJRA%29%20AND%20status%20was%20Resolved%20" +
                       "BY%20jsmith%20before%202011-02-02")

    );
  }
}
