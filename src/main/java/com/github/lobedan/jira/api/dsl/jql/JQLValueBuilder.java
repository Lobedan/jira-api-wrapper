package com.github.lobedan.jira.api.dsl.jql;

import com.github.lobedan.jira.api.domain.dsl.jql.JQL;
import com.github.lobedan.jira.api.domain.dsl.jql.JQLPredicate;
import com.github.lobedan.jira.api.domain.dsl.jql.JQLValue;

/**
 * @author svenklemmer
 * @since jira-api-wrapper 0.1.0
 */
public class JQLValueBuilder implements JQLValue {
    private JQL jql;

    public JQLValueBuilder(JQL aJQL) {
        jql = aJQL;
    }

    private JQLPredicate addValueToPredicate(String field) {
        jql.add(field);
        return new JQLPredicateBuilder(jql);
    }

    @Override
    public JQLPredicate empty() {
        return addValueToPredicate(" empty");
    }

    @Override
    public JQLPredicate nul() {
        return addValueToPredicate(" null");
    }
}
