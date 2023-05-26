package qajava;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
public class MyConfiguration {

  private final Environment environment;

  public MyConfiguration(Environment environment) {
    this.environment = environment;
  }

  public void printOutEnv(){
    System.out.println(environment);
  }

  public Integer getCpuCount(){
    return Integer.valueOf(environment.getProperty("NUMBER_OF_PROCESSORS"));
  }

  public String getJavaVersion() {
    return environment.getProperty("java.version");
  }

//  @Bean
//  @Profile("dev")
//  public MyStartClass createMyStartClass(){
//    //..init data configure stuff etc..
//    return new MyStartClass(null, null);
//  }
//  @Bean
//  @Profile("prod")
//  public MyStartClass createMyStartClassForProd(){
//    //..init data configure stuff etc..
//    return new MyStartClass(null, null);
//  }
}
