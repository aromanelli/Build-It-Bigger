package info.romanelli.udacity.jokeslib;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import info.romanelli.udacity.jokeslib.model.Joke;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class ChuckNorrisJokerTest {

    private ChuckNorrisJoker jokerFetcher;
    private ChuckNorrisJoker jokerStatic;
    private List<Joke> listJokes;

    @SuppressWarnings("RedundantThrows")
    @org.junit.Before
    public void setUp() throws Exception {

        jokerFetcher = new ChuckNorrisJoker();

        listJokes = new ArrayList<>();
        listJokes.add(new Joke() {
            @Override
            public String getText() {
                return "TEST1";
            }

            @Override
            public int getId() {
                return Integer.MAX_VALUE;
            }
        });
        listJokes.add(new Joke() {
            @Override
            public String getText() {
                return "TEST2";
            }

            @Override
            public int getId() {
                return Integer.MAX_VALUE - 1;
            }
        });
        jokerStatic = new ChuckNorrisJoker(listJokes);
    }

    @SuppressWarnings("RedundantThrows")
    @org.junit.After
    public void tearDown() throws Exception {
        jokerFetcher = null;
        jokerStatic = null;
    }

    @org.junit.Test
    public void getJoke() {
        try {
            Joke joke = jokerStatic.getJoke(0);
            assertEquals(joke.getText(), "TEST1");
            assertEquals(joke.getId(), Integer.MAX_VALUE);
            joke = jokerStatic.getJoke(1);
            assertEquals(joke.getText(), "TEST2");
            assertEquals(joke.getId(), Integer.MAX_VALUE - 1);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void sizeJokes() {
        try {
            assertEquals(2, jokerStatic.sizeJokes());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void getRandomJoke() {
        try {
            Joke joke = jokerStatic.getRandomJoke();
            assertNotNull(joke);
            assertTrue(joke.getId() >= 0);
            assertNotNull(joke.getText());
            assertTrue(joke.getText().trim().length() >= 1);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void getJokes() {
        try {
            List<Joke> jokes = jokerStatic.getJokes();
            assertEquals(listJokes, jokes);
        } catch (Exception e) {
            fail();
        }
    }

    private void waitUntilFetched() {
        try {
            int count = 0;
            while (!jokerFetcher.isJokesFetched()) {
                count++;
                Thread.sleep(1000);
                if (count >= 15) {
                    throw new IllegalStateException("Testing wait time expired!");
                }
            }
        } catch (Exception e) {
            fail(e.getLocalizedMessage());
        }
    }


    @Test
    public void sizeJokesFetched() {
        try {
            waitUntilFetched();
            assertTrue(jokerFetcher.sizeJokes() >= 1);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void getRandomJokeFetched() {
        try {
            waitUntilFetched();
            Joke joke = jokerFetcher.getRandomJoke();
            assertNotNull(joke);
            assertTrue(joke.getId() >= 0);
            assertNotNull(joke.getText());
            assertTrue(joke.getText().trim().length() >= 1);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void getJokesFetched() {
        try {
            waitUntilFetched();
            List<Joke> jokes = jokerFetcher.getJokes();
            assertNotNull(jokes);
            assertTrue(jokes.size() >= 1);
        } catch (Exception e) {
            fail();
        }
    }


}