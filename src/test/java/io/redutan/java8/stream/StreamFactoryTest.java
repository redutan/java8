package io.redutan.java8.stream;

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
        try (Stream<String> lines = Files.lines(Paths.get("file.txt"), Charset.defaultCharset())) {
            uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(" ")))
                    .distinct()
                    .count();
        }
        System.out.println("단어수 : " + uniqueWords);
    }
}
