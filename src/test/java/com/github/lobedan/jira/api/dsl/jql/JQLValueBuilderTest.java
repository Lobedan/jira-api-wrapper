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
public class JQLValueBuilderTest {

    @Test
    public void testEmptyValue() throws Exception {
        assertThat(
                jql()
                        .assignee().isNot().empty()
                        .build(),
                is("assignee is not empty")
        );
    }

    @Test
    public void testNullValue() throws Exception {
        assertThat(
                jql()
                        .assignee().is().nul()
                        .build(),
                is("assignee is null")
        );
    }

}
