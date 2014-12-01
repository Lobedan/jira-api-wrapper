package com.github.lobedan.jira.api.domain.dsl.jql;

/**
 * A field in JQL is a word that represents a JIRA field (or a custom field that has already been defined in JIRA).
 * In a clause, a field is followed by an operator, which in turn is followed by one or more values (or functions).
 * The operator compares the value of the field with one or more values or functions on the right, such that only true
 * results are retrieved by the clause.
 *
 * @author svenklemmer
 * @since jira-api-wrapper 0.1.0
 */
public interface JQLField {

  /**
   * Search for issues that are assigned to a particular Affects Version(s). You can search by version name or version ID
   * (i.e. the number that JIRA automatically allocates to a version).
   * <p/>
   * NOTE: It is safer to search by version ID than by version name.
   * Different projects may have versions with the same name, so searching by version name may return issues from multiple projects.
   * It is also possible for your JIRA administrator to change the name of a version, which could break any saved filters
   * which rely on that name. Version IDs, however, are unique and cannot be changed.
   * <p/>
   * <p/>
   * Supported Operators
   *
   * @see JQLOperator#equal(Object)  =
   * @see JQLOperator#notEquals(Object) !=
   * @see JQLOperator#greaterThan(Object) () >
   * @see JQLOperator#greaterThanEquals(Object) () >=
   * @see JQLOperator#lessThan(Object) ()  <
   * @see JQLOperator#lessThanEquals(Object) () <=
   * @see JQLOperator#is() IS
   * @see JQLOperator#isNot() IS NOT
   * @see JQLOperator#in(Object...) () IN
   * @see JQLOperator#notIn(Object...) () NOT IN
   * <p/>
   * Note that the comparison operators (e.g. ">") use the version order that has been set up by your project administrator, not a numeric or alphabetic order.
   */
  JQLOperator affectedVersion();

  /**
   * Search for issues that are assigned to a particular user. You can search by the user's Full Name, ID or Email Address.
   * <p/>
   * <p/>
   * Supported Operators
   *
   * @see JQLOperator#equal(Object) =
   * @see JQLOperator#notEquals(Object) !=
   * @see JQLOperator#was(Object) () WAS
   * @see JQLOperator#wasIn(Object...) () WAS IN
   * @see JQLOperator#wasNot(Object) () WAS NOT
   * @see JQLOperator#wasNotIn(Object...) () WAS NOT IN
   * @see JQLOperator#changed() CHANGED
   */
  JQLOperator assignee();

  /**
   * Search for issues which have or do not have attachments. You can only use the EMPTY or IS NOT EMPTY operators for this field.
   *
   * @see JQLValue#empty()
   * @see JQLOperator#not()
   * <p/>
   * <p/>
   * Supported Operators
   * @see JQLOperator#is()
   * @see JQLOperator#isNot()
   */
  JQLOperator attachments();

  /**
   * Search for issues that belong to projects in a particular Category.
   * <p/>
   * Supported Operators
   *
   * @see JQLOperator#equal(Object) =
   * @see JQLOperator#notEquals(Object) !=
   * @see JQLOperator#is() IS
   * @see JQLOperator#isNot() IS NOT
   * @see JQLOperator#in(Object...) () IN
   * @see JQLOperator#notIn(Object...) () NOT IN
   */
  JQLOperator category();

  /**
   * Search for issues that have a Comment which contains particular text.
   * <p/>
   * <p/>
   * Supported Operators
   *
   * @see JQLOperator#contains(String) ()
   * @see JQLOperator#doesNotContain(String) ()
   */
  JQLOperator comment();

  /**
   * Search for issues that belong to a particular component(s) of a project. You can search by component name or
   * component ID (i.e. the number that JIRA automatically allocates to a component).
   * <p/>
   * Note: It is safer to search by component ID than by component name
   * Different projects may have components with the same name, so searching by component name may return issues from
   * multiple projects. It is also possible for your JIRA administrator to change the name of a component, which could
   * break any saved filters which rely on that name. Component IDs, however, are unique and cannot be changed.
   * <p/>
   * <p/>
   * Supported Operators
   *
   * @see JQLOperator#equal(Object) =
   * @see JQLOperator#notEquals(Object) !=
   * @see JQLOperator#is() IS
   * @see JQLOperator#isNot() IS NOT
   * @see JQLOperator#in(Object...) IN
   * @see JQLOperator#notIn(Object...) NOT IN
   */
  JQLOperator component();

  /**
   * Search for issues that were created on, before or after a particular date (or date range). Note that if a
   * time-component is not specified, midnight will be assumed. Please note that the search results will be relative
   * to your configured time zone (which is by default the JIRA server's time zone).
   * <p/>
   * Use one of the following formats:
   * "yyyy/MM/dd HH:mm"
   * "yyyy-MM-dd HH:mm"
   * "yyyy/MM/dd"
   * "yyyy-MM-dd"
   * <p/>
   * Or use "w" (weeks), "d" (days), "h" (hours) or "m" (minutes) to specify a date relative to the current time. The
   * default is "m" (minutes). Be sure to use quote-marks ("); if you omit the quote-marks, the number you supply will be
   * interpreted as milliseconds after epoch (1970-1-1).
   * <p/>
   * Aternative you can use
   *
   * @see #createdDate()
   * <p/>
   * Supported Operators
   * @see JQLOperator#equal(Object) =
   * @see JQLOperator#notEquals(Object) !=
   * @see JQLOperator#greaterThan(Object) >
   * @see JQLOperator#greaterThanEquals(Object) () >=
   * @see JQLOperator#lessThan(Object) ()  <
   * @see JQLOperator#lessThanEquals(Object) () <=
   * @see JQLOperator#is() IS
   * @see JQLOperator#isNot() IS NOT
   * @see JQLOperator#in(Object...) () IN
   * @see JQLOperator#notIn(Object...) () NOT IN
   */
  JQLOperator created();

  /**
   * @see #created()
   */
  JQLOperator createdDate();

  /**
   *
   */
  JQLOperator creator();

  /**
   *
   */
  JQLOperator customField(String fieldName);

  /**
   *
   */
  JQLOperator description();

  /**
   *
   */
  JQLOperator due();

  /**
   * @see #due()
   */
  JQLOperator dueDate();

  /**
   *
   */
  JQLOperator environment();

  /**
   *
   */
  JQLOperator epicLink();

  /**
   *
   */
  JQLOperator filter();

  /**
   *
   */
  JQLOperator fixVersion();

  /**
   * @see #issueKey()
   */
  JQLOperator id();

  /**
   * @see #issueKey()
   */
  JQLOperator issue();

  /**
   *
   */
  JQLOperator issueKey();

  /**
   * @see #issueKey()
   */
  JQLOperator key();

  /**
   *
   */
  JQLOperator lastViewed();

  /**
   *
   */
  JQLOperator level();

  /**
   *
   */
  JQLOperator originalEstimate();

  /**
   *
   */
  JQLOperator parent();

  /**
   *
   */
  JQLOperator priority();

  /**
   *
   */
  JQLOperator project();

  /**
   *
   */
  JQLOperator remainingEstimate();

  /**
   *
   */
  JQLOperator reporter();

  /**
   * @see #filter()
   */
  JQLOperator request();

  /**
   * @see #resolved()
   */
  JQLOperator resolutionDate();

  /**
   *
   */
  JQLOperator resolution();

  /**
   *
   */
  JQLOperator resolved();

  /**
   * @see #filter()
   */
  JQLOperator savedFilter();

  /**
   * @see #filter()
   */
  JQLOperator searchRequest();

  /**
   *
   */
  JQLOperator sprint();

  /**
   *
   */
  JQLOperator status();

  /**
   *
   */
  JQLOperator summary();

  /**
   *
   */
  JQLOperator text();

  /**
   * @see #remainingEstimate()
   */
  JQLOperator timeEstimate();

  /**
   *
   */
  JQLOperator timeOriginalEstimate();

  /**
   *
   */
  JQLOperator type();

  /**
   *
   */
  JQLOperator timeSpent();

  /**
   *
   */
  JQLOperator updated();

  /**
   *
   */
  JQLOperator voter();

  /**
   *
   */
  JQLOperator votes();

  /**
   *
   */
  JQLOperator watcher();

  /**
   *
   */
  JQLOperator watchers();

  /**
   *
   */
  JQLOperator workRatio();
}