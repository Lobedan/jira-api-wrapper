package com.github.lobedan.jira.api.builder;

import java.net.URI;

import com.github.lobedan.jira.api.types.ProtocolType;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author svenklemmer
 * @since 0.1.0
 */
@RunWith(JUnit4.class)
public class JiraUrlBuilderTest {

  private JiraUrlBuilder builder;

  @Before
  public void setup() {
    builder = new JiraUrlBuilder();
  }

  @Test
  public void testCanBuildJiraBaseUrl() throws Exception {
    assertThat(builder.protocol("http").host("example.com").apiPath("/jira/rest/api/latest/").build().toString(),
               is("http://example.com/jira/rest/api/latest/"));
    assertThat(builder.protocol(ProtocolType.HTTP).host("example.com").apiPath("/jira/rest/api/latest/").build().toString(),
               is("http://example.com/jira/rest/api/latest/"));
  }

  @Test
  public void testCanBuildUriInstance() throws Exception {
    assertThat(builder.protocol("http").host("example.com").apiPath("/jira/rest/api/latest/").build(),
               is(new URI("http", "example.com", "/jira/rest/api/latest/", null)));
  }

  @Test
  public void testCanBuildWithPort() throws Exception {
    assertThat(builder.protocol("http").host("example.com").port(80).apiPath("/jira/rest/api/latest/").build().toString(),
               is("http://example.com:80/jira/rest/api/latest/"));

    assertThat(builder.protocol("http").host("example.com").port("8080").apiPath("/jira/rest/api/latest/").build().toString(),
               is("http://example.com:8080/jira/rest/api/latest/"));

    assertThat(builder.protocol("http").host("example.com").port(9425).apiPath("/jira/rest/api/latest/").build().toString(),
               is("http://example.com:9425/jira/rest/api/latest/"));
  }

}
