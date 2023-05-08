package qajava.lessons;

import java.math.BigDecimal;

public class PrimitiveTypes {

/*
conversions:
        char --v
byte > short > int > long > float > double

last primitive type is boolean
*/


  public static void main(String[] args) {
    char character = 'a';
    System.out.printf("char holds a single character, ie: %c\n\n", character);


    boolean bool = true;
    //Note that SpringBuilder is NOT thread safe - StringBuffer is, but it is rarely needed
    StringBuilder builder = new StringBuilder();
    builder.append("And last primitive type is ")
      .append("boolean, for example: ")
      .append(bool);
    String boolInfo = builder.toString();
    System.out.println(boolInfo + '\n');


    System.out.println("Logical operations (used in conditions):");
    System.out.println("NOT: !true == " + !true);
    System.out.println("AND: (true && true) == " + (true && true));
    System.out.println("AND: (true && false) == " + (true && false));
    System.out.println("OR: (false && false) == " + (false && false));
    System.out.println("OR: (true && false) == " + (true && false));
    System.out.println("NOT TO BE CONFUSED WITH BITWISE OPERATIONS: & or |");
    System.out.println();


    String byteInfo = String.format("Byte: %d - %d", Byte.MIN_VALUE, Byte.MAX_VALUE);
    String shortInfo = "Short: %d - %d".formatted(Short.MIN_VALUE, Short.MAX_VALUE);
    String intInfo = "Integer: %d - %d".formatted(Integer.MIN_VALUE, Integer.MAX_VALUE);
    String longInfo = "Long: %d - %d".formatted(Long.MIN_VALUE, Long.MAX_VALUE);
    //though .formatted is present only since Java 15.

    String wholeTypes = String.join("\n", byteInfo, shortInfo, intInfo, longInfo);
    System.out.println(wholeTypes + '\n');


    //actually uses StringBuilder under the hood
    String decimalTypes = "" +
      "Float: " + Float.MIN_VALUE + " - " + Float.MAX_VALUE + '\n' +
      "Double: " + Double.MIN_VALUE + " - " + Double.MAX_VALUE;
    System.out.println(decimalTypes);
    System.out.println("decimalTypes have precision, 6-7 decimal spaces for float and 14-16 for double");
    System.out.println("due to precision being  limited counting errors are a consequence! ie:");
    System.out.println("0.1*10 = " + (0.1 * 10));
    System.out.println("buuut 0.1+0.1+0.1+0.1+0.1+0.1+0.1+0.1+0.1+0.1 = " + (0.1 + 0.1 + 0.1 + 0.1 + 0.1 + 0.1 + 0.1 + 0.1 + 0.1 + 0.1));
    System.out.println("or 0.2+0.1 =" + (0.2 + 0.1));
    System.out.println("or 3.3/3 =" + (3.3 / 3));
    System.out.println("That is why BigDecimal is used scientifically and most banks store transactions in lowest denomination as long.");
    System.out.println("BigDecimal 0.2+0.1 = " + BigDecimal.valueOf(0.2).add(BigDecimal.valueOf(0.1)));
    System.out.println("BigDecimal 3.3/3 = " + BigDecimal.valueOf(3.3).divide(BigDecimal.valueOf(3)));
    System.out.println("BigDecimal is out of scope though, but feel free to go through docs! ;)");
    System.out.println();


    System.out.println("Relational operations:");
    System.out.println("equals: (1 == 1) == " + (1 == 1));
    System.out.println("not equals: (1 != 2) == " + (1 != 2));
    System.out.println("greater than: (2 > 2) == " + (2 > 2));
    System.out.println("greater equals: (2 >= 2) == " + (2 >= 2));
    System.out.println();

    String s1 = "abc";
    String s2 = "abc";
    String s3 = new String("abc");
    System.out.println("s1==s2 (" + (s1 == s2) + ") but s1!=s3 (" + (s1 != s3) + ")");
    System.out.println("s1==s2 (" + (s1.equals(s2)) + ") but s1!=s3 (" + (!s1.equals(s3)) + ")");
    System.out.println("thus for complex types use .equals() and/or .compareTo()");
    System.out.println();


    System.out.println("All basic (+ - / *) math ops are allowed but:");
    System.out.println("integer: 3/2 =" + 3/2);
    System.out.println("float: 3/2 =" + 3/2.0);
    System.out.println("we also have module: 3%2 = " + 3%2);
    System.out.println("although it gets funky with float: 3.3%0.1 = " + 3.3%0.1);
    System.out.println();


    System.out.println("""
      <div>
        <span> <b>Oh!</b> We can also write multi-line
          since Java 15... :)
        </span>
      </div>
      """
    );

    System.out.println("MOST IMPORTANT DISTINCTION: primitive types MAY NOT BE NULL!");
  }

}


//for great overview of java operators:
//https://www.programiz.com/java-programming/operators
