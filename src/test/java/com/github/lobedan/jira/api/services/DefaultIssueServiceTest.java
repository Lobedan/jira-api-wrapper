/*
 * This document contains trade secret data which is the property of
 * Ippen Digital GmbH & Co. KG. Information contained herein may not be
 * used, copied or disclosed in whole or part except as permitted by
 * written agreement from Ippen Digital GmbH & Co. KG.
 *
 * Copyright (C) 2007-2008 Ippen Digital GmbH & Co. KG / Munich / Germany
 */
package com.github.lobedan.jira.api.services;

import com.github.lobedan.jira.api.domain.jira.Issue;
import com.github.lobedan.jira.api.dsl.jiraurl.JiraUrlBuilder;
import com.github.lobedan.jira.api.exceptions.IssueNotFoundException;
import com.github.lobedan.jira.api.types.SchemeType;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.client.MockRestServiceServer;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.Arrays;

import static com.github.lobedan.jira.api.dsl.jiraurl.JiraUrlBuilder.jiraUrl;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@ContextConfiguration(locations = "classpath:test-app-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class DefaultIssueServiceTest {

  private static final Logger LOGGER = LogManager.getLogger(DefaultIssueServiceTest.class);

  @Autowired
  private DefaultIssueService service;
    private HttpRestTemplate httpRestTemplate;
  private JiraUrlBuilder jiraUrlBuilder;

    private String testKey = "TEST-1234";

  @Before
  public void setup() throws URISyntaxException {
    jiraUrlBuilder = jiraUrl()
        .scheme(SchemeType.HTTP)
        .host("example.com");

      httpRestTemplate = new HttpRestTemplate();
      httpRestTemplate.setCredentials(new UsernamePasswordCredentials("test", "test"));

      service.setHttpRestTemplate(httpRestTemplate);
    service.setBaseUrlBuilder(jiraUrlBuilder);
  }

  @Test
  public void testCanGetIssueByKey() throws Exception {
      String getIssueJson = getFileContent("responses/get-issue.json");
      assertThat(jiraUrlBuilder.build().toString(), is("http://example.com/rest/api/latest"));

      mockServer()
              .expect(requestTo(jiraUrlBuilder.build().toString() + "/issue/" + testKey))
              .andExpect(method(HttpMethod.GET))
              .andRespond(withSuccess(getIssueJson, MediaType.APPLICATION_JSON));

      Issue responseObj = service.getIssue(testKey);

    assertThat(responseObj, is(notNullValue()));
      assertThat(responseObj.getKey(), is(equalTo(testKey)));
  }

    @Test(expected = IssueNotFoundException.class)
    public void testCanThrowExceptionIfIssueNotFound() throws Exception {
        mockServer()
                .expect(requestTo(jiraUrlBuilder.build().toString() + "/issue/" + testKey))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess("{\n" +
                        "  \"errorMessages\": [\n" +
                        "    \"DER VORGANG EXISTIERT NICHT.\"\n" +
                        "  ],\n" +
                        "  \"errors\": {}\n" +
                        "}", MediaType.APPLICATION_JSON));

        service.getIssue(testKey);
    }

  @Test
  public void testCanUpdateIssueByKey() throws Exception {
    String[] testLabels = new String[] { "label1", "label2", "label3" };
      String getIssueJson = getFileContent("responses/get-issue.json");
      String updateIssueJsonWithoutCustom = getFileContent("responses/update-issue.json");
      assertThat(jiraUrlBuilder.build().toString(), is("http://example.com/rest/api/latest"));

      mockServer()
              .expect(requestTo(jiraUrlBuilder.build().toString() + "/issue/" + testKey))
              .andExpect(method(HttpMethod.GET))
              .andRespond(withSuccess(getIssueJson, MediaType.APPLICATION_JSON));
      Issue responseObj = service.getIssue(testKey);

      assertThat(responseObj, is(notNullValue()));
      assertThat(responseObj.getKey(), is(equalTo(testKey)));
      assertThat(Arrays.asList(responseObj.getFields().getLabels()), hasItem("label1"));

      mockServer()
              .expect(requestTo(jiraUrlBuilder.build().toString() + "/issue/" + testKey))
              .andExpect(method(HttpMethod.PUT))
              .andRespond(withSuccess(updateIssueJsonWithoutCustom, MediaType.APPLICATION_JSON));
    HttpStatus status = service.updateIssue(testKey, testLabels);
    assertThat(status, is(HttpStatus.OK));

      mockServer()
              .expect(requestTo(jiraUrlBuilder.build().toString() + "/issue/" + testKey))
              .andExpect(method(HttpMethod.GET))
              .andRespond(withSuccess(updateIssueJsonWithoutCustom, MediaType.APPLICATION_JSON));
      responseObj = service.getIssue(testKey);
      assertThat(responseObj.getKey(), is(equalTo(testKey)));
      assertThat(Arrays.asList(responseObj.getFields().getLabels()).containsAll(Arrays.asList(testLabels)), is(true));
  }

    @Ignore
    public void testCanUpdateIssueByKeyWithCustomTags() throws Exception {
        String[] testLabels = new String[]{"label1", "label2", "label3"};
        String getIssueJson = getFileContent("responses/get-issue.json");
        String updateIssueJsonWithCustom = getFileContent("responses/update-issue-custom.json");

        assertThat(jiraUrlBuilder.build().toString(), is("http://example.com/rest/api/latest"));

        mockServer()
                .expect(requestTo(jiraUrlBuilder.build().toString() + "/issue/" + testKey))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(getIssueJson, MediaType.APPLICATION_JSON));

        Issue responseObj = service.getIssue(testKey);

        assertThat(responseObj, is(notNullValue()));
        assertThat(responseObj.getKey(), is(equalTo(testKey)));
        assertThat(Arrays.asList(responseObj.getFields().getLabels()), hasItem("label1"));

        mockServer()
                .expect(requestTo(jiraUrlBuilder.build().toString() + "/issue/" + testKey))
                .andExpect(method(HttpMethod.PUT))
                .andRespond(withSuccess(updateIssueJsonWithCustom, MediaType.APPLICATION_JSON));

        HttpStatus status = service.updateIssue(testKey, testLabels);
        assertThat(status, is(HttpStatus.OK));

        mockServer()
                .expect(requestTo(jiraUrlBuilder.build().toString() + "/issue/" + testKey))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(getIssueJson, MediaType.APPLICATION_JSON));
        responseObj = service.getIssue(testKey);
        assertThat(responseObj.getKey(), is(equalTo(testKey)));
        assertThat(Arrays.asList(responseObj.getFields().getLabels()), hasItems(testLabels));
        assertThat(Arrays.asList(responseObj.getFields().getLabels()), hasItem("CUSTOM"));
    }

    @Ignore
//    @Test(expected = NoUpdateIssueException.class)
    public void testThrowsExceptionIfCouldNotUpdate() {
        mockServer()
                .expect(requestTo(jiraUrlBuilder.build().toString() + "/issue/" + testKey))
                .andExpect(method(HttpMethod.PUT))
                .andRespond(withSuccess());

        service.updateIssue(testKey, new String[]{"label"});
    }

    @Ignore
    private MockRestServiceServer mockServer() {
        return MockRestServiceServer.createServer(httpRestTemplate);
    }

    @Ignore
    private String getFileContent(String filename) throws Exception {
        InputStream in = new ClassPathResource(filename).getInputStream();
        char[] tmp = new char[4096];
        StringBuilder b = new StringBuilder();
        try (InputStreamReader reader = new InputStreamReader(in, Charset.forName("utf-8"))) {
            while (true) {
                int len = reader.read(tmp);
                if (len < 0) {
                    break;
                }
                b.append(tmp, 0, len);
            }
            reader.close();
        }
        in.close();
        return b.toString();
    }
}
