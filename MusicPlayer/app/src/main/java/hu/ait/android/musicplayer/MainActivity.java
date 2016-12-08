package hu.ait.android.musicplayer;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements MediaPlayer.OnPreparedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnPlay = (Button) findViewById(R.id.btnPlay);
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer mp = MediaPlayer.create(MainActivity.this,
                        R.raw.music);
                mp.setOnPreparedListener(MainActivity.this);


            }
        });
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mp.start();
    }


}
