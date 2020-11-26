package victor.training.cleancode;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UtilsVsVO {
   // Ford Focus:     [2012 ---- 2016]
   // Search:              [2014 ---- 2018]
   public static void main(String[] args) {
      // can't afford a 2021 car
      CarSearchCriteria criteria = new CarSearchCriteria(2014, 2018, "Ford");
      CarModel fordFocusMk2 = new CarModel("Ford", "Focus", 2012, 2016);
      List<CarModel> models = new SearchEngine().filterCarModels(criteria, Arrays.asList(fordFocusMk2));
      System.out.println(models);
   }
}


class SearchEngine {

   public List<CarModel> filterCarModels(CarSearchCriteria criteria, List<CarModel> models) {
      List<CarModel> results = models.stream()
          .filter(model -> MathUtil.intersect(model.getYearInterval(), criteria.getYearInterval()))
          .collect(Collectors.toList());


      System.out.println("More filtering logic");
      return results;
   }

   private void applyCapacityFilter() {
      Interval interval1 = new Interval(1000, 1600);
      Interval interval2 = new Interval(1250, 2000);
      System.out.println(MathUtil.intersect(interval1, interval2));
   }
}

class MathUtil {
   static boolean intersect(Interval interval1, Interval interval2) {
      return interval1.getStart() <= interval2.getEnd() && interval2.getStart() <= interval1.getEnd();
   }
}

class Interval {
   private final int start;
   private final int end;

   Interval(int start, int end) {
      this.start = start;
      this.end = end;
   }

   public int getEnd() {
      return end;
   }

   public int getStart() {
      return start;
   }
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

   public Interval getYearInterval() {
      return new Interval(startYear, endYear);
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

   @Override
   public String toString() {
      return "CarModel{" +
             "make='" + make + '\'' +
             ", model='" + model + '\'' +
             '}';
   }

   public Interval getYearInterval() {
      return new Interval(startYear, endYear);
   }
}