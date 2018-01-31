package me.thomas.knowledge.stream.lazycalculation;

import java.util.concurrent.TimeUnit;

/**
 * @author zhaoxinsheng
 * @date 31/01/2018.
 */
public class LazyListClient {

    public static LazyList<Integer> from(int n) {
        return new LazyList<>(n, () -> from(n + 1));
    }

    public static LazyList<Integer> primes(LazyList<Integer> numbers) {
        return new LazyList<>(numbers.head(), () -> primes(numbers.tail().filter(n -> {
            System.out.println("n = " + n);
            System.out.println("numbers.head(): " + numbers.head());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return n % numbers.head() != 0;
        })));
    }

    public static void main(String[] args) {
        primes(from(2)).printAll();
    }
}
