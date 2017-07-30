package com.realm.sumit;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.realm.sumit.adapter.LessonsAdapter;
import com.realm.sumit.Realm.UserLessonRMObject;
import com.realm.sumit.Realm.UserProfileRMObject;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by sumit on 27/07/17.
 */

public class LessonsFragment extends Fragment {

    private String mType;

    private LessonsAdapter mLessonsAdapter;
    private RealmList<UserLessonRMObject> mUserLessons;
    private RecyclerView mRvLessonsView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.completed_lessons_fragment, container, false);
        mRvLessonsView = (RecyclerView) view.findViewById(R.id.rv_lessons);
        mRvLessonsView.setHasFixedSize(true);
        mRvLessonsView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        RealmQuery<UserProfileRMObject> query1 = Realm.getDefaultInstance().where(UserProfileRMObject.class);
        RealmResults<UserProfileRMObject> result2 = query1.findAll();
        if (mType.equalsIgnoreCase("Completed")) {
            mUserLessons = new RealmList<>();
            for (int i = 0; i < result2.get(0).getUserLessons().size(); i++) {
                if (result2.get(0).getUserLessons().get(i).getStatus().equalsIgnoreCase("Completed")) {
                    UserLessonRMObject userLessonRMObject = new UserLessonRMObject();
                    userLessonRMObject.setLessonTitle(result2.get(0).getUserLessons().get(i).getLessonTitle());
                    userLessonRMObject.setStatus(result2.get(0).getUserLessons().get(i).getStatus());
                    userLessonRMObject.setLessonId(result2.get(0).getUserLessons().get(i).getLessonId());
                    mUserLessons.add(userLessonRMObject);
                }
            }

        } else {
            mUserLessons = new RealmList<>();
            for (int i = 0; i < result2.get(0).getUserLessons().size(); i++) {
                if (result2.get(0).getUserLessons().get(i).getStatus().equalsIgnoreCase("started")) {
                    UserLessonRMObject userLessonRMObject = new UserLessonRMObject();
                    userLessonRMObject.setLessonTitle(result2.get(0).getUserLessons().get(i).getLessonTitle());
                    userLessonRMObject.setStatus(result2.get(0).getUserLessons().get(i).getStatus());
                    userLessonRMObject.setLessonId(result2.get(0).getUserLessons().get(i).getLessonId());
                    mUserLessons.add(userLessonRMObject);
                }
            }
        }

        mLessonsAdapter = new LessonsAdapter(mUserLessons, this.getActivity());
        mRvLessonsView.setAdapter(mLessonsAdapter);
    }
    
    public void setType(String type) {
        mType = type;
    }

}
