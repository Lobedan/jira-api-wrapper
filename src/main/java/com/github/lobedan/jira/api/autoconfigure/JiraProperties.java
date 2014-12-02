package com.github.lobedan.jira.api.autoconfigure;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration properties for jira support
 *
 * @author Sven Klemmer
 * @since 0.1.0
 */
@ConfigurationProperties(prefix = "jira")
public class JiraProperties {

  private String scheme;
  private String host;
  private String port;
  private String apiPath;
  private String apiVersion;

  private String apiUser;
  private String apiPwd;

  public JiraProperties() {
  }

  public String getScheme() {
    return scheme;
  }

  public void setScheme(String aScheme) {
    scheme = aScheme;
  }

  public String getApiVersion() {
    return apiVersion;
  }

  public void setApiVersion(String aApiVersion) {
    apiVersion = aApiVersion;
  }

  public String getHost() {
    return host;
  }

  public void setHost(String aHost) {
    host = aHost;
  }

  public String getPort() {
    return port;
  }

  public void setPort(String aPort) {
    port = aPort;
  }

  public String getApiPath() {
    return apiPath;
  }

  public void setApiPath(String aApiPath) {
    apiPath = aApiPath;
  }

  public String getApiUser() {
    return apiUser;
  }

  public void setApiUser(String aApiUser) {
    apiUser = aApiUser;
  }

  public String getApiPwd() {
    return apiPwd;
  }

  public void setApiPwd(String aApiPwd) {
    if (Base64.isArrayByteBase64(apiPwd.getBytes())) {
      apiPwd = Base64.decodeBase64(apiPwd.getBytes()).toString();
    } else {
      apiPwd = aApiPwd;
    }
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this)
        .append("scheme", scheme)
        .append("host", host)
        .append("port", port)
        .append("apiPath", apiPath)
        .append("apiVersion", apiVersion)
        .append("apiUser", apiUser)
        .append("apiPwd", apiPwd)
        .toString();
  }
}
