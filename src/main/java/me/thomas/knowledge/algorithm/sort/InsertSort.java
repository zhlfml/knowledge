package me.thomas.knowledge.algorithm.sort;

import java.util.Arrays;

/**
 * 插入排序
 *
 * @author zhaoxinsheng
 * @date 2019/02/11 Monday
 */
public class InsertSort {

    public static int[] sort(int[] array) {
        // i需要从1开始
        for (int i = 1; i < array.length; i++) {
            if (array[i] < array[i - 1]) {
                int j = i - 1;
                int temp = array[i];
                for (; j >= 0 && array[j] > temp; j--) {
                    array[j + 1] = array[j];
                }
                array[j + 1] = temp;
            }
        }
        return array;
    }

    public static void main(String[] args) {
        int[] array = {21, 19, 35, 14, 28, 37, 30, 26};
        sort(array);
        Arrays.stream(array).forEach(System.out::println);
    }
}