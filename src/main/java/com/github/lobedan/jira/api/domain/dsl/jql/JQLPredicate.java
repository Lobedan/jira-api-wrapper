package com.github.lobedan.jira.api.domain.dsl.jql;

import java.util.Date;

/**
 * @author svenklemmer
 * @since jira-api-wrapper 0.1.0
 */
public interface JQLPredicate extends JQLKeyword {

    JQLPredicate after(String date);

    JQLPredicate after(Date aDate);

    JQLPredicate before(String date);

    JQLPredicate before(Date aDate);

    JQLPredicate by(String username);

    JQLPredicate during(String date1, String date2);

    JQLPredicate during(Date date1, Date date2);

    JQLPredicate on(String date);

    JQLPredicate on(Date aDate);

    JQLPredicate from(Object value);

    JQLPredicate to(Object value);
}