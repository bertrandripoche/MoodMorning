package com.depuisletemps.moodmorning.controller;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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


public class HistoryActivity extends AppCompatActivity {
    private SharedPreferences mPreferences;

    private LinearLayout mLine1;
    private LinearLayout mLine2;
    private LinearLayout mLine3;
    private LinearLayout mLine4;
    private LinearLayout mLine5;
    private LinearLayout mLine6;
    private LinearLayout mLine7;

    private ImageView mCommentLine1;
    private ImageView mCommentLine2;
    private ImageView mCommentLine3;
    private ImageView mCommentLine4;
    private ImageView mCommentLine5;
    private ImageView mCommentLine6;
    private ImageView mCommentLine7;

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

        mCommentLine1= (ImageView) findViewById(R.id.previous_comment_1);;
        mCommentLine2= (ImageView) findViewById(R.id.previous_comment_2);;
        mCommentLine3= (ImageView) findViewById(R.id.previous_comment_3);;
        mCommentLine4= (ImageView) findViewById(R.id.previous_comment_4);;
        mCommentLine5= (ImageView) findViewById(R.id.previous_comment_5);;
        mCommentLine6= (ImageView) findViewById(R.id.previous_comment_6);;
        mCommentLine7= (ImageView) findViewById(R.id.previous_comment_7);;

        LinearLayout[] tabLine = {mLine1, mLine2, mLine3, mLine4, mLine5, mLine6, mLine7};
        ImageView[] tabComment = {mCommentLine1, mCommentLine2, mCommentLine3, mCommentLine4, mCommentLine5, mCommentLine6, mCommentLine7};

        String[] last7Days = AboutTime.getLast7Days();

        for (int i = 6; i > -1; i--) {
            /*Toast.makeText(this,last7Days[i],Toast.LENGTH_SHORT).show();*/
            Log.d("3", last7Days[i]);
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
}
