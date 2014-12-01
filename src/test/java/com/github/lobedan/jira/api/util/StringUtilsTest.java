package com.github.lobedan.jira.api.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import com.github.lobedan.jira.api.types.ExpandType;
import com.github.lobedan.jira.api.types.SchemeType;

import org.junit.Ignore;
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
    assertThat(StringUtils.stringify(ExpandType.CHANGELOG), is("changelog"));
    assertThat(StringUtils.stringify(ExpandType.RENDEREDFIELDS), is("renderedfields"));
    assertThat(StringUtils.stringify(SchemeType.HTTP.getName()), is("http"));
    assertThat(StringUtils.stringify(SchemeType.HTTP.getOrdinal()), is("1"));
  }

  @Test
  public void testCanMakeCommaSeparatedList() throws Exception {
    assertThat(StringUtils.commaSeparatedList("test", "test2", "test3"), is("test,test2,test3"));
    assertThat(StringUtils.commaSeparatedList(Arrays.asList(new Object[]{ "test", "test2", "test3" })),
               is("test,test2,test3"));
    assertThat(StringUtils.commaSeparatedList(1, 2, 3), is("1,2,3"));
    assertThat(StringUtils.commaSeparatedList(Arrays.asList(new Object[]{ 1, 2, 3 })), is("1,2,3"));
  }

  @Test
  public void testCanMakewhiteSpaceSeparatedList() throws Exception {
    assertThat(StringUtils.whiteSpaceSeparatedList("test", "test2", "test3"), is("test test2 test3"));
    assertThat(StringUtils.whiteSpaceSeparatedList(Arrays.asList(new Object[]{ "test", "test2", "test3" })),
               is("test test2 test3"));
    assertThat(StringUtils.whiteSpaceSeparatedList(1, 2, 3), is("1 2 3"));
    assertThat(StringUtils.whiteSpaceSeparatedList(Arrays.asList(new Object[]{ 1, 2, 3 })), is("1 2 3"));
  }

  @Ignore
  public void testCanConvertStringDateToDateObject() throws Exception {
    Date date = new Date();
    String stringDate = new SimpleDateFormat("yyyy-mm-dd").format(date);

    assertThat(StringUtils.stringToDate(stringDate), is(date));
  }

  @Test(expected = ParseException.class)
  public void testCanNOTConvertStringDateToDateObject() throws Exception {
    StringUtils.stringToDate("23");
  }
}
