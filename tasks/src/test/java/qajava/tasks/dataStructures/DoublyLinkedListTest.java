package qajava.tasks.dataStructures;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

//since it is a list type with same contract, tests are the same
//and different parts will be happening only under the hood in our limited example
class DoublyLinkedListTest {

  final int TEST_VALUE_1 = 1;
  final int TEST_VALUE_2 = 2;
  final int NON_EXISTENT_VALUE = Integer.MAX_VALUE;
  final int NEGATIVE_INDEX = -1;


  @Test
  void add() {
    //setup
    DoublyLinkedList list = new DoublyLinkedList();

    //operation under test
    list.add(TEST_VALUE_1);

    //test
    assertTrue(list.contains(TEST_VALUE_1));
  }

  @Test
  void add_with2elements() {
    //setup
    DoublyLinkedList list = new DoublyLinkedList();

    //operation under test
    list.add(TEST_VALUE_1);
    list.add(TEST_VALUE_2);

    //test
    assertTrue(list.contains(TEST_VALUE_1));
    assertTrue(list.contains(TEST_VALUE_2));
  }

  @Test
  void remove() {
    //setup
    DoublyLinkedList list = new DoublyLinkedList();
    list.add(TEST_VALUE_1);

    //operation under test
    list.remove(TEST_VALUE_1);

    //test
    assertFalse(list.contains(TEST_VALUE_1));
  }

  @Test
  void remove_oneOfTwoValues() {
    //setup
    DoublyLinkedList list = new DoublyLinkedList();
    list.add(TEST_VALUE_1);
    list.add(TEST_VALUE_2);

    //operation under test
    list.remove(TEST_VALUE_1);

    //test
    assertFalse(list.contains(TEST_VALUE_1));
    assertTrue(list.contains(TEST_VALUE_2));
  }

  @Test
  void contains() {
    DoublyLinkedList list = new DoublyLinkedList();
    list.add(TEST_VALUE_1);

    assertTrue(list.contains(TEST_VALUE_1));
    assertFalse(list.contains(NON_EXISTENT_VALUE));
  }

  @Test
  void contains_withTwoValues() {
    DoublyLinkedList list = new DoublyLinkedList();
    list.add(TEST_VALUE_1);
    list.add(TEST_VALUE_2);

    assertTrue(list.contains(TEST_VALUE_1));
    assertTrue(list.contains(TEST_VALUE_2));
    assertFalse(list.contains(NON_EXISTENT_VALUE));
  }

  @Test
  void get() {
    DoublyLinkedList list = new DoublyLinkedList();
    list.add(TEST_VALUE_1);

    assertEquals(list.get(0), TEST_VALUE_1);
  }

  @Test
  void get_withTwoValues() {
    DoublyLinkedList list = new DoublyLinkedList();
    list.add(TEST_VALUE_1);
    list.add(TEST_VALUE_2);

    assertEquals(list.get(0), TEST_VALUE_1);
    assertEquals(list.get(1), TEST_VALUE_2);
  }

  @Test
  void get_throwsOBE_ifListEmpty() {
    DoublyLinkedList list = new DoublyLinkedList();

    assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));
  }

  @Test
  void get_throwsOBE_ifNegativeIndexPassed() {
    DoublyLinkedList list = new DoublyLinkedList();

    assertThrows(IndexOutOfBoundsException.class, () -> list.get(NEGATIVE_INDEX));
  }

  @Test
  void get_throwsOBE_ifOutOfBoundsIndexPassed() {
    DoublyLinkedList list = new DoublyLinkedList();
    list.add(TEST_VALUE_1);

    assertThrows(IndexOutOfBoundsException.class, () -> list.get(NON_EXISTENT_VALUE));
  }


  @Test
  void addAtIndex() {
    DoublyLinkedList list = new DoublyLinkedList();

    list.addAtIndex(0, TEST_VALUE_1);

    assertEquals(list.get(0), TEST_VALUE_1);
  }

  @Test
  void addAtIndex_addTwoValues() {
    DoublyLinkedList list = new DoublyLinkedList();

    list.addAtIndex(0, TEST_VALUE_1);
    list.addAtIndex(1, TEST_VALUE_2);

    assertEquals(list.get(0), TEST_VALUE_1);
    assertEquals(list.get(1), TEST_VALUE_2);
  }

  @Test
  void addAtIndex_throwsOBE_ifIndexNegative() {
    DoublyLinkedList list = new DoublyLinkedList();

    assertThrows(IndexOutOfBoundsException.class, () -> list.addAtIndex(NEGATIVE_INDEX, TEST_VALUE_1));
  }

  @Test
  void addAtIndex_throwsOBE_ifIndexOutOfBounds() {
    DoublyLinkedList list = new DoublyLinkedList();

    assertThrows(IndexOutOfBoundsException.class, () -> list.addAtIndex(NON_EXISTENT_VALUE, TEST_VALUE_1));
  }
}
