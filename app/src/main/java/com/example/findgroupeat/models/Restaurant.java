package com.example.findgroupeat.models;

import com.google.gson.annotations.SerializedName;

public class Restaurant {
    @SerializedName("name")
    private String name;
    @SerializedName("id")
    private String id;
    @SerializedName("lng")
    private String longitude;
    @SerializedName("lat")
    private String latitude;
    @SerializedName("formattedAddress")
    private String[] address;
    @SerializedName("distance")
    private String distance;
    private RestaurantDetails bestPhoto;
    private String bestPhotoURL;



    public Restaurant(String name, String id, String longitude, String latitude, String[] address, String distance, RestaurantDetails bestPhoto, String bestPhotoURL) {
        this.name = name;
        this.id = id;
        this.longitude = longitude;
        this.latitude = latitude;
        this.address = address;
        this.distance = distance;
        this.bestPhoto = bestPhoto;
        this.bestPhotoURL = bestPhoto.getPrefix() + bestPhoto.getSize() + bestPhoto.getSuffix();

    }

    public RestaurantDetails getBestPhoto() {
        return bestPhoto;
    }

    public void setBestPhoto(RestaurantDetails bestPhoto) {
        this.bestPhoto = bestPhoto;
    }

    public String getBestPhotoURL() {
        return bestPhotoURL;
    }

    public void setBestPhotoURL(String bestPhotoURL) {
        this.bestPhotoURL = bestPhotoURL;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setAddress(String[] address) {
        this.address = address;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public String[] getAddress() {
        return address;
    }

    public String getDistance() {
        return distance;
    }
}
