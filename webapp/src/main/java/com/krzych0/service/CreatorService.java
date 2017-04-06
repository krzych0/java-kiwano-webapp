package com.krzych0.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class CreatorService {

  @Autowired
  CreatorClientProvider provider;


  public void login() {
    provider.login();
  }
}
