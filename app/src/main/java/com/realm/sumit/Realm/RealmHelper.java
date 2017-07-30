package com.realm.sumit.Realm;


import com.realm.sumit.dtos.RMUserLesson;
import com.realm.sumit.dtos.RMUserResponse;
import com.realm.sumit.dtos.RmUserProfileResponse;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmList;

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


	public void saveUserToRealm(RMUserResponse userResponse){

        Realm realmInstance = Realm.getDefaultInstance();
        realmInstance.beginTransaction();
        UserRMObject user = realmInstance.createObject(UserRMObject.class); // Create a new object

        user.setEmail(userResponse.getUser().getEmail());
        user.setCreatedAt(userResponse.getUser().getCreatedAt());
        user.setId(userResponse.getUser().getId());
        user.setMobile(userResponse.getUser().getMobile());
        user.setUsername(userResponse.getUser().getUsername());
        user.setUpdatedAt(userResponse.getUser().getUpdatedAt());
        user.setRoleId(userResponse.getUser().getRoleIds().get(0));
        user.setCompanyId(userResponse.getUser().getCompanyIds().get(0));

        realmInstance.copyToRealm(user);
        realmInstance.commitTransaction();
    }

    public void saveUserProfileToRealm(RmUserProfileResponse userProfileResponse){

        Realm realmInstance = Realm.getDefaultInstance();
        realmInstance.beginTransaction();
        UserProfileRMObject userProfile = realmInstance.createObject(UserProfileRMObject.class);

        userProfile.setMobile(userProfileResponse.getUserProfile().getUserDocument().getMobile());
        userProfile.setId(userProfileResponse.getUserProfile().getId());
        userProfile.setEmail(userProfileResponse.getUserProfile().getUserDocument().getEmail());
        userProfile.setDocumentId(userProfileResponse.getUserProfile().getUserDocument().getId());
        userProfile.setName(userProfileResponse.getUserProfile().getUserDocument().getUserName());
        userProfile.setUserName(userProfileResponse.getUserProfile().getUserDocument().getUserName());

        ArrayList<RMUserLesson> userLessons = new ArrayList<>();
        RealmList<UserLessonRMObject> userLessonsRMObjects = new RealmList<>();
        userLessons = userProfileResponse.getUserProfile().getUserLessons();
        for(RMUserLesson userLesson: userLessons){
            UserLessonRMObject userLessonRMObject = realmInstance.createObject(UserLessonRMObject.class);;

            userLessonRMObject.setLessonId(userLesson.getLessonsId());
            userLessonRMObject.setLessonTitle(userLesson.getLesson().getTitle());
            userLessonRMObject.setStatus(userLesson.getStatus());
            userLessonsRMObjects.add(userLessonRMObject);
        }
        userProfile.setUserLessons(userLessonsRMObjects);

        realmInstance.copyToRealm(userProfile);
        realmInstance.commitTransaction();
    }
}
