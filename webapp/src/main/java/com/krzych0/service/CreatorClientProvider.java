package com.krzych0.service;

import com.imgtec.creator.CreatorClient;
import com.krzych0.config.AccessKeysWrapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

/**
 *
 */

public class CreatorClientProvider {

  @Autowired
  AccessKeysWrapper akw;

  @Autowired
  CreatorClient client;


  public void login() {
    try {
      client.getAuthManager().authorize(akw.getKey(), akw.getSecret());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }


}
