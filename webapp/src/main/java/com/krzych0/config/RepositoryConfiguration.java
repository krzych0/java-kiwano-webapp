package com.krzych0.config;


import com.krzych0.repository.TemperatureRepository;
import com.krzych0.service.TemperatureService;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories({"com.krzych0.repository"})
public class RepositoryConfiguration extends AbstractMongoConfiguration {

  @Value("${spring.data.mongodb.uri}")
  private String uri;

  private MongoClientURI clientUri;

  @Override
  protected String getDatabaseName() {
    if (clientUri == null) {
      clientUri = new MongoClientURI(uri);
    }
    return clientUri.getDatabase();
  }

  @Override
  public Mongo mongo() throws Exception {
    if (clientUri == null) {
      clientUri = new MongoClientURI(uri);
    }
    return new MongoClient(clientUri);
  }

  @Bean
  public TemperatureService temperatureService(TemperatureRepository repository) {
    return new TemperatureService(repository);
  }
}
