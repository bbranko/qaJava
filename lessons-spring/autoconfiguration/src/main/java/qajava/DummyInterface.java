package qajava;

public interface DummyInterface {
  default void print(){
    System.out.println("Default implementation");
  };
}
