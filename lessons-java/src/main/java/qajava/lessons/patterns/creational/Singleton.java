package qajava.lessons.patterns.creational;

/**
 * Singleton assures that only ever one instance of the object will be created.
 * It is more flexible than static class, since we can call methods through polymorphism
 * and more easily test it.
 */
//minimal implementation
public class Singleton {

  //constructor cannot be accessible
  private Singleton() {
  }

  //eager creation of singleton instance
  private static final Singleton theOnlyInstance = new Singleton();

  //getting object of type Singleton is only allowed through one method,
  //which is usually named getInstance
  public static Singleton getInstance(){
    return theOnlyInstance;
  }
}
//Note: Spring @Bean, @Component, @Service, @Controller, @Repository, @Configuration etc are all singletons by default!

class LazySingleton{
  private LazySingleton() {
  }

  private static LazySingleton theOnlyInstance;

  //...BUT this is not thread safe!
  public static LazySingleton getInstance(){
    if(theOnlyInstance == null){
      theOnlyInstance = new LazySingleton();
    }
    return theOnlyInstance;
  }
}

class LazyThreadSafeSingleton{
  private LazyThreadSafeSingleton() {
  }

  private static LazyThreadSafeSingleton theOnlyInstance;

  //...BUT this is not all that performant! (accessing method is always blocking due to "synchronized")
  public static synchronized LazyThreadSafeSingleton getInstance(){
    if(theOnlyInstance == null){
      theOnlyInstance = new LazyThreadSafeSingleton();
    }
    return theOnlyInstance;
  }
}

//Bill Pugh's java solution to singleton
class LazyThreadSafePerformantSingleton{
  private LazyThreadSafePerformantSingleton() {
  }

  private static class LazyInstanceHolder {
    final static LazyThreadSafePerformantSingleton theOnlyInstance = new LazyThreadSafePerformantSingleton();
  }

  //Thread safe and performant!
  //Calling method by itself is non-blocking, but due to how java initializes static inner classes.
  //instance gets created in thread safe manner during first time access to LazyInstanceHolder.theOnlyInstance is requested.
  //After that, all following calls are non-blocking since we only need to return the one and only instance of our singleton.
  public static synchronized LazyThreadSafePerformantSingleton getInstance(){
    return LazyInstanceHolder.theOnlyInstance;
  }
}

