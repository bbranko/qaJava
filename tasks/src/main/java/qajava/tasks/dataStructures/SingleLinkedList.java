package qajava.tasks.dataStructures;

public class SingleLinkedList implements IntList {

  private Integer value;

  @Override
  public void add(Integer value) {
    this.value = value;
  }

  @Override
  public void remove(Integer value) {
    this.value = null;
  }

  @Override
  public Boolean contains(Integer value) {
    return this.value == value;
  }

  @Override
  public Integer get(Integer index) {
    return this.value;
  }

  @Override
  public void addAtIndex(Integer index, Integer value) {
    this.value = value;
  }
}
