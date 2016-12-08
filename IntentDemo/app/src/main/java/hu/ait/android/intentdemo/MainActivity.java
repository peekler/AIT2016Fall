package hu.ait.android.intentdemo;

import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText etSearch;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = (WebView) findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                webView.loadUrl(url);
                return true;
            }
        });
        webView.loadUrl("http://www.facebook.com");

        if (getIntent().getData() != null) {
            String file = getIntent().getData().getPath();
            Toast.makeText(this, file, Toast.LENGTH_LONG).show();
        }

        etSearch = (EditText) findViewById(R.id.etSearch);
        TextView tvData = (TextView) findViewById(R.id.tvData);
        Button btnIntent = (Button) findViewById(R.id.btnIntent);
        btnIntent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //intentPhoneCall();
                //intentPickContact();
                //intentSearch(etSearch.getText().toString());
                intentShare(etSearch.getText().toString());
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    public void intentPhoneCall() {
        Intent intentCall = new Intent(Intent.ACTION_CALL,
                Uri.parse("tel:+36208225581"));
        try {
            startActivity(intentCall);
        }catch (SecurityException e){
        }
    }

    public void intentPickContact() {
        Intent i = new Intent(Intent.ACTION_PICK,
                ContactsContract.Contacts.CONTENT_URI);
        startActivity(i);
        //startActivityForResult(i,101);
    }

    public void intentSearch(String text) {
        Intent intentSearch = new Intent(Intent.ACTION_WEB_SEARCH);
        intentSearch.putExtra(SearchManager.QUERY, text);
        startActivity(intentSearch);
    }

    public void intentShare(String text) {
        Intent intentTest = new Intent(Intent.ACTION_SEND);
        intentTest.setType("text/plain");
        intentTest.putExtra(Intent.EXTRA_TEXT, text);
        intentTest.setPackage("com.facebook.katana");
        //startActivity(Intent.createChooser(intentTest, "Select share app"));
        startActivity(intentTest);
    }

}
