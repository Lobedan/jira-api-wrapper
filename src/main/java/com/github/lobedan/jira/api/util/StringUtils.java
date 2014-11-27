package com.github.lobedan.jira.api.util;

import java.util.List;

/**
 * some usefull methods for working with strings
 *
 * @author svenklemmer
 * @since 0.1.0
 */
public class StringUtils {

    /**
     * Converts a object into a string
     *
     * @param value object to convert
     * @return String value of object
     */
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
            } else if (value instanceof Enum) {
                return value.toString().toLowerCase();
            } else {
                return value.toString();
            }
        }
    }

    /**
     * creates a comma separated list out of objects in strings
     *
     * @param values array of objects
     * @return comma separated list
     */
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

    /**
     * creates a comma separated list out of objects in string
     *
     * @param values list of objects
     * @return comma separated list
     */
    public static String commaSeparatedList(List<Object> values) {
        return commaSeparatedList(values.toArray(new Object[values.size()]));
    }

    /**
     * almost the same list #commaSeparatedList but without comma
     *
     * @param values array of objects
     * @return list of separated items
     */
    public static String whiteSpaceSeparatedList(Object... values) {
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

    /**
     * almost the same list #commaSeparatedList but without comma
     *
     * @param values list of objects
     * @return list of separated items
     */
    public static String whiteSpaceSeparatedList(List<Object> values) {
        return whiteSpaceSeparatedList(values.toArray(new Object[values.size()]));
    }
}
