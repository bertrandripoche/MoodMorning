package com.depuisletemps.moodmorning.model;

import android.support.v7.app.AppCompatActivity;

public class MoodStore extends AppCompatActivity {
    private String date;
    private Mood mood;
    private String comment;

    public MoodStore(String date, Mood mood, String comment) {
        this.date = date;
        this.mood = mood;
        this.comment = comment;
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

    /* public TreeMap<String, String> getAllDatesSorted(Context context) {
        SharedPreferences mPreferences = context.getSharedPreferences("mPreferences", Context.MODE_PRIVATE);
        Map<String, ?> allPrefs = mPreferences.getAll();
        TreeMap<String, String> allPrefsSorted = new TreeMap<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                // blablabla
                return 0;
            }
        });
        for (Map.Entry<String, ?> stringEntry : allPrefs.entrySet()) {
            allPrefsSorted.put(stringEntry.getKey(),stringEntry.getValue());
        }
        return allPrefsSorted;
    } */
}
