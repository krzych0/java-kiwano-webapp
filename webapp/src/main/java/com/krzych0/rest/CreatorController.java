package com.krzych0.rest;

import com.krzych0.service.CreatorService;
import com.krzych0.service.SubscriptionService;
import com.krzych0.service.TemperatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

/**
 *
 */
@RestController
public class CreatorController {

  private static final String template = "Hello, %s!";
  private final AtomicLong counter = new AtomicLong();

  @Autowired
  TemperatureService service;

  @Autowired
  CreatorService creatorService;

  @Autowired
  SubscriptionService subscriptionService;

  @RequestMapping(value = "/test")
  public Api api(@RequestParam(value = "name", defaultValue = "World") String name) {
    service.doWork();
    creatorService.login();
    subscriptionService.subscribe();
    return new Api(counter.incrementAndGet(),
        String.format(template, name));
  }
}
