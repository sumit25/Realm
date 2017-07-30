package com.realm.sumit.Realm;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by sumit on 27/07/17.
 */

public class UserProfileRMObject extends RealmObject {

    private String id;
    private String documentId;
    private String name;
    private String email;
    private long mobile;
    private String userName;

    private RealmList<UserLessonRMObject> userLessons;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
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

    public RealmList<UserLessonRMObject> getUserLessons() {
        return userLessons;
    }

    public void setUserLessons(RealmList<UserLessonRMObject> userLessons) {
        this.userLessons = userLessons;
    }
}
