package com.krzych0.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 */
public class JwtAuthenticationToken extends AbstractAuthenticationToken {

  private String userId;
  private String name;
  private String token;

  /**
   * Creates a token with the supplied array of authorities.
   *
   * @param authorities the collection of <tt>GrantedAuthority</tt>s for the principal
   *                    represented by this authentication object.
   */
  public JwtAuthenticationToken(Collection<? extends GrantedAuthority> authorities,
                                String userId, String name, String token) {
    super(authorities);
    this.userId = userId;
    this.name = name;
    this.token = token;
    setAuthenticated(true);
  }

  public JwtAuthenticationToken(String authToken) {
    super(AuthorityUtils.NO_AUTHORITIES);
    this.token = authToken;
  }

  @Override
  public Object getCredentials() {
    return "N/A";
  }

  @Override
  public Object getPrincipal() {
    return name;
  }

  public String getToken() {
    return token;
  }

  public String getUserId() {
    return userId;
  }

  public static JwtAuthenticationToken fromToken(String secret, String token) throws UnsupportedEncodingException {
    Claims body = Jwts.parser()
        .setSigningKey(secret.getBytes("UTF-8"))
        .parseClaimsJws(token)
        .getBody();

    //No exception - we can trust this JWT
    List<SimpleGrantedAuthority> grantedAuthorities =
        grantedAuthorities(Arrays.asList("USER"));
    return new JwtAuthenticationToken(grantedAuthorities, "WhatEver", "WhatEver", token);

  }

  private static List<SimpleGrantedAuthority> grantedAuthorities(List<String> roles) {
    return roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
  }
}
