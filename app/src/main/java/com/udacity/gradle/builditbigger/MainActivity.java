package com.udacity.gradle.builditbigger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spanned;
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
        // TODO AOR TEMPORARY, FOR TESTING PURPOSES ONLY ...
        // https://stackoverflow.com/questions/2116162/how-to-display-html-in-textview
        Spanned text;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            text = Html.fromHtml(joker.getRandomJoke().getText(), Html.FROM_HTML_MODE_LEGACY);
        } else {
            text = Html.fromHtml(joker.getRandomJoke().getText());
        }
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }

}
