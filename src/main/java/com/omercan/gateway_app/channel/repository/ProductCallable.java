package com.omercan.gateway_app.channel.repository;

import com.google.gson.JsonElement;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface ProductCallable
{
    @GET("api/product")
    Call<List<JsonElement>> getAll();

    @DELETE("api/product/{id}")
    Call<String> deleteByID(@Path("id") Integer id);

    @GET("api/product/{id}")
    Call<JsonElement> findByID(@Path("id") Integer id);

    @POST("api/product")
    Call<JsonElement> save(@Body JsonElement product);
}
