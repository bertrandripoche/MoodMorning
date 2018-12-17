package com.depuisletemps.moodmorning.controller;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.depuisletemps.moodmorning.R;
import com.depuisletemps.moodmorning.model.Mood;
import com.depuisletemps.moodmorning.model.MoodStore;
import com.depuisletemps.moodmorning.utils.AboutTime;
import com.jakewharton.threetenabp.AndroidThreeTen;

import java.util.Arrays;


public class HistoryActivity extends AppCompatActivity implements View.OnClickListener {
    private SharedPreferences mPreferences;

    private LinearLayout mLine1;
    private LinearLayout mLine2;
    private LinearLayout mLine3;
    private LinearLayout mLine4;
    private LinearLayout mLine5;
    private LinearLayout mLine6;
    private LinearLayout mLine7;

    private ImageButton mCommentLine1;
    private ImageButton mCommentLine2;
    private ImageButton mCommentLine3;
    private ImageButton mCommentLine4;
    private ImageButton mCommentLine5;
    private ImageButton mCommentLine6;
    private ImageButton mCommentLine7;

    String[] last7Days = AboutTime.getLast7Days();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        AndroidThreeTen.init(this);

        mLine1= (LinearLayout) findViewById(R.id.previous_line_1);
        mLine2= (LinearLayout) findViewById(R.id.previous_line_2);
        mLine3= (LinearLayout) findViewById(R.id.previous_line_3);
        mLine4= (LinearLayout) findViewById(R.id.previous_line_4);
        mLine5= (LinearLayout) findViewById(R.id.previous_line_5);
        mLine6= (LinearLayout) findViewById(R.id.previous_line_6);
        mLine7= (LinearLayout) findViewById(R.id.previous_line_7);

        mCommentLine1= (ImageButton) findViewById(R.id.previous_comment_1);
        mCommentLine2= (ImageButton) findViewById(R.id.previous_comment_2);
        mCommentLine3= (ImageButton) findViewById(R.id.previous_comment_3);
        mCommentLine4= (ImageButton) findViewById(R.id.previous_comment_4);
        mCommentLine5= (ImageButton) findViewById(R.id.previous_comment_5);
        mCommentLine6= (ImageButton) findViewById(R.id.previous_comment_6);
        mCommentLine7= (ImageButton) findViewById(R.id.previous_comment_7);

        mCommentLine1.setTag(1);
        mCommentLine2.setTag(2);
        mCommentLine3.setTag(3);
        mCommentLine4.setTag(4);
        mCommentLine5.setTag(5);
        mCommentLine6.setTag(6);
        mCommentLine7.setTag(7);

        mCommentLine1.setOnClickListener(this);
        mCommentLine2.setOnClickListener(this);
        mCommentLine3.setOnClickListener(this);
        mCommentLine4.setOnClickListener(this);
        mCommentLine5.setOnClickListener(this);
        mCommentLine6.setOnClickListener(this);
        mCommentLine7.setOnClickListener(this);

        LinearLayout[] tabLine = {mLine1, mLine2, mLine3, mLine4, mLine5, mLine6, mLine7};
        ImageView[] tabComment = {mCommentLine1, mCommentLine2, mCommentLine3, mCommentLine4, mCommentLine5, mCommentLine6, mCommentLine7};

        for (int i = 0; i < last7Days.length; i++) {

            if (MoodStore.getPreferences(this, last7Days[i]) != "%") {
                MoodStore dayInfo = new MoodStore(MoodStore.getPreferences(this, last7Days[i]));
                Mood dayMood = Mood.valueOf(dayInfo.getMood());

                tabLine[i].setBackgroundColor(Color.parseColor(dayMood.getColor()));
                LinearLayout.LayoutParams lay = (LinearLayout.LayoutParams) tabLine[i].getLayoutParams();
                lay.weight = dayMood.getHistoryWidth();
                if (dayInfo.getComment() != "%") {
                    tabComment[i].setVisibility(View.VISIBLE);
                }
            }
        }
    }

    public void onClick(View v) {
        int nbComment = (int) v.getTag();

        MoodStore dayInfo = new MoodStore(MoodStore.getPreferences(this, last7Days[nbComment - 1]));
        String comment = dayInfo.getComment();
        Toast.makeText(this,comment,Toast.LENGTH_LONG).show();
    }
}
