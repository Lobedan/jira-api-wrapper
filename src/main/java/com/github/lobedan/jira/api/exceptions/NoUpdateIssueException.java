package com.github.lobedan.jira.api.exceptions;

/**
 * Created by zambezi on 03.12.14.
 */
public class NoUpdateIssueException extends RuntimeException {
    public NoUpdateIssueException() {
        super();
    }

    public NoUpdateIssueException(String message) {
        super(message);
    }

    public NoUpdateIssueException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoUpdateIssueException(Throwable cause) {
        super(cause);
    }

    protected NoUpdateIssueException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
