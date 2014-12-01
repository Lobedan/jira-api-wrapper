package com.github.lobedan.jira.api.domain;

import java.util.Date;

/**
 * @author svenklemmer
 * @since jira-api-wrapper 0.1.0
 */
public interface Predicate extends Keyword {

  Predicate after(String date);

  Predicate after(Date aDate);

  Predicate before(String date);

  Predicate before(Date aDate);

  Predicate by(String username);

  Predicate during(String date1, String date2);

  Predicate during(Date date1, Date date2);

  Predicate on(String date);

  Predicate on(Date aDate);

  Predicate from(Object value);

  Predicate to(Object value);
}