package victor.training.cleancode;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UtilsVsVO {
   public List<CarModel> filterCarModels(CarSearchCriteria criteria, List<CarModel> models) {
         Interval criteriaInterval = new Interval(criteria.getStartYear(), criteria.getEndYear());
      List<CarModel> results = new ArrayList<>(models);
      results.removeIf(model -> !model.getYearInterval().intersects(criteriaInterval));
      System.out.println("More filtering logic");
      return results;
   }
}
class Altu {
   public static void main(String[] args) {
      System.out.println(new Interval(1, 3).intersects(new Interval(2, 4)));
   }
}
class Interval {
   private final int start;
   private final int end;
   public Interval(int start, int end) {
      this.start = start;
      this.end = end;
   }
   public boolean intersects(Interval other) {
      return start <= other.end && other.start <= end;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Interval interval = (Interval) o;
      return start == interval.start &&
             end == interval.end;
   }

   @Override
   public int hashCode() {
      return Objects.hash(start, end);
   }
}

//@Document
//class Incident {
//   Long id;
//   String name;
//   Status status;
//   Interval fixIntervalDays;
//}

class MathUtil {

}


class CarSearchCriteria {
   private final int startYear;
   private final int endYear;
   private final String make;

   public CarSearchCriteria(int startYear, int endYear, String make) {
      this.make = make;
      if (startYear > endYear) throw new IllegalArgumentException("start larger than end");
      this.startYear = startYear;
      this.endYear = endYear;
   }

   public int getStartYear() {
      return startYear;
   }

   public int getEndYear() {
      return endYear;
   }

   public String getMake() {
      return make;
   }
}

class CarModel {
   private final String make;
   private final String model;
   private final int startYear;
   private final int endYear;

   public CarModel(String make, String model, int startYear, int endYear) {
      this.make = make;
      this.model = model;
      if (startYear > endYear) throw new IllegalArgumentException("start larger than end");
      this.startYear = startYear;
      this.endYear = endYear;
   }

   public int getEndYear() {
      return endYear;
   }

   public int getStartYear() {
      return startYear;
   }

   public String getMake() {
      return make;
   }

   public String getModel() {
      return model;
   }

   public Interval getYearInterval() {
      return new Interval(startYear, endYear);
   }
}