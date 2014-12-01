package com.github.lobedan.jira.api.domain.dsl.jql;

/**
 * @author svenklemmer
 * @since jira-api-wrapper 0.1.0
 */
public interface JQLOrder {

  JQLKeyword asc();

  JQLKeyword desc();
}
