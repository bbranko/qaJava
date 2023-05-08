package qajava.tasks.dataStructures;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class SingleLinkedListTest {

  final int TEST_VALUE_1 = 1;
  final int TEST_VALUE_2 = 2;
  final int NON_EXISTENT_VALUE = Integer.MAX_VALUE;
  final int NEGATIVE_INDEX = -1;


  class SingleLinkedListUnderTest extends SingleLinkedList {}


  @Test
  void add() {
    //setup
    SingleLinkedList list = new SingleLinkedList();

    //operation under test
    list.add(TEST_VALUE_1);

    //test
    assertTrue(list.contains(TEST_VALUE_1));
  }

  @Test
  void add_with2elements() {
    //setup
    SingleLinkedList list = new SingleLinkedList();

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
    SingleLinkedList list = new SingleLinkedList();
    list.add(TEST_VALUE_1);

    //operation under test
    list.remove(TEST_VALUE_1);

    //test
    assertFalse(list.contains(TEST_VALUE_1));
  }

  @Test
  void remove_oneOfTwoValues() {
    //setup
    SingleLinkedList list = new SingleLinkedList();
    list.add(TEST_VALUE_1);
    list.add(TEST_VALUE_2);
    System.out.println(list); //instead of log

    //operation under test
    list.remove(TEST_VALUE_1);
    System.out.println(list); //instead of log

    //test
    assertFalse(list.contains(TEST_VALUE_1));
    assertTrue(list.contains(TEST_VALUE_2));
  }

  @Test
  void contains() {
    SingleLinkedList list = new SingleLinkedList();
    list.add(TEST_VALUE_1);

    assertTrue(list.contains(TEST_VALUE_1));
    assertFalse(list.contains(NON_EXISTENT_VALUE));
  }

  @Test
  void contains_withTwoValues() {
    SingleLinkedList list = new SingleLinkedList();
    list.add(TEST_VALUE_1);
    list.add(TEST_VALUE_2);

    assertTrue(list.contains(TEST_VALUE_1));
    assertTrue(list.contains(TEST_VALUE_2));
    assertFalse(list.contains(NON_EXISTENT_VALUE));
  }


  void contains_withRealValueCheck() {
    SingleLinkedListUnderTest list = new SingleLinkedListUnderTest();
    list.add(TEST_VALUE_1);
    list.add(TEST_VALUE_2);

    //we can access inner state with extended class if logically we cannot confirm state.
    //code smell, because it causes coupling!
    assertEquals(list.value, TEST_VALUE_1);
//    assertEquals(list.next.value, TEST_VALUE_2);
  }

  @Test
  void get() {
    SingleLinkedList list = new SingleLinkedList();
    list.add(TEST_VALUE_1);

    assertEquals(list.get(0), TEST_VALUE_1);
  }

  @Test
  void get_withTwoValues() {
    SingleLinkedList list = new SingleLinkedList();
    list.add(TEST_VALUE_1);
    list.add(TEST_VALUE_2);

    assertEquals(list.get(0), TEST_VALUE_1);
    assertEquals(list.get(1), TEST_VALUE_2);
  }

  @Test
  void get_throwsOBE_ifListEmpty() {
    SingleLinkedList list = new SingleLinkedList();

    assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));
  }

  @Test
  void get_throwsOBE_ifNegativeIndexPassed() {
    SingleLinkedList list = new SingleLinkedList();

    assertThrows(IndexOutOfBoundsException.class, () -> list.get(NEGATIVE_INDEX));
  }

  @Test
  void get_throwsOBE_ifOutOfBoundsIndexPassed() {
    SingleLinkedList list = new SingleLinkedList();
    list.add(TEST_VALUE_1);

    assertThrows(IndexOutOfBoundsException.class, () -> list.get(NON_EXISTENT_VALUE));
  }


  @Test
  void addAtIndex() {
    SingleLinkedList list = new SingleLinkedList();

    list.addAtIndex(0, TEST_VALUE_1);

    assertEquals(list.get(0), TEST_VALUE_1);
  }

  @Test
  void addAtIndex_addTwoValues() {
    SingleLinkedList list = new SingleLinkedList();


    list.addAtIndex(0, TEST_VALUE_1);
    list.addAtIndex(1, TEST_VALUE_2);

    assertEquals(list.get(0), TEST_VALUE_1);
    assertEquals(list.get(1), TEST_VALUE_2);
  }

  @Test
  void addAtIndex_throwsOBE_ifIndexNegative() {
    SingleLinkedList list = new SingleLinkedList();

    assertThrows(IndexOutOfBoundsException.class, () -> list.addAtIndex(NEGATIVE_INDEX, TEST_VALUE_1));
  }

  @Test
  void addAtIndex_throwsOBE_ifIndexOutOfBounds() {
    SingleLinkedList list = new SingleLinkedList();

    assertThrows(IndexOutOfBoundsException.class, () -> list.addAtIndex(NON_EXISTENT_VALUE, TEST_VALUE_1));
  }
}
