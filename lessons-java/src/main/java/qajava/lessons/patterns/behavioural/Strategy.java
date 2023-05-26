package qajava.lessons.patterns.behavioural;


import java.util.List;

/**
 * Strategy patterns defines how to change behaviour(strategy) of one method at runtime.
 * <p>
 * For example:
 * - to change navigation generateRoute method from "for car" to "for walking"
 * - or to change calculator calculate method from "add 2 numbers" to "subtract 2 numbers"
 */
public class Strategy {
  //example code
  public static void main(String[] args) {
    NavigationStrategy carRouteStrategy = new CarRouteStrategy();
    NavigationStrategy walkRouteStrategy = new WalkRouteStrategy();

    //ie we create our navigator with initial strategy set to routes for cars
    NavigationInterface navigator = new Navigator(carRouteStrategy);
    navigator.navigate();
    //at some point, we switch to walking
    navigator.setRoutingStrategy(walkRouteStrategy);
    navigator.navigate();


    System.out.println("Example usage of strategy pattern in Java 8+");
    //Starting with Java 8 and addition of Stream API, there are far more practical examples of applying strategy pattern
    //namely, lambdas that we are passing to stream processing steps such as map, filter, etc. are prime examples of application of Strategy pattern!
    List<Integer> exampleList = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
    exampleList.stream()
      .filter(number -> number % 2 == 0) //setting custom filtering strategy to remove all odd numbers
      .map(number -> number * number)    //setting custom map strategy to square all numbers
      .forEach(System.out::println);     //setting custom consumer strategy to print out all numbers

    //when used properly, strategy pattern can be very powerful due to flexibility it adds and simplicity of its usage!
  }
}

//note
interface NavigationStrategy{
  String calculateRoute();
}

class CarRouteStrategy implements NavigationStrategy{
  @Override
  public String calculateRoute() {
    return "calculating route for CAR done.";
  }
}
class WalkRouteStrategy implements NavigationStrategy{
  @Override
  public String calculateRoute() {
    return "calculating route for WALKING done.";
  }
}

interface NavigationInterface {
  //for strategy pattern, we should have ability to change strategy in runtime
  void setRoutingStrategy(NavigationStrategy strategy);
  //...and a way to trigger execution of current strategy
  void navigate();
}

class Navigator implements NavigationInterface {
  NavigationStrategy currentStrategy;

  public Navigator(NavigationStrategy initialStrategy) {
    this.currentStrategy = initialStrategy;
  }

  @Override
  public void setRoutingStrategy(NavigationStrategy strategy) {
    this.currentStrategy = strategy;
  }
  @Override
  public void navigate() {
    System.out.println(currentStrategy.calculateRoute());
  }
}
