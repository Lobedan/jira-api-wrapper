/*
 * This document contains trade secret data which is the property of
 * Ippen Digital GmbH & Co. KG. Information contained herein may not be
 * used, copied or disclosed in whole or part except as permitted by
 * written agreement from Ippen Digital GmbH & Co. KG.
 *
 * Copyright (C) 2007-2008 Ippen Digital GmbH & Co. KG / Munich / Germany
 */
package com.github.lobedan.jira.api.services;

import java.net.URISyntaxException;

import com.github.lobedan.jira.api.domain.Issue;
import com.github.lobedan.jira.api.types.SchemeType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.github.lobedan.jira.api.builder.JiraUrlBuilder.jiraUrl;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

@ContextConfiguration(locations = "classpath:test-app-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class DefaultIssueServiceTest {

  private static final Logger LOGGER = LogManager.getLogger(DefaultIssueServiceTest.class);

  @Autowired
  private DefaultIssueService service;
  private DefaultIssueService spy;

  @Before
  public void setup() throws URISyntaxException {
    spy = spy(service);
    spy.setBaseUrlBuilder(
        jiraUrl()
            .scheme(SchemeType.HTTP)
            .host("example.com")
            .path("")
            .apiVersion("latest")
    );
  }

  @Test
  public void testCanGetIssueByKey() throws Exception {
    assertThat(spy.getBaseUrlBuilder().build().toString(), is("http://example.com/rest/api/latest/"));

    Issue testObject = new Issue();
    testObject.setKey("TEST-1234");
    doReturn(testObject).when(spy).getIssue("TEST-1234");

    assertThat(testObject, is(notNullValue()));
    assertThat(testObject.getKey(), is("TEST-1234"));
  }

}
