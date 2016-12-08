package hu.ait.android.broadcastdemo.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class AirPlaneReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        boolean airPlaneState = intent.getBooleanExtra("state", false);

        Toast.makeText(context, "AIRPLANE: "+airPlaneState,
                Toast.LENGTH_SHORT).show();
    }
}
