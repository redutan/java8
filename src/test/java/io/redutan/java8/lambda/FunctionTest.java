package io.redutan.java8.lambda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * 말 그대로 함수
 * @author myeongju.jung
 */
public class FunctionTest {

    @Test
    public void testGetLength() throws Exception {
        // 입력데이터
        String data = "redutan";

        // 문자열의 length를 알려주는 람다식
        Function<String, Integer> logic = (String s) -> s.length();

        // 람다식 적용 시 입력객체가 출력객체로 변환되어 리턴된다
        Integer result = logic.apply(data);
        System.out.println("결과 : " + result);
    }

    @Test
    public void testGetLengths() throws Exception {
        // 입력데이터
        List<String> datas = Arrays.asList("redutan", "Eddy", "Dustin");

        // 문자열의 length를 알려주는 람다식
        Function<String, Integer> logic = (String s) -> s.length();

        // 입력데이터 문자열 리스트 전체를 length을 가지는 숫자리스트로 변환
        List<Integer> results = process(datas, logic);

        for (Integer result : results) {
            System.out.println("문자열 length : " + result);
        }
    }

    private static <T, R> List<R> process(List<T> datas, Function<T, R> logic) {
        List<R> results = new ArrayList<>();
        for (T data : datas) {
            logic.apply(data);
        }
        return results;
    }
}
