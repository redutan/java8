package io.redutan.java8.lambda;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * 어떤 동작을 소비 (입력만 존재하고 출력은 존재하지 않는다)
 * @author myeongju.jung
 */
public class ConsumerTest {
    @Test
    public void testPrintInteger() throws Exception {
        // 입력데이터
        Integer data = 100;

        // 숫자를 출력하는 동작을 수행하는 람다식
        Consumer<Integer> logic = (Integer i) -> System.out.println("출력된 데이터 : " + i);

        // 람다식 적용 시 알고리즘이 수행된다
        logic.accept(data);
    }

    @Test
    public void testPrintIntegers() throws Exception {
        // 입력데이터
        List<Integer> datas = Arrays.asList(1, 2, 3, 4, 5);

        // 숫자를 출력하는 동작을 수행하는 람다식
        Consumer<Integer> logic = (Integer i) -> System.out.println("출력된 데이터 : " + i);

        // 입력 데이터
        action(datas, logic);
    }

    private <T> void action(List<T> datas, Consumer<T> consumer) {
        for (T data : datas) {
            consumer.accept(data);
        }
    }
}
