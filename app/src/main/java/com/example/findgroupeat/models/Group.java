package com.example.findgroupeat.models;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

import org.json.JSONArray;

import java.util.List;

@ParseClassName("Group")
public class Group extends ParseObject {

    public static final String KEY_ID = "objectId";
    public static final String KEY_USERS = "users";

    public JSONArray getUsers() {
        return getJSONArray(KEY_USERS);
    }

    public void setUsers(List<ParseUser> users) {
        put(KEY_USERS, users);
    }

    public String getID() {
        return getString(KEY_ID);
    }
}
