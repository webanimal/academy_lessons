package ru.webanimal.academy_lessons.utils;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateTimeUtils {

    private static final long HOURS_IN_DAY = 24;

    public static String getPublishedDateAsFormattedString(Date date) {
        long diff = (System.currentTimeMillis() - date.getTime()) / (60 * 60 * 1000);
        StringBuilder sb = new StringBuilder();
        sb.append(getPrefix(diff));
        sb.append(getDate(diff, date));
        return sb.toString();
    }

    private static String getPrefix(long diff) {
        if (diff <= 1) {
            return "just";

        } else if (diff < HOURS_IN_DAY) {
            return String.valueOf(diff) + " hr. ago";

        } else if (diff < HOURS_IN_DAY * 2) {
            return "yesterday, ";

        } else {
            return "";
        }
    }

    private static String getDate(long diff, Date date) {
        if (diff < HOURS_IN_DAY) {
            return "";

        } else if (diff < HOURS_IN_DAY * 2) {
            return getHoursFormatter().format(date);
        }

        return getDaysFormatter().format(date);
    }


    private static SimpleDateFormat getHoursFormatter() {
        SimpleDateFormat publishingFormat = new SimpleDateFormat("h:mm a", Locale.getDefault());
        DateFormatSymbols symbolsFormat = new DateFormatSymbols();
        symbolsFormat.setAmPmStrings(new String[]{"AM", "PM"});
        publishingFormat.setDateFormatSymbols(symbolsFormat);

        return publishingFormat;
    }

    private static SimpleDateFormat getDaysFormatter() {
        return new SimpleDateFormat("yyyy MMM dd", Locale.getDefault());
    }
}
