package com.depuisletemps.moodmorning.controller;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.depuisletemps.moodmorning.R;
import com.depuisletemps.moodmorning.model.Mood;
import com.depuisletemps.moodmorning.model.MoodStore;
import com.depuisletemps.moodmorning.utils.AboutTime;
import com.jakewharton.threetenabp.AndroidThreeTen;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView mMood;
    private ImageButton mCommentBtn;
    private ImageButton mHistoryBtn;

    /*private SharedPreferences mPreferences;*/

    private Mood mood;
    private String today;

    private MoodStore todayInfo;

    private GestureDetectorCompat mDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AndroidThreeTen.init(this);

        mMood = (ImageView) findViewById(R.id.activity_main_mood);
        mCommentBtn = (ImageButton) findViewById(R.id.activity_main_comment_btn);
        mHistoryBtn = (ImageButton) findViewById(R.id.activity_main_history_btn);
        /*mPreferences = getPreferences(MODE_PRIVATE);*/

        mCommentBtn.setOnClickListener(this);
        mHistoryBtn.setOnClickListener(this);

        mDetector = new GestureDetectorCompat(this, new MyGestureListener());

        mood = Mood.HAPPY;
        today = AboutTime.todayStr;

        // As soon as someone open the app, we record the date, comment, mood
        todayInfo = MoodStore.checkMoodForToday(this);

        /*String test = mPreferences.getString("2018-12-13", "truc");
        Toast.makeText(this,test,Toast.LENGTH_LONG).show();*/

    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        this.mDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    public void onClick(View v) {
        if (v.equals(mCommentBtn)) {
            // If we click the Comment button

            LayoutInflater factory = LayoutInflater.from(this);
            final View commentBoxView = factory.inflate(R.layout.activity_main_my_comment_box, null);
            final EditText myComment = (EditText) commentBoxView.findViewById(R.id.activity_main_my_comment_input);

            // If a comment has been entered today, we display it, if not, we display the hint
            if (!todayInfo.getComment().equals("%")) {
                myComment.setHint(todayInfo.getComment());
            } 

            // We open the Dialog box to enter the comment
            AlertDialog.Builder commentBox = new AlertDialog.Builder(MainActivity.this);
            commentBox.setView(commentBoxView);
            commentBox.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                // If "Add" is touched and comment not empty, we set the new record with date, mood, comment
                public void onClick(DialogInterface dialog, int which) {
                    String comment = myComment.getText().toString();
                    if (!comment.equals("")) {
                        String toBeStored = today + "_" + mood + "_" + comment;
                        /*mPreferences.edit().putString(today, toBeStored).apply();*/
                        MoodStore.storePreferences(MainActivity.this, today, toBeStored);
                        todayInfo = MoodStore.checkMoodForToday(MainActivity.this);
                    }
                }
            });
            commentBox.setNegativeButton("Cancel", null);
            commentBox.show();

        } else if (v.equals(mHistoryBtn)) {
            // If we click the History button
            Intent historyActivityIntent = new Intent(MainActivity.this, HistoryActivity.class);
            startActivity(historyActivityIntent);
        }
    }

    /*private MoodStore checkMoodForToday() {
        if (mPreferences.getString(today, null) != null ) {
            todayInfo = new MoodStore(mPreferences.getString(today, null));
        } else {
            String reg = today + "_" + mood.name() + "_%";
            todayInfo = new MoodStore(reg);
        }
        return todayInfo;
    }*/

    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2,
                               float velocityX, float velocityY) {

            // Swipe down
            if (event1.getY() < event2.getY()) mood = Mood.changeMood("down", mood);
            // Swipe up
            else mood = Mood.changeMood("up", mood);

            mMood.setBackgroundColor(Color.parseColor(mood.getColor()));
            int resID = getResources().getIdentifier(mood.getFileName(), "drawable", getPackageName());
            mMood.setImageResource(resID);

            return true;
        }
    }
}
