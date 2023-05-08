package qajava.tasks.dataStructures;

import java.util.Objects;

//doubly linked list, differs from single linked list only in structure
//doubly linked list has also a pointer to a previous element
//which in real life application can make some things easier and faster
//though in our limited example will only slightly affect wiring of elements
public class DoublyLinkedList implements IntList {

  protected Integer value;
  protected DoublyLinkedList next;
  protected DoublyLinkedList prev;


  @Override
  public void add(Integer value) {
    if (this.value == null) {
      this.value = value;
    } else {
      if (next == null) {
        next = new DoublyLinkedList();
        next.prev = this; //only diff here
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

  //written like this it will actually also work for doubly linked list out of the box
  @Override
  public void remove(Integer value) {
    final boolean doIContain = Objects.equals(this.value, value);

    if (doIContain) {
      removeValueFromIndex0();
    } else if (next != null) {
      next.remove(value);
    }

  }

  //works also for doubly linked list
  @Override
  public Boolean contains(Integer value) {
    final boolean doIContain = Objects.equals(this.value, value);
    final boolean doesNextContain = next != null && next.contains(value);

    return doIContain || doesNextContain;
  }

  protected DoublyLinkedList getElementAtIndex(int index) {
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

  //works also for doubly linked list
  @Override
  public Integer get(Integer index) {
    if (index < 0) {
      throw new IndexOutOfBoundsException("Index cannot be negative!");
    }
    if (value == null) {
      throw new IndexOutOfBoundsException("List is empty!");
    }

    DoublyLinkedList elementAtAnIndex = getElementAtIndex(index);

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
      DoublyLinkedList newElement = new DoublyLinkedList();
      newElement.value = this.value;
      newElement.next = next;
      newElement.prev = this; //only diff here

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
      DoublyLinkedList newElement = new DoublyLinkedList();
      newElement.value = value;
      newElement.next = next;

      //only diff here
      newElement.prev = this;
      if(newElement.next != null) {
        newElement.next.prev = newElement;
      }
      //--

      next = newElement;
    } else {
      next.addAtIndex(index - 1, value);
    }

  }

}
