package qajava.tasks.dataStructures;

//by no means is this in any way optimal solution,
//BUT it is a solution which satisfies task specification!
public class Set implements IntCollection {

  //we will reuse previous structure for storage,
  //and only add set specific logic overtop :)
  private final SingleLinkedList values;

  public Set() {
    this.values = new SingleLinkedList();
  }

  @Override
  public void add(Integer value) {
    //only add value if unique
    if (!values.contains(value)) {
      values.add(value);
    }
  }

  //just a pass-through to underlying storage
  @Override
  public void remove(Integer value) {
    values.remove(value);
  }

  //just a pass-through to underlying storage
  @Override
  public Boolean contains(Integer value) {
    return values.contains(value);
  }

  @Override
  public String toString() {
    return "Set{" + values.valuesToString() + '}';
  }
}
