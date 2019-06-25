package am.armboldmind.mvpapplication.shared.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public final class DateTimeUtils {

    public static final String SERVER_DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss";
    public static final String DATE_PATTERN_DAY_MONTH_YEAR_HOUR = "dd MMMM yyyy, HH:mm";
    public static final String DATE_PATTERN_DAY_MONTH_YEAR = "dd MMM yyyy";
    public static final String DATE_PATTERN_DAY_MONTH_YEAR_2 = "dd MMMM, yyyy";
    public static final String DATE_PATTERN_DAY_MONTH = "dd MMM";
    public static final String DATE_PATTERN_MONTH = "MMM";
    public static final String DATE_PATTERN_DAY = "dd";
    public static final String DATE_PATTERN_YEAR = "yyyy";
    public static final String DATE_PATTERN_TIME_1 = "HH : mm";
    public static final String DATE_PATTERN_TIME_2 = "HH:mm";
    public static final String DATE_PATTERN_MINUTE = "mm";
    public static final String DATE_PATTERN_HOUR = "HH";

    public static String convertServerDate(String date, String datePattern, String convertingDatePattern) {
        if (date != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(datePattern, Locale.getDefault());
            sdf.setTimeZone(TimeZone.getTimeZone("UTC"));

            SimpleDateFormat sdf1 = new SimpleDateFormat(convertingDatePattern, Locale.getDefault());
            sdf1.setTimeZone(TimeZone.getDefault());
            try {
                return sdf1.format(sdf.parse(date));
            } catch (ParseException e) {
                e.printStackTrace();
                return "";
            }
        } else
            return "";
    }

    public static String convertDateToString(Date date, String datePattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(datePattern, Locale.getDefault());
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        return sdf.format(date);
    }

    public static Date convertStringToDate(String date, String datePattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(datePattern, Locale.getDefault());
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return new Date();
        }
    }

    public static Boolean compareDate(final String firstDate, final String secondDate) {
        String firstDateStr = convertServerDate(firstDate, SERVER_DATE_PATTERN, DATE_PATTERN_DAY_MONTH_YEAR);
        String secondDateStr = convertServerDate(secondDate, SERVER_DATE_PATTERN, DATE_PATTERN_DAY_MONTH_YEAR);
        return firstDateStr.equals(secondDateStr);
    }
}