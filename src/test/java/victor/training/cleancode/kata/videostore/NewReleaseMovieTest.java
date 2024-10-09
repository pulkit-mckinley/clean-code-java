package victor.training.cleancode.kata.videostore;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NewReleaseMovieTest {

    @Test
    void getAmount() {
        Movie movie = new NewReleaseMovie("Inception");
        assertEquals(3, movie.getAmountFor(1));
        assertEquals(6, movie.getAmountFor(2));
    }

}
