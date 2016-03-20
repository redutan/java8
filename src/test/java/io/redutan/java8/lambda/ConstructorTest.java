package io.redutan.java8.lambda;

import lombok.Value;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import static java.util.stream.Collectors.toList;

/**
 * 생성자 레퍼런스
 * @author myeongju.jung
 */
public class ConstructorTest {
    @Test
    public void testConstructor() throws Exception {
        // 기본 생성자로 사과 객체 생성 (람다)
        Supplier<Apple> ex1 = () -> new Apple();
        Apple aEx1 = ex1.get();

        // 기본 생성자로 사과 객체 생성 (생성자 레퍼런스)
        Supplier<Apple> ex2 = Apple::new;
        Apple aEx2 = ex2.get();

        // 특정 무게의 사과 객체 생성 (람다)
        Function<Integer, Apple> ex3 = (weight) -> new Apple(weight);
        Apple aEx3 = ex3.apply(100);

        // 특정 무계의 사과 객체 생성 (생성자 레퍼런스)
        Function<Integer, Apple> ex4 = Apple::new;
        Apple aEx4 = ex4.apply(100);

        // 제공되는 데이터의 무게를 가지는 사과 리스트 생성
        List<Integer> weights = Arrays.asList(12, 34, 56, 78);
        List<Apple> apples = process(weights, Apple::new);

        apples.stream()
                .forEach(System.out::println);
    }

    private <T, R> List<R> process(List<T> weights, Function<T, R> function) {
        return weights.stream()
                .map(function::apply)
                .collect(toList());
    }

    @Value
    private static class Apple {
        private int weight;
        private String color;

        Apple() {
            this.weight = 0;
            this.color = null;
        }

        Apple(int weight) {
            this.weight = weight;
            this.color = null;
        }
    }
}
