package qajava.lessons.patterns.structural;

/**
 * Proxy has the same interface as the class that it wraps.
 * Proxy is usually used to wrap/adapt/manage 3rd party services.
 * <p>
 * Difference with Decorator:
 * - Proxy manages lifecycle of object that it wraps, as opposed to Decorator which gets applied to already existing object.
 * Difference with Adapter:
 * - Proxy has the same interface as the class it wraps
 */
public class Proxy {
  //example usage
  public static void main(String[] args) {
    String result; //helper variable...

    //we create a service proxy and use it without care, since proxy manages lifecycle of underlying service
    ServiceInterface proxiedService = new ProxyForSome3rdPartyService();
    result = proxiedService.doSomeWork(10);
    System.out.println("Proxied service response: " + result);
    result = proxiedService.doSomeFailureProneWork();
    System.out.println("Flaky service method response: " + result);

    //just to get more response variation
    result = proxiedService.doSomeFailureProneWork();
    System.out.println("Flaky service method response: " + result);
    result = proxiedService.doSomeFailureProneWork();
    System.out.println("Flaky service method response: " + result);
  }
}

interface ServiceInterface {
  String doSomeWork(Integer data);

  String doSomeFailureProneWork();
}

class Some3rdPartyService implements ServiceInterface {

  @Override
  public String doSomeWork(Integer data) {
    return "did some work on " + data;
  }

  @Override
  public String doSomeFailureProneWork() {
    if (Math.random() > 0.2) {
      return "failed";
    } else {
      return "work done successfully!";
    }
  }
}


class ProxyForSome3rdPartyService implements ServiceInterface {
  Some3rdPartyService service;

  //proxy defines at which point service will be created, so one thing we can do is lazy load it
  //in other cases maybe we need to refresh connection, validate state, etc... this can all be abstracted away with proxy
  private Some3rdPartyService getServiceInstant() {
    if (service == null) {
      service = new Some3rdPartyService();
    }
    return service;
  }

  @Override
  public String doSomeWork(Integer data) {
    //proxy methods can be and mostly are pass through
    return getServiceInstant().doSomeWork(data);
  }

  @Override
  public String doSomeFailureProneWork() {
    //or we can implement retry mechanic for flaky services
    System.out.println("Trying to do someWork...");
    int maxRetries = 10; //note: you should always have a secondary break mechanism to not enter an endles loop
    while (maxRetries-- > 0) {
      String result = getServiceInstant().doSomeFailureProneWork();
      if (result.equals("failed")) {
        System.out.println("Processing failed, retrying...");
      } else {
        return result;
      }
    }
    return "Maximum number of retries exceeded, please try again later";
  }
}
