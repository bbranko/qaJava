package qajava.lessons;

// this is a comment
/*
  This
  is
  a multi line comment!
*/

/**
  This is a JavaDoc comment!
  writing it this way should trigger IDE auto-generation of basic structure
  html is supported within
  This is what it is shown in quick documentation, so be concise and helpful!
*/



//package must come first and must reflect directory structure from project root
//package kds.tuts;

//if there are any, imports must come next and before class definition

import java.util.Arrays;


//class should be the same name as the filename that it is declared in
//most basically, class is definition of a custom type
//Object is one concrete instance of that custom type (class)
public class Syntax {

  //if it main class it has to contain program entry point which is method with exact signature of: "public static void main(String[] args)"
  public static void main(String[] args) {
    //chunk of code, a code block must be wrapped in curly braces { }
    //line of inner code is either a variable definition, basic variable manipulation, a flow control statement or method invocation
    //line of inner code
    int a = 1; //variable definition
    a = a + 2; //basic variable manipulation
    if(a == 3) { //flow control statement
      System.out.println(a); //method invocation of an outer method
    }
    //FYI if function is a class function (as opposed to being a standalone function) then it is called a method ("=_=)
    //...but most tend to use function and method interchangeably


    //when defining a variable, first comes type, then variable name
    //variable names must only contain alphanumeric characters and/or _ and/or $
    //variable names should be in camelcase and starting with lowercase letter
    int thisIsANumber;

    //variable names MUST NOT contain spaces!
    //variable names may not start with numbers, be a reserved word, or a literal
    //reserved word is for example: int, if, class...
    //literal is primitive and String type values and null

//    int 7thisIs NOT Ok;

    //args is short for arguments, can have a different name but must be of type String[]
    //it is a list of arguments passed to JVM
    System.out.println(Arrays.toString(args));


    //>>> skip this at first
    //example of instantiating an object of a class and calling one of its methods
    Syntax concreteObject = new Syntax();
    concreteObject.print();
    //also note
    Syntax concreteObject2 = new Syntax();
    System.out.println(concreteObject.equals(concreteObject2)); //result is `false`, since concreteObject and concreteObject2 are to different instances
    //...thooooough you can override equals and hash methods, but that is a different talk...
  }

  //class can have fields
  //field is variable in which inner state is preserved
  //filed has modifiers defining access rights, can it be changed and is it class global variable and variable definition as described before
  int simplestField; //default access level is package local
  public int canBeAccessedDirectly;
  private int canBeUsedOnlyWithinClassInstance;
  static int classGlobalVariable;
  final int THIS_IS_A_CONSTANT_AND_CANNOT_BE_CHANGED = 0;
  //constants are usually written in all-caps separated by underscore
  //also note that `final` variables have to be initialized (assigned a value) inline or in constructor

  //class can have methods
  //method is a function of a class
  //method has access modifier, is it class global method or not, return type, name which follows same rules as variable and list of arguments
  //argument list is comma separated list of variable definitions
  //non-void methods must return a value by invoking return statement which is usually last one
  protected int thisMethodReturns5(){
    return 5;
  }
  private void thisMethodHasNoReturnValue(int butHasAnArgument){
    System.out.println(butHasAnArgument);
  }
  public void print(){
    thisMethodHasNoReturnValue(
      thisMethodReturns5()
    );
  }


  //constructor is a class method that returns one concrete instance of a class, an object
  //constructor name must be that of the class
  //constructor while it has no written return type in reality it is that of the class and return statement is the last statement executed
  //ONLY constructors are invoked via `new` statement
  //there can be many constructors, same as with methods (method overloading)
  //simplest (and default) constructor is no args public constructor
  public Syntax() {
  }
  //note that body can be empty
}


//if you really, really want, see more at
//verbose: https://en.wikipedia.org/wiki/Java_syntax
//BNF: https://cui.unige.ch/isi/bnf/JAVA/AJAVA.html
