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
    final boolean doIContain = Objects.equals(this.value, value);

    if (doIContain) {
      if (next == null) {
        //if this is the only value, just remove it
        this.value = null;
      } else {
        // if there is next value in list, next value becomes first
        this.value = next.value;
        this.next = next.next;
      }
    } else if (next != null) {
      //else we need to traverse rest of the list
      SingleLinkedList previous = this;
      SingleLinkedList next = this.next;

      while (next != null) {
        boolean doesNextOneContain = Objects.equals(next.value, value);
        if (doesNextOneContain) {
          //if next element contains number to be removed, remove element by rewiring
          //(we simply skip the element, and Garbage Collector will take care of the rest)
          previous.next = next.next;
          return; //no need to continue.
        } else {
          //move to next element
          previous = next;
          next = next.next;
        }
      }
    }

  }

  @Override
  public Boolean contains(Integer value) {
    final boolean doIContain = Objects.equals(this.value, value);
    final boolean doesNextContain = next != null && next.contains(value);

    return doIContain || doesNextContain;
  }

  @Override
  public Integer get(Integer index) {
    if (index < 0) {
      throw new IndexOutOfBoundsException("Index cannot be negative!");
    }
    if (value == null) {
      throw new IndexOutOfBoundsException("List is empty!");
    }

    if (index == 0) {
      //first element, so we return it
      return value;
    } else {
      //we have to traverse rest of the list
      int currentIndex = 1;
      SingleLinkedList current = next;
      while (currentIndex < index && current != null) {
        currentIndex++;
        current = current.next;
      }

      if (current == null) {
        throw new IndexOutOfBoundsException("Index out of bounds!");
      } else {
        return current.value;
      }
    }
  }

  @Override
  public void addAtIndex(Integer index, Integer value) {
    if (index < 0) {
      throw new IndexOutOfBoundsException("Index cannot be negative!");
    }

    if (index == 0) {
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
    } else {
      //we need to traverse the list to find index where to insert element
      int currentIndex = 1;
      SingleLinkedList previous = this;
      while (currentIndex < index - 1 && previous != null) {
        currentIndex++;
        previous = previous.next;
      }

      if (previous == null) {
        throw new IndexOutOfBoundsException("Index out of bounds!");
      } else {
        //insert and rewire
        SingleLinkedList newElement = new SingleLinkedList();
        newElement.value = value;
        newElement.next = previous.next;
        previous.next = newElement;
      }

    }

  }
}
