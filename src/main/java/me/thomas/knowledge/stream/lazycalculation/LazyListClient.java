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
        System.out.println("Invoke primes(numbers.head = " + numbers.head() + ")");
        return new LazyList<>(numbers.head(), () -> primes(numbers.tail().filter(n -> n % numbers.head() != 0)));
    }

    public static void main(String[] args) {
        LazyList<Integer> primes = primes(from(2));
        System.out.println(primes.head());
        System.out.println(primes.tail().head());
        System.out.println(primes.tail().tail().head());
        System.out.println(primes.tail().tail().tail().head());
    }
}
