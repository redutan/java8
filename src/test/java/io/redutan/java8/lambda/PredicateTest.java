package io.redutan.java8.lambda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toList;

/**
 * 술어 (입력 값의 true, false 여부 검사)
 * @author myeongju.jung
 */
public class PredicateTest {
    @Test
    public void testEmptyString() throws Exception {
        // 입력 데이터
        String data = "aaa";

        // 문자열이 빈값이 아닌지를 확인하는 람다식
        Predicate<String> logic = (String s) -> !s.isEmpty();

        // 람다식 적용 시 알고리즘에 의해 적용 결과가 boolean값으로 리턴
        boolean result = logic.test(data);
        System.out.println("결과 : " + result);
    }

    @Test
    public void testEmptyStrings() throws Exception {
        // 입력 데이터
        List<String> datas = Arrays.asList("aaa", "bbb", "", "ccc", "");

        // 문자열이 빈값이 아닌지를 확인하는 람다식
        Predicate<String> logic = (String s) -> !s.isEmpty();

        // 입력 데이터 중에서 빈값이 아닌 데이터만 필터링해서 리턴
        List<String> results = filter(datas, logic);
        results.stream().forEach(s -> System.out.println("필터링 된 데이터 : " + s));
    }

    private static <T> List<T> filter(List<T> datas, Predicate<T> logic) {
        List<T> results = new ArrayList<>();
        return datas.stream()
                .filter(logic)
                .collect(toList());
    }
}
