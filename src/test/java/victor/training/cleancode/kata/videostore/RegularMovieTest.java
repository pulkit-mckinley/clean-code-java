package victor.training.cleancode.kata.videostore;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RegularMovieTest {

    @Test
    void getAmount() {
        Movie movie = new RegularMovie("Inception");
        assertEquals(2.0f, movie.getAmountFor(1));
        assertEquals(2.0f, movie.getAmountFor(2));
    }

    @Test
    void getAmountWhenDurationIsGreaterThan2() {
        Movie movie = new RegularMovie("Inception");
        assertEquals(3.5f, movie.getAmountFor(3));
    }
}
