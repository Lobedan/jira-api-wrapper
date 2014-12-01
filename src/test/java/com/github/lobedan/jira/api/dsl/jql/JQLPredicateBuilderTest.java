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
public class JQLPredicateBuilderTest {

    @Test
    public void testAfterPredicate() throws Exception {
        assertThat(
                jql()
                        .created().changed().after("2012/02/02")
                        .build(),
                is("created CHANGED after 2012/02/02")
        );
    }

    @Test
    public void testBeforePredicate() throws Exception {
        assertThat(
                jql()
                        .created().changed().before("2012/02/02")
                        .build(),
                is("created CHANGED before 2012/02/02")
        );
    }

    @Test
    public void testByPredicate() throws Exception {
        assertThat(
                jql()
                        .status().changed().by("Markus")
                        .build(),
                is("status CHANGED BY Markus")
        );
    }

    @Test
    public void testDuringPredicate() throws Exception {
        assertThat(
                jql()
                        .status().changed().during("2014/12/01", "2014/12/02")
                        .build(),
                is("status CHANGED DURING (2014/12/01, 2014/12/02)")
        );
    }

    @Test
    public void testOnPredicate() throws Exception {
        assertThat(
                jql()
                        .status().wasNot("In Progress").on("2014/12/01")
                        .build(),
                is("status was not In Progress ON 2014/12/01")
        );
    }

    @Test
    public void testFromToPredicate() throws Exception {
        assertThat(
                jql()
                        .status().changed().from("In Progress").to("Closed")
                        .build(),
                is("status CHANGED FROM In Progress TO Closed")
        );
    }
}
