package com.github.lobedan.jira.api.builder;

import com.github.lobedan.jira.api.types.SchemeType;
import com.github.lobedan.jira.api.types.ValueType;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static com.github.lobedan.jira.api.builder.JiraUrlBuilder.JiraUrl;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Test all Jira Fields
 *
 * @author Sven Klemmer
 * @since 0.1.0
 */
@RunWith(JUnit4.class)
public class JQLFieldBuilderTest {

  @Test
  public void testAffectedVersionField() throws Exception {
    assertThat(
        JiraUrl()
            .scheme(SchemeType.HTTP)
            .host("example.com")
            .path("")
            .apiVersion(2)
            .jql()
            .affectedVersion().equal(3.14)
            .or()
            .affectedVersion().equal("Big Ted")
            .or()
            .affectedVersion().equal(10350)
            .end()
            .build()
            .toString(),
        is("http://example.com/rest/api/2/?jql=affectedVersion%20%3D%203.14%20OR%20affectedVersion%20%3D%20" +
           "Big%20Ted%20OR%20affectedVersion%20%3D%2010350"));
  }

  @Test
  public void testAssigneeField() throws Exception {
    assertThat(
        JiraUrl()
            .scheme(SchemeType.HTTP)
            .host("example.com")
            .path("")
            .apiVersion(2)
            .jql()
            .assignee().equal("jsmith")
            .or()
            .assignee().was("\"John Smith\"")
            .end()
            .build()
            .toString(),
        is("http://example.com/rest/api/2/?jql=assignee%20%3D%20jsmith%20OR%20assignee%20was%20%22John%20Smith%22"));
  }

  @Test
  public void testAttachmentsField() throws Exception {
    assertThat(
        JiraUrl()
            .scheme(SchemeType.HTTP)
            .host("example.com")
            .path("")
            .apiVersion(2)
            .jql()
            .attachments().is(ValueType.EMPTY)
            .or()
            .attachments().isNot(ValueType.NULL)
            .end()
            .build()
            .toString(),
        is("http://example.com/rest/api/2/?jql=attachments%20is%20empty%20OR%20attachments%20is%20null"));
  }

  @Test
  public void testCategoryField() throws Exception {
    assertThat(
        JiraUrl()
            .scheme(SchemeType.HTTP)
            .host("example.com")
            .path("")
            .apiVersion(2)
            .jql()
            .category().equal("\"Alphabet Projects\"")
            .end()
            .build()
            .toString(),
        is("http://example.com/rest/api/2/?jql=category%20%3D%20%22Alphabet%20Projects%22"));
  }

  @Test
  public void testCommentField() throws Exception {
    assertThat(
        JiraUrl()
            .scheme(SchemeType.HTTP)
            .host("example.com")
            .path("")
            .apiVersion(2)
            .jql()
            .comment().contains("\"My PC is quite old\"")
            .or()
            .comment().doesNotContain("\"My PC is quite old\"")
            .end()
            .build()
            .toString(),
        is("http://example.com/rest/api/2/?jql=comment%20~%20%22My%20PC%20is%20quite%20old%22%20OR%20comment%20" +
           "%21~%20%22My%20PC%20is%20quite%20old%22"));
  }

  @Test
  public void testComponentField() throws Exception {
    assertThat(
        JiraUrl()
            .scheme(SchemeType.HTTP)
            .host("example.com")
            .path("")
            .apiVersion(2)
            .jql()
            .component().in("Comp1", "Comp2")
            .or()
            .component().in("Comp1")
            .and()
            .component().in("Comp2")
            .or()
            .component().equal("Comp1")
            .and()
            .component().equal("Comp2")
            .or()
            .component().equal(20500)
            .end()
            .build()
            .toString(),
        is("http://example.com/rest/api/2/?jql=component%20%28Comp1%2CComp2%29%20OR%20component%20%28Comp1%29%20AND%20"
           +
           "component%20%28Comp2%29%20OR%20component%20%3D%20Comp1%20AND%20component%20%3D%20Comp2%20OR%20component%20%3D%2020500"));
  }

  @Test
  public void testCreatedField() throws Exception {
    assertThat(
        JiraUrl()
            .scheme(SchemeType.HTTP)
            .host("example.com")
            .path("")
            .apiVersion(2)
            .jql()
            .created().lessThan("2010/12/12")
            .or()
            .created().lessThanEquals("2010/12/12")
            .or()
            .created().greaterThan("2010/12/12")
            .or()
            .created().greaterThanEquals("2010/12/12")
            .end()
            .build()
            .toString(),
        is("http://example.com/rest/api/2/?jql=created%20%3C%202010%2F12%2F12%20OR%20created%20%3C%3D%202010" +
           "%2F12%2F12%20OR%20created%20%3E%202010%2F12%2F12%20OR%20created%20%3E%3D%202010%2F12%2F12"));
  }

  @Test
  public void testCreatorField() throws Exception {
    assertThat(
        JiraUrl()
            .scheme(SchemeType.HTTP)
            .host("example.com")
            .path("")
            .apiVersion(2)
            .jql()
            .creator().equal("Jill Jones")
            .end()
            .build()
            .toString(),
        is("http://example.com/rest/api/2/?jql=creator%20%3D%20Jill%20Jones"));
  }

  @Test
  public void testCustomFieldField() throws Exception {
    assertThat(
        JiraUrl()
            .scheme(SchemeType.HTTP)
            .host("example.com")
            .path("")
            .apiVersion(2)
            .jql()
            .customField("test").equal("test")
            .end()
            .build()
            .toString(),
        is("http://example.com/rest/api/2/?jql=test%20%3D%20test"));
  }

  @Test
  public void testDescriptionField() throws Exception {
    assertThat(
        JiraUrl()
            .scheme(SchemeType.HTTP)
            .host("example.com")
            .path("")
            .apiVersion(2)
            .jql()
            .description().contains("Please")
            .or()
            .description().doesNotContain("Please")
            .end()
            .build()
            .toString(),
        is("http://example.com/rest/api/2/?jql=description%20~%20Please%20OR%20description%20%21~%20Please"));
  }

  @Test
  public void testDueDateField() throws Exception {
    assertThat(
        JiraUrl()
            .scheme(SchemeType.HTTP)
            .host("example.com")
            .path("")
            .apiVersion(2)
            .jql()
            .dueDate().lessThan("2010/12/31")
            .or()
            .dueDate().lessThanEquals("2010/12/31")
            .and()
            .dueDate().equal("1d")
            .or()
            .dueDate().greaterThan("2011/01/01")
            .and()
            .dueDate().greaterThanEquals("2011/01/01")
            .end()
            .build()
            .toString(),
        is("http://example.com/rest/api/2/?jql=due%20%3C%202010%2F12%2F31%20OR%20due%20%3C%3D%202010%2F12%2F31%20AND" +
           "%20due%20%3D%201d%20OR%20due%20%3E%202011%2F01%2F01%20AND%20due%20%3E%3D%202011%2F01%2F01"));
  }

  @Test
  public void testEnvironmentField() throws Exception {
    assertThat(
        JiraUrl()
            .scheme(SchemeType.HTTP)
            .host("example.com")
            .path("")
            .apiVersion(2)
            .jql()
            .environment().contains("Third Floor")
            .end()
            .build()
            .toString(),
        is("http://example.com/rest/api/2/?jql=environment%20~%20Third%20Floor"));
  }

  @Test
  public void testEpicLinkField() throws Exception {
    assertThat(
        JiraUrl()
            .scheme(SchemeType.HTTP)
            .host("example.com")
            .path("")
            .apiVersion(2)
            .jql()
            .epicLink().equal("Jupiter")
            .end()
            .build()
            .toString(),
        is("http://example.com/rest/api/2/?jql=%22epic%20link%22%20%3D%20Jupiter"));
  }

  @Test
  public void testFilterField() throws Exception {
    assertThat(
        JiraUrl()
            .scheme(SchemeType.HTTP)
            .host("example.com")
            .path("")
            .apiVersion(2)
            .jql()
            .filter().equal(12000)
            .end()
            .build()
            .toString(),
        is("http://example.com/rest/api/2/?jql=filter%20%3D%2012000"));
  }

  @Test
  public void testFixVersionField() throws Exception {
    assertThat(
        JiraUrl()
            .scheme(SchemeType.HTTP)
            .host("example.com")
            .path("")
            .apiVersion(2)
            .jql()
            .fixVersion().in(3.14, 4.2)
            .or()
            .fixVersion().equal(10001)
            .end()
            .build()
            .toString(),
        is("http://example.com/rest/api/2/?jql=fixVersion%20%283.14%2C4.2%29%20OR%20fixVersion%20%3D%2010001"));
  }

  @Test
  public void testIssueKeyField() throws Exception {
    assertThat(
        JiraUrl()
            .scheme(SchemeType.HTTP)
            .host("example.com")
            .path("")
            .apiVersion(2)
            .jql()
            .issueKey().equal("ABC-123")
            .or()
            .key().equal("ABC-123")
            .end()
            .build()
            .toString(),
        is("http://example.com/rest/api/2/?jql=issueKey%20%3D%20ABC-123%20OR%20issueKey%20%3D%20ABC-123"));
  }

  @Test
  public void testIssueTypeField() throws Exception {
    assertThat(
        JiraUrl()
            .scheme(SchemeType.HTTP)
            .host("example.com")
            .path("")
            .apiVersion(2)
            .jql()
            .issueType().in("Bug", "Improvement")
            .or()
            .issueType().equal(2)
            .end()
            .build()
            .toString(),
        is("http://example.com/rest/api/2/?jql=issueType%20%28Bug%2CImprovement%29%20OR%20issueType%20%3D%202")
    );
  }

  @Test
  public void testLastViewedField() throws Exception {
    assertThat(
        JiraUrl()
            .scheme(SchemeType.HTTP)
            .host("example.com")
            .path("")
            .apiVersion(2)
            .jql()
            .lastViewed().lessThan("2010/12/12")
            .or()
            .lastViewed().lessThanEquals("2010/12/12")
            .or()
            .lastViewed().greaterThan("2010/12/12")
            .or()
            .lastViewed().greaterThanEquals("2010/12/12")
            .end()
            .build()
            .toString(),
        is("http://example.com/rest/api/2/?jql=lastViewed%20%3C%202010%2F12%2F12%20OR%20lastViewed%20%3C%3D%202010" +
           "%2F12%2F12%20OR%20lastViewed%20%3E%202010%2F12%2F12%20OR%20lastViewed%20%3E%3D%202010%2F12%2F12"));
  }

  @Test
  public void testLevelField() throws Exception {
    assertThat(
        JiraUrl()
            .scheme(SchemeType.HTTP)
            .host("example.com")
            .path("")
            .apiVersion(2)
            .jql()
            .level().in("Really High")
            .or()
            .level().equal(123)
            .end()
            .build()
            .toString(),
        is("http://example.com/rest/api/2/?jql=level%20%28Really%20High%29%20OR%20level%20%3D%20123"));
  }

  @Test
  public void testOriginalEstimateField() throws Exception {
    assertThat(
        JiraUrl()
            .scheme(SchemeType.HTTP)
            .host("example.com")
            .path("")
            .apiVersion(2)
            .jql()
            .originalEstimate().equal("1h")
            .or()
            .originalEstimate().greaterThan("2d")
            .end()
            .build()
            .toString(),
        is("http://example.com/rest/api/2/?jql=originalEstimate%20%3D%201h%20OR%20originalEstimate%20%3E%202d"));
  }

  @Test
  public void testParentField() throws Exception {
    assertThat(
        JiraUrl()
            .scheme(SchemeType.HTTP)
            .host("example.com")
            .path("")
            .apiVersion(2)
            .jql()
            .parent().equal("TEST-1234")
            .end()
            .build()
            .toString(),
        is("http://example.com/rest/api/2/?jql=parent%20%3D%20TEST-1234"));
  }

  @Test
  public void testPriorityField() throws Exception {
    assertThat(
        JiraUrl()
            .scheme(SchemeType.HTTP)
            .host("example.com")
            .path("")
            .apiVersion(2)
            .jql()
            .priority().equal("High")
            .or()
            .priority().equal(10000)
            .end()
            .build()
            .toString(),
        is("http://example.com/rest/api/2/?jql=priority%20%3D%20High%20OR%20priority%20%3D%2010000"));
  }

  @Test
  public void testProjectField() throws Exception {
    assertThat(
        JiraUrl()
            .scheme(SchemeType.HTTP)
            .host("example.com")
            .path("")
            .apiVersion(2)
            .jql()
            .project().equal(1234)
            .end()
            .build()
            .toString(),
        is("http://example.com/rest/api/2/?jql=project%20%3D%201234"));
  }

  @Test
  public void testRemainingEstimateField() throws Exception {
    assertThat(
        JiraUrl()
            .scheme(SchemeType.HTTP)
            .host("example.com")
            .path("")
            .apiVersion(2)
            .jql()
            .remainingEstimate().greaterThan("4h")
            .end()
            .build()
            .toString(),
        is("http://example.com/rest/api/2/?jql=remainingEstimate%20%3E%204h"));
  }

  @Test
  public void testReporterField() throws Exception {
    assertThat(
        JiraUrl()
            .scheme(SchemeType.HTTP)
            .host("example.com")
            .path("")
            .apiVersion(2)
            .jql()
            .reporter().equal("jjones")
            .end()
            .build()
            .toString(),
        is("http://example.com/rest/api/2/?jql=reporter%20%3D%20jjones"));
  }

  @Test
  public void testResolutionField() throws Exception {
    assertThat(
        JiraUrl()
            .scheme(SchemeType.HTTP)
            .host("example.com")
            .path("")
            .apiVersion(2)
            .jql()
            .resolution().equal(5)
            .or()
            .resolution().in("Open", "Closed")
            .end()
            .build()
            .toString(),
        is("http://example.com/rest/api/2/?jql=resolution%20%3D%205%20OR%20resolution%20%28Open%2CClosed%29"));
  }

  @Test
  public void testResolvedField() throws Exception {
    assertThat(
        JiraUrl()
            .scheme(SchemeType.HTTP)
            .host("example.com")
            .path("")
            .apiVersion(2)
            .jql()
            .resolved().lessThan("2010/12/31")
            .or()
            .resolved().lessThanEquals("2010/12/31")
            .and()
            .resolved().greaterThan("2010/12/31")
            .or()
            .resolved().greaterThanEquals("2010/12/31")
            .end()
            .build()
            .toString(),
        is("http://example.com/rest/api/2/?jql=resolved%20%3C%202010%2F12%2F31%20OR%20resolved%20%3C%3D%202010" +
           "%2F12%2F31%20AND%20resolved%20%3E%202010%2F12%2F31%20OR%20resolved%20%3E%3D%202010%2F12%2F31"));
  }

  @Test
  public void testSprintField() throws Exception {
    assertThat(
        JiraUrl()
            .scheme(SchemeType.HTTP)
            .host("example.com")
            .path("")
            .apiVersion(2)
            .jql()
            .sprint().equal(999)
            .or()
            .sprint().in("February 1", "February 2", "February 3")
            .or()
            .sprint().isNot(ValueType.EMPTY)
            .end()
            .build()
            .toString(),
        is("http://example.com/rest/api/2/?jql=sprint%20%3D%20999%20OR%20sprint%20%28February%201%2CFebruary%202%2C" +
           "February%203%29%20OR%20sprint%20is%20empty"));
  }

  @Test
  public void testStatusField() throws Exception {
    assertThat(
        JiraUrl()
            .scheme(SchemeType.HTTP)
            .host("example.com")
            .path("")
            .apiVersion(2)
            .jql()
            .status().was("open")
            .or()
            .status().equal(1)
            .or()
            .status().equal("open")
            .end()
            .build()
            .toString(),
        is("http://example.com/rest/api/2/?jql=status%20was%20open%20OR%20status%20%3D%201%20OR%20status%20%3D%20open"));
  }

  @Test
  public void testSummaryField() throws Exception {
    assertThat(
        JiraUrl()
            .scheme(SchemeType.HTTP)
            .host("example.com")
            .path("")
            .apiVersion(2)
            .jql()
            .summary().contains("Error")
            .or()
            .summary().doesNotContain("Error")
            .end()
            .build()
            .toString(),
        is("http://example.com/rest/api/2/?jql=summary%20~%20Error%20OR%20summary%20%21~%20Error"));
  }

  @Test
  public void testTextField() throws Exception {
    assertThat(
        JiraUrl()
            .scheme(SchemeType.HTTP)
            .host("example.com")
            .path("")
            .apiVersion(2)
            .jql()
            .text().contains("Test")
            .end()
            .build()
            .toString(),
        is("http://example.com/rest/api/2/?jql=text%20~%20Test"));
  }

  @Test
  public void testTimeSpentField() throws Exception {
    assertThat(
        JiraUrl()
            .scheme(SchemeType.HTTP)
            .host("example.com")
            .path("")
            .apiVersion(2)
            .jql()
            .timeSpent().greaterThan("5d")
            .end()
            .build()
            .toString(),
        is("http://example.com/rest/api/2/?jql=timeSpent%20%3E%205d"));
  }

  @Test
  public void testUpdatedField() throws Exception {
    assertThat(
        JiraUrl()
            .scheme(SchemeType.HTTP)
            .host("example.com")
            .path("")
            .apiVersion(2)
            .jql()
            .updated().lessThan("2010/12/12")
            .or()
            .updated().lessThanEquals("2010/12/12")
            .or()
            .updated().greaterThan("2010/12/12")
            .or()
            .updated().greaterThanEquals("2010/12/12")
            .end()
            .build()
            .toString(),
        is("http://example.com/rest/api/2/?jql=updated%20%3C%202010%2F12%2F12%20OR%20updated%20%3C%3D%202010" +
           "%2F12%2F12%20OR%20updated%20%3E%202010%2F12%2F12%20OR%20updated%20%3E%3D%202010%2F12%2F12")
    );
  }

  @Test
  public void testVoterField() throws Exception {
    assertThat(
        JiraUrl()
            .scheme(SchemeType.HTTP)
            .host("example.com")
            .path("")
            .apiVersion(2)
            .jql()
            .voter().equal("jssmith")
            .end()
            .build()
            .toString(),
        is("http://example.com/rest/api/2/?jql=voter%20%3D%20jssmith"));
  }

  @Test
  public void testVotesField() throws Exception {
    assertThat(
        JiraUrl()
            .scheme(SchemeType.HTTP)
            .host("example.com")
            .path("")
            .apiVersion(2)
            .jql()
            .votes().greaterThan(12)
            .end()
            .build()
            .toString(),
        is("http://example.com/rest/api/2/?jql=votes%20%3E%2012"));
  }

  @Test
  public void testWatcherField() throws Exception {
    assertThat(
        JiraUrl()
            .scheme(SchemeType.HTTP)
            .host("example.com")
            .path("")
            .apiVersion(2)
            .jql()
            .watcher().equal("jsmith")
            .end()
            .build()
            .toString(),
        is("http://example.com/rest/api/2/?jql=watcher%20%3D%20jsmith"));
  }

  @Test
  public void testWatchersField() throws Exception {
    assertThat(
        JiraUrl()
            .scheme(SchemeType.HTTP)
            .host("example.com")
            .path("")
            .apiVersion(2)
            .jql()
            .watchers().greaterThan(3)
            .end()
            .build()
            .toString(),
        is("http://example.com/rest/api/2/?jql=watchers%20%3E%203"));
  }

  @Test
  public void testWorkRatioField() throws Exception {
    assertThat(
        JiraUrl()
            .scheme(SchemeType.HTTP)
            .host("example.com")
            .path("")
            .apiVersion(2)
            .jql()
            .workRatio().greaterThan(75)
            .end()
            .build()
            .toString(),
        is("http://example.com/rest/api/2/?jql=workRatio%20%3E%2075"));
  }
}