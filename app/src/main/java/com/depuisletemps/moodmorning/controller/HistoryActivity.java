package com.depuisletemps.moodmorning.controller;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
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
import com.depuisletemps.moodmorning.model.MoodStore;
import com.depuisletemps.moodmorning.utils.TimeUtils;
import com.jakewharton.threetenabp.AndroidThreeTen;


public class HistoryActivity extends AppCompatActivity {
    private final MoodDao mMoodDao  = new MoodDao();

    private final String[] last7Days = TimeUtils.getLast7Days();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        AndroidThreeTen.init(this);

        LinearLayout line1 = findViewById(R.id.activity_history_line_1);
        LinearLayout line2 = findViewById(R.id.activity_history_line_2);
        LinearLayout line3 = findViewById(R.id.activity_history_line_3);
        LinearLayout line4 = findViewById(R.id.activity_history_line_4);
        LinearLayout line5 = findViewById(R.id.activity_history_line_5);
        LinearLayout line6 = findViewById(R.id.activity_history_line_6);
        LinearLayout line7 = findViewById(R.id.activity_history_line_7);

        ImageButton commentLine1 = findViewById(R.id.activity_history__comment_1);
        ImageButton commentLine2 = findViewById(R.id.activity_history__comment_2);
        ImageButton commentLine3 = findViewById(R.id.activity_history__comment_3);
        ImageButton commentLine4 = findViewById(R.id.activity_history__comment_4);
        ImageButton commentLine5 = findViewById(R.id.activity_history__comment_5);
        ImageButton commentLine6 = findViewById(R.id.activity_history__comment_6);
        ImageButton commentLine7 = findViewById(R.id.activity_history__comment_7);

        LinearLayout[] tabLine = {line1, line2, line3, line4, line5, line6, line7};
        ImageView[] tabComment = {commentLine1, commentLine2, commentLine3, commentLine4, commentLine5, commentLine6, commentLine7};

        for (int i = 0; i < last7Days.length; i++) {
            // We check if we have a stored mood for the accurate day
            if (!mMoodDao.getPreferences(this, last7Days[i]).equals("%")) {
                final MoodStore dayInfo = mMoodDao.getMoodStoreFromRecord(mMoodDao.getPreferences(this, last7Days[i]));

                // We change the background according to the mood of the day
                if (dayInfo != null) {
                    tabLine[i].setBackgroundColor(Color.parseColor(dayInfo.getMood().getColor()));
                    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) tabLine[i].getLayoutParams();
                    layoutParams.weight = dayInfo.getMood().getHistoryWidth();

                    // If existing, we print the comment via a Toast
                    if (!TextUtils.isEmpty(dayInfo.getComment()) && !dayInfo.getComment().equals("null")) {
                        tabComment[i].setVisibility(View.VISIBLE);
                        tabComment[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                LayoutInflater inflater = getLayoutInflater();
                                View layout = inflater.inflate(R.layout.activity_history_my_comment_toast,
                                        (ViewGroup) findViewById(R.id.activity_history_comment_box_toast));

                                TextView text = layout.findViewById(R.id.activity_history_comment_text_toast);
                                text.setText(dayInfo.getComment());

                                Toast toast = new Toast(getApplicationContext());
                                toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 10);
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
}
