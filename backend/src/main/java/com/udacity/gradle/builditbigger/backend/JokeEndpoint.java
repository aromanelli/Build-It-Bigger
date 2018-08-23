package com.udacity.gradle.builditbigger.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import info.romanelli.udacity.jokeslib.ChuckNorrisJoker;
import info.romanelli.udacity.jokeslib.Joker;
import info.romanelli.udacity.jokeslib.model.Joke;

/** An endpoint class we are exposing */
@Api(
        name = "jokesApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.builditbigger.gradle.udacity.com",
                ownerName = "backend.builditbigger.gradle.udacity.com"
        )
)
public class JokeEndpoint {

    private Joker joker;

    public JokeEndpoint() {
        joker = new ChuckNorrisJoker();
    }

    @ApiMethod(name = "getJoke")
    public JokeBean getJoke() {
        JokeBean jokeBean = new JokeBean();
        Joke joke;
        try {
            joke = joker.getRandomJoke();
        }  catch (IllegalStateException ise) {
            // Joker hasn't completed fetching jokes from the Net yet
            joke = new Joke() {
                @Override
                public String getText() {
                    return "No jokes are available at this time.  Try again later.";
                }
                @Override
                public int getId() {
                    return Integer.MIN_VALUE;
                }
            };
        }
        jokeBean.setJoke(joke);
        return jokeBean;
    }

}
