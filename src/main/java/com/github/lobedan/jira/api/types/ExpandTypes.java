package com.github.lobedan.jira.api.types;

/**
 * represents the expand values in jira json
 *
 * for more information see https://docs.atlassian.com/jira/REST/latest/
 *
 * @author svenklemmer
 * @since jira-api-wrapper 0.1.0
 */
public enum ExpandTypes {
  EDITMETA, RENDEREDFIELDS, TRANSITIONS, CHANGELOG, OPERATIONS
}
