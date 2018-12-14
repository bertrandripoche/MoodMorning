package com.depuisletemps.moodmorning.utils;

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
        for (int i = 0; i < 7; i++) {
            LocalDate dayBefore = today.minusDays(i+1);
            daysBefore[i] = dayBefore.format(formatter);
        }
        return daysBefore;
    }
}
