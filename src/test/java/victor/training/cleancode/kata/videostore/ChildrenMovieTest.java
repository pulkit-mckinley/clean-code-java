package victor.training.cleancode.kata.videostore;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ChildrenMovieTest {

    @Test
    void getAmountWhenDurationIsLessThanOrEqualTo3() {
        Movie movie = new ChildrenMovie("Inception");
        assertEquals(1.5f, movie.getAmountFor(1));
        assertEquals(1.5f, movie.getAmountFor(3));
    }

    @Test
    void getAmountWhenDurationIsGreaterThan3() {
        Movie movie = new ChildrenMovie("Inception");
        assertEquals(3.0f, movie.getAmountFor(4));
    }

}
