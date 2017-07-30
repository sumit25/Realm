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
import com.realm.sumit.dtos.UserProfileRMObject;
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

        //getCurrentUser();
    }


}
