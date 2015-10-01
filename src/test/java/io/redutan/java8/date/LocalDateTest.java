package io.redutan.java8.date;

import org.junit.Before;
import org.junit.Test;

import java.time.*;
import java.time.temporal.ChronoUnit;

/**
 * Created by myeongju.jung on 2015. 9. 27..
 */
public class LocalDateTest {
    LocalDate today;

    @Before
    public void setUp() throws Exception {
        today = LocalDate.now();
    }

    // How to get today's date in Java 8
    @Test
    public void testToday() throws Exception {
        System.out.println("Today's local date " + today);
    }

    // How to get current day, month and year in java 8
    @Test
    public void testTodayYMD() throws Exception {
        int year = today.getYear();
        int month = today.getMonthValue();  // 1 base
        int day = today.getDayOfMonth();

        System.out.printf("Year : %d, Month : %d, Day : %d", year, month, day);
    }

    // How to get a particular date in Java 8
    @Test
    public void testOf() throws Exception {
        LocalDate dateOfBirth = LocalDate.of(1980, 11, 1);
        System.out.println("Your date of birth is : " + dateOfBirth);
    }

    // How to check if two dates are equal in Java 8
    @Test
    public void testEquals() throws Exception {
        LocalDate date1 = LocalDate.of(2015, 9, 27);
        if (date1.equals(today)) {
            System.out.printf("Today %s and date1 %s are same date %n", today, date1);
        } else {
            System.out.printf("Today %s and date1 %s are not same date %n", today, date1);
        }
    }

    // How to check for recurring events e.g. birthday in Java 8
    @Test
    public void testEqualsMonth() throws Exception {
        LocalDate dateOfBirth = LocalDate.of(1980, 9, 27);
        MonthDay monthDayOfBirth = MonthDay.of(dateOfBirth.getMonth(), dateOfBirth.getDayOfMonth());
        MonthDay currentMonthDay = MonthDay.from(today);
        if (currentMonthDay.equals(monthDayOfBirth)) {
            System.out.println("Many Many happy returns of the day !!");
        } else {
            System.out.println("Sorry, today is not your birthday");
        }
    }

    // How to find Date after 1 week
    @Test
    public void testPlusWeek() throws Exception {
        LocalDate nextWeek = today.plus(1, ChronoUnit.WEEKS);
        System.out.println("Today is " + today);
        System.out.println("Date after 1 week " + nextWeek);
    }

    // Date before and after 1 year
    @Test
    public void testPlusMinusYear() throws Exception {
        LocalDate previousYear  = today.minus(1, ChronoUnit.YEARS);
        System.out.println("Date before 1 year " + previousYear);

        LocalDate nextYear = today.plus(1, ChronoUnit.YEARS);
        System.out.println("Date after 1 year " + nextYear);
    }

    // Using Clock in Java 8
    @Test
    public void testClock() throws Exception {
        Clock utcClock = Clock.systemUTC();
        System.out.println("UTC " + utcClock);
        Clock defaultClokc = Clock.systemDefaultZone();
        System.out.println("Clock " + defaultClokc);
    }

    // How to see if a date is before or after another dates in Java 8
    @Test
    public void testAfterBefore() throws Exception {
        LocalDate tomorrow = today.plusDays(1);

        if (tomorrow.isAfter(today)) {
            System.out.println("Tomorrow comes after today");
        }

        LocalDate yesterday = today.minusDays(1);

        if (yesterday.isBefore(today)) {
            System.out.println("Yesterday is day before today");
        }
    }

    // Dealing with time zones in Java 8
    @Test
    public void testZoneDateTime() throws Exception {
        // Date and time with time zone in Java 8
        ZoneId america = ZoneId.of("America/New_York");
        LocalDateTime localDateAndTime = LocalDateTime.now();
        ZonedDateTime dateAndTimeInNewyork = ZonedDateTime.of(localDateAndTime, america);
        System.out.println("current date and time in a particular timezone : " + dateAndTimeInNewyork);
    }
}
