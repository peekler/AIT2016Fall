package hu.ait.android.multiactivitydemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        TextView tvData = (TextView) findViewById(R.id.tvData);

        String name = getIntent().getStringExtra(MainActivity.KEY_NAME);
        int size = getIntent().getIntExtra(MainActivity.KEY_SIZE, 3);

        tvData.setText("Hi, "+name+", board size: "+size);
    }
}
