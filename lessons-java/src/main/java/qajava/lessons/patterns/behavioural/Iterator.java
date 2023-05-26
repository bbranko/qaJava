package qajava.lessons.patterns.behavioural;

import java.util.ArrayList;
import java.util.List;

/**
 * Iterator encapsulates how a collection should be traversed behind very minimal interface of two methods: hasNext and getNext.
 * Of course, iterator interface can be more complex (as is the case with java.util.Iterator),
 * but from Iterator pattern standpoint those should always be optional.
 * <p>
 * When writing a custom class that can be traversed, we would often also write a method `getIterator` to get
 */
public class Iterator {

  //example code
  public static void main(String[] args) {
    List<Integer> exampleList = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);

    //List (and actually all java collection classes), already implement method that returns an Iterator
    java.util.Iterator<Integer> javaIterator = exampleList.iterator();
    //default way to use an iterator
    System.out.println("Iterating with java iterator");
    while (javaIterator.hasNext()) {
      Integer nextValue = javaIterator.next();
      //...and from this point comes processing of the value, in our case, a simple printout
      System.out.println(nextValue);
    }

    //important! iterator may be used to traverse a collection ONLY ONCE!
    System.out.println("By default, there is no way to reset an iterator.");
    System.out.println("Once hasNext is " + javaIterator.hasNext() + " only way is to create a new iterator.");
    //...and in case of Java, invoking next again, would throw an NoSuchElementException

    //but we can always ask for new instance of an iterator over same collection
    javaIterator = exampleList.iterator();
    //java iterators also implement forEach, though this is not a part of Iterator pattern
    javaIterator.forEachRemaining(System.out::println);

    //furthermore, if a collection implements java.lang.Iterable interface,
    //that collection can be iterated over with for-in loop:
    System.out.println("Iterating for-in loop");
    for (Integer nextValue : exampleList) {
      System.out.println(nextValue);
    }

    //those were all vanilla Java examples - lets try out our custom ones
    IteratorInterface<Integer> ourCustomIterator = new RegularIntIterator(exampleList);
    //this one works in same way as java iterator (minus the optional functions)
    System.out.println("Iterating with our custom regular iterator");
    while (ourCustomIterator.hasNext()) {
      Integer nextValue = ourCustomIterator.getNext();
      System.out.println(nextValue);
    }

    //we can use our funky iterator that lets us define step in which to iterate
    System.out.println("Iterating with our custom iterator in steps of 3");
    ourCustomIterator = new CustomStepIterator(exampleList, 3);
    while (ourCustomIterator.hasNext()) {
      Integer nextValue = ourCustomIterator.getNext();
      System.out.println(nextValue);
    }

    //...we can also define an offset
    System.out.println("Iterating with our custom iterator in steps of 3 and offset 2");
    ourCustomIterator = new CustomStepIterator(exampleList, 2, 3);
    while (ourCustomIterator.hasNext()) {
      Integer nextValue = ourCustomIterator.getNext();
      System.out.println(nextValue);
    }

    //...we can even go backwards
    System.out.println("Iterating with our custom iterator in steps of -1");
    ourCustomIterator = new CustomStepIterator(exampleList, -1);
    while (ourCustomIterator.hasNext()) {
      Integer nextValue = ourCustomIterator.getNext();
      System.out.println(nextValue);
    }

    //...if we set nothing it will behave as regular iterator
    System.out.println("If we pass nothing our custom iterator behaves like regular iterator");
    ourCustomIterator = new CustomStepIterator(exampleList);
    while (ourCustomIterator.hasNext()) {
      Integer nextValue = ourCustomIterator.getNext();
      System.out.println(nextValue);
    }

  }

}

//...and we set return type as generic so that same interface can be implemented to traverse collections with any type of elements
interface IteratorInterface<T> {
  boolean hasNext();

  T getNext();
}

//implementation of iterator hides state and logic of how we actually do iteration
//ie this is just a regular iterator mooving from first element of a list to the last,
// but we can (and will) do something more funky in later examples
class RegularIntIterator implements IteratorInterface<Integer> {
  private final List<Integer> listBeingTraversed;
  private int currentIndex;

  RegularIntIterator(List<Integer> listBeingTraversed) {
    this.listBeingTraversed = listBeingTraversed;
    this.currentIndex = 0;
  }


  @Override
  public boolean hasNext() {
    return currentIndex < listBeingTraversed.size();
  }

  @Override
  public Integer getNext() {
    Integer result = listBeingTraversed.get(currentIndex);
    currentIndex++;
    return result;
  }
}


//example of custom funky iterator :D
//This iterator allows for custom offset and step - we can even move backwards with negative step!
//If we do not pass anything it will behave same as RegularIntIterator.
class CustomStepIterator implements IteratorInterface<Integer> {

  private final List<Integer> ourCopyOfListBeingTraversed;
  private int currentIndex;
  private final int step;

  public CustomStepIterator(List<Integer> listBeingTraversed) {
    this(listBeingTraversed, 0, 1);
  }

  public CustomStepIterator(List<Integer> listBeingTraversed, int step) {
    this(listBeingTraversed, 0, step);
  }

  //of course, we should have checks for invalid values but this is just for a demo ;)
  public CustomStepIterator(List<Integer> listBeingTraversed, int initialOffset, int step) {
    this.ourCopyOfListBeingTraversed = new ArrayList<>(listBeingTraversed);
    this.currentIndex = initialOffset;
    //when mowing forward we need to reduce step by one due to element removal from list
    //when moving in reverse, step has correct value
    if (step > 0) {
      step = step - 1;
    }
    this.step = step;
  }

  @Override
  public boolean hasNext() {
    return ourCopyOfListBeingTraversed.size() > 0;
  }

  @Override
  public Integer getNext() {
    int listSize = ourCopyOfListBeingTraversed.size();
    currentIndex = (listSize + currentIndex) % listSize;
    Integer result = ourCopyOfListBeingTraversed.remove(currentIndex);
    currentIndex = currentIndex + step;
    return result;
  }
}
