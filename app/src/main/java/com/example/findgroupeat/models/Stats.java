
package com.example.findgroupeat.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Stats {

    @SerializedName("tipCount")
    @Expose
    private Integer tipCount;
    @SerializedName("usersCount")
    @Expose
    private Integer usersCount;
    @SerializedName("checkinsCount")
    @Expose
    private Integer checkinsCount;
    @SerializedName("visitsCount")
    @Expose
    private Integer visitsCount;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Stats() {
    }

    /**
     * 
     * @param visitsCount
     * @param checkinsCount
     * @param usersCount
     * @param tipCount
     */
    public Stats(Integer tipCount, Integer usersCount, Integer checkinsCount, Integer visitsCount) {
        super();
        this.tipCount = tipCount;
        this.usersCount = usersCount;
        this.checkinsCount = checkinsCount;
        this.visitsCount = visitsCount;
    }

    public Integer getTipCount() {
        return tipCount;
    }

    public void setTipCount(Integer tipCount) {
        this.tipCount = tipCount;
    }

    public Integer getUsersCount() {
        return usersCount;
    }

    public void setUsersCount(Integer usersCount) {
        this.usersCount = usersCount;
    }

    public Integer getCheckinsCount() {
        return checkinsCount;
    }

    public void setCheckinsCount(Integer checkinsCount) {
        this.checkinsCount = checkinsCount;
    }

    public Integer getVisitsCount() {
        return visitsCount;
    }

    public void setVisitsCount(Integer visitsCount) {
        this.visitsCount = visitsCount;
    }

}
