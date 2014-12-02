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

import com.github.lobedan.jira.api.domain.jira.Search;
import com.github.lobedan.jira.api.dsl.jiraurl.JiraUrlBuilder;
import com.github.lobedan.jira.api.types.SchemeType;

import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.github.lobedan.jira.api.dsl.jiraurl.JiraUrlBuilder.jiraUrl;
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
  private HttpRestTemplate spy;

  private JiraUrlBuilder jiraUrlBuilder;

  @Before
  public void setup() throws URISyntaxException {
    jiraUrlBuilder = jiraUrl()
        .scheme(SchemeType.HTTP)
        .host("example.com");

    spy = spy(new HttpRestTemplate(new UsernamePasswordCredentials("test", "test")));
    service.setHttpRestTemplate(spy);
    service.setBaseUrlBuilder(jiraUrlBuilder);
  }

  @Test
  public void testCanSearchJiraInstance() throws Exception {
    assertThat(jiraUrlBuilder.build().toString(), is("http://example.com:80//rest/api/latest/"));

    Search testObject = new Search();
    testObject.setTotal(1);
    ResponseEntity<Search> testResponse = new ResponseEntity<Search>(testObject, HttpStatus.OK);

    doReturn(testResponse).when(spy).exchange(jiraUrlBuilder.build().toString() + "/search?jql=",
                                              HttpMethod.GET,
                                              Search.class);

    assertThat(testObject, is(notNullValue()));
    assertThat(testObject.getTotal(), is(1));
  }
}
