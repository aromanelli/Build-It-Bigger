package com.udacity.gradle.builditbigger.backend;

/** The object model for the data we are sending through endpoints */
public class JokeBean {

    private String myJoke;

    public String getJoke() {
        return myJoke;
    }

    public void setJoke(String joke) {
        this.myJoke = joke;
    }

}
