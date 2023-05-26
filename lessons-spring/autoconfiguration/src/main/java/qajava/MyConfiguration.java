package qajava;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@AutoConfiguration
//@ConditionalOnBean(MyStartClass.class)
//@Configuration
public class MyConfiguration {

  private final Environment environment;

  public MyConfiguration(Environment environment) {
    System.out.println("my conf created");
    this.environment = environment;
  }

  public void printOutEnv() {
    System.out.println(environment);
  }

  public Integer getCpuCount() {
    return Integer.valueOf(environment.getProperty("NUMBER_OF_PROCESSORS"));
  }

  public String getJavaVersion() {
    return environment.getProperty("java.version");
  }

  @Bean
  @ConditionalOnMissingBean
  public DummyInterface createDummy() {
    System.out.println("dummy configured");
    return new DummyInterface() {
      @Override
      public void print() {
        System.out.println("Conditional!");
      }
    };
  }

}
