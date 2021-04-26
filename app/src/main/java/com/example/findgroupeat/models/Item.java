
package com.example.findgroupeat.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Item {

    @SerializedName("reasons")
    @Expose
    private Reasons reasons;
    @SerializedName("venue")
    @Expose
    private Venue venue;
    @SerializedName("referralId")
    @Expose
    private String referralId;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private Photos_ photos;

    public List<String> getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(List<String> photoUrl) {
        this.photoUrl = photoUrl;
    }

    public void addPhotoUrl(List<String> photoUrl, String itemToAdd) {
        this.photoUrl.add(itemToAdd);
    }

    public Photos_ getPhotos() {
        return photos;
    }

    public void setPhotos(Photos_ photos) {
        this.photos = photos;
    }

    private List<String> photoUrl;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Item() {
    }




    public Reasons getReasons() {
        return reasons;
    }

    public void setReasons(Reasons reasons) {
        this.reasons = reasons;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public String getReferralId() {
        return referralId;
    }

    public void setReferralId(String referralId) {
        this.referralId = referralId;
    }


    @Override
    public String toString() {
        return "Item{" +
                "reasons=" + reasons +
                ", venue=" + venue +
                ", referralId='" + referralId + '\'' +
                ", additionalProperties=" + additionalProperties +
                ", photos=" + photos +
                ", photoUrl=" + photoUrl +
                '}';
    }
}
