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

import com.github.lobedan.jira.api.domain.jira.Fields;
import com.github.lobedan.jira.api.domain.jira.Issue;
import com.github.lobedan.jira.api.dsl.jiraurl.JiraUrlBuilder;
import com.github.lobedan.jira.api.types.SchemeType;

import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
public class DefaultIssueServiceTest {

  private static final Logger LOGGER = LogManager.getLogger(DefaultIssueServiceTest.class);

  @Autowired
  private DefaultIssueService service;
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
  public void testCanGetIssueByKey() throws Exception {
    String testKey = "TEST-1234";

    assertThat(jiraUrlBuilder.build().toString(), is("http://example.com:80//rest/api/latest/"));

    Issue testObject = new Issue();
    testObject.setKey(testKey);

    ResponseEntity<Issue> testResponse = new ResponseEntity<Issue>(testObject, HttpStatus.OK);

    doReturn(testResponse).when(spy).exchange(jiraUrlBuilder.build().toString() + "/issue/" + testKey,
                                            HttpMethod.GET,
                                            Issue.class);

    Issue responseObj = service.getIssue("TEST-1234");

    assertThat(testObject, is(notNullValue()));
    assertThat(testObject, is(responseObj));

    assertThat(responseObj, is(notNullValue()));
    assertThat(responseObj.getKey(), is(testKey));
  }
  @Test
  public void testCanUpdateIssueByKey() throws Exception {
    String testKey = "TEST-1234";
    String[] testLabels = new String[] { "label1", "label2", "label3" };

    assertThat(jiraUrlBuilder.build().toString(), is("http://example.com:80//rest/api/latest/"));

    Issue testObject = new Issue();
    testObject.setKey(testKey);

    Fields fields = new Fields();
    fields.setLabels(testLabels);

    testObject.setFields(fields);

    ResponseEntity<Issue> testResponse = new ResponseEntity<Issue>(testObject, HttpStatus.OK);

    doReturn(testResponse).when(spy).exchange(jiraUrlBuilder.build().toString() + "/issue/" + testKey,
                                            HttpMethod.GET,
                                            Issue.class);

    HttpStatus status = service.updateIssue(testKey, testLabels);

    assertThat(testObject, is(notNullValue()));
    assertThat(status, is(HttpStatus.OK));
  }
}
