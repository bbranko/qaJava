package qajava.tasks.dataStructures;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SingleLinkedListTest {

  final int TEST_VALUE = 1;
  final int NON_EXISTENT_VALUE = Integer.MAX_VALUE;



  @Test
  void add() {
    //setup
    SingleLinkedList list = new SingleLinkedList();

    //operation under test
    list.add(TEST_VALUE);

    //test
    assertTrue(list.contains(TEST_VALUE));
  }

  @Test
  void remove() {
    //setup
    SingleLinkedList list = new SingleLinkedList();
    list.add(TEST_VALUE);

    //operation under test
    list.remove(TEST_VALUE);

    //test
    assertFalse(list.contains(TEST_VALUE));
  }

  @Test
  void contains() {
    SingleLinkedList list = new SingleLinkedList();
    list.add(TEST_VALUE);

    assertTrue(list.contains(TEST_VALUE));
    assertFalse(list.contains(NON_EXISTENT_VALUE));
  }

  @Test
  void get() {
    SingleLinkedList list = new SingleLinkedList();
    list.add(TEST_VALUE);

    assertEquals(list.get(0), TEST_VALUE);
  }

  @Test
  void get_throwsOBE_ifOutOfBoundsIndexPassed() {
    SingleLinkedList list = new SingleLinkedList();

    assertThrows(IndexOutOfBoundsException.class, () -> list.get(NON_EXISTENT_VALUE));
  }


  @Test
  void addAtIndex() {
    SingleLinkedList list = new SingleLinkedList();

    list.addAtIndex(0, TEST_VALUE);

    assertEquals(list.get(0), TEST_VALUE);
  }

  @Test
  void addAtIndex_throwsOBE_ifIndexOutOfBounds() {
    SingleLinkedList list = new SingleLinkedList();

    assertThrows(IndexOutOfBoundsException.class, () -> list.addAtIndex(NON_EXISTENT_VALUE, TEST_VALUE));
  }
}
