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
public class JQLKeywordBuilderTest {

    @Test
    public void testAndKeyword() throws Exception {
        assertThat(
                jql()
                        .assignee().is().empty()
                        .and()
                        .affectedVersion().greaterThan(2)
                        .build(),
                is("assignee is empty AND affectedVersion > 2")
        );
    }

    @Test
    public void testOrKeyword() throws Exception {
        assertThat(
                jql()
                        .assignee().is().empty()
                        .or()
                        .affectedVersion().greaterThan(2)
                        .build(),
                is("assignee is empty OR affectedVersion > 2")
        );
    }

    @Test
    public void testOrderByKeyword() throws Exception {
        assertThat(
                jql()
                        .assignee().is().empty().orderBy("reporter").asc()
                        .build(),
                is("assignee is empty order by reporter asc")
        );
    }
}
