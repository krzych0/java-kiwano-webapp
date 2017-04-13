package com.krzych0;

import com.krzych0.config.Constants;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.core.env.SimpleCommandLinePropertySource;

/**
 *
 */
@SpringBootApplication
@EnableAutoConfiguration(exclude = {JacksonAutoConfiguration.class})
public class Application implements CommandLineRunner {

  public static void main(String[] args) {

    SpringApplication application = new SpringApplication(Application.class);
    SimpleCommandLinePropertySource source = new SimpleCommandLinePropertySource(args);

    //add default profile if no profile has been set
    addDefaultProfile(application, source);

    application.run(args);
  }

  private static void addDefaultProfile(SpringApplication application, SimpleCommandLinePropertySource source) {
    if (!source.containsProperty("spring.profiles.active")) {
      application.setAdditionalProfiles(Constants.SPRING_PROFILE_DEVELOPMENT);
    }
  }

  @Override
  public void run(String... args) throws Exception {

  }
}
