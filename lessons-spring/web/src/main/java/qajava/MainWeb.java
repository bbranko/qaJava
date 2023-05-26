package qajava;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
public class MainWeb {


  public static void main(String[] args) {
    System.out.println("app started");

    SpringApplication.run(MainWeb.class);
    //nothing to run, spring runs Server for us...
    System.out.println("app done");
  }

}
