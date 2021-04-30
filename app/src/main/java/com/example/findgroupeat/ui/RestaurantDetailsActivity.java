package com.example.findgroupeat.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.findgroupeat.R;
import com.example.findgroupeat.RestaurantsActivity;
import com.example.findgroupeat.models.Restaurant;
import com.example.findgroupeat.models.RestaurantDetails;

import org.parceler.Parcels;

public class RestaurantDetailsActivity extends AppCompatActivity {

    private TextView tvRestaurantName;
    private TextView tvRestaurantAddress;
    private TextView tvRestaurantHours;
    private TextView tvRestaurantDescription;
    private ImageView ivRestaurantImage;
    private Restaurant restaurant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_details);
        tvRestaurantAddress = findViewById(R.id.tvAddress);
        tvRestaurantDescription = findViewById(R.id.tvRestaurantDescription);
        tvRestaurantHours = findViewById(R.id.tvHours);
        tvRestaurantName = findViewById(R.id.tvRestaurantName);
        ivRestaurantImage = findViewById(R.id.restaurantPhoto);
        Intent i = getIntent();
        String photoUrl = i.getStringExtra("restaurantDetailsPhotoUrl");
        String name = i.getStringExtra("restaurantDetailsName");
        String address = i.getStringExtra("restaurantDetailsAddress");
        String hours = i.getStringExtra("restaurantDetailsHours");
        String description = i.getStringExtra("restaurantDetailsDescription");
        tvRestaurantAddress.setText(address);
        tvRestaurantName.setText(name);
        tvRestaurantHours.setText(hours);
        tvRestaurantDescription.setText(description);

        Glide.with(this)
                .load(photoUrl)
                .centerCrop()
                .into(ivRestaurantImage);




    }
}