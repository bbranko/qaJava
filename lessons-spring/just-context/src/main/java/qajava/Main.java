package qajava;


import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
public class Main {


  public static void main(String[] args) {
    System.out.println("Hello world!");

    ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(Main.class);


    MyStartClass springBean = context.getBean(MyStartClass.class);
    springBean.run();
    context.close();

  }


}
