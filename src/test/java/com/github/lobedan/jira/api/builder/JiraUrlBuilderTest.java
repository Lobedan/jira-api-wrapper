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
    public void testCanConstructJiraUrlDSL() throws Exception {
        assertThat(
                JiraUrl()
                        .scheme(SchemeType.HTTP)
                        .host("example.com")
                        .port(8080)
                        .path("/jira")
                        .apiVersion(2)
                        .build()
                        .toString(), is("http://example.com:8080/jira/rest/api/2/"));

        assertThat(
                JiraUrl()
                        .scheme(SchemeType.HTTPS)
                        .host("example.com")
                        .path("")
                        .apiVersion("latest")
                        .build()
                        .toString(), is("https://example.com/rest/api/latest/"));
    }

    @Test
    public void testCanConstructJiraUrlWithJQLDSL() throws Exception {
        assertThat(
                JiraUrl()
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
                        .created().after("2012-02-13")
                        .end()
                        .build()
                        .toString(),
                is("http://example.com:8080/jira/rest/api/latest/?jql=" +
                        "reporter%20%3D%20Markus%20OR%20assignee%20%3D%20Sven%20AND%20created%20after%202012-02-13"));
    }
}
