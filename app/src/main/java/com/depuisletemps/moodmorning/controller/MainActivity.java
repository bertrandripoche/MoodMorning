package com.depuisletemps.moodmorning.controller;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.depuisletemps.moodmorning.R;
import com.depuisletemps.moodmorning.model.Mood;
import com.depuisletemps.moodmorning.model.MoodStore;
import com.depuisletemps.moodmorning.utils.TimeUtils;
import com.jakewharton.threetenabp.AndroidThreeTen;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView mImageViewMood;
    private ImageButton mCommentBtn;
    private ImageButton mHistoryBtn;

    private MoodStore todayInfo;

    private GestureDetectorCompat mDetector;

    private MoodDao mMoodDao = new MoodDao();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AndroidThreeTen.init(this);

        mImageViewMood = (ImageView) findViewById(R.id.activity_main_mood);
        mCommentBtn = (ImageButton) findViewById(R.id.activity_main_comment_btn);
        mHistoryBtn = (ImageButton) findViewById(R.id.activity_main_history_btn);

        mCommentBtn.setOnClickListener(this);
        mHistoryBtn.setOnClickListener(this);

        mDetector = new GestureDetectorCompat(this, new MyGestureListener());

        // As soon as someone opens the app, we record the date, comment, mood (or we get the existing ones)
        todayInfo = mMoodDao.getTodaysMood(this);
        if (todayInfo == null) {
            todayInfo = new MoodStore(TimeUtils.getDate(), Mood.HAPPY, null);
            String toBeStored = TimeUtils.getDate() + "_" + todayInfo.getMood() + "_" ;
            mMoodDao.storePreferences(this, TimeUtils.getDate(), toBeStored);
        }

        updateView(todayInfo.getMood());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        this.mDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    public void onClick(View v) {
        // If we click the Comment button
        if (v.equals(mCommentBtn)) {
            LayoutInflater factory = LayoutInflater.from(this);
            final View commentBoxView = factory.inflate(R.layout.activity_main_my_comment_box, null);
            final EditText myComment = (EditText) commentBoxView.findViewById(R.id.activity_main_my_comment_input);

            // If a comment has been entered today, we display it, if not, we display the hint
            if (todayInfo.getComment() != null && !todayInfo.getComment().equals("")) {
                myComment.setHint(todayInfo.getComment());
            } 

            // We open the Dialog box to enter the comment
            AlertDialog.Builder commentBox = new AlertDialog.Builder(MainActivity.this);
            commentBox.setView(commentBoxView);
            commentBox.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                // If "Add" is touched and comment not empty, we set the new record with date, mood, comment
                public void onClick(DialogInterface dialog, int which) {
                    String comment = myComment.getText().toString();
                    // If a comment has been specified, we update the stored mood
                    if (!comment.equals("")) {
                        String toBeStored = TimeUtils.getDate() + "_" + todayInfo.getMood() + "_" + comment;
                        mMoodDao.storePreferences(MainActivity.this, TimeUtils.getDate(), toBeStored);
                        todayInfo = mMoodDao.getTodaysMood(MainActivity.this);
                    }
                }
            });
            commentBox.setNegativeButton("Cancel", null);
            commentBox.show();
        // If we click the History button
        } else if (v.equals(mHistoryBtn)) {
            // We start the History activity
            Intent historyActivityIntent = new Intent(MainActivity.this, HistoryActivity.class);
            startActivity(historyActivityIntent);
        }
    }

    public void updateView(Mood mood) {
        mImageViewMood.setBackgroundColor(Color.parseColor(mood.getColor()));
        int resID = getResources().getIdentifier(mood.getFileName(), "drawable", getPackageName());
        mImageViewMood.setImageResource(resID);
    }

    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2,
                               float velocityX, float velocityY) {

            // Swipe down
            if (event1.getY() < event2.getY()) todayInfo.setMood(Mood.changeMood("down", todayInfo.getMood()));
            // Swipe up
            else todayInfo.setMood(Mood.changeMood("up", todayInfo.getMood()));

            mImageViewMood.setBackgroundColor(Color.parseColor(todayInfo.getMood().getColor()));
            int resID = getResources().getIdentifier(todayInfo.getMood().getFileName(), "drawable", getPackageName());
            mImageViewMood.setImageResource(resID);

            String currentComment = todayInfo.getComment();
            String toBeStored = TimeUtils.getDate() + "_" + todayInfo.getMood() + "_" + currentComment;
            mMoodDao.storePreferences(MainActivity.this, TimeUtils.getDate(), toBeStored);
            todayInfo = mMoodDao.getTodaysMood(MainActivity.this);

            return true;
        }
    }
}
