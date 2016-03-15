package io.redutan.java8.stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * @author myeongju.jung
 */
public class MapTest {
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

    @Test
    public void testMapping() throws Exception {
        // 메뉴명만 추출
        System.out.println("메뉴명만 추출");
        List<String> names = menu.stream()
                .map(Dish::getName)
                .collect(toList());
        printList(names);

        // 문자열 길이 추출
        System.out.println("문자열 길이 추출");
        List<String> words = Arrays.asList("Java8", "Lambdas", "In", "Actions");
        List<Integer> wordLengths = words.stream()
                .map(String::length)
                .collect(toList());
        printList(wordLengths);

        // 메뉴명의 길이 추출
        System.out.println("메뉴명의 길이 추출");
        List<Integer> menuLengths = menu.stream()
                .map(Dish::getName)
                .map(String::length)
                .collect(toList());
        printList(menuLengths);
    }

    /** 제곱근 */
    @Test
    public void testSquare() throws Exception {
        System.out.println("제곱근");
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> squares = numbers.stream()
                .map(n -> n * n)
                .collect(toList());
        printList(squares);
    }

    @Test
    public void testMap() throws Exception {
        String[] arrayOfWords = {"Goodbye", "World"};
        Stream<String> streamOfWord = Arrays.stream(arrayOfWords);

        streamOfWord.forEach(System.out::println);
        System.out.println();

        // 고유문자를 리턴해보자. 원하는 결과 ["H", "e", "l", "o", "W", "r", "d"]
        List<String> words = Arrays.asList("Hello", "World");

        // Stream<String[]> 스트림이 List로 리턴된다.
        // 출력결과로 String 배열의 toString
        List<String[]> result1 = words.stream()
                .map(s -> s.split(""))
                .distinct()
                .collect(toList());
        printList(result1);
    }

    private <T> void printList(List<T> list) {
        list.stream().forEach(System.out::println);
    }
}
