package qajava;


import org.springframework.stereotype.Component;

@Component
public class MyStartClass {

  private final MyConfiguration configuration;
  private final MyPropertyFile propertyFile;

  public MyStartClass(MyConfiguration configuration, MyPropertyFile propertyFile) {
    this.configuration = configuration;
    this.propertyFile = propertyFile;
  }

  public void run() {
    configuration.printOutEnv();
    System.out.println("Cpu count is: " + configuration.getCpuCount());
    System.out.println("Java version is: " + configuration.getJavaVersion());

    System.out.println("application.properties custom prop: " + propertyFile.getMyCustomProperty());
  }
}
