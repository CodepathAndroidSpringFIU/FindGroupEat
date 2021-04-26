
package com.example.findgroupeat.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Provider {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("icon")
    @Expose
    private Icon__1 icon;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Provider() {
    }

    /**
     * 
     * @param name
     * @param icon
     */
    public Provider(String name, Icon__1 icon) {
        super();
        this.name = name;
        this.icon = icon;
    }

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
