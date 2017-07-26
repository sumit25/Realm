package com.realm.sumit.dtos;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by sumit on 26/07/17.
 */

public class RMUserDTO implements Serializable {


    @SerializedName("_id")
    private String id;

    @SerializedName("company_ids")
    private ArrayList<String> companyIds;

    @SerializedName("created_at")
    private String createdAt;

    private String email;

    @SerializedName("is_suspended")
    private boolean isSuspended;

    private long mobile;
    private String name;

    @SerializedName("role_ids")
    private ArrayList<String> roleIds;

    @SerializedName("updated_at")
    private String updatedAt;

    private String username;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<String> getCompanyIds() {
        return companyIds;
    }

    public void setCompanyIds(ArrayList<String> companyIds) {
        this.companyIds = companyIds;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isSuspended() {
        return isSuspended;
    }

    public void setSuspended(boolean suspended) {
        isSuspended = suspended;
    }

    public long getMobile() {
        return mobile;
    }

    public void setMobile(long mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(ArrayList<String> roleIds) {
        this.roleIds = roleIds;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
