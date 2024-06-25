package victor.training.cleancode.kata.videostore;

import java.util.*;

class Customer {
	private final String name;
	private final Map<Movie, Integer> rentals = new LinkedHashMap<>(); // preserves order of elements

	public Customer(String name) {
		this.name = name;
	}

    public void addRental(Movie m, int d) {
		rentals.put(m, d);
	}

	public String statement() {
		double totalPrice = 0;
		int frequentRenterPoints = 0;
		String result = "Rental Record for " + name + "\n";
		// loop over each movie rental
		for (Movie each : rentals.keySet()) {
			double price = 0;
			// determine amounts for every line
			int dr = rentals.get(each);
			price = calculateNewPrice(each, price, dr);
			// add frequent renter points
			frequentRenterPoints++;
			// add bonus for a two day new release rental
			if (each.getPriceCode() != null &&
				 (each.getPriceCode() == Movie.NEW_RELEASE)
				 && dr > 1)
				frequentRenterPoints++;
			// show figures line for this rental
			result += "\t" + each.getTitle() + "\t" + price + "\n";
			totalPrice += price;
		}
		// add footer lines
		result += "Amount owed is " + totalPrice + "\n";
		result += "You earned " + frequentRenterPoints + " frequent renter points";
		return result;
	}

	private static double calculateNewPrice(Movie each, double thisAmount, int dr) {
		switch (each.getPriceCode()) {
			case Movie.REGULAR -> {
				thisAmount += 2;
				if (dr > 2)
					thisAmount += (dr - 2) * 1.5;
			}
			case Movie.NEW_RELEASE -> thisAmount += dr * 3;
			case Movie.CHILDRENS -> {
				thisAmount += 1.5;
				if (dr > 3)
					thisAmount += (dr - 3) * 1.5;
			}
		}
		return thisAmount;
	}
}