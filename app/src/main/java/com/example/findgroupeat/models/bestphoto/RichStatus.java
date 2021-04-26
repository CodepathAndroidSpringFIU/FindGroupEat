
package com.example.findgroupeat.models.bestphoto;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class RichStatus {

    @SerializedName("entities")
    @Expose
    private List<java.lang.Object> entities = null;
    @SerializedName("text")
    @Expose
    private String text;

    public List<java.lang.Object> getEntities() {
        return entities;
    }

    public void setEntities(List<java.lang.Object> entities) {
        this.entities = entities;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
