package hu.ait.android.highlowgame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class GameActivity extends AppCompatActivity {

    public static final String KEY_GEN = "KEY_GEN";
    private int generatedNum = 0;
    private Random random = new Random(System.currentTimeMillis());
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        if (savedInstanceState != null && savedInstanceState.containsKey(KEY_GEN)) {
            generatedNum = savedInstanceState.getInt(KEY_GEN, 0);
        } else {
            generateNewNumber();
        }

        final EditText etGuess = (EditText) findViewById(R.id.etGuess);
        final TextView tvStatus = (TextView) findViewById(R.id.tvStatus);
        Button btnGuess = (Button) findViewById(R.id.btnGuess);

        btnGuess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!"".equals(etGuess.getText().toString())) {
                    int guess = Integer.parseInt(etGuess.getText().toString());

                    if (guess < generatedNum) {
                        tvStatus.setText("The number is higher");
                        count++;
                    } else if (guess > generatedNum) {
                        tvStatus.setText("The number is lower");
                        count++;
                    } else if (guess == generatedNum) {
                        tvStatus.setText("You have won! Steps: "+count);

                        startActivity(new Intent(GameActivity.this, ResultActivity.class));

                    } else {
                        tvStatus.setText("This can not happen :)");
                    }
                } else {
                    etGuess.setError("This field can not be empty");
                }

            }
        });

    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(KEY_GEN,generatedNum);
    }

    private void generateNewNumber() {
        generatedNum = random.nextInt(10);
    }

}
