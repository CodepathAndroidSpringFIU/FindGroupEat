package com.example.findgroupeat.responses;

import com.example.findgroupeat.models.Restaurant;

import java.util.ArrayList;

public class RestaurantResponse {

    private ArrayList<Restaurant> restaurants;

    public ArrayList<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(ArrayList<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }
}
