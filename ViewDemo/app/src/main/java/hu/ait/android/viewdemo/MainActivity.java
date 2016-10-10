package hu.ait.android.viewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private final String[] cityNames = new String[] { "Budapest", "Bukarest","Paris", "Wienna",
      "New York", "New Haven"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final ImageView ivLogo = (ImageView) findViewById(R.id.ivLogo);
        ivLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ivLogo.setImageResource(R.drawable.ait_budapest);
            }
        });


        AutoCompleteTextView etCities = (AutoCompleteTextView) findViewById(R.id.etCities);
        ArrayAdapter<String> citiesAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, cityNames);
        etCities.setAdapter(citiesAdapter);
    }
    
    
    public void pressHandler(View v) {
        Toast.makeText(MainActivity.this, "Working!", Toast.LENGTH_SHORT).show();
    }
    
}
