package qajava.lessons.patterns.behavioural;

/**
 * Chain of responsibility defines idea of making (usually) a linked list of Commands with conditional execution in order to process an input.
 * We can also use any other (ideally acyclic) graph structure, or we can even implement the chain via wrapping similarly to Decorators.
 * Difference to Decorator pattern is that Chain of Responsibility can break execution flow, while Decorator should never do that.
 */
public class ChainOfResponsibility {

  //example code
  public static void main(String[] args) {
    //this is just a basic example, other variants will maybe be added at some point...
    //first we need to set up the chain
    CoRNodeInterface normalizeInputHandler = new NormalizeInputHandler();
    CoRNodeInterface validationHandler = new ValidationHandler();
    CoRNodeInterface processRequestHandler = new ProcessRequestHandler();

    //then we need to link them, and we ont processing to be: normalize -> validate -> process
    normalizeInputHandler.setNext(validationHandler);
    validationHandler.setNext(processRequestHandler);

    //since we wrote our chan as a linked list, first element of the chain is also chain start
    CoRNodeInterface processingChain = normalizeInputHandler;

    //and then we have our chain all set up and we are ready to handle requests!
    processingChain.handle("This request will FAIL!");
    processingChain.handle("This request will PASS!");

  }

}

interface CoRNodeInterface {
  void handle(String request);
  void setNext(CoRNodeInterface next);
}

class NormalizeInputHandler implements CoRNodeInterface {
  CoRNodeInterface next;

  @Override
  public void handle(String request) {
    String result = request.toLowerCase();
    System.out.println("Input [" + request + "] normalized [" + result + "]");
    if (next != null) {
      next.handle(result);
    }
  }

  @Override
  public void setNext(CoRNodeInterface next) {
    this.next = next;
  }
}

class ValidationHandler implements CoRNodeInterface {
  CoRNodeInterface next;

  @Override
  public void handle(String request) {
    if (request.contains("fail")) {
      System.out.println("Validation failed... BREAKING EXECUTION!");
    } else {
      System.out.println("Validation passed... Continuing");
      if (next != null) {
        next.handle(request);
      }
    }
  }

  @Override
  public void setNext(CoRNodeInterface next) {
    this.next = next;
  }
}

class ProcessRequestHandler implements CoRNodeInterface {
  CoRNodeInterface next;

  @Override
  public void handle(String request) {
    System.out.println("Processing request: [" + request + "]");
    if (next != null) {
      next.handle(request);
    }
  }

  @Override
  public void setNext(CoRNodeInterface next) {
    this.next = next;
  }
}
