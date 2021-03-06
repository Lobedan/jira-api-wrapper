package com.github.lobedan.jira.api.domain.dsl.jql;

/**
 * @author svenklemmer
 * @since jira-api-wrapper 0.1.0
 */
public interface JQLValue {
  /**
   * Used to search for issues where a given field does not have a value.
   *
   * @see #nul()
   * <p/>
   * Note that EMPTY can only be used with fields that support the IS and IS NOT operators.
   * To see a field's supported operators, check the individual field reference.
   */
  JQLPredicate empty();

  /**
   * Used to search for issues where a given field does not have a value.
   *
   * @see #empty()
   * <p/>
   * Note that NULL can only be used with fields that support the IS and IS NOT operators.
   * To see a field's supported operators, check the individual field reference.
   * <p/>
   * named nul because java does not allow null as method name
   */
  JQLPredicate nul();
}
