import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;


/*
Programing languages are high level abstractions of actual zeros and ones code that will be executed.
Programing paradigm is a smart-ass way to say around which programming patterns a programing language was mainly created.
(Paradigm is greek in origin, meaning pattern...)
What that means is that writing code in particular language is only possible, is easier, more efficient or more performant if written in certain way.
BUT, everything can be written and supported in
Also note, that practically all languages are multi-paradigm languages since they all support at least few patterns of coding
Also also note, paradigms can be more general and more specific which adds to the mess of trying to understand them
Also also also!!! note - everything and anything can be written in any Turing complete language (which is all modern programing languages),
it is just a matter of aesthetics and performance!


Main paradigm in Java is Object-Oriented Programming (OOP for short), which has the following main principles:
Encapsulation - grouping stuff in same place and limiting access to parts
Abstraction - exposing api for interaction with internal state, thus ABSTRACTING how thing internally work
Inheritance - ability to share code by extending existing one
Polymorphism - ability to resolve concrete overridden method to execute when manipulating object stored within its interface or parent class
*/


/*
 Classes are pieces of code with global and/or internal methods and/or state that should represent a logical whole
 ie they could be:
 - a blueprint of an object, such as user with name, email, etc. (internal state and methods)
 -- instance of a class is called Object, and it is upon a concrete instance that non-static methods ("internal" ones) get executed!
 - group of global (static) constants, if enum is not sufficient
 - group of global (static) methods, ie utility methods, though Singleton pattern is likely preferred as it allows extension and easier testing

*/


//this is and interface
interface Talkative {
  //it contains only method signatures and nothing else
  //methods defined must be public, since we do not care about enforcing what we cannot access through defined api
  void talk();
}

//interfaces can extend other interfaces
interface Animal extends Talkative {
  //since Java 8, interface methods can have default implementation
  default void name() {
    System.out.println("Say my name, say my name ~");
    //...within it, we CAN NOT manipulate object state directly, but we can call other methods and do data manipulation as per usual
    talk();
  }
}

//class can implement many interfaces (although here Talkative is redundant as it comes already through Animal...)
abstract class Dog implements Animal, Talkative {
//abstract classes do not have to fulfill full contract of interfaces
//...but they also cannot be instantiated
//...since they do not implement full contract of interfaces...

//abstract classes are used for code sharing through inheritance
//alternative (and often more flexible) approach for code sharing is Composite pattern (will be covered later)
//--

  @Override
  public void talk() {
    System.out.println("BARK!");
  }
}

//...but, in Java, class can extend only one class
//this is java solution to the diamond problem, more info: https://en.wikipedia.org/wiki/Multiple_inheritance#The_diamond_problem
class ConcreteDog extends Dog {
  private final String name;

  public ConcreteDog(String name) {
    this.name = name;
  }

  @Override
  public void name() {
    System.out.println("This is" + name);
  }
}

//btw, there can be many classes defined in same file, but only one can be public, and it has to have same as the file within which it is defined
class ConcreteCat implements Animal {
  private final String name;

  public ConcreteCat(String name) {
    this.name = name;
  }

  @Override
  public void talk() {
    System.out.println(name + " says: MeoooW! :3");
  }
}


public class DataStructures {


  public static void main(String[] args) {

    //this is an array, arrays have FIXED size and allocate continues space in memory
    //SIZE OF AN ARRAY CANNOT BE CHANGED!
    int[] array = new int[10];
    //if size is omitted, size is minimal size needed to store initialization value.
    int[] array2 = {1, 2, 3, 4, 5};
    System.out.println("Defined and initialized array: " + Arrays.toString(array2));

    //index marks amount of type-sized offsets from memory start to where value is stored...
    int index = 2;
    array[index] = 10;
    System.out.println("Allocated array with one added value " + Arrays.toString(array));

    //this is a matrix
    int[][] matrix = new int[10][10];
    //you can think of a matrix as array of an array...

    //this is n-dimensional (in this case 5-dimensional) matrix
    int[][][][][] matrix5d = new int[10][10][10][10][10];


    // and that is why we have a List which is a dynamically allocated array
    // but List is an interface, so lets see a demo first

    //interface guarantees existence of particular methods
    //that is why we can store objects of concrete implementation
    Animal dog = new ConcreteDog("DOG");
    Animal cat = new ConcreteCat("CAT");
    //and through polymorphism, when we call said methods
    //...correct concrete methods will be invoked
    dog.talk();
    cat.talk();
    //thus, by using interfaces, you can write more universal code that does not care about underlying implementation,
    //of which there could be many due to various reasons, such as speed optimization, memory optimization, thread safety... etc.


    //back to Lists
    //List is an example of generic interface that is an extension of Collection interface
    List<Integer> list = new ArrayList<>(); //most often used concrete implementation of List interface is ArrayList
    //most notable methods that come from Collection are
    list.add(1);
    list.addAll(List.of(1, 2, 3));
    list.size(); //returns 3 at this point
    list.isEmpty(); //returns false at this point
    list.contains(1); // returns true at this point
    //following command removes passed in object from the List
    //note that, this is not same as `list.remove(1)` !!!
    //...which is an overload of method from Collection in List interface to remove value at passed in index!
    list.remove(Integer.valueOf(1));
    //also there is .iterator() which allows us to use for-in loop, which iterates over every element of a collection
    for (int x : list) {
      System.out.println(x);
    }

    //from List interface most notable functions are:
    list.get(1);
    list.sort(Integer::compareTo);
    //        ^ sort expects a Comparator to be passed, so for example we pass in default one from Integer

    //another way to define a List
    List<Integer> immutableList = List.of(1, 2, 3);
    //BUT BEWARE - this list is immutable, meaning read only,
    //although it implements List interface, methods that would mutate the list such as add or remove
    //are set to throw UnsupportedOperationException AT RUNTIME!!! ie
//    immutableList.remove(1); //this throws exception!!!


    //Set is another example of generic interface that is an extension of Collection interface
    Set<Integer> set = new HashSet<>(); //most often used concrete implementation of Set interface is HashSet
    //IMPORTANT HashSet relies on correctness of .hashCode() function to properly fulfill its contract
    //notable methods for collection are the same,
    set.add(1);
    set.add(1);// though adding more than one of the same will be ignored or will override previous value...
    set.add(2);
    System.out.println("set contents: " + set);

    //Sets can be also initialized with .of method, which will also result in immutable set
    Set<Integer> immutableSet = Set.of(1,1,2); //FURTHERMORE, passing duplicates to .of statement,
    //will throw IllegalArgumentException AT RUNTIME!!!


    //Map is a generic dictionary of key-value pairs
    Map<Integer, String> intToStringMap = new HashMap<>(); //HashMap is most common implementation used due to it speed
    //BUT same as HashSet it relies on correctness of .hachCode() method to operate properly
    //see following function to better understand the issue:
    mapKeyDemoIssue();

    //most notable methods are
    intToStringMap.put(1, "one");
    intToStringMap.putIfAbsent(1, "oNe>?"); //will have no effect since we already have value under `1`
    intToStringMap.putIfAbsent(2, "two");   //will add mapping for `2` since there was none before
    intToStringMap.get(1); //returns "one" at this point
    intToStringMap.size(); //returns 2 at this point
    intToStringMap.isEmpty(); //returns false at this point
    intToStringMap.remove(1);
  }

  //btw, this is a nested class
  //it is allowed too, though due to readability I
  static class Key {

    int x = 0;
    int y = 0;

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Key key = (Key) o;
      return x == key.x && y == key.y;
    }

    @Override
    public int hashCode() {
      return Objects.hash(x, y);
    }
  }

  static void mapKeyDemoIssue() {
    Key key = new Key();

    // Map.of has a different getter implementation than HashMap (also less efficient)
    Map<Key, String> map = Map.of(key, "test");
    System.out.println(map.get(key));
    System.out.println(key.hashCode());

    //...and if internal values of key change...
    key.x = 10;

    //...still works, as long as .equals is true!
    System.out.println(map.get(key));
    System.out.println(key.hashCode());


    //however, HashMap relies on result of .hashCode() method
    Map<Key, String> map2 = new HashMap<>();
    map2.put(key, "test");

    System.out.println(map2.get(key));
    System.out.println(key.hashCode());

    //...and if internal values of key change...
    key.y = 10;

    //...it fails in getting the value even though we are using the same object as key, because calculated hash value changed!
    System.out.println(map2.get(key));
    System.out.println(key.hashCode());
  }

}


/*
Tasks:

Define collection interface that has:
add, remove, contains

define list interface that extends collections to have:
get, addAtIndex methods

define a concrete implementations for single and doubly linked list.
One element of single linked list stores at least:
value, nextElement
where nextElement is next single list object, or null if it is last element in list
visualisation: https://cdn.programiz.com/sites/tutorial2program/files/linked-list-concept_0.png
One element of doubly linked list stores at least:
value, nextElement, previousElement
where nextElement is next doubly linked list object, or null if it is LAST element in list
and previousElement is previous doubly linked list object, or null if it is FIRST element in list
visualisation: https://cdn.programiz.com/sites/tutorial2program/files/doubly-linked-list-concept.png

define a concrete implementations for Set.
set is a collection which does not have same objects in it.

Let the data stored be int
Override toString method of all concrete classes so that it is prints out stored contents!
Feel free to write everything in one file, though this is last time we will be doing it like this :)
*/
