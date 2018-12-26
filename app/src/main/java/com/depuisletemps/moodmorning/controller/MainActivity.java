package com.depuisletemps.moodmorning.controller;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.depuisletemps.moodmorning.R;
import com.depuisletemps.moodmorning.model.Direction;
import com.depuisletemps.moodmorning.model.Mood;
import com.depuisletemps.moodmorning.model.MoodStore;
import com.depuisletemps.moodmorning.utils.TimeUtils;
import com.jakewharton.threetenabp.AndroidThreeTen;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView mImageViewMood;
    private ImageButton mCommentBtn;
    private ImageButton mHistoryBtn;
    private ImageButton mStatsBtn;

 //   private MoodStore todayInfo;

    private GestureDetectorCompat mDetector;

    private MoodDao mMoodDao = new MoodDao();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AndroidThreeTen.init(this);

        mImageViewMood = findViewById(R.id.activity_main_mood);
        mCommentBtn = findViewById(R.id.activity_main_comment_btn);
        mHistoryBtn = findViewById(R.id.activity_main_history_btn);
        mStatsBtn = findViewById(R.id.activity_main_stats_btn);

        mCommentBtn.setOnClickListener(this);
        mHistoryBtn.setOnClickListener(this);
        mStatsBtn.setOnClickListener(this);

        mDetector = new GestureDetectorCompat(this, new MyGestureListener());

        // As soon as someone opens the app, we record the date, comment, mood (or we get the existing ones), and update the scree accordingly
        processCurrentMood();
    }

    @Override
    public void onResume(){
        super.onResume();
        processCurrentMood();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        this.mDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    /**
     * This method :
     * - check the stored mood and update the screen accordingly
     * - if there is no mood stored, it stores the current one
     */
    private void processCurrentMood() {
        MoodStore todayInfo = mMoodDao.getTodaysMood(this);
        if (todayInfo == null) {
            todayInfo = getDefaultMoodStore();
            mMoodDao.insertMoodstore(this, todayInfo);
        }
        updateView(todayInfo.getMood());
    }

    @NonNull
    private MoodStore getDefaultMoodStore() {
        MoodStore todayInfo;
        todayInfo = new MoodStore(TimeUtils.getDate(), Mood.HAPPY, null);
        return todayInfo;
    }

    /*
    This method :
    - update the screen accordingly to match the required mood
    PARAM : Mood mood which is the mood that we want to have set
     */
    public void updateView(Mood mood) {
        mImageViewMood.setBackgroundColor(Color.parseColor(mood.getColor()));
        int resID = getResources().getIdentifier(mood.getFileName(), "drawable", getPackageName());
        mImageViewMood.setImageResource(resID);
    }

    public void onClick(View v) {
        // If we click the Comment button
        if (v.equals(mCommentBtn)) {
            displayCommentBox();

        // If we click the History button
        } else if (v.equals(mHistoryBtn)) {
            startHistoryActivity();

            // If we click the Stats button
        } else if (v.equals(mStatsBtn)) {
            startStatsActivity();
        }
    }

    private void startStatsActivity() {
        // We start the Stats activity
        Intent statsActivityIntent = new Intent(MainActivity.this, StatsActivity.class);
        startActivity(statsActivityIntent);
    }

    private void startHistoryActivity() {
        // We start the History activity
        Intent historyActivityIntent = new Intent(MainActivity.this, HistoryActivity.class);
        startActivity(historyActivityIntent);
    }

    private void displayCommentBox() {
        LayoutInflater factory = LayoutInflater.from(this);
        @SuppressLint("InflateParams") final View commentBoxView = factory.inflate(R.layout.activity_main_my_comment_box, null);
        final EditText myComment = commentBoxView.findViewById(R.id.activity_main_my_comment_input);

        // If a comment has been entered today, we display it, if not, we display the hint
        MoodStore todayInfo = mMoodDao.getTodaysMood(this);
        if (todayInfo != null && !TextUtils.isEmpty(todayInfo.getComment())) {
            myComment.setHint(todayInfo.getComment());
        }

        // We open the Dialog box to enter the comment
        AlertDialog.Builder commentBox = new AlertDialog.Builder(MainActivity.this);
        commentBox.setView(commentBoxView);
        commentBox.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            // If "Add" is touched and comment not empty, we set the new record with date, mood, comment
            public void onClick(DialogInterface dialog, int which) {
                String comment = myComment.getText().toString();
                // If a comment has been specified and we touch "Add", we update the stored mood
                mMoodDao.updateTodaysComment(MainActivity.this, comment);
            }
        });
        commentBox.setNegativeButton("Cancel", null);
        commentBox.show();
    }

    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2,
                               float velocityX, float velocityY) {

            MoodStore todayInfo = mMoodDao.getTodaysMood(MainActivity.this);

            if (todayInfo.getMood() != null) {
                // Swipe down
                if (event1.getY() < event2.getY()) todayInfo.setMood(Mood.changeMood(Direction.DOWN, todayInfo.getMood()));
                // Swipe up
                else todayInfo.setMood(Mood.changeMood(Direction.UP, todayInfo.getMood()));
                updateView(todayInfo.getMood());
                mMoodDao.updateTodaysMood(MainActivity.this, todayInfo.getMood());
            }

            return true;
        }
    }
}
