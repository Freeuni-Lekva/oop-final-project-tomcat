package ge.edu.freeuni.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DatetimeUtil {
    public static String getDuration(Long startTimestamp, Long finishTimestamp) {
        long durationMillis = finishTimestamp - startTimestamp;
        long hours = TimeUnit.MILLISECONDS.toHours(durationMillis);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(durationMillis) % 60;
        long seconds = TimeUnit.MILLISECONDS.toSeconds(durationMillis) % 60;

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    public static String convertTimestampToString(long timestamp) {
        Date date = new Date(timestamp * 1000L);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        return sdf.format(date);
    }
}
