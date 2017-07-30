package com.realm.sumit;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.realm.sumit.adapter.LessonsPagerAdapter;


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

    }


}
