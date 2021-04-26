package com.example.findgroupeat.models;

import com.google.gson.annotations.SerializedName;

public class RestaurantDetails {

    @SerializedName("name")
    private String name;
    @SerializedName("phone")
    private String phone;
    @SerializedName("id")
    private String id;
    @SerializedName("formattedAddress")
    private String [] formattedAddress;
    @SerializedName("description")
    private String description;
    @SerializedName("prefix")
    private String prefix;
    private String size;
    @SerializedName("suffix")
    private String suffix;



    public RestaurantDetails(String name, String phone, String id, String[] formattedAddress, String description, String getPhotoID, String prefix, String size, String suffix) {
        this.name = name;
        this.phone = phone;
        this.id = id;
        this.formattedAddress = formattedAddress;
        this.description = description;
        this.prefix = prefix;
        this.size = "300x300";
        this.suffix = suffix;


    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSize() {
        return "300x300";
    }

    public void setSize(String size) {
        this.size = "300x300";
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String[] getFormattedAddress() {
        return formattedAddress;
    }

    public void setFormattedAddress(String[] formattedAddress) {
        this.formattedAddress = formattedAddress;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
