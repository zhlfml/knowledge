package me.thomas.knowledge.algorithm.sort;

/**
 * 插入排序：需要插入第i个元素，之前的[0 .. i - 1]个元素都是有序的
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
                int temp = array[i]; // 将要被插入的第i个元素
                // 从右到左的循环：将第i个元素从后向前插入正确的位置
                for (; j >= 0 && array[j] > temp; j--) {
                    array[j + 1] = array[j];
                }
                array[j + 1] = temp;
            }
        }
        return array;
    }

}