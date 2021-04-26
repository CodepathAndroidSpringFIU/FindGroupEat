
package com.example.findgroupeat.models.bestphoto;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Response {

    @SerializedName("venue")
    @Expose
    private Venue venue;

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

}
