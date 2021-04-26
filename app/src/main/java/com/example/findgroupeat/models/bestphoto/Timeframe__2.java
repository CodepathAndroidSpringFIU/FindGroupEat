
package com.example.findgroupeat.models.bestphoto;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Timeframe__2 {

    @SerializedName("days")
    @Expose
    private String days;
    @SerializedName("includesToday")
    @Expose
    private Boolean includesToday;
    @SerializedName("open")
    @Expose
    private List<Open__2> open = null;
    @SerializedName("segments")
    @Expose
    private List<java.lang.Object> segments = null;

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public Boolean getIncludesToday() {
        return includesToday;
    }

    public void setIncludesToday(Boolean includesToday) {
        this.includesToday = includesToday;
    }

    public List<Open__2> getOpen() {
        return open;
    }

    public void setOpen(List<Open__2> open) {
        this.open = open;
    }

    public List<java.lang.Object> getSegments() {
        return segments;
    }

    public void setSegments(List<java.lang.Object> segments) {
        this.segments = segments;
    }

}
