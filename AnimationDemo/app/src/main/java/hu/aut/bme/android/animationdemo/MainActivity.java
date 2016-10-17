package hu.aut.bme.android.animationdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Animation pushAnim = AnimationUtils.loadAnimation(MainActivity.this,
                R.anim.push_anim);
        final Animation sendAnim = AnimationUtils.loadAnimation(MainActivity.this,
                R.anim.send_anim);

        final TextView tvMessage = (TextView) findViewById(R.id.tvMessage);
        final Button btnSend = (Button) findViewById(R.id.btnSend);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pushAnim.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        tvMessage.startAnimation(sendAnim);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }
                });
                btnSend.startAnimation(pushAnim);
            }
        });
    }
}
