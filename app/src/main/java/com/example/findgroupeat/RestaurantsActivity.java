package com.example.findgroupeat;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DiffUtil;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Toast;

import com.example.findgroupeat.adapters.RestaurantsAdapter;
import com.example.findgroupeat.models.Explore;
import com.example.findgroupeat.models.GPSTracker;
import com.example.findgroupeat.models.Group;
import com.example.findgroupeat.models.Item;
import com.example.findgroupeat.models.bestphoto.Bestphotoreal2;


import com.example.findgroupeat.models.parsemodels.LikedRestaurant;
import com.example.findgroupeat.models.parsemodels.Lobby;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.parse.CountCallback;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.yuyakaido.android.cardstackview.CardStackLayoutManager;
import com.yuyakaido.android.cardstackview.CardStackListener;
import com.yuyakaido.android.cardstackview.CardStackView;
import com.yuyakaido.android.cardstackview.Direction;
import com.yuyakaido.android.cardstackview.StackFrom;
import com.yuyakaido.android.cardstackview.SwipeableMethod;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestaurantsActivity extends AppCompatActivity implements CardStackListener {
    private static final String TAG = "MainActivity";

    private final String CLIENT_ID = BuildConfig.FQ_CLIENT_ID;
    private final String CLIENT_SECRET = BuildConfig.FQ_CLIENT_SECRET;
    private static final String BASE_URL = "https://api.foursquare.com/v2/";
    private final static String version = "20190519";
    private GPSTracker gps;
    private Retrofit retrofit = null;
    private int limit = 5;
    private int numUsers;

    TranslateAnimation animation;
    private CardStackView cardStackView;
    private CardStackLayoutManager cardStackLayoutManager;
    private RestaurantsAdapter adapter;
    private DrawerLayout drawerLayout;
    private RestaurantService restaurantService;
    private Context mContext;
    String fullCoordinates = null;


    List<Item> restaurantList;
    List<Item> likedRestaurants;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);

        mContext = this;
        likedRestaurants = new ArrayList<>();
        try {
            numUsers = getUserNumInLobby();
        } catch (ParseException e) {
            Log.e("RestaurantsActivity", e.toString());
        }

        if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(RestaurantsActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

        } else {
            Toast.makeText(mContext,"You need to have granted permission",Toast.LENGTH_SHORT).show();
            gps = new GPSTracker(mContext, RestaurantsActivity.this);

            // Check if GPS enabled
            if (gps.canGetLocation()) {

                double latitude = gps.getLatitude();
                double longitude = gps.getLongitude();
                fullCoordinates = gps.getLatitude() + "," + gps.getLongitude();

                // \n is for new line
                //Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
            } else {
                // Can't get location.
                // GPS or network is not enabled.
                // Ask user to enable GPS/network in settings.
                gps.showSettingsAlert();
            }
        }
        Log.v(TAG, "full cords are : " + fullCoordinates);

        RxJava3CallAdapterFactory rxAdapter = RxJava3CallAdapterFactory.create();
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(rxAdapter).build();
        }
        restaurantService = retrofit.create(RestaurantService.class);
        cardStackView = findViewById(R.id.card_stack_view);
        adapter = new RestaurantsAdapter(restaurantList, getApplicationContext());
        drawerLayout = findViewById(R.id.drawer_layout);
        cardStackLayoutManager = new CardStackLayoutManager(this, this);



        restaurantList = new ArrayList<>();
        Call<Explore> call1 = restaurantService.getRestaurants(CLIENT_ID, CLIENT_SECRET, fullCoordinates, "browse", "10000", 5, "4d4b7105d754a06374d81259",
                version);



        setUpNavigation();


        

        call1.enqueue(new Callback<Explore>() {

            @Override
            public void onResponse(Call<Explore> call, Response<Explore> response) {
                Explore venue = response.body();
                int totalResults = venue.getResponse().getTotalResults();
                Log.v(TAG, "total results is: " + totalResults);
                List<Group> groupList = venue.getResponse().getGroups();

                restaurantList.addAll(groupList.get(0).getItems());

                System.out.println("the amount of items in restaurant List is " + restaurantList.size());
                totalResults = Math.min(totalResults, 5);
                for (int i = 0; i < totalResults; i++) {
                    Call<Bestphotoreal2> call2 = restaurantService.getRestaurantDetails(restaurantList.get(i).getVenue().getId(), CLIENT_ID, CLIENT_SECRET, version);
                    restaurantList.get(i).setPhotoUrl(getPhotos(call2, totalResults));
                    initialize();

                }

                System.out.println("the amount of items in restaurant List after getPhotosCall " + restaurantList.size());


            }

            @Override
            public void onFailure(Call<Explore> call, Throwable t) {
                System.out.println("explore failed");
            }
        });
        initialize();





    }






    public List<String> getPhotos(Call<Bestphotoreal2> call2, int totalResults) {
        List<String> photoUrlList2 = new ArrayList<>();
        call2.enqueue(new Callback<Bestphotoreal2>() {
            @Override
            public void onResponse(Call<Bestphotoreal2> call, Response<Bestphotoreal2> response) {

                Bestphotoreal2 bestPhoto = response.body();
                Log.v(TAG, String.valueOf(response.body()));
                List<String> photoUrlList = new ArrayList<>();

                    int numOfPhotos = response.body().getResponse().getVenue().getPhotos().getCount();
                    Log.v(TAG, "num of photos is: " + numOfPhotos);
                    numOfPhotos = Math.min(numOfPhotos, 5);
                String prefix = bestPhoto.getResponse().getVenue().getBestPhoto().getPrefix();
                String suffix = bestPhoto.getResponse().getVenue().getBestPhoto().getSuffix();
                String photoUrl = prefix + "150x150" + suffix;
                Log.v(TAG, "photoURL is " + photoUrl);

                photoUrlList.add(photoUrl);
                photoUrlList2.add(photoUrl);
                Log.v(TAG, "num of items in photoUrl is " + photoUrlList.size());
                if (!photoUrlList.contains(photoUrl)) {
                    for (int i = 0; i < restaurantList.size(); i++) {
                        Log.v(TAG, "restaurant List Size is : " + restaurantList.size());
                        restaurantList.get(i).addPhotoUrl(photoUrlList, photoUrl);
                        restaurantList.get(i).setPhotoUrl(photoUrlList);



                    }
                }
                initialize();


            }


            @Override
            public void onFailure(Call<Bestphotoreal2> call, Throwable t) {
                Log.v(TAG, "Failed on call to photo");
            }

        });
        return photoUrlList2;
    }














    @Override
    public void onCardDragging(Direction direction, float ratio) {
        Log.d(TAG, "onCardSwiped: p=" + cardStackLayoutManager.getTopPosition() + " d=" + direction);

    }


    @Override
    public void onCardSwiped(Direction direction) {
        if (direction == Direction.Right) {
            createLikedRestaurant();
        }
        if (cardStackLayoutManager.getTopPosition() == adapter.getItemCount() - 5) {
            paginate();
        }
    }

    public int getUserNumInLobby() throws ParseException {
        Lobby lobby = (Lobby) Parcels.unwrap(getIntent().getParcelableExtra("lobby"));
        ParseQuery<ParseUser> users = lobby.getUsers().getQuery();
        return users.count();
    }
    public void createLikedRestaurant() {
        int topPosition = cardStackLayoutManager.getTopPosition();
        String likedRestaurantID = adapter.getRestaurants().get(topPosition).getVenue().getId();
        ParseQuery<LikedRestaurant> query = ParseQuery.getQuery(LikedRestaurant.class);
        query.whereEqualTo("restaurantID", likedRestaurantID);
        query.findInBackground(new FindCallback<LikedRestaurant>() {
            @Override
            public void done(List<LikedRestaurant> objects, ParseException e) {
                if (e == null) {
                    if (objects.isEmpty()) {
                        LikedRestaurant restaurant = new LikedRestaurant();
                        restaurant.setRestaurantID(likedRestaurantID);
                        restaurant.saveInBackground(er ->  {
                            if (er == null) {
                                //saved
                            } else {
                                Toast.makeText(getApplicationContext(), er.toString(), Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                    else {
                        objects.get(0).increment("likes");
                        objects.get(0).saveInBackground();
                        // Check again the number of users in the lobby

                        if (objects.get(0).getLikes() >= numUsers) {
                            Intent i = new Intent(RestaurantsActivity.this, ResultActivity.class);
                            String restaurantID = restaurantList.get(cardStackLayoutManager.getTopPosition()-1).getVenue().getId();
                            Lobby lobby = (Lobby) Parcels.unwrap(getIntent().getParcelableExtra("lobby"));
                            String lobbyObjectId = lobby.getObjectId();
                            i.putExtra("restaurantID", restaurantID);
                            i.putExtra("lobbyObjectId", lobbyObjectId);
                            startActivity(i);
                            finish();
                        }
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        });
//
    }



    @Override
    public void onCardRewound() {

    }

    @Override
    public void onCardCanceled() {

    }

    @Override
    public void onCardAppeared(View view, int position) {
        //TextView tv = view.findViewById(R.id.item_name);
    }

    @Override
    public void onCardDisappeared(View view, int position) {
        //TextView tv = view.findViewById(R.id.item_name);
        String restaurantName = restaurantList.get(0).getVenue().getName();
    }

    private void setUpNavigation() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open_drawer, R.string.close_drawer);
        actionBarDrawerToggle.syncState();
        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        NavigationView navigationView = findViewById(R.id.navigation_view);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                drawerLayout.closeDrawers();
                return true;
            }
        });

    }

    private void initialize() {
        cardStackLayoutManager.setStackFrom(StackFrom.None);
        cardStackLayoutManager.setVisibleCount(3);
        cardStackLayoutManager.setTranslationInterval(8.0f);
        cardStackLayoutManager.setScaleInterval(0.95f);
        cardStackLayoutManager.setSwipeThreshold(0.3f);
        cardStackLayoutManager.setMaxDegree(20.0f);
        cardStackLayoutManager.setDirections(Direction.HORIZONTAL);
        cardStackLayoutManager.setCanScrollHorizontal(true);
        cardStackLayoutManager.setSwipeableMethod(SwipeableMethod.Manual);
        cardStackLayoutManager.setOverlayInterpolator(new LinearInterpolator());
        System.out.println("amount of items in restaurant list in initialize is " + restaurantList.size());
        cardStackView.setLayoutManager(cardStackLayoutManager);
        adapter = new RestaurantsAdapter(restaurantList, getApplicationContext());
        cardStackView.setAdapter(adapter);
        cardStackView.setItemAnimator(new DefaultItemAnimator());

    }

    private void paginate() {
        List<Item> old = adapter.getRestaurants();
        List<Item> newList = new ArrayList<>();
        CardStackCallback callback = new CardStackCallback(old, newList);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(callback);
        adapter.setRestaurants(newList);
        diffResult.dispatchUpdatesTo(adapter);

    }

    private void setupButton() {
        FloatingActionButton likeButton = findViewById(R.id.like_button);
        likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardStackView.swipe();

            }
        });
        FloatingActionButton rewindButton = findViewById(R.id.rewind_button);
        rewindButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardStackView.rewind();
            }
        });
    }
}





