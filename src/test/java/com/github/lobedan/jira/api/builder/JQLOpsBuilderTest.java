package com.github.lobedan.jira.api.builder;

import com.github.lobedan.jira.api.types.OrderType;
import com.github.lobedan.jira.api.types.SchemeType;
import com.github.lobedan.jira.api.types.ValueType;

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
public class JQLOpsBuilderTest {

  @Test
  public void testEqualNotEqual() throws Exception {
    assertThat(
        JiraUrl()
            .scheme(SchemeType.HTTP)
            .host("example.com")
            .path("")
            .apiVersion(2)
            .jql()
            .assignee().equal("hans")
            .or()
            .assignee().notEqual("klaus")
            .or()
            .assignee().notEqual(ValueType.NULL)
            .end()
            .build()
            .toString(),
        is("http://example.com/rest/api/2/?jql=assignee%20%3D%20hans%20OR%20assignee%20%21%3D%20klaus%20OR%20assignee%20%21%3D%20null"));
  }

  @Test
  public void testGreaterThan() throws Exception {
    assertThat(
        JiraUrl()
            .scheme(SchemeType.HTTP)
            .host("example.com")
            .path("")
            .apiVersion(2)
            .jql()
            .votes().greaterThan(4)
            .or()
            .votes().greaterThanEquals(3)
            .end()
            .build()
            .toString(),
        is("http://example.com/rest/api/2/?jql=votes%20%3E%204%20OR%20votes%20%3E%3D%203"));
  }

  @Test
  public void testLessThan() throws Exception {
    assertThat(
        JiraUrl()
            .scheme(SchemeType.HTTP)
            .host("example.com")
            .path("")
            .apiVersion(2)
            .jql()
            .votes().lessThan(4)
            .or()
            .votes().lessThanEquals(3)
            .end()
            .build()
            .toString(),
        is("http://example.com/rest/api/2/?jql=votes%20%3C%204%20OR%20votes%20%3C%3D%203"));
  }

  @Test
  public void testInNotIn() throws Exception {
    assertThat(
        JiraUrl()
            .scheme(SchemeType.HTTP)
            .host("example.com")
            .path("")
            .apiVersion(2)
            .jql()
            .reporter().in("jsmith", "jbrown", "jjones")
            .and()
            .fixVersion().notIn("A", "B", "C", "D")
            .end()
            .build()
            .toString(),
        is("http://example.com/rest/api/2/?jql=reporter%20%28jsmith%2Cjbrown%2Cjjones%29%20AND%20fixVersion%20%28A%2CB%2CC%2CD%29"));
  }

  @Test
  public void testContainsDoesNotContain() throws Exception {
    assertThat(
        JiraUrl()
            .scheme(SchemeType.HTTP)
            .host("example.com")
            .path("")
            .apiVersion(2)
            .jql()
            .summary().contains("win*")
            .and()
            .summary().doesNotContain("value")
            .end()
            .build()
            .toString(),
        is("http://example.com/rest/api/2/?jql=summary%20~%20win%2A%20AND%20summary%20%21~%20value"));
  }

  @Test
  public void testIsNotIs() throws Exception {
    assertThat(
        JiraUrl()
            .scheme(SchemeType.HTTP)
            .host("example.com")
            .path("")
            .apiVersion(2)
            .jql()
            .fixVersion().is(ValueType.EMPTY)
            .and()
            .fixVersion().isNot(ValueType.NULL)
            .end()
            .build()
            .toString(),
        is("http://example.com/rest/api/2/?jql=fixVersion%20is%20empty%20AND%20fixVersion%20is%20null"));
  }

  @Test
  public void testWasWasNot() throws Exception {
    assertThat(
        JiraUrl()
            .scheme(SchemeType.HTTP)
            .host("example.com")
            .path("")
            .apiVersion(2)
            .jql()
            .status().was("In Progress")
            .and()
            .status().wasNot("Open")
            .end()
            .build()
            .toString(),
        is("http://example.com/rest/api/2/?jql=status%20was%20In%20Progress%20AND%20status%20was%20not%20Open"));
  }

  @Test
  public void testWasInWasNotIn() throws Exception {
    assertThat(
        JiraUrl()
            .scheme(SchemeType.HTTP)
            .host("example.com")
            .path("")
            .apiVersion(2)
            .jql()
            .status().wasIn("Resolved", "In Progress")
            .and()
            .status().wasNotIn("Resolved", "In Progress")
            .end()
            .build()
            .toString(),
        is("http://example.com/rest/api/2/?jql=status%20%28Resolved%2CIn%20Progress%29%20AND%20status%20%28Resolved%2CIn%20Progress%29"));
  }

  @Test
  public void testChanged() throws Exception {
    assertThat(
        JiraUrl()
            .scheme(SchemeType.HTTP)
            .host("example.com")
            .path("")
            .apiVersion(2)
            .jql()
            .assignee().changed()
            .and()
            .assignee().changed().from("In Progress").to("Open")
            .and()
            .assignee().changed().by("freddy")
            .end()
            .build()
            .toString(),
        is("http://example.com/rest/api/2/?jql=assignee%20CHANGED%20%20AND%20assignee%20CHANGED%20%20FROM%20In%20Progress"
           + "%20TO%20Open%20AND%20assignee%20CHANGED%20%20BY%20freddy"));
  }

  @Test
  public void testByFromToBeforeAfter() throws Exception {
    assertThat(
        JiraUrl()
            .scheme(SchemeType.HTTP)
            .host("example.com")
            .path("")
            .apiVersion(2)
            .jql()
            .assignee().changed()
            .and()
            .assignee().changed().from("In Progress").to("Open")
            .and()
            .assignee().changed().by("freddy").before("2012/01/01").after("2011/01/01")
            .and()
            .status().changed().during("2011/01/01", "2012/01/01")
            .or()
            .status().changed().on("2013/02/02")
            .and()
            .dueDate().is(ValueType.EMPTY).during("2011/01/01", "2012/01/01")
            .orderBy(OrderType.ASC, "priority", "created")
            .end()
            .build()
            .toString(),
        is("http://example.com/rest/api/2/?jql=assignee%20CHANGED%20%20AND%20assignee%20CHANGED%20%20FROM%20In%20Progress"
           + "%20TO%20Open%20AND%20assignee%20CHANGED%20%20BY%20freddy%20BEFORE%202012%2F01%2F01%20AFTER%202011%2F01%2F01%20"
           + "AND%20status%20CHANGED%20%20DURING%20%282011%2F01%2F01%2C%202012%2F01%2F01%29%20OR%20status%20CHANGED%20%20ON"
           + "%202013%2F02%2F02%20AND%20due%20is%20empty%20DURING%20%282011%2F01%2F01%2C%202012%2F01%2F01%29%20order%20by%20"
           + "priority%2Ccreated%20ASC"));
  }
}
