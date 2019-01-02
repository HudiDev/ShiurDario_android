package com.hudiilfeld.shiurdiario.models;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WebService {



        //Log.d("current_date", currentDate);
    //2018-11-13
    //base url is: "http://ws.shiurdiario.com"
    @GET("/dafyomi.php")
    Call<WebResponse_previousDaf> getPreviousDapim(@Query("date") String date);

    @GET("/dafyomi.php")
    Call<WebResponse_masechet> getMasechtot(@Query("date") String date);

    @GET("/masechet.php")
    Call<WebResponse_shiurDaf> getShiurDapim(@Query("m") String masechet,
                                             @Query("p") Integer page);


}
