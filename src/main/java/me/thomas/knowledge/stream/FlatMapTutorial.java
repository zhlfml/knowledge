package me.thomas.knowledge.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author zhaoxinsheng
 * @date 8/9/16.
 */
public class FlatMapTutorial {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        List<Integer> result = numbers.stream().map(d -> d * d).collect(Collectors.toList());
        result.forEach(System.out::println);

        // 数对操作, 返回总和能被3整除的数对.
        List<Integer> number1 = Arrays.asList(1, 2, 3);
        List<Integer> number2 = Arrays.asList(3, 4);
        // 我的版本
        List<int[]> pairs = number1.stream()
                .flatMap(i -> number2.stream().map(j -> new int[] {i, j}))
                .filter(pair -> (pair[0] + pair[1]) % 3 == 0)
                .collect(Collectors.toList());
        System.out.println(pairs);

        // 书上的版本
        List<int[]> pair2 = number1.stream()
                .flatMap(i -> number2.stream()
                        .filter(j -> (i + j) % 3 == 0)
                        .map(j -> new int[] {i, j}))
                .collect(Collectors.toList());
        System.out.println(pair2);
    }
}
