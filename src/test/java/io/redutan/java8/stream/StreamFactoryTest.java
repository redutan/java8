package io.redutan.java8.stream;

import org.junit.Ignore;
import org.junit.Test;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * 스트림 생성
 * <p>
 *     스트림을 일반적으로 컬렉션에서 생성하지만 직접 스트림을 생성하는 것도 가능하다.
 *
 *     주요API
 *     <pre>
 *         // 문자열로 스트림 생성
 *         public static <T> Stream<T> of(T t)
 *
 *         // 배열로 스트림 생성
 *         public static <T> Stream<T> stream(T[] array)
 *
 *         // 무한스트림 생성 - 초기값 존재. 내부에서 연산이 누적된다.
 *         public static <T> Stream<T> iterate(final T seed, fina UnaryOperator<T> f)
 *
 *         // 무한스트림 생성 - 초기값 미존재. 외부에서 연산을 가져온다.
 *         public static <T> Stream<T> generate(Suppiler<T> s)
 *     </pre>
 * </p>
 * @author myeongju.jung
 */
public class StreamFactoryTest {

    @Test
    @Ignore("절대경로 이슈 때문에 무시처리")
    public void testCreate() throws Exception {
        // 문자열로 스트림을 생성
        Stream<String> stream = Stream.of("Java 8", "Lambda", "In", "Action");
        stream.map(String::toUpperCase).forEach(System.out::println);

        // 배열로 스트림을 생성
        int[] numbers = {1, 2, 3, 4, 5};
        int sum = Arrays.stream(numbers).sum();
        System.out.println(sum);

        // 파일 라인 별로 스트림 생성
        long uniqueWords = 0;
        try (Stream<String> lines = Files.lines(Paths.get("/Users/myeongju.jung/git/java8/src/test/resources/file.txt"),
                Charset.defaultCharset())) {
            uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(" ")))
                    .distinct()
                    .count();
        }
        System.out.println("단어수 : " + uniqueWords);
    }

    @Test
    public void testInfinity() throws Exception {
        // 0부터 2씩 더해가는 무한스트림 생성(짝수)
        // 주의 : 무한스트림은 limit와 함께 써야한다.
        Stream.iterate(0, n -> n + 2)
                .limit(5)
                .forEach(System.out::println);
        System.out.println();

        // 1부터 2씩 더해가는 무한스트림 생성(홀수)
        // 주의 : 무한스트림은 limit와 함께 써야한다.
        Stream.iterate(1, n -> n + 2)
                .limit(5)
                .forEach(System.out::println);
        System.out.println();

        // 피보나치 수열
        Stream.iterate(new int[] {0, 1}, arr -> new int[] {arr[1], arr[0] + arr[1]})
                .limit(5)
                .forEach(arr -> System.out.print("(" + arr[0] + ", " + arr[1] + ")"));
        System.out.println();

        // 피보나치 수
        Stream.iterate(new int[] {0, 1}, arr -> new int[] {arr[1], arr[0] + arr[1]})
                .limit(10)
                .map(arr -> arr[0])
                .forEach(System.out::println);
    }
}
