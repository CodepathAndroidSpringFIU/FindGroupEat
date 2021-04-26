
package com.example.findgroupeat.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Item__1 {

    @SerializedName("summary")
    @Expose
    private String summary;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("reasonName")
    @Expose
    private String reasonName;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Item__1() {
    }

    /**
     * 
     * @param summary
     * @param reasonName
     * @param type
     */
    public Item__1(String summary, String type, String reasonName) {
        super();
        this.summary = summary;
        this.type = type;
        this.reasonName = reasonName;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReasonName() {
        return reasonName;
    }

    public void setReasonName(String reasonName) {
        this.reasonName = reasonName;
    }

}
