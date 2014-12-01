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

public interface SearchService extends JiraService {
  Search searchForIssues(JiraUrlBuilder builder);
}
