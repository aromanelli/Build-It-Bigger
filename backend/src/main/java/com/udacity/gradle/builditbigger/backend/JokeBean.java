package com.udacity.gradle.builditbigger.backend;

import info.romanelli.udacity.jokeslib.model.Joke;

/** The object model for the data we are sending through endpoints */
public class JokeBean {

    private Joke joke;

    public Joke getJoke() {
        return joke;
    }

    public void setJoke(Joke joke) {
        this.joke = joke;
    }

    public String getJokeText() {
        return joke.getText();
    }

}
