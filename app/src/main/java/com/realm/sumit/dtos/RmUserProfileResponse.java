package com.realm.sumit.dtos;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by sumit on 26/07/17.
 */

public class RmUserProfileResponse implements Serializable{

    @SerializedName("user_profile")
    private RMUserProfile userProfile;

    public RMUserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(RMUserProfile userProfile) {
        this.userProfile = userProfile;
    }
}
