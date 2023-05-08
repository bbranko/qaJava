package qajava.lessons;

public class ControlStatements {
  public static void main(String[] args) {
    //there are two basic types of control structures: branching and looping
    //branching defines what to execute based on condition
    //looping repeats a piece of code over and over based on condition.

    //most basic loop is while loop, and it checks condition before executing for the first time
    int i = 0;
    while (i++ < 10) {
      System.out.println("Looping, i=" + i);
    }

    //while we are at it, also note that i++ while similar, is different from ++i
    i = 0;
    while (++i < 10) {
      System.out.println("Looping, i=" + i);
    }

    while (true) {
      System.out.println("this is infinite loop! Beware of always true conditions!!!");

      System.out.println("that is why we need `break` to escape it");
      break;
    }

    do {
      System.out.println("do while, executes at least once even though condition may be false to begin with.");
    } while (i < 10);

    //for loop have three sections
    //first is init section, next is condition section and final section is post-op section, usually reserved for incrementing counter
    //though each section is optional
    for (; ; ) {
      System.out.println("for loop without condition is same as while(true) - infinite");
      break;
    }

    for (
      System.out.println("0. Init sections triggers only once at the beginning");
      !System.out.printf("1. Condition triggers before each loop\n").checkError() && i-- >= 7;
      System.out.println("3. Last thing that executes is for post op after each iteration")
    ) {
      System.out.println("2. for loop body gets executed");
    }

    //usually for loop will look something like this:
    for (int counter = 0; counter < 10; counter++) {
      System.out.println("Let us count: " + counter);
    }
    //for-in left for a bit later
    //--


    //the most basic of branching operations is if
    for (int counter = 0; counter < 10; counter++) {
      //`if` requires a condition statement just like while.
      if (counter % 2 == 0) {
        //if statement is true, first block is executed
        System.out.println("Number " + counter + " is even.");
      } else {
        //else block is optional, and if present only executes if initial condition was false
        System.out.println("Number " + counter + " is odd.");
      }
    }

    for (int counter = 0; counter < 10; counter++) {
      if (counter > 6) {
        System.out.println("Number " + counter + " is greater than 6.");
        //`if` can be chained with else-if statement
      } else if (counter > 3) {
        System.out.println("Number " + counter + " is greater than 3.");
      } else {
        System.out.println("Number " + counter + " is greater than 0.");
      }
    }


    //only other branching operations is switch
    //though more adequate name would be `sieve` as it executes statement block that matches a first `case` it encounters
    //furthermore, more than one `case` may be executed as execution `falls through` if not `broken`
    //the most basic of branching operations is if
    for (int counter = 0; counter < 10; counter++) {


      switch (counter) {
        //case can have multiple matching values defined
        //same as: `case 1: case 2: case 3:`
        case 1, 2, 3:
          System.out.println("Counter in range 1 to 3");
        case 5:
          if (counter < 5) {
            System.out.println("Because 1,2,3 did not break they also fallthrough to 5");
          } else {
            System.out.println("This is 5");
          }
          break;
        default:
          System.out.println("Optional default case, since we don't have `case` for: " + counter);
          break; //THIS IS BAD PRACTICE!!! default should be last and thus without break...
        case 6:
          System.out.println("Though case is behind `default`, it will still trigger, as match has higher priority than default.");
      }


    }
    //note: switch cases can be strings or enums or char, byte, short, int, BUT not long
    //noteNOTE!!!: switch case must contain constant value, while `if` condition can contain evaluation as long as it evaluates to boolean value

  }
}



/*
Split into groups of 3 to 5.
Work on tasks individually.
Once everyone is done, exchange your work and compare solutions.

1. print out first n numbers of Fibonacci sequence, where n is for example 10
2. print out all prime numbers from 1 to n, where n is for example 100
3. foobarbazz:
   For a number range from 1 to n, where n is for example 100
   Write a program that:
   - printouts "foo" when number is divisible by 3
   - printouts "bar" when number is divisible by 5
   - printouts "bazz" when number is divisible by both 3 and 5

bonus:
1. implement switch statement logic via if statement
   - example program: map numbers from 1 to 7 to name of day of the week; for example if number is 1, output should be "Monday",
     but map 6 and 7 (Saturday and Sunday) to printout "It is weekend!".
2. implement for loop with while
   - example program: sum of integer numbers in range from i to j, where i<j
3. implement do_while loop with for
   - example program: printout random numbers generated by Math.rand() until value is bigger than 0.6

bonusbonus:
- implement each task as its own separate function
*/
