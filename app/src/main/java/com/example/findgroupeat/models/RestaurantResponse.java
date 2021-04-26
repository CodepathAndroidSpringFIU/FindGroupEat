package com.example.findgroupeat.models;

import java.util.ArrayList;
import java.util.List;

public class RestaurantResponse {
    public List<Restaurant> restaurant;

    public RestaurantResponse(List<Restaurant> restaurant) {
        this.restaurant = new ArrayList<Restaurant>();
    }

    public List<Restaurant> getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(List<Restaurant> restaurant) {
        this.restaurant = restaurant;
    }
}
