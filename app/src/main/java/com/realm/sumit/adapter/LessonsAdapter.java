package com.realm.sumit.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.realm.sumit.R;
import com.realm.sumit.dtos.UserLessonRMObject;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;

/**
 * Created by sumit on 30/07/17.
 */

public class LessonsAdapter extends RecyclerView.Adapter<LessonsAdapter.LessonsViewHolder> {

    private Listener mListener;
    private RealmList<UserLessonRMObject> mUserLessons;
    private Context mContext;

    public LessonsAdapter(RealmList<UserLessonRMObject> userLessons, Listener listener, Context context) {

        if (null == userLessons) {
            mUserLessons = new RealmList<>();
        }
        mUserLessons = userLessons;
        mContext = context;
        mListener = listener;

    }

    public void addLessons(RealmList<UserLessonRMObject> lessonsList) {
        if (null != lessonsList && lessonsList.size() > 0) {
            int start = mUserLessons.size();
            mUserLessons.addAll(lessonsList);
            notifyItemRangeInserted(start, lessonsList.size());
        }
    }

    @Override
    public LessonsAdapter.LessonsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_lesson_row, parent, false);
        return new LessonsViewHolder(v);
    }

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
            if (null != mListener) {
                mListener.onItemClicked(mUserLessons.get(position));
            }
        }
    }

    public interface Listener {
        void onGetNextPage(int position);

        void onItemClicked(UserLessonRMObject article);
    }
}
