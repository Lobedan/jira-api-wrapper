/*
 * This document contains trade secret data which is the property of
 * Ippen Digital GmbH & Co. KG. Information contained herein may not be
 * used, copied or disclosed in whole or part except as permitted by
 * written agreement from Ippen Digital GmbH & Co. KG.
 *
 * Copyright (C) 2007-2008 Ippen Digital GmbH & Co. KG / Munich / Germany
 */
package com.github.lobedan.jira.api.services;

import com.github.lobedan.jira.api.builder.JiraUrlBuilder;
import com.github.lobedan.jira.api.domain.jira.Search;
import com.github.lobedan.jira.api.exceptions.NoIssuesFoundException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DefaultSearchService implements SearchService, HttpRestTemplateAware {
  private static final Logger LOGGER = LogManager.getLogger(DefaultSearchService.class);

  private JiraUrlBuilder baseUrlBuilder;
  private HttpRestTemplate restTemplate;

  @Override
  public JiraUrlBuilder getBaseUrlBuilder() {
    return baseUrlBuilder;
  }

  @Override
  @Autowired
  @Qualifier("jiraBaseUrlBuilder")
  public void setBaseUrlBuilder(JiraUrlBuilder aUrlBuilder) {
    this.baseUrlBuilder = aUrlBuilder;
  }

  @Override
  @Autowired
  @Qualifier("defaultHttpRestTemplate")
  public void setHttpRestTemplate(HttpRestTemplate aHttpRestTemplate) {
    this.restTemplate = aHttpRestTemplate;
  }

  @Override
  public Search searchForIssues(JiraUrlBuilder builder) {
    ResponseEntity<Search> response = restTemplate.exchange(baseUrlBuilder.build().toString(), HttpMethod.GET, Search.class);
    if (response != null) {
      return response.getBody();
    } else {
      throw new NoIssuesFoundException("Could no find any issues");
    }
  }
}
