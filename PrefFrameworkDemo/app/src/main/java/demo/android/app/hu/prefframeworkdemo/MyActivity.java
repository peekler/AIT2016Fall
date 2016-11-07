package demo.android.app.hu.prefframeworkdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class MyActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        String user =PreferenceManager.getDefaultSharedPreferences(this).
                getString("key_username","unknown");
        boolean likeCat = PreferenceManager.getDefaultSharedPreferences(this).getBoolean("key_like_cat", true);

        Toast.makeText(MyActivity.this,
                user+", likes cats: "+likeCat, Toast.LENGTH_SHORT).show();
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            // call SettingsActivity and set default opening fragment
            Intent i = new Intent(this,MySettings.class);
            //i.putExtra(":android:no_headers", true);
            //i.putExtra(":android:show_fragment",
            //        MySettings.FragmentSettingsBasic.class.getName());
            startActivity(i);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
