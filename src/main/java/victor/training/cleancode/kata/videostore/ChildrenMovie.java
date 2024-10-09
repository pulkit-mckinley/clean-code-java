package victor.training.cleancode.kata.videostore;

public class ChildrenMovie extends Movie {

    public ChildrenMovie(String title) {
        super(title, PriceCode.CHILDREN);
    }

    public double getAmountFor(int duration) {
        double amount = 1.5;
        if (duration > 3) {
            amount += (duration - 3) * 1.5;
        }
        return amount;
    }
}
