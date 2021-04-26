
package com.example.findgroupeat.models.bestphoto;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class User {

    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("countryCode")
    @Expose
    private String countryCode;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("tips")
    @Expose
    private Tips tips;
    @SerializedName("lists")
    @Expose
    private Lists lists;
    @SerializedName("bio")
    @Expose
    private String bio;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Tips getTips() {
        return tips;
    }

    public void setTips(Tips tips) {
        this.tips = tips;
    }

    public Lists getLists() {
        return lists;
    }

    public void setLists(Lists lists) {
        this.lists = lists;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

}
