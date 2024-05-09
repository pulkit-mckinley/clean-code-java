package victor.training.cleancode.kata.videostore;

import java.util.LinkedHashMap;
import java.util.Map;

import static victor.training.cleancode.kata.videostore.MovieEnum.NEW_RELEASE;

class Customer {
    private String name;
    private Map<Movie, Integer> rentals = new LinkedHashMap<>(); // preserves order!

    public Customer(String name) {
        this.name = name;
    }

    public void addRental(Movie m, int d) {
        rentals.put(m, d);
    }

    public String getName() {
        return name;
    }

    public String statement() {
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        String result = "Rental Record for " + getName() + "\n";
        // iterate each rental
        for (Movie movie : rentals.keySet()) {
            double thisAmount = 0;
            // determine amounts for every line
            int dailyRentalPrice = rentals.get(movie);
            thisAmount = getThisAmount(movie, thisAmount, dailyRentalPrice);
            // add frequent renter points
            frequentRenterPoints++;
            // add bonus for a two day new release rental
            if (movie.getPriceCode() != null &&
                    (movie.getPriceCode() == NEW_RELEASE)
                    && dailyRentalPrice > 1)
                frequentRenterPoints++;
            // show figures line for this rental
            result += "\t" + movie.getTitle() + "\t" + thisAmount + "\n";
            totalAmount += thisAmount;
        }
        // add footer lines
        result += "Amount owed is " + totalAmount + "\n";
        result += "You earned " + frequentRenterPoints + " frequent renter points";
        return result;
    }

    private static double getThisAmount(Movie each, double thisAmount, int dailyRentalPrice) {
        switch (each.getPriceCode()) {
            case REGULAR:
                thisAmount += 2;
                if (dailyRentalPrice > 2)
                    thisAmount += (dailyRentalPrice - 2) * 1.5;
                break;
            case NEW_RELEASE:
                thisAmount += dailyRentalPrice * 3;
                break;
            case CHILDRENS:
                thisAmount += 1.5;
                if (dailyRentalPrice > 3)
                    thisAmount += (dailyRentalPrice - 3) * 1.5;
                break;
        }
        return thisAmount;
    }
}