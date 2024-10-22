package victor.training.cleancode.kata.expense;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import victor.training.testing.tools.CaptureSystemOutput;
import victor.training.testing.tools.CaptureSystemOutput.OutputCapture;

class ExpenseReportTest {

    private final ExpenseReport report = new ExpenseReport();
    private final Expense dinnerExpense = new Expense("DINNER", 6000);
    private final Expense breakfastExpense = new Expense("BREAKFAST", 3000);
    private final Expense carRentalExpense = new Expense("CAR_RENTAL", 7000);

    @Test
    @CaptureSystemOutput
    void decent(OutputCapture outputCapture) {
        report.printReport(List.of(
                new Expense("DINNER", 100),
                new Expense("DINNER", 100),
                new Expense("BREAKFAST", 50),
                new Expense("CAR_RENTAL", 200)
        ));

        Assertions.assertThat(outputCapture.toString()).isEqualToIgnoringWhitespace(
                """
        Expenses Report
        Dinner	100	\s
        Dinner	100	\s
        Breakfast	50	\s
        Car Rental	200	\s
        Meal expenses: 250
        Total expenses: 450
        """);
    }

    @Test
    @CaptureSystemOutput
    void bigBreakfast(OutputCapture outputCapture) {
        report.printReport(List.of(
                new Expense("BREAKFAST", 1200)
        ));

        Assertions.assertThat(outputCapture.toString()).isEqualToIgnoringWhitespace(
                """
        Expenses Report
        Breakfast	1200	X
        Meal expenses: 1200
        Total expenses: 1200
        Meal expenses exceed limit
        """);
    }

    @Test
    @CaptureSystemOutput
    void bigDinner(OutputCapture outputCapture) {
        report.printReport(List.of(
                new Expense("DINNER", 5200)
        ));

        Assertions.assertThat(outputCapture.toString()).isEqualToIgnoringWhitespace(
                """
        Expenses Report
        Dinner	5200	X
        Meal expenses: 5200
        Total expenses: 5200
        Meal expenses exceed limit
        """);
    }

    @Test
    void mealOver() {

        boolean isDinnerExpenseOver = dinnerExpense.isMealOver();
        boolean isBreakfastExpenseOver = breakfastExpense.isMealOver();

        Assertions.assertThat(isDinnerExpenseOver).isTrue();
        Assertions.assertThat(isBreakfastExpenseOver).isTrue();

    }

    @Test
    void getEnglishName() {

        String dinnerEnglishName = dinnerExpense.getEnglishName();
        String breakfastEnglishName = breakfastExpense.getEnglishName();
        String carRentalName = carRentalExpense.getEnglishName();

        Assertions.assertThat(dinnerEnglishName).isEqualTo("Dinner");
        Assertions.assertThat(breakfastEnglishName).isEqualTo("Breakfast");
        Assertions.assertThat(carRentalName).isEqualTo("Car Rental");

    }

    @Test
    @CaptureSystemOutput
    void printSingleExpense(OutputCapture outputCapture) {
        dinnerExpense.printExpense();
        carRentalExpense.printExpense();

        Assertions.assertThat(outputCapture.toString()).isEqualToIgnoringWhitespace(
                """
        Dinner	6000	X
        Car Rental	7000
        """);

    }

}
