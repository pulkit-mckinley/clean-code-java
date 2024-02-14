package victor.training.cleancode.immutable.advanced;

import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class ImmutableAdvanced {
   public static void main(String[] args) {
      List<Integer> numbers = Stream.of(1, 2, 3).collect(toList()); // ArrayList

      Immutable immutable = new Immutable(1, ImmutableList.copyOf(numbers), new Other(15));
      System.out.println("Before: " + immutable);

      wilderness(immutable);

      System.out.println("After:  " + immutable);
   }

   private static void wilderness(Immutable immutable) {
      // dark, deep logic
//      immutable.getNumbers().clear();
   }
}
// only shallow immutable. to make it deep immutable,
class Immutable {
   private final Integer x;
   private final ImmutableList<Integer> numbers;
   private final Other other;

   Immutable(Integer x, ImmutableList<Integer> numbers, Other other) {
      this.x = x;
      this.numbers = numbers;
      this.other = other;
   }
//   public List<Integer> getNumbers() {
//      return new ArrayList<>(numbers); // avoid
   // + memory
   // + misleading for clients; they might think they can modify the list
//   }
//   public List<Integer> getNumbers() {
//      return Collections.unmodifiableList(numbers); // old style
//   }
   public ImmutableList<Integer> getNumbers() { // guava
      return numbers;
   }
   public Integer getX() {
      return x;
   }
   public Other getOther() {
      return other;
   }

   public String toString() {
      return String.format("Immutable{x=%d, numbers=%s, other=%s}", x, numbers, other);
   }
}

class Other {
   private int a;

   public Other(int a) {
      this.a = a;
   }

   public int getA() {
      return a;
   }

   public void setA(int a) {
      this.a = a;
   }
}
