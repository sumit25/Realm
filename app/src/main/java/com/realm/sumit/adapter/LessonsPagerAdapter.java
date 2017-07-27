package com.realm.sumit.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.realm.sumit.CompletedLessonsFragment;
import com.realm.sumit.LessonsFragment;
import com.realm.sumit.PendingLessonsFragment;

/**
 * Created by sumit on 27/07/17.
 */

public class LessonsPagerAdapter extends FragmentPagerAdapter {

    private Context mContext;
    private String[] mTitles = {"Completed", "Pending"};

//    public CompletedLessonsFragment mCompletedLessonsFragment;
    //public PendingLessonsFragment mPendingLessonsFragment;

    public LessonsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.mContext = context;
      //  mCompletedLessonsFragment = new CompletedLessonsFragment();
      //  mPendingLessonsFragment = new PendingLessonsFragment();
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position < mTitles.length) {
            return mTitles[position];
        }
        return super.getPageTitle(position);
    }


    /*@Override
    public Fragment getItem(int position) {
        if (1 == position) {
            return mPendingLessonsFragment;
        }
        return mCompletedLessonsFragment;
    }*/

    @Override
    public Fragment getItem(int position) {
        LessonsFragment lessonsFragment = new LessonsFragment();
        lessonsFragment.setType(mTitles[position]);
        return lessonsFragment;
    }
}
