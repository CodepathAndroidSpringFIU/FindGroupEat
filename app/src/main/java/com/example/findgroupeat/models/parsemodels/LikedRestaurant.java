package com.example.findgroupeat.models.parsemodels;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.util.HashMap;

@ParseClassName("LikedRestaurant")
public class LikedRestaurant extends ParseObject {

    public static final String KEY_RESTAURANTID = "restaurantID";
    public static final String KEY_LIKES = "likes";

    public void setRestaurantID(String id) {
        put(KEY_RESTAURANTID, id);
    }

    public String getRestaurantID() {
        return getString(KEY_RESTAURANTID);
    }

    public Integer getLikes() {
        return getInt(KEY_LIKES);
    }


//    private HashMap<String, Integer> likedRestaurant;
//    int likedRestaurantCount = 0;
//
//    public LikedRestaurant() {
//        super();
//    }
//
//    public HashMap<String, Integer> getRestaurantLiked() {
//        return likedRestaurant;
//    }
//    public void setLikedRestaurant(String restaurantID) {
//        likedRestaurant.put(restaurantID, likedRestaurantCount++);
//    }
}
