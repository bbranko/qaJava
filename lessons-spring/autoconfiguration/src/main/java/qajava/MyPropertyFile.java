package qajava;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("application.properties")
public class MyPropertyFile {

  private final Environment environment;

  public MyPropertyFile(Environment environment) {
    System.out.println("my props created");
    this.environment = environment;
  }

  public String getMyCustomProperty() {
    return environment.getProperty("this.is.a.property");
  }
}
