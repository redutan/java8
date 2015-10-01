package io.redutan.java8.date;

import org.junit.Before;
import org.junit.Test;

import java.time.Clock;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

/**
 * Created by myeongju.jung on 2015. 9. 27..
 */
public class LocalTimeTest {
    LocalTime time;

    @Before
    public void setUp() throws Exception {
        time = LocalTime.now();
    }

    // How to get current Time in Java 8
    @Test
    public void testNow() throws Exception {
        LocalTime time = LocalTime.now();
        System.out.println("Local time now : " + time);
    }

    // How to add hours in time
    @Test
    public void testPlusHours() throws Exception {
        LocalTime time = LocalTime.now();
        LocalTime newTime = time.plusHours(2);
        System.out.println("Time after 2 hours : " + newTime);
    }
}
