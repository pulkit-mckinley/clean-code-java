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

            isMealOver |= expense.isMealOver();
            expense.printExpense();

            totalExpense += expense.amount;
        }

        printTotalExpenses(mealExpenses, totalExpense, isMealOver);
    }

    private void printTotalExpenses(int mealExpenses, int totalExpense, boolean isMealOver) {
        System.out.println("Meal expenses: " + mealExpenses);
        System.out.println("Total expenses: " + totalExpense);
        if (isMealOver) {
            System.out.println("Meal expenses exceed limit");
        }
    }

}
