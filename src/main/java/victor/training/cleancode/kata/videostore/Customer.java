package victor.training.cleancode.kata.videostore;

import java.util.*;

class Customer {

    private String name;
    private Map<Movie, Integer> rentalsDuration = new LinkedHashMap<>(); // preserves order of elements

    public Customer(String name) {
        this.name = name;
    }

    public void addRental(Movie movie, int duration) {
        rentalsDuration.put(movie, duration);
    }

    public String getName() {
        return name;
    }

    public String getStatement() {
        double amountOwed = 0;
        int frequentRenterPoints = 0;

        String statement = "Rental Record for " + getName() + "\n";

        for (Movie movie : rentalsDuration.keySet()) {

            if (isBonusValid(movie)) {
                frequentRenterPoints++;
            }
            frequentRenterPoints++;

            double movieAmount = calculateMovieAmount(movie);
            amountOwed += movieAmount;

            // show figures line for this rental
            statement += "\t" + movie.getTitle() + "\t" + movieAmount + "\n";
        }

        statement += addFooterLines(amountOwed, frequentRenterPoints);
        return statement;
    }

    private String addFooterLines(double amountOwed, int frequentRenterPoints) {
        String footerStatement = "";
        footerStatement += "Amount owed is " + amountOwed + "\n";
        footerStatement += "You earned " + frequentRenterPoints + " frequent renter points";
        return footerStatement;
    }

    // add bonus for a two day new release rental
    private boolean isBonusValid(Movie movie) {
        if (movie.getPriceCode() == null) {
            return false;
        }

        boolean isNewRelease = movie.getPriceCode() == Movie.PriceCode.NEW_RELEASE;
        return isNewRelease && rentalsDuration.get(movie) > 1;
    }

    private double calculateMovieAmount(Movie movie) {

        int duration = rentalsDuration.get(movie);
        return movie.getAmountFor(duration);
    }
}
