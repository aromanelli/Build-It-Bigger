package info.romanelli.udacity.jokeslib.network;

import java.util.List;

import info.romanelli.udacity.jokeslib.model.Joke;

public interface JokesRequester {

    void receiveJokes(final List<Joke> jokes);

}
