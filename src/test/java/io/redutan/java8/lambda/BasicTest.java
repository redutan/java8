package io.redutan.java8.lambda;

import lombok.Data;

import java.util.Comparator;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * 람다 기본 문법
 * @author myeongju.jung
 */
public class BasicTest {

    public static void main(String[] args) {
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

    @Data
    private static class Apple {
        private Integer weight;
    }
}
