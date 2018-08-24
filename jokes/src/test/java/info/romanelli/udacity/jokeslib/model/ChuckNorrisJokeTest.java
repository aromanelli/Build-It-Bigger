package info.romanelli.udacity.jokeslib.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ChuckNorrisJokeTest {

    private ChuckNorrisJoke joke;

    @SuppressWarnings("RedundantThrows")
    @Before
    public void setUp() throws Exception {
        joke = new ChuckNorrisJoke();
    }

    @SuppressWarnings("RedundantThrows")
    @After
    public void tearDown() throws Exception {
        joke = null;
    }

    @Test
    public void getJoke() {
        assertEquals("Chuck Norris deems you unworthy of hearing a joke about him.", joke.getJoke());
    }

    @Test
    public void getId() {
        assertEquals(Integer.MIN_VALUE, joke.getId());
    }
}