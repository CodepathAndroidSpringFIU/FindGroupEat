package com.example.findgroupeat.models.parsemodels;

import com.parse.ParseClassName;
import com.parse.ParseObject;

import java.util.HashMap;

@ParseClassName("LikedRestaurant")
public class likedRestaurant extends ParseObject {
    private HashMap<String, Integer> likedRestaurant;
    int likedRestaurantCount = 0;

    public likedRestaurant() {
        super();
    }

    public HashMap<String, Integer> getRestaurantLiked() {
        return likedRestaurant;
    }
    public void setLikedRestaurant(String restaurantID) {
        likedRestaurant.put(restaurantID, likedRestaurantCount++);
    }
}
