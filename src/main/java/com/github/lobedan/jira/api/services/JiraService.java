package com.github.lobedan.jira.api.services;

/**
 * this is the main class of jira-api-wrapper it provides all crud methods to
 * access jira rest api and make some actions
 * its implemented in
 *
 * @author svenklemmer
 * @since jira-api-wrapper 0.1.0
 */
public interface JiraService extends JiraUrlAware {

    void searchJira();

    void createProject();

    void changeProject();

    void deleteProject();

}
