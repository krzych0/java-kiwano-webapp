package com.krzych0.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import java.io.UnsupportedEncodingException;

/**
 *
 */
public class JwtAuthenticationProvider implements AuthenticationProvider {

  private final String secretKey;

  public JwtAuthenticationProvider(String secretKey) {
    this.secretKey = secretKey;
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return JwtAuthenticationToken.class.isAssignableFrom(authentication);

  }

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    try {
      return (authentication instanceof JwtAuthenticationToken) ?
          getJwtAuthentication(((JwtAuthenticationToken) authentication).getToken()) :
          null;
    } catch (RuntimeException | UnsupportedEncodingException e) {
      throw new InvalidAuthenticationException("Access denied", e);
    }
  }

  @SuppressWarnings("unchecked")
  private Authentication getJwtAuthentication(String token) throws UnsupportedEncodingException {

    return JwtAuthenticationToken.fromToken(secretKey, token);
  }
}
