import java.util.HashSet;
import java.util.Set;

public class MemoryLeak {

  static Set<Node> set = new HashSet();

  public static void main(String[] args) {

    //Java is much more resilient than it was \o/

    //String pool is in heap and GCable
//    while (true) {
//      String s = String.valueOf(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
//      s.intern();
//    }

    //Simple cyclic references are caught and collectable
//    while (true) {
//      Node n1 = new Node();
//      Node n2 = new Node();
//      Node n3 = new Node();
//      n1.n = n2;
//      n2.n = n3;
//      n3.n = n1;
//    }

    //Doing stupid things will still cause OutOfMemory though
//    while (true) {
//      set.add(new Node());
//    }
  }
}

class Node {
  Node n;
}

