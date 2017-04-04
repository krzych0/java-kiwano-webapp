package com.krzych0;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

  @RequestMapping(value = "/")
  public Api api(@RequestParam(value="name", defaultValue="World") String name) {
    return new Api(counter.incrementAndGet(),
        String.format(template, name));
  }


  public static class Api {

    private final long id;
    private final String content;

    public Api(long id, String content) {
      this.id = id;
      this.content = content;
    }

    public long getId() {
      return id;
    }

    public String getContent() {
      return content;
    }
  }
}
