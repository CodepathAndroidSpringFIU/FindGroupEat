
package com.example.findgroupeat.models.bestphoto;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ListItems {

    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("items")
    @Expose
    private List<Item__4> items = null;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<Item__4> getItems() {
        return items;
    }

    public void setItems(List<Item__4> items) {
        this.items = items;
    }

}
