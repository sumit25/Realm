package com.realm.sumit;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;

import com.realm.sumit.adapter.LessonsPagerAdapter;
import com.realm.sumit.api.APICallback;
import com.realm.sumit.config.RealmApp;
import com.realm.sumit.dtos.RMUserResponse;
import com.realm.sumit.dtos.RealmString;
import com.realm.sumit.dtos.RmUserProfileResponse;
import com.realm.sumit.dtos.UserRMObject;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class LessonsActivity extends AppCompatActivity {

    private TabLayout mTabLayout;
    private ViewPager vpLessonsFragments;
    private LessonsPagerAdapter mLessonsPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lessons);

        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        vpLessonsFragments = (ViewPager) findViewById(R.id.vp_lessons_fragments);

        mLessonsPagerAdapter = new LessonsPagerAdapter(this, getSupportFragmentManager());
        vpLessonsFragments.setAdapter(mLessonsPagerAdapter);
        mTabLayout.setupWithViewPager(vpLessonsFragments);

        getCurrentUser();
    }

    private void getCurrentUser() {

        RealmApp.getAPIClient().getCurrentUser(new APICallback<RMUserResponse>() {
            @Override
            public void onResponse(RMUserResponse body) {
                Log.d("username", body.getUser().getName());
                Log.d("company id", body.getUser().getCompanyIds().get(0).toString());


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

                getUserProfile(body);


            }
        });
    }

    private void getUserProfile(RMUserResponse userResponse) {

        RealmQuery<UserRMObject> query = Realm.getDefaultInstance().where(UserRMObject.class);
        RealmResults<UserRMObject> result1 = query.findAll();

        Log.d("result from realm", result1.get(0).getEmail());
        Log.d("realm role id", result1.get(0).getRoleId());
        Log.d("realm company id", result1.get(0).getCompanyId());

        RealmApp.getAPIClient().getUserProfile(userResponse.getUser().getCompanyIds().get(0).toString(), userResponse.getUser().getId(), new APICallback<RmUserProfileResponse>() {
            @Override
            public void onResponse(RmUserProfileResponse body) {
                Log.d("user name in profile", body.getUserProfile().getUserDocument().getName());
            }
        });
    }
}
