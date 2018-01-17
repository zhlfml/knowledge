package me.thomas.knowledge.stream;

import java.util.Arrays;

/**
 * 验证同时包含limit和sorted时，这两个方法的调用顺序对结果的影响
 *
 * @author zhaoxinsheng
 * @date 17/01/2018.
 */
public class LimitSorted {

    public static void main(String[] args) {
        int[] array = new int[] {9, 6, 8, 5, 7, 2, 1, 3, 4};
        Arrays.stream(array).sorted().limit(3).forEach(System.out::println);
        // expect: [1, 2, 3] right

        Arrays.stream(array).limit(3).sorted().forEach(System.out::println);
        // expect: [6, 8, 9] right
    }
}
