package victor.training.cleancode.immutable.advanced;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class ImmutableAdvanced {
   public static void main(String[] args) {
      List<Integer> numbers = Stream.of(1, 2, 3).collect(toList());

      Immutable immutable = new Immutable(1, numbers, new Other(15));
      System.out.println("Before: " + immutable);

      wilderness(immutable);

      System.out.println("After:  " + immutable);
   }

   private static void wilderness(Immutable immutable) {
      // dark deep logic
      immutable.getNumbers().add(1);
   }
}

class Immutable { // SHALLOW IMMUTABLE
   private final int x;
   private final List<Integer> numbers;
   private final Other other;

   Immutable(int x, List<Integer> numbers, Other other) {
      this.x = x;
      this.numbers = numbers;
      this.other = other;
   }
   public List<Integer> getNumbers() {
      return numbers;
   }
   public int getX() {
      return x;
   }
   public Other getOther() {
      return other;
   }

   @Override
   public String toString() {
      return String.format("Immutable{x=%d, numbers=%s, other=%s}", x, numbers, other);
   }
}

record Other(int a) {
}
