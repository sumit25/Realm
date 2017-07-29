package com.realm.sumit;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.realm.sumit.adapter.LessonsAdapter;
import com.realm.sumit.dtos.UserLessonRMObject;
import com.realm.sumit.dtos.UserProfileRMObject;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by sumit on 27/07/17.
 */

public class LessonsFragment extends Fragment implements  LessonsAdapter.Listener{

    private String mType;

    private TextView tv;
    private LessonsAdapter mLessonsAdapter;
    private RealmList<UserLessonRMObject> mUserLessons;
    private RecyclerView mRvLessonsView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.completed_lessons_fragment, container, false);
        tv = (TextView) view.findViewById(R.id.tv);
        mRvLessonsView = (RecyclerView) view.findViewById(R.id.rv_lessons);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tv.setText(mType);

        if(mType.equalsIgnoreCase("Completed")){
            RealmQuery<UserProfileRMObject> query1 = Realm.getDefaultInstance().where(UserProfileRMObject.class);
            RealmResults<UserProfileRMObject> result2 = query1.findAll();

            mUserLessons = new RealmList<>();
            for(int i=0 ; i < result2.get(0).getUserLessons().size(); i ++){
                UserLessonRMObject userLessonRMObject = new UserLessonRMObject();
                userLessonRMObject.setLessonTitle(result2.get(0).getUserLessons().get(i).getLessonTitle());
                userLessonRMObject.setStatus(result2.get(0).getUserLessons().get(i).getStatus());
                userLessonRMObject.setLessonId(result2.get(0).getUserLessons().get(i).getLessonId());
                mUserLessons.add(userLessonRMObject);
            }
            //http://gradlewhy.ghost.io/realm-results-with-recyclerview/
            mLessonsAdapter = new LessonsAdapter(mUserLessons, LessonsFragment.this, this.getActivity());
            mRvLessonsView.setAdapter(mLessonsAdapter);
            mLessonsAdapter.notifyDataSetChanged();


            //mLessonsAdapter.addLessons(mUserLessons);
           // mLessonsAdapter.notifyDataSetChanged();
        }
    }

    public void setType(String type) {
        mType = type;
        if (null != tv) {
            tv.setText(mType);
        }
    }

    @Override
    public void onGetNextPage(int position) {

    }

    @Override
    public void onItemClicked(UserLessonRMObject article) {

    }
}
