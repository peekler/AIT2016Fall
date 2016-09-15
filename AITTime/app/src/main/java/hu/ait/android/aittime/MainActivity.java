package hu.ait.android.aittime;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnTime = (Button) findViewById(R.id.btnTime);
        final TextView tvDate = (TextView) findViewById(R.id.tvDate);
        final EditText etName = (EditText) findViewById(R.id.etName);
        final LinearLayout layoutContent = (LinearLayout) findViewById(
                R.id.layoutContent);

        btnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTime(etName, tvDate, layoutContent);
            }
        });
    }

    private void showTime(EditText etName, TextView tvDate, LinearLayout layoutContent) {
        String time = etName.getText().toString()+
                ", the time is: "+new Date(
                System.currentTimeMillis()).toString();

        Toast.makeText(MainActivity.this,
                time,
                Toast.LENGTH_SHORT).show();
        tvDate.setText(time);


        Snackbar.make(layoutContent, time,
                Snackbar.LENGTH_LONG).show();
    }
}
