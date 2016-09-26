package hu.ait.android.multiactivitydemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static final String KEY_NAME = "KEY_NAME";
    public static final String KEY_SIZE = "KEY_SIZE";
    private EditText etName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = (EditText) findViewById(R.id.etName);

        Button btnEasy = (Button) findViewById(R.id.btnEasy);
        btnEasy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startGameWithSize(3);
            }
        });

        Button btnMedium = (Button) findViewById(R.id.btnMedium);
        btnMedium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startGameWithSize(5);
            }
        });

        Button btnHard = (Button) findViewById(R.id.btnHard);
        btnHard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startGameWithSize(9);
            }
        });
    }

    private void startGameWithSize(int size) {
        Intent intentStartGame = new Intent();
        intentStartGame.setClass(this, GameActivity.class);

        intentStartGame.putExtra(KEY_NAME, etName.getText().toString());
        intentStartGame.putExtra(KEY_SIZE, size);

        startActivity(intentStartGame);
    }


}
