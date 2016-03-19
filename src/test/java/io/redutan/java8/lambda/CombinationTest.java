package io.redutan.java8.lambda;

import lombok.Value;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.util.Comparator.comparing;

/**
 * 람다식 조합
 * @author myeongju.jung
 */
public class CombinationTest {

    @Test
    public void testSort() throws Exception {
        List<Apple> inventory = new ArrayList<>();
        inventory.addAll(Arrays.asList(
                new Apple(80, "green"),
                new Apple(155, "green"),
                new Apple(120, "red")
        ));

        // 사과를 무게로 정렬
        inventory.sort(comparing(Apple::getWeight));

        // 사과를 무게로 내림차순 정렬
        inventory.sort(comparing(Apple::getWeight).reversed());

        // 사과를 무게로 내림차순 정렬 시 같은 무게가 나오면 색깔로 정렬
        inventory.sort(comparing(Apple::getWeight).reversed().thenComparing(Apple::getColor));

        for (Apple apple : inventory) {
            System.out.println(apple);
        }
    }

    @Test
    public void testFilter() throws Exception {
        // 빨간 사과만 확인하는 Predicate
        Predicate<Apple> red = a -> "red".equals(a.getColor());

        // 빨갛고 무거운 사과만 확인하는 Predicate : (a and b)
        Predicate<Apple> redHeavy = red.and(a -> a.getWeight() > 100);
        // 우선순위를 바꾸고 싶었는데 오류가 난다 ㅠㅠ
        // Predicate<Apple> redAndHeavy2 = (a -> a.getWeight() > 100).and(red);

        // 빨갛고 무거운 사과거나 녹색 사과를 확인하는 Predicate : ((a and b) or c)
        Predicate<Apple> redHeavyOrGreen = redHeavy.or(a -> "green".equals(a.getColor()));

        System.out.println(redHeavy.test(new Apple(120, "red")));
        System.out.println(redHeavyOrGreen.test(new Apple(120, "blue")));
    }

    @Test
    public void testMultiFunction() throws Exception {
        // x 값을 +1 해서 리턴하는 Function
        Function<Integer, Integer> plus1 = i -> i+1;

        // x 값을 2배로 리턴하는 Function
        Function<Integer, Integer> multi2 = i -> i * 2;

        // aaa 결과값을 입력값으로 bbb 결과값을 리턴하는
        Function<Integer, Integer> plus1Multi2 = plus1.andThen(multi2);

        System.out.println(plus1Multi2.apply(1));
    }

    @Value
    private static class Apple {
        private int weight;
        private String color;
    }
}
