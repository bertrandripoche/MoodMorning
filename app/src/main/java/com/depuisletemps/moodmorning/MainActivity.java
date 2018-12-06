package com.depuisletemps.moodmorning;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView mMood;
    private ImageButton mCommentBtn;
    private ImageButton mHistoryBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMood = (ImageView) findViewById(R.id.activity_main_mood);
        mCommentBtn = (ImageButton) findViewById(R.id.activity_main_comment);
        mHistoryBtn = (ImageButton) findViewById(R.id.activity_main_history);

        mCommentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
 /*               final EditText commentBox = new EditText();

                AlertDialog dialog = new AlertDialog.Builder()
                        .setTitle("About my mood")
                        .setMessage("Add a comment")
                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String comment = String.valueOf(commentBox.getText());
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .create();*/
 System.out.print("TEST OK");
            }
       });
    }
}
