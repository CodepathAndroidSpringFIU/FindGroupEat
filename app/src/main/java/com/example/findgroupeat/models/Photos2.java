package com.example.findgroupeat.models;

import android.app.Notification;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Photos2 {
    private Meta meta;
    private List<Notification> notifications = new ArrayList<Notification>();
    private PhotoResponse response;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     *     The meta
     */
    public Meta getMeta() {
        return meta;
    }

    /**
     *
     * @param meta
     *     The meta
     */
    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    /**
     *
     * @return
     *     The notifications
     */
    public List<Notification> getNotifications() {
        return notifications;
    }

    /**
     *
     * @param notifications
     *     The notifications
     */
    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

    /**
     *
     * @return
     *     The response
     */
    public PhotoResponse getResponse() {
        return response;
    }

    /**
     *
     * @param response
     *     The response
     */
    public void setResponse(PhotoResponse response) {
        this.response = response;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}
