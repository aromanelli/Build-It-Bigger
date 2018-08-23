package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;
import android.text.Html;
import android.text.Spanned;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.udacity.gradle.builditbigger.backend.jokesApi.JokesApi;

import java.io.IOException;

public class EndpointsAsyncTask extends AsyncTask<EndpointsAsyncTask.Listener, Void, String> {

    private static JokesApi JOKES_API_SERVICE;
    private Listener listener;

    @Override
    protected String doInBackground(EndpointsAsyncTask.Listener... listeners) {

        if(JOKES_API_SERVICE == null) {  // Only do this once
            JokesApi.Builder builder = new JokesApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(abstractGoogleClientRequest ->
                            abstractGoogleClientRequest.setDisableGZipContent(true));
                    // end options for devappserver
            JOKES_API_SERVICE = builder.build();
        }

        listener = listeners[0];

        try {
            return JOKES_API_SERVICE.getRandomJoke().execute().getJoke();
        } catch (IOException e) {
            return e.getMessage();
        }

    }

    @Override
    protected void onPostExecute(String result) {

        // Purify/clean-up the joke text, removing any non-viewing formatting text ...
        // https://stackoverflow.com/questions/2116162/how-to-display-html-in-textview
        Spanned text;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            text = Html.fromHtml(result, Html.FROM_HTML_MODE_LEGACY);
        } else {
            text = Html.fromHtml(result);
        }

        listener.fetchedJoke(text.toString());
    }

    public interface Listener {
        void fetchedJoke(final String text);
    }

}
