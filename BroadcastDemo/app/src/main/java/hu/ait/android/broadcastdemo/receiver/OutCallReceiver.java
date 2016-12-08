package hu.ait.android.broadcastdemo.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class OutCallReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        final String originalNumber = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
        Toast.makeText(context, "Outgoing call catched! " +
                originalNumber, Toast.LENGTH_LONG).show();


        //setResultData("111");

    }
}
