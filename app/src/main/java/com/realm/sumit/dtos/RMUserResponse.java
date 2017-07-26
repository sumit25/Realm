package com.realm.sumit.dtos;

import java.io.Serializable;

import io.realm.RealmObject;

/**
 * Created by sumit on 26/07/17.
 */

public class RMUserResponse extends RealmObject implements Serializable {

    private RMUserDTO user;

    public RMUserDTO getUser() {
        return user;
    }

    public void setUser(RMUserDTO user) {
        this.user = user;
    }
}
