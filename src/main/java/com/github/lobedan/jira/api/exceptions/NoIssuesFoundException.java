/*
 * This document contains trade secret data which is the property of
 * Ippen Digital GmbH & Co. KG. Information contained herein may not be
 * used, copied or disclosed in whole or part except as permitted by
 * written agreement from Ippen Digital GmbH & Co. KG.
 *
 * Copyright (C) 2007-2008 Ippen Digital GmbH & Co. KG / Munich / Germany
 */
package com.github.lobedan.jira.api.exceptions;

public class NoIssuesFoundException extends RuntimeException {
  public NoIssuesFoundException() {
    super();
  }

  public NoIssuesFoundException(String message) {
    super(message);
  }

  public NoIssuesFoundException(String message, Throwable cause) {
    super(message, cause);
  }

  public NoIssuesFoundException(Throwable cause) {
    super(cause);
  }

  protected NoIssuesFoundException(String message,
                                   Throwable cause,
                                   boolean enableSuppression,
                                   boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
