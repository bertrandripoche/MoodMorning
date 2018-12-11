package com.depuisletemps.moodmorning.controller;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.depuisletemps.moodmorning.R;
import com.depuisletemps.moodmorning.model.Mood;
import com.depuisletemps.moodmorning.model.MoodStore;
import com.jakewharton.threetenabp.AndroidThreeTen;

import org.threeten.bp.format.DateTimeFormatter;
import org.threeten.bp.ZonedDateTime;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView mMood;
    private ImageButton mCommentBtn;
    private ImageButton mHistoryBtn;

    private SharedPreferences mPreferences;
    public static final String PREF_KEY_COMMENT = "PREF_KEY_COMMENT";

    private String mood;
    private String today;

    private MoodStore todayInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AndroidThreeTen.init(this);

        mMood = (ImageView) findViewById(R.id.activity_main_mood);
        mCommentBtn = (ImageButton) findViewById(R.id.activity_main_comment_btn);
        mHistoryBtn = (ImageButton) findViewById(R.id.activity_main_history_btn);
        mPreferences = getPreferences(MODE_PRIVATE);

        mCommentBtn.setOnClickListener(this);
        mHistoryBtn.setOnClickListener(this);

        mood = getMood();
        today = getDate();

        todayInfo = checkMoodForToday();

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

            AlertDialog.Builder commentBox = new AlertDialog.Builder(MainActivity.this);
            commentBox.setView(commentBoxView);
            commentBox.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    String comment = myComment.getText().toString();
                    String toBeStored = today + "_" + mood + "_" + comment;
                    mPreferences.edit().putString(today, toBeStored).apply();
                    todayInfo = checkMoodForToday();
                }
            });
            commentBox.setNegativeButton("Cancel", null);
            commentBox.show();

        } else if (v.equals(mHistoryBtn)) {
            // If we click the History button
            Toast.makeText(this, "History !", Toast.LENGTH_SHORT).show();
            Intent historyActivityIntent = new Intent(MainActivity.this, HistoryActivity.class);
            startActivity(historyActivityIntent);
        }
    }

    private String getDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
        String formattedZonedDate = formatter.format(ZonedDateTime.now());
        /*formattedZonedDate = formattedZonedDate.replace("-", "_");*/
        return formattedZonedDate;
    }

    private String getMood() {
        return (String) mMood.getTag();
    }

    private MoodStore checkMoodForToday() {
        if (mPreferences.getString(today, null) != null ) {
            todayInfo = new MoodStore(mPreferences.getString(today, null));
        } else {
            String reg = today + "_" + mood + "_%";
            todayInfo = new MoodStore(reg);
        }
        return todayInfo;
    }
}