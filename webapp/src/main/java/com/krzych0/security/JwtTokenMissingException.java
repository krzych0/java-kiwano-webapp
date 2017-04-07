package com.krzych0.security;

import javax.naming.AuthenticationException;

/**
 *
 */
public class JwtTokenMissingException extends AuthenticationException {

  public JwtTokenMissingException(String message) {
    super(message);
  }
}
