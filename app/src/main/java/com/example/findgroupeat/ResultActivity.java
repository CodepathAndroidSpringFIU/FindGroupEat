package com.example.findgroupeat;

import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.findgroupeat.models.bestphoto.Bestphotoreal2;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
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
        ivResultsPic = findViewById(R.id.ivResultPic);
        tvResultAddress = findViewById(R.id.tvResultAddress);
        tvResultName = findViewById(R.id.tvResultName);
        tvResultDescription = findViewById(R.id.tvResultDescription);
        tvResultNumber = findViewById(R.id.tvResultNumber);
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
                //String description = results.getResponse().getVenue().getDescription();  //Doesn't work currently
                String address = results.getResponse().getVenue().getLocation().getAddress();
                String number = results.getResponse().getVenue().getContact().getPhone();
                String picturePrefix = results.getResponse().getVenue().getBestPhoto().getPrefix();
                String pictureSuffix= results.getResponse().getVenue().getBestPhoto().getSuffix();
                String photoUrl = picturePrefix + "150x150" + pictureSuffix;

                Glide.with(getApplication())
                        .load(photoUrl)
                        .centerCrop()
                        .into(ivResultsPic);

                Log.v("ResultActivity", "the following items are: " + name + " " + address + " " + number);

                tvResultName.setText(name);
                tvResultAddress.setText(address);
                //tvResultDescription.setText(description); //Doesn't work currently.
                tvResultNumber.setText(number);

            }

            @Override
            public void onFailure(Call<Bestphotoreal2> call, Throwable t) {

            }
        });


    }
}