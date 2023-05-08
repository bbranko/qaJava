package qajava.tasks.dataStructures;

import java.util.Objects;

public class SingleLinkedList implements IntList {

  protected Integer value;
  private SingleLinkedList next;


  @Override
  public void add(Integer value) {
    if (this.value == null) {
      this.value = value;
    } else {
      if (next == null) {
        next = new SingleLinkedList();
      }
      next.add(value);
    }

  }

  @Override
  public void remove(Integer value) {
    this.value = null;
  }

  @Override
  public Boolean contains(Integer value) {
    final boolean doIContain = Objects.equals(this.value, value);
    final boolean doesNextContain = next != null && next.contains(value);

    return doIContain || doesNextContain;
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
