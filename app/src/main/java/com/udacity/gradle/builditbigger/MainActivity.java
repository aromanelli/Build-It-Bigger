package com.udacity.gradle.builditbigger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import info.romanelli.udacity.jokeslib.ChuckNorrisJoker;
import info.romanelli.udacity.jokeslib.Joker;


public class MainActivity extends AppCompatActivity {

    private Joker joker = new ChuckNorrisJoker(); // TODO AOR TEMPORARY FOR TESTING ONLY

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        Toast.makeText(
                this,
                // TODO AOR TEMPORARY FOR TESTING ONLY ...
                Html.fromHtml(joker.getRandomJoke().getText()),
                Toast.LENGTH_LONG
        ).show();
    }


}
