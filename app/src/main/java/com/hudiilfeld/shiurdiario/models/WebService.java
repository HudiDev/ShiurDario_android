package com.hudiilfeld.shiurdiario.models;

import com.hudiilfeld.shiurdiario.models.WebResponse_daf;
import com.hudiilfeld.shiurdiario.models.WebResponse_masechet;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WebService {



        //Log.d("current_date", currentDate);
    //2018-11-13
    //base url is: "http://ws.shiurdiario.com"
    @GET("/dafyomi.php?date=")
    Call<WebResponse_daf> getDapim(@Query("date") String date);

    @GET("/dafyomi.php?date=")
    Call<WebResponse_masechet> getMasechtot(@Query("date") String date);



}