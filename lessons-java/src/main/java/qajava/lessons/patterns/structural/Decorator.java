package qajava.lessons.patterns.structural;

/**
 * Decorator has the same interface as the class of object that it wraps.
 * Decorator is usually used to further enhance/extend methods of wrapped object.
 * <p>
 * Difference with Proxy:
 * - Decorator extends functionality of methods of wrapped objects, as opposed to Proxy which is usually mostly pass through.
 * - Decorator is applied to existing instances, while Proxy usually manages lifecycle of instance that it wraps
 * Difference with Adapter:
 * - Decorator has the same interface as the class it wraps.
 * <p>
 * Biggest limitation of Decorator is that it has to follow common interface as core functionality that is being decorated.
 * Aspect may be considered Decorator that surpasses that limitation - though it does so at price of far grater complexity,
 * and it usually requires processing system in place that implements Aspect's functionality.
 * In aspect oriented programing, aspects can be defined with scope to which package/class/method they apply, at which
 * point of their execution, and in which order though all that at expense of added non-trivial complexity. :)
 */
public class Decorator {

  //example usage
  public static void main(String[] args) {
    //power of decorators is that they allow for transparent flexible extensions to core functionality

    //we can apply only one to one service:
    CommonInterface coreService10 = new CoreFunctionalityImplementation10();
    CommonInterface loggedCoreService10 = new DecoratorForLogging(coreService10);
    loggedCoreService10.processData(5);

    //or we can mix and mach any number of decorators in order we want and apply it to any number of services!
    CommonInterface coreService20 = new CoreFunctionalityImplementation20();
    CommonInterface validatedCoreService20 = new DecoratorForValidation(coreService20, 10);
    CommonInterface loggedValidatedCoreService20 = new DecoratorForLogging(validatedCoreService20);
    loggedValidatedCoreService20.processData(5); //this will fail validation
    loggedValidatedCoreService20.processData(15); //this will pass
  }

}


interface CommonInterface{
  Integer processData(Integer data);
}

class CoreFunctionalityImplementation10 implements CommonInterface{
  @Override
  public Integer processData(Integer data) {
    System.out.println("Processing +10");
    return data + 10;
  }
}

class CoreFunctionalityImplementation20 implements CommonInterface{
  @Override
  public Integer processData(Integer data) {
    System.out.println("Processing +20");
    return data + 20;
  }
}

class DecoratorForLogging implements CommonInterface{
  final CommonInterface objectBeingDecorated;

  public DecoratorForLogging(CommonInterface objectBeingDecorated) {
    this.objectBeingDecorated = objectBeingDecorated;
  }


  @Override
  public Integer processData(Integer data) {
    System.out.println("Input is: " + data);
    Integer result = objectBeingDecorated.processData(data);
    System.out.println("Output is: " + result);
    return result;
  }
}

class DecoratorForValidation implements CommonInterface{
  final CommonInterface objectBeingDecorated;
  final Integer noLessThan;

  public DecoratorForValidation(CommonInterface objectBeingDecorated, Integer noLessThan) {
    this.objectBeingDecorated = objectBeingDecorated;
    this.noLessThan = noLessThan;
  }

  @Override
  public Integer processData(Integer data) {
    if(data < noLessThan){
      System.out.println("Validation failed! Input " + data + " is less than " + noLessThan);
      return null;
    }

    System.out.println("Validation passed, continuing...");
    Integer result = objectBeingDecorated.processData(data);
    return result;
  }
}
