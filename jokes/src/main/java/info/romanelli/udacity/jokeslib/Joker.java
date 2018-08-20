package info.romanelli.udacity.jokeslib;

import java.util.List;

import info.romanelli.udacity.jokeslib.model.Joke;

public interface Joker {
    int sizeJokes();
    Joke getJoke(int index);
    Joke getRandomJoke();
    List<Joke> getJokes();
}
