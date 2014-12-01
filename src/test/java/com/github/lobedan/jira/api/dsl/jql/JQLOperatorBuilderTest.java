package com.github.lobedan.jira.api.dsl.jql;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static com.github.lobedan.jira.api.dsl.jql.JQLBuilder.jql;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by zambezi on 01.12.14.
 */
@RunWith(JUnit4.class)
public class JQLOperatorBuilderTest {

    @Test
    public void testEqualOperator() throws Exception {
        assertThat(
                jql()
                        .assignee().equal("Markus")
                        .build(),
                is("assignee = Markus")
        );
    }

    @Test
    public void testNotEqualsOperator() throws Exception {
        assertThat(
                jql()
                        .assignee().notEquals("Markus")
                        .build(),
                is("assignee != Markus")
        );
    }

    @Test
    public void testGreaterThanOperator() throws Exception {
        assertThat(
                jql()
                        .affectedVersion().greaterThan(2)
                        .build(),
                is("affectedVersion > 2")
        );
    }

    @Test
    public void testGreaterThanEqualsOperator() throws Exception {
        assertThat(
                jql()
                        .affectedVersion().greaterThanEquals(2)
                        .build(),
                is("affectedVersion >= 2")
        );
    }

    @Test
    public void testLessThanOperator() throws Exception {
        assertThat(
                jql()
                        .affectedVersion().lessThan(2)
                        .build(),
                is("affectedVersion < 2")
        );
    }

    @Test
    public void testLessThanEqualsOperator() throws Exception {
        assertThat(
                jql()
                        .affectedVersion().lessThanEquals(2)
                        .build(),
                is("affectedVersion <= 2")
        );
    }

    @Test
    public void testInOperator() throws Exception {
        assertThat(
                jql()
                        .reporter().in("jsmith", "jbrown", "jjones")
                        .build(),
                is("reporter in (jsmith,jbrown,jjones)")
        );
    }

    @Test
    public void testNotInOperator() throws Exception {
        assertThat(
                jql()
                        .reporter().notIn("jsmith", "jbrown", "jjones")
                        .build(),
                is("reporter not in (jsmith,jbrown,jjones)")
        );
    }

    @Test
    public void testContainsOperator() throws Exception {
        assertThat(
                jql()
                        .summary().contains("win*")
                        .build(),
                is("summary ~ \"win*\"")
        );
    }

    @Test
    public void testDoesNotContainOperator() throws Exception {
        assertThat(
                jql()
                        .summary().doesNotContain("win*")
                        .build(),
                is("summary !~ \"win*\"")
        );
    }

    @Test
    public void testIsOperator() throws Exception {
        assertThat(
                jql()
                        .fixVersion().is().empty()
                        .build(),
                is("fixVersion is empty")
        );
    }

    @Test
    public void testIsNotOperator() throws Exception {
        assertThat(
                jql()
                        .fixVersion().isNot().empty()
                        .build(),
                is("fixVersion is not empty")
        );
    }

    @Test
    public void testWasOperator() throws Exception {
        assertThat(
                jql()
                        .status().was("Resolved").by("jsmith").before("2011/02/02")
                        .build(),
                is("status was Resolved BY jsmith before 2011/02/02")
        );
    }

    @Test
    public void testWasNotOperator() throws Exception {
        assertThat(
                jql()
                        .status().wasNot("Resolved").by("jsmith").before("2011/02/02")
                        .build(),
                is("status was not Resolved BY jsmith before 2011/02/02")
        );
    }

    @Test
    public void testWasInOperator() throws Exception {
        assertThat(
                jql()
                        .status().wasIn("Resolved", "In Progress")
                        .build(),
                is("status was in (Resolved,In Progress)")
        );
    }

    @Test
    public void testWasNotInOperator() throws Exception {
        assertThat(
                jql()
                        .status().wasNotIn("Resolved", "In Progress")
                        .build(),
                is("status was not in (Resolved,In Progress)")
        );
    }

    @Test
    public void testChangedOperator() throws Exception {
        assertThat(
                jql()
                        .assignee().changed()
                        .build(),
                is("assignee CHANGED")
        );
        assertThat(
                jql()
                        .status().changed().from("In Progress").to("Open")
                        .build(),
                is("status CHANGED FROM In Progress TO Open")
        );
    }
}
