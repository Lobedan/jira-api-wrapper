package com.github.lobedan.jira.api.domain.dsl.jql;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author svenklemmer
 * @since jira-api-wrapper 0.1.0
 */
public class JQL {
    private static final Logger LOGGER = LogManager.getLogger(JQL.class);

    private StringBuilder sb;

    public JQL() {
        clear();
    }

    public StringBuilder sb() {
        return sb;
    }

    public void add(String string) {
        LOGGER.debug("added string \"" + string + "\" to stringbuilder");
        sb.append(string);
    }

    public void clear() {
        sb = new StringBuilder();
    }

    public String build() {
        return sb.toString();
    }
}
