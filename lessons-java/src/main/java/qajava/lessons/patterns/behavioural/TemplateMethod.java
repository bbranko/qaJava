package qajava.lessons.patterns.behavioural;

/**
 * In template method, base class or interface defines algorithm in a method that is written as a composition of overridable steps.
 * In subclasses, you then can override/adjust one or more of such steps
 * <p>
 * It is basically idea of having base class with default implementation
 * where you override some parts of it in subclasses.
 */
public class TemplateMethod {

  //example code
  public static void main(String[] args) {
    TemplateMethodBase processAbove10 = new ProcessIfAbove10();

    processAbove10.doWork(5);
    processAbove10.doWork(15);

    TemplateMethodBase nonShallPass = new NonShallPass();
    nonShallPass.doWork(5);
    nonShallPass.doWork(15);

    //fact that we only override steps, but control as to when those steps will be used is somewhere else(base class)
    //represents another example application of IoC principle
  }
}

abstract class TemplateMethodBase {

  //skeleton of the algorithm, that uses overridable steps
  //of course it can have input and output!
  //It could also be final so that overriding of actual template method is prevented
  void doWork(Integer data) {
    boolean isValid = stepValidateData(data);
    //we can define algorithm of arbitrary complexity
    if (isValid) {
      stepProcessData(data);
    } else {
      stepReportError();
    }
  }

  abstract boolean stepValidateData(Integer data);

  void stepProcessData(Integer data) {
    System.out.println("Processing: " + data);
  }

  void stepReportError() {
    System.out.println("Something went wrong!");
  }
}

class ProcessIfAbove10 extends TemplateMethodBase {

  @Override
  boolean stepValidateData(Integer data) {
    System.out.println("Got " + data + ", validating...");
    return data > 10;
  }

  @Override
  void stepReportError() {
    System.out.println("Error: Data was not above 10!");
  }
}

class NonShallPass extends TemplateMethodBase {

  @Override
  boolean stepValidateData(Integer data) {
    //not much sense to it, we just can do silly stuff like this too
    System.out.println("Got " + data + ", but we are considering everything as invalid just because we can.");
    return false;
  }
}
