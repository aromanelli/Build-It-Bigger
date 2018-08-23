package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.ads.MobileAds;
import com.udacity.gradle.builditbigger.databinding.ActivityMainBinding;

import timber.log.Timber;


public class MainActivity
        extends AppCompatActivity
        implements EndpointsAsyncTask.Listener {

    ActivityMainBinding mBinding;

    final static private String KEY_INTENT_TO_LAUNCH = "intentToLaunch";
    private Intent intentToLaunch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // (https://developers.google.com/admob/android/quick-start#initialize_mobileads)
        Timber.i("onCreate: Initializing AdMob");
        // *MUST* happen before setContentView! (Fragment's onCreateView called on setContentView!)
        MobileAds.initialize(MainActivity.this, getString(R.string.admob_app_id));

        // setContentView(R.layout.activity_main);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        if (savedInstanceState != null) {
            intentToLaunch = savedInstanceState.getParcelable("KEY_INTENT_TO_LAUNCH");
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelable("KEY_INTENT_TO_LAUNCH", intentToLaunch);
        super.onSaveInstanceState(outState);
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
        if (id == R.id.action_tell_joke) {
            tellJoke(findViewById(R.id.action_tell_joke));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view) {

        // Create an Intent to notify the joke viewer that we have a joke to read ...
        intentToLaunch = new Intent();
        // REVIEWER: Realize I could of used an explicit intent, but I wanted to try out an implicit intent, for learning purposes.
        // Intent intent = new Intent(this, JokeViewerActivity.class);
        intentToLaunch.setAction(Intent.ACTION_VIEW);
        intentToLaunch.setType("text/plain");

        if (intentToLaunch.resolveActivity(getPackageManager()) != null) {
            //noinspection unchecked
            new EndpointsAsyncTask().execute(this);
        } else {
            // No app is available; notify user, then quit ...
            Snackbar snackbar = Snackbar
                    .make(view, getString(R.string.msg_no_viewer), Snackbar.LENGTH_INDEFINITE)
                    .setAction(getString(R.string.close), view1 -> finish());
            View sbView = snackbar.getView();
            TextView textView = sbView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(Color.YELLOW);
            snackbar.show();
            intentToLaunch = null;
        }

    }

    @Override
    public void fetchedJoke(String text) {

        if (intentToLaunch == null)
            throw new IllegalStateException("Expected a non-null Intent reference!");

        // Toast.makeText(this, text, Toast.LENGTH_LONG).show();

        // Add the joke text to the existing/passed-in Intent ...
        intentToLaunch.putExtra(Intent.EXTRA_TEXT, text);

        // Show app that can view jokes ...
        startActivity(intentToLaunch);
    }

}
