package com.github.lobedan.jira.api.exceptions;

/**
 * Created by zambezi on 03.12.14.
 */
public class ServerNotFoundException extends RuntimeException {
    public ServerNotFoundException() {
        super();
    }

    public ServerNotFoundException(String message) {
        super(message);
    }

    public ServerNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServerNotFoundException(Throwable cause) {
        super(cause);
    }

    protected ServerNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
