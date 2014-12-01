package com.github.lobedan.jira.api.exceptions;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static com.github.lobedan.jira.api.dsl.jql.JQLBuilder.jql;

/**
 * @author svenklemmer
 * @since jira-api-wrapper 0.1.0
 */
@RunWith(JUnit4.class)
public class ParameterCountExceptionTest {

    @Test(expected = ParameterCountException.class)
  public void testIsThrownWhenOnlyOneWhenAtLeastTwoNeeded() throws Exception {
        jql().status().wasIn("Hallo");
    }
}
