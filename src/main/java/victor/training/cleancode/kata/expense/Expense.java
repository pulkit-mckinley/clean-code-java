package victor.training.cleancode.kata.expense;

public class Expense {

    String type;
    int amount;

    public Expense(String type, int amount) {
        this.type = type;
        this.amount = amount;
    }

    public boolean isBreakfastOrDinner() {
        return this.type.equals("BREAKFAST") || this.type.equals("DINNER");
    }

    public String getEnglishName() {
        String en = "";
        switch (this.type) {
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
        return en;
    }

    public boolean isMealOver() {
        return this.type.equals("DINNER") && this.amount > 5000 || this.type.equals("BREAKFAST") && this.amount > 1000;
    }

    public void printExpense() {
        System.out.println(this.getEnglishName() + "\t" + this.amount + "\t" + (this.isMealOver() ? "X" : ""));
    }
}
