package hu.ait.fragmentdemo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static android.R.attr.fragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnOne = (Button) findViewById(R.id.btnOne);
        btnOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFragmentByTag(FragmentMain.TAG);
            }
        });
        Button btnTwo = (Button) findViewById(R.id.btnTwo);
        btnTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFragmentByTag(FragmentDetails.TAG);
            }
        });



        showFragmentByTag(FragmentMain.TAG);
    }


    private void showFragmentByTag(String tag) {
        Fragment fragment =
                getSupportFragmentManager().findFragmentByTag(tag);

        if (fragment == null) {
            if (tag.equals(FragmentMain.TAG)) {
                fragment = new FragmentMain();
            } else if (tag.equals(FragmentDetails.TAG)){
                fragment = new FragmentDetails();
            }
        }

        FragmentTransaction transaction =
                getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(android.R.anim.slide_in_left,
                android.R.anim.slide_out_right);

        transaction.replace(R.id.layoutContainer, fragment, tag);

        transaction.addToBackStack(null);

        transaction.commit();
    }


}
