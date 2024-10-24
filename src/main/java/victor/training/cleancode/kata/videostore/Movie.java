package victor.training.cleancode.kata.videostore;

public class Movie {

    public enum PriceCode {
        REGULAR,
        CHILDREN,
        NEW_RELEASE
    }

    private String _title;
    PriceCode _priceCode;

    protected Movie(String title, PriceCode priceCode) {
        _title = title;
        _priceCode = priceCode;
    }

    public double getAmountFor(int duration) {
        throw new RuntimeException("wrong movie type");
    }

    public PriceCode getPriceCode() {
        return _priceCode;
    }

    public void setPriceCode(PriceCode arg) {
        _priceCode = arg;
    }

    public String getTitle() {
        return _title;
    }
;
}
