package com.github.lobedan.jira.api.dsl.jql;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
public class JQLBuilderTest {
  private static final Logger LOGGER = LogManager.getLogger(JQLBuilderTest.class);

  @Test
  public void testCanCreateJQLFieldBuilderInstance() throws Exception {
    assertThat(jql(), is(instanceOf(JQLFieldBuilder.class)));
  }
}
