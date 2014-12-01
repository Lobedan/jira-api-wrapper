package com.github.lobedan.jira.api.dsl.builder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static com.github.lobedan.jira.api.dsl.builder.JQLBuilder.jql;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author svenklemmer
 * @since jira-api-wrapper 0.1.0
 */
@RunWith(JUnit4.class)
public class JQLFieldBuilderTest {
  private static final Logger LOGGER = LogManager.getLogger(JQLFieldBuilderTest.class);

  @Test
  public void testAffectedVersionField() throws Exception {
    assertThat(
        jql()
            .affectedVersion().is().empty()
            .build(),
        is("affectedVersion is empty")
    );
  }

  @Test
  public void testAssigneeField() throws Exception {
    assertThat(
        jql()
            .assignee().is().empty()
            .build(),
        is("assignee is empty")
    );
  }

  @Test
  public void testCategoryField() throws Exception {
    assertThat(
        jql()
            .category().is().empty()
            .build(),
        is("category is empty")
    );
  }

  @Test
  public void testCommentField() throws Exception {
    assertThat(
        jql()
            .comment().is().empty()
            .build(),
        is("comment is empty")
    );
  }

  @Test
  public void testComponentField() throws Exception {
    assertThat(
        jql()
            .component().is().empty()
            .build(),
        is("component is empty")
    );
  }

  @Test
  public void testCreatedField() throws Exception {
    assertThat(
        jql()
            .created().is().empty()
            .build(),
        is("created is empty")
    );
  }

  @Test
  public void testCreatedDateField() throws Exception {
    assertThat(
        jql()
            .createdDate().is().empty()
            .build(),
        is("created is empty")
    );
  }

  @Test
  public void testCreatorField() throws Exception {
    assertThat(
        jql()
            .creator().is().empty()
            .build(),
        is("creator is empty")
    );
  }

  @Test
  public void testCustomFields() throws Exception {
    assertThat(
        jql()
            .customField("test").is().empty()
            .build(),
        is("test is empty")
    );
  }

  @Test
  public void testDescriptionField() throws Exception {
    assertThat(
        jql()
            .description().is().empty()
            .build(),
        is("description is empty")
    );
  }

  @Test
  public void testDueField() throws Exception {
    assertThat(
        jql()
            .due().is().empty()
            .build(),
        is("due is empty")
    );
  }

  @Test
  public void testDueDateField() throws Exception {
    assertThat(
        jql()
            .dueDate().is().empty()
            .build(),
        is("dueDate is empty")
    );
  }

  @Test
  public void testEnvironmentField() throws Exception {
    assertThat(
        jql()
            .environment().is().empty()
            .build(),
        is("environment is empty")
    );
  }

  @Test
  public void testEpicLinkField() throws Exception {
    assertThat(
        jql()
            .epicLink().is().empty()
            .build(),
        is("\"epic link\" is empty")
    );
  }

  @Test
  public void testFilterField() throws Exception {
    assertThat(
        jql()
            .filter().is().empty()
            .build(),
        is("filter is empty")
    );
  }

  @Test
  public void testRequestField() throws Exception {
    assertThat(
        jql()
            .request().is().empty()
            .build(),
        is("request is empty")
    );
  }

  @Test
  public void testSavedFilterField() throws Exception {
    assertThat(
        jql()
            .savedFilter().is().empty()
            .build(),
        is("savedFilter is empty")
    );
  }

  @Test
  public void testSearchRequestField() throws Exception {
    assertThat(
        jql()
            .searchRequest().is().empty()
            .build(),
        is("searchRequest is empty")
    );
  }

  @Test
  public void testFixVersionField() throws Exception {
    assertThat(
        jql()
            .fixVersion().is().empty()
            .build(),
        is("fixVersion is empty")
    );
  }

  @Test
  public void testIssueKeyField() throws Exception {
    assertThat(
        jql()
            .issueKey().is().empty()
            .build(),
        is("issueKey is empty")
    );
  }

  @Test
  public void testIdField() throws Exception {
    assertThat(
        jql()
            .id().is().empty()
            .build(),
        is("id is empty")
    );
  }

  @Test
  public void testIssueField() throws Exception {
    assertThat(
        jql()
            .issue().is().empty()
            .build(),
        is("issue is empty")
    );
  }

  @Test
  public void testKeyField() throws Exception {
    assertThat(
        jql()
            .key().is().empty()
            .build(),
        is("key is empty")
    );
  }

  @Test
  public void testLastViewedField() throws Exception {
    assertThat(
        jql()
            .lastViewed().is().empty()
            .build(),
        is("lastViewed is empty")
    );
  }

  @Test
  public void testLevelField() throws Exception {
    assertThat(
        jql()
            .level().is().empty()
            .build(),
        is("level is empty")
    );
  }

  @Test
  public void testOriginalEstimateField() throws Exception {
    assertThat(
        jql()
            .originalEstimate().is().empty()
            .build(),
        is("originalEstimate is empty")
    );
  }

  @Test
  public void testTimeOriginalEstimateField() throws Exception {
    assertThat(
        jql()
            .timeOriginalEstimate().is().empty()
            .build(),
        is("timeOriginalEstimate is empty")
    );
  }

  @Test
  public void testParentField() throws Exception {
    assertThat(
        jql()
            .parent().is().empty()
            .build(),
        is("parent is empty")
    );
  }

  @Test
  public void testPriorityField() throws Exception {
    assertThat(
        jql()
            .priority().is().empty()
            .build(),
        is("priority is empty")
    );
  }

  @Test
  public void testProjectField() throws Exception {
    assertThat(
        jql()
            .project().is().empty()
            .build(),
        is("project is empty")
    );
  }

  @Test
  public void testRemainingEstimateField() throws Exception {
    assertThat(
        jql()
            .remainingEstimate().is().empty()
            .build(),
        is("remainingEstimate is empty")
    );
  }

  @Test
  public void testTimeEstimateField() throws Exception {
    assertThat(
        jql()
            .timeEstimate().is().empty()
            .build(),
        is("timeEstimate is empty")
    );
  }

  @Test
  public void testReporterField() throws Exception {
    assertThat(
        jql()
            .reporter().is().empty()
            .build(),
        is("reporter is empty")
    );
  }

  @Test
  public void testResolutionField() throws Exception {
    assertThat(
        jql()
            .resolution().is().empty()
            .build(),
        is("resolution is empty")
    );
  }

  @Test
  public void testResolutionDateField() throws Exception {
    assertThat(
        jql()
            .resolutionDate().is().empty()
            .build(),
        is("resolutionDate is empty")
    );
  }

  @Test
  public void testResolvedField() throws Exception {
    assertThat(
        jql()
            .resolved().is().empty()
            .build(),
        is("resolved is empty")
    );
  }
}
