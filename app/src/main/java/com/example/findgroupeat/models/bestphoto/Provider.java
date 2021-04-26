
package com.example.findgroupeat.models.bestphoto;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Provider {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("icon")
    @Expose
    private Icon__1 icon;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Icon__1 getIcon() {
        return icon;
    }

    public void setIcon(Icon__1 icon) {
        this.icon = icon;
    }

}
