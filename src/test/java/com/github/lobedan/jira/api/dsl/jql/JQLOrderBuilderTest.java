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
public class JQLOrderBuilderTest {

    @Test
    public void testASCOrder() throws Exception {
        assertThat(
                jql()
                        .assignee().is().empty().orderBy("reporter").asc()
                        .build(),
                is("assignee is empty order by reporter asc")
        );
    }

    @Test
    public void testDESCOrder() throws Exception {
        assertThat(
                jql()
                        .assignee().is().empty().orderBy("reporter").desc()
                        .build(),
                is("assignee is empty order by reporter desc")
        );
    }
}
