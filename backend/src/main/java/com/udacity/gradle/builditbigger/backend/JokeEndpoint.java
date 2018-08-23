package com.udacity.gradle.builditbigger.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import info.romanelli.udacity.jokeslib.ChuckNorrisJoker;
import info.romanelli.udacity.jokeslib.Joker;

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

    @ApiMethod(name = "getRandomJoke")
    public JokeBean getJoke() {
        JokeBean jokeBean = new JokeBean();
        String text;
        try {
            text = joker.getRandomJoke().getJoke();
        }  catch (IllegalStateException ise) {
            // Most likely Joker hasn't completed fetching jokes from the Net yet
            text = "No jokes are available at this time.  Try again later.";
        }
        jokeBean.setJoke(text);
        return jokeBean;
    }

}
