package io.redutan.java8.lambda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * 공급자(인수없음)
 * @author myeongju.jung
 */
public class SupplierTest {

    @Test
    public void testRandom() throws Exception {
        // 랜덤값을 제공하는 람다식
        Supplier<Double> logic = () -> Math.random();

        // 람다식 적용 시 알고리즘에 의해 적용결과가 리턴된다.
        Double result = logic.get();
        System.out.println("결과 : " + result);
    }

    @Test
    public void testRandoms() throws Exception {
        // 램덤값을 제공하는 람다식
        Supplier<Double> logic = () -> Math.random();

        List<Double> results = random(logic);

        for (Double result : results) {
            System.out.println("랜덤 데이터 : " + result);
        }
    }

    public static <T> List<T> random(Supplier<T> supplier) {
        List<T> results = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            results.add(supplier.get());
        }
        return results;
    }
}
