
package com.example.findgroupeat.models;

import java.util.List;

import com.example.findgroupeat.models.Group;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Response {

    @SerializedName("suggestedRadius")
    @Expose
    private Integer suggestedRadius;
    @SerializedName("headerLocation")
    @Expose
    private String headerLocation;
    @SerializedName("headerFullLocation")
    @Expose
    private String headerFullLocation;
    @SerializedName("headerLocationGranularity")
    @Expose
    private String headerLocationGranularity;
    @SerializedName("query")
    @Expose
    private String query;
    @SerializedName("totalResults")
    @Expose
    private Integer totalResults;
    @SerializedName("suggestedBounds")
    @Expose
    private SuggestedBounds suggestedBounds;
    @SerializedName("groups")
    @Expose
    private List<Group> groups = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Response() {
    }

    public Response(Integer suggestedRadius, String headerLocation, String headerFullLocation, String headerLocationGranularity, String query, Integer totalResults, SuggestedBounds suggestedBounds, List<Group> groups) {
        this.suggestedRadius = suggestedRadius;
        this.headerLocation = headerLocation;
        this.headerFullLocation = headerFullLocation;
        this.headerLocationGranularity = headerLocationGranularity;
        this.query = query;
        this.totalResults = totalResults;
        this.suggestedBounds = suggestedBounds;
        this.groups = groups;
    }

    public Integer getSuggestedRadius() {
        return suggestedRadius;
    }

    public void setSuggestedRadius(Integer suggestedRadius) {
        this.suggestedRadius = suggestedRadius;
    }

    public String getHeaderLocation() {
        return headerLocation;
    }

    public void setHeaderLocation(String headerLocation) {
        this.headerLocation = headerLocation;
    }

    public String getHeaderFullLocation() {
        return headerFullLocation;
    }

    public void setHeaderFullLocation(String headerFullLocation) {
        this.headerFullLocation = headerFullLocation;
    }

    public String getHeaderLocationGranularity() {
        return headerLocationGranularity;
    }

    public void setHeaderLocationGranularity(String headerLocationGranularity) {
        this.headerLocationGranularity = headerLocationGranularity;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public SuggestedBounds getSuggestedBounds() {
        return suggestedBounds;
    }

    public void setSuggestedBounds(SuggestedBounds suggestedBounds) {
        this.suggestedBounds = suggestedBounds;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }
}
