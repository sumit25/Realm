package com.realm.sumit.Realm;

import com.realm.sumit.api.APIClient;
import com.realm.sumit.dtos.RMUserResponse;
import com.realm.sumit.dtos.UserRMObject;

import io.realm.Realm;

/**
 * Created by sumit on 27/07/17.
 */

public class RealmHelper {
    private static RealmHelper mRealmHelper;


    /* Singleton Configuration */
    private RealmHelper() {
    }

    public static RealmHelper init() {
        if (null == mRealmHelper) {
            mRealmHelper = new RealmHelper();
        }
        return mRealmHelper;
    }
	/* End Singleton Configuration */


	public void saveUserToRealm(RMUserResponse body){

        Realm realmInstance = Realm.getDefaultInstance();
        realmInstance.beginTransaction();
        UserRMObject user = realmInstance.createObject(UserRMObject.class); // Create a new object

        user.setEmail(body.getUser().getEmail());
        user.setCreatedAt(body.getUser().getCreatedAt());
        user.setId(body.getUser().getId());
        user.setMobile(body.getUser().getMobile());
        user.setUsername(body.getUser().getUsername());
        user.setUpdatedAt(body.getUser().getUpdatedAt());
        user.setRoleId(body.getUser().getRoleIds().get(0));
        user.setCompanyId(body.getUser().getCompanyIds().get(0));

        realmInstance.copyToRealm(user);
        realmInstance.commitTransaction();
    }
}
