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

import org.springframework.http.HttpStatus;

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

  /**
   * call this if you have own specified labels given as an String[]
   * it will replace all your self defined labels with new one and leave labels that are
   * inserted by users
   *
   * @param id
   * @param newLabels
   * @param ownLabels
   * @return
   */
  HttpStatus updateIssue(long id, String[] newLabels, String[] ownLabels);

  /**
   * @see #updateIssue(String, String[], String[])
   *
   * @param key
   * @param newLabels
   * @param ownLabels
   * @return
   */
  HttpStatus updateIssue(String key, String[] newLabels, String[] ownLabels);

  HttpStatus updateIssue(long id, String[] newLabels);

  HttpStatus updateIssue(String key, String[] newLabels);
}
