package com.github.lobedan.jira.api.builder;

import java.net.URI;

import com.github.lobedan.jira.api.types.ExpandTypes;
import com.github.lobedan.jira.api.types.ProtocolType;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static com.github.lobedan.jira.api.builder.JQLStaticBuilder.field;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author svenklemmer
 * @since 0.1.0
 */
@RunWith(JUnit4.class)
public class CustomJiraUrlBuilderTest {

  private CustomJiraUrlBuilder builder;

  @Before
  public void setup() {
    builder = new CustomJiraUrlBuilder();
  }

  @Test
  public void testCanBuildJiraBaseUrl() throws Exception {
    assertThat(builder.protocol("http").host("example.com").apiPath("/jira/rest/api/latest/").buildAndClear().toString(),
               is("http://example.com/jira/rest/api/latest/"));
    assertThat(builder.protocol(ProtocolType.HTTP).host("example.com").apiPath("/jira/rest/api/latest/").buildAndClear()
                   .toString(),
               is("http://example.com/jira/rest/api/latest/"));
  }

  @Test
  public void testCanBuildUriInstance() throws Exception {
    assertThat(builder.protocol("http").host("example.com").apiPath("/jira/rest/api/latest/").buildAndClear(),
               is(new URI("http", "example.com", "/jira/rest/api/latest/", null)));
  }

  @Test
  public void testCanBuildWithPort() throws Exception {
    assertThat(builder.protocol("http").host("example.com").port(80).apiPath("/jira/rest/api/latest/").buildAndClear()
                   .toString(),
               is("http://example.com:80/jira/rest/api/latest/"));

    assertThat(builder.protocol("http").host("example.com").port("8080").apiPath("/jira/rest/api/latest/").buildAndClear()
                   .toString(),
               is("http://example.com:8080/jira/rest/api/latest/"));

    assertThat(builder.protocol("http").host("example.com").port(9425).apiPath("/jira/rest/api/latest/").buildAndClear()
                   .toString(),
               is("http://example.com:9425/jira/rest/api/latest/"));
  }

  @Test
  public void testCanIncludeJQLQuery() throws Exception {
    assertThat(
        builder
            .protocol("http")
            .host("example.com")
            .apiPath("/jira/rest/api/latest/")
            .jql(
                new JQLBuilder()
                    .brackets(
                        field("person").is("markus")
                            .or()
                            .field("person").is("salvatore")
                    )
                    .and()
                    .field("created").was("2012-01-02")
            )
            .buildAndClear().toString(),
        is("http://example.com/jira/rest/api/latest/?jql=%28person+is+markus+OR+person+is+salvatore%29+AND+created+was+2012-01-02"));
  }

  @Test
  public void testCanBuildWithParameters() throws Exception {
    assertThat(
        builder
            .protocol("http")
            .host("example.com")
            .port(80)
            .apiPath("/jira/rest/api/latest/")
            .startAt(0)
            .maxResults(10)
            .total(96)
            .buildAndClear().toString(), is("http://example.com:80/jira/rest/api/latest/?startAt=0&maxResults=10&total=96"));

    assertThat(
        builder
            .protocol("http")
            .host("example.com")
            .port(80)
            .apiPath("/jira/rest/api/latest/")
            .jql(
                new JQLBuilder()
                    .brackets(
                        field("person").is("markus")
                            .or()
                            .field("person").is("salvatore")
                    )
                    .and()
                    .field("created").was("2012-01-02")
            )
            .startAt(0)
            .maxResults(10)
            .total(96)
            .buildAndClear().toString(),
        is("http://example.com:80/jira/rest/api/latest/?jql=%28person+is+markus+OR+person+is+salvatore%29+AND+created+was+2012-01-02&startAt=0&maxResults=10&total=96"));

    assertThat(
        builder
            .protocol("http")
            .host("example.com")
            .port(80)
            .apiPath("/jira/rest/api/latest/")
            .jql(
                new JQLBuilder()
                    .brackets(
                        field("person").is("markus")
                            .or()
                            .field("person").is("salvatore")
                    )
                    .and()
                    .field("created").was("2012-01-02")
            )
            .maxResults(10)
            .total(96)
            .buildAndClear().toString(),
        is("http://example.com:80/jira/rest/api/latest/?jql=%28person+is+markus+OR+person+is+salvatore%29+AND+created+was+2012-01-02&maxResults=10&total=96"));

    assertThat(
        builder
            .protocol("http")
            .host("example.com")
            .port(80)
            .apiPath("/jira/rest/api/latest/")
            .jql(
                new JQLBuilder()
                    .brackets(
                        field("person").is("markus")
                            .or()
                            .field("person").is("salvatore")
                    )
                    .and()
                    .field("created").was("2012-01-02")
            )
            .startAt(0)
            .total(96)
            .buildAndClear().toString(),
        is("http://example.com:80/jira/rest/api/latest/?jql=%28person+is+markus+OR+person+is+salvatore%29+AND+created+was+2012-01-02&startAt=0&total=96"));
  }

  @Test
  public void testCanBuildWithExpand() throws Exception {
    assertThat(
        builder
            .protocol("http")
            .host("example.com")
            .port(80)
            .apiPath("/jira/rest/api/latest/")
            .jql(
                new JQLBuilder()
                    .brackets(
                        field("person").is("markus")
                            .or()
                            .field("person").is("salvatore")
                    )
                    .and()
                    .field("created").was("2012-01-02")
            )
            .startAt(0)
            .total(96)
            .expand(ExpandTypes.CHANGELOG, ExpandTypes.OPERATIONS)
            .buildAndClear().toString(),
        is("http://example.com:80/jira/rest/api/latest/?jql=%28person+is+markus+OR+person+is+salvatore%29+AND+created+was+2012-01-02&startAt=0&total=96&expand=changelog%2Coperations"));
  }
}
