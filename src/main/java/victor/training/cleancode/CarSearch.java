package victor.training.cleancode;

import java.util.List;
import java.util.stream.Collectors;

class CarSearch {

  // run tests
  public List<CarModel> filterCarModels(CarSearchCriteria criteria,
                                        List<CarModel> carModels) {
    List<CarModel> results = carModels.stream()
        .filter(carModel -> criteria.getYearRange().intersects(carModel.getYearRange()))
        .collect(Collectors.toList());
    System.out.println("More filtering logic ...");
    return results;
  }

}

class SomeOtherClientCode {
  private void applyLengthFilter() { // pretend
    System.out.println(new Range(1000, 1600).intersects(new Range(1250, 2000)));
  }
  private void applyCapacityFilter() { // pretend
    System.out.println(new Range(1000, 1600).intersects(new Range(1250, 2000)));
  }
}

class MathUtil {

}
//    Range (guava) or my own?


class CarSearchCriteria { // a DTO received from JSON
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

  Range getYearRange() {
    return new Range(getStartYear(), getEndYear());
  }
}

// @Entity
class CarModel { // the Entity Model👑
  // @Id
  private Long id;
  private String make;
  private String model;
  private Range yearRange; // -1 field => dev = 😊

  protected CarModel() {
  } // for Hibernate

  public CarModel(String make, String model, int startYear, int endYear) {
    this.make = make;
    this.model = model;
    if (startYear > endYear) throw new IllegalArgumentException("start larger than end");
//    this.startYear = startYear;
//    this.endYear = endYear;
    this.yearRange = new Range(startYear, endYear);
  }

  public Long getId() {
    return id;
  }

  public Range getYearRange() {
    return yearRange;
  }

  public String getMake() {
    return make;
  }

  public String getModel() {
    return model;
  }
}

class CarModelMapper {
  public CarModelDto toDto(CarModel carModel) {
    CarModelDto dto = new CarModelDto();
    dto.make = carModel.getMake();
    dto.model = carModel.getModel();
    dto.startYear = carModel.getYearRange().start();
    dto.startYear = carModel.getYearRange().start();
    dto.startYear = carModel.getYearRange().start();
    dto.startYear = carModel.getYearRange().start();
    dto.startYear = carModel.getYearRange().start();
    dto.startYear = carModel.getYearRange().start();
    dto.startYear = carModel.getYearRange().start();
    dto.startYear = carModel.getYearRange().start();
//    dto.endYear = carModel.getEndYear();
    dto.endYear = carModel.getYearRange().end();
    return dto;
  }

  public CarModel fromDto(CarModelDto dto) {
    return new CarModel(dto.make, dto.model, dto.startYear, dto.endYear);
  }
}

class CarModelDto {
  public String make;
  public String model;
  public int startYear;
  public int endYear;
}