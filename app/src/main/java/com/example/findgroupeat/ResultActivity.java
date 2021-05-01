package com.example.findgroupeat;

import android.content.Intent;
import android.os.Bundle;

import com.example.findgroupeat.models.bestphoto.Bestphotoreal2;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ResultActivity extends AppCompatActivity {

    private final String CLIENT_ID = BuildConfig.FQ_CLIENT_ID;
    private final String CLIENT_SECRET = BuildConfig.FQ_CLIENT_SECRET;
    private static final String BASE_URL = "https://api.foursquare.com/v2/";
    private final static String version = "20190519";
    private Retrofit retrofit = null;
    private RestaurantService restaurantService;
    private ImageView ivResultsPic;
    private TextView tvResultName;
    private TextView tvResultDescription;
    private TextView tvResultAddress;
    private TextView tvResultNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Toolbar toolbar = findViewById(R.id.toolbar);
        tvResultAddress = findViewById(R.id.tvResultAddress);
        tvResultName = findViewById(R.id.tvResultName);
        tvResultDescription = findViewById(R.id.tvRestaurantDescription);
        tvResultNumber = findViewById(R.id.tvRestaurantDescription);
        setSupportActionBar(toolbar);
        RxJava3CallAdapterFactory rxAdapter = RxJava3CallAdapterFactory.create();
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(rxAdapter).build();
        }
        restaurantService = retrofit.create(RestaurantService.class);

        Intent i = getIntent();
        String restaurantID = i.getStringExtra("restaurantID");


        Call<Bestphotoreal2> call2 = restaurantService.getRestaurantDetails(restaurantID, CLIENT_ID, CLIENT_SECRET, version);

        call2.enqueue(new Callback<Bestphotoreal2>() {
            @Override
            public void onResponse(Call<Bestphotoreal2> call, Response<Bestphotoreal2> response) {
                Bestphotoreal2 results = response.body();

                String name = results.getResponse().getVenue().getName();
                String description = results.getResponse().getVenue().getDescription();
                String address = results.getResponse().getVenue().getLocation().getAddress();
                String number = results.getResponse().getVenue().getContact().getPhone();

                tvResultName.setText(name);
                tvResultAddress.setText(address);
                tvResultDescription.setText(description);
                tvResultNumber.setText(number);

            }

            @Override
            public void onFailure(Call<Bestphotoreal2> call, Throwable t) {

            }
        });

    }
}