package com.example.findgroupeat.models;

import com.example.findgroupeat.models.Photos_;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

public class PhotoResponse {
    @SerializedName("photos")
    private Photos_response photos;

    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     *     The photos
     */
    public Photos_response getPhotos() {
        return photos;
    }

    /**
     *
     * @param photos
     *     The photos
     */
    public void setPhotos(Photos_response photos) {
        this.photos = photos;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}
