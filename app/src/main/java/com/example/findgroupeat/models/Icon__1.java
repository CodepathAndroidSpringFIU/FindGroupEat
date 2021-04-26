
package com.example.findgroupeat.models;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Icon__1 {

    @SerializedName("prefix")
    @Expose
    private String prefix;
    @SerializedName("sizes")
    @Expose
    private List<Integer> sizes = null;
    @SerializedName("name")
    @Expose
    private String name;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Icon__1() {
    }

    /**
     * 
     * @param sizes
     * @param prefix
     * @param name
     */
    public Icon__1(String prefix, List<Integer> sizes, String name) {
        super();
        this.prefix = prefix;
        this.sizes = sizes;
        this.name = name;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public List<Integer> getSizes() {
        return sizes;
    }

    public void setSizes(List<Integer> sizes) {
        this.sizes = sizes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
