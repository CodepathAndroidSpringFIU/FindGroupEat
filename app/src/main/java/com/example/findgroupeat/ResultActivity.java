package com.example.findgroupeat;


import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.findgroupeat.models.bestphoto.Bestphotoreal2;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.findgroupeat.models.parsemodels.Lobby;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import org.parceler.Parcels;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

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
    private Button btnExit;


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
        btnExit = findViewById(R.id.btnExit);

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
        String lobbyObjectId = i.getStringExtra("lobbyObjectId");

        Call<Bestphotoreal2> call2 = restaurantService.getRestaurantDetails(restaurantID, CLIENT_ID, CLIENT_SECRET, version);

        call2.enqueue(new Callback<Bestphotoreal2>() {
            @Override
            public void onResponse(Call<Bestphotoreal2> call, Response<Bestphotoreal2> response) {
                Bestphotoreal2 results = response.body();

                String name = results.getResponse().getVenue().getName();
                String description = results.getResponse().getVenue().getDescription();//Doesn't work currently
                String address = results.getResponse().getVenue().getLocation().getAddress();
                String number = results.getResponse().getVenue().getContact().getFormattedPhone();
                String picturePrefix = results.getResponse().getVenue().getBestPhoto().getPrefix();
                String pictureSuffix= results.getResponse().getVenue().getBestPhoto().getSuffix();
                String photoUrl = picturePrefix + "150x150" + pictureSuffix;

                Glide.with(getApplication())
                        .load(photoUrl)
                        .centerCrop()
                        .into(ivResultsPic);

                Log.v("ResultActivity", "the following items are: " + name + " " + address + " " + number + " " + description);

                tvResultName.setText(name);
                tvResultAddress.setText("Address: " + address);
                tvResultDescription.setText(description);
                tvResultNumber.setText("Phone Number: " + number);

                // add to user's history
                //addToHistory(lobbyObjectId, name);
            }

            @Override
            public void onFailure(Call<Bestphotoreal2> call, Throwable t) {

            }
        });

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ResultActivity.this, MainActivity.class);
                startActivity(i);

                //Remove the user from lobby
                //lobby.removeUser(ParseUser.getCurrentUser());

                finish();
                //Remove the user from looby


                //Check number of users in the lobby, if it's 0 then delete the lobby
                deleteLobby(lobbyObjectId);

            }
         });
        }


//    private void addToHistory(String lobbyId, String name) {
//        //ParseQuery<Lobby> query = ParseQuery<>(Lobby.class);
//
//        ParseQuery<ParseUser> users = lobby.getUsers().getQuery();
//        users.findInBackground(new FindCallback<ParseUser>() {
//            @Override
//            public void done(List<ParseUser> objects, ParseException e) {
//                if (e != null) {
//                    Log.e("ResultActivity", "Error in getting users");
//                }
//                for (ParseUser user: objects) {
//                    user.add("restaurants", name);
//                    user.saveInBackground();
//                }
//            }
//        });
//    }

    public int getUserNumInLobby() throws ParseException {
        Lobby lobby = (Lobby) Parcels.unwrap(getIntent().getParcelableExtra("lobby"));
        ParseQuery<ParseUser> users = lobby.getUsers().getQuery();
        return users.count();
    }

    public void deleteLobby(String lobbyObjectId) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Lobby");
        query.getInBackground(lobbyObjectId, (object, e) -> {
            if (e == null) {
                //Object was fetched
                //Deletes the fetched ParseObject from the database
                object.deleteInBackground(e2 -> {
                    if (e2 == null) {
                        Toast.makeText(this, "Delete Successful", Toast.LENGTH_SHORT).show();
                    } else {
                        //Something went wrong while deleting the Object
                        Toast.makeText(this, "Error: " + e2.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                //Something went wrong
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }




}