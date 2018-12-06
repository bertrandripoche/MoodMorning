package com.depuisletemps.moodmorning;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView mMood;
    private Button mCommentBtn;
    private Button mHistoryBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMood = (ImageView) findViewById(R.id.activity_main_mood);
        mCommentBtn = (Button) findViewById(R.id.activity_main_comment);
        mHistoryBtn = (Button) findViewById(R.id.activity_main_history);
    }
}
