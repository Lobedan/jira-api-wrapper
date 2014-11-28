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

import com.github.lobedan.jira.api.domain.Search;
import com.github.lobedan.jira.api.types.SchemeType;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.github.lobedan.jira.api.builder.JiraUrlBuilder.JiraUrl;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

@ContextConfiguration(locations = "classpath:test-app-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class DefaultSearchServiceTest {
  @Autowired
  private DefaultSearchService service;
  private DefaultSearchService spy;

  @Before
  public void setup() throws URISyntaxException {
    service.setBaseUrlBuilder(
        JiraUrl()
            .scheme(SchemeType.HTTP)
            .host("example.com")
            .path("")
            .apiVersion("latest")
            .jql()
            .assignee().equal("hans")
            .and()
            .status().notEqual("Closed")
            .end()
    );
    spy = spy(service);
  }

  @Test
  public void testCanGetIssueByKey() throws Exception {
    assertThat(spy.getBaseUrlBuilder().build().toString(),
               is("http://example.com/rest/api/latest/?jql=assignee%20%3D%20"
                  + "hans%20AND%20status%20%21%3D%20Closed"));

    Search testObject = new Search();
    testObject.setTotal(1);
    doReturn(testObject).when(spy).searchForIssues(service.getBaseUrlBuilder());

    assertThat(testObject, is(notNullValue()));
    assertThat(testObject.getTotal(), is(1));
  }
}
