package io.redutan.java8.date;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.MonthDay;
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

    @Test
    public void testToday() throws Exception {
        System.out.println("Today's local date " + today);
    }

    @Test
    public void testTodayYMD() throws Exception {
        int year = today.getYear();
        int month = today.getMonthValue();  // 1 base
        int day = today.getDayOfMonth();

        System.out.printf("Year : %d, Month : %d, Day : %d", year, month, day);
    }

    @Test
    public void testOf() throws Exception {
        LocalDate dateOfBirth = LocalDate.of(1980, 11, 1);
        System.out.println("Your date of birth is : " + dateOfBirth);
    }

    @Test
    public void testEquals() throws Exception {
        LocalDate date1 = LocalDate.of(2015, 9, 27);
        if (date1.equals(today)) {
            System.out.printf("Today %s and date1 %s are same date %n", today, date1);
        } else {
            System.out.printf("Today %s and date1 %s are not same date %n", today, date1);
        }
    }

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

    @Test
    public void testPlusWeek() throws Exception {
        LocalDate nextWeek = today.plus(1, ChronoUnit.WEEKS);
        System.out.println("Today is " + today);
        System.out.println("Date after 1 week " + nextWeek);
    }

    @Test
    public void testPlusMinusYear() throws Exception {
        LocalDate previousYear  = today.minus(1, ChronoUnit.YEARS);
        System.out.println("Date before 1 year " + previousYear);

        LocalDate nextYear = today.plus(1, ChronoUnit.YEARS);
        System.out.println("Date after 1 year " + nextYear);
    }


}
