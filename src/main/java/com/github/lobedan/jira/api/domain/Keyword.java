package com.github.lobedan.jira.api.domain;

/**
 * A keyword in JQL is a word or phrase that does (or is) any of the following:
 * <p/>
 * joins two or more clauses together to form a complex JQL query
 * alters the logic of one or more clauses
 * alters the logic of operators
 * has an explicit definition in a JQL query
 * performs a specific function that alters the results of a JQL query.
 *
 * @author svenklemmer
 * @since jira-api-wrapper 0.1.0
 */
public interface Keyword {

  /**
   * Used to combine multiple clauses, allowing you to refine your search.
   * <p/>
   * Note that you can use parentheses to control the order in which clauses are executed.
   */
  Field and();

  /**
   * Used to combine multiple clauses, allowing you to expand your search.
   * <p/>
   * Note that you can use parentheses to control the order in which clauses are executed.
   * <p/>
   * (Note: also see IN, which can be a more convenient way to search for multiple values of a field.)
   */
  Field or();

  /**
   * Used to specify the fields by whose values the search results will be sorted.
   * <p/>
   * By default, the field's own sorting order will be used. You can override this by specifying ascending order ("asc")
   * or descending order ("desc").
   *
   * @see com.github.lobedan.jira.api.types.OrderType
   */
  Order orderBy(String... fieldNames);

  /**
   * returns jql query as string
   */
  String build();

  /**
   * returns jql as object
   */
  JQL andReturn();
}
