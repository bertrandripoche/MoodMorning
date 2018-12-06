package com.depuisletemps.moodmorning;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
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

        mCommentBtn.setOnClickListener(
            // method to display an alert dialog with an edit text
                final EditText commentBox = new EditText(this);

            AlertDialog dialog = new AlertDialog.Builder(this)
                    .setTitle("About my mood")
                    .setMessage("Add a comment")
                    .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String comment = String.valueOf(commentBox.getText());
                        }
                    })
                    .setNegativeButton("Cancel", null)
                    .create();
        );


    }
}
