package victor.training.cleancode;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

public class FullName {
   private final String firstName;
   private final String lastName;

   public FullName(String firstName, String lastName) {
      this.firstName = requireNonNull(firstName);
      this.lastName = requireNonNull(lastName);
   }

   public String getFirstName() {
      return firstName;
   }

   public String getLastName() {
      return lastName;
   }

   public FullName withLastName(String newLastname) {
      return new FullName(firstName, newLastname);
   }
}
