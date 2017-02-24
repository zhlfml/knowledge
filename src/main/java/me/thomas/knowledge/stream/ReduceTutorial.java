package me.thomas.knowledge.stream;

import java.util.Comparator;
import java.util.OptionalInt;
import java.util.function.IntSupplier;
import java.util.stream.Collector;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author zhaoxinsheng
 * @date 8/15/16.
 */
public class ReduceTutorial {

    public static void main(String[] args) {
        OptionalInt optional = IntStream.rangeClosed(1, 100)
                .filter(i -> i % 2 == 1)
                .reduce((a, b) -> a + b);

        if (optional.isPresent()) {
            System.out.println(optional.getAsInt());
        }

        Stream.iterate(new int[] {0, 1}, arr -> new int[] {arr[1], arr[0] + arr[1] })
                .limit(20)
                .forEach(arr -> System.out.print("[" + arr[0] + "," + arr[1] + "]"));
        System.out.println("\n");

        int sum = Stream.iterate(new int[] {0, 1}, arr -> new int[] {arr[1], arr[0] + arr[1] })
                .limit(20)
                .map(arr -> arr[0])
                .reduce(0, (a, b) -> a + b);
        System.out.println(sum);

        Stream.iterate(new int[] {0, 1}, arr -> new int[] {arr[1], arr[0] + arr[1] })
                .limit(20)
                .map(arr -> arr[0])
                .forEach(System.out::println);

        IntStream.generate(new IntSupplier() {

            int prev = 0;
            int curr = 1;

            @Override
            public int getAsInt() {
                int oldPrev = prev;
                int next = prev + curr;
                this.prev = curr;
                this.curr = next;
                return oldPrev;
            }
        }).limit(20)
                .forEach(System.out::println);

        Stream.generate(Math::random)
                .limit(10)
                .sorted(Comparator.comparing(Double::doubleValue).reversed())
                .forEach(System.out::println);
    }
}
