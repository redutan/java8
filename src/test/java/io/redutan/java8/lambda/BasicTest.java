package io.redutan.java8.lambda;

import lombok.Value;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static java.util.Comparator.comparing;

/**
 * 람다 기본 문법
 * @author myeongju.jung
 */
public class BasicTest {

    @Test
    public void testBasic() throws Exception {
        // 객체에서 선택/추출
        Function<String, Integer> ex1 = (String s) -> s.length();

        // 불린 표현식
        Predicate<Apple> ex2 = (Apple a) -> a.getWeight() > 150;

        // 객체에서 소비
        BiConsumer<Integer, Integer> ex3 = (Integer x, Integer y) -> {
            System.out.println("Result:");
            System.out.println(x + y);
        };

        // 특정값을 리턴
        Supplier<Integer> x4 = () -> 42;

        // 두 객체 비교
        Comparator<Apple> ex5 = (Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight());

        // 불린 표현식
        Predicate<List<String>> ex6 = (List<String> list) -> {
            return list.isEmpty();
        };
    }

    @Test
    public void testSort() throws Exception {
        // 데이터 생성
        List<Apple> inventory = new ArrayList<>();
        inventory.addAll(Arrays.asList(
                new Apple(80, "green"),
                new Apple(155, "green"),
                new Apple(120, "red")
        ));

        // 1. 문으로 작성
        inventory.sort((Apple a1, Apple a2) -> {
           return a1.getWeight().compareTo(a2.getWeight());
        });

        // 2. 식으로 변경
        inventory.sort((Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight()));

        // 3. 인자 타입 생략
        inventory.sort((a1, a2) -> a1.getWeight().compareTo(a2.getWeight()));

        // 4. static 유틸리티 함수 적용
        inventory.sort(comparing(a -> a.getWeight()));

        // 5. 메서드 레퍼런스 활용
        inventory.sort(comparing(Apple::getWeight));
    }

    @Value
    private static class Apple {
        private Integer weight;
        private String color;
    }
}
