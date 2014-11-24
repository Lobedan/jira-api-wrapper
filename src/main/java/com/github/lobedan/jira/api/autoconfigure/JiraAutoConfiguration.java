package com.github.lobedan.jira.api.autoconfigure;

import com.github.lobedan.jira.api.builder.CustomJiraUrlBuilder;
import com.github.lobedan.jira.api.services.HttpRestTemplate;

import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * {@link org.springframework.boot.autoconfigure.EnableAutoConfiguration Auto configuration} for jira support.
 *
 * @author Sven Klemmer
 * @since 0.1.0
 */
@Configuration
@ConditionalOnProperty(prefix = "jira", value = "host")
@ConditionalOnMissingBean(name = "jiraBaseUrl")
@EnableConfigurationProperties(JiraProperties.class)
public class JiraAutoConfiguration {

  @Autowired
  private JiraProperties properties;

  @Bean(name = "jiraBaseUrl")
  public CustomJiraUrlBuilder jiraUrl() {

    String protocol;
    if (properties.getHost().contains("https://")) {
      properties.setHost(properties.getHost().replace("https://", ""));
      protocol = "https://";
    } else {
      properties.setHost(properties.getHost().replace("http://", ""));
      protocol = "http://";
    }

    return new CustomJiraUrlBuilder()
        .protocol(protocol)
        .host(properties.getHost())
        .port(properties.getPort())
        .apiPath(properties.getApiPath());
  }

  @Bean(name = "jiraCredentials")
  public UsernamePasswordCredentials credentials() {
    return new UsernamePasswordCredentials(properties.getApiUser(), properties.getApiPwd());
  }

  @Bean(name = "defaultHttpRestTemplate")
  public HttpRestTemplate httpRestTemplate() {
    return new HttpRestTemplate(new UsernamePasswordCredentials(properties.getApiUser(), properties.getApiPwd()));
  }
}
