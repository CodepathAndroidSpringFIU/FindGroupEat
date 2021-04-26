
package com.example.findgroupeat.models;


import com.example.findgroupeat.models.Provider;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Delivery {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("provider")
    @Expose
    private Provider provider;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Delivery() {
    }

    /**
     * 
     * @param provider
     * @param id
     * @param url
     */
    public Delivery(String id, String url, Provider provider) {
        super();
        this.id = id;
        this.url = url;
        this.provider = provider;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

}
