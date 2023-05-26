package qajava;


import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class MyStartClass {

  private final MyConfiguration configuration;
  private final MyPropertyFile propertyFile;
  private final DummyInterface dummy;

  public MyStartClass(MyConfiguration configuration, MyPropertyFile propertyFile, DummyInterface dummy) {
    System.out.println("start class created");
    this.configuration = configuration;
    this.propertyFile = propertyFile;
    this.dummy = dummy;
  }

  public void run() {
    dummy.print();

    configuration.printOutEnv();
    System.out.println("Cpu count is: " + configuration.getCpuCount());
    System.out.println("Java version is: " + configuration.getJavaVersion());

    System.out.println("application.properties custom prop: " + propertyFile.getMyCustomProperty());
  }

//  @Bean
//  dummy2 classCreates(){
//    System.out.println("class can create beans too");
//    return new dummy2() {};
//  }
}

//interface dummy2{}
