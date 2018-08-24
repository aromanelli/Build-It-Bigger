package info.romanelli.udacity.jokeslib.network;

import java.util.List;

import info.romanelli.udacity.jokeslib.model.Joke;

public interface JokesRequester {

    /**
     * @param jokes A {@code null} if something went wrong, or else a {@link List} of {@link Joke}s.
     */
    void receiveJokes(final List<Joke> jokes);

}
