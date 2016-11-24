package hu.ait.moneyexchnagerates;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import hu.ait.moneyexchnagerates.api.MoneyApi;
import hu.ait.moneyexchnagerates.data.MoneyResult;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder().
                baseUrl("http://api.fixer.io").
                addConverterFactory(GsonConverterFactory.create()).
                build();
        final MoneyApi moneyApi = retrofit.create(MoneyApi.class);


        tvResult = (TextView) findViewById(R.id.tvResult);
        final EditText etMoney = (EditText) findViewById(R.id.etMoney);
        Button btnGetRates = (Button) findViewById(R.id.btnGetRates);
        btnGetRates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<MoneyResult> call = moneyApi.getRatesToUsd("USD", "metricx");
                call.enqueue(new Callback<MoneyResult>() {
                    @Override
                    public void onResponse(Call<MoneyResult> call, Response<MoneyResult> response) {
                        tvResult.setText(""+response.body().getRates().gethUF());
                    }

                    @Override
                    public void onFailure(Call<MoneyResult> call, Throwable t) {
                        Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }


}
