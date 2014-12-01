package com.github.lobedan.jira.api.domain.jira;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * a simple date wrapper class which makes it more comfortable to work
 * with the dateformat jira provides
 * <p/>
 * inside it will still work with {@link java.util.Date}
 *
 * @author Sven Klemmer
 * @since 0.1.0
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class JiraDate {
  private static final Logger LOGGER = LogManager.getLogger(JiraDate.class);
  private static final String JIRA_DATE_STRING = "yyyy-MM-dd'T'HH:mm:ss.SSSZ"; //2009-05-11T09:45:12.000+0200

  private SimpleDateFormat sdf = new SimpleDateFormat(JIRA_DATE_STRING);

  private Date date;

  public JiraDate() { }

  public JiraDate(String aDate) throws ParseException {
    setDate(aDate);
  }

  public Date getDate() {
    return date;
  }

  public void setDate(String aDate) throws ParseException {
    date = sdf.parse(aDate);
  }

  public void setJavaDate(Date aDate) {
    this.date = aDate;
  }

  public String getDateAsString() {
    return sdf.format(date);
  }
}
