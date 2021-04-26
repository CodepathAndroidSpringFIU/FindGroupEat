package com.example.findgroupeat.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Photos_response {
    private Integer count;
    private List<Photo_Restaurant> items = new ArrayList<>();
    private Integer dupesRemoved;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     *     The count
     */
    public Integer getCount() {
        return count;
    }

    /**
     *
     * @param count
     *     The count
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     *
     * @return
     *     The items
     */
    public List<Photo_Restaurant> getItems() {
        return items;
    }

    /**
     *
     * @param items
     *     The items
     */
    public void setItems(List<Photo_Restaurant> items) {
        this.items = items;
    }

    /**
     *
     * @return
     *     The dupesRemoved
     */
    public Integer getDupesRemoved() {
        return dupesRemoved;
    }

    /**
     *
     * @param dupesRemoved
     *     The dupesRemoved
     */
    public void setDupesRemoved(Integer dupesRemoved) {
        this.dupesRemoved = dupesRemoved;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}
