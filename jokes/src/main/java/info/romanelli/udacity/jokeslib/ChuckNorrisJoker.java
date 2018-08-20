package info.romanelli.udacity.jokeslib;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

import info.romanelli.udacity.jokeslib.model.Joke;
import info.romanelli.udacity.jokeslib.network.ChuckNorrisJokesFetcher;
import info.romanelli.udacity.jokeslib.network.JokesRequester;

public class ChuckNorrisJoker implements Joker {

    final private Random randomizer;
    private List<Joke> listJokes;

    private AtomicBoolean abJokesFetched = new AtomicBoolean(false);

    ChuckNorrisJoker() {

        this.randomizer = new Random(
                (long) (new Date().getTime() / Math.PI)
        );

        JokerJokesRequester requester = new JokerJokesRequester();
        ChuckNorrisJokesFetcher fetcher = new ChuckNorrisJokesFetcher(requester);
        fetcher.requestJokes();
    }

    ChuckNorrisJoker(final List<Joke> listJokes) {

        this.randomizer = new Random(
                (long) (new Date().getTime() / Math.PI)
        );

        this.listJokes = listJokes;
        if (listJokes == null || listJokes.size() <= 0)
            throw new IllegalArgumentException("Invalid jokes list!");

        abJokesFetched.set(true);
    }

    public boolean isJokesFetched() {
        return abJokesFetched.get();
    }

    private void checkJokesFetched() {
        if (!abJokesFetched.get())
            throw new IllegalStateException("Jokes have not been fetched yet!");
    }

    @Override
    public int sizeJokes() {
        checkJokesFetched();
        return listJokes.size();
    }

    @Override
    public Joke getJoke(int index) {
        checkJokesFetched();
        return listJokes.get(index);
    }

    @Override
    public Joke getRandomJoke() throws IllegalStateException {
        checkJokesFetched();
        return listJokes.get( randomizer.nextInt(listJokes.size()) );
    }

    @Override
    public List<Joke> getJokes() {
        checkJokesFetched();
        return Collections.unmodifiableList(listJokes);
    }

    class JokerJokesRequester implements JokesRequester {
        @Override
        public void receiveJokes(List<Joke> jokes) {
            ChuckNorrisJoker.this.listJokes = jokes;
            abJokesFetched.set(true);
        }
    }

}
