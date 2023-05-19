package qajava.lessons.patterns.creational;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/*
 * This pattern defines who is responsible for making a copy,
 * where Prototype pattern states that it is object's responsibility to know how to make a copy of itself,
 * since any other approach is too error-prone, if at all possible.
 *
 * Object of a class that can be cloned is called a Prototype, thus the name Prototype pattern.
 *
 * One can use java.lang.Cloneable interface to enforce this pattern on a class,
 * but since it got created with Java 1, due to limitations of that time design decisions it is a bit cumbersome to use.
 * java.lang.Cloneable which, while requesting implementation of clone method, has a return type of Object
 * (though, it can be same class since Java 5) and throws a checked exception that needs to be handled.
 */
//Prototype interface with f-bounded generic return type and no exception trowing.
interface PrototypeInterface<T extends PrototypeInterface> {
  T clone();
}

class PrototypeImpl1 implements PrototypeInterface<PrototypeImpl1> {

  String content;

  public PrototypeImpl1() {
  }

  //it is a good approach to define a copy constructor, that has all copy making construction logic
  //you can keep it private to enforce usage of .clone()
  private PrototypeImpl1(PrototypeImpl1 org) {
    this(); //usually, first we reuse default constructor to init an object
    this.content = new String(org.content); //not needed for String, but imagine it is a more complex type
  }

  @Override
  public PrototypeImpl1 clone() {
    return new PrototypeImpl1(this);
  }
}

//just for demonstration
class PrototypeImpl2 implements PrototypeInterface<PrototypeImpl2> {
  Integer content;
  public PrototypeImpl2() {
  }
  private PrototypeImpl2(PrototypeImpl2 org) {
    this.content = org.content;
  }
  @Override
  public PrototypeImpl2 clone() {
    return new PrototypeImpl2(this);
  }
}

class PrototypeWithJava1 implements Cloneable {

  String content;

  @Override
  public PrototypeWithJava1 clone() {
    try {
      //IMPORTANT: Object.clone does only a shallow, field-by-field, copy of an object!
      return (PrototypeWithJava1) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new RuntimeException("warning: something went wrong while cloning PrototypeWithJava1");
    }
  }
}


public class Prototype {

  //example code
  public static void main(String[] args) {
    PrototypeImpl1 orgCustom = new PrototypeImpl1();
    orgCustom.content = "some content";
    PrototypeImpl1 cloneCustom = orgCustom.clone();
    System.out.println("Not same instance: " + (orgCustom != cloneCustom));
    System.out.println("Same contents: " + Objects.equals(orgCustom.content, cloneCustom.content));

    PrototypeWithJava1 orgJava = new PrototypeWithJava1();
    orgJava.content = "some content";
    PrototypeWithJava1 cloneJava = orgJava.clone();
    System.out.println("Not same instance: " + (orgJava != cloneJava));
    System.out.println("Same contents: " + Objects.equals(orgJava.content, cloneJava.content));

    //now that we implemented Prototype we can safely clone objects knowing that each object knows how to make a copy(clone) of itself
    //even if we do not know how to do it ourselves due to use of interfaces or subclasses etc.
    List<PrototypeInterface<?>> list = List.of(new PrototypeImpl1(), new PrototypeImpl2());
    List<PrototypeInterface<?>> clonedList = new ArrayList<>();
    for(PrototypeInterface<?> cloneableObject : list){
      clonedList.add(cloneableObject.clone());
    }
  }

}

