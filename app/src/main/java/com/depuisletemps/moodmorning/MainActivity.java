package com.depuisletemps.moodmorning;

import android.content.DialogInterface;
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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mMood;
    private ImageButton mCommentBtn;
    private ImageButton mHistoryBtn;

    private SharedPreferences mPreferences;
    public static final String PREF_KEY_COMMENT = "PREF_KEY_COMMENT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMood = (ImageView) findViewById(R.id.activity_main_mood);
        mCommentBtn = (ImageButton) findViewById(R.id.activity_main_comment_btn);
        mHistoryBtn = (ImageButton) findViewById(R.id.activity_main_history_btn);
        mPreferences = getPreferences(MODE_PRIVATE);

        mCommentBtn.setOnClickListener(this);
        mHistoryBtn.setOnClickListener(this);
    }

    public void onClick(View v) {
        if (v.equals(mCommentBtn)) {

            LayoutInflater factory = LayoutInflater.from(this);
            final View commentBoxView = factory.inflate(R.layout.activity_main_my_comment_box, null);
            final EditText myComment = (EditText)commentBoxView.findViewById(R.id.activity_main_my_comment_input);

            final String test = mPreferences.getString(PREF_KEY_COMMENT, "OK");
            Toast.makeText(MainActivity.this, test, Toast.LENGTH_SHORT).show();
            if (mPreferences.getString(PREF_KEY_COMMENT, null) != "") {
                myComment.setHint(mPreferences.getString(PREF_KEY_COMMENT, null));
            }

            AlertDialog.Builder commentBox = new AlertDialog.Builder(MainActivity.this);
            commentBox.setView(commentBoxView);
            commentBox.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    String comment = myComment.getText().toString();
// Toast.makeText(MainActivity.this, comment, Toast.LENGTH_SHORT).show();
                    mPreferences.edit().putString(PREF_KEY_COMMENT, comment).apply();
                }
            });
            commentBox.setNegativeButton("Cancel", null);
            commentBox.show();
        } else if (v.equals(mHistoryBtn)) {
            Toast.makeText(this, "History !", Toast.LENGTH_SHORT).show();
        }
    }
}
