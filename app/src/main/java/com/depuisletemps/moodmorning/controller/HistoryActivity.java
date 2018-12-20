package com.depuisletemps.moodmorning.controller;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.depuisletemps.moodmorning.R;
import com.depuisletemps.moodmorning.model.Mood;
import com.depuisletemps.moodmorning.model.MoodStore;
import com.depuisletemps.moodmorning.utils.TimeUtils;
import com.jakewharton.threetenabp.AndroidThreeTen;


public class HistoryActivity extends AppCompatActivity {
    private MoodDao mMoodDao  = new MoodDao();

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

    private MoodStore mMood1;
    private MoodStore mMood2;
    private MoodStore mMood3;
    private MoodStore mMood4;
    private MoodStore mMood5;
    private MoodStore mMood6;
    private MoodStore mMood7;

    String[] last7Days = TimeUtils.getLast7Days();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        AndroidThreeTen.init(this);

        mLine1= (LinearLayout) findViewById(R.id.activity_history_line_1);
        mLine2= (LinearLayout) findViewById(R.id.activity_history_line_2);
        mLine3= (LinearLayout) findViewById(R.id.activity_history_line_3);
        mLine4= (LinearLayout) findViewById(R.id.activity_history_line_4);
        mLine5= (LinearLayout) findViewById(R.id.activity_history_line_5);
        mLine6= (LinearLayout) findViewById(R.id.activity_history_line_6);
        mLine7= (LinearLayout) findViewById(R.id.activity_history_line_7);

        mCommentLine1= (ImageButton) findViewById(R.id.activity_history__comment_1);
        mCommentLine2= (ImageButton) findViewById(R.id.activity_history__comment_2);
        mCommentLine3= (ImageButton) findViewById(R.id.activity_history__comment_3);
        mCommentLine4= (ImageButton) findViewById(R.id.activity_history__comment_4);
        mCommentLine5= (ImageButton) findViewById(R.id.activity_history__comment_5);
        mCommentLine6= (ImageButton) findViewById(R.id.activity_history__comment_6);
        mCommentLine7= (ImageButton) findViewById(R.id.activity_history__comment_7);

        LinearLayout[] tabLine = {mLine1, mLine2, mLine3, mLine4, mLine5, mLine6, mLine7};
        ImageView[] tabComment = {mCommentLine1, mCommentLine2, mCommentLine3, mCommentLine4, mCommentLine5, mCommentLine6, mCommentLine7};

        for (int i = 0; i < last7Days.length; i++) {

            if (mMoodDao.getPreferences(this, last7Days[i]) != "%") {
                final MoodStore dayInfo = mMoodDao.getMoodStoreFromRecord(mMoodDao.getPreferences(this, last7Days[i]));

                tabLine[i].setBackgroundColor(Color.parseColor(dayInfo.getMood().getColor()));
                LinearLayout.LayoutParams lay = (LinearLayout.LayoutParams) tabLine[i].getLayoutParams();
                lay.weight = dayInfo.getMood().getHistoryWidth();
                if (!dayInfo.getComment().equals("")) {
                    tabComment[i].setVisibility(View.VISIBLE);
                    tabComment[i].setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            LayoutInflater inflater = getLayoutInflater();
                            View layout = inflater.inflate(R.layout.activity_history_my_comment_toast,
                                    (ViewGroup) findViewById(R.id.activity_history_comment_box_toast));

                            TextView text = (TextView) layout.findViewById(R.id.activity_history_comment_text_toast);
                            text.setText(dayInfo.getComment());

                            Toast toast = new Toast(getApplicationContext());
                            toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 10);
                            toast.setDuration(Toast.LENGTH_LONG);
                            toast.setView(layout);
                            toast.show();
                        }
                    });
                }
            }
        }
    }

}
