package com.example.findgroupeat.responses;

import com.example.findgroupeat.models.Restaurant;

import java.util.List;

public class RestaurantPhotoResponse {
    private List<Restaurant> venuePhotos;

    public List<Restaurant> getVenuePhotos() {
        return venuePhotos;
    }

    public void setVenuePhotos(List<Restaurant> venuePhotos) {
        this.venuePhotos = venuePhotos;
    }
}
