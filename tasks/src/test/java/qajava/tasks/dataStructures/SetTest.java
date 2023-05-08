package qajava.tasks.dataStructures;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class SetTest {

  final int TEST_VALUE_1 = 1;
  final int TEST_VALUE_2 = 2;
  final int NON_EXISTENT_VALUE = Integer.MAX_VALUE;

  @Test
  void add() {
    //setup
    Set set = new Set();

    //operation under test
    set.add(TEST_VALUE_1);

    //test
    assertTrue(set.contains(TEST_VALUE_1));
  }

  @Test
  void add_with2elements() {
    //setup
    Set set = new Set();

    //operation under test
    set.add(TEST_VALUE_1);
    set.add(TEST_VALUE_2);

    //test
    assertTrue(set.contains(TEST_VALUE_1));
    assertTrue(set.contains(TEST_VALUE_2));
  }

  @Test
  void add_confirmNoDuplicates() {
    //setup
    Set set = new Set();

    //operation under test
    set.add(TEST_VALUE_1);
    set.add(TEST_VALUE_1);
    set.remove(TEST_VALUE_1);


    //test
    assertFalse(set.contains(TEST_VALUE_1));
  }

  @Test
  void add_confirmNoDuplicates_withTwoValues() {
    //setup
    Set set = new Set();

    //operation under test
    set.add(TEST_VALUE_1);
    set.add(TEST_VALUE_2);
    set.add(TEST_VALUE_1);
    System.out.println(set); //instead of log

    set.remove(TEST_VALUE_1);
    System.out.println(set); //instead of log


    //test
    assertFalse(set.contains(TEST_VALUE_1));
    assertTrue(set.contains(TEST_VALUE_2));
  }

  @Test
  void remove() {
    //setup
    Set set = new Set();
    set.add(TEST_VALUE_1);

    //operation under test
    set.remove(TEST_VALUE_1);

    //test
    assertFalse(set.contains(TEST_VALUE_1));
  }

  @Test
  void remove_oneOfTwoValues() {
    //setup
    Set set = new Set();
    set.add(TEST_VALUE_1);
    set.add(TEST_VALUE_2);

    //operation under test
    set.remove(TEST_VALUE_1);

    //test
    assertFalse(set.contains(TEST_VALUE_1));
    assertTrue(set.contains(TEST_VALUE_2));
  }

  @Test
  void contains() {
    Set set = new Set();
    set.add(TEST_VALUE_1);

    assertTrue(set.contains(TEST_VALUE_1));
    assertFalse(set.contains(NON_EXISTENT_VALUE));
  }

  @Test
  void contains_withTwoValues() {
    Set set = new Set();
    set.add(TEST_VALUE_1);
    set.add(TEST_VALUE_2);

    assertTrue(set.contains(TEST_VALUE_1));
    assertTrue(set.contains(TEST_VALUE_2));
    assertFalse(set.contains(NON_EXISTENT_VALUE));
  }
}
