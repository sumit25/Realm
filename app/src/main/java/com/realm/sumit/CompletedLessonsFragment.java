package com.realm.sumit;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by sumit on 27/07/17.
 */

public class CompletedLessonsFragment extends Fragment {

    private String mType;

    private TextView tv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.completed_lessons_fragment, container, false);
        tv = (TextView) view.findViewById(R.id.tv);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tv.setText(mType);
    }

    public void setType(String type) {
        mType = type;
        if (null != tv) {
            tv.setText(mType);
        }
    }
}
