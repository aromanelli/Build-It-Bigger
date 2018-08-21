package info.romanelli.udacity.jokeviewer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.TextView;

public class JokeViewerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.joke_viewer);

        TextView tv = findViewById(R.id.tv_joke_viewer);
        if (getIntent().getAction().equals(Intent.ACTION_VIEW) &&
                "text/plain".equals(getIntent().getType())) {
            CharSequence cs = getIntent().getCharSequenceExtra(Intent.EXTRA_TEXT);
            if (!TextUtils.isEmpty(cs)) {
                tv.setText(cs);
            } else {
                tv.setText(getString(R.string.msg_no_joke_to_show));
            }
        } else {
            tv.setText(getString(R.string.msg_no_joke_to_show));
        }

    }
}
