package io.redutan.java8.stream;

import org.junit.Test;

import java.util.ArrayList;
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
    public void testFlatMap() throws Exception {
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

        // Stream<Stream<String>> 스트림이 List로 리턴된다
        List<Stream<String>> result2 = words.stream()
                .map(s -> s.split(""))
                .map(Arrays::stream)
                .distinct()
                .collect(toList());
        printList(result2);

        // Stream<String> 스트림이 정상적으로 List로 리턴된다
        // flatMap에 의해 각각의 Stream<String> 들이 하나의 Stream으로 합쳐진다
        // 출력결과로 string의 값이 출력
        List<String> result3 = words.stream()
                .map(s -> s.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(toList());
        printList(result3);
    }

    /** 두개의 숫자 쌍을 반환한다 */
    @Test
    public void testPair_java7() throws Exception {
        List<Integer> number1 = Arrays.asList(1, 2, 3);
        List<Integer> number2 = Arrays.asList(3, 4);

        List<int[]> result = new ArrayList<>();
        for (Integer num1 : number1) {
            for (Integer num2 : number2) {
                result.add(new int[] {num1, num2});
            }
        }
        for (int[] is : result) {
            System.out.println("(" + is[0] + "," + is[1] + ")");
        }
    }

    /** 두개의 숫자 쌍을 반환한다 - java8 */
    @Test
    public void testPair_java8() throws Exception {
        List<Integer> number1 = Arrays.asList(1, 2, 3);
        List<Integer> number2 = Arrays.asList(3, 4);

        List<int[]> result = number1.stream()
                .flatMap(n1 -> number2.stream()
                        .map(n2 -> new int[]{n1, n2}))
                .collect(toList());
        for (int[] is : result) {
            System.out.println("(" + is[0] + "," + is[1] + ")");
        }
    }

    /** 두개의 숫자 중 합이 3으로 나누어 떨어지는 쌍만 반환 */
    @Test
    public void testFilterPair_java8() throws Exception {
        List<Integer> number1 = Arrays.asList(1, 2, 3);
        List<Integer> number2 = Arrays.asList(3, 4);

        List<int[]> result = number1.stream()
                .flatMap(n1 -> number2.stream()
                        .filter(n2 -> (n1 + n2) % 3 == 0)
                        .map(n2 -> new int[]{n1, n2}))
                .collect(toList());
        for (int[] is : result) {
            System.out.println("(" + is[0] + "," + is[1] + ")");
        }
    }



    private <T> void printList(List<T> list) {
        list.stream().forEach(System.out::println);
    }
}
