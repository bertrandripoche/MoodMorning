package com.depuisletemps.moodmorning.utils;

import org.threeten.bp.ZonedDateTime;
import org.threeten.bp.format.DateTimeFormatter;

public class TimeUtils {

    private final static DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;

    public static String getDate() {
        return formatter.format(ZonedDateTime.now());
    }

    /**
     * Method to get the last 7 days as an array of String (YYYY-MM-DD)
     * @return an array of String (YYYY-MM-DD)
     */
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
