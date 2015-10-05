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

    // How to represent fixed date e.g. credit card expiry, YearMonth
    @Test
    public void testYearMonth() throws Exception {
        YearMonth currentYearMonth = YearMonth.now();
        System.out.printf("Days in month year %s : %d%n", currentYearMonth, currentYearMonth.lengthOfMonth());
        YearMonth creditCardExpiry = YearMonth.of(2018, Month.FEBRUARY);
        System.out.printf("Your credit card expires on %s %n", creditCardExpiry);
    }

    // How to check Leap Year in Java 8
    @Test
    public void testLeapYear() throws Exception {
        if (today.isLeapYear()) {
            System.out.println("This year is leap year");
        } else {
            System.out.println(today.getYear() + " is not a leap year");
        }
    }

    // How many days, month between two dates
    @Test
    public void testBetweenMonth() throws Exception {
        LocalDate java8Release = LocalDate.of(2014, Month.FEBRUARY, 14);
        Period periodToNextJavaRelease = Period.between(java8Release, today);
        System.out.println("Month left between today and Java 8 release : " +
                periodToNextJavaRelease.getMonths() + "m, " + periodToNextJavaRelease.getDays() + "d");
    }

    // Date and Time with timezone offset
    @Test
    public void testTimezone() throws Exception {
        LocalDateTime datetime = LocalDateTime.of(2014, Month.JANUARY, 14, 19 ,30);
        ZoneOffset offset = ZoneOffset.of("+05:30");
        OffsetDateTime date = OffsetDateTime.of(datetime, offset);
        System.out.println("Date and Time with timezone offset in Java : " + date);
    }

}
