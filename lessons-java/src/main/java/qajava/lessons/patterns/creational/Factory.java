package qajava.lessons.patterns.creational;

import lombok.Data;

/**
 * Factory pattern presents a way to centralize object creation (encapsulate and abstract it away),
 * so that instead of calling and setting object our self at all different places we can call
 * factory create method instead.
 * <p>
 * Factory create method can be configurable, and it can return multitude of different concrete objects
 * sharing the same base be it interface or abstract class...
 * <p>
 * Factory of a Factory is an abstract factory.
 * It can be used to abstract away selection of factory for family of objects.
 */
public class Factory {
  //example usage
  public static void main(String[] args) {

    //instead of knowing details of complex object creation:
    ClassWithComplexCreation manuallyCreatedObject = new ClassWithComplexCreation();
    //imagine a lot of fields being set or if there is logic as to values being set
    manuallyCreatedObject.setData(10);

    //we can instead abstract it away into factory
    FactoryForClassWithComplexCreation complexFactory = new FactoryForClassWithComplexCreation();
    //...and now we do not need to concern ourselves how object is created, just use it
    ClassWithComplexCreation defaultComplex = complexFactory.createClassWithComplexCreation();
    ClassWithComplexCreation customComplex = complexFactory.createClassWithComplexCreationParametrized(10);
    //factory also makes it easier to test object creation


    //instead of knowing details of creation for object of each subtype class
    Shape rectangle = new Rectangle();
    Shape circle = new Circle();

    //we can abstract creation away into factory and get desired type by passing type as a paramter
    SameBaseFactory baseFactory = new SameBaseFactory();
    Shape rectangle2 = baseFactory.createShape(Shape.Type.RECTANGLE);
    Shape circle2 = baseFactory.createShape(Shape.Type.CIRCLE);
    //then when we need to support more types, we just extend a method of a factory


    //instead of having to know which group of concrete implementations to create and use
    //which can be error-prone and lead to non-trivial errors
    //ie we can mix up element types like so:
    UIElement window = new WinWindow();
    UIElement button = new IOSButton();

    //we can use abstract factory to ensure we get a factory with compatible bundle of elements
    AbstractUIFactory abstractUIFactory = new AbstractUIFactory();
    UIFactory concreteUIFactory = abstractUIFactory.createUIFactory(SystemType.WINDOWS);
    //...now, all elements will be matching :)
    UIElement matchingWindow = concreteUIFactory.createWindow();
    UIElement matchingButton = concreteUIFactory.createButton();


    //end notes:
    //- as with all patterns, factory has its time and place and implementing it straight away may introduce unnecessary complexity.
    //- in Spring,
    //-- factory could be used to save details of object creation such as should class object be instantiated as singleton or regular object
    //-- or abstract factory could be used to create object factories that all belong to same Spring profile
  }
}

//for encapsulating creation demo:
@Data
class ClassWithComplexCreation {
  //imagine a lot of complex types here
  Integer data;
}

class FactoryForClassWithComplexCreation {
  ClassWithComplexCreation createClassWithComplexCreation() {
    //now all complex logic is in one place and can be reused anywhere...
    ClassWithComplexCreation manuallyCreatedObject = new ClassWithComplexCreation();
    manuallyCreatedObject.setData(10);
    return manuallyCreatedObject;
  }

  //we can even make it configurable, by passing multiple parameters or configuration objects...
  ClassWithComplexCreation createClassWithComplexCreationParametrized(Integer data) {
    //imagine here processing of passed data to create our custom object
    ClassWithComplexCreation manuallyCreatedObject = new ClassWithComplexCreation();
    manuallyCreatedObject.setData(data);
    return manuallyCreatedObject;
  }
}
//--

//for grouping creation of objects of same base type demo:
abstract class Shape {
  //good idea to have an enum for differentiating what needs to be built
  enum Type {
    RECTANGLE, TRIANGLE, CIRCLE
  }

  //other shape related fields and methods
}

class Rectangle extends Shape {
  //rectangle specific fields and methods and/or method overrides...
}

class Triangle extends Shape {
}

class Circle extends Shape {
}

class SameBaseFactory {
  Shape createShape(Shape.Type type) {
    return switch (type) {
      case RECTANGLE -> new Rectangle();
      case TRIANGLE -> new Triangle();
      case CIRCLE -> new Circle();
      default -> throw new UnsupportedOperationException("Creation of type " + type.name() + " is not yet supported!");
    };
  }
}
//--

//for abstract factory demo:
enum SystemType{
  WINDOWS, IOS, LINUX
}

interface UIElement{}

class WinWindow implements UIElement{}
class WinButton implements UIElement{}

class IOSWindow implements UIElement{}
class IOSButton implements UIElement{}


interface UIFactory{
  UIElement createWindow();
  UIElement createButton();
}

class WinFactory implements UIFactory{
  @Override
  public UIElement createWindow() {
    return new WinWindow();
  }
  @Override
  public UIElement createButton() {
    return new WinButton();
  }
}
class IOSFactory implements UIFactory{
  @Override
  public UIElement createWindow() {
    return new IOSWindow();
  }
  @Override
  public UIElement createButton() {
    return new IOSButton();
  }
}

class AbstractUIFactory{
  UIFactory createUIFactory(SystemType type){
    return switch (type){
      case WINDOWS -> new WinFactory();
      case IOS -> new IOSFactory();
      default -> throw new UnsupportedOperationException("OS not yet supported: " + type.name());
    };
  }
}
//--
