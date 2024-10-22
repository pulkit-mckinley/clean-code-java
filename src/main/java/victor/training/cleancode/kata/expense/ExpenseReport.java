package victor.training.cleancode.kata.expense;

import java.util.List;

public class ExpenseReport {

    public void printReport(List<Expense> expenses) {
        int totalExpense = 0;
        int mealExpenses = 0;
        boolean isMealOver = false;

        System.out.println("Expenses Report");

        for (Expense expense : expenses) {
            if (expense.isBreakfastOrDinner()) {
                mealExpenses += expense.amount;
            }

            String en = null;
            switch (expense.type) {
                case "DINNER":
                    en = "Dinner";
                    break;
                case "BREAKFAST":
                    en = "Breakfast";
                    break;
                case "CAR_RENTAL":
                    en = "Car Rental";
                    break;
            }

            // meal over expenses
            String m = expense.type.equals("DINNER") && expense.amount > 5000 || expense.type.equals("BREAKFAST") && expense.amount > 1000 ? "X" : " ";
            isMealOver |= m.equals("X");

            System.out.println(en + "\t" + expense.amount + "\t" + m);

            totalExpense += expense.amount;
        }

        System.out.println("Meal expenses: " + mealExpenses);
        System.out.println("Total expenses: " + totalExpense);
        if (isMealOver) {
            System.out.println("Meal expenses exceed limit");
        }
    }
}
