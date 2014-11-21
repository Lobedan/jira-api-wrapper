package com.github.lobedan.jira.api.domain;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * a simple date wrapper class which makes it more comfortable to work
 * with the dateformat jira provides
 *
 * inside it will still work with {@link java.util.Date}
 *
 * @author Sven Klemmer
 * @since 0.1.0
 */
public class JiraDate {
    private static final Logger LOGGER = LogManager.getLogger(JiraDate.class);
    private static final String JIRA_DATE_STRING = "yyyy-MM-dd'T'HH:mm:ss.SSSZ"; //2009-05-11T09:45:12.000+0200

    private SimpleDateFormat sdf = new SimpleDateFormat(JIRA_DATE_STRING);

    private Date date;

    public Date getDate() {
        return date;
    }

    public String getDateAsString() {
        return sdf.format(date);
    }

    public void setDate(Date aDate) {
        this.date = aDate;
    }

    public void setDate(String aDate) throws ParseException {
            date = sdf.parse(aDate);
    }
}
