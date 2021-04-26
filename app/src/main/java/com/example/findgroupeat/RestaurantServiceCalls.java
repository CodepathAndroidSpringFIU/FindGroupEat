package com.example.findgroupeat;

import android.os.Build;

import com.example.findgroupeat.models.Explore;
import com.example.findgroupeat.models.Item;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestaurantServiceCalls implements Callback<Explore> {

    static final String BASE_URL = "https://api.foursquare.com/v2/";
    private static final String CLIENT_ID = BuildConfig.FQ_CLIENT_ID;
    private static final String CLIENT_SECRET = BuildConfig.FQ_CLIENT_SECRET;


    public void start() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        RestaurantService restaurantService = retrofit.create(RestaurantService.class);


    }



    @Override
    public void onResponse(Call<Explore> call, Response<Explore> response) {

    }

    @Override
    public void onFailure(Call<Explore> call, Throwable t) {

    }
}
