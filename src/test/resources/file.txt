package io.redutan.java8.stream;

import org.junit.Ignore;
import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 기본형 특화 스트림
 * <p>
 *     java8에서 3가지 기본형(primitive) 특화 스트림을 제공한다.
 *     <pre>
 *         - IntStream
 *         - DoubleStream
 *         - LongStream
 *     </pre>
 *     기존 스트림을 특화 스트림으로 변환
 *     <pre>
 *         IntStream mapToInt(ToIntFunction<? super T> mapper)
 *         DoubleStream mapToDouble(ToDoubleFunction<? super T> mapper)
 *         LongStream mapToLong(ToLongFunction<? super T> mapper)
 *     </pre>
 *     특화 스트림을 객체 스트림으로 복원
 *     <pre>
 *         Stream<Integer> boxed()
 *         Stream<Double> boxed()
 *         Stream<Long> boxed()
 *     </pre>
 *     특정범위의 숫자를 스트림으로 생성
 *     <pre>
 *         // 시작값과 종료값 미포함
 *         public IntStream range(int startInclusive, int endExclusive)
 *         // 시작값과 종료값 포함
 *         public IntStream rangeClosed(int startInclusive, int endExclusive)
 *     </pre>
 * </p>
 * @author myeongju.jung
 */
public class PrimitiveStreamTest {
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


    /** autoboxing 비용 */
    @Test
    @Ignore("10초 정도 걸려서 무시")
    public void testAutoBoxingCost() throws Exception {
        // 캐스팅 비용은 엄청 비싸다!!!!!!!!!
        Instant start = Instant.now();
        Long sum = 0L;
        for (long i = 0; i < Integer.MAX_VALUE; i++) {
            sum += i;
        }
        Instant end = Instant.now();
        System.out.println("Long 결과 : " + sum + ", 수행시간 : " + Duration.between(start, end).toMillis());
    }

    /** 기본타입 비용 */
    @Test
    public void testPrimitiveCost() throws Exception {
        Instant start = Instant.now();
        long sum = 0L;
        for (long i = 0; i < Integer.MAX_VALUE; i++) {
            sum += i;
        }
        Instant end = Instant.now();
        System.out.println("Long 결과 : " + sum + ", 수행시간 : " + Duration.between(start, end).toMillis());
    }

    @Test
    public void testPrimitiveStream() throws Exception {
        // 일반 스트림 : 칼로리 값을 누적하여 합계를 구한다
        int sum1 = menu.stream()
                .map(d -> d.getCalories())
                .reduce(0, (a, b) -> a + b);
        System.out.println(sum1);
        System.out.println();

        // 특화 스트림 : sum을 이용하여 합계를 구한다
        int sum2 = menu.stream()
                .mapToInt(d -> d.getCalories())
                .sum();
        System.out.println(sum2);
        System.out.println();

        // 특화 스트림의 count
        long count = menu.stream().mapToInt(d -> d.getCalories()).count();
        System.out.println(count);

        // 특화 스트림의 sum
        int sum = menu.stream().mapToInt(d -> d.getCalories()).sum();
        System.out.println(sum2);

        // 특화 스트림의 max (없다면 초기값)
        OptionalInt max = menu.stream().mapToInt(Dish::getCalories).max();
        System.out.println(max.orElse(0));

        // 특화 스트림의 min (없다면 초기값)
        OptionalInt min = menu.stream().mapToInt(Dish::getCalories).min();
        System.out.println(min.orElse(0));
    }

    /**
     * 1 ~ 100 까지의 숫자 중 @{code a^2 + b^2 = c^2} 만족하는 {@literal (a, b, c)} 값을 구한다
     * 정수만 대상이므로 정수를 먼저 필터링 후 계산
     * @throws Exception
     */
    @Test
    public void testFilter1() throws Exception {
        Stream<int[]> result = IntStream.rangeClosed(1, 100)
                .boxed()
                .flatMap(a -> IntStream.rangeClosed(a, 100)
                                .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                                .boxed()
                                .map(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)})
                );
        result.limit(3).forEach(arr -> System.out.println("(" + arr[0] + ", " + arr[1] + ", " + arr[2] + ")"));
        System.out.println();
    }

    /**
     * 1 ~ 100 까지의 숫자 중 @{code a^2 + b^2 = c^2} 만족하는 {@literal (a, b, c)} 값을 구한다
     * 모든 값을 계산 후 정수만 필터링
     * @throws Exception
     */
    @Test
    public void testFilter2() throws Exception {
        Stream<double[]> result = IntStream.rangeClosed(1, 100)
                .boxed()
                .flatMap(a -> IntStream.rangeClosed(a, 100)
                                .boxed()
                                .map(b -> new double[]{a, b, Math.sqrt(a * a + b * b)})
                                .filter(arr -> arr[2] % 1 == 0)
                );
        result.limit(3).forEach(arr -> System.out.println("(" + arr[0] + ", " + arr[1] + ", " + arr[2] + ")"));
        System.out.println();
    }
}
