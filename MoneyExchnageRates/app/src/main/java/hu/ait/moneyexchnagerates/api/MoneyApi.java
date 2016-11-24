package hu.ait.moneyexchnagerates.api;

import hu.ait.moneyexchnagerates.data.MoneyResult;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by peter on 2016. 11. 21..
 */

public interface MoneyApi {
    @GET("latest")
    Call<MoneyResult> getRatesToUsd(@Query("base") String base,
                                    @Query("units") String units);



}
