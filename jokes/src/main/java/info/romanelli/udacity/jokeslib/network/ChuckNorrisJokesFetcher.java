package info.romanelli.udacity.jokeslib.network;

import com.google.gson.Gson;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

// http://www.icndb.com/api/
// http://api.icndb.com/jokes/random
public class ChuckNorrisJokesFetcher implements JokesFetcher {

    private Gson gson;
    private JokesRequester receiver;

    public ChuckNorrisJokesFetcher(final JokesRequester receiver) {
        if (receiver == null)
            throw new IllegalArgumentException("Receiver reference is not valid!");
        this.receiver = receiver;

        gson = new Gson();
    }

    private String getJokeJSon(final String url) {

        Request request = new Request.Builder()
                .url(url)
                .build();

        try {
            OkHttpClient client = new OkHttpClient();
            try (Response response = client.newCall(request).execute()) {
                if ((response != null) && (response.body() != null) && response.isSuccessful()) {
                    //noinspection ConstantConditions
                    return response.body().string();
                } else {
                    System.err.println(response);
                    return null;
                }
            }
        } catch (final Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public void requestJokes() {
        new Thread(() -> {

            try {

                String json = getJokeJSon("https://api.icndb.com/jokes/");

                ChuckNorrisFetchedJokes fetchedJoke = gson.fromJson(json, ChuckNorrisFetchedJokes.class);

                if (fetchedJoke == null) {
                    System.err.println("Something bad happened fetching joke! [1]");
                    //noinspection ConstantConditions
                    System.err.println(fetchedJoke);
                    receiver.receiveJokes(null);
                } else if (fetchedJoke.getValue() == null) {
                    System.err.println("Something bad happened fetching joke! [2]");
                    System.err.println(fetchedJoke);
                    receiver.receiveJokes(null);
                } else if (!"success".equals(fetchedJoke.getType())) {
                    System.err.println("Something bad happened fetching joke! [3]");
                    System.err.println(fetchedJoke);
                    receiver.receiveJokes(null);
                } else {
                    receiver.receiveJokes( new ArrayList<>( fetchedJoke.getValue() ) );
                }

            } catch (final Exception e) {
                    e.printStackTrace();
                    receiver.receiveJokes(null);
            }

        }
        ).start();
    }

}
