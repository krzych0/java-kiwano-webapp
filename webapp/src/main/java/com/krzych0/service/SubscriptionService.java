package com.krzych0.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class SubscriptionService {

  @Value("${spring.webhook.url}")
  String url;

  @Autowired
  CreatorService creatorService;

  public void subscribe() {
    creatorService.subscribe(url);
  }
}
