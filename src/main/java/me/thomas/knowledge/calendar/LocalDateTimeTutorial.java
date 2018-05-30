package me.thomas.knowledge.calendar;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author zhaoxinsheng
 * @date 02/11/2016.
 */
public class LocalDateTimeTutorial {

    public static void main(String[] args) {
        ZonedDateTime localDateTime = ZonedDateTime.ofInstant(Instant.ofEpochSecond(1437136300), ZoneId.systemDefault());
        System.out.println(localDateTime);
        System.out.println(localDateTime.format(DateTimeFormatter.ISO_INSTANT));
        long timestamp = localDateTime.toEpochSecond();
        System.out.println(timestamp);

        LocalDateTime dateTime = LocalDateTime.of(2017, Month.APRIL, 28, 14, 31, 58, 123);
        OffsetDateTime offsetDateTime = OffsetDateTime.of(dateTime, ZoneOffset.ofHours(5));
        System.out.println(offsetDateTime.format(DateTimeFormatter.ISO_INSTANT));
        String strDate = "2017-04-28T09:31:58.000000123Z";
        System.out.println(OffsetDateTime.parse(strDate, DateTimeFormatter.ISO_INSTANT));

    }

}
