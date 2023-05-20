package qajava.lessons.patterns.structural;

/**
 * Adapter to a whole system is called Facade.
 *
 * Facade hides inner workings of a system and exposes only desired endpoints in a desired(adapted) format.
 */
public class Facade {
  //example code
  public static void main(String[] args) {
    //at point of usage we simply do not care about existence or inner workings of a system(library) behind a facade
    DesiredInterface facadeForInner = new FacadeAdaptingInnerServices();
    //we just user it to do work in desired manner
    facadeForInner.processData(10);
    facadeForInner.getData();

  }
}

//imagine we have complex system with intertwined methods
class InnerService1{
  void method1(){}
  void method2(){}
}
class InnerService2{
  Integer producerMethod(){
    return null; //some value
  }
}
class InnerService3{
  void consumerMethod(Integer data){}
}

//we can implement Facade abstracting it all away and adapting it to our needs
//ie we could hide 3rd party library behind far simpler Facade that exposes only endpoints relevant for our code
interface DesiredInterface{
  Integer getData();
  void processData(Integer input);
}

class FacadeAdaptingInnerServices implements DesiredInterface{

  final InnerService1 innerService1;
  final InnerService2 innerService2;
  final InnerService3 innerService3;

  public FacadeAdaptingInnerServices() {
    this.innerService1 = new InnerService1();
    this.innerService2 = new InnerService2();
    this.innerService3 = new InnerService3();
  }

  @Override
  public Integer getData() {
    innerService1.method1();
    return innerService2.producerMethod();
  }

  @Override
  public void processData(Integer input) {
    innerService3.consumerMethod(input);
    innerService1.method2();
  }
}
