package com.github.lobedan.jira.api.builder;

/**
 * Interface representing a builder. Builders are objects that are used to
 * construct other objects.
 *
 * @author svenklemmer
 * @since 0.1.0
 */
public interface Builder<T> {
  /**
   * Builds and returns the object.
   */
  public T build();

  /**
   * Builds and return the object but also clears the builder instance
   * mostly used in test environment
   */
  public T buildAndClear();

  /**
   * clears builder instance
   */
  public void clear();
}
