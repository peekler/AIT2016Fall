package hu.ait.android.butterknifedemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tvData)
    TextView tvData;
    @BindView(R.id.layoutContent)
    LinearLayout layoutContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnAddData)
    public void addClick(Button btn) {
        tvData.setText(new Date(System.currentTimeMillis()).toString());

        layoutContent.setBackgroundColor(Color.RED);
    }
}
