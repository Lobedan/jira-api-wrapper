package com.github.lobedan.jira.api.domain;

import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Created by zambezi on 21.11.14.
 */
@RunWith(JUnit4.class)
public class JiraDateTest {

  private JiraDate date;

  @Before
  public void setup() throws ParseException  {
    date = new JiraDate();
  }

  @Test
  public void testCanFormatJiraDateFormatToDate() throws Exception {

  }

  @Test(expected = ParseException.class)
  public void testCanThrowExceptionOnParseError() throws Exception {
    date.setDate("dummy");
  }
}
