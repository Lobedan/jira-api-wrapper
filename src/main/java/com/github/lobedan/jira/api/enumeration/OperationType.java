package com.github.lobedan.jira.api.enumeration;

/**
 * generalisation of different operations like !=, IS, IS NOT, >, >=, <, <=
 * for more information refer to the official documentation at
 * https://confluence.atlassian.com/display/JIRA/Advanced+Searching#AdvancedSearching-operatoroperatorsOperatorsReference
 *
 * @author svenklemmer
 * @since 0.1.0
 */
public enum OperationType {
  EQUALS,
  NOT_EQUALS,

  GREATER_THAN,
  GREATER_THAN_EQUALs,

  LESS_THAN,
  LESS_THAN_EQUAL,

  IN,
  NOT_IN,

  CONTAINS,
  DOES_NOT_CONTAIN,

  IS,
  IS_NOT,

  WAS,
  WAS_IN,
  WAS_NOT_IN,
  WAS_NOT,

  CHANGED
}
