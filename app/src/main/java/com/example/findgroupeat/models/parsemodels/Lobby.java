package com.example.findgroupeat.models.parsemodels;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseRelation;
import com.parse.ParseUser;

import org.json.JSONArray;
import org.parceler.Parcel;

import java.util.List;

@ParseClassName("Lobby")
public class Lobby extends ParseObject {

    public static final String KEY_NAME = "name";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_USERS = "users";

    public Lobby() {}

    public ParseRelation<ParseUser> getUsers() {
        return getRelation(KEY_USERS);
    }

    public void addUser(ParseUser user) {
        getUsers().add(user);
        saveInBackground();
    }

    public String getName() {
        return getString(KEY_NAME);
    }

    public void setName(String name) {
        put(KEY_NAME, name);
    }

    public String getPassword() {
        return getString(KEY_PASSWORD);
    }

    public void setPasword(String password) {
        put(KEY_PASSWORD, password);
    }
}
