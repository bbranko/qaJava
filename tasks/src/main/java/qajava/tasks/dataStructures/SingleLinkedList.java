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

  private void removeValueFromIndex0() {
    if (next == null) {
      //if this is the only value, just remove it
      this.value = null;
    } else {
      // if there is next value in list, next value becomes first
      this.value = next.value;
      this.next = next.next;
    }
  }

  @Override
  public void remove(Integer value) {
    final boolean doIContain = Objects.equals(this.value, value);

    if (doIContain) {
      removeValueFromIndex0();
    } else if (next != null) {
      next.remove(value);
    }

  }

  @Override
  public Boolean contains(Integer value) {
    final boolean doIContain = Objects.equals(this.value, value);
    final boolean doesNextContain = next != null && next.contains(value);

    return doIContain || doesNextContain;
  }

  private SingleLinkedList getElementAtIndex(int index) {
    //this is OUR private method.
    //We will assume that we are always using it correctly, all validations and checks will be done outside it!
    if (index == 0) {
      //this is always first (index 0) element
      return this;
    } else if (next != null) {
      //for other indexes, we have to traverse rest of the list
      return next.getElementAtIndex(index - 1);
    }

    return null;
  }

  @Override
  public Integer get(Integer index) {
    if (index < 0) {
      throw new IndexOutOfBoundsException("Index cannot be negative!");
    }
    if (value == null) {
      throw new IndexOutOfBoundsException("List is empty!");
    }

    SingleLinkedList elementAtAnIndex = getElementAtIndex(index);

    if (elementAtAnIndex == null) {
      throw new IndexOutOfBoundsException("Index out of bounds!");
    } else {
      return elementAtAnIndex.value;
    }
  }

  private void insertValueAtIndex0(Integer value) {
    if (this.value == null) {
      //if list was empty, we add first value
      this.value = value;
    } else {
      //if it was not, we need to insert an element before first element,
      //and we will do that by copying first element and rewiring
      SingleLinkedList newElement = new SingleLinkedList();
      newElement.value = this.value;
      newElement.next = next;

      this.value = value;
      next = newElement;
    }
  }

  @Override
  public void addAtIndex(Integer index, Integer value) {
    if (index < 0) {
      throw new IndexOutOfBoundsException("Index cannot be negative!");
    }
    if (index > 1 && next == null) {
      throw new IndexOutOfBoundsException("Index out of bounds!");
    }

    if (index == 0) {
      insertValueAtIndex0(value);
    } else if (index == 1) {
      SingleLinkedList newElement = new SingleLinkedList();
      newElement.value = value;
      newElement.next = next;

      next = newElement;
    } else {
      next.addAtIndex(index - 1, value);
    }
  }

  public String valuesToString() {
    StringBuilder valuesString = new StringBuilder();

    if (value != null) {
      SingleLinkedList next = this;
      do {
        valuesString.append(next.value).append(", ");
        next = next.next;
      } while (next != null);
      valuesString.delete(valuesString.length() - 2, valuesString.length());
    }

    return valuesString.toString();
  }

  @Override
  public String toString() {
    return "SingleLinkedList[" + valuesToString() + ']';
  }
}
