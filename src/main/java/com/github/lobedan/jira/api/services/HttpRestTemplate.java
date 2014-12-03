package com.github.lobedan.jira.api.services;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

/**
 * implementation of {@link org.springframework.web.client.RestTemplate} which special basic auth
 * methods for jira
 * credentials are also provided by {@link com.github.lobedan.jira.api.autoconfigure.JiraAutoConfiguration} when
 * using spring boot
 *
 * @author svenklemmer
 * @since jira-api-wrapper 0.1.0
 */
public class HttpRestTemplate extends RestTemplate {
  private static final Logger LOGGER = LogManager.getLogger(HttpRestTemplate.class);

  private UsernamePasswordCredentials credentials;

  public HttpRestTemplate() {
    super();
  }

  public HttpRestTemplate(UsernamePasswordCredentials aCredentials) {
    setCredentials(aCredentials);
  }

  public <T> ResponseEntity<T> exchange(URI url,
                                        HttpMethod method,
                                        Class<T> responseType) throws RestClientException {

    return super.exchange(url, method, getRequest(), responseType);
  }

  public <T> ResponseEntity<T> exchange(URI url,
                                        HttpMethod method,
                                        Class<T> responseType, String json) throws RestClientException {

    return super.exchange(url, method, getRequest(json), responseType);
  }

  public <T> ResponseEntity<T> exchange(String url,
                                        HttpMethod method,
                                        Class<T> responseType) throws RestClientException {

    return super.exchange(url, method, getRequest(), responseType);
  }

  public <T> ResponseEntity<T> exchange(String url,
                                        HttpMethod method,
                                        Class<T> responseType,
                                        String json) throws RestClientException {

    return super.exchange(url, method, getRequest(json), responseType);
  }

  private HttpEntity<String> getRequest() {
    return getRequest(getHeaders());
  }

  private HttpEntity<String> getRequest(String content) {
    return new HttpEntity<String>(content, getHeaders());
  }

  private HttpEntity<String> getRequest(HttpHeaders headers) {
    return new HttpEntity<String>(headers);
  }

  private HttpHeaders getHeaders() {
    Assert.notNull(credentials, "You must provide some credentials");
    HttpHeaders headers = new HttpHeaders();
    String cred = credentials.getUserName() + ":" + credentials.getPassword();
    byte[] base64Token = Base64.encodeBase64(cred.getBytes());
    String base64EncodedToken = new String(base64Token);

    //adding Authorization header for HTTP Basic authentication
    headers.add("Authorization", "Basic " + base64EncodedToken);
    return headers;
  }

  public Credentials getCredentials() {
    return credentials;
  }

  public void setCredentials(UsernamePasswordCredentials aCredentials) {
    credentials = aCredentials;
  }
}
