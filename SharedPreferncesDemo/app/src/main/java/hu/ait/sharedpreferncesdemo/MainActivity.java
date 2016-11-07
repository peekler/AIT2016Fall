package hu.ait.sharedpreferncesdemo;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Date;
import java.util.prefs.Preferences;

public class MainActivity extends AppCompatActivity {

    public static final String KEY_LAST_USED = "KEY_LAST_USED";
    private TextView tvData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvData = (TextView) findViewById(R.id.tvData);
    }

    @Override
    protected void onStart() {
        super.onStart();
        showLastUsedTime();
    }

    private void showLastUsedTime() {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        String time = sp.getString(KEY_LAST_USED,
                "This is the first time you use it!");
        tvData.setText(time);
    }

    @Override
    protected void onStop() {
        super.onStop();
        saveLastUsedTime();
    }

    private void saveLastUsedTime() {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);

        SharedPreferences.Editor editor = sp.edit();
        editor.putString(KEY_LAST_USED,
                new Date(System.currentTimeMillis()).toString());

        editor.commit();
    }
}
