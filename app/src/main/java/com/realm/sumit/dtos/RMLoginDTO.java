package com.realm.sumit.dtos;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by sumit on 26/07/17.
 */

public class RMLoginDTO implements Serializable {

    private String username;
    private String password;

    @SerializedName("grant_type")
    private String grantType;
    private String scope;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}
