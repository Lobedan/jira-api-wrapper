package com.github.lobedan.jira.api;

/**
 * exception is thrown when a method has not enough parameters, when they need a array
 *
 * @author svenklemmer
 * @since 0.1.0
 */
public class ParameterCountException extends RuntimeException {
  public ParameterCountException() {
    super();
  }

  public ParameterCountException(String message) {
    super(message);
  }

  public ParameterCountException(String message, Throwable cause) {
    super(message, cause);
  }

  public ParameterCountException(Throwable cause) {
    super(cause);
  }

  protected ParameterCountException(String message,
                                    Throwable cause,
                                    boolean enableSuppression,
                                    boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
