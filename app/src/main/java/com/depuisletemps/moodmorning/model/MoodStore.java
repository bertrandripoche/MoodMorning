package com.depuisletemps.moodmorning.model;

import android.content.SharedPreferences;
import com.jakewharton.threetenabp.AndroidThreeTen;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.format.DateTimeFormatter;
import org.threeten.bp.ZonedDateTime;

public class MoodStore {
    private SharedPreferences mPreferences;
    private String date;
    private String mood;
    private String comment;

    public MoodStore(String reg) {
        int i = 0;
        for (String result: reg.split("_")) {
            switch (i) {
                case 0:
                this.date = result;
                break;

                case 1:
                    this.mood = result;
                    break;

                case 2:
                    this.comment = result;
                    break;

                default:
                    break;
            }
            i++;
        }
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
