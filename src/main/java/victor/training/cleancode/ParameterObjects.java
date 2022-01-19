package victor.training.cleancode;

public class ParameterObjects {
   public static void main(String[] args) {
      new ParameterObjects().placeOrder(new FullName("John", "Doe"), new Address("St. Albergue", "Paris", 99));
   }

   public void placeOrder(FullName fullName, Address address) {
      if (fullName.fName() == null || fullName.getlName() == null) throw new IllegalArgumentException();

      System.out.println("Some Logic");
      System.out.println("Shipping to " + address.getCity() + " on St. " + address.getStreetName() + " " + address.getStreetNumber());

   }
}

class AnotherClass {
   public void otherMethod(String firstName, String lastName, int x) {
      if (firstName == null || lastName == null) throw new IllegalArgumentException();

      System.out.println("Another distant Logic " + x);
      System.out.println("Person: " + lastName);
   }
}

class Person {
   private Long id;
   private String firstName;
   private String lastName;
   private String phone;

   public Person(String firstName, String lastName) {
      if (firstName == null || lastName == null) throw new IllegalArgumentException();
      this.firstName = firstName;
      this.lastName = lastName;
   }

   // TODO hard-core: implement setter
   public void setLastName(String lastName) {
      this.lastName = lastName;
   }

   public String getFirstName() {
      return firstName;
   }

   public String getLastName() {
      return lastName;
   }
}

class PersonService {
   public void f(Person person) {
      System.out.println("Hi there, " + person.getFirstName());

      String fullNameStr = person.getFirstName() + " " + person.getLastName().toUpperCase();
      System.out.println("Record for " + fullNameStr);
   }

   public void p(Address address) {
      System.out.println("Living in " + address.getStreetName() + " on St. " + address.getCity() + " " + address.getStreetNumber());
   }

   public void pcaller() {
       p(new Address("Dristor", "Bucharest", 91));
   }
}