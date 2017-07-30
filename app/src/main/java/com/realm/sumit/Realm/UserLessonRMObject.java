package com.realm.sumit.Realm;

import io.realm.RealmObject;

/**
 * Created by sumit on 28/07/17.
 */

public class UserLessonRMObject extends RealmObject {

    private String lessonId;
    private String status;
    private String lessonTitle;

    public String getLessonId() {
        return lessonId;
    }

    public void setLessonId(String lessonId) {
        this.lessonId = lessonId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLessonTitle() {
        return lessonTitle;
    }

    public void setLessonTitle(String lessonTitle) {
        this.lessonTitle = lessonTitle;
    }
}
