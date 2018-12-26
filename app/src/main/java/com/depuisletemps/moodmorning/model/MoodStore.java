package com.depuisletemps.moodmorning.model;

import android.text.TextUtils;

public class MoodStore {
    private String date;
    private Mood mood;
    private String comment;

    public MoodStore(String date, Mood mood, String comment) {
        if (date != null && mood != null) {
            this.date = date;
            this.mood = mood;
            this.comment = comment;
        }
    }

    public String getDate() {
        return date;
    }

    public Mood getMood() {
        return mood;
    }

    public String getComment() {
        return comment;
    }

    public void setMood(Mood mood) {
        this.mood = mood;
    }

    public void setComment(String comment) {
        if (!TextUtils.isEmpty(comment)) {
            this.comment = comment;
        } else {
            this.comment = "";
        }
    }

    /*
     Would be useful if we want to check the 7 last moods instead of the moods of the 7 last days
     public TreeMap<String, String> getAllDatesSorted(Context context) {
        SharedPreferences mPreferences = context.getSharedPreferences("mPreferences", Context.MODE_PRIVATE);
        Map<String, ?> allPrefs = mPreferences.getAll();
        TreeMap<String, String> allPrefsSorted = new TreeMap<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                // logic here
                return 0;
            }
        });
        for (Map.Entry<String, ?> stringEntry : allPrefs.entrySet()) {
            allPrefsSorted.put(stringEntry.getKey(),stringEntry.getValue());
        }
        return allPrefsSorted;
    } */
}
