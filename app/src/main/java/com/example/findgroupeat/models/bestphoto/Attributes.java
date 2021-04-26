
package com.example.findgroupeat.models.bestphoto;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Attributes {

    @SerializedName("groups")
    @Expose
    private List<Group__7> groups = null;

    public List<Group__7> getGroups() {
        return groups;
    }

    public void setGroups(List<Group__7> groups) {
        this.groups = groups;
    }

}
