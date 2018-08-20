package info.romanelli.udacity.jokeslib.network;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import info.romanelli.udacity.jokeslib.model.Joke;

import static org.junit.Assert.assertTrue;

public class ChuckNorrisJokesFetcherTest {

    private TestJokesRequester receiver;

    static class TestJokesRequester implements JokesRequester {
        private Collection<Joke> jokes = new ArrayList<>();
        @Override
        public void receiveJokes(List<Joke> jokes) {
            this.jokes.addAll(jokes);
        }
    }

    @SuppressWarnings("RedundantThrows")
    @Before
    public void setUp() throws Exception {
        receiver = new TestJokesRequester();
    }

    @SuppressWarnings("RedundantThrows")
    @After
    public void tearDown() throws Exception {
        receiver = null;
    }

    @Test
    public void getJokes() {
        new ChuckNorrisJokesFetcher(receiver).requestJokes();
        assertTrue(
                examineJokes(receiver.jokes, 15000)
        );
    }

    @SuppressWarnings("SameParameterValue")
    private boolean examineJokes(final Collection<Joke> jokes, int waitTimeMillis) {
        int sleepTimeMillis = 1000;
        int waitCount = waitTimeMillis / sleepTimeMillis;
        boolean flag = false;
        for (int i = 0; i < waitCount; i++) {
            System.out.println(jokes);
            if ((!jokes.isEmpty())) {
                for (Joke joke : jokes) {
                    if (joke != null) {
                        assertTrue(joke.getId() > 0);
                        assertTrue(joke.getText() != null);
                        assertTrue(joke.getText().trim().length() >= 1);
                    } else {
                        break;
                    }
                    flag = true;
                }
            }

            if (!flag) {
                try {
                    System.out.println(i);
                    Thread.sleep(sleepTimeMillis);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                break;
            }

        }
        return flag;
    }

}