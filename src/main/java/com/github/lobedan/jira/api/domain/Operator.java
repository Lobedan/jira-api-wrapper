package com.github.lobedan.jira.api.domain;

/**
 * An operator in JQL is one or more symbols or words which compares the value of a field on its left with one or more
 * values (or functions) on its right, such that only true results are retrieved by the clause.
 * Some operators may use the NOT keyword.
 *
 * @author svenklemmer
 * @since jira-api-wrapper 0.1.0
 */
public interface Operator {

  /**
   * The "=" operator is used to search for issues where the value of the specified field exactly matches the specified value.
   * (Note: cannot be used with text fields; see the CONTAINS operator instead.)
   *
   * @see #contains(String)
   * <p/>
   * To find issues where the value of a specified field exactly matches multiple values, use multiple "=" statements with the AND operator.
   * @see com.github.lobedan.jira.api.domain.Keyword#and()
   */
  Keyword equal(Object value);

  /**
   * The "!=" operator is used to search for issues where the value of the specified field does not match the specified value.
   * (Note: cannot be used with text fields; see the DOES NOT CONTAIN ("!~") operator instead.)
   *
   * @see #doesNotContain(String)
   * Note that typing field != value is the same as typing NOT field = value, and that field != EMPTY is the same as field IS_NOT EMPTY.
   * <p/>
   * The "!=" operator will not match a field that has no value (i.e. a field that is empty). For example, component != fred
   * will only match issues that have a component and the component is not "fred". To find issues that have a component other
   * than "fred" or have no component, you would need to type: component != fred or component is empty.
   */
  Keyword notEquals(Object value);

  /**
   * The ">" operator is used to search for issues where the value of the specified field is greater than the
   * specified value. Cannot be used with text fields.
   * <p/>
   * Note that the ">" operator can only be used with fields which support ordering (e.g. date fields and version fields).
   * To see a field's supported operators, check the individual field reference.
   *
   * @see com.github.lobedan.jira.api.domain.Field
   */
  Keyword greaterThan(Object value);

  /**
   * The ">=" operator is used to search for issues where the value of the specified field is greater than or equal to the
   * specified value. Cannot be used with text fields.
   * <p/>
   * Note that the ">=" operator can only be used with fields which support ordering (e.g. date fields and version fields).
   * To see a field's supported operators, check the individual field reference.
   *
   * @see com.github.lobedan.jira.api.domain.Field
   */
  Keyword greaterThanEquals(Object value);

  /**
   * The "<" operator is used to search for issues where the value of the specified field is less than the specified value.
   * Cannot be used with text fields.
   * <p/>
   * Note that the "<" operator can only be used with fields which support ordering (e.g. date fields and version fields).
   * To see a field's supported operators, check the individual field reference.
   *
   * @see com.github.lobedan.jira.api.domain.Field
   */
  Keyword lessThan(Object value);

  /**
   * The "<=" operator is used to search for issues where the value of the specified field is less than or equal to than
   * the specified value. Cannot be used with text fields.
   * <p/>
   * Note that the "<=" operator can only be used with fields which support ordering (e.g. date fields and version fields).
   * To see a field's supported operators, check the individual field reference.
   *
   * @see com.github.lobedan.jira.api.domain.Field
   */
  Keyword lessThanEquals(Object value);

  /**
   * The "IN" operator is used to search for issues where the value of the specified field is one of multiple specified values.
   * The values are specified as a comma-delimited list, surrounded by parentheses.
   * <p/>
   * Using "IN" is equivalent to using multiple EQUALS (=) statements, but is shorter and more convenient. That is,
   * typing reporter IN (tom, jane, harry) is the same as typing reporter = "tom" OR reporter = "jane" OR reporter = "harry".
   */
  Keyword in(Object... values);

  /**
   * The "NOT IN" operator is used to search for issues where the value of the specified field is not one of multiple specified values.
   * <p/>
   * Using "NOT IN" is equivalent to using multiple NOT_EQUALS (!=) statements, but is shorter and more convenient. That is,
   * typing reporter NOT IN (tom, jane, harry) is the same as typing reporter != "tom" AND reporter != "jane" AND reporter != "harry".
   * <p/>
   * The "NOT IN" operator will not match a field that has no value (i.e. a field that is empty). For example, assignee not in
   * (jack,jill) will only match issues that have an assignee and the assignee is not "jack" or "jill". To find issues that are
   * assigned to someone other than "jack" or "jill" or are unassigned, you would need to type: assignee not in (jack,jill) or assignee is empty.
   */
  Keyword notIn(Object... values);

  /**
   * The "~" operator is used to search for issues where the value of the specified field matches the specified value
   * (either an exact match or a "fuzzy" match — see examples below). For use with text fields only, i.e.:
   *
   * @see Field#summary()
   * @see Field#description()
   * @see Field#environment()
   * @see Field#comment()
   * <p/>
   * Note: Custom fields are not supported yet
   */
  Keyword contains(String value);

  /**
   * The "!~" operator is used to search for issues where the value of the specified field is not a "fuzzy" match for
   * the specified value. For use with text fields only, i.e.:
   *
   * @see Field#summary()
   * @see Field#description()
   * @see Field#environment()
   * @see Field#comment()
   * <p/>
   * Note: Custom fields are not supported yet
   */
  Keyword doesNotContain(String value);

  /**
   * The "IS" operator can only be used with EMPTY or NULL. That is, it is used to search for issues where the specified
   * field has no value.
   * <p/>
   * Note that not all fields are compatible with this operator; see the individual field reference for details.
   *
   * @see com.github.lobedan.jira.api.domain.Field
   */
  Value is();

  /**
   * The "IS NOT" operator can only be used with EMPTY or NULL. That is, it is used to search for issues where the
   * specified field has a value.
   * <p/>
   * Note that not all fields are compatible with this operator; see the individual field reference for details.
   *
   * @see com.github.lobedan.jira.api.domain.Field
   */
  Value isNot();

  /**
   * The "WAS" operator is used to find issues that currently have, or previously had, the specified value for the specified field.
   * <p/>
   * This operator has the following optional predicates:
   * AFTER "date"
   * BEFORE "date"
   * BY "username"
   * DURING ("date1","date2")
   * ON "date"
   * This operator will match the value name (e.g. "Resolved"), which was configured in your system at the time that the
   * field was changed. This operator will also match the value ID associated with that value name too — that is,
   * it will match "4" as well as "Resolved".
   * (Note: This operator can be used with the Assignee, Fix Version, Priority,  Reporter, Resolution and Status fields only.)
   */
  Predicate was(Object value);

  /**
   * The "WAS IN" operator is used to find issues that currently have, or previously had, any of multiple specified values
   * for the specified field. The values are specified as a comma-delimited list, surrounded by parentheses.
   * <p/>
   * Using "WAS IN" is equivalent to using multiple WAS statements, but is shorter and more convenient. That is, typing
   * status WAS IN ('Resolved', 'Closed') is the same as typing status WAS "Resolved" OR status WAS "Closed".
   * <p/>
   * This operator has the following optional predicates:
   * AFTER "date"
   * BEFORE "date"
   * BY "username"
   * DURING ("date1","date2")
   * ON "date"
   * This operator will match the value name (e.g. "Resolved"), which was configured in your system at the time that the
   * field was changed. This operator will also match the value ID associated with that value name too — that is, it will
   * match "4" as well as "Resolved".
   * (Note: This operator can be used with the Assignee, Fix Version, Priority,  Reporter, Resolution and Status fields only.)
   */
  Predicate wasIn(Object... values);

  /**
   * The "WAS NOT IN" operator is used to search for issues where the value of the specified field has never been one of
   * multiple specified values.
   * <p/>
   * Using "WAS NOT IN" is equivalent to using multiple WAS_NOT statements, but is shorter and more convenient.
   * That is, typing status WAS NOT IN ("Resolved","In Progress") is the same as typing status WAS NOT "Resolved" AND
   * status WAS NOT "In Progress".
   * <p/>
   * This operator has the following optional predicates:
   * AFTER "date"
   * BEFORE "date"
   * BY "username"
   * DURING ("date1","date2")
   * ON "date"
   * This operator will match the value name (e.g. "Resolved"), which was configured in your system at the time that the
   * field was changed. This operator will also match the value ID associated with that value name too — that is, it
   * will match "4" as well as "Resolved".
   * (Note: This operator can be used with the Assignee, Fix Version, Priority,  Reporter, Resolution and Status fields only.)
   */
  Predicate wasNotIn(Object... values);

  /**
   * The "WAS NOT" operator is used to find issues that have never had the specified value for the specified field.
   * <p/>
   * This operator has the following optional predicates:
   * AFTER "date"
   * BEFORE "date"
   * BY "username"
   * DURING ("date1","date2")
   * ON "date"
   * This operator will match the value name (e.g. "Resolved"), which was configured in your system at the time that
   * the field was changed. This operator will also match the value ID associated with that value name too — that is,
   * it will match "4" as well as "Resolved".
   * (Note: This operator can be used with the Assignee, Fix Version, Priority,  Reporter, Resolution and Status fields only.)
   */
  Predicate wasNot(Object value);

  /**
   * The "CHANGED" operator is used to find issues that have a value which had changed for the specified field.
   * <p/>
   * This operator has the following optional predicates:
   * <p/>
   * AFTER "date"
   * BEFORE "date"
   * BY "username"
   * DURING ("date1","date2")
   * ON "date"
   * FROM "oldvalue"
   * TO "newvalue"
   * (Note: This operator can be used with the Assignee, Fix Version, Priority, Reporter, Resolution and Status fields only.)
   */
  Predicate changed();

  /**
   * Used to negate individual clauses or a complex JQL query (a query made up of more than one clause)
   * using parentheses, allowing you to refine your search
   * <p/>
   * (Note: also see NOT EQUALS ("!="), DOES NOT CONTAIN ("!~"), NOT IN and IS NOT.)
   */
  Operator not();
}
