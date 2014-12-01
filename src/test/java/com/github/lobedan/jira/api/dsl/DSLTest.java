package com.github.lobedan.jira.api.dsl;

import com.github.lobedan.jira.api.domain.dsl.jql.JQL;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static com.github.lobedan.jira.api.dsl.jql.JQLBuilder.jql;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author svenklemmer
 * @since jira-api-wrapper 0.1.0
 */
@RunWith(JUnit4.class)
public class DSLTest {

  @Test
  public void testMultipleJql() throws Exception {
    assertThat(
        jql()
            .affectedVersion()
            .is()
            .empty()
            .build(),
        is("affectedVersion is empty")
    );
    assertThat(
        jql()
            .assignee().equal("markus")
            .and()
            .reporter().equal("zoltan")
            .build(),
        is("assignee = markus AND reporter = zoltan")
    );

    assertThat(
        jql()
            .created().greaterThan("2012/01/02")
            .and()
            .updated().changed().after("2014/02/05")
            .build(),
        is("created > 2012/01/02 AND updated CHANGED after 2014/02/05")
    );
  }

  @Test
  public void testCanReturnJQLObject() throws Exception {
    assertThat(
        jql()
            .assignee().is().empty()
            .andReturn(),
        is(instanceOf(JQL.class))
    );

      assertThat(
              jql()
                      .assignee().is().empty()
                      .and()
                      .affectedVersion().greaterThan(2)
                      .or()
                      .fixVersion().greaterThan(4)
                      .andReturn(),
              is(instanceOf(JQL.class))
      );
  }
}
