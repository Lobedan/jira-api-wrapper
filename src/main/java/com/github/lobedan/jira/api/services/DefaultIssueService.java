package com.github.lobedan.jira.api.services;

import com.github.lobedan.jira.api.domain.jira.Issue;
import com.github.lobedan.jira.api.dsl.jiraurl.JiraUrlBuilder;
import com.github.lobedan.jira.api.exceptions.IssueNotFoundException;
import com.github.lobedan.jira.api.exceptions.NoUpdateIssueException;
import com.github.lobedan.jira.api.exceptions.ServerNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Basic CRUD service to work with issues
 *
 * @author Sven Klemmer
 * @since 0.1.0
 */
@Service
public class DefaultIssueService implements IssueService, HttpRestTemplateAware {
  private static final Logger LOGGER = LogManager.getLogger(DefaultIssueService.class);

  private JiraUrlBuilder baseUrlBuilder;
  private HttpRestTemplate restTemplate;

  @Override
  public Issue getIssue(long id) {
    return getIssue(String.valueOf(id));
  }

  @Override
  public Issue getIssue(String key) {
    Assert.notNull(baseUrlBuilder.build());
    ResponseEntity<Issue> response = restTemplate.exchange(baseUrlBuilder.build().toString() + "/issue/" + key,
                                                           HttpMethod.GET, Issue.class);
    if (response.getBody() != null && response.getBody().getKey() != null) {
      return response.getBody();
    } else {
      throw new IssueNotFoundException("Could not find Issue with key or ID " + key);
    }
  }

  @Override
  public HttpStatus updateIssue(long id, String[] newLabels, String[] ownLabels) {
    return updateIssue("" + id, newLabels, ownLabels);
  }

  @Override
  public HttpStatus updateIssue(String key, String[] newLabels, String[] ownLabels) {
    List<String> updateLabels = new ArrayList<String>(Arrays.asList(newLabels));

    Issue actualIssue = getIssue(key);
    if (Arrays.equals(actualIssue.getFields().getLabels(), newLabels)) {
      LOGGER.warn("old Labels and new labels match, there is nothing to update");
      return HttpStatus.OK;
    } else {
      updateLabels = mergeLabels(updateLabels, removeLabels(actualIssue.getFields().getLabels(), ownLabels));
      return updateIssue(key, updateLabels.toArray(new String[updateLabels.size()]));
    }
  }

  @Override
  public HttpStatus updateIssue(long id, String[] newLabels) {
    return updateIssue("" + id, newLabels);
  }

  @Override
  public HttpStatus updateIssue(String key, String[] newLabels) {
    String changeJSON = "{"
            + "\"update\":"
            + "{"
            + "\"labels\" : ["
            + "{"
            + "\"set\" :[";
    for (String l : newLabels) {
      changeJSON += "\"" + l + "\",";
    }
    changeJSON += "\"\""
            + "]"
            + "}"
            + "]"
            + "}"
            + "}";
    ResponseEntity<String> responseEntity = null;
    try {
      responseEntity = restTemplate.exchange(baseUrlBuilder.build().toString() + "/issue/" + key,
              HttpMethod.PUT, String.class, changeJSON);
    } catch (HttpClientErrorException e) {
      if (e.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
        throw new ServerNotFoundException("Could not connect to jira server");
      }
    }
    if (responseEntity != null && responseEntity.getBody() != null) {
      return responseEntity.getStatusCode();
    } else {
      throw new NoUpdateIssueException("Could not update issue " + key + " in jira");
    }
  }

  @Override
  public JiraUrlBuilder getBaseUrlBuilder() {
    return baseUrlBuilder;
  }

  @Override
  @Autowired
  @Qualifier("jiraBaseUrlBuilder")
  public void setBaseUrlBuilder(JiraUrlBuilder aUrlBuilder) {
    this.baseUrlBuilder = aUrlBuilder;
  }

  @Override
  @Autowired
  @Qualifier("defaultHttpRestTemplate")
  public void setHttpRestTemplate(HttpRestTemplate aHttpRestTemplate) {
    this.restTemplate = aHttpRestTemplate;
  }

  private List<String> mergeLabels(List<String> l1, List<String> l2) {
    if (l1.size() == 0) {
      return l2;
    } else if (l2.size() == 0) {
      return l1;
    } else if (l2.size() == 0 && l1.size() == 0) {
      LOGGER.warn("Both lists are empty return new ArrayList()");
      return new ArrayList<String>();
    } else {
      for (String s1 : l1) {
        if (!l2.contains(s1)) {
          l2.add(s1);
        }
      }
      return l2;
    }
  }

  private List<String> removeLabels(String[] oldLabels, String[] ownLabels) {
    List<String> strings = new ArrayList<String>(Arrays.asList(oldLabels));
    strings.removeAll(Arrays.asList(ownLabels));
    return strings;
  }
}