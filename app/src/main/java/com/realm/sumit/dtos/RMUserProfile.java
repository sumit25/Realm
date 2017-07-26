package com.realm.sumit.dtos;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by sumit on 26/07/17.
 */

public class RMUserProfile {


    @SerializedName("_id")
    private String id;

    @SerializedName("user_document")
    private RMUserDocument userDocument;

    @SerializedName("user_lessons")
    private ArrayList<RMUserLesson> userLessons;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public RMUserDocument getUserDocument() {
        return userDocument;
    }

    public void setUserDocument(RMUserDocument userDocument) {
        this.userDocument = userDocument;
    }

    public ArrayList<RMUserLesson> getUserLessons() {
        return userLessons;
    }

    public void setUserLessons(ArrayList<RMUserLesson> userLessons) {
        this.userLessons = userLessons;
    }
}
