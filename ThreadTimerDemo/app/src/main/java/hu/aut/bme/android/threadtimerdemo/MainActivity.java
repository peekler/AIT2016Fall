package hu.aut.bme.android.threadtimerdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private TextView tvData;
    private boolean enabled = false;

    private Timer timerMain;

    private class TimerTaskShowTime extends TimerTask {
        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(MainActivity.this, new Date(System.currentTimeMillis()).toString(),
                            Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvData = (TextView) findViewById(R.id.tvData);
        Button btnStart = (Button) findViewById(R.id.btnStart);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enabled = true;
                new MyShowThread().start();
                Toast.makeText(MainActivity.this, "Hello", Toast.LENGTH_SHORT).show();


                if (timerMain != null) {
                    timerMain.cancel();
                }
                timerMain = new Timer();
                timerMain.schedule(new TimerTaskShowTime(),0,5000);
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();

        enabled = false;

        if (timerMain != null) {
            timerMain.cancel();
        }
    }

    private class MyShowThread extends Thread {
        public void run() {
            while (enabled) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvData.append("#");
                    }
                });


                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
