
package com.example.findgroupeat.models.bestphoto;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Page {

    @SerializedName("pageInfo")
    @Expose
    private PageInfo pageInfo;
    @SerializedName("user")
    @Expose
    private User user;

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
