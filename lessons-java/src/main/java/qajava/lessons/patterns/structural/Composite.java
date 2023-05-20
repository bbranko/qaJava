package qajava.lessons.patterns.structural;

import java.util.List;

/**
 * Not to be confused with Composition in general like one from "Composition vs Inheritance" discussion!
 * <p>
 * Composite pattern is idea of object and group of objects sharing same interface so that you
 * can dynamically "compose" an object of any complexity yet still work with it in same manner as with any part of it.
 */
public class Composite {
  //example usage
  public static void main(String[] args) {
    //we can compose objects in any way we like
    Drawable rectangle1 = new Rectangle();
    Drawable rectangle2 = new Rectangle();
    Drawable rectangleCanvas = new Canvas(List.of(rectangle1, rectangle2));
    Drawable circle1 = new Circle();
    Drawable circle2 = new Circle();
    Drawable circleCanvas = new Canvas(List.of(circle1, circle2));
    Drawable rectangle3 = new Rectangle();
    Drawable circle3 = new Circle();
    //we can even mix and match them since they all share common interface
    Drawable mixedCanvas = new Canvas(List.of(rectangleCanvas, circle3, circleCanvas, rectangle3));

    //and in the end we can work with all of them in same way, exactly because they share same interface
    System.out.println("Interact with simple object:");
    rectangle1.draw();
    System.out.println("Interact with complex object:");
    mixedCanvas.draw();
  }
}

//one possible usage would be for scene composition
interface Drawable {
  void draw();
}

class Rectangle implements Drawable {
  @Override
  public void draw() {
    System.out.println("Drawing rectangle: " + this);
  }
}

class Circle implements Drawable {
  @Override
  public void draw() {
    System.out.println("Drawing circle: " + this);
  }
}

class Canvas implements Drawable {
  final List<Drawable> canvasElements;

  public Canvas(List<Drawable> canvasElements) {
    this.canvasElements = canvasElements;
  }

  @Override
  public void draw() {
    System.out.println("> Drawing canvas: " + this + ". Canvas elements are:");
    System.out.println("---");
    canvasElements.forEach(Drawable::draw);
    System.out.println("===");
  }
}
