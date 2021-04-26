
package com.example.findgroupeat.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class BeenHere {

    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("lastCheckinExpiredAt")
    @Expose
    private Integer lastCheckinExpiredAt;
    @SerializedName("marked")
    @Expose
    private Boolean marked;
    @SerializedName("unconfirmedCount")
    @Expose
    private Integer unconfirmedCount;

    /**
     * No args constructor for use in serialization
     * 
     */
    public BeenHere() {
    }

    /**
     * 
     * @param marked
     * @param count
     * @param lastCheckinExpiredAt
     * @param unconfirmedCount
     */
    public BeenHere(Integer count, Integer lastCheckinExpiredAt, Boolean marked, Integer unconfirmedCount) {
        super();
        this.count = count;
        this.lastCheckinExpiredAt = lastCheckinExpiredAt;
        this.marked = marked;
        this.unconfirmedCount = unconfirmedCount;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getLastCheckinExpiredAt() {
        return lastCheckinExpiredAt;
    }

    public void setLastCheckinExpiredAt(Integer lastCheckinExpiredAt) {
        this.lastCheckinExpiredAt = lastCheckinExpiredAt;
    }

    public Boolean getMarked() {
        return marked;
    }

    public void setMarked(Boolean marked) {
        this.marked = marked;
    }

    public Integer getUnconfirmedCount() {
        return unconfirmedCount;
    }

    public void setUnconfirmedCount(Integer unconfirmedCount) {
        this.unconfirmedCount = unconfirmedCount;
    }

}
