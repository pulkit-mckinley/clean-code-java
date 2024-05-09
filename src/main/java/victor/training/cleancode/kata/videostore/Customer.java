package victor.training.cleancode.kata.videostore;

import java.util.*;

import static victor.training.cleancode.kata.videostore.enums.MovieType.*;

class Customer {
	private final String name;
	private final Map<Movie, Integer> rentals = new LinkedHashMap<>(); // preserves order

	public Customer(String name) {
		this.name = name;
	};

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
		//  iterate each rental
		for (Movie each : rentals.keySet()) {
			double thisAmount = 0;
			// determine amounts for every line
			int dr = rentals.get(each);
			switch (each.getMovieType()) {
				case REGULAR:
					thisAmount = getRegularAmount(thisAmount, dr, 2, 2);
					break;
				case NEW_RELEASE:
					thisAmount += dr * 3;
					break;
				case CHILDREN:
					thisAmount = getRegularAmount(thisAmount, dr, 1.5, 3);
					break;
			}
			// add frequent renter points
			frequentRenterPoints++;
			// add bonus for a two day new release rental
			if (each.getMovieType() != null &&
				 (each.getMovieType() == NEW_RELEASE)
				 && dr > 1)
				frequentRenterPoints++;
			// show figures line for this rental
			result += "\t" + each.getTitle() + "\t" + thisAmount + "\n";
			totalAmount += thisAmount;
		}
		// add footer lines
		result += "Amount owed is " + totalAmount + "\n";
		result += "You earned " + frequentRenterPoints + " frequent renter points";
		return result;
	}

	private double getRegularAmount(double thisAmount, int dr, double i, int i2) {
		thisAmount += i;
		if (dr > i2)
			thisAmount += (dr - i2) * 1.5;
		return thisAmount;
	}
}