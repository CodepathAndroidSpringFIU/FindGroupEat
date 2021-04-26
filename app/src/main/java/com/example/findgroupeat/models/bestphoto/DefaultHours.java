
package com.example.findgroupeat.models.bestphoto;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class DefaultHours {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("richStatus")
    @Expose
    private RichStatus__2 richStatus;
    @SerializedName("isOpen")
    @Expose
    private Boolean isOpen;
    @SerializedName("isLocalHoliday")
    @Expose
    private Boolean isLocalHoliday;
    @SerializedName("dayData")
    @Expose
    private List<java.lang.Object> dayData = null;
    @SerializedName("timeframes")
    @Expose
    private List<Timeframe__2> timeframes = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public RichStatus__2 getRichStatus() {
        return richStatus;
    }

    public void setRichStatus(RichStatus__2 richStatus) {
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

    public List<java.lang.Object> getDayData() {
        return dayData;
    }

    public void setDayData(List<java.lang.Object> dayData) {
        this.dayData = dayData;
    }

    public List<Timeframe__2> getTimeframes() {
        return timeframes;
    }

    public void setTimeframes(List<Timeframe__2> timeframes) {
        this.timeframes = timeframes;
    }

}
