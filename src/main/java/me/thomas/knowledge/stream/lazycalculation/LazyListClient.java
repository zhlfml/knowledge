package me.thomas.knowledge.stream.lazycalculation;

/**
 * @author zhaoxinsheng
 * @date 31/01/2018.
 */
public class LazyListClient {

    public static LazyList<Integer> from(int n) {
        return new LazyList<>(n, () -> from(n + 1));
    }

    public static LazyList<Integer> primes(LazyList<Integer> numbers) {
        return new LazyList<>(numbers.head(), () -> primes(numbers.tail().filter(n -> n % numbers.head() != 0)));
    }

    public static void main(String[] args) {
        primes(from(2)).printAll();
    }
}
