package com.github.lobedan.jira.api.util;

import java.util.Arrays;

import com.github.lobedan.jira.api.types.ExpandTypes;
import com.github.lobedan.jira.api.types.ProtocolType;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author svenklemmer
 * @since jira-api-wrapper 0.1.0
 */
@RunWith(JUnit4.class)
public class StringUtilsTest {

  @Test
  public void testCanMakeStringValuesOutOfObjects() throws Exception {
    assertThat(StringUtils.stringify(1), is("1"));
    assertThat(StringUtils.stringify(1L), is("1"));
    assertThat(StringUtils.stringify(1f), is("1.0"));
    assertThat(StringUtils.stringify(1.00), is("1.0"));
    assertThat(StringUtils.stringify(true), is("true"));
    assertThat(StringUtils.stringify(false), is("false"));
    assertThat(StringUtils.stringify(ExpandTypes.CHANGELOG), is("changelog"));
    assertThat(StringUtils.stringify(ExpandTypes.RENDEREDFIELDS), is("renderedfields"));
    assertThat(StringUtils.stringify(ProtocolType.HTTP), is("http"));
  }

  @Test
  public void testCanMakeCommaSeparatedList() throws Exception {
    assertThat(StringUtils.commaSeparatedList("test", "test2", "test3"), is("test,test2,test3"));
    assertThat(StringUtils.commaSeparatedList(Arrays.asList(new Object[]{ "test", "test2", "test3" })), is("test,test2,test3"));
    assertThat(StringUtils.commaSeparatedList(1, 2, 3), is("1,2,3"));
    assertThat(StringUtils.commaSeparatedList(Arrays.asList(new Object[]{ 1, 2, 3 })), is("1,2,3"));
  }

  @Test
  public void testCanMakewhiteSpaceSeparatedList() throws Exception {
    assertThat(StringUtils.whiteSpaceSeparatedList("test", "test2", "test3"), is("test test2 test3"));
    assertThat(StringUtils.whiteSpaceSeparatedList(Arrays.asList(new Object[]{ "test", "test2", "test3" })), is("test test2 test3"));
    assertThat(StringUtils.whiteSpaceSeparatedList(1, 2, 3), is("1 2 3"));
    assertThat(StringUtils.whiteSpaceSeparatedList(Arrays.asList(new Object[]{ 1, 2, 3 })), is("1 2 3"));
  }

}
