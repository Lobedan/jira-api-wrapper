package com.github.lobedan.jira.api.types;

/**
 * List of common protocols
 *
 * @author svenklemmer
 * @since jira-api-wrapper 0.1.0
 */
public enum SchemeType {
    HTTP("http", 1),
    HTTPS("https", 2),
    FTP("ftp", 4),
    GOPHER("gopher", 8),
    SOCKS("socks", 16),
    JAR("jar", 32),
    JAVASCRIPT("javascript", 64),
    RMI("rmi", 128);

    private String name;
    private int ordinal;

    SchemeType(String aName, int aOrdinal) {
        name = aName;
        ordinal = aOrdinal;
    }

    public String getName() {
        return name;
    }

    public int getOrdinal() {
        return ordinal;
    }

    @Override
    public String toString() {
        return name;
    }
}
