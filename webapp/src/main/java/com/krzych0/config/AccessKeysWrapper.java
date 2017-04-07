package com.krzych0.config;

import org.springframework.beans.factory.annotation.Value;

/**
 *
 */
public class AccessKeysWrapper {

  @Value("${spring.creator.key}")
  private String key;
  @Value("${spring.creator.secret}")
  private String secret;

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public String getSecret() {
    return secret;
  }

  public void setSecret(String secret) {
    this.secret = secret;
  }
}
