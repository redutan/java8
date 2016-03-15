package io.redutan.java8.stream;

import lombok.Value;
import org.junit.Test;

import java.util.*;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

/**
 * @author myeongju.jung
 */
public class BasicStreamTest {
    List<Dish> menu = Arrays.asList(
        new Dish("fork",            false,  800, Dish.Type.MEAT),
        new Dish("beef",            false,  700, Dish.Type.MEAT),
        new Dish("chicken",         false,  400, Dish.Type.MEAT),
        new Dish("french fries",    true,   530, Dish.Type.OTHER),
        new Dish("rice",            true,   350, Dish.Type.OTHER),
        new Dish("season fruit",    true,   120, Dish.Type.OTHER),
        new Dish("pizza",           true,   550, Dish.Type.OTHER),
        new Dish("prawns",          false,  400, Dish.Type.FISH),
        new Dish("salmon",          false,  450, Dish.Type.FISH)
    );

    /** 칼로리가 400 보다 낮고 칼로리 크기로 정렬한 메뉴명을 반환 by java7 */
    @Test
    public void testFilter_Java7() throws Exception {
        System.out.println("칼로리가 400 보다 낮고 칼로리 크기로 정렬한 메뉴명을 반환 by java7");
        List<Dish> filteredList = new ArrayList<>();
        for (Dish dish : menu) {
            if (dish.getCalories() < 400) {
                filteredList.add(dish);
            }
        }
        Collections.sort(filteredList, new Comparator<Dish>() {
            @Override
            public int compare(Dish o1, Dish o2) {
                return Integer.compare(o1.getCalories(), o2.getCalories());
            }
        });
        List<String> names = new ArrayList<>();
        for (Dish dish : filteredList) {
            names.add(dish.getName());
        }
        for (String name : names) {
            System.out.println(name);
        }
    }

    /** 칼로리가 400 보다 낮고 칼로리 크기로 정렬한 메뉴명을 반환 by java8 */
    @Test
    public void testFilter_Java8() throws Exception {
        System.out.println("칼로리가 400 보다 낮고 칼로리 크기로 정렬한 메뉴명을 반환 by java8");
        List<String> names = menu.stream()
                .filter(d -> d.getCalories() < 400)
                .sorted(comparing(Dish::getCalories))
                .map(Dish::getName)
                .collect(toList());

        printList(names);
    }

    /** 채식이 가능한 메뉴만 필터링 */
    @Test
    public void testFilter() throws Exception {
        System.out.println("채식이 가능한 메뉴만 필터링");
        List<Dish> dishes = menu.stream()
                .filter(Dish::isVegetarian)
                .collect(toList());
        printList(dishes);
    }

    @Test
    public void testFilterAndSlice() throws Exception {
        // 칼로리가 300 이상
        System.out.println("칼로리 300 이상");
        List<Dish> over300s = menu.stream()
                .filter(d -> d.getCalories() >= 300)
                .collect(toList());
        printList(over300s);

        // 칼로리가 300 이상 5개 까지
        System.out.println("칼로리가 300 이상 5개 까지");
        List<Dish> over300Limit5s = menu.stream()
                .filter(d -> d.getCalories() >= 300)
                .limit(5)
                .collect(toList());
        printList(over300Limit5s);

        // 칼로리가 300 이상 2개 이후
        System.out.println("칼로리 300 이상 2개 이후");
        List<Dish> over300LimitAfter2s = menu.stream()
                .filter(d -> d.getCalories() >= 300)
                .skip(2)
                .collect(toList());
        printList(over300LimitAfter2s);
    }

    private <T> void printList(List<T> list) {
        list.stream().forEach(System.out::println);
    }

    /** 짝수만 필터해서 중복제거 */
    @Test
    public void testFilterEven() throws Exception {
        System.out.println("짝수만 필터해서 중복제거");
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> evens = numbers.stream()
                .filter(i -> i % 2 == 0)
                .distinct()
                .collect(toList());
        evens.stream().forEach(System.out::println);
    }
}

@Value
class Dish {
    private String name;
    private boolean vegetarian;
    private int calories;
    private Type type;

    enum Type {
        MEAT, FISH, OTHER
    }
}
