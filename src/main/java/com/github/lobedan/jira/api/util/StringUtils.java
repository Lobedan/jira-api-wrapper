package com.github.lobedan.jira.api.util;

/**
 * some usefull methods for working with strings
 *
 * @author svenklemmer
 * @since 0.1.0
 */
public class StringUtils {

  public static String stringify(Object value) {
    if (value instanceof Boolean) {
      boolean b = (Boolean) value;
      return (b) ? "true" : "false";
    } else {
      if (value instanceof Integer) {
        return "" + (Integer) value;
      } else if (value instanceof Double) {
        return "" + (Double) value;
      } else if (value instanceof Long) {
        return "" + (Long) value;
      } else if (value instanceof Float) {
        return "" + (Float) value;
      } else {
        return (String) value;
      }
    }
  }

  public static String commaSeparatedList(Object... values) {
    String toAdd = "";
    for (int i = 0; i < values.length; i++) {
      if (i == values.length - 1) {
        toAdd += stringify(values[i]);
      } else {
        toAdd += stringify(values[i]) + ",";
      }
    }
    return toAdd;
  }

  public static String whitespaceSeparatedList(Object... values) {
    String toAdd = "";
    for (int i = 0; i < values.length; i++) {
      if (i == values.length - 1) {
        toAdd += stringify(values[i]);
      } else {
        toAdd += stringify(values[i]) + " ";
      }
    }
    return toAdd;
  }
}
