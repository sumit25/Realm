package com.realm.sumit.dtos;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by sumit on 26/07/17.
 */

public class RMUserDocument implements Serializable{

    @SerializedName("_id")
    private String id;

    private String name;
    private String email;
    private long mobile;

    @SerializedName("username")
    private String userName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getMobile() {
        return mobile;
    }

    public void setMobile(long mobile) {
        this.mobile = mobile;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
