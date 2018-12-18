package com.depuisletemps.moodmorning.utils;

import android.util.Log;

import org.threeten.bp.LocalDate;
import org.threeten.bp.ZonedDateTime;
import org.threeten.bp.format.DateTimeFormatter;

public class TimeUtils {

    private static DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;

    public static String getDate() {
        return formatter.format(ZonedDateTime.now());
    }

    public static String[] getLast7Days() {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
        String[] daysBefore = new String[7];
        int j = 0;
        for (int i = 7; i > 0 ; i--) {
            ZonedDateTime dayBefore = ZonedDateTime.now().minusDays(i);
            daysBefore[j] = dayBefore.format(formatter);
            j++;
        }
        return daysBefore;
    }
}
