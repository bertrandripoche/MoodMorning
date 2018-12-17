package com.depuisletemps.moodmorning.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

import com.depuisletemps.moodmorning.utils.AboutTime;

public class MoodStore extends AppCompatActivity {
    private String date;
    private String mood;
    private String comment;
    private static String today = AboutTime.todayStr;
    private static Mood currentMood = Mood.HAPPY;

    public MoodStore(String record) {
        String[] recordArray = record.split("_", 3);

        int recordArraySize = recordArray.length;

        if (recordArraySize == 3) {
            this.date = recordArray[0];
            this.mood = recordArray[1];
            this.comment = recordArray[2];
        }
    }

    public static void storePreferences(Context context, String today, String record) {
            SharedPreferences mPreferences = context.getSharedPreferences("mPreferences", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = mPreferences.edit();
            editor.putString(today, record);
            editor.commit();
    }

    public static String getPreferences(Context context, String key) {
        SharedPreferences mPreferences = context.getSharedPreferences("mPreferences", Context.MODE_PRIVATE);
        return mPreferences.getString(key,"%");
    }

    public static MoodStore checkMoodForToday(Context context) {
        MoodStore todayInfo;
        SharedPreferences mPreferences = context.getSharedPreferences("mPreferences", Context.MODE_PRIVATE);

        if (mPreferences.getString(today, null) != null ) {
            todayInfo = new MoodStore(mPreferences.getString(today, null));
        } else {
            String record = today + "_" + currentMood + "_%";
            todayInfo = new MoodStore(record);
        }
        return todayInfo;
    }

    public String getDate() {
        return date;
    }

    public String getMood() {
        return mood;
    }

    public String getComment() {
        return comment;
    }
}
