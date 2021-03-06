package com.github.lobedan.jira.api.autoconfigure;

import com.github.lobedan.jira.api.dsl.jiraurl.JiraUrlBuilder;
import com.github.lobedan.jira.api.services.HttpRestTemplate;
import com.github.lobedan.jira.api.types.SchemeType;

import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.github.lobedan.jira.api.dsl.jiraurl.JiraUrlBuilder.jiraUrl;

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

  @Bean(name = "jiraBaseUrlBuilder")
  public JiraUrlBuilder jiraBaseUrl() {

    SchemeType schemeType;
    if (properties.getScheme().contains("https")) {
      schemeType = SchemeType.HTTPS;
    } else {
      schemeType = SchemeType.HTTP;
    }

    return jiraUrl()
        .scheme(schemeType)
        .host(properties.getHost())
        .port(properties.getPort())
        .path(properties.getApiPath())
        .apiVersion(properties.getApiVersion());
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
