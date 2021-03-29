package com.example.findgroupeat.models;

import com.parse.ParseClassName;
import com.parse.ParseObject;

import org.json.JSONArray;

import java.util.List;

@ParseClassName("Restaurant")
public class Restaurant extends ParseObject {
    public static final String KEY_ID = "objectId";
    public static final String KEY_NAME = "name";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_PHOTO = "photoUrl";
    public static final String KEY_TAGS = "tags";

    public String getName() {
        return getString(KEY_NAME);
    }

    public void setName(String name) {
        put(KEY_NAME, name);
    }

    public String getDescription() {
        return getString(KEY_DESCRIPTION);
    }

    public void setDescription(String description) {
        put(KEY_DESCRIPTION, description);
    }

    public String getPhoto() {
        return getString(KEY_PHOTO);
    }

    public void setPhoto(String photoUrl) {
        put(KEY_PHOTO, photoUrl);
    }

    public JSONArray getTags() {
        return getJSONArray(KEY_TAGS);
    }

    public void setTags(List<String> tags) {
        put(KEY_TAGS, tags);
    }

    public String getID() {
        return getString(KEY_ID);
    }
}
