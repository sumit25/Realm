package com.realm.sumit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.realm.sumit.api.APICallback;
import com.realm.sumit.config.RealmApp;
import com.realm.sumit.dtos.RMUserResponse;
import com.realm.sumit.dtos.RmUserProfileResponse;

import io.realm.Realm;
import io.realm.RealmResults;

public class LessonsActivity extends AppCompatActivity {

    // Obtain a Realm instance
    Realm realm = Realm.getDefaultInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lessons);

        RealmApp.getAPIClient().getCurrentUser(new APICallback<RMUserResponse>() {
            @Override
            public void onResponse(final RMUserResponse body) {
                Log.d("username", body.getUser().getName());
               // Log.d("company id", body.getUser().getCompanyIds().get(0));

                realm.beginTransaction();

                realm.commitTransaction();

                realm.executeTransaction(new Realm.Transaction(){
                    @Override
                    public void execute(Realm realm) {
                        RMUserResponse user = realm.createObject(RMUserResponse.class); // Create a new object
                        user.setUser(body.getUser());
                    }
                });

                getUserProfile(body);

            }
        });
    }

    private void getUserProfile(RMUserResponse userResponse){
        RMUserResponse rmUserResponse = realm.where(RMUserResponse.class).findFirst();

        Log.d("from relm::", rmUserResponse.getUser().getEmail());

//        RealmApp.getAPIClient().getUserProfile(userResponse.getUser().getCompanyIds().get(0), userResponse.getUser().getId(), new APICallback<RmUserProfileResponse>() {
//            @Override
//            public void onResponse(RmUserProfileResponse body) {
//                Log.d("user name in profile", body.getUserProfile().getUserDocument().getName());
//
//
//
//            }
//        });
    }
}
