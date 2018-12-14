package com.depuisletemps.moodmorning.controller;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.depuisletemps.moodmorning.R;
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

    private TextView mTextLine1;
    private TextView mTextLine2;
    private TextView mTextLine3;
    private TextView mTextLine4;
    private TextView mTextLine5;
    private TextView mTextLine6;
    private TextView mTextLine7;

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

        mPreferences = getPreferences(MODE_PRIVATE);

        String[] last7Days = AboutTime.getLast7Days();

        /*for (int i = 0; i < last7Days.length; i++) {
            if (mPreferences.getString(last7Days[i], null) != null) {
                MoodStore dayInfo = new MoodStore(mPreferences.getString(last7Days[i], null));
                Toast.makeText(this,mPreferences.getString(last7Days[i], null),Toast.LENGTH_SHORT).show();
            }
        }*/

        String test = mPreferences.getString("2018-12-13", "truc");
        Toast.makeText(this,test,Toast.LENGTH_LONG).show();

    }
}
