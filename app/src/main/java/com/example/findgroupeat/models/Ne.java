
package com.example.findgroupeat.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Ne {

    @SerializedName("lat")
    @Expose
    private Double lat;
    @SerializedName("lng")
    @Expose
    private Double lng;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Ne() {
    }

    /**
     * 
     * @param lng
     * @param lat
     */
    public Ne(Double lat, Double lng) {
        super();
        this.lat = lat;
        this.lng = lng;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

}