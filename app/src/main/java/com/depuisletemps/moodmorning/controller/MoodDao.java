package com.depuisletemps.moodmorning.controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;

import com.depuisletemps.moodmorning.model.Mood;
import com.depuisletemps.moodmorning.model.MoodStore;
import com.depuisletemps.moodmorning.utils.TimeUtils;

import java.util.Map;

class MoodDao {
    private static final String PREFS_NAME = "mPreferences";
    private static final String SEPARATOR = "_";

    /**
     * This method stores the information of the day
     * @param moodStore : the MoodStore representing the current day/mood/comment
     */
    void insertMoodstore(Context context, MoodStore moodStore) {
        SharedPreferences mPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mPreferences.edit();

        String toBeStored = moodStore.getMood() + SEPARATOR + moodStore.getComment();
        editor.putString(TimeUtils.getDate(), toBeStored);
        editor.apply();
    }

    /**
     * This method returns the String stored for the expected day
     * @param key representing the date YYYY-MM-DD for which we are looking for a stored data
     * @return the serialized string representing a moodstore saved in preferences for specified key
     */
    @Nullable
    String getPreferences(Context context, String key) {
        SharedPreferences mPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return mPreferences.getString(key,null);
    }

    /**
     * @return the object MoodStore for today if existing
     */
    @Nullable
    MoodStore getTodaysMood(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        MoodStore moodStore = null;

        if (preferences.getString(TimeUtils.getDate(), null) != null ) {
            moodStore = getMoodStoreFromRecord(preferences.getString(TimeUtils.getDate(), "%"));
            }

        return moodStore;
    }

    /**
     * This method updates only the mood in the stored information for today
     * @param mood : the mood that we want to store in our MoodStore
     */
    void updateTodaysMood(Context context, Mood mood) {
        MoodStore today = getTodaysMood(context);
        if (today != null) {
            today.setMood(mood);
        }
        this.insertMoodstore(context,today);
    }

    /**
     * This method updates only the comment in the stored information for today
     * @param comment : the comment that we want to store in our MoodStore
     */
    void updateTodaysComment(Context context, String comment) {
        MoodStore today = this.getTodaysMood(context);
        if (today != null) {
            today.setComment(comment);
            insertMoodstore(context, today);
        }
    }

    /**
     * @return all the records stored in SharePreferences
     */
    Map<String, ?> getAllMoods(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return preferences.getAll();
    }

    /**
     * This method returns the object MoodStore from a stored record
     * @param record : our serialized String representing our MoodStore
     * @return the MoodStore matching the serialized record
     */
    @Nullable
    MoodStore getMoodStoreFromRecord(String record) {
        MoodStore moodStore = null;
        String[] recordArray = record.split(SEPARATOR, 2);

        int recordArraySize = recordArray.length;

        if (recordArraySize == 2) {
            Mood mood = Mood.valueOf(recordArray[0]);
            String comment = recordArray[1];

            moodStore = new MoodStore(mood, comment);
        }
        return moodStore;
    }
}
