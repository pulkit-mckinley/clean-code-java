package victor.training.cleancode.kata.videostore;

public class RegularMovie extends Movie {

    public RegularMovie(String title) {
        super(title, PriceCode.REGULAR);
    }

    public double getAmountFor(int duration) {
        double amt = 2;
        if (duration > 2) {
            amt += (duration - 2) * 1.5;
        }
        return amt;
    }
}
