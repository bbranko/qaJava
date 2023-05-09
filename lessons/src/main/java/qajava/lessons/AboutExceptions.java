package qajava.lessons;

public class AboutExceptions {
  public static void main(String[] args) {
    //there are Exceptions and there are Errors.
    //Errors are environment and system related issues that are being thrown by JVM itself.
    //Errors are by definition unchecked.

    try {
      System.out.println("We can throw Errors (though we really SHOULD NOT do that)");
      throw new OutOfMemoryError("This happens when we spend all JVM memory");
    } catch (Error error) {
      System.out.println("We can catch Errors, to try and recover from them (though we really SHOULD NOT do that)");
      System.out.println(error);
    }

    try {
      throw new StackOverflowError("This happens for example if we wrote bad recursive method");
    } catch (Error error) {
      System.out.println(
        "Especially because recovery is rarely reliably possible, since Errors " +
          "indicate a serious problem that a reasonable application should not try to catch."
      );
      System.out.println(error);
    }


    //Exceptions, however, are recoverable
    //Basic separations are checked Exceptions witch Exceptions by default are,
    //and unchecked Exceptions that extend RuntimeException


    try {
      //read quick-doc
      throwException();
    } catch (Exception e) {
      System.out.println("...or that it needs to be handled with try-catch block!");
      System.out.println(e);
    }


    //oh right, we handle(catch) exceptions with try-catch block,
    //at ANY level, so long that try-catch wraps the part that actually trows exception, ie:
    try {
      nestedExceptionThrow();
      //we can even declare multiple type of exceptions to be caught,
      //so long as they are disjoint - meaning one does not extend the other or vice-versa
    } catch (IndexOutOfBoundsException | ClassCastException e) {
      System.out.println("We caught: " + e);
    }


    try {
      //of course, we can define our custom exception by extending either Exception or RuntimeException
      //...though, extending from Throwable is enough to make a class candidate for `throw` statement
      throw new MyCustomException("");
    } catch (MyCustomException e) {
      System.out.println("Custom catch! :" + e);
    }

    try {
      System.out.println("Finally, try-catch blocks can have a finally block.");
//      throw new Exception();
    } catch (Exception e) {
      System.out.println("Though finally block WILL NOT execute if we rethrow an from a catch exception!!!");
      throw new RuntimeException("Throwing exception from a catch - Yeah - you can do it too! B-)");
    } finally {
      System.out.println(
        "Finally block is a block of code that always executes irrelevant if exception was caught or not."
      );
    }


    //Final note: there is significant performance impact to using exceptions,
    // especially so if we are generating stacktrace data,
    // thus exceptions should NOT be used for regular data flows!
    //Exceptions purpose is for recovery when things go (very) wrong,
    // and that is for what they should be used.

    //more info: https://www.baeldung.com/java-exceptions-performance
    //note that usage of exception handling and/or reflection api easily adds up - something to keep in mind ;)

  }

  /**
   * being a checked exception, means it has to be either added to a signature
   */
  private static void throwException() throws Exception {
    throw new Exception("exception");
  }

  private static void nestedExceptionThrow() {
    nestedThrow();
  }

  private static void nestedThrow() {
    throw new IndexOutOfBoundsException("This is unchecked exception since it extends RuntimeException! Sneaky, sneaky... ( >.>)");
  }
}

/**
 * Custom exceptions are classes like any other.
 * Reason for defining one may be to add custom handling in class specific catch,
 * or to pass data that would aid recovery or provide custom insights as to what went wrong...
 */
class MyCustomException extends Exception {
  public MyCustomException(String message) {
    super(message);
  }
}
