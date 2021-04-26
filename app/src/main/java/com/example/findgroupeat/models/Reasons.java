
package com.example.findgroupeat.models;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Reasons {

    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("items")
    @Expose
    private List<Item__1> items = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Reasons() {
    }

    /**
     * 
     * @param count
     * @param items
     */
    public Reasons(Integer count, List<Item__1> items) {
        super();
        this.count = count;
        this.items = items;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<Item__1> getItems() {
        return items;
    }

    public void setItems(List<Item__1> items) {
        this.items = items;
    }

}
