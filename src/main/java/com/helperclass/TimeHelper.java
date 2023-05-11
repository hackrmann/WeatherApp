package com.helperclass;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class TimeHelper {


    public static String convertUnixToLocal(long currentTimeInUnixSeconds, long timezone) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z");
        Instant instant = Instant.ofEpochMilli(currentTimeInUnixSeconds*1000L);
        ZoneOffset offset = ZoneOffset.ofTotalSeconds((int) timezone);
        OffsetDateTime offsetDateTime = OffsetDateTime.ofInstant(instant, offset);
        ZonedDateTime zonedDateTime = offsetDateTime.toZonedDateTime();
        String formattedString = zonedDateTime.format(formatter);
        return formattedString;
    }
}
