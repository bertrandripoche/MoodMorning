package com.depuisletemps.moodmorning.model;

import android.text.TextUtils;

public class MoodStore {
    private Mood mood;
    private String comment;

    public MoodStore(Mood mood, String comment) {
        if (mood != null) {
            this.mood = mood;
            this.comment = comment;
        }
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
    WARNING :
    KEPT ONLY IF WE WOULD LIKE TO CHANGE THE HISTORY FOR THE 7 LAST MOODS
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
