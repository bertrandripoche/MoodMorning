package com.depuisletemps.moodmorning.controller;

import android.content.Context;
import android.content.SharedPreferences;

import com.depuisletemps.moodmorning.model.Mood;
import com.depuisletemps.moodmorning.model.MoodStore;
import com.depuisletemps.moodmorning.utils.TimeUtils;

import java.util.Map;

public class MoodDao {
    private static final String PREFS_NAME = "mPreferences";

    /*
    This methods stores the information of the day
    PARAM :
    Context context
    String today : the current day string YYYY-MM-DD
    String record : the string representing our day_mood_comment
     */
    public void storePreferences(Context context, String today, String record) {
        SharedPreferences mPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putString(today, record);
        editor.commit();
    }

    /*
    This method returns the String stored for the expected day
    PARAM :
    Context context
    String key (representing a date YYYY-MM-DD)
     */
    public String getPreferences(Context context, String key) {
        SharedPreferences mPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return mPreferences.getString(key,"%");
    }

    /*
    This method returns the object MoodStore for today if existing
    PARAM :
    Context context
     */
    public MoodStore getTodaysMood(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        MoodStore moodStore = null;

        if (preferences.getString(TimeUtils.getDate(), null) != null ) {
            moodStore = getMoodStoreFromRecord(preferences.getString(TimeUtils.getDate(), "%"));
            }

        return moodStore;
    }

    /*
    This method returns all the records stored in SharePreferences
    PARAM :
    Context context
     */
    public Map<String, ?> getAllMoods(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return preferences.getAll();
    }

    /*
    This method returns the object MoodStore from a stored record
    PARAM :
    String record
     */
    public MoodStore getMoodStoreFromRecord(String record) {
        MoodStore moodStore = null;
        String[] recordArray = record.split("_", 3);

        int recordArraySize = recordArray.length;

        if (recordArraySize == 3) {
            String date = recordArray[0];
            Mood mood = Mood.valueOf(recordArray[1]);
            String comment = recordArray[2];

            moodStore = new MoodStore(date, mood, comment);
        }
        return moodStore;
    }
}
