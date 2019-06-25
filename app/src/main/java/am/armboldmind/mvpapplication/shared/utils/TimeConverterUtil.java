package am.armboldmind.mvpapplication.shared.utils;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public final class TimeConverterUtil {
    public static String convertMillisToString(final long millis) {
        if (TimeUnit.MILLISECONDS.toHours(millis) == 0L)
            return String.format(Locale.getDefault(), "%02d:%02d",
                    TimeUnit.MILLISECONDS.toMinutes(millis) -
                            TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                    TimeUnit.MILLISECONDS.toSeconds(millis) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
        else
            return String.format(Locale.getDefault(), "%02d:%02d",
                    TimeUnit.MILLISECONDS.toHours(millis),
                    TimeUnit.MILLISECONDS.toMinutes(millis) -
                            TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)));
    }
}