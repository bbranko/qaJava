package qajava.lessons.patterns.other;

/**
 * Dependency injection in its base is...
 */
public class DependencyInjection {

  public static void main(String[] args) {
    //instead of having class constructing its dependencies by itself
    DependantClassWithInternalizedConstruction dependantObject = new DependantClassWithInternalizedConstruction();

    //we can manage all classes and pass to their constructor whatever they need
    Dependency1 dependency1 = new Dependency1();
    Dependency2 dependency2 = new Dependency2();

    DependantClass10 dependantClass10 = new DependantClass10(dependency1);
    //we can even reuse dependencies if it makes sense in our context
    DependantClass20 dependantClass20 = new DependantClass20(dependency1, dependency2);
    //Fact that dependencies are no longer being constructed by the class itself, but being injected into it from outside, is dependency injection.
    //Furthermore, since creation of dependencies is now outside, classes them self do not control when they will be created nor what exact dependency
    // that will be passed to them, which is one example of application of Inversion of Control principle.

    //Separate all this into annotation processing method that returns pollable constructed classes with their dependencies resolved and stored(Context)
    // and you get how Spring creates ApplicationContext
  }

}

class Dependency1{}
class Dependency2{}


class DependantClassWithInternalizedConstruction{
  Dependency1 dependency1;
  Dependency2 dependency2;

  public DependantClassWithInternalizedConstruction() {
    dependency1 = new Dependency1();
    dependency2 = new Dependency2();
  }
}


class DependantClass10 {
  Dependency1 dependency1;

  public DependantClass10(Dependency1 dependency1) {
    this.dependency1 = dependency1;
  }
}
class DependantClass20 {
  Dependency1 dependency1;
  Dependency2 dependency2;

  public DependantClass20(Dependency1 dependency1, Dependency2 dependency2) {
    this.dependency1 = dependency1;
    this.dependency2 = dependency2;
  }
}
