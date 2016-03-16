package io.redutan.java8.stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

/**
 * 매칭
 * Created by redutan on 2016. 3. 16..
 */
public class MatchTest {
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
    public void testMatch() throws Exception {
        if (menu.stream().anyMatch(Dish::isVegetarian)) {
            System.out.println("채식 메뉴가 존재합니다 TRUE");
        } else {
            System.out.println("채식 메뉴가 존재합니다 FALSE");
        }
        if (menu.stream().allMatch(d -> d.getCalories() <= 1000)) {
            System.out.println("모든 메뉴가 1000 칼로리 이하입나다 TRUE");
        } else {
            System.out.println("모든 메뉴가 1000 칼로리 이하입나다 TRUE");
        }
        if (menu.stream().noneMatch(d -> d.getCalories() >= 1000)) {
            System.out.println("모든 메뉴가 1000 칼로리 이상이 아닙니다 TRUE");
        } else {
            System.out.println("모든 메뉴가 1000 칼로리 이상이 아닙니다 TRUE");
        }
    }

    @Test
    public void testFind() throws Exception {
        // 채식 메뉴가 존재하는지 검사
        Optional<Dish> dish = menu.stream()
                .filter(Dish::isVegetarian)
                .findAny();

        // 결과가 존재하는지 검사
        if (dish.isPresent()) {
            Dish realDish = dish.get(); // 결과리턴
            System.out.println("채식메뉴가 1개 이상 존재함 : " + realDish);
        } else {
            System.out.println("채식메뉴가 없음");
        }
        System.out.println();

        // 채식 메뉴가 존재하는지 검사
        menu.stream()
                .filter(Dish::isVegetarian)
                .findAny()
                .ifPresent(d -> System.out.println("채식메뉴가 1개 이상 존재함 : " + d));
    }

    @Test
    public void testFind2() throws Exception {
        List<Integer> someNumbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // 모든 데이터를 제곱하고 3으로 나눠지는 리스트
        List<Integer> result = someNumbers.stream()
                .map(x -> x * x)
                .filter(x -> x % 3 == 0)
                .collect(toList());
        printList(result);
        System.out.println();

        // 모든 데이터를 제곱하고 3으로 나눠지는 첫번째 데이터
        Optional<Integer> firstSquare = someNumbers.stream()
                .map(x -> x * x)
                .filter(x -> x % 3 == 0)
                .findFirst();

        if (firstSquare.isPresent()) {
            System.out.println("첫번째 요소 : " + firstSquare.get());
        }
        System.out.println();

        // 모든 데이터를 제곱하고 3으로 나눠지는 리스트 첫번째 데이터
        someNumbers.stream()
                .map(x -> x * x)
                .filter(x -> x % 3 == 0)
                .findFirst()
                .ifPresent(x -> System.out.println("첫번째 요소 : " + x));
        System.out.println();

        // 모든 데이터를 제곱하고 3으로 나눠지는 리스트 아무 데이터
        someNumbers.stream()
                .map(x -> x * x)
                .filter(x -> x % 3 == 0)
                .findAny()
                .ifPresent(x -> System.out.println("아무요소 : " + x));
        System.out.println();
    }

    private <T> void printList(List<T> list) {
        list.stream().forEach(System.out::println);
    }
}
