package hu.ait.android.highlowgame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Button btnOk = (Button) findViewById(R.id.btnOk);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //finish();

                Intent intentShowMenu = new Intent();
                intentShowMenu.setClass(ResultActivity.this, MainActivity.class);
                intentShowMenu.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                 | Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                startActivity(intentShowMenu);

            }
        });
    }


    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Toast.makeText(ResultActivity.this, "Press ok to exit", Toast.LENGTH_SHORT).show();
    }
}
