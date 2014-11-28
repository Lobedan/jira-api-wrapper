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
import com.github.lobedan.jira.api.domain.Issue;
import com.github.lobedan.jira.api.exceptions.IssueNotFoundException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * Basic CRUD service to work with issues
 *
 * @author Sven Klemmer
 * @since 0.1.0
 */
@Service
public class DefaultIssueService implements IssueService, HttpRestTemplateAware {
  private static final Logger LOGGER = LogManager.getLogger(DefaultIssueService.class);

  private JiraUrlBuilder baseUrlBuilder;
  private HttpRestTemplate restTemplate;

  @Override
  public Issue getIssue(long id) {
    return getIssue(String.valueOf(id));
  }

  @Override
  public Issue getIssue(String key) {
    Assert.notNull(baseUrlBuilder.build());
    ResponseEntity<Issue> response = restTemplate.getForEntity(baseUrlBuilder.build().toString() + "/issue/" + key, Issue.class);
    if (response.getBody() != null) {
      return response.getBody();
    } else {
      throw new IssueNotFoundException("Could not find Issue with key or ID " + key);
    }
  }

  @Override
  public JiraUrlBuilder getJiraUrlBuilder() {
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
}
