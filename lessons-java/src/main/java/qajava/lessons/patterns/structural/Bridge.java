package qajava.lessons.patterns.structural;

/**
 * Bridge is adapter pattern that you implement in advance to separate (usually complex) abstraction from (usually complex) implementation.
 * Furthermore, bridge is facade pattern that you implement in advance, since each "dimension" of end result should be assigned its own
 * class hierarchy.
 * <p>
 * If done right, it allows for independent development/maintenance of each of its constituent parts, such as client facing abstraction,
 * or any part of underlying implementation.
 * <p>
 * If done prematurely, it adds non-trivial amount of complexity and therefore great care should be taken when considering it.
 * <p>
 * In layman terms, bridge pattern is about how to achieve object Composition in a flexible and manageable manner.
 * (where amount of objects being composed is from 1 to n)
 */
public class Bridge {
  //example usage
  public static void main(String[] args) {
    //Bridge pattern as a separation of client facing API and concrete implementation API for ease of maintenance
    ImplementationInterface concreteImpl10 = new ConcreteImplementation10();
    //we can than use our concrete implementation with basic API
    BridgeSeparatingApiFromImpl basicApi10 = new BridgeSeparatingApiFromImpl(concreteImpl10);
    basicApi10.processData(5);
    //or we can use extended API
    ExtendedBridgeSeparatingApiFromImpl extendedApi10 = new ExtendedBridgeSeparatingApiFromImpl(concreteImpl10);
    extendedApi10.consumeEvenMoreData(5);

    //and should we have another concrete implementation, we can just wrap it in desired API (Bridge) and work on
    ImplementationInterface concreteImpl20 = new ConcreteImplementation20();
    BridgeSeparatingApiFromImpl basicApi20 = new BridgeSeparatingApiFromImpl(concreteImpl20);
    basicApi20.processData(5);
    //--


    //Bridge pattern as means of achieving composition of two (or more) independent(decoupled) class hierarchies.
    Shape square = new Square();
    Color red = new Red();
    BridgeForShapes compositeObject = new BridgeForShapes(square,red);
    compositeObject.draw();
  }
}

//--
//Example of using Bridge pattern to separate API form actual implementation
//One way to think about is that we are preemptively implementing Adapter pattern,
//only we are doing it so that we can easier maintain on one side API and on the other Implementation of it
//by means of decoupling one from the other.
//--

//interface that actual underlying implementations will have
//this what Bridge will use, usually it is more fine-grained/lower/basic API than what bridge exposes
interface ImplementationInterface {
  Integer produceData();
  void consumeData(Integer data);
  void doMoreWork();
}

//some concrete implementations, idea being that we can combine anyone of them with the bridge,
//and it will all auto-magically work in desired manner
class ConcreteImplementation10 implements  ImplementationInterface{
  @Override
  public Integer produceData() {
    System.out.println("Producing 10");
    return 10;
  }

  @Override
  public void consumeData(Integer data) {
    System.out.println("Consuming " + data + " x10");
  }

  @Override
  public void doMoreWork() {
    System.out.println("Doing work x10");
  }
}

class ConcreteImplementation20 implements  ImplementationInterface{
  @Override
  public Integer produceData() {
    System.out.println("Producing 20");
    return 20;
  }

  @Override
  public void consumeData(Integer data) {
    System.out.println("Consuming " + data + " x20");
  }

  @Override
  public void doMoreWork() {
    System.out.println("Doing work x20");
  }
}

//Bridge pattern that implements actual client facing API
class BridgeSeparatingApiFromImpl {
  final ImplementationInterface concreteImpl;

  public BridgeSeparatingApiFromImpl(ImplementationInterface concreteImpl) {
    this.concreteImpl = concreteImpl;
  }

  //...API call can be a simple pass through to underlying implementation
  public void doMoreWork(){
    concreteImpl.doMoreWork();
  }

  //...but it is often meant to be meaningful abstraction of combination of simpler commands
  public Integer processData(Integer data){
    concreteImpl.consumeData(data);
    return concreteImpl.produceData();
  }
}

//...and we can even extend it to add more functionality
class ExtendedBridgeSeparatingApiFromImpl extends BridgeSeparatingApiFromImpl{

  public ExtendedBridgeSeparatingApiFromImpl(ImplementationInterface concreteImpl) {
    super(concreteImpl);
  }

  //some api extension:
  public void consumeEvenMoreData(Integer data){
    concreteImpl.consumeData(data);
    concreteImpl.doMoreWork();
  }
}

//--
//Example of using Bridge pattern for proper uncoupled object composition.
//--
//imagine we initially had shape hierarchy as so
abstract class BaseShape {}
class BasicSquare extends BaseShape {}
class BasicTriangle extends BaseShape {}

//now imagine we want to extend Shape with Color
//Stupid way to do it would be to try and add all concrete variations ie:
class RedSquare extends BaseShape {}
class BlueSquare extends BaseShape {}
class RedCircle extends BaseShape {}
class BlueCircle extends BaseShape {}
//etc...

//Better way would be to maybe add Color through one more step in inheritance tree
abstract class ColoredShape extends BaseShape {}
class ColoredSquare extends ColoredShape {}
class ColoredTriangle extends ColoredShape {}

//or maybe we could add Color to BaseShape with composition
abstract class BaseColor {}
abstract class BaseShapeWithColor {
  BaseColor myColor;
}
class SquareWithColor extends BaseShapeWithColor {}
class TriangleWithColor extends BaseShapeWithColor {}
//In both cases we are affecting original inheritance tree and coupling it to another inheritance tree
//Both of which may introduce non-trivial changes with non-trivial and hard to foresee effects.


//Instead, it would be smarter to leave separate aspects(dimensions) separate, meaning we should not mix their hierarchies
//and do a proper non-coupled composition via bridge pattern
interface Shape {}
class Square implements Shape {}
class Triangle implements Shape {}

interface Color {}
class Red implements Color {}
class Blue implements Color {}

//now we implement Bridge(Composite) which will present unification of Shape and Color and define universal API
//which we will be able to change independently of underlying constituent parts
class BridgeForShapes {
  final Shape shape;
  final Color color;

  public BridgeForShapes(Shape shape, Color color) {
    this.shape = shape;
    this.color = color;
  }

  //example api
  void draw(){
    System.out.println(
      "I am " + shape.getClass().getSimpleName() +
      " of color " + color.getClass().getSimpleName()
    );
  }
}


