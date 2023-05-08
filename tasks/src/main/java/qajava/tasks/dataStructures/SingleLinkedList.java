package qajava.tasks.dataStructures;

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
    if (this.value == value) {
      return true;
    } else {
      if (next == null) {
        return false;
      } else {
        return next.contains(value);
      }
    }
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
