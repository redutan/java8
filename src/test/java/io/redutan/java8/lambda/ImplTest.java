package io.redutan.java8.lambda;

import org.junit.Test;

/**
 * @author myeongju.jung
 */
public class ImplTest {
    /** 내부 클래스 구현 */
    @Test
    public void testInnerClass() throws Exception {
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("I am running");
            }
        };
        new Thread(r1).start();
    }

    /** 익명 클래스 구현 */
    @Test
    public void testAnonymousClass() throws Exception {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("I am running");
            }
        }).start();
    }

    @Test
    public void testLambda() throws Exception {
        // () : 메소드 시그니쳐
        // -> : 람다 연산자
        // -> 이후 : 메서드 구현
        Runnable r1 = () -> System.out.println("I am running");
        new Thread(r1).start();

        // 더 줄이면
        new Thread(() -> System.out.println("I am running")).start();
    }

    /**
     * 인터페이스 구현 방식
     */
    static class MyRunnable implements  Runnable {
        @Override
        public void run() {
            System.out.println("I am running");
        }

        @Test
        public void test() throws Exception {
            MyRunnable r1 = new MyRunnable();
            new Thread(r1).start();
        }
    }
}