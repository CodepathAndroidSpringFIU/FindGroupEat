
package com.example.findgroupeat.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class SuggestedBounds {

    @SerializedName("ne")
    @Expose
    private Ne ne;
    @SerializedName("sw")
    @Expose
    private Sw sw;

    /**
     * No args constructor for use in serialization
     * 
     */
    public SuggestedBounds() {
    }

    /**
     * 
     * @param sw
     * @param ne
     */
    public SuggestedBounds(Ne ne, Sw sw) {
        super();
        this.ne = ne;
        this.sw = sw;
    }

    public Ne getNe() {
        return ne;
    }

    public void setNe(Ne ne) {
        this.ne = ne;
    }

    public Sw getSw() {
        return sw;
    }

    public void setSw(Sw sw) {
        this.sw = sw;
    }

}
