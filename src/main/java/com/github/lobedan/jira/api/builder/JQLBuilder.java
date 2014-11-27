package com.github.lobedan.jira.api.builder;

import com.github.lobedan.jira.api.domain.builder.JQLMetaHolder;
import com.github.lobedan.jira.api.types.OrderType;
import com.github.lobedan.jira.api.util.StringUtils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author svenklemmer
 * @since jira-api-wrapper 0.1.0
 */
public class JQLBuilder {
    private static final Logger LOGGER = LogManager.getLogger(JQLBuilder.class);

    private JiraUrlBuilder parent;

    public JQLBuilder(JiraUrlBuilder p) {
        parent = p;
    }

    public JQLFieldBuilder or() {
        JQLMetaHolder.getInstance().jql().add(" OR ");
        return new JQLFieldBuilder(parent);
    }

    public JQLFieldBuilder and() {
        JQLMetaHolder.getInstance().jql().add(" AND ");
        return new JQLFieldBuilder(parent);
    }

    public JQLBuilder by(String username) {
        JQLMetaHolder.getInstance().jql().add(" BY " + username);
        return this;
    }

    public JQLBuilder from(Object value) {
        JQLMetaHolder.getInstance().jql().add(" FROM " + StringUtils.stringify(value));
        return this;
    }

    public JQLBuilder to(Object value) {
        JQLMetaHolder.getInstance().jql().add(" TO " + StringUtils.stringify(value));
        return this;
    }

    public JQLBuilder after(Object value) {
        JQLMetaHolder.getInstance().jql().add(" AFTER " + StringUtils.stringify(value));
        return this;
    }

    public JQLBuilder before(Object value) {
        JQLMetaHolder.getInstance().jql().add(" BEFORE " + StringUtils.stringify(value));
        return this;
    }

    public JQLBuilder on(Object value) {
        JQLMetaHolder.getInstance().jql().add(" ON " + StringUtils.stringify(value));
        return this;
    }

    public JQLBuilder during(Object value1, Object value2) {
        JQLMetaHolder.getInstance().jql()
            .add(" DURING (" + StringUtils.stringify(value1) + ", " + StringUtils.stringify(value2) + ")");
        return this;
    }

    public JQLBuilder orderBy(OrderType orderType, Object... values) {
        JQLMetaHolder.getInstance().jql().add(" order by " + StringUtils.commaSeparatedList(values) + " " + orderType);
        return this;
    }

    public JiraUrlBuilder end() {
        parent.addJQL(JQLMetaHolder.getInstance().jql().sb().toString());
        return parent;
    }
}
