package qajava;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(exclude = MyConfiguration.class)
//@EnableAutoConfiguration(exclude = MyConfiguration.class)
public class MainBoot {


  public static void main(String[] args) {
    System.out.println("app started");

    ConfigurableApplicationContext context = SpringApplication.run(MainBoot.class);

    MyStartClass bean = context.getBean(MyStartClass.class);
    bean.run();

    System.out.println("app done");
  }

//  @Bean
//  CommandLineRunner commandLineRunner(MyStartClass myStartClass){
//    return (args) -> myStartClass.run();
//  }

}
