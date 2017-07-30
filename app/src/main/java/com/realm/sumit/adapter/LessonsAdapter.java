package com.realm.sumit.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.realm.sumit.R;
import com.realm.sumit.Realm.UserLessonRMObject;


import io.realm.RealmList;

/**
 * Created by sumit on 30/07/17.
 */

public class LessonsAdapter extends RecyclerView.Adapter<LessonsAdapter.LessonsViewHolder> {

    private RealmList<UserLessonRMObject> mUserLessons;
    private Context mContext;

    public LessonsAdapter(RealmList<UserLessonRMObject> userLessons, Context context) {
        mUserLessons = userLessons;
        mContext = context;

    }

    @Override
    public LessonsAdapter.LessonsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_lesson_row, parent, false);
        return new LessonsViewHolder(v);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(LessonsAdapter.LessonsViewHolder holder, int position) {

        holder.position = position;
        UserLessonRMObject lesson = mUserLessons.get(position);
        holder.tvTitle.setText(lesson.getLessonTitle());
    }

    @Override
    public int getItemCount() {
        int count = 0;
        if (null != mUserLessons) {
            count = mUserLessons.size();
        }
        return count;
    }

    class LessonsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        int position;
        final TextView tvTitle;


        LessonsViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.lesson_title);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

        }
    }

}
