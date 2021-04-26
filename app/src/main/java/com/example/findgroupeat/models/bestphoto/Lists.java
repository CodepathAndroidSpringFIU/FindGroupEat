
package com.example.findgroupeat.models.bestphoto;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Lists {

    @SerializedName("groups")
    @Expose
    private List<Group__2> groups = null;

    public List<Group__2> getGroups() {
        return groups;
    }

    public void setGroups(List<Group__2> groups) {
        this.groups = groups;
    }

}
