package com.realm.sumit.dtos;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by sumit on 26/07/17.
 */

public class RMUserLesson implements Serializable {

    @SerializedName("lesson_id")
    private String lessonsId;

    private String status;
    private RMLesson lesson;

    public String getLessonsId() {
        return lessonsId;
    }

    public void setLessonsId(String lessonsId) {
        this.lessonsId = lessonsId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public RMLesson getLesson() {
        return lesson;
    }

    public void setLesson(RMLesson lesson) {
        this.lesson = lesson;
    }
}
