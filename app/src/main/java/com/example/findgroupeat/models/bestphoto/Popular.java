
package com.example.findgroupeat.models.bestphoto;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Popular {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("richStatus")
    @Expose
    private RichStatus__1 richStatus;
    @SerializedName("isOpen")
    @Expose
    private Boolean isOpen;
    @SerializedName("isLocalHoliday")
    @Expose
    private Boolean isLocalHoliday;
    @SerializedName("timeframes")
    @Expose
    private List<Timeframe__1> timeframes = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public RichStatus__1 getRichStatus() {
        return richStatus;
    }

    public void setRichStatus(RichStatus__1 richStatus) {
        this.richStatus = richStatus;
    }

    public Boolean getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(Boolean isOpen) {
        this.isOpen = isOpen;
    }

    public Boolean getIsLocalHoliday() {
        return isLocalHoliday;
    }

    public void setIsLocalHoliday(Boolean isLocalHoliday) {
        this.isLocalHoliday = isLocalHoliday;
    }

    public List<Timeframe__1> getTimeframes() {
        return timeframes;
    }

    public void setTimeframes(List<Timeframe__1> timeframes) {
        this.timeframes = timeframes;
    }

}
