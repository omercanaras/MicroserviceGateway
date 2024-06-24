package com.omercan.gateway_app.channel.repository;

import com.google.gson.JsonElement;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface TransactionCallable
{
    @GET("api/transaction/user/{id}")
    Call<List<JsonElement>> getTransactionsOfUser(@Path("id") Integer userId);

    @GET("api/transaction/{id}")
    Call<JsonElement> findById(@Path("id") Integer id);

    @GET("api/transaction")
    Call<List<JsonElement>> getAll();

    //URL end Methodtype(@DELETE) important
    @DELETE("api/transaction/{id}")
    Call<String> deleteByID(@Path("id") Integer id);

    @POST("api/transaction")
    Call<JsonElement> save(@Body JsonElement transaction);
}
