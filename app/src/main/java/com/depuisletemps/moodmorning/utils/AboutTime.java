package com.depuisletemps.moodmorning.utils;

import android.util.Log;

import org.threeten.bp.LocalDate;
import org.threeten.bp.ZonedDateTime;
import org.threeten.bp.format.DateTimeFormatter;

public class AboutTime {

    private static DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
    public static LocalDate today = LocalDate.now();
    public static String todayStr = today.format(formatter);

    public static String getDate() {
        String formattedZonedDate = formatter.format(ZonedDateTime.now());
        return formattedZonedDate;
    }

    public static String[] getLast7Days() {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
        String[] daysBefore = new String[7];
        int j = 0;
        for (int i = 7; i > 0 ; i--) {
            LocalDate dayBefore = today.minusDays(i);
            daysBefore[j] = dayBefore.format(formatter);
            j++;
        }
        return daysBefore;
    }
}
