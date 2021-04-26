package com.example.findgroupeat;


import com.example.findgroupeat.models.bestphoto.BestPhoto;
import com.example.findgroupeat.models.Explore;
import com.example.findgroupeat.models.Photos2;
import com.example.findgroupeat.models.bestphoto.Bestphotoreal2;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RestaurantService {



    @GET("venues/explore/")
    Call<Explore> getRestaurants(@Query("client_id") String clientID, @Query("client_secret") String clientSecret, @Query("ll") String latitudeLongitude,
                                                                 @Query("section") String section, @Query("radius") String radius, @Query("limit") int limit,
                                                                 @Query("categoryId") String categoryID, @Query("v") String version);

    @GET("venues/{VENUE_ID}/")
    Call<Bestphotoreal2> getRestaurantDetails(
            @Path("VENUE_ID") String VENUE_ID, @Query("client_id") String clientID, @Query("client_secret") String client_secret,
            @Query("v") String version
    );

    @GET("venues/{venue_id}/photos/")
        Call<Photos2> getPhotos(
                @Path("venue_id") String venue_id,
                @Query("client_id") String client_id,
                @Query("client_secret") String client_secret,
                @Query("v") String v);



    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.foursquare.com/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();



}
