package victor.training.cleancode.kata.videostore;

public class NewReleaseMovie extends Movie {

    public NewReleaseMovie(String title) {
        super(title, PriceCode.NEW_RELEASE);
    }

    public double getAmountFor(int duration) {
        return duration * 3;
    }
}
