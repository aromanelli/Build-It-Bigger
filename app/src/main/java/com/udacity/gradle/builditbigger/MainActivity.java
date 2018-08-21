package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.ads.MobileAds;

import info.romanelli.udacity.jokeslib.ChuckNorrisJoker;
import info.romanelli.udacity.jokeslib.Joker;
import timber.log.Timber;


public class MainActivity extends AppCompatActivity {

    private Joker joker = new ChuckNorrisJoker(); // TODO AOR TEMPORARY FOR TESTING ONLY

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // (https://developers.google.com/admob/android/quick-start#initialize_mobileads)
        Timber.i("onCreate: Initializing AdMob");
        // *MUST* happen before setContentView! (Fragment's onCreateView called on setContentView!)
        MobileAds.initialize(MainActivity.this, getString(R.string.admob_app_id));

        setContentView(R.layout.activity_main);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view) {

        // Purify/clean-up the joke text, removing any non-viewing formatting text ...
        // https://stackoverflow.com/questions/2116162/how-to-display-html-in-textview
        CharSequence text;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            text = Html.fromHtml(joker.getRandomJoke().getText(), Html.FROM_HTML_MODE_LEGACY);
        } else {
            text = Html.fromHtml(joker.getRandomJoke().getText());
        }

        // Create an Intent to notify the joke viewer that we have a joke to read ...
        Intent intent = new Intent();
        // REVIEWER: Realize I could of used an explicit intent, but I wanted to try out an implicit intent, for learning purposes.
        // Intent intent = new Intent(this, JokeViewerActivity.class); // new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, text);

        if (intent.resolveActivity(getPackageManager()) != null) {
            // Show app that can view jokes ...
            startActivity(intent);
        } else {
            // No app is available; notify user, then quit ...
            Snackbar snackbar = Snackbar
                    .make(view, "No joke viewer is available.", Snackbar.LENGTH_INDEFINITE)
                    .setAction("CLOSE", view1 -> finish());
            View sbView = snackbar.getView();
            TextView textView = sbView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(Color.YELLOW);
            snackbar.show();
        }

    }

}
