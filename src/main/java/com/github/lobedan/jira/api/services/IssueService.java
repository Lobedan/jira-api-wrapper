/*
 * This document contains trade secret data which is the property of
 * Ippen Digital GmbH & Co. KG. Information contained herein may not be
 * used, copied or disclosed in whole or part except as permitted by
 * written agreement from Ippen Digital GmbH & Co. KG.
 *
 * Copyright (C) 2007-2008 Ippen Digital GmbH & Co. KG / Munich / Germany
 */
package com.github.lobedan.jira.api.services;

import com.github.lobedan.jira.api.domain.Issue;

/**
 * interface which provides basic methods for working with issues
 *
 * @author Sven Klemmer
 * @since 0.1.0
 */
public interface IssueService extends JiraService {

  /**
   * Get a issue by its id
   *
   * @param id of issue
   * @return issue object of json response
   */
  Issue getIssue(long id);

  /**
   * get a issue by its key, most recommend method
   *
   * @param key of issue
   * @return issue object of json response
   */
  Issue getIssue(String key);
}
