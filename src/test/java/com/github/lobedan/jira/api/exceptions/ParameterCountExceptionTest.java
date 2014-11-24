package com.github.lobedan.jira.api.exceptions;

import com.github.lobedan.jira.api.builder.JQLBuilder;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * @author svenklemmer
 * @since jira-api-wrapper 0.1.0
 */
@RunWith(JUnit4.class)
public class ParameterCountExceptionTest {

  @Test(expected = ParameterCountException.class)
  public void testIsThrownWhenOnlyOneWhenAtLeastTwoNeeded() throws Exception {
    new JQLBuilder()
        .field("person").wasIn("test").build();
  }
}
