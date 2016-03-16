package io.redutan.java8.stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * 리듀싱
 * Created by redutan on 2016. 3. 16..
 */
public class ReduceTest {
    List<Dish> menu = Arrays.asList(
            new Dish("fork", false, 800, Dish.Type.MEAT),
            new Dish("beef", false, 700, Dish.Type.MEAT),
            new Dish("chicken", false, 400, Dish.Type.MEAT),
            new Dish("french fries", true, 530, Dish.Type.OTHER),
            new Dish("rice", true, 350, Dish.Type.OTHER),
            new Dish("season fruit", true, 120, Dish.Type.OTHER),
            new Dish("pizza", true, 550, Dish.Type.OTHER),
            new Dish("prawns", false, 400, Dish.Type.FISH),
            new Dish("salmon", false, 450, Dish.Type.FISH)
    );

    List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

    @Test
    public void testSum() throws Exception {
        // 합계 (Collection)
        int sum = 0;
        for (Integer x : numbers) {
            sum += x;
        }
        System.out.println(sum);

        // 합계 (람다)
        int sum2 = numbers.stream().reduce(0, (a, b) -> a + b);
        System.out.println(sum2);

        // 합계 (메서드 레퍼런스)
        int sum3 = numbers.stream().reduce(0, Integer::sum);

        // 초기값 없이 합계
        Optional<Integer> sum4 = numbers.stream().reduce(Integer::sum);
        System.out.println(sum4.get());
    }

    @Test
    public void testMax() throws Exception {
        // Max (람다)
        Optional<Integer> max = numbers.stream().reduce((a, b) -> a > b ? a : b);
        System.out.println(max.get());

        // Max (메서드레퍼런스)
        Optional<Integer> max2 = numbers.stream().reduce(Integer::max);
        System.out.println(max2.get());
    }

    @Test
    public void testMin() throws Exception {
        // Min (람다)
        Optional<Integer> min = numbers.stream().reduce((a, b) -> a < b ? a : b);
        System.out.println(min.get());

        // Max (메서드레퍼런스)
        Optional<Integer> min2 = numbers.stream().reduce(Integer::min);
        System.out.println(min2.get());
    }

    @Test
    public void testCount() throws Exception {
        // 카운트 (Collection)
        int count = menu.size();

        // 카운트 (람다)
        int count2 = menu.stream()
                .map(dish -> 1)
                .reduce(0, (a, b) -> a + b);

        // 카운트
        long count3 = menu.stream().count();
        System.out.println(count + ", " + count2 + ", " + count3);
    }
}
